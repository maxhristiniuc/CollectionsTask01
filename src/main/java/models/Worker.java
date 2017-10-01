package models;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Worker {

    private String firstName;
    private String lastName;
    private int age;
    private boolean readyForTrip;
    private Set<String> listOfCountries = new TreeSet<>();


    public Worker(String firstName, String lastName, int age,
        boolean readyForTrip, String[] listOfCountries)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.readyForTrip = readyForTrip;
        this.listOfCountries = new TreeSet<>(Arrays.asList(listOfCountries));
    }

    public boolean isReadyForTrip() {
        return readyForTrip;
    }

    public boolean canVisitCountry(String country) {
        for(String c : listOfCountries){
            if(c.equals(country))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return firstName + ' '
                + lastName +
                ", age: " + age;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (readyForTrip ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (firstName != null ? !firstName.equals(worker.firstName) : worker.firstName != null) return false;
        return lastName != null ? lastName.equals(worker.lastName) : worker.lastName == null;

    }


}
