package bar.os.controller.model;

import java.util.HashSet;
import java.util.Set;

import bar.os.entity.Employee;
import bar.os.entity.Tab;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeData {

	
private Long employeeId;
	
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeEmail;
	private String employeeRole;
	Set<TabData> tabs = new HashSet<>();
	
	public EmployeeData(Employee employee) {
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
		employeeLastName = employee.getEmployeeLastName();
		employeeEmail = employee.getEmployeeEmail();
		employeeRole = employee.getEmployeeRole();
		
		for(Tab tab : employee.getTabs()) {
			tabs.add(new TabData(tab));
		}
}
}

