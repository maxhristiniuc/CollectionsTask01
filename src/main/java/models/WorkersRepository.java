package models;
import utils.DateUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import java.util.*;

public class WorkersRepository {

    private static final int NUMBER_OF_WORKERS = 17;
    private static final int NUMBER_OF_COUNTRIES_PER_WORKER = 3;
    private static final LocalDate FROM_DATE = LocalDate.of(2016, 1, 1);
    private static final LocalDate TO_DATE = LocalDate.of(2017, 1, 1);

    private Map<LocalDate, Worker> workersMap = new TreeMap<>();

    public void addWorker(LocalDate hireDate, Worker worker){
        workersMap.put(hireDate, worker);
    }

    public Set<Worker> workersReadyForTrip(LocalDate startDate, LocalDate endDate) {

        List<Worker> result = new ArrayList<>();
        for (Map.Entry<LocalDate, Worker> currentWorker : workersMap.entrySet())
        {
            if(currentWorker.getValue().isReadyForTrip()
                    && currentWorker.getKey().compareTo(startDate) >= 0
                    && currentWorker.getKey().compareTo(endDate) <= 0)
            {
                result.add(currentWorker.getValue());
            }
        }

        return new HashSet<>(result);
    }

    public Map<String, List<Worker>> getWorkersPerCountry(String[] countries,
        LocalDate fromDate, LocalDate toDate) {
        Set<Worker> workers = workersReadyForTrip(fromDate, toDate);
        Map<String , List<Worker>> workersThatCanGo = new TreeMap<>();

        for(String c : countries){
            workersThatCanGo.put(c, new ArrayList<>());
            for(Worker w : workers)
            {
                if(w.canVisitCountry(c)){
                    List<Worker> workersPerCountry = workersThatCanGo.get(c);
                    workersPerCountry.add(w);

                }
            }
        }

        Map<String, List<Worker>> workersThatCanGoDescSort = new TreeMap<>(
                (Comparator<String>) (o1, o2) -> o2.compareTo(o1)
        );

        workersThatCanGoDescSort.putAll(workersThatCanGo);

        workersThatCanGoDescSort = Collections.unmodifiableMap(workersThatCanGoDescSort);
        return workersThatCanGoDescSort;
    }

    public void initializeRepository() throws IOException {
        List<String> countriesList = getData("src/main/resources/countries.txt");
        List<String> namesList = getData("src/main/resources/names.txt");
        Random rand = new Random();
        List<Worker> workersToReAdd = new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_WORKERS; i++)
        {
            String[] name = namesList.get(i).split("\\s+");
            String[] empCountries = new String[NUMBER_OF_COUNTRIES_PER_WORKER];
            for (int j = 0; j < NUMBER_OF_COUNTRIES_PER_WORKER; j++)
            {
                int index = rand.nextInt(countriesList.size());
                String country = countriesList.get(index);
                empCountries[j] = country;
            }
            Worker employee = new Worker(name[0], name[1], 20 + rand.nextInt(20),
                    rand.nextBoolean(), empCountries);

            LocalDate hireDate = DateUtils.getRandomDate(rand,
                    FROM_DATE, TO_DATE);
            this.addWorker(hireDate, employee);

            if (workersToReAdd.size() < 3)
                workersToReAdd.add(employee);
        }

        for (Worker w : workersToReAdd)
            addWorker(DateUtils.getRandomDate(rand,
                    FROM_DATE, TO_DATE), w);
    }


    private static List<String> getData(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }
}
