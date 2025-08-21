import java.io.*;
import java.util.*;

// ======================== GAME ENGINE ========================
public class GameEngine {
    private User user;
    private Scanner scanner;
    private final String saveFile = "savegame.dat";

    public GameEngine() {
        scanner = new Scanner(System.in);
        user = FileManager.loadUser(saveFile);

        if (user == null) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            user = new User(username);
        }
    }

    public void start() {
        System.out.println("Welcome, " + user.getUsername() + "! The game has started.");
        boolean running = true;

        while (running) {
            handleTurn();
            System.out.println("\nDo you want to continue playing? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("no")) {
                running = false;
                FileManager.saveUser(user, saveFile);
                System.out.println("Thanks for playing! Final score: " + user.getScore());
            }
        }
    }

    public void handleTurn() {
        System.out.println("\n--- Turn Menu ---");
        System.out.println("1. Check Pets (not implemented yet)");
        System.out.println("2. Dummy Add Score");
        System.out.print("Choose an action: ");

        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                System.out.println("Feature for pets coming soon!");
                break;
            case 2:
                user.increaseScore(10);
                System.out.println("Score increased! Current score: " + user.getScore());
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.start();
    }
}

// ======================== USER ========================
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private int score;

    public User(String username) {
        this.username = username;
        this.score = 0;
    }

    public String getUsername() { return username; }
    public int getScore() { return score; }
    public void increaseScore(int value) { score += value; }
}

// ======================== FILE MANAGER ========================
class FileManager {
    public static void saveUser(User user, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(user);
            System.out.println("Game saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public static User loadUser(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            User user = (User) in.readObject();
            System.out.println("Game loaded from " + filename);
            return user;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No save file found, starting fresh.");
            return null;
        }
    }
}
