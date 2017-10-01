import models.Worker;
import models.WorkersRepository;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            WorkersRepository workersRepository = new WorkersRepository();
            workersRepository.initializeRepository();

            Map<String, List<Worker>> workers1 = workersRepository.getWorkersPerCountry(
                    new String[]{"Romania","Moldova", "Germany"},
                    LocalDate.of(2016, 1, 1), LocalDate.of(2018, 2, 1));

            for (Map.Entry<String, List<Worker>> pair : workers1.entrySet())
            {
                System.out.printf("Country: %s\nWorkers that can visit it:\n", pair.getKey());
                for (Worker w : pair.getValue())
                {
                    System.out.printf("%s\n", w);
                }
                System.out.println();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
