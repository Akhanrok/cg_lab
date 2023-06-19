package Objects;

import Structures.Point;
import Structures.Ray;
import Structures.Vector;

public class Plane {
    private Point point;
    private Vector normal;

    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    public float intersectPlane(Ray ray) {
        Vector rayDirection = ray.direction.normalize();

        float denominator = (float) normal.dotProduct(rayDirection);
        if (denominator == 0) { // Немає точки перетину - промінь паралельний до площини
            return Float.POSITIVE_INFINITY;
        }

        Vector pointToRayOrigin = ray.origin.subtract(point);
        float numerator = (float) normal.dotProduct(pointToRayOrigin);

        float t = -numerator / denominator;
        if (t < 0) { // Немає точки перетину - промінь належить площині
            return 0;
        }

        return t;
    }
}
