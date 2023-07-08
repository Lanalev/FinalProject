package bar.os.service;
//author Svitlana Leven
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bar.os.controller.model.TabData;
import bar.os.dao.CocktailDao;
import bar.os.dao.EmployeeDao;
import bar.os.dao.TabDao;
import bar.os.entity.Cocktail;
import bar.os.entity.Employee;
import bar.os.entity.Tab;

@Service
public class TabService {
	@Autowired
	private TabDao tabDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private CocktailDao cocktailDao;

	@Transactional(readOnly = false)
	public TabData saveTab(TabData tabData, Long employeeId) {
		Long tabId = tabData.getTabId();
		Tab tab = findOrCreateTab(tabId);
		Employee employee = employeeDao.findByEmployeeId(employeeId);
		setFieldsInTab(tab, tabData);
		employee.getTabs().add(tab);
		tab.setEmployee(employee);
		return new TabData(tabDao.save(tab));

	}

	private void setFieldsInTab(Tab tab, TabData tabData) {
		tab.setTax(tabData.getTax());
		tab.setTotal(tabData.getTotal());
	}

	private Tab findOrCreateTab(Long tabId) {
		Tab tab;
		if (Objects.isNull(tabId)) {
			tab = new Tab();
		} else {
			tab = findTabById(tabId);
		}

		return tab;
	}

	private Tab findTabById(Long tabId) {

		return tabDao.findById(tabId)
				.orElseThrow(() -> new NoSuchElementException("Tab with ID=" + tabId + " was not found"));
	}

	@Transactional(readOnly = true)
	public List<TabData> retrieveAllTabs() {
		List<Tab> tabs = tabDao.findAll();
		List<TabData> response = new LinkedList<>();

		for (Tab tab : tabs) {
			response.add(new TabData(tab));
		}
		return response;
	}

	public TabData retrieveTabById(Long tabId) {
		Tab tab = findTabById(tabId);
		return new TabData(tab);
	}

	public List<TabData> retrieveAllTabsByEmployeeId(Long employeeId) {
		Employee employee = employeeDao.findByEmployeeId(employeeId);
		List<TabData> result = new LinkedList<>();

		for (Tab tab : employee.getTabs()) {
			result.add(new TabData(tab));
		}
		return result;
	}

	@Transactional(readOnly = false)
	public void deleteTabById(Long tabId) {
		Tab tab = findTabById(tabId);
		tabDao.delete(tab);

	}

	@Transactional(readOnly = false)
	public TabData addCocktailTab(String cocktailName, Long tabId) {
		Cocktail cocktail = cocktailDao.findByName(cocktailName);
		Tab tab = findTabById(tabId);
		tab.getCocktails().add(cocktail);
		return new TabData(tabDao.save(tab));
	}
}
