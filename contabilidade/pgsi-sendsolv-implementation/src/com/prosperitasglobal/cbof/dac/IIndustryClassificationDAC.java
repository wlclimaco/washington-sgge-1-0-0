package com.prosperitasglobal.cbof.dac;

import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IIndustryClassificationDAC.
 */
public interface IIndustryClassificationDAC
{
	/**
	 * Fetch code value by code.
	 *
	 * @param request the request
	 * @return the internal results response< code value>
	 */
	public InternalResultsResponse<CodeValue> fetchCodeValueByCode(CodeValueRequest request);
}
