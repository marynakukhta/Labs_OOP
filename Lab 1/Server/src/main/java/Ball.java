public class Ball {
    private Pair<Double, Double> position;
    private double radius;
    private double mass;

    private Pair<Double, Double> speed;

    public Ball(Pair<Double, Double> position, double radius, double mass, Pair<Double, Double> speed) {
        this.position = position;
        this.radius = radius;
        this.mass = mass;
        this.speed = speed;
    }

    public void iterate() {
        position.setX(position.getX() + speed.getX());
        position.setY(position.getY() + speed.getY());
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public double getVX() {
        return speed.getX();
    }

    public double getVY() {
        return speed.getY();
    }

    public double getMass() {
        return mass;
    }

    public Pair<Double, Double> getSpeed() {
        return speed;
    }

    public Pair<Double, Double> getPosition() {
        return position;
    }

    public double getRadius() {
        return radius;
    }

    public void setSpeed(Pair<Double, Double> speed) {
        this.speed = speed;
    }

    public void setPosition(Pair<Double, Double> position) {
        this.position = position;
    }
}
