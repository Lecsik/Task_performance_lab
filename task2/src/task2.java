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
        Path path1 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\poligon.txt");
        Path path2 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\readme2.txt");

        if (Files.exists(path1) && Files.exists(path2)) {

            List<String> poligonString = Files.readAllLines(path1);
            List<String> pointString = Files.readAllLines(path2);
            List<Point> poligonFinal = new ArrayList<>();
            List<Point> pointFinal = new ArrayList<>();

            for (String s : poligonString) {
                String[] subStr;
                String delimeter = " ";
                subStr = s.split(delimeter);
                poligonFinal.add(new Point(Float.parseFloat(subStr[0]), Float.parseFloat(subStr[1])));
            }
            for (String s : pointString) {
                String[] subStr;
                String delimeter = " ";
                subStr = s.split(delimeter);

                pointFinal.add(new Point(Float.parseFloat(subStr[0]), Float.parseFloat(subStr[1])));

            }

            for (Point point : pointFinal) {
                System.out.println(pointPlace(point, poligonFinal));
            }
        }
    }


    public static int pointPlace(Point point, List<Point> polygon) {
        int j;
        int result;
        boolean resIns = false;
        boolean resOnLine = false;
        boolean resOnPoint = false;

        for (Point value : polygon) {
            if (point.x == value.x && point.y == value.y) resOnPoint = !resOnPoint;

        }
        if (resOnPoint) result = 0;

        else {
            j = polygon.size() - 1;
            for (int i = 0; i < polygon.size(); i++) {
                if ((point.y - polygon.get(i).y) * (polygon.get(j).x - polygon.get(i).x) == (point.x - polygon.get(i).x) * (polygon.get(j).y - polygon.get(i).y))
                    resOnLine = !resOnLine;
                j = i;
            }
            if (resOnLine) result = 1;
            else {
                j = polygon.size() - 1;
                for (int i = 0; i < polygon.size(); i++) {

                    if ((((polygon.get(i).y < point.y) && (point.y < polygon.get(j).y)) || ((polygon.get(j).y <= point.y) && (point.y < polygon.get(i).y))) &&
                            (point.x > (polygon.get(j).x - polygon.get(i).x) * (point.y - polygon.get(i).y) / (polygon.get(j).y - polygon.get(i).y) + polygon.get(i).x))
                        resIns = !resIns;
                    j = i;
                }

                if (resIns) result = 2;
                else result = 3;
            }

        }
        return result;
    }
}


