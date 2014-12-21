package ru.mephi.hytech.clustering.service.estimate;

public interface Estimator<T> {

	public double estimate(T t);
}
