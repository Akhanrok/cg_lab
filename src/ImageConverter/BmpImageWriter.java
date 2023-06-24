package ImageConverter;

import java.io.FileOutputStream;
import java.io.IOException;

public class BmpImageWriter implements ImageWriter {
    @Override
    public boolean canWrite(String format) {
        return format.equalsIgnoreCase("bmp");
    }

    @Override
    public void writeImage(int[][][] pixels, String filePath) throws IOException {
        int height = pixels.length;
        int width = pixels[0].length;

        byte[] imageData = convertToBmp(pixels, width, height);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(imageData);
        }
    }

    private byte[] convertToBmp(int[][][] pixels, int width, int height) {
        int paddingBytes = (4 - (width * 3) % 4) % 4;

        int imageSize = (width * 3 + paddingBytes) * height;

        byte[] fileHeader = createFileHeader(imageSize);

        byte[] imageHeader = createImageHeader(width, height);

        byte[] pixelData = createPixelData(pixels, width, height, paddingBytes);

        byte[] imageData = new byte[fileHeader.length + imageHeader.length + pixelData.length];
        System.arraycopy(fileHeader, 0, imageData, 0, fileHeader.length);
        System.arraycopy(imageHeader, 0, imageData, fileHeader.length, imageHeader.length);
        System.arraycopy(pixelData, 0, imageData, fileHeader.length + imageHeader.length, pixelData.length);

        return imageData;
    }

    private byte[] createFileHeader(int imageSize) {
        byte[] header = new byte[14];

        header[0] = 0x42;
        header[1] = 0x4D;

        // File size
        int fileSize = imageSize + 54;  // Image size + header size
        header[2] = (byte) (fileSize & 0xFF);
        header[3] = (byte) ((fileSize >> 8) & 0xFF);
        header[4] = (byte) ((fileSize >> 16) & 0xFF);
        header[5] = (byte) ((fileSize >> 24) & 0xFF);

        // Reserved
        header[6] = 0x00;
        header[7] = 0x00;
        header[8] = 0x00;
        header[9] = 0x00;

        // Pixel data offset
        header[10] = 0x36;
        header[11] = 0x00;
        header[12] = 0x00;
        header[13] = 0x00;

        return header;
    }

    private byte[] createImageHeader(int width, int height) {
        byte[] header = new byte[40];

        // Header size
        header[0] = 0x28;
        header[1] = 0x00;
        header[2] = 0x00;
        header[3] = 0x00;

        // Image width
        header[4] = (byte) (width & 0xFF);
        header[5] = (byte) ((width >> 8) & 0xFF);
        header[6] = (byte) ((width >> 16) & 0xFF);
        header[7] = (byte) ((width >> 24) & 0xFF);

        // Image height
        header[8] = (byte) (height & 0xFF);
        header[9] = (byte) ((height >> 8) & 0xFF);
        header[10] = (byte) ((height >> 16) & 0xFF);
        header[11] = (byte) ((height >> 24) & 0xFF);

        // Planes
        header[12] = 0x01;
        header[13] = 0x00;

        // Bits per pixel
        header[14] = 0x18;
        header[15] = 0x00;

        // Compression (none)
        header[16] = 0x00;
        header[17] = 0x00;
        header[18] = 0x00;
        header[19] = 0x00;

        // Image size (can be 0 for uncompressed images)
        header[20] = 0x00;
        header[21] = 0x00;
        header[22] = 0x00;
        header[23] = 0x00;

        // X pixels per meter (can be 0 for default)
        header[24] = 0x00;
        header[25] = 0x00;
        header[26] = 0x00;
        header[27] = 0x00;

        // Y pixels per meter (can be 0 for default)
        header[28] = 0x00;
        header[29] = 0x00;
        header[30] = 0x00;
        header[31] = 0x00;

        // Total colors (0 for no palette)
        header[32] = 0x00;
        header[33] = 0x00;
        header[34] = 0x00;
        header[35] = 0x00;

        // Important colors (0 for all)
        header[36] = 0x00;
        header[37] = 0x00;
        header[38] = 0x00;
        header[39] = 0x00;

        return header;
    }

    private byte[] createPixelData(int[][][] pixels, int width, int height, int paddingBytes) {
        byte[] pixelData = new byte[(width * 3 + paddingBytes) * height];
        int dataIndex = 0;

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                byte red = (byte) pixels[y][x][0];
                byte green = (byte) pixels[y][x][1];
                byte blue = (byte) pixels[y][x][2];
                pixelData[dataIndex++] = blue;
                pixelData[dataIndex++] = green;
                pixelData[dataIndex++] = red;
            }

            for (int i = 0; i < paddingBytes; i++) {
                pixelData[dataIndex++] = 0x00;
            }
        }

        return pixelData;
    }
}
