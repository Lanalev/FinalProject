package bar.os.dao;
//Author David Atwood

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bar.os.entity.Inventory;

public interface InventoryDao extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findInventoryByName(String name);



}
