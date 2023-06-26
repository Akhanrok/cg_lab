package Tests;

import ImageConverter.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImageConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the input filename:");
        String inputFilePath = scanner.nextLine();

        System.out.println("Enter the output filename:");
        String outputFilePath = scanner.nextLine();

        // Отримуємо розширення файлу
        String inputExtension = getFileExtension(inputFilePath);
        String outputExtension = getFileExtension(outputFilePath);

        // Створюємо відповідні пари формату файлу та класів для читання/запису зображень
        Map<String, Class<? extends ImageReader>> supportedReaders = new HashMap<>();
        supportedReaders.put("bmp", BmpImageReader.class);
        supportedReaders.put("ppm", PpmImageReader.class);
        supportedReaders.put("gif", GifImageReader.class);
        supportedReaders.put("png", PngImageReader.class);

        Map<String, Class<? extends ImageWriter>> supportedWriters = new HashMap<>();
        supportedWriters.put("bmp", BmpImageWriter.class);
        supportedWriters.put("ppm", PpmImageWriter.class);
        supportedWriters.put("gif", GifImageWriter.class);
        supportedWriters.put("png", PngImageWriter.class);

        Class<? extends ImageReader> readerClass = supportedReaders.get(inputExtension);
        Class<? extends ImageWriter> writerClass = supportedWriters.get(outputExtension);

        if (readerClass == null || writerClass == null) {
            System.out.println("Error: Invalid file format.");
            return;
        }

        try {
            if (inputExtension.equals("gif")) {
                System.out.println("Conversion of GIF images is not implemented.");
                return;
            }

            ImageReader imageReader = readerClass.getDeclaredConstructor().newInstance();
            ImageWriter imageWriter = writerClass.getDeclaredConstructor().newInstance();

            int[][][] imagePixels = imageReader.readImage(inputFilePath);
            imageWriter.writeImage(imagePixels, outputFilePath);
            System.out.println("Image converted successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while converting the image.");
            e.printStackTrace();
        }
    }

    private static String getFileExtension(String filePath) {
        int extensionIndex = filePath.lastIndexOf('.');
        if (extensionIndex == -1) {
            return "";
        }
        return filePath.substring(extensionIndex + 1).toLowerCase();
    }
}
