public class Main {
    public static void main(String[] args) {
        System.out.println("This is Byte Kurma!");

        Expense expenseTester = new Expense();
        Account accountTester = new Account();
        User userTester = new User();
        Budget budgetTester = new Budget();

        Accounts accounts = new Accounts();
        Users users = new Users();

        // Testing methods in Budget Class
        budgetTester.addBudget(expenseTester);
        budgetTester.removeBudget(expenseTester);
        budgetTester.printBudget();
        budgetTester.findExpense("description");

        // Testing methods in Accounts Class
        accounts.addAccount(accountTester);
        accounts.removeAccount(accountTester);
        accounts.printAccount();
        accounts.findAccount("150732227823");

        // Testing methods in Users Class
        users.addUser(userTester);
        users.removeUser(userTester);
        users.printUser();
        users.findUser("3526732");
    }
}
