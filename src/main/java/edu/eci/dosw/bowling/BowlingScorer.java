package edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public int calculate(List<Frame> frames) {
        int total = 0;

        for (int i = 0; i < frames.size() && i < 10; i++) {
            Frame frame = frames.get(i);

            if (frame.getType() == FrameType.STRIKE) {
                total += 10;
                total += getNextRoll(frames, i);
                total += getSecondNextRoll(frames, i);
            } else if (frame.getType() == FrameType.SPARE) {
                total += 10;
                total += getNextRoll(frames, i);
            } else {
                total += sumRolls(frame);
            }
        }

        return total;
    }

    private int sumRolls(Frame frame) {
        int total = 0;

        for (int pins : frame.getRolls()) {
            total += pins;
        }

        return total;
    }

    private int getNextRoll(List<Frame> frames, int frameIndex) {
        if (frameIndex + 1 >= frames.size()) {
            return 0;
        }

        Frame nextFrame = frames.get(frameIndex + 1);

        if (nextFrame.getRolls().isEmpty()) {
            return 0;
        }

        return nextFrame.getRolls().get(0);
    }

    private int getSecondNextRoll(List<Frame> frames, int frameIndex) {
        if (frameIndex + 1 >= frames.size()) {
            return 0;
        }

        Frame nextFrame = frames.get(frameIndex + 1);

        if (nextFrame.getRolls().size() >= 2) {
            return nextFrame.getRolls().get(1);
        }

        if (frameIndex + 2 >= frames.size()) {
            return 0;
        }

        Frame followingFrame = frames.get(frameIndex + 2);

        if (followingFrame.getRolls().isEmpty()) {
            return 0;
        }

        return followingFrame.getRolls().get(0);
    }
}