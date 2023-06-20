package Tests;

import Objects.Sphere;
import Structures.Point;
import Structures.Ray;
import Structures.Vector;

public class SphereIntersectionTest {
    public static void testSphereIntersection() {
        // Випадок 1: промінь перетинає сферу
        Point center = new Point(0, 0, 0);
        float radius = 1;
        Sphere sphere = new Sphere(center, radius);

        Point rayOrigin = new Point(0, 0, -2);
        Vector rayDirection = new Vector(0, 0, 1);
        Ray ray = new Ray(rayOrigin, rayDirection);

        Point[] intersectionPoints = sphere.intersectSphere(ray);

        if (intersectionPoints.length > 0) {
            for (Point intersectionPoint : intersectionPoints) {
                System.out.println("Intersection point: (" + intersectionPoint.x + ", " + intersectionPoint.y + ", " + intersectionPoint.z + ")");
            }
        } else {
            System.out.println("No intersection");
        }

        // Випадок 2: промінь не перетинає сферу
        Point center2 = new Point(0, 0, 0);
        float radius2 = 1;
        Sphere sphere2 = new Sphere(center2, radius2);

        Point rayOrigin2 = new Point(0, 2, -2);
        Vector rayDirection2 = new Vector(0, 0, 1);
        Ray ray2 = new Ray(rayOrigin2, rayDirection2);

        Point[] intersectionPoints2 = sphere2.intersectSphere(ray2);

        if (intersectionPoints2.length > 0) {
            for (Point intersectionPoint : intersectionPoints2) {
                System.out.println("Intersection point: (" + intersectionPoint.x + ", " + intersectionPoint.y + ", " + intersectionPoint.z + ")");
            }
        } else {
            System.out.println("No intersection");
        }
    }

    public static void main(String[] args) {
        testSphereIntersection();
    }
}

