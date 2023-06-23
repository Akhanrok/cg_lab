package Objects;

import Structures.*;

public class Triangle implements Intersectable {
    private Point vertex0;
    private Point vertex1;
    private Point vertex2;
    private static final float kEpsilon = 0.0001f;

    public Triangle(Point vertex0, Point vertex1, Point vertex2) {
        this.vertex0 = vertex0;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    @Override
    public Point[] intersect(Ray ray) {
        Vector orig = new Vector(ray.origin.x, ray.origin.y, ray.origin.z);
        Vector dir = new Vector(ray.direction.x, ray.direction.y, ray.direction.z);

        Vector v0v1 = vertex1.subtract(vertex0);
        Vector v0v2 = vertex2.subtract(vertex0);
        Vector N = v0v1.crossProduct(v0v2);

        float NdotRayDirection = (float) N.dotProduct(dir);
        if (Math.abs(NdotRayDirection) < kEpsilon) {
            return null;
        }

        float d = (float) -N.dotProduct(new Vector(vertex0.x, vertex0.y, vertex0.z));
        float t = (float) (-(N.dotProduct(orig) + d) / NdotRayDirection);

        if (t < 0) {
            return null;
        }

        Point intersectionPoint = new Point(orig.x + t * dir.x, orig.y + t * dir.y, orig.z + t * dir.z);

        Vector C;

        Vector edge0 = vertex1.subtract(vertex0);
        Vector vp0 = intersectionPoint.subtract(vertex0);
        C = edge0.crossProduct(vp0);
        if (N.dotProduct(C) < 0) {
            return null;
        }

        Vector edge1 = vertex2.subtract(vertex1);
        Vector vp1 = intersectionPoint.subtract(vertex1);
        C = edge1.crossProduct(vp1);
        if (N.dotProduct(C) < 0) {
            return null;
        }

        Vector edge2 = vertex0.subtract(vertex2);
        Vector vp2 = intersectionPoint.subtract(vertex2);
        C = edge2.crossProduct(vp2);
        if (N.dotProduct(C) < 0) {
            return null;
        }

        return new Point[] { intersectionPoint };
    }

    @Override
    public Vector getNormal(Point point) {
        Vector v0v1 = vertex1.subtract(vertex0);
        Vector v0v2 = vertex2.subtract(vertex0);
        return v0v1.crossProduct(v0v2).normalize();
    }
}
