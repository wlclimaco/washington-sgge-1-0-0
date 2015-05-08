package com.prosperitasglobal.cbof.bai;

import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.response.CodeValueResponse;

public interface ICodeValueBAI
{
	/**
	 * Fetch all code value by type.
	 *
	 * @param request the request
	 * @return the code value response
	 */
	public CodeValueResponse fetchAllCodeValueByType(CodeValueRequest request);
}
