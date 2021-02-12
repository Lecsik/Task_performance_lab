import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class task2 {

    private static class Point {
        public final float x;
        public final float y;

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] arg) throws IOException {
        Path polygonPath = Path.of(arg[0]);
        Path pointPath = Path.of(arg[1]);

        List<String> polygonString = Files.readAllLines(polygonPath);
        List<String> pointsStrings = Files.readAllLines(pointPath);
        List<Point> polygon = new ArrayList<>();
        List<Point> points = new ArrayList<>();

        for (String s : polygonString) {
            String[] subStr = s.split(" ");
            polygon.add(new Point(Float.parseFloat(subStr[0]), Float.parseFloat(subStr[1])));
        }
        for (String s : pointsStrings) {
            String[] subStr = s.split(" ");
            points.add(new Point(Float.parseFloat(subStr[0]), Float.parseFloat(subStr[1])));
        }

        for (Point point : points) {
            System.out.println(pointPlace(point, polygon));
        }
    }


    private static int pointPlace(Point point, List<Point> polygon) {
        for (Point value : polygon) {
            if (point.x == value.x && point.y == value.y) return 0;
        }

        int j = polygon.size() - 1;
        for (int i = 0; i < polygon.size(); i++) {
            if ((point.y - polygon.get(i).y) * (polygon.get(j).x - polygon.get(i).x) == (point.x - polygon.get(i).x) * (polygon.get(j).y - polygon.get(i).y))
                return 1;
            j = i;
        }

        j = polygon.size() - 1;
        boolean resIns = false;
        for (int i = 0; i < polygon.size(); i++) {
            if ((((polygon.get(i).y < point.y) && (point.y < polygon.get(j).y)) || ((polygon.get(j).y <= point.y) && (point.y < polygon.get(i).y))) &&
                    (point.x > (polygon.get(j).x - polygon.get(i).x) * (point.y - polygon.get(i).y) / (polygon.get(j).y - polygon.get(i).y) + polygon.get(i).x))
                resIns = !resIns;
            j = i;
        }

        if (resIns) return 2;
        else return 3;
    }
}
