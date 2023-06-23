package Structures;

public class Camera {
    private Point position;
    private Vector direction;
    private Vector right;
    private Vector up;

    public Camera(Point position, Vector direction, Vector right, Vector up) {
        this.position = position;
        this.direction = direction.normalize();
        this.right = right.normalize();
        this.up = up.normalize();
    }

    public Point getPosition() {
        return position;
    }

    public Vector getDirection() {
        return direction;
    }

    public Vector getRight() {
        return right;
    }

    public Vector getUp() {
        return up;
    }
}


