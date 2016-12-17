package model;

import java.util.List;

public class Halt implements Instruction {
    static final int HALTING_INDEX = -1;

    public int execute(List<Integer> inputs) {
        return HALTING_INDEX;
    }

    @Override
    public boolean equals(Object o) {
        return this == o
               || !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "HALT";
    }
}
