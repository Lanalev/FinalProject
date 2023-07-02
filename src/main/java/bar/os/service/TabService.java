package bar.os.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import bar.os.controller.model.TabData;
import bar.os.dao.TabDao;
import bar.os.entity.Employee;
import bar.os.entity.Tab;

public class TabService {
	@Autowired
	private TabDao tabDao;

	@Transactional(readOnly = false)
	public TabData saveTab(TabData tabData) {
		Long tabId = tabData.getTabId();
		Tab tab = findOrCreateTab(tabId);

		setFieldsInTab(tab, tabData);
		return new TabData(tabDao.save(tab));

	}

	private void setFieldsInTab(Tab tab, TabData tabData) {
		tab.setTabId(tabData.getTabId());
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
		List<Tab> tabs = tabDao.findAllByEmployeeId(employeeId);
		List<TabData> result = new LinkedList<>();

		for (Tab tab : tabs) {
			result.add(new TabData(tab));
		}
		return result;
	}

	@Transactional(readOnly = false)
	public void deleteTabById(Long tabId) {
		Tab tab = findTabById(tabId);
		tabDao.delete(tab);

	}
}
