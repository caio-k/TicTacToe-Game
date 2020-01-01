package movements;

import gameBoard.GameBoard;
import gameBoard.Position;
import gameBoard.PositionMapping;

import java.util.ArrayList;
import java.util.List;

public class ComputerMovements {
    private int step;

    public int firstMovement() {
        this.step = 1;
        int random = 1 + (int) (Math.random() * 100);

        if (random < 20) return 0;
        else if (random < 40) return 2;
        else if (random < 60) return 4;
        else if (random < 80) return 6;
        else return 8;
    }

    public int nextStep(GameBoard gameBoard) {
        this.step++;
        switch (this.step) {
            case 2: return secondMovement(gameBoard);
            case 3: return thirdMovement(gameBoard);
            case 4: return fourthMovement(gameBoard);
            case 5: return fifthMovement(gameBoard);
            default: return -1;
        }
    }

    private int secondMovement(GameBoard gameBoard) {
        if (gameBoard.getPositionByStoredPosition(0).isXPosition()) {
            if (gameBoard.getPositionByStoredPosition(1).isOPosition() || gameBoard.getPositionByStoredPosition(3).isOPosition()) return 4;
            else if (gameBoard.getPositionByStoredPosition(2).isOPosition() || gameBoard.getPositionByStoredPosition(6).isOPosition()) return 8;
            else return 6;
        } else {
            if (gameBoard.getPositionByStoredPosition(0).isOPosition() || gameBoard.getPositionByStoredPosition(8).isOPosition()) return 2;
            else return 0;
        }
    }

    private int thirdMovement(GameBoard gameBoard) {
        int magicSum = 0;

        for (Position position : gameBoard.getPositionUsedByX())
            magicSum += position.getMagicPosition();

        int thirdPosition = PositionMapping.magicPositionToStoredPosition(15 - magicSum);

        return gameBoard.getPositionByStoredPosition(thirdPosition).isEmptyPosition() ?
                thirdPosition : getSecondBestChoiceForThirdPosition(gameBoard);
    }

    private int fourthMovement(GameBoard gameBoard) {
        List<Position> positionList = gameBoard.getPositionUsedByX();
        List<Integer> sumList = new ArrayList<>();

        sumList.add(positionList.get(0).getMagicPosition() + positionList.get(1).getMagicPosition());
        sumList.add(positionList.get(0).getMagicPosition() + positionList.get(2).getMagicPosition());
        sumList.add(positionList.get(1).getMagicPosition() + positionList.get(2).getMagicPosition());

        for (Integer sum : sumList) {
            int position = PositionMapping.magicPositionToStoredPosition(15 - sum);
            if (position >= 0 && gameBoard.getPositionByStoredPosition(position).isEmptyPosition()) return position;
        }

        if (gameBoard.getPositionByStoredPosition(3).isEmptyPosition()) return 3;
        else if (gameBoard.getPositionByStoredPosition(1).isEmptyPosition()) return 1;
        else return 7;
    }

    private int fifthMovement(GameBoard gameBoard) {
        int index = 0;
        while(!gameBoard.getPositionByStoredPosition(index).isEmptyPosition()) index++;

        return index;
    }

    private int getSecondBestChoiceForThirdPosition(GameBoard gameBoard) {
        int firstOPosition = 0;

        while (!gameBoard.getPositionByStoredPosition(firstOPosition).isOPosition()) firstOPosition++;

        switch (firstOPosition) {
            case 0: return 3;
            case 1: case 7: return 6;
            case 2: return gameBoard.getPositionByStoredPosition(4).isOPosition() ? 6 : 5;
            case 3:
                if (gameBoard.getPositionByStoredPosition(4).isOPosition()) return 5;
                else if (gameBoard.getPositionByStoredPosition(5).isOPosition()) return 4;
                else if (gameBoard.getPositionByStoredPosition(8).isOPosition()) return 2;
                else return 4;
            case 6: return 7;
            default: return 2;
        }
    }
}
