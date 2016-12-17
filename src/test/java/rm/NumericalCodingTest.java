package rm;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NumericalCodingTest {

    @Test
    public void ofPair() {
        assertThat(NumericalCoding.of(0, 0), is(1));
        assertThat(NumericalCoding.of(1, 0), is(2));
        assertThat(NumericalCoding.of(0, 1), is(3));
        assertThat(NumericalCoding.of(2, 2), is(20));
    }

    @Test
    public void ofList() {
        assertThat(NumericalCoding.of(Collections.singletonList(3)), is(8));
        assertThat(NumericalCoding.of(Arrays.asList(1, 3)), is(34));
        assertThat(NumericalCoding.of(Arrays.asList(2, 1, 3)), is(276));
    }

    @Test
    public void toPair() {
        for (int i = 1; i < 100; i++) {
            assertThat(NumericalCoding.of(NumericalCoding.toPair(i)), is(i));
        }
    }

    @Test
    public void toList() {
        for(int i = 0; i < 100; i++) {
            assertThat(NumericalCoding.of(NumericalCoding.toList(i)), is(i));
        }
    }

}