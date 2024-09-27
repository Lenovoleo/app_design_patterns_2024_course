public class modul3homework {
    
}


// Произведите корректную (правильную) по вашему мнению реализацию с применением принципа DRY:

// Использование параметризованных методов

class Error{
    public void printMessage(String nameMessage, String message){
        System.out.println(nameMessage + ": " + message );
    }
    public void LogError(String message){
        printMessage("ERROR", message);
    }
    public void LogWarning(String message){
        printMessage("WARNING", message);
    }
    public void LogInfo(String message){
        printMessage("INFO", message);
    }
}

// Использование общих конфигурационных настроек

class Config{
    public static final String CONNECTION_STRING = "Server=myServer;Database=myDb;UserId=myUser;Password=myPass;";
}

class DatabaseService{
    public void Connect(){
        String connectionString = Config.CONNECTION_STRING;
        // Логика подключения к базе данных
    }
}

class LoggingService{
    public void Log(String message){
        String connectionString = Config.CONNECTION_STRING;
        // Логика записи лога в базу данных
    }
}

// Произведите корректную (правильную) по вашему мнению реализацию с применением принципа :

// Избегание ненужного вложения кода

class Numbers{
    public void ProcessNumbers(int numbers[]){
        if(numbers != null && numbers.length > 0){
            for (var number : numbers) {
                if(number > 0){
                    System.out.println(number);
                }
            }
        }
    }
}

// Избегание ненужного использования LINQ
class PositiveNumbers{
    public void PrintPositiveNumbers(int numbers[]){
        for(int number : numbers){
            if(number > 0){
                System.out.println(number);
            }
        }
    }
}

// Избегание избыточного использования исключений

class DivideByZero{
    public int Divide(int a ,int b){
        if(b == 0){
            System.out.println("Error");
            return 0;
        }else{
            return a/b;
        }
    }
}

// Произведите корректную (правильную) по вашему мнению реализацию с применением принципа YAGNI:

// Создание многофункционального класса

class User{
    String Name;
    String Email;
    String Address;

    public void SaveToDatabase(){

    }
}

// Добавление ненужных настроек или конфигураций

class FileReader{
    public void ReadFile(String filePath){
        // Reading with buffer
    }
    public void ReadFileWithoutBuffer(String filePath){
        // Reading without buffer
    }
}

// Добавление ненужных методов и функций

interface ReportGenerator{
    public void GenerateReport();
}