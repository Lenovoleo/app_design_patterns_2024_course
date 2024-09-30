import javax.crypto.spec.IvParameterSpec;

public class modul05homework {
    
}

interface IVehicle{
    void Drive();
    void Refuel();
}

class Car implements IVehicle{
    protected String brand;
    protected String model;
    protected String type_fuel;

    @Override
    public void Drive(){}

    @Override
    public void Refuel(){}
}

class Motorcycle implements IVehicle{
    protected String moto_type;
    protected double engine_capacity;

    @Override
    public void Drive(){

    }

    @Override
    public void Refuel(){}
}

class Truck implements IVehicle{
    protected double load_capacity;
    protected int num_of_axles;

    @Override
    public void Drive(){}

    @Override
    public void Refuel(){}
}

abstract class VehicleFactory{
    public abstract IVehicle CreateVehicle(){
        
    }
}