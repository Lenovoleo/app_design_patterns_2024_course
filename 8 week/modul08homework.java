import java.util.*;

interface ICommand {
    void execute();
    void undo();
}

class Light {
    private boolean isOn;

    public void turnOn() {
        isOn = true;
        System.out.println("Свет включен.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Свет выключен.");
    }

    public boolean isOn() {
        return isOn;
    }
}

class Door {
    private boolean isOpen;

    public void open() {
        isOpen = true;
        System.out.println("Дверь открыта.");
    }

    public void close() {
        isOpen = false;
        System.out.println("Дверь закрыта.");
    }

    public boolean isOpen() {
        return isOpen;
    }
}

class Thermostat {
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Температура установлена на " + temperature + " градусов.");
    }

    public int getTemperature() {
        return temperature;
    }
}

class LightOnCommand implements ICommand {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements ICommand {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

class DoorOpenCommand implements ICommand {
    private final Door door;

    public DoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

    @Override
    public void undo() {
        door.close();
    }
}

class DoorCloseCommand implements ICommand {
    private final Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

    @Override
    public void undo() {
        door.open();
    }
}

class SetTemperatureCommand implements ICommand {
    private final Thermostat thermostat;
    private final int temperature;

    public SetTemperatureCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        thermostat.setTemperature(temperature);
    }

    @Override
    public void undo() {
    }
}

class Invoker {
    private final Stack<ICommand> undoStack = new Stack<>();

    public void executeCommand(ICommand command) {
        command.execute();
        undoStack.push(command);
    }

    public void undoCommand() {
        if (!undoStack.isEmpty()) {
            ICommand command = undoStack.pop();
            command.undo();
        }
    }
}

// public class modul08homework {
//     public static void main(String[] args) {
//         Light light = new Light();
//         Door door = new Door();
//         Thermostat thermostat = new Thermostat();

//         Invoker invoker = new Invoker();

//         invoker.executeCommand(new LightOnCommand(light)); 
//         invoker.executeCommand(new DoorOpenCommand(door)); 
//         invoker.executeCommand(new SetTemperatureCommand(thermostat, 22)); 

//         invoker.undoCommand(); 
//         invoker.undoCommand(); 
//         invoker.undoCommand(); 
//     }
// }




abstract class Beverage {
    
    public final void prepare() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    private void boilWater() {
        System.out.println("Кипятим воду.");
    }

    private void pourInCup() {
        System.out.println("Наливаем в чашку.");
    }

    protected abstract void brew();
    protected abstract void addCondiments();

    protected boolean customerWantsCondiments() {
        System.out.println("Добавить добавки? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("y");
    }
}

class Tea extends Beverage {

    @Override
    protected void brew() {
        System.out.println("Завариваем чай.");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавляем лимон и сахар.");
    }
}

class Coffee extends Beverage {

    @Override
    protected void brew() {
        System.out.println("Завариваем кофе.");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Добавляем молоко и сахар.");
    }
}

// public class modul08homework {
//     public static void main(String[] args) {
//         System.out.println("Заказ чая:");
//         Beverage tea = new Tea();
//         tea.prepare();

//         System.out.println("\nЗаказ кофе:");
//         Beverage coffee = new Coffee();
//         coffee.prepare();
//     }
// }


interface IMediator {
    void sendMessage(String message, User sender);
    void join(User user);
    void leave(User user);
}

class ChatRoom implements IMediator {
    private Map<User, List<String>> messages = new HashMap<>();
    private List<User> users = new ArrayList<>();

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                messages.computeIfAbsent(user, k -> new ArrayList<>()).add(sender.getName() + ": " + message);
                System.out.println(sender.getName() + " отправил сообщение " + user.getName() + ": " + message);
            }
        }
    }

    @Override
    public void join(User user) {
        users.add(user);
        System.out.println(user.getName() + " присоединился к чату.");
    }

    @Override
    public void leave(User user) {
        users.remove(user);
        System.out.println(user.getName() + " покинул чат.");
    }

    public List<String> getMessages(User user) {
        return messages.getOrDefault(user, new ArrayList<>());
    }

    public void clearMessages(User user) {
        messages.put(user, new ArrayList<>());
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(IMediator mediator, String message) {
        mediator.sendMessage(message, this);
    }

    public void receiveMessages(ChatRoom chatRoom) {
        List<String> userMessages = chatRoom.getMessages(this);
        for (String message : userMessages) {
            System.out.println("Получено сообщение: " + message);
        }
        chatRoom.clearMessages(this);
    }

    public void joinChat(IMediator mediator) {
        mediator.join(this);
    }

    public void leaveChat(IMediator mediator) {
        mediator.leave(this);
    }
}

public class modul08homework {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User user1 = new User("John");
        User user2 = new User("Kim");
        User user3 = new User("Chen");

        user1.joinChat(chatRoom);
        user2.joinChat(chatRoom);

        user1.sendMessage(chatRoom, "Привет, Kim!");
        user2.sendMessage(chatRoom, "Привет, John! Как дела?");

        user1.receiveMessages(chatRoom);
        user2.receiveMessages(chatRoom);

        user3.joinChat(chatRoom);

        user3.sendMessage(chatRoom, "Привет всем!");

        user1.receiveMessages(chatRoom);
        user2.receiveMessages(chatRoom);
        user3.receiveMessages(chatRoom);

        user2.leaveChat(chatRoom);

        user1.sendMessage(chatRoom, "Kim, ты где?");

        user1.receiveMessages(chatRoom);
        user3.receiveMessages(chatRoom);
    }
}
