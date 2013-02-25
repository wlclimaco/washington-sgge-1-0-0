package com.sensus.mlc.smartpoint.bcl;

import static com.sensus.mlc.base.TestBaseUtil.createInquiryLightRequest;
import static com.sensus.mlc.base.TestBaseUtil.createUserContext;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.ListTypeEnum;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.group.bcl.IGroupBCL;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.dac.ISmartPointDAC;
import com.sensus.mlc.smartpoint.model.CurrentAlarmWarningMessage;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.GeocodeSmartpointInfo;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.LightHistory;
import com.sensus.mlc.smartpoint.model.LightIntensityEnum;
import com.sensus.mlc.smartpoint.model.LightStatusEnum;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.PropertyValidValue;
import com.sensus.mlc.smartpoint.model.SearchLight;
import com.sensus.mlc.smartpoint.model.SearchParameter;
import com.sensus.mlc.smartpoint.model.SensusPartNumberConfiguration;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.tag.bcl.ITagBCL;

/**
 * The Class SmartPointBCLImplTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/smartpoint/smartpointbclimpltest.xml"})
public class SmartPointAccessorBCLTest extends AbstractTestBaseBusiness
{
	/** The Constant ARBITRARY_PERCENTAGE_1000. */
	private static final Integer ARBITRARY_PERCENTAGE_1000 = 1000;

	/** The Constant ARBITRARY_PERCENTAGE_10. */
	private static final Integer ARBITRARY_PERCENTAGE_10 = 10;

	/** The Constant ARBITRARY_RESULTS_LIST_SIZE_6. */
	private static final int ARBITRARY_RESULTS_LIST_SIZE_6 = 6;

	/** The Constant ARBITRARY_SEARCH_PARAMETERS_SIZE_4. */
	private static final int ARBITRARY_SEARCH_PARAMETERS_SIZE_4 = 4;

	/** The Constant UPDATE_PROCESS. */
	private static final String UPDATE_PROCESS = "updateProcess";

	/** The Constant FETCH_PROCESS_BY_ID. */
	private static final String FETCH_PROCESS_BY_ID = "fetchProcessById";

	/** The Constant FILE_NAME. */
	private static final String FILE_NAME = "C:\\Users\\QATEmployee\\Desktop\\file\\testPOIWrite";

	/** The Constant PECO. */
	private static final String PECO = "PECO";

	/** The Constant FETCH_ALL_LIGHTS. */
	private static final String FETCH_ALL_LIGHTS = "fetchAllLights";

	/** The smart point accessor bcl. */
	private ISmartPointAccessorBCL smartPointAccessorBCL;

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
	 * Gets the smart point accessor bcl.
	 *
	 * @return the smart point accessor bcl
	 */
	public ISmartPointAccessorBCL getSmartPointAccessorBCL()
	{
		return smartPointAccessorBCL;
	}

	/**
	 * Sets the smart point accessor bcl.
	 *
	 * @param smartPointAccessorBCL the new smart point accessor bcl
	 */
	@Resource(name = "smartPointAccessorBCLTarget")
	public void setSmartPointAccessorBCL(ISmartPointAccessorBCL smartPointAccessorBCL)
	{
		this.smartPointAccessorBCL = smartPointAccessorBCL;
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
		setArea(getSmartPointAccessorBCL(), LCAreaEnum.SMARTPOINT);
	}

	/**
	 * Reset the smart point area.
	 */
	@After
	public void resetMocksToSmartPointArea()
	{
		resetMocksData(getSmartPointAccessorBCL());
		setSmartPointArea();
	}

	/**
	 * Test fetch all lights.
	 */
	@Test
	public void testFetchAllLights()
	{
		// Success situation - Process ID
		InquiryLightRequest request = TestBaseUtil.createInquiryLightRequest(TestBaseUtil.createUserContext());
		request.setProcessId(1);
		InternalResultsResponse<Light> response = getSmartPointAccessorBCL().fetchAllLights(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, FETCH_ALL_LIGHTS);
		response = getSmartPointAccessorBCL().fetchAllLights(request);
		assertMessages(response, ERROR_CODE);

		resetMocksToSmartPointArea();

		// Success situation - Search
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.SUCCESS, ISmartPointDAC.class,
				FETCH_ALL_LIGHTS);
		request = TestBaseUtil.createInquiryLightRequest(TestBaseUtil.createUserContext());
		request.setProcessId(null);
		request.setSearch(TestBaseUtil.createCustomSearch());
		response = getSmartPointAccessorBCL().fetchAllLights(request);
		assertResultResponse(response);

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, FETCH_ALL_LIGHTS);
		response = getSmartPointAccessorBCL().fetchAllLights(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch light by id.
	 */
	@Test
	public void testFetchLightById()
	{
		// Success situation
		LightRequest lightRequest = TestBaseUtil.createLightRequest();
		Light light = TestBaseUtil.createLight();
		lightRequest.addLight(light);
		InternalResultsResponse<Light> response = getSmartPointAccessorBCL().fetchLightById(lightRequest);
		assertResultResponse(response);

		lightRequest = TestBaseUtil.createLightRequest();
		light = TestBaseUtil.createLight();
		light.getLightLocation().setZipCode("OffSetNegative");
		lightRequest.addLight(light);
		response = getSmartPointAccessorBCL().fetchLightById(lightRequest);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, "fetchLightById");
		lightRequest = TestBaseUtil.createLightRequest();
		light = TestBaseUtil.createLight();
		lightRequest.addLight(light);
		response = getSmartPointAccessorBCL().fetchLightById(lightRequest);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test count device.
	 */
	@Test
	public void testCountDevice()
	{
		// Success situation
		LightingControlRequest request = new LightingControlRequest(getDummyUserContext());
		InternalResultsResponse<Integer> response = getSmartPointAccessorBCL().countLights(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class, "countLights");
		response = getSmartPointAccessorBCL().countLights(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch update light status.
	 */
	@Test
	public void testFetchUpdateLightStatus()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		request.setProcess(TestBaseUtil.createProcess(TestBaseUtil.createLight(), null));
		InternalResultsResponse<Light> response = getSmartPointAccessorBCL().fetchUpdateLightStatus(request);
		assertResultResponse(response);
	}

	/**
	 * Test fetch property valid value by name.
	 */
	@Test
	public void testFetchPropertyValidValueByName()
	{
		// Success situation
		PropertyValidValuesRequest request = TestBaseUtil.createPropertyValidValuesRequest();

		List<PropertyEnum> properties = new ArrayList<PropertyEnum>();
		properties.add(PropertyEnum.WATTAGE_RATING);
		properties.add(PropertyEnum.LAMP_TYPE);

		request.setProperties(properties);

		InternalResultsResponse<PropertyValidValue> response =
				getSmartPointAccessorBCL().fetchPropertyValidValues(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchPropertyValidValues");
		response = getSmartPointAccessorBCL().fetchPropertyValidValues(request);
		assertEquals("Results list object should be 0", response.getResultsList().size(), 0);
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
	 * Test fetch all custom search.
	 */
	@Test
	public void testFetchAllCustomSearch()
	{
		// Success situation
		InquiryPaginationRequest request = TestBaseUtil.createInquiryPaginationRequest();
		InternalResultsResponse<CustomSearch> response = getSmartPointAccessorBCL().fetchAllCustomSearch(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchAllCustomSearch");
		response = getSmartPointAccessorBCL().fetchAllCustomSearch(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch all lights by custom search.
	 */
	@Test
	public void testFetchAllLightsByCustomSearch()
	{
		// Success situation
		CustomSearch customSearch = new CustomSearch();
		customSearch.setName("name1");
		customSearch.setDescription("description1");

		SearchParameter param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.GROUP_ID);
		param.setValue("5");

		customSearch.getSearchParameters().add(param);

		CustomSearchRequest request = TestBaseUtil.createCustomSearchRequest();
		request.setCustomSearch(customSearch);

		InternalResultsResponse<CustomSearch> response = getSmartPointUpdaterBCL().insertCustomSearch(request);
		assertResultResponse(response);

		InquiryLightRequest inquiryLightRequest =
				TestBaseUtil.createInquiryLightRequest(TestBaseUtil.createUserContext());
		inquiryLightRequest.setSearch(response.getFirstResult());

		InternalResultsResponse<Light> lightResponse =
				getSmartPointAccessorBCL().fetchAllLights(inquiryLightRequest);
		assertResultResponse(lightResponse);
	}

	/**
	 * Test can insert custom search.
	 */
	@Test
	public void testCanInsertCustomSearch()
	{
		// Can Insert
		List<MessageInfo> list = new ArrayList<MessageInfo>();

		CustomSearchRequest request = TestBaseUtil.createCustomSearchRequest();
		request.setCustomSearch(TestBaseUtil.createCustomSearch());
		Boolean canInsert =
				getSmartPointAccessorBCL().fetchCanInsertCustomSearch(TestBaseUtil.createTenant(),
						TestBaseUtil.createCustomSearch(), 1, list, null);
		assertEquals("Can Insert", canInsert, true);

		resetMocksToSmartPointArea();

		// Can Not Insert
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchCanInsertCustomSearch");
		canInsert =
				getSmartPointAccessorBCL().fetchCanInsertCustomSearch(TestBaseUtil.createTenant(),
						TestBaseUtil.createCustomSearch(), 1, list, null);
		assertEquals("Can Not Insert", canInsert, false);
	}

	/**
	 * Test verify custom search.
	 */
	@Test
	public void testVerifyCustomSearch()
	{
		// Success situation
		List<SearchParameter> params = new ArrayList<SearchParameter>();
		SearchParameter param = new SearchParameter();

		CustomSearch customSearch = new CustomSearch();

		// Without Search Parameters
		SearchLight customSearchReturn = getSmartPointAccessorBCL().verifyCustomSearch(customSearch);
		assertEquals("should have no parameters", 0, customSearchReturn.getSearchParameters().size());

		customSearch.setName("name");
		customSearch.setDescription("description");

		// One Search Parameters with value null
		customSearch.setSearchParameters(Arrays.asList(new SearchParameter()));
		customSearchReturn = getSmartPointAccessorBCL().verifyCustomSearch(customSearch);
		assertNotNull("should be valid  ", customSearch.getSearchParameters());

		param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.GROUP_ID);
		param.setValue("0");
		params.add(param);

		param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.TAG_ID);
		param.setValue("1");
		params.add(param);

		param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.OFFSET_SCHEDULE);
		param.setValue("2");
		params.add(param);

		param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.EVENT_SCHEDULE);
		param.setValue("3");
		params.add(param);

		customSearch.setSearchParameters(params);

		customSearchReturn = getSmartPointAccessorBCL().verifyCustomSearch(customSearch);
		assertNotNull("Should be valid", customSearch.getSearchParameters());
		assertEquals("should have the same number of params", params.size(),
				customSearch.getSearchParameters().size());

		for (SearchParameter p : customSearch.getSearchParameters())
		{
			assertFalse("should be all false ", p.isValidParameter() && !p.isValidParameter());
		}

		assertNotNull("Should be valid ", customSearchReturn.getSearchParameters());
		assertEquals("should have parameters", ARBITRARY_SEARCH_PARAMETERS_SIZE_4, customSearchReturn
				.getSearchParameters().size());

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, IGroupBCL.class,
				"fetchGroupById");
		customSearchReturn = getSmartPointAccessorBCL().verifyCustomSearch(customSearch);

		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, IScheduleBCL.class,
				"fetchScheduleById");
		customSearchReturn = getSmartPointAccessorBCL().verifyCustomSearch(customSearch);

		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ITagBCL.class,
				"fetchTagById");
		customSearchReturn = getSmartPointAccessorBCL().verifyCustomSearch(customSearch);

		resetMocksToSmartPointArea();

		// Success situation
		params.clear();
		param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.STREET_NAME);
		param.setValue("street name");
		params.add(param);

		param = new SearchParameter();
		param.setPropertyEnum(PropertyEnum.CITY_NAME);
		param.setValue("omaha");
		params.add(param);

		customSearch.setSearchParameters(params);

		customSearchReturn = getSmartPointAccessorBCL().verifyCustomSearch(customSearch);
		assertNotNull("should be valid ", customSearch.getSearchParameters());
		assertEquals("should have the same number of params ", params.size(),
				customSearch.getSearchParameters().size());

		for (SearchParameter p : customSearch.getSearchParameters())
		{
			assertTrue("should be all true ", p.isValidParameter());
		}

		assertNotNull("should be valid", customSearchReturn.getSearchParameters());
		assertEquals("should have the same number of parameters", params.size(), customSearchReturn
				.getSearchParameters().size());
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(createUserContext());
		inquiryLightRequest.setProcessId(new Integer(1));
		inquiryLightRequest.setFileName(FILE_NAME
				+ LCDateUtil.getNewDateUTC().getTime() + ".csv ");
		InquiryLightResponse response = getSmartPointAccessorBCL().generateFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertNotNull(response.getFileName());
		assertTrue(response.isOperationSuccess());

		// Errors situations
		// ISmartPointDAC
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"generateFileCSV");
		response = getSmartPointAccessorBCL().generateFileCSV(inquiryLightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToSmartPointArea();

		// IProcessBCL
		// Return operation success because is set at implementation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, IProcessBCL.class, FETCH_PROCESS_BY_ID);
		response = getSmartPointAccessorBCL().generateFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertFalse(response.isOperationSuccess());
		resetMocksToSmartPointArea();

		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, IProcessBCL.class, UPDATE_PROCESS);
		response = getSmartPointAccessorBCL().generateFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertFalse(response.isOperationSuccess());
		resetMocksToSmartPointArea();
	}

	/**
	 * Test generate light history file csv.
	 */
	@Test
	public void testGenerateLightHistoryFileCSV()
	{

		// Success situation
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(createUserContext());
		inquiryLightRequest.setProcessId(new Integer(1));
		inquiryLightRequest.setFileName(FILE_NAME
				+ LCDateUtil.getNewDateUTC().getTime() + ".csv");
		InquiryLightResponse response = getSmartPointAccessorBCL().generateLightHistoryFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertNotNull(response.getFileName());
		assertTrue(response.isOperationSuccess());

		// Errors situations
		// ISmartPointDAC
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"generateLightHistoryFileCSV");
		response = getSmartPointAccessorBCL().generateLightHistoryFileCSV(inquiryLightRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);
		resetMocksToSmartPointArea();

		// IProcessBCL
		// Return operation success because is set at implementation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				FETCH_PROCESS_BY_ID);
		response = getSmartPointAccessorBCL().generateLightHistoryFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertFalse(response.isOperationSuccess());
		assertNull(response.getFileName());
		resetMocksToSmartPointArea();

		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, IProcessBCL.class,
				UPDATE_PROCESS);
		response = getSmartPointAccessorBCL().generateLightHistoryFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertFalse(response.isOperationSuccess());
		assertNull(response.getFileName());
		resetMocksToSmartPointArea();
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateGenericFileCSV()
	{
		// Success situation
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(createUserContext());
		inquiryLightRequest.setProcessId(new Integer(1));
		InquiryLightResponse response = getSmartPointAccessorBCL().generateGenericFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertNotNull(response.getFileName());
		assertTrue(response.isOperationSuccess());
	}

	/**
	 * Test generate summary file csv.
	 */
	@Test
	public void testGenerateSummaryFileCSV()
	{
		// Success situation
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(createUserContext());
		inquiryLightRequest.setProcessId(new Integer(1));
		InquiryLightResponse response = getSmartPointAccessorBCL().generateSummaryFileCSV(inquiryLightRequest);
		assertNotNull(response);
		assertNotNull(response.getFileName());
		assertTrue(response.isOperationSuccess());
	}

	/**
	 * Test fetch light schedule by id.
	 */
	@Test
	public void testFetchLightScheduleById()
	{
		//Test success
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.SUCCESS, ISmartPointDAC.class, "fetchLightScheduleById");
		internalResultsResponse = getSmartPointAccessorBCL().fetchLightScheduleById(TestBaseUtil.createLightRequest());
		assertResultResponse(internalResultsResponse);

	}

	/**
	 * Test fetch light last operation data by id.
	 */
	@Test
	public void testFetchLightLastOperationDataById()
	{
		//Test success
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.SUCCESS, ISmartPointDAC.class, "fetchLightLastOperationDataById");
		internalResultsResponse = getSmartPointAccessorBCL().fetchLightLastOperationDataById(TestBaseUtil.createLightRequest());
		assertResultResponse(internalResultsResponse);

	}

	/**
	 * Test fetch light location by id.
	 */
	@Test
	public void testFetchLightLocationById()
	{
		//Test success
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.SUCCESS, ISmartPointDAC.class, "fetchLightLocationById");
		internalResultsResponse = getSmartPointAccessorBCL().fetchLightLocationById(TestBaseUtil.createLightRequest());
		assertResultResponse(internalResultsResponse);

	}

	/**
	 * Testfetch light configuration by id.
	 */
	@Test
	public void testfetchLightConfigurationById()
	{
		//Test success
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.SUCCESS, ISmartPointDAC.class, "fetchLightConfigurationById");
		internalResultsResponse = getSmartPointAccessorBCL().fetchLightConfigurationById(TestBaseUtil.createLightRequest());
		assertResultResponse(internalResultsResponse);

	}

	/**
	 * Test fetch lighting configurations by part number.
	 */
	@Test
	public void testFetchLigthingConfigurationsByPartNumber()
	{
		LightingConfigurationRequest request = new LightingConfigurationRequest(getDummyUserContext());
		request.setPartNumber(1);
		InternalResultsResponse<SensusPartNumberConfiguration> response =
				getSmartPointAccessorBCL().fetchLigthingConfigurationsByPartNumber(request);
		assertNotNull(response);
		assertNotNull(response.getResultsList());
		assertEquals(ARBITRARY_RESULTS_LIST_SIZE_6, response.getResultsList().size());
		for (SensusPartNumberConfiguration conf : response.getResultsList())
		{
			System.out.println("Light Intensity [" + conf.getIntensityLevel() + "] Hardware Settings ["
					+ conf.getHardwareSetting() + "] Currente Scale ["
					+ conf.getCurrentScale() + "] Full on Required [" + conf.getFullOnRequired() + "]");
		}

	}

	/**
	 * Test fetch light intensity by light.
	 */
	@Test
	public void testFetchLightIntensityByLight()
	{
		// Success situation
		LightIntensityEnum response = getSmartPointAccessorBCL().fetchLightIntensityByLight(1, ARBITRARY_PERCENTAGE_10);
		assertNotNull(response);
		assertEquals(response, LightIntensityEnum.LEVEL_1);

		response = getSmartPointAccessorBCL().fetchLightIntensityByLight(1, 0);
		assertNotNull(response);
		assertEquals(response, LightIntensityEnum.LEVEL_0);

		response = getSmartPointAccessorBCL().fetchLightIntensityByLight(1, ARBITRARY_PERCENTAGE_1000);
		assertNotNull(response);
		assertEquals(response, LightIntensityEnum.LEVEL_0);
	}

	/**
	 * Test fetch all lights by process.
	 */
	@Test
	public void testFetchAllLightsByProcess()
	{
		// Success situation
		ProcessRequest request = TestBaseUtil.createProcessRequest();
		Process process = TestBaseUtil.createProcess(TestBaseUtil.createLight(), null);
		request.setProcess(process);
		InternalResultsResponse<Light> response = getSmartPointAccessorBCL().fetchAllLightsByProcess(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchAllLightsByProcess");
		response = getSmartPointAccessorBCL().fetchAllLightsByProcess(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch light by rni id.
	 */
	@Test
	public void testFetchLightByRniId()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResultsResponse<Light> response = getSmartPointAccessorBCL().fetchLightByRniId(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchLightByRniId");
		response = getSmartPointAccessorBCL().fetchLightByRniId(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch status message by light id.
	 */
	@Test
	public void testFetchStatusMessageByLightID()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResultsResponse<StatusMessage> response =
				getSmartPointAccessorBCL().fetchStatusMessageByLightID(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchStatusMessageByLightID");
		response = getSmartPointAccessorBCL().fetchStatusMessageByLightID(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch status message by id.
	 */
	@Test
	public void testFetchStatusMessageById()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest();
		InternalResultsResponse<StatusMessage> response =
				getSmartPointAccessorBCL().fetchStatusMessageById(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchStatusMessageById");
		response =
				getSmartPointAccessorBCL().fetchStatusMessageById(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test is light in tenant.
	 */
	@Test
	public void testIsLightInTenant()
	{
		// Success situation
		LightStatusRequest request = new LightStatusRequest(getDummyUserContext());
		request.setTenantRniCode(PECO);
		request.setLightRniId(1);
		request.setUserContext(getUserContext());
		InternalResponse response = getSmartPointAccessorBCL().isLightInTenant(request, true);
		assertFalse(response.isInError());

		// Error situation
		request.setTenantRniCode("Invalid");
		response = getSmartPointAccessorBCL().isLightInTenant(request, true);
		assertEquals(1, response.getMessageInfoList().size());
		assertTrue(response.isInError());

		request.setTenantRniCode("Acme");
		response = getSmartPointAccessorBCL().isLightInTenant(request, true);
		assertEquals(1, response.getMessageInfoList().size());
		assertTrue(response.isInError());

		request.setTenantRniCode(PECO);
		request.setLightRniId(0);
		response = getSmartPointAccessorBCL().isLightInTenant(request, true);
		assertEquals(1, response.getMessageInfoList().size());
		assertTrue(response.isInError());
	}

	/**
	 * Test fetch light history.
	 */
	@Test
	public void testFetchLightHistory()
	{
		// Success situation
		InquiryLightRequest request = TestBaseUtil.createInquiryLightRequest(TestBaseUtil.createUserContext());
		request.getLights().add(TestBaseUtil.createLight());
		InternalResultsResponse<LightHistory> response = getSmartPointAccessorBCL().fetchLightHistory(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchLightHistory");
		response = getSmartPointAccessorBCL().fetchLightHistory(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch light history header.
	 */
	@Test
	public void testFetchLightHistoryHeader()
	{
		// Success situation
		LightRequest request = TestBaseUtil.createLightRequest();
		request.addLight(TestBaseUtil.createLight());
		InternalResultsResponse<HashMap<String, Integer>> response =
				getSmartPointAccessorBCL().fetchLightHistoryHeader(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchLightHistoryHeader");
		response = getSmartPointAccessorBCL().fetchLightHistoryHeader(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch all columns filters.
	 */
	@Test
	public void testFetchAllColumnsFilters()
	{
		// Success situation
		ColumnFilterRequest request = TestBaseUtil.createColumnFilterRequest();
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		InternalResultsResponse<ColumnFilterResponse> response =
				getSmartPointAccessorBCL().fetchAllColumnFilters(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchAllColumnFilters");
		request = TestBaseUtil.createColumnFilterRequest();
		request.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);
		response = getSmartPointAccessorBCL().fetchAllColumnFilters(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch all lights to apply schedule.
	 */
	@Test
	public void testFetchAllLightsToApplySchedule()
	{
		// Success situation
		ScheduleRequest request = TestBaseUtil.createScheduleRequest();
		request.setSchedule(TestBaseUtil.createOffSetSchedule());
		InternalResultsResponse<Light> response =
				getSmartPointAccessorBCL().fetchAllLightsToApplySchedule(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchAllLightsToApplySchedule");
		response = getSmartPointAccessorBCL().fetchAllLightsToApplySchedule(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch smartpoints to map.
	 */
	@Test
	public void testFetchSmartpointsToMap()
	{
		// Success situation
		InquiryLightRequest request = TestBaseUtil.createInquiryLightRequest(TestBaseUtil.createUserContext());
		request.setGeoCodeTrunc(1);
		InternalResultsResponse<GeocodeSmartpointInfo> response =
				getSmartPointAccessorBCL().fetchSmartpointsToMap(request);
		assertResultResponse(response);

		resetMocksToSmartPointArea();

		// Error situation
		setSituation(getSmartPointAccessorBCL(), SituationsEnum.ERROR, ISmartPointDAC.class,
				"fetchSmartpointsToMap");
		response = getSmartPointAccessorBCL().fetchSmartpointsToMap(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch current alarm status messages by light.
	 */
	@Test
	public void testFetchCurrentAlarmStatusMessagesByLight()
	{
		// Success situation
		InternalResultsResponse<CurrentAlarmWarningMessage> response =
				getSmartPointAccessorBCL().fetchCurrentAlarmStatusMessagesByLight(
						insertLights().get(0).getId());
		assertResultResponse(response);
	}

	/**
	 * Test fetch all lights to csv.
	 */
	@Test
	public void testFetchAllLightsToCSV()
	{
		InquiryLightRequest inquiryLightRequest = createInquiryLightRequest(createUserContext());
		inquiryLightRequest.setProcessId(1);
		InternalResultsResponse<HashMap<String, String>> response =
				getSmartPointAccessorBCL().fetchAllLightsToCSV(inquiryLightRequest);
		assertNotNull(response);
		assertNotNull(response.getResultsList());
	}

	/**
	 * Gets the user context.
	 *
	 * @return the user context
	 */
	private UserContext getUserContext()
	{
		UserContext result = new UserContext();
		result.setId(1);
		result.setUserId("QAT1");
		return result;
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
