package org.gic;

public class CommandLineInputValidator {
    public boolean isValidGridSize(Integer gridSize) {
        return gridSize >= 3 && gridSize <= 10;
    }

    public boolean isValidMineSize(Integer noOfMines) {
        return noOfMines >= 1;
    }

    public boolean isValidSquareRevealInput(Integer row, int column) {
        return row >= 1 && column >= 1;
    }
}
