import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class task1 {

    public static void main(String[] arg) throws IOException {
        Path path = Paths.get(arg[0]);

        List<String> strings = Files.readAllLines(path);
        ArrayList<Short> shorts = new ArrayList<>(strings.size());
        for (String s : strings) {
            shorts.add(Short.parseShort(s));
        }
        System.out.println(String.format("%.2f", percentile90(shorts)));
        System.out.println(String.format("%.2f", mediana(shorts)));
        System.out.println(String.format("%.2f", maximum(shorts)));
        System.out.println(String.format("%.2f", minimum(shorts)));
        System.out.println(String.format("%.2f", average(shorts)));
    }

    private static double percentile90(ArrayList<Short> list) {
        list.sort(Comparator.naturalOrder());
        int index = (int) Math.ceil(((double) 90 / (double) 100) * (double) list.size());
        return list.get(index - 1);
    }

    private static double mediana(ArrayList<Short> list) {
        list.sort(Comparator.reverseOrder());
        double med;
        if (list.size() % 2 == 0) {
            med = (double) (list.get((list.size() / 2) - 1) + list.get(((list.size() / 2)))) / (double) 2;
        } else med = list.get((list.size() + 1) / 2);
        return med;
    }

    private static double maximum(ArrayList<Short> list) {
        short max = list.get(0);
        for (short i : list) {
            if (i >= max) max = i;
        }
        return max;
    }

    private static double minimum(ArrayList<Short> list) {
        short min = list.get(0);
        for (short i : list) {
            if (i <= min) min = i;
        }
        return min;
    }

    private static double average(ArrayList<Short> list) {
        int sum = 0;
        for (short i : list) {
            sum += i;
        }
        return (double) sum / (double) list.size();
    }
}
