//Manas Ismail Abdylas, 22008600

package com.example.bytekurma;

public class History {
    private int id;
    private int userId;
    private String date;
    private String category;
    private String type;
    private float amount;

    // Constructor
    public History(String date, String category, String type, float amount) {
        this.date = date;
        this.category = category;
        this.type = type;
        this.amount = amount;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setAmount(float amount){
        this.amount = amount;
    }

    //Getters
    public int getId() {
        return id;
    }

    public int getUserId(){
        return userId;
    }

    public String getDate(){
        return date;
    }

    public String getCategory(){
        return category;
    }

    public String getType(){
        return type;
    }

    public float getAmount(){
        return amount;
    }
}
