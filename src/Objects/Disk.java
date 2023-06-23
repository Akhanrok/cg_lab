package Objects;

import Structures.Ray;
import Structures.Point;
import Structures.Vector;

public class Disk implements Intersectable {
    private Point center;
    private Vector normal;
    private float radius;

    public Disk(Point center, Vector normal, float radius) {
        this.center = center;
        this.normal = normal.normalize();
        this.radius = radius;
    }

    @Override
    public Point[] intersect(Ray ray) {
        Vector rayDir = ray.direction.normalize();
        double t = (center.subtract(ray.origin).dotProduct(normal)) / rayDir.dotProduct(normal);

        if (t >= 0) {
            Point intersectionPoint = ray.origin.add(rayDir.multiply((float) t));
            Vector v = intersectionPoint.subtract(center);
            float distanceSquared = (float) v.dotProduct(v);
            if (distanceSquared <= radius * radius) {
                if (isPointOnDisk(intersectionPoint)) {
                    return new Point[] { intersectionPoint };
                }
            }
        }

        return null;
    }

    private boolean isPointOnDisk(Point point) {
        Vector pointToCenter = center.subtract(point);
        double distance = pointToCenter.dotProduct(normal);
        return Math.abs(distance) < 1e-6;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}