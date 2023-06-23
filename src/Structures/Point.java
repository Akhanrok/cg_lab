package Structures;

public class Point {
    public float x;
    public float y;
    public float z;

    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point add(Vector v) {
        return new Point(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vector subtract(Point p) {
        return new Vector(this.x - p.x, this.y - p.y, this.z - p.z);
    }

    public float distance(Point p) {
        float dx = p.x - this.x;
        float dy = p.y - this.y;
        float dz = p.z - this.z;
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public float getDistance(Point p) {
        return distance(p);
    }
}
