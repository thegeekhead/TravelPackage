package Model;

public class StandardPassenger extends Passenger {
    public StandardPassenger(String name, int passengerNumber, double balance) {
        super(name, passengerNumber, balance);
    }

    @Override
    public void activitySignUp(Activity activity) {
        if (balance >= activity.getCost()) {
            balance -= activity.getCost();
            activities.add(activity);
            activity.decreaseCapacity();
            System.out.println("Standard passenger " + name + " signed up for activity: " + activity.getName());
        } else {
            System.out.println("Insufficient balance for standard passenger " + name + " to sign up for activity: " + activity.getName());
        }
    }
}