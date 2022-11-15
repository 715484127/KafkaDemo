package org.example.beans;

public class Order {
    long id;
    int amount;
    public Order(long id,int amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
