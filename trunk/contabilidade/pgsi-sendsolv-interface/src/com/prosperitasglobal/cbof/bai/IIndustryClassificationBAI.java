package com.prosperitasglobal.cbof.bai;

import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;

/**
 * The Interface IIndustryClassificationBAI.
 */
public interface IIndustryClassificationBAI
{
	/**
	 * Fetch all naics.
	 *
	 * @param request the request
	 * @return the code value response
	 */
	public CodeValueResponse fetchAllNAICS(CodeValueRequest request);

	/**
	 * Fetch all sic.
	 *
	 * @param request the request
	 * @return the code value response
	 */
	public CodeValueResponse fetchAllSIC(CodeValueRequest request);

	/**
	 * Fetch sic by code.
	 *
	 * @param request the request
	 * @return the code value response
	 */
	public CodeValueResponse fetchSICByCode(CodeValueRequest request);

	/**
	 * Fetch naics by code.
	 *
	 * @param request the request
	 * @return the code value response
	 */
	public CodeValueResponse fetchNAICSByCode(CodeValueRequest request);
}
