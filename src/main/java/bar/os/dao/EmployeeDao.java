package bar.os.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.os.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

	List<Employee> findAllByEmployeeRole(String employeeRole);

	String findEmployeeRoleByID(Long employeeId);


}
