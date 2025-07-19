import java.util.Scanner;

public class ATMSystem {
    static String userId = "admin";
    static String userPin = "1234";
    static double balance = 1000.00;
    static StringBuilder transactionHistory = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Login
        System.out.print("Enter User ID: ");
        String inputId = sc.nextLine();
        System.out.print("Enter User PIN: ");
        String inputPin = sc.nextLine();

        if (!inputId.equals(userId) || !inputPin.equals(userPin)) {
            System.out.println("❌ Invalid credentials. Access Denied.");
            return;
        }

        System.out.println("✅ Login Successful!\n");

        // Menu Loop
        while (true) {
            System.out.println("📋 ATM MENU:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose option (1-5): ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> showHistory();
                case 2 -> withdraw(sc);
                case 3 -> deposit(sc);
                case 4 -> transfer(sc);
                case 5 -> {
                    System.out.println("👋 Thank you for using the ATM. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("⚠️ Invalid option. Try again.\n");
            }
        }
    }

    static void showHistory() {
        System.out.println("\n📜 Transaction History:");
        if (transactionHistory.length() == 0) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println(transactionHistory);
        }
    }

    static void withdraw(Scanner sc) {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = sc.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Withdrawn ₹").append(amount).append("\n");
            System.out.println("✅ Withdrawal Successful. New Balance: ₹" + balance);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    static void deposit(Scanner sc) {
        System.out.print("Enter amount to deposit: ₹");
        double amount = sc.nextDouble();
        if (amount > 0) {
            balance += amount;
            transactionHistory.append("Deposited ₹").append(amount).append("\n");
            System.out.println("✅ Deposit Successful. New Balance: ₹" + balance);
        } else {
            System.out.println("⚠️ Invalid amount.");
        }
    }

    static void transfer(Scanner sc) {
        sc.nextLine(); // clear buffer
        System.out.print("Enter recipient name: ");
        String recipient = sc.nextLine();
        System.out.print("Enter amount to transfer: ₹");
        double amount = sc.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.append("Transferred ₹").append(amount)
                              .append(" to ").append(recipient).append("\n");
            System.out.println("✅ Transfer Successful to " + recipient + ". New Balance: ₹" + balance);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }
}
