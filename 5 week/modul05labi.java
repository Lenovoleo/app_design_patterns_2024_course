import java.util.Scanner;

public class modul05labi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите тип транспорта:");
        System.out.println("1. Автомобиль");
        System.out.println("2. Мотоцикл");
        System.out.println("3. Самолет");

        int choice = scanner.nextInt();

        Transport transport = createTransport(choice);

        System.out.println("Введите модель:");
        String model = scanner.next();
        transport.setModel(model);

        System.out.println("Введите скорость:");
        int speed = scanner.nextInt();
        transport.setSpeed(speed);

        System.out.println("\nСоздан транспорт:");
        System.out.println("Тип: " + transport.getClass().getSimpleName());
        System.out.println("Модель: " + transport.getModel());
        System.out.println("Скорость: " + transport.getSpeed() + " км/ч");

        transport.Move();
        transport.FuelUp();
    }

    private static Transport createTransport(int choice) {
        switch (choice) {
            case 1:
                return new Car();
            case 2:
                return new Motorcycle();
            case 3:
                return new Plane();
            default:
                System.out.println("Неверный выбор.");
                return null;
        }
    }
}

interface Transport {
    void Move();
    void FuelUp();
    String getModel();
    void setModel(String model);
    int getSpeed();
    void setSpeed(int speed);
}

class Car implements Transport {
    private String model;
    private int speed;

    @Override
    public void Move() {
        System.out.println("Автомобиль " + model + " едет со скоростью " + speed + " км/ч.");
    }

    @Override
    public void FuelUp() {
        System.out.println("Заправляем автомобиль " + model + " бензином.");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

class Motorcycle implements Transport {
    private String model;
    private int speed;

    @Override
    public void Move() {
        System.out.println("Мотоцикл " + model + " едет со скоростью " + speed + " км/ч.");
    }

    @Override
    public void FuelUp() {
        System.out.println("Заправляем мотоцикл " + model + " бензином.");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

class Plane implements Transport {
    private String model;
    private int speed;

    @Override
    public void Move() {
        System.out.println("Самолет " + model + " летит со скоростью " + speed + " км/ч.");
    }

    @Override
    public void FuelUp() {
        System.out.println("Заправляем самолет " + model + " авиационным топливом.");
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}