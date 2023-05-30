import Model.*;
import service.TravelPackageService;

public class Main {
    public static void main(String[] args) {
        // Create travel package
        TravelPackage package1 = new TravelPackage("Package 1", 10);

        // Create destinations
        Destination destination1 = new Destination("Destination 1");
        Destination destination2 = new Destination("Destination 2");

        // Create activities
        Activity activity1 = new Activity("Activity 1", "Description 1", 50.0, 10, destination1);
        Activity activity2 = new Activity("Activity 2", "Description 2", 100.0, 10, destination1);
        Activity activity3 = new Activity("Activity 3", "Description 3", 200.0, 10, destination2);

        // Add activities to destinations
        destination1.addActivity(activity1);
        destination1.addActivity(activity2);
        destination2.addActivity(activity3);

        // Add destinations to travel package
        package1.addDestination(destination1);
        package1.addDestination(destination2);

        // Create passengers
        Passenger passenger1 = new StandardPassenger("Passenger 1", 1, 500.0);
        Passenger passenger2 = new GoldPassenger("Passenger 2", 2, 1000.0);
        Passenger passenger3 = new PremiumPassenger("Passenger 3", 3);

        // Add passengers to travel package
        package1.addPassenger(passenger1);
        package1.addPassenger(passenger2);
        package1.addPassenger(passenger3);

        // Sign up passengers for activities
        passenger1.signUpForActivity(activity1);
        passenger1.signUpForActivity(activity2);
        passenger2.signUpForActivity(activity1);
        passenger2.signUpForActivity(activity3);
        passenger3.signUpForActivity(activity2);

        System.out.println("=========================================================================================");
        // Print itinerary
        TravelPackageService service = new TravelPackageService(package1);
        service.printItinerary();
        System.out.println("=========================================================================================");

        // Print passenger list
        service.printPassengerList();
        System.out.println("=========================================================================================");

        // Print passenger details
        passenger1.printPassengerDetails();
        passenger2.printPassengerDetails();
        passenger3.printPassengerDetails();
        System.out.println("=========================================================================================");
        service.printAvailableActivities();
    }
}
