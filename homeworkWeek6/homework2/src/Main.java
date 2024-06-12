// Syahir Amri bin Mohd Azha, 22007728
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is Byte Kurma!");
        System.out.println();

        // Testing 1
        User userA = new User();
        Accounts accountA = new Accounts();
        userA.setName("A");
        userA.setId("20026723");
        userA.setPassword("hello123");
        userA.setAccounts(accountA);

        Accounts accountB = new Accounts();
        User userB = new User("B", "20112737", "morning123", accountB);

        System.out.println("Testing 1");
        System.out.println(userA.getName() + "'s ID is " + userA.getId());
        System.out.println(userB.getName() + "'s password is " + userB.getPassword());
        System.out.println();


        // Testing 2
        Account firstAcc = new Account();

        Expenses expsTester = new Expenses();
        Budget budgetTester = new Budget();
        Account secondAcc = new Account(budgetTester, expsTester, 12523.20, "167290901728");

        System.out.println("Testing 2");
        userB.getAccounts().addAccount(firstAcc);
        System.out.println("User B has successfully added his first account");
        userB.getAccounts().addAccount(secondAcc);
        System.out.println("User B has successfully added his second account");
        System.out.println();


        // Testing 3
        Expense firstExpense = new Expense();
        firstExpense.setDescription("First");
        Expense secondExpense = new Expense();
        secondExpense.setDescription("Second");

        secondAcc.getActualExpenses().addExpense(firstExpense);
        secondAcc.getActualExpenses().addExpense(secondExpense);
        System.out.println("Testing 3");
        System.out.println("Successfully added two expenses with description 'First' and 'Second' accordingly");
        System.out.println("Checking second expense location");
        System.out.println(secondAcc.getActualExpenses().findExpense("Second"));
        System.out.println("Checking third expense location");
        System.out.println(secondAcc.getActualExpenses().findExpense("Third"));
        System.out.println();

        // Testing 4
        System.out.println("Testing 4");
        System.out.println(secondAcc.getAccountNumber());
        System.out.println("Successfully printed User B's second account number");
        System.out.println();

        // Testing 5
        System.out.println("Testing 5");
        Budget budget = new Budget();
        Expense expense = new Expense(new Date(), 100.0, "Test Expense", "Test Category", "Test Payment Method", "Test Reference", "Test Paid To");
        budget.addBudget(expense);
        budget.printBudget();
        System.out.println();

        // Testing 6
        System.out.println("Testing 6");
        Accounts accounts1 = new Accounts();
        User user1 = new User("John", "U1", "password1", accounts1);

        Accounts accounts2 = new Accounts();
        User user2 = new User("Smith", "U2", "password2", accounts2);

        Accounts accounts3 = new Accounts();
        User user3 = new User("Bob", "U3", "password3", accounts3);

        Users users = new Users();

        users.addUser(user1);
        users.addUser(user2);
        users.addUser(user3);

        System.out.println("Printing All users given:");
        users.printUser();
    }
}
