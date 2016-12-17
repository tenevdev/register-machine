package model;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.*;

public class NumericalCoding {

    public static int of(int x, int y) {
        return (int) Math.pow(2, x) * (2 * y + 1);
    }

    public static int of(List<Integer> l) {

        if(l.isEmpty()) {
            return 0;
        }

        return of(l.get(0), of(l.subList(1, l.size())));
    }

    public static Pair<Integer, Integer> toPair(int n) {
        // TODO: Throw if n < 1

        int x = Integer.numberOfTrailingZeros(n);
        int y = n >> (x + 1);

        //noinspection SuspiciousNameCombination
        return Pair.of(x, y);
    }

    public static Pair<BigInteger, BigInteger> toPair(BigInteger n) {
        // TODO: Throw if n < 1

        int x = n.getLowestSetBit();
        BigInteger y = n.shiftRight(x + 1);

        //noinspection SuspiciousNameCombination
        return Pair.of(BigInteger.valueOf(x), y);
    }

    public static List<Integer> toList(int n) {
        if(n == 0) {
            return new ArrayList<>();
        }

        int head = Integer.numberOfTrailingZeros(n);
        int tail = n >> (head + 1);

        List<Integer> list = toList(tail);
        list.add(0, head);

        return list;
    }

    public static List<BigInteger> toList(BigInteger n) {
        if(n.equals(BigInteger.ZERO)) {
            return new ArrayList<>();
        }

        int head = n.getLowestSetBit(); // number of trailing zero bits
        BigInteger tail = n.shiftRight(head + 1);

        List<BigInteger> list = toList(tail);
        list.add(0, BigInteger.valueOf(head));

        return list;
    }


    public static Integer of(Pair<Integer, Integer> pair) {
        return of(pair.getLeft(), pair.getRight());
    }
}
