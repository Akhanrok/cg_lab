package Objects;

import Structures.*;

public interface Intersectable {
    Point[] intersect(Ray ray);
    Vector getNormal(Point point);
}


