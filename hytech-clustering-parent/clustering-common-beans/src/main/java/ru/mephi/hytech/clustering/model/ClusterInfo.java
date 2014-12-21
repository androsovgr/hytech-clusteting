package ru.mephi.hytech.clustering.model;

import java.util.Arrays;
import java.util.List;

public class ClusterInfo {

	private long modelCount;
	private double radius;
	private double[] centerCoordinates;
	private List<MinMax> borders;

	public ClusterInfo(long modelCount, double radius, double[] centerCoordinates, List<MinMax> borders) {
		super();
		this.modelCount = modelCount;
		this.radius = radius;
		this.centerCoordinates = centerCoordinates;
		this.borders = borders;
	}

	public ClusterInfo() {
		super();
	}

	@Override
	public String toString() {
		return "ClasterInfo [modelCount=" + modelCount + ", radius=" + radius + ", centerCoordinates="
				+ Arrays.toString(centerCoordinates) + ", borders=" + borders + "]";
	}

	public long getModelCount() {
		return modelCount;
	}

	public void setModelCount(long modelCount) {
		this.modelCount = modelCount;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double[] getCenterCoordinates() {
		return centerCoordinates;
	}

	public void setCenterCoordinates(double[] centerCoordinates) {
		this.centerCoordinates = centerCoordinates;
	}

	public List<MinMax> getBorders() {
		return borders;
	}

	public void setBorders(List<MinMax> borders) {
		this.borders = borders;
	}

}
