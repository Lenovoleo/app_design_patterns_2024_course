import java.util.Scanner;

public class modul05prac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        System.out.println("Choose one document:\n1.Report \n2.Resume \n3.Letter \n4.Invoice");
        choice = sc.nextInt();
        Document doc = null;
        switch (choice) {
            case 1:
                doc = new ReportCreator().CreteDocument();
                break;
            case 2:
            doc = new ResumeCreator().CreteDocument();
            break;
            case 3:
            doc = new LetterCreator().CreteDocument();
            break;
            case 4:
            doc = new InvoiceCreator().CreteDocument();
            break;
            default:
            System.out.println("Error number");
                break;
        }

        if(doc != null){
            doc.Open();
        }
    }
}

interface Document{
    void Open();
}

class Report implements Document{
    @Override
    public void Open(){
        System.out.println("Opened Report");
    }
}
class Resume implements Document{
    @Override
    public void Open(){
        System.out.println("Opened Resume");

    }
}
class Letter implements Document{
    @Override
    public void Open(){
        System.out.println("Opened Letter");

    }
}

class Invoice implements Document{
    @Override
    public void Open(){
        System.out.println("Opened Invoice");

    }
}


abstract class DocumentCreator{
    abstract Document CreteDocument();
}

class ReportCreator extends DocumentCreator{
    @Override
    public Document CreteDocument(){
        return new Report();
    }
}

class ResumeCreator extends DocumentCreator{
    @Override
    public Document CreteDocument(){
        return new Resume();
    }
}


class LetterCreator extends DocumentCreator{
    @Override
    public Document CreteDocument(){
        return new Letter();
    }
}

class InvoiceCreator extends DocumentCreator{
    @Override
    public Document CreteDocument(){
        return new Invoice();
    }
}