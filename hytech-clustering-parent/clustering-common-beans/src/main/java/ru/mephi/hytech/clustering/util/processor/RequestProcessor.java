package ru.mephi.hytech.clustering.util.processor;

import ru.mephi.hytech.clustering.request.BaseRequest;
import ru.mephi.hytech.clustering.response.BaseResponse;

public interface RequestProcessor<Req extends BaseRequest, Res extends BaseResponse> {

	public Res process(Req request);

}
