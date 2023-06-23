package Tests;

import Objects.Triangle;
import Structures.*;

// Випадок 1: промінь перетинає трикутник
// Випадок 2: промінь не перетинає трикутник

public class TriangleIntersectionTest {
    public static void testTriangleIntersection() {
        Point[] vertex0 = {
                new Point(0, 0, 0),
                new Point(0, 0, 0)
        };

        Point[] vertex1 = {
                new Point(1, 0, 0),
                new Point(1, 0, 0)
        };

        Point[] vertex2 = {
                new Point(0, 1, 0),
                new Point(0, 1, 0)
        };

        Point[] rayOrigins = {
                new Point(0.5f, 0.5f, -1),
                new Point(1, 1, -1)
        };

        Vector[] rayDirections = {
                new Vector(0, 0, 1),
                new Vector(0, 0, 1)
        };

        for (int i = 0; i < 2; i++) {
            Triangle triangle = new Triangle(vertex0[i], vertex1[i], vertex2[i]);
            Ray ray = new Ray(rayOrigins[i], rayDirections[i]);
            Point[] intersectionPoints = triangle.intersect(ray);

            if (intersectionPoints != null) {
                System.out.println("Intersection point for Triangle " + (i+1) + ":");
                for (Point intersectionPoint : intersectionPoints) {
                    System.out.println("(" + intersectionPoint.x + ", " + intersectionPoint.y + ", " + intersectionPoint.z + ")");
                }
            } else {
                System.out.println("No intersection point for Triangle " + (i+1));
            }
        }
    }

    public static void main(String[] args) {
        testTriangleIntersection();
    }
}
