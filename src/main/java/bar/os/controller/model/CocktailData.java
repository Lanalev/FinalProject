package bar.os.controller.model;
//Author David Atwood

import java.util.HashSet;
import java.util.Set;

import bar.os.entity.BottleType;
import bar.os.entity.Cocktail;
import bar.os.entity.Tab;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CocktailData {

	private Long cocktailId;
	private String name;
	private Long costToCustomer;
	private String instructions;
	private String ingrediants;
	private CocktailBottleType baseLiqour;

	private Set<TabData> tabs = new HashSet<>();

	public CocktailData(Cocktail cocktail) {
		cocktailId = cocktail.getCocktailId();
		name = cocktail.getName();
		costToCustomer = cocktail.getCostToCustomer();
		instructions = cocktail.getInstructions();
		ingrediants = cocktail.getIngrediants();
		baseLiqour = new CocktailBottleType(cocktail.getBaseLiqour());
		}	

	
	
	@Data
	@NoArgsConstructor
	public static class CocktailBottleType {
		private Long baseLiqourId;
		private String name;

		public CocktailBottleType(BottleType bottleType) {		
			baseLiqourId = bottleType.getBottleTypeId();
			name = bottleType.getName();
			
		}
	}
}
