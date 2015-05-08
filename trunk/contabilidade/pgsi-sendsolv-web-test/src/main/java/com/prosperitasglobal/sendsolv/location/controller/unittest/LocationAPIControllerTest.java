package com.prosperitasglobal.sendsolv.location.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;

public class LocationAPIControllerTest extends AbstractTestBase
{
	private static final String API_LOCATION_DELETE = "/api/location/delete";

	private static final String API_LOCATION_EDIT = "/api/location/edit";

	private static final String API_LOCATION_FETCHALL = "/api/location/fetchall";

	private static final String API_LOCATION_FETCH = "/api/location/fetch";

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private static final String API_LOCATION_ADD = "/api/location/add";

	/** The Constant FETCHMESSAGES. */
	private static final String FETCHMESSAGES = "/fetchmessages?localeLanguage=en_Us";

	private static final String APPLY_STATUS = "/api/member/applyStatus";

	private ILocationBAI locationBAI;
	private static final Logger LOG = LoggerFactory.getLogger(LocationAPIControllerTest.class);

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	@Test
	public void fetchAllLocations()
	{

		// Mock Response 1
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		for (int i = 0; i < 5; i++)
		{
			Location location = new Location();
			location.setBusinessType(BusinessTypeEnum.LOCATION);
			location.setId(i);
			response.getLocationList().add(location);
		}

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().fetchLocationByRequest(
						Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"sortExpressions\":[],\"preQueryCount\":true,\"startPage\":0,\"pageSize\":25}");

			performTest(API_LOCATION_FETCHALL).andExpect(
					jsonPath("$.locationList", hasSize(5)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new LocationResponse();
			response.setOperationSuccess(true);
			response.setLocationList(new ArrayList<Location>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLocationBAI().fetchLocationByRequest(
							Matchers.any(PagedInquiryRequest.class)))
					.thenReturn(response);

			performTest(API_LOCATION_FETCHALL).andExpect(
					jsonPath("$.locationList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void fetchLocations()
	{

		// Mock Response 1
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		Location location = new Location();
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setId(1);
		response.getLocationList().add(location);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().fetchLocationById(
						Matchers.any(FetchByIdRequest.class)))
				.thenReturn(response);

		try
		{

			setData("{\"id\":25}");

			performTest(API_LOCATION_FETCH).andExpect(
					jsonPath("$.locationList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new LocationResponse();
			response.setOperationSuccess(true);
			response.setLocationList(new ArrayList<Location>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLocationBAI().fetchLocationById(
							Matchers.any(FetchByIdRequest.class)))
					.thenReturn(response);

			performTest(API_LOCATION_FETCH).andExpect(
					jsonPath("$.locationList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void InsertLocation()
	{

		// Mock Response 1
		LocationResponse response = new LocationResponse();

		response.setLocationList(new ArrayList<Location>());
		Location location = new Location();
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setId(1);
		response.getLocationList().add(location);

		Mockito.when(
				getLocationBAI().insertLocation(
						Matchers.any(LocationMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"location\":{\"id\":\"11816\",\"country\":{\"code\":\"USA\"},\"employerIdentificationNumber\":\"\",\"name\":\"10EB_location\",\"northAmericanIndustryClassificationSystemCode\":{\"code\":\"334517\"},\"standardIndustrialClassificationCode\":{\"code\":\"2731\"},\"numberOfEmployees\":{\"id\":23},\"numberOfMigrantWorkers\":{\"id\":14},\"statusValue\":1,\"modelAction\":\"UPDATE\",\"parentOrganizationId\":\"11829\",\"businessTypeValue\":2,\"version\":0,\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":123456789,\"extension\":100,\"country\":{\"code\":\"USA\"},\"priorityValue\":1}]}}");

			performTest(API_LOCATION_ADD).andExpect(
					jsonPath("$.locationList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			response = new LocationResponse();
			response.setOperationSuccess(false);
			response.setLocationList(new ArrayList<Location>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLocationBAI().insertLocation(
							Matchers.any(LocationMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_LOCATION_ADD).andExpect(
					jsonPath("$.locationList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void UpdateLocation()
	{

		// Mock Response 1
		LocationResponse response = new LocationResponse();

		response.setLocationList(new ArrayList<Location>());
		Location location = new Location();
		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setId(1);
		response.getLocationList().add(location);
		response.setOperationSuccess(true);

		Mockito.when(
				getLocationBAI().updateLocation(
						Matchers.any(LocationMaintenanceRequest.class))).thenReturn(response);

		try
		{

			setData("{\"location\":{\"id\":\"11816\",\"country\":{\"code\":\"USA\"},\"employerIdentificationNumber\":\"\",\"name\":\"10EB_location\",\"northAmericanIndustryClassificationSystemCode\":{\"code\":\"334517\"},\"standardIndustrialClassificationCode\":{\"code\":\"2731\"},\"numberOfEmployees\":{\"id\":23},\"numberOfMigrantWorkers\":{\"id\":14},\"statusValue\":1,\"modelAction\":\"UPDATE\",\"parentOrganizationId\":\"11829\",\"businessTypeValue\":2,\"version\":0,\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":123456789,\"extension\":100,\"country\":{\"code\":\"USA\"},\"priorityValue\":1}]}}");
			performTest(API_LOCATION_EDIT).andExpect(
					jsonPath("$.locationList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			response = new LocationResponse();
			response.setOperationSuccess(false);
			response.setLocationList(new ArrayList<Location>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLocationBAI().updateLocation(
							Matchers.any(LocationMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_LOCATION_EDIT).andExpect(
					jsonPath("$.locationList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void DeleteLocation()
	{

		// Mock Response 1
		setData("{\"location\":{\"id\":\"11816\",\"country\":{\"code\":\"USA\"},\"employerIdentificationNumber\":\"\",\"name\":\"10EB_location\",\"northAmericanIndustryClassificationSystemCode\":{\"code\":\"334517\"},\"standardIndustrialClassificationCode\":{\"code\":\"2731\"},\"numberOfEmployees\":{\"id\":23},\"numberOfMigrantWorkers\":{\"id\":14},\"statusValue\":1,\"modelAction\":\"UPDATE\",\"parentOrganizationId\":\"11829\",\"businessTypeValue\":2,\"version\":0,\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":123456789,\"extension\":100,\"country\":{\"code\":\"USA\"},\"priorityValue\":1}]}}");
		LocationResponse response = new LocationResponse();
		response.setLocationList(new ArrayList<Location>());
		response.setOperationSuccess(true);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().deleteLocation(
						Matchers.any(LocationMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			performTest(API_LOCATION_DELETE).andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Error situation ==================================

			response = new LocationResponse();
			response.setOperationSuccess(false);
			response.setLocationList(new ArrayList<Location>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLocationBAI().deleteLocation(
							Matchers.any(LocationMaintenanceRequest.class)))
					.thenReturn(response);

			performTest(API_LOCATION_DELETE).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);
		}

		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	@Test
	public void ApplyStatus()
	{

		// Mock Response 1
		setData("{\"location\":{\"id\":\"11816\",\"country\":{\"code\":\"USA\"},\"employerIdentificationNumber\":\"\",\"name\":\"10EB_location\",\"northAmericanIndustryClassificationSystemCode\":{\"code\":\"334517\"},\"standardIndustrialClassificationCode\":{\"code\":\"2731\"},\"numberOfEmployees\":{\"id\":23},\"numberOfMigrantWorkers\":{\"id\":14},\"statusValue\":1,\"modelAction\":\"UPDATE\",\"parentOrganizationId\":\"11829\",\"businessTypeValue\":2,\"version\":0,\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":123456789,\"extension\":100,\"country\":{\"code\":\"USA\"},\"priorityValue\":1}]}}");

		String s =
				new String(
						"{\"location\":{\"id\":\"11816\",\"country\":{\"code\":\"USA\"},\"employerIdentificationNumber\":\"\",\"name\":\"10EB_location\",\"northAmericanIndustryClassificationSystemCode\":{\"code\":\"334517\"},\"standardIndustrialClassificationCode\":{\"code\":\"2731\"},\"numberOfEmployees\":{\"id\":23},\"numberOfMigrantWorkers\":{\"id\":14},\"statusValue\":1,\"modelAction\":\"UPDATE\",\"parentOrganizationId\":\"11829\",\"businessTypeValue\":2,\"version\":0,\"contactList\":[{\"modelAction\":\"INSERT\",\"addressLine1\":\"Street Address\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"addressLine4\":\"\",\"cityName\":\"City\",\"stateProvinceRegion\":{\"id\":1},\"country\":{\"code\":\"USA\"},\"postalCode\":\"38082-0243\",\"priority\":1,\"type\":\"address\",\"contactTypeValue\":7,\"id\":null},{\"contactTypeValue\":\"2\",\"id\":\"\",\"type\":\"phone\",\"modelAction\":\"INSERT\",\"number\":123456789,\"extension\":100,\"country\":{\"code\":\"USA\"},\"priorityValue\":1}]}}");

		LocationResponse response = new LocationResponse();

		List<Location> locationList = new ArrayList<Location>();

		Location location = new Location();
		location.setId(1);

		locationList.add(location);

		response.setLocationList(locationList);
		response.setOperationSuccess(true);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getLocationBAI().applyStatus(
						Matchers.any(LocationMaintenanceRequest.class)))
				.thenReturn(response);

		try
		{

			performTest("/api/location/applyStatus").andExpect(
					jsonPath("$.locationList[0].id", equalTo(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Failure situation ==================================

			response = new LocationResponse();
			response.setOperationSuccess(false);
			response.setLocationList(new ArrayList<Location>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getLocationBAI().applyStatus(
							Matchers.any(LocationMaintenanceRequest.class)))
					.thenReturn(response);

			performTest("/api/location/applyStatus").andExpect(
					jsonPath("$.locationList", hasSize(0))).andExpect(
					jsonPath("$.operationSuccess", equalTo(false)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	/**
	 * Fetch list.
	 *
	 * @throws Exception the exception
	 */
	// @Test
	public void fetchMessages() throws Exception
	{

		getMockMvc().perform(
				get(FETCHMESSAGES))
				.andExpect(status().isOk());
	}

}
