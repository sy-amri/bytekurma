// Daniela Adlin binti Razwan, 22008820

import java.util.Date;

public class Expense {
    private Date date;
    private double amount;
    private String description, category, paymentMethod, recipientReference, paidTo;

    public Expense(){
        this.date = new Date();
        this.amount = 0;
    }

    public Expense(Date date, double amount, String description, String category, String paymentMethod, String recipientReference, String paidTo) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.recipientReference = recipientReference;
        this.paidTo = paidTo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setRecipientReference(String recipientReference) {
        this.recipientReference = recipientReference;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory(){
        return category;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }

    public String getRecipientReference(){
        return recipientReference;
    }

    public String getPaidTo(){
        return paidTo;
    }
}
