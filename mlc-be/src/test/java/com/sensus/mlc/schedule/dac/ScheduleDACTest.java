package com.sensus.mlc.schedule.dac;

import static com.sensus.mlc.base.TestBaseUtil.assertResponse;
import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.mlc.base.TestBaseUtil.createDaysOfWeek;
import static com.sensus.mlc.base.TestBaseUtil.createEvent;
import static com.sensus.mlc.base.TestBaseUtil.createInquiryScheduleRequest;
import static com.sensus.mlc.base.TestBaseUtil.createOffSetSchedule;
import static com.sensus.mlc.base.TestBaseUtil.createSchedule;
import static com.sensus.mlc.base.TestBaseUtil.createScheduleRequest;
import static com.sensus.mlc.base.TestBaseUtil.createUnknownOffsetScheduleRequest;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.schedule.model.DaysOfWeekEnum;
import com.sensus.mlc.schedule.model.Event;
import com.sensus.mlc.schedule.model.EventSchedule;
import com.sensus.mlc.schedule.model.OffsetSchedule;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.ScheduleTypeEnum;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ScheduleDACTest.
 */
public class ScheduleDACTest extends AbstractTestBaseDAC
{
	/** The Constant TENANT. */
	private static final Tenant TENANT = new Tenant(1);

	/** The Constant ARBITRARY_INTENSITY_10. */
	private static final Integer ARBITRARY_INTENSITY_10 = 10;

	/** The Constant ARBITRARY_INTENSITY_20. */
	private static final Integer ARBITRARY_INTENSITY_20 = 20;

	/** The Constant ARBITRARY_INTENSITY_0. */
	private static final Integer ARBITRARY_INTENSITY_0 = 0;

	/** The Constant ARBITRARY_AMOUNT_LIGHT. */
	private static final int ARBITRARY_AMOUNT_LIGHT = 10;

	/** The Constant ARBITRARY_SUNRISE_OFF_SET_MINUTES_5. */
	private static final Integer ARBITRARY_SUNRISE_OFF_SET_MINUTES_25 = 25;

	/** The Constant ARBITRARY_SUNSET_OFF_SET_MINUTES_10. */
	private static final Integer ARBITRARY_SUNSET_OFF_SET_MINUTES_10 = 10;

	/** The Constant ARBITRARY_OFF_SET_SCHEDULE_ID. */
	private static final Integer ARBITRARY_OFF_SET_SCHEDULE_ID = 9999999;

	/** The Constant ARBITRARY_PAGE_SIZE_10. */
	private static final Integer ARBITRARY_PAGE_SIZE_10 = 10;

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
		EventSchedule eventSchedule = createSchedule();
		eventSchedule.setEvents(events);

		// create a new light and add at schedule event
		List<Light> lights = new ArrayList<Light>();
		lights.add(insertLight());
		eventSchedule.setLights(lights);

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
	 * Test apply smart point to schedule.
	 */
	@Test
	public void testApplySmartPointToSchedule()
	{
		ScheduleRequest request = createScheduleRequest();
		OffsetSchedule schedule = insertScheduleOffset(request);
		List<Light> lights = insertLights(ARBITRARY_AMOUNT_LIGHT);

		applySmartPointToSchedule(schedule, lights);
	}

	/**
	 * Test apply unknown offset schedule.
	 */
	@Test
	public void testApplyUnknownOffsetSchedule()
	{
		ScheduleRequest scheduleRequest = createUnknownOffsetScheduleRequest();
		OffsetSchedule offsetSchedule = insertScheduleOffset(scheduleRequest);

		Light light = insertLight();
		offsetSchedule.getLights().add(light);
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
		EventSchedule eventSchedule = insertScheduleEvent(scheduleRequest);
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
		inquiryScheduleRequest.setSortExpressions(null);
		List<Integer> listSelectionPagination = new ArrayList<>();
		listSelectionPagination.add(1);
		inquiryScheduleRequest.setSelectionPaginationIds(listSelectionPagination);
		response = getScheduleDAC().fetchAllSchedules(inquiryScheduleRequest);
		assertResultResponse(response);

		// PaginationAllSelected Null
		inquiryScheduleRequest.setPaginationAllSelected(null);
		response = getScheduleDAC().fetchAllSchedules(inquiryScheduleRequest);
		assertEquals("Should be No Rows Found Error", response.getStatus(), Status.NoRowsFoundError);
	}

	/**
	 * Test fetch schedule by id.
	 */
	@Test
	public void testFetchScheduleById()
	{
		EventSchedule eventSchedule = createSchedule();
		eventSchedule.setId(1);
		ScheduleRequest request = createScheduleRequest();
		request.setSchedule(eventSchedule);
		InternalResultsResponse<Schedule> response = getScheduleDAC().fetchScheduleById(request.getSchedule());

		assertResultResponse(response);
		assertEquals(1, response.getResultsList().size());
		assertNotNull(response.getFirstResult().getName());
	}

	/**
	 * Test delete smart point from schedule.
	 */
	@Test
	public void testDeleteSmartPointFromSchedule()
	{
		ScheduleRequest request = createScheduleRequest();
		OffsetSchedule schedule = insertScheduleOffset(request);
		request.setSchedule(schedule);

		InternalResponse response = getScheduleDAC().deleteSmartpointFromSchedule(request);
		assertResponse(response);

		request = createScheduleRequest();
		EventSchedule eventSchedule = insertScheduleEvent(request);
		request.setSchedule(eventSchedule);

		response = getScheduleDAC().deleteSmartpointFromSchedule(request);
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
	 * Test fetch smartpoint by schedule.
	 */
	@Test
	public void testFetchSmartpointBySchedule()
	{
		ScheduleRequest request = createScheduleRequest();
		EventSchedule eventSchedule = insertScheduleEvent(request);
		request.setSchedule(eventSchedule);
		InternalResponse internalResult = getScheduleDAC().applySmartPointToSchedule(request);
		assertResponse(internalResult);
		InternalResultsResponse<Light> response = getScheduleDAC().fetchSmartpointBySchedule(request);
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