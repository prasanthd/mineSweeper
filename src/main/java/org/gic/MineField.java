package org.gic;

import java.util.Arrays;
import java.util.Random;

public class MineField {
    private Boolean isFieldBombed = false;
    private Character[][] mineField;
    private Integer size;

    public MineField(Integer size) {
        this.size = size;
        this.mineField = new Character[size][size];
        for (Character[] sweepRow : mineField) {
            Arrays.fill(sweepRow, '_');
        }
    }

    public Character[][] placeMines(Integer numberOfMines, Random random) {
        for (int index = 0; index < numberOfMines; index++) {
            int row = random.nextInt(size);
            int column = random.nextInt(size);
            if (mineField[row][column] != '*') {
                mineField[row][column] = '*';
            } else {
                index--;
            }
        }
        return mineField;
    }

    public Character[][] sweep(Integer row, Integer column) {
        if (mineField[row][column] == '*') {
            this.isFieldBombed = true;
            return mineField;
        }
        sweepSquare(row, column);
        return mineField;
    }

    private void sweepSquare(Integer row, Integer column) {
        if (isOutOfFieldBoundary(row, column) ||
                isAlreadyUpdated(row, column) ||
                isItMineSquare(row, column)) {
            return;
        }

        int numberOfAdjacentMines = calculateAdjacentMines(row, column);
        mineField[row][column] = (char) ('0' + numberOfAdjacentMines);

        if (numberOfAdjacentMines == 0) {
            sweepSquare(row - 1, column - 1);
            sweepSquare(row - 1, column + 1);
            sweepSquare(row + 1, column - 1);
            sweepSquare(row + 1, column + 1);
            sweepSquare(row, column - 1);
            sweepSquare(row, column + 1);
            sweepSquare(row - 1, column);
            sweepSquare(row + 1, column);
        }
    }

    public Boolean isFieldBombed() {
        return this.isFieldBombed;
    }

    public Boolean isFullySwept() {
        int count = 0;
        for (Character[] row : mineField) {
            for (Character square : row) {
                if (square != '_') {
                    count++;
                }
            }
        }
        return count == size * size;
    }

    public Integer calculateAdjacentMines(Integer row, Integer column) {
        Integer count = 0;
        for (int index = row - 1; index <= row + 1; index++) {
            for (int columnIndex = column - 1; columnIndex <= column + 1; columnIndex++) {
                if (index >= 0 && columnIndex >= 0 && index < mineField.length
                        && columnIndex < mineField[index].length && mineField[index][columnIndex] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    private Boolean isItMineSquare(Integer sweepRow, Integer sweepColumn) {
        return mineField[sweepRow][sweepColumn] == '*';
    }

    private Boolean isAlreadyUpdated(Integer sweepRow, Integer sweepColumn) {
        return mineField[sweepRow][sweepColumn] != '_';
    }

    private Boolean isOutOfFieldBoundary(Integer sweepRow, Integer sweepColumn) {
        return sweepRow < 0 || sweepRow >= mineField.length || sweepColumn < 0 || sweepColumn >= mineField[0].length;
    }

    public Character[][] getMaskedMineField() {
        Character[][] maskedFields = new Character[size][size];
        for (int index = 0; index < size; index++) {
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                if (mineField[index][columnIndex] == '*') {
                    maskedFields[index][columnIndex] = '_';
                } else {
                    maskedFields[index][columnIndex] = mineField[index][columnIndex];
                }
            }
        }
        return maskedFields;
    }
}
