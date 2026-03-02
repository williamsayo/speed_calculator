import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TravelCalculatorTest {
    TravelCalculator calculator = new TravelCalculator();
    private final double DELTA = 0.01;
    @Test
    void testCalculateSpeed() {
        assertEquals(100,calculator.calculateSpeed(100,1),DELTA);
    }

    @Test
    void testCalculateSpeed_with_negativeTime() {
        assertThrows(IllegalArgumentException.class,()->calculator.calculateSpeed(100,0));
    }
}