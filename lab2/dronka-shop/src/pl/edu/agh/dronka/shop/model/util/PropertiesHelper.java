package pl.edu.agh.dronka.shop.model.util;

import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.dronka.shop.model.*;

public class PropertiesHelper {

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
}
