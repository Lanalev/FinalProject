package bar.os.dao;
//Author David Atwood

import org.springframework.data.jpa.repository.JpaRepository;

import bar.os.entity.BottleType;

public interface BottleTypeDao extends JpaRepository<BottleType, Long> {


	BottleType findByName(String name);

	BottleType findByInventoryName(String inventoryName);


}
