package edu.eci.dosw.bowling;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingScorerTest {

    @Test
    void allZeroRolls_scoreIsZero() {
        List<Frame> frames = createNormalFrames(10, 0, 0);

        BowlingScorer scorer = new BowlingScorer();

        assertEquals(0, scorer.calculate(frames));
    }

    @Test
    void normalRolls_scoreIsSumOfPins() {
        List<Frame> frames = createNormalFrames(10, 3, 4);

        BowlingScorer scorer = new BowlingScorer();

        assertEquals(70, scorer.calculate(frames));
    }

    @Test
    void spare_scoresTenPlusNextRoll() {
        List<Frame> frames = new ArrayList<>();

        Frame spare = new Frame();
        spare.addRoll(5);
        spare.addRoll(5);
        frames.add(spare);

        Frame nextFrame = new Frame();
        nextFrame.addRoll(3);
        nextFrame.addRoll(4);
        frames.add(nextFrame);

        BowlingScorer scorer = new BowlingScorer();

        assertEquals(20, scorer.calculate(frames));
    }

    @Test
    void strike_scoresTenPlusNextTwoRolls() {
        List<Frame> frames = new ArrayList<>();

        Frame strike = new Frame();
        strike.addRoll(10);
        frames.add(strike);

        Frame nextFrame = new Frame();
        nextFrame.addRoll(4);
        nextFrame.addRoll(3);
        frames.add(nextFrame);

        BowlingScorer scorer = new BowlingScorer();

        assertEquals(24, scorer.calculate(frames));
    }

    @Test
    void twoStrikesAndFive_scoresCorrectly() {
        List<Frame> frames = new ArrayList<>();

        Frame firstStrike = new Frame();
        firstStrike.addRoll(10);
        frames.add(firstStrike);

        Frame secondStrike = new Frame();
        secondStrike.addRoll(10);
        frames.add(secondStrike);

        Frame lastFrame = new Frame();
        lastFrame.addRoll(5);
        lastFrame.addRoll(0);
        frames.add(lastFrame);

        BowlingScorer scorer = new BowlingScorer();

        assertEquals(45, scorer.calculate(frames));
    }

    private List<Frame> createNormalFrames(
            int numberOfFrames,
            int firstRoll,
            int secondRoll
    ) {
        List<Frame> frames = new ArrayList<>();

        for (int i = 0; i < numberOfFrames; i++) {
            Frame frame = new Frame();
            frame.addRoll(firstRoll);
            frame.addRoll(secondRoll);
            frames.add(frame);
        }

        return frames;
    }

    @Test
    void allSparesAndLastFive_scoreIs150() {
        List<Frame> frames = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            Frame frame = new Frame();
            frame.addRoll(5);
            frame.addRoll(5);
            frames.add(frame);
        }

        Frame tenthFrame = new Frame();
        tenthFrame.addRoll(5);
        tenthFrame.addRoll(5);
        tenthFrame.addRoll(5);
        frames.add(tenthFrame);

        BowlingScorer scorer = new BowlingScorer();

        assertEquals(150, scorer.calculate(frames));
    }

    @Test
    void perfectGame_scoreIs300() {
        List<Frame> frames = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            Frame frame = new Frame();
            frame.addRoll(10);
            frames.add(frame);
        }

        Frame tenthFrame = new Frame();
        tenthFrame.addRoll(10);
        tenthFrame.addRoll(10);
        tenthFrame.addRoll(10);
        frames.add(tenthFrame);

        BowlingScorer scorer = new BowlingScorer();

        assertEquals(300, scorer.calculate(frames));
    }
}