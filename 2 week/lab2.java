public class lab2 {
    
}


//  SRP
class Invoice {
    private int id;
    private List<Item> items;
    private double taxRate;

    public Invoice(int id, List<Item> items, double taxRate) {
        this.id = id;
        this.items = items;
        this.taxRate = taxRate;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTaxRate() {
        return taxRate;
    }
}

class InvoiceCalculator {
    public double calculateTotal(Invoice invoice) {
        double subTotal = 0;
        for (Item item : invoice.getItems()) {
            subTotal += item.getPrice();
        }
        return subTotal + (subTotal * invoice.getTaxRate());
    }
}

class InvoiceRepository {
    public void saveToDatabase(Invoice invoice) {
        // Логика для сохранения счета-фактуры в базу данных
        System.out.println("Invoice saved to database: " + invoice.getId());
    }
}

class Item {
    private double price;

    public Item(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

// OCP
enum CustomerType {
    REGULAR, SILVER, GOLD, PLATINUM
}

interface IDiscountCalculator {
    double calculateDiscount(double amount);
}

class RegularDiscountCalculator implements IDiscountCalculator {
    @Override
    public double calculateDiscount(double amount) {
        return amount;
    }
}

class SilverDiscountCalculator implements IDiscountCalculator {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.9;
    }
}

class GoldDiscountCalculator implements IDiscountCalculator {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.8;
    }
}

class PlatinumDiscountCalculator implements IDiscountCalculator {
    @Override
    public double calculateDiscount(double amount) {
        return amount * 0.7;
    }
}

class DiscountCalculator {
    private IDiscountCalculator discountCalculator;

    public DiscountCalculator(CustomerType customerType) {
        switch (customerType) {
            case REGULAR:
                discountCalculator = new RegularDiscountCalculator();
                break;
            case SILVER:
                discountCalculator = new SilverDiscountCalculator();
                break;
            case GOLD:
                discountCalculator = new GoldDiscountCalculator();
                break;
            case PLATINUM:
                discountCalculator = new PlatinumDiscountCalculator();
                break;
            default:
                throw new IllegalArgumentException("Unknown customer type");
        }
    }

    public double calculateDiscount(double amount) {
        return discountCalculator.calculateDiscount(amount);
    }
}
// ISP
interface IWorker {
    void work();
}

interface IEatable {
    void eat();
}

interface ISleepable {
    void sleep();
}

class HumanWorker implements IWorker, IEatable, ISleepable {
    @Override
    public void work() {
        System.out.println("Human is working");
    }

    @Override
    public void eat() {
        System.out.println("Human is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Human is sleeping");
    }
}

class RobotWorker implements IWorker {
    @Override
    public void work() {
        System.out.println("Robot is working");
    }
}

// DIP
interface INotificationService {
    void sendNotification(String message);
}

class EmailService implements INotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SmsService implements INotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class Notification {
    private INotificationService notificationService;

    public Notification(INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void send(String message) {
        notificationService.sendNotification(message);
    }
}