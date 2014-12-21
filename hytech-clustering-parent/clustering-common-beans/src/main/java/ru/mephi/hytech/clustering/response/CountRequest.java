package ru.mephi.hytech.clustering.response;

import ru.mephi.hytech.clustering.request.BaseRequest;

public class CountRequest extends BaseRequest {

	private long count;

	@Override
	public String toString() {
		return "CountRequest [count=" + count + "]";
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public CountRequest() {
		super();
	}

	public CountRequest(long count) {
		super();
		this.count = count;
	}

}
