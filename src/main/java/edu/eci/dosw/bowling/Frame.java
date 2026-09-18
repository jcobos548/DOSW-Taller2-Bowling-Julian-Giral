package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<Integer> rolls;

    public Frame() {
        this.rolls = new ArrayList<>();
    }

    public List<Integer> getRolls() {
        return List.copyOf(rolls);
    }
}