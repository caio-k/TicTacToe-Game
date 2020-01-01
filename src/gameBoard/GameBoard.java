package gameBoard;

import movements.MovementTracking;

import java.util.List;

import static gameBoard.Symbol.*;

public class GameBoard {
    private Rotation rotation;
    private Position[] gameBoard;
    private MovementTracking xMovementTracking, oMovementTracking;

    public GameBoard(int firstMovementShownToUser) {
        gameBoard = new Position[9];
        xMovementTracking = new MovementTracking();
        oMovementTracking = new MovementTracking();
        rotation = new Rotation(getNumberOfRotation(firstMovementShownToUser));
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
        int[] magicPosition = {8, 3, 4, 1, 5, 9, 6, 7, 2};
        for (int storedPosition = 0; storedPosition < gameBoard.length; storedPosition++) {
            gameBoard[storedPosition] = new Position(rotation.storedPositionToPositionShown(storedPosition) , storedPosition, magicPosition[storedPosition]);
            gameBoard[storedPosition].setSymbol(EMPTY);
        }
    }

    private Position getPositionByPositionShown(int positionShown) {
        return this.gameBoard[rotation.positionShownToStoredPosition(positionShown)];
    }

    public Position getPositionByStoredPosition(int storedPosition) {
        return this.gameBoard[storedPosition];
    }

    private void setXPositionByPositionShown(int positionShown) {
        Position position = getPositionByPositionShown(positionShown);
        position.setSymbol(X_POSITION);
        doTrackingForX(position.getStoredPosition());
        xMovementTracking.addPosition(position);
    }

    public void setXPositionByStoredPosition(int storedPosition) {
        this.gameBoard[storedPosition].setSymbol(X_POSITION);
        doTrackingForX(storedPosition);
        xMovementTracking.addPosition(this.gameBoard[storedPosition]);
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

        return xMovementTracking.checkIfRowCountEqualsThree(row) ||
                xMovementTracking.checkIfColumnCountEqualsThree(column) ||
                xMovementTracking.checkIfDiagonalCountEqualsThree() ||
                xMovementTracking.checkIfAntiDiagonalCountEqualsThree();
    }

    public List<Position> getPositionUsedByX() {
        return xMovementTracking.getPositions();
    }

}
