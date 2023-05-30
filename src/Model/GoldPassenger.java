package Model;

public class GoldPassenger extends Passenger {
    public GoldPassenger(String name, int passengerNumber, double balance) {
        super(name, passengerNumber, balance);
    }

    @Override
    public void activitySignUp(Activity activity) {
        double discountedCost = activity.getCost() * 0.9;
        if (balance >= discountedCost) {
            balance -= discountedCost;
            activities.add(activity);
            activity.decreaseCapacity();
            System.out.println("Gold passenger " + name + " signed up for activity: " + activity.getName() + " (Discounted Cost: " + discountedCost + ")");
        } else {
            System.out.println("Insufficient balance for gold passenger " + name + " to sign up for activity: " + activity.getName());
        }
    }
}
