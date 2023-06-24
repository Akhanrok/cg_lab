package ImageConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PngImageWriter implements ImageWriter {
    @Override
    public boolean canWrite(String format) {
        return format.equalsIgnoreCase("png");
    }

    @Override
    public void writeImage(int[][][] pixels, String filePath) throws IOException {
        int height = pixels.length;
        int width = pixels[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int red = pixels[y][x][0];
                int green = pixels[y][x][1];
                int blue = pixels[y][x][2];
                int rgb = (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, rgb);
            }
        }

        File outputFile = new File(filePath);
        ImageIO.write(image, "png", outputFile);
    }
}
