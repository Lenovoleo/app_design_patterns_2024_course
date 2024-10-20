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
}

class AirConditioner {
    private int temperature = 24; 

    public void increaseTemperature() {
        temperature++;
        System.out.println("Температура кондиционера повышена до " + temperature + " градусов.");
    }

    public void decreaseTemperature() {
        temperature--;
        System.out.println("Температура кондиционера понижена до " + temperature + " градусов.");
    }
}

class TurnOnLightCommand implements ICommand {
    private Light light;

    public TurnOnLightCommand(Light light) {
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

class TurnOffLightCommand implements ICommand {
    private Light light;

    public TurnOffLightCommand(Light light) {
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

class RemoteControl {
    private Stack<ICommand> commandHistory = new Stack<>();

    public void executeCommand(ICommand command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            ICommand lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("История команд пуста.");
        }
    }
}

// public class modul08prac {
//     public static void main(String[] args) {
//         Light light = new Light();
//         AirConditioner airConditioner = new AirConditioner();

//         RemoteControl remoteControl = new RemoteControl();

//         remoteControl.executeCommand(new TurnOnLightCommand(light));  
//         remoteControl.executeCommand(new TurnOffLightCommand(light)); 
//         remoteControl.executeCommand(new TurnOnLightCommand(light));  

//         remoteControl.undoLastCommand();
//     }
// }



abstract class ReportGenerator {
    protected abstract void prepareData();
    protected abstract void formatData();
    protected abstract void saveReport();

    public final void generateReport() {
        prepareData();
        formatData();
        saveReport();
    }
}

class PdfReport extends ReportGenerator {
    @Override
    protected void prepareData() {
        System.out.println("Подготовка данных для PDF-отчета...");
    }

    @Override
    protected void formatData() {
        System.out.println("Форматирование данных для PDF-отчета...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Сохранение PDF-отчета...");
    }
}

class ExcelReport extends ReportGenerator {
    @Override
    protected void prepareData() {
        System.out.println("Подготовка данных для Excel-отчета...");
    }

    @Override
    protected void formatData() {
        System.out.println("Форматирование данных для Excel-отчета...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Сохранение Excel-отчета...");
    }
}

class HtmlReport extends ReportGenerator {
    @Override
    protected void prepareData() {
        System.out.println("Подготовка данных для HTML-отчета...");
    }

    @Override
    protected void formatData() {
        System.out.println("Форматирование данных для HTML-отчета...");
    }

    @Override
    protected void saveReport() {
        System.out.println("Сохранение HTML-отчета...");
    }
}

// public class modul08prac {
//     public static void main(String[] args) {
//         ReportGenerator pdfReport = new PdfReport();
//         pdfReport.generateReport();

//         ReportGenerator excelReport = new ExcelReport();
//         excelReport.generateReport();

//         ReportGenerator htmlReport = new HtmlReport();
//         htmlReport.generateReport();
//     }
// }




interface IMediator {
    void sendMessage(User sender, String message);
    void userJoined(User user);
    void userLeft(User user);
}

class ChatMediator implements IMediator {
    private Map<String, List<User>> channels = new HashMap<>();

    @Override
    public void sendMessage(User sender, String message) {
        System.out.println("[" + sender.getName() + "] отправил сообщение: " + message);
        for (User user : channels.get(sender.getChannel())) {
            if (user != sender) {
                user.receiveMessage(sender, message);
            }
        }
    }

    @Override
    public void userJoined(User user) {
        channels.putIfAbsent(user.getChannel(), new ArrayList<>());
        channels.get(user.getChannel()).add(user);
        System.out.println(user.getName() + " присоединился к каналу " + user.getChannel());

        for (User otherUser : channels.get(user.getChannel())) {
            if (otherUser != user) {
                otherUser.notifyUserJoined(user);
            }
        }
    }

    @Override
    public void userLeft(User user) {
        channels.get(user.getChannel()).remove(user);
        System.out.println(user.getName() + " покинул канал " + user.getChannel());

        for (User otherUser : channels.get(user.getChannel())) {
            otherUser.notifyUserLeft(user);
        }
    }
}

interface IUser {
    String getName();
    String getChannel();
    void sendMessage(String message);
    void receiveMessage(User sender, String message);
    void notifyUserJoined(User user);
    void notifyUserLeft(User user);
}

class User implements IUser {
    private IMediator mediator;
    private String name;
    private String channel;

    public User(IMediator mediator, String name, String channel) {
        this.mediator = mediator;
        this.name = name;
        this.channel = channel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getChannel() {
        return channel;
    }

    @Override
    public void sendMessage(String message) {
        mediator.sendMessage(this, message);
    }

    @Override
    public void receiveMessage(User sender, String message) {
        System.out.println("[" + name + "] получил сообщение от [" + sender.getName() + "]: " + message);
    }

    @Override
    public void notifyUserJoined(User user) {
        System.out.println(user.getName() + " присоединился к каналу " + user.getChannel());
    }

    @Override
    public void notifyUserLeft(User user) {
        System.out.println(user.getName() + " покинул канал " + user.getChannel());
    }
}

public class modul08prac {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediator();

        User user1 = new User(mediator, "User1", "Channel1");
        User user2 = new User(mediator, "User2", "Channel1");
        User user3 = new User(mediator, "User3", "Channel2");

        mediator.userJoined(user1);
        mediator.userJoined(user2);
        mediator.userJoined(user3);

        user1.sendMessage("Привет!");
        user2.sendMessage("Как дела?");

        mediator.userLeft(user2);

        user1.sendMessage("Прощай!");
    }
}
