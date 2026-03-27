import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;


public class TrainConsistManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        System.out.println("===== Train ID & Cargo Code Validation =====");

        System.out.print("Enter Train ID (format TRN-1234): ");
        String trainId = sc.nextLine();

        System.out.print("Enter Cargo Code (format PET-AB): ");
        String cargoCode = sc.nextLine();


        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        // Matchers
        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        boolean isTrainValid = trainMatcher.matches();
        boolean isCargoValid = cargoMatcher.matches();

        System.out.println("\nValidation Results:");
        System.out.println("Train ID Valid: " + isTrainValid);
        System.out.println("Cargo Code Valid: " + isCargoValid);

        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("First Class", 30));
        bogies.add(new Bogie("AC Chair", 50));

        Map<String, List<Bogie>> groupedBogies =
                bogies.stream()
                        .collect(Collectors.groupingBy(Bogie::getType));

        System.out.println("\n===== Grouped Bogies =====");

        groupedBogies.forEach((type, list) -> {
            System.out.println("Type: " + type);
            list.forEach(b -> System.out.println("  " + b));
        });


        int totalSeats =
                bogies.stream()
                        .map(Bogie::getCapacity)
                        .reduce(0, Integer::sum);

        System.out.println("\n===== Total Seating Capacity =====");
        System.out.println("Total Seats: " + totalSeats);

        sc.close();
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