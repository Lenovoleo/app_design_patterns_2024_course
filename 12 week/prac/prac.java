import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Event {
    int id;
    String name;
    String date;
    String location;

    public Event(int id, String name, String date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    @Override
    public String toString() {
        return id + ". " + name + " (" + date + ", " + location + ")";
    }
}

class User {
    int id;
    String name;
    String role; 

    public User(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}

class Booking {
    int id;
    User user;
    Event event;

    public Booking(int id, User user, Event event) {
        this.id = id;
        this.user = user;
        this.event = event;
    }

    @Override
    public String toString() {
        return id + ". " + user.name + " забронировал " + event.name;
    }
}

public class prac {
    static List<Event> events = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();
        mainMenu();
    }

    static void seedData() {
        events.add(new Event(1, "Наурыз Мейрамы", "2024-03-22", "Алматы"));
        events.add(new Event(2, "Концерт Димаша", "2024-06-15", "Астана"));
        users.add(new User(1, "Ержан", "Пользователь"));
        users.add(new User(2, "Айгерим", "Администратор"));
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n1. Просмотр мероприятий");
            System.out.println("2. Бронирование мероприятия");
            System.out.println("3. Отмена бронирования");
            System.out.println("4. Управление мероприятиями (для администратора)");
            System.out.println("5. Просмотр всех бронирований (для администратора)");
            System.out.println("6. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewEvents();
                case 2 -> bookEvent();
                case 3 -> cancelBooking();
                case 4 -> manageEvents();
                case 5 -> viewBookings();
                case 6 -> {
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    static void viewEvents() {
        System.out.println("\nСписок мероприятий:");
        for (Event event : events) {
            System.out.println(event);
        }
    }

    static void bookEvent() {
        System.out.print("\nВведите ваш ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("Пользователь не найден.");
            return;
        }

        System.out.print("Введите ID мероприятия: ");
        int eventId = scanner.nextInt();
        Event event = findEventById(eventId);
        if (event == null) {
            System.out.println("Мероприятие не найдено.");
            return;
        }

        bookings.add(new Booking(bookings.size() + 1, user, event));
        System.out.println("Мероприятие успешно забронировано!");
    }

    static void cancelBooking() {
        System.out.print("\nВведите ID бронирования: ");
        int bookingId = scanner.nextInt();
        Booking booking = findBookingById(bookingId);
        if (booking == null) {
            System.out.println("Бронирование не найдено.");
            return;
        }

        bookings.remove(booking);
        System.out.println("Бронирование отменено.");
    }

    static void manageEvents() {
        System.out.print("\nВведите ваш ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user == null || !user.role.equals("Администратор")) {
            System.out.println("Доступ запрещён.");
            return;
        }

        System.out.println("1. Добавить мероприятие");
        System.out.println("2. Удалить мероприятие");
        System.out.print("Выберите действие: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            addEvent();
        } else if (choice == 2) {
            deleteEvent();
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    static void viewBookings() {
        System.out.print("\nВведите ваш ID: ");
        int userId = scanner.nextInt();
        User user = findUserById(userId);
        if (user == null || !user.role.equals("Администратор")) {
            System.out.println("Доступ запрещён.");
            return;
        }

        System.out.println("\nСписок бронирований:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    static void addEvent() {
        System.out.print("Введите название мероприятия: ");
        scanner.nextLine(); 
        String name = scanner.nextLine();
        System.out.print("Введите дату мероприятия: ");
        String date = scanner.nextLine();
        System.out.print("Введите место проведения: ");
        String location = scanner.nextLine();

        events.add(new Event(events.size() + 1, name, date, location));
        System.out.println("Мероприятие добавлено.");
    }

    static void deleteEvent() {
        System.out.print("Введите ID мероприятия: ");
        int eventId = scanner.nextInt();
        Event event = findEventById(eventId);
        if (event == null) {
            System.out.println("Мероприятие не найдено.");
            return;
        }

        events.remove(event);
        System.out.println("Мероприятие удалено.");
    }

    static User findUserById(int id) {
        return users.stream().filter(u -> u.id == id).findFirst().orElse(null);
    }

    static Event findEventById(int id) {
        return events.stream().filter(e -> e.id == id).findFirst().orElse(null);
    }

    static Booking findBookingById(int id) {
        return bookings.stream().filter(b -> b.id == id).findFirst().orElse(null);
    }
}
