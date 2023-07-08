package bar.os.controller.model;
//author Svitlana Leven
import java.util.HashSet;
import java.util.Set;

import bar.os.entity.Cocktail;
import bar.os.entity.Employee;
import bar.os.entity.Tab;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TabData {
	private Long tabId;
	private Long tax;
	private Long total;
	private TabEmployee employee;
	Set<CocktailData> cocktails = new HashSet<>();

	public TabData(Tab tab) {
		tabId = tab.getTabId();
		tax = tab.getTax();
		total = tab.getTotal();
		employee = new TabEmployee(tab.getEmployee());

		for (Cocktail cocktail : tab.getCocktails()) {
			cocktails.add(new CocktailData(cocktail));
		}

	}
	@Data
	@NoArgsConstructor
    public static class TabEmployee {
	private Long employeeId;
	private String employeeFirstName;
	
	public TabEmployee (Employee employee) {
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
	}
}
}

