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

        boolean isTrainValid = trainPattern.matcher(trainId).matches();
        boolean isCargoValid = cargoPattern.matcher(cargoCode).matches();

        System.out.println("\nTrain ID Valid: " + isTrainValid);
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


        System.out.println("\n===== Goods Bogie Safety Check =====");

        List<GoodsBogie> goodsBogies = new ArrayList<>();

        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Rectangular", "Coal"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));


        boolean isSafe =
                goodsBogies.stream()
                        .allMatch(b ->
                                !b.getType().equalsIgnoreCase("Cylindrical")
                                        || b.getCargo().equalsIgnoreCase("Petroleum")
                        );

        System.out.println("Train Safety Status: " + (isSafe ? "SAFE" : "UNSAFE"));

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


class GoodsBogie {
    private String type;
    private String cargo;

    public GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    public String getType() {
        return type;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return "GoodsBogie{type='" + type + "', cargo='" + cargo + "'}";
    }
}