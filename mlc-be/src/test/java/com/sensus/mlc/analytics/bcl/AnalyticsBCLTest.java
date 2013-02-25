/*
 *
 */
package com.sensus.mlc.analytics.bcl;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.analytics.dac.IAnalyticsDAC;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.mlc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.mlc.analytics.model.AnalyticsGroupWarning;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class AnalyticsBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/analytics/analyticsbclimpltest.xml"})
public class AnalyticsBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant GREGORIAN_CALENDAR_2010. */
	private static final Integer GREGORIAN_CALENDAR_2010 = 2010;

	/** The Constant GREGORIAN_CALENDAR_28. */
	private static final Integer GREGORIAN_CALENDAR_28 = 28;

	/** The Constant GREGORIAN_CALENDAR_12. */
	private static final Integer GREGORIAN_CALENDAR_12 = 12;

	/** The Constant GREGORIAN_CALENDAR_2011. */
	private static final Integer GREGORIAN_CALENDAR_2011 = 2011;

	/** The Constant ANALYTICS_GROUP_8_2. */
	private static final double ANALYTICS_GROUP_8_2 = 8.2;

	/** The Constant ANALYTICS_GROUP_6_7. */
	private static final double ANALYTICS_GROUP_6_7 = 6.7;

	/** The Constant ANALYTICS_GROUP_5_1. */
	private static final double ANALYTICS_GROUP_5_1 = 5.1;

	/** The Constant ANALYTICS_GROUP_4_2. */
	private static final double ANALYTICS_GROUP_4_2 = 4.2;

	/** The Constant ANALYTICS_GROUP_3_1. */
	private static final double ANALYTICS_GROUP_3_1 = 3.1;

	/** The Constant ANALYTICS_GROUP_18_0. */
	private static final double ANALYTICS_GROUP_18_0 = 18.0;

	/** The Constant ANALYTICS_GROUP_7_0. */
	private static final double ANALYTICS_GROUP_7_0 = 7.0;

	/** The Constant ANALYTICS_GROUP_12_0. */
	private static final double ANALYTICS_GROUP_12_0 = 12.0;

	/** The Constant ANALYTICS_GROUP_5_0. */
	private static final double ANALYTICS_GROUP_5_0 = 5.0;

	/** The Constant ANALYTICS_GROUP_4_0. */
	private static final double ANALYTICS_GROUP_4_0 = 4.0;

	/** The Constant ANALYTICS_GROUP_6_0. */
	private static final double ANALYTICS_GROUP_6_0 = 6.0;

	/** The Constant ANALYTICS_GROUP_3_0. */
	private static final double ANALYTICS_GROUP_3_0 = 3.0;

	/** The Constant ANALYTICS_GROUP_11. */
	private static final Integer ANALYTICS_GROUP_11 = 11;

	/** The Constant ANALYTICS_GROUP_6. */
	private static final Integer ANALYTICS_GROUP_6 = 6;

	/** The Constant ANALYTICS_GROUP_5. */
	private static final Integer ANALYTICS_GROUP_5 = 5;

	/** The Constant ANALYTICS_GROUP_7. */
	private static final Integer ANALYTICS_GROUP_7 = 7;

	/** The Constant ANALYTICS_GROUP_4. */
	private static final Integer ANALYTICS_GROUP_4 = 4;

	/** The Constant ANALYTICS_GROUP_3. */
	private static final Integer ANALYTICS_GROUP_3 = 3;

	/** The Constant ASSERT_4. */
	private static final int ASSERT_4 = 4;

	/** The Constant ASSERT_3. */
	private static final int ASSERT_3 = 3;

	/** The Constant BEAVERTON. */
	private static final String BEAVERTON = "Beaverton";

	/** The Constant I5. */
	private static final String I5 = "I5";

	/** The Constant WOODSTOCK. */
	private static final String WOODSTOCK = "Woodstock";

	/** The Constant SHOULD_HAVE_THE_SAME_SIZE. */
	private static final String SHOULD_HAVE_THE_SAME_SIZE = "should have the same size";

	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = "-";

	/** The analytics bcl. */
	private IAnalyticsBCL analyticsBCL;

	/**
	 * Gets the analytics bcl.
	 *
	 * @return the analytics bcl
	 */
	public IAnalyticsBCL getAnalyticsBCL()
	{
		return analyticsBCL;
	}

	/**
	 * Sets the analytics bcl.
	 *
	 * @param analyticsBCL the new analytics bcl
	 */
	@Resource(name = "analyticsBCLTarget")
	public void setAnalyticsBCL(IAnalyticsBCL analyticsBCL)
	{
		this.analyticsBCL = analyticsBCL;
	}

	/**
	 * Sets the analytics area.
	 */
	public void setAnalyticsArea()
	{
		setArea(getAnalyticsBCL(), LCAreaEnum.ANALYTICS);
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
	 * Reset mocks to analytics area.
	 */
	@After
	public void resetMocksToAnalyticsArea()
	{
		resetMocksData(getAnalyticsBCL());
		setAnalyticsArea();
	}

	/**
	 * Test fetch analytics alerts by type.
	 */
	@Test
	public void testFetchAnalyticsAlertsByType()
	{
		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();

		InternalResultsResponse<Map<String, Integer>> response =
				getAnalyticsBCL().fetchAnalyticsAlertsByType(analyticsRequest);

		assertNotNull(response);
		assertEquals("Should bring 1 lines ", 1, response.getResultsList().size());
	}

	/**
	 * Test fetch analytics alarms.
	 */
	@Test
	public void testFetchAnalyticsAlarms()
	{

		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();

		InternalResultsResponse<Light> internalResult =
				getAnalyticsBCL().fetchAnalyticsAlarmsByStatusId(analyticsRequest);

		assertNotNull(internalResult);
		assertEquals("Should bring 3 lights ", ASSERT_3, internalResult.getResultsList().size());
	}

	/**
	 * Test fetch all analytics installed by group.
	 */
	@Test
	public void testFetchAllAnalyticsInstalledByGroup()
	{
		InternalResultsResponse<AnalyticsGroup> response;

		InquiryAnalyticsRequest request = TestBaseUtil.createInquiryAnalyticsRequest();

		Date endDate = new Date(createEndDate());
		Date initialDate = new Date(createInitialDate());

		request.setEndDate(endDate);
		request.setInitialDate(initialDate);
		request.setPageSize(2);
		request.setStartRow(1);
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_INSTALLED);

		response = getAnalyticsBCL().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertNotNull(response.getResultsList());
		assertEquals(2, response.getResultsList().size());
	}

	/**
	 * Test fetch all analytics warning by group.
	 */
	@Test
	public void testFetchAllAnalyticsWarningByGroup()
	{
		InternalResultsResponse<AnalyticsGroup> response;

		InquiryAnalyticsRequest request = new InquiryAnalyticsRequest(getUserContext());

		Date endDate = new Date(createEndDate());
		Date initialDate = new Date(createInitialDate());

		request.setEndDate(endDate);
		request.setInitialDate(initialDate);
		request.setPageSize(2);
		request.setStartRow(1);
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_WARNING);

		response = getAnalyticsBCL().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertNotNull(response.getResultsList());

		assertEquals(ASSERT_3, response.getResultsList().size());
	}

	/**
	 * Test fetch all analytics consumption by group.
	 */
	@Test
	public void testFetchAllAnalyticsConsumptionByGroup()
	{
		InternalResultsResponse<AnalyticsGroup> response;

		InquiryAnalyticsRequest request = new InquiryAnalyticsRequest(getUserContext());

		Date endDate = new Date(createEndDate());
		Date initialDate = new Date(createInitialDate());

		request.setEndDate(endDate);
		request.setInitialDate(initialDate);
		request.setPageSize(2);
		request.setStartRow(1);
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CONSUMPTION);

		response = getAnalyticsBCL().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertNotNull(response.getResultsList());
		assertEquals(2, response.getResultsList().size());
	}

	/**
	 * Test fetch all analytics alarms by group.
	 */
	@Test
	public void testFetchAllAnalyticsAlarmsByGroup()
	{
		InquiryAnalyticsRequest request = new InquiryAnalyticsRequest(getUserContext());

		Date endDate = new Date(createEndDate());
		Date initialDate = new Date(createInitialDate());

		request.setEndDate(endDate);
		request.setInitialDate(initialDate);
		request.setPageSize(2);
		request.setStartRow(1);
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);

		InternalResultsResponse<AnalyticsGroup> response = getAnalyticsBCL().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertNotNull(response.getResultsList());

		assertEquals(ASSERT_3, response.getResultsList().size());
	}

	/**
	 * Test fetch all analytics eco mode by group.
	 */
	@Test
	public void testFetchAllAnalyticsEcoModeByGroup()
	{
		InternalResultsResponse<AnalyticsGroup> response;

		InquiryAnalyticsRequest request = new InquiryAnalyticsRequest(getUserContext());

		Date endDate = new Date(createEndDate());
		Date initialDate = new Date(createInitialDate());

		request.setEndDate(endDate);
		request.setInitialDate(initialDate);
		request.setPageSize(2);
		request.setStartRow(1);
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ECOMODE);

		response = getAnalyticsBCL().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertNotNull(response.getResultsList());
		assertEquals(ASSERT_3, response.getResultsList().size());
	}

	/**
	 * Test fetch all analytics carbon credits by group.
	 */
	@Test
	public void testFetchAllAnalyticsCarbonCreditsByGroup()
	{
		InquiryAnalyticsRequest request = new InquiryAnalyticsRequest(getUserContext());

		Date endDate = new Date(createEndDate());
		Date initialDate = new Date(createInitialDate());

		request.setEndDate(endDate);
		request.setInitialDate(initialDate);
		request.setPageSize(2);
		request.setStartRow(1);
		request.setAnalyticsTypeEnum(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS);

		InternalResultsResponse<AnalyticsGroup> response = getAnalyticsBCL().fetchAllAnalyticsByGroup(request);
		assertNotNull(response);
		assertNotNull(response.getResultsList());
		assertEquals(ASSERT_3, response.getResultsList().size());
	}

	/**
	 * Test fetch all analytics by date.
	 */
	@Test
	public void testFetchAllAnalyticsByDate()
	{
		// testing without AnalyticsTypeEnum
		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setGroup(new AnalyticsGroup(2));

		InternalResultsResponse<AnalyticsGroupColumns> result =
				getAnalyticsBCL().fetchAllAnalyticsByDate(analyticsRequest);

		assertNotNull(result);
		assertNotNull(result.getResultsList());

		for (AnalyticsTypeEnum atEnum : AnalyticsTypeEnum.values())
		{
			Calendar date = Calendar.getInstance();
			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.SECOND, 0);
			date.add(Calendar.MONTH, -1);

			analyticsRequest = TestBaseUtil.createAnalyticsRequest();
			analyticsRequest.setAnalyticsTypeEnum(atEnum);
			analyticsRequest.setInitialDate(date.getTime());
			date.add(Calendar.MONTH, 1);
			analyticsRequest.setEndDate(date.getTime());

			result = getAnalyticsBCL().fetchAllAnalyticsByDate(analyticsRequest);

			assertNotNull(result);
			assertNotNull(result.getResultsList());
		}
	}

	/**
	 * Test fetch dashboard header.
	 */
	@Test
	public void testFetchDashboardHeader()
	{
		AnalyticsRequest request = TestBaseUtil.createAnalyticsRequest();

		Date endDate = new Date(createEndDate());
		Date initialDate = new Date(createInitialDate());

		request.setEndDate(endDate);
		request.setInitialDate(initialDate);

		InternalResultsResponse<AnalyticsGroupColumns> response =
				getAnalyticsBCL().fetchDashboardHeader(request);

		assertNotNull(response);
		assertEquals("Status should be OperationSuccess", Status.OperationSuccess, response.getStatus());
	}

	/**
	 * Test parse analytics group alarm.
	 */
	@Test
	public void testParseAnalyticsGroupAlarm()
	{

		List<AnalyticsGroupAlarm> originalList = new ArrayList<AnalyticsGroupAlarm>();
		originalList.add(new AnalyticsGroupAlarm(1, BEAVERTON, 1, 2, ANALYTICS_GROUP_3));
		originalList.add(new AnalyticsGroupAlarm(2, I5, ANALYTICS_GROUP_3, ANALYTICS_GROUP_4, ANALYTICS_GROUP_7));
		originalList.add(new AnalyticsGroupAlarm(ANALYTICS_GROUP_3, WOODSTOCK, ANALYTICS_GROUP_5, ANALYTICS_GROUP_6, ANALYTICS_GROUP_11));

		List<AnalyticsGroup> endList = getAnalyticsBCL().parseAnalyticsGroupAlarm(originalList);

		assertNotNull(endList);
		assertEquals(SHOULD_HAVE_THE_SAME_SIZE, originalList.size(), endList.size());

		for (AnalyticsGroup ag : endList)
		{
			assertNotNull(ag.getColumns());

			for (AnalyticsGroupColumns agc : ag.getColumns())
			{
				System.out.println(ag.getName() + SEPARATOR + agc.getDescription() + SEPARATOR + agc.getValue());
			}

		}

	}

	/**
	 * Test parse analytics group warning.
	 */
	@Test
	public void testParseAnalyticsGroupWarning()
	{
		List<AnalyticsGroupWarning> originalList = new ArrayList<AnalyticsGroupWarning>();
		originalList.add(new AnalyticsGroupWarning(1, BEAVERTON, 1, 2, ANALYTICS_GROUP_3));
		originalList.add(new AnalyticsGroupWarning(2, I5, ANALYTICS_GROUP_3, ANALYTICS_GROUP_4, ANALYTICS_GROUP_7));
		originalList.add(new AnalyticsGroupWarning(ANALYTICS_GROUP_3, WOODSTOCK, ANALYTICS_GROUP_5, ANALYTICS_GROUP_6, ANALYTICS_GROUP_11));

		List<AnalyticsGroup> endList = getAnalyticsBCL().parseAnalyticsGroupWarning(originalList);

		assertNotNull(endList);
		assertEquals(SHOULD_HAVE_THE_SAME_SIZE, originalList.size(), endList.size());

		for (AnalyticsGroup ag : endList)
		{
			assertNotNull(ag.getColumns());

			for (AnalyticsGroupColumns agc : ag.getColumns())
			{
				System.out.println(ag.getName() + SEPARATOR + agc.getDescription() + SEPARATOR + agc.getValue());
			}

		}

	}

	/**
	 * Test parse analytics group type light.
	 */
	@Test
	public void testParseAnalyticsGroupTypeLight()
	{

		List<AnalyticsGroupTypeLight> originalList = new ArrayList<AnalyticsGroupTypeLight>();
		originalList.add(new AnalyticsGroupTypeLight(1, BEAVERTON, 1.0, 2.0, ANALYTICS_GROUP_3_0, ANALYTICS_GROUP_6_0));
		originalList.add(new AnalyticsGroupTypeLight(2, I5, ANALYTICS_GROUP_3_0, ANALYTICS_GROUP_4_0, ANALYTICS_GROUP_5_0, ANALYTICS_GROUP_12_0));
		originalList.add(new AnalyticsGroupTypeLight(ANALYTICS_GROUP_3, WOODSTOCK, ANALYTICS_GROUP_5_0, ANALYTICS_GROUP_6_0, ANALYTICS_GROUP_7_0, ANALYTICS_GROUP_18_0));

		List<AnalyticsGroup> endList = getAnalyticsBCL().parseAnalyticsGroupTypeLight(originalList);

		assertNotNull(endList);
		assertEquals(SHOULD_HAVE_THE_SAME_SIZE, originalList.size(), endList.size());

		for (AnalyticsGroup ag : endList)
		{
			assertNotNull(ag.getColumns());
			assertEquals("should have the same number of columns + 1 (total).", ASSERT_4, ag.getColumns().size());

			for (AnalyticsGroupColumns agc : ag.getColumns())
			{
				System.out.println(ag.getName() + SEPARATOR + agc.getDescription() + SEPARATOR + agc.getValue());
			}

		}

	}

	/**
	 * Test parse analytics group carbon credits.
	 */
	@Test
	public void testParseAnalyticsGroupCarbonCredits()
	{

		List<AnalyticsGroupCarbonCredits> originalList = new ArrayList<AnalyticsGroupCarbonCredits>();
		originalList.add(new AnalyticsGroupCarbonCredits(1, BEAVERTON, 1.0, 2.0, ANALYTICS_GROUP_3_0, ANALYTICS_GROUP_4_0));
		originalList.add(new AnalyticsGroupCarbonCredits(2, I5, ANALYTICS_GROUP_3_1, ANALYTICS_GROUP_4_2, ANALYTICS_GROUP_5_0, ANALYTICS_GROUP_6_0));
		originalList.add(new AnalyticsGroupCarbonCredits(ANALYTICS_GROUP_3, WOODSTOCK, ANALYTICS_GROUP_5_1, ANALYTICS_GROUP_6_7, ANALYTICS_GROUP_7_0, ANALYTICS_GROUP_8_2));

		List<AnalyticsGroup> endList = getAnalyticsBCL().parseAnalyticsGroupCarbonCredits(originalList);

		assertNotNull(endList);
		assertEquals(SHOULD_HAVE_THE_SAME_SIZE, originalList.size(), endList.size());

		for (AnalyticsGroup ag : endList)
		{
			assertNotNull(ag.getColumns());
			assertEquals("should have the same number of columns + 1 (total) ", ASSERT_4, ag.getColumns().size());

			for (AnalyticsGroupColumns agc : ag.getColumns())
			{
				System.out.println(ag.getName() + SEPARATOR + agc.getDescription() + SEPARATOR + agc.getValue());
			}

		}

	}

	/**
	 * Test fetch all analytics group.
	 */
	@Test
	public void testFetchAllAnalyticsGroup()
	{

		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();

		InternalResultsResponse<AnalyticsGroup> response =
				getAnalyticsBCL().fetchAllAnalyticsGroup(analyticsRequest);

		assertNotNull(response);
		assertNotNull(response.getResultsList());
		for (AnalyticsGroup analyticsGroup : response.getResultsList())
		{
			System.out.println("Id[" + analyticsGroup.getId() + "] Name[" + analyticsGroup.getName() + "]");

		}
	}

	/**
	 * Test fetch dashboard resume.
	 */
	@Test
	public void testFetchDashboardResume()
	{

		AnalyticsRequest analyticsRequest = TestBaseUtil.createAnalyticsRequest();
		analyticsRequest.setGroup(new AnalyticsGroup(2));

		InternalResultsResponse<AnalyticsGroupColumns> result =
				getAnalyticsBCL().fetchDashboardResume(analyticsRequest);

		assertNotNull(result);
		assertNotNull(result.getResultsList());

		for (AnalyticsGroupColumns agc : result.getResultsList())
		{
			assertNotNull(agc.getAnalyticsTypeEnum());
			assertNotNull(agc.getValue());
			assertNotNull(agc.getAverage());
			assertNotNull(agc.getChange());

			System.out.println(agc.getAnalyticsTypeEnum() + ", total: " + agc.getValue() + ", average: "
					+ agc.getAverage() + ", change:"
					+ agc.getChange());
		}

	}

	/**
	 * Test generate file csv.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Success situation
		InquiryAnalyticsRequest inquiryAnalyticsRequest = TestBaseUtil.createInquiryAnalyticsRequest();
		inquiryAnalyticsRequest.setFileName(TestBaseUtil.FILE_NAME);
		inquiryAnalyticsRequest.setAnalyticsTypeEnum(AnalyticsTypeEnum.LIGHTING_ALARM);
		InquiryAnalyticsResponse response = getAnalyticsBCL().generateFileCSV(inquiryAnalyticsRequest);
		assertNotNull(response);
		assertNotNull(response.getFileName());

		// Errors situations
		// AnalyticsDAC
		this.setSituation(getAnalyticsBCL(), SituationsEnum.ERROR, IAnalyticsDAC.class, "generateFileCSV");
		response = getAnalyticsBCL().generateFileCSV(inquiryAnalyticsRequest);
		assertFalse(response.isOperationSuccess());
		this.assertMessages(response, ERROR_CODE);
		resetMocksToAnalyticsArea();

		// IProcessBCL
		this.setSituation(getAnalyticsBCL(), SituationsEnum.ERROR, IProcessBCL.class, "updateProcess");
		response = getAnalyticsBCL().generateFileCSV(inquiryAnalyticsRequest);
		assertFalse(response.isOperationSuccess());
		this.assertMessages(response, ERROR_CODE);
		resetMocksToAnalyticsArea();

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
		return result;
	}

	/**
	 * Creates the end date.
	 *
	 * @return the long
	 */
	private Long createEndDate()
	{
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(GREGORIAN_CALENDAR_2011, GREGORIAN_CALENDAR_12, GREGORIAN_CALENDAR_28, 0, 0, 0);
		return gregorianCalendar.getTimeInMillis();
	}

	/**
	 * Creates the initial date.
	 *
	 * @return the long
	 */
	private Long createInitialDate()
	{
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(GREGORIAN_CALENDAR_2010, 0, 1, 0, 0, 0);
		return gregorianCalendar.getTimeInMillis();
	}
}
