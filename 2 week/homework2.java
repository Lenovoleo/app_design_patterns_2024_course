import java.util.List;


public class homework2 {
    
}

// SRP

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

interface IEmployeeSalaryCalculator {
    double calculateSalary(Employee employee);
}

class Employee {
    private String name;
    private double baseSalary;
    private IEmployeeSalaryCalculator salaryCalculator;

    public Employee(String name, double baseSalary, IEmployeeSalaryCalculator salaryCalculator) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.salaryCalculator = salaryCalculator;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double calculateSalary() {
        return salaryCalculator.calculateSalary(this);
    }
}

class PermanentEmployeeSalaryCalculator implements IEmployeeSalaryCalculator {
    @Override
    public double calculateSalary(Employee employee) {
        return employee.getBaseSalary() * 1.2;
    }
}

class ContractEmployeeSalaryCalculator implements IEmployeeSalaryCalculator {
    @Override
    public double calculateSalary(Employee employee) {
        return employee.getBaseSalary() * 1.1;
    }
}

class InternEmployeeSalaryCalculator implements IEmployeeSalaryCalculator {
    @Override
    public double calculateSalary(Employee employee) {
        return employee.getBaseSalary() * 0.8;
    }
}


// ISP

interface IPrinter {
    void print(String content);
}

interface IScanner {
    void scan(String content);
}

interface IFax {
    void fax(String content);
}

class AllInOnePrinter implements IPrinter, IScanner, IFax {
    @Override
    public void print(String content) {
        System.out.println("Printing: " + content);
    }

    @Override
    public void scan(String content) {
        System.out.println("Scanning: " + content);
    }

    @Override
    public void fax(String content) {
        System.out.println("Faxing: " + content);
    }
}

class BasicPrinter implements IPrinter, IScanner {
    @Override
    public void print(String content) {
        System.out.println("Printing: " + content);
    }

    @Override
    public void scan(String content) {
        System.out.println("Scanning: " + content);
    }
}

// DIP

interface INotificationSender {
    void sendNotification(String message);
}

class EmailSender implements INotificationSender {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SmsSender implements INotificationSender {
    @Override
    public void sendNotification(String message) {
        System.out.println("SMS sent: " + message);
    }
}

class MessengerSender implements INotificationSender {
    @Override
    public void sendNotification(String message) {
        System.out.println("Messenger message sent: " + message);
    }
}

class NotificationService {
    private final INotificationSender notificationSender;

    public NotificationService(INotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    public void sendNotification(String message) {
        notificationSender.sendNotification(message);
    }
}


