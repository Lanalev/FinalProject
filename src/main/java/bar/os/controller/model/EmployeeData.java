package bar.os.controller.model;

import java.util.HashSet;
import java.util.Set;

import bar.os.entity.Tab;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class EmployeeData {
private Long employeeId;
	
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeEmail;
	private String employeeEmployeeRole;
	Set<Tab> tabs = new HashSet<>();
}
	
	

