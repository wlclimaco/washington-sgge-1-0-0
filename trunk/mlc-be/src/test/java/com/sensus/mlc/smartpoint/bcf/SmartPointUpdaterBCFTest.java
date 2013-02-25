package com.sensus.mlc.smartpoint.bcf;

import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLightStatusNotificationRequest;
import static com.sensus.mlc.base.TestBaseUtil.createProcessRequest;
import static com.sensus.mlc.base.TestBaseUtil.getLatitudeRandom;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcl.ISmartPointUpdaterBCL;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightLocation;
import com.sensus.mlc.smartpoint.model.LightParameter;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;

/**
 * The Class SmartPointUpdaterBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/smartpoint/smartpointbcfimpltest.xml"})
public class SmartPointUpdaterBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant UPDATE_LIGHT_LAT_LNG. */
	private static final String UPDATE_LIGHT_LAT_LNG = "updateLightLatLng";

	/** The Constant DELETE_CUSTOM_SEARCH. */
	private static final String DELETE_CUSTOM_SEARCH = "deleteCustomSearch";

	/** The Constant UPDATE_LIGHT_STATUS. */
	private static final String UPDATE_LIGHT_STATUS = "updateLightStatus";

	/** The Constant UPDATE_LIGHT_PROTECTED. */
	private static final String UPDATE_LIGHT_PROTECTED = "updateLightProtected";

	/** The Constant RESET_MIN_MAX_VALUE. */
	private static final String RESET_MIN_MAX_VALUE = "resetMinMaxValue";

	/** The Constant UPSERT_LIGHT_PROPERTY. */
	private static final String UPSERT_LIGHT_PROPERTY = "upsertLightProperty";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.smartpointbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_PROTECT_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_PROTECT_REQUIRED =
			"sensus.mlc.smartpointvalidator.protect.required";

	/** The Constant SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED =
			"sensus.mlc.validator.selection.pagination.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED =
			"sensus.mlc.validator.is.monitored.required";

	/** The Constant SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED =
			"sensus.mlc.validator.property.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightrniid.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED =
			"sensus.mlc.smartpointvalidator.id.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED =
			"sensus.mlc.smartpointvalidator.latitude.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED =
			"sensus.mlc.smartpointvalidator.longitude.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE =
			"sensus.mlc.smartpointvalidator.invalid.latitude";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE =
			"sensus.mlc.smartpointvalidator.invalid.longitude";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_CUSTOM_SEARCH_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_CUSTOM_SEARCH_REQUIRED =
			"sensus.mlc.smartpointvalidator.custom.search.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_COLUMNS_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_COLUMNS_REQUIRED =
			"sensus.mlc.validator.column.list.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_LIST_TYPE_ENUM_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIST_TYPE_ENUM_REQUIRED =
			"sensus.mlc.smartpointvalidator.listtypeenum.required";

	/** The Constant TWO_HUNDRED_DOUBLE. */
	private static final Double TWO_HUNDRED_DOUBLE = 200.00;

	/** The smart point updater bcf. */
	private ISmartPointUpdaterBCF smartPointUpdaterBCF;

	/**
	 * Gets the smart point updater bcf.
	 * 
	 * @return the smart point updater bcf
	 */
	public ISmartPointUpdaterBCF getSmartPointUpdaterBCF()
	{
		return this.smartPointUpdaterBCF;
	}

	/**
	 * Sets the smart point updater bcf.
	 * 
	 * @param smartPointUpdaterBCF the new smart point updater bcf
	 */
	@Resource(name = "smartPointUpdaterBCFTarget")
	public void setSmartPointUpdaterBCF(ISmartPointUpdaterBCF smartPointUpdaterBCF)
	{
		this.smartPointUpdaterBCF = smartPointUpdaterBCF;
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
		setArea(getSmartPointUpdaterBCF(), LCAreaEnum.SMARTPOINT);
	}

	/**
	 * Reset mocks to smart point area.
	 */
	@After
	public void resetMocksToSmartPointArea()
	{
		resetMocksData(getSmartPointUpdaterBCF());
		setSmartPointArea();
	}

	/**
	 * Test update light protected.
	 */
	@Test
	public void testUpdateLightProtected()
	{
		// Validation situation - Protect Required
		LightRequest request = createLightRequest();
		LightResponse response = getSmartPointUpdaterBCF().updateLightProtected(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_PROTECT_REQUIRED);

		// Validation situation - Light id(s) Required
		request.setProtect(true);
		response = getSmartPointUpdaterBCF().updateLightProtected(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// Success situation
		request.addLight(createLight());
		request.setPaginationAllSelected(true);
		response = getSmartPointUpdaterBCF().updateLightProtected(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class,
				UPDATE_LIGHT_PROTECTED);
		response = getSmartPointUpdaterBCF().updateLightProtected(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, ISmartPointUpdaterBCL.class,
				UPDATE_LIGHT_PROTECTED);
		response = getSmartPointUpdaterBCF().updateLightProtected(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test update light status.
	 */
	@Test
	public void testUpdateLightStatus()
	{
		// Validation situatLight id(s) and is monitoreditored Required
		LightRequest request = createLightRequest();
		request.setIsMonitored(null);
		LightResponse response = getSmartPointUpdaterBCF().updateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);

		// Success situation
		request.addLight(createLight());
		request.setIsMonitored(true);
		request.setPaginationAllSelected(true);
		response = getSmartPointUpdaterBCF().updateLightStatus(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class,
				UPDATE_LIGHT_STATUS);
		response = getSmartPointUpdaterBCF().updateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, ISmartPointUpdaterBCL.class,
				UPDATE_LIGHT_STATUS);
		response = getSmartPointUpdaterBCF().updateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test upsert light property.
	 */
	@Test
	public void testUpsertLightProperty()
	{
		// Validation situation light and property required
		LightStatusNotificationRequest request = createLightStatusNotificationRequest();
		request.setLight(null);
		LightResponse response = getSmartPointUpdaterBCF().upsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED,
				SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED);

		// Validation situation RNI ID and property Required
		request = createLightStatusNotificationRequest();
		response = getSmartPointUpdaterBCF().upsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED,
				SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED);

		// Success situation
		List<LightParameter> params = new ArrayList<LightParameter>();
		params.add(new LightParameter(PropertyEnum.POLE_ID.getValue(), "1"));
		request.setLight(createLight());
		request.setLightParameters(params);
		response = getSmartPointUpdaterBCF().upsertLightProperty(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class,
				UPSERT_LIGHT_PROPERTY);
		response = getSmartPointUpdaterBCF().upsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, ISmartPointUpdaterBCL.class,
				UPSERT_LIGHT_PROPERTY);
		response = getSmartPointUpdaterBCF().upsertLightProperty(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test update light lat lng.
	 */
	@Test
	public void testUpdateLightLatLng()
	{
		// Validation situation - Light Required
		LightRequest request = createLightRequest();
		LightResponse response = getSmartPointUpdaterBCF().updateLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Light ID and Parameters Required
		request.addLight(new Light());
		response = getSmartPointUpdaterBCF().updateLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED);

		// Validation situation - Latitude and longitude Required
		request.getLights().clear();
		Light light = createLight();
		request.addLight(light);
		response = getSmartPointUpdaterBCF().updateLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LATITUDE_REQUIRED,
				SENSUS_MLC_SMARTPOINTVALIDATOR_LONGITUDE_REQUIRED);

		// Validation situation - Invalid Latitude/Longitude
		light.setLightLocation(new LightLocation(TWO_HUNDRED_DOUBLE, TWO_HUNDRED_DOUBLE));
		response = getSmartPointUpdaterBCF().updateLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LATITUDE,
				SENSUS_MLC_SMARTPOINTVALIDATOR_INVALID_LONGITUDE);

		// Success situation
		light.setLightLocation(new LightLocation(getLatitudeRandom(), getLatitudeRandom()));
		response = getSmartPointUpdaterBCF().updateLightLatLng(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class,
				UPDATE_LIGHT_LAT_LNG);
		response = getSmartPointUpdaterBCF().updateLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, ISmartPointUpdaterBCL.class,
				UPDATE_LIGHT_LAT_LNG);
		response = getSmartPointUpdaterBCF().updateLightLatLng(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test delete custom search.
	 */
	@Test
	public void testDeleteCustomSearch()
	{
		// Validation situation - Custom Search Required
		CustomSearchRequest request = TestBaseUtil.createCustomSearchRequest();
		CustomSearchResponse response = getSmartPointUpdaterBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_CUSTOM_SEARCH_REQUIRED);

		// Validation situation - Custom Search ID Required
		request.setCustomSearch(new CustomSearch());
		response = getSmartPointUpdaterBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Success situation
		request.setCustomSearch(TestBaseUtil.createCustomSearch());
		response = getSmartPointUpdaterBCF().deleteCustomSearch(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class,
				DELETE_CUSTOM_SEARCH);
		response = getSmartPointUpdaterBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, ISmartPointUpdaterBCL.class,
				DELETE_CUSTOM_SEARCH);
		response = getSmartPointUpdaterBCF().deleteCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test insert custom search.
	 */
	@Test
	public void testInsertCustomSearch()
	{
		// Validation situation - Custom Search Required
		CustomSearchRequest request = TestBaseUtil.createCustomSearchRequest();
		CustomSearchResponse response = getSmartPointUpdaterBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_CUSTOM_SEARCH_REQUIRED);

		// Validation situation - Custom Search Name and Columns Required
		request.setCustomSearch(new CustomSearch());
		response = getSmartPointUpdaterBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Validation situation - Custom Search Name and Columns Required
		request.setCustomSearch(TestBaseUtil.createCustomSearch());
		request.getCustomSearch().setListColumn(null);
		response = getSmartPointUpdaterBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_COLUMNS_REQUIRED);

		// Success situation
		request.setCustomSearch(TestBaseUtil.createCustomSearch());
		response = getSmartPointUpdaterBCF().insertCustomSearch(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class);
		response = getSmartPointUpdaterBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, ISmartPointUpdaterBCL.class);
		response = getSmartPointUpdaterBCF().insertCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test reset min max value.
	 */
	@Test
	public void testResetMinMaxValue()
	{
		// Validation situation - Light id(s) Required
		LightRequest request = createLightRequest();
		LightResponse response = getSmartPointUpdaterBCF().resetMinMaxValue(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// Success situation
		request.setPaginationAllSelected(true);
		response = getSmartPointUpdaterBCF().resetMinMaxValue(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class,
				RESET_MIN_MAX_VALUE);
		response = getSmartPointUpdaterBCF().resetMinMaxValue(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, ISmartPointUpdaterBCL.class,
				RESET_MIN_MAX_VALUE);
		response = getSmartPointUpdaterBCF().resetMinMaxValue(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test insert column filters.
	 */
	@Test
	public void testInsertColumnFilters()
	{
		// Validation situation - List Type Enum Required
		ColumnFilterRequest request = TestBaseUtil.createColumnFilterRequest();
		request.setListTypeEnum(null);
		ColumnFilterResponse response = getSmartPointUpdaterBCF().insertColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIST_TYPE_ENUM_REQUIRED);

		// Success situation
		request = TestBaseUtil.createColumnFilterRequest();
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		response = getSmartPointUpdaterBCF().insertColumnFilters(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, ISmartPointUpdaterBCL.class);
		request = TestBaseUtil.createColumnFilterRequest();
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		response = getSmartPointUpdaterBCF().insertColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, ISmartPointUpdaterBCL.class);
		request = TestBaseUtil.createColumnFilterRequest();
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		response = getSmartPointUpdaterBCF().insertColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test insert csv process.
	 */
	@Test
	public void testInsertCSVProcess()
	{
		// Success situation
		LightSelectionRequest request = TestBaseUtil.createLightSelectionRequest();
		ProcessResponse response = getSmartPointUpdaterBCF().insertCSVProcess(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.ERROR, IProcessBCF.class);
		request = createProcessRequest();
		response = getSmartPointUpdaterBCF().insertCSVProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointUpdaterBCF(), SituationsEnum.EXCEPTION, IProcessBCF.class);
		request = createProcessRequest();
		response = getSmartPointUpdaterBCF().insertCSVProcess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION);
	}
}
