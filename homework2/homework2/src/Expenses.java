// Ahmad Afif Danial bin Azhari, 22009264

import java.util.ArrayList;

public class Expenses {
    private ArrayList<Expense> expenses;

    public Expenses() {
        this.expenses = new ArrayList<>();
    }

    public Expenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public void setExpenses(ArrayList<Expense> expenses){
        this.expenses = expenses;
    }

    public ArrayList<Expense> getExpenses(){
        return expenses;
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public void removeExpense(Expense expense) {
        this.expenses.remove(expense);
    }

    public void printExpense() {
        for (int i = 0; i < expenses.size(); i++){
            Expense expense = expenses.get(i);
            System.out.println(expense.getDescription());
        }
    }

    public Expense findExpense(String description) {
        for (int i = 0; i < expenses.size(); i++){
            Expense expense = expenses.get(i);
            if (expense.getDescription().equals(description)) {
                return expense;
            }
        }
        return null;
    }
}
