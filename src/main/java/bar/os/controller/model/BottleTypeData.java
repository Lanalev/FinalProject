package bar.os.controller.model;
//Author David Atwood and Svitlana Leven

import java.util.HashSet;
import java.util.Set;

import bar.os.entity.BottleType;
import bar.os.entity.Cocktail;
import bar.os.entity.Inventory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BottleTypeData {

	private Long bottleTypeId;
	private String name;
	
	private Set<CocktailData> cocktails = new HashSet<>();
	private Set<InventoryData> inventory = new HashSet<>();
	
	public BottleTypeData (BottleType bottleType) {
		this.bottleTypeId = bottleType.getBottleTypeId();
		this.name = bottleType.getName();
	}
//		
//		for(Cocktail cocktail : bottleType.getCocktails()) {
//			this.cocktails.add(new CocktailData(cocktail));
//		}
//		
//		for (Inventory inventory : bottleType.getInventory()) {
//			this.inventory.add(new InventoryData(inventory));
//		}
//	}
//	
	public BottleTypeData (Long bottleTypeId, String type) {
		this.bottleTypeId = bottleTypeId;
		this.name = type;
	}
	

}
