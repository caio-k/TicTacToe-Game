package gameBoard;

import static gameBoard.Symbol.*;

public class Position {
    private int positionShownToUser;
    private int storedPosition;
    private int magicPosition;
    private Symbol symbol;

    public Position(int positionShownToUser, int storedPosition, int magicPosition) {
        this.positionShownToUser = positionShownToUser;
        this.storedPosition = storedPosition;
        this.magicPosition = magicPosition;
    }

    public int getPositionShownToUser() {
        return this.positionShownToUser;
    }

    public int getStoredPosition() {
        return this.storedPosition;
    }

    protected void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public boolean isXPosition() {
        return this.symbol.equals(X_POSITION);
    }

    public boolean isOPosition() {
        return this.symbol.equals(O_POSITION);
    }

    public boolean isEmptyPosition() {
        return this.symbol.equals(EMPTY);
    }

    public int getMagicPosition() {
        return this.magicPosition;
    }
}
