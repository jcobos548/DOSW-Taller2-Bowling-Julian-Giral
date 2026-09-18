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
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException(
                    "Los pinos deben estar entre 0 y 10"
            );
        }

        if (frames.isEmpty()) {
            Frame frame = new Frame();
            frame.addRoll(pins);
            frames.add(frame);

            if (pins == 10) {
                currentFrame++;
            }

            return;
        }

        if (currentFrame >= 10) {
            Frame tenthFrame = frames.get(9);

            if (!hasBonusRolls(tenthFrame)
                    || tenthFrame.getRolls().size() >= 3) {
                throw new IllegalStateException(
                        "El juego ya terminó"
                );
            }

            tenthFrame.addRoll(pins);
            return;
        }

        if (frames.size() <= currentFrame) {
            frames.add(new Frame());
        }

        Frame frame = frames.get(currentFrame);

        if (frame.getRolls().isEmpty()) {
            frame.addRoll(pins);

            if (pins == 10) {
                currentFrame++;
            }

            return;
        }

        int firstRoll = frame.getRolls().get(0);

        if (firstRoll + pins > 10) {
            throw new IllegalArgumentException(
                    "Los pinos del frame no pueden superar 10"
            );
        }

        frame.addRoll(pins);
        currentFrame++;
    }

    private boolean hasBonusRolls(Frame frame) {
        return frame.getType() == FrameType.STRIKE
                || frame.getType() == FrameType.SPARE;
    }

    public int score() {
        // TODO: implementar con TDD
        return 0;
    }

    public boolean isComplete() {
        // TODO: implementar con TDD
        return false;
    }

    public List<Frame> getFrames() {
        return List.copyOf(frames);
    }
}