package Objects;

import Structures.Ray;
import Structures.Point;
import Structures.Vector;

public class Sphere implements Intersectable {
    private Point center;
    private float radius;

    public Sphere(Point center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Point[] intersect(Ray ray) {
        Vector rayDir = ray.direction.normalize();
        Vector sphereToRay = ray.origin.subtract(center);

        float a = (float) rayDir.dotProduct(rayDir);
        float b = (float) (2 * rayDir.dotProduct(sphereToRay));
        float c = (float) (sphereToRay.dotProduct(sphereToRay) - radius * radius);

        float discriminant = b * b - 4 * a * c;
        if (discriminant >= 0) {
            float sqrtDiscriminant = (float) Math.sqrt(discriminant);
            float t1 = (-b + sqrtDiscriminant) / (2 * a);
            float t2 = (-b - sqrtDiscriminant) / (2 * a);

            if (t1 >= 0 || t2 >= 0) {
                Point[] intersectionPoints = new Point[t1 >= 0 && t2 >= 0 ? 2 : 1];

                int index = 0;
                if (t1 >= 0) {
                    Point intersectionPoint1 = ray.origin.add(rayDir.multiply(t1));
                    intersectionPoints[index++] = intersectionPoint1;
                }
                if (t2 >= 0) {
                    Point intersectionPoint2 = ray.origin.add(rayDir.multiply(t2));
                    intersectionPoints[index] = intersectionPoint2;
                }

                return intersectionPoints;
            }
        }

        return null;
    }

    public Point getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }
}
