package com.prosperitasglobal.cbof.bai;

import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;

/**
 * The Interface INameSupplementBAI.
 */
public interface INameSupplementBAI
{

	/**
	 * Fetch all prefix.
	 *
	 * @param request the request
	 * @return the code value response
	 */
	public CodeValueResponse fetchAllPrefix(CodeValueRequest request);

	/**
	 * Fetch all suffix.
	 *
	 * @param request the request
	 * @return the code value response
	 */
	public CodeValueResponse fetchAllSuffix(CodeValueRequest request);

}
