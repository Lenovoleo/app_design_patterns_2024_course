import java.util.*;

interface IPaymentStrategy {
    void Pay(double amount);
}

class CreditCardPaymentStrategy implements IPaymentStrategy {
    @Override
    public void Pay(double amount) {
        System.out.println("Оплата кредитной картой: " + amount);
    }
}

class PaypalPaymentStrategy implements IPaymentStrategy {
    @Override
    public void Pay(double amount) {
        System.out.println("Оплата через PayPal: " + amount);
    }
}

class CryptocurrencyPaymentStrategy implements IPaymentStrategy {
    @Override
    public void Pay(double amount) {
        System.out.println("Оплата криптовалютой: " + amount);
    }
}

class PaymentContext {
    private IPaymentStrategy paymentStrategy;

    public PaymentContext(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void makePayment(double amount) {
        paymentStrategy.Pay(amount);
    }
}

// public class modul07homework {
//     public static void main(String[] args) {
//         PaymentContext paymentContext = new PaymentContext(new CreditCardPaymentStrategy());
//         paymentContext.makePayment(100.0);

//         paymentContext.setPaymentStrategy(new PaypalPaymentStrategy());
//         paymentContext.makePayment(50.0);

//         paymentContext.setPaymentStrategy(new CryptocurrencyPaymentStrategy());
//         paymentContext.makePayment(25.0);
//     }
// }


interface IObserver {
    void update(double usdRate, double eurRate);
}

interface ISubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyObservers();
}

class CurrencyExchange implements ISubject {
    private double usdRate;
    private double eurRate;
    private List<IObserver> observers = new ArrayList<>();

    public CurrencyExchange(double usdRate, double eurRate) {
        this.usdRate = usdRate;
        this.eurRate = eurRate;
    }

    public void setRates(double usdRate, double eurRate) {
        this.usdRate = usdRate;
        this.eurRate = eurRate;
        notifyObservers();
    }

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(usdRate, eurRate);
        }
    }
}

class ConsoleObserver implements IObserver {
    @Override
    public void update(double usdRate, double eurRate) {
        System.out.println("Обновление курсов: USD=" + usdRate + ", EUR=" + eurRate);
    }
}

class EmailObserver implements IObserver {
    @Override
    public void update(double usdRate, double eurRate) {
        System.out.println("Отправка уведомления по email: USD=" + usdRate + ", EUR=" + eurRate);
    }
}

class SmsObserver implements IObserver {
    @Override
    public void update(double usdRate, double eurRate) {
        System.out.println("Отправка SMS: USD=" + usdRate + ", EUR=" + eurRate);
    }
}

public class modul07homework {
    public static void main(String[] args) {
        CurrencyExchange exchange = new CurrencyExchange(1.0, 0.9);

        ConsoleObserver consoleObserver = new ConsoleObserver();
        EmailObserver emailObserver = new EmailObserver();
        SmsObserver smsObserver = new SmsObserver();

        exchange.attach(consoleObserver);
        exchange.attach(emailObserver);
        exchange.attach(smsObserver);

        exchange.setRates(1.1, 0.8);

        exchange.detach(emailObserver);

        exchange.setRates(1.2, 0.7);
    }
}