package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterMachine {

    private final List<Instruction> instructions;

    public RegisterMachine(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Integer> executeOn(Integer... inputs) {
        int nextIdx = 0;
        List<Integer> registers = Arrays.asList(inputs);
        do {
            Instruction nextInstruction = instructions.get(nextIdx);
            nextIdx = nextInstruction.execute(registers);
        } while (nextIdx >= 0 && nextIdx < instructions.size());

        return registers;
    }

    public List<List<Integer>> executeWithIntermediateResultsOn(boolean includeLabelIndex, Integer... inputs) {
        int nextIdx = 0;
        List<Integer> registers = Arrays.asList(inputs);
        List<List<Integer>> registerStates = new ArrayList<>();

        do {
            Instruction nextInstruction = instructions.get(nextIdx);
            nextIdx = nextInstruction.execute(registers);

            List<Integer> intermediateResult = new ArrayList<>(registers);
            if(includeLabelIndex) {
                intermediateResult.add(0, nextIdx);
            }
            registerStates.add(intermediateResult);
        } while (nextIdx >= 0 && nextIdx < instructions.size());

        return registerStates;
    }

    public List<List<Integer>> executeWithIntermediateResultsOn(Integer... inputs) {
        return executeWithIntermediateResultsOn(false, inputs);
    }

        @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegisterMachine that = (RegisterMachine) o;

        return instructions.equals(that.instructions);
    }

    @Override
    public int hashCode() {
        if (instructions.size() == 0) {
            return 0;
        }

        List<Integer> instructionCodes = instructions.stream().map(Object::hashCode).collect(Collectors.toList());

        return NumericalCoding.of(instructionCodes);
    }

    @Override
    public String toString() {
        return instructions.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    public static RegisterMachine from(int n) {
        List<Integer> instructionCodes =  NumericalCoding.toList(n);
        return new RegisterMachine(instructionCodes.stream().map(Instruction::from).collect(Collectors.toList()));
    }

    public static RegisterMachine from(BigInteger n) {
        List<BigInteger> instructionCodes =  NumericalCoding.toList(n);
        return new RegisterMachine(instructionCodes.stream().map(Instruction::from).collect(Collectors.toList()));
    }
}
