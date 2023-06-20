package Objects;

import Structures.Point;
import Structures.Ray;
import Structures.Vector;

public class Sphere {
    private Point center;
    private float radius;

    public Sphere(Point center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point[] intersect(Ray ray) {
        Point orig = ray.origin;
        Vector dir = ray.direction;

        float[] tValues = solveQuadratic(orig, dir);
        if (tValues == null) {
            return new Point[0]; // Немає точки перетину
        }

        float t0 = tValues[0];
        float t1 = tValues[1];

        if (t0 > t1) {
            float temp = t0;
            t0 = t1;
            t1 = temp;
        }

        if (t0 < 0) {
            t0 = t1;
            if (t0 < 0) {
                return new Point[0]; // Немає точки перетину
            }
        }

        Point intersectionPoint1 = orig.add(dir.multiply(t0));
        Point intersectionPoint2 = orig.add(dir.multiply(t1));

        return new Point[]{intersectionPoint1, intersectionPoint2};
    }

    private float[] solveQuadratic(Point orig, Vector dir) {
        Vector L = orig.subtract(center); // Вектор з точки початку променя до центру сфери
        float a = (float) dir.dotProduct(dir);
        float b = (float) (2 * dir.dotProduct(L));
        float c = (float) (L.dotProduct(L) - radius * radius);

        float discr = b * b - 4 * a * c;
        if (discr < 0) {
            return null; // Немає розв'язків
        }

        float sqrtDiscr = (float) Math.sqrt(discr);
        float q = (b > 0) ? -0.5f * (b + sqrtDiscr) : -0.5f * (b - sqrtDiscr);

        float t0 = q / a;
        float t1 = c / q;

        return new float[]{t0, t1};
    }
}
