package org.gic;

import java.util.Random;

public class GameRunner {
    private CommandLineHandler commandLineHandler;

    public GameRunner() {
        this.commandLineHandler = new CommandLineHandler();
    }

    public void start() {
        Integer fieldSize = commandLineHandler.getGridSize();
        Integer noOfMines = commandLineHandler.getNoOfMines();
        MineField mineField = new MineField(fieldSize);
        mineField.placeMines(noOfMines, new Random());
        while(true) {
            Integer[] squareToReveal= commandLineHandler.getASquareToReveal();
            mineField.sweep(squareToReveal[0], squareToReveal[1]);
            commandLineHandler.printAdjacentMines(mineField.calculateAdjacentMines(squareToReveal[0], squareToReveal[1]));
            commandLineHandler.printUpdatedMinefield(mineField.getMaskedMineField());
            if (mineField.isFieldBombed()){
                commandLineHandler.printGameOver();
                start();
            }
            if (mineField.isFullySwept()){
                commandLineHandler.printGameSuccess();
                start();
            }
        }
    }
}
