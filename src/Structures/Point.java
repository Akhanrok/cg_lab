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

    public Vector add(Point p) {
        return new Vector(this.x + p.x, this.y + p.y, this.z + p.z);
    }

    public Vector subtract(Point p) {
        return new Vector(this.x - p.x, this.y - p.y, this.z - p.z);
    }
}
