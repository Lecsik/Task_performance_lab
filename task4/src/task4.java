import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class task4 {

    public static void main(String[] arg) throws IOException {
        Path path = Path.of(arg[0]);
        DateFormat format = new SimpleDateFormat("HH:mm");
        //заполнение листа строк из файла
        List<String> inputTimeString = Files.readAllLines(path);

        //заполнение листа inputTimes из листа строк
        List<InterTime> inputTimes = new ArrayList<>();
        for (String s : inputTimeString) {
            String[] subStr = s.split(" ");
            try {
                inputTimes.add(new InterTime(format.parse(subStr[0]), format.parse(subStr[1])));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //формирование массива заполнености банка в каждую минуту времени
        int[] clients = new int[12 * 60];
        for (InterTime inputTime : inputTimes) {
            int a = toMinute(inputTime.start);
            int b = toMinute(inputTime.end);
            for (int i = a; i < b; i++) {
                clients[i] += 1;
            }
        }

        //Заполнение листа минут, в течении которых было максимальное количество посетителей
        ArrayList<Integer> maxCountCients = maxInd(clients);

        // выделение интервалов времени,  в течении которых было максимальное количество посетителей
        ArrayList<InterTime> finalIntervalMaxClient = new ArrayList<>();
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
            System.out.println(format.format(i.start) + " " + format.format(i.end));
        }
    }

    private static class InterTime {
        public final Date start;
        public final Date end;

        InterTime(Date start, Date end) {
            this.start = start;
            this.end = end;
        }
    }

    private static int toMinute(Date HandM) {
        DateFormat formatH = new SimpleDateFormat("HH");
        DateFormat formatm = new SimpleDateFormat("mm");
        return Integer.parseInt(formatH.format(HandM)) * 60 + Integer.parseInt(formatm.format(HandM)) - 8 * 60;
    }

    private static String toHours(int m) {
        m += 8 * 60;
        long
                hour = m / 60,
                min = m % 60;
        return String.format("%02d:%02d", hour, min);
    }

    private static ArrayList<Integer> maxInd(int[] a) {
        int max = 0;
        for (int value : a) {
            if (value > max) {
                max = value;
            }
        }

        ArrayList<Integer> maxIndex = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == max)
                maxIndex.add(i);
        }
        return maxIndex;
    }
}
