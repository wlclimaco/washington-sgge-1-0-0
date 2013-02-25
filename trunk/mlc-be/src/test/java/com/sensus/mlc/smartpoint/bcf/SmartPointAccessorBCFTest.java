package com.sensus.mlc.smartpoint.bcf;

import static com.sensus.mlc.base.TestBaseUtil.createCustomSearch;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createLight;
import static com.sensus.mlc.base.TestBaseUtil.createLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createUserContext;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.MLCSortExpression;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightOrderByEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CountLightResponse;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryCustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryGeocodeSmartpointInfoResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.smartpoint.model.response.LightingConfigurationsResponse;
import com.sensus.mlc.smartpoint.model.response.PropertyValidValuesResponse;
import com.sensus.mlc.smartpoint.model.response.StatusMessageResponse;

/**
 * The Class SmartPointAccessorBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/smartpoint/smartpointbcfimpltest.xml"})
public class SmartPointAccessorBCFTest extends AbstractTestBaseBusiness
{
	/** The Constant ARBITRARY_PROCESS_ID_99. */
	private static final Integer ARBITRARY_PROCESS_ID_99 = 99;

	/** The Constant ARBITRARY_DEVICE_LIGHT_COUNT. */
	private static final int ARBITRARY_DEVICE_LIGHT_COUNT = 200;

	/** The Constant FETCH_LIGHT_HISTORY_HEADER. */
	private static final String FETCH_LIGHT_HISTORY_HEADER = "fetchLightHistoryHeader";

	/** The Constant FETCH_LIGHT_HISTORY. */
	private static final String FETCH_LIGHT_HISTORY = "fetchLightHistory";

	/** The Constant FETCH_LIGHT_BY_RNI_ID. */
	private static final String FETCH_LIGHT_BY_RNI_ID = "fetchLightByRniId";

	/** The Constant FETCH_CURRENT_ALARM_STATUS_MESSAGES_BY_LIGHT. */
	private static final String FETCH_CURRENT_ALARM_STATUS_MESSAGES_BY_LIGHT = "fetchCurrentAlarmStatusMessagesByLight";

	/** The Constant FETCH_STATUS_MESSAGE_BY_ID. */
	private static final String FETCH_STATUS_MESSAGE_BY_ID = "fetchStatusMessageById";

	/** The Constant GENERATE_LIGHT_HISTORY_FILE_CSV. */
	private static final String GENERATE_LIGHT_HISTORY_FILE_CSV = "generateLightHistoryFileCSV";

	/** The Constant CSV_FILE_NAME. */
	private static final String CSV_FILE_NAME = "csvFileName";

	/** The Constant FETCH_LIGHT_BY_ID. */
	private static final String FETCH_LIGHT_BY_ID = "fetchLightById";

	/** The Constant FETCH_ALL_LIGHTS. */
	private static final String FETCH_ALL_LIGHTS = "fetchAllLights";

	/** The Constant COUNT_LIGHTS. */
	private static final String COUNT_LIGHTS = "countLights";

	/** The Constant GENERATE_FILE_CSV. */
	private static final String GENERATE_FILE_CSV = "generateFileCSV";

	/** The Constant FETCH_ALL_COLUMN_FILTERS. */
	private static final String FETCH_ALL_COLUMN_FILTERS = "fetchAllColumnFilters";

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.smartpointbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED =
			"sensus.mlc.smartpointvalidator.id.required";

	/** The Constant SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED =
			"sensus.mlc.validator.property.required";

	/** The Constant SENSUS_MLC_USERVALIDATOR_LIST_TYPE_ENUM_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIST_TYPE_ENUM_REQUIRED =
			"sensus.mlc.smartpointvalidator.listtypeenum.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_REQUIRED =
			"sensus.mlc.smartpointvalidator.statusmessage.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_ID_REQUIRED =
			"sensus.mlc.smartpointvalidator.statusmessage.id.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightrniid.required";

	/** The smart point accessor bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	@Resource(name = "smartPointAccessorBCFTarget")
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
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
		setArea(getSmartPointAccessorBCF(), LCAreaEnum.SMARTPOINT);
	}

	/**
	 * Reset mocks to smart point area.
	 */
	@After
	public void resetMocksToSmartPointArea()
	{
		resetMocksData(getSmartPointAccessorBCF());
		setSmartPointArea();
	}

	/**
	 * Test fetch all smart points.
	 */
	@Test
	public void testFetchAllSmartPoints()
	{
		// Success situation without sort expression
		InquiryLightRequest request = createInquiryLightRequest(createUserContext());
		InquiryLightResponse response = getSmartPointAccessorBCF().fetchAllLights(request);
		assertTrue(response.isOperationSuccess());

		// Success situation without sort expression field and property
		request = createInquiryLightRequest(createUserContext());
		request.setSortExpression(new MLCSortExpression());
		response = getSmartPointAccessorBCF().fetchAllLights(request);
		assertTrue(response.isOperationSuccess());

		// Success situation
		request = createInquiryLightRequest(createUserContext());
		MLCSortExpression sortExpression = new MLCSortExpression();
		sortExpression.setField(LightOrderByEnum.STATUS.getValue());
		sortExpression.setIsProperty(false);
		request.setSortExpression(sortExpression);
		response = getSmartPointAccessorBCF().fetchAllLights(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_ALL_LIGHTS);
		response = getSmartPointAccessorBCF().fetchAllLights(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_ALL_LIGHTS);
		response = getSmartPointAccessorBCF().fetchAllLights(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch light by id.
	 */
	@Test
	public void testFetchLightById()
	{
		// Validation situation light required
		LightRequest request = createLightRequest();
		LightResponse response = getSmartPointAccessorBCF().fetchLightById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation light id required
		request.addLight(new Light());
		response = getSmartPointAccessorBCF().fetchLightById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED);

		// Success situation
		request.getFirstLight().setId(1);
		response = getSmartPointAccessorBCF().fetchLightById(request);
		assertResultResponse(response);
		assertNotNull(response.getLights());
		assertEquals(1, response.getLights().size());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_LIGHT_BY_ID);
		response = getSmartPointAccessorBCF().fetchLightById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_LIGHT_BY_ID);
		response = getSmartPointAccessorBCF().fetchLightById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test count lights.
	 */
	@Test
	public void testCountLights()
	{
		// Success situation
		LightingControlRequest request = new LightingControlRequest(createUserContext());
		CountLightResponse response = getSmartPointAccessorBCF().countLights(request);
		assertNotNull(response);
		assertEquals(ARBITRARY_DEVICE_LIGHT_COUNT, response.getDeviceLightCount().intValue());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				COUNT_LIGHTS);
		response = getSmartPointAccessorBCF().countLights(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				COUNT_LIGHTS);
		response = getSmartPointAccessorBCF().countLights(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch update light status.
	 */
	@Test
	public void testFetchUpdateLightStatus()
	{
		// Validate situation - Process Required
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.setProcess(null);
		LightResponse response = getSmartPointAccessorBCF().fetchUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validate situation - Process ID Required
		request = TestBaseUtil.createProcessRequest();
		Process process = TestBaseUtil.createProcess(TestBaseUtil.createLight(), null);
		process.setId(null);
		request.setProcess(process);
		response = getSmartPointAccessorBCF().fetchUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		process = TestBaseUtil.createProcess(TestBaseUtil.createLight(), null);
		process.setId(ARBITRARY_PROCESS_ID_99);

		// Success situation
		request = TestBaseUtil.createProcessRequest();
		request.setProcess(process);
		response = getSmartPointAccessorBCF().fetchUpdateLightStatus(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class);
		request = TestBaseUtil.createProcessRequest();
		request.setProcess(process);
		response = getSmartPointAccessorBCF().fetchUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class);
		request = TestBaseUtil.createProcessRequest();
		request.setProcess(process);
		response = getSmartPointAccessorBCF().fetchUpdateLightStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch property valid values.
	 */
	@Test
	public void testFetchPropertyValidValues()
	{
		// Validation situation - Property Required
		PropertyValidValuesRequest request = TestBaseUtil.createPropertyValidValuesRequest();
		request.setProperties(null);
		PropertyValidValuesResponse response = getSmartPointAccessorBCF().fetchPropertyValidValues(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_PROPERTY_REQUIRED);

		// Success situation
		request = TestBaseUtil.createPropertyValidValuesRequest();
		request.setProperties(Arrays.asList(PropertyEnum.LAMP_TYPE));
		response = getSmartPointAccessorBCF().fetchPropertyValidValues(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class);
		request = TestBaseUtil.createPropertyValidValuesRequest();
		request.setProperties(Arrays.asList(PropertyEnum.LAMP_TYPE));
		response = getSmartPointAccessorBCF().fetchPropertyValidValues(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class);
		request = TestBaseUtil.createPropertyValidValuesRequest();
		request.setProperties(Arrays.asList(PropertyEnum.LAMP_TYPE));
		response = getSmartPointAccessorBCF().fetchPropertyValidValues(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all custom search.
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		// Success situation
		InquiryPaginationRequest request = TestBaseUtil.createInquiryPaginationRequest();
		InquiryCustomSearchResponse response = getSmartPointAccessorBCF().fetchAllCustomSearch(request);
		assertTrue(response.isOperationSuccess());

		// Success situation without sort expression
		request.getSortExpressions().clear();
		response = getSmartPointAccessorBCF().fetchAllCustomSearch(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class);
		response = getSmartPointAccessorBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class);
		response = getSmartPointAccessorBCF().fetchAllCustomSearch(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all lights by custom search.
	 */
	@Test
	public void testFetchAllLightsByCustomSearch()
	{
		UserContext userContext = createUserContext();

		// Success situation
		InquiryLightRequest request = createInquiryLightRequest(userContext);
		CustomSearch search = createCustomSearch();
		request.setSearch(search);
		InquiryLightResponse response = getSmartPointAccessorBCF().fetchAllLights(request);
		assertTrue(response.isOperationSuccess());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_ALL_LIGHTS);
		request = createInquiryLightRequest(userContext);
		search = createCustomSearch();
		request.setSearch(search);
		response = getSmartPointAccessorBCF().fetchAllLights(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_ALL_LIGHTS);
		request = createInquiryLightRequest(userContext);
		search = createCustomSearch();
		request.setSearch(search);
		response = getSmartPointAccessorBCF().fetchAllLights(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Validation situation - Tenant/User Context
		InquiryLightRequest request = new InquiryLightRequest();
		InquiryLightResponse response = getSmartPointAccessorBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Process ID Required
		request = createInquiryLightRequest(createUserContext());
		response = getSmartPointAccessorBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - CSV File Name Required
		request.setProcessId(1);
		response = getSmartPointAccessorBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Success situation
		request.setFileName(CSV_FILE_NAME);
		response = getSmartPointAccessorBCF().generateFileCSV(request);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getFileName());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				GENERATE_FILE_CSV);
		response = getSmartPointAccessorBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				GENERATE_FILE_CSV);
		response = getSmartPointAccessorBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);

	}

	/**
	 * Test generate light history file csv.
	 */
	@Test
	public void testGenerateLightHistoryFileCSV()
	{
		// Validation situation - Tenant/User Context
		InquiryLightRequest request = new InquiryLightRequest();
		InquiryLightResponse response = getSmartPointAccessorBCF().generateLightHistoryFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED, SENSUS_MLC_VALIDATOR_ID_REQUIRED,
				SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation - Process ID Required
		request = createInquiryLightRequest(createUserContext());
		response = getSmartPointAccessorBCF().generateLightHistoryFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Validation situation - CSV File Name Required
		request.setProcessId(1);
		response = getSmartPointAccessorBCF().generateLightHistoryFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Success situation
		request.setFileName(CSV_FILE_NAME);
		response = getSmartPointAccessorBCF().generateLightHistoryFileCSV(request);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getFileName());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				GENERATE_LIGHT_HISTORY_FILE_CSV);
		response = getSmartPointAccessorBCF().generateLightHistoryFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				GENERATE_LIGHT_HISTORY_FILE_CSV);
		response = getSmartPointAccessorBCF().generateLightHistoryFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch ligthing configurations by part number.
	 */
	@Test
	public void testFetchLigthingConfigurationsByPartNumber()
	{
		// Validation situation light required
		LightingConfigurationRequest request = new LightingConfigurationRequest();
		request.setUserContext(createUserContext());
		LightingConfigurationsResponse response =
				getSmartPointAccessorBCF().fetchLigthingConfigurationsByPartNumber(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation light id required
		request.setLight(new Light());
		response = getSmartPointAccessorBCF().fetchLigthingConfigurationsByPartNumber(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED);

		// Success situation
		request.getLight().setId(1);
		response = getSmartPointAccessorBCF().fetchLigthingConfigurationsByPartNumber(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class);
		request = TestBaseUtil.createLightingConfigurationRequest();
		request.setLight(TestBaseUtil.createLight());
		response = getSmartPointAccessorBCF().fetchLigthingConfigurationsByPartNumber(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class);
		request = TestBaseUtil.createLightingConfigurationRequest();
		request.setLight(TestBaseUtil.createLight());
		response = getSmartPointAccessorBCF().fetchLigthingConfigurationsByPartNumber(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch all column filters.
	 */
	@Test
	public void testFetchAllColumnFilters()
	{
		// Validation situation - List Type Enum Required
		ColumnFilterRequest request = TestBaseUtil.createColumnFilterRequest();
		ColumnFilterResponse response = getSmartPointAccessorBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIST_TYPE_ENUM_REQUIRED);

		// Success situation
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		response = getSmartPointAccessorBCF().fetchAllColumnFilters(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_ALL_COLUMN_FILTERS);
		response = getSmartPointAccessorBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_ALL_COLUMN_FILTERS);
		response = getSmartPointAccessorBCF().fetchAllColumnFilters(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch current alarm status messages by light.
	 */
	@Test
	public void testFetchCurrentAlarmStatusMessagesByLight()
	{
		// Validation situation light required
		LightRequest request = createLightRequest();
		CurrentAlarmWarningMessageResponse response =
				getSmartPointAccessorBCF().fetchCurrentAlarmStatusMessagesByLight(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation light id required
		request.addLight(new Light());
		response = getSmartPointAccessorBCF().fetchCurrentAlarmStatusMessagesByLight(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED);

		// Success situation
		request.getFirstLight().setId(1);
		response = getSmartPointAccessorBCF().fetchCurrentAlarmStatusMessagesByLight(request);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getCurrentAlarmWarningMessages());
		assertEquals(1, response.getCurrentAlarmWarningMessages().size());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_CURRENT_ALARM_STATUS_MESSAGES_BY_LIGHT);
		response = getSmartPointAccessorBCF().fetchCurrentAlarmStatusMessagesByLight(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_CURRENT_ALARM_STATUS_MESSAGES_BY_LIGHT);
		response = getSmartPointAccessorBCF().fetchCurrentAlarmStatusMessagesByLight(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch smartpoints to map.
	 */
	@Test
	public void testFetchSmartpointsToMap()
	{
		// Success situation
		InquiryLightRequest request = createInquiryLightRequest(createUserContext());
		InquiryGeocodeSmartpointInfoResponse response = getSmartPointAccessorBCF().fetchSmartpointsToMap(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class);
		response = getSmartPointAccessorBCF().fetchSmartpointsToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class);
		response = getSmartPointAccessorBCF().fetchSmartpointsToMap(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch status message by light id.
	 */
	@Test
	public void testFetchStatusMessageByLightID()
	{
		// Validation situation light required
		LightRequest request = createLightRequest();
		StatusMessageResponse response = getSmartPointAccessorBCF().fetchStatusMessageByLightID(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation light id required
		request.addLight(new Light());
		response = getSmartPointAccessorBCF().fetchStatusMessageByLightID(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED);

		// Success situation
		request.getFirstLight().setId(1);
		response = getSmartPointAccessorBCF().fetchStatusMessageByLightID(request);
		assertTrue(response.isOperationSuccess());
		assertEquals(0, response.getMessageList().size());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class);
		request = TestBaseUtil.createLightRequest();
		request.getLights().add(TestBaseUtil.createLight());
		response = getSmartPointAccessorBCF().fetchStatusMessageByLightID(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class);
		request = TestBaseUtil.createLightRequest();
		request.getLights().add(TestBaseUtil.createLight());
		response = getSmartPointAccessorBCF().fetchStatusMessageByLightID(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch status message by id.
	 */
	@Test
	public void testFetchStatusMessageById()
	{
		// Validation situation status message required
		LightRequest request = createLightRequest();
		StatusMessageResponse response = getSmartPointAccessorBCF().fetchStatusMessageById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_REQUIRED);

		// Validation status message id required
		StatusMessage statusMessage = new StatusMessage();
		List<StatusMessage> statusMessages = new ArrayList<StatusMessage>();
		statusMessages.add(statusMessage);
		request.setStatusMessages(statusMessages);
		response = getSmartPointAccessorBCF().fetchStatusMessageById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_STATUSMESSAGE_ID_REQUIRED);

		// Success situation
		request.getFirstMessage().setId(1);
		response = getSmartPointAccessorBCF().fetchStatusMessageById(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_STATUS_MESSAGE_BY_ID);
		response = getSmartPointAccessorBCF().fetchStatusMessageById(request);
		assertFalse(response.isOperationSuccess());

		// Error situation - No light
		resetMocksToSmartPointArea();
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_STATUS_MESSAGE_BY_ID);
		response = getSmartPointAccessorBCF().fetchStatusMessageById(request);
		assertFalse(response.isOperationSuccess());

		// Exception situation
		resetMocksToSmartPointArea();
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_STATUS_MESSAGE_BY_ID);
		response = getSmartPointAccessorBCF().fetchStatusMessageById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch light id by rni id.
	 */
	@Test
	public void testFetchLightIdByRniId()
	{
		// Validation situation light required
		LightRequest request = createLightRequest();
		LightResponse response = getSmartPointAccessorBCF().fetchLightIdByRniId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation light id required
		request.addLight(new Light());
		response = getSmartPointAccessorBCF().fetchLightIdByRniId(request);
		assertEquals(1, response.getMessageList().size());
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Success situation
		request.getFirstLight().setRniId(1);
		response = getSmartPointAccessorBCF().fetchLightIdByRniId(request);
		assertResultResponse(response);
		assertNotNull(response.getLights());
		assertEquals(1, response.getLights().size());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_LIGHT_BY_RNI_ID);
		response = getSmartPointAccessorBCF().fetchLightIdByRniId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_LIGHT_BY_RNI_ID);
		response = getSmartPointAccessorBCF().fetchLightIdByRniId(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch light history.
	 */
	@Test
	public void testFetchLightHistory()
	{
		// Success situation
		InquiryLightRequest request = createInquiryLightRequest(createUserContext());
		List<Light> lights = new ArrayList<Light>();
		lights.add(createLight());
		request.setLights(lights);
		InquiryLightResponse response = getSmartPointAccessorBCF().fetchLightHistory(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_LIGHT_HISTORY);
		response = getSmartPointAccessorBCF().fetchLightHistory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_LIGHT_HISTORY);
		response = getSmartPointAccessorBCF().fetchLightHistory(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch light history header.
	 */
	@Test
	public void testFetchLightHistoryHeader()
	{
		// Validation situation light required
		LightRequest request = createLightRequest();
		LightResponse response = getSmartPointAccessorBCF().fetchLightHistoryHeader(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Validation situation light id required
		request.addLight(new Light());
		response = getSmartPointAccessorBCF().fetchLightHistoryHeader(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_ID_REQUIRED);

		// Success situation
		request.getFirstLight().setId(1);
		response = getSmartPointAccessorBCF().fetchLightHistoryHeader(request);
		assertResultResponse(response);
		assertNotNull(response.getLights());

		// Error situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.ERROR, ISmartPointAccessorBCL.class,
				FETCH_LIGHT_HISTORY_HEADER);
		response = getSmartPointAccessorBCF().fetchLightHistoryHeader(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Exception situation
		setSituation(getSmartPointAccessorBCF(), SituationsEnum.EXCEPTION, ISmartPointAccessorBCL.class,
				FETCH_LIGHT_HISTORY_HEADER);
		response = getSmartPointAccessorBCF().fetchLightHistoryHeader(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Assert result response.
	 * 
	 * @param response the response
	 */
	private void assertResultResponse(LightResponse response)
	{
		assertNotNull("Response object should not to be null", response);
		assertNotNull("Message list object should not to be null", response.getMessageInfoList());
		assertEquals(0, response.getMessageInfoList().size());
	}

}
