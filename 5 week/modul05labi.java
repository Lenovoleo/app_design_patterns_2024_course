import java.util.Scanner;

public class modul05labi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Выберите тип транспорта:\n 1.Car \n 2.Moto \n 3.Plane \n 4.Bike");
        choice = sc.nextInt();
        sc.nextLine(); 

        System.out.println("Введите модель:");
        String model = sc.nextLine();

        System.out.println("Введите скорость:");
        int speed = sc.nextInt();

        Transport transport = null;

        switch (choice) {
            case 1:
                transport = new CarFactory().CreateTransport(model, speed);
                break;
            case 2:
                transport = new MotorcycleFactory().CreateTransport(model, speed);
                break;
            case 3:
                transport = new PlaneFactory().CreateTransport(model, speed);
                break;
            case 4:
                transport = new BikeFactory().CreateTransport(model, speed);
                break;
            default:
                System.out.println("Неверный выбор!");
                System.exit(0);
        }

        if (transport != null) {
            transport.Move();
            transport.Fuel();
        }
    }
}

interface Transport {

    void Move();
    void Fuel();

}

class Car implements Transport {
    protected String model;
    protected int speed;

    Car(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    @Override
    public void Move() {
        System.out.println("Машина " + model + " едет со скоростью " + speed + " км/ч");
    }

    @Override
    public void Fuel() {
        System.out.println("Использует бензин");
    }
}

class Motorcycle implements Transport {
    protected String model;
    protected int speed;

    Motorcycle(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    @Override
    public void Move() {
        System.out.println("Мотоцикл " + model + " едет со скоростью " + speed + " км/ч");
    }

    @Override
    public void Fuel() {
        System.out.println("Использует бензин");
    }
}

class Plane implements Transport {
    protected String model;
    protected int speed;

    Plane(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    @Override
    public void Move() {
        System.out.println("Самолет " + model + " летит со скоростью " + speed + " км/ч");
    }

    @Override
    public void Fuel() {
        System.out.println("Использует керосин");
    }
}

class Bike implements Transport {
    protected String model;
    protected int speed;

    Bike(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    @Override
    public void Move() {
        System.out.println("Велосипед " + model + " едет со скоростью " + speed + " км/ч");
    }

    @Override
    public void Fuel() {
        System.out.println("Использует энергию человека");
    }
}

abstract class TransportFactory {
    public abstract Transport CreateTransport(String model, int speed);
}

class CarFactory extends TransportFactory {
    @Override
    public Transport CreateTransport(String model, int speed) {
        return new Car(model, speed);
    }
}

class MotorcycleFactory extends TransportFactory {
    @Override
    public Transport CreateTransport(String model, int speed) {
        return new Motorcycle(model, speed);
    }
}

class PlaneFactory extends TransportFactory {
    @Override
    public Transport CreateTransport(String model, int speed) {
        return new Plane(model, speed);
    }
}

class BikeFactory extends TransportFactory {
    @Override
    public Transport CreateTransport(String model, int speed) {
        return new Bike(model, speed);
    }
}
