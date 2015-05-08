package com.prosperitasglobal.cbof.dac;

import com.prosperitasglobal.cbof.model.Language;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ILanguageDAC.
 */
public interface ILanguageDAC
{

	/**
	 * Fetch language by request.
	 *
	 * @param request the request
	 * @return the internal results response< language>
	 */
	public InternalResultsResponse<Language> fetchLanguageByRequest(PagedInquiryRequest request);

}
