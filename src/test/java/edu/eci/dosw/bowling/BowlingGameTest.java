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

    @Test
    void rollMoreThanTenPins_throwsException() {
        BowlingGame game = new BowlingGame();

        assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(11)
        );
    }

    @Test
    void twoRollsInFrameExceedingTenPins_throwsException() {
        BowlingGame game = new BowlingGame();

        game.roll(7);

        assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(6)
        );
    }

    @Test
    void rollAfterGameIsComplete_throwsException() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }

        assertThrows(
                IllegalStateException.class,
                () -> game.roll(0)
        );
    }

    @Test
    void rollTenPins_marksFrameAsStrikeAndAdvances() {
        BowlingGame game = new BowlingGame();

        game.roll(10);

        assertEquals(FrameType.STRIKE, game.getFrames().get(0).getType());
        assertEquals(1, game.getFrames().size());
    }

    @Test
    void twoRollsOfFive_marksFrameAsSpare() {
        BowlingGame game = new BowlingGame();

        game.roll(5);
        game.roll(5);

        assertEquals(
                FrameType.SPARE,
                game.getFrames().get(0).getType()
        );
    }

    @Test
    void tenthFrameStrike_allowsThreeRolls() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 9; i++) {
            game.roll(0);
            game.roll(0);
        }

        game.roll(10);
        game.roll(5);
        game.roll(3);

        assertEquals(3, game.getFrames().get(9).getRolls().size());
    }

    @Test
    void allZeroRolls_scoreIsZero() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }

        assertEquals(0, game.score());
    }

    @Test
    void normalRolls_scoreIsSumOfPins() {
        BowlingGame game = new BowlingGame();

        game.roll(3);
        game.roll(4);
        game.roll(2);
        game.roll(5);
        game.roll(6);
        game.roll(1);

        assertEquals(21, game.score());
    }

    @Test
    void spare_scoresTenPlusNextRoll() {
        BowlingGame game = new BowlingGame();

        game.roll(5);
        game.roll(5);
        game.roll(3);

        assertEquals(13, game.score());
    }

    @Test
    void strike_scoresTenPlusNextTwoRolls() {
        BowlingGame game = new BowlingGame();

        game.roll(10);
        game.roll(4);
        game.roll(3);

        assertEquals(17, game.score());
    }

    @Test
    void twoStrikesAndFive_scoresCorrectly() {
        BowlingGame game = new BowlingGame();

        game.roll(10);
        game.roll(10);
        game.roll(5);

        assertEquals(45, game.score());
    }
}

