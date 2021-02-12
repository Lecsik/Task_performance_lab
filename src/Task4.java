import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class InterTime {
    public final Date x;
    public final Date y;

    InterTime(Date x, Date y) {
        this.x = x;
        this.y = y;
    }
}

public class Task4 {
    public static void main(String[] arg) throws IOException {
        Path path = Path.of("c:\\Users\\Lecsik\\Documents\\filesforexample\\time.txt");
        DateFormat format = new SimpleDateFormat("HH:mm");
        if (Files.exists(path)) {
            //заполнение листа строк из файла
            List<String> inputTimeString = Files.readAllLines(path);
            List<InterTime> inputTimeFinal = new ArrayList<>();
            int[] clients = new int[1440];
            ArrayList<Integer> maxCountCients;

            ArrayList<InterTime> finalIntervalMaxClient = new ArrayList<>();
            //заполнение листа inputTimeFinal из листа строк
            for (String s : inputTimeString) {
                String[] subStr;
                String delimeter = " ";
                subStr = s.split(delimeter);
                try {
                    inputTimeFinal.add(new InterTime(format.parse(subStr[0]), format.parse(subStr[1])));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //формирование массива заполнености банка в каждую минуту времени
            for (int i = 0; i < clients.length; i++) {
                for (InterTime p : inputTimeFinal) {

                    if (i >= toMinute(p.x) && i < toMinute(p.y))
                        clients[i] += 1;
                }
            }
            //Заполнение листа минут, в течении которых было максимальное количество посетителей
            maxCountCients = maxInd(clients);

            // выделение интервалов времени,  в течении которых было максимальное количество посетителей
            int finalIntervalMaxClientFirst = maxCountCients.get(0);
            int previous = finalIntervalMaxClientFirst;
            for (int i = 1; i < maxCountCients.size(); i++) {
                int current = maxCountCients.get(i);
                if (current - previous > 1) {
                    try {
                        finalIntervalMaxClient.add(new InterTime(
                                format.parse(toHours(finalIntervalMaxClientFirst)),
                                format.parse(toHours(previous + 1))
                        ));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    finalIntervalMaxClientFirst = current;
                }

                previous = current;

            }

            try {
                finalIntervalMaxClient.add(new InterTime(
                        format.parse(toHours(finalIntervalMaxClientFirst)),
                        format.parse(toHours(maxCountCients.get(maxCountCients.size() - 1) + 1))
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }

//вывод
            for (InterTime i : finalIntervalMaxClient) {
                System.out.println(format.format(i.x) + " " + format.format(i.y));
            }


        }
    }

    public static int toMinute(Date HandM) {
        DateFormat formatH = new SimpleDateFormat("HH");
        DateFormat formatm = new SimpleDateFormat("mm");
        return Integer.parseInt(formatH.format(HandM)) * 60 + Integer.parseInt(formatm.format(HandM));
    }

    public static String toHours(int m) {
        long
                hour = m / 60,
                min = m % 60;
        return String.format("%02d:%02d", hour, min);

    }

    public static ArrayList<Integer> maxInd(int[] a) {
        int max = 0;
        ArrayList<Integer> maxIndex = new ArrayList<>();
        for (int value : a) {
            if (value > max) {
                max = value;
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == max)
                maxIndex.add(i);
        }
        return maxIndex;
    }


}
