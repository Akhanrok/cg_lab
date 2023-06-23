package ImageConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class PpmImageReader implements ImageReader {
    private static final Pattern PATTERN_COMMENT = Pattern.compile("#.*");
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
            int width = Integer.parseInt(sizeTokens[0]);
            int height = Integer.parseInt(sizeTokens[1]);

            int[][][] pixels = new int[height][width][3];

            int pixelCount = width * height;
            int currentPixel = 0;
            String line;
            while ((line = reader.readLine()) != null && currentPixel < pixelCount) {
                line = PATTERN_COMMENT.matcher(line).replaceAll("").trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] colorTokens = line.split("\\s+");

                int red = Integer.parseInt(colorTokens[0]);
                int green = Integer.parseInt(colorTokens[1]);
                int blue = Integer.parseInt(colorTokens[2]);
                pixels[currentPixel / width][currentPixel % width][0] = red;
                pixels[currentPixel / width][currentPixel % width][1] = green;
                pixels[currentPixel / width][currentPixel % width][2] = blue;

                currentPixel++;
            }

            return pixels;
        }
    }
}