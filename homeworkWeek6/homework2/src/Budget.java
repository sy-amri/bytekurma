// Ahmad Afif Danial bin Azhari, 22009264

public class Budget {
    private Expenses plannedExpenses;

    public Budget() {
        this.plannedExpenses = new Expenses();
    }

    public Budget(Expenses plannedExpenses) {
        this.plannedExpenses = plannedExpenses;
    }

    public void setPlannedExpenses(Expenses plannedExpenses) {
        this.plannedExpenses = plannedExpenses;
    }

    public Expenses getPlannedExpenses() {
        return this.plannedExpenses;
    }

    public void addBudget(Expense expense) {
        plannedExpenses.addExpense(expense);
    }

    public void removeBudget(Expense expense) {
        plannedExpenses.removeExpense(expense);
    }

    public void printBudget() {
        plannedExpenses.printExpense();
    }

    public Expense findBudget(String description) {
        Expense expense = plannedExpenses.findExpense(description);
        return expense;
    }
}
