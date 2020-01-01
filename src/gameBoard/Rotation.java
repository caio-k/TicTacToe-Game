package gameBoard;

public class Rotation {
    private final int numberOfRotation;
    private final int[] DISPLACEMENTS = {2, 4, 6, 2, -2, -4, -6, -2};

    public Rotation(int numberOfRotation) {
        this.numberOfRotation = numberOfRotation;
    }

    public int positionShownToStoredPosition(int positionShown) {
        int rotatedPosition = PositionMapping.anyPositionToRotatedPosition(positionShown);
        int storedPosition = positionShown;
        int loops = 0;

        if (rotatedPosition < 0) return 4;

        while (loops < numberOfRotation) {
            storedPosition += DISPLACEMENTS[rotatedPosition];
            rotatedPosition = (rotatedPosition + 2) % 8;
            loops++;
        }
        return storedPosition;
    }

    public int storedPositionToPositionShown(int storedPosition) {
        int rotatedPosition = PositionMapping.anyPositionToRotatedPosition(storedPosition);
        int positionShown = storedPosition;
        int loops = 0;

        if (rotatedPosition < 0) return 4;

        while (loops < numberOfRotation) {
            if (rotatedPosition >= 2) rotatedPosition -= 2;
            else rotatedPosition = rotatedPosition == 0 ? 6 : 7;
            positionShown -= DISPLACEMENTS[rotatedPosition];
            loops++;
        }
        return positionShown;
    }
}
