import java.util.ArrayList;
import java.util.List;

public class modeul06homework {
    public static void main(String[] args) {
        ConfigurationManager configManager1 = ConfigurationManager.getInstance("config.txt");
        ConfigurationManager configManager2 = ConfigurationManager.getInstance("config.txt");
        System.out.println(configManager1 == configManager2); 

        ReportDirector director = new ReportDirector();
        IReportBuilder textBuilder = new TextReportBuilder();
        director.constructReport(textBuilder);
        Report textReport = new Report(textBuilder.getReport());
        textReport.display();

        IReportBuilder htmlBuilder = new HtmlReportBuilder();
        director.constructReport(htmlBuilder);
        Report htmlReport = new Report(htmlBuilder.getReport());
        htmlReport.display();

        List<Product> products = new ArrayList<>();
        products.add(new Product("Товар 1", 100, 2));
        products.add(new Product("Товар 2", 50, 1));
        Order order = new Order(products, 10, 5, "Visa");

        try {
            Order clonedOrder = (Order) order.clone();
            clonedOrder.setProducts(new ArrayList<>());
            clonedOrder.getProducts().add(new Product("Товар 3", 200, 3)); 
            System.out.println("Оригинальный заказ:");
            System.out.println(order.getProducts());
            System.out.println("Клонированный заказ:");
            System.out.println(clonedOrder.getProducts());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
// Одиночка
class ConfigurationManager {
    private static ConfigurationManager instance;
    private String configFilePath;
    private List<String> settings;

    private ConfigurationManager(String configFilePath) {
        this.configFilePath = configFilePath;
        this.settings = new ArrayList<>();
        loadSettings();
    }

    public static ConfigurationManager getInstance(String configFilePath) {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager(configFilePath);
                }
            }
        }
        return instance;
    }

    private void loadSettings() {
        
    }

    public void addSetting(String setting) {
        settings.add(setting);
    }

    public List<String> getSettings() {
        return settings;
    }
}


// Строитель
interface IReportBuilder {
    void setHeader(String header);
    void setContent(String content);
    void setFooter(String footer);
    String getReport();
}

class TextReportBuilder implements IReportBuilder {
    private StringBuilder report;

    public TextReportBuilder() {
        report = new StringBuilder();
    }

    @Override
    public void setHeader(String header) {
        report.append("Header: ").append(header).append("\n");
    }

    @Override
    public void setContent(String content) {
        report.append("Content: ").append(content).append("\n");
    }

    @Override
    public void setFooter(String footer) {
        report.append("Footer: ").append(footer).append("\n");
    }

    @Override
    public String getReport() {
        return report.toString();
    }
}

class HtmlReportBuilder implements IReportBuilder {
    private StringBuilder report;

    public HtmlReportBuilder() {
        report = new StringBuilder();
        report.append("<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n");
    }

    @Override
    public void setHeader(String header) {
        report.append("<h1>").append(header).append("</h1>\n");
    }

    @Override
    public void setContent(String content) {
        report.append("<p>").append(content).append("</p>\n");
    }

    @Override
    public void setFooter(String footer) {
        report.append("<p>").append(footer).append("</p>\n");
    }

    @Override
    public String getReport() {
        report.append("</body>\n</html>");
        return report.toString();
    }
}

class ReportDirector {
    public void constructReport(IReportBuilder builder) {
        builder.setHeader("Отчет о продажах");
        builder.setContent("Данные о продажах за месяц");
        builder.setFooter("Подвал отчета");
    }
}

class Report {
    private String content;

    public Report(String content) {
        this.content = content;
    }

    public void display() {
        System.out.println(content);
    }
}

// Прототип
class Order implements Cloneable {
    private List<Product> products;
    private double shippingCost;
    private double discount;
    private String paymentMethod;

    public Order(List<Product> products, double shippingCost, double discount, String paymentMethod) {
        this.products = products;
        this.shippingCost = shippingCost;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Order clonedOrder = (Order) super.clone();
        clonedOrder.products = new ArrayList<>();
        for (Product product : products) {
            clonedOrder.products.add((Product) product.clone());
        }
        return clonedOrder;
    }

    // Getters and setters
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

class Product implements Cloneable {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}