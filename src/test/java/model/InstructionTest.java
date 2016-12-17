package model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InstructionTest {

    @Test
    public void haltFromZero() {
        assertThat(Instruction.from(0), is(new Halt()));
    }

    @Test
    public void from() {
        assertThat(Instruction.from(20), is(new Plus(1, 2)));
        assertThat(Instruction.from(18), is(new Minus(0, 0, 2)));
    }
}