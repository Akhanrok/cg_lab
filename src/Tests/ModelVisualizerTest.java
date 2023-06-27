package Tests;

import ImageConverter.*;
import Objects.*;
import RayTracer.*;
import Structures.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModelVisualizerTest {
    public static void main(String[] args) {
        Camera camera = new Camera(new Point(1, 0, -12), new Vector(0, 0, 1), new Vector(1, 0, 0), new Vector(0, 1, 0));
        LightSource light = new LightSource(new Point(1, 0, -1), new Vector(1, -1, -1).normalize());
        int width = 1000;
        int height = 800;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the input filename:");
        String inputFilePath = scanner.nextLine();

        try {
            List<Triangle> triangles = ObjImageReader.readObjFile(inputFilePath);

            Point groundCenter = new Point(0, -4, 0);
            float groundRadius = 4;
            Sphere groundSphere = new Sphere(groundCenter, groundRadius);
            triangles.addAll(getTrianglesFromSphere(groundSphere));

            Triangle[] triangleArray = triangles.toArray(new Triangle[0]);

            RayTracer rayTracer = new RayTracer(width, height, camera, triangleArray, light);
            rayTracer.render();
            char[][] pixels = rayTracer.getPixels();
            int[][][] rgbPixels = new int[height][width][3];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    char symbol = pixels[y][x];
                    int[] rgb = getRGB(symbol);
                    rgbPixels[height - 1 - y][x][0] = rgb[0];
                    rgbPixels[height - 1 - y][x][1] = rgb[1];
                    rgbPixels[height - 1 - y][x][2] = rgb[2];
                }
            }

            String ppmFilePath = inputFilePath.replace(".obj", ".ppm");
            String pngFilePath = inputFilePath.replace(".obj", ".png");

            PpmImageWriter ppmWriter = new PpmImageWriter();
            ppmWriter.writeImage(rgbPixels, ppmFilePath);

            PngImageWriter pngWriter = new PngImageWriter();
            pngWriter.writeImage(rgbPixels, pngFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] getRGB(char symbol) {
        return switch (symbol) {
            case '-' -> new int[]{0, 0, 0};
            case '.' -> new int[]{64, 64, 64};
            case '*' -> new int[]{128, 128, 128};
            case 'O' -> new int[]{192, 192, 192};
            case '#' -> new int[]{224, 224, 224};
            default -> new int[]{255, 255, 255};
        };
    }

    private static List<Triangle> getTrianglesFromSphere(Sphere sphere) {
        List<Triangle> triangles = new ArrayList<>();

        int numSegments = 32;
        int numRings = 16;

        for (int ring = 0; ring < numRings; ring++) {
            float phi1 = (float) ring / numRings * (float) Math.PI;
            float phi2 = (float) (ring + 1) / numRings * (float) Math.PI;

            for (int segment = 0; segment < numSegments; segment++) {
                float theta1 = (float) segment / numSegments * 2 * (float) Math.PI;
                float theta2 = (float) (segment + 1) / numSegments * 2 * (float) Math.PI;

                Point p1 = getSphericalPoint(sphere, phi1, theta1);
                Point p2 = getSphericalPoint(sphere, phi1, theta2);
                Point p3 = getSphericalPoint(sphere, phi2, theta1);
                Point p4 = getSphericalPoint(sphere, phi2, theta2);

                Triangle triangle1 = new Triangle(p1, p3, p2);
                Triangle triangle2 = new Triangle(p3, p4, p2);

                triangles.add(triangle1);
                triangles.add(triangle2);
            }
        }

        return triangles;
    }

    private static Point getSphericalPoint(Sphere sphere, float phi, float theta) {
        float x = (float) (sphere.getRadius() * Math.sin(phi) * Math.cos(theta));
        float y = (float) (sphere.getRadius() * Math.sin(phi) * Math.sin(theta));
        float z = (float) (sphere.getRadius() * Math.cos(phi));

        return sphere.getCenter().add(new Vector(x, y, z));
    }
}