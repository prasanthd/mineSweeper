package org.gic;

import java.util.Scanner;

public class CommandLineHandler {
    private final Scanner input;
    private final CommandLineInputValidator validator;
    public CommandLineHandler() {
        validator = new CommandLineInputValidator();
        input = new Scanner(System.in);
    }

    public Integer getGridSize() {
        System.out.println("Welcome to Minesweeper!");
        while (true) {
            System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
            int gridSize = input.nextInt();
            if(validator.isValidGridSize(gridSize)) {
                return gridSize;
            }
            System.out.println("Grid size must be between 3 to 10");
        }
    }

    public Integer getNoOfMines() {
        while (true) {
            System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
            int noOfMines = input.nextInt();
            if (validator.isValidMineSize(noOfMines)) {
                return noOfMines;
            }
            System.out.println("Mine size must be between 1 to 35% of the total square");
        }
    }

    public Integer[] getASquareToReveal() {
        while (true) {
            System.out.println("\nSelect a square row to reveal (e.g. 2):");
            int row = input.nextInt();
            System.out.println("Select a square column reveal (e.g. 2):");
            int column = input.nextInt();
            if (validator.isValidSquareRevealInput(row , column)) {
                return new Integer[]{row - 1, column - 1};
            }
            System.out.println("Input must be greater than 1");
        }
    }

    public void printGameOver() {
        System.out.println("\nOh no, you detonated a mine! Game over.\n" +
                "Press any key to play again...");
    }

    public void printGameSuccess() {
        System.out.println("\nCongratulations, you have won the game!\n" +
                "Press any key to play again...");
    }

    public void printAdjacentMines(Integer noOfAdjacentMines) {
        System.out.printf("This square contains %s adjacent mines.\n", noOfAdjacentMines);
    }

    public void printUpdatedMinefield(Character[][] mineField) {
        Character[] alphabets = new Character[] {'A', 'B', 'C', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        System.out.println("Here is your updated minefield:");
        for (int index = 0; index < mineField.length; index++) {
            System.out.print(' ') ;
            System.out.print(index + 1) ;
        }

        for (int index = 0; index < mineField.length; index++) {
            System.out.print("\n" + alphabets[index]) ;
            for (int columnIndex = 0; columnIndex < mineField.length; columnIndex++) {
                System.out.printf("%s", mineField[index][columnIndex]);
            }
        }
    }
}
