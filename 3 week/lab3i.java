public class lab3i {
    
}

// Произведите корректную (правильную) по вашему мнению реализацию с применением принципа Single-Responsibility Principle (SRP)
class Invoice{
    int Id;
    String Items[];
    double TaxRate;

}

class InvoiceCalculator{
    public double CalculateTotal(Invoice invoice){
        double subTotal = 0;
        for (var item : invoice.Items) {
            subTotal += item.Price;
        }
        return subTotal + (subTotal * invoice.TaxRate)
    }
}

class InvoiceRepository{
    public void SaveToDatabase(){

    }
}

// Произведите корректную (правильную) по вашему мнению реализацию с применением принципа Open-Closed Principle, OCP:

abstract class DiscountCalculator {

    public abstract double CalculateDiscount(double amount);
}

class RegularDiscountCalculator extends DiscountCalculator{
    @Override
    public double CalculateDiscount(double amount){
        return amount;
    }
}
class SilverDiscountCalclulator extends DiscountCalculator{
    @Override
    public double CalculateDiscount(double amount){
        return amount * 0.9;
    }
}

class GoldDiscountCalculator extends DiscountCalculator{
    @Override
    public double CalculateDiscount(double amount){
        return amount * 0.8;
    }
}


// Произведите корректную (правильную) по вашему мнению реализацию с применением принципа Interface Segregation Principle, ISP:


public interface IWork {
    public void Work();
}

public interface  IEat  {
    public void Eat();
    
}
public interface ISleep{
    public void Sleep();
}

class HumanWorker implements IWork, IEat, ISleep{
    public void Work(){}

    public void Eat(){}

    public void Sleep(){}
}

class RobotWorker implements IWork{
    public void Work(){}
}

// Произведите корректную (правильную) по вашему мнению реализацию с применением принципа Dependency-Inversion Principle, DIP:

interface SendService{
    public void SendMessage(String message);
}

class EmailService implements SendService{
    public void SendMessage(String message){}


}

class Notifications{
    private EmailService emailService;
    public void Send(String message){}
}