package ImageConverter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BmpImageReader implements ImageReader {
    private static final int HEADER_SIZE = 14;
    private static final int INFO_HEADER_SIZE = 40;
    private static final int BYTES_PER_PIXEL = 3;

    @Override
    public boolean canRead(String filePath) {
        return filePath.toLowerCase().endsWith(".bmp");
    }

    @Override
    public int[][][] readImage(String filePath) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] header = new byte[HEADER_SIZE];
            bis.read(header);

            byte[] infoHeader = new byte[INFO_HEADER_SIZE];
            bis.read(infoHeader);

            int width = ByteBuffer.wrap(infoHeader, 4, 4).getInt();
            int height = ByteBuffer.wrap(infoHeader, 8, 4).getInt();

            int paddingBytes = calculatePaddingBytes(width);

            int[][][] pixels = new int[height][width][3];
            byte[] pixelData = new byte[width * BYTES_PER_PIXEL + paddingBytes];
            for (int y = height - 1; y >= 0; y--) {
                bis.read(pixelData);
                int dataIndex = 0;
                for (int x = 0; x < width; x++) {
                    int blue = pixelData[dataIndex++] & 0xFF;
                    int green = pixelData[dataIndex++] & 0xFF;
                    int red = pixelData[dataIndex++] & 0xFF;
                    pixels[y][x][0] = red;
                    pixels[y][x][1] = green;
                    pixels[y][x][2] = blue;
                }
            }

            return pixels;
        }
    }

    private int calculatePaddingBytes(int width) {
        int bytesPerRow = width * BYTES_PER_PIXEL;
        int paddingBytes = 0;
        if (bytesPerRow % 4 != 0) {
            paddingBytes = 4 - (bytesPerRow % 4);
        }
        return paddingBytes;
    }
}

