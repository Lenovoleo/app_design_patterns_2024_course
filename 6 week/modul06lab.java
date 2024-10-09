import java.util.ArrayList;
import java.util.List;

// Singleton

class Logger {
    private static Logger instance;
    private LogLevel logLevel;
    private String logFilePath;

    private Logger() {
        logLevel = LogLevel.INFO;
        logFilePath = "log.txt";
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message, LogLevel level) {
        if (level.ordinal() >= logLevel.ordinal()) {
            System.out.println(String.format("[%s] %s", level, message));
        }
    }

    public void setLogLevel(LogLevel level) {
        this.logLevel = level;
    }

    public void setLogFilePath(String path) {
        this.logFilePath = path;
    }
}

enum LogLevel {
    INFO, WARNING, ERROR
}

// Builder

class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String os;

    public Computer(String cpu, String ram, String storage, String gpu, String os) {
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.gpu = gpu;
        this.os = os;
    }

    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getGpu() {
        return gpu;
    }

    public String getOs() {
        return os;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}

interface IComputerBuilder {
    void setCpu(String cpu);
    void setRam(String ram);
    void setStorage(String storage);
    void setGpu(String gpu);
    void setOs(String os);
    Computer getComputer();
}

class OfficeComputerBuilder implements IComputerBuilder {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String os;

    @Override
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public void setRam(String ram) {
        this.ram = ram;
    }

    @Override
    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Override
    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    @Override
    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public Computer getComputer() {
        return new Computer(cpu, ram, storage, gpu, os);
    }
}

class GamingComputerBuilder implements IComputerBuilder {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String os;

    @Override
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public void setRam(String ram) {
        this.ram = ram;
    }

    @Override
    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Override
    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    @Override
    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public Computer getComputer() {
        return new Computer(cpu, ram, storage, gpu, os);
    }
}

class ComputerDirector {
    private IComputerBuilder builder;

    public ComputerDirector(IComputerBuilder builder) {
        this.builder = builder;
    }

    public void constructComputer() {
        builder.setCpu("Intel i5");
        builder.setRam("8GB");
        builder.setStorage("1TB HDD");
        builder.setGpu("Integrated");
        builder.setOs("Windows 10");
    }

    public Computer getComputer() {
        return builder.getComputer();
    }
}

// Prototype

interface IPrototype {
    IPrototype clone();
}

class Section implements IPrototype {
    private String title;
    private String text;

    public Section(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Section clone() {
        return new Section(this.title, this.text);
    }

    @Override
    public String toString() {
        return "Section{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

class Image implements IPrototype {
    private String url;

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Image clone() {
        return new Image(this.url);
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                '}';
    }
}

class Document implements IPrototype {
    private String title;
    private String content;
    private List<Section> sections;
    private List<Image> images;

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
        this.sections = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public List<Image> getImages() {
        return images;
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    @Override
    public Document clone() {
        Document clonedDocument = new Document(this.title, this.content);
        for (Section section : this.sections) {
            clonedDocument.addSection(section.clone());
        }
        for (Image image : this.images) {
            clonedDocument.addImage(image.clone());
        }
        return clonedDocument;
    }

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sections=" + sections +
                ", images=" + images +
                '}';
    }
}

class DocumentManager {
    public Document createDocument(IPrototype prototype) {
        return (Document) prototype.clone();
    }
}

public class modul06lab {
    public static void main(String[] args) {
        // Singleton
        Logger logger = Logger.getInstance();
        logger.log("Информация", LogLevel.INFO);
        logger.log("Предупреждение", LogLevel.WARNING);
        logger.log("Ошибка", LogLevel.ERROR);
        logger.setLogLevel(LogLevel.WARNING);
        logger.log("Информация", LogLevel.INFO); 

        // Builder
        IComputerBuilder officeBuilder = new OfficeComputerBuilder();
        ComputerDirector officeDirector = new ComputerDirector(officeBuilder);
        officeDirector.constructComputer();
        Computer officeComputer = officeDirector.getComputer();
        System.out.println("Офисный компьютер: " + officeComputer);

        IComputerBuilder gamingBuilder = new GamingComputerBuilder();
        ComputerDirector gamingDirector = new ComputerDirector(gamingBuilder);
        gamingDirector.constructComputer();
        Computer gamingComputer = gamingDirector.getComputer();
        System.out.println("Игровой компьютер: " + gamingComputer);

        // Prototype
        Document document = new Document("Мой документ", "Это текст документа");
        document.addSection(new Section("Раздел 1", "Текст раздела 1"));
        document.addSection(new Section("Раздел 2", "Текст раздела 2"));
        document.addImage(new Image("https://example.com/image.jpg"));

        System.out.println("Исходный документ:");
        System.out.println(document);

        DocumentManager manager = new DocumentManager();
        Document clonedDocument = manager.createDocument(document);

        System.out.println("\nКлонированный документ:");
        System.out.println(clonedDocument);

        clonedDocument.setTitle("Клонированный документ");
        clonedDocument.getSections().get(0).setText("Измененный текст раздела 1");
        clonedDocument.addImage(new Image("https://example.com/another-image.jpg"));

        System.out.println("\nИзмененный клонированный документ:");
        System.out.println(clonedDocument);

        System.out.println("\nИсходный документ (не изменен):");
        System.out.println(document);
    }
}