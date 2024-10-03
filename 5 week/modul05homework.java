import java.util.Scanner;

public class modul05homework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        System.out.println("Choose 1 transport: \n1.Car \n2.Motorcycle \n3.Truck \n4.Bus");
        choice = sc.nextInt();
        sc.nextLine(); 

        IVehicle transport = null;

        switch (choice) {
            case 1:
                transport = new CarFactory().CreateVehicle();
                System.out.println("Enter brand: ");
                String brand = sc.nextLine();
                System.out.println("Enter model: ");
                String model = sc.nextLine();
                System.out.println("Enter type of fuel: ");
                String type_fuel = sc.nextLine();

                
                ((Car) transport).setBrand(brand);
                ((Car) transport).setModel(model);
                ((Car) transport).setTypeFuel(type_fuel);
                break;

            case 2:
                transport = new MotorcycleFactory().CreateVehicle();
                System.out.println("Enter moto type: ");
                String moto_type = sc.nextLine();
                System.out.println("Enter engine capacity: ");
                double engine_capacity = sc.nextDouble();

                
                ((Motorcycle) transport).setMotoType(moto_type);
                ((Motorcycle) transport).setEngineCapacity(engine_capacity);
                break;

            case 3:
                transport = new TruckFactory().CreateVehicle();
                System.out.println("Enter load capacity: ");
                double load_capacity = sc.nextDouble();
                System.out.println("Enter number of axles: ");
                int num_of_axles = sc.nextInt();

                
                ((Truck) transport).setLoadCapacity(load_capacity);
                ((Truck) transport).setNumOfAxles(num_of_axles);
                break;

            case 4:
                transport = new BusFactory().CreateVehicle();
                System.out.println("Enter capacity: ");
                int capacity = sc.nextInt();
                sc.nextLine(); // Чтобы не пропустить строку
                System.out.println("Enter type of fuel: ");
                String bus_fuel = sc.nextLine();

                
                ((Bus) transport).setCapacity(capacity);
                ((Bus) transport).setTypeFuel(bus_fuel);
                break;

            default:
                System.out.println("Error number");
                return;
        }

        
        if (transport != null) {
            System.out.println("Testing the transport system:");
            transport.Drive();
            transport.Refuel();
        }
    }
}

interface IVehicle {
    void Drive();
    void Refuel();
}

class Car implements IVehicle {
    protected String brand;
    protected String model;
    protected String type_fuel;

    Car() {}

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTypeFuel(String type_fuel) {
        this.type_fuel = type_fuel;
    }

    @Override
    public void Drive() {
        System.out.println("Driving a car: " + brand + " " + model);
    }

    @Override
    public void Refuel() {
        System.out.println("Refueling the car with " + type_fuel);
    }
}

class Motorcycle implements IVehicle {
    protected String moto_type;
    protected double engine_capacity;

    
    Motorcycle() {}

    public void setMotoType(String moto_type) {
        this.moto_type = moto_type;
    }

    public void setEngineCapacity(double engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    @Override
    public void Drive() {
        System.out.println("Driving a motorcycle: " + moto_type);
    }

    @Override
    public void Refuel() {
        System.out.println("Refueling the motorcycle");
    }
}

class Truck implements IVehicle {
    protected double load_capacity;
    protected int num_of_axles;

    
    Truck() {}

    public void setLoadCapacity(double load_capacity) {
        this.load_capacity = load_capacity;
    }

    public void setNumOfAxles(int num_of_axles) {
        this.num_of_axles = num_of_axles;
    }

    @Override
    public void Drive() {
        System.out.println("Driving a truck with load capacity: " + load_capacity);
    }

    @Override
    public void Refuel() {
        System.out.println("Refueling the truck");
    }
}

class Bus implements IVehicle {
    protected int capacity;
    protected String type_fuel;

    
    Bus() {}

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTypeFuel(String type_fuel) {
        this.type_fuel = type_fuel;
    }

    @Override
    public void Drive() {
        System.out.println("Driving a bus with capacity: " + capacity);
    }

    @Override
    public void Refuel() {
        System.out.println("Refueling the bus with " + type_fuel);
    }
}

abstract class VehicleFactory {
    public abstract IVehicle CreateVehicle();
}

class CarFactory extends VehicleFactory {
    @Override
    public IVehicle CreateVehicle() {
        return new Car();  
    }
}

class MotorcycleFactory extends VehicleFactory {
    @Override
    public IVehicle CreateVehicle() {
        return new Motorcycle();  
    }
}

class TruckFactory extends VehicleFactory {
    @Override
    public IVehicle CreateVehicle() {
        return new Truck();  
    }
}

class BusFactory extends VehicleFactory {
    @Override
    public IVehicle CreateVehicle() {
        return new Bus();  
    }
}
