package bar.os.controller;
//Author David Atwood And Svitlana Leven

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bar.os.controller.model.BottleTypeData;
import bar.os.entity.BottleType;
import bar.os.service.BottleTypeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bottle_type")
@Slf4j
public class BottleTypeController {
	
	@Autowired
	private BottleTypeService bottleTypeService;
	
	
	
	
	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public BottleTypeData addNewBottleType (@RequestBody BottleType bottleType) {
		
		log.info("Adding new bottle type {}", bottleType);
		return bottleTypeService.addNewBottleType(bottleType);
	}
	
	@GetMapping("/get_all")
	public List<BottleTypeData> getAllBottleTypes() {
		log.info("Getting all bottle types");
		return bottleTypeService.getAllBottleTypes();
	}
	
}
