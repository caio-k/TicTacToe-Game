package movements;

import gameBoard.Position;

import java.util.ArrayList;
import java.util.List;

public class MovementTracking {
    private int[] row, column;
    private int diagonal, antiDiagonal;
    private List<Position> positions;

    public MovementTracking() {
        this.row = new int[3];
        this.column = new int[3];
        this.diagonal = 0;
        this.antiDiagonal = 0;
        this.positions = new ArrayList<>();
    }

    public void increaseRow(int numberRow) {
        this.row[numberRow]++;
    }

    public boolean checkIfRowCountEqualsThree(int numberRow) {
        return this.row[numberRow] == 3;
    }

    public void increaseColumn(int numberColumn) {
        this.column[numberColumn]++;
    }

    public boolean checkIfColumnCountEqualsThree(int numberColumn) {
        return this.column[numberColumn] == 3;
    }

    public void increaseDiagonal() {
        this.diagonal++;
    }

    public boolean checkIfDiagonalCountEqualsThree() {
        return this.diagonal == 3;
    }

    public void increaseAntiDiagonal() {
        this.antiDiagonal++;
    }

    public boolean checkIfAntiDiagonalCountEqualsThree() {
        return this.antiDiagonal == 3;
    }

    public void addPosition(Position position) {
        this.positions.add(position);
    }

    public List<Position> getPositions() {
        return this.positions;
    }

}
