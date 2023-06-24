package ImageConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class PpmImageReader implements ImageReader {
    private static final Pattern PATTERN_SIZE = Pattern.compile("(\\d+)\\s+(\\d+)");

    @Override
    public boolean canRead(String filePath) {
        return filePath.toLowerCase().endsWith(".ppm");
    }

    @Override
    public int[][][] readImage(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String magicNumber = reader.readLine().trim();
            if (!magicNumber.equals("P3")) {
                throw new ImageFormatException("Invalid magic number");
            }

            String sizeLine = reader.readLine().trim();
            if (!PATTERN_SIZE.matcher(sizeLine).matches()) {
                throw new ImageFormatException("Invalid image size");
            }
            String[] sizeTokens = sizeLine.split("\\s+");

            String colorMaxLine = reader.readLine().trim();
            int colorMax = Integer.parseInt(colorMaxLine);

            int width = Integer.parseInt(sizeTokens[0]);
            int height = Integer.parseInt(sizeTokens[1]);
            int[][][] pixels = new int[height][width][3];

            int red, green, blue;
            for (int y = 0; y < height; y++) {
                String line = reader.readLine().trim();
                String[] colorTokens = line.split("\\s+");
                for (int x = 0; x < width; x++) {
                    red = Integer.parseInt(colorTokens[3 * x]);
                    green = Integer.parseInt(colorTokens[3 * x + 1]);
                    blue = Integer.parseInt(colorTokens[3 * x + 2]);

                    // Normalize the pixel values based on the maximum color value
                    pixels[y][x][0] = (int) ((red / (double) colorMax) * 255);
                    pixels[y][x][1] = (int) ((green / (double) colorMax) * 255);
                    pixels[y][x][2] = (int) ((blue / (double) colorMax) * 255);
                }
            }

            return pixels;
        }
    }
}
