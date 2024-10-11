import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class modul06prac {
    public static void main(String[] args) {
        // Singleton Pattern 
        Logger logger = Logger.getInstance();
        logger.log("Starting application...", Level.INFO);
        logger.setLogLevel(Level.WARNING);
        logger.log("Warning message...", Level.WARNING);
        logger.log("Error message...", Level.SEVERE);

        // Builder Pattern 
        ReportStyle style = new ReportStyle("lightblue", "black", 14);
        ReportDirector director = new ReportDirector();
        TextReportBuilder textBuilder = new TextReportBuilder();
        HtmlReportBuilder htmlBuilder = new HtmlReportBuilder();
        Report textReport = director.constructReport(textBuilder, style);
        Report htmlReport = director.constructReport(htmlBuilder, style);
        textReport.export("text");
        htmlReport.export("html");

        // Prototype Pattern 
        Weapon sword = new Weapon(10);
        Armor plate = new Armor(5);
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Fireball"));
        skills.add(new Skill("Heal"));
        Character warrior = new Character(100, 15, 10, 5, sword, plate, skills);
        Character mage = (Character) warrior.clone();
        mage.weapon = new Weapon(5);
        mage.skills.remove(0);
        mage.skills.add(new Skill("Ice Blast"));
        System.out.println("Warrior:");
        System.out.println("Weapon damage: " + warrior.weapon.damage);
        System.out.println("Skills: " + warrior.skills);
        System.out.println("Mage:");
        System.out.println("Weapon damage: " + mage.weapon.damage);
        System.out.println("Skills: " + mage.skills);
    }
}


// Singleton Pattern 
class Logger {
    private static Logger instance;
    private File logFile;
    private Level logLevel;

    private Logger() {
        try {
            this.logFile = new File("logs.txt");
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            this.logLevel = Level.INFO;
        } catch (IOException e) {
            System.err.println("Error creating log file: " + e.getMessage());
        }
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

    public void log(String message, Level level) {
        if (level.intValue() >= this.logLevel.intValue()) {
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(String.format("[%s] %s\n", level, message));
            } catch (IOException e) {
                System.err.println("Error writing to log file: " + e.getMessage());
            }
        }
    }

    public void setLogLevel(Level level) {
        this.logLevel = level;
    }
}

// Builder Pattern 
interface IReportBuilder {
    void setHeader(String header);
    void setContent(String content);
    void setFooter(String footer);
    void addSection(String sectionName, String sectionContent);
    void setStyle(ReportStyle style);
    Report getReport();
}

class ReportStyle {
    String backgroundColor;
    String fontColor;
    int fontSize;

    public ReportStyle(String backgroundColor, String fontColor, int fontSize) {
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
    }
}

class Report {
    String header;
    String content;
    String footer;
    List<String> sections = new ArrayList<>();
    ReportStyle style;

    public Report(String header, String content, String footer, List<String> sections, ReportStyle style) {
        this.header = header;
        this.content = content;
        this.footer = footer;
        this.sections = sections;
        this.style = style;
    }

    public void export(String format) {
        if (format.equalsIgnoreCase("text")) {
            System.out.println("Text Report:");
            System.out.println(header);
            System.out.println(content);
            for (String section : sections) {
                System.out.println(section);
            }
            System.out.println(footer);
        } else if (format.equalsIgnoreCase("html")) {
            System.out.println("HTML Report:");
            System.out.println("<!DOCTYPE html>");
            System.out.println("<html>");
            System.out.println("<head>");
            System.out.println("<style>");
            System.out.println("body { background-color: " + style.backgroundColor + "; color: " + style.fontColor + "; font-size: " + style.fontSize + "px; }");
            System.out.println("</style>");
            System.out.println("</head>");
            System.out.println("<body>");
            System.out.println("<h1>" + header + "</h1>");
            System.out.println("<p>" + content + "</p>");
            for (String section : sections) {
                System.out.println("<h2>" + section + "</h2>");
            }
            System.out.println("<p>" + footer + "</p>");
            System.out.println("</body>");
            System.out.println("</html>");
        } else if (format.equalsIgnoreCase("pdf")) {
            System.out.println("PDF Report (Not implemented)");
        } else {
            System.out.println("Unsupported format.");
        }
    }
}

class TextReportBuilder implements IReportBuilder {
    Report report;

    public TextReportBuilder() {
        report = new Report("", "", "", new ArrayList<>(), null);
    }

    @Override
    public void setHeader(String header) {
        report.header = header;
    }

    @Override
    public void setContent(String content) {
        report.content = content;
    }

    @Override
    public void setFooter(String footer) {
        report.footer = footer;
    }

    @Override
    public void addSection(String sectionName, String sectionContent) {
        report.sections.add(sectionName + ": " + sectionContent);
    }

    @Override
    public void setStyle(ReportStyle style) {
        report.style = style;
    }

    @Override
    public Report getReport() {
        return report;
    }
}

class HtmlReportBuilder implements IReportBuilder {
    Report report;

    public HtmlReportBuilder() {
        report = new Report("", "", "", new ArrayList<>(), null);
    }

    @Override
    public void setHeader(String header) {
        report.header = header;
    }

    @Override
    public void setContent(String content) {
        report.content = content;
    }

    @Override
    public void setFooter(String footer) {
        report.footer = footer;
    }

    @Override
    public void addSection(String sectionName, String sectionContent) {
        report.sections.add(sectionName + ": " + sectionContent);
    }

    @Override
    public void setStyle(ReportStyle style) {
        report.style = style;
    }

    @Override
    public Report getReport() {
        return report;
    }
}

class ReportDirector {
    public Report constructReport(IReportBuilder builder, ReportStyle style) {
        builder.setHeader("Report Header");
        builder.setContent("Report Content");
        builder.setFooter("Report Footer");
        builder.addSection("Section 1", "Section 1 Content");
        builder.addSection("Section 2", "Section 2 Content");
        builder.setStyle(style);
        return builder.getReport();
    }
}

// Prototype Pattern 
interface ICloneable {
    Object clone();
}

class Weapon implements ICloneable {
    int damage;

    public Weapon(int damage) {
        this.damage = damage;
    }

    @Override
    public Object clone() {
        return new Weapon(this.damage);
    }
}

class Armor implements ICloneable {
    int defense;

    public Armor(int defense) {
        this.defense = defense;
    }

    @Override
    public Object clone() {
        return new Armor(this.defense);
    }
}

class Skill implements ICloneable {
    String name;

    public Skill(String name) {
        this.name = name;
    }

    @Override
    public Object clone() {
        return new Skill(this.name);
    }
}

class Character implements ICloneable {
    int health;
    int strength;
    int agility;
    int intelligence;
    Weapon weapon;
    Armor armor;
    List<Skill> skills = new ArrayList<>();

    public Character(int health, int strength, int agility, int intelligence, Weapon weapon, Armor armor, List<Skill> skills) {
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.weapon = weapon;
        this.armor = armor;
        this.skills = skills;
    }

    @Override
    public Object clone() {
        Character clone = new Character(this.health, this.strength, this.agility, this.intelligence, (Weapon) this.weapon.clone(), (Armor) this.armor.clone(), new ArrayList<>());
        for (Skill skill : this.skills) {
            clone.skills.add((Skill) skill.clone());
        }
        return clone;
    }
}

