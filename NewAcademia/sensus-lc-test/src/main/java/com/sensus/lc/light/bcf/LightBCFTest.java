package com.sensus.lc.light.bcf;

import static com.sensus.lc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.lc.base.TestBaseUtil.RANDOM;
import static com.sensus.lc.base.TestBaseUtil.createFetchByIdRequest;
import static com.sensus.lc.base.TestBaseUtil.createLightMassUpdateRequest;
import static com.sensus.lc.base.TestBaseUtil.createUserContext;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.MaintenanceResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.LastOperationalData;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightSchedule;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;
import com.sensus.lc.light.model.response.ChangesResponse;
import com.sensus.lc.light.model.response.ConfigurationMaintenanceResponse;
import com.sensus.lc.light.model.response.CountResponse;
import com.sensus.lc.light.model.response.FetchAllResponse;
import com.sensus.lc.light.model.response.GeocodeLightInfoResponse;
import com.sensus.lc.light.model.response.LastOperationalDataMaintenanceResponse;
import com.sensus.lc.light.model.response.LightResponse;
import com.sensus.lc.light.model.response.ScheduleMaintenanceResponse;

/**
 * The Class LightBCFTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/mlc/light/lightbcfimpletest.xml"})
public class LightBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant COUNT_ALL_BY_REQUEST. */
	private static final String COUNT_ALL_BY_REQUEST = "countAllByRequest";

	/** The Constant COLUMN_SORT_EXPRESSION_LIGHT_ID. */
	private static final String COLUMN_SORT_EXPRESSION_LIGHT_ID = "light_id";

	/** The Constant FETCH_ALL_BY_REQUEST. */
	private static final String FETCH_ALL_BY_REQUEST = "fetchAllByRequest";

	/** The Constant ARBITRARY_PAGE_SIZE_15. */
	private static final Integer ARBITRARY_PAGE_SIZE_15 = 15;

	/** The Constant ARBITRARY_PAGE_SIZE_20. */
	private static final Integer ARBITRARY_PAGE_SIZE_20 = 20;

	/** The Constant UPDATE_LIGHT_MASS. */
	private static final String UPDATE_LIGHT_MASS = "updateLightMass";

	/** The Constant UPDATE_LIGHT_BASE. */
	private static final String UPDATE_LIGHT_BASE = "updateLightBase";

	/** The Constant RESET_MIN_MAX_VALUE. */
	private static final String RESET_MIN_MAX_VALUE = "resetMinMaxValue";

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant USER_CONTEXT_REQUIRED. */
	private static final String USER_CONTEXT_REQUIRED = "sensus.mlc.uservalidator.user.context.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_RNI_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_RNI_REQUIRED = "sensus.mlc.validator.id.rni.required";

	/** The Constant DEFAULT_LIGHT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_LIGHT_BCF_EXCEPTION_MSG = "sensus.mlc.lightbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_REQUIRED =
			"sensus.mlc.validator.selection.pagination.required";

	/** The Constant SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED = "sensus.mlc.page.size.required";

	/** The Constant SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED = "sensus.mlc.validator.parent.id.required";

	/** The light bcf. */
	private ILightBCF lightBCF;

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setLightArea();
	}

	/**
	 * Sets the light area.
	 */
	public void setLightArea()
	{
		setArea(getLightBCF(), LCAreaEnum.LIGHT);
	}

	/**
	 * Reset mocks to light area.
	 */
	@After
	public void resetMocksToLightArea()
	{
		resetMocksData(getLightBCF());
		setLightArea();
	}

	/**
	 * Gets the light bcf.
	 * 
	 * @return the light bcf
	 */
	public ILightBCF getLightBCF()
	{
		return lightBCF;
	}

	/**
	 * Sets the light bcf.
	 * 
	 * @param lightBCF the new light bcf
	 */
	@Resource(name = "lightBCFTarget")
	public void setLightBCF(ILightBCF lightBCF)
	{
		this.lightBCF = lightBCF;
	}

	/**
	 * Test fetch all by request.
	 */
	@Test
	public void testFetchAllByRequest()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest(userContext);
		FetchAllResponse response = getLightBCF().fetchAllByRequest(request);
		Assert.assertNotNull(response);

		// Test Validation Situation - User Context equals Null
		request = new LightRequest();
		response = getLightBCF().fetchAllByRequest(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new LightRequest();
		request.setUserContext(new UserContext());
		response = getLightBCF().fetchAllByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new LightRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().fetchAllByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Fail with an error situation
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class, FETCH_ALL_BY_REQUEST);
		request = TestBaseUtil.createLightRequest(userContext);
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		response = getLightBCF().fetchAllByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test Fail with a exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class, FETCH_ALL_BY_REQUEST);
		request = TestBaseUtil.createLightRequest(userContext);
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		response = getLightBCF().fetchAllByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	@Test
	public void testFetchAttributeChanges()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest(userContext);
		ChangesResponse response = getLightBCF().fetchAttributeChanges(request);
		Assert.assertNotNull(response);
	}

	/**
	 * Test count all by request.
	 */
	@Test
	public void testCountAllByRequest()
	{
		UserContext userContext = createUserContext();

		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest(userContext);
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		CountResponse response = getLightBCF().countAllByRequest(request);
		Assert.assertNotNull(request);
		assertTrue(response.isOperationSuccess());
		Assert.assertTrue("The value should be greater than 0", response.getCount() > 0);

		// Test Validation Situation - User Context equals Null
		request = new LightRequest();
		response = getLightBCF().countAllByRequest(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID is null
		request = new LightRequest();
		request.setUserContext(new UserContext());
		response = getLightBCF().countAllByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new LightRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().countAllByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Validation Situation - Page size is required
		request = TestBaseUtil.createLightRequest(userContext);
		request.setPageSize(ARBITRARY_PAGE_SIZE_20);
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		response = getLightBCF().countAllByRequest(request);
		assertMessages(response, SENSUS_MLC_PAGE_SIZE_VALIDATOR_REQUIRED);

		// Test Validation Situation - Sort Expression is required
		request = new LightRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		response = getLightBCF().countAllByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Fail with an error situation
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class, COUNT_ALL_BY_REQUEST);
		request = TestBaseUtil.createLightRequest(userContext);
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		response = getLightBCF().countAllByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test Fail with an exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class, COUNT_ALL_BY_REQUEST);
		request = TestBaseUtil.createLightRequest(userContext);
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		response = getLightBCF().countAllByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch by id.
	 */
	@Test
	public void testFetchById()
	{
		UserContext userContext = createUserContext();

		// Test validation UserContext null
		FetchByIdRequest request = new FetchByIdRequest(null);
		LightResponse lightResponse = getLightBCF().fetchById(request);
		assertMessages(lightResponse, USER_CONTEXT_REQUIRED);

		// Test validation UserId null
		request = createFetchByIdRequest(new UserContext(), null);
		lightResponse = getLightBCF().fetchById(request);
		assertMessages(lightResponse, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test validation Tenant null
		request = createFetchByIdRequest(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))), null);
		lightResponse = getLightBCF().fetchById(request);
		assertMessages(lightResponse, SENSUS_MLC_VALIDATOR_REQUIRED, Arrays.asList("Tenant"));

		// Test validation light id or rni_id
		request = createFetchByIdRequest(userContext, null);
		lightResponse = getLightBCF().fetchById(request);
		assertMessages(lightResponse, SENSUS_MLC_VALIDATOR_ID_RNI_REQUIRED);

		// Test success light id
		request = createFetchByIdRequest(userContext, ONE);
		lightResponse = getLightBCF().fetchById(request);
		Assert.assertNotNull(lightResponse);

		// Test Fail with an error situation
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class);
		lightResponse = getLightBCF().fetchById(request);
		assertFalse(lightResponse.isOperationSuccess());
		assertMessages(lightResponse, ERROR_CODE);

		// Test Fail with an exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class);
		lightResponse = getLightBCF().fetchById(request);
		assertFalse(lightResponse.isOperationSuccess());
		assertMessages(lightResponse, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test update base.
	 */
	@Test
	public void testUpdateLightMass()
	{
		UserContext userContext = createUserContext();

		// Creating a new object Light
		Light light = TestBaseUtil.createLight();

		// Success situation
		LightMassUpdateRequest request = createLightMassUpdateRequest(userContext, light);
		request.setPaginationAllSelected(true);

		MaintenanceResponse response = getLightBCF().updateLightMass(request);
		assertTrue(response.isOperationSuccess());

		// Test Validation Situation - User Context equals Null
		request = new LightMassUpdateRequest(light);
		response = getLightBCF().updateLightMass(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new LightMassUpdateRequest(light);
		request.setUserContext(new UserContext());
		response = getLightBCF().updateLightMass(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new LightMassUpdateRequest(light);
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().updateLightMass(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Validation Situation - Object Light null
		request = createLightMassUpdateRequest(userContext, null);
		request.setPaginationAllSelected(true);

		response = getLightBCF().updateLightMass(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Fail with an error situation
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class, UPDATE_LIGHT_MASS);

		light = TestBaseUtil.createLight();
		request = createLightMassUpdateRequest(userContext, light);
		request.setPaginationAllSelected(true);
		response = getLightBCF().updateLightMass(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test fail with an exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class, UPDATE_LIGHT_MASS);

		request = createLightMassUpdateRequest(userContext, light);
		request.setPaginationAllSelected(true);
		response = getLightBCF().updateLightMass(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test light base.
	 */
	@Test
	public void testUpdateLightBase()
	{
		// Creating the user context
		UserContext userContext = createUserContext();

		// Creating a new object Light
		Light light = TestBaseUtil.createLight();

		// Success situation
		LightMaintenanceRequest request = TestBaseUtil.createLightMaintenanceRequest(userContext, light);
		MaintenanceResponse response = getLightBCF().updateLightBase(request);
		assertTrue(response.isOperationSuccess());

		// Test Validation Situation - User Context equals Null
		request = new LightMaintenanceRequest(light);
		response = getLightBCF().updateLightBase(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Test Validation Situation - User ID null
		request = new LightMaintenanceRequest(light);
		request.setUserContext(new UserContext());
		response = getLightBCF().updateLightBase(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Test Validation Situation - Tenant null
		request = new LightMaintenanceRequest(light);
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().updateLightBase(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Validation Situation - Object Light null
		request = TestBaseUtil.createLightMaintenanceRequest(userContext, null);
		response = getLightBCF().updateLightBase(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Test Fail with an error situation
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class, UPDATE_LIGHT_BASE);

		light = TestBaseUtil.createLight();
		request = TestBaseUtil.createLightMaintenanceRequest(userContext, light);
		response = getLightBCF().updateLightBase(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Test fail with an exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class, UPDATE_LIGHT_BASE);

		request = TestBaseUtil.createLightMaintenanceRequest(userContext, light);
		response = getLightBCF().updateLightBase(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test reset min max value.
	 */
	@Test
	public void testResetMinMaxValue()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		LightResponse response = getLightBCF().resetMinMaxValue(request);
		assertTrue(response.isOperationSuccess());

		// Validation Situation - User Context null
		request = new LightRequest();
		response = getLightBCF().resetMinMaxValue(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - User Id null
		request = new LightRequest();
		request.setUserContext(new UserContext());
		response = getLightBCF().resetMinMaxValue(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation Situation - Tenant null
		request = new LightRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().resetMinMaxValue(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Error situation
		resetMocksToLightArea();
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class, RESET_MIN_MAX_VALUE);
		request = TestBaseUtil.createLightRequest(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		response = getLightBCF().resetMinMaxValue(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class,
				RESET_MIN_MAX_VALUE);
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		response = getLightBCF().resetMinMaxValue(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all to map by request.
	 */
	@Test
	public void testFetchAllToMapByRequest()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		GeocodeLightInfoResponse response = getLightBCF().fetchAllToMapByRequest(request);
		assertTrue(response.isOperationSuccess());

		// Validate situation - User context null
		request = new LightRequest();
		response = getLightBCF().fetchAllToMapByRequest(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - User id null
		request = new LightRequest();
		request.setUserContext(new UserContext());
		response = getLightBCF().fetchAllToMapByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation Situation - Tenant null
		request = new LightRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().fetchAllToMapByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Error situation
		request = TestBaseUtil.createLightRequest(TestBaseUtil.createUserContext());
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class);
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		response = getLightBCF().fetchAllToMapByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class);
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		response = getLightBCF().fetchAllToMapByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test fetch map bounds by request.
	 */
	@Test
	public void testFetchMapBoundsByRequest()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest(TestBaseUtil.createUserContext());
		request.addSortExpressions(TestBaseUtil.createMLCSortExpression(COLUMN_SORT_EXPRESSION_LIGHT_ID, false));
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);
		GeocodeLightInfoResponse response = getLightBCF().fetchMapBoundsByRequest(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - User Context null
		request = new LightRequest();
		response = getLightBCF().fetchMapBoundsByRequest(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - User ID null
		request = new LightRequest();
		request.setUserContext(new UserContext());
		response = getLightBCF().fetchMapBoundsByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new LightRequest();
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().fetchMapBoundsByRequest(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Exception situation
		request = TestBaseUtil.createLightRequest(TestBaseUtil.createUserContext());
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class);
		request.setPageSize(ARBITRARY_PAGE_SIZE_15);

		response = getLightBCF().fetchMapBoundsByRequest(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test update last operational data.
	 */
	@Test
	public void testUpdateLastOperationalData()
	{
		// Success situation
		LastOperationalDataMaintenanceRequest request = new LastOperationalDataMaintenanceRequest();
		request.setLastOperationalData(new LastOperationalData());
		request.getLastOperationalData().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(TestBaseUtil.createUserContext());
		LastOperationalDataMaintenanceResponse response = getLightBCF().updateLastOperationalData(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - User Context null
		request = new LastOperationalDataMaintenanceRequest();
		request.setLastOperationalData(new LastOperationalData());
		request.getLastOperationalData().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		response = getLightBCF().updateLastOperationalData(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - User ID null
		request = new LastOperationalDataMaintenanceRequest();
		request.setLastOperationalData(new LastOperationalData());
		request.getLastOperationalData().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(new UserContext());
		response = getLightBCF().updateLastOperationalData(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new LastOperationalDataMaintenanceRequest();
		request.setLastOperationalData(new LastOperationalData());
		request.getLastOperationalData().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().updateLastOperationalData(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Parent ID null
		request = new LastOperationalDataMaintenanceRequest();
		request.setLastOperationalData(new LastOperationalData());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightBCF().updateLastOperationalData(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED);

		// Error situation
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class);
		request.setLastOperationalData(new LastOperationalData());
		request.getLastOperationalData().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		response = getLightBCF().updateLastOperationalData(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class);
		request.setLastOperationalData(new LastOperationalData());
		request.getLastOperationalData().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		response = getLightBCF().updateLastOperationalData(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test update Configuration.
	 */
	@Test
	public void testUpdateConfiguration()
	{
		// Success situation
		ConfigurationMaintenanceRequest request = new ConfigurationMaintenanceRequest();
		request.setConfiguration(new Configuration());
		request.getConfiguration().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(TestBaseUtil.createUserContext());
		ConfigurationMaintenanceResponse response = getLightBCF().updateConfiguration(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - User Context null
		request = new ConfigurationMaintenanceRequest();
		request.setConfiguration(new Configuration());
		request.getConfiguration().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		response = getLightBCF().updateConfiguration(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - User ID null
		request = new ConfigurationMaintenanceRequest();
		request.setConfiguration(new Configuration());
		request.getConfiguration().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(new UserContext());
		response = getLightBCF().updateConfiguration(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ConfigurationMaintenanceRequest();
		request.setConfiguration(new Configuration());
		request.getConfiguration().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().updateConfiguration(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Parent ID null
		request = new ConfigurationMaintenanceRequest();
		request.setConfiguration(new Configuration());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightBCF().updateConfiguration(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED);

		// Validation situation - Configuration null
		request = new ConfigurationMaintenanceRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightBCF().updateConfiguration(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED);

		// Error situation
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class);
		request.setConfiguration(new Configuration());
		request.getConfiguration().setParentId(RANDOM.nextInt(NUMBER_RANGE));

		response = getLightBCF().updateConfiguration(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class);
		request.setConfiguration(new Configuration());
		request.getConfiguration().setParentId(RANDOM.nextInt(NUMBER_RANGE));

		response = getLightBCF().updateConfiguration(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);
	}

	@Test
	public void testUpdateSchedule()
	{
		// Success situation
		ScheduleMaintenanceRequest request = new ScheduleMaintenanceRequest();
		request.setSchedule(new LightSchedule());
		request.getSchedule().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(TestBaseUtil.createUserContext());
		ScheduleMaintenanceResponse response = getLightBCF().updateSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Validation situation - User Context null
		request = new ScheduleMaintenanceRequest();
		request.setSchedule(new LightSchedule());
		request.getSchedule().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		response = getLightBCF().updateSchedule(request);
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation situation - User ID null
		request = new ScheduleMaintenanceRequest();
		request.setSchedule(new LightSchedule());
		request.getSchedule().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(new UserContext());
		response = getLightBCF().updateSchedule(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - Tenant null
		request = new ScheduleMaintenanceRequest();
		request.setSchedule(new LightSchedule());
		request.getSchedule().setParentId(RANDOM.nextInt(NUMBER_RANGE));
		request.setUserContext(new UserContext(String.valueOf(RANDOM.nextInt(NUMBER_RANGE))));
		response = getLightBCF().updateSchedule(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Parent ID null
		request = new ScheduleMaintenanceRequest();
		request.setSchedule(new LightSchedule());
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightBCF().updateSchedule(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED);

		// Validation situation - LightSchedule null
		request = new ScheduleMaintenanceRequest();
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getLightBCF().updateSchedule(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_PARENT_ID_REQUIRED);

		// Error situation
		setSituation(getLightBCF(), SituationsEnum.ERROR, ILightBCL.class);
		request.setSchedule(new LightSchedule());
		request.getSchedule().setParentId(RANDOM.nextInt(NUMBER_RANGE));

		response = getLightBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToLightArea();

		// Exception situation
		setSituation(getLightBCF(), SituationsEnum.EXCEPTION, ILightBCL.class);
		request.setSchedule(new LightSchedule());
		request.getSchedule().setParentId(RANDOM.nextInt(NUMBER_RANGE));

		response = getLightBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_LIGHT_BCF_EXCEPTION_MSG);

	}
}
