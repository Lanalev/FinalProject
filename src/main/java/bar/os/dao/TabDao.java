package bar.os.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.os.entity.Tab;

public interface TabDao extends JpaRepository<Tab, Long> {

	List<Tab> findAllByEmployeeId(Long employeeId);

}
