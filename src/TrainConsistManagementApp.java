import java.util.*;
import java.util.stream.Collectors;


public class TrainConsistManagementApp {

    public static void main(String[] args) {


        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("First Class", 30));
        bogies.add(new Bogie("AC Chair", 50));

        Map<String, List<Bogie>> groupedBogies =
                bogies.stream()
                        .collect(Collectors.groupingBy(Bogie::getType));

        System.out.println("===== Grouped Bogies by Type =====\n");

        groupedBogies.forEach((type, list) -> {
            System.out.println("Type: " + type);
            list.forEach(b -> System.out.println("  " + b));
            System.out.println();
        });



        int totalSeats =
                bogies.stream()
                        .map(Bogie::getCapacity)
                        .reduce(0, Integer::sum);

        System.out.println("===== Total Seating Capacity =====");
        System.out.println("Total Seats in Train: " + totalSeats);
    }
}


class Bogie {
    private String type;
    private int capacity;

    public Bogie(String type, int capacity) {
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