package com.sensus.lc.analytics.bcf;

import static com.sensus.lc.base.util.LCDateUtil.getNewDateUTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.lc.analytics.bcl.IAnalyticsBCL;
import com.sensus.lc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.lc.analytics.model.AnalyticsTypeEnum;
import com.sensus.lc.analytics.model.request.AnalyticsCSVRequest;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.analytics.model.response.AnalyticsResponse;
import com.sensus.lc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Class AnalyticsBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/analytics/analyticsbcfimpltest.xml"})
public class AnalyticsBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant ARBITRARY_ASSERT_6. */
	private static final int ARBITRARY_ASSERT_6 = 6;

	/** The Constant ARBITRARY_ASSERT_3. */
	private static final int ARBITRARY_ASSERT_3 = 3;

	/** The Constant ARBITRARY_ASSERT_5. */
	private static final int ARBITRARY_ASSERT_5 = 5;

	/** The Constant SHOULD_BRING_5_GROUPS. */
	private static final String SHOULD_BRING_5_GROUPS = "Should bring 5 groups";

	/** The Constant DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG = "sensus.mlc.analyticsbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION. */
	protected static final String SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.processbclimpl.defaultexception";

	/** The Constant SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED. */
	private static final String SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED =
			"sensus.mlc.ecomodevalidator.rangedate.required";

	/** The Constant SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_TYPE_REQUIRED =
			"sensus.mlc.analytics.validator.analytics.type.required";

	/** The Constant SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_DATE_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_DATE_TYPE_REQUIRED =
			"sensus.mlc.analytics.validator.analytics.date.type.required";

	/** The Constant SENSUS_MLC_ANALYTICS_VALIDATOR_STATUS_EXCEPTION_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_ANALYTICS_VALIDATOR_STATUS_EXCEPTION_TYPE_REQUIRED =
			"sensus.mlc.analytics.validator.status.exception.type.required";

	/** The analytics bcf. */
	private IAnalyticsBCF analyticsBCF;

	/**
	 * Gets the analytics bcf.
	 * 
	 * @return the analytics bcf
	 */
	public IAnalyticsBCF getAnalyticsBCF()
	{
		return analyticsBCF;
	}

	/**
	 * Sets the analytics bcf.
	 * 
	 * @param analyticsBCF the new analytics bcf
	 */
	@Resource(name = "analyticsBCFTarget")
	public void setAnalyticsBCF(IAnalyticsBCF analyticsBCF)
	{
		this.analyticsBCF = analyticsBCF;
	}

	/**
	 * Sets the analytics area.
	 */
	public void setAnalyticsArea()
	{
		setArea(getAnalyticsBCF(), LCAreaEnum.ANALYTICS);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setAnalyticsArea();
	}

	/**
	 * Removes the eco mode area.
	 */
	@After
	public void resetMocksToAnalyticsArea()
	{
		resetMocksData(getAnalyticsBCF());
		setAnalyticsArea();
	}

	/**
	 * Test fetch analytics alerts by type.
	 */
	@Test
	public void testFetchAnalyticsAlertsByType()
	{
		// Success situation
		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		AnalyticsResponse response = getAnalyticsBCF().fetchAnalyticsAlertsByType(analyticsRequest);
		assertNotNull(response);
		assertEquals("Should bring 3 lines ", ARBITRARY_ASSERT_3, response.getAlertsByType().size());

		// Error situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		response = getAnalyticsBCF().fetchAnalyticsAlertsByType(analyticsRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		analyticsRequest = new AnalyticsRequest();
		response = getAnalyticsBCF().fetchAnalyticsAlertsByType(analyticsRequest);

		// Exception situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IAnalyticsBCL.class);
		response = getAnalyticsBCF().fetchAnalyticsAlertsByType(analyticsRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch analytics alarms.
	 */
	@Test
	public void testFetchAnalyticsAlarms()
	{
		// Success situation
		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setAlertSubtype(AlertSubTypeEnum.LAMP_FAILURE);
		AnalyticsResponse response = getAnalyticsBCF().fetchAnalyticsAlarmsByStatusId(analyticsRequest);
		assertNotNull(response);
		assertEquals("Should bring 5 lights ", ARBITRARY_ASSERT_5, response.getLights().size());

		// Error situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		analyticsRequest.setAlertSubtype(AlertSubTypeEnum.LAMP_FAILURE);
		response = getAnalyticsBCF().fetchAnalyticsAlarmsByStatusId(analyticsRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IAnalyticsBCL.class);
		analyticsRequest.setAlertSubtype(AlertSubTypeEnum.LAMP_FAILURE);
		response = getAnalyticsBCF().fetchAnalyticsAlarmsByStatusId(analyticsRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);

		// Error situation
		analyticsRequest = new AnalyticsRequest();
		response = getAnalyticsBCF().fetchAnalyticsAlarmsByStatusId(analyticsRequest);

		// Validation situation
		resetMocksToAnalyticsArea();

		// AnalyticsAlarms - statusExceptionTypeEnum empty
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		response = getAnalyticsBCF().fetchAnalyticsAlarmsByStatusId(analyticsRequest);
		assertMessages(response, SENSUS_MLC_ANALYTICS_VALIDATOR_STATUS_EXCEPTION_TYPE_REQUIRED);
	}

	/**
	 * Test fetch all analytics by group.
	 */
	@Test
	public void testFetchAllAnalyticsByGroup()
	{
		// Test success
		InquiryAnalyticsRequest request = TestBaseUtil.createInquiryAnalyticsRequest();
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		request.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
		request.setEndDate(getNewDateUTC());
		request.setInitialDate(getNewDateUTC());
		InquiryAnalyticsResponse response = getAnalyticsBCF().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertEquals(SHOULD_BRING_5_GROUPS, ARBITRARY_ASSERT_5, response.getGroups().size());

		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_WARNING);
		response = getAnalyticsBCF().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertEquals(SHOULD_BRING_5_GROUPS, ARBITRARY_ASSERT_5, response.getGroups().size());

		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_INSTALLED);
		response = getAnalyticsBCF().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertEquals(SHOULD_BRING_5_GROUPS, ARBITRARY_ASSERT_5, response.getGroups().size());

		// Error situation
		request = TestBaseUtil.createInquiryAnalyticsRequest();
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		request.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
		request.setEndDate(getNewDateUTC());
		request.setInitialDate(getNewDateUTC());
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		response = getAnalyticsBCF().fetchAllAnalyticsByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		request = new InquiryAnalyticsRequest();
		response = getAnalyticsBCF().fetchAllAnalyticsByGroup(request);

		// Error situation
		request = TestBaseUtil.createInquiryAnalyticsRequest();
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		request.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
		response = getAnalyticsBCF().fetchAllAnalyticsByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// Error situation
		request = TestBaseUtil.createInquiryAnalyticsRequest();
		request.setEndDate(getNewDateUTC());
		request.setInitialDate(getNewDateUTC());
		response = getAnalyticsBCF().fetchAllAnalyticsByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_TYPE_REQUIRED,
				SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_DATE_TYPE_REQUIRED);

		// Exception situation
		request = TestBaseUtil.createInquiryAnalyticsRequest();
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		request.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
		request.setEndDate(getNewDateUTC());
		request.setInitialDate(getNewDateUTC());
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IAnalyticsBCL.class);
		response = getAnalyticsBCF().fetchAllAnalyticsByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch dashboard resume.
	 */
	@Test
	public void testFetchDashboardResume()
	{
		// Success situation
		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		AnalyticsResponse analyticsResponse = getAnalyticsBCF().fetchDashboardResume(analyticsRequest);
		assertNotNull(analyticsResponse);
		assertEquals("Should 6 columns", ARBITRARY_ASSERT_6, analyticsResponse.getColumns().size());

		// Error situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		analyticsResponse = getAnalyticsBCF().fetchDashboardResume(analyticsRequest);
		assertFalse(analyticsResponse.isOperationSuccess());
		assertMessages(analyticsResponse, ERROR_CODE);

		// Error situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsResponse = getAnalyticsBCF().fetchDashboardResume(analyticsRequest);
		assertFalse(analyticsResponse.isOperationSuccess());
		assertMessages(analyticsResponse, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// Error situation
		analyticsRequest = new AnalyticsRequest();
		analyticsResponse = getAnalyticsBCF().fetchDashboardResume(analyticsRequest);

		// Exception situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IAnalyticsBCL.class);
		analyticsResponse = getAnalyticsBCF().fetchDashboardResume(analyticsRequest);
		assertFalse(analyticsResponse.isOperationSuccess());
		assertMessages(analyticsResponse, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch analytics by date.
	 */
	@Test
	public void testFetchAnalyticsByDate()
	{
		// Success situation
		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);

		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		AnalyticsResponse analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);
		assertNotNull(analyticsResponse);
		assertEquals("Should 3 columns", ARBITRARY_ASSERT_3, analyticsResponse.getColumns().size());

		// Error situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);

		date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);
		assertFalse(analyticsResponse.isOperationSuccess());
		assertMessages(analyticsResponse, ERROR_CODE);

		// Error situation
		analyticsRequest = new AnalyticsRequest();
		analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);

		// Exception situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);

		date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IAnalyticsBCL.class);
		analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);
		assertFalse(analyticsResponse.isOperationSuccess());
		assertMessages(analyticsResponse, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);

		// Validation situation
		resetMocksToAnalyticsArea();

		// AnalyticsByDate - analytics type and analytics date type empty
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);
		assertMessages(analyticsResponse, SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_TYPE_REQUIRED,
				SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_DATE_TYPE_REQUIRED);

		// AnalyticsByDate - range date empty
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);
		analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);
		assertMessages(analyticsResponse, SENSUS_MLC_ECOMODEVALIDATOR_RANGEDATE_REQUIRED);

		// AnalyticsByDate - analytics type empty
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setAnalyticsDateTypeEnum(AnalyticsDateTypeEnum.ONE_DAY);

		date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);
		assertMessages(analyticsResponse, SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_TYPE_REQUIRED);

		// AnalyticsByDate - analytics type empty
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);

		date = Calendar.getInstance();
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.add(Calendar.MONTH, -1);

		analyticsRequest.setInitialDate(date.getTime());
		date.add(Calendar.MONTH, 1);
		analyticsRequest.setEndDate(date.getTime());
		analyticsResponse = getAnalyticsBCF().fetchAllAnalyticsByDate(analyticsRequest);
		assertMessages(analyticsResponse, SENSUS_MLC_ANALYTICS_VALIDATOR_ANALYTICS_DATE_TYPE_REQUIRED);
	}

	/**
	 * Test fetch dashboard header.
	 */
	@Test
	public void testFetchDashboardHeader()
	{
		// Success situation
		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setEndDate(getNewDateUTC());
		analyticsRequest.setInitialDate(getNewDateUTC());
		AnalyticsResponse response = getAnalyticsBCF().fetchDashboardHeader(analyticsRequest);
		assertNotNull(response);
		assertEquals("Should bring 6 columns ", ARBITRARY_ASSERT_6, response.getColumns().size());

		// Error situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		analyticsRequest.setEndDate(getNewDateUTC());
		analyticsRequest.setInitialDate(getNewDateUTC());
		response = getAnalyticsBCF().fetchDashboardHeader(analyticsRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		analyticsRequest = new AnalyticsRequest();
		response = getAnalyticsBCF().fetchDashboardHeader(analyticsRequest);

		// Exception situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IAnalyticsBCL.class);
		analyticsRequest.setEndDate(getNewDateUTC());
		analyticsRequest.setInitialDate(getNewDateUTC());
		response = getAnalyticsBCF().fetchDashboardHeader(analyticsRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		AnalyticsCSVRequest request = new AnalyticsCSVRequest();
		String fileName = System.getProperty("java.io.tmpdir")
				+ System.getProperty("line.separator") + "testPOIWrite"
				+ getNewDateUTC().getTime()
				+ ".csv";
		List<Column> columns = Arrays.asList(new Column(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS.getValue()));
		InquiryAnalyticsRequest inquiryAnalyticsRequest = TestBaseUtil.createInquiryAnalyticsRequest();
		request.setFileName(fileName);
		inquiryAnalyticsRequest.setEndDate(getNewDateUTC());
		inquiryAnalyticsRequest.setInitialDate(getNewDateUTC());
		inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		inquiryAnalyticsRequest.setListColumn(columns);
		request.setUserContext(inquiryAnalyticsRequest.getUserContext());
		request.setInquiryAnalyticsRequest(inquiryAnalyticsRequest);
		CSVResponse response = getAnalyticsBCF().generateFileCSV(request);
		assertNotNull(response);
		assertNotNull(response.getFileName());

		// Error situation
		request = new AnalyticsCSVRequest();
		inquiryAnalyticsRequest = TestBaseUtil.createInquiryAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		inquiryAnalyticsRequest.setListColumn(columns);
		inquiryAnalyticsRequest.setEndDate(getNewDateUTC());
		inquiryAnalyticsRequest.setInitialDate(getNewDateUTC());
		inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		request.setUserContext(inquiryAnalyticsRequest.getUserContext());
		request.setInquiryAnalyticsRequest(inquiryAnalyticsRequest);
		response = getAnalyticsBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		request = new AnalyticsCSVRequest();
		inquiryAnalyticsRequest = new InquiryAnalyticsRequest();
		request.setInquiryAnalyticsRequest(inquiryAnalyticsRequest);
		response = getAnalyticsBCF().generateFileCSV(request);

		// Error situation
		request = new AnalyticsCSVRequest();
		inquiryAnalyticsRequest = TestBaseUtil.createInquiryAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		inquiryAnalyticsRequest.setEndDate(getNewDateUTC());
		inquiryAnalyticsRequest.setInitialDate(getNewDateUTC());
		inquiryAnalyticsRequest.setListColumn(columns);
		request.setUserContext(inquiryAnalyticsRequest.getUserContext());
		request.setInquiryAnalyticsRequest(inquiryAnalyticsRequest);
		response = getAnalyticsBCF().generateFileCSV(request);

		// Validation situation
		request = new AnalyticsCSVRequest();
		inquiryAnalyticsRequest = TestBaseUtil.createInquiryAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.VALIDATION, IAnalyticsBCL.class);
		inquiryAnalyticsRequest.setEndDate(getNewDateUTC());
		inquiryAnalyticsRequest.setInitialDate(getNewDateUTC());
		inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		inquiryAnalyticsRequest.setListColumn(columns);
		request.setUserContext(inquiryAnalyticsRequest.getUserContext());
		request.setInquiryAnalyticsRequest(inquiryAnalyticsRequest);
		response = getAnalyticsBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());

		// Exception situation
		request = new AnalyticsCSVRequest();
		inquiryAnalyticsRequest = TestBaseUtil.createInquiryAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IAnalyticsBCL.class);
		inquiryAnalyticsRequest.setEndDate(getNewDateUTC());
		inquiryAnalyticsRequest.setInitialDate(getNewDateUTC());
		inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		inquiryAnalyticsRequest.setListColumn(columns);
		request.setUserContext(inquiryAnalyticsRequest.getUserContext());
		request.setInquiryAnalyticsRequest(inquiryAnalyticsRequest);
		response = getAnalyticsBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test fetch all analytics group.
	 */
	@Test
	public void testFetchAllAnalyticsGroup()
	{
		// Success situation
		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		AnalyticsResponse response = getAnalyticsBCF().fetchAllAnalyticsGroup(analyticsRequest);
		assertNotNull(response);
		assertEquals("Should bring 3 groups ", ARBITRARY_ASSERT_3, response.getAnalyticsGroups().size());

		// Error situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IAnalyticsBCL.class);
		response = getAnalyticsBCF().fetchAllAnalyticsGroup(analyticsRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation
		analyticsRequest = new AnalyticsRequest();
		response = getAnalyticsBCF().fetchAllAnalyticsGroup(analyticsRequest);

		// Exception situation
		analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IAnalyticsBCL.class);
		response = getAnalyticsBCF().fetchAllAnalyticsGroup(analyticsRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);

	}

	/**
	 * Test insert csv process.
	 */
	@Test
	public void testInsertCSVProcess()
	{
		// Success situation
		ProcessRequest processRequest = TestBaseUtil.createProcessRequest();
		ProcessResponse response = getAnalyticsBCF().insertCSVProcess(processRequest);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response.getProcesses());
		assertTrue(response.getProcesses().size() > 0);

		// Error situation
		resetMocksToAnalyticsArea();
		setSituation(getAnalyticsBCF(), SituationsEnum.ERROR, IProcessBCF.class);
		processRequest = TestBaseUtil.createProcessRequest();
		response = getAnalyticsBCF().insertCSVProcess(processRequest);
		assertFalse(response.isOperationSuccess());
		assertEquals(1, response.getMessageInfoList().size());

		// Error situation
		processRequest = new ProcessRequest();
		response = getAnalyticsBCF().insertCSVProcess(processRequest);

		// Exception situation
		resetMocksToAnalyticsArea();
		setSituation(getAnalyticsBCF(), SituationsEnum.EXCEPTION, IProcessBCF.class);
		processRequest = TestBaseUtil.createProcessRequest();
		response = getAnalyticsBCF().insertCSVProcess(processRequest);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION);
	}

}
