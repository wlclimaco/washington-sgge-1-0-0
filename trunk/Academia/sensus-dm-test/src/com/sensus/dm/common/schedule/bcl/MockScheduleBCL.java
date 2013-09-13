package com.sensus.dm.common.schedule.bcl;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Class MockScheduleBCL.
 * 
 * @author QAT Global.
 */
public class MockScheduleBCL extends AbstractMockBase implements IScheduleBCL
{

	/** The Constant FIVE. */
	private static final int FIVE = 5;

	/** The Constant FOUR. */
	private static final int FOUR = 4;

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant ALL. */
	private static final String ALL = "All";

	/** The Constant OTA_METER_FLEXNET_ID. */
	private static final BigInteger OTA_METER_FLEXNET_ID = new BigInteger("46549474");

	/** The Constant OTA_METER_DEVICE_ID. */
	private static final String OTA_METER_DEVICE_ID = "1N6028900474";

	/** The Constant SIXTY_TWO. */
	protected static final Integer SIXTY_TWO = 62;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchAllSchedule(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMSchedule> fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			InternalResultsResponse<DMSchedule> internalResultsResponse = new InternalResultsResponse<DMSchedule>();
			internalResultsResponse.getResultsList().add(new DMSchedule());
			internalResultsResponse.getResultsList().add(new DMSchedule());
			internalResultsResponse.getResultsList().add(new DMSchedule());
			internalResultsResponse.getResultsList().add(new DMSchedule());
			internalResultsResponse.getResultsList().add(new DMSchedule());
			return internalResultsResponse;
		}

		return (InternalResultsResponse<DMSchedule>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#insertSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMSchedule> insertSchedule(ScheduleRequest scheduleRequest)
	{
		return createNewInternalResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#deleteSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{

		return createNewInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#updateSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMSchedule> updateSchedule(ScheduleRequest scheduleRequest)
	{
		return createNewInternalResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchSchedule(ScheduleRequest scheduleRequest)
	{
		return createNewInternalResultsResponse();

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.bcl.IScheduleBCL#generateFileCSV(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InternalResponse generateFileCSV(InquiryScheduleRequest inquiryScheduleRequest)
	{
		return createNewInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#generateFileCSVScheduleDevice(com.sensus.dm.common.schedule.model.
	 * request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResponse generateFileCSVScheduleDevice(ScheduleRequest scheduleRequest)
	{
		return createNewInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchSchedulersToExecute(com.sensus.dm.common.schedule.model.request
	 * .
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchSchedulesToExecute(ScheduleRequest request)
	{
		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			return response;
		}

		DMSchedule schedule = TestBaseUtil.createSchedule(DMConvertUtil.convertDateToDefaultUTC(new Date()),
				ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.getActionTypeName(), FrequencyTypeEnum.DAILY);
		schedule.setId(1);
		schedule.getFrequency().setNeverEnds(null);
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, +1);
		schedule.getFrequency().setEndDate(DMConvertUtil.convertDateToDefaultUTC(cal1.getTime()));
		response.getResultsList().add(schedule);

		DemandResponseEventAction demandResponse = (DemandResponseEventAction)schedule.getDmAction();
		demandResponse.setId(ONE);
		demandResponse.setActionType(new ActionType(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT));
		demandResponse.setIsMonitored(Boolean.TRUE);
		demandResponse.setOnDemand(Boolean.TRUE);
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);

		demandResponse.setActionTime(DMConvertUtil.convertDateToDefaultUTC(c.getTime()));

		demandResponse.addDevice(TestBaseUtil.createDevice(DeviceTypeEnum.ELECTRIC_METER, OTA_METER_FLEXNET_ID,
				OTA_METER_DEVICE_ID));
		demandResponse.setEnrollmentCode(1);
		demandResponse.setDemandResponseDuration(SIXTY_TWO);
		demandResponse.setRandomizeStart(Boolean.TRUE);
		demandResponse.setRandomizeEnd(Boolean.FALSE);
		demandResponse.setCooling(FIVE);
		demandResponse.setCriticalityLevel(ONE);
		demandResponse.addDeviceClass(ALL);

		DMSchedule schedule1 =
				TestBaseUtil.createSchedule(new Date(), ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.NEVER);
		schedule1.setId(1);
		response.getResultsList().add(schedule1);

		DMSchedule schedule2 =
				TestBaseUtil.createSchedule(new Date(), ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.DAILY);
		schedule2.setId(2);
		schedule2.getFrequency().setNeverEnds(null);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, +1);
		schedule2.getFrequency().setEndDate(cal.getTime());
		schedule2.getFrequency().setEndsAfterOccurrences(2);
		schedule2.getFrequency().setExecutedOccurrences(1);
		response.getResultsList().add(schedule2);

		DMSchedule schedule3 =
				TestBaseUtil.createSchedule(new Date(), ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.EVERY_WEEKDAY);
		schedule3.setId(THREE);
		response.getResultsList().add(schedule3);

		DMSchedule schedule4 =
				TestBaseUtil.createSchedule(new Date(), ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.MONTHLY);
		schedule4.setId(FOUR);
		response.getResultsList().add(schedule4);

		DMSchedule schedule5 =
				TestBaseUtil.createSchedule(new Date(), ActionTypeEnum.INITIATE_DEMAND_RESET_EVENT.getActionTypeName(),
						FrequencyTypeEnum.WEEKLY);
		schedule5.setId(FIVE);
		response.getResultsList().add(schedule5);

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#updateScheduleStatus(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse updateScheduleStatus(ScheduleRequest scheduleRequest)
	{
		return createNewInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchScheduleByGroup(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchScheduleByGroup(ScheduleRequest scheduleRequest)
	{
		return createNewInternalResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchScheduleByDevice(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchScheduleByDevice(ScheduleRequest scheduleRequest)
	{
		return createNewInternalResultsResponse();
	}

	/**
	 * Creates the new internal results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<DMSchedule> createNewInternalResultsResponse()
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			InternalResultsResponse<DMSchedule> internalResultsResponse = new InternalResultsResponse<DMSchedule>();
			internalResultsResponse.getResultsList().add(new DMSchedule());
			return internalResultsResponse;
		}

		return (InternalResultsResponse<DMSchedule>)verifyOtherSituations();
	}

	/**
	 * Creates the new internal response.
	 * 
	 * @return the internal response
	 */
	private InternalResponse createNewInternalResponse()
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			InternalResponse internalResponse = new InternalResponse();
			internalResponse.setStatus(Status.OperationSuccess);
			return internalResponse;
		}

		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#insertScheduleOnDemand(com.sensus.dm.elec.action.model.request
	 * .InquiryActionRequest)
	 */
	@Override
	public InternalResponse insertScheduleOnDemand(InquiryActionRequest inquiryActionRequest)
	{
		return new InternalResponse();
	}

}