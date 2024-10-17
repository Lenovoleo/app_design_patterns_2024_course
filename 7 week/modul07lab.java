import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

interface IShippingStrategy {
    double calculateShippingCost(double weight, double distance);
}

class StandardShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 0.5 + distance * 0.1;
    }
}

class ExpressShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return (weight * 0.75 + distance * 0.2) + 10; 
    }
}

class InternationalShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 1.0 + distance * 0.5 + 15; 
    }
}

class NightShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return (weight * 0.5 + distance * 0.1) + 20;
    }
}

class DeliveryContext {
    private IShippingStrategy shippingStrategy;

    public void setShippingStrategy(IShippingStrategy strategy) {
        this.shippingStrategy = strategy;
    }

    public double calculateCost(double weight, double distance) {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Shipping strategy not set.");
        }

        if (weight <= 0 || distance <= 0) {
            throw new IllegalArgumentException("Weight and distance must be positive.");
        }

        return shippingStrategy.calculateShippingCost(weight, distance);
    }
}

// class modul07lab {
//     public static void main(String[] args) {
//         DeliveryContext deliveryContext = new DeliveryContext();
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Choose shipping type: 1 - Standard, 2 - Express, 3 - International, 4 - Night.");
//         String choice = scanner.nextLine();

//         switch (choice) {
//             case "1":
//                 deliveryContext.setShippingStrategy(new StandardShippingStrategy());
//                 break;
//             case "2":
//                 deliveryContext.setShippingStrategy(new ExpressShippingStrategy());
//                 break;
//             case "3":
//                 deliveryContext.setShippingStrategy(new InternationalShippingStrategy());
//                 break;
//             case "4":
//                 deliveryContext.setShippingStrategy(new NightShippingStrategy());
//                 break;
//             default:
//                 System.out.println("Invalid choice.");
//                 scanner.close();
//                 return;
//         }

//         try {
//             System.out.println("Enter package weight (kg):");
//             double weight = Double.parseDouble(scanner.nextLine());

//             System.out.println("Enter delivery distance (km):");
//             double distance = Double.parseDouble(scanner.nextLine());

//             double cost = deliveryContext.calculateCost(weight, distance);
//             System.out.printf("Shipping cost: %.2f%n", cost);
//         } catch (NumberFormatException e) {
//             System.out.println("Invalid input format. Please enter numeric values.");
//         } catch (Exception e) {
//             System.out.println("An error occurred: " + e.getMessage());
//         } finally {
//             scanner.close();
//         }
//     }
// }





interface IObserver {
    void update(float temperature);
}

interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}

class WeatherStation implements ISubject {
    private List<IObserver> observers;
    private float temperature;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(float newTemperature) {
        System.out.println("Изменение температуры: " + newTemperature + "°C");
        temperature = newTemperature;
        notifyObservers();
    }
}

class WeatherDisplay implements IObserver {
    private String name;

    public WeatherDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " показывает новую температуру: " + temperature + "°C");
    }
}

public class modul07lab {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        WeatherDisplay mobileApp = new WeatherDisplay("Мобильное приложение");
        WeatherDisplay digitalBillboard = new WeatherDisplay("Электронное табло");

        weatherStation.registerObserver(mobileApp);
        weatherStation.registerObserver(digitalBillboard);

        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);

        weatherStation.removeObserver(digitalBillboard);
        weatherStation.setTemperature(28.0f);
    }
}
