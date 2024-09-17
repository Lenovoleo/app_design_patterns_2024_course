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
    }
}

abstract class Vehicle{
    protected String brand;
    protected String model;
    protected int issue_year;

    public Vehicle(String brand, String model, int issue_year){
        this.brand = brand;
        this.model = model;
        this.issue_year = issue_year;
    }
    public abstract void startEngine();
    public abstract void stopEngine();
}

class Car extends Vehicle{
    protected int amount_doors;
    protected String transmission_type;

    public Car(String brand, String model, int issue_year, int amount_doors, String transmission_type){
        super(brand, model, issue_year);
        this.amount_doors = amount_doors;
        this.transmission_type = transmission_type;
    }

    @Override
    public void startEngine(){
        System.out.println("Engine start");
    }

    @Override
    public void stopEngine(){
        System.out.println("Engine stop");
    }
}

class Motorcycle extends Vehicle{
    protected String body_type;
    protected boolean has_box;

    public Motorcycle(String brand , String model, int issue_year, String body_type, boolean has_box){
        super(brand , model, issue_year);
        this.body_type = body_type;
        this.has_box = has_box;
    }
    @Override
    public void startEngine(){
        System.out.println("Engine start");
    }

    @Override
    public void stopEngine(){
        System.out.println("Engine stop");
    }

}


class Garage{
    private Vehicle list_vehicle[];
    private int size_of_vehicles = 0;

    public void addVehicle(Vehicle car){
        list_vehicle[size_of_vehicles] = car;
        size_of_vehicles++;
    }

    Garage(Vehicle list_vehicle[]){
        this.list_vehicle = list_vehicle;
    }
    public void dropVehicle(int index_vehicle){
        Vehicle arr[] = new Vehicle[list_vehicle.length - 1];
        int index = 0;

        for(int i = 0; i < list_vehicle.length ; i++){
            if(i != index_vehicle){
                arr[index++] = list_vehicle[i];
            }
        }
        list_vehicle = arr;
    
    
    }
    public Vehicle[] getVehicles(){
        return list_vehicle;
    }
}

class Fleet{
    private Garage list_garages[];
    private int size_list_garages = 0;

    Fleet(Garage list_garages[]){
        this.list_garages = list_garages;
    }

    public void addGarage(Garage g){
        list_garages[size_list_garages] = g;
        size_list_garages++;
    }

    public void dropGarage(int index_garage){
        Garage arr[] = new Garage[list_garages.length - 1];
        int index = 0;
        for(int i = 0; i < list_garages.length; i++){
            if(i != index_garage){
                arr[index] = list_garages[i];
            }
        }
        list_garages = arr;
    }
    public Vehicle searchVehicle(String brand, String model){
        for (Garage garage : list_garages) {
            for (Vehicle vehicle : garage.getVehicles()) {  
                if (vehicle.brand.equals(brand) && vehicle.model.equals(model)) {
                    return vehicle;  
                }
            }
        }
        return null; 
    }


}