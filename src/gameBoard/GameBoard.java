package gameBoard;

import movements.MovementTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gameBoard.Symbol.*;

public class GameBoard {
    private Rotation rotation;
    private List<Position> gameBoard;
    private MovementTracking xMovementTracking, oMovementTracking;
    private int movementCounter;

    public GameBoard(int firstMovementShownToUser) {
        this.movementCounter = 0;
        this.gameBoard = new ArrayList<>();
        this.xMovementTracking = new MovementTracking();
        this.oMovementTracking = new MovementTracking();
        this.rotation = new Rotation(getNumberOfRotation(firstMovementShownToUser));
        init();
        setXPositionByPositionShown(firstMovementShownToUser);
    }

    private int getNumberOfRotation(int firstMovementShownToUser) {
        switch (firstMovementShownToUser) {
            case 0: case 1: return 0;
            case 3: case 6: return 1;
            case 7: case 8: return 2;
            case 2: case 5: return 3;
            default: return 4;
        }
    }

    private void init() {
        List<Integer> magicPosition = Arrays.asList(8, 3, 4, 1, 5, 9, 6, 7, 2);
        while (gameBoard.size() < 9) {
            int storedPosition = gameBoard.size();
            gameBoard.add(new Position(rotation.storedPositionToPositionShown(storedPosition), storedPosition, magicPosition.get(storedPosition)));
        }
    }

    private Position getPositionByPositionShown(int positionShown) {
        return this.gameBoard.get(rotation.positionShownToStoredPosition(positionShown));
    }

    public Position getPositionByStoredPosition(int storedPosition) {
        return this.gameBoard.get(storedPosition);
    }

    private void setXPositionByPositionShown(int positionShown) {
        Position position = getPositionByPositionShown(positionShown);
        position.setSymbol(X_POSITION);
        doTrackingForX(position.getStoredPosition());
        xMovementTracking.addPosition(position);
        movementCounter++;
    }

    public void setXPositionByStoredPosition(int storedPosition) {
        this.gameBoard.get(storedPosition).setSymbol(X_POSITION);
        doTrackingForX(storedPosition);
        xMovementTracking.addPosition(this.gameBoard.get(storedPosition));
        movementCounter++;
    }

    public void setOPositionByPositionShown(int positionShown) {
        Position position = getPositionByPositionShown(positionShown);
        position.setSymbol(O_POSITION);

        int row = PositionMapping.getRowByStoredPosition(position.getStoredPosition());
        int column = PositionMapping.getColumnByStoredPosition(position.getStoredPosition());
        oMovementTracking.increaseRow(row);
        oMovementTracking.increaseColumn(column);
        if (row == column) oMovementTracking.increaseDiagonal();
        if (row + column == 2) oMovementTracking.increaseAntiDiagonal();

        oMovementTracking.addPosition(position);
        movementCounter++;
    }

    private void doTrackingForX(int storedPosition) {
        int row = PositionMapping.getRowByStoredPosition(storedPosition);
        int column = PositionMapping.getColumnByStoredPosition(storedPosition);
        xMovementTracking.increaseRow(row);
        xMovementTracking.increaseColumn(column);
        if (row == column) xMovementTracking.increaseDiagonal();
        if (row + column == 2) xMovementTracking.increaseAntiDiagonal();
    }

    public boolean isXWinner(int storedPosition) {
        int row = PositionMapping.getRowByStoredPosition(storedPosition);
        int column = PositionMapping.getColumnByStoredPosition(storedPosition);

        return xMovementTracking.checkIfRowCountEqualsThree(row) ||
                xMovementTracking.checkIfColumnCountEqualsThree(column) ||
                xMovementTracking.checkIfDiagonalCountEqualsThree() ||
                xMovementTracking.checkIfAntiDiagonalCountEqualsThree();
    }

    public boolean isOWinner(int positionShown) {
        int storedPosition = getPositionByPositionShown(positionShown).getStoredPosition();
        int row = PositionMapping.getRowByStoredPosition(storedPosition);
        int column = PositionMapping.getColumnByStoredPosition(storedPosition);

        return oMovementTracking.checkIfRowCountEqualsThree(row) ||
                oMovementTracking.checkIfColumnCountEqualsThree(column) ||
                oMovementTracking.checkIfDiagonalCountEqualsThree() ||
                oMovementTracking.checkIfAntiDiagonalCountEqualsThree();
    }

    public List<Position> getPositionUsedByX() {
        return xMovementTracking.getPositions();
    }

    public boolean isADraw() {
        return movementCounter == 9;
    }

}
