public class prac1 {
    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Camry", 2020, 4, "Automatic");
        Car car2 = new Car("Honda", "Civic", 2019, 4, "Manual");
        Motorcycle moto1 = new Motorcycle("Yamaha", "MT-07", 2021, "Naked", false);

        
        Garage garage1 = new Garage(new Vehicle[10]);
        Garage garage2 = new Garage(new Vehicle[5]);

       
        garage1.addVehicle(car1);
        garage1.addVehicle(moto1);
        garage2.addVehicle(car2);

    
        Fleet fleet = new Fleet(new Garage[5]); 
        fleet.addGarage(garage1);
        fleet.addGarage(garage2);

       
        Vehicle foundVehicle = fleet.searchVehicle("Toyota", "Camry");
        if (foundVehicle != null) {
            System.out.println("Found vehicle: " + foundVehicle.brand + " " + foundVehicle.model);
        } else {
            System.out.println("Vehicle not found");
        }
    }
}


abstract class Vehicle {
    protected String brand;
    protected String model;
    protected int issue_year;

    public Vehicle(String brand, String model, int issue_year) {
        this.brand = brand;
        this.model = model;
        this.issue_year = issue_year;
    }

    public abstract void startEngine();
    public abstract void stopEngine();
}


class Car extends Vehicle {
    protected int amount_doors;
    protected String transmission_type;

    public Car(String brand, String model, int issue_year, int amount_doors, String transmission_type) {
        super(brand, model, issue_year);
        this.amount_doors = amount_doors;
        this.transmission_type = transmission_type;
    }

    @Override
    public void startEngine() {
        System.out.println("Car engine started.");
    }

    @Override
    public void stopEngine() {
        System.out.println("Car engine stopped.");
    }
}


class Motorcycle extends Vehicle {
    protected String body_type;
    protected boolean has_box;

    public Motorcycle(String brand, String model, int issue_year, String body_type, boolean has_box) {
        super(brand, model, issue_year);
        this.body_type = body_type;
        this.has_box = has_box;
    }

    @Override
    public void startEngine() {
        System.out.println("Motorcycle engine started.");
    }

    @Override
    public void stopEngine() {
        System.out.println("Motorcycle engine stopped.");
    }
}


class Garage {
    protected Vehicle[] list_vehicle;
    private int size_of_vehicles = 0;

  
    Garage(Vehicle[] list_vehicle) {
        this.list_vehicle = list_vehicle;
    }

   
    public void addVehicle(Vehicle car) {
        if (size_of_vehicles < list_vehicle.length) {
            list_vehicle[size_of_vehicles] = car;
            size_of_vehicles++;
        } else {
            System.out.println("Garage is full, cannot add more vehicles.");
        }
    }

    
    public void dropVehicle(int index_vehicle) {
        Vehicle[] arr = new Vehicle[list_vehicle.length - 1];
        int index = 0;

        for (int i = 0; i < size_of_vehicles; i++) {
            if (i != index_vehicle) {
                arr[index++] = list_vehicle[i];
            }
        }
        list_vehicle = arr;
        size_of_vehicles--;
    }

  
    public Vehicle[] getVehicles() {
        Vehicle[] currentVehicles = new Vehicle[size_of_vehicles];
        for (int i = 0; i < size_of_vehicles; i++) {
            currentVehicles[i] = list_vehicle[i];
        }
        return currentVehicles;
    }
}


class Fleet {
    private Garage[] list_garages;
    private int size_list_garages = 0;

    
    Fleet(Garage[] list_garages) {
        this.list_garages = list_garages;
    }


    public void addGarage(Garage g) {
        if (size_list_garages < list_garages.length) {
            list_garages[size_list_garages] = g;
            size_list_garages++;
        } else {
            System.out.println("Fleet is full, cannot add more garages.");
        }
    }


    public void dropGarage(int index_garage) {
        if (index_garage < 0 || index_garage >= size_list_garages) {
            System.out.println("Invalid index, no garage removed.");
            return;
        }

        Garage[] arr = new Garage[list_garages.length - 1];
        int index = 0;
        for (int i = 0; i < size_list_garages; i++) {
            if (i != index_garage) {
                arr[index++] = list_garages[i];
            }
        }
        list_garages = arr;
        size_list_garages--;
    }

    
    public Vehicle searchVehicle(String brand, String model) {
        for (int i = 0; i < size_list_garages; i++) {
            for (Vehicle vehicle : list_garages[i].getVehicles()) {
                if (vehicle.brand.equals(brand) && vehicle.model.equals(model)) {
                    return vehicle;
                }
            }
        }
        return null;
    }
}
