import java.util.*;


class RoomBookingSystem{

    public void bookRoom(String roomType) {
        System.out.println("Room of type " + roomType + " booked.");
    }
    public void availableRoom(String roomType){
        System.out.println("Room of type " + roomType + " available");
    }
    public void cancelRoom(String roomType) {
        System.out.println("Room of type " + roomType + " canceled.");
    }
}

class RestaurantSystem{
    public void reserveTable(int numOfPeople){
        System.out.println("Table reserved for " + numOfPeople + " people.");
    }
    public void orderFood(String foodItem) {
        System.out.println("Ordered food item: " + foodItem);
    }
}

class EventManagementSystem {
    public void reserveEventSpace(String eventType) {
        System.out.println(eventType + " event space reserved.");
    }

    public void orderEquipment(String equipment) {
        System.out.println("Ordered equipment: " + equipment);
    }
}

class CleaningService {
    public void scheduleCleaning(String roomType) {
        System.out.println("Scheduled cleaning for " + roomType);
    }

    public void performCleaning(String roomType) {
        System.out.println("Performed cleaning for " + roomType);
    }
}

class HotelFacade{
    private RoomBookingSystem roomBooking;
    private RestaurantSystem restaurant;
    private EventManagementSystem eventManagement;
    private CleaningService cleaning;

    HotelFacade(){
        roomBooking = new RoomBookingSystem();
        restaurant = new RestaurantSystem();
        eventManagement = new EventManagementSystem();
        cleaning = new CleaningService();
    }


    public void bookRoomWithDiningAndCleaning(String roomType, String foodItem){
        roomBooking.bookRoom(roomType);
        restaurant.orderFood(foodItem);
        cleaning.scheduleCleaning(roomType);
        System.out.println("Room booking with dining and cleaning completed.");
    }

    public void organizeEventWithRoomsAndEquipment(String eventType, String roomType, String equipment) {
        eventManagement.reserveEventSpace(eventType);
        roomBooking.bookRoom(roomType);
        eventManagement.orderEquipment(equipment);
        System.out.println("Event organized with rooms and equipment.\n");
    }

    public void reserveRestaurantTableWithTaxi(int numOfPeople) {
        restaurant.reserveTable(numOfPeople);
        System.out.println("Taxi automatically ordered for guests.\n");
    }

    public void cancelRoomBooking(String roomType) {
        roomBooking.cancelRoom(roomType);
    }

    public void requestCleaning(String roomType) {
        cleaning.performCleaning(roomType);
    }

}



abstract class OrganizationComponent {
    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public abstract void showDetails();

    public abstract int calculateBudget();

    public abstract int getEmployeeCount();
}

class Employee extends OrganizationComponent {
    private String position;
    private int salary;

    public Employee(String name, String position, int salary) {
        super(name);
        this.position = position;
        this.salary = salary;
    }

    public void showDetails() {
        System.out.println("Employee: " + name + ", Position: " + position + ", Salary: " + salary);
    }

    public int calculateBudget() {
        return salary;
    }

    public int getEmployeeCount() {
        return 1;
    }
}

class Department extends OrganizationComponent {
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    public void addComponent(OrganizationComponent component) {
        components.add(component);
    }

    public void removeComponent(OrganizationComponent component) {
        components.remove(component);
    }

    public void showDetails() {
        System.out.println("Department: " + name);
        for (OrganizationComponent component : components) {
            component.showDetails();
        }
    }

    public int calculateBudget() {
        int totalBudget = 0;
        for (OrganizationComponent component : components) {
            totalBudget += component.calculateBudget();
        }
        return totalBudget;
    }

    public int getEmployeeCount() {
        int totalEmployees = 0;
        for (OrganizationComponent component : components) {
            totalEmployees += component.getEmployeeCount();
        }
        return totalEmployees;
    }

    public OrganizationComponent findEmployee(String employeeName) {
        for (OrganizationComponent component : components) {
            if (component instanceof Employee && component.name.equals(employeeName)) {
                return component;
            } else if (component instanceof Department) {
                OrganizationComponent result = ((Department) component).findEmployee(employeeName);
                if (result != null) return result;
            }
        }
        return null;
    }

    public void listAllEmployees() {
        for (OrganizationComponent component : components) {
            component.showDetails();
        }
    }
}

class Contractor extends Employee {
    public Contractor(String name, String position, int fixedPayment) {
        super(name, position, fixedPayment);
    }

    public int calculateBudget() {
        return 0;  
    }
}

public class modul10prac {
    public static void main(String[] args) {
        // Используем фасад для управления гостиницей
        HotelFacade hotelFacade = new HotelFacade();
        hotelFacade.bookRoomWithDiningAndCleaning("Deluxe Room", "Pizza");
        hotelFacade.organizeEventWithRoomsAndEquipment("Conference", "Suite", "Projector");
        hotelFacade.reserveRestaurantTableWithTaxi(4);
        hotelFacade.cancelRoomBooking("Deluxe Room");
        hotelFacade.requestCleaning("Suite");

        Department headOffice = new Department("Head Office");
        Department financeDept = new Department("Finance Department");
        Department hrDept = new Department("HR Department");

        Employee emp1 = new Employee("John Doe", "Manager", 5000);
        Employee emp2 = new Employee("Jane Smith", "Accountant", 3000);
        Contractor contractor = new Contractor("Mark Brown", "Freelancer", 2000);

        financeDept.addComponent(emp1);
        financeDept.addComponent(emp2);
        hrDept.addComponent(contractor);

        headOffice.addComponent(financeDept);
        headOffice.addComponent(hrDept);

        System.out.println("\nOrganization Structure:");
        headOffice.showDetails();

        System.out.println("\nTotal Budget: " + headOffice.calculateBudget());
        System.out.println("Total Employee Count: " + headOffice.getEmployeeCount());

        System.out.println("\nFinding Employee 'John Doe':");
        OrganizationComponent foundEmployee = headOffice.findEmployee("John Doe");
        if (foundEmployee != null) {
            foundEmployee.showDetails();
        }

        System.out.println("\nAll Employees in Head Office:");
        headOffice.listAllEmployees();
    }
}