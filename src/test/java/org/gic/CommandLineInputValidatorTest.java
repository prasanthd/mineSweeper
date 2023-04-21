package org.gic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineInputValidatorTest {
    CommandLineInputValidator validator;
    @Before
    public void setUp() throws Exception {
        validator = new CommandLineInputValidator();
    }

    @Test
    public void shouldSayInvalidWhenGridSizeIsNotBetweenThreeAndTen() {
        assertTrue(validator.isValidGridSize(3));
        assertTrue(validator.isValidGridSize(10));
        assertFalse(validator.isValidGridSize(2));
        assertFalse(validator.isValidGridSize(11));
    }

    @Test
    public void shouldSayInvalidMineGridSizeIsLessThanOne() {
        assertTrue(validator.isValidMineSize(2));
        assertTrue(validator.isValidMineSize(1));
        assertFalse(validator.isValidMineSize(0));
    }

    @Test
    public void shouldSayInvalidRevealSizeIsLessThanOne() {
        assertTrue(validator.isValidSquareRevealInput(2,2));
        assertTrue(validator.isValidSquareRevealInput(1,3));
        assertFalse(validator.isValidSquareRevealInput(0 ,0));
    }
}