package play;

import controller.Controller;
import movements.ComputerMovements;
import view.GameBoardView;

public class TicTacToe {
    public static void main(String[] args) {
        ComputerMovements computerMovements = new ComputerMovements();
        GameBoardView gameBoardView = new GameBoardView();
        Controller controller = new Controller(gameBoardView, computerMovements);
        controller.initController();
    }
}
