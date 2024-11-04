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
        System.out.println(new String('-'+ depth) + "File: "+ name);

    }


}

class Directory extends FileSystemComponent{
    private List<FileSystemComponent> children = new List<FileSystemComponent>();


    Directory(String name){
        super(name);
    }

    @Override
    public void Add(FileSystemComponent component){
        children.Add(component);
    }

    @Override
    public void Remove(FileSystemComponent component){
        children.Remove(component);
    }

    @Override
    public FileSystemComponent GetChild(int index){
        return children[index];
    }

    @Override
    public void Display(int depth){
        System.out.println(new String('-' + depth)+ " Directory: "+ name);
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