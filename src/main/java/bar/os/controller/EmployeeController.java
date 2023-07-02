package bar.os.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bar.os.controller.model.EmployeeData;
import bar.os.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeData saveEmployee(@RequestBody EmployeeData employeeData) {
		log.info("Creating an employee {}", employeeData);
		return employeeService.saveEmployee(employeeData);
	}

	@PutMapping("/updateemployee/{employeeId}")
	public EmployeeData updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeData employeeData) {
		employeeData.setEmployeeId(employeeId);
		log.info("Updating employee {}", employeeData);
		return employeeService.saveEmployee(employeeData);
	}
	
	@GetMapping("/employee")
	public List<EmployeeData> retrieveAllEmployees() {
		log.info("Retrieve all employees called.");
		return employeeService.retrieveAllEmployees();
	}
	
	@GetMapping("/employee/{employeeId}")
	public EmployeeData retrieveEmployeeById(@PathVariable Long employeeId) {
		log.info("Retrieving employee with ID={}", employeeId);
		return employeeService.retrieveEmployeeById(employeeId);
	}
	@GetMapping("/employee/{employeeRole}")
	public List<EmployeeData> retrieveEmployeeByRole(@PathVariable String employeeRole) {
		log.info("Retrieving employee with the role ", employeeRole);
		return employeeService.retrieveEmployeeByRole(employeeRole);
	
}
	@DeleteMapping("/delete/{employeeId}")
	public Map<String, String> deleteEmployeeById(@PathVariable Long employeeId) {
		log.info("Deleting an employee with ID={}", employeeId);
		employeeService.deleteEmployeeById(employeeId);
		return Map.of("message", "Deleting of employee with ID=" + employeeId + " was successful.");
	}
}
