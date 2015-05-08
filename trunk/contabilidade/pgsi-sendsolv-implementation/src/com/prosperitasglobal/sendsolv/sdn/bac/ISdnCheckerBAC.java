package com.prosperitasglobal.sendsolv.sdn.bac;

import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

public interface ISdnCheckerBAC
{
	@Deprecated
	public InternalResultsResponse<SdnStatusHistory> checkSdn(SdnCheckerRequest request);

	public InternalResponse checkForNewSdnFile(Request request);

	public InternalResultsResponse<SdnStatusHistory> checkMemberSdn(SdnCheckerRequest<Member> request);

	public InternalResultsResponse<SdnStatusHistory> checkRecipientSdn(SdnCheckerRequest<Recipient> request);

	public InternalResultsResponse<SdnStatusHistory> checkLiaisonSdn(SdnCheckerRequest<Liaison> request);

	public InternalResultsResponse<SdnStatusHistory> checkBusinessSdn(SdnCheckerRequest<Business> request);

	public InternalResultsResponse<SdnStatusHistory> fetchCurrentSdnStatusHistory(SdnStatusHistoryRequest request);

	public InternalResultsResponse<SdnStatusHistory> fetchFullSdnStatusHistory(SdnStatusHistoryRequest request);

	public InternalResponse updateSdnStatusHistory(SdnStatusHistoryRequest request);

	public InternalResultsResponse<SdnHistory> fetchSdnStatusHistoryByRequest(
			SdnStatusHistoryInquiryRequest request);

}
