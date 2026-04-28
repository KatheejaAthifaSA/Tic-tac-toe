package black;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tictactoe extends JFrame implements ActionListener {
    public final int SIZE = 3;
    public JButton[][] buttons = new JButton[SIZE][SIZE];
    public JLabel statusLabel = new JLabel("Player X's Turn");
    public char currentPlayer = 'X';
    public boolean gameActive = true;

    public Tictactoe() {
        setTitle("Tic Tac Toe - DevOps Edition");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        JPanel board = new JPanel(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                board.add(buttons[i][j]);
            }
        }
        add(board, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (gameActive && btn.getText().equals("")) {
            btn.setText(String.valueOf(currentPlayer));
            if (checkWin()) {
                statusLabel.setText("Player " + currentPlayer + " Wins!");
                gameActive = false;
            } else if (isBoardFull()) {
                statusLabel.setText("It's a Tie!");
                gameActive = false;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText("Player " + currentPlayer + "'s Turn");
            }
        }
    }

    public boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (checkMatch(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (checkMatch(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        return checkMatch(buttons[0][0], buttons[1][1], buttons[2][2]) || 
               checkMatch(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean checkMatch(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().isEmpty() && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().equals("")) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tictactoe().setVisible(true));
    }
}