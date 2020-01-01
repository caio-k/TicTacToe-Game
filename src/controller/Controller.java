package controller;

import gameBoard.GameBoard;
import movements.ComputerMovements;
import view.GameBoardView;

import javax.swing.JButton;
import java.util.Arrays;

public class Controller {
    private GameBoardView gameBoardView;
    private GameBoard gameBoard;
    private ComputerMovements computerMovements;
    private JButton[] jButtons;
    private boolean firstClick;
    private int firstComputerMovement;

    public Controller(GameBoardView gameBoardView, ComputerMovements computerMovements) {
        this.gameBoardView = gameBoardView;
        this.computerMovements = computerMovements;
        this.firstComputerMovement = computerMovements.firstMovement();
        this.gameBoard = new GameBoard(firstComputerMovement);
        this.firstClick = true;
    }

    public void initController() {
        int index = 0;
        jButtons = gameBoardView.getJButtons();

        for (JButton jButton : jButtons) {
            if (index == 4) {
                jButton.addActionListener(event -> {
                    if (firstClick) initGame();
                    else userMovement(jButton);
                });
            } else {
                jButton.addActionListener(event -> userMovement(jButton));
            }
            index++;
        }
    }

    private void initGame() {
        this.firstClick = false;
        for (JButton jButton : jButtons) {
            jButton.setText("");
            jButton.setEnabled(true);
        }
        gameBoardView.marksPositionX(this.firstComputerMovement);
    }

    private void pcMovement() {
        int storedPosition = computerMovements.nextStep(gameBoard);
        gameBoard.setXPositionByStoredPosition(storedPosition);
        gameBoardView.marksPositionX(gameBoard.getPositionByStoredPosition(storedPosition).getPositionShownToUser());

        if (gameBoard.isXWinner(storedPosition)) {
            gameBoardView.blockAllJButtons();
        }
    }

    private void userMovement(JButton jButton) {
        int positionShown = Arrays.asList(jButtons).indexOf(jButton);
        gameBoard.setOPositionByPositionShown(positionShown);
        gameBoardView.marksPositionO(positionShown);

        if (gameBoard.isOWinner(positionShown)) {
            gameBoardView.blockAllJButtons();
        } else {
            pcMovement();
        }
    }
}
