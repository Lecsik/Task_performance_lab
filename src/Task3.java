import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Task3 {
    public static void main(String[] arg) throws IOException {
        Path path1 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\Cash1.txt");
        Path path2 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\Cash2.txt");
        Path path3 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\Cash3.txt");
        Path path4 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\Cash4.txt");
        Path path5 = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\Cash5.txt");
        if (Files.exists(path1) && Files.exists(path2) && Files.exists(path3) && Files.exists(path4) && Files.exists(path5)) {

            List<String> list1 = Files.readAllLines(path1);
            List<String> list2 = Files.readAllLines(path2);
            List<String> list3 = Files.readAllLines(path3);
            List<String> list4 = Files.readAllLines(path4);
            List<String> list5 = Files.readAllLines(path5);
            ArrayList<Float> list1final = new ArrayList<Float>();
            ArrayList<Float> list2final = new ArrayList<Float>();
            ArrayList<Float> list3final = new ArrayList<Float>();
            ArrayList<Float> list4final = new ArrayList<Float>();
            ArrayList<Float> list5final = new ArrayList<Float>();
            ArrayList<Float> SumInterv = new ArrayList<Float>();
            int maxInd = -1;
            float max = 0;
            for (int i = 0; i < list1.size(); i++) {
                list1final.add(Float.parseFloat(list1.get(i)));
                list2final.add(Float.parseFloat(list2.get(i)));
                list3final.add(Float.parseFloat(list3.get(i)));
                list4final.add(Float.parseFloat(list4.get(i)));
                list5final.add(Float.parseFloat(list5.get(i)));
            }
            for (int i = 0; i < list1.size(); i++) {
                SumInterv.add(i, (list1final.get(i) + list2final.get(i) + list3final.get(i) + list4final.get(i) + list5final.get(i)));
            }
            for (int i = 0; i < SumInterv.size(); i++) {
                if (SumInterv.get(i) > max) {
                    max = SumInterv.get(i);
                    maxInd = i;
                }
            }
            System.out.println(maxInd + 1);
        }

    }

}
