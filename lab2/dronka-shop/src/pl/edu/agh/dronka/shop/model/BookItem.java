package pl.edu.agh.dronka.shop.model;

public class BookItem extends Item {
    int pagesNumber;
    boolean hardCover;

    public BookItem(String name, Category category, int price, int quantity, int pagesNumber, boolean hardCover) {
        super(name, category, price, quantity);
    this.pagesNumber=pagesNumber;
    this.hardCover=hardCover;
    }
    public BookItem(){}

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public void setHardCover(boolean hardCover) {
        this.hardCover = hardCover;
    }
    public int getPagesNumber() {
        return pagesNumber;
    }

    public boolean isHardCover() {
        return hardCover;
    }

}
