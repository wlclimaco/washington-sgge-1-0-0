package com.sensus.dm.elec.action.bcl.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.scheduler.processor.ActionProcessor;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tag.bcl.ITagBCL;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.action.bcl.IActionBCL;
import com.sensus.dm.elec.action.dac.IActionDAC;
import com.sensus.dm.elec.action.model.CancelDemandResponseEventAction;
import com.sensus.dm.elec.action.model.CancelSendHanTextMessageAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.HanDeviceConfiguration;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;

/**
 * The Class ActionBCLImpl.
 * 
 * @author QAT Global.
 * 
 */
public class ActionBCLImpl implements IActionBCL
{

	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Log LOG = LogFactory.getLog(ActionBCLImpl.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant COMMA. */
	private static final String COMMA = ",";

	/** The Constant PIPE_DELIMITER. */
	private static final String PIPE_DELIMITER = "|";

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant EMPTY_METER. */
	private static final String EMPTY_METER = "Empty Meter";

	/** The Constant INVALID_FLEXNET_ID. */
	private static final String INVALID_FLEXNET_ID = "Invalid FlexNet ID";

	/** The Constant REPEATED. */
	private static final String REPEATED = "Frequencyed";

	// -------------------------------------------------------------------------
	// i18n messages.

	/** The Constant CSV. */
	private static final String CSV = ".csv";

	// -------------------------------------------------------------------------
	// i18n messages. unreachable

	/** The Constant FAILED_TO_CREATE_UNREACHABLE_IDS_FILE. */
	private static final String FAILED_TO_CREATE_UNREACHABLE_IDS_FILE =
			"sensus.epm.actionbclimpl.unreachable.ids.file.creation.failed";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	/** The Constant SENSUS_DM_IMPORT_FILE_EMPTY. */
	private static final String SENSUS_DM_IMPORT_FILE_EMPTY = "sensus.epm.actionvalidator.import.file.empty";

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant FOUR. */
	private static final Integer FOUR = 4;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The Constant SIX. */
	private static final Integer SIX = 6;

	/** The Constant MACADDRESS. */
	private static final String MACADDRESS = "macAddress";

	/** The Constant METER_ID. */
	private static final String METER_ID = "meterId";

	/** The Constant MANUFACTURER. */
	private static final String MANUFACTURER = "manufacturer";

	/** The Constant MODEL. */
	private static final String MODEL = "model";

	/** The Constant INSTALLCODE. */
	private static final String INSTALLCODE = "installCode";

	/** The Constant HANDEVICE_ID. */
	private static final String HANDEVICE_ID = "hanDeviceId";

	/** The Constant PARAMS_PER_LINE. */
	private static final String PARAMS_PER_LINE = "sensus.epm.actionvalidator.import.file.error.params.should.be.six";

	/** The Constant MACADDRESS_INVALID. */
	private static final String MACADDRESS_INVALID = "sensus.epm.actionvalidator.import.file.error.macaddress.invalid";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp upload file path. */
	private String tempUploadFilePath;

	/** The action dac. */
	private IActionDAC actionDAC;

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL;

	/** The device bcl. */
	private IDeviceBCL deviceBCL;

	/** The water meter bcl. */
	private IWaterMeterBCL waterMeterBCL;

	/** The gas meter bcl. */
	private IGasMeterBCL gasMeterBCL;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The tag bcl. */
	private ITagBCL tagBCL;

	/** The action processor. */
	private ActionProcessor actionProcessor;

	/**
	 * Gets the temp upload file path.
	 * 
	 * @return the temp upload file path
	 */
	public String getTempUploadFilePath()
	{
		return tempUploadFilePath;
	}

	/**
	 * Sets the temp upload file path.
	 * 
	 * @param tempUploadFilePath the new temp upload file path
	 */
	public void setTempUploadFilePath(String tempUploadFilePath)
	{
		this.tempUploadFilePath = tempUploadFilePath;
	}

	/**
	 * Gets the action dac.
	 * 
	 * @return the action dac
	 */
	public IActionDAC getActionDAC()
	{
		return actionDAC;
	}

	/**
	 * Sets the action dac.
	 * 
	 * @param actionDAC the new action dac
	 */
	public void setActionDAC(IActionDAC actionDAC)
	{
		this.actionDAC = actionDAC;
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
	 * Gets the tag bcl.
	 * 
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 * 
	 * @param tagBCL the new tag bcl
	 */
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
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
	 * Gets the device bcl.
	 * 
	 * @return the device bcl
	 */
	public IDeviceBCL getDeviceBCL()
	{
		return deviceBCL;
	}

	/**
	 * Sets the device bcl.
	 * 
	 * @param deviceBCL the new device bcl
	 */
	public void setDeviceBCL(IDeviceBCL deviceBCL)
	{
		this.deviceBCL = deviceBCL;
	}

	/**
	 * Gets the action processor.
	 * 
	 * @return the action processor
	 */
	public ActionProcessor getActionProcessor()
	{
		return actionProcessor;
	}

	/**
	 * Sets the action processor.
	 * 
	 * @param actionProcessor the new action processor
	 */
	public void setActionProcessor(ActionProcessor actionProcessor)
	{
		this.actionProcessor = actionProcessor;
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
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#insertDeviceFromFileToAction(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public InternalResponse insertDeviceFromFileToAction(ActionRequest actionRequest)
	{
		// gets the action
		InternalResultsResponse<DMAction> actionResponse = fetchActionById(actionRequest);
		if (actionResponse.isInError() || !actionResponse.hasResults())
		{
			return actionResponse;
		}

		DMAction action = actionResponse.getFirstResult();

		// fills the unreachable list
		// leaves only good devices on action.getDevices()
		action.setDevices(checkActionDevices(actionRequest));

		// Creating a list with base properties
		List<Property> properties = new ArrayList<Property>();

		InternalResponse internalResponse = checkUnreachableIds(actionRequest, properties);
		if (internalResponse.isInError())
		{
			return internalResponse;
		}

		// if there are devices on the list (after checkActionDevices), insert them
		if (!ValidationUtil.isNullOrEmpty(action.getDevices()))
		{
			// inserts the devices to the action
			actionRequest.setAction(action);
			InternalResponse response = insertDevicesToAction(actionRequest);
			if (response.isInError())
			{
				return response;
			}

			// inserts the process items
			internalResponse =
					getProcessBCL().insertProcessItems(
							new ProcessRequest(new DMProcess(actionRequest.getProcessId(), DMUtil
									.generateProcessItems(actionRequest.getAction().getDevices(),
											ProcessItemStatusEnum.COMPLETED)),
									actionRequest.getUserContext()));

			if (internalResponse.isInError())
			{
				return internalResponse;
			}

		}

		// remove the file
		DMUtil.deleteFile(getTempUploadFilePath() + actionRequest.getFileName());

		// update process of edit schedule as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(actionRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED, properties),
						actionRequest.getUserContext()));

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#insertAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResultsResponse<DMAction> insertAction(ActionRequest actionRequest)
	{

		// inserts the action
		InternalResultsResponse<DMAction> response = getActionDAC().insertAction(actionRequest);
		if (response.isInError())
		{
			return response;
		}

		actionRequest.getAction().setId(response.getFirstResult().getId());

		// insert groups to action
		if (!ValidationUtil.isNullOrEmpty(actionRequest.getAction().getGroups())
				|| !ValidationUtil.isNullOrEmpty(actionRequest.getAction().getTags()))
		{
			InternalResponse internalResponse = insertGroupSetToAction(actionRequest);
			if (internalResponse.isInError())
			{
				response.addMessages(internalResponse.getMessageInfoList());
				response.setStatus(internalResponse.getStatus());
				return response;
			}
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#updateAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResponse updateAction(ActionRequest actionRequest)
	{
		// update groups (will delete and insert again)
		InternalResponse response = updateGroupSet(actionRequest);
		if (response.isInError())
		{
			return response;
		}

		// delete all devices from action
		return deleteDevicesFromAction(actionRequest);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#insertGroupsToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertGroupSetToAction(ActionRequest actionRequest)
	{
		if (!ValidationUtil.isNullOrEmpty(actionRequest.getAction().getGroups()))
		{
			return getActionDAC().insertGroupsToAction(actionRequest);
		}

		return getActionDAC().insertTagsToAction(actionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#insertDevicesToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertDevicesToAction(ActionRequest actionRequest)
	{
		return getActionDAC().insertDevicesToAction(actionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#applyDeviceToAction(com.sensus.dm.elec.action.model.request.
	 * InquiryActionRequest)
	 */
	@Override
	public InternalResponse applyDeviceToAction(InquiryActionRequest inquiryActionRequest)
	{
		// if this action is on Demand we have to create the action (action_instance table)
		// and the relation between action and endpoint (action_instance_endpoint)
		// otherwise this method was called from schedule processor, then it is necessary to create only the process

		DMAction action = inquiryActionRequest.getFirstAction();
		UserContext uc = inquiryActionRequest.getUserContext();

		// set user id into the action (change it further so FE apply it)
		action.setCreateUser(uc.getUserId());

		// if it is a GET actions, Retry or Cancel:
		// - do not create new process
		// - do not fetch any device (will be used process id further)
		if (!action.isProcessRequired())
		{
			getActionProcessor().setAction(action);
			getActionProcessor().execute();

			return new InternalResponse();
		}

		// create the process
		InternalResultsResponse<DMProcess> response =
				insertActionProcess(new ActionRequest(action, uc, inquiryActionRequest.getUnreachableIds(),
						inquiryActionRequest.getIdFileType(), inquiryActionRequest.getServiceTypeEnum(),
						inquiryActionRequest.getProcessId(), inquiryActionRequest.getTenant(),
						inquiryActionRequest.getFileName(), inquiryActionRequest.getScheduleName()));
		if (response.isInError())
		{
			return response;
		}

		action.setProcessId(response.getFirstResult().getId());
		action.setRniEventId(response.getFirstResult().getRniEventId());

		getActionProcessor().setAction(action);
		getActionProcessor().execute();

		return new InternalResponse();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#deleteGroupsFromAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse deleteGroupsFromAction(ActionRequest actionRequest)
	{
		return getActionDAC().deleteGroupsFromAction(actionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#deleteDevicesFromAction(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse deleteDevicesFromAction(ActionRequest actionRequest)
	{
		return getActionDAC().deleteDevicesFromAction(actionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#fetchActionById(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResultsResponse<DMAction> fetchActionById(ActionRequest actionRequest)
	{
		return getActionDAC().fetchActionById(actionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#importHanDeviceFromAction(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse importHanDeviceFromAction(ActionRequest actionRequest)
	{
		InquiryActionRequest inquiryActionRequest =
				new InquiryActionRequest(actionRequest.getAction(), actionRequest.getUserContext(),
						actionRequest.getServiceTypeEnum(), actionRequest.getTenant());

		InternalResponse response = verifyHanDeviceFile(actionRequest, inquiryActionRequest);

		if (response.isInError())
		{
			return response;
		}

		// validates customerId action to import han
		validateCustomerId(actionRequest.getAction().getDevices(), inquiryActionRequest.getTenant().getKey());

		if (!actionRequest.getAction().isProcessRequired())
		{
			actionRequest.getAction().setProcessId(actionRequest.getProcessId());
			return applyDeviceToAction(inquiryActionRequest);
		}

		// creates the action to OnDemand actions
		// when applyDeviceToAction is called from a schedule, do not create the action,
		// once it was created by schedule itself
		InternalResultsResponse<DMAction> insertActionResponse = insertAction(actionRequest);
		if (insertActionResponse.isInError())
		{
			return insertActionResponse;
		}

		// add devices to the action
		response = insertDevicesToAction(actionRequest);
		if (response.isInError())
		{
			return response;
		}

		// import han devices, calling Sensus BCF methods
		response = applyDeviceToAction(inquiryActionRequest);

		// if imported correctly and there is tag to associate
		if (!response.isInError() && !ValidationUtil.isNullOrEmpty(actionRequest.getTags()))
		{
			return addTagToImportedDevices(actionRequest);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#connectDisconnectActionOnDemand(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public InternalResponse connectDisconnectActionOnDemand(ActionRequest actionRequest)
	{
		InquiryActionRequest inquiryActionRequest =
				new InquiryActionRequest(actionRequest.getAction(), actionRequest.getUserContext(),
						actionRequest.getServiceTypeEnum(), actionRequest.getTenant());
		// creates the action to OnDemand actions
		// when applyDeviceToAction is called from a schedule, do not create the action,
		// once it was created by schedule itself
		InternalResultsResponse<DMAction> insertActionResponse = insertAction(actionRequest);
		if (insertActionResponse.isInError())
		{
			return insertActionResponse;
		}

		// add devices to the action
		InternalResponse response = insertDevicesToAction(actionRequest);
		if (response.isInError())
		{
			return response;
		}

		// connect/Disconnect han devices, calling Sensus BCF methods
		return applyDeviceToAction(inquiryActionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#applyActionOnDemand(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse applyActionOnDemand(ActionRequest actionRequest)
	{
		if (ActionTypeEnum.IMPORT_HAN_DEVICE.equals(actionRequest.getAction().getActionType().getActionTypeEnum())
				|| ActionTypeEnum.RETRY_IMPORT_HAN_DEVICE.equals(actionRequest.getAction().getActionType()
						.getActionTypeEnum()))
		{
			return importHanDeviceFromAction(actionRequest);
		}

		return connectDisconnectActionOnDemand(actionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#abortAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResponse abortAction(ActionRequest actionRequest)
	{
		InternalResultsResponse<DMProcess> response = new InternalResultsResponse<DMProcess>();

		if (!checkProcessById(response, actionRequest))
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Process to abort not found. Process will not be aborted. ProcessId: "
						+ actionRequest.getProcessId());
			}

			return response;
		}

		if (!canProcessBeAborted(response.getFirstResult()))
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Processes not in Process, not Started or not Command Sent state can not be aborted. ProcessId: "
						+ actionRequest.getProcessId());
			}

			return response;
		}

		// call applyDeviceToAction if it is canceling initiate demand response or send han text message
		// applyDeviceToAction will call the RNI
		// when the listeners receives the cancel response, it will mark the process and its items as aborted
		if (!ValidationUtil.isNull(response.getFirstResult().getAction())
				&& isRNICancelAction(response.getFirstResult().getAction()))
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Calling RNI to abort process. ProcessId: "
						+ actionRequest.getProcessId());
			}

			return applyDeviceToAction(new InquiryActionRequest(createCancelAction(response.getFirstResult(),
					actionRequest), actionRequest.getUserContext(),
					actionRequest.getProcessId(), actionRequest.getTenant()));
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Aborting process without RNI calling. ProcessId: "
					+ actionRequest.getProcessId());
		}

		// updates the process and its items manually if it is not an RNI call
		response.getFirstResult().setProcessStatusEnum(ProcessStatusEnum.ABORTED);

		for (ProcessItem processItem : response.getFirstResult().getProcessItems())
		{
			if (ProcessItemStatusEnum.RUNNING.equals(processItem.getProcessItemStatusEnum()))
			{
				processItem.setProcessItemStatusEnum(ProcessItemStatusEnum.ABORTED);
			}
		}

		return getProcessBCL().updateProcessStatus(
				new ProcessRequest(response.getFirstResult(), actionRequest.getUserContext()));

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#insertDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse insertDevicesOptOutList(ActionRequest actionRequest)
	{
		return getActionDAC().insertDevicesOptOutList(actionRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#deleteDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse deleteDevicesOptOutList(ActionRequest actionRequest)
	{
		return getActionDAC().deleteDevicesOptOutList(actionRequest);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Check process by id.
	 * 
	 * @param response the response
	 * @param actionRequest the action request
	 * @return true, if successful
	 */
	private boolean checkProcessById(InternalResultsResponse<DMProcess> response, ActionRequest actionRequest)
	{
		InternalResultsResponse<DMProcess> internalResultsresponse =
				getProcessBCL().fetchProcessById(
						new ProcessRequest(new DMProcess(actionRequest.getProcessId()), actionRequest
								.getUserContext(), null, actionRequest.getTenant()));

		if (internalResultsresponse.isInError() || !internalResultsresponse.hasResults())
		{
			response.setStatus(internalResultsresponse.getStatus());
			response.addMessages(internalResultsresponse.getMessageInfoList());
			return false;
		}

		response.addResult(internalResultsresponse.getFirstResult());

		return true;
	}

	/**
	 * Validate file lines.
	 * 
	 * @param fileLines the file lines
	 * @return the internal response
	 */
	private InternalResponse validateFileLines(List<String> fileLines)
	{
		if (ValidationUtil.isNullOrEmpty(fileLines))
		{
			return createErrorFileResponse(SENSUS_DM_IMPORT_FILE_EMPTY, null);
		}

		// used to tell if there were only line returns in the file and no actual content
		boolean atLeastOneLineWithParams = false;

		int lineNum = ZERO;

		for (String line : fileLines)
		{

			lineNum++;

			String[] params = line.split(COMMA);

			// this line is blank, so go to the next line
			if (ValidationUtil.isNull(params) || params.length == ZERO
					|| params.length == ONE && params[0].equalsIgnoreCase(EMPTY_STRING))
			{
				continue;
			}

			// this line has some kind of content, so the file is not empty
			atLeastOneLineWithParams = true;

			// if we don`t have exactly 6 parameters then just quit, won`t work
			if (params.length != SIX)
			{
				// these are the 2 params to pass to the error message, line # and the line value
				Object[] args = new Object[2];
				args[0] = lineNum;
				args[1] = line;
				return createErrorFileResponse(PARAMS_PER_LINE, args);
			}

			if (ValidationUtil.isNullOrEmpty(params[0]))
			{
				// these are the 2 params to pass to the error message, line # and the line value
				Object[] args = new Object[2];
				args[0] = lineNum;
				args[1] = line;
				return createErrorFileResponse(MACADDRESS_INVALID, args);
			}

		}

		// if there wasn`t at least one line with content, show empty file error message
		if (!atLeastOneLineWithParams)
		{
			return createErrorFileResponse(SENSUS_DM_IMPORT_FILE_EMPTY, null);
		}
		return new InternalResponse();

	}

	/**
	 * Fill import device list.
	 * 
	 * @param devices the devices
	 * @param unreachableIds the unreachable ids
	 * @param fileLines the file lines
	 * @param customerId the customer id
	 */
	private void fillImportDeviceList(List<Device> devices, List<String> unreachableIds, List<String> fileLines,
			String customerId)
	{
		// column names and positions
		HashMap<String, Integer> fileFormat = new HashMap<String, Integer>(SIX);
		fileFormat.put(MACADDRESS, ZERO);
		fileFormat.put(METER_ID, ONE);
		fileFormat.put(MANUFACTURER, TWO);
		fileFormat.put(MODEL, THREE);
		fileFormat.put(INSTALLCODE, FOUR);
		fileFormat.put(HANDEVICE_ID, FIVE);

		for (String line : fileLines)
		{

			String[] params = line.split(COMMA);

			// this line is blank, so go to the next line
			if (ValidationUtil.isNull(params) || params.length == ZERO
					|| params.length == ONE && params[0].equalsIgnoreCase(EMPTY_STRING))
			{
				continue;
			}

			// macAddresss
			BigInteger flexNetId = DMConvertUtil.convertMacAddress(params[fileFormat.get(MACADDRESS)]);

			// check the conversion of the string 00:07:a6:ff:ff:00:92:5b to a BigInteger
			// if it fails, then save this macAddress as an unreachable Id and quit
			if (ValidationUtil.isNull(flexNetId))
			{
				unreachableIds.add(params[fileFormat.get(MACADDRESS)]);
				continue;
			}

			// model and manufacturer
			String model = params[fileFormat.get(MODEL)];
			String manufacturer = params[fileFormat.get(MANUFACTURER)];
			if (ValidationUtil.isNullOrEmpty(model) || ValidationUtil.isNullOrEmpty(manufacturer))
			{
				unreachableIds.add(String.valueOf(flexNetId));
				continue;
			}

			HanDevice hd = new HanDevice(new Radio(flexNetId, customerId));
			hd.setConfiguration(new HanDeviceConfiguration(params[fileFormat.get(INSTALLCODE)]));
			hd.setDeviceModel(new DeviceModel(manufacturer, model));

			// DeviceId
			String deviceId = params[fileFormat.get(HANDEVICE_ID)];
			if (ValidationUtil.isNullOrEmpty(deviceId))
			{
				unreachableIds.add(String.valueOf(flexNetId));
				continue;
			}
			hd.setDeviceId(deviceId);

			// MeterId
			String meterId = params[fileFormat.get(METER_ID)];
			if (!ValidationUtil.isNullOrEmpty(meterId))
			{
				// fetch the electric meter by meter id
				InternalResultsResponse<Device> response =
						getElectricMeterBCL().fetchDeviceById(
								new DeviceRequest(new Device(meterId, DeviceTypeEnum.ELECTRIC_METER), new DMTenant(
										customerId)));

				// this means that the meter was not found in the db
				// but we should probably still add the Han Device just without connecting it
				if (!response.isInError() && response.hasResults())
				{
					hd.getRadio().addDevice(response.getFirstResult());
				}
			}

			devices.add(hd);

		}

	}

	/**
	 * Adds the tag to imported devices.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	private InternalResponse addTagToImportedDevices(ActionRequest actionRequest)
	{
		InternalResultsResponse<Device> internalResultResponse =
				getDeviceBCL().fetchDeviceByIdImport(
						new DeviceRequest(actionRequest.getAction().getDevices(), actionRequest
								.getServiceTypeEnum(), actionRequest.getTenant()));

		if (internalResultResponse.isInError() || !internalResultResponse.hasResults())
		{
			return internalResultResponse;
		}

		InquiryDeviceRequest inquiryDeviceRequest =
				new InquiryDeviceRequest(Boolean.FALSE, actionRequest.getUserContext(), new SortExpression(
						FLEXNET_ID, Direction.Ascending), internalResultResponse.getResultsList(),
						actionRequest.getServiceTypeEnum(), actionRequest.getTenant());

		for (Tag tag : actionRequest.getTags())
		{
			inquiryDeviceRequest.addTag(tag);
			InternalResponse response = getTagBCL().insertDeviceToTag(inquiryDeviceRequest);
			if (response.isInError())
			{
				return response;
			}
			inquiryDeviceRequest.getTags().clear();
		}

		return new InternalResponse();
	}

	/**
	 * Verify han device file.
	 * 
	 * @param actionRequest the action request
	 * @param inquiryActionRequest the inquiry action request
	 * @return the internal response
	 */
	private InternalResponse verifyHanDeviceFile(ActionRequest actionRequest, InquiryActionRequest inquiryActionRequest)
	{
		/*
		 * POSITIONAL FILE SAMPLE HERE!
		 * MAC ADDRESS ,METER(OPTIONAL) ,MANUFACTURER ,MODEL NUMBER ,INSTALL CODE(OPTIONAL), HAN_DEVICE_ID
		 * 00:07:a6:ff:fG:00:93:53,1001 ,Sensus/HAI ,539 63 537 08004, ,PCT9353
		 * The meter to be imported on pair is optional,
		 * so if you are NOT importing it you must leave the second AND fifth columns empty.
		 * To import several HAN Devices just go to the next line and enter the another row of data.
		 * 00:07:a6:ff:fG:00:93:53,,Sensus/HAI,539 63 537 08004,,PCT9353
		 */
		/*
		 * All lines must have a comma at the end, except the last...
		 * 00:07:a6:ff:ff:00:92:5b,,Sensus/HAI,81A00-1FT,1001M,IHD925B,
		 * 00:07:a6:ff:ff:00:93:53,,Sensus/HAI,539 63 537 08002,,PCT9353,
		 * 00:07:a6:ff:fG:00:93:53,,Sensus/HAI,539 63 537 08002,,PCT9353,
		 * 00:07:a6:ff:ff:00:93:4f,,Sensus/HAI,539 63 537 08002,,PCT934f,
		 * 00:07:a6:ff:ff:00:93:57,,Sensus/HAI,539 63 537 08002,,PCT9357,
		 * 00:07:a6:ff:ff:00:93:65,,Sensus/HAI,81A00-1FT,,IHD9365
		 */

		if (!ValidationUtil.isNull(actionRequest.getHanDevicesFile()))
		{
			DMAction action = actionRequest.getAction();

			action.setDevices(new ArrayList<Device>());
			List<String> unreachableIds = new ArrayList<String>();

			// read the file and creates an array of string (each line of array = each line of file)
			List<String> fileLines = DMUtil.readFileLines(actionRequest.getHanDevicesFile());

			// validate the lines to make sure it matches the import file pattern
			InternalResponse internalResponse = validateFileLines(fileLines);
			if (internalResponse.isInError())
			{
				return internalResponse;
			}

			// fill devices list or unreachableIds list according all lines of file
			fillImportDeviceList(action.getDevices(), unreachableIds, fileLines, actionRequest.getTenant().getKey());

			// return if there is no devices neither unreachable ids
			if (ValidationUtil.isNullOrEmpty(action.getDevices()) && ValidationUtil.isNullOrEmpty(unreachableIds))
			{
				return internalResponse;
			}

			// Devices that could not be converted.
			if (!ValidationUtil.isNullOrEmpty(unreachableIds))
			{
				inquiryActionRequest.setUnreachableIds(unreachableIds);
				inquiryActionRequest.setIdFileType(PropertyEnum.NETWORK_ADDRESS);
			}
		}

		return new InternalResponse();
	}

	/**
	 * Creates the error file response.
	 * 
	 * @param code the code
	 * @param args the args
	 * @return the internal response
	 */
	private InternalResponse createErrorFileResponse(String code, Object[] args)
	{
		InternalResponse response = new InternalResponse();
		response.setStatus(Status.ExceptionError);
		response.getMessageInfoList().add(new MessageInfo(code,
				Message.MessageSeverity.Error,
				Message.MessageLevel.FieldValidation, args));

		return response;
	}

	/**
	 * Can process be aborted.
	 * 
	 * @param process the process
	 * @return true, if successful
	 */
	private boolean canProcessBeAborted(DMProcess process)
	{
		return ProcessStatusEnum.IN_PROCESS.equals(process.getProcessStatusEnum())
				|| ProcessStatusEnum.STARTED.equals(process.getProcessStatusEnum())
				|| ProcessStatusEnum.COMMAND_SENT.equals(process.getProcessStatusEnum());
	}

	/**
	 * Checks if is rNI cancel action.
	 * 
	 * @param action the action
	 * @return true, if is rNI cancel action
	 */
	private boolean isRNICancelAction(DMAction action)
	{
		return action.getActionType().getActionTypeEnum().equals(ActionTypeEnum.INITIATE_DEMAND_RESPONSE_EVENT)
				|| action.getActionType().getActionTypeEnum().equals(ActionTypeEnum.SEND_HAN_TEXT_MESSAGE);
	}

	/**
	 * Creates the cancel action.
	 * 
	 * @param process the process
	 * @param request the request
	 * @return the ePM action view
	 */
	private DMAction createCancelAction(DMProcess process, ActionRequest request)
	{
		switch (process.getAction().getActionType().getActionTypeEnum())
		{
			case SEND_HAN_TEXT_MESSAGE:
				return new CancelSendHanTextMessageAction(request.getUserContext().getUserId(), Boolean.FALSE,
						Boolean.FALSE, request.getProcessId(), process.getRniEventId(), checkDevice(process,
								ProcessItemStatusEnum.RUNNING));
			case INITIATE_DEMAND_RESPONSE_EVENT:
				return new CancelDemandResponseEventAction(request.getUserContext().getUserId(), Boolean.FALSE,
						Boolean.FALSE, request.getProcessId(), process.getRniEventId(), checkDevice(process,
								ProcessItemStatusEnum.RUNNING));
			default:
				return null;
		}

	}

	/**
	 * Apply device to action.
	 * 
	 * @param process the process
	 * @param processItemStatus the process item status
	 * @return the list
	 */
	private List<Device> checkDevice(DMProcess process, ProcessItemStatusEnum processItemStatus)
	{
		List<Device> devices = new ArrayList<Device>();

		for (ProcessItem processItem : process.getProcessItems())
		{
			if (processItemStatus.equals(processItem.getProcessItemStatusEnum()))
			{
				devices.add(processItem.getDevice());
			}
		}

		return devices;
	}

	/**
	 * Check action devices.
	 * 
	 * @param actionRequest the action request
	 * @return the list
	 */
	private List<Device> checkActionDevices(ActionRequest actionRequest)
	{

		String meterIds = DMUtil.readFile(getTempUploadFilePath() + actionRequest.getFileName());
		if (ValidationUtil.isNullOrEmpty(meterIds))
		{
			return null;
		}

		actionRequest.setUnreachableIds(new ArrayList<String>());

		PropertyEnum idType = actionRequest.getIdFileType();

		// rolls the ids list to find unreachable
		List<Device> devices = new ArrayList<Device>();

		for (String id : meterIds.split(COMMA))
		{
			id = id.trim();

			String invalidFlexNetIdMsg =
					new StringBuilder(id).append(PIPE_DELIMITER).append(INVALID_FLEXNET_ID).toString();

			if (ValidationUtil.isNullOrEmpty(id))
			{
				actionRequest.getUnreachableIds().add(new StringBuilder(id).append(EMPTY_METER).toString());
				continue;
			}
			else if (!PropertyEnum.DEVICE_ID.equals(idType) && !PropertyEnum.NETWORK_ADDRESS.equals(idType))
			{
				actionRequest.getUnreachableIds().add(invalidFlexNetIdMsg);
				continue;
			}

			try
			{
				Device device = new Device();

				switch (idType)
				{
					case NETWORK_ADDRESS:
						BigInteger flexNetId = DMConvertUtil.convertMacAddress(id);
						if (flexNetId != null)
						{
							device.setRadio(new Radio(flexNetId));
						}

						else
						{
							device.setRadio(new Radio(new BigInteger(id)));
						}
						break;

					case DEVICE_ID:
						device.setDeviceId(id);
						break;

					default:
						break;

				}

				InternalResultsResponse<Device> deviceResponse = getDeviceBCL().fetchDeviceByIdImport(
						new DeviceRequest(device, actionRequest.getServiceTypeEnum(), actionRequest.getTenant()));

				if (deviceResponse.isInError() || ValidationUtil.isNullOrEmpty(deviceResponse.getResultsList()))
				{
					actionRequest.getUnreachableIds().add(invalidFlexNetIdMsg);
					continue;
				}

				if (!DMUtil.containsFlexNetIDOnDeviceList(deviceResponse.getFirstResult().getRadio().getFlexNetId(),
						devices))
				{
					devices.add(deviceResponse.getFirstResult());
				}
				else
				{
					actionRequest.getUnreachableIds().add(
							new StringBuilder(id).append(PIPE_DELIMITER).append(REPEATED).toString());
				}
			}
			catch (NumberFormatException e)
			{
				actionRequest.getUnreachableIds().add(invalidFlexNetIdMsg);
			}
		}

		return devices;

	}

	/**
	 * Apply process properties.
	 * 
	 * @param actionRequest the action request
	 * @return the list
	 */
	private List<Property> applyProcessProperties(ActionRequest actionRequest)
	{
		List<Property> properties = new ArrayList<Property>();

		// default properties, used in each and every action
		properties.add(new Property(PropertyEnum.ACTION_ID.getValue(), actionRequest.getAction().getId().toString()));

		if (!ValidationUtil.isNullOrEmpty(actionRequest.getScheduleName())
				&& (ValidationUtil.isNull(actionRequest.getAction().isOnDemand()) || !actionRequest.getAction()
						.isOnDemand()))
		{
			properties.add(new Property(PropertyEnum.ACTION_NAME.getValue(), actionRequest.getScheduleName()));
		}
		else
		{
			properties.add(new Property(PropertyEnum.ACTION_NAME.getValue(), actionRequest.getAction()
					.getActionType().getActionTypeEnum()
					.getActionTypeName()));
		}

		DMConvertUtil.convertActionToProperty(actionRequest.getAction(), properties);

		return properties;
	}

	/**
	 * Insert action process.
	 * 
	 * @param actionRequest the action request
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMProcess> insertActionProcess(ActionRequest actionRequest)
	{
		// create the process
		DMProcess process =
				DMUtil.generateProcess(actionRequest.getAction().getIsMonitored(), true,
						actionRequest.getAction(), new ProcessType(actionRequest.getAction().getActionType()
								.getActionTypeEnum().getActionTypeName())
						, null, ProcessStatusEnum.IN_PROCESS,
						applyProcessProperties(actionRequest));

		process.setRniEventId(DMUtil.generateRNIEventId());

		// if there is no startTime to the action (came from schedule), then apply process start time on it
		if (!ValidationUtil.isNull(actionRequest.getAction().getStartTime()))
		{
			process.setStartTime(createProcessStartTime(actionRequest.getAction().getStartTime(),
					process.getStartTime()));
		}

		// check unreachableIds
		// the file containing the unreachableIds has to be created after the successful IDs are
		// inserted so that the filename for the unreachableIds can use the same process ID
		InternalResponse internalResponse = checkUnreachableIds(actionRequest, process.getProperties());
		if (internalResponse.isInError())
		{
			InternalResultsResponse<DMProcess> processResponse = new InternalResultsResponse<DMProcess>();
			processResponse.setStatus(internalResponse.getStatus());
			processResponse.addMessages(internalResponse.getMessageInfoList());
			return processResponse;
		}

		return getProcessBCL().insertProcess(
				new ProcessRequest(process, actionRequest.getUserContext(), actionRequest.getServiceTypeEnum(),
						actionRequest.getTenant()));

	}

	/**
	 * Creates the process start time.
	 * 
	 * @param actionStartTime the action start time
	 * @param processStartTime the process start time
	 * @return the date
	 */
	private Date createProcessStartTime(Date actionStartTime, Date processStartTime)
	{
		Calendar calSchedule = Calendar.getInstance();
		calSchedule.setTime(actionStartTime);

		Calendar calProcess = Calendar.getInstance();
		calProcess.setTime(processStartTime);
		calProcess.set(Calendar.HOUR, calSchedule.get(Calendar.HOUR));
		calProcess.set(Calendar.MINUTE, calSchedule.get(Calendar.MINUTE));
		calProcess.set(Calendar.SECOND, 00);
		calProcess.set(Calendar.MILLISECOND, 0);

		return calProcess.getTime();

	}

	/**
	 * Update groups.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	private InternalResponse updateGroupSet(ActionRequest actionRequest)
	{
		// delete the old groups and insert the new ones

		// delete all group from action
		InternalResponse response = deleteGroupsFromAction(actionRequest);
		if (response.isInError())
		{
			return response;
		}

		if (!ValidationUtil.isNullOrEmpty(actionRequest.getAction().getGroups())
				|| !ValidationUtil.isNullOrEmpty(actionRequest.getAction().getTags()))
		{
			// insert group or tag to action
			return insertGroupSetToAction(actionRequest);
		}

		return response;

	}

	/**
	 * Check unreachable ids.
	 * 
	 * @param actionRequest the action request
	 * @param properties the properties
	 * @return the internal response
	 */
	private InternalResponse checkUnreachableIds(ActionRequest actionRequest, List<Property> properties)
	{

		if (!ValidationUtil.isNullOrEmpty(actionRequest.getUnreachableIds()))
		{
			properties
					.add(new Property(PropertyEnum.FILE_IDS_TYPE.getValue(), actionRequest.getIdFileType().toString()));
			properties.add(new Property(PropertyEnum.FILE_PATH.getValue(), getTempUploadFilePath()));

			InternalResponse response = createUnreachableIdsFile(actionRequest);
			if (response.isInError())
			{
				return response;
			}

			// adding property related unreachable
			properties.add(new Property(PropertyEnum.UNREACHABLE_IDS.getValue(), String.valueOf(actionRequest
					.getUnreachableIds().size())));
		}

		return new InternalResponse();
	}

	/**
	 * Creates the unreachable ids file.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	private InternalResponse createUnreachableIdsFile(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();

		StringBuilder strBuilder =
				new StringBuilder(getTempUploadFilePath()).append(actionRequest.getProcessId()).append(CSV);

		if (!DMUtil.createFile(actionRequest.getUnreachableIds(), strBuilder.toString()))
		{
			response.setStatus(Status.ExceptionError);
			response.addMessage(FAILED_TO_CREATE_UNREACHABLE_IDS_FILE, MessageSeverity.Error,
					MessageLevel.Other);
		}

		return response;
	}

	/**
	 * Validate customer id.
	 * 
	 * @param devices the devices
	 * @param customerId the customer id
	 */
	private void validateCustomerId(List<Device> devices, String customerId)
	{
		if (!ValidationUtil.isNullOrEmpty(devices))
		{
			for (Device device : devices)
			{
				if (!ValidationUtil.isNull(device.getRadio())
						&& ValidationUtil.isNullOrEmpty(device.getRadio().getCustomerId()))
				{
					device.getRadio().setCustomerId(customerId);
				}
			}
		}
	}

}
