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
                new Vector(0, 0, 1)
        };

        Point[] rayOrigins = {
                new Point(1, 1, -1),
                new Point(1, 1, 1),
                new Point(1, 1, 0)
        };

        Vector[] rayDirections = {
                new Vector(0, 0, 1),
                new Vector(0, 0, 1),
                new Vector(0, 0, 1)
        };

        for (int i = 0; i < 3; i++) {
            Plane plane = new Plane(planePoints[i], planeNormals[i]);
            Ray ray = new Ray(rayOrigins[i], rayDirections[i]);
            float t = plane.intersectPlane(ray);

            if (t == Float.POSITIVE_INFINITY) {
                System.out.println("No intersection. Ray is parallel to the plane");
            } else if (t == 0) {
                System.out.println("No intersection. Ray lies within the plane");
            } else {
                Point intersectionPoint = rayOrigins[i].add(rayDirections[i].multiply(t));
                System.out.println("Intersection point: (" + intersectionPoint.x + ", " + intersectionPoint.y + ", " + intersectionPoint.z + ")");
            }
        }
    }

    public static void main(String[] args) {
        testPlaneIntersection();
    }
}
