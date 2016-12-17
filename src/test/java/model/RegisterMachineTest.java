package model;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class RegisterMachineTest {

    private final List<Instruction> instructions = new ArrayList<Instruction>() {{
        add(new Minus(1, 3, 5));
        add(new Minus(1, 4, 2));
        add(new Halt());
        add(new Minus(1, 1, 2));
        add(new Plus(0, 0));
        add(new Halt());
    }};

    private final RegisterMachine divBy3 = new RegisterMachine(instructions);


    @Test
    public void acceptsInputAndProducesOutput() {
        List<Instruction> instructions = new ArrayList<Instruction>() {{ add(new Halt()); }};
        RegisterMachine identity = new RegisterMachine(instructions);

        assertThat(identity.executeOn(0, 3, 4), contains(0, 3, 4));
    }

    @Test
    public void executesInstructions() {
        List<Instruction> instructions = new ArrayList<Instruction>() {{ add(new Plus(0, 1));  add(new Halt()); }};
        RegisterMachine one = new RegisterMachine(instructions);

        assertThat(one.executeOn(0, 42, 71), contains(1, 42, 71));
    }

    @Test
    public void followsTheExecutionOrderOfInstructions() {
        List<Instruction> instructions = new ArrayList<Instruction>() {{
            add(new Plus(0, 2));
            add(new Halt());
            add(new Minus(0, 2, 3));
            add(new Halt());
        }};

        RegisterMachine one = new RegisterMachine(instructions);

        assertThat(one.executeOn(10, 42, 71), contains(0, 42, 71));
    }

    @Test
    public void producesExpectedOutputForFairlyComplexProgram() {
        assertThat(divBy3.executeOn(0, 7), contains(2, 0));
    }

    @Test
    public void canConstructFromNumber() {
        RegisterMachine registerMachine = RegisterMachine.from(786432);

        assertThat(registerMachine.executeOn(2), contains(0));
        assertThat(registerMachine.executeOn(123432), contains(0));
        assertThat(registerMachine.executeOn(0), contains(0));
    }

    @Test
    public void canConstructFromBigNumber() {
        // 2 ^ 94 * 16395
        BigInteger code = BigInteger.ONE.shiftLeft(94).multiply(BigInteger.valueOf(16395));
        RegisterMachine registerMachine = RegisterMachine.from(code);

        // TODO: Don't use hashCode for encoding simply because codes are big ints
        // assertThat(registerMachine.hashCode(), is(code));

        assertThat(registerMachine.executeOn(2, 0), contains(0, 0));
    }

    @Test
    public void producesExpectedIntermediateResults() {
        List<List<Integer>> results = divBy3.executeWithIntermediateResultsOn(true, 0, 7);

        assertThat(results.size(), is(11));
        assertThat(results.get(results.size() - 1).get(0), is(-1));
    }
}
