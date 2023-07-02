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
import bar.os.controller.model.TabData;
import bar.os.service.TabService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tab")
@Slf4j
public class TabController {
	@Autowired
	private TabService tabService;

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TabData saveTab(@RequestBody TabData tabData) {
		log.info("Creating a tab {}", tabData);
		return tabService.saveTab(tabData);

	}

	@PutMapping("/updatetab/{tabId}")
	public TabData updateTab(@PathVariable Long tabId, @RequestBody TabData tabData) {
		tabData.setTabId(tabId);
		log.info("Updating tab {}", tabData);
		return tabService.saveTab(tabData);
	}

	@GetMapping("/getall")
	public List<TabData> retrieveAllTabs() {
		log.info("Retrieve all tabs called.");
		return tabService.retrieveAllTabs();
	}

	@GetMapping("/get/{tabId}")
	public TabData retrieveTabById(@PathVariable Long tabId) {
		log.info("Retrieving a tab with ID={}", tabId);
		return tabService.retrieveTabById(tabId);
	}

	@GetMapping("/{employeeId}/get")
	public List<TabData> retrieveAllTabsByEmployeeId(@PathVariable Long employeeId) {
		log.info("Retrieving all tabs by employeeId", employeeId);
		return tabService.retrieveAllTabsByEmployeeId(employeeId);

	}

	@DeleteMapping("/delete/{tabId}")
	public Map<String, String> deleteTabById(@PathVariable Long tabId) {
		log.info("Deleting an tab with ID={}", tabId);
		tabService.deleteTabById(tabId);
		return Map.of("message", "Deleting of tab with ID=" + tabId + " was successful.");
}
}