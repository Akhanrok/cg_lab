package ImageConverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjImageReader implements ImageReader {
    private static final String VERTEX_PREFIX = "v";
    private static final String FACE_PREFIX = "f";

    @Override
    public boolean canRead(String filePath) {
        return filePath.toLowerCase().endsWith(".obj");
    }

    @Override
    public int[][][] readImage(String filePath) throws IOException {
        List<float[]> vertices = new ArrayList<>();
        List<float[]> faces = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(VERTEX_PREFIX)) {
                    float[] vertex = parseVertex(line);
                    vertices.add(vertex);
                } else if (line.startsWith(FACE_PREFIX)) {
                    float[] face = parseFace(line);
                    faces.add(face);
                }
            }
        }

        return visualizeModel(vertices, faces);
    }

    private float[] parseVertex(String line) {
        String[] tokens = line.split("\\s+");
        float x = Float.parseFloat(tokens[1]);
        float y = Float.parseFloat(tokens[2]);
        float z = Float.parseFloat(tokens[3]);
        return new float[]{x, y, z};
    }

    private float[] parseFace(String line) {
        String[] tokens = line.split("\\s+");
        float[] face = new float[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            String[] indices = tokens[i].split("/");
            int vertexIndex = Integer.parseInt(indices[0]) - 1;
            face[i - 1] = vertexIndex;
        }
        return face;
    }

    private int[][][] visualizeModel(List<float[]> vertices, List<float[]> faces) {
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float minZ = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float maxY = Float.MIN_VALUE;
        float maxZ = Float.MIN_VALUE;

        for (float[] vertex : vertices) {
            float x = vertex[0];
            float y = vertex[1];
            float z = vertex[2];

            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            minZ = Math.min(minZ, z);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            maxZ = Math.max(maxZ, z);
        }

        int width = (int) Math.ceil(maxX - minX);
        int height = (int) Math.ceil(maxY - minY);

        int[][][] pixels = new int[height][width][3];

        for (float[] face : faces) {
            float[] v1 = vertices.get((int) face[0]);
            float[] v2 = vertices.get((int) face[1]);
            float[] v3 = vertices.get((int) face[2]);

            int x1 = Math.round(v1[0] - minX);
            int y1 = Math.round(v1[1] - minY);
            int z1 = Math.round(v1[2] - minZ);
            int x2 = Math.round(v2[0] - minX);
            int y2 = Math.round(v2[1] - minY);
            int z2 = Math.round(v2[2] - minZ);
            int x3 = Math.round(v3[0] - minX);
            int y3 = Math.round(v3[1] - minY);
            int z3 = Math.round(v3[2] - minZ);

            fillTriangle(pixels, x1, y1, z1, x2, y2, z2, x3, y3, z3);
        }

        return pixels;
    }

    private void fillTriangle(int[][][] pixels, int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3) {
        int xMin = Math.min(x1, Math.min(x2, x3));
        int xMax = Math.max(x1, Math.max(x2, x3));
        int yMin = Math.min(y1, Math.min(y2, y3));
        int yMax = Math.max(y1, Math.max(y2, y3));

        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                if (isPointInTriangle(x, y, x1, y1, x2, y2, x3, y3)) {
                    pixels[y][x][0] = 255;
                    pixels[y][x][1] = 255;
                    pixels[y][x][2] = 255;
                }
            }
        }
    }

    private boolean isPointInTriangle(int x, int y, int x1, int y1, int x2, int y2, int x3, int y3) {
        float alpha = ((y2 - y3) * (x - x3) + (x3 - x2) * (y - y3)) / (float) ((y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3));
        float beta = ((y3 - y1) * (x - x3) + (x1 - x3) * (y - y3)) / (float) ((y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3));
        float gamma = 1.0f - alpha - beta;

        return alpha >= 0 && beta >= 0 && gamma >= 0;
    }
}


