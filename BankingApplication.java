// Name: Adit Ghosh
// Course: B.Tech CSE(Full Stack Development)
// Roll Number: 2401350016

import java.util.Scanner;

// Class to represent a bank account
class Account {
    int accountNumber;
    String accountHolderName;
    double balance;
    String email;
    String phoneNumber;

    // constructor to initialize account
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // add money to account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // withdraw money if balance is sufficient
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ". New Balance: " + balance);
        } else {
            System.out.println("Invalid withdraw amount or insufficient balance.");
        }
    }

    // show details of this account
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("--------------------------------------");
    }

    // change email and phone
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated.");
    }
}

// handles user actions and stores accounts
class UserInterface {
    Scanner sc = new Scanner(System.in);
    Account[] accounts = new Account[100];  // fixed size for simplicity
    int accountCount = 0;
    int nextAccountNumber = 1001;

    // create account using given details
    public void createAccount(String name, double amount, String email, String phone) {
        Account acc = new Account(nextAccountNumber, name, amount, email, phone);
        accounts[accountCount] = acc;
        accountCount++;
        System.out.println("Account created with Account Number: " + nextAccountNumber);
        nextAccountNumber++;
    }

    // create account with user input
    public void createAccountInteractive() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume newline
        System.out.print("Enter email address: ");
        String email = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        createAccount(name, amount, email, phone);
    }

    // deposit to account
    public void performDeposit(int accNo, double amount) {
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performDepositInteractive() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        performDeposit(accNo, amount);
    }

    // withdraw from account
    public void performWithdrawal(int accNo, double amount) {
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performWithdrawalInteractive() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        performWithdrawal(accNo, amount);
    }

    // view details
    public void showAccountDetails(int accNo) {
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void showAccountDetailsInteractive() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        showAccountDetails(accNo);
    }

    // update contact info
    public void updateContact(int accNo, String email, String phone) {
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void updateContactInteractive() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new email: ");
        String email = sc.nextLine();
        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();
        updateContact(accNo, email, phone);
    }

    // search account in array
    private Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accountNumber == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    // some predefined test runs
    public void testCases() {
        System.out.println("----- Running Test Cases -----");

        createAccount("John Doe", 1000.0, "john@example.com", "1234567890");
        createAccount("Alice Smith", 2000.0, "alice@example.com", "9876543210");

        performDeposit(1001, 500.0);
        performWithdrawal(1002, 300.0);
        updateContact(1001, "john.doe@newmail.com", "1122334455");

        showAccountDetails(1001);
        showAccountDetails(1002);

        System.out.println("----- Test Cases Completed -----");
    }

    // interactive menu for manual testing
    public void mainMenu() {
        while (true) {
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: createAccountInteractive(); break;
                case 2: performDepositInteractive(); break;
                case 3: performWithdrawalInteractive(); break;
                case 4: showAccountDetailsInteractive(); break;
                case 5: updateContactInteractive(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice, try again.");
            }
        }
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        OOPS.UserInterface ui = new OOPS.UserInterface();

        // default run test cases
        ui.testCases();

        // for manual testing, uncomment this
        // ui.mainMenu();
    }
}
