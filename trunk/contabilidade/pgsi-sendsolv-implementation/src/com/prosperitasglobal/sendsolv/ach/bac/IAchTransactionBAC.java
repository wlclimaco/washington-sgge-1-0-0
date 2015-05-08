package com.prosperitasglobal.sendsolv.ach.bac;

import com.prosperitasglobal.sendsolv.ach.model.AchStatus;
import com.prosperitasglobal.sendsolv.ach.model.request.AchTransactionRequest;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IAchTransactionBAC extends IAchCommonBAC
{
	public InternalResultsResponse<AchStatus> cancel(AchTransactionRequest request);

	public InternalResultsResponse<AchStatus> modify(AchTransactionRequest request);

	public InternalResultsResponse<AchStatus> transfer(AchTransactionRequest request);

	public InternalResultsResponse<String> obtainNewAchTransactionIdentifier(Request request);

}
