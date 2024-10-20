import java.util.*;

interface ICommand {
    void execute();
    void undo();
}

class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class Light {
    public void on() {
        System.out.println("Свет включен.");
    }

    public void off() {
        System.out.println("Свет выключен.");
    }
}

class TelevisionOnCommand implements ICommand {
    private Television tv;

    public TelevisionOnCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}

class TelevisionOffCommand implements ICommand {
    private Television tv;

    public TelevisionOffCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}

class Television {
    public void on() {
        System.out.println("Телевизор включен.");
    }

    public void off() {
        System.out.println("Телевизор выключен.");
    }
}

class RemoteControl {
    private ICommand onCommand;
    private ICommand offCommand;

    public void setCommands(ICommand onCommand, ICommand offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    public void pressOnButton() {
        onCommand.execute();
    }

    public void pressOffButton() {
        offCommand.execute();
    }

    public void pressUndoButton() {
        onCommand.undo();
    }
}

// public class modul08lab {
//     public static void main(String[] args) {
//         Light livingRoomLight = new Light();
//         Television tv = new Television();

//         ICommand lightOn = new LightOnCommand(livingRoomLight);
//         ICommand lightOff = new LightOffCommand(livingRoomLight);

//         ICommand tvOn = new TelevisionOnCommand(tv);
//         ICommand tvOff = new TelevisionOffCommand(tv);

//         RemoteControl remote = new RemoteControl();

//         System.out.println("Управление светом:");
//         remote.setCommands(lightOn, lightOff);
//         remote.pressOnButton();
//         remote.pressOffButton();
//         remote.pressUndoButton();

//         System.out.println("\nУправление телевизором:");
//         remote.setCommands(tvOn, tvOff);
//         remote.pressOnButton();
//         remote.pressOffButton();
//     }
// }


abstract class Beverage {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater() {
        System.out.println("Кипячение воды...");
    }

    private void pourInCup() {
        System.out.println("Наливание в чашку...");
    }

    protected abstract void brew();
    protected abstract void addCondiments();
}

class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Заваривание чая...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавление лимона...");
    }
}

class Coffee extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Заваривание кофе...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавление сахара и молока...");
    }
}

// public class modul08lab {
//     public static void main(String[] args) {
//         System.out.println("Приготовление чая:");
//         Beverage tea = new Tea();
//         tea.prepareRecipe();
//         System.out.println();

//         System.out.println("Приготовление кофе:");
//         Beverage coffee = new Coffee();
//         coffee.prepareRecipe();
//         System.out.println();
//     }
// }



interface IMediator {
    void sendMessage(String message, Colleague sender);
    void registerColleague(Colleague colleague);
}

abstract class Colleague {
    protected IMediator mediator;

    public Colleague(IMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receiveMessage(String message);
}

class ChatMediator implements IMediator {
    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void registerColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void sendMessage(String message, Colleague sender) {
        for (Colleague colleague : colleagues) {
            if (colleague != sender) {
                colleague.receiveMessage(message);
            }
        }
    }
}

class User extends Colleague {
    private String name;

    public User(IMediator mediator, String name) {
        super(mediator);
        this.name = name;
    }

    public void send(String message) {
        System.out.println(name + " отправляет сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}

public class modul08lab {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatMediator();

        User user1 = new User(chatMediator, "Алиса");
        User user2 = new User(chatMediator, "Боб");
        User user3 = new User(chatMediator, "Чарли");

        chatMediator.registerColleague(user1);
        chatMediator.registerColleague(user2);
        chatMediator.registerColleague(user3);

        user1.send("Привет всем!");
        user2.send("Привет, Алиса!");
        user3.send("Всем привет!");
    }
}
