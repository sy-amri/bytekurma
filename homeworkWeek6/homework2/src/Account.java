// Manas Ismail Abdylas, 22008600

public class Account{
    private Budget budget;
    private Expenses actualExpenses;
    private double currentBalance;
    private String accountNumber;

    public Account(){
        this.budget = new Budget();
        this.actualExpenses = new Expenses();
    }

    public Account(Budget budget, Expenses actualExpenses, double currentBalance, String accountNumber) {
        this.budget = budget;
        this.actualExpenses = actualExpenses;
        this.currentBalance = currentBalance;
        this.accountNumber = accountNumber;
    }

    public void setBudget(Budget budget){
        this.budget = budget;
    }

    public void setActualExpenses(Expenses actualExpenses) {
        this.actualExpenses = actualExpenses;
    }

    public void setCurrentBalance(double currentBalance){
        this.currentBalance = currentBalance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private Budget getBudget(){
        return budget;
    }

    public Expenses getActualExpenses() {
        return actualExpenses;
    }

    public double getCurrentBalance(){
        return currentBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
