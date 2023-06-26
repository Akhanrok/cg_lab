package Tests;

import ImageConverter.*;
import Objects.Triangle;
import RayTracer.RayTracer;
import Structures.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ModelVisualizerConsoleTest {
    public static void main(String[] args) {
        Camera camera = new Camera(new Point(1, 0, -12), new Vector(0, 0, 1), new Vector(1, 0, 0), new Vector(0, 1, 0));
        LightSource light = new LightSource(new Vector(1, -1, -1).normalize());
        int width = 80;
        int height = 60;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the input filename:");
        String inputFilePath = scanner.nextLine();

        try {
            List<Triangle> triangles = ObjImageReader.readObjFile(inputFilePath);
            RayTracer rayTracer = new RayTracer(width, height, camera, triangles.toArray(new Triangle[0]), light);
            rayTracer.render();

            char[][] pixels = rayTracer.getPixels();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    System.out.print(pixels[height - 1 - y][x] + "  ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
