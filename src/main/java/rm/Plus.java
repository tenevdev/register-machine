package rm;

import java.util.List;

public class Plus implements Instruction {

    private final int register;
    private final int nextLabel;

    public Plus(int register, int nextLabel) {
        this.register = register;
        this.nextLabel = nextLabel;
    }

    public int execute(List<Integer> inputs) {
        inputs.set(register, inputs.get(register) + 1);
        return nextLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Plus plus = (Plus) o;

        return register == plus.register && nextLabel == plus.nextLabel;

    }

    @Override
    public int hashCode() {
        return NumericalCoding.of(2 * register, nextLabel);
    }

    @Override
    public String toString() {
        return "+R" + register + " -> L" + nextLabel;
    }
}
