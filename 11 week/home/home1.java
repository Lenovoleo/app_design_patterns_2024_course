import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Hotel {
    String name;
    String location;
    Map<String, Boolean> rooms = new HashMap<>(); // Room number and availability

    Hotel(String n, String loc) {
        name = n;
        location = loc;
    }

    void addRoom(String roomNumber) {
        rooms.put(roomNumber, true);
    }

    boolean isRoomAvailable(String roomNumber) {
        return rooms.containsKey(roomNumber) && rooms.get(roomNumber);
    }

    void bookRoom(String roomNumber) {
        if (isRoomAvailable(roomNumber)) {
            rooms.put(roomNumber, false);
            System.out.println("Room " + roomNumber + " in " + name + " is booked.");
        } else {
            System.out.println("Room " + roomNumber + " is not available.");
        }
    }

    void releaseRoom(String roomNumber) {
        if (rooms.containsKey(roomNumber)) {
            rooms.put(roomNumber, true);
            System.out.println("Room " + roomNumber + " in " + name + " is now available.");
        }
    }
}

class BookingService {
    void bookRoom(Hotel hotel, String roomNumber) {
        hotel.bookRoom(roomNumber);
    }

    void releaseRoom(Hotel hotel, String roomNumber) {
        hotel.releaseRoom(roomNumber);
    }
}

class PaymentService {
    void processPayment(String user, double amount) {
        System.out.println("Payment of $" + amount + " processed for " + user + ".");
    }
}

class NotificationService {
    void sendNotification(String user, String message) {
        System.out.println("Notification to " + user + ": " + message);
    }
}

class UserManagementService {
    List<String> users = new ArrayList<>();

    void registerUser(String user) {
        users.add(user);
        System.out.println(user + " registered successfully.");
    }

    boolean isUserRegistered(String user) {
        return users.contains(user);
    }
}

class UIComponent {
    void interact() {
        UserManagementService userManager = new UserManagementService();
        BookingService bookingService = new BookingService();
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new NotificationService();

        Hotel hotel = new Hotel("Grand Hotel", "New York");
        hotel.addRoom("101");
        hotel.addRoom("102");

        String user = "Alice";
        userManager.registerUser(user);

        if (userManager.isUserRegistered(user)) {
            bookingService.bookRoom(hotel, "101");
            paymentService.processPayment(user, 200.0);
            notificationService.sendNotification(user, "Your room is booked!");
        }

        hotel.bookRoom("102"); // Another booking example
        hotel.releaseRoom("101");
    }
}

public class home1 {
    public static void main(String[] args) {
        UIComponent ui = new UIComponent();
        ui.interact();
    }
}