package com.prosperitasglobal.cbof.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.cbof.dac.ILanguageDAC;
import com.prosperitasglobal.cbof.model.Language;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

/**
 * The Class LanguageDACImpl.
 */
public class LanguageDACImpl extends SqlSessionDaoSupport implements ILanguageDAC
{

	/** The Constant LANGUAGE_NAMESPACE. */
	private static final String LANGUAGE_NAMESPACE = "languageMap.";

	/** The Constant PERSON_STMT_FETCH_ALL_LANGUAGE_BY_REQUEST. */
	private static final String PERSON_STMT_FETCH_ALL_LANGUAGE_BY_REQUEST = LANGUAGE_NAMESPACE
			+ "fetchLanguageByRequest";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.prosperitasglobal.cbof.dac.ILanguageDAC#fetchLanguageByRequest(com.prosperitasglobal.sendsolv.model.request
	 * .PagedInquiryRequest)
	 */
	@Override
	public InternalResultsResponse<Language> fetchLanguageByRequest(PagedInquiryRequest request)
	{
		InternalResultsResponse<Language> response = new InternalResultsResponse<Language>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), PERSON_STMT_FETCH_ALL_LANGUAGE_BY_REQUEST, response);

		return response;
	}

}
