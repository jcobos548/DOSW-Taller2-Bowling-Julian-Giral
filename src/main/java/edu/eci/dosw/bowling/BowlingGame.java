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
            return;
        }

        Frame frame = frames.get(currentFrame);

        if (frame.getRolls().size() == 1) {
            int firstRoll = frame.getRolls().get(0);

            if (firstRoll + pins > 10) {
                throw new IllegalArgumentException(
                        "Los pinos del frame no pueden superar 10"
                );
            }

            frame.addRoll(pins);
            return;
        }

        if (currentFrame == 9) {
            throw new IllegalStateException(
                    "El juego ya terminó"
            );
        }

        currentFrame++;

        Frame newFrame = new Frame();
        newFrame.addRoll(pins);
        frames.add(newFrame);
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