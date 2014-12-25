package ru.mephi.hytech.clustering.gui.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BubbleChartModel;
import org.primefaces.model.chart.BubbleChartSeries;

import ru.mephi.hytech.clustering.gui.util.ResponseHandler;
import ru.mephi.hytech.clustering.model.Cluster;
import ru.mephi.hytech.clustering.response.ClasterListResponse;
import ru.mephi.hytech.clustering.response.CountRequest;
import ru.mephi.hytech.clustering.service.OrchestrationService;

@ManagedBean
@SessionScoped
public class ClusterBean {

	private int clusterCount;
	private List<Cluster> clusters;
	private Cluster selected;
	private BubbleChartModel chart = new BubbleChartModel();

	private int axisX;
	private int axisY;
	private int[] axises = { 0, 1, 2, 3, 4 };

	@EJB
	private OrchestrationService orchestrationService;

	public void clusterize() {
		CountRequest request = new CountRequest(clusterCount);
		ClasterListResponse response = orchestrationService.clusterize(request);
		if (ResponseHandler.handle(response, true, "Кластеризация")) {
			clusters = response.getClusters();
		} else {
			clusters = new ArrayList<Cluster>();
		}
		updateChart();
	}

	public void updateChart() {
		chart.clear();
		int i = 1;
		for (Cluster cluster : clusters) {
			double[] centerCoordintates = cluster.getClusterInfo().getCenterCoordinates();
			chart.add(new BubbleChartSeries(i++ + "", (int) centerCoordintates[axisX], (int) centerCoordintates[axisY],
					(int) cluster.getClusterInfo().getRadius()));
		}
	}

	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage("Выбран кластер", "Item Index: " + (event.getItemIndex() + 1));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		selected = clusters.get(event.getItemIndex());
	}

	public int getClusterCount() {
		return clusterCount;
	}

	public void setClusterCount(int clusterCount) {
		this.clusterCount = clusterCount;
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}

	public Cluster getSelected() {
		return selected;
	}

	public void setSelected(Cluster selected) {
		this.selected = selected;
	}

	public BubbleChartModel getChart() {
		return chart;
	}

	public void setChart(BubbleChartModel chart) {
		this.chart = chart;
	}

	public int getAxisX() {
		return axisX;
	}

	public void setAxisX(int axisX) {
		this.axisX = axisX;
	}

	public int getAxisY() {
		return axisY;
	}

	public void setAxisY(int axisY) {
		this.axisY = axisY;
	}

	public int[] getAxises() {
		return axises;
	}

	public void setAxises(int[] axises) {
		this.axises = axises;
	}

}
