package gameBoard;

public class PositionMapping {

    public static int anyPositionToRotatedPosition(int anyPosition) {
        switch (anyPosition) {
            case 0: return 0;
            case 1: return 1;
            case 2: return 2;
            case 3: return 7;
            case 5: return 3;
            case 6: return 6;
            case 7: return 5;
            case 8: return 4;
            default: return -1;
        }
    }

    public static int getRowByStoredPosition(int storedPosition) {
        return (storedPosition - (storedPosition % 3)) / 3;
    }

    public static int getColumnByStoredPosition(int storedPosition) {
        return storedPosition % 3;
    }

    public static int magicPositionToStoredPosition(int magicPosition) {
        switch (magicPosition) {
            case 1: return 3;
            case 2: return 8;
            case 3: return 1;
            case 4: return 2;
            case 5: return 4;
            case 6: return 6;
            case 7: return 7;
            case 8: return 0;
            case 9: return 5;
            default: return -1;
        }
    }
}
