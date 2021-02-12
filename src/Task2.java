import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


class Point {
    public float x;
    public float y;

    Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

public class Task2 {
    public static void main(String[] arg) throws IOException {
        Path path1 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\poligon.txt");
        Path path2 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\readme2.txt");
        //if(!Files.exists(path2)) Files.createFile(path2);
        if (Files.exists(path1) && Files.exists(path2)) {

            List<String> PoligonString = Files.readAllLines(path1);
            List<String> PointString = Files.readAllLines(path2);
            List<Point> PoligonFinal = new ArrayList<>();
            List<Point> PointFinal = new ArrayList<>();

            for (String s : PoligonString) {
                String[] subStr;
                String delimeter = " ";
                subStr = s.split(delimeter);
                PoligonFinal.add(new Point(Float.parseFloat(subStr[0]), Float.parseFloat(subStr[1])));
            }
            for (String s : PointString) {
                String[] subStr;
                String delimeter = " ";
                subStr = s.split(delimeter);

                PointFinal.add(new Point(Float.parseFloat(subStr[0]), Float.parseFloat(subStr[1])));

            }

            for (int i = 0; i < PointFinal.size(); i++) {
                System.out.println(PointPlace(PointFinal.get(i), PoligonFinal));
            }
        }
    }


    public static int PointPlace(Point point, List<Point> polygon) {
        int j = polygon.size() - 1;
        int result = 5;
        boolean resIns = false;
        boolean resOnLine = false;
        boolean resOnPoint = false;

        for (int i = 0; i < polygon.size(); i++) {
            if (point.x == polygon.get(i).x && point.y == polygon.get(i).y) resOnPoint = !resOnPoint;

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


