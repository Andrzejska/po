# Laboratorium 2
## Wprowadzanie zmian w istniejącej aplikacji

### 1.Dodatkowe właściwości dla produktów w poszczególnych kategoriach
Rozszerz aplikacje dronka-shop tak, aby na stronie Szczegóły produktu wyświetlała właściwości, specyficzne dla danej kategorii produktów.

1.Najpierw byli stworzone następne clasy -> BookItem, ElectronicItem, SportItem, MusicItem, FoodItem, ktore dziedziczą z klasy Item i zawierają poszczególne dodatkowe atrybuty.

**a)BookItem**
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
#### b)ElectronicItem
```java
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
```
#### c)SportItem
```java
package pl.edu.agh.dronka.shop.model;

import pl.edu.agh.dronka.shop.model.Item;

public class SportItem extends Item {
    public SportItem(String name, Category category, int price, int quantity){
        super(name,category,price,quantity);
    }
    public SportItem(){}
}
```
#### d)MusicItem
```java
package pl.edu.agh.dronka.shop.model;

public class MusicItem extends Item{
    MusicStyle musicStyle;

    public MusicStyle getMusicStyle() {
        return musicStyle;
    }

    public boolean isVideoConnected() {
        return videoConnected;
    }

    boolean videoConnected;

    public void setMusicStyle(MusicStyle musicStyle) {
        this.musicStyle = musicStyle;
    }

    public void setVideoConnected(boolean videoConnected) {
        this.videoConnected = videoConnected;
    }

    public MusicItem(String name, Category category, int price, int quantity, MusicStyle musicStyle, boolean videoConnected){
        super(name,category,price,quantity);
        this.musicStyle=musicStyle;
        this.videoConnected=videoConnected;
    }
    public MusicItem(){}
}
```
#### e)FoodItem
```java
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
```
