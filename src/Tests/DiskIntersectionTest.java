package Tests;

import Objects.Disk;
import Structures.Point;
import Structures.Ray;
import Structures.Vector;

public class DiskIntersectionTest {
    public static void testDiskIntersection() {
        // Випадок 1: промінь перетинає диск
        Point diskCenter1 = new Point(2, 0, 1);
        Vector diskNormal1 = new Vector(0, 0, 1);
        float diskRadius1 = 1;
        Disk disk1 = new Disk(diskCenter1, diskNormal1, diskRadius1);

        Point rayOrigin1 = new Point(2, 0, -1);
        Vector rayDirection1 = new Vector(0, 0, 1);
        Ray ray1 = new Ray(rayOrigin1, rayDirection1);

        float t1 = disk1.intersectDisk(ray1);

        if (t1 == Float.POSITIVE_INFINITY) {
            System.out.println("No intersection. Ray lies outside the plane");
        } else if (t1 == 0) {
            System.out.println("No intersection. Intersection point lies outside the disk");
        } else {
            Point intersectionPoint1 = rayOrigin1.add(rayDirection1.multiply(t1));
            System.out.println("Intersection point: (" + intersectionPoint1.x + ", " + intersectionPoint1.y + ", " + intersectionPoint1.z + ")");
        }

        // Випадок 2: промінь не належить площині
        Point diskCenter2 = new Point(0, 0, 0);
        Vector diskNormal2 = new Vector(0, 1, 0);
        float diskRadius2 = 1;
        Disk disk2 = new Disk(diskCenter2, diskNormal2, diskRadius2);

        Point rayOrigin2 = new Point(1, 1, 1);
        Vector rayDirection2 = new Vector(0, 0, 1);
        Ray ray2 = new Ray(rayOrigin2, rayDirection2);

        float t2 = disk2.intersectDisk(ray2);

        if (t2 == Float.POSITIVE_INFINITY) {
            System.out.println("No intersection. Ray lies outside the plane");
        } else if (t2 == 0) {
            System.out.println("No intersection. Intersection point lies outside the disk");
        } else {
            Point intersectionPoint2 = rayOrigin2.add(rayDirection2.multiply(t2));
            System.out.println("Intersection point: (" + intersectionPoint2.x + ", " + intersectionPoint2.y + ", " + intersectionPoint2.z + ")");
        }

        // Випадок 3: точка перетину поза межами диску
        Point diskCenter3 = new Point(1, 0, 1);
        Vector diskNormal3 = new Vector(0, 0, 1);
        float diskRadius3 = 1;
        Disk disk3 = new Disk(diskCenter3, diskNormal3, diskRadius3);

        Point rayOrigin3 = new Point(1, 0, -1);
        Vector rayDirection3 = new Vector(0, 0, 2);
        Ray ray3 = new Ray(rayOrigin3, rayDirection3);

        float t3 = disk3.intersectDisk(ray3);

        if (t3 == Float.POSITIVE_INFINITY) {
            System.out.println("No intersection. Ray lies outside the plane");
        } else if (t3 == 0) {
            System.out.println("No intersection. Intersection point lies outside the disk");
        } else {
            Point intersectionPoint3 = rayOrigin3.add(rayDirection3.multiply(t3));
            System.out.println("Intersection point: (" + intersectionPoint3.x + ", " + intersectionPoint3.y + ", " + intersectionPoint3.z + ")");
        }
    }

    public static void main(String[] args) {
        testDiskIntersection();
    }
}





