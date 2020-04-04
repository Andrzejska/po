package pl.edu.agh.dronka.shop.model;

import pl.edu.agh.dronka.shop.model.Item;

public class SportItem extends Item {
    public SportItem(String name, Category category, int price, int quantity){
        super(name,category,price,quantity);
    }
    public SportItem(){}
}
