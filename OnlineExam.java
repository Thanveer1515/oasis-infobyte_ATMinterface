import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExam {
    static String userId = "admin";
    static String password = "1234";
    static String name = "Student";
    static boolean examSubmitted = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Login
        System.out.print("Enter User ID: ");
        String inputId = sc.nextLine();
        System.out.print("Enter Password: ");
        String inputPass = sc.nextLine();

        if (!inputId.equals(userId) || !inputPass.equals(password)) {
            System.out.println("❌ Login Failed. Exiting...");
            return;
        }

        System.out.println("\n✅ Login Successful! Welcome, " + name);

        while (true) {
            System.out.println("\n📋 MENU:");
            System.out.println("1. Update Profile & Password");
            System.out.println("2. Take Exam");
            System.out.println("3. Logout");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> updateProfile(sc);
                case 2 -> takeExam(sc);
                case 3 -> {
                    System.out.println("👋 Logged out. Goodbye!");
                    return;
                }
                default -> System.out.println("⚠️ Invalid choice.");
            }
        }
    }

    static void updateProfile(Scanner sc) {
        System.out.print("Enter new name: ");
        name = sc.nextLine();
        System.out.print("Enter new password: ");
        password = sc.nextLine();
        System.out.println("✅ Profile updated successfully.");
    }

    static void takeExam(Scanner sc) {
        System.out.println("🧠 Exam Started! You have 60 seconds to complete.\n");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                examSubmitted = true;
                System.out.println("\n⏰ Time's up! Exam auto-submitted.");
                timer.cancel();
            }
        }, 60000); // 60 seconds

        int score = 0;

        // Question 1
        System.out.println("1. What is the capital of India?");
        System.out.println("A. Mumbai\nB. Delhi\nC. Chennai\nD. Kolkata");
        System.out.print("Answer: ");
        String ans1 = sc.nextLine();
        if (!examSubmitted && ans1.equalsIgnoreCase("B")) score++;

        // Question 2
        System.out.println("\n2. Java is a...");
        System.out.println("A. Snake\nB. Database\nC. Programming Language\nD. Browser");
        System.out.print("Answer: ");
        String ans2 = sc.nextLine();
        if (!examSubmitted && ans2.equalsIgnoreCase("C")) score++;

        // Question 3
        System.out.println("\n3. Shortcut to compile a Java file?");
        System.out.println("A. java\nB. javac\nC. javadoc\nD. jar");
        System.out.print("Answer: ");
        String ans3 = sc.nextLine();
        if (!examSubmitted && ans3.equalsIgnoreCase("B")) score++;

        if (!examSubmitted) {
            timer.cancel(); // Stop the timer if submitted manually
            System.out.println("\n✅ Exam Submitted!");
        }

        System.out.println("🎯 Your Score: " + score + " out of 3");
    }
}
