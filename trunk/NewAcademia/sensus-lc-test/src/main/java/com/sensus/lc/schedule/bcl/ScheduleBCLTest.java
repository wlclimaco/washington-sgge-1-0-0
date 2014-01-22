package com.sensus.lc.schedule.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.createDaysOfWeek;
import static com.sensus.lc.base.TestBaseUtil.createInquiryScheduleRequest;
import static com.sensus.lc.base.TestBaseUtil.createEventSchedule;
import static com.sensus.lc.base.TestBaseUtil.createScheduleRequest;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.util.LCPropertiesExtractorUtil;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.schedule.dac.IScheduleDAC;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;

/**
 * The Class ScheduleBCLTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/schedule/schedulebclimpltest.xml"})
public class ScheduleBCLTest extends AbstractTestBaseBusiness
{

	/** The Constant SUNRISE_SUNSET_NEGATIVE. */
	private static final String SUNRISE_SUNSET_NEGATIVE = "SunriseSunsetNegative";

	/** The Constant UPDATE_SCHEDULE. */
	private static final String UPDATE_SCHEDULE = "updateSchedule";

	/** The Constant FETCH_SCHEDULE_BY_ID. */
	private static final String FETCH_SCHEDULE_BY_ID = "fetchScheduleById";

	/** The Constant FETCH_LIGHT_SCHEDULE_BY_ID. */
	private static final String FETCH_LIGHT_SCHEDULE_BY_ID = "fetchLightScheduleById";

	/** The Constant APPLY_LIGHT_TO_SCHEDULE. */
	private static final String APPLY_LIGHT_TO_SCHEDULE = "applyLightToSchedule";

	/** The Constant INSERT_SCHEDULE. */
	private static final String INSERT_SCHEDULE = "insertSchedule";

	/** The Constant INSERT_PROCESS. */
	private static final String INSERT_PROCESS = "insertProcess";

	/** The Constant SUBMIT_PROCESS. */
	private static final String SUBMIT_PROCESS = "submitProcess";

	/** The Constant DELETE_SCHEDULE. */
	private static final String DELETE_SCHEDULE = "deleteSchedule";

	/** The Constant SCHEDULE_DELETED. */
	private static final String SCHEDULE_DELETED = "sensus.mlc.schedulebcfimpl.mlcscheduledeleted";

	/** The Constant SENSUS_MLC_SCHEDULEBCLIMPL_TOOMANYEVENTS. */
	private static final String SENSUS_MLC_SCHEDULEBCLIMPL_TOOMANYEVENTS = "sensus.mlc.schedulebclimpl.toomanyevents";

	/** The Constant SENSUS_MLC_SCHEDULEBCLIMPL_RNIEXCEPTION. */
	private static final String SENSUS_MLC_SCHEDULEBCLIMPL_RNIEXCEPTION = "sensus.mlc.schedulebcfimpl.rniexception";

	/** The Constant SENSUS_MLC_SCHEDULEBCLIMPL_MLCEXCEPTION. */
	private static final String SENSUS_MLC_SCHEDULEBCLIMPL_MLCEXCEPTION = "sensus.mlc.schedulebcfimpl.mlcexception";

	/** The Constant ARBITRARY_INTENSITY_10. */
	private static final Integer ARBITRARY_INTENSITY_10 = 10;

	/** The Constant ARBITRARY_INTENSITY_20. */
	private static final Integer ARBITRARY_INTENSITY_20 = 20;

	/** The Constant ARBITRARY_INTENSITY_30. */
	private static final Integer ARBITRARY_INTENSITY_30 = 30;

	/** The Constant ARBITRARY_INTENSITY_40. */
	private static final Integer ARBITRARY_INTENSITY_40 = 40;

	/** The Constant ARBITRARY_INTENSITY_50. */
	private static final Integer ARBITRARY_INTENSITY_50 = 50;

	/** The Constant ARBITRARY_PAGINATION_ID_4. */
	private static final Integer ARBITRARY_PAGINATION_ID_4 = 4;

	/** The schedule bcl. */
	private IScheduleBCL scheduleBCL;

	/**
	 * Gets the schedule bcl.
	 * 
	 * @return the schedule bcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return scheduleBCL;
	}

	/**
	 * Sets the schedule bcl.
	 * 
	 * @param scheduleBCL the new schedule bcl
	 */
	@Resource(name = "scheduleBCLTarget")
	public void setScheduleBCL(IScheduleBCL scheduleBCL)
	{
		this.scheduleBCL = scheduleBCL;
	}

	/**
	 * Sets the schedule area.
	 */
	public void setScheduleArea()
	{
		setArea(getScheduleBCL(), LCAreaEnum.SCHEDULE);
	}

	/**
	 * Removes the schedule area.
	 */
	@After
	public void removeScheduleArea()
	{
		setArea(getScheduleBCL(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Removes the eco mode area.
	 */
	@After
	public void resetMocksToScheduleArea()
	{
		resetMocksData(getScheduleBCL());
		setScheduleArea();
	}

	/**
	 * Test insert schedule.
	 */
	@Test
	public void testInsertSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().insertSchedule(request);
		assertResponse(response);

		// Error situation - Insert Schedule
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, INSERT_SCHEDULE);
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().insertSchedule(request);
		this.assertMessages(response, ERROR_CODE);

		// Error situation - Too many Events
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, INSERT_SCHEDULE);
		request = createScheduleRequest();
		EventSchedule schedule = createEventSchedule();
		schedule.getEvents().add(TestBaseUtil.createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_10));
		schedule.getEvents().add(TestBaseUtil.createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_20));
		schedule.getEvents().add(TestBaseUtil.createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_30));
		schedule.getEvents().add(TestBaseUtil.createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_40));
		schedule.getEvents().add(TestBaseUtil.createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_50));
		request.setSchedule(schedule);
		response = getScheduleBCL().insertSchedule(request);
		this.assertMessages(response, SENSUS_MLC_SCHEDULEBCLIMPL_TOOMANYEVENTS);

		// Error situation - Insert Process
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);
		request = createScheduleRequest();
		OffsetSchedule scheduleOffSet = TestBaseUtil.createOffSetSchedule();
		request.setSchedule(scheduleOffSet);
		response = getScheduleBCL().insertSchedule(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch schedule by id.
	 */
	@Test
	public void testFetchScheduleById()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().fetchScheduleById(request);
		assertResponse(response);

		// Success situation - Offset
		request = createScheduleRequest();
		OffsetSchedule scheduleOffSet = TestBaseUtil.createOffSetSchedule();
		request.setSchedule(scheduleOffSet);
		response = getScheduleBCL().fetchScheduleById(request);
		assertResponse(response);

		// Success situation - Offset - Sunset/Sunrise Negative
		request = createScheduleRequest();
		scheduleOffSet = TestBaseUtil.createOffSetSchedule();
		scheduleOffSet.setName(SUNRISE_SUNSET_NEGATIVE);
		request.setSchedule(scheduleOffSet);
		response = getScheduleBCL().fetchScheduleById(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class);
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().fetchScheduleById(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test update schedule offset.
	 * 
	 * @throws DatatypeConfigurationException the datatype configuration exception
	 */
	@Test
	public void testUpdateScheduleOffset() throws DatatypeConfigurationException
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(TestBaseUtil.createOffSetSchedule());
		InternalResponse response = getScheduleBCL().initiateUpdateSchedule(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleBCL.class);
		request.setSchedule(TestBaseUtil.createOffSetSchedule());
		response = getScheduleBCL().initiateUpdateSchedule(request);
		// We can't assert the response if is true or false, because the DAC don't bring response
		assertNotNull(response);

		// Error situation - RNI Failure
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, UPDATE_SCHEDULE);
		request.setSchedule(TestBaseUtil.createOffSetSchedule());
		response = getScheduleBCL().initiateUpdateSchedule(request);
		this.assertMessages(response, SENSUS_MLC_SCHEDULEBCLIMPL_RNIEXCEPTION);

		// Error situation - submitProcess
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class, SUBMIT_PROCESS);
		request.setSchedule(TestBaseUtil.createOffSetSchedule());
		response = getScheduleBCL().initiateUpdateSchedule(request);
		this.assertMessages(response, SENSUS_MLC_SCHEDULEBCLIMPL_MLCEXCEPTION);
	}

	/**
	 * Test update schedule event.
	 * 
	 * @throws DatatypeConfigurationException the datatype configuration exception
	 */
	@Test
	public void testUpdateScheduleEvent() throws DatatypeConfigurationException
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().initiateUpdateSchedule(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleBCL.class);
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().initiateUpdateSchedule(request);
		// We can't assert the response if is true or false, because the DAC don't bring response
		assertNotNull(response);
	}

	/**
	 * Test fetch all schedules.
	 */
	@Test
	public void testFetchAllSchedules()
	{
		// Success situation
		InquiryScheduleRequest request = createInquiryScheduleRequest();
		InternalResponse response = getScheduleBCL().fetchAllSchedules(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class);
		request = createInquiryScheduleRequest();
		response = getScheduleBCL().fetchAllSchedules(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete schedule.
	 */
	@Test
	public void testDeleteSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().deleteSchedule(request);
		assertMessages(response, SCHEDULE_DELETED);

		// Error situation - fetchScheduleById
		resetMocksToScheduleArea();
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, FETCH_SCHEDULE_BY_ID);
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().deleteSchedule(request);
		assertMessages(response, ERROR_CODE);

		// Error situation - deleteSchedule
		resetMocksToScheduleArea();
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, DELETE_SCHEDULE);
		request = createScheduleRequest();
		EventSchedule schedule = createEventSchedule();
		request.setSchedule(schedule);
		response = getScheduleBCL().deleteSchedule(request);
		assertMessages(response, ERROR_CODE);

		// Error situation - insertProcess
		resetMocksToScheduleArea();
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class, INSERT_PROCESS);
		request = createScheduleRequest();
		schedule = createEventSchedule();
		request.setSchedule(schedule);
		response = getScheduleBCL().deleteSchedule(request);
		assertMessages(response, SCHEDULE_DELETED, ERROR_CODE);
	}

	/**
	 * Test initiate delete schedule.
	 */
	@Test
	public void testInitiateDeleteSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		List<Integer> paginationIds = new ArrayList<Integer>();
		paginationIds.add(ARBITRARY_PAGINATION_ID_4);
		request.setSelectionPaginationIds(paginationIds);
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().initiateDeleteSchedule(request);
		assertResponse(response);

		// Error situation - submitProcess
		resetMocksToScheduleArea();
		request = createScheduleRequest();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class, SUBMIT_PROCESS);
		request.setSelectionPaginationIds(paginationIds);
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().initiateDeleteSchedule(request);
		this.assertMessages(response, ERROR_CODE);

		// Error situation - deleteSchedule
		resetMocksToScheduleArea();
		request = createScheduleRequest();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, DELETE_SCHEDULE);
		request.setSelectionPaginationIds(paginationIds);
		request.setSchedule(createEventSchedule());
		request.setIsMonitored(true);
		response = getScheduleBCL().initiateDeleteSchedule(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test delete light from schedule.
	 */
	@Test
	public void testDeleteLightFromSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().deleteLightFromSchedule(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class);
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().deleteLightFromSchedule(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test apply light to schedule.
	 */
	@Test
	public void testApplyLightToSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		request.getSchedule().setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		request.getSchedule().setLights(Arrays.asList(TestBaseUtil.createLight().getId()));
		InternalResponse response = getScheduleBCL().applyLightToSchedule(request);
		assertResponse(response);

		// Validation situation
		resetMocksToScheduleArea();

		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		request.getSchedule().setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		request.getSchedule().setLights(Arrays.asList(TestBaseUtil.createLight().getId()));
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, APPLY_LIGHT_TO_SCHEDULE);
		response = getScheduleBCL().applyLightToSchedule(request);
		assertMessages(response, ERROR_CODE);

		// Validation situation
		resetMocksToScheduleArea();
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		request.getSchedule().setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		request.getSchedule().setLights(Arrays.asList(TestBaseUtil.createLight().getId()));
		setSituation(getScheduleBCL(), SituationsEnum.VALIDATION, ILightBCL.class,
				FETCH_LIGHT_SCHEDULE_BY_ID);
		response = getScheduleBCL().applyLightToSchedule(request);
		assertResponse(response);

		// Error situation - applyLightToSchedule
		resetMocksToScheduleArea();
		setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class, APPLY_LIGHT_TO_SCHEDULE);
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		request.getSchedule().setLights(Arrays.asList(TestBaseUtil.createLight().getId()));
		response = getScheduleBCL().applyLightToSchedule(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test initiate delete light from schedule.
	 */
	@Test
	public void testInitiateDeleteLightFromSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().initiateDeleteLightFromSchedule(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().initiateDeleteLightFromSchedule(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test initiate apply light to schedule.
	 */
	@Test
	public void testInitiateApplyLightToSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		List<Light> lightList = new ArrayList<Light>();
		Light light = new Light();
		light.setId(1);
		lightList.add(light);
		request.getSchedule().setLights(LCPropertiesExtractorUtil.extractLightId(lightList));

		InternalResponse response = getScheduleBCL().initiateApplyLightToSchedule(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IProcessBCL.class);
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		request.getSchedule().setLights(LCPropertiesExtractorUtil.extractLightId(lightList));
		response = getScheduleBCL().initiateApplyLightToSchedule(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test apply unknown event schedule.
	 */
	@Test
	public void testApplyUnknownEventSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().applyUnknownEventSchedule(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class);
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().applyUnknownEventSchedule(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test apply unknown offset schedule.
	 */
	@Test
	public void testApplyUnknownOffsetSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		InternalResponse response = getScheduleBCL().applyUnknownOffsetSchedule(request);
		assertResponse(response);

		// Error situation
		resetMocksToScheduleArea();
		this.setSituation(getScheduleBCL(), SituationsEnum.ERROR, IScheduleDAC.class);
		request = createScheduleRequest();
		request.setSchedule(createEventSchedule());
		response = getScheduleBCL().applyUnknownOffsetSchedule(request);
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch can insert.
	 */
	@Test
	public void testFetchCanInsert()
	{
		EventSchedule schedule = createEventSchedule();
		List<MessageInfo> list = new ArrayList<MessageInfo>();
		getScheduleBCL().fetchCanInsert(schedule, list, TestBaseUtil.createTenant());
	}

	/**
	 * Test fetch can update.
	 */
	@Test
	public void testFetchCanUpdate()
	{
		EventSchedule schedule = createEventSchedule();
		List<MessageInfo> list = new ArrayList<MessageInfo>();
		getScheduleBCL().fetchCanUpdate(schedule, list, TestBaseUtil.createTenant());
	}
}
