import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    private final double
            speedX = 1.0,
            speedY = -1.0,
            posX = 5.0,
            posY = 5.0;

    private final double radius = 1.0;
    private final double mass = 100;

    private final double
            newSpeedX = 2.0,
            newSpeedY = 5.0,
            newPosX = 0.0,
            newPosY = 0.0;

    private final Pair<Double, Double> speed = new Pair<>(speedX, speedY);
    private final Pair<Double, Double> position = new Pair<>(posX, posY);

    private final Pair<Double, Double> newSpeed = new Pair<>(newSpeedX, newSpeedY);
    private final Pair<Double, Double> newPosition = new Pair<>(newPosX, newPosY);

    private final Ball ball = new Ball(position, radius, mass, speed);

    @Test
    void getValues() {
        assertEquals(ball.getPosition(), position);
        assertEquals(ball.getSpeed(), speed);
        assertEquals(ball.getMass(), mass);
        assertEquals(ball.getRadius(), radius);

        assertEquals(ball.getX(), posX);
        assertEquals(ball.getY(), posY);
        assertEquals(ball.getVX(), speedX);
        assertEquals(ball.getVY(), speedY);
    }

    @Test
    void iterate() {
        ball.iterate();
        assertEquals(ball.getX(), posX + speedX);
        assertEquals(ball.getY(), posY + speedY);
    }

    @Test
    void iterateAfterSet() {
        ball.setPosition(newPosition);
        ball.setSpeed(newSpeed);
        ball.iterate();
        assertEquals(ball.getX(), newPosX + newSpeedX);
        assertEquals(ball.getY(), newPosY + newSpeedY);
    }

}