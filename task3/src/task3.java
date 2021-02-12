import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class task3 {

    public static void main(String[] arg) throws IOException {
        Path path1 = Path.of(arg[0]);
        Path path2 = Path.of(arg[1]);
        Path path3 = Path.of(arg[2]);
        Path path4 = Path.of(arg[3]);
        Path path5 = Path.of(arg[4]);

        List<String> strings1 = Files.readAllLines(path1);
        List<String> strings2 = Files.readAllLines(path2);
        List<String> strings3 = Files.readAllLines(path3);
        List<String> strings4 = Files.readAllLines(path4);
        List<String> strings5 = Files.readAllLines(path5);

        ArrayList<Float> sumInterv = new ArrayList<>();
        for (int i = 0; i < strings1.size(); i++) {
            sumInterv.add(Float.parseFloat(strings1.get(i)) +
                    Float.parseFloat(strings2.get(i)) +
                    Float.parseFloat(strings3.get(i)) +
                    Float.parseFloat(strings4.get(i)) +
                    Float.parseFloat(strings5.get(i)));
        }

        int maxInd = -1;
        float max = 0;
        for (int i = 0; i < sumInterv.size(); i++) {
            if (sumInterv.get(i) > max) {
                max = sumInterv.get(i);
                maxInd = i;
            }
        }
        System.out.println(maxInd + 1);
    }
}
