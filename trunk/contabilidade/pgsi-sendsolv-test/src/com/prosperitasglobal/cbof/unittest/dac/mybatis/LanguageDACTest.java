package com.prosperitasglobal.cbof.unittest.dac.mybatis;

import org.junit.Before;
import org.junit.Test;

import com.prosperitasglobal.cbof.model.Language;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LanguageDACTest.
 */
public class LanguageDACTest extends AbstractTestBaseDAC
{

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/cbof/unittest/dac/mybatis/conf/insertLanguage.sql", false);
	}

	/**
	 * Test fetch language by request.
	 */
	@Test
	public void testFetchLanguageByRequest()
	{
		InternalResultsResponse<Language> response = getLanguageDAC().fetchLanguageByRequest(new PagedInquiryRequest());
		CommonTestRoutines.assertResultResponse(response);
	}

}
