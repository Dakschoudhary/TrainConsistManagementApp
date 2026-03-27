import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;


public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("===== Creating Passenger Bogies =====");

        List<Bogie> bogies = new ArrayList<>();

        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 50));

            bogies.add(new Bogie("First Class", -10));

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Continue execution safely
        System.out.println("\nValid Bogies in System:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }


        Map<String, List<Bogie>> grouped =
                bogies.stream()
                        .collect(Collectors.groupingBy(Bogie::getType));

        System.out.println("\nGrouped Bogies:");
        grouped.forEach((type, list) -> {
            System.out.println(type + " -> " + list.size());
        });


        int totalSeats = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seats: " + totalSeats);
    }
}


class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}


class Bogie {
    private String type;
    private int capacity;

    public Bogie(String type, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Bogie{type='" + type + "', capacity=" + capacity + "}";
    }
}