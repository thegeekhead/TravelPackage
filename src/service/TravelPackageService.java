package service;

import Model.*;

import java.util.ArrayList;
import java.util.List;

public class TravelPackageService {
    private TravelPackage travelPackage;

    public TravelPackageService(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + travelPackage.getName());
        System.out.println("Itinerary:");
        for (Destination destination : travelPackage.getItinerary()) {
            System.out.println("Destination: " + destination.getName());
            System.out.println("Activities:");
            for (Activity activity : destination.getActivities()) {
                System.out.println("  Name: " + activity.getName());
                System.out.println("  Description: " + activity.getDescription());
                System.out.println("  Cost: " + activity.getCost());
                System.out.println("  Capacity: " + activity.getCapacity());
                System.out.println();
            }
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + travelPackage.getName());
        System.out.println("Passenger Capacity: " + travelPackage.getPassengerCapacity());
        System.out.println("Number of Passengers Enrolled: " + travelPackage.getPassengers().size());
        System.out.println();
        System.out.println("Passengers:");
        for (Passenger passenger : travelPackage.getPassengers()) {
            System.out.println("  Name: " + passenger.getName() + ", Number: " + passenger.getPassengerNumber());
        }
    }

    public void printAvailableActivities() {
        System.out.println("Available Activities:");
        for (Destination destination : travelPackage.getItinerary()) {
            for (Activity activity : destination.getActivities()) {
                if (activity.getCapacity() > 0) {
                    System.out.println("Destination: " + destination.getName());
                    System.out.println("Activity: " + activity.getName());
                    System.out.println("Total Enrolled: " + (activity.getTotalCapacity() - activity.getCapacity()));
                    System.out.println("Available Spaces: " + activity.getCapacity());

                    System.out.println();
                }
            }
        }
    }
}