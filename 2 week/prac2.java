import java.util.ArrayList;
import java.util.List;
public class prac2 {
    
}



interface IPayment {
    void processPayment(double amount);
}


class CreditCardPayment implements IPayment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата кредитной картой: " + amount);
    }
}


class PayPalPayment implements IPayment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Оплата через PayPal: " + amount);
    }
}


interface IDelivery {
    void deliverOrder(Order order);
}

class CourierDelivery implements IDelivery {
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Доставка курьером: " + order.getId());
    }
}


class PostDelivery implements IDelivery {
    @Override
    public void deliverOrder(Order order) {
        System.out.println("Доставка почтой: " + order.getId());
    }
}


interface INotification {
    void sendNotification(String message);
}


class EmailNotification implements INotification {
    @Override
    public void sendNotification(String message) {
        System.out.println("Отправка уведомления по почте: " + message);
    }
}


class SmsNotification implements INotification {
    @Override
    public void sendNotification(String message) {
        System.out.println("Отправка уведомления по SMS: " + message);
    }
}


class DiscountCalculator {
    
    public double calculateDiscount(Order order) {
        
        return 0.0; 
    }
}

// Класс заказа
class Order {
    private int id;
    private List<Item> items;
    private IPayment paymentMethod;
    private IDelivery deliveryMethod;
    private INotification notificationMethod;
    private DiscountCalculator discountCalculator;

    public Order(int id, List<Item> items, IPayment paymentMethod, IDelivery deliveryMethod,
                 INotification notificationMethod, DiscountCalculator discountCalculator) {
        this.id = id;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.notificationMethod = notificationMethod;
        this.discountCalculator = discountCalculator;
    }

    public int getId() {
        return id;
    }

    public void addItem(Item item) {
        items.add(item);
    }

 
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Item item : items) {
            totalCost += item.getPrice();
        }
        
        totalCost -= discountCalculator.calculateDiscount(this);
        return totalCost;
    }

   
    public void placeOrder() {
        double totalCost = calculateTotalCost();
        paymentMethod.processPayment(totalCost);
        deliveryMethod.deliverOrder(this);
        notificationMethod.sendNotification("Ваш заказ оформлен! Номер заказа: " + id);
    }
}


class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

