package org.gic;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MineFieldTest {
    private MineField mineField;

    @Before
    public void setUp() throws Exception {
        mineField = new MineField(3);
    }

    @Test
    public void shouldNotPlaceAnyMinesWhenNumberOfMinesGivenIsZero() {
        Random random = new Random();
        Character[][] expectedMineField = new Character[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};

        Character[][] plantedMineField = mineField.placeMines(0, random);

        assertEquals(expectedMineField, plantedMineField);
    }

    @Test
    public void shouldPlaceThreeMinesInMineField() {
        Random mockRandom = new MockRandom(new int[]{0, 0, 1, 2, 2, 1});

        Character[][] expectedMineField = new Character[][]{{'*', '_', '_'}, {'_', '_', '*'}, {'_', '*', '_'}};
        Character[][] plantedMineField = mineField.placeMines(3, mockRandom);

        assertEquals(expectedMineField, plantedMineField);
    }

    @Test
    public void shouldReturnFieldAsBombedWhenLandingOnMine() {
        Random mockRandom = new MockRandom(new int[]{0, 0, 1, 1});

        mineField.placeMines(2, mockRandom);
        mineField.sweep(0, 0);
        assertTrue(mineField.isFieldBombed());
    }


    @Test
    public void shouldUpdateOnlySweptSquareWhenAdjacentHasMine() {
        Random mockRandom = new MockRandom(new int[]{0, 0, 0, 2, 2, 0, 2, 2});
        Character[][] expectedMineField = new Character[][]{
                {'*', '_', '*'},
                {'_', '4', '_'},
                {'*', '_', '*'}
        };

        mineField.placeMines(4, mockRandom);
        Character[][] sweptMineField = mineField.sweep(1, 1);

        assertEquals(expectedMineField, sweptMineField);
    }

    @Test
    public void shouldUpdateAdjacentSquareBasedOnAdjacentMines1() {
        Random mockRandom = new MockRandom(new int[]{0, 0, 0, 2, 2, 0});
        Character[][] expectedMineField1 = new Character[][]{
                {'*', '_', '*'},
                {'_', '3', '1'},
                {'*', '1', '0'}
        };
        Character[][] expectedMineField2 = new Character[][]{
                {'*', '_', '*'},
                {'2', '3', '1'},
                {'*', '1', '0'}
        };
        Character[][] expectedMineField3 = new Character[][]{
                {'*', '2', '*'},
                {'2', '3', '1'},
                {'*', '1', '0'}
        };

        mineField.placeMines(3, mockRandom);
        Character[][] sweptMineField = mineField.sweep(2, 2);
        assertEquals(expectedMineField1, sweptMineField);
        Character[][] sweptMineField2 = mineField.sweep(1, 0);
        assertEquals(expectedMineField2, sweptMineField2);
        Character[][] sweptMineField3 = mineField.sweep(0, 1);
        assertEquals(expectedMineField3, sweptMineField3);
    }

    @Test
    public void shouldUpdateRecursivelyTillItFindsTheMine() {
        Random mockRandom = new MockRandom(new int[]{2, 2});
        Character[][] expectedMineField = new Character[][]{
                {'0', '0', '0'},
                {'0', '1', '1'},
                {'0', '1', '*'}
        };

        mineField.placeMines(1, mockRandom);
        Character[][] sweptMineField = mineField.sweep(0, 0);

        assertEquals(expectedMineField, sweptMineField);
    }

    @Test
    public void shouldReturnAsFullFieldSweptWhenNoEmptySquareLeft() {
        Random mockRandom = new MockRandom(new int[]{2, 2});
        Character[][] expectedMineField = new Character[][]{
                {'0', '0', '0'},
                {'0', '1', '1'},
                {'0', '1', '*'}
        };
        mineField.placeMines(1, mockRandom);

        assertFalse(mineField.isFullySwept());
        Character[][] sweptMineField = mineField.sweep(0, 0);

        assertEquals(expectedMineField, sweptMineField);
        assertTrue(mineField.isFullySwept());
    }


    private class MockRandom extends Random {
        private final int[] randomReturns;
        private Integer index = 0;

        public MockRandom(int[] mockReturns) {
            this.randomReturns = mockReturns;
        }

        @Override
        public int nextInt(int size) {
            return randomReturns[index++];
        }
    }
}

