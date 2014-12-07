package ru.mephi.hytech.clustering.util.processor;

public interface RequestProcessor<Req, Res> {

	public Res process(Req request) throws InstantiationException,
			IllegalAccessException;

}
