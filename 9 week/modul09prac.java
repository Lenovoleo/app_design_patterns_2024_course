import java.util.*;

interface IReport {
    String generate();
}

class SalesReport implements IReport {
    public String generate() {
        return "Sales Report Data";
    }
}

class UserReport implements IReport {
    public String generate() {
        return "User Report Data";
    }
}

abstract class ReportDecorator implements IReport {
    protected IReport report;

    public ReportDecorator(IReport report) {
        this.report = report;
    }

    public String generate() {
        return report.generate();
    }
}

class DateFilterDecorator extends ReportDecorator {
    private Date startDate;
    private Date endDate;

    public DateFilterDecorator(IReport report, Date startDate, Date endDate) {
        super(report);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String generate() {
        return super.generate() + " with date filter from " + startDate + " to " + endDate;
    }
}

class SortingDecorator extends ReportDecorator {
    private String criteria;

    public SortingDecorator(IReport report, String criteria) {
        super(report);
        this.criteria = criteria;
    }

    public String generate() {
        return super.generate() + " sorted by " + criteria;
    }
}

class CsvExportDecorator extends ReportDecorator {
    public CsvExportDecorator(IReport report) {
        super(report);
    }

    public String generate() {
        return super.generate() + " in CSV format";
    }
}

class PdfExportDecorator extends ReportDecorator {
    public PdfExportDecorator(IReport report) {
        super(report);
    }

    public String generate() {
        return super.generate() + " in PDF format";
    }
}

interface IInternalDeliveryService {
    void deliverOrder(String orderId);
    String getDeliveryStatus(String orderId);
}

class InternalDeliveryService implements IInternalDeliveryService {
    public void deliverOrder(String orderId) {
        System.out.println("Delivering order " + orderId + " via internal service");
    }

    public String getDeliveryStatus(String orderId) {
        return "Status of order " + orderId + " via internal service";
    }
}

class ExternalLogisticsServiceA {
    public void shipItem(int itemId) {
        System.out.println("Shipping item " + itemId + " via ExternalLogisticsServiceA");
    }

    public String trackShipment(int shipmentId) {
        return "Status of shipment " + shipmentId + " via ExternalLogisticsServiceA";
    }
}

class LogisticsAdapterA implements IInternalDeliveryService {
    private ExternalLogisticsServiceA serviceA;

    public LogisticsAdapterA(ExternalLogisticsServiceA serviceA) {
        this.serviceA = serviceA;
    }

    public void deliverOrder(String orderId) {
        serviceA.shipItem(Integer.parseInt(orderId));
    }

    public String getDeliveryStatus(String orderId) {
        return serviceA.trackShipment(Integer.parseInt(orderId));
    }
}

class ExternalLogisticsServiceB {
    public void sendPackage(String packageInfo) {
        System.out.println("Sending package " + packageInfo + " via ExternalLogisticsServiceB");
    }

    public String checkPackageStatus(String trackingCode) {
        return "Status of package " + trackingCode + " via ExternalLogisticsServiceB";
    }
}

class LogisticsAdapterB implements IInternalDeliveryService {
    private ExternalLogisticsServiceB serviceB;

    public LogisticsAdapterB(ExternalLogisticsServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public void deliverOrder(String orderId) {
        serviceB.sendPackage(orderId);
    }

    public String getDeliveryStatus(String orderId) {
        return serviceB.checkPackageStatus(orderId);
    }
}

class DeliveryServiceFactory {
    public IInternalDeliveryService getDeliveryService(String type) {
        if ("internal".equals(type)) {
            return new InternalDeliveryService();
        } else if ("A".equals(type)) {
            return new LogisticsAdapterA(new ExternalLogisticsServiceA());
        } else if ("B".equals(type)) {
            return new LogisticsAdapterB(new ExternalLogisticsServiceB());
        }
        throw new IllegalArgumentException("Invalid delivery service type");
    }
}

public class modul09prac {
    public static void main(String[] args) {
        IReport report = new SalesReport();
        report = new DateFilterDecorator(report, new Date(System.currentTimeMillis() - 86400000L * 30), new Date());
        report = new SortingDecorator(report, "date");
        report = new CsvExportDecorator(report);
        System.out.println(report.generate());

        DeliveryServiceFactory factory = new DeliveryServiceFactory();
        IInternalDeliveryService deliveryService = factory.getDeliveryService("A");
        deliveryService.deliverOrder("123");
        System.out.println(deliveryService.getDeliveryStatus("123"));
    }
}
