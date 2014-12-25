package ru.mephi.hytech.clustering.gui.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ru.mephi.hytech.clustering.gui.util.ResponseHandler;
import ru.mephi.hytech.clustering.model.Person;
import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;
import ru.mephi.hytech.clustering.response.CountRequest;
import ru.mephi.hytech.clustering.response.PersonListResponse;
import ru.mephi.hytech.clustering.service.OrchestrationService;
import ru.mephi.hytech.clustering.service.UserDbService;

@ManagedBean
@SessionScoped
public class StateBean {

	private boolean systemOk;
	private long modelCount;
	private List<Person> persons;

	private long addCount;

	@EJB
	private UserDbService userDbService;
	@EJB
	private OrchestrationService orchestrationService;

	public StateBean() {

	}

	@PostConstruct
	public void init() {
		updateAll();
	}

	public void updateAll() {
		BaseRequest request = new BaseRequest();
		PersonListResponse response = userDbService.getAllPeople(request);
		if (ResponseHandler.handle(response, true, "Обновление списка моделей")) {
			persons = response.getUsers();
			modelCount = persons.size();
			systemOk = true;
		} else {
			setFailed();
		}
	}

	public void addModels() {
		CountRequest request = new CountRequest(addCount);
		BaseResponse response = orchestrationService.fillDb(request);
		if (ResponseHandler.handle(response, true, "Заполнение БД")) {
			updateAll();
		} else {
			setFailed();
		}
	}

	public void clear() {
		BaseRequest request = new BaseRequest();
		BaseResponse response = userDbService.clear(request);
		if (ResponseHandler.handle(response, true, "Очистка БД")) {
			updateAll();
		} else {
			setFailed();
		}
	}

	private void setFailed() {
		systemOk = false;
		modelCount = 0;
		persons = new ArrayList<Person>();
	}

	public boolean isSystemOk() {
		return systemOk;
	}

	public void setSystemOk(boolean systemOk) {
		this.systemOk = systemOk;
	}

	public long getModelCount() {
		return modelCount;
	}

	public void setModelCount(long modelCount) {
		this.modelCount = modelCount;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public long getAddCount() {
		return addCount;
	}

	public void setAddCount(long addCount) {
		this.addCount = addCount;
	}

}
