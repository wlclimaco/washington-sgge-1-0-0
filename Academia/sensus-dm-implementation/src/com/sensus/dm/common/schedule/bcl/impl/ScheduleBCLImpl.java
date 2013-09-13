package com.sensus.dm.common.schedule.bcl.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.model.Frequency;
import com.sensus.common.scheduler.model.FrequencyTypeEnum;
import com.sensus.common.scheduler.model.ScheduleStatusEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.AddDeviceToScheduleAction;
import com.sensus.dm.common.action.model.CreateScheduleAction;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.action.model.EditScheduleAction;
import com.sensus.dm.common.action.model.RemoveScheduleAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.schedule.bcl.IScheduleBCL;
import com.sensus.dm.common.schedule.dac.IScheduleDAC;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.elec.action.bcl.IActionBCL;
import com.sensus.dm.elec.action.model.GetDemandResponseSetupStatusAction;
import com.sensus.dm.elec.action.model.GetTamperDetectTimerAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;
import com.sensus.dm.water.device.model.WaterMeter;

/**
 * The Class ScheduleBCLImpl.
 * 
 * @author QAT Global.
 */
public class ScheduleBCLImpl implements IScheduleBCL
{

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant DEMANDREAD_COLUMNS_TO_EXPORT. */
	private static final String[] SCHEDULE_COLUMNS_TO_EXPORT = {CsvColumnEnum.EVENT_NAME.getValue(),
			CsvColumnEnum.ACTION_NAME_SCH.getValue(), CsvColumnEnum.ACTION_TYPE_SCH.getValue(),
			CsvColumnEnum.SCHEDULE_BY.getValue(), CsvColumnEnum.DELIVER_ON.getValue(),
			CsvColumnEnum.ACTION_DATE_SCH.getValue(), CsvColumnEnum.FREQUENCY.getValue(),
			CsvColumnEnum.DEVICE_COUNT.getValue(), CsvColumnEnum.SCHEDULE_STATUS.getValue()};

	/** The Constant SCHEDULE_DEVICE_COLUMNS_TO_EXPORT. */
	private static final String[] SCHEDULE_DEVICE_COLUMNS_TO_EXPORT = {CsvColumnEnum.DEVICE_ID.getValue(),
			CsvColumnEnum.NETWORK_ADDRESS.getValue(), CsvColumnEnum.ADDRESS.getValue()};

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant FILE_CREATION_FAILED. */
	private static final String FILE_CREATION_FAILED = "sensus.epm.processbclimpl.file.creation.failed";

	/** The Constant PROCESSING_SCHEDULE. */
	private static final String PROCESSING_SCHEDULE =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingSchedule";

	/** The Constant UNDERLINE. */
	private static final String UNDERLINE = "_";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp file path. */
	private String tempUploadFilePath;

	/** The schedule dac. */
	private IScheduleDAC scheduleDAC;

	/** The action bcl. */
	private IActionBCL actionBCL;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The schedule columns. */
	private List<CSVColumn> scheduleColumns;

	/** The schedule device colums. */
	private List<CSVColumn> scheduleDeviceColums;

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL;

	/** The water meter bcl. */
	private IWaterMeterBCL waterMeterBCL;

	/** The gas meter bcl. */
	private IGasMeterBCL gasMeterBCL;

	/**
	 * Gets the temp upload file path.
	 * 
	 * @return the tempUploadFilePath
	 */
	public String getTempUploadFilePath()
	{
		return tempUploadFilePath;
	}

	/**
	 * Sets the temp upload file path.
	 * 
	 * @param tempUploadFilePath the tempUploadFilePath to set
	 */
	public void setTempUploadFilePath(String tempUploadFilePath)
	{
		this.tempUploadFilePath = tempUploadFilePath;
	}

	/**
	 * Gets the schedule dac.
	 * 
	 * @return the schedule dac
	 */
	public IScheduleDAC getScheduleDAC()
	{
		return scheduleDAC;
	}

	/**
	 * Sets the schedule dac.
	 * 
	 * @param scheduleDAC the new schedule dac
	 */
	public void setScheduleDAC(IScheduleDAC scheduleDAC)
	{
		this.scheduleDAC = scheduleDAC;
	}

	/**
	 * Gets the action bcl.
	 * 
	 * @return the action bcl
	 */
	public IActionBCL getActionBCL()
	{
		return actionBCL;
	}

	/**
	 * Sets the action bcl.
	 * 
	 * @param actionBCL the new action bcl
	 */
	public void setActionBCL(IActionBCL actionBCL)
	{
		this.actionBCL = actionBCL;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Gets the schedule columns.
	 * 
	 * @return the schedule columns
	 */
	public List<CSVColumn> getScheduleColumns()
	{
		return scheduleColumns;
	}

	/**
	 * Sets the schedule columns.
	 * 
	 * @param scheduleColumns the new schedule columns
	 */
	public void setScheduleColumns(List<CSVColumn> scheduleColumns)
	{
		this.scheduleColumns = scheduleColumns;
	}

	/**
	 * Gets the schedule device colums.
	 * 
	 * @return the schedule device colums
	 */
	public List<CSVColumn> getScheduleDeviceColums()
	{
		return scheduleDeviceColums;
	}

	/**
	 * Sets the schedule device colums.
	 * 
	 * @param scheduleDeviceColums the new schedule device colums
	 */
	public void setScheduleDeviceColums(List<CSVColumn> scheduleDeviceColums)
	{
		this.scheduleDeviceColums = scheduleDeviceColums;
	}

	/**
	 * Gets the electric meter bcl.
	 * 
	 * @return the electric meter bcl
	 */
	public IElectricMeterBCL getElectricMeterBCL()
	{
		return electricMeterBCL;
	}

	/**
	 * Sets the electric meter bcl.
	 * 
	 * @param electricMeterBCL the new electric meter bcl
	 */
	public void setElectricMeterBCL(IElectricMeterBCL electricMeterBCL)
	{
		this.electricMeterBCL = electricMeterBCL;
	}

	/**
	 * Gets the water meter bcl.
	 * 
	 * @return the water meter bcl
	 */
	public IWaterMeterBCL getWaterMeterBCL()
	{
		return waterMeterBCL;
	}

	/**
	 * Sets the water meter bcl.
	 * 
	 * @param waterMeterBCL the new water meter bcl
	 */
	public void setWaterMeterBCL(IWaterMeterBCL waterMeterBCL)
	{
		this.waterMeterBCL = waterMeterBCL;
	}

	/**
	 * Gets the gas meter bcl.
	 * 
	 * @return the gas meter bcl
	 */
	public IGasMeterBCL getGasMeterBCL()
	{
		return gasMeterBCL;
	}

	/**
	 * Sets the gas meter bcl.
	 * 
	 * @param gasMeterBCL the new gas meter bcl
	 */
	public void setGasMeterBCL(IGasMeterBCL gasMeterBCL)
	{
		this.gasMeterBCL = gasMeterBCL;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchAllSchedule(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchAllSchedule(InquiryScheduleRequest inquiryScheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = getScheduleDAC().fetchAllSchedule(inquiryScheduleRequest);
		if (response.isInError() || !response.hasResults())
		{
			return response;
		}

		for (DMSchedule schedule : response.getResultsList())
		{
			DMConvertUtil.convertPropertyToScheduleParameter(schedule);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchSchedule(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = getScheduleDAC().fetchSchedule(scheduleRequest);
		if (response.isInError() || !response.hasResults())
		{
			return response;
		}

		DMConvertUtil.convertPropertyToScheduleParameter(response.getFirstResult());

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchScheduleByGroup(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchScheduleByGroup(ScheduleRequest scheduleRequest)
	{
		return getScheduleDAC().fetchScheduleByGroup(scheduleRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchScheduleByDevice(com.sensus.dm.common.schedule.model.request
	 * .ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchScheduleByDevice(ScheduleRequest scheduleRequest)
	{
		return getScheduleDAC().fetchScheduleByDevice(scheduleRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#fetchSchedulesToExecute(com.sensus.dm.common.schedule.model.request
	 * .
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> fetchSchedulesToExecute(ScheduleRequest scheduleRequest)
	{
		InternalResultsResponse<DMSchedule> response = getScheduleDAC().fetchSchedulesToExecute(scheduleRequest);
		if (response.isInError() || !response.hasResults())
		{
			return response;
		}

		for (DMSchedule schedule : response.getResultsList())
		{
			DMConvertUtil.convertPropertyToScheduleParameter(schedule);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#insertSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResultsResponse<DMSchedule> insertSchedule(ScheduleRequest scheduleRequest)
	{

		InternalResultsResponse<DMSchedule> response = new InternalResultsResponse<DMSchedule>();

		// check whether the schedule name already exists
		InternalResultsResponse<Boolean> canInsertResponse = getScheduleDAC().canScheduleBeInserted(scheduleRequest);
		if (ValidationUtil.isNull(canInsertResponse.getFirstResult()) || !canInsertResponse.getFirstResult())
		{
			return createErrorResponse(response, canInsertResponse);
		}

		// insert the action, its groups and tags
		InternalResultsResponse<DMAction> actionResponse =
				getActionBCL().insertAction(
						new ActionRequest(scheduleRequest.getSchedule().getDmAction(),
								scheduleRequest.getUserContext(),
								scheduleRequest.getTenant()));
		if (actionResponse.isInError())
		{
			return createErrorResponse(response, actionResponse);
		}

		// sets the action Id and inserts the schedule
		scheduleRequest.getSchedule().getDmAction().setId(actionResponse.getFirstResult().getId());

		// inserts the schedule
		response = getScheduleDAC().insertSchedule(scheduleRequest);
		if (response.isInError())
		{
			return response;
		}

		// insert schedule properties
		// InternalResponse internalResponse = insertScheduleProperties(scheduleRequest);
		scheduleRequest.getSchedule().setProperties(createScheduleProperties(scheduleRequest.getSchedule()));

		// insert schedule properties
		InternalResponse internalResponse = getScheduleDAC().insertScheduleProperty(scheduleRequest);
		if (internalResponse.isInError())
		{
			return createErrorResponse(response, internalResponse);
		}

		if (scheduleRequest.getSchedule().getDmAction().isOnDemand())
		{
			return response;
		}

		return insertDeviceSchedule(response, scheduleRequest, CreateScheduleAction.ACTION);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#updateSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleRequest scheduleRequest)
	{

		// check whether the schedule can be updated
		InternalResultsResponse<Boolean> canUpdateResponse = getScheduleDAC().canScheduleBeUpdated(scheduleRequest);
		if (ValidationUtil.isNull(canUpdateResponse.getFirstResult()) || !canUpdateResponse.getFirstResult())
		{
			return canUpdateResponse;
		}

		// check whether there is something running for the schedule
		InternalResponse response = fetchMessageProcessing(scheduleRequest.getSchedule());
		if (response.isInError())
		{
			return response;
		}

		response = updateScheduleStatus(scheduleRequest);
		if (response.isInError())
		{
			return response;
		}

		// update action (devices, groups and tags when available)
		ActionRequest actionRequest =
				new ActionRequest(scheduleRequest.getSchedule().getDmAction(), scheduleRequest.getUserContext(),
						scheduleRequest.getIdsFile(), scheduleRequest.getIdFileType(), scheduleRequest.getUploadIds());
		response = getActionBCL().updateAction(actionRequest);

		if (response.isInError())
		{
			return response;
		}

		return insertDeviceSchedule(new InternalResultsResponse<DMSchedule>(), scheduleRequest,
				EditScheduleAction.ACTION);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#updateScheduleStatus(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse updateScheduleStatus(ScheduleRequest scheduleRequest)
	{
		// update schedule
		InternalResponse response = getScheduleDAC().updateSchedule(scheduleRequest);
		if (response.isInError())
		{
			return response;
		}

		// update schedule properties (will delete and insert again)
		return updateScheduleProperties(scheduleRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#deleteSchedule(com.sensus.dm.common.schedule.model.request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse deleteSchedule(ScheduleRequest scheduleRequest)
	{

		// check whether the schedule can be deleted
		InternalResultsResponse<Boolean> canDeleteResponse = getScheduleDAC().canScheduleBeDeleted(scheduleRequest);
		if (ValidationUtil.isNull(canDeleteResponse.getFirstResult()) || !canDeleteResponse.getFirstResult())
		{
			return canDeleteResponse;
		}

		// check whether there is something running for the schedule
		InternalResponse response = fetchMessageProcessing(scheduleRequest.getSchedule());
		if (response.isInError())
		{
			return response;
		}

		// Used to get schedule name to show on description.
		InternalResultsResponse<DMSchedule> fetchScheduleResponse = fetchSchedule(scheduleRequest);
		if (fetchScheduleResponse.isInError())
		{
			return fetchScheduleResponse;
		}

		// delete the schedule
		scheduleRequest.setSchedule(fetchScheduleResponse.getFirstResult());
		response = getScheduleDAC().deleteSchedule(scheduleRequest);
		if (response.isInError())
		{
			return response;
		}

		// insert the process for deleting schedule
		return insertScheduleProcess(scheduleRequest, RemoveScheduleAction.ACTION, ProcessStatusEnum.COMPLETED, false);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#generateFileCSV(com.sensus.dm.common.schedule.model.request.
	 * InquiryScheduleRequest
	 * )
	 */
	@Override
	public InternalResponse generateFileCSV(InquiryScheduleRequest inquiryScheduleRequest)
	{
		// Get schedules and generate file csv with this information
		InternalResultsResponse<DMSchedule> fetchResponse = fetchAllSchedule(inquiryScheduleRequest);

		if (fetchResponse.isInError())
		{
			return fetchResponse;
		}

		DMUtil.generateCSV(getScheduleColumns(), SCHEDULE_COLUMNS_TO_EXPORT, fetchResponse.getResultsList(),
				inquiryScheduleRequest, fetchResponse);

		if (fetchResponse.isInError())
		{
			return fetchResponse;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryScheduleRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED),
						inquiryScheduleRequest.getUserContext(), inquiryScheduleRequest.getFileName()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.schedule.bcl.IScheduleBCL#generateFileCSVScheduleDevice(com.sensus.dm.common.schedule.model.
	 * request.
	 * ScheduleRequest)
	 */
	@Override
	public InternalResponse generateFileCSVScheduleDevice(ScheduleRequest scheduleRequest)
	{

		// Get schedules and generate file csv with this information
		InternalResultsResponse<DMSchedule> fetchResponse = fetchSchedule(scheduleRequest);

		if (fetchResponse.isInError() || !fetchResponse.hasResults())
		{
			return fetchResponse;
		}

		DMUtil.generateCSV(getScheduleDeviceColums(), SCHEDULE_DEVICE_COLUMNS_TO_EXPORT,
				getDeviceListSchedule(fetchResponse.getFirstResult()),
				scheduleRequest, fetchResponse);

		if (fetchResponse.isInError())
		{
			return fetchResponse;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(
						DMUtil.generateProcess(scheduleRequest.getProcessId(), ProcessStatusEnum.COMPLETED),
						scheduleRequest.getUserContext()));

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
		DMSchedule schedule = new DMSchedule();
		schedule.setStartTime(DMConvertUtil.convertDateToDefaultUTC(new Date()));
		schedule.setCreateDate(schedule.getStartTime());
		schedule.setName(PropertyEnum.ON_DEMAND.getValue() + UNDERLINE + schedule.getStartTime().getTime());
		schedule.setStatusEnum(ScheduleStatusEnum.ENABLED);
		schedule.setDmAction(inquiryActionRequest.getFirstAction());

		schedule.setFrequency(new Frequency());
		schedule.getFrequency().setFrequencyTypeEnum(FrequencyTypeEnum.NEVER);
		schedule.getFrequency().setNextExecution(schedule.getStartTime());

		InternalResponse response =
				insertSchedule(new ScheduleRequest(schedule, inquiryActionRequest.getUserContext(),
						inquiryActionRequest.getTenant(), inquiryActionRequest.getServiceTypeEnum()));

		if (response.isInError())
		{
			return response;
		}

		// fetch all devices if the device list is empty
		if (ValidationUtil.isNullOrEmpty(inquiryActionRequest.getFirstAction().getDevices()))
		{
			fillDevicesToAction(inquiryActionRequest);
		}

		return getActionBCL()
				.insertDevicesToAction(
						new ActionRequest(inquiryActionRequest.getFirstAction(), inquiryActionRequest
								.getUserContext()));
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Fill devices to action.
	 * 
	 * @param inquiryActionRequest the inquiry action request
	 */
	private void fillDevicesToAction(InquiryActionRequest inquiryActionRequest)
	{
		switch (inquiryActionRequest.getDeviceSearch().getFirstDeviceType())
		{
			case ELECTRIC_METER:

				InternalResultsResponse<ElectricMeter> electricResponse =
						getElectricMeterBCL().fetchAllElectricMeter(
								new InquiryDeviceRequest(inquiryActionRequest.getSelectionPaginationIds(),
										inquiryActionRequest.getPaginationAllSelected(), inquiryActionRequest
												.getUserContext(),
										new SortExpression(FLEXNET_ID, Direction.Ascending),
										inquiryActionRequest.getDeviceSearch(), inquiryActionRequest.getTenant()));

				if (electricResponse.isInError() || !electricResponse.hasResults())
				{
					return;
				}

				inquiryActionRequest.getFirstAction().setDevices(new ArrayList<Device>());
				inquiryActionRequest.getFirstAction().getDevices().addAll(electricResponse.getResultsList());
				break;

			case WATER_METER:

				InternalResultsResponse<WaterMeter> waterResponse = getWaterMeterBCL().fetchAllWaterMeters(
						new InquiryDeviceRequest(inquiryActionRequest.getSelectionPaginationIds(),
								inquiryActionRequest.getPaginationAllSelected(), inquiryActionRequest.getUserContext(),
								new SortExpression(FLEXNET_ID, Direction.Ascending),
								inquiryActionRequest.getDeviceSearch(), inquiryActionRequest.getTenant()));

				if (waterResponse.isInError() || !waterResponse.hasResults())
				{
					return;
				}

				inquiryActionRequest.getFirstAction().setDevices(new ArrayList<Device>());
				inquiryActionRequest.getFirstAction().getDevices().addAll(waterResponse.getResultsList());
				break;

			case GAS_METER:

				InternalResultsResponse<GasMeter> gasResponse = getGasMeterBCL().fetchAllGasMeters(
						new InquiryDeviceRequest(inquiryActionRequest.getSelectionPaginationIds(),
								inquiryActionRequest.getPaginationAllSelected(), inquiryActionRequest.getUserContext(),
								new SortExpression(FLEXNET_ID, Direction.Ascending),
								inquiryActionRequest.getDeviceSearch(), inquiryActionRequest.getTenant()));

				if (gasResponse.isInError() || !gasResponse.hasResults())
				{
					return;
				}

				inquiryActionRequest.getFirstAction().setDevices(new ArrayList<Device>());
				inquiryActionRequest.getFirstAction().getDevices().addAll(gasResponse.getResultsList());
				break;

			case HAN_DEVICE:

				InternalResultsResponse<HanDevice> hanResponse = getElectricMeterBCL().fetchAllHanDevices(
						new InquiryDeviceRequest(inquiryActionRequest.getSelectionPaginationIds(),
								inquiryActionRequest.getPaginationAllSelected(), inquiryActionRequest.getUserContext(),
								new SortExpression(FLEXNET_ID, Direction.Ascending),
								inquiryActionRequest.getDeviceSearch(), inquiryActionRequest.getTenant()));

				if (hanResponse.isInError() || !hanResponse.hasResults())
				{
					return;
				}

				inquiryActionRequest.getFirstAction().setDevices(new ArrayList<Device>());
				inquiryActionRequest.getFirstAction().getDevices().addAll(hanResponse.getResultsList());
				break;

			case LCM:

				InternalResultsResponse<LCM> lcmResponse = getElectricMeterBCL().fetchAllLCM(
						new InquiryDeviceRequest(inquiryActionRequest.getSelectionPaginationIds(),
								inquiryActionRequest.getPaginationAllSelected(), inquiryActionRequest.getUserContext(),
								new SortExpression(FLEXNET_ID, Direction.Ascending),
								inquiryActionRequest.getDeviceSearch(), inquiryActionRequest.getTenant()));

				if (lcmResponse.isInError() || !lcmResponse.hasResults())
				{
					return;
				}

				inquiryActionRequest.getFirstAction().setDevices(new ArrayList<Device>());
				inquiryActionRequest.getFirstAction().getDevices().addAll(lcmResponse.getResultsList());
				break;

			default:
				break;
		}
	}

	/**
	 * Insert device schedule.
	 * 
	 * @param response the response
	 * @param scheduleRequest the schedule request
	 * @param scheduleAction the schedule action
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMSchedule> insertDeviceSchedule(InternalResultsResponse<DMSchedule> response,
			ScheduleRequest scheduleRequest, String scheduleAction)
	{
		// insert only schedule process
		InternalResponse internalResponse =
				insertScheduleProcess(scheduleRequest, scheduleAction, ProcessStatusEnum.COMPLETED, false);
		if (internalResponse.isInError())
		{
			return createErrorResponse(response, internalResponse);
		}

		// check and add devices associated with the schedule whether:
		// - user informed it manually - getIdsFile()
		// - user imported the devices via CSV file - getUploadIds()
		if (!ValidationUtil.isNull(scheduleRequest.getUploadIds())
				|| !ValidationUtil.isNull(scheduleRequest.getIdsFile()))
		{
			internalResponse = insertScheduleDevices(scheduleRequest);
			if (internalResponse.isInError())
			{
				return createErrorResponse(response, internalResponse);
			}
		}

		return response;
	}

	/**
	 * Insert schedule process.
	 * 
	 * @param scheduleRequest the schedule request
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param monitored the monitored
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMProcess> insertScheduleProcess(ScheduleRequest scheduleRequest,
			String actionType, ProcessStatusEnum processStatusEnum, Boolean monitored)
	{

		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.SCHEDULE_ID.getValue(), scheduleRequest.getSchedule()
				.getId().toString()));
		properties.add(new Property(PropertyEnum.SCHEDULE_NAME.getValue(), scheduleRequest.getSchedule()
				.getName()));

		return getProcessBCL().insertProcess(
				new ProcessRequest(DMUtil.generateProcess(
						monitored, true, null,
						new ProcessType(actionType),
						null, processStatusEnum, properties), scheduleRequest.getUserContext(), scheduleRequest
						.getServiceTypeEnum(), scheduleRequest.getTenant()));

	}

	/**
	 * Creates the schedule properties.
	 * 
	 * @param schedule the schedule
	 * @return the list
	 */
	private List<Property> createScheduleProperties(DMSchedule schedule)
	{
		// prepare properties
		List<Property> properties = new ArrayList<Property>();

		if (!ValidationUtil.isNull(schedule.getDmAction().getIsMonitored()))
		{
			properties.add(new Property(PropertyEnum.IS_MONITORED.getValue()
					, schedule.getDmAction().getIsMonitored().toString()));
		}

		// used when creating schedule and when creating process
		DMConvertUtil.convertActionToProperty(schedule.getDmAction(), properties);

		DMConvertUtil.convertFrequencyToProperty(schedule.getFrequency(), properties);

		// should be only verified when creating schedule (we can not put inside DMConvertUtil.convertActionToProperty
		// at this point because those params are inserted only when async message is received).
		switch (schedule.getDmAction().getActionType().getActionTypeEnum())
		{
			case GET_TAMPER_DETECT_TIMER:
				DMConvertUtil.convertTamperTimerToProperty(
						((GetTamperDetectTimerAction)schedule.getDmAction()).getLcmRelays(), properties);
				break;

			case GET_DEMAND_RESPONSE_SETUP_STATUS:

				// the enrollment code is null when set up Demand Response for an Entek LCM
				// from within the detail page and separate relays are chosen
				// in that case, the enrollment code is set within each LCMRelay
				if (!ValidationUtil.isNullOrEmpty(((GetDemandResponseSetupStatusAction)schedule.getDmAction())
						.getLcmRelays()))
				{
					DMConvertUtil.convertLCMRelayToProperty(
							((GetDemandResponseSetupStatusAction)schedule.getDmAction()).getLcmRelays(), properties);
				}
				else
				{
					DMConvertUtil.convertDemandResponseSetupToProperty(
							(GetDemandResponseSetupStatusAction)schedule.getDmAction(), properties);
				}
				break;

			case GET_DEMAND_RESPONSE_EVENT_STATUS:
				if (!ValidationUtil.isNullOrZero(schedule.getDmAction().getProcessId()))
				{
					properties.add(new Property(PropertyEnum.PARENT_PROCESS.getValue(), schedule.getDmAction()
							.getProcessId().toString()));
				}
				break;

			default:
				break;
		}

		return properties;
	}

	/**
	 * Creates the device file.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	private InternalResponse createDeviceFile(ScheduleRequest scheduleRequest)
	{

		InternalResponse response = new InternalResponse();

		String fileName =
				scheduleRequest.getIdFileType().getValue() + "-"
						+ scheduleRequest.getSchedule().getId()
						+ ".csv";

		if (!ValidationUtil.isNull(scheduleRequest.getIdsFile())
				&& !ValidationUtil.isNull(scheduleRequest.getIdFileType()))
		{
			// Open the uploaded file
			File file = new File(getTempUploadFilePath() + fileName);

			DMUtil.copyFile(scheduleRequest.getIdsFile(), file);

			scheduleRequest.setFileName(file.getName());

		}
		else if (!ValidationUtil.isNull(scheduleRequest.getUploadIds())
				&& !ValidationUtil.isNull(scheduleRequest.getIdFileType()))
		{
			String[] uploadIds = scheduleRequest.getUploadIds().split(",");

			// Create a file adding the device ids
			if (!DMUtil.createFile(Arrays.asList(uploadIds), getTempUploadFilePath() + fileName))
			{
				response.setStatus(Status.ExceptionError);
				response.addMessage(FILE_CREATION_FAILED, MessageSeverity.Error, MessageLevel.Other);
				return response;
			}
			scheduleRequest.setFileName(fileName);
		}

		return response;
	}

	/**
	 * Update schedule properties.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	private InternalResponse updateScheduleProperties(ScheduleRequest scheduleRequest)
	{
		// delete all properties from schedule
		InternalResponse response = getScheduleDAC().deleteAllScheduleProperty(scheduleRequest);
		if (response.isInError())
		{
			return response;
		}

		// insert again the new properties
		// return insertScheduleProperties(scheduleRequest);
		scheduleRequest.getSchedule().setProperties(createScheduleProperties(scheduleRequest.getSchedule()));

		// insert schedule properties
		return getScheduleDAC().insertScheduleProperty(scheduleRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param schedule the schedule
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(DMSchedule schedule)
	{
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.SCHEDULE_ID.getValue(), schedule.getId().toString()));

		InternalResponse response = getProcessBCL().fetchCheckProcessing(
				new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));

		if (response.isInError())
		{
			response.addMessage(PROCESSING_SCHEDULE,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation);

		}

		return response;

	}

	/**
	 * Insert schedule devices.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the internal response
	 */
	private InternalResponse insertScheduleDevices(ScheduleRequest scheduleRequest)
	{

		// insert in_process process (add device to schedule)
		InternalResultsResponse<DMProcess> processResponse =
				insertScheduleProcess(scheduleRequest, AddDeviceToScheduleAction.ACTION,
						ProcessStatusEnum.IN_PROCESS, scheduleRequest.getIsMonitored());
		if (processResponse.isInError())
		{
			return processResponse;
		}

		scheduleRequest.setProcessId(processResponse.getFirstResult().getId());

		// create one device file on server (when user sets devices ids)
		// copy the device file on server (when user sets a CSV file)
		return createDeviceFile(scheduleRequest);

	}

	/**
	 * Creates the error response.
	 * 
	 * @param response the response
	 * @param internalResponse the internal response
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMSchedule> createErrorResponse(InternalResultsResponse<DMSchedule> response,
			InternalResponse internalResponse)
	{
		response.setStatus(internalResponse.getStatus());
		response.addMessages(internalResponse.getMessageInfoList());
		return response;
	}

	/**
	 * Gets the device list schedule.
	 * 
	 * @param schedule the schedule
	 * @return the device list schedule
	 */
	private List<Device> getDeviceListSchedule(DMSchedule schedule)
	{
		List<Device> deviceList = new ArrayList<Device>();

		if (ValidationUtil.isNull(schedule) || ValidationUtil.isNull(schedule.getDmAction()))
		{
			return deviceList;
		}

		if (!ValidationUtil.isNull(schedule.getDmAction().getFirstGroup()))
		{
			for (Group group : schedule.getDmAction().getGroups())
			{
				deviceList.addAll(group.getDevices());
			}

			return deviceList;
		}
		else if (!ValidationUtil.isNull(schedule.getDmAction().getFirstTag()))
		{
			for (Tag tag : schedule.getDmAction().getTags())
			{
				deviceList.addAll(tag.getDevices());
			}

			return deviceList;
		}

		return schedule.getDmAction().getDevices();
	}
}
