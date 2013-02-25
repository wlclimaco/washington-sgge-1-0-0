package com.sensus.mlc.schedule.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.APPLY_SMP_SCHEDULE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.CLEAR_SMP_SCHEDULE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_ID;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_APPLY_SCHEDULE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.GATEWAY_CLEAR_SCHEDULE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INITIATE_DELETE_SCHEDULE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INITIATE_UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.schedule.bcf.IScheduleBCF;
import com.sensus.mlc.schedule.bcl.IScheduleBCL;
import com.sensus.mlc.schedule.model.Schedule;
import com.sensus.mlc.schedule.model.request.InquiryScheduleRequest;
import com.sensus.mlc.schedule.model.request.ScheduleRequest;
import com.sensus.mlc.schedule.model.response.InquiryScheduleResponse;
import com.sensus.mlc.schedule.model.response.ScheduleResponse;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class ScheduleBCFImpl.
 */
public class ScheduleBCFImpl extends AbstractBaseBCF implements IScheduleBCF
{

	/** The Constant LIGHT_LIST. */
	private static final String LIGHT_LIST = ObjectToBeValidatedKeyEnum.LIGHT_LIST.getValue();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.schedulebcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleBCFImpl.class);

	/** The Constant INQUIRY_ECOMODE_REQUEST_NAME. */
	private static final String INQUIRY_SCHEDULE_REQUEST_NAME = InquiryScheduleRequest.class.getSimpleName();

	/** The Constant SCHEDULE_NAME. */
	private static final String SCHEDULE_NAME = Schedule.class.getSimpleName();

	/** The Constant SCHEDULE_REQUEST_NAME. */
	private static final String SCHEDULE_REQUEST_NAME = ScheduleRequest.class.getSimpleName();

	/** The Constant TENANT_NAME. */
	private static final String TENANT_NAME = Tenant.class.getSimpleName();

	/** The scheduleBcl. */
	private IScheduleBCL scheduleBCL; // injected by Spring through setter

	/** The schedule validation controller. */
	private ValidationController scheduleValidationController;

	/** The light list validation controller. */
	private ValidationController lightListValidationController;

	/** The map data validation controller. */
	private ValidationController mapDataValidationController;

	/**
	 * Spring Sets the scheduleBcl.
	 * 
	 * @param scheduleBCLInjected the new schedule bcl
	 */
	public void setScheduleBCL(IScheduleBCL scheduleBCLInjected)
	{
		this.scheduleBCL = scheduleBCLInjected;
	}

	/**
	 * Gets the scheduleBcl.
	 * 
	 * @return the scheduleBcl
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return this.scheduleBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcf.IScheduleBCF#fetchAllSchedules(com.sensus.mlc.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InquiryScheduleResponse fetchAllSchedules(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InquiryScheduleResponse response = new InquiryScheduleResponse();
		InternalResultsResponse<Schedule> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH);
			context.putObjectToBeValidated(INQUIRY_SCHEDULE_REQUEST_NAME, inquiryScheduleRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().fetchAllSchedules(inquiryScheduleRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#insertSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse scheduleResponse = new ScheduleResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(SCHEDULE_REQUEST_NAME, scheduleRequest);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());
			context.putObjectToBeValidated(TENANT_NAME, scheduleRequest.getTenant());

			if (getLightingControlRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().insertSchedule(scheduleRequest);
			}

			handleOperationStatusAndMessages(scheduleResponse, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, scheduleResponse, ex, DEFAULT_EXCEPTION_MSG);
		}
		return scheduleResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#initiateDeleteSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse initiateDeleteSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), INITIATE_DELETE_SCHEDULE);
			context.putObjectToBeValidated(SCHEDULE_REQUEST_NAME, scheduleRequest);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().initiateDeleteSchedule(scheduleRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#deleteSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResponse internalResponse = null;
		try
		{

			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), DELETE);
			context.putObjectToBeValidated(SCHEDULE_REQUEST_NAME, scheduleRequest);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());

			if (getLightingControlRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().deleteSchedule(scheduleRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#initiateUpdateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse initiateUpdateSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResponse internalResponse = null;
		try
		{

			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), INITIATE_UPDATE);
			context.putObjectToBeValidated(SCHEDULE_REQUEST_NAME, scheduleRequest);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());
			context.putObjectToBeValidated(TENANT_NAME, scheduleRequest.getTenant());

			if (getLightSelectionRequestValidationController().validate(context) &&
					getScheduleValidationController().validate(context))
			{
				context.putObjectToBeValidated(LIGHT_LIST, scheduleRequest.getSchedule().getLights());
				if (getLightListValidationController().validate(context))
				{
					internalResponse = getScheduleBCL().initiateUpdateSchedule(scheduleRequest);
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#updateSchedule(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());
			context.putObjectToBeValidated(TENANT_NAME, scheduleRequest.getTenant());

			if (getScheduleValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().updateSchedule(scheduleRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#fetchScheduleById(com.sensus.mlc.schedule.model.request.ScheduleRequest)
	 */
	@Override
	public ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResultsResponse<Schedule> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), FETCH_BY_ID);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());

			if (getScheduleValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().fetchScheduleById(scheduleRequest);
				response.setSchedules(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#initiateDeleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public ScheduleResponse initiateDeleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), GATEWAY_CLEAR_SCHEDULE);
			context.putObjectToBeValidated(SCHEDULE_REQUEST_NAME, scheduleRequest);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());

			if (getLightSelectionRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				context.putObjectToBeValidated(LIGHT_LIST, scheduleRequest.getSchedule().getLights());
				if (getLightListValidationController().validate(context))
				{
					internalResponse = getScheduleBCL().initiateDeleteSmartpointFromSchedule(scheduleRequest);
					response.setProcesses(internalResponse.getResultsList());
				}

			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcf.IScheduleBCF#deleteSmartpointFromSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse deleteSmartpointFromSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), CLEAR_SMP_SCHEDULE);
			context.putObjectToBeValidated(SCHEDULE_REQUEST_NAME, scheduleRequest);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());

			if (getScheduleValidationController().validate(context))
			{

				context.putObjectToBeValidated(LIGHT_LIST, scheduleRequest.getSchedule().getLights());
				if (getLightListValidationController().validate(context))
				{
					internalResponse = getScheduleBCL().deleteSmartpointFromSchedule(scheduleRequest);
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.schedule.bcf.IScheduleBCF#initiateApplySmartPointToSchedule(com.sensus.mlc.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public ScheduleResponse initiateApplySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResultsResponse<Process> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), GATEWAY_APPLY_SCHEDULE);
			context.putObjectToBeValidated(SCHEDULE_REQUEST_NAME, scheduleRequest);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());

			if (getLightSelectionRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{

				context.putObjectToBeValidated(LIGHT_LIST, scheduleRequest.getSchedule().getLights());
				if (getLightListValidationController().validate(context))
				{
					internalResponse = getScheduleBCL().initiateApplySmartPointToSchedule(scheduleRequest);
					response.setProcesses(internalResponse.getResultsList());
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.schedule.bcf.IScheduleBCF#applySmartPointToSchedule(com.sensus.mlc.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse applySmartPointToSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(getSlcActionName(), APPLY_SMP_SCHEDULE);
			context.putObjectToBeValidated(SCHEDULE_NAME, scheduleRequest.getSchedule());

			if (getScheduleValidationController().validate(context))
			{

				context.putObjectToBeValidated(LIGHT_LIST, scheduleRequest.getSchedule().getLights());
				if (getLightListValidationController().validate(context))
				{
					internalResponse = getScheduleBCL().applySmartPointToSchedule(scheduleRequest);
				}
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Gets the schedule validation controller.
	 * 
	 * @return the schedule validation controller
	 */
	public ValidationController getScheduleValidationController()
	{
		return this.scheduleValidationController;
	}

	/**
	 * Sets the schedule validation controller.
	 * 
	 * @param scheduleValidationController the new schedule validation controller
	 */
	public void setScheduleValidationController(ValidationController scheduleValidationController)
	{
		this.scheduleValidationController = scheduleValidationController;
	}

	/**
	 * Gets the light list validation controller.
	 * 
	 * @return the light list validation controller
	 */
	public ValidationController getLightListValidationController()
	{
		return this.lightListValidationController;
	}

	/**
	 * Sets the light list validation controller.
	 * 
	 * @param lightListValidationController the new light list validation controller
	 */
	public void setLightListValidationController(ValidationController lightListValidationController)
	{
		this.lightListValidationController = lightListValidationController;
	}

	/**
	 * Gets the map data validation controller.
	 * 
	 * @return the map data validation controller
	 */
	public ValidationController getMapDataValidationController()
	{
		return this.mapDataValidationController;
	}

	/**
	 * Sets the map data validation controller.
	 * 
	 * @param mapDataValidationController the new map data validation controller
	 */
	public void setMapDataValidationController(ValidationController mapDataValidationController)
	{
		this.mapDataValidationController = mapDataValidationController;
	}
}
