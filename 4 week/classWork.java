// public class classWork {
//     public static void main(String[] args) {
//         DocumentCreator creator = new ReportCreator();
//         Document report = creator.CreateDocument();
//         report.Open();
//     }
// }

// interface Document {
//     public void Open();
// }

// class Report implements Document {
//     @Override
//     public void Open() {}
// }

// class Resume implements Document {
//     @Override
//     public void Open() {}
// }

// class Letter implements Document {
//     @Override
//     public void Open() {}
// }

// abstract class DocumentCreator {
//     abstract Document CreateDocument();
// }

// class ReportCreator extends DocumentCreator {
//     @Override
//     public Document CreateDocument() {
//         return new Report();
//     }
// }

// class ResumeCreator extends DocumentCreator {
//     @Override
//     public Document CreateDocument() {
//         return new Resume();
//     }
// }

// class LetterCreator extends DocumentCreator {
//     @Override
//     public Document CreateDocument() {
//         return new Letter();
//     }
// }


public class classWork {
    public static void main(String[] args) {

        Document document1 = DocumentCreator.createDocument(DocumentType.REPORT);
        document1.Open();


        Document document2 = DocumentCreator.createDocument(DocumentType.RESUME);
        document2.Open();

     
        Document document3 = DocumentCreator.createDocument(DocumentType.LETTER);
        document3.Open();
    }
}

interface Document {
    public void Open();
}

class Report implements Document {
    @Override
    public void Open() {
        System.out.println("Opening Report document...");
    }
}

class Resume implements Document {
    @Override
    public void Open() {
        System.out.println("Opening Resume document...");
    }
}

class Letter implements Document {
    @Override
    public void Open() {
        System.out.println("Opening Letter document...");
    }
}
class Invoice implements Document{
    @Override
    public void Open() {
        System.out.println("Opening Letter document...");
    }
}

enum DocumentType {
    REPORT,
    RESUME,
    LETTER,
    INVOICE
}

class DocumentCreator {
    public static Document createDocument(DocumentType type) {
        switch (type) {
            case REPORT:
                return new Report();
            case RESUME:
                return new Resume();
            case LETTER:
                return new Letter();
            case INVOICE:
                return new Invoice();
            default:
                throw new IllegalArgumentException("Invalid document type");
        }
    }
}