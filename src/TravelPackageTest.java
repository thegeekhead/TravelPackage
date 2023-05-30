import Model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TravelPackageService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TravelPackageTest {
    private Activity activity1,activity2,activity3,activity4;
    private Passenger passenger1,passenger2,passenger3,passenger4,passenger5,passenger6;
    private TravelPackageService service;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        // Create travel package
        TravelPackage travelPackage = new TravelPackage("Package 1", 10);
        System.setOut(new PrintStream(outputStreamCaptor));


        // Create destinations
        Destination destination1 = new Destination("Destination 1");
        Destination destination2 = new Destination("Destination 2");

        // Create activities for each destination
        activity1 = new Activity("Activity 1", "Description 1", 50.0, 5, destination1);
        activity2 = new Activity("Activity 2", "Description 2", 100.0, 10, destination1);
        activity3 = new Activity("Activity 3", "Description 3", 75.0, 8, destination2);
        activity4 = new Activity("Activity 4", "Description 4", 120.0, 15, destination2);

        // Add activities to destinations
        destination1.addActivity(activity1);
        destination1.addActivity(activity2);
        destination2.addActivity(activity3);
        destination2.addActivity(activity4);

        // Add destinations to travel package
        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);

        // Create passengers
        passenger1 = new StandardPassenger("John", 1, 200.0);
        passenger2 = new GoldPassenger("Alice", 2, 500.0);
        passenger3 = new PremiumPassenger("Mike", 3);
        passenger4 = new PremiumPassenger("Posum", 4);
        passenger5 = new PremiumPassenger("Ratul", 5);
        passenger6 = new PremiumPassenger("Josh", 6);

        // Add passengers to travel package
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);
        passenger1.signUpForActivity(activity1);

        service = new TravelPackageService(travelPackage);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testPrintItinerary() {
        String expectedOutput = "Standard passenger John signed up for activity: Activity 1\r\n" +
                "Travel Package: Package 1\r\n" +
                "Itinerary:\r\n" +
                "Destination: Destination 1\r\n" +
                "Activities:\r\n" +
                "  Name: Activity 1\r\n" +
                "  Description: Description 1\r\n" +
                "  Cost: 50.0\r\n" +
                "  Capacity: 4\r\n" +
                "\r\n" +
                "  Name: Activity 2\r\n" +
                "  Description: Description 2\r\n" +
                "  Cost: 100.0\r\n" +
                "  Capacity: 10\r\n" +
                "\r\n" +
                "Destination: Destination 2\r\n" +
                "Activities:\r\n" +
                "  Name: Activity 3\r\n" +
                "  Description: Description 3\r\n" +
                "  Cost: 75.0\r\n" +
                "  Capacity: 8\r\n" +
                "\r\n" +
                "  Name: Activity 4\r\n" +
                "  Description: Description 4\r\n" +
                "  Cost: 120.0\r\n" +
                "  Capacity: 15\r\n" +
                "\r\n";

        service.printItinerary();
        Assertions.assertEquals(expectedOutput.trim(),outputStreamCaptor.toString().trim());
    }

    @Test
    public void testPrintPassengerList() {
        String expectedOutput = "Standard passenger John signed up for activity: Activity 1\r\n" +
                "Travel Package: Package 1\r\n" +
                "Passenger Capacity: 10\r\n" +
                "Number of Passengers Enrolled: 3\r\n" +
                "\r\n" +
                "Passengers:\r\n" +
                "  Name: John, Number: 1\r\n" +
                "  Name: Alice, Number: 2\r\n" +
                "  Name: Mike, Number: 3\r\n" +
                "\r\n";
        service.printPassengerList();
        Assertions.assertEquals(expectedOutput.trim(),outputStreamCaptor.toString().trim() );
    }

    @Test
    public void testPrintPassengerDetails() {
        String expectedOutput = "Standard passenger John signed up for activity: Activity 1\r\n" +
                "Passenger Details:\r\n" +
                "Name: John\r\n" +
                "Number: 1\r\n" +
                "Balance: 150.0\r\n" +
                "\r\n" +
                "Activities:\r\n" +
                "  Activity: Activity 1\r\n" +
                "  Destination: Destination 1\r\n" +
                "  Cost: 50.0\r\n" +
                "\r\n";
        passenger1.printPassengerDetails();
        Assertions.assertEquals(expectedOutput.trim(),outputStreamCaptor.toString().trim() );
    }

    @Test
    public void testPrintAvailableActivities() {
        String expectedOutput = "Standard passenger John signed up for activity: Activity 1\r\n" +
                "Available Activities:\r\n" +
                "Destination: Destination 1\r\n" +
                "Activity: Activity 1\r\n" +
                "Total Enrolled: 1\r\n" +
                "Available Spaces: 4\r\n" +
                "\r\n" +
                "Destination: Destination 1\r\n" +
                "Activity: Activity 2\r\n" +
                "Total Enrolled: 0\r\n" +
                "Available Spaces: 10\r\n\r\n" +
                "Destination: Destination 2\r\n" +
                "Activity: Activity 3\r\n" +
                "Total Enrolled: 0\r\n" +
                "Available Spaces: 8\r\n" +
                "\r\n"+
                "Destination: Destination 2\r\n"+
                "Activity: Activity 4\r\n"+
                "Total Enrolled: 0\r\n"+
                "Available Spaces: 15\r\n"+
                "\r\n";
        service.printAvailableActivities();
        Assertions.assertEquals(expectedOutput.trim(),outputStreamCaptor.toString().trim());
    }

    @Test
    public void testActivityDecreaseCapacity() {
        Assertions.assertEquals(4, activity1.getCapacity());
        activity1.decreaseCapacity();
        activity1.decreaseCapacity();
        Assertions.assertEquals(2, activity1.getCapacity());
    }
    @Test
    public void testStandardPassengerCost()
    {
        Assertions.assertEquals(150.0,passenger1.getBalance());
        passenger1.signUpForActivity(activity2);
        Assertions.assertEquals(50.0,passenger1.getBalance());
    }
    @Test
    public void testGoldPassenger()
    {
        //Passenger2 Inital balance should be displayed
        Assertions.assertEquals(500.0,passenger2.getBalance());
        passenger2.signUpForActivity(activity1);
        //after signup in one activity
        Assertions.assertEquals(455.0,passenger2.getBalance());
        passenger2.signUpForActivity(activity4);
        Assertions.assertEquals(347,passenger2.getBalance());
    }

    @Test
    public void testActivityCapacity()
    {
        //as passenger1 already enrolled in initalization state
        Assertions.assertEquals(4,activity1.getCapacity());
        passenger2.signUpForActivity(activity1);
        passenger3.signUpForActivity(activity1);
        passenger4.signUpForActivity(activity1);
        Assertions.assertEquals(1,activity1.getCapacity());
    }
    @Test
    public void testActivityFull()
    {
        String expected = "Standard passenger John signed up for activity: Activity 1\r\n" +
                "Gold passenger Alice signed up for activity: Activity 1 (Discounted Cost: 45.0)\r\n" +
                "Premium passenger Mike signed up for activity: Activity 1 (Free)\r\n" +
                "Premium passenger Posum signed up for activity: Activity 1 (Free)\r\n" +
                "Premium passenger Ratul signed up for activity: Activity 1 (Free)\r\n" +
                "Activity Activity 1 is already at full capacity. Cannot sign up more passengers.";

        passenger2.signUpForActivity(activity1);
        passenger3.signUpForActivity(activity1);
        passenger4.signUpForActivity(activity1);
        passenger5.signUpForActivity(activity1);
        passenger6.signUpForActivity(activity1);

        Assertions.assertEquals(expected.trim(),outputStreamCaptor.toString().trim());
    }
}