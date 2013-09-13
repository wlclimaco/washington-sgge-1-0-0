package com.sensus.dm.common.schedule.dac;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResetEventAction;

/**
 * The Class ScheduleDACImplTest.
 * 
 * @author QAT Global.
 */

public class ScheduleDACImplTest extends AbstractTestBaseDAC
{
	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant SCHEDULE_TEST. */
	private static final String SCHEDULE_TEST = "Schedule Test Junit";

	/** The Constant SCHEDULE_TEST_99. */
	private static final String SCHEDULE_TEST_99 = "Schedule Test 99";

	/** The Constant START_TIME. */
	private static final String START_TIME = "start_time";

	/** The Constant CLOSE_BRACKETS. */
	private static final String CLOSE_BRACKETS = "]";

	/** The Constant EVENT_NAME_OPEN_BRACKETS. */
	private static final String EVENT_NAME_OPEN_BRACKETS = "Event Name =[";

	/** The Constant ACTION_TYPE_OPEN_BRACKETS. */
	private static final String ACTION_TYPE_OPEN_BRACKETS = "ActionType=[";

	/** The Constant ACTION_CATEGORY_OPEN_BRACKETS. */
	private static final String ACTION_CATEGORY_OPEN_BRACKETS = "ActionCategory =[";

	/** The Constant STATUS_SCHEDULE_OPEN_BRACKETS. */
	private static final String STATUS_SCHEDULE_OPEN_BRACKETS = "Status =[";

	/** The Constant PROPERTY. */
	private static final String PROPERTY = "property = ";

	/** The Constant SCHEDULE_ID. */
	private static final String SCHEDULE_ID = "Schedule_id = ";

	/** The Constant EQUALS. */
	private static final String EQUALS = " = ";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Tests the inserting of a schedule property.
	 */
	@Test
	public void testInsertScheduleProperty()
	{
		// insert schedule
		DMSchedule schedule =
				insertSchedule(
						TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
								ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
								FrequencyTypeEnum.DAILY), false);

		// generate scheduleProperty
		schedule.setProperties(createPropertySchedule(schedule));
		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		// insert schedule property
		InternalResponse response = getScheduleDAC().insertScheduleProperty(request);
		TestBaseUtil.assertResponse(response);

		// fetch schedule by id
		InternalResultsResponse<DMSchedule> resultResponse = getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResponse(resultResponse);

		for (Property property : resultResponse.getFirstResult().getProperties())
		{
			System.out.println(PROPERTY + property.getPropertyName() + EQUALS + property.getPropertyValue());
		}
	}

	/**
	 * Test delete schedule property.
	 */
	@Test
	public void testDeleteScheduleProperty()
	{
		// insert schedule
		DMSchedule schedule =
				insertSchedule(
						TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
								ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
								FrequencyTypeEnum.DAILY), false);

		// generate scheduleProperty
		schedule.setProperties(createPropertySchedule(schedule));
		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		// insert schedule property
		InternalResponse response = getScheduleDAC().insertScheduleProperty(request);
		TestBaseUtil.assertResponse(response);

		for (Property property : schedule.getProperties())
		{
			System.out.println(PROPERTY + property.getPropertyName() + EQUALS + property.getPropertyValue());
		}

		// delete schedule property
		response =
				getScheduleDAC().deleteAllScheduleProperty(
						new ScheduleRequest(schedule, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(response);

		// fetch schedule by id
		InternalResultsResponse<DMSchedule> resultResponse = getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResponse(resultResponse);
		assertEquals(0, resultResponse.getFirstResult().getProperties().size());

	}

	/**
	 * Test insert schedule.
	 */
	@Test
	public void testInsertSchedule()
	{
		DMSchedule schedule = new DMSchedule();
		schedule.setName(SCHEDULE_TEST);
		schedule.setDescription(SCHEDULE_TEST);
		schedule.setCreateDate(new Date());
		schedule.setStartTime(new Date());
		schedule.setStatusEnum(ScheduleStatusEnum.ENABLED);
		// create Action
		DMAction action =
				new DemandResetEventAction(Boolean.FALSE, Boolean.TRUE, new Date());
		schedule.setDmAction(insertAction(action));
		// create Frequency
		Frequency frequency = new Frequency();
		frequency.setFrequencyEnumValue(FrequencyTypeEnum.DAILY.getValue());
		frequency.setStartOnDate(new Date());
		frequency.setNeverEnds(Boolean.TRUE);
		frequency.setNextExecution(new Date());
		frequency.setTimeToRepeat(1);
		schedule.setFrequency(frequency);

		// insert schedule Electric
		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(new DMTenant(CUSTOMER_ID));
		InternalResponse response =
				getScheduleDAC().insertSchedule(request);
		TestBaseUtil.assertResponse(response);

		// fetch schedule by id Electric
		InternalResultsResponse<DMSchedule> resultResponse =
				getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResultResponse(resultResponse);

		DMSchedule sch = resultResponse.getFirstResult();
		System.out.println(SCHEDULE_ID + sch.getId());

		// insert schedule Water
		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		response = getScheduleDAC().insertSchedule(request);
		TestBaseUtil.assertResponse(response);

		// fetch schedule by id Water
		resultResponse =
				getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResultResponse(resultResponse);

		sch = resultResponse.getFirstResult();
		System.out.println(SCHEDULE_ID + sch.getId());
	}

	/**
	 * Test fetch schedule by group.
	 */
	@Test
	public void testFetchScheduleByGroup()
	{
		// create schedule
		DMSchedule schedule =
				TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
						ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.DAILY);
		DMAction action = new DemandResetEventAction();

		// insert action with group
		schedule.setDmAction(insertActionWithGroup(action));

		// insert schedule
		schedule = insertSchedule(schedule, Boolean.FALSE);

		// fetch schedule by group
		InternalResultsResponse<DMSchedule> response =
				getScheduleDAC().fetchScheduleByGroup(new ScheduleRequest(schedule, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResultResponse(response);

		System.out.println(SCHEDULE_ID + response.getFirstResult().getId());
	}

	/**
	 * Test fetch schedule by device.
	 */
	@Test
	public void testFetchScheduleByDevice()
	{
		// create schedule
		DMSchedule schedule =
				TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
						ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.DAILY);
		DMAction action = new DemandResetEventAction();

		// insert action with Device
		schedule.setDmAction(insertActionWithDevice(action));

		// insert schedule
		schedule = insertSchedule(schedule, Boolean.FALSE);

		// fetch schedule by Device
		InternalResultsResponse<DMSchedule> response =
				getScheduleDAC().fetchScheduleByDevice(new ScheduleRequest(schedule, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResultResponse(response);

		System.out.println(SCHEDULE_ID + response.getFirstResult().getId());

	}

	/**
	 * Tests fetching all schedule entities.
	 */
	@Test
	public void testFetchAllSchedule()
	{
		// insert schedule
		insertSchedule(
				TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
						ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.DAILY),
				Boolean.FALSE, ServiceTypeEnum.ELECTRIC);

		insertSchedule(
				TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
						ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.DAILY),
				Boolean.FALSE, ServiceTypeEnum.WATER);

		InquiryScheduleRequest request = new InquiryScheduleRequest();
		request.setBaseSearch(new BaseSearch());
		request.getBaseSearch().setStartDate(new Date());
		request.getBaseSearch().setEndDate(new Date());

		SortExpression sortExpression = new SortExpression(START_TIME, Direction.Descending);

		request.addSortExpressions(sortExpression);

		request.setPageSize(TWENTY_FIVE);

		List<ScheduleStatusEnum> status = new ArrayList<ScheduleStatusEnum>();
		status.add(ScheduleStatusEnum.ENABLED);
		status.add(ScheduleStatusEnum.PAUSED);

		request.setScheduleStatusEnums(status);
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(new DMTenant("ACME"));
		InternalResultsResponse<DMSchedule> response = getScheduleDAC().fetchAllSchedule(request);
		TestBaseUtil.assertResultResponse(response);

		// request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		// response = getScheduleDAC().fetchAllSchedule(request);
		// TestBaseUtil.assertResultResponse(response);

		System.out
				.println("-------------------------FETCH ALL SCHEDULES-------------------------------------------");
		for (DMSchedule sch : response.getResultsList())
		{

			System.out.println("Process Id with value for today = " + sch.getDmAction().getProcessId());
			System.out.println(EVENT_NAME_OPEN_BRACKETS
					+ sch.getName()
					+ CLOSE_BRACKETS
					+ ACTION_TYPE_OPEN_BRACKETS
					+ sch.getDmAction().getActionType().getActionTypeEnum().getActionTypeName()
					+ CLOSE_BRACKETS
					+ ACTION_CATEGORY_OPEN_BRACKETS
					+ sch.getDmAction().getActionType().getActionTypeEnum().getActionCategoryEnum()
							.getActionTypeCategoryName() + CLOSE_BRACKETS);

		}
		System.out.println("-----------------------------FETCH ALL SCHEDULES FIM----------------------------------");

	}

	/**
	 * Test fetch schedulers to execute.
	 */
	@Test
	public void testFetchSchedulersToExecute()
	{
		// create schedule
		DMSchedule schedule =
				TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
						ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.DAILY);

		// insert action with Device
		DMAction action = new DemandResetEventAction();
		schedule.setDmAction(insertActionWithDevice(action));

		// insert schedule
		insertSchedule(schedule, Boolean.FALSE, ServiceTypeEnum.ELECTRIC);
		insertSchedule(schedule, Boolean.FALSE, ServiceTypeEnum.WATER);

		ScheduleRequest scheduleRequest = new ScheduleRequest();

		// get end date and time (applied time zone hours difference)
		Calendar cEndDate = Calendar.getInstance();
		cEndDate.add(Calendar.DATE, +1);
		scheduleRequest.setEndDate(cEndDate.getTime());

		// Get start date and time (applied time zone hours difference)
		Calendar cStartDate = Calendar.getInstance();
		cStartDate.add(Calendar.DATE, -1);
		scheduleRequest.setStartDate(cStartDate.getTime());

		scheduleRequest.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		InternalResultsResponse<DMSchedule> internalResultsResponse =
				getScheduleDAC().fetchSchedulesToExecute(scheduleRequest);
		TestBaseUtil.assertResultResponse(internalResultsResponse);

		scheduleRequest.setServiceTypeEnum(ServiceTypeEnum.WATER);
		internalResultsResponse = getScheduleDAC().fetchSchedulesToExecute(scheduleRequest);
		TestBaseUtil.assertResultResponse(internalResultsResponse);

		if (!ValidationUtil.isNullOrEmpty(internalResultsResponse.getResultsList()))
		{
			for (DMSchedule s : internalResultsResponse.getResultsList())
			{
				System.out.println("-----------------------Schedule to Execute------------------------------");

				System.out.println(EVENT_NAME_OPEN_BRACKETS + s.getName() + CLOSE_BRACKETS);
				System.out
						.println(ACTION_TYPE_OPEN_BRACKETS
								+ s.getDmAction().getActionType().getActionTypeEnum().getActionTypeName()
								+ CLOSE_BRACKETS);
				System.out
						.println(ACTION_CATEGORY_OPEN_BRACKETS
								+ s.getDmAction().getActionType().getActionTypeEnum().getActionCategoryEnum()
										.getActionTypeCategoryName()
								+ CLOSE_BRACKETS);
				System.out.println("-----------------------End of Schedule to Execute------------------------------");
			}
		}
	}

	/**
	 * Tests fetching a schedule entity by its id.
	 */
	@Test
	public void testFetchSchedule()
	{
		// first insert schedule
		DMSchedule schedule =
				insertSchedule(
						TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
								ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
								FrequencyTypeEnum.DAILY), false);

		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		// fetch schedule by id, name and action
		InternalResultsResponse<DMSchedule> resultResponse =
				getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResultResponse(resultResponse);

		System.out.println("-----------------------------SCHEDULE------------------------------------------");

		if (resultResponse.getFirstResult() != null)
		{
			System.out.println(EVENT_NAME_OPEN_BRACKETS + resultResponse.getFirstResult().getName() + CLOSE_BRACKETS);
			System.out
					.println(ACTION_TYPE_OPEN_BRACKETS
							+ resultResponse.getFirstResult().getDmAction().getActionType().getActionTypeEnum()
							+ CLOSE_BRACKETS);
			System.out
					.println(ACTION_CATEGORY_OPEN_BRACKETS
							+ resultResponse.getFirstResult().getDmAction().getActionType().getActionTypeEnum()
									.getActionCategoryEnum().getActionTypeCategoryName()
							+ CLOSE_BRACKETS);

			System.out.println(resultResponse.getFirstResult().getStartTime());

			System.out.println("--------------------------------------------------------------------");

		}

		// fetch schedule by name
		request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		resultResponse =
				getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResultResponse(resultResponse);

		System.out.println("-----------------------SCHEDULE BY NAME------------------------------------");
		System.out.println(EVENT_NAME_OPEN_BRACKETS + resultResponse.getFirstResult().getName() + CLOSE_BRACKETS);
		System.out.println("----------------------------------------------------------------------------");

	}

	/**
	 * Tests the deletion on a schedule entity.
	 */
	@Test
	public void testDeleteScheduled()
	{
		// first insert schedule
		DMSchedule schedule =
				insertSchedule(
						TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
								ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
								FrequencyTypeEnum.DAILY), false);

		// delete schedule
		InternalResponse response = getScheduleDAC().deleteSchedule(new ScheduleRequest(schedule));
		TestBaseUtil.assertResponse(response);

		// fetch schedule by id
		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		InternalResultsResponse<DMSchedule> resultResponse =
				getScheduleDAC().fetchSchedule(request);
		assertEquals(Status.NoRowsFoundError, resultResponse.getStatus());

	}

	/**
	 * Tests whether a schedule can be inserted.
	 */
	@Test
	public void testCanScheduleBeInserted()
	{
		// first create schedule
		DMSchedule schedule =
				TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
						ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.DAILY);

		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createTenant());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		// can schedule be insert
		InternalResultsResponse<Boolean> canResponse =
				getScheduleDAC().canScheduleBeInserted(request);
		TestBaseUtil.assertResultResponse(canResponse);

		schedule =
				TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
						ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.DAILY);

		request = new ScheduleRequest(schedule, TestBaseUtil.createTenant());
		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		// can schedule be insert
		canResponse = getScheduleDAC().canScheduleBeInserted(request);
		TestBaseUtil.assertResultResponse(canResponse);

		// // insert schedule
		// schedule = insertSchedule(schedule, Boolean.FALSE);
		//
		// schedule.setId(null);
		// // can schedule be insert
		// canResponse = getScheduleDAC().canScheduleBeInserted(new ScheduleRequest(schedule));
		// assertEquals(Status.ExceptionError, canResponse.getStatus());

	}

	/**
	 * Tests whether a schedule can be updated.
	 */
	@Test
	public void testCanScheduleBeUpdated()
	{
		// first create schedule
		DMSchedule schedule = insertSchedule(TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
				ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
				FrequencyTypeEnum.DAILY), Boolean.FALSE);

		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createTenant());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		// can schedule be insert
		InternalResultsResponse<Boolean> canResponse =
				getScheduleDAC().canScheduleBeUpdated(request);
		// assertEquals(Status.ExceptionError, canResponse.getStatus());
		//
		// // insert schedule
		// schedule.setName(SCHEDULE_TEST + SCHEDULE_TEST_99);
		//
		// // can schedule be insert
		// canResponse = getScheduleDAC().canScheduleBeUpdated(new ScheduleRequest(schedule));
		TestBaseUtil.assertResultResponse(canResponse);

		schedule = TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
				ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
				FrequencyTypeEnum.DAILY);

		request = new ScheduleRequest(schedule, TestBaseUtil.createTenant());

		request.setServiceTypeEnum(ServiceTypeEnum.WATER);
		canResponse = getScheduleDAC().canScheduleBeUpdated(request);
		TestBaseUtil.assertResultResponse(canResponse);
	}

	/**
	 * Test can schedule be deleted.
	 */
	@Test
	public void testCanScheduleBeDeleted()
	{
		// first create schedule
		DMSchedule schedule =
				insertSchedule(
						TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
								ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
								FrequencyTypeEnum.DAILY), Boolean.FALSE);

		// can schedule be insert
		InternalResultsResponse<Boolean> canResponse =
				getScheduleDAC().canScheduleBeDeleted(new ScheduleRequest(schedule));
		TestBaseUtil.assertResultResponse(canResponse);
	}

	/**
	 * Tests updating a schedule entity.
	 */
	@Test
	public void testUpdateSchedule()
	{
		// first create schedule
		DMSchedule schedule =
				insertSchedule(
						TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
								ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
								FrequencyTypeEnum.DAILY), Boolean.FALSE);

		System.out.println("---------------------------UPDATE SCHEDULE-----------------------------------");
		System.out.println(EVENT_NAME_OPEN_BRACKETS + schedule.getName() + CLOSE_BRACKETS);
		System.out.println("-----------------------------------------------------------------------------");

		// update schedule
		schedule.setName(SCHEDULE_TEST + SCHEDULE_TEST_99);
		InternalResponse response =
				getScheduleDAC().updateSchedule(new ScheduleRequest(schedule, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(response);

		// fetch schedule by id
		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		InternalResultsResponse<DMSchedule> resultResponse =
				getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResultResponse(resultResponse);

		System.out.println("-------------------- UPDATE SCHEDULE ------------------------------------");
		System.out.println(EVENT_NAME_OPEN_BRACKETS + resultResponse.getFirstResult().getName() + CLOSE_BRACKETS);
		System.out.println("-------------------------------------------------------------");

	}

	/**
	 * Test update schedule status.
	 */
	@Test
	public void testUpdateScheduleStatus()
	{
		// first create schedule
		DMSchedule schedule =
				insertSchedule(
						TestBaseUtil.createSchedule(Calendar.getInstance().getTime(),
								ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
								FrequencyTypeEnum.DAILY), Boolean.FALSE);

		System.out.println("---------------------------SCHEDULE STATUS--------------------------------------");
		System.out.println(EVENT_NAME_OPEN_BRACKETS + schedule.getName() + CLOSE_BRACKETS);
		System.out.println(STATUS_SCHEDULE_OPEN_BRACKETS + schedule.getStatusEnum().getValue() + CLOSE_BRACKETS);
		System.out.println("-------------------------SCHEDULE STATUS FIM------------------------------------");

		// update schedule status
		schedule.setStatusEnum(ScheduleStatusEnum.PAUSED);
		InternalResponse response =
				getScheduleDAC().updateSchedule(new ScheduleRequest(schedule, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(response);

		// fetch schedule by id
		ScheduleRequest request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		InternalResultsResponse<DMSchedule> resultResponse =
				getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResultResponse(resultResponse);

		// update schedule status - active - run now
		schedule.setStatusEnum(ScheduleStatusEnum.ENABLED);
		schedule.setDmAction(new DMAction(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT)));
		schedule.getDmAction().setActionTime(new Date());

		response =
				getScheduleDAC().updateSchedule(new ScheduleRequest(schedule, TestBaseUtil.createUserContext()));
		TestBaseUtil.assertResponse(response);

		// fetch schedule by id
		request = new ScheduleRequest(schedule, TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		resultResponse = getScheduleDAC().fetchSchedule(request);
		TestBaseUtil.assertResultResponse(resultResponse);

		System.out.println("---------------------------SCHEDULE-----------------------------------------");
		System.out.println(EVENT_NAME_OPEN_BRACKETS + resultResponse.getFirstResult().getName() + CLOSE_BRACKETS);
		System.out.println(STATUS_SCHEDULE_OPEN_BRACKETS + resultResponse.getFirstResult().getStatusEnum().getValue()
				+ CLOSE_BRACKETS);
		System.out.println("-------------------------------------------------------------------------");
	}

}
