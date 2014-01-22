package com.sensus.lc.schedule.bcf;

import static com.sensus.lc.base.TestBaseUtil.createInquiryScheduleRequest;
import static com.sensus.lc.base.TestBaseUtil.createScheduleRequest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.base.util.LCPropertiesExtractorUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.schedule.bcl.IScheduleBCL;
import com.sensus.lc.schedule.model.DaysOfWeekEnum;
import com.sensus.lc.schedule.model.Event;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.lc.schedule.model.response.ScheduleResponse;

/**
 * The Class ScheduleBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/schedule/schedulebcfimpltest.xml"})
public class ScheduleBCFTest extends AbstractTestBaseBusiness
{
	private static final String DELETE_LIGHT_FROM_SCHEDULE = "deleteLightFromSchedule";

	/** The Constant RANDOM. */
	public static final Random RANDOM = new Random();

	/** The Constant NUMBER_RANGE. */
	public static final Integer NUMBER_RANGE = Integer.MAX_VALUE;

	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "Description";

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The Constant SCHEDULE_DEFAULT_EXCEPTION. */
	private static final String SCHEDULE_DEFAULT_EXCEPTION = "sensus.mlc.schedulebcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED =
			"sensus.mlc.schedulevalidator.type.required";

	/** The Constant SENSUS_MLC_VALIDATOR_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_REQUIRED = "sensus.mlc.validator.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED =
			"sensus.mlc.schedulevalidator.event.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.lightvalidator.intensity.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_TIME_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_TIME_REQUIRED =
			"sensus.mlc.schedulevalidator.event.time.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_DAY_OF_WEEK_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_DAY_OF_WEEK_REQUIRED =
			"sensus.mlc.schedulevalidator.event.day.of.week.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_SUNRISEOFFSET_GREATER_THAN_ZERO. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_SUNRISEOFFSET_GREATER_THAN_ZERO =
			"sensus.mlc.schedulevalidator.sunriseoffset.greaterthanzero";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_SUNSETOFFSET_GREATER_THAN_ZERO. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_SUNSETOFFSET_GREATER_THAN_ZERO =
			"sensus.mlc.schedulevalidator.sunsetoffset.greaterthanzero";

	/** The Constant SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED =
			"sensus.mlc.validator.is.monitored.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_OFFSET_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_OFFSET_REQUIRED =
			"sensus.mlc.schedulevalidator.offset.required";

	/** The Constant SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED =
			"sensus.mlc.validator.selection.pagination.id.required";

	/** The Constant SENSUS_MLC_LIGHTVALIDATOR_LIGHTLIST_REQUIRED. */
	private static final String SENSUS_MLC_LIGHTVALIDATOR_LIGHTLIST_REQUIRED =
			"sensus.mlc.lightvalidator.lightlist.required";

	/** The Constant ARBITRARY_SUNRISE_OFF_SET_MINUTES_N10. */
	private static final Integer ARBITRARY_SUNRISE_OFF_SET_MINUTES_N10 = -10;

	/** The Constant ARBITRARY_SUNSET_OFF_SET_MINUTES_N10. */
	private static final Integer ARBITRARY_SUNSET_OFF_SET_MINUTES_N10 = -10;

	/** The Constant ARBITRARY_OFF_SET_SCHEDULE_5. */
	private static final Integer ARBITRARY_OFF_SET_SCHEDULE_5 = 5;

	/** The Constant ARBITRARY_INTENSITY_10. */
	private static final Integer ARBITRARY_INTENSITY_10 = 10;

	/** The Constant ARBITRARY_SUNRISE_OFF_SET_MINUTES_5. */
	private static final Integer ARBITRARY_SUNRISE_OFF_SET_MINUTES_5 = 5;

	/** The Constant ARBITRARY_SUNSET_OFF_SET_MINUTES_10. */
	private static final Integer ARBITRARY_SUNSET_OFF_SET_MINUTES_10 = 10;

	/** The Constant ARBITRARY_EVENT_SCHEDULE_5. */
	private static final Integer ARBITRARY_EVENT_SCHEDULE_5 = 5;

	/** The Constant ARBITRARY_YEAR_2011. */
	private static final Integer ARBITRARY_YEAR_2011 = 2011;

	/** The Constant ARBITRARY_MONTH_9. */
	private static final Integer ARBITRARY_MONTH_9 = 9;

	/**
	 * Gets the schedule bcf.
	 *
	 * @return the schedule bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 *
	 * @param scheduleBCF the new schedule bcf
	 */
	@Resource(name = "scheduleBCFTarget")
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Sets the schedule area.
	 */
	public void setScheduleArea()
	{
		setArea(getScheduleBCF(), LCAreaEnum.SCHEDULE);
	}

	/**
	 * Removes the schedule area.
	 */
	@After
	public void removeScheduleArea()
	{
		setArea(getScheduleBCF(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Test insert schedule.
	 */
	@Test
	public void testInsertSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().insertSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Schedule Without Schedule
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_REQUIRED);

		// Error situation - Schedule Without name
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setName(null);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Error situation - Schedule Event Without Events
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		EventSchedule eventSchedule = getEventSchedule();
		eventSchedule.setEvents(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED);

		// Error situation - Schedule Event Without intensity
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setIntensity(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		// Error situation - Schedule Event Without time
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setTime(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_TIME_REQUIRED);

		// Error situation - Schedule Event Without days
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setDays(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_DAY_OF_WEEK_REQUIRED);

		// Error situation - Offset Schedule Without minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		OffsetSchedule offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Offset Schedule Without minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunsetOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Offset Schedule negative minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(ARBITRARY_SUNRISE_OFF_SET_MINUTES_N10);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_SUNSETOFFSET_GREATER_THAN_ZERO);

		// Error situation - Offset Schedule negative minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunsetOffsetMinutes(ARBITRARY_SUNSET_OFF_SET_MINUTES_N10);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_SUNRISEOFFSET_GREATER_THAN_ZERO);

		// Error situation - Offset Schedule null minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(null);
		offsetSchedule.setSunsetOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_OFFSET_REQUIRED);

		// Error situation - Offset Schedule Without intensity
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setIntensity(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);

	}

	/**
	 * Test fetch all schedules.
	 */
	@Test
	public void testFetchAllSchedules()
	{

		// Success situation
		InquiryScheduleRequest request = createInquiryScheduleRequest();
		InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedules(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createInquiryScheduleRequest();
		response = getScheduleBCF().fetchAllSchedules(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createInquiryScheduleRequest();
		response = getScheduleBCF().fetchAllSchedules(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);

	}

	/**
	 * Test initiate delete schedule.
	 */
	@Test
	public void testInitiateDeleteSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		request.setSelectionPaginationIds(ids);
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().initiateDeleteSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(ids);
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().initiateDeleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Without ids
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().initiateDeleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_SELECTION_PAGINATION_ID_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(ids);
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().initiateDeleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);
	}

	/**
	 * Test delete schedule.
	 */
	@Test
	public void testDeleteSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().deleteSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().deleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Without schedule id
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		EventSchedule eventSchedule = getEventSchedule();
		eventSchedule.setId(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().deleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().deleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);

	}

	/**
	 * Test update schedule.
	 */
	@Test
	public void testUpdateSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().updateSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Without id
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		EventSchedule scheduleEvent = getEventSchedule();
		scheduleEvent.setId(null);
		request.setSchedule(scheduleEvent);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Error situation - Schedule Without name
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setName(null);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Error situation - Schedule Event Without Events
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		EventSchedule eventSchedule = getEventSchedule();
		eventSchedule.setEvents(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED);

		// Error situation - Schedule Event Without intensity
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setIntensity(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		// Error situation - Schedule Event Without time
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setTime(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_TIME_REQUIRED);

		// Error situation - Schedule Event Without days
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setDays(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_DAY_OF_WEEK_REQUIRED);

		// Error situation - Offset Schedule Without minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		OffsetSchedule offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Offset Schedule Without minutes Sunrise and Sunset
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(null);
		offsetSchedule.setSunsetOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_OFFSET_REQUIRED);

		// Error situation - Offset Schedule Without minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunsetOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Offset Schedule negative minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(ARBITRARY_SUNRISE_OFF_SET_MINUTES_N10);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_SUNSETOFFSET_GREATER_THAN_ZERO);

		// Error situation - Offset Schedule negative minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunsetOffsetMinutes(ARBITRARY_SUNSET_OFF_SET_MINUTES_N10);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_SUNRISEOFFSET_GREATER_THAN_ZERO);

		// Error situation - Offset Schedule Without intensity
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setIntensity(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);
	}

	/**
	 * Test initiate update schedule.
	 */
	@Test
	public void testInitiateUpdateSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().initiateUpdateSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);

		// Validation situation
		testInitiateUpdateScheduleValidation();
	}

	private void testInitiateUpdateScheduleValidation()
	{
		ScheduleRequest request;
		ScheduleResponse response;
		// Error situation - Without id
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		EventSchedule scheduleEvent = getEventSchedule();
		scheduleEvent.setId(null);
		request.setSchedule(scheduleEvent);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Error situation - light Without id
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		scheduleEvent = getEventSchedule();
		scheduleEvent.setLights(null);
		request.setSchedule(scheduleEvent);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_LIGHTLIST_REQUIRED);

		// Error situation - Schedule Without name
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setName(null);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_NAME_REQUIRED);

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Error situation - Schedule Event Without Events
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		EventSchedule eventSchedule = getEventSchedule();
		eventSchedule.setEvents(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED);

		// Error situation - Schedule Event Without intensity
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setIntensity(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		// Error situation - Schedule Event Without time
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setTime(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_TIME_REQUIRED);

		// Error situation - Schedule Event Without days
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		eventSchedule = getEventSchedule();
		eventSchedule.getEvents().get(0).setDays(null);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_DAY_OF_WEEK_REQUIRED);

		// Error situation - Offset Schedule Without minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		OffsetSchedule offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Offset Schedule Without minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunsetOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Offset Schedule Without minutes Sunrise and Sunset
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(null);
		offsetSchedule.setSunsetOffsetMinutes(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_OFFSET_REQUIRED);

		// Error situation - Offset Schedule negative minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunriseOffsetMinutes(ARBITRARY_SUNRISE_OFF_SET_MINUTES_N10);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_SUNSETOFFSET_GREATER_THAN_ZERO);

		// Error situation - Offset Schedule negative minutes
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setSunsetOffsetMinutes(ARBITRARY_SUNSET_OFF_SET_MINUTES_N10);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_SUNRISEOFFSET_GREATER_THAN_ZERO);

		// Error situation - Offset Schedule Without intensity
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		offsetSchedule = getOffsetSchedule();
		offsetSchedule.setIntensity(null);
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_LIGHTVALIDATOR_INTENSITY_REQUIRED);

		// Error situation - Request without monitored atributte
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setIsMonitored(null);
		offsetSchedule = getOffsetSchedule();
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);
	}

	/**
	 * Test fetch schedule by id.
	 */
	@Test
	public void testFetchScheduleById()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().fetchScheduleById(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().fetchScheduleById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Without id
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		EventSchedule scheduleEvent = getEventSchedule();
		scheduleEvent.setId(null);
		request.setSchedule(scheduleEvent);
		response = getScheduleBCF().fetchScheduleById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().fetchScheduleById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);
	}

	/**
	 * Test delete light from schedule.
	 */
	@Test
	public void testDeleteLightFromSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().deleteLightFromSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().deleteLightFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class, DELETE_LIGHT_FROM_SCHEDULE);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().deleteLightFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().deleteLightFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);
	}

	/**
	 * Test initiate delete light from schedule.
	 */
	@Test
	public void testInitiateDeleteLightFromSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().initiateDeleteLightFromSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().initiateDeleteLightFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Request without monitored atributte
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setIsMonitored(null);
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().initiateDeleteLightFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().initiateDeleteLightFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);

	}

	/**
	 * Test apply light to schedule.
	 */
	@Test
	public void testApplyLightToSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSelectionPaginationIds(Arrays.asList(TestBaseUtil.createLight().getId()));
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().applyLightToSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(Arrays.asList(TestBaseUtil.createLight().getId()));
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().applyLightToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(Arrays.asList(TestBaseUtil.createLight().getId()));
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().applyLightToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(Arrays.asList(TestBaseUtil.createLight().getId()));
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().applyLightToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);

	}

	/**
	 * Test initiate apply light to schedule.
	 */
	@Test
	public void testInitiateApplyLightToSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		request.setSelectionPaginationIds(list);
		ScheduleResponse response = getScheduleBCF().initiateApplyLightToSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(list);
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().initiateApplyLightToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(list);
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().initiateApplyLightToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Error situation - Request without monitored atributte
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(list);
		request.setIsMonitored(null);
		OffsetSchedule offsetSchedule = getOffsetSchedule();
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().initiateApplyLightToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSelectionPaginationIds(list);
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().initiateApplyLightToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);
	}

	/**
	 * Gets the offset schedule.
	 *
	 * @return the offset schedule
	 */
	private OffsetSchedule getOffsetSchedule()
	{
		OffsetSchedule offSetSchedule = new OffsetSchedule();

		List<Light> lights = new ArrayList<Light>();

		// Test without changes
		offSetSchedule.setId(ARBITRARY_OFF_SET_SCHEDULE_5);
		offSetSchedule.setName("Offset Schedule");
		offSetSchedule.setDescription(DESCRIPTION);
		offSetSchedule.setSunriseOffsetMinutes(ARBITRARY_SUNRISE_OFF_SET_MINUTES_5);
		offSetSchedule.setSunsetOffsetMinutes(ARBITRARY_SUNSET_OFF_SET_MINUTES_10);
		offSetSchedule.setSunriseBefore(true);
		offSetSchedule.setSunsetBefore(true);
		offSetSchedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		offSetSchedule.setIntensity(ARBITRARY_INTENSITY_10);

		Light light = TestBaseUtil.createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(RANDOM.nextInt(NUMBER_RANGE)));

		light.setOffsetSchedule(offSetSchedule);
		lights.add(light);

		offSetSchedule.setLights(LCPropertiesExtractorUtil.extractLightId(lights));

		return offSetSchedule;
	}

	/**
	 * Gets the event schedule.
	 *
	 * @return the event schedule
	 */
	private EventSchedule getEventSchedule()
	{

		EventSchedule eventSchedule = new EventSchedule();

		Light light = TestBaseUtil.createLight();
		light.getRadio().setFlexNetId(BigInteger.valueOf(RANDOM.nextInt(NUMBER_RANGE)));

		List<Light> lights = new ArrayList<Light>();
		lights.add(light);

		// Test without changes
		eventSchedule.setId(ARBITRARY_EVENT_SCHEDULE_5);
		eventSchedule.setName("Event Schedule");
		eventSchedule.setDescription(DESCRIPTION);
		List<Event> eventList = new ArrayList<Event>();
		Event event = new Event();
		eventSchedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);
		List<DaysOfWeekEnum> daysOfWeek = new ArrayList<DaysOfWeekEnum>();
		daysOfWeek.add(DaysOfWeekEnum.WEDNESDAY);
		event.setDays(daysOfWeek);
		event.setIntensity(ARBITRARY_INTENSITY_10);

		Date date = LCDateUtil.getNewDateUTC();
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(ARBITRARY_YEAR_2011, 0, ARBITRARY_MONTH_9, 0, 0, 0);
		date.setTime(gregorianCalendar.getTimeInMillis());
		event.setTime(date);
		eventList.add(event);
		eventSchedule.setEvents(eventList);

		eventSchedule.setLights(LCPropertiesExtractorUtil.extractLightId(lights));

		return eventSchedule;

	}

	/**
	 * Removes the eco mode area.
	 */
	@After
	public void resetMocksToScheduleArea()
	{
		resetMocksData(getScheduleBCF());
		setScheduleArea();
	}

}
