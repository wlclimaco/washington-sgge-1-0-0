package com.prosperitasglobal.cbof.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.dac.ICodeValueDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class CodeValueDACImpl.
 */
public class CodeValueDACImpl extends SqlSessionDaoSupport implements ICodeValueDAC
{

	/** The Constant FETCH_ALL_INDUSTRY_CLASSIFICATION_BY_TYPE. */
	private static final String FETCH_ALL_INDUSTRY_CLASSIFICATION_BY_TYPE =
			"codeValueMap.fetchAllIndustryClassificationByType";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ICodeValueDAC#fetchAllCodeValueByType(com.prosperitasglobal.cbof.model.request
	 * .CodeValueRequest)
	 */
	@Override
	public InternalResultsResponse<CodeValue> fetchAllCodeValueByType(CodeValueRequest request)
	{
		InternalResultsResponse<CodeValue> response = new InternalResultsResponse<CodeValue>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_INDUSTRY_CLASSIFICATION_BY_TYPE,
				request, response);

		return response;
	}

}
