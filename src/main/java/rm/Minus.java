package rm;

import java.util.List;

public class Minus implements Instruction {

    private final int register;
    private final int nextLabelIfNonZero;
    private final int nextLabelIfZero;

    public Minus(int register, int nextLabelIfNonZero, int nextLabelIfZero) {
        this.register = register;
        this.nextLabelIfNonZero = nextLabelIfNonZero;
        this.nextLabelIfZero = nextLabelIfZero;
    }

    public int execute(List<Integer> inputs) {
        int value = inputs.get(register);

        if(value == 0) {
            return nextLabelIfZero;
        }

        inputs.set(register, value - 1);
        return nextLabelIfNonZero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Minus minus = (Minus) o;

        return register == minus.register
               && nextLabelIfZero == minus.nextLabelIfZero
               && nextLabelIfNonZero == minus.nextLabelIfNonZero;

    }

    @Override
    public int hashCode() {
        return NumericalCoding.of(2 * register + 1, NumericalCoding.of(nextLabelIfNonZero, nextLabelIfZero) - 1);
    }

    @Override
    public String toString() {
        return "-R" + register
                + " -> L" + nextLabelIfNonZero
                + ", L" + nextLabelIfZero;
    }
}
