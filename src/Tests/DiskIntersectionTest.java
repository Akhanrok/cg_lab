package Tests;

import Objects.Disk;
import Structures.*;

// Випадок 1: промінь перетинає диск
// Випадок 2: промінь не належить площині
// Випадок 3: точка перетину поза межами диску

public class DiskIntersectionTest {
    public static void testDiskIntersection() {
        Point[] diskCenters = {
                new Point(0, 0, 0),
                new Point(1, 2, 3),
                new Point(-1, 0, 2)
        };

        Vector[] diskNormals = {
                new Vector(0, 0, 1),
                new Vector(1, 0, 0),
                new Vector(0, 1, 0)
        };

        float[] diskRadius = {
                2,
                1.5f,
                0.5f
        };

        Point[] rayOrigins = {
                new Point(1, 1, -1),
                new Point(-1, 0, 0),
                new Point(0, 0, 0)
        };

        Vector[] rayDirections = {
                new Vector(0, 0, 1),
                new Vector(1, 0, 0),
                new Vector(0, 1, 0)
        };

        for (int i = 0; i < 3; i++) {
            Disk disk = new Disk(diskCenters[i], diskNormals[i], diskRadius[i]);
            Ray ray = new Ray(rayOrigins[i], rayDirections[i]);
            Point[] intersectionPoints = disk.intersect(ray);

            if (intersectionPoints != null) {
                System.out.println("Intersection point for Disk " + (i+1) + ":");
                for (Point intersectionPoint : intersectionPoints) {
                    System.out.println("(" + intersectionPoint.x + ", " + intersectionPoint.y + ", " + intersectionPoint.z + ")");
                }
            } else {
                System.out.println("No intersection point for Disk " + (i+1));
            }
        }
    }

    public static void main(String[] args) {
        testDiskIntersection();
    }
}
