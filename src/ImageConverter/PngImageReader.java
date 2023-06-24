package ImageConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PngImageReader implements ImageReader {
    @Override
    public boolean canRead(String filePath) {
        return filePath.toLowerCase().endsWith(".png");
    }

    @Override
    public int[][][] readImage(String filePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(filePath));

        int height = image.getHeight();
        int width = image.getWidth();

        int[][][] pixels = new int[height][width][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                pixels[y][x][0] = red;
                pixels[y][x][1] = green;
                pixels[y][x][2] = blue;
            }
        }

        return pixels;
    }
}
