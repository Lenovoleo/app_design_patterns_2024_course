public class modul3lab {
    
}
// Использование методов для устранения дублирования кода
class OrderService{
    double quantitiy;
    double price;


    public void CreateOrder(String productName , int quantity, double price){
        System.out.println("Order for " + productName + " created. Total: "+ getTotalPrice(quantity, price));
    }
    public void UpdateOrder(String productName , int quantity, double price){
        System.out.println("Order for " + productName + " updated. New total: "+ getTotalPrice(quantity, price));
    }
    public double getTotalPrice(int quantitiy, double price){
        return quantitiy * price;
    }
}

// Использование общих базовых классов

class Vehicle{
    public void Start(){
        System.out.println("Vehicle is starting");
    }
    public void Stop(){
        System.out.println("Vehicle is stopping");
    }
}

class Car extends Vehicle{

}

class Truck extends Vehicle{

}

// Избегание чрезмерного использования абстракций

class Calculator{
    protected double a;
    protected double b;
    Calculator(double a, double b){
        this.a = a;
        this.b = b;

    }

    public void AdditionOperation(double a , double b){
        System.out.println(a + b); 
    }
}


// Избегание избыточного использования шаблонов проектирования
class Client{
    public void Execute(){
        System.out.println("Doing something....");
    }
}

// Избыточное создание абстракций

class Circle{
    double radius;

    Circle(double radius){
        this.radius = radius;
    }

    public double CalculateArea(){
        return Math.PI * radius * radius;
    }
}

class Square{
    double side;

    Square(double side){
        this.side = side;
    }

    public double CalculateArea(){
        return side * side;
    }
}
// Излишняя параметризация методов

class MathOperations{
    public int Add(int a, int b){
        return a + b;
    }
}