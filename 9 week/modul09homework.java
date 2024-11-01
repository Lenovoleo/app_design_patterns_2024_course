
interface Beverage {
    String getDescription();
    double cost();
}

class Espresso implements Beverage {
    public String getDescription() { return "Espresso"; }
    public double cost() { return 1.99; }
}

class Tea implements Beverage {
    public String getDescription() { return "Tea"; }
    public double cost() { return 1.49; }
}

class Latte implements Beverage {
    public String getDescription() { return "Latte"; }
    public double cost() { return 2.49; }
}

class Mocha implements Beverage {
    public String getDescription() { return "Mocha"; }
    public double cost() { return 2.99; }
}

abstract class BeverageDecorator implements Beverage {
    protected Beverage beverage;
    public BeverageDecorator(Beverage beverage) { this.beverage = beverage; }
    public String getDescription() { return beverage.getDescription(); }
    public double cost() { return beverage.cost(); }
}

class Milk extends BeverageDecorator {
    public Milk(Beverage beverage) { super(beverage); }
    public String getDescription() { return super.getDescription() + ", Milk"; }
    public double cost() { return super.cost() + 0.3; }
}

class Sugar extends BeverageDecorator {
    public Sugar(Beverage beverage) { super(beverage); }
    public String getDescription() { return super.getDescription() + ", Sugar"; }
    public double cost() { return super.cost() + 0.2; }
}

class WhippedCream extends BeverageDecorator {
    public WhippedCream(Beverage beverage) { super(beverage); }
    public String getDescription() { return super.getDescription() + ", Whipped Cream"; }
    public double cost() { return super.cost() + 0.5; }
}

class Vanilla extends BeverageDecorator {
    public Vanilla(Beverage beverage) { super(beverage); }
    public String getDescription() { return super.getDescription() + ", Vanilla"; }
    public double cost() { return super.cost() + 0.4; }
}

public class modul09homework {
    public static void main(String[] args) {
        Beverage order1 = new Espresso();
        order1 = new Milk(order1);
        order1 = new Sugar(order1);
        System.out.println(order1.getDescription() + ": $" + order1.cost());

        Beverage order2 = new Latte();
        order2 = new WhippedCream(order2);
        order2 = new Vanilla(order2);
        System.out.println(order2.getDescription() + ": $" + order2.cost());
    }
}


interface IPaymentProcessor {
    void processPayment(double amount);
}

class PayPalPaymentProcessor implements IPaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " via PayPal");
    }
}

class StripePaymentService {
    public void makeTransaction(double totalAmount) {
        System.out.println("Processing transaction of $" + totalAmount + " via Stripe");
    }
}

class StripePaymentAdapter implements IPaymentProcessor {
    private StripePaymentService stripeService;
    public StripePaymentAdapter(StripePaymentService stripeService) { this.stripeService = stripeService; }
    public void processPayment(double amount) { stripeService.makeTransaction(amount); }
}

class SquarePaymentService {
    public void initiatePayment(double price) {
        System.out.println("Initiating payment of $" + price + " via Square");
    }
}

class SquarePaymentAdapter implements IPaymentProcessor {
    private SquarePaymentService squareService;
    public SquarePaymentAdapter(SquarePaymentService squareService) { this.squareService = squareService; }
    public void processPayment(double amount) { squareService.initiatePayment(amount); }
}

public class modul09homework {
    public static void main(String[] args) {
        IPaymentProcessor paypalProcessor = new PayPalPaymentProcessor();
        IPaymentProcessor stripeProcessor = new StripePaymentAdapter(new StripePaymentService());
        IPaymentProcessor squareProcessor = new SquarePaymentAdapter(new SquarePaymentService());

        paypalProcessor.processPayment(50.0);
        stripeProcessor.processPayment(75.0);
        squareProcessor.processPayment(100.0);
    }
}
