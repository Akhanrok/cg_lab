package Tests;

import Objects.Triangle;
import Structures.Point;
import Structures.Ray;
import Structures.Vector;

public class TriangleIntersectionTest {
    public static void testTriangleIntersection() {
        Point v0 = new Point(0f, 0f, 0f);
        Point v1 = new Point(1f, 0f, 0f);
        Point v2 = new Point(0f, 1f, 0f);
        Triangle triangle = new Triangle(v0, v1, v2);

        // Випадок 1: промінь перетинає трикутник
        Point rayOrigin1 = new Point(0.5f, 0.5f, -1f);
        Vector rayDirection1 = new Vector(0f, 0f, 1f);
        Ray ray = new Ray(rayOrigin1, rayDirection1);

        Point intersectionPoint1 = triangle.intersectTriangle(ray);

        if (intersectionPoint1 != null) {
            System.out.println("Intersection point: (" + intersectionPoint1.x + ", " + intersectionPoint1.y + ", " + intersectionPoint1.z + ")");
        } else {
            System.out.println("No intersection");
        }

        // Випадок 2: промінь не перетинає трикутник
        Point rayOrigin2 = new Point(1f, 1f, -1f);
        Vector rayDirection2 = new Vector(0f, 0f, 1f);
        Ray ray2 = new Ray(rayOrigin2, rayDirection2);

        Point intersectionPoint2 = triangle.intersectTriangle(ray2);

        if (intersectionPoint2 != null) {
            System.out.println("Intersection point: (" + intersectionPoint2.x + ", " + intersectionPoint2.y + ", " + intersectionPoint2.z + ")");
        } else {
            System.out.println("No intersection");
        }
    }

    public static void main(String[] args) {
        testTriangleIntersection();
    }
}

