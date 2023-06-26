package Tests;

import ImageConverter.*;
import Objects.Triangle;
import RayTracer.RayTracer;
import Structures.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ModelVisualizerTest {
    public static void main(String[] args) {
        Camera camera = new Camera(new Point(1, 0, -12), new Vector(0, 0, 1), new Vector(1, 0, 0), new Vector(0, 1, 0));
        LightSource light = new LightSource(new Vector(1, -1, -1).normalize());
        int width = 1000;
        int height = 800;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the input filename:");
        String inputFilePath = scanner.nextLine();

        try {
            List<Triangle> triangles = ObjImageReader.readObjFile(inputFilePath);
            RayTracer rayTracer = new RayTracer(width, height, camera, triangles.toArray(new Triangle[0]), light);
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
            case '.' -> new int[]{64, 64, 64};
            case '*' -> new int[]{128, 128, 128};
            case 'O' -> new int[]{192, 192, 192};
            case '#' -> new int[]{255, 255, 255};
            default -> new int[]{0, 0, 0};
        };
    }
}