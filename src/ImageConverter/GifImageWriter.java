package ImageConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class GifImageWriter implements ImageWriter {
    @Override
    public boolean canWrite(String filePath) {
        return filePath.toLowerCase().endsWith(".gif");
    }

    @Override
    public void writeImage(int[][][] pixels, String filePath) throws IOException {
        int width = pixels[0].length;
        int height = pixels.length;
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

        ImageIO.write(image, "GIF", new FileOutputStream(filePath));
    }
}

