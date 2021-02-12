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
    public Date x;
    public Date y;

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
            List<String> TimeString = Files.readAllLines(path);
            List<InterTime> TimeFinal = new ArrayList<>();
            int[] Clients = new int[1440];
            ArrayList<Integer> MaxCountCients = new ArrayList<>();

            ArrayList<InterTime> FinalIntervalMaxClient = new ArrayList<>();
            //заполнение листа TimeFinal из листа строк
            for (String s : TimeString) {
                String[] subStr;
                String delimeter = " ";
                subStr = s.split(delimeter);
                try {
                    TimeFinal.add(new InterTime(format.parse(subStr[0]), format.parse(subStr[1])));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //формирование массива заполнености банка в каждую минуту времени
            for (int i = 0; i < Clients.length; i++) {
                for (InterTime p : TimeFinal) {

                    if (i >= toMinute(p.x) && i < toMinute(p.y))
                        Clients[i] += 1;
                }
            }
            //Заполнение листа минут, в течении которых было максимальное количество посетителей
            MaxCountCients = MaxInd(Clients);

            // выделение интервалов времени,  в течении которых было максимальное количество посетителей
            int FinalIntervalMaxClientIndex = MaxCountCients.get(0);
            int previous = FinalIntervalMaxClientIndex;
            for (int i = 1; i < MaxCountCients.size(); i++) {
                int current = MaxCountCients.get(i);
                if (current - previous > 1) {
                    try {
                        FinalIntervalMaxClient.add(new InterTime(
                                format.parse(toHours(FinalIntervalMaxClientIndex)),
                                format.parse(toHours(previous + 1))
                        ));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    FinalIntervalMaxClientIndex = current;
                }

                previous = current;

            }

            try {
                FinalIntervalMaxClient.add(new InterTime(
                        format.parse(toHours(FinalIntervalMaxClientIndex)),
                        format.parse(toHours(MaxCountCients.get(MaxCountCients.size() - 1) + 1))
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }

//вывод
            for (InterTime i : FinalIntervalMaxClient) {
                System.out.println(format.format(i.x) + " " + format.format(i.y));
            }


        }
    }

    public static int toMinute(Date HandM) {
        DateFormat formatH = new SimpleDateFormat("HH");
        DateFormat formatm = new SimpleDateFormat("mm");
        int m = Integer.parseInt(formatH.format(HandM)) * 60 + Integer.parseInt(formatm.format(HandM));
        return m;
    }

    public static String toHours(int m) {
        long
                hour = m / 60,
                min = m / 1 % 60;
        return String.format("%02d:%02d", hour, min);

    }

    public static ArrayList<Integer> MaxInd(int[] a) {
        int max = 0;
        ArrayList<Integer> MaxIndex = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == max)
                MaxIndex.add(i);
        }
        return MaxIndex;
    }


}
