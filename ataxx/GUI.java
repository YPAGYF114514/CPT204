package ataxx;

// Optional Task: The GUI for the Ataxx Game

class GUI implements View, CommandSource, Reporter {

    // Complete the codes here
    GUI(String ataxx) {

    }

    // Add some codes here






    // These methods could be modified

    @Override
    public void update(Board board) {

    }

    @Override
    public String getCommand(String prompt) {
        return null;
    }

    @Override
    public void announceWinner(PieceState state) {

    }

    @Override
    public void announceMove(Move move, PieceState player) {

    }

    @Override
    public void message(String format, Object... args) {

    }

    @Override
    public void error(String format, Object... args) {

    }

    public void setVisible(boolean b) {

    }

    public void pack() {

    }

}









//package ataxx;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//class GUI extends JFrame implements View, CommandSource, Reporter {
//    private final BoardPanel boardPanel;
//    private final JTextArea messageArea;
//
//    GUI(String title) {
//        super(title);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        boardPanel = new BoardPanel();
//        add(boardPanel, BorderLayout.CENTER);
//
//        messageArea = new JTextArea();
//        messageArea.setEditable(false);
//        JScrollPane messageScrollPane = new JScrollPane(messageArea);
//        messageScrollPane.setPreferredSize(new Dimension(400, 100));
//        add(messageScrollPane, BorderLayout.SOUTH);
//
//        pack();
//        setVisible(true);
//    }
//
//    @Override
//    public void update(Board board) {
//        boardPanel.updateBoard(board);
//    }
//
//    @Override
//    public String getCommand(String prompt) {
//        return JOptionPane.showInputDialog(this, prompt);
//    }
//
//    @Override
//    public void announceWinner(PieceState state) {
//        JOptionPane.showMessageDialog(this, state + " wins!");
//    }
//
//    @Override
//    public void announceMove(Move move, PieceState player) {
//        message(String.format("%s: %s%n", player, move));
//    }
//
//    @Override
//    public void message(String format, Object... args) {
//        messageArea.append(String.format(format, args));
//    }
//
//    @Override
//    public void error(String format, Object... args) {
//        JOptionPane.showMessageDialog(this, String.format(format, args), "Error", JOptionPane.ERROR_MESSAGE);
//    }
//
//    private class BoardPanel extends JPanel {
//        private final JButton[][] buttons;
//
//        BoardPanel() {
//            setLayout(new GridLayout(7, 7));
//            buttons = new JButton[7][7];
//
//            for (char r = 6; r >= 1; r -= 1) {
//                for (char c = 1; c < 7; c += 1) {
//                    JButton button = new JButton();
//                    button.addActionListener(new ButtonListener(r, c));
//                    buttons[r][c] = button;
//                    add(button);
//                }
//            }
//
////            for (char r = '7'; r >= '1'; r--) {
////                for (char c = 'g'; c >= 'a'; c--) {
////                    JButton button = new JButton();
////                    button.addActionListener(new ButtonListener(r, c));
////                    buttons[r][c] = button;
////                    add(button);
////                }
////            }
//
//        }
//
//        void updateBoard(Board board) {
//            for (int row = 0; row < 7; row++) {
//                for (int col = 0; col < 7; col++) {
////                    PieceState state = board.getPiece(row, col);
//                    PieceState state = board.getContent((char) row, (char) col);
//                    buttons[row][col].setText(state.toString());
//                }
//            }
//        }
//
//        private class ButtonListener implements ActionListener {
//            private final int row;
//            private final int col;
//
//            ButtonListener(int row, int col) {
//                this.row = row;
//                this.col = col;
//            }
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Implement button click logic here
//            }
//        }
//    }
//}

