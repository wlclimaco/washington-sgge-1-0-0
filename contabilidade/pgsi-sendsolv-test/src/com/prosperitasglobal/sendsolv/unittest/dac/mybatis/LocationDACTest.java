package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.Email;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.criteria.InquiryCriteria;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.unittest.util.CommonSdnTestRoutines;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LocationDACTest.
 */
public class LocationDACTest extends AbstractTestBaseDAC
{

	/** The Constant SECOND_LOC_NEEDS_TO_MATCH_LIST. */
	private static final String SECOND_LOC_NEEDS_TO_MATCH_LIST = "Second Loc needs to match List";

	/** The Constant FIRST_LOC_NEEDS_TO_MATCH_LIST. */
	private static final String FIRST_LOC_NEEDS_TO_MATCH_LIST = "First Loc needs to match List";

	/** The Constant HUNDRED. */
	private static final Integer HUNDRED = 100;

	/** The Constant COUNTRY_CODE. */
	private static final String COUNTRY_CODE = "BRA";

	/** The Constant CITY_NAME. */
	private static final String CITY_NAME = "Oz";

	/** The Constant EMAIL_ADDRESS_UPDATED. */
	private static final String EMAIL_ADDRESS_UPDATED = "emailAddressUpdated";

	/** The Constant FOUR_HUNDRED_ONE. */
	private static final String FOUR_HUNDRED_ONE = "401";

	/** The Constant UPDATED_NAME. */
	private static final String UPDATED_NAME = "Updated Name";

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant FOUR. */
	private static final Integer FOUR = 4;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The dummy modify user. */
	private String dummyModifyUser = "MDOE";

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteBusiness.sql", false);
		Mockito.reset(getMockSdnCheckerBAC());
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
	 * Test fetchAllLocations with paging.
	 */
	@Test
	public void testfetchAllLocations()
	{
		Integer pageSize = TWO;
		Integer totalLocs = TWENTY;

		// Add 20 locs
		List<Location> locList = insertLocations(totalLocs);

		// get first page
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(ZERO);
		request.setPreQueryCount(true);
		request.setStatusList(Arrays.asList(StatusEnum.ACTIVE));
		InternalResultsResponse<Location> response = getLocationDAC().fetchLocationByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue("First page size needs to be > 0", response.getResultsList().size() > ZERO);
		Assert.assertEquals("first page size needs to be = 2", pageSize, (Integer)response.getResultsList().size());

		Assert.assertTrue("List of Business Product Plans need to be > 0", response.getResultsList().get(0)
				.getBusinessProductPlanList().size() > ZERO);
		// Assert.assertTrue("List of Frequency Based Event need to be > 0", response.getResultsList().get(0)
		// .getFrequencyBasedEventList().size() > ZERO);

		Assert.assertEquals(FIRST_LOC_NEEDS_TO_MATCH_LIST, locList.get(ZERO).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_LOC_NEEDS_TO_MATCH_LIST, locList.get(ONE).getId(), response.getResultsList()
				.get(ONE).getId());
		Assert.assertEquals("Number of rows need to match", totalLocs, response.getResultsSetInfo()
				.getTotalRowsAvailable());

		// go to next page
		request.setStartPage(ONE);

		response = getLocationDAC().fetchLocationByRequest(request);
		Assert.assertTrue("second page size needs to be > 0", response.getResultsList().size() > ZERO);
		Assert.assertEquals("second page size needs to be = 2", pageSize, (Integer)response.getResultsList().size());
		Assert.assertEquals(FIRST_LOC_NEEDS_TO_MATCH_LIST, locList.get(TWO).getId(), response.getResultsList()
				.get(ZERO).getId());
		Assert.assertEquals(SECOND_LOC_NEEDS_TO_MATCH_LIST, locList.get(THREE).getId(),
				response.getResultsList().get(ONE).getId());

		// one more page
		request.setStartPage(TWO);

		response = getLocationDAC().fetchLocationByRequest(request);
		Assert.assertTrue("third page size needs to be > 0", response.getResultsList().size() > ZERO);
		Assert.assertEquals("third page size needs to be = 2", pageSize, (Integer)response.getResultsList().size());
		Assert.assertEquals(FIRST_LOC_NEEDS_TO_MATCH_LIST, locList.get(FOUR).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_LOC_NEEDS_TO_MATCH_LIST, locList.get(FIVE).getId(),
				response.getResultsList().get(ONE).getId());
	}

	/**
	 * Test fetch location that has product plan.
	 */
	@Test
	public void testFetchLocationThatHasProductPlan()
	{
		BusinessProductPlan businessProductPlan = insertBusinessProductPlan();

		PagedInquiryRequest inquiryRequest = new PagedInquiryRequest();
		InquiryCriteria criteria = new InquiryCriteria();
		criteria.setHasProductPlan(true);
		inquiryRequest.setInquiryCriteria(criteria);
		InternalResultsResponse<Location> locationResponse = getLocationDAC().fetchLocationByRequest(inquiryRequest);
		CommonTestRoutines.assertResultResponse(locationResponse);

		Assert.assertEquals("The id should be the same.", locationResponse.getFirstResult().getId(),
				businessProductPlan.getLocationId());

	}

	/**
	 * Test fetching a frequency based event by id.
	 */
	@Test
	public void testFetchFrequencyBasedEventById()
	{
		Location insertedLocation = insertLocation();
		FrequencyBasedEvent event = insertedLocation.getFrequencyBasedEventList().get(0);
		getLocationDAC().fetchFrequencyBasedEventById(event.getId());
		// Currently we can't really test this, because we can't build events. Events are
		// maintained outside the system.
	}

	/**
	 * Test fetchAllLocations with paging.
	 */
	@Test
	public void testFetchLocationById()
	{
		Location insertedLocation = insertLocation();
		InternalResultsResponse<Location> response =
				getLocationDAC().fetchLocationById(new FetchByIdRequest(insertedLocation.getId()));
		CommonTestRoutines.assertResultResponse(response);

		response.getFirstResult().getFrequencyBasedEventList();

		// No insert on the FrequencyBasedEvent DAC
		// Assert.assertTrue(!listOfEvents.isEmpty());
	}

	/**
	 * Test fetch location by id check sdn status code.
	 */
	@Test
	public void testFetchLocationByIdCheckSDNStatusCode()
	{
		Location location = insertLocation();

		prepareMockSdnCheckerBAC(location);

		InternalResultsResponse<Location> response2 =
				getLocationDAC().fetchLocationById(new FetchByIdRequest(location.getId()));
		CommonTestRoutines.assertResultResponse(response2);

		// Verify the SDN status code should be equals.
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, response2.getFirstResult().getSDNStatus());

	}

	/**
	 * Test fetch location by request check sdn status code.
	 */
	@Test
	public void testFetchLocationByRequestCheckSDNStatusCode()
	{

		List<Location> locationList = insertLocations(TWO);

		prepareMockSdnCheckerBAC(locationList.get(0));

		PagedInquiryRequest inquiryRequest = new PagedInquiryRequest();
		inquiryRequest.setStatusList(Arrays.asList(StatusEnum.ACTIVE));
		InternalResultsResponse<Location> locationResponse = getLocationDAC().fetchLocationByRequest(inquiryRequest);
		CommonTestRoutines.assertResultResponse(locationResponse);

		// Verify the SDN status code should match.
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, locationResponse.getResultsList().get(0)
				.getSDNStatus());
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, locationResponse.getResultsList().get(1)
				.getSDNStatus());

	}

	// Test insert Location (includes Contacts - Email,Phone, Address)
	/**
	 * Test insert location.
	 */
	@Test
	public void testFetchLocationsByOrganization()
	{
		Integer pageSize = TWO;
		Integer totalLocs = TWENTY;

		// Add 20 location, each under a different Organization
		List<Location> locList = insertLocations(totalLocs);

		// Read from Db passing parentId = parent id of first location
		PagedInquiryRequest request = new PagedInquiryRequest();
		Integer parentId = locList.get(ZERO).getParentOrganizationId();
		request.setParentId(parentId);
		request.setPageSize(pageSize);
		request.setStartPage(ZERO);
		request.setPreQueryCount(true);

		InternalResultsResponse<Location> response = getLocationDAC().fetchLocationByRequest(request);

		Assert.assertTrue("first page size needs to be > 0", response.getResultsList().size() > ZERO);
		Assert.assertEquals("first page size needs to be = 1", 1, response.getResultsList().size());
		Assert.assertEquals(FIRST_LOC_NEEDS_TO_MATCH_LIST, locList.get(ZERO).getId(),
				response.getResultsList().get(ZERO).getId());

	}

	/*
	 * Test insert Location (includes Contacts - Email,Phone, Address)
	 */
	/**
	 * Test insert location.
	 */
	@Test
	public void testInsertLocation()
	{
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		InternalResultsResponse<Location> response = getLocationDAC().insertLocation(location);

		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test update location.
	 */
	@Test
	public void testUpdateLocation()
	{
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		// location.getNorthAmericanIndustryClassificationSystemCode().setId(ONE);
		// location.getStandardIndustrialClassificationCode().setId(ONE);

		getLocationDAC().insertLocation(location);

		location.setModelAction(PersistanceActionEnum.UPDATE);
		location.setName(UPDATED_NAME);
		location.setModifyUser(dummyModifyUser);
		location.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());

		for (Contact contact : location.getContactList())
		{
			contact.setModelAction(PersistanceActionEnum.UPDATE);

			switch (contact.getContactType())
			{
				case OTHER:
					((Phone)contact).setNumber("2349987");
					((Phone)contact).setExtension("A401");
					((Phone)contact).getCountry().setCode(COUNTRY_CODE);
					break;
				case EMAIL_PERSONAL:
					((Email)contact).setEmailAddress(EMAIL_ADDRESS_UPDATED);
					break;
				case POSTAL_HOME:
					((Address)contact).setCityName(CITY_NAME);
					break;
				case PHONE_WORK:
					((Phone)contact).setNumber(FOUR_HUNDRED_ONE);
					((Phone)contact).setExtension(FOUR_HUNDRED_ONE);
					((Phone)contact).getCountry().setCode(COUNTRY_CODE);
					break;
				case EMAIL_WORK:
					((Email)contact).setEmailAddress(EMAIL_ADDRESS_UPDATED);
					break;
				case POSTAL_WORK:
					((Address)contact).setCityName(CITY_NAME);
					break;
				default:
					break;
			}
		}

		InternalResultsResponse<Location> response = getLocationDAC().updateLocation(location);
		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test delete location.
	 */
	@Test
	public void testDeleteLocation()
	{
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		InternalResultsResponse<Location> response = getLocationDAC().insertLocation(location);
		CommonTestRoutines.assertResponse(response);

		InternalResponse deleteResponse = getLocationDAC().deleteLocation(location);
		CommonTestRoutines.assertResponse(deleteResponse);

		response = getLocationDAC().fetchLocationById(new FetchByIdRequest(location.getId()));

		Assert.assertEquals("Location cannot be found after delete", Status.NoRowsFoundError,
				response.getStatus());
	}

	/**
	 * Test update location ol.
	 */
	@Test
	public void testUpdateLocationOL()
	{
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		InternalResultsResponse<Location> response = getLocationDAC().insertLocation(location);

		location.setModelAction(PersistanceActionEnum.UPDATE);
		location.setName(UPDATED_NAME);

		response = getLocationDAC().updateLocation(location);
		Assert.assertEquals("Should NOT get an Optimistic Locking Error, but got " + response.getStatus(),
				Status.OperationSuccess, response.getStatus());

		response = getLocationDAC().fetchLocationById(new FetchByIdRequest(location.getId()));
		location = response.getFirstResult();

		Assert.assertTrue("Version should be > 0 ", location.getVersion() > 0);

		location.setModelAction(PersistanceActionEnum.UPDATE);

		// Set the version number to 100 to generate an OL error.
		location.setVersion(HUNDRED);
		response = getLocationDAC().updateLocation(location);
		Assert.assertEquals("Should get an Optimistic Locking Error, but got " + response.getStatus(),
				Status.OptimisticLockingError, response.getStatus());
	}

	/**
	 * Test update risk.
	 */
	@Test
	public void testUpdateRisk()
	{
		// Insert an organization first.
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		InternalResultsResponse<Location> locationResponse =
				getLocationDAC().insertLocation(location);

		// update risk
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setParentKey(locationResponse.getFirstResult().getId());
		risk.setParentKeyType(BusinessTypeEnum.LOCATION);
		risk.setVersion(locationResponse.getFirstResult().getVersion());
		request.setRisk(risk);

		InternalResponse internalResponse = getLocationDAC().updateRisk(request);
		Assert.assertEquals("internalResponse.getStatus() should be Success", Status.OperationSuccess,
				internalResponse.getStatus());
	}

	/**
	 * Test apply status.
	 */
	@Test
	public void testApplyStatus()
	{
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		getLocationDAC().insertLocation(location);

		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		location.setStatus(StatusEnum.INACTIVE);
		request.setLocation(location);

		InternalResponse response = getLocationDAC().applyLocationStatus(location);
		CommonTestRoutines.assertResponse(response);

		InternalResultsResponse<Location> result =
				getLocationDAC().fetchLocationById(new FetchByIdRequest(location.getId()));
		CommonTestRoutines.assertResponse(result);

		Assert.assertEquals("The status person needs to be the same", location.getStatus(), result.getFirstResult()
				.getStatus());
	}

	/**
	 * Insert locations.
	 *
	 * @param numberOfLocsToInsert the number of locs to insert
	 * @return the list< location>
	 */
	private List<Location> insertLocations(Integer numberOfLocsToInsert)
	{
		ArrayList<Location> locList = new ArrayList<Location>();

		for (int i = 0; i < numberOfLocsToInsert; i++)
		{
			Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

			InternalResultsResponse<Location> response = getLocationDAC().insertLocation(location);

			if (!response.isInError())
			{
				insertBusinessProductPlans(location, 2);
				locList.add(location);
			}
		}

		return locList;
	}

	private void insertBusinessProductPlans(Location location, Integer numberToInsert)
	{
		Product product = insertProduct();
		Payer payer = insertPayer();

		BusinessProductPlan businessProductPlan = null;
		for (int i = 0; i < numberToInsert; i++)
		{
			businessProductPlan = CommonTestRoutines.createDummyBusinessProductPlan(product, location, payer);
			getProductPlanDAC().insertBusinessProductPlan(businessProductPlan);
		}
	}

	/**
	 * Prepare the Sdn Checker BAC's Mock for Location in order to return the expected result.
	 *
	 * @param business
	 */
	private void prepareMockSdnCheckerBAC(Business business)
	{
		SdnCheckerRequest<Business> request = new SdnCheckerRequest<Business>();
		request.setPersonOrBusiness(business);

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();
		response.addResult(CommonSdnTestRoutines.createDummySdnStatusHistory(SDNStatusEnum.PENDING_INVESTIGATION));

		Mockito.when(getMockSdnCheckerBAC().fetchCurrentSdnStatusHistory(Matchers.any(SdnStatusHistoryRequest.class)))
				.thenReturn(response);

	}

}
