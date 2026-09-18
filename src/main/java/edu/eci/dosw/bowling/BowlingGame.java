package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private final List<Frame> frames;
    private int currentFrame;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.currentFrame = 0;
    }

    public void roll(int pins) {
        validatePins(pins);

        if (frames.isEmpty()) {
            createFirstFrame(pins);
            return;
        }

        if (currentFrame >= 10) {
            addBonusRoll(pins);
            return;
        }

        Frame frame = getCurrentFrame();

        if (frame.getRolls().isEmpty()) {
            addFirstRoll(frame, pins);
            return;
        }

        addSecondRoll(frame, pins);
    }

    private void validatePins(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException(
                    "Los pinos deben estar entre 0 y 10"
            );
        }
    }

    private void createFirstFrame(int pins) {
        Frame frame = new Frame();
        frame.addRoll(pins);
        frames.add(frame);

        if (pins == 10) {
            currentFrame++;
        }
    }

    private Frame getCurrentFrame() {
        if (frames.size() <= currentFrame) {
            frames.add(new Frame());
        }

        return frames.get(currentFrame);
    }

    private void addFirstRoll(Frame frame, int pins) {
        frame.addRoll(pins);

        if (pins == 10) {
            currentFrame++;
        }
    }

    private void addSecondRoll(Frame frame, int pins) {
        int firstRoll = frame.getRolls().get(0);

        if (firstRoll + pins > 10) {
            throw new IllegalArgumentException(
                    "Los pinos del frame no pueden superar 10"
            );
        }

        frame.addRoll(pins);
        currentFrame++;
    }

    private void addBonusRoll(int pins) {
        Frame tenthFrame = frames.get(9);

        if (!hasBonusRolls(tenthFrame)
                || tenthFrame.getRolls().size() >= 3) {
            throw new IllegalStateException(
                    "El juego ya terminó"
            );
        }

        tenthFrame.addRoll(pins);
    }

    private boolean hasBonusRolls(Frame frame) {
        return frame.getType() == FrameType.STRIKE
                || frame.getType() == FrameType.SPARE;
    }

    public int score() {
        int total = 0;

        for (Frame frame : frames) {
            for (int pins : frame.getRolls()) {
                total += pins;
            }
        }

        return total;
    }

    public boolean isComplete() {
        // TODO: implementar con TDD
        return false;
    }

    public List<Frame> getFrames() {
        return List.copyOf(frames);
    }
}