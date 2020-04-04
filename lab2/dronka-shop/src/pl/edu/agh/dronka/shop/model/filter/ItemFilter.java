package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemFilter {
    private Item itemSpec=new Item();

    public ItemFilter(Category category) {
switch (category){
    case BOOKS:
        itemSpec=new BookItem();
        break;
    case ELECTRONICS:
        itemSpec=new ElectronicItem();
        break;
    case FOOD:
        itemSpec= new FoodItem();
        break;
    case MUSIC:
        itemSpec=new MusicItem();
        break;
    case SPORT:
        itemSpec=new SportItem();
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
        switch(item.getCategory()){
            case BOOKS:
                // applies filter only if the flag (twarda oprawa) is true)
                if(itemSpec instanceof BookItem && (((BookItem) itemSpec).isHardCover()&&!((BookItem)item).isHardCover()))
                    return false;
            case ELECTRONICS:
                // applies filter only if the flag (Gwarancja) is true)
                if(itemSpec instanceof ElectronicItem && ((ElectronicItem) itemSpec).isGuaranty()&&!((ElectronicItem) item).isGuaranty())
                    return false;
                // applies filter only if the flag (Mobilny) is true)
                if(itemSpec instanceof ElectronicItem && ((ElectronicItem) itemSpec).isMobile()&&!((ElectronicItem) item).isMobile())
                    return false;
            case MUSIC:
                // applies filter only if the flag (Wideo) is true)
                if(itemSpec instanceof MusicItem&&((MusicItem)itemSpec).isVideoConnected()&&!((MusicItem)item).isVideoConnected())
                return false;
        }
        return true;
    }

}