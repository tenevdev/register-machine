package model;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.List;

public interface Instruction {
    int execute(List<Integer> inputs);

    static Instruction from(int n) {
        if(n == 0) {
            return new Halt();
        }

        Pair<Integer, Integer> pairCode = NumericalCoding.toPair(n);
        int x = pairCode.getLeft();
        int y = pairCode.getRight();

        if(x % 2 == 0) {
            return new Plus(x / 2, y);
        }

        pairCode =  NumericalCoding.toPair(y + 1);

        return new Minus((x - 1) / 2, pairCode.getLeft(), pairCode.getRight());
    }

    static Instruction from(BigInteger n) {
        if(n.equals(BigInteger.ZERO)) {
            return new Halt();
        }

        Pair<BigInteger, BigInteger> pairCode = NumericalCoding.toPair(n);
        int x = pairCode.getLeft().intValue();
        int y = pairCode.getRight().intValue();

        if(x % 2 == 0) { // x % 2 == 0
            return new Plus(x >> 1, y);
        }

        Pair<Integer, Integer> labels =  NumericalCoding.toPair(y + 1);

        return new Minus((x - 1) / 2, labels.getLeft(), labels.getRight());
    }
}
