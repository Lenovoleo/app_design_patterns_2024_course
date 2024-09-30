public class modul05lab{

}

interface Transport{
    void Move();
    void FuelUp();
}

class Car implements Transport{
    @Override
    public void Move(){}

    @Override
    public FuelUp(){}
}

class Motorcycle implements Transport{
    @Override
    public void Move(){}

    @Override
    public FuelUp(){}
}

class Plane implements Transport{
    @Override
    public void Move(){}

    @Override
    public FuelUp(){}
}

abstract class TransportFactory{
    public abstract Transport CreateTransport(){

    }
}

