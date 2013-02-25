package com.sensus.mlc.smartpoint.bcl;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createStatusMessage;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.smartpoint.dac.ISmartPointDAC;
import com.sensus.mlc.smartpoint.model.Column;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Filter;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightBlinkEnum;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SmartPointColumnEnum;
import com.sensus.mlc.smartpoint.model.SmartPointFilterEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.GuaranteeLightExistenceRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class SmartPointUpdaterBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/smartpoint/smartpointbclimpltest.xml"})
public class SmartPointUpdaterBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant NAME. */
	private static final String NAME = "name";

	/** The Constant ONE. */
	private static final String ONE = "1";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant RESET_MIN_MAX_FAILED. */
	private static final String RESET_MIN_MAX_FAILED =
			"sensus.mlc.smartpointbclimpl.update.min.max.failed";

	/** The smart point updater bcl. */
	private ISmartPointUpdaterBCL smartPointUpdaterBCL;

	/**
	 * Gets the smart point updater bcl.
	 *
	 * @return the smart point updater bcl
	 */
	public ISmartPointUpdaterBCL getSmartPointUpdaterBCL()
	{
		return smartPointUpdaterBCL;
	}

	/**
	 * Sets the smart point updater bcl.
	 *
	 * @param smartPointUpdaterBCL the new smart point updater bcl
	 */
	@Resource(name = "smartPointUpdaterBCLTarget")
	public void setSmartPointUpdaterBCL(ISmartPointUpdaterBCL smartPointUpdaterBCL)
	{
		this.smartPointUpdaterBCL = smartPointUpdaterBCL;
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setSmartPointArea();
	}

	/**
	 * Sets the smart point area.
	 */
	public void setSmartPointArea()
	{
		setArea(getSmartPointUpdaterBCL(), LCAreaEnum.SMARTPOINT);
	}

	/**
	 * Reset the smart point area.
	 */
	@After
	public void resetMocksToSmartPointArea()
	{
		resetMocksData(getSmartPointUpdaterBCL());
		setSmartPointArea();
	}

	/**
	 * Test verify communication message.
	 */
	@Test
	public void testVerifyCommunicationMessage()
	{
		UserContext userContext = TestBaseUtil.createUserContext();
		userContext.setUserId("System");
		getSmartPointUpdaterBCL().verifyCommunicationMessage(userContext);
	}

	/**
	 * Test update light status.
	 */
	@Test
	public void testUpdateLightStatus()
	{
		LightRequest lightRequest = createLightRequest();
		lightRequest.setTenant(new Tenant(1));

		// Test without lights
		lightRequest.setLightStatusEnumValue(LightStatusEnum.OVERRIDE.getValue());
		InternalResultsResponse<Light> response = getSmartPointUpdaterBCL().updateLightStatus(lightRequest);
		assertResultResponse(response);

		// Test with lights
		lightRequest.setLights(insertLights());

		// Status Active
		lightRequest.setLightStatusEnumValue(LightStatusEnum.ACTIVE.getValue());

		response = getSmartPointUpdaterBCL().updateLightStatus(lightRequest);
		assertResultResponse(response);

		// Status Deactivated
		lightRequest.setLightStatusEnumValue(LightStatusEnum.DEACTIVATED.getValue());
		lightRequest.setLightBlinkEnum(LightBlinkEnum.NONE);

		response = getSmartPointUpdaterBCL().updateLightStatus(lightRequest);
		assertResultResponse(response);

		// Status Maintenance
		lightRequest.setLightStatusEnumValue(LightStatusEnum.MAINTENANCE.getValue());
		lightRequest.setLightBlinkEnum(LightBlinkEnum.NONE);

		response = getSmartPointUpdaterBCL().updateLightStatus(lightRequest);
		assertResultResponse(response);
	}

	/**
	 * Test update light protected.
	 */
	@Test
	public void testUpdateLightProtected()
	{
		// Success situation
		List<Integer> selectionPaginationIds = new ArrayList<Integer>();
		selectionPaginationIds.add(1);

		Tenant tenant = new Tenant();
		tenant.setRniCode(ONE);

		LightRequest lightRequest = createLightRequest();
		lightRequest.setSelectionPaginationIds(selectionPaginationIds);
		lightRequest.setTenant(tenant);
		lightRequest.setProtect(true);

		InternalResultsResponse<Light> response = getSmartPointUpdaterBCL().updateLightProtected(lightRequest);
		assertResultResponse(response);

		// Errors situations
		// IProcessBCL
		setSituation(getSmartPointUpdaterBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);
		response = getSmartPointUpdaterBCL().updateLightProtected(lightRequest);
		assertTrue(response.isInError());
		assertMessages(response, ERROR_CODE);
		resetMocksToSmartPointArea();

		// ISmartpointDAC
		setSituation(getSmartPointUpdaterBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"updateLightProtected");
		response = getSmartPointUpdaterBCL().updateLightProtected(lightRequest);
		assertTrue(response.isInError());
		assertMessages(response, ERROR_CODE);
		resetMocksToSmartPointArea();
	}

	/**
	 * Test insert status message.
	 */
	@Test
	public void testInsertStatusMessage()
	{
		// Success situation
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(createLight());

		List<StatusMessage> statusMessages = new ArrayList<StatusMessage>();
		statusMessages.add(createStatusMessage());

		lightRequest.setStatusMessages(statusMessages);
		InternalResponse response = getSmartPointUpdaterBCL().insertStatusMessage(lightRequest);
		assertResponse(response);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Error situation
		lightRequest.getLights().clear();
		response = getSmartPointUpdaterBCL().insertStatusMessage(lightRequest);
		assertEquals(Status.ExceptionError, response.getStatus());

		lightRequest.getStatusMessages().clear();
		response = getSmartPointUpdaterBCL().insertStatusMessage(lightRequest);
		assertEquals(Status.ExceptionError, response.getStatus());
	}

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		CustomSearch customSearch = new CustomSearch();
		customSearch.setName(NAME);
		customSearch.setDescription("custom search description");

		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.GROUP_ID);
		param.setValue(ONE);

		customSearch.getSearchParameters().add(param);

		CustomSearchRequest request = new CustomSearchRequest(getDummyUserContext());
		request.setCustomSearch(customSearch);

		// good insert
		InternalResultsResponse<CustomSearch> response = getSmartPointUpdaterBCL().insertCustomSearch(request);
		assertResultResponse(response);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertEquals("should return the same name  ", customSearch.getName(), response.getFirstResult().getName());
	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		CustomSearch customSearch = new CustomSearch();
		customSearch.setName(NAME);
		customSearch.setDescription("description");

		CustomSearchRequest request = new CustomSearchRequest(getDummyUserContext());
		request.setCustomSearch(customSearch);

		// good insert
		InternalResultsResponse<CustomSearch> response = getSmartPointUpdaterBCL().insertCustomSearch(request);
		assertEquals(Status.OperationSuccess, response.getStatus());
		assertNotNull("should return the custom search ", response.getFirstResult());
		assertEquals("should return the same name ", customSearch.getName(), response.getFirstResult().getName());

		InternalResponse responseDelete = getSmartPointUpdaterBCL().deleteCustomSearch(request);

		assertEquals(Status.OperationSuccess, responseDelete.getStatus());
	}

	/**
	 * Test update light lat lng.
	 */
	@Test
	public void testUpdateLightLatLng()
	{
		LightRequest request = createLightRequest();
		request.addLight(new Light(1));
		InternalResponse response = getSmartPointUpdaterBCL().updateLightLatLng(request);
		assertNotNull(response);
		assertFalse(response.isInError());
	}

	/**
	 * Test update light.
	 */
	@Test
	public void testUpdateLight()
	{
		LightRequest request = createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResponse response = getSmartPointUpdaterBCL().updateLight(request);
		assertNotNull(response);
		assertFalse(response.isInError());
	}

	/**
	 * Test update light schedule.
	 */
	@Test
	public void testUpdateLightSchedule()
	{
		LightRequest request = createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResponse response = getSmartPointUpdaterBCL().updateLightSchedule(request);
		assertNotNull(response);
		assertFalse(response.isInError());
	}

	/**
	 * Test update light last operation data.
	 */
	@Test
	public void testUpdateLightLastOperationData()
	{
		LightRequest request = createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResponse response = getSmartPointUpdaterBCL().updateLightLastOperationData(request);
		assertNotNull(response);
		assertFalse(response.isInError());
	}

	/**
	 * Test update light location.
	 */
	@Test
	public void testUpdateLightLocation()
	{
		LightRequest request = createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResponse response = getSmartPointUpdaterBCL().updateLightLocation(request);
		assertNotNull(response);
		assertFalse(response.isInError());
	}

	/**
	 * Test update light configuration.
	 */
	@Test
	public void testUpdateLightConfiguration()
	{
		LightRequest request = createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResponse response = getSmartPointUpdaterBCL().updateLightConfiguration(request);
		assertNotNull(response);
		assertFalse(response.isInError());
	}

	/**
	 * Test insert light schedule.
	 */
	@Test
	public void testInsertLightSchedule()
	{
		GuaranteeLightExistenceRequest request = new GuaranteeLightExistenceRequest();
		request.addLight(TestBaseUtil.createLight());
		getSmartPointUpdaterBCL().insertLightSchedule(request);
	}
	/**
	 * Test reset min max.
	 */
	@Test
	public void testResetMinMax()
	{
		// Success situation
		LightRequest lightRequest = createLightRequest();
		lightRequest.addLight(new Light(1));
		InternalResponse response = getSmartPointUpdaterBCL().resetMinMaxValue(lightRequest);
		assertResponse(response);
		assertEquals(Status.OperationSuccess, response.getStatus());

		// Errors situations
		// IProcessBCL
		resetMocksToSmartPointArea();
		setSituation(getSmartPointUpdaterBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);
		response = getSmartPointUpdaterBCL().resetMinMaxValue(lightRequest);
		assertTrue(response.isInError());
		assertMessages(response, ERROR_CODE);
		resetMocksToSmartPointArea();

		// ISmartpointDAC
		resetMocksToSmartPointArea();
		setSituation(getSmartPointUpdaterBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"resetMinMaxValue");
		response = getSmartPointUpdaterBCL().resetMinMaxValue(lightRequest);
		assertTrue(response.isInError());
		assertMessages(response, RESET_MIN_MAX_FAILED);
		resetMocksToSmartPointArea();
	}

	/**
	 * Test insert column filters.
	 */
	@Test
	public void testInsertColumnFilters()
	{
		ColumnFilterRequest request = new ColumnFilterRequest();

		// Test Success
		// The default case cannot be tested because the ListTypeEnum has just one attribute
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);

		List<Filter> filters = new ArrayList<Filter>();

		Filter filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.ADDRESS);
		filter.setDisplayOrder(0);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.ALARM_TYPE);
		filter.setDisplayOrder(1);
		filters.add(filter);

		request.setFilters(filters);

		List<Column> listColumn = new ArrayList<Column>();
		Column column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.RNI_ID);
		column.setDisplayOrder(0);
		listColumn.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.LAMP_TYPE);
		column.setDisplayOrder(0);
		listColumn.add(column);

		request.setListColumn(listColumn);

		InternalResponse response = getSmartPointUpdaterBCL().insertColumnFilters(request);
		assertResponse(response);
		assertNotNull(response);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test insert column filters.
	 */
	@Test
	public void testInsertColumnsFiltersToCustomSearch()
	{
		ColumnFilterRequest request = new ColumnFilterRequest();

		// Test Success
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);

		List<Filter> filters = new ArrayList<Filter>();

		Filter filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.ADDRESS);
		filter.setDisplayOrder(0);
		filters.add(filter);

		filter = new Filter();
		filter.setFilterEnum(SmartPointFilterEnum.ALARM_TYPE);
		filter.setDisplayOrder(1);
		filters.add(filter);

		request.setFilters(filters);

		List<Column> listColumn = new ArrayList<Column>();
		Column column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.RNI_ID);
		column.setDisplayOrder(0);
		listColumn.add(column);

		column = new Column();
		column.setColumnEnum(SmartPointColumnEnum.LAMP_TYPE);
		column.setDisplayOrder(0);
		listColumn.add(column);

		request.setListColumn(listColumn);

		InternalResponse response = getSmartPointUpdaterBCL().insertColumnsFiltersToCustomSearch(request);
		assertResponse(response);
		assertNotNull(response);
		assertEquals(Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Insert lights.
	 *
	 * @return the list
	 */
	private List<Light> insertLights()
	{
		List<Light> lightList = new ArrayList<Light>();
		Light light = new Light();

		light.setId(1);
		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setLightStatusEnum(LightStatusEnum.ALARM);
		light.setLightIntensityEnum(LightIntensityEnum.LEVEL_5);
		light.setCurrentStatusMessage(statusMessage);
		light.setRniId(1);
		light.setSmartPointId(1);
		lightList.add(light);

		light = new Light();
		light.setId(2);
		statusMessage = new StatusMessage();
		statusMessage.setLightStatusEnum(LightStatusEnum.WARNING);
		light.setLightIntensityEnum(LightIntensityEnum.LEVEL_6);
		light.setCurrentStatusMessage(statusMessage);
		light.setRniId(2);
		light.setSmartPointId(2);
		lightList.add(light);

		return lightList;
	}

	/**
	 * Assert result response.
	 *
	 * @param response the response
	 */
	private void assertResultResponse(InternalResultsResponse<?> response)
	{
		assertNotNull("Response object should not to be null", response);
		assertNotNull("Message list object should not to be null", response.getMessageInfoList());
		assertEquals(0, response.getMessageInfoList().size());
	}

	/**
	 * Gets the dummy user context.
	 *
	 * @return the dummy user context
	 */
	private UserContext getDummyUserContext()
	{
		UserContext result = new UserContext();
		result.setId(1);
		result.setUserId("QAT");
		return result;
	}
}
