package RayTracer;

import Objects.Intersectable;
import Structures.Camera;
import Structures.LightSource;
import Structures.Point;
import Structures.Ray;
import Structures.Vector;

public class RayTracer {
    private int width;
    private int height;
    private Camera camera;
    private Intersectable[] objects;
    private LightSource light;

    public RayTracer(int width, int height, Camera camera, Intersectable[] objects, LightSource light) {
        this.width = width;
        this.height = height;
        this.camera = camera;
        this.objects = objects;
        this.light = light;
    }

    public void renderToConsole() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Ray ray = generateRay(x, y);
                char symbol = traceRay(ray);
                System.out.print(symbol + "  ");
            }
            System.out.println();
        }
    }

    private Ray generateRay(int x, int y) {
        float u = (float) x / (width - 1);
        float v = (float) y / (height - 1);

        Vector right = camera.getRight();
        Vector up = camera.getUp();
        Vector direction = camera.getDirection();

        Vector rayDirection = direction.add(right.multiply(u - 0.5f)).add(up.multiply(v - 0.5f));
        rayDirection = rayDirection.normalize();

        Point rayOrigin = camera.getPosition();
        return new Ray(rayOrigin, rayDirection);
    }

    private char traceRay(Ray ray) {
        Point nearestIntersection = null;
        Intersectable nearestObject = null;

        for (Intersectable object : objects) {
            Point[] intersectionPoints = object.intersect(ray);

            if (intersectionPoints != null) {
                for (Point intersection : intersectionPoints) {
                    if (nearestIntersection == null || intersection.getDistance(ray.getOrigin()) < nearestIntersection.getDistance(ray.getOrigin())) {
                        nearestIntersection = intersection;
                        nearestObject = object;
                    }
                }
            }
        }

        if (nearestIntersection != null) {
            return shadePixel(ray, nearestIntersection, nearestObject);
        }

        return ' ';
    }

    private char shadePixel(Ray ray, Point intersectionPoint, Intersectable object) {
        Vector normal = object.getNormal(intersectionPoint);
        Vector lightDirection = light.getDirection().normalize();

        float intensity = (float) normal.dotProduct(lightDirection);
        intensity = Math.max(intensity, 0);

        if (intensity < 0) {
            return ' ';
        } else if (intensity < 0.2) {
            return '.';
        } else if (intensity < 0.5) {
            return '*';
        } else if (intensity < 0.8) {
            return 'O';
        } else {
            return '#';
        }
    }
}