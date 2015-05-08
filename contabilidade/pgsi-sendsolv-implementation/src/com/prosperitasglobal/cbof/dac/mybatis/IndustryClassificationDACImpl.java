package com.prosperitasglobal.cbof.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class IndustryClassificationDACImpl.
 */
public class IndustryClassificationDACImpl extends SqlSessionDaoSupport implements IIndustryClassificationDAC
{

	/** The Constant FETCH_INDUSTRY_CLASSIFICATION_BY_CODE. */
	private static final String FETCH_INDUSTRY_CLASSIFICATION_BY_CODE =
			"industryClassificationMap.fetchIndustryClassificationByCode";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC#fetchCodeValueByCode(com.prosperitasglobal.cbof.model
	 * .request.CodeValueRequest)
	 */
	@Override
	public InternalResultsResponse<CodeValue> fetchCodeValueByCode(CodeValueRequest request)
	{
		InternalResultsResponse<CodeValue> response = new InternalResultsResponse<CodeValue>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_INDUSTRY_CLASSIFICATION_BY_CODE,
				request, response);

		return response;
	}

}
