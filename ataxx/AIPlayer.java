//package ataxx;
//
//import java.util.ArrayList;
//
//// Final Project Part A.2 Ataxx AI Player (A group project)
//
///** A Player that computes its own moves. */
//class AIPlayer extends Player {
//
//
//    /** A new AIPlayer for GAME that will play MYCOLOR.
//     *  SEED is used to initialize a random-number generator,
//     *  increase the value of SEED would make the AIPlayer move automatically.
//     *  Identical seeds produce identical behaviour. */
//    AIPlayer(Game game, PieceState myColor, long seed) {
//        super(game, myColor);
//    }
//
//
//
//    @Override
//    boolean isAuto() {
//        return true;
//    }
//
//    @Override
//    String getAtaxxMove() {
//        Move move = findMove();
//        getAtaxxGame().reportMove(move, getMyState());
//        return move.toString();
//    }
//
//    /** Return a move for me from the current position, assuming there
//     *  is a move. */
//    private Move findMove() {
//        Board b = new Board(getAtaxxBoard());
//        lastFoundMove = null;
//
//        // Here we just have the simple AI to randomly move.
//        // However, it does not meet with the requirements of Part A.2.
//        // Therefore, the following codes should be modified
//        // in order to meet with the requirements of Part A.2.
//        // You can create add your own method and put your method here.
//
//        ArrayList<Move> listOfMoves =
//                possibleMoves(b, b.nextMove());
//        int moveArrayLength = listOfMoves.size();
//        int randomIndex = (int) (Math.random() * moveArrayLength);
//        for(int i = 0; i < moveArrayLength; i++){
//            if (i == randomIndex){
//                b.createMove(listOfMoves.get(i));
//                lastFoundMove = listOfMoves.get(i);
//            }
//        }
//
//
//
//        // Please do not change the codes below
//        if (lastFoundMove == null) {
//            lastFoundMove = Move.pass();
//        }
//        return lastFoundMove;
//    }
//
//
//    /** The move found by the last call to the findMove method above. */
//    private Move lastFoundMove;
//
//
//    /** Return all possible moves for a color.
//     * @param board the current board.
//     * @param myColor the specified color.
//     * @return an ArrayList of all possible moves for the specified color. */
//    private ArrayList<Move> possibleMoves(Board board, PieceState myColor) {
//        ArrayList<Move> possibleMoves = new ArrayList<>();
//        for (char row = '7'; row >= '1'; row--) {
//            for (char col = 'a'; col <= 'g'; col++) {
//                int index = Board.index(col, row);
//                if (board.getContent(index) == myColor) {
//                    ArrayList<Move> addMoves
//                            = assistPossibleMoves(board, row, col);
//                    possibleMoves.addAll(addMoves);
//                }
//            }
//        }
//        return possibleMoves;
//    }
//
//    /** Returns an Arraylist of legal moves.
//     * @param board the board for testing
//     * @param row the row coordinate of the center
//     * @param col the col coordinate of the center */
//    private ArrayList<Move>
//    assistPossibleMoves(Board board, char row, char col) {
//        ArrayList<Move> assistPossibleMoves = new ArrayList<>();
//        for (int i = -2; i <= 2; i++) {
//            for (int j = -2; j <= 2; j++) {
//                if (i != 0 || j != 0) {
//                    char row2 = (char) (row + j);
//                    char col2 = (char) (col + i);
//                    Move currMove = Move.move(col, row, col2, row2);
//                    if (board.moveLegal(currMove)) {
//                        assistPossibleMoves.add(currMove);
//                    }
//                }
//            }
//        }
//        return assistPossibleMoves;
//    }
//}


package ataxx;

import java.util.ArrayList;

// Final Project Part A.2 Ataxx AI Player (A group project)

/** A Player that computes its own moves. */
class AIPlayer extends Player {


    /** A new AIPlayer for GAME that will play MYCOLOR.
     *  SEED is used to initialize a random-number generator,
     *  increase the value of SEED would make the AIPlayer move automatically.
     *  Identical seeds produce identical behaviour. */
    AIPlayer(Game game, PieceState myColor, long seed) {
        super(game, myColor);
    }



    @Override
    boolean isAuto() {
        return true;
    }

    @Override
    String getAtaxxMove() {
        Move move = findMove();
        getAtaxxGame().reportMove(move, getMyState());
        return move.toString();
    }

    /** Return a move for me from the current position, assuming there
     *  is a move. */
    private Move findMove() {
        Board b = new Board(getAtaxxBoard());
        lastFoundMove = null;
        int maxDepth = 3; // set this to the depth you want to search

        // use negative infinity and positive infinity for initial alpha and beta
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;

        double bestScore = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        ArrayList<Move> listOfMoves = possibleMoves(b, b.nextMove());

        for (Move move : listOfMoves) {
            b.createMove(move);
            double score = minimax(b, maxDepth, alpha, beta, false);
            b.undoMove(move);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }

            alpha = Math.max(alpha, bestScore);

            if (beta <= alpha) {
                break; // beta cut-off
            }
        }

        lastFoundMove = bestMove != null ? bestMove : Move.pass();
        return lastFoundMove;
    }

    private double minimax(Board board, int depth, double alpha, double beta, boolean maximizingPlayer) {
        if (depth == 0 || board.gameOver()) {
            return evaluate(board);
        }

        ArrayList<Move> listOfMoves = possibleMoves(board, board.nextMove());

        if (maximizingPlayer) {
            double maxEval = Double.NEGATIVE_INFINITY;
            for (Move move : listOfMoves) {
                board.createMove(move);
                double eval = minimax(board, depth - 1, alpha, beta, false);
                board.undoMove(move);

                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);

                if (beta <= alpha) {
                    break; // alpha cut-off
                }
            }
            return maxEval;
        } else {
            double minEval = Double.POSITIVE_INFINITY;
            for (Move move : listOfMoves) {
                board.createMove(move);
                double eval = minimax(board, depth - 1, alpha, beta, true);
                board.undoMove(move);

                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);

                if (beta <= alpha) {
                    break; // beta cut-off
                }
            }
            return minEval;
        }
    }





    /** The move found by the last call to the findMove method above. */
    private Move lastFoundMove;


    /** Return all possible moves for a color.
     * @param board the current board.
     * @param myColor the specified color.
     * @return an ArrayList of all possible moves for the specified color. */
    private ArrayList<Move> possibleMoves(Board board, PieceState myColor) {
        ArrayList<Move> possibleMoves = new ArrayList<>();
        for (char row = '7'; row >= '1'; row--) {
            for (char col = 'a'; col <= 'g'; col++) {
                int index = Board.index(col, row);
                if (board.getContent(index) == myColor) {
                    ArrayList<Move> addMoves
                            = assistPossibleMoves(board, row, col);
                    possibleMoves.addAll(addMoves);
                }
            }
        }
        return possibleMoves;
    }

    /** Returns an Arraylist of legal moves.
     * @param board the board for testing
     * @param row the row coordinate of the center
     * @param col the col coordinate of the center */
    private ArrayList<Move>
    assistPossibleMoves(Board board, char row, char col) {
        ArrayList<Move> assistPossibleMoves = new ArrayList<>();
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (i != 0 || j != 0) {
                    char row2 = (char) (row + j);
                    char col2 = (char) (col + i);
                    Move currMove = Move.move(col, row, col2, row2);
                    if (board.moveLegal(currMove)) {
                        assistPossibleMoves.add(currMove);
                    }
                }
            }
        }
        return assistPossibleMoves;
    }
}
