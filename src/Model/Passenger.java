package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Passenger {
    protected String name;
    protected int passengerNumber;
    protected double balance;
    protected List<Activity> activities;

    public Passenger(String name, int passengerNumber, double balance) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.balance = balance;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void signUpForActivity(Activity activity)
    {
        if (activity.getCapacity() > 0) {
            activitySignUp(activity);
        } else {
            System.out.println("Activity " + activity.getName() + " is already at full capacity. Cannot sign up more passengers.");
        }
    }
    public abstract void activitySignUp(Activity activity);

    public void printPassengerDetails() {
        System.out.println("Passenger Details:");
        System.out.println("Name: "+name);
        System.out.println("Number: " + passengerNumber);
        if (this instanceof StandardPassenger) {
            System.out.println("Balance: " + balance);
        }
        System.out.println();
        System.out.println("Activities:");
        for (Activity activity : activities) {
            System.out.println("  Activity: " + activity.getName());
            System.out.println("  Destination: " + activity.getDestination().getName());
            System.out.println("  Cost: " + activity.getCost());
        }
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", passengerNumber=" + passengerNumber +
                ", balance=" + balance +
                ", activities=" + activities +
                '}';
    }
}

