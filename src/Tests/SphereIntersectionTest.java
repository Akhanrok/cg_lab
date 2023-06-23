package Tests;

import Objects.Sphere;
import Structures.*;

// Випадок 1: промінь перетинає сферу
// Випадок 2: промінь не перетинає сферу

public class SphereIntersectionTest {
    public static void testSphereIntersection() {
        Point[] sphereCenters = {
                new Point(0, 0, 0),
                new Point(0, 0, 0)
        };

        float[] sphereRadii = {
                1,
                1.5f
        };

        Point[] rayOrigins = {
                new Point(0, 0, -2),
                new Point(0, 2, -2)
        };

        Vector[] rayDirections = {
                new Vector(0, 0, 1),
                new Vector(0, 0, 1)
        };

        for (int i = 0; i < 2; i++) {
            Sphere sphere = new Sphere(sphereCenters[i], sphereRadii[i]);
            Ray ray = new Ray(rayOrigins[i], rayDirections[i]);
            Point[] intersectionPoints = sphere.intersect(ray);

            if (intersectionPoints != null && intersectionPoints.length > 0) {
                System.out.println("Intersection points for Sphere " + (i+1) + ":");
                for (Point intersectionPoint : intersectionPoints) {
                    System.out.println("(" + intersectionPoint.x + ", " + intersectionPoint.y + ", " + intersectionPoint.z + ")");
                }
            } else {
                System.out.println("No intersection points for Sphere " + (i+1));
            }
        }
    }

    public static void main(String[] args) {
        testSphereIntersection();
    }
}
