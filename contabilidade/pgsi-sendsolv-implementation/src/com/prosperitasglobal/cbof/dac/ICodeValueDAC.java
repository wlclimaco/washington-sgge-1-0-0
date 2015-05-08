package com.prosperitasglobal.cbof.dac;

import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ICodeValueDAC.
 */
public interface ICodeValueDAC
{
	/**
	 * Fetch all code value by type.
	 *
	 * @param request the request
	 * @return the internal results response< code value>
	 */
	public InternalResultsResponse<CodeValue> fetchAllCodeValueByType(CodeValueRequest request);

}
