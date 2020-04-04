# Laboratorium 2
## Wprowadzanie zmian w istniejącej aplikacji

### 1. Dodatkowe właściwości dla produktów w poszczególnych kategoriach
Rozszerz aplikacje dronka-shop tak, aby na stronie Szczegóły produktu wyświetlała właściwości, specyficzne dla danej kategorii produktów.

1. Najpierw byli stworzone następne klasy -> BookItem, ElectronicItem, SportItem, MusicItem, FoodItem, które dziedziczą z klasy Item i zawierają poszczególne dodatkowe atrybuty.

**a) BookItem**
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
**b) ElectronicItem**
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
**c) SportItem**
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
#### d) MusicItem
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
**e) FoodItem**
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
2. W metodzie **readItems** klasy **shopProvider** zostalo dodane wsczytywanie dodatkowych danych w zależności od kategorii.
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
4. Demonstracja zmian :

![Image](https://github.com/Andrzejska/OOD/blob/master/lab2/img/bookItem.png)
![Image](https://github.com/Andrzejska/OOD/blob/master/lab2/img/electronicItem.png)

### 2. Rozszerzenie panelu
Rozszerz panel wyświetlania produktów tak, aby było możliwe filtrowanie produktów po
właściwościach typu boolean, specyficznych dla danej kategorii.

1. Najpierw był dodany konstructor klasy **ItemFilter** który inicjalizował atrybut **itemSpec** w zależności od kategorii. Dodatkowo została zmieniona metoda **appliesTo** która zwracała wynik również wzależności od kategorii podanego Itema.
```java
package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.*;

public class ItemFilter {
    private Item itemSpec = new Item();

    public ItemFilter(Category category) {
        switch (category) {
            case BOOKS:
                itemSpec = new BookItem();
                break;
            case ELECTRONICS:
                itemSpec = new ElectronicItem();
                break;
            case FOOD:
                itemSpec = new FoodItem();
                break;
            case MUSIC:
                itemSpec = new MusicItem();
                break;
            case SPORT:
                itemSpec = new SportItem();
                break;
        }
    }

    public Item getItemSpec() {
        return itemSpec;
    }

    public boolean appliesTo(Item item) {
        if (itemSpec.getName() != null
                && !itemSpec.getName().equals(item.getName())) {
            return false;
        }
        if (itemSpec.getCategory() != null
                && !itemSpec.getCategory().equals(item.getCategory())) {
            return false;
        }

        // applies filter only if the flag (secondHand) is true)
        if (itemSpec.isSecondhand() && !item.isSecondhand()) {
            return false;
        }

        // applies filter only if the flag (polish) is true)
        if (itemSpec.isPolish() && !item.isPolish()) {
            return false;
        }
        switch (item.getCategory()) {
            case BOOKS:
                // applies filter only if the flag (twarda oprawa) is true)
                if (itemSpec instanceof BookItem && (((BookItem) itemSpec).isHardCover() && !((BookItem) item).isHardCover()))
                    return false;
            case ELECTRONICS:
                // applies filter only if the flag (Gwarancja) is true)
                if (itemSpec instanceof ElectronicItem && ((ElectronicItem) itemSpec).isGuaranty() && !((ElectronicItem) item).isGuaranty())
                    return false;
                // applies filter only if the flag (Mobilny) is true)
                if (itemSpec instanceof ElectronicItem && ((ElectronicItem) itemSpec).isMobile() && !((ElectronicItem) item).isMobile())
                    return false;
            case MUSIC:
                // applies filter only if the flag (Wideo) is true)
                if (itemSpec instanceof MusicItem && ((MusicItem) itemSpec).isVideoConnected() && !((MusicItem) item).isVideoConnected())
                    return false;
        }
        return true;
    }

}
```
2. Potem została metoda **fillProperties** dla klasy **PropertiesPanel** która dla poszczególnych kategorii wyświetla zadane checkboxy i wywoła filtr w zależności od ich zawartości.
```java
  public void fillProperties() {
        removeAll();
        filter= new ItemFilter(shopController.getCurrentCategory());
        filter.getItemSpec().setCategory(shopController.getCurrentCategory());
        add(createPropertyCheckbox("Tanie bo polskie", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                filter.getItemSpec().setPolish(
                        ((JCheckBox) event.getSource()).isSelected());
                shopController.filterItems(filter);
            }
        }));

        add(createPropertyCheckbox("Używany", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                filter.getItemSpec().setSecondhand(
                        ((JCheckBox) event.getSource()).isSelected());
                shopController.filterItems(filter);
            }
        }));
        switch (filter.getItemSpec().getCategory()) {
            case ELECTRONICS:
                add(createPropertyCheckbox("Mobilny", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((ElectronicItem) filter.getItemSpec()).setMobile(
                                ((JCheckBox) e.getSource()).isSelected());
                        shopController.filterItems(filter);
                    }
                }));
				add(createPropertyCheckbox("Gwarancja", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((ElectronicItem) filter.getItemSpec()).setGuaranty(
								((JCheckBox) e.getSource()).isSelected());
						shopController.filterItems(filter);
					}
				}));
				break;
			case BOOKS:
				add(createPropertyCheckbox("Twarda oprawa", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((BookItem) filter.getItemSpec()).setHardCover(
								((JCheckBox) e.getSource()).isSelected());
						shopController.filterItems(filter);
					}
				}));
                break;
            case MUSIC:
				add(createPropertyCheckbox("Wideo", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((MusicItem) filter.getItemSpec()).setVideoConnected(
								((JCheckBox) e.getSource()).isSelected());
						shopController.filterItems(filter);
					}
				}));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + filter.getItemSpec().getCategory());
        }

    }
```

3. Demonstracja zmian :

![Image](https://github.com/Andrzejska/OOD/blob/master/lab2/img/electronicCheckBox.png)
