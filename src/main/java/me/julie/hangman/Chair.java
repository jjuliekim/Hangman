package me.julie.hangman;

import java.util.Objects;

public class Chair {
    private final int legs;

    public Chair(int legs) {
        this.legs = legs;
    }

    public int getLegs() {
        return legs;
    }

    @Override
    public String toString() {
        return "Chair{" +
                "legs=" + legs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chair chair = (Chair) o;
        return legs == chair.legs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(legs);
    }
}
