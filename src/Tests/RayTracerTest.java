package Tests;

import Objects.*;
import RayTracer.RayTracer;
import Structures.*;

public class RayTracerTest {
    public static void main(String[] args) {
        // Define the scene
        int width = 20;
        int height = 20;
        Camera camera = new Camera(new Point(0, 0, -10), new Vector(0, 0, 1), new Vector(1, 0, 0), new Vector(0, 1, 0));
        LightSource light = new LightSource(new Vector(1, -1, -1).normalize());

        Intersectable[] objects = {
                new Disk(new Point(0, 0, 0), new Vector(0, 0, 1), 4),
                new Sphere(new Point(0, 0, 0), 3),
                new Plane(new Point(0, 0, 0), new Vector(0, 0, -1)),
                new Triangle(new Point(-5, 5, 0), new Point(-3, 5, 0), new Point(-5, 3, 0))
        };

        // Create the ray tracer
        RayTracer rayTracer = new RayTracer(width, height, camera, objects, light);

        // Render the scene to console
        rayTracer.renderToConsole();
    }
}
