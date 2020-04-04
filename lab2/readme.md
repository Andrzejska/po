# Laboratorium 2
## Wprowadzanie zmian w istniejącej aplikacji

### 1.Dodatkowe właściwości dla produktów w poszczególnych kategoriach

Rozszerz aplikacje dronka-shop tak, aby na stronie Szczegóły produktu wyświetlała właściwości, specyficzne dla danej kategorii produktów.

1.Napierw byli stworzone następne clasy -> BookItem,ElectronicItem,SportItem,MusicItem,FoodItem ,ktory dziedziczą z klasy Item i zawierają 
poszczególne dodatkowe atrybuty.

```java
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

```
