package com.prosperitasglobal.cbof.unittest.bai;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.bai.ILanguageBAI;
import com.prosperitasglobal.cbof.dac.ILanguageDAC;
import com.prosperitasglobal.cbof.model.Language;
import com.prosperitasglobal.cbof.model.response.LanguageResponse;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LanguageBAITest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/cbofbaiimpltest.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/pgsi-cbof-validators-context-test.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
public class LanguageBAITest extends AbstractJUnit4SpringContextTests
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant LANGUAGE_NAME. */
	private static final String LANGUAGE_NAME = "Language Name";

	/** The language bai. */
	private ILanguageBAI languageBAI;

	/** The mock language dac. */
	private ILanguageDAC mockLanguageDAC;

	/**
	 * Gets the language bai.
	 *
	 * @return the language bai
	 */
	public ILanguageBAI getLanguageBAI()
	{
		return languageBAI;
	}

	/**
	 * Sets the language bai.
	 *
	 * @param languageBAI the language bai
	 */
	@Resource
	public void setLanguageBAI(ILanguageBAI languageBAI)
	{
		this.languageBAI = languageBAI;
	}

	/**
	 * Gets the mock language dac.
	 *
	 * @return the mock language dac
	 */
	public ILanguageDAC getMockLanguageDAC()
	{
		return mockLanguageDAC;
	}

	/**
	 * Sets the mock language dac.
	 *
	 * @param mockLanguageDAC the mock language dac
	 */
	@Resource
	public void setMockLanguageDAC(ILanguageDAC mockLanguageDAC)
	{
		this.mockLanguageDAC = mockLanguageDAC;
	}

	/**
	 * Test fetch language by request.
	 */
	@Test
	public void testFetchLanguageByRequest()
	{
		InternalResultsResponse<Language> internalResponse = new InternalResultsResponse<Language>();

		Language language = new Language();
		language.setId(ONE);
		language.setName(LANGUAGE_NAME);
		internalResponse.addResult(language);

		Mockito.when(getMockLanguageDAC().fetchLanguageByRequest(Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(internalResponse);

		LanguageResponse response = getLanguageBAI().fetchLanguageByRequest(new PagedInquiryRequest());

		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test fetch member by request exception.
	 */
	@Test
	public void testFetchMemberByRequestException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		Mockito.when(getMockLanguageDAC().fetchLanguageByRequest(request)).thenThrow(new RuntimeException());

		LanguageResponse response = getLanguageBAI().fetchLanguageByRequest(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockLanguageDAC()).fetchLanguageByRequest(request);
	}
}
