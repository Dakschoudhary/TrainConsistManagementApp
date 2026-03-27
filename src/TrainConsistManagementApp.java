import java.util.*;


class Bogie {
    private String name;
    private int capacity;


    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }


    public void display() {
        System.out.println(name + " Bogie - Capacity: " + capacity);
    }
}

class TrainConsistManagementApp {
    public static void main(String[] args) {


        List<Bogie> bogieList = new ArrayList<>();


        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 56));
        bogieList.add(new Bogie("First Class", 24));

        bogieList.sort(Comparator.comparingInt(Bogie::getCapacity));


        System.out.println("Bogies sorted by capacity (Ascending):");
        for (Bogie b : bogieList) {
            b.display();
        }


        bogieList.sort(Comparator.comparingInt(Bogie::getCapacity).reversed());

        System.out.println("\nBogies sorted by capacity (Descending):");
        for (Bogie b : bogieList) {
            b.display();
        }
    }
}