import java.util.*;


class AudioSystem{
    public void TurnOn(){
        System.out.println("Audio on");
    }

    public void SetVolume(int level){
        System.out.println("Audio vol is "+ level);
    }
    public void TurnOff(){
        System.out.println("Audio off");
    }
}


class VideoProjector{
    public void TurnOn(){
        System.out.println("Video projector is turned on");
    }

    public void SetResolution(String resolution){
        System.out.println("Video resolution is "+ resolution);
    }

    public void TurnOff(){
        System.out.println("Video projector is turned off");
    }
}

class LightingSystem{
    public void TurnOn(){
        System.out.println("Lights are turned on");
    }

    public void SetBrightness(int level){
        System.out.println("\"Lights brightness is set to "+ level);
    }

    public void TurnOff(){
        System.out.println("Lights are turned off.");
    }
}

class HomeTheaterFacade{
    private AudioSystem audioSystem;
    private VideoProjector videoProjector;
    private LightingSystem lightingSystem;

    HomeTheaterFacade(AudioSystem audioSystem, VideoProjector videoProjector, LightingSystem lightingSystem){
        this.audioSystem = audioSystem;
        this.videoProjector = videoProjector;
        this.lightingSystem = lightingSystem;
    }

    public void StartMovie(){
        System.out.println("Preparing to start the movie...");
        lightingSystem.TurnOn();
        lightingSystem.SetBrightness(5);
        audioSystem.TurnOn();
        audioSystem.SetVolume(8);
        videoProjector.TurnOn();
        videoProjector.SetResolution("HD");
        System.out.println("Movie ended.");
    }

    public void EndMovie(){
        System.out.println("Shutting down movie...");
        videoProjector.TurnOff();

        audioSystem.TurnOff();

        lightingSystem.TurnOff();

        System.out.println("Movie ended");
    }
}

// public class modul10lab {

//     public static void main(String[] args) {
//         AudioSystem audio = new AudioSystem();
//         VideoProjector video = new VideoProjector();
//         LightingSystem lights = new LightingSystem();

//         HomeTheaterFacade homeTheater = new HomeTheaterFacade(audio, video, lights);

//         homeTheater.StartMovie();
//         System.out.println();
//         homeTheater.EndMovie();
//     }
// }



abstract class FileSystemComponent{
    protected String name;

    FileSystemComponent(String name){
        this.name = name;
    }

    abstract void Display(int depth);
    abstract void Add(FileSystemComponent component);

    abstract void Remove(FileSystemComponent component);

    abstract FileSystemComponent GetChild(int index);
}

class File extends FileSystemComponent{
    File(String name){
        super(name);
    }

    @Override
    public void Display(int depth){
        System.out.println("-".repeat(depth) + "File: " + name);

    }
    @Override
    void Add(FileSystemComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    void Remove(FileSystemComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    FileSystemComponent GetChild(int index) {
        throw new UnsupportedOperationException();
    }


}

class Directory extends FileSystemComponent{
    private List<FileSystemComponent> children = new ArrayList<FileSystemComponent>();


    Directory(String name){
        super(name);
    }

    @Override
    public void Add(FileSystemComponent component){
        children.add(component);
    }

    @Override
    public void Remove(FileSystemComponent component){
        children.remove(component);
    }

    @Override
    public FileSystemComponent GetChild(int index){
        return children.get(index);
    }

    @Override
    public void Display(int depth){
        System.out.println("-".repeat(depth) + "Directory: " + name);

        for (var component : children) {
            component.Display(depth + 2);
        }
    }
}

public class modul10lab {

    public static void main(String[] args) {
        Directory root = new Directory("Root");
        File file1 = new File("File1.txt");
        File file2 = new File("File2.txt");

        Directory subDir = new Directory("SubDirectory");
        File suFile1 = new File("Subfile1.txt");

        root.Add(file1);
        root.Add(file2);
        subDir.Add(suFile1);
        root.Add(subDir);

        root.Display(1);
    }
}