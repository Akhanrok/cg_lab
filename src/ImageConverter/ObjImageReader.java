package ImageConverter;

import Objects.Triangle;
import Structures.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjImageReader {
    public static List<Triangle> readObjFile(String filePath) throws IOException {
        List<Triangle> triangles = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        List<Point> vertices = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("v ")) {
                String[] parts = line.split("\\s+");
                float x = Float.parseFloat(parts[1]);
                float y = Float.parseFloat(parts[2]);
                float z = Float.parseFloat(parts[3]);
                vertices.add(new Point(x, y, z));
            } else if (line.startsWith("f ")) {
                String[] parts = line.split("\\s+");
                int v0 = Integer.parseInt(parts[1]) - 1;
                int v1 = Integer.parseInt(parts[2]) - 1;
                int v2 = Integer.parseInt(parts[3]) - 1;
                triangles.add(new Triangle(vertices.get(v0), vertices.get(v1), vertices.get(v2)));
            }
        }

        reader.close();

        return triangles;
    }
}

