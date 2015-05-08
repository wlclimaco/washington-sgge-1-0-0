/*
 *
 */
package com.prosperitasglobal.sendsolv.unittest.bai;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.dac.IIndustryClassificationDAC;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.request.CodeValueRequest;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ILocationBAC;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.model.response.RiskResponse;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LocationBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/locationbaiimpltest.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
@ActiveProfiles("sqlserver")
public class LocationBAIImplTest extends AbstractJUnit4SpringContextTests
{

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(LocationBAIImplTest.class);

	/** The Constant TWO_HUNDRED_FIFTY_SIX. */
	private static final Integer TWO_HUNDRED_FIFTY_SIX = 256;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant BAC_KEY. */
	private static final String BAC_KEY = "sendsolv.base.locationvalidator.id.required";

	/** The Constant SENDSOLV_BASE_RISKVALIDATOR_RISKLEVEL_REQUIRED. */
	private static final String SENDSOLV_BASE_RISKVALIDATOR_RISKLEVEL_REQUIRED =
			"sendsolv.base.riskvalidator.risklevel.required";

	/** The Constant SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED. */
	private static final String SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED = "sendsolv.base.validator.version.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAICSC_OR_SICC_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAICSC_OR_SICC_REQUIRED =
			"sendsolv.base.businessvalidator.naicsc.or.sicc.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED =
			"sendsolv.base.businessvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED =
			"sendsolv.base.validator.paging.parameters.required";

	/** The Constant PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED =
			"sendsolv.base.locationvalidator.parentorganization.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS =
			"sendsolv.base.businessvalidator.invalid.naics";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC =
			"sendsolv.base.businessvalidator.invalid.sic";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED =
			"sendsolv.base.businessvalidator.status.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_INACTIVE. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_NOT_ACTIVE =
			"sendsolv.base.businessvalidator.location.parent.not.active";

	// error response object to be used in several tests.
	/** The err response. */
	private InternalResultsResponse<Location> errResponse;

	/** The location bai. */
	private ILocationBAI locationBAI;

	/** The mock location bac. */
	private ILocationBAC mockLocationBAC;

	/** The mock location dac. */
	private ILocationDAC mockLocationDAC;

	/** The mock organization dac. */
	private IOrganizationDAC mockOrganizationDAC;

	/** The mock industry classification dac. */
	private IIndustryClassificationDAC mockIndustryClassificationDAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockLocationBAC());
	}

	/**
	 * Gets the mock location bac.
	 *
	 * @return the mock location bac
	 */
	public ILocationBAC getMockLocationBAC()
	{
		return mockLocationBAC;
	}

	/**
	 * Sets the mock location bac.
	 *
	 * @param mockBAC the mock location bac
	 */
	@Resource
	public void setMockLocationBAC(ILocationBAC mockBAC)
	{
		mockLocationBAC = mockBAC;
	}

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param locationBAI the location bai
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * Gets the mock industry classification dac.
	 *
	 * @return the mock industry classification dac
	 */
	public IIndustryClassificationDAC getMockIndustryClassificationDAC()
	{
		return mockIndustryClassificationDAC;
	}

	/**
	 * Sets the mock industry classification dac.
	 *
	 * @param mockIndustryClassificationDAC the mock industry classification dac
	 */
	@Resource
	public void setMockIndustryClassificationDAC(IIndustryClassificationDAC mockIndustryClassificationDAC)
	{
		this.mockIndustryClassificationDAC = mockIndustryClassificationDAC;
	}

	/**
	 * Gets the mock organization dac.
	 *
	 * @return the mock organization dac
	 */
	public IOrganizationDAC getMockOrganizationDAC()
	{
		return mockOrganizationDAC;
	}

	/**
	 * Sets the mock organization dac.
	 *
	 * @param mockOrganizationDAC the mock organization dac
	 */
	@Resource
	public void setMockOrganizationDAC(IOrganizationDAC mockOrganizationDAC)
	{
		this.mockOrganizationDAC = mockOrganizationDAC;
	}

	/**
	 * Gets the err response.
	 *
	 * @return the err response
	 */
	public InternalResultsResponse<Location> getErrResponse()
	{
		return errResponse;
	}

	/**
	 * Sets the err response.
	 *
	 * @param errResponse the err response
	 */
	public void setErrResponse(InternalResultsResponse<Location> errResponse)
	{
		this.errResponse = errResponse;
	}

	/**
	 * Gets the mock location dac.
	 *
	 * @return the mock location dac
	 */
	public ILocationDAC getMockLocationDAC()
	{
		return mockLocationDAC;
	}

	/**
	 * Sets the mock location dac.
	 *
	 * @param mockLocationDAC the mock location dac
	 */
	@Resource
	public void setMockLocationDAC(ILocationDAC mockLocationDAC)
	{
		this.mockLocationDAC = mockLocationDAC;
	}

	/**
	 * Creates the fetch response.
	 *
	 * @param location the location
	 * @return the internal results response< location>
	 */
	private InternalResultsResponse<Location> createFetchResponse(Location location)
	{
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();
		response.addResult(location);

		return response;
	}

	/**
	 * The Constructor.
	 */
	public LocationBAIImplTest()
	{
		// setup the error response object.
		setErrResponse(new InternalResultsResponse<Location>());
		getErrResponse().addMessage(BAC_KEY, MessageSeverity.Error, MessageLevel.Field);
		getErrResponse().setStatus(Status.UnspecifiedError);
	}

	/**
	 * Test insert location.
	 */
	@Test
	public void testInsertLocation()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setModelAction(PersistanceActionEnum.INSERT);
		request.setLocation(location);

		InternalResultsResponse<Location> internalResponse = new InternalResultsResponse<Location>();
		internalResponse.addResult(location);

		Mockito.when(getMockLocationBAC().insertLocation(request)).thenReturn(internalResponse);

		LocationResponse response = getLocationBAI().insertLocation(request);
		CommonTestRoutines.assertResponse(response);

		// ensures the mock's insertLocation was called 1 time.
		Mockito.verify(getMockLocationBAC()).insertLocation(request);
	}

	/**
	 * Test insert risk.
	 */
	@Test
	public void testInsertRisk()
	{
		// Test Insert Success
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.INSERT);
		request.setRisk(risk);

		InternalResultsResponse<Risk> internalResponse = new InternalResultsResponse<Risk>();
		internalResponse.addResult(CommonTestRoutines.createDummyRisk());

		Mockito.when(getMockLocationBAC().updateRisk(request)).thenReturn(internalResponse);

		RiskResponse response = getLocationBAI().updateRisk(request);

		Assert.assertTrue("Message list should be empty", response.getMessageList().isEmpty());

		// Make sure the row inserted comes back
		Assert.assertTrue("New record should come back.", response.getRiskList().size() == 1);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockLocationBAC()).updateRisk(request);

	}

	/**
	 * Test insert risk note too big.
	 */
	@Test
	public void testInsertRiskNoteTooBig()
	{
		// Test Insert Success
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);

		risk.setRiskLevelNote("a");
		for (int i = 0; i < TWO_HUNDRED_FIFTY_SIX; i++)
		{
			risk.setRiskLevelNote(risk.getRiskLevelNote().concat("b"));
		}

		request.setRisk(risk);

		InternalResultsResponse<Risk> internalResponse = new InternalResultsResponse<Risk>();
		internalResponse.addResult(CommonTestRoutines.createDummyRisk());

		Mockito.when(getMockLocationBAC().updateRisk(request)).thenReturn(internalResponse);

		RiskResponse response = getLocationBAI().updateRisk(request);

		// Should fail because note is too big
		Assert.assertTrue(response.getMessageList().size() == 1);

		// Make sure the row inserted comes back
		Assert.assertTrue("New record should not come back.", response.getRiskList().size() == 0);
	}

	/**
	 * Test insert risk fields missing.
	 */
	@Test
	public void testInsertRiskFieldsMissing()
	{
		// Test Insert Success
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);

		risk.setRiskLevel(null);
		risk.setVersion(null);

		request.setRisk(risk);

		InternalResultsResponse<Risk> internalResponse = new InternalResultsResponse<Risk>();
		internalResponse.addResult(CommonTestRoutines.createDummyRisk());

		Mockito.when(getMockLocationBAC().updateRisk(request)).thenReturn(internalResponse);

		RiskResponse response = getLocationBAI().updateRisk(request);

		CommonTestRoutines.assertMessages(response, SENDSOLV_BASE_VALIDATOR_VERSION_REQUIRED,
				SENDSOLV_BASE_RISKVALIDATOR_RISKLEVEL_REQUIRED);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).updateRisk(request);

	}

	/**
	 * Test insert location with parent id null.
	 */
	@Test
	public void testInsertLocationWithParentIdNull()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setParentOrganizationId(null);
		location.setModelAction(PersistanceActionEnum.INSERT);

		request.setLocation(location);

		LocationResponse response = getLocationBAI().insertLocation(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).insertLocation(request);
	}

	/**
	 * Test insert location with invalid naicsc and sic.
	 */
	@Test
	public void testInsertLocationWithInvalidNaicscAndSic()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		CodeValue code = new CodeValue();
		code.setCode("12345");
		location.setNorthAmericanIndustryClassificationSystemCode(code);
		location.setModelAction(PersistanceActionEnum.INSERT);

		request.setLocation(location);

		Mockito.when(getMockIndustryClassificationDAC().fetchCodeValueByCode(Matchers.any(CodeValueRequest.class)))
				.thenReturn(new InternalResultsResponse<CodeValue>());

		LocationResponse response = getLocationBAI().insertLocation(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_NAICS,
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_INVALID_SIC);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).insertLocation(request);
	}

	/**
	 * Test insert location with naicsc and sicc null.
	 */
	@Test
	public void testInsertLocationWithNaicscAndSiccNull()
	{

		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setModelAction(PersistanceActionEnum.INSERT);
		location.setNorthAmericanIndustryClassificationSystemCode(null);
		location.setStandardIndustrialClassificationCode(null);

		request.setLocation(location);

		mockFetchOrganizationById(StatusEnum.ACTIVE);

		LocationResponse response = getLocationBAI().insertLocation(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAICSC_OR_SICC_REQUIRED);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).insertLocation(request);
	}

	/**
	 * Test insert location with naicsc and sicc codes null.
	 */
	@Test
	public void testInsertLocationWithNaicscAndSiccCodesNull()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setModelAction(PersistanceActionEnum.INSERT);

		location.getNorthAmericanIndustryClassificationSystemCode().setCode(null);
		location.getStandardIndustrialClassificationCode().setCode(null);

		request.setLocation(location);

		LocationResponse response = getLocationBAI().insertLocation(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_NAICSC_OR_SICC_REQUIRED);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).insertLocation(request);

	}

	/**
	 * Test insert location with exception.
	 */
	@Test
	public void testInsertLocationWithException()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setModelAction(PersistanceActionEnum.INSERT);

		request.setLocation(location);

		// Throws an exception when mock's insertLocation is called.
		Mockito.when(getMockLocationBAC().insertLocation(request)).thenThrow(new RuntimeException());

		LocationResponse response = getLocationBAI().insertLocation(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockLocationBAC()).insertLocation(request);
	}

	/**
	 * Test update location without id.
	 */
	@Test
	public void testUpdateLocationWithoutId()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setId(null);
		request.setLocation(location);
		location.setModelAction(PersistanceActionEnum.UPDATE);

		mockFetchCodeValueByCode();
		mockFetchOrganizationById(StatusEnum.ACTIVE);

		LocationResponse response = getLocationBAI().updateLocation(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).insertLocation(request);

	}

	/**
	 * Test update location.
	 */
	@Test
	public void testUpdateLocation()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setId(1);
		location.setModelAction(PersistanceActionEnum.UPDATE);

		request.setLocation(location);

		Mockito.when(getMockLocationBAC().updateLocation(request)).thenReturn(
				new InternalResultsResponse<Location>());

		LocationResponse response = getLocationBAI().updateLocation(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationBAC()).updateLocation(request);
	}

	/**
	 * Test update location exception.
	 */
	@Test
	public void testUpdateLocationException()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setId(1);
		location.setModelAction(PersistanceActionEnum.UPDATE);

		request.setLocation(location);

		Mockito.when(getMockLocationBAC().updateLocation(request)).thenThrow(new RuntimeException());

		LocationResponse response = getLocationBAI().updateLocation(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockLocationBAC()).updateLocation(request);
	}

	/**
	 * Test delete location.
	 */
	@Test
	public void testDeleteLocation()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		Location location = new Location();
		location.setId(THREE);
		request.setLocation(location);

		Mockito.when(getMockLocationBAC().deleteLocation(request)).thenReturn(
				new InternalResultsResponse<Location>());

		LocationResponse response = getLocationBAI().deleteLocation(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationBAC()).deleteLocation(request);
	}

	/**
	 * Test delete location exception.
	 */
	@Test
	public void testDeleteLocationException()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		Location location = new Location();
		location.setId(THREE);
		request.setLocation(location);

		Mockito.when(getMockLocationBAC().deleteLocation(request)).thenThrow(new RuntimeException());

		LocationResponse response = getLocationBAI().deleteLocation(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockLocationBAC()).deleteLocation(request);
	}

	/**
	 * Test delete location failure.
	 */
	@Test
	public void testDeleteLocationFailure()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();

		Location location = new Location();
		location.setId(THREE);
		request.setLocation(location);

		// returns the error object when deleteLocation is called.
		Mockito.when(getMockLocationBAC().deleteLocation(request)).thenReturn(getErrResponse());

		LocationResponse response = getLocationBAI().deleteLocation(request);

		CommonTestRoutines.assertMessages(response, BAC_KEY);

		Mockito.verify(getMockLocationBAC()).deleteLocation(request);
	}

	/**
	 * Test fetch location by id.
	 */
	@Test
	public void testFetchLocationById()
	{
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		FetchByIdRequest request = new FetchByIdRequest(location.getParentOrganizationId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockLocationBAC().fetchLocationById(request)).thenReturn(createFetchResponse(location));

		LocationResponse response = getLocationBAI().fetchLocationById(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationBAC()).fetchLocationById(request);
	}

	/**
	 * Test fetch location by id exception.
	 */
	@Test
	public void testFetchLocationByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(THREE);

		Mockito.when(getMockLocationBAC().fetchLocationById(request)).thenThrow(new RuntimeException());

		LocationResponse response = getLocationBAI().fetchLocationById(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockLocationBAC()).fetchLocationById(request);
	}

	/**
	 * Test fetch location by id no location id.
	 */
	@Test
	public void testFetchLocationByIdNoLocationId()
	{
		FetchByIdRequest request = new FetchByIdRequest();
		LocationResponse response = getLocationBAI().fetchLocationById(request);

		Assert.assertEquals(1, response.getMessageList().size());
		Assert.assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());

		// Ensures the mock's fetchLocationById was not called, due to validation error.
		Mockito.verify(getMockLocationBAC(), Mockito.never()).fetchLocationById(request);
	}

	/**
	 * Test fetch location by request.
	 */
	@Test
	public void testFetchLocationByRequest()
	{
		Mockito.when(getMockLocationBAC().fetchLocationByRequest(Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyLocation(CommonTestRoutines
						.createDummyOrganization())));

		LocationResponse response = getLocationBAI().fetchLocationByRequest(new PagedInquiryRequest());

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationBAC()).fetchLocationByRequest(Matchers.any(PagedInquiryRequest.class));
	}

	/**
	 * Test fetch location by request with error.
	 */
	@Test
	public void testFetchLocationByRequestWithError()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPageSize(null);
		request.setStartPage(null);
		Mockito.when(getMockLocationBAC().fetchLocationByRequest(request))
				.thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyLocation(CommonTestRoutines
						.createDummyOrganization())));

		LocationResponse response = getLocationBAI().fetchLocationByRequest(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_VALIDATOR_PAGING_PARAMETERS_REQUIRED);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).fetchLocationByRequest(request);
	}

	/**
	 * Test fetch location by request exception.
	 */
	@Test
	public void testFetchLocationByRequestException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();

		Mockito.when(getMockLocationBAC().fetchLocationByRequest(request)).thenThrow(new RuntimeException());

		LocationResponse response = getLocationBAI().fetchLocationByRequest(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockLocationBAC()).fetchLocationByRequest(request);
	}

	/**
	 * Test fetch location by organization.
	 */
	@Test
	public void testFetchLocationByOrganization()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(1);

		Mockito.when(getMockLocationBAC().fetchLocationByRequest(Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummyLocation(CommonTestRoutines
						.createDummyOrganization())));

		LocationResponse response = getLocationBAI().fetchLocationByOrganization(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationBAC()).fetchLocationByRequest(Matchers.any(PagedInquiryRequest.class));
	}

	/**
	 * Test fetch location by organization with error.
	 */
	@Test
	public void testFetchLocationByOrganizationWithError()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(null);

		Mockito.when(getMockLocationBAC().fetchLocationByRequest(request))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummyLocation(CommonTestRoutines
						.createDummyOrganization())));

		LocationResponse response = getLocationBAI().fetchLocationByOrganization(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_LOCATIONVALIDATOR_PARENT_ID_REQUIRED);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).fetchLocationByRequest(request);
	}

	/**
	 * Test fetch location by organization exception.
	 */
	@Test
	public void testFetchLocationByOrganizationException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(1);

		Mockito.when(getMockLocationBAC().fetchLocationByRequest(request)).thenThrow(new RuntimeException());

		LocationResponse response = getLocationBAI().fetchLocationByOrganization(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockLocationBAC()).fetchLocationByRequest(request);
	}

	/**
	 * Test apply status.
	 */
	@Test
	public void testApplyStatus()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();

		CommonTestRoutines.createDummyUserContext(request);
		Location location = new Location();
		location.setId(CommonTestRoutines.getRandomInt());
		location.setModelAction(PersistanceActionEnum.UPDATE);
		location.setStatus(StatusEnum.INACTIVE);
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		request.setLocation(location);

		InternalResultsResponse<Location> internalResponse = new InternalResultsResponse<Location>();
		internalResponse.setStatus(Status.OperationSuccess);

		mockFetchLocationById();
		mockFetchOrganizationById(StatusEnum.ACTIVE);

		Mockito.when(getMockLocationBAC().applyStatus(request)).thenReturn(internalResponse);

		LocationResponse response = getLocationBAI().applyStatus(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationBAC()).applyStatus(request);
	}

	/**
	 * Test apply status with error.
	 */
	@Test
	public void testApplyStatusWithError()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();

		CommonTestRoutines.createDummyUserContext(request);

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setId(CommonTestRoutines.getRandomInt());
		location.setModelAction(PersistanceActionEnum.UPDATE);
		location.setStatus(null);
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		request.setLocation(location);

		mockFetchLocationById();
		mockFetchOrganizationById(StatusEnum.INACTIVE);

		Mockito.when(getMockLocationBAC().applyStatus(request))
				.thenReturn(createFetchResponse(location));

		LocationResponse response = getLocationBAI().applyStatus(request);
		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_LOCATION_PARENT_NOT_ACTIVE,
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED);

		Mockito.verify(getMockLocationBAC(), Mockito.never()).applyStatus(request);
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

	/**
	 * Mock fetch location by id.
	 */
	private void mockFetchLocationById()
	{
		InternalResultsResponse<Location> internalResponse = new InternalResultsResponse<Location>();
		internalResponse.setStatus(Status.OperationSuccess);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		location.setStatus(StatusEnum.SETUP);
		internalResponse.addResult(location);

		Mockito.when(getMockLocationDAC().fetchLocationById(Matchers.any(FetchByIdRequest.class))).thenReturn(
				internalResponse);
	}

	/**
	 * Mock fetch organization by id.
	 */
	private void mockFetchOrganizationById(StatusEnum status)
	{
		InternalResultsResponse<Organization> internalResponse = new InternalResultsResponse<Organization>();
		internalResponse.setStatus(Status.OperationSuccess);
		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setStatus(status);
		internalResponse.addResult(organization);

		Mockito.when(getMockOrganizationDAC().fetchOrganizationById(Matchers.any(FetchByIdRequest.class))).thenReturn(
				internalResponse);
	}
}
