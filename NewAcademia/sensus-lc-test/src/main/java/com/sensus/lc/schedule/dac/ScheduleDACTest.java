package com.sensus.lc.schedule.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createDaysOfWeek;
import static com.sensus.lc.base.TestBaseUtil.createEvent;
import static com.sensus.lc.base.TestBaseUtil.createInquiryScheduleRequest;
import static com.sensus.lc.base.TestBaseUtil.createOffSetSchedule;
import static com.sensus.lc.base.TestBaseUtil.createEventSchedule;
import static com.sensus.lc.base.TestBaseUtil.createScheduleRequest;
import static com.sensus.lc.base.TestBaseUtil.createUnknownOffsetScheduleRequest;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.util.LCPropertiesExtractorUtil;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.schedule.model.DaysOfWeekEnum;
import com.sensus.lc.schedule.model.Event;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.OffsetSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.ScheduleTypeEnum;
import com.sensus.lc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class ScheduleDACTest.
 */
@ContextConfiguration(locations = {
		"classpath:sensus-slc-dim-table-config-context.xml"
		, "classpath:sensus-slc-part-number-context.xml"})
public class ScheduleDACTest extends AbstractTestBaseDAC
{
	/** The light default. */
	private Light lightDefault;

	/** The Constant RANDOM. */
	public static final Random RANDOM = new Random();

	/** The Constant NUMBER_RANGE. */
	public static final Integer NUMBER_RANGE = Integer.MAX_VALUE;

	/** The Constant TENANT. */
	private static final Tenant TENANT = new Tenant(1);

	/** The Constant ARBITRARY_INTENSITY_10. */
	private static final Integer ARBITRARY_INTENSITY_10 = 10;

	/** The Constant ARBITRARY_INTENSITY_20. */
	private static final Integer ARBITRARY_INTENSITY_20 = 20;

	/** The Constant ARBITRARY_INTENSITY_0. */
	private static final Integer ARBITRARY_INTENSITY_0 = 0;

	/** The Constant ARBITRARY_SUNRISE_OFF_SET_MINUTES_5. */
	private static final Integer ARBITRARY_SUNRISE_OFF_SET_MINUTES_25 = 25;

	/** The Constant ARBITRARY_SUNSET_OFF_SET_MINUTES_10. */
	private static final Integer ARBITRARY_SUNSET_OFF_SET_MINUTES_10 = 10;

	/** The Constant ARBITRARY_OFF_SET_SCHEDULE_ID. */
	private static final Integer ARBITRARY_OFF_SET_SCHEDULE_ID = 9999999;

	/** The Constant ARBITRARY_PAGE_SIZE_10. */
	private static final Integer ARBITRARY_PAGE_SIZE_10 = 10;

	/**
	 * Gets the light default.
	 *
	 * @return the light default
	 */
	public Light getLightDefault()
	{
		return lightDefault;
	}

	/**
	 * Sets the light default.
	 *
	 * @param lightDefault the new light default
	 */
	public void setLightDefault(Light lightDefault)
	{
		this.lightDefault = lightDefault;
	}

	/**
	 * Setup test.
	 */
	@Before
	public void setupTest()
	{
		setCacheStatementScope(getScheduleDAC());

		if (getLightDefault() == null)
		{
			setLightDefault(insertLight());
		}
	}

	/**
	 * Test insert schedule event.
	 */
	@Test
	public void testInsertScheduleEvent()
	{
		// create two events
		Event eventOne = createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_10);
		Event eventTwo = createEvent(createDaysOfWeek(), ARBITRARY_INTENSITY_20);

		List<Event> events = new ArrayList<Event>();
		events.add(eventOne);
		events.add(eventTwo);

		// create a new Schedule
		EventSchedule eventSchedule = createEventSchedule();
		eventSchedule.setEvents(events);

		// create a new light and add at schedule event
		List<Light> lights = new ArrayList<Light>();
		lights.add(insertLight());
		eventSchedule.setLights(LCPropertiesExtractorUtil.extractLightId(lights));

		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(eventSchedule);

		InternalResultsResponse<Schedule> response = getScheduleDAC().insertSchedule(request);
		assertResultResponse(response);
	}

	/**
	 * Test insert schedule offset.
	 */
	@Test
	public void testInsertScheduleOffset()
	{
		OffsetSchedule offSetSchedule = createOffSetSchedule();

		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(offSetSchedule);

		InternalResultsResponse<Schedule> response = getScheduleDAC().insertSchedule(request);
		assertResultResponse(response);
	}

	/**
	 * Test apply light to schedule.
	 */
	@Test
	public void testApplyLightToSchedule()
	{
		ScheduleRequest request = createScheduleRequest();
		OffsetSchedule schedule = insertScheduleOffset(request);

		applyLightToSchedule(schedule, Arrays.asList(getLightDefault()));
	}

	/**
	 * Test apply unknown offset schedule.
	 */
	@Test
	public void testApplyUnknownOffsetSchedule()
	{
		ScheduleRequest scheduleRequest = createUnknownOffsetScheduleRequest();
		OffsetSchedule offsetSchedule = insertScheduleOffset(scheduleRequest);

		offsetSchedule.getLights().add(getLightDefault().getId());
		scheduleRequest.setSchedule(offsetSchedule);

		InternalResponse response = getScheduleDAC().applyUnknownOffsetSchedule(scheduleRequest);
		assertResponse(response);
	}

	/**
	 * Test apply unknown event schedule.
	 */
	@Test
	public void testApplyUnknownEventSchedule()
	{
		ScheduleRequest scheduleRequest = createUnknownOffsetScheduleRequest();
		EventSchedule eventSchedule = insertScheduleEvent(scheduleRequest, Arrays.asList(getLightDefault()));
		scheduleRequest.setSchedule(eventSchedule);

		InternalResponse response = getScheduleDAC().applyUnknownEventSchedule(scheduleRequest);
		assertResponse(response);
	}

	/**
	 * Test update schedule event.
	 */
	@Test
	public void testUpdateScheduleEvent()
	{
		ScheduleRequest request = createScheduleRequest();

		// Insert a new Schedule
		EventSchedule eventSchedule = insertScheduleEvent(request);

		eventSchedule.setName("nameUpd1");
		eventSchedule.setDescription("descriptionUpd1");
		eventSchedule.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);

		List<DaysOfWeekEnum> daysOfWeek = new ArrayList<DaysOfWeekEnum>();
		daysOfWeek.add(DaysOfWeekEnum.WEDNESDAY);
		daysOfWeek.add(DaysOfWeekEnum.THURSDAY);

		Event eventOne = createEvent(daysOfWeek, ARBITRARY_INTENSITY_10);
		Event eventTwo = createEvent(daysOfWeek, ARBITRARY_INTENSITY_0);

		List<Event> events = new ArrayList<Event>();
		events.add(eventOne);
		events.add(eventTwo);

		eventSchedule.setEvents(events);
		request.setSchedule(eventSchedule);

		InternalResponse response = getScheduleDAC().updateSchedule(request);
		assertResponse(response);
	}

	/**
	 * Test update schedule offset.
	 */
	@Test
	public void testUpdateScheduleOffset()
	{
		ScheduleRequest request = createScheduleRequest();

		// Insert Schedule Offset
		OffsetSchedule offSetSchedule = insertScheduleOffset(request);

		offSetSchedule.setName("nameUpd");
		offSetSchedule.setDescription("descriptionUpd");
		offSetSchedule.setScheduleTypeEnum(ScheduleTypeEnum.OFFSET);
		offSetSchedule.setSunriseOffsetMinutes(ARBITRARY_SUNRISE_OFF_SET_MINUTES_25);
		offSetSchedule.setSunsetOffsetMinutes(ARBITRARY_SUNSET_OFF_SET_MINUTES_10);
		offSetSchedule.setSunriseBefore(true);
		offSetSchedule.setSunsetBefore(true);
		request.setSchedule(offSetSchedule);

		InternalResponse response = getScheduleDAC().updateSchedule(request);
		assertResponse(response);
	}

	/**
	 * Test delete schedule.
	 */
	@Test
	public void testDeleteSchedule()
	{
		ScheduleRequest request = createScheduleRequest();
		Schedule offsetSchedule = insertScheduleOffset(request);

		InternalResponse response = getScheduleDAC().deleteSchedule(offsetSchedule);
		assertResponse(response);
	}

	/**
	 * Test fetch all schedules.
	 */
	@Test
	public void testFetchAllSchedules()
	{
		insertScheduleEvent(createScheduleRequest());

		SortExpression sortExpression = new SortExpression();
		sortExpression.setField("name");
		sortExpression.setDirection(SortExpression.Direction.Ascending);

		InquiryScheduleRequest inquiryScheduleRequest = createInquiryScheduleRequest();

		inquiryScheduleRequest.addSortExpressions(sortExpression);
		inquiryScheduleRequest.setStartRow(0);
		inquiryScheduleRequest.setPageSize(ARBITRARY_PAGE_SIZE_10);
		inquiryScheduleRequest.setPaginationAllSelected(true);
		inquiryScheduleRequest.setSelectionPaginationIds(null);
		inquiryScheduleRequest.setScheduleTypeEnum(ScheduleTypeEnum.EVENT);

		InternalResultsResponse<Schedule> response = getScheduleDAC().fetchAllSchedules(inquiryScheduleRequest);
		assertResultResponse(response);

		// SelectionPaginationIds
		inquiryScheduleRequest.setScheduleTypeEnum(null);
		List<Integer> listSelectionPagination = new ArrayList<>();
		listSelectionPagination.add(1);
		inquiryScheduleRequest.setSelectionPaginationIds(listSelectionPagination);
		response = getScheduleDAC().fetchAllSchedules(inquiryScheduleRequest);
		assertResultResponse(response);
	}

	/**
	 * Test fetch schedule by id.
	 */
	@Test
	public void testFetchScheduleById()
	{
		EventSchedule eventSchedule = createEventSchedule();
		eventSchedule.setId(1);
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(eventSchedule);
		InternalResultsResponse<Schedule> response = getScheduleDAC().fetchScheduleById(request.getSchedule());

		assertResultResponse(response);
		assertEquals(1, response.getResultsList().size());
		assertNotNull(response.getFirstResult().getName());
	}

	/**
	 * Test delete light from schedule.
	 */
	@Test
	public void testDeleteLightFromSchedule()
	{
		ScheduleRequest request = createScheduleRequest();
		OffsetSchedule schedule = insertScheduleOffset(request);
		request.setSchedule(schedule);

		InternalResponse response = getScheduleDAC().deleteLightFromSchedule(request);
		assertResponse(response);

		request = createScheduleRequest();
		EventSchedule eventSchedule = insertScheduleEvent(request, Arrays.asList(getLightDefault()));
		request.setSchedule(eventSchedule);

		response = getScheduleDAC().deleteLightFromSchedule(request);
		assertResponse(response);
	}

	/**
	 * Test can update.
	 */
	@Test
	public void testCanUpdate()
	{
		ScheduleRequest request = createScheduleRequest();
		List<MessageInfo> list = new ArrayList<MessageInfo>();
		assertTrue(getScheduleDAC().fetchCanUpdate(insertScheduleOffset(request), list, TENANT));

		request = createScheduleRequest();
		list = new ArrayList<MessageInfo>();
		OffsetSchedule schedule = insertScheduleOffset(request);
		schedule.setName("NameNotEqual");
		assertTrue(getScheduleDAC().fetchCanUpdate(schedule, list, TENANT));

		schedule.setId(ARBITRARY_OFF_SET_SCHEDULE_ID);
		assertFalse(getScheduleDAC().fetchCanUpdate(schedule, list, TENANT));
	}

	/**
	 * Test fetch light by schedule.
	 */
	@Test
	public void testFetchLightBySchedule()
	{
		ScheduleRequest request = createScheduleRequest();
		EventSchedule eventSchedule = insertScheduleEvent(request, Arrays.asList(getLightDefault()));
		request.setSchedule(eventSchedule);
		request.setSelectionPaginationIds(Arrays.asList(getLightDefault().getId()));
		InternalResponse internalResult = getScheduleDAC().applyLightToSchedule(request);
		assertResponse(internalResult);
		InternalResultsResponse<Light> response = getScheduleDAC().fetchLightBySchedule(request);
		assertResultResponse(response);
	}

	/**
	 * Test fetch can delete.
	 */
	@Test
	public void testFetchCanDelete()
	{
		ScheduleRequest request = createScheduleRequest();
		assertTrue(getScheduleDAC().fetchCanDelete(insertScheduleOffset(request)));
		assertTrue(getScheduleDAC().fetchCanDelete(insertScheduleEvent(request)));
	}

	/**
	 * Test fetch can insert.
	 */
	@Test
	public void testFetchCanInsert()
	{
		testCanUpdate();

		ScheduleRequest request = createScheduleRequest();
		List<MessageInfo> list = new ArrayList<MessageInfo>();
		assertFalse(getScheduleDAC().fetchCanInsert(insertScheduleOffset(request), list, TENANT));
		assertFalse(getScheduleDAC().fetchCanInsert(insertScheduleEvent(request), list, TENANT));
	}
}