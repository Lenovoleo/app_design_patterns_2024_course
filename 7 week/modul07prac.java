public class modul07prac {
    public static void main(String[] args) {
        
    }
}

interface ICostCalculationStrategy{
    float paymentStrategy(int passengers, bool hasDiscount, int classServise);
}

class AviaTravel implements ICostCalculationStrategy{

    @Override
    public float paymentStrategy(int passengers, bool hasDiscount, int classServise){
        reutrn 0;
    }
}

class TravelBookingContext{
    private ICostCalculationStrategy travelStrategy;

    public float executeStrategy(int passengers, bool hasDiscount, int classServise){
        return travelStrategy.paymentStrategy(passengers);
    }
}