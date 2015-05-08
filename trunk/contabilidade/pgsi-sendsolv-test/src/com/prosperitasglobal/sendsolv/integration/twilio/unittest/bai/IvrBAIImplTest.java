package com.prosperitasglobal.sendsolv.integration.twilio.unittest.bai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.w3c.dom.NodeList;

import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.integration.twilio.bai.IIvrBAI;
import com.prosperitasglobal.sendsolv.integration.twilio.model.IvrIdentity;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.integration.twilio.model.response.IvrResponse;
import com.qat.framework.validation.ValidationUtil;

@ContextConfiguration(locations =
{"classpath:com/prosperitasglobal/sendsolv/integration/twilio/unittest/bai/conf/ivrbaiimpltest.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"
})
public class IvrBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(IvrBAIImplTest.class);

	/** The Constant SHOULD_RETURN_1_STRING_ONLY. */
	private static final String SHOULD_RETURN_1_STRING_ONLY = "Should return 1 String only";

	/** The Constant RESPONSE_CANNOT_BE_NULL. */
	private static final String RESPONSE_CANNOT_BE_NULL = "Response cannot be null";

	/** The Constant PHONE. */
	private static final String PHONE_NUMBER = "+123";

	/** The Constant CALL_SID. */
	private static final String CALL_SID = "ABX";

	/** The Constant PIN_NUMBER. */
	private static final String PIN_NUMBER = "1234";

	/** The ivr bac impl. */
	private IIvrBAI ivrBAIImpl;

	/** The main url. */
	private String mainUrl;

	/** The action path. */
	private String controllerPath;

	/** The resource path. */
	private String resourcePath;

	/** The static resource path. */
	private String staticResourcePath;

	/** The supported languages list. */
	private List<SupportedLanguage> supportedLanguagesList;

	/**
	 * Test validate pin when pin is not found.
	 */
	@Test
	public void testRequestWithNoAction()
	{
		IvrRequest ivrRequest = createIvrRequest(null, null);

		IvrResponse ivrResponse = getIvrBAIImpl().processAction(ivrRequest);

		assertResponse(ivrResponse);
	}

	private void assertResponse(IvrResponse response)
	{
		assertNotNull(RESPONSE_CANNOT_BE_NULL, response);

		assertEquals(SHOULD_RETURN_1_STRING_ONLY, 1, response.getTwimlResponseList().size());

		NodeList nodeList =
				CommonTestRoutines.simpleXPath(response.getTwimlResponseList().get(0), "Response/Say");

		assertEquals("Should get 2 nodes", 2, nodeList.getLength());

		nodeList =
				CommonTestRoutines.simpleXPath(response.getTwimlResponseList().get(0), "Response/Dial");

		assertEquals("Should get 1 node", 1, nodeList.getLength());
	}

	private IvrRequest createIvrRequest(String phone, String pin)
	{
		IvrRequest ivrRequest = new IvrRequest();
		ivrRequest.setSupportedLanguagesList(getSupportedLanguagesList());
		ivrRequest.setMainUrl(getMainUrl());
		ivrRequest.setControllerPath(getControllerPath());
		ivrRequest.setResourcePath(getResourcePath());
		ivrRequest.setStaticResourcePath(getStaticResourcePath());
		ivrRequest.setCallSid(CALL_SID);

		IvrIdentity ivrIdentity = new IvrIdentity();

		if (ValidationUtil.isNullOrEmpty(phone))
		{
			ivrIdentity.setPhoneNumber(PHONE_NUMBER);
		}
		else
		{
			ivrIdentity.setPhoneNumber(phone);
		}

		if (ValidationUtil.isNullOrEmpty(pin))
		{
			ivrIdentity.setPinNumber((PIN_NUMBER));
		}
		else
		{
			ivrIdentity.setPinNumber(pin);
		}

		ivrRequest.setIvrIdentity(ivrIdentity);

		return ivrRequest;
	}

	public IIvrBAI getIvrBAIImpl()
	{
		return ivrBAIImpl;
	}

	@Resource
	public void setIvrBAIImpl(IIvrBAI ivrBAIImpl)
	{
		this.ivrBAIImpl = ivrBAIImpl;
	}

	/**
	 * Gets the supported languages list.
	 *
	 * @return the supported languages list
	 */
	public List<SupportedLanguage> getSupportedLanguagesList()
	{
		return supportedLanguagesList;
	}

	/**
	 * Sets the supported languages list.
	 *
	 * @param supportedLanguagesList the new supported languages list
	 */
	@Resource
	public void setSupportedLanguagesList(List<SupportedLanguage> supportedLanguagesList)
	{
		this.supportedLanguagesList = supportedLanguagesList;
	}

	/**
	 * Gets the resource path.
	 *
	 * @return the resource path
	 */
	public String getResourcePath()
	{
		return resourcePath;
	}

	/**
	 * Sets the resource path.
	 *
	 * @param resourcePath the new resource path
	 */
	@Resource
	public void setResourcePath(String resourcePath)
	{
		this.resourcePath = resourcePath;
	}

	/**
	 * Gets the main url.
	 *
	 * @return the main url
	 */
	public String getMainUrl()
	{
		return mainUrl;
	}

	/**
	 * Sets the main url.
	 *
	 * @param mainUrl the new main url
	 */
	@Resource
	public void setMainUrl(String mainUrl)
	{
		this.mainUrl = mainUrl;
	}

	/**
	 * Gets the action path.
	 *
	 * @return the action path
	 */
	public String getControllerPath()
	{
		return controllerPath;
	}

	/**
	 * Sets the action path.
	 *
	 * @param controllerPath the new action path
	 */
	@Resource
	public void setControllerPath(String controllerPath)
	{
		this.controllerPath = controllerPath;
	}

	/**
	 * Gets the static resource path.
	 *
	 * @return the static resource path
	 */
	public String getStaticResourcePath()
	{
		return staticResourcePath;
	}

	/**
	 * Sets the static resource path.
	 *
	 * @param staticResourcePath the new static resource path
	 */
	@Resource
	public void setStaticResourcePath(String staticResourcePath)
	{
		this.staticResourcePath = staticResourcePath;
	}
}
