package Model;

public class PremiumPassenger extends Passenger {
    public PremiumPassenger(String name, int passengerNumber) {
        super(name, passengerNumber, 0);
    }

    @Override
    public void activitySignUp(Activity activity) {
        activities.add(activity);
        activity.decreaseCapacity();
        System.out.println("Premium passenger " + name + " signed up for activity: " + activity.getName() + " (Free)");
    }
}