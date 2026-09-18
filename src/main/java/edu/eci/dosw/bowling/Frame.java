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

        if (pins == 10 && rolls.size() == 1) {
            type = FrameType.STRIKE;
        }
    }

    public List<Integer> getRolls() {
        return List.copyOf(rolls);
    }

    public FrameType getType() {
        return type;
    }
}