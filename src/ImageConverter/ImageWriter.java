package ImageConverter;

import java.io.IOException;

public interface ImageWriter {
    boolean canWrite(String format);
    void writeImage(int[][][] pixels, String filePath) throws IOException;
}
