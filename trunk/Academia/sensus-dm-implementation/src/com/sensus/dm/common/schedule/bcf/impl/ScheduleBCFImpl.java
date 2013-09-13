package com.sensus.dm.common.schedule.bcf.impl;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.Schedule;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.common.schedule.bcl.IScheduleBCL;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.InquiryScheduleResponse;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.action.model.DemandResponseEventAction;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Class ScheduleBCFImpl.
 * 
 * @author QAT Global.
 */
public class ScheduleBCFImpl implements IScheduleBCF
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleBCFImpl.class);

	/** The schedule bcl. */
	private IScheduleBCL scheduleBCL; // injected by Spring through setter

	/** The schedule validation controller. */
	private ValidationController scheduleValidationController;

	/** The frequency validation controller. */
	private ValidationController frequencyValidationController;

	/** The action validation controller. */
	private ValidationController actionValidationController;

	/** The group validation controller. */
	private ValidationController groupValidationController;

	/** The device validation controller. */
	private ValidationController deviceValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/** The range date validation controller. */
	private ValidationController rangeDateValidationController;

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController;

	private ValidationController radioValidationController;

	/** The inquiry action request validation controller. */
	private ValidationController inquiryActionRequestValidationController;

	/** The Constant DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG = "sensus.epm.schedulebcfimpl.defaultexception";

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
	public void setScheduleBCL(IScheduleBCL scheduleBCL)
	{
		this.scheduleBCL = scheduleBCL;
	}

	/**
	 * Gets the tenant request validation controller.
	 * 
	 * @return the tenant request validation controller
	 */
	public ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	/**
	 * Sets the tenant request validation controller.
	 * 
	 * @param tenantRequestValidationController the new tenant request validation controller
	 */
	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	/**
	 * Gets the schedule validation controller.
	 * 
	 * @return the schedule validation controller
	 */
	public ValidationController getScheduleValidationController()
	{
		return scheduleValidationController;
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
	 * Gets the action validation controller.
	 * 
	 * @return the action validation controller
	 */
	public ValidationController getActionValidationController()
	{
		return actionValidationController;
	}

	/**
	 * Sets the action validation controller.
	 * 
	 * @param actionValidationController the new action validation controller
	 */
	public void setActionValidationController(ValidationController actionValidationController)
	{
		this.actionValidationController = actionValidationController;
	}

	/**
	 * Gets the device validation controller.
	 * 
	 * @return the device validation controller
	 */
	public ValidationController getDeviceValidationController()
	{
		return deviceValidationController;
	}

	/**
	 * Sets the device validation controller.
	 * 
	 * @param deviceValidationController the new device validation controller
	 */
	public void setDeviceValidationController(ValidationController deviceValidationController)
	{
		this.deviceValidationController = deviceValidationController;
	}

	/**
	 * Gets the group validation controller.
	 * 
	 * @return the group validation controller
	 */
	public ValidationController getGroupValidationController()
	{
		return groupValidationController;
	}

	/**
	 * Sets the group validation controller.
	 * 
	 * @param groupValidationController the new group validation controller
	 */
	public void setGroupValidationController(ValidationController groupValidationController)
	{
		this.groupValidationController = groupValidationController;
	}

	/**
	 * Gets the inquiry request validation controller.
	 * 
	 * @return the inquiry request validation controller
	 */
	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * Sets the inquiry request validation controller.
	 * 
	 * @param inquiryRequestValidationController the new inquiry request validation controller
	 */
	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
	}

	/**
	 * Gets the frequency validation controller.
	 * 
	 * @return the frequency validation controller
	 */
	public ValidationController getFrequencyValidationController()
	{
		return frequencyValidationController;
	}

	/**
	 * Sets the frequency validation controller.
	 * 
	 * @param frequencyValidationController the new frequency validation controller
	 */
	public void setFrequencyValidationController(ValidationController frequencyValidationController)
	{
		this.frequencyValidationController = frequencyValidationController;
	}

	/**
	 * Gets the range date validation controller.
	 * 
	 * @return the range date validation controller
	 */
	public ValidationController getRangeDateValidationController()
	{
		return rangeDateValidationController;
	}

	/**
	 * Sets the range date validation controller.
	 * 
	 * @param rangeDateValidationController the new range date validation controller
	 */
	public void setRangeDateValidationController(ValidationController rangeDateValidationController)
	{
		this.rangeDateValidationController = rangeDateValidationController;
	}

	/**
	 * Gets the inquiry action request validation controller.
	 * 
	 * @return the inquiry action request validation controller
	 */
	public ValidationController getInquiryActionRequestValidationController()
	{
		return inquiryActionRequestValidationController;
	}

	/**
	 * Sets the inquiry action request validation controller.
	 * 
	 * @param inquiryActionRequestValidationController the new inquiry action request validation controller
	 */
	public void setInquiryActionRequestValidationController(
			ValidationController inquiryActionRequestValidationController)
	{
		this.inquiryActionRequestValidationController = inquiryActionRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchAllSchedule(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InquiryScheduleResponse fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InquiryScheduleResponse response = new InquiryScheduleResponse();

		try
		{
			InternalResultsResponse<DMSchedule> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_SCHEDULE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryScheduleRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryScheduleRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryScheduleRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().fetchAllSchedule(inquiryScheduleRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleById(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse fetchScheduleById(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResultsResponse<DMSchedule> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_ID);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);
			context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());

			if (getTenantRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().fetchSchedule(scheduleRequest);
				response.setSchedules(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByName(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByName(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResultsResponse<DMSchedule> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_NAME);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);
			context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());

			if (getTenantRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().fetchSchedule(scheduleRequest);
				response.setSchedules(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByGroup(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByGroup(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResultsResponse<DMSchedule> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_SCHEDULE_BY_GROUP);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);
			context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());

			if (getTenantRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				context.putObjectToBeValidated(DMAction.class.getSimpleName(),
						scheduleRequest.getSchedule().getDmAction());
				if (getActionValidationController().validate(context))
				{
					context.putObjectToBeValidated(Group.class.getSimpleName(), scheduleRequest.getSchedule()
							.getDmAction().getFirstGroup());
					if (getGroupValidationController().validate(context))
					{
						internalResponse = getScheduleBCL().fetchScheduleByGroup(scheduleRequest);
						response.setSchedules(internalResponse.getResultsList());
					}
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByDevice(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByDevice(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResultsResponse<DMSchedule> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_SCHEDULE_BY_DEVICE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);
			context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());

			if (getTenantRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				context.putObjectToBeValidated(DMAction.class.getSimpleName(),
						scheduleRequest.getSchedule().getDmAction());
				if (getActionValidationController().validate(context))
				{
					Device device = scheduleRequest.getSchedule().getDmAction().getFirstDevice();
					context.putObjectToBeValidated(Device.class.getSimpleName(), device);
					if (getDeviceValidationController().validate(context))
					{
						Radio radio = device.getRadio();
						context.putObjectToBeValidated(Radio.class.getSimpleName(), radio);
						if (getRadioValidationController().validate(context))
						{
							internalResponse = getScheduleBCL().fetchScheduleByDevice(scheduleRequest);
							response.setSchedules(internalResponse.getResultsList());
						}
					}
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#fetchScheduleByAction(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse fetchScheduleByAction(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResultsResponse<DMSchedule> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_SCHEDULE_BY_ACTION);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);
			context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());

			if (getTenantRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				context.putObjectToBeValidated(DMAction.class.getSimpleName(), scheduleRequest.getSchedule()
						.getDmAction());
				if (getActionValidationController().validate(context))
				{
					internalResponse = getScheduleBCL().fetchSchedule(scheduleRequest);
					response.setSchedules(internalResponse.getResultsList());
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#insertSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse insertSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResultsResponse<DMSchedule> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_SCHEDULE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);

			if (validateSchedule(context, scheduleRequest)
					&& getTenantRequestValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().insertSchedule(scheduleRequest);
				response.setSchedules(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#updateSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE_SCHEDULE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);

			if (validateSchedule(context, scheduleRequest)
					&& getTenantRequestValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().updateSchedule(scheduleRequest);
				response.setSchedules(new ArrayList<DMSchedule>());
				response.getSchedules().add(scheduleRequest.getSchedule());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#updateScheduleStatus(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse updateScheduleStatus(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE_STATUS);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);
			context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());

			if (getTenantRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().updateScheduleStatus(scheduleRequest);
				response.setSchedules(new ArrayList<DMSchedule>());
				response.getSchedules().add(scheduleRequest.getSchedule());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#deleteSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public ScheduleResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					scheduleRequest);
			context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());

			if (getTenantRequestValidationController().validate(context)
					&& getScheduleValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().deleteSchedule(scheduleRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#generateFileCSV(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest
	 * )
	 */
	@Override
	public InquiryScheduleResponse generateFileCSV(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InquiryScheduleResponse response = new InquiryScheduleResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryScheduleRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryScheduleRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryScheduleRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().generateFileCSV(inquiryScheduleRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcf.IScheduleBCF#generateFileCSVScheduleDevice(com.sensus.dm.common.schedule.model.
	 * request
	 * .
	 * ScheduleRequest)
	 */
	@Override
	public ScheduleResponse generateFileCSVScheduleDevice(ScheduleRequest scheduleRequest)
	{
		ScheduleResponse response = new ScheduleResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), scheduleRequest);

			if (getScheduleValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				internalResponse = getScheduleBCL().generateFileCSVScheduleDevice(scheduleRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Insert schedule on demand.
	 * 
	 * @param inquiryActionRequest the inquiry action request
	 * @return the schedule response
	 */
	@Override
	public ScheduleResponse insertScheduleOnDemand(InquiryActionRequest inquiryActionRequest)
	{
		ScheduleResponse response = new ScheduleResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.APPLY_DEVICE_TO_ACTION);
			context.putObjectToBeValidated(InquiryActionRequest.class.getSimpleName(), inquiryActionRequest);
			context.putObjectToBeValidated(DMAction.class.getSimpleName(), inquiryActionRequest.getFirstAction());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryActionRequest);

			if (getInquiryActionRequestValidationController().validate(context)
					&& validateDuration(context, inquiryActionRequest.getFirstAction()))
			{
				internalResponse =
						getScheduleBCL().insertScheduleOnDemand(inquiryActionRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_SCHEDULE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Validate duration.
	 * 
	 * @param context the context
	 * @param action the action
	 * @return true, if successful
	 */
	private boolean validateDuration(ValidationContext context, DMAction action)
	{
		if (!ValidationUtil.isNull(action.getActionType())
				&& ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT.equals(action.getActionType().getActionTypeEnum()))
		{
			DemandResponseEventAction demandResponse = (DemandResponseEventAction)action;

			return DMUtil.validateDuration(context.getMessages(), demandResponse.getActionTime(),
					DMConvertUtil.convertDateToDefaultUTC(new Date()), demandResponse.getDemandResponseDuration());
		}
		return true;
	}

	/**
	 * Validate insert schedule.
	 * 
	 * @param context the context
	 * @param scheduleRequest the schedule request
	 * @return true, if successful
	 */
	private boolean validateSchedule(ValidationContext context, ScheduleRequest scheduleRequest)
	{
		// validate schedule
		context.putObjectToBeValidated(Schedule.class.getSimpleName(), scheduleRequest.getSchedule());

		if (!getScheduleValidationController().validate(context))
		{
			return false;
		}

		// validate frequency
		context.putObjectToBeValidated(Frequency.class.getSimpleName(), scheduleRequest.getSchedule()
				.getFrequency());

		if (!getFrequencyValidationController().validate(context))
		{
			return false;
		}

		// validate Action
		context.putObjectToBeValidated(DMAction.class.getSimpleName(), scheduleRequest.getSchedule().getDmAction());

		if (!getActionValidationController().validate(context))
		{
			return false;
		}

		return validateScheduleDates(context, scheduleRequest);
	}

	/**
	 * Validate schedule dates.
	 * 
	 * @param context the context
	 * @param scheduleRequest the schedule request
	 * @return true, if successful
	 */
	private boolean validateScheduleDates(ValidationContext context, ScheduleRequest scheduleRequest)
	{
		// validate schedule dates x repeat start date
		// starts on date (repeat dialog) must be greater than schedule date time
		context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
				scheduleRequest.getSchedule().getStartTime());
		context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
				scheduleRequest.getSchedule().getFrequency().getStartOnDate());

		if (!getRangeDateValidationController().validate(context))
		{
			return false;
		}

		// validate repeat start and end dates
		// if end date is informed (repeat dialog), then it must be greater than starts on date
		if (!ValidationUtil.isNull(scheduleRequest.getSchedule().getFrequency().getStartOnDate()))
		{
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
					scheduleRequest.getSchedule().getFrequency().getStartOnDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
					scheduleRequest.getSchedule().getFrequency().getEndDate());

			if (!getRangeDateValidationController().validate(context))
			{
				return false;
			}
		}

		// The ActionTime of DemanadResponse can be a date in the past
		// but can not exceed 24 hours from the time of start
		if (!ValidationUtil.isNull(scheduleRequest.getSchedule().getDmAction())
				&& scheduleRequest.getSchedule().getDmAction().getActionType().getActionTypeEnum()
						.equals(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT))
		{
			return validateDateDemandResponse(context, scheduleRequest);
		}

		// validate pre set dates according each action
		// schedule date time must be greater than pre set date (action time)
		context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
				scheduleRequest.getSchedule().getStartTime());
		context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
				scheduleRequest.getSchedule().getDmAction().getActionTime());

		if (!getRangeDateValidationController().validate(context))
		{
			return false;
		}

		return true;
	}

	/**
	 * Validate date demand response.
	 * 
	 * @param context the context
	 * @param scheduleRequest the schedule request
	 * @return true, if successful
	 */
	private boolean validateDateDemandResponse(ValidationContext context, ScheduleRequest scheduleRequest)
	{
		DemandResponseEventAction action = (DemandResponseEventAction)scheduleRequest.getSchedule().getDmAction();

		if (validateActionDate(context, scheduleRequest.getSchedule().getStartTime(), scheduleRequest.getSchedule()
				.getDmAction().getActionTime()))
		{
			return true;
		}

		Date nowDate = DMConvertUtil.convertDateToDefaultUTC(new Date());

		if (validateActionDate(context, nowDate, scheduleRequest.getSchedule().getStartTime()))
		{
			nowDate = scheduleRequest.getSchedule().getStartTime();
		}

		return DMUtil.validateDuration(context.getMessages(), action.getActionTime(), nowDate,
				action.getDemandResponseDuration());

	}

	/**
	 * Validate action date.
	 * 
	 * @param context the context
	 * @param ini the ini
	 * @param end the end
	 * @return true, if successful
	 */
	private boolean validateActionDate(ValidationContext context, Date initialDate, Date endDate)
	{
		if (!ValidationUtil.isNull(initialDate) && !ValidationUtil.isNull(endDate))
		{
			if (endDate.before(initialDate))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * @return the radioValidationController
	 */
	public ValidationController getRadioValidationController()
	{
		return radioValidationController;
	}

	/**
	 * @param radioValidationController the radioValidationController to set
	 */
	public void setRadioValidationController(ValidationController radioValidationController)
	{
		this.radioValidationController = radioValidationController;
	}

}
