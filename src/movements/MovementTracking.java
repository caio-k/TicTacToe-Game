package movements;

import gameBoard.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MovementTracking {
    private int[] row, column;
    private int diagonal, antiDiagonal;
    private List<Position> positions;
    private List<Integer> winningsPositions;

    public MovementTracking() {
        this.row = new int[3];
        this.column = new int[3];
        this.diagonal = 0;
        this.antiDiagonal = 0;
        this.positions = new ArrayList<>();
        this.winningsPositions = Collections.emptyList();
    }

    public void increaseRow(int numberRow) {
        this.row[numberRow]++;
    }

    public boolean checkIfRowCountEqualsThree(int numberRow) {
        if(this.row[numberRow] == 3) {
            int firstPosition = 3*numberRow;
            winningsPositions = Arrays.asList(firstPosition, firstPosition + 1, firstPosition + 2);
            return true;
        }
        return false;
    }

    public void increaseColumn(int numberColumn) {
        this.column[numberColumn]++;
    }

    public boolean checkIfColumnCountEqualsThree(int numberColumn) {
        if(this.column[numberColumn] == 3) {
            winningsPositions = Arrays.asList(numberColumn, numberColumn + 3, numberColumn + 6);
            return true;
        }
        return false;
    }

    public void increaseDiagonal() {
        this.diagonal++;
    }

    public boolean checkIfDiagonalCountEqualsThree() {
        if(this.diagonal == 3) {
            winningsPositions = Arrays.asList(0, 4, 8);
            return true;
        }
        return false;
    }

    public void increaseAntiDiagonal() {
        this.antiDiagonal++;
    }

    public boolean checkIfAntiDiagonalCountEqualsThree() {
        if (this.antiDiagonal == 3) {
            winningsPositions = Arrays.asList(2, 4, 6);
            return true;
        }
        return false;
    }

    public void addPosition(Position position) {
        this.positions.add(position);
    }

    public List<Position> getPositions() {
        return this.positions;
    }

    public List<Integer> getWinningsPositions() {
        return this.winningsPositions;
    }

    public int getWinningPositionsSize() {
        return this.winningsPositions.size();
    }

}
