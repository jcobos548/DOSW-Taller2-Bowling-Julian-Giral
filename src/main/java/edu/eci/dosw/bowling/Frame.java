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

        if (rolls.size() == 1 && pins == 10) {
            type = FrameType.STRIKE;
        } else if (rolls.size() == 2 && rolls.get(0) + rolls.get(1) == 10) {
            type = FrameType.SPARE;
        }
    }

    public List<Integer> getRolls() {
        return List.copyOf(rolls);
    }

    public FrameType getType() {
        return type;
    }
}