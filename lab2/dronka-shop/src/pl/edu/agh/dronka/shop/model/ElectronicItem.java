package pl.edu.agh.dronka.shop.model;

import pl.edu.agh.dronka.shop.model.Item;

public class ElectronicItem extends Item {
    public boolean isMobile() {
        return mobile;
    }

    public boolean isGuaranty() {
        return guaranty;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public void setGuaranty(boolean guaranty) {
        this.guaranty = guaranty;
    }

    boolean mobile;
    boolean guaranty;
    public ElectronicItem(String name, Category category, int price, int quantity, boolean mobile, boolean guaranty){
        super(name,category,price,quantity);
        this.mobile=mobile;
        this.guaranty=guaranty;
    }
    public ElectronicItem(){}
}
