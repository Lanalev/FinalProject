package bar.os.controller;
//Author David Atwood

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

import bar.os.controller.model.InventoryData;
import bar.os.controller.model.InventoryData.InventoryBottleType;
import bar.os.entity.BottleType;
import bar.os.service.InventoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/inventory")
@Slf4j
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/{employeeId}/add/{bottleTypeName}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public InventoryData addToInventory(@PathVariable Long employeeId, @PathVariable String bottleTypeName, @RequestBody InventoryData inventoryData) {
		inventoryService.checkRole(employeeId);
		log.info("Adding {} to inventory", inventoryData);
		return inventoryService.saveToInventory(inventoryData, bottleTypeName);
	}

	@PutMapping("/{employeeId}/update/{InventoryName}")
	public InventoryData updateInventoryByName(@PathVariable Long employeeId, @PathVariable String InventoryName,
			@RequestBody InventoryData inventoryData) {
		inventoryService.checkRole(employeeId);
		inventoryData.setInventoryId(inventoryService.getInventoryIdByName(InventoryName));
		
		log.info("Updating inventory item {} to {}. ", InventoryName, inventoryData);
		return inventoryService.updateInventory(inventoryData, InventoryName);

	}

	@DeleteMapping("/{employeeId}/delete/{name}")
	public Map<String, String> deleteInventoryItemByName(@PathVariable Long employeeId, @PathVariable String name) {
		inventoryService.checkRole(employeeId);
		log.info("Deleting {} from inventory", name);
		inventoryService.deleteByName(name);
		return Map.of("Message", "Deletion of " + name + " from inventory was successful.");
	}

	@GetMapping("/{employeeId}/get")
	public List<InventoryData> retrieveAllInventory(@PathVariable Long employeeId) {
		log.info("Retrieve all inventory called.");
		return inventoryService.retrieveAllInventory();
		
	}
	
	@GetMapping("{employeeId}/get/{name}")
	public InventoryData retrieveInventoryByName(@PathVariable Long employeeId, @PathVariable String name) {
		log.info("Retrieving inventory item {}.", name);
		return inventoryService.retriveItemByName(name);
	}
	
	@GetMapping("/get/{bottleTypeName}")
	public List<InventoryData> retrieveAllByType(@PathVariable String bottleTypeName){
		log.info("Retrieving all inventory of type={}", bottleTypeName);
		return inventoryService.retrieveAllByType(bottleTypeName);
	}
	
}
