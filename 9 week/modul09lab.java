import java.util.*;


interface IBeverage {
    double GetCost();
    String GetDescription();
}

class Coffee implements IBeverage {
    public double GetCost() {
        return 50.0;
    }

    public String GetDescription() {
        return "Coffee";
    }
}

abstract class BeverageDecorator implements IBeverage {
    protected IBeverage beverage;

    BeverageDecorator(IBeverage beverage) {
        this.beverage = beverage;
    }

    public double GetCost() {
        return beverage.GetCost();
    }

    public String GetDescription() {
        return beverage.GetDescription();
    }
}

class MilkDecorator extends BeverageDecorator {
    MilkDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double GetCost() {
        return super.GetCost() + 10.0;
    }

    @Override
    public String GetDescription() {
        return super.GetDescription() + ", Milk";
    }
}

class SugarDecorator extends BeverageDecorator {
    SugarDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double GetCost() {
        return super.GetCost() + 5.0;
    }

    @Override
    public String GetDescription() {
        return super.GetDescription() + ", Sugar";
    }
}

class ChocolateDecorator extends BeverageDecorator {
    ChocolateDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double GetCost() {
        return super.GetCost() + 15.0;
    }

    @Override
    public String GetDescription() {
        return super.GetDescription() + ", Chocolate";
    }
}

class VanillaDecorator extends BeverageDecorator {
    VanillaDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double GetCost() {
        return super.GetCost() + 7.0;
    }

    @Override
    public String GetDescription() {
        return super.GetDescription() + ", Vanilla";
    }
}

class CinnamonDecorator extends BeverageDecorator {
    CinnamonDecorator(IBeverage beverage) {
        super(beverage);
    }

    @Override
    public double GetCost() {
        return super.GetCost() + 6.0;
    }

    @Override
    public String GetDescription() {
        return super.GetDescription() + ", Cinnamon";
    }
}

public class modul09lab {
    public static void main(String[] args) {
        IBeverage beverage = new Coffee();
        System.out.println(beverage.GetDescription() + " : " + beverage.GetCost() );

        beverage = new MilkDecorator(beverage);
        System.out.println(beverage.GetDescription() + " : " + beverage.GetCost() );

        beverage = new SugarDecorator(beverage);
        System.out.println(beverage.GetDescription() + " : " + beverage.GetCost() );

        beverage = new ChocolateDecorator(beverage);
        System.out.println(beverage.GetDescription() + " : " + beverage.GetCost() );

        beverage = new VanillaDecorator(beverage);
        System.out.println(beverage.GetDescription() + " : " + beverage.GetCost() );

        beverage = new CinnamonDecorator(beverage);
        System.out.println(beverage.GetDescription() + " : " + beverage.GetCost() + " ");

        IBeverage simpleBeverage = new Coffee();
        simpleBeverage = new VanillaDecorator(simpleBeverage);
        simpleBeverage = new CinnamonDecorator(simpleBeverage);
        System.out.println(simpleBeverage.GetDescription() + " : " + simpleBeverage.GetCost() );
    }
}


interface IPaymentProcessor {
    void ProcessPayment(double amount);
    void RefundPayment(double amount);
}

class InternalPaymentProcessor implements IPaymentProcessor {
    public void ProcessPayment(double amount) {
        System.out.println("Processing payment of " + amount + " via internal system.");
    }

    public void RefundPayment(double amount) {
        System.out.println("Refunding payment of " + amount + " via internal system.");
    }
}

class ExternalPaymentSystemA {
    public void MakePayment(double amount) {
        System.out.println("Making payment of " + amount + " via External Payment System A.");
    }

    public void MakeRefund(double amount) {
        System.out.println("Making refund of " + amount + " via External Payment System A.");
    }
}

class ExternalPaymentSystemB {
    public void SendPayment(double amount) {
        System.out.println("Sending payment of " + amount + " via External Payment System B.");
    }

    public void ProcessRefund(double amount) {
        System.out.println("Processing refund of " + amount + " via External Payment System B.");
    }
}

class PaymentAdapterA implements IPaymentProcessor {
    private ExternalPaymentSystemA externalSystemA;

    public PaymentAdapterA(ExternalPaymentSystemA externalSystemA) {
        this.externalSystemA = externalSystemA;
    }

    @Override
    public void ProcessPayment(double amount) {
        externalSystemA.MakePayment(amount);
    }

    @Override
    public void RefundPayment(double amount) {
        externalSystemA.MakeRefund(amount);
    }
}

class PaymentAdapterB implements IPaymentProcessor {
    private ExternalPaymentSystemB externalSystemB;

    public PaymentAdapterB(ExternalPaymentSystemB externalSystemB) {
        this.externalSystemB = externalSystemB;
    }

    @Override
    public void ProcessPayment(double amount) {
        externalSystemB.SendPayment(amount);
    }

    @Override
    public void RefundPayment(double amount) {
        externalSystemB.ProcessRefund(amount);
    }
}

class PaymentProcessorSelector {
    public static IPaymentProcessor GetPaymentProcessor(String region, String currency) {
        if (currency.equals("USD") && region.equals("US")) {
            return new InternalPaymentProcessor();
        } else if (currency.equals("EUR")) {
            return new PaymentAdapterA(new ExternalPaymentSystemA());
        } else if (currency.equals("GBP")) {
            return new PaymentAdapterB(new ExternalPaymentSystemB());
        }
        return new InternalPaymentProcessor();
    }
}

// public class modul09lab {
//     public static void main(String[] args) {
//         IPaymentProcessor internalProcessor = PaymentProcessorSelector.GetPaymentProcessor("US", "USD");
//         internalProcessor.ProcessPayment(100.0);
//         internalProcessor.RefundPayment(50.0);

//         IPaymentProcessor adapterA = PaymentProcessorSelector.GetPaymentProcessor("EU", "EUR");
//         adapterA.ProcessPayment(200.0);
//         adapterA.RefundPayment(100.0);

//         IPaymentProcessor adapterB = PaymentProcessorSelector.GetPaymentProcessor("UK", "GBP");
//         adapterB.ProcessPayment(300.0);
//         adapterB.RefundPayment(150.0);
//     }
// }
