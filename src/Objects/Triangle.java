package Objects;

import Structures.Point;
import Structures.Ray;
import Structures.Vector;

public class Triangle {
    private Point v0, v1, v2;
    private static final float kEpsilon = 0.0001f;

    public Triangle(Point v0, Point v1, Point v2) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
    }

    public Point intersectTriangle(Ray ray) {
        Vector orig = new Vector(ray.origin.x, ray.origin.y, ray.origin.z);
        Vector dir = new Vector(ray.direction.x, ray.direction.y, ray.direction.z);

        Vector v0v1 = v1.subtract(v0);
        Vector v0v2 = v2.subtract(v0);
        Vector N = v0v1.crossProduct(v0v2);

        float NdotRayDirection = (float) N.dotProduct(dir);
        if (Math.abs(NdotRayDirection) < kEpsilon) {
            return null;
        }

        float d = (float) -N.dotProduct(new Vector(v0.x, v0.y, v0.z));
        float t = (float) (-(N.dotProduct(orig) + d) / NdotRayDirection);

        if (t < 0) {
            return null;
        }

        Point P = new Point(orig.x + t * dir.x, orig.y + t * dir.y, orig.z + t * dir.z);

        Vector C;

        Vector edge0 = v1.subtract(v0);
        Vector vp0 = P.subtract(v0);
        C = edge0.crossProduct(vp0);
        if (N.dotProduct(C) < 0) {
            return null;
        }

        Vector edge1 = v2.subtract(v1);
        Vector vp1 = P.subtract(v1);
        C = edge1.crossProduct(vp1);
        if (N.dotProduct(C) < 0) {
            return null;
        }

        Vector edge2 = v0.subtract(v2);
        Vector vp2 = P.subtract(v2);
        C = edge2.crossProduct(vp2);
        if (N.dotProduct(C) < 0) {
            return null;
        }

        return P;
    }
}

