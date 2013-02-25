package com.sensus.mlc.schedule.bcf;

import static com.sensus.mlc.base.TestBaseUtil.createInquiryScheduleRequest;
import static com.sensus.mlc.base.TestBaseUtil.createScheduleRequest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.util.LCDateUtil;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.schedule.model.DaysOfWeekEnum;
import com.sensus.mlc.schedule.model.Event;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;
import com.sensus.mlc.smartpoint.model.Light;

/**
 * The Class ScheduleBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/schedule/schedulebcfimpltest.xml"})
public class ScheduleBCFTest extends AbstractTestBaseBusiness
{

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

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED. */
	private static final String SENSUS_MLC_SCHEDULEVALIDATOR_EVENT_REQUIRED =
			"sensus.mlc.schedulevalidator.event.required";

	/** The Constant SENSUS_MLC_VALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_ID_REQUIRED = "sensus.mlc.validator.id.required";

	/** The Constant SENSUS_MLC_SCHEDULEVALIDATOR_ID_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED =
			"sensus.mlc.smartpointvalidator.lightrniid.required";

	/** The Constant SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED. */
	private static final String SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED =
			"sensus.mlc.smartpointvalidator.intensity.required";

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

	/** The Constant SENSUS_MLC_VALIDATOR_LIGHT_ID_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_LIGHT_ID_REQUIRED =
			"sensus.mlc.smartpointvalidator.id.required";

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
		return this.scheduleBCF;
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
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

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
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

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
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

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
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

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

		// Error situation - smart point Without id
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		scheduleEvent = getEventSchedule();
		Light light = new Light();
		light.setId(null);
		light.setRniId(1);
		List<Light> lights = new ArrayList<Light>();
		lights.add(light);
		scheduleEvent.setLights(lights);
		request.setSchedule(scheduleEvent);
		response = getScheduleBCF().initiateUpdateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_LIGHT_ID_REQUIRED);

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
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

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
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_INTENSITY_REQUIRED);

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
	 * Test delete smartpoint from schedule.
	 */
	@Test
	public void testDeleteSmartpointFromSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().deleteSmartpointFromSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().deleteSmartpointFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Error situation - Schedule Without RniId
		resetMocksToScheduleArea();
		request = createScheduleRequest();
		EventSchedule scheduleEvent = getEventSchedule();
		Light light = new Light();
		light.setRniId(null);
		List<Light> lights = new ArrayList<Light>();
		lights.add(light);
		scheduleEvent.setLights(lights);
		request.setSchedule(scheduleEvent);
		response = getScheduleBCF().deleteSmartpointFromSchedule(request);
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class, "deleteSmartpointFromSchedule");
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().deleteSmartpointFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().deleteSmartpointFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);
	}

	/**
	 * Test initiate delete smartpoint from schedule.
	 */
	@Test
	public void testInitiateDeleteSmartpointFromSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().initiateDeleteSmartpointFromSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().initiateDeleteSmartpointFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Schedule lights no rni id
		resetMocksToScheduleArea();
		request = createScheduleRequest();
		EventSchedule scheduleEvent = getEventSchedule();
		Light light = new Light();
		light.setId(null);
		List<Light> lights = new ArrayList<Light>();
		lights.add(light);
		scheduleEvent.setLights(lights);
		request.setSchedule(scheduleEvent);
		response = getScheduleBCF().initiateDeleteSmartpointFromSchedule(request);
		assertMessages(response, SENSUS_MLC_VALIDATOR_LIGHT_ID_REQUIRED);

		// Error situation - Request without monitored atributte
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setIsMonitored(null);
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().initiateDeleteSmartpointFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().initiateDeleteSmartpointFromSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);

	}

	/**
	 * Test apply smart point to schedule.
	 */
	@Test
	public void testApplySmartPointToSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		ScheduleResponse response = getScheduleBCF().applySmartPointToSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().applySmartPointToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Schedule lights no rni id
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		List<Light> lights = new ArrayList<Light>();
		lights.add(new Light());
		EventSchedule eventSchedule = getEventSchedule();
		eventSchedule.setLights(lights);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().applySmartPointToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().applySmartPointToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().applySmartPointToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_DEFAULT_EXCEPTION);

	}

	/**
	 * Test initiate apply smart point to schedule.
	 */
	@Test
	public void testInitiateApplySmartPointToSchedule()
	{
		// Success situation
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		request.setSelectionPaginationIds(list);
		ScheduleResponse response = getScheduleBCF().initiateApplySmartPointToSchedule(request);
		assertTrue(response.isOperationSuccess());

		// Error situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		response = getScheduleBCF().initiateApplySmartPointToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		// Error situation - Schedule lights no rni id
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		List<Light> lights = new ArrayList<Light>();
		lights.add(new Light());
		EventSchedule eventSchedule = getEventSchedule();
		eventSchedule.setLights(lights);
		request.setSchedule(eventSchedule);
		response = getScheduleBCF().applySmartPointToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SMARTPOINTVALIDATOR_LIGHT_RNIID_REQUIRED);

		// Error situation - Schedule Without Type
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getEventSchedule());
		request.getSchedule().setScheduleTypeEnum(null);
		response = getScheduleBCF().initiateApplySmartPointToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_SCHEDULEVALIDATOR_TYPE_REQUIRED);

		// Error situation - Request without monitored atributte
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setIsMonitored(null);
		OffsetSchedule offsetSchedule = getOffsetSchedule();
		request.setSchedule(offsetSchedule);
		response = getScheduleBCF().initiateApplySmartPointToSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_IS_MONITORED_REQUIRED);

		// Exception situation
		resetMocksToScheduleArea();
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class);
		request = createScheduleRequest();
		request.setSchedule(getOffsetSchedule());
		response = getScheduleBCF().initiateApplySmartPointToSchedule(request);
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

		Light light = new Light();
		light.setId(1);
		light.setRniId(1);
		light.setOffSetSchedule(offSetSchedule);
		lights.add(light);

		offSetSchedule.setLights(lights);

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

		Light light = new Light();
		light.setId(1);
		light.setRniId(1);
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
