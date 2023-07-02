package bar.os.service;
//Author David Atwood

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bar.os.controller.model.InventoryData;
import bar.os.dao.BottleTypeDao;
import bar.os.dao.EmployeeDao;
import bar.os.dao.InventoryDao;
import bar.os.entity.BottleType;
import bar.os.entity.Inventory;

@Service
public class InventoryService {

	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private BottleTypeDao bottleTypeDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	

	
	@Transactional(readOnly = false)
	public InventoryData saveToInventory(InventoryData inventoryData, String bottleTypeName) {
		Long inventoryId = inventoryData.getInventoryId();
		BottleType bottleType = bottleTypeDao.findByName(bottleTypeName);
		Inventory inventory = FindOrCreateInventory(inventoryId, inventoryData.getName());
		setFieldsInInventory(inventory, inventoryData);
		inventory.setBottleType(bottleType);		
		bottleType.getInventory().add(inventory);
		Inventory dbInventory = inventoryDao.save(inventory);
		return new InventoryData(dbInventory);
	}

	private void setFieldsInInventory(Inventory inventory, InventoryData inventoryData) {
		inventory.setName(inventoryData.getName());
		inventory.setCost(inventoryData.getCost());
		inventory.setSizeInOz(inventoryData.getSizeInOz());
		inventory.setInInventory(inventoryData.getInInventory());		
	}

	private Inventory FindOrCreateInventory(Long inventoryId, String name) {
		Inventory inventory;
		
		if (Objects.isNull(inventoryId)) {
			Optional<Inventory> opInventory = inventoryDao.findInventoryByName(name);
			if (opInventory.isPresent()) {
				throw new DuplicateKeyException("item with name " + name + " already exists");
			}
			inventory = new Inventory();
		} else {
			inventory = findInventoryByName(name);
			
		}
		
		return inventory;
	}

	private Inventory findInventoryByName(String name) {			
		return inventoryDao.findInventoryByName(name).orElseThrow(() -> new NoSuchElementException("Inventory item with the name " + name + "doesnt exist"));
	}
	
	@Transactional(readOnly = false)
	public InventoryData updateItemByName() {
		
		return null;
	}



	public Long getInventoryIdByName(String name) {
		Long response = findInventoryByName(name).getInventoryId();
		return response;
	}

	@Transactional(readOnly = false)
	public void deleteByName(String name) {
		Inventory inventory = findInventoryByName(name);
		inventoryDao.delete(inventory);
	}

	@Transactional(readOnly = true)
	public List<InventoryData> retrieveAllInventory() {
		List<Inventory> inventories = inventoryDao.findAll();
		List<InventoryData> response = new LinkedList<>();
		
		for(Inventory inventory : inventories) {
			response.add(new InventoryData(inventory));
			
		}
		return response;
	}

	@Transactional(readOnly = true)
	public InventoryData retriveItemByName(String name) {
		Inventory inventory = findInventoryByName(name);
		
		return new InventoryData(inventory);
	}

	public InventoryData updateInventory(InventoryData inventoryData, String inventoryName) {
		Long inventoryId = inventoryData.getInventoryId();
		BottleType bottleType = bottleTypeDao.findByInventoryName(inventoryName);
		Inventory inventory = FindOrCreateInventory(inventoryId, inventoryData.getName());
		setFieldsInInventory(inventory, inventoryData);
		inventory.setBottleType(bottleType);		
		bottleType.getInventory().add(inventory);
		Inventory dbInventory = inventoryDao.save(inventory);
		return new InventoryData(dbInventory);
		
	}

	public List<InventoryData> retrieveAllByType(String bottleTypeName) {		
		List<Inventory> inventories = inventoryDao.findAll();
		List<InventoryData> response = new LinkedList<>();				
		for(Inventory inventory : inventories) {
			if (inventory.getBottleType().getName().equals(bottleTypeName))
				
			response.add(new InventoryData(inventory));
		}
		
		return response;
	}

	public void checkRole(Long employeeId) {
		String employeeRole = employeeDao.findEmployeeRoleByID(employeeId);
		if(!employeeRole.equals("manager")) {
			throw new UnsupportedOperationException("You do not have permissions for this operation");
		}
	}


}
