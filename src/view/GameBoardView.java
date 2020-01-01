package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Font;

public class GameBoardView extends JFrame {

    private JButton[] jButtons;

    public GameBoardView() {
        super("Tic Tac Toe");

        createView();

        this.setSize(450, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void createView() {
        JPanel jPanel = new JPanel(new GridLayout(3,3));
        this.getContentPane().add(jPanel);

        jButtons = new JButton[9];

        for (int index = 0; index < jButtons.length; index++) {
            jButtons[index] = new JButton();
        }

        createButtons();

        for (JButton jButton : jButtons) {
            jPanel.add(jButton);
        }
    }

    private void createButtons() {

        for (JButton jButton : jButtons) {
            jButton.setSize(150, 150);
            jButton.setBackground(new Color(144,238,144));
            jButton.setFont(new Font("TimesRoman", Font.BOLD, 35));
            jButton.setForeground(new Color(47,79,79));
            jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jButton.setEnabled(false);
            jButton.setText("");
        }

        jButtons[4].setText("START");
        jButtons[4].setEnabled(true);
    }

    public void marksPositionO(int position) {
        jButtons[position].setText("O");
        jButtons[position].setEnabled(false);
    }

    public void marksPositionX(int position) {
        jButtons[position].setText("X");
        jButtons[position].setEnabled(false);
    }

    public JButton[] getJButtons() {
        return jButtons;
    }

    public void blockAllJButtons() {
        for (JButton jButton : jButtons) {
            jButton.setEnabled(false);
        }
    }

}
