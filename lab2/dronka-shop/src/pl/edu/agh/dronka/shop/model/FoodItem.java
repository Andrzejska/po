package pl.edu.agh.dronka.shop.model;

import java.util.Date;

public class FoodItem extends Item {
    Date date;

    public FoodItem(String name, Category category, int price, int quantity, Date date) {
        super(name, category, price, quantity);
        this.date = date;
    }

    public FoodItem() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
