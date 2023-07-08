package bar.os.service;
//author Svitlana Leven
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bar.os.controller.model.EmployeeData;
import bar.os.dao.EmployeeDao;
import bar.os.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Transactional(readOnly = false)
	public EmployeeData saveEmployee(EmployeeData employeeData) {
		Long employeeId = employeeData.getEmployeeId();
		Employee employee = findOrCreateEmployee(employeeId);

		setFieldsInEmployee(employee, employeeData);
		return new EmployeeData(employeeDao.save(employee));

	}

	private void setFieldsInEmployee(Employee employee, EmployeeData employeeData) {
		employee.setEmployeeFirstName(employeeData.getEmployeeFirstName());
		employee.setEmployeeLastName(employeeData.getEmployeeLastName());
		employee.setEmployeeEmail(employeeData.getEmployeeEmail());
		employee.setEmployeeRole(employeeData.getEmployeeRole());

	}

	private Employee findOrCreateEmployee(Long employeeId) {
		Employee employee;
		if (Objects.isNull(employeeId)) {
			employee = new Employee();
		} else {
			employee = findEmployeeById(employeeId);
		}

		return employee;
	}

	private Employee findEmployeeById(Long employeeId) {

		return employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " was not found"));
	}

	@Transactional(readOnly = true)
	public List<EmployeeData> retrieveAllEmployees() {
		List<Employee> employees = employeeDao.findAll();
		List<EmployeeData> response = new LinkedList<>();

		for (Employee employee : employees) {
			response.add(new EmployeeData(employee));
		}

		return response;
	}

	public EmployeeData retrieveEmployeeById(Long employeeId) {
		Employee employee = findEmployeeById(employeeId);
		return new EmployeeData(employee);
	}

	public List<EmployeeData> retrieveEmployeeByRole(String employeeRole) {
		List<Employee> employees = employeeDao.findAllByEmployeeRole(employeeRole);
		List<EmployeeData> response = new LinkedList<>();

		for (Employee employee : employees) {
			response.add(new EmployeeData(employee));
		}

		return response;
	}

	@Transactional(readOnly = false)
	public void deleteEmployeeById(Long employeeId) {
		Employee employee = findEmployeeById(employeeId);
		employeeDao.delete(employee);

	}

//	public EmployeeData findEmployeeRoleByID(Long employeeId) {
//		Employee employeeRole = employeeDao.findEmployeeRoleByID(employeeId);
//		return new EmployeeData();
//	}

}
