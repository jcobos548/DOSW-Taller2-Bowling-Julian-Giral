package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<Integer> rolls;
    private FrameType type;

    public Frame() {
        this.rolls = new ArrayList<>();
        this.type = FrameType.NORMAL;
    }

    public void addRoll(int pins) {
        rolls.add(pins);
        updateType();
    }

    private void updateType() {
        if (isStrike()) {
            type = FrameType.STRIKE;
        } else if (isSpare()) {
            type = FrameType.SPARE;
        }
    }

    private boolean isStrike() {
        return rolls.size() == 1 && rolls.get(0) == 10;
    }

    private boolean isSpare() {
        return rolls.size() == 2 && rolls.get(0) + rolls.get(1) == 10;
    }

    public List<Integer> getRolls() {
        return List.copyOf(rolls);
    }

    public FrameType getType() {
        return type;
    }
}