import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful");
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
        } else if (amount > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice = 0;

        do {
            System.out.println("\nATM MACHINE");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdrawMoney();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (choice != 4);

        scanner.close();
    }

    private void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");

        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount");
            scanner.next();
            return;
        }

        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private void depositMoney() {
        System.out.print("Enter amount to deposit: ");

        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount");
            scanner.next();
            return;
        }

        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void checkBalance() {
        System.out.println("Current Balance: " + account.getBalance());
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(5000);
        ATM atm = new ATM(userAccount);
        atm.showMenu();
    }
}