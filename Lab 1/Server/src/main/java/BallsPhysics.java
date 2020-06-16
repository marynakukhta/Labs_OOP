import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class BallsPhysics implements Runnable {

    public BallsPhysics(Session session, AtomicBoolean isAlive, int sleepTime) {
        this.session = session;
        this.isAlive = isAlive;
        this.sleepTime = sleepTime;
    }

    private AtomicBoolean isAlive;
    private int sleepTime = 100;
    private Session session;

    private Ball first = new Ball(new Pair<>(Const.A_X_POS, Const.A_Y_POS), Const.A_RADIUS, Const.A_MASS, new Pair<>(Const.A_X_SPEED, Const.A_Y_SPEED));
    private Ball second = new Ball(new Pair<>(Const.B_X_POS, Const.B_Y_POS), Const.B_RADIUS, Const.B_MASS, new Pair<>(Const.B_X_SPEED, Const.B_Y_SPEED));

    private boolean wasCollided = false;

    public void iterate() {
        if (collided(first, second) && !wasCollided) {
            System.out.println("Collided!");
            Pair<Double, Double> newASpeed = getNewSpeed(first, second);
            Pair<Double, Double> newBSpeed = getNewSpeed(second, first);

            first.setSpeed(newASpeed);
            second.setSpeed(newBSpeed);
            wasCollided = true;
        }

        first.iterate();
        second.iterate();

        try {
            session.getBasicRemote().sendText("" + first.getX() + " " + first.getY() + " " + second.getX() + " " + second.getY());
            System.out.println("Send new coordinates");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean collided(Ball a, Ball b) {
        return Double.compare(
                distanceBetweenDots(a.getX(), a.getY(), b.getX(), b.getY()),
                a.getRadius() + b.getRadius()) < 0;
    }

    private double distanceBetweenDots(double aX, double aY, double bX, double bY) {
        return Math.sqrt(Math.pow(bX - aX, 2.0) + Math.pow(bY - aY, 2.0));
    }

    private double len(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    private double angle(double x, double y) {
        return Math.atan(y / x);
    }

    private Pair<Double, Double> getNewSpeed(Ball a, Ball b) {
        double angleM = Math.atan2(b.getX() - a.getX(), b.getY() - a.getY());
        if ( angleM < 0 ) { angleM += 2 * Math.PI; }
        angleM += (Math.PI / 2);


        double sub = len(a.getVX(), a.getVY()) * Math.cos(angle(a.getVX(), a.getVY()) - angleM) * (a.getMass() - b.getMass())
                + 2 * b.getMass() * len(b.getVX(), b.getVY()) * Math.cos(angle(b.getVX(), b.getVY()) - angleM);

        double x = sub / (a.getMass() + b.getMass()) * Math.cos(angleM) + len(a.getVX(), a.getVY())
                * Math.sin(angle(a.getVX(), b.getVY()) - angleM) * Math.cos(angleM + Math.PI / 2);

        double y = sub / (a.getMass() + b.getMass()) * Math.sin(angleM) + len(a.getVX(), a.getVY())
                * Math.sin(angle(a.getVX(), b.getVY()) - angleM) * Math.sin(angleM + Math.PI / 2);

        return new Pair<>(x, -y);
    }

    @Override
    public void run() {
        while(isAlive.get()) {
            iterate();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
