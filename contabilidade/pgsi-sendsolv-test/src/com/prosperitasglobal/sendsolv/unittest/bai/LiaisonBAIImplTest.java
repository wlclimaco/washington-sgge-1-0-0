package com.prosperitasglobal.sendsolv.unittest.bai;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ILiaisonBAC;
import com.prosperitasglobal.sendsolv.bai.ILiaisonBAI;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.LiaisonTypeEnum;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.PersonName;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LiaisonResponse;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LiaisonBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/liaisonbaiimpltest.xml"})
@ActiveProfiles("sqlserver")
public class LiaisonBAIImplTest extends AbstractJUnit4SpringContextTests
{

	/** The Constant TWO_HUNDRED_FIFTY_SIX. */
	private static final Integer TWO_HUNDRED_FIFTY_SIX = 256;

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant FIFTEEN. */
	private static final Integer FIFTEEN = 15;

	/** The Constant INVALID_SSN. */
	private static final String INVALID_SSN = "1111111111";

	/** The Constant FIFTY_ONE. */
	private static final Integer FIFTY_ONE = 51;

	/** The Constant TITLE. */
	private static final String TITLE = "Test Liaision";

	/** The Constant SSN_NAME. */
	private static final String SSN_NAME = "Social Security Number ";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_REQUIRED =
			"sendsolv.base.liaisonvalidator.liaison.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_TYPE_REQUIRED =
			"sendsolv.base.liaisonvalidator.pepstatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LOCATION_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LOCATION_ID_REQUIRED =
			"sendsolv.base.liaisonvalidator.location.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_TITLE_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_TITLE_TOO_BIG =
			"sendsolv.base.liaisonvalidator.title.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_WRONG_SSN. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_WRONG_SSN =
			"sendsolv.base.personvalidator.wrong.ssn";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_PARENT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_PARENT_ID_REQUIRED =
			"sendsolv.base.liaisonvalidator.parent.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.last.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_MIDDLE_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_MIDDLE_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.middle.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_TOO_BIG =
			"sendsolv.base.personvalidator.first.name.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PEP_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PEP_STATUS_REQUIRED =
			"sendsolv.base.personvalidator.pepstatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED =
			"sendsolv.base.personvalidator.personstatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED =
			"sendsolv.base.personvalidator.contactlist.required";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED =
			"sendsolv.base.personvalidator.risk.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_GENDER_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_GENDER_REQUIRED =
			"sendsolv.base.personvalidator.gender.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_TYPE_REQUIRED =
			"sendsolv.base.personvalidator.persontype.required";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant BAC_KEY. */
	private static final String BAC_KEY = "sendsolv.base.liaisonvalidator.id.required";

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The liaison bai. */
	private ILiaisonBAI liaisonBAI;

	/** The liaison bac. */
	private ILiaisonBAC liaisonBAC;

	/** The err response. */
	private InternalResultsResponse<Liaison> errResponse;

	/**
	 * Gets the liaison bai.
	 *
	 * @return the liaison bai
	 */
	public ILiaisonBAI getLiaisonBAI()
	{
		return liaisonBAI;
	}

	/**
	 * Sets the liaison bai.
	 *
	 * @param liaisonBAI the liaison bai
	 */
	@Resource
	public void setLiaisonBAI(ILiaisonBAI liaisonBAI)
	{
		this.liaisonBAI = liaisonBAI;
	}

	/**
	 * Gets the liaison bac.
	 *
	 * @return the liaison bac
	 */
	public ILiaisonBAC getLiaisonBAC()
	{
		return liaisonBAC;
	}

	/**
	 * Sets the liaison bac.
	 *
	 * @param liaisonBAC the liaison bac
	 */
	@Resource
	public void setLiaisonBAC(ILiaisonBAC liaisonBAC)
	{
		this.liaisonBAC = liaisonBAC;
	}

	/**
	 * Gets the err response.
	 *
	 * @return the err response
	 */
	public InternalResultsResponse<Liaison> getErrResponse()
	{
		return errResponse;
	}

	/**
	 * Sets the err response.
	 *
	 * @param errResponse the err response
	 */
	public void setErrResponse(InternalResultsResponse<Liaison> errResponse)
	{
		this.errResponse = errResponse;
	}

	/**
	 * Test insert liaison.
	 */
	@Test
	public void testInsertLiaison()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		liaison.setModelAction(PersistanceActionEnum.INSERT);
		request.setLiaison(liaison);

		InternalResultsResponse<Liaison> internalResponse = new InternalResultsResponse<Liaison>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getLiaisonBAC().insertLiaison(request)).thenReturn(internalResponse);

		LiaisonResponse response = getLiaisonBAI().insertLiaison(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getLiaisonBAC()).insertLiaison(request);
	}

	/**
	 * Test insert liaison fields missing.
	 */
	@Test
	public void testInsertLiaisonFieldsMissing()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Liaison liaison = new Liaison();
		liaison.setLiaisonType(null);
		liaison.setTitle(TITLE + createInvalidTitle());
		liaison.setNameList(Arrays.asList(new PersonName()));
		liaison.setLocationId(null);
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.LIAISON);
		document.setValue(INVALID_SSN);
		document.getDocumentType().setName(SSN_NAME);
		liaison.setDocumentList(Arrays.asList(document));
		liaison.setPepStatus(null);
		liaison.setPersonStatusValue(null);
		liaison.setNameList(null);
		liaison.setPersonType(null);
		liaison.setFirstName(createInvalidName());
		liaison.setMiddleName(createInvalidName());
		liaison.setLastName(createInvalidName());

		liaison.setModelAction(PersistanceActionEnum.INSERT);
		request.setLiaison(liaison);

		Mockito.when(getLiaisonBAC().insertLiaison(request)).thenReturn(new InternalResultsResponse<Liaison>());

		LiaisonResponse response = getLiaisonBAI().insertLiaison(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_TYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LOCATION_ID_REQUIRED,
				PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_TITLE_TOO_BIG,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_WRONG_SSN,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_TOO_BIG,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_MIDDLE_NAME_TOO_BIG,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_TOO_BIG,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PEP_STATUS_REQUIRED,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED,
				SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_GENDER_REQUIRED,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_TYPE_REQUIRED);

		Mockito.verify(getLiaisonBAC(), Mockito.never()).insertLiaison(request);
	}

	/**
	 * Test insert liaison without liaison object.
	 */
	@Test
	public void testInsertLiaisonWithoutLiaisonObject()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getLiaisonBAC().insertLiaison(request)).thenReturn(new InternalResultsResponse<Liaison>());

		LiaisonResponse response = getLiaisonBAI().insertLiaison(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_LIAISON_REQUIRED);

		// ensures the mock's insertLocation was called 1 time.
		Mockito.verify(getLiaisonBAC(), Mockito.never()).insertLiaison(request);
	}

	/**
	 * Test insert liaison exception.
	 */
	@Test
	public void testInsertLiaisonException()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Liaison liaison = CommonTestRoutines.createDummyLiaison();

		liaison.setModelAction(PersistanceActionEnum.INSERT);
		request.setLiaison(liaison);

		Mockito.when(getLiaisonBAC().insertLiaison(request)).thenThrow(new RuntimeException());

		LiaisonResponse response = getLiaisonBAI().insertLiaison(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		// ensures the mock's insertLocation was called 1 time.
		Mockito.verify(getLiaisonBAC()).insertLiaison(request);
	}

	/**
	 * Test update liaison.
	 */
	@Test
	public void testUpdateLiaison()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		liaison.setLiaisonType(LiaisonTypeEnum.ACCOUNTING);
		liaison.setLocationId(FIFTEEN);
		liaison.setModelAction(PersistanceActionEnum.UPDATE);

		request.setLiaison(liaison);

		InternalResultsResponse<Liaison> internalResponse = new InternalResultsResponse<Liaison>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getLiaisonBAC().updateLiaison(request)).thenReturn(internalResponse);

		LiaisonResponse response = getLiaisonBAI().updateLiaison(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getLiaisonBAC()).updateLiaison(request);
	}

	/**
	 * Test update liaison exception.
	 */
	@Test
	public void testUpdateLiaisonException()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		liaison.setLiaisonType(LiaisonTypeEnum.ACCOUNTING);
		liaison.setLocationId(FIFTEEN);
		liaison.setModelAction(PersistanceActionEnum.UPDATE);

		request.setLiaison(liaison);

		Mockito.when(getLiaisonBAC().updateLiaison(request)).thenThrow(new RuntimeException());

		LiaisonResponse response = getLiaisonBAI().updateLiaison(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getLiaisonBAC()).updateLiaison(request);
	}

	/**
	 * Test delete liaison.
	 */
	@Test
	public void testDeleteLiaison()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		request.setLiaison(liaison);

		Mockito.when(getLiaisonBAC().deleteLiaison(request)).thenReturn(
				new InternalResultsResponse<Location>());

		LiaisonResponse response = getLiaisonBAI().deleteLiaison(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getLiaisonBAC()).deleteLiaison(request);
	}

	/**
	 * Test delete liaison exception.
	 */
	@Test
	public void testDeleteLiaisonException()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		request.setLiaison(liaison);

		Mockito.when(getLiaisonBAC().deleteLiaison(request)).thenThrow(new RuntimeException());

		LiaisonResponse response = getLiaisonBAI().deleteLiaison(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getLiaisonBAC()).deleteLiaison(request);
	}

	/**
	 * Test fetch liaison by id.
	 */
	@Test
	public void testFetchLiaisonById()
	{
		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		FetchByIdRequest request = new FetchByIdRequest(liaison.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getLiaisonBAC().fetchLiaisonById(request)).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyLiaison()));

		LiaisonResponse result = getLiaisonBAI().fetchLiaisonById(request);

		CommonTestRoutines.assertResponse(result);

		Mockito.verify(getLiaisonBAC()).fetchLiaisonById(request);
	}

	/**
	 * Test fetch liaison by id exception.
	 */
	@Test
	public void testFetchLiaisonByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(TEN);

		Mockito.when(getLiaisonBAC().fetchLiaisonById(request)).thenThrow(new RuntimeException());

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonById(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getLiaisonBAC()).fetchLiaisonById(request);
	}

	/**
	 * Test fetch liaison by id no liaison id.
	 */
	@Test
	public void testFetchLiaisonByIdNoLiaisonId()
	{
		FetchByIdRequest request = new FetchByIdRequest();
		LiaisonResponse response = getLiaisonBAI().fetchLiaisonById(request);

		Assert.assertEquals(1, response.getMessageList().size());
		Assert.assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());

		Mockito.verify(getLiaisonBAC(), Mockito.never()).fetchLiaisonById(request);
	}

	/**
	 * Test fetch liaison by request.
	 */
	@Test
	public void testFetchLiaisonByRequest()
	{
		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(Matchers.any(PagedInquiryRequest.class)))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyLiaison()));

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByRequest(new PagedInquiryRequest());

		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch liaison by request with error.
	 */
	@Test
	public void testFetchLiaisonByRequestWithError()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPageSize(null);
		request.setStartPage(null);
		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(request))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyLiaison()));

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByRequest(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);

		Mockito.verify(getLiaisonBAC(), Mockito.never()).fetchLiaisonByRequest(request);
	}

	/**
	 * Test fetch liaison by request exception.
	 */
	@Test
	public void testFetchLiaisonByRequestException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();

		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(request)).thenThrow(new RuntimeException());

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByRequest(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getLiaisonBAC()).fetchLiaisonByRequest(request);
	}

	/**
	 * Test fetch liaison by organization.
	 */
	@Test
	public void testFetchLiaisonByOrganization()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(ONE);

		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(Matchers.any(PagedInquiryRequest.class)))
		.thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyLiaison()));

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByOrganization(request);

		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test fetch liaison by organization with error.
	 */
	@Test
	public void testFetchLiaisonByOrganizationWithError()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(null);

		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(request))
		.thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyLiaison()));

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByOrganization(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_PARENT_ID_REQUIRED);

		Mockito.verify(getLiaisonBAC(), Mockito.never()).fetchLiaisonByRequest(request);
	}

	/**
	 * Test fetch liaison by organization exception.
	 */
	@Test
	public void testFetchLiaisonByOrganizationException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(ONE);

		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(request)).thenThrow(new RuntimeException());

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByOrganization(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getLiaisonBAC()).fetchLiaisonByRequest(request);
	}

	/**
	 * Test fetch liaison by location.
	 */
	@Test
	public void testFetchLiaisonByLocation()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(ONE);

		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(Matchers.any(PagedInquiryRequest.class)))
		.thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyLiaison()));

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByLocation(request);

		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test fetch liaison by location with error.
	 */
	@Test
	public void testFetchLiaisonByLocationWithError()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(null);

		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(request))
		.thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyLiaison()));

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByLocation(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_LIAISONVALIDATOR_PARENT_ID_REQUIRED);

		Mockito.verify(getLiaisonBAC(), Mockito.never()).fetchLiaisonByRequest(request);
	}

	/**
	 * Test fetch liaison by location exception.
	 */
	@Test
	public void testFetchLiaisonByLocationException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(ONE);

		Mockito.when(getLiaisonBAC().fetchLiaisonByRequest(request)).thenThrow(new RuntimeException());

		LiaisonResponse response = getLiaisonBAI().fetchLiaisonByLocation(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getLiaisonBAC()).fetchLiaisonByRequest(request);
	}

	/**
	 * Creates the fetch response.
	 *
	 * @param liaison the liaison
	 * @return the internal results response< liaison>
	 */
	private InternalResultsResponse<Liaison> createFetchResponse(Liaison liaison)
	{
		InternalResultsResponse<Liaison> response = new InternalResultsResponse<Liaison>();
		response.addResult(liaison);
		return response;
	}

	/**
	 * Creates the invalid name.
	 *
	 * @return the string
	 */
	private String createInvalidName()
	{
		String name = new String();
		for (int i = 0; i < FIFTY_ONE; i++)
		{
			name += "N";
		}
		return name;
	}

	/**
	 * Creates the invalid title.
	 *
	 * @return the string
	 */
	private String createInvalidTitle()
	{
		String name = new String();
		for (int i = 0; i < TWO_HUNDRED_FIFTY_SIX; i++)
		{
			name += "T";
		}
		return name;
	}

}
