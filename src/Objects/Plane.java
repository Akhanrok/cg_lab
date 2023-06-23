package Objects;

import Structures.Ray;
import Structures.Point;
import Structures.Vector;

public class Plane implements Intersectable {
    private Point point;
    private Vector normal;

    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    @Override
    public Point[] intersect(Ray ray) {
        Vector rayDir = ray.direction.normalize();

        double t = (point.subtract(ray.origin).dotProduct(normal)) / rayDir.dotProduct(normal);

        if (t >= 0) {
            Point intersectionPoint = ray.origin.add(rayDir.multiply((float) t));

            Vector pointToPlane = intersectionPoint.subtract(point);
            double distance = pointToPlane.dotProduct(normal);

            if (Math.abs(distance) < 1e-6) {
                return new Point[] { intersectionPoint };
            }
        }

        return null;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}
