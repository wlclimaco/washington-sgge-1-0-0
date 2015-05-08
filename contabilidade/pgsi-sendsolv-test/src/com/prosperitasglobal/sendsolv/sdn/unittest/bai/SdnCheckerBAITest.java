package com.prosperitasglobal.sendsolv.sdn.unittest.bai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.bai.ISdnCheckerBAI;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.criteria.SdnStatusHistoryCriteria;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnCheckerResponse;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnHistoryResponse;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.Response;

/**
 * The Class SdnCheckerBAITest.
 */
@ContextConfiguration(locations =
{
		"classpath:com/prosperitasglobal/sendsolv/sdn/unittest/bai/conf/sdncheckerbaiimpltest.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-validators-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"
})
public class SdnCheckerBAITest extends AbstractJUnit4SpringContextTests
{
	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCH_CITY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCH_CITY_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.sdnmatch.city.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_COUNTRY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_COUNTRY_REQUIRED =
			"sendsolv.base.businessvalidator.country.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_REQUIRED =
			"sendsolv.base.personvalidator.last.name.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_REQUIRED =
			"sendsolv.base.personvalidator.first.name.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.last.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_MIDDLE_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_MIDDLE_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.middle.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.first.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED =
			"sendsolv.base.businessvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSONID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSONID_REQUIRED =
			"sendsolv.base.personvalidator.personid.required";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.sdnmatchtype.required";

	/** The Constant PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCH_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNMATCHVALIDATOR_SDNMATCH_REQUIRED =
			"sendsolv.base.sdnmatchvalidator.sdnmatch.required";

	/** The Constant TWO_HUNDRED_FIFTY_FIVE. */
	private static final Integer TWO_HUNDRED_FIFTY_FIVE = 255;

	/** The Constant PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_REQUEST_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_REQUEST_REQUIRED =
			"sendsolv.base.sdnhistoryvalidator.request.required";

	/** The Constant PROSPERITASGLOBAL_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED =
			"sendsolv.base.sdnhistoryvalidator.sdnmatchtype.required";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant ONE. */
	private static final Integer ONE = new Integer(1);

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(SdnCheckerBAITest.class);

	/** The sdn checker bai. */
	private ISdnCheckerBAI sdnCheckerBAI;

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	/** The mock industry classification dac. */
	private IIndustryClassificationDAC mockIndustryClassificationDAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockSdnCheckerBAC());
	}

	/**
	 * Gets the sdn checker bai.
	 *
	 * @return the sdnCheckerBAI
	 */
	public ISdnCheckerBAI getSdnCheckerBAI()
	{
		return sdnCheckerBAI;
	}

	/**
	 * Sets the sdn checker bai.
	 *
	 * @param sdnCheckerBAI the sdnCheckerBAI to set
	 */
	@Resource
	public void setSdnCheckerBAI(ISdnCheckerBAI sdnCheckerBAI)
	{
		this.sdnCheckerBAI = sdnCheckerBAI;
	}

	/**
	 * Gets the mock sdn checker bac.
	 *
	 * @return the mockSdnCheckerBAC
	 */
	public ISdnCheckerBAC getMockSdnCheckerBAC()
	{
		return mockSdnCheckerBAC;
	}

	/**
	 * Sets the mock sdn checker bac.
	 *
	 * @param mockSdnCheckerBAC the mockSdnCheckerBAC to set
	 */
	@Resource
	public void setMockSdnCheckerBAC(ISdnCheckerBAC mockSdnCheckerBAC)
	{
		this.mockSdnCheckerBAC = mockSdnCheckerBAC;
	}

	/**
	 * Test check sdn to person.
	 */
	@Test
	public void testCheckSdnToPerson()
	{
		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();
		request.setPersonOrBusiness(CommonTestRoutines.createDummyMember());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockSdnCheckerBAC().checkMemberSdn(request)).thenReturn(
				new InternalResultsResponse<SdnStatusHistory>());

		SdnCheckerResponse response = getSdnCheckerBAI().checkMemberSdn(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSdnCheckerBAC()).checkMemberSdn(request);
	}

	/**
	 * Test check sdn to business.
	 */
	@Test
	public void testCheckSdnToBusiness()
	{
		SdnCheckerRequest<Business> request = new SdnCheckerRequest<Business>();
		request.setPersonOrBusiness(CommonTestRoutines.createDummyOrganization());
		CommonTestRoutines.createDummyUserContext(request);

		mockFetchCodeValueByCode();

		Mockito.when(getMockSdnCheckerBAC().checkBusinessSdn(request)).thenReturn(
				new InternalResultsResponse<SdnStatusHistory>());

		SdnCheckerResponse response = getSdnCheckerBAI().checkBusinessSdn(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSdnCheckerBAC()).checkBusinessSdn(request);
	}

	/**
	 * Test check sdn exception.
	 */
	@Test
	public void testCheckSdnException()
	{
		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();
		request.setPersonOrBusiness(CommonTestRoutines.createDummyMember());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockSdnCheckerBAC().checkMemberSdn(request)).thenThrow(new RuntimeException());

		SdnCheckerResponse response = getSdnCheckerBAI().checkMemberSdn(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockSdnCheckerBAC()).checkMemberSdn(request);
	}

	/**
	 * Testcheck for new sdn file.
	 */
	@Test
	public void testcheckForNewSdnFile()
	{
		Request request = new Request();

		Mockito.when(getMockSdnCheckerBAC().checkForNewSdnFile(request)).thenReturn(
				new InternalResponse());

		Response response = getSdnCheckerBAI().checkForNewSdnFile(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSdnCheckerBAC()).checkForNewSdnFile(request);
	}

	@Test
	public void testUpdateSdnStatusHistory()
	{
		SdnStatusHistoryRequest request = new SdnStatusHistoryRequest();

		SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();
		sdnStatusHistory.setId(ONE);
		sdnStatusHistory.setSdnStatus(SDNStatusEnum.NEUTRAL);
		request.setSdnStatusHistory(sdnStatusHistory);
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockSdnCheckerBAC().updateSdnStatusHistory(request)).thenReturn(
				new InternalResponse());

		Response response = getSdnCheckerBAI().updateSdnStatusHistory(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSdnCheckerBAC()).updateSdnStatusHistory(request);
	}

	@Test
	public void testUpdateSdnStatusHistoryNoFields()
	{
		SdnStatusHistoryRequest request = new SdnStatusHistoryRequest();

		SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();

		request.setSdnStatusHistory(sdnStatusHistory);
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockSdnCheckerBAC().updateSdnStatusHistory(request)).thenReturn(
				new InternalResponse());

		Response response = getSdnCheckerBAI().updateSdnStatusHistory(request);
		assertFalse(response.isOperationSuccess());

		assertEquals("", 2, response.getMessageList().size());
	}

	/**
	 * Testcheck for new sdn file exception.
	 */
	@Test
	public void testcheckForNewSdnFileException()
	{
		Request request = new Request();

		Mockito.when(getSdnCheckerBAI().checkForNewSdnFile(request)).thenThrow(new RuntimeException());

		Response response = getSdnCheckerBAI().checkForNewSdnFile(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch of a {@link SdnHistory} with success.
	 */
	@Test
	public void testFetchSdnStatusHistoryByRequest()
	{
		SdnStatusHistoryInquiryRequest request = new SdnStatusHistoryInquiryRequest();
		request.setCriteria(new SdnStatusHistoryCriteria());
		request.getCriteria().setMatchType(SdnMatchTypeEnum.ENTITY);

		Mockito.when(
				getMockSdnCheckerBAC().fetchSdnStatusHistoryByRequest(request))
				.thenReturn(createFetchResponse());

		SdnHistoryResponse response = getSdnCheckerBAI().fetchSdnStatusHistoryByRequest(request);
		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch of a {@link SdnHistory} that takes an exception.
	 */
	@Test
	public void testFetchSdnStatusHistoryByRequestWithException()
	{
		SdnStatusHistoryInquiryRequest request = new SdnStatusHistoryInquiryRequest();
		request.setCriteria(new SdnStatusHistoryCriteria());
		request.getCriteria().setMatchType(SdnMatchTypeEnum.ENTITY);

		Mockito.when(
				getMockSdnCheckerBAC().fetchSdnStatusHistoryByRequest(request)).thenThrow(new RuntimeException());

		SdnHistoryResponse response = getSdnCheckerBAI().fetchSdnStatusHistoryByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch of a {@link SdnHistory} missing the request.
	 */
	@Test
	public void testFetchSdnStatusHistoryByRequestMissingRequest()
	{
		SdnStatusHistoryInquiryRequest request = null;

		Mockito.when(
				getMockSdnCheckerBAC().fetchSdnStatusHistoryByRequest(request))
				.thenReturn(createFetchResponse());

		SdnHistoryResponse response = getSdnCheckerBAI().fetchSdnStatusHistoryByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_SDNHISTORYVALIDATOR_REQUEST_REQUIRED);
	}

	/**
	 * Test fetch of a {@link SdnHistory} missing Match Type.
	 */
	@Test
	public void testFetchSdnStatusHistoryByRequestMissingMatchType()
	{
		SdnStatusHistoryInquiryRequest request = new SdnStatusHistoryInquiryRequest();

		Mockito.when(
				getMockSdnCheckerBAC().fetchSdnStatusHistoryByRequest(request))
				.thenReturn(createFetchResponse());

		SdnHistoryResponse response = getSdnCheckerBAI().fetchSdnStatusHistoryByRequest(request);
		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_SDNMATCHVALIDATOR_SDNMATCHTYPE_REQUIRED);
	}

	/**
	 * Creates the invalid text.
	 *
	 * @param maxValue the max value
	 * @return the string
	 */
	private String createInvalidText(Integer maxValue)
	{
		String text = new String();
		for (int i = 0; i < maxValue; i++)
		{
			text += "A";
		}
		return text;
	}

	/**
	 * Creates the sdn match to business.
	 *
	 * @return the sdn match
	 */
	private SdnMatch createSdnMatchToBusiness()
	{
		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.ENTITY);
		sdnMatch.setParentKey(ONE);
		sdnMatch.setLastName("AEROCARIBBEAN");
		sdnMatch.setCityName("Havana");
		sdnMatch.setAddressCountryName("Cuba");
		return sdnMatch;
	}

	/**
	 * Creates the sdn match to person.
	 *
	 * @return the sdn match
	 */
	private SdnMatch createSdnMatchToPerson()
	{
		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
		sdnMatch.setParentKey(ONE);
		sdnMatch.setFirstName("Mary");
		sdnMatch.setLastName("Abdelnur");
		sdnMatch.setCityName("Panama City");
		sdnMatch.setAddressCountryName("Panama");
		return sdnMatch;
	}

	/**
	 * Mock fetch code value by code.
	 */
	private void mockFetchCodeValueByCode()
	{
		InternalResultsResponse<CodeValue> internalResponse = new InternalResultsResponse<CodeValue>();
		internalResponse.setStatus(Status.OperationSuccess);
		CodeValue code = new CodeValue();
		code.setCode("999999");
		internalResponse.addResult(code);

		Mockito.when(getMockIndustryClassificationDAC().fetchCodeValueByCode(Matchers.any(CodeValueRequest.class)))
		.thenReturn(internalResponse);
	}

	public IIndustryClassificationDAC getMockIndustryClassificationDAC()
	{
		return mockIndustryClassificationDAC;
	}

	@Resource
	public void setMockIndustryClassificationDAC(IIndustryClassificationDAC mockIndustryClassificationDAC)
	{
		this.mockIndustryClassificationDAC = mockIndustryClassificationDAC;
	}

	/**
	 * Creates the fetch response for {@link SdnStatusHistory}.
	 *
	 * @param moneyTransfer the money transfer.
	 * @return the internal results response of {@link MoneyTransfer}
	 */
	private InternalResultsResponse<SdnHistory> createFetchResponse()
	{
		InternalResultsResponse<SdnHistory> response = new InternalResultsResponse<SdnHistory>();
		return response;
	}
}
