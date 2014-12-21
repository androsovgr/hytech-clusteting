package ru.mephi.hytech.clustering.model;

public class MinMax {

	private double min;
	private double max;

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "MinMax [min=" + min + ", max=" + max + "]";
	}

	public MinMax() {
		super();
	}

	public MinMax(double min, double max) {
		super();
		this.min = min;
		this.max = max;
	}

}
