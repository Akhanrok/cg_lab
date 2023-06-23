package Structures;

public class LightSource {
    private Vector direction;

    public LightSource(Vector direction) {
        this.direction = direction.normalize();
    }

    public Vector getDirection() {
        return direction;
    }
}

