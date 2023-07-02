package bar.os.controller.model;

import java.util.HashSet;
import java.util.Set;

import bar.os.entity.Cocktail;
import bar.os.entity.Tab;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TabData {
	private Long tabId;
	private int tax;
	private Long total;
	Set<CocktailData> cocktails = new HashSet<>();

	public TabData(Tab tab) {
		tabId = tab.getTabId();
		tax = tab.getTax();
		total = tab.getTotal();

		for (Cocktail cocktail : tab.getCocktails()) {
			cocktails.add(new CocktailData(cocktail));
		}

	}

}
