package com.prosperitasglobal.sendsolv.sdn.bai;

import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnCheckerResponse;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnHistoryResponse;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnStatusHistoryResponse;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.Response;

/**
 * The Interface ISdnCheckerBAI.
 */
public interface ISdnCheckerBAI
{

	/**
	 * @deprecated replaced by {@link #setBusiness(Business business)},{@link #setPerson(Person person)} and
	 *             {@link #setMatchType(SdnMatchTypeEnum matchType)} When creating the {@link SdnCheckerRequest} set the
	 *             corresponding type and object.Don't forget to set UserContext for the request.
	 */
	@Deprecated
	public SdnCheckerResponse checkSdn(SdnCheckerRequest request);

	public SdnCheckerResponse checkMemberSdn(SdnCheckerRequest<Member> request);

	public SdnCheckerResponse checkRecipientSdn(SdnCheckerRequest<Recipient> request);

	public SdnCheckerResponse checkLiaisonSdn(SdnCheckerRequest<Liaison> request);

	public SdnCheckerResponse checkBusinessSdn(SdnCheckerRequest<Business> request);

	/**
	 * Check for new sdn file.
	 *
	 * @param request the request
	 * @return the response
	 */
	public Response checkForNewSdnFile(Request request);

	public SdnStatusHistoryResponse fetchCurrentSdnStatusHistory(SdnStatusHistoryRequest request);

	public SdnStatusHistoryResponse fetchFullSdnStatusHistory(SdnStatusHistoryRequest request);

	public SdnStatusHistoryResponse updateSdnStatusHistory(SdnStatusHistoryRequest request);

	public SdnHistoryResponse fetchSdnStatusHistoryByRequest(SdnStatusHistoryInquiryRequest request);

}
