package controller;

import gameBoard.GameBoard;
import movements.ComputerMovements;
import view.GameBoardView;

import javax.swing.JButton;
import java.util.List;

public class Controller {
    private GameBoardView gameBoardView;
    private GameBoard gameBoard;
    private ComputerMovements computerMovements;
    private List<JButton> jButtonList;
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
        jButtonList = gameBoardView.getJButtons();

        JButton middleJButton = jButtonList.get(4);
        middleJButton.addActionListener(e -> {
            if (firstClick) initGame();
            else userMovement(middleJButton);
        });
        jButtonList.remove(middleJButton);
        jButtonList.forEach(jButton -> jButton.addActionListener(e -> userMovement(jButton)));
        jButtonList.add(4, middleJButton);
    }

    private void initGame() {
        this.firstClick = false;
        jButtonList.forEach(jButton -> {
            jButton.setText("");
            jButton.setEnabled(true);
        });
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
        int positionShown = jButtonList.indexOf(jButton);
        gameBoard.setOPositionByPositionShown(positionShown);
        gameBoardView.marksPositionO(positionShown);

        if (gameBoard.isOWinner(positionShown)) {
            gameBoardView.blockAllJButtons();
        } else {
            pcMovement();
        }
    }
}
