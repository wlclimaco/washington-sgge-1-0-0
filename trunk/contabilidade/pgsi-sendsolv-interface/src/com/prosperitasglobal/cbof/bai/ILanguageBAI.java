package com.prosperitasglobal.cbof.bai;

import com.prosperitasglobal.cbof.model.response.LanguageResponse;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;

/**
 * The Interface ILanguageBAI.
 */
public interface ILanguageBAI
{

	/**
	 * Fetch language by request.
	 *
	 * @param request the request
	 * @return the language response
	 */
	public LanguageResponse fetchLanguageByRequest(PagedInquiryRequest request);
}
