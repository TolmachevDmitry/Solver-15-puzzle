package com.company;

public class State {
    public int[][] directlyState;
    public int iEmpty;
    public int jEmpty;

    public State prev;

    State(int[][] state, int iEmpty, int jEmpty, State prev) {
        this.directlyState = state;
        this.iEmpty = iEmpty;
        this.jEmpty = jEmpty;
        this.prev = prev;
    }
}
