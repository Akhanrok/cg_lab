package Objects;

import Structures.Point;
import Structures.Ray;
import Structures.Vector;

public class Disk {
    private Point center;
    private Vector normal;
    private float radius;

    public Disk(Point center, Vector normal, float radius) {
        this.center = center;
        this.normal = normal.normalize();
        this.radius = radius;
    }

    public float intersectDisk(Ray ray) {
        // Перетин променем площини, якій належить диск
        Plane plane = new Plane(center, normal);
        float t = plane.intersectPlane(ray);

        if (t == Float.POSITIVE_INFINITY) {
            // Немає точки перетину з площиною - промінь паралельний до площини
            return Float.POSITIVE_INFINITY;
        } else if (t == 0) {
            // Немає точки перетину з площиною  - промінь належить площині
            return 0;
        }

        // Обчислення точки перетину
        Point intersectionPoint = ray.origin.add(ray.direction.multiply(t));
        // Обчислення відстані до центру диска
        Vector centerToIntersection = intersectionPoint.subtract(center);
        float distanceToCenter = (float) centerToIntersection.length();

        if (distanceToCenter <= radius) {
            // Точка перетину належить диску
            return t;
        } else {
            // Точка перетину лежить поза межами диску
            return 0;
        }
    }
}

