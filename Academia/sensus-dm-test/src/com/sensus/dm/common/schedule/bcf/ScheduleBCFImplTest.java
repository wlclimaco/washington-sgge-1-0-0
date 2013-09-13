package com.sensus.dm.common.schedule.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.schedule.bcl.IScheduleBCL;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.InquiryScheduleResponse;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResetEventAction;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.SendHanTextMessageAction;

/**
 * The Class ScheduleBCFImplTest.
 * 
 * @author QAT Global
 * 
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/schedule/schedulebcfimpltest.xml"})
public class ScheduleBCFImplTest extends AbstractTestBaseBusiness
{
	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant SHOULD_BE_TRUE. */
	private static final String SHOULD_BE_TRUE = "should be true ";

	/** The Constant SHOULD_BRING_ONE_SCHEDULE. */
	private static final String SHOULD_BRING_ONE_SCHEDULE = "should bring one schedule";

	/** The Constant LETTER_D. */
	private static final String LETTER_D = "d";

	/** The Constant LETTER_N. */
	private static final String LETTER_N = "n";

	/** The Constant NAME_SCHEDULE. */
	private static final String NAME_SCHEDULE = "schedule name";

	/** The Constant FETCH_ALL_SCHEDULE. */
	private static final String FETCH_ALL_SCHEDULE = "fetchAllSchedule";

	/** The Constant FETCH_SCHEDULE. */
	private static final String FETCH_SCHEDULE = "fetchSchedule";

	/** The Constant FETCH_SCHEDULE_BY_GROUP. */
	private static final String FETCH_SCHEDULE_BY_GROUP = "fetchScheduleByGroup";

	/** The Constant FETCH_SCHEDULE_BY_DEVICE. */
	private static final String FETCH_SCHEDULE_BY_DEVICE = "fetchScheduleByDevice";

	/** The Constant INSERT_SCHEDULE. */
	private static final String INSERT_SCHEDULE = "insertSchedule";

	/** The Constant UPDATE_SCHEDULE. */
	private static final String UPDATE_SCHEDULE = "updateSchedule";

	/** The Constant UPDATE_SCHEDULE_STATUS. */
	private static final String UPDATE_SCHEDULE_STATUS = "updateScheduleStatus";

	/** The Constant DELETE_SCHEDULE. */
	private static final String DELETE_SCHEDULE = "deleteSchedule";

	/** The Constant GENERATE_FILE_CSV. */
	private static final String GENERATE_FILE_CSV = "generateFileCSV";

	/** The Constant GENERATE_FILE_CSV_SCHEDULE_DEVICE. */
	private static final String GENERATE_FILE_CSV_SCHEDULE_DEVICE = "generateFileCSVScheduleDevice";

	/** The Constant ALL. */
	private static final String ALL = "All";

	/** The Constant OTA_METER_FLEXNET_ID. */
	private static final BigInteger OTA_METER_FLEXNET_ID = new BigInteger("46549474");

	/** The Constant OTA_METER_DEVICE_ID. */
	private static final String OTA_METER_DEVICE_ID = "1N6028900474";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG = "sensus.epm.schedulebcfimpl.defaultexception";

	/** The Constant SCHEDULE_REQUIRED. */
	private static final String SCHEDULE_REQUIRED = "sensus.epm.schedulevalidator.required";

	/** The Constant SCHEDULE_ID_REQUIRED. */
	private static final String SCHEDULE_ID_REQUIRED = "sensus.epm.schedulevalidator.id.required";

	/** The Constant NAME_REQUIRED. */
	private static final String SCHEDULE_NAME_REQUIRED = "sensus.epm.schedulevalidator.name.required";

	/** The Constant STATUS_REQUIRED. */
	private static final String STATUS_REQUIRED = "sensus.epm.schedulevalidator.status.required";

	/** The Constant DESCRIPTION_INVALID. */
	private static final String DESCRIPTION_INVALID = "sensus.epm.schedulevalidator.description.invalid";

	/** The Constant NAME_INVALID. */
	private static final String SCHEDULE_NAME_INVALID = "sensus.epm.schedulevalidator.name.invalid";

	/** The Constant TYPE_REQUIRED. */
	private static final String FREQUENCY_TYPE_REQUIRED = "sensus.epm.frequencyvalidator.frequencyType.required";

	/** The Constant REQUIRED. */
	private static final String FREQUENCY_REQUIRED = "sensus.epm.frequencyvalidator.required";

	/** The Constant STARTS_ON_DATE_REQUIRED. */
	private static final String STARTS_ON_DATE_REQUIRED = "sensus.epm.frequencyvalidator.startsOnDate.required";

	/** The Constant REPEATS_EVERY_REQUIRED. */
	private static final String REPEATS_EVERY_REQUIRED = "sensus.epm.frequencyvalidator.repeatsEvery.required";

	/** The Constant REPEAT_BY_INVALID. */
	private static final String REPEAT_BY_INVALID = "sensus.epm.frequencyvalidator.repeatBy.invalid";

	/** The Constant REPEATS_ON. */
	private static final String REPEATS_ON = "sensus.epm.frequencyvalidator.repeatsOn.invalid";

	/** The Constant ENDS_INVALID. */
	private static final String ENDS_INVALID = "sensus.epm.frequencyvalidator.ends.invalid";

	/** The Constant STARTDATETIME_REQUIRED. */
	private static final String STARTDATETIME_REQUIRED =
			"sensus.epm.rangedatevalidator.start.date.time.required";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_GROUP_REQUIRED. */
	private static final String EPM_GROUP_REQUIRED = "sensus.dm.common.groupvalidator.group.required";

	/** The Constant SENSUS_EPM_GROUPVALIDATOR_ID_REQUIRED. */
	private static final String EPM_GROUP_ID_REQUIRED = "sensus.dm.common.groupvalidator.id.required";

	/** The Constant EPM_ACTION_CRITICALITY_LEVEL_RIQUIRED. */
	private static final String EPM_ACTION_CRITICALITY_LEVEL_RIQUIRED =
			"sensus.epm.actionvalidator.criticality.level.required";

	/** The Constant EPM_ACTION_DURATION_INVALID. */
	private static final String EPM_ACTION_DURATION_INVALID = "sensus.epm.actionvalidator.duration.invalid";

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

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
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	// Test Settings:

	/**
	 * Sets the schedule area.
	 */
	public void setScheduleArea()
	{
		setArea(getScheduleBCF(), EPMAreaEnum.SCHEDULE);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setScheduleArea();
	}

	/**
	 * Removes the schedule area.
	 */
	@After
	public void resetMocks()
	{
		resetMocksData(getScheduleBCF());
		setScheduleArea();
	}

	/**
	 * Run common tests inquiry.
	 * 
	 * @return the inquiry schedule request
	 */
	@Test
	public void testFetchAllScheduled()
	{
		// Validation Situation - user context required
		InquiryScheduleRequest request = new InquiryScheduleRequest();
		InquiryScheduleResponse response = getScheduleBCF().fetchAllSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, TENANT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - sort expression required
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setTenant(DM_TENANT);
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getScheduleBCF().fetchAllSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ORDERBY_REQUIRED);

		// Success Situation
		request.getSortExpressions().add(new SortExpression(PropertyEnum.FLEXNET_ID.getValue(), Direction.Ascending));
		request.setStartRow(ZERO);
		request.setPageSize(TWENTY_FIVE);
		response = getScheduleBCF().fetchAllSchedule(request);
		assertEquals(true, response.isOperationSuccess());
		assertEquals(FIVE, response.getSchedules().size());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				FETCH_ALL_SCHEDULE);

		response = getScheduleBCF().fetchAllSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				FETCH_ALL_SCHEDULE);

		response = getScheduleBCF().fetchAllSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();

	}

	/**
	 * Tests fetching a schedule entity by its id.
	 */
	@Test
	public void testFetchScheduledById()
	{
		// Validation Situation - user context required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().fetchScheduleById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - schedule required
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getScheduleBCF().fetchScheduleById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - schedule id required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().fetchScheduleById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_ID_REQUIRED);

		// Success Situation
		request.getSchedule().setId(ONE);
		response = getScheduleBCF().fetchScheduleById(request);
		assertNotNull(response);
		assertNotNull(response.getSchedules());
		assertEquals(SHOULD_BRING_ONE_SCHEDULE, ONE, response.getSchedules().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				FETCH_SCHEDULE);

		response = getScheduleBCF().fetchScheduleById(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				FETCH_SCHEDULE);

		response = getScheduleBCF().fetchScheduleById(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();

	}

	/**
	 * Test fetch schedule by name.
	 */
	@Test
	public void testFetchScheduleByName()
	{
		// Validation Situation - user context required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().fetchScheduleByName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - schedule required
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getScheduleBCF().fetchScheduleByName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - schedule name required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().fetchScheduleByName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_NAME_REQUIRED);

		// Success Situation
		request.getSchedule().setName(NAME_SCHEDULE);
		response = getScheduleBCF().fetchScheduleByName(request);
		assertNotNull(response);
		assertNotNull(response.getSchedules());
		assertEquals(SHOULD_BRING_ONE_SCHEDULE, ONE, response.getSchedules().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				FETCH_SCHEDULE);

		response = getScheduleBCF().fetchScheduleByName(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				FETCH_SCHEDULE);

		response = getScheduleBCF().fetchScheduleByName(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Test fetch schedule by group.
	 */
	@Test
	public void fetchScheduleByGroup()
	{
		// Validation Situation - user context required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().fetchScheduleByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user schedule required
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getScheduleBCF().fetchScheduleByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - action required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().fetchScheduleByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_ACTION_REQUIRED);

		// Validation Situation - group required
		request.getSchedule().setDmAction(new DMAction());
		response = getScheduleBCF().fetchScheduleByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_GROUP_REQUIRED);

		// Validation Situation - group id required
		request.getSchedule().getDmAction().addGroup(new Group());
		response = getScheduleBCF().fetchScheduleByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_GROUP_ID_REQUIRED);

		// Success Situation
		request.getSchedule().getDmAction().getFirstGroup().setId(ONE);
		response = getScheduleBCF().fetchScheduleByGroup(request);
		assertNotNull(response);
		assertNotNull(response.getSchedules());
		assertEquals(SHOULD_BRING_ONE_SCHEDULE, ONE, response.getSchedules().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				FETCH_SCHEDULE_BY_GROUP);

		response = getScheduleBCF().fetchScheduleByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				FETCH_SCHEDULE_BY_GROUP);

		response = getScheduleBCF().fetchScheduleByGroup(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Test fetch schedule by device.
	 */
	@Test
	public void testFetchScheduleByDevice()
	{
		// Validation Situation - user context required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().fetchScheduleByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getScheduleBCF().fetchScheduleByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - action required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().fetchScheduleByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_ACTION_REQUIRED);

		// Validation Situation - device required
		request.getSchedule().setDmAction(new DMAction());
		response = getScheduleBCF().fetchScheduleByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEVICE_REQUIRED);

		// Validation Situation - device id required
		Device deviceObject = new Device(new Radio());
		request.getSchedule().getDmAction().addDevice(deviceObject);

		response = getScheduleBCF().fetchScheduleByDevice(request);

		assertFalse(response.isOperationSuccess());
		assertMessages(response, FLEXNET_ID_REQUIRED);

		// Success Situation
		request.getSchedule().getDmAction().getFirstDevice().getRadio().setFlexNetId(ELECTRIC_FLEXNET_ID);
		response = getScheduleBCF().fetchScheduleByDevice(request);
		assertNotNull(response);
		assertNotNull(response.getSchedules());
		assertEquals(SHOULD_BRING_ONE_SCHEDULE, ONE, response.getSchedules().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				FETCH_SCHEDULE_BY_DEVICE);

		response = getScheduleBCF().fetchScheduleByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				FETCH_SCHEDULE_BY_DEVICE);

		response = getScheduleBCF().fetchScheduleByDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Test fetch schedule by action.
	 */
	@Test
	public void testFetchScheduleByAction()
	{
		// Validation Situation - user context required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().fetchScheduleByAction(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getScheduleBCF().fetchScheduleByAction(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - action required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().fetchScheduleByAction(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_ACTION_REQUIRED);

		// Validation Situation - action id required
		request.getSchedule().setDmAction(new DMAction());
		response = getScheduleBCF().fetchScheduleByAction(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_ACTION_ID_REQUIRED);

		// Success Situation
		request.getSchedule().getDmAction().setId(ONE);
		response = getScheduleBCF().fetchScheduleByAction(request);
		assertNotNull(response);
		assertNotNull(response.getSchedules());
		assertEquals(SHOULD_BRING_ONE_SCHEDULE, ONE, response.getSchedules().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				FETCH_SCHEDULE);

		response = getScheduleBCF().fetchScheduleByAction(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				FETCH_SCHEDULE);

		response = getScheduleBCF().fetchScheduleByAction(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Test insert schedule.
	 */
	@Test
	public void testInsertSchedule()
	{
		// Validation Situation - schedule required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - schedule name and status required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_NAME_REQUIRED, STATUS_REQUIRED);

		// Validation Situation - schedule name invalid
		String name = StringUtils.repeat(LETTER_N, TWO_HUNDRED_ONE);
		request.setSchedule(new DMSchedule(name, ScheduleStatusEnum.ENABLED));
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_NAME_INVALID);

		// Validation Situation - schedule description invalid
		name = StringUtils.repeat(LETTER_N, TWO_HUNDRED);
		String desc = StringUtils.repeat(LETTER_D, TWO_HUNDRED_ONE);
		request.setSchedule(new DMSchedule(name, ScheduleStatusEnum.ENABLED));
		request.getSchedule().setDescription(desc);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DESCRIPTION_INVALID);

		// validation Frequency
		validateFrequency(request);

		// validation Action
		validateAction(request);

		// Validation Situation - start date required
		request.getSchedule()
				.setDmAction(new DemandResetEventAction(true, true, new Date()));
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, STARTDATETIME_REQUIRED);

		// Validation Situation - range date invalid
		Calendar calendar = Calendar.getInstance();
		calendar.set(TWO_THOUSAND_THIRTEEN, ONE, ONE);
		request.getSchedule().getDmAction().setActionTime(calendar.getTime());
		calendar.set(TWO_THOUSAND_THIRTEEN, TWO, ONE);
		request.getSchedule().getFrequency().setEndDate(calendar.getTime());
		request.getSchedule().setStartTime(calendar.getTime());
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, RANGEDATE_INVALID);

		// Validation Situation - user context required
		// calendar.set(TWO_THOUSAND_THIRTEEN, THREE, ONE);
		calendar.set(TWO_THOUSAND_THIRTEEN, ONE, ONE);
		request.getSchedule().getFrequency().setStartOnDate(null);
		request.getSchedule().getFrequency().setEndDate(calendar.getTime());
		request.getSchedule().setStartTime(calendar.getTime());
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, TENANT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setTenant(DM_TENANT);
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getScheduleBCF().insertSchedule(request);
		assertNotNull(response);
		assertNotNull(response.getSchedules());
		assertEquals(SHOULD_BRING_ONE_SCHEDULE, ONE, response.getSchedules().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				INSERT_SCHEDULE);

		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				INSERT_SCHEDULE);

		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();

		// Verification Situation - used for coverage of validateScheduleDates method
		request.getSchedule().setStartTime(new Date());
		request.getSchedule().getFrequency().setStartOnDate(new Date());
		request.getSchedule().getFrequency().setEndDate(calendar.getTime());
		response = getScheduleBCF().insertSchedule(request);
		assertNotNull(response);
		assertMessages(response, RANGEDATE_INVALID);
		assertFalse(response.isOperationSuccess());

	}

	/**
	 * Test insert schedule demand response.
	 */
	@Test
	public void testInsertScheduleDemandResponse()
	{
		// validate demandResponse
		// Duration Invalid
		ScheduleRequest request = TestBaseUtil.createScheduleRequest();
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);

		DMSchedule schedule = TestBaseUtil.createSchedule(DMConvertUtil.convertDateToDefaultUTC(new Date()),
				ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.getActionTypeName(), FrequencyTypeEnum.DAILY);
		schedule.setId(ONE);
		schedule.getFrequency().setNeverEnds(null);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, +ONE);
		schedule.getFrequency().setEndDate(DMConvertUtil.convertDateToDefaultUTC(calendar.getTime()));

		DemandResponseEventAction demandResponse = (DemandResponseEventAction)schedule.getDmAction();
		demandResponse.setId(ONE);
		demandResponse.setActionType(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT));
		demandResponse.setIsMonitored(Boolean.TRUE);
		demandResponse.setOnDemand(Boolean.TRUE);
		calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -ONE);

		demandResponse.setActionTime(DMConvertUtil.convertDateToDefaultUTC(calendar.getTime()));

		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));
		demandResponse.setEnrollmentCode(1);
		demandResponse.setDemandResponseDuration(SIXTY_TWO);
		demandResponse.setRandomizeStart(Boolean.TRUE);
		demandResponse.setRandomizeEnd(Boolean.FALSE);
		demandResponse.setCooling(FIVE);
		demandResponse.setCriticalityLevel(ONE);
		demandResponse.addDeviceClass(ALL);

		request.setSchedule(schedule);
		ScheduleResponse response = getScheduleBCF().insertSchedule(request);

		assertNotNull(response);
		assertMessages(response, EPM_ACTION_DURATION_INVALID);
		assertFalse(response.isOperationSuccess());

		// Duration Invalid
		calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -ONE);
		calendar.add(Calendar.HOUR, ONE);
		demandResponse.setActionTime(DMConvertUtil.convertDateToDefaultUTC(calendar.getTime()));
		demandResponse.setDemandResponseDuration(ONE_THOUSAND_THREE_HUNDRED_SEVENTY_NINE);

		response = getScheduleBCF().insertSchedule(request);

		assertNotNull(response);
		assertMessages(response, EPM_ACTION_DURATION_INVALID);
		assertFalse(response.isOperationSuccess());

		// Duration valid but startDate invalid
		demandResponse.setDemandResponseDuration(ONE_THOUSAND_FOUR_HUNDRED_FOURTY);
		calendar = new GregorianCalendar();
		calendar.setTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		calendar.add(Calendar.HOUR, TWO);
		schedule.setStartTime(calendar.getTime());

		Calendar cFreq = new GregorianCalendar();
		cFreq.setTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		cFreq.add(Calendar.HOUR, TWO);
		schedule.setFrequency(new Frequency(FrequencyTypeEnum.DAILY, ONE, cFreq.getTime(), false, ZERO, null,
				new ArrayList<Integer>()));

		response = getScheduleBCF().insertSchedule(request);

		assertNotNull(response);
		assertMessages(response, EPM_ACTION_DURATION_INVALID);
		assertFalse(response.isOperationSuccess());

		// success
		request.setTenant(DM_TENANT);
		calendar = new GregorianCalendar();
		calendar.setTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		calendar.add(Calendar.MINUTE, FIFTY);

		schedule.setStartTime(calendar.getTime());

		response = getScheduleBCF().insertSchedule(request);

		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

	}

	/**
	 * Test update schedule.
	 */
	@Test
	public void testUpdateSchedule()
	{
		// Validation Situation - schedule required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - schedule id, name and status required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_ID_REQUIRED, SCHEDULE_NAME_REQUIRED, STATUS_REQUIRED);

		// Validation Situation - schedule name invalid
		String name = StringUtils.repeat(LETTER_N, TWO_HUNDRED_ONE);
		request.setSchedule(new DMSchedule(name, ScheduleStatusEnum.ENABLED));
		request.getSchedule().setId(ONE);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_NAME_INVALID);

		// Validation Situation - schedule description invalid
		name = StringUtils.repeat(LETTER_N, TWO_HUNDRED);
		String desc = StringUtils.repeat(LETTER_D, TWO_HUNDRED_ONE);
		request.setSchedule(new DMSchedule(name, ScheduleStatusEnum.ENABLED));
		request.getSchedule().setId(ONE);
		request.getSchedule().setDescription(desc);
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DESCRIPTION_INVALID);

		// validation Frequency
		validateFrequency(request);

		// Validation Situation - start date required
		request.getSchedule().setFrequency(new Frequency());
		request.getSchedule().getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.NEVER);
		request.getSchedule()
				.setDmAction(
						new DemandResetEventAction(true, true, new Date()));
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, STARTDATETIME_REQUIRED);

		// Validation Situation - range date invalid
		Calendar calendar = Calendar.getInstance();
		calendar.set(TWO_THOUSAND_THIRTEEN, ONE, ONE);
		request.getSchedule().getDmAction().setActionTime(calendar.getTime());
		calendar.set(TWO_THOUSAND_THIRTEEN, TWO, ONE);
		request.getSchedule().getFrequency().setEndDate(calendar.getTime());
		request.getSchedule().setStartTime(calendar.getTime());
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, RANGEDATE_INVALID);

		// Validation Situation - user context required
		// calendar.set(TWO_THOUSAND_THIRTEEN, THREE, ONE);
		calendar.set(TWO_THOUSAND_THIRTEEN, ONE, ONE);
		request.getSchedule().getFrequency().setEndDate(calendar.getTime());
		request.getSchedule().setStartTime(calendar.getTime());
		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED, TENANT_REQUIRED, SERVICE_TYPE_ENUM_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		request.setTenant(DM_TENANT);
		response = getScheduleBCF().updateSchedule(request);
		assertNotNull(response);
		assertNotNull(response.getSchedules());
		assertEquals(SHOULD_BRING_ONE_SCHEDULE, ONE, response.getSchedules().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				UPDATE_SCHEDULE);

		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				UPDATE_SCHEDULE);

		response = getScheduleBCF().updateSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Test update schedule status.
	 */
	@Test
	public void testUpdateScheduleStatus()
	{

		// Validation Situation - user context required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().updateScheduleStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(TestBaseUtil.createUserContext());
		request.setServiceTypeEnum(ServiceTypeEnum.ELECTRIC);
		response = getScheduleBCF().updateScheduleStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - schedule id, name and status required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().updateScheduleStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_ID_REQUIRED, STATUS_REQUIRED);

		// Success Situation
		request.getSchedule().setId(ONE);
		request.getSchedule().setStatusEnum(ScheduleStatusEnum.ENABLED);
		response = getScheduleBCF().updateScheduleStatus(request);
		assertNotNull(response);
		assertNotNull(response.getSchedules());
		assertEquals(SHOULD_BRING_ONE_SCHEDULE, ONE, response.getSchedules().size());
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				UPDATE_SCHEDULE_STATUS);

		response = getScheduleBCF().updateScheduleStatus(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				UPDATE_SCHEDULE_STATUS);

		response = getScheduleBCF().updateScheduleStatus(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Tests deleting a schedule entity by its id.
	 */
	@Test
	public void testDeleteSchedule()
	{
		// Validation Situation - user context required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().deleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, USER_CONTEXT_REQUIRED);

		// Validation Situation - user id required
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getScheduleBCF().deleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - schedule id
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().deleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_ID_REQUIRED);

		// Success Situation
		request.getSchedule().setId(ONE);
		response = getScheduleBCF().deleteSchedule(request);
		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				DELETE_SCHEDULE);

		response = getScheduleBCF().deleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				DELETE_SCHEDULE);

		response = getScheduleBCF().deleteSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Tests the CSV file generation.
	 */
	@Test
	public void testGenerateFileCSV()
	{
		// Validation Situation - file name, process id and user context required
		InquiryScheduleRequest request = new InquiryScheduleRequest();
		request.setTenant(TestBaseUtil.createTenant());
		InquiryScheduleResponse response = getScheduleBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED);

		// Validation Situation - file name invalid, user id and locale required
		String name = StringUtils.repeat(LETTER_N, TWO_HUNDRED_FIFTY_SIX);
		request.setFileName(name);
		request.setProcessId(ONE);
		request.setUserContext(TestBaseUtil.createUserContext());
		response = getScheduleBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, FILE_NAME_INVALID, LOCALE_REQUIRED);

		// Success Situation
		name = StringUtils.repeat(LETTER_N, TWO_HUNDRED_FIFTY_FIVE);
		request.getSortExpressions().add(new SortExpression(PropertyEnum.FLEXNET_ID.getValue(), Direction.Ascending));
		request.setFileName(name);
		request.setPageSize(FIFTEEN);
		request.getUserContext().setLocaleString(USER_CONTEXT_LOCALE);
		response = getScheduleBCF().generateFileCSV(request);
		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				GENERATE_FILE_CSV);

		response = getScheduleBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				GENERATE_FILE_CSV);

		response = getScheduleBCF().generateFileCSV(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Test generate file csv schedule meters.
	 */
	@Test
	public void testGenerateFileCSVScheduleMeters()
	{
		// Validation Situation - schedule required
		ScheduleRequest request = new ScheduleRequest();
		ScheduleResponse response = getScheduleBCF().generateFileCSVScheduleDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_REQUIRED);

		// Validation Situation - schedule id required
		request.setSchedule(new DMSchedule());
		response = getScheduleBCF().generateFileCSVScheduleDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SCHEDULE_ID_REQUIRED);

		// Validation Situation - file name, process id and user context required
		request.getSchedule().setId(ONE);
		request.setTenant(TestBaseUtil.createTenant());
		response = getScheduleBCF().generateFileCSVScheduleDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, PROCESS_ID_REQUIRED, FILE_NAME_REQUIRED, USER_CONTEXT_REQUIRED);

		// Validation Situation - file name invalid, user id and locale required
		String name = StringUtils.repeat(LETTER_N, TWO_HUNDRED_FIFTY_SIX);
		request.setFileName(name);
		request.setProcessId(ONE);
		request.setUserContext(TestBaseUtil.createUserContext());

		response = getScheduleBCF().generateFileCSVScheduleDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, FILE_NAME_INVALID, LOCALE_REQUIRED);

		// Success Situation
		name = StringUtils.repeat(LETTER_N, TWO_HUNDRED_FIFTY_FIVE);
		request.setFileName(name);
		request.getUserContext().setLocaleString(USER_CONTEXT_LOCALE);
		response = getScheduleBCF().generateFileCSVScheduleDevice(request);
		assertNotNull(response);
		assertTrue(SHOULD_BE_TRUE, response.isOperationSuccess());

		// Error situation
		setSituation(getScheduleBCF(), SituationsEnum.ERROR, IScheduleBCL.class,
				GENERATE_FILE_CSV_SCHEDULE_DEVICE);

		response = getScheduleBCF().generateFileCSVScheduleDevice(request);
		assertFalse(response.isOperationSuccess());
		assertEquals(response.getMessageList().get(ZERO).getMessageInfo().getCode(), ERROR_CODE);

		resetMocks();

		// Exception situation
		setSituation(getScheduleBCF(), SituationsEnum.EXCEPTION, IScheduleBCL.class,
				GENERATE_FILE_CSV_SCHEDULE_DEVICE);

		response = getScheduleBCF().generateFileCSVScheduleDevice(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);

		resetMocks();
	}

	/**
	 * Validate frequency.
	 * 
	 * @param request the request
	 */
	private void validateFrequency(ScheduleRequest request)
	{
		// Validation Situation - schedule frequency required
		String desc = StringUtils.repeat(LETTER_D, TWO_HUNDRED);
		request.getSchedule().setDescription(desc);
		ScheduleResponse response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, FREQUENCY_REQUIRED);

		// Validation Situation - schedule frequency type required
		request.getSchedule().setFrequency(new Frequency());
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, FREQUENCY_TYPE_REQUIRED);

		// Validation Situation - frequency type: DAILY/YEARLY - starts on date, repeats every required and ends invalid
		request.getSchedule().getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.DAILY);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, STARTS_ON_DATE_REQUIRED, ENDS_INVALID, REPEATS_EVERY_REQUIRED);

		// Validation Situation - frequency type: EVERY_WEEKDAY/EVERY_MON_WED_FRI/EVERY_TUE_THURS - starts on date
		// required, ends invalid
		request.getSchedule().getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.EVERY_WEEKDAY);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, STARTS_ON_DATE_REQUIRED, ENDS_INVALID);

		// Validation Situation - frequency type: WEEKLY - starts on date required, repeats every required, ends
		// invalid, repeats on invalid
		request.getSchedule().getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.WEEKLY);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, STARTS_ON_DATE_REQUIRED, ENDS_INVALID, REPEATS_EVERY_REQUIRED, REPEATS_ON);

		// Validation Situation - frequency type: MONTHLY - starts on date required, repeats every required, ends
		// invalid, repeats by invalid
		request.getSchedule().getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.MONTHLY);
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, STARTS_ON_DATE_REQUIRED, ENDS_INVALID, REPEATS_EVERY_REQUIRED, REPEAT_BY_INVALID);
	}

	/**
	 * Validate action.
	 * 
	 * @param request the request
	 */
	private void validateAction(ScheduleRequest request)
	{
		// Validation Situation - action required
		request.getSchedule().getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.NEVER);
		ScheduleResponse response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_ACTION_REQUIRED);

		// Validation Situation - action is on demand, action is monitored, action time and action type description
		// required
		request.getSchedule().setDmAction(new DMAction());
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_ACTION_ON_DEMAND_REQUIRED, EPM_ACTION_MONITORED_REQUIRED,
				EPM_ACTION_TIME_REQUIRED, EPM_ACTION_TYPE_DESCRIPTION_REQUIRED);

		// ************** DemandResponseEventAction *************
		// Validation Situation - Action Type: DemandResponseEventAction - action enrollment code, action duration
		// required
		request.getSchedule().setDmAction(
				new DemandResponseEventAction(true, true, new Date()));
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_ACTION_ENROLLMENTCODE_REQUIRED, EPM_ACTION_DURATION_REQUIRED,
				EPM_ACTION_CRITICALITY_LEVEL_RIQUIRED);

		// ************** SendHanTextMessageAction *************
		// Validation Situation - Action Type: SendHanTextMessageAction - action send text and action duration required
		request.getSchedule().setDmAction(
				new SendHanTextMessageAction(true, true, new Date()));
		response = getScheduleBCF().insertSchedule(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, EPM_ACTION_SEND_TEXT_REQUIRED, EPM_ACTION_DURATION_REQUIRED);
	}
}
