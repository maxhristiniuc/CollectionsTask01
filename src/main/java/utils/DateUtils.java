package utils;


import java.time.LocalDate;
import java.util.Random;

public class DateUtils {
    public static LocalDate getRandomDate(Random rand, LocalDate fromDate, LocalDate toData)
    {
        int minDay = (int)fromDate.toEpochDay();
        int maxDay = (int)toData.toEpochDay();
        long randomDay = minDay + rand.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
