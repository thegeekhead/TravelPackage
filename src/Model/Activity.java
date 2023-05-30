package Model;

public class Activity {
    private String name;
    private String description;
    private double cost;
    private int currentCapacity;

    private int totalCapacity;


    private Destination destination;

    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.currentCapacity = capacity;
        this.totalCapacity = capacity;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return currentCapacity;
    }

    public void decreaseCapacity() {
        currentCapacity--;
    }

    public Destination getDestination() {
        return destination;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", currentCapacity=" + currentCapacity +
                ", totalCapacity=" + totalCapacity +
                ", destination=" + destination +
                '}';
    }
}