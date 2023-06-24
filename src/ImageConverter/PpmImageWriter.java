package ImageConverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PpmImageWriter implements ImageWriter {
    @Override
    public boolean canWrite(String format) {
        return format.equalsIgnoreCase("ppm");
    }

    @Override
    public void writeImage(int[][][] pixels, String filePath) throws IOException {
        int height = pixels.length;
        int width = pixels[0].length;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("P3");
            writer.newLine();

            writer.write(width + " " + height);
            writer.newLine();

            writer.write("255");
            writer.newLine();

            for (int[][] pixel : pixels) {
                for (int x = 0; x < width; x++) {
                    int red = pixel[x][0];
                    int green = pixel[x][1];
                    int blue = pixel[x][2];
                    writer.write(red + " " + green + " " + blue + " ");
                }
                writer.newLine();
            }
        }
    }
}
