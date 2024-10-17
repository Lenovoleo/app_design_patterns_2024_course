import java.util.*;

interface ICostCalculationStrategy {
    double calculateCost(double distance, String transportType, int passengers, String serviceClass, boolean hasDiscount);
}

class PlaneCostStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String transportType, int passengers, String serviceClass, boolean hasDiscount) {
        
        double baseCost = distance * 10; 
        if (serviceClass.equals("business")) {
            baseCost *= 1.5; 
        }
        if (hasDiscount) {
            baseCost *= 0.8; 
        }
        return baseCost * passengers; 
    }
}

class TrainCostStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String transportType, int passengers, String serviceClass, boolean hasDiscount) {
        
        double baseCost = distance * 5; 
        if (serviceClass.equals("business")) {
            baseCost *= 1.2; 
        }
        if (hasDiscount) {
            baseCost *= 0.9; 
        }
        return baseCost * passengers; 
    }
}

class BusCostStrategy implements ICostCalculationStrategy {
    @Override
    public double calculateCost(double distance, String transportType, int passengers, String serviceClass, boolean hasDiscount) {
        
        double baseCost = distance * 2; 
        if (serviceClass.equals("business")) {
            baseCost *= 1.1; 
        }
        if (hasDiscount) {
            baseCost *= 0.95; 
        }
        return baseCost * passengers; 
    }
}

class TravelBookingContext {
    private ICostCalculationStrategy costCalculationStrategy;

    public TravelBookingContext(ICostCalculationStrategy costCalculationStrategy) {
        this.costCalculationStrategy = costCalculationStrategy;
    }

    public void setCostCalculationStrategy(ICostCalculationStrategy costCalculationStrategy) {
        this.costCalculationStrategy = costCalculationStrategy;
    }

    public double calculateCost(double distance, String transportType, int passengers, String serviceClass, boolean hasDiscount) {
        return costCalculationStrategy.calculateCost(distance, transportType, passengers, serviceClass, hasDiscount);
    }
}

// public class modul07prac {
//     public static void main(String[] args) {
//         TravelBookingContext context = new TravelBookingContext(new PlaneCostStrategy());

        
//         double cost = context.calculateCost(1000, "plane", 2, "business", false);
//         System.out.println("Стоимость перелета самолетом: " + cost);

        
//         context.setCostCalculationStrategy(new TrainCostStrategy());
//         cost = context.calculateCost(1000, "train", 2, "business", true);
//         System.out.println("Стоимость поездки на поезде: " + cost);
//     }
// }

interface IObserver {
    void update(String stockSymbol, double newPrice);
}

interface ISubject {
    void attach(IObserver observer, String stockSymbol);
    void detach(IObserver observer, String stockSymbol);
    void notifyObservers(String stockSymbol, double newPrice);
}

class StockExchange implements ISubject {
    private Map<String, List<IObserver>> observers = new HashMap<>();
    private Map<String, Double> stockPrices = new HashMap<>();

    public StockExchange() {
    }

    public void setStockPrice(String stockSymbol, double newPrice) {
        stockPrices.put(stockSymbol, newPrice);
        notifyObservers(stockSymbol, newPrice);
    }

    @Override
    public void attach(IObserver observer, String stockSymbol) {
        observers.computeIfAbsent(stockSymbol, k -> new ArrayList<>()).add(observer);
    }

    @Override
    public void detach(IObserver observer, String stockSymbol) {
        observers.getOrDefault(stockSymbol, new ArrayList<>()).remove(observer);
    }

    @Override
    public void notifyObservers(String stockSymbol, double newPrice) {
        observers.getOrDefault(stockSymbol, new ArrayList<>()).forEach(observer -> observer.update(stockSymbol, newPrice));
    }
}

class Trader implements IObserver {
    private String stockSymbol;

    public Trader(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    @Override
    public void update(String stockSymbol, double newPrice) {
        if (stockSymbol.equals(this.stockSymbol)) {
            System.out.println("Трейдер получил уведомление о цене акции " + stockSymbol + ": " + newPrice);
        }
    }
}

class TradingRobot implements IObserver {
    private String stockSymbol;
    private double triggerPrice;

    public TradingRobot(String stockSymbol, double triggerPrice) {
        this.stockSymbol = stockSymbol;
        this.triggerPrice = triggerPrice;
    }

    @Override
    public void update(String stockSymbol, double newPrice) {
        if (stockSymbol.equals(this.stockSymbol) && newPrice > triggerPrice) {
            System.out.println("Торговый робот выполнил автоматическую покупку акции " + stockSymbol + " по цене " + newPrice);
        }
    }
}

public class modul07prac {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();

        Trader trader = new Trader("AAPL");
        stockExchange.attach(trader, "AAPL");

        TradingRobot robot = new TradingRobot("GOOG", 1000.0);
        stockExchange.attach(robot, "GOOG");

        stockExchange.setStockPrice("AAPL", 150.0);

        stockExchange.setStockPrice("GOOG", 1200.0);

        stockExchange.detach(trader, "AAPL");

        stockExchange.setStockPrice("AAPL", 160.0);
    }
}