import java.util.*;




class TV {
    public void on() { System.out.println("TV включен"); }
    public void off() { System.out.println("TV выключен"); }
    public void setChannel(int channel) { System.out.println("Канал установлен на " + channel); }
}

class AudioSystem {
    public void on() { System.out.println("Аудиосистема включена"); }
    public void off() { System.out.println("Аудиосистема выключена"); }
    public void setVolume(int level) { System.out.println("Громкость установлена на " + level); }
}

class DVDPlayer {
    public void play() { System.out.println("Воспроизведение DVD"); }
    public void pause() { System.out.println("Пауза DVD"); }
    public void stop() { System.out.println("Остановка DVD"); }
}

class GameConsole {
    public void on() { System.out.println("Игровая консоль включена"); }
    public void startGame() { System.out.println("Запуск игры"); }
}

class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audioSystem;
    private DVDPlayer dvdPlayer;
    private GameConsole gameConsole;

    public HomeTheaterFacade(TV tv, AudioSystem audioSystem, DVDPlayer dvdPlayer, GameConsole gameConsole) {
        this.tv = tv;
        this.audioSystem = audioSystem;
        this.dvdPlayer = dvdPlayer;
        this.gameConsole = gameConsole;
    }

    public void watchMovie() {
        tv.on();
        audioSystem.on();
        dvdPlayer.play();
    }

    public void endMovie() {
        dvdPlayer.stop();
        tv.off();
        audioSystem.off();
    }

    public void playGame() {
        gameConsole.on();
        gameConsole.startGame();
    }

    public void listenToMusic() {
        tv.on();
        audioSystem.on();
    }

    public void setVolume(int level) {
        audioSystem.setVolume(level);
    }
}

public class modul10homework {
    public static void main(String[] args) {
        TV tv = new TV();
        AudioSystem audioSystem = new AudioSystem();
        DVDPlayer dvdPlayer = new DVDPlayer();
        GameConsole gameConsole = new GameConsole();
        
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, audioSystem, dvdPlayer, gameConsole);

        homeTheater.watchMovie();
        homeTheater.setVolume(5);
        homeTheater.endMovie();
        
        homeTheater.playGame();
        
        homeTheater.listenToMusic();
        homeTheater.setVolume(8);
    }
}



abstract class FileSystemComponent {
    protected String name;
    
    public FileSystemComponent(String name) {
        this.name = name;
    }
    
    public abstract void display();
    public abstract int getSize();
}

class File extends FileSystemComponent {
    private int size;

    public File(String name, int size) {
        super(name);
        this.size = size;
    }
    
    @Override
    public void display() {
        System.out.println("Файл: " + name + ", Размер: " + size);
    }

    @Override
    public int getSize() {
        return size;
    }
}

class Directory extends FileSystemComponent {
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }
    
    public void addComponent(FileSystemComponent component) {
        if (!components.contains(component)) {
            components.add(component);
        }
    }
    
    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }
    
    @Override
    public void display() {
        System.out.println("Папка: " + name);
        for (FileSystemComponent component : components) {
            component.display();
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

public class modul10homework {
    public static void main(String[] args) {
        Directory root = new Directory("Корень");
        Directory folder1 = new Directory("Папка 1");
        Directory folder2 = new Directory("Папка 2");

        File file1 = new File("Файл 1", 100);
        File file2 = new File("Файл 2", 200);
        File file3 = new File("Файл 3", 300);

        folder1.addComponent(file1);
        folder1.addComponent(file2);

        folder2.addComponent(file3);

        root.addComponent(folder1);
        root.addComponent(folder2);

        root.display();
        System.out.println("Общий размер: " + root.getSize());
    }
}
