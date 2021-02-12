import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Task1 {

    public static void main(String[] arg) throws IOException {
        Path path = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\readme.txt");

        if (Files.exists(path)) {
            List<String> list2 = Files.readAllLines(path);
            ArrayList<Short> listfinal = new ArrayList<>();
            for (String s : list2) {
                listfinal.add(Short.parseShort(s));
            }
            String formattedDouble1 = String.format("%.2f", percentile90(listfinal));
            String formattedDouble2 = String.format("%.2f", mediana(listfinal));
            String formattedDouble3 = String.format("%.2f", maximum(listfinal));
            String formattedDouble4 = String.format("%.2f", minimum(listfinal));
            String formattedDouble5 = String.format("%.2f", average(listfinal));
            System.out.println(formattedDouble1);
            System.out.println(formattedDouble2);
            System.out.println(formattedDouble3);
            System.out.println(formattedDouble4);
            System.out.println(formattedDouble5);
        }
    }

    public static double percentile90(ArrayList<Short> list) {
        list.sort(Comparator.naturalOrder());
        int index = (int) Math.ceil(((double) 90 / (double) 100) * (double) list.size());
        return list.get(index - 1);
    }

    public static double mediana(ArrayList<Short> list) {
        list.sort(Comparator.reverseOrder());
        double med;
        if (list.size() % 2 == 0) {
            med = (double) (list.get((list.size() / 2) - 1) + list.get(((list.size() / 2)))) / (double) 2;
        } else med = list.get((list.size() + 1) / 2);
        return med;
    }

    public static double maximum(ArrayList<Short> list) {
        short max = list.get(0);
        for (short i : list) {
            if (i >= max) max = i;
        }
        return max;
    }

    public static double minimum(ArrayList<Short> list) {
        short min = list.get(0);
        for (short i : list) {
            if (i <= min) min = i;
        }
        return min;
    }

    public static double average(ArrayList<Short> list) {
        int sum = 0;
        for (short i : list) {
            sum += i;
        }
        return (double) sum / (double) list.size();
    }
}
