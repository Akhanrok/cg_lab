package Structures;

public class Vector {
    public float x;
    public float y;
    public float z;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    public Vector multiply(float scalar) {
        return new Vector(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public double dotProduct(Vector v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vector crossProduct(Vector v) {
        float crossX = this.y * v.z - this.z * v.y;
        float crossY = this.z * v.x - this.x * v.z;
        float crossZ = this.x * v.y - this.y * v.x;
        return new Vector(crossX, crossY, crossZ);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector normalize() {
        double vectorLength = length();
        float normalizedX = this.x / (float) vectorLength;
        float normalizedY = this.y / (float) vectorLength;
        float normalizedZ = this.z / (float) vectorLength;
        return new Vector(normalizedX, normalizedY, normalizedZ);
    }
}

