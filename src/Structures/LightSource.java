package Structures;

public class LightSource {
    private Vector direction;
    private Point position;

    public LightSource(Point position, Vector direction) {
        this.direction = direction.normalize();
        this.position = position;
    }

    public Vector getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }
}

