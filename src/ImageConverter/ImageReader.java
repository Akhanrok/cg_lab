package ImageConverter;

import java.io.IOException;

public interface ImageReader {
    boolean canRead(String filePath);
    int[][][] readImage(String filePath) throws IOException;
}

