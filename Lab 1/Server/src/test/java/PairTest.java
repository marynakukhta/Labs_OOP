import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {
    private final double x = 3.14, y = -5.1;
    private final double newX = 0.0, newY = 2.28;

    private final Pair<Double, Double> pair = new Pair<>(x, y);

    @Test
    void getValues() {
        assertEquals(pair.getX(), x);
        assertEquals(pair.getY(), y);
    }

    @Test
    void setValues() {
        pair.setX(newX);
        pair.setY(newY);
        assertEquals(pair.getX(), newX);
        assertEquals(pair.getY(), newY);
    }

    @Test
    void testArgumentTypes() {
        assertTrue(pair.getX() instanceof Number);
        assertTrue(pair.getY() instanceof Number);
    }

    @Test
    void testNull() {
        Pair<Number, Number> nullPair = new Pair<>(null, null);
        assertNull(nullPair.getX());
        assertNull(nullPair.getY());
    }
}