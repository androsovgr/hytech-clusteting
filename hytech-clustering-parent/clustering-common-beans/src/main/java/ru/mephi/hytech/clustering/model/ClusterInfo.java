package ru.mephi.hytech.clustering.model;

import java.util.Arrays;
import java.util.List;

public class ClusterInfo {

	private long modelCount;
	private double radius;
	private double[] centerCoordinates;
	private double[] centerCoordinatesUnnormed;
	private List<MinMax> borders;
	private List<MinMax> bordersUnnormed;

	public ClusterInfo(long modelCount, double radius, double[] centerCoordinates, double[] centerCoordinatesUnnormed,
			List<MinMax> borders, List<MinMax> bordersUnnormed) {
		super();
		this.modelCount = modelCount;
		this.radius = radius;
		this.centerCoordinates = centerCoordinates;
		this.centerCoordinatesUnnormed = centerCoordinatesUnnormed;
		this.borders = borders;
		this.bordersUnnormed = bordersUnnormed;
	}

	public ClusterInfo() {
		super();
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "ClusterInfo [modelCount="
				+ modelCount
				+ ", radius="
				+ radius
				+ ", centerCoordinates="
				+ (centerCoordinates != null ? Arrays.toString(Arrays.copyOf(centerCoordinates,
						Math.min(centerCoordinates.length, maxLen))) : null)
				+ ", centerCoordinatesUnnormed="
				+ (centerCoordinatesUnnormed != null ? Arrays.toString(Arrays.copyOf(centerCoordinatesUnnormed,
						Math.min(centerCoordinatesUnnormed.length, maxLen))) : null)
				+ ", borders="
				+ (borders != null ? borders.subList(0, Math.min(borders.size(), maxLen)) : null)
				+ ", bordersUnnormed="
				+ (bordersUnnormed != null ? bordersUnnormed.subList(0, Math.min(bordersUnnormed.size(), maxLen))
						: null) + "]";
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

	public double[] getCenterCoordinatesUnnormed() {
		return centerCoordinatesUnnormed;
	}

	public void setCenterCoordinatesUnnormed(double[] centerCoordinatesUnnormed) {
		this.centerCoordinatesUnnormed = centerCoordinatesUnnormed;
	}

	public List<MinMax> getBordersUnnormed() {
		return bordersUnnormed;
	}

	public void setBordersUnnormed(List<MinMax> bordersUnnormed) {
		this.bordersUnnormed = bordersUnnormed;
	}

}
