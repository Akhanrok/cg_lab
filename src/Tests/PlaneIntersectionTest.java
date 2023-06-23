package Tests;

import Objects.Plane;
import Structures.Point;
import Structures.Ray;
import Structures.Vector;

// Випадок 1: промінь перетинає площину
// Випадок 2: промінь паралельний до площини
// Випадок 3: промінь належить площині

public class PlaneIntersectionTest {
    public static void testPlaneIntersection() {
        Point[] planePoints = {
                new Point(0, 0, 0),
                new Point(0, 0, 0),
                new Point(0, 0, 0)
        };

        Vector[] planeNormals = {
                new Vector(0, 0, 1),
                new Vector(0, 1, 0),
                new Vector(0, 1, 0)
        };

        Point[] rayOrigins = {
                new Point(1, 1, -1),
                new Point(1, 1, 1),
                new Point(0, 0, 0)
        };

        Vector[] rayDirections = {
                new Vector(0, 0, 1),
                new Vector(0, 0, 1),
                new Vector(0, 0, 1)
        };

        for (int i = 0; i < 3; i++) {
            Plane plane = new Plane(planePoints[i], planeNormals[i]);
            Ray ray = new Ray(rayOrigins[i], rayDirections[i]);
            Point[] intersectionPoints = plane.intersect(ray);

            if (intersectionPoints != null) {
                System.out.println("Intersection point for Plane " + (i+1) + ":");
                for (Point intersectionPoint : intersectionPoints) {
                    System.out.println("(" + intersectionPoint.x + ", " + intersectionPoint.y + ", " + intersectionPoint.z + ")");
                }
            } else {
                System.out.println("No intersection point for Plane " + (i+1));
            }
        }
    }

    public static void main(String[] args) {
        testPlaneIntersection();
    }
}
