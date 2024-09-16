public class lab1 {
    public static void main(String[] args) {
        Worker alex = new Worker("Alex", 0, "Main worker", 5000, 40);
        Worker julia = new Worker("Julia", 1, "Middle worker", 3500, 60);

        Manager bob = new Manager("Bob", 3, "Manger", 200000, 40000);
        Manager liza = new Manager("Liza", 4, "Main manager", 3500000, 50000);

        System.out.println("Salary of " + alex.name + ": " + alex.calcSalary());
        System.out.println("Salary of " + julia.name + ": " + julia.calcSalary());
        System.out.println("Salary of " + bob.name + ": " + bob.calcSalary());
        System.out.println("Salary of " + liza.name + ": " + liza.calcSalary());
    }
}

abstract class Employee{
    protected String name;
    protected int id;
    protected String position;

    public Employee(String name, int id ,String position){
        this.name = name;
        this.id = id;
        this.position = position;
    }
    public abstract double calcSalary();
     
    
}

class Worker extends Employee{
    protected double rate_per_hour;
    protected int work_hours;
    public Worker(String name, int id, String position, double reate_per_hour, int work_hours){
        super(name, id, position);
        this.rate_per_hour = reate_per_hour;
        this.work_hours = work_hours;
    }
    @Override
    public double calcSalary(){
        return work_hours * rate_per_hour;
    }
}

class Manager extends Employee{
    protected double fix_salary;
    protected double award;

    public Manager(String name , int id, String position, double fix_salary, double award){
        super(name, id , position);
        this.fix_salary = fix_salary;
        this.award = award;
    }

    @Override
    public double calcSalary(){
        return  fix_salary + award;
    }

}