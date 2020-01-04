package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class GameBoardView extends JFrame {

    private List<JButton> jButtonList;

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

        jButtonList = new ArrayList<>();
        while (jButtonList.size() < 9) {
            jButtonList.add(new JButton());
        }

        createButtons();
        jButtonList.forEach(jPanel::add);
    }

    private void createButtons() {
        jButtonList.forEach(jButton -> {
            jButton.setSize(150, 150);
            jButton.setBackground(new Color(144,238,144));
            jButton.setFont(new Font("TimesRoman", Font.BOLD, 35));
            jButton.setForeground(new Color(47,79,79));
            jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jButton.setEnabled(false);
            jButton.setText("");
        });

        JButton middleButton = jButtonList.get(4);
        middleButton.setText("START");
        middleButton.setEnabled(true);
    }

    public void marksPositionO(int position) {
        marksPosition(position, "O");
    }

    public void marksPositionX(int position) {
        marksPosition(position, "X");
    }

    private void marksPosition(int position, String symbol) {
        JButton jButton = jButtonList.get(position);
        jButton.setText(symbol);
        jButton.setEnabled(false);
    }

    public List<JButton> getJButtons() {
        return jButtonList;
    }

    public void blockAllJButtons() {
        jButtonList.forEach(jButton -> jButton.setEnabled(false));
    }

    public void colorsAllJButton() {
        Color drawColor = new Color(190, 23, 40);
        jButtonList.forEach(jButton -> jButton.setBackground(drawColor));
    }

}
