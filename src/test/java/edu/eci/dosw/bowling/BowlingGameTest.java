package edu.eci.dosw.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @Test
    void rollZero_registersZeroPins() {
        BowlingGame game = new BowlingGame();

        assertDoesNotThrow(() -> game.roll(0));
        assertEquals(0, game.getFrames().get(0).getRolls().get(0));
    }

    @Test
    void rollNegativePins_throwsException() {
        BowlingGame game = new BowlingGame();

        assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(-1)
        );
    }
}

