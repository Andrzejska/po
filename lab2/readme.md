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
**b)ElectronicItem**
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
**c)SportItem**
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
**e)FoodItem**
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
2.W metodzie **readItems** klasy **shopProvider** zostalo dodane wsczytywanie dodatkowych danych w zależnośći od kategorii.
```java
private static List<Item> readItems(CSVReader reader, Category category) {
        List<Item> items = new ArrayList<>();
        
        try {
            reader.parse();
            List<String[]> data = reader.getData();

            for (String[] dataLine : data) {

                String name = reader.getValue(dataLine, "Nazwa");
                int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
                int quantity = Integer.parseInt(reader.getValue(dataLine,
                        "Ilość"));

                boolean isPolish = Boolean.parseBoolean(reader.getValue(
                        dataLine, "Tanie bo polskie"));
                boolean isSecondhand = Boolean.parseBoolean(reader.getValue(
                        dataLine, "Używany"));
                Item item;
                switch (category) {

                    case BOOKS:
                        item = new BookItem(name, category, price, quantity, (int)
                                Integer.parseInt(reader.getValue(dataLine, "Liczba stron")),
                                Boolean.parseBoolean(reader.getValue(dataLine, "Twarda oprawa")));
                        break;
                    case ELECTRONICS:
                        item = new ElectronicItem(name, category, price, quantity,
                                Boolean.parseBoolean(reader.getValue(dataLine, "Mobilny")),
                                Boolean.parseBoolean(reader.getValue(dataLine, "Gwarancja")));
                        break;
                    case FOOD:
                        String dataS = reader.getValue(dataLine, "Data");
                        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = formatData.parse(dataS);
                        item = new FoodItem(name, category, price, quantity, date);
                        break;
                    case MUSIC:
                        item = new MusicItem(name, category, price, quantity, MusicStyle.parseStyle(reader.getValue(dataLine, "Styl")),
                                Boolean.parseBoolean(reader.getValue(dataLine, "Video")));
                        break;
                    case SPORT:
                    	item=new SportItem(name,category,price,quantity);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + category);
                }
                item.setPolish(isPolish);
                item.setSecondhand(isSecondhand);

                items.add(item);

            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return items;
    }
```
3. W metodzie **getPropertiesMap** klasy **PropertiesHelper** zostalo dodane wyświetłianie dodatkowych danych w zależnośći od kategorii
```java
public static Map<String, Object> getPropertiesMap(Item item) {
		Map<String, Object> propertiesMap = new LinkedHashMap<>();
		
		propertiesMap.put("Nazwa", item.getName());
		propertiesMap.put("Cena", item.getPrice());
		propertiesMap.put("Kategoria", item.getCategory().getDisplayName()); 
		propertiesMap.put("Ilość", Integer.toString(item.getQuantity()));
		propertiesMap.put("Tanie bo polskie", item.isPolish());
		propertiesMap.put("Używany", item.isSecondhand());
		if (item instanceof BookItem) {
			propertiesMap.put("Liczba stron", ((BookItem)item).getPagesNumber());
			propertiesMap.put("Twarda oprawa", ((BookItem)item).isHardCover());
		} else if (item instanceof ElectronicItem){
			propertiesMap.put("Mobilny", ((ElectronicItem) item).isMobile());
			propertiesMap.put("Gwarancja", ((ElectronicItem)item).isGuaranty());
		} else if(item instanceof FoodItem){
			propertiesMap.put("Data przydatności", ((FoodItem) item).getDate());
		} else if (item instanceof MusicItem){
			propertiesMap.put("Gatunek muzyczny", ((MusicItem) item).getMusicStyle());
			propertiesMap.put("Czy jest video", ((MusicItem) item).isVideoConnected());
		}
		return propertiesMap;
	}
```
4.Demonstracja zmian :
![Image](https://github.com/Andrzejska/OOD/blob/master/lab2/img/bookItem.png)
![Image](https://github.com/Andrzejska/OOD/blob/master/lab2/img/electronicItem.png)
