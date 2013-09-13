package com.sensus.dm.common.group.bcl.impl;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.AddDeviceToGroupAction;
import com.sensus.dm.common.action.model.CreateGroupAction;
import com.sensus.dm.common.action.model.DeleteDeviceFromGroupAction;
import com.sensus.dm.common.action.model.EditGroupAction;
import com.sensus.dm.common.action.model.RemoveGroupAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.base.util.csv.model.CsvColumnEnum;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.bcl.IGroupBCL;
import com.sensus.dm.common.group.dac.IGroupDAC;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.ProcessType;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.action.bcl.IActionBCL;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.HanDeviceSearch;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;

/**
 * The Class GroupBCLImpl.
 * 
 * @author QAT Brazil.
 */
public class GroupBCLImpl implements IGroupBCL
{

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(GroupBCLImpl.class);

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	/** The Constant STR_CSV. */
	private static final String STR_CSV = ".csv";

	/** The Constant STR_HIFEN. */
	private static final String STR_HIFEN = "-";

	/** The Constant STR_COMMA. */
	private static final String STR_COMMA = ",";

	/** The Constant INT_0. */
	private static final Integer INT_0 = 0;

	/** The Constant PROCESS_NAME_SEPARATOR. */
	private static final String PROCESS_NAME_SEPARATOR = "|";

	/** The Constant PROCESSING_GROUP. */
	private static final String PROCESSING_GROUP =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingGroup";

	/** The Constant FILE_CREATION_FAILED. */
	private static final String FILE_CREATION_FAILED = "sensus.epm.processbclimpl.file.creation.failed";

	/** The Constant GROUP_ELECTRIC_COLUMNS_TO_EXPORT. */
	private static final String[] GROUP_ELECTRIC_COLUMNS_TO_EXPORT = {CsvColumnEnum.NAME.getValue(),
			CsvColumnEnum.DESCRIPTION.getValue(), CsvColumnEnum.DEVICE_TYPE.getValue(),
			CsvColumnEnum.GROUP_TYPE.getValue(),
			CsvColumnEnum.DEVICE_COUNT.getValue(), CsvColumnEnum.DATE_ADDED.getValue()};

	/** The Constant GROUP_COLUMNS_TO_EXPORT. */
	private static final String[] GROUP_COLUMNS_TO_EXPORT = {CsvColumnEnum.NAME.getValue(),
			CsvColumnEnum.DESCRIPTION.getValue(), CsvColumnEnum.GROUP_TYPE.getValue(),
			CsvColumnEnum.DEVICE_COUNT.getValue(), CsvColumnEnum.DATE_ADDED.getValue()};

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp upload file path. */
	private String tempUploadFilePath;

	/** The group dac. */
	private IGroupDAC groupDAC;

	/** The action bcl. */
	private IActionBCL actionBCL;

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

	/** The all possible columns. */
	private List<CSVColumn> allPossibleColumns;

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
	 * Gets the group dac.
	 * 
	 * @return the group dac
	 */
	public IGroupDAC getGroupDAC()
	{
		return groupDAC;
	}

	/**
	 * Sets the group dac.
	 * 
	 * @param groupDAC the new group dac
	 */
	public void setGroupDAC(IGroupDAC groupDAC)
	{
		this.groupDAC = groupDAC;
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
	 * Gets the all possible columns.
	 * 
	 * @return the all possible columns
	 */
	public List<CSVColumn> getAllPossibleColumns()
	{
		return allPossibleColumns;
	}

	/**
	 * Sets the all possible columns.
	 * 
	 * @param allPossibleColumns the new all possible columns
	 */
	public void setAllPossibleColumns(List<CSVColumn> allPossibleColumns)
	{
		this.allPossibleColumns = allPossibleColumns;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcl.IGroupBCL#deleteGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Group group : groupRequest.getGroups())
		{

			// Are there something running for this group ?
			response = fetchMessageProcessing(group);
			if (response.isInError())
			{
				return response;
			}

			// the group still exists
			InternalResultsResponse<Group> groupResponse =
					fetchAllGroups(new InquiryGroupRequest(group, groupRequest.getServiceTypeEnum(),
							groupRequest.getTenant()));
			if (groupResponse.isInError())
			{
				return groupResponse;
			}

			// deletes the group
			response = getGroupDAC().deleteGroup(new GroupRequest(group));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del group
			InternalResultsResponse<DMProcess> processResponse =
					insertGroupProcess(groupResponse.getFirstResult(), groupRequest.getIsMonitored(),
							RemoveGroupAction.ACTION, ProcessStatusEnum.COMPLETED,
							null, groupRequest);

			if (processResponse.isInError())
			{
				response.setStatus(processResponse.getStatus());
				response.addMessages(processResponse.getMessageInfoList());
				return response;
			}

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcl.IGroupBCL#insertGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		InternalResultsResponse<Boolean> canInsertResponse = getGroupDAC().fetchCanGroupBeInserted(groupRequest);

		InternalResultsResponse<Group> groupResponse = new InternalResultsResponse<Group>();

		if (ValidationUtil.isNull(canInsertResponse) || !canInsertResponse.getFirstResult())
		{
			groupResponse.setStatus(canInsertResponse.getStatus());
			groupResponse.addMessages(canInsertResponse.getMessageInfoList());
			return groupResponse;
		}

		// insert group
		groupResponse = getGroupDAC().insertGroup(groupRequest);
		if (groupResponse.isInError())
		{
			return groupResponse;
		}

		// insert process for group creation

		InternalResultsResponse<DMProcess> processResponse =
				insertGroupProcess(groupRequest.getFirstGroup(), false,
						CreateGroupAction.ACTION, ProcessStatusEnum.COMPLETED,
						null, groupRequest);

		if (processResponse.isInError())
		{
			groupResponse.setStatus(processResponse.getStatus());
			groupResponse.addMessages(processResponse.getMessageInfoList());
			return groupResponse;
		}

		groupRequest.setProcessId(processResponse.getFirstResult().getId());

		if (!ValidationUtil.isNullOrEmpty(groupRequest.getUploadIds())
				|| !ValidationUtil.isNull(groupRequest.getIdsFile()))
		{
			addDevicesToGroup(groupRequest, groupResponse);
		}

		return groupResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcl.IGroupBCL#updateGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> updateGroup(GroupRequest groupRequest)
	{
		InternalResultsResponse<Group> groupResponse = new InternalResultsResponse<Group>();

		if (!validateUpdateGroup(groupResponse, groupRequest))
		{
			return groupResponse;
		}

		// inserts the group
		groupResponse = getGroupDAC().updateGroup(groupRequest);
		if (groupResponse.isInError())
		{
			return groupResponse;
		}

		// insert process for edit group
		InternalResultsResponse<DMProcess> processResponse =
				insertGroupProcess(groupRequest.getFirstGroup(), false,
						EditGroupAction.ACTION, ProcessStatusEnum.COMPLETED, null, groupRequest);

		if (processResponse.isInError())
		{
			groupResponse.setStatus(processResponse.getStatus());
			groupResponse.addMessages(processResponse.getMessageInfoList());
			return groupResponse;
		}

		if (!ValidationUtil.isNull(groupRequest.isGroupReplace()) && groupRequest.isGroupReplace())
		{
			return updateGroupReplace(groupResponse, processResponse, groupRequest);
		}

		// uploading the devices, in order to add to database further
		if (!ValidationUtil.isNull(groupRequest.getIdsFile())
				&& !ValidationUtil.isNull(groupRequest.getIdFileType())
				&& !ValidationUtil.isNull(groupResponse.getFirstResult())
				|| !ValidationUtil.isNull(groupRequest.getIdFileType())
				&& !ValidationUtil.isNull(groupRequest.getUploadIds()))
		{
			addDevicesToGroup(groupRequest, groupResponse);
		}

		return groupResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#deleteDeviceFromGroup(com.sensus.dm.common.group.model.request.DeviceRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResponse deleteDeviceFromGroup(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InternalResponse response = new InternalResponse();
		new InternalResultsResponse<Device>();

		List<Device> devices = inquiryDeviceRequest.getDevices();

		if (ValidationUtil.isNullOrEmpty(devices))
		{
			switch (inquiryDeviceRequest.getServiceTypeEnum())
			{
			// fetch the list of devices to add to group (according its paginationIds)
				case ELECTRIC:
					devices =
							getElectricMeterBCL().fetchAllDevices(inquiryDeviceRequest).getResultsList();
					break;
				case WATER:

					devices =
							(List<Device>)(List<?>)getWaterMeterBCL().fetchAllWaterMeters(inquiryDeviceRequest)
									.getResultsList();
					break;
				case GAS:
					devices =
							(List<Device>)(List<?>)
							getGasMeterBCL().fetchAllGasMeters(inquiryDeviceRequest).getResultsList();
					break;
				default:
					break;
			}

		}

		// delete the devices from group and generate
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();

		for (Device device : devices)
		{
			response =
					getGroupDAC().deleteDeviceFromGroup(
							new InquiryDeviceRequest(device, inquiryDeviceRequest.getFirstGroup(), inquiryDeviceRequest
									.getUserContext()));

			if (!ValidationUtil.isNullOrEmpty(response.getMessageInfoList()))
			{
				processItems.add(new ProcessItem(device, ProcessItemStatusEnum.FAILED, response
						.getMessageInfoList().get(INT_0).getCode()));
			}
			else
			{
				processItems.add(new ProcessItem(device, ProcessItemStatusEnum.COMPLETED));
			}
		}

		// get group name to create process
		if (ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getFirstGroup().getName()))
		{
			InternalResultsResponse<Group> groupResponse =
					getGroupDAC().fetchAllGroups(
							new InquiryGroupRequest(inquiryDeviceRequest.getFirstGroup(), inquiryDeviceRequest
									.getTenant()));

			if (!groupResponse.isInError() && groupResponse.hasResults())
			{
				inquiryDeviceRequest.getFirstGroup().setName(groupResponse.getFirstResult().getName());
			}
		}

		// insert process for del group
		InternalResultsResponse<DMProcess> processResponse =
				insertGroupProcess(inquiryDeviceRequest.getFirstGroup(), inquiryDeviceRequest.getIsMonitored(),
						DeleteDeviceFromGroupAction.ACTION,
						ProcessStatusEnum.COMPLETED, processItems, inquiryDeviceRequest);

		if (processResponse.isInError())
		{
			return processResponse;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#fetchAllGroups(com.sensus.dm.common.group.model.request.InquiryGroupRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryGroupRequest inquiryGroupRequest)
	{
		return getGroupDAC().fetchAllGroups(inquiryGroupRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#insertDeviceToGroup(com.sensus.dm.common.group.model.request.GroupRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResponse insertDeviceToGroup(InquiryDeviceRequest inquiryDeviceRequest)
	{

		List<Device> devices = inquiryDeviceRequest.getDevices();
		DMProcess process = null;

		List<Property> properties = new ArrayList<Property>();

		// Insert used when user sends a SelectedPaginationIds or PaginationAllSelected
		if (!ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getSelectionPaginationIds())
				|| !ValidationUtil.isNull(inquiryDeviceRequest.getPaginationAllSelected())
				&& inquiryDeviceRequest.getPaginationAllSelected())
		{

			// fetch the list of devices to add to group (according its paginationIds)
			switch (inquiryDeviceRequest.getServiceTypeEnum())
			{
				case ELECTRIC:
					devices = getElectricMeterBCL().fetchAllDevices(inquiryDeviceRequest).getResultsList();
					break;

				case WATER:
					devices =
							(List<Device>)(List<?>)getWaterMeterBCL().fetchAllWaterMeters(inquiryDeviceRequest)
									.getResultsList();
					break;

				case GAS:
					devices = (List<Device>)(List<?>)
							getGasMeterBCL().fetchAllGasMeters(inquiryDeviceRequest).getResultsList();
					break;

				default:
					break;
			}

		}

		else if (!ValidationUtil.isNull(inquiryDeviceRequest.getFirstGroup())
				&& !ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getFileName()))
		{
			// Get devices id from a group. For instance: Create group with sm file upload.

			String filePath = getTempUploadFilePath();
			Integer unreachableIds = inquiryDeviceRequest.getUnreachableIds().size();

			devices = inquiryDeviceRequest.getFirstGroup().getDevices();

			InternalResultsResponse<DMProcess> inquiryProcessResponse =
					getProcessBCL().fetchProcessById(
							new ProcessRequest(new DMProcess(inquiryDeviceRequest.getProcessId()),
									inquiryDeviceRequest.getUserContext()));

			if (!inquiryProcessResponse.isInError())
			{
				String description = inquiryDeviceRequest.getFirstGroup().getId() + PROCESS_NAME_SEPARATOR
						+ inquiryDeviceRequest.getFirstGroup().getName() + PROCESS_NAME_SEPARATOR + filePath
						+ PROCESS_NAME_SEPARATOR + unreachableIds + PROCESS_NAME_SEPARATOR
						+ inquiryDeviceRequest.getIdFileType();

				process = inquiryProcessResponse.getFirstResult();
				process.setDescription(description);

				properties.add(new Property(PropertyEnum.FILE_PATH.getValue(), filePath));
				properties.add(new Property(PropertyEnum.UNREACHABLE_IDS.getValue(), unreachableIds.toString()));
				properties.add(new Property(PropertyEnum.FILE_IDS_TYPE.getValue(), inquiryDeviceRequest
						.getIdFileTypeValue()));

			}

			if (!ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getUnreachableIds()))
			{
				DMUtil.createFile(inquiryDeviceRequest.getUnreachableIds(), getTempUploadFilePath()
						+ inquiryDeviceRequest.getProcessId()
						+ STR_CSV);
			}

		}

		// insert devices to group
		return applyDevicesToGroup(inquiryDeviceRequest, devices, process, properties);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#insertDeviceFromFileToGroup(com.sensus.dm.common.group.model.request.
	 * GroupRequest)
	 */
	@Override
	public InternalResponse insertDeviceFromFileToGroup(GroupRequest groupRequest)
	{
		List<String> unreachableIds = new ArrayList<String>();

		if (ValidationUtil.isNull(groupRequest.getFirstGroup()))
		{
			InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();
			response.setStatus(Status.ExceptionError);
			return response;
		}

		groupRequest.setFileName(getTempUploadFilePath() + groupRequest.getFileName());

		String deviceIds = DMUtil.readFile(groupRequest.getFileName());
		DMUtil.deleteFile(groupRequest.getFileName());

		Group group = checkGroupDevices(groupRequest, unreachableIds, deviceIds);

		if (!ValidationUtil.isNullOrEmpty(group.getDevices()) || !ValidationUtil.isNullOrEmpty(unreachableIds))
		{
			InquiryDeviceRequest deviceRequest = new InquiryDeviceRequest();
			deviceRequest.addGroup(group);
			deviceRequest.setPaginationAllSelected(false);
			deviceRequest.setUserContext(groupRequest.getUserContext());
			deviceRequest.setIsMonitored(groupRequest.getIsMonitored());
			deviceRequest.setUnreachableIds(unreachableIds);
			deviceRequest.setIdFileTypeValue(String.valueOf(groupRequest.getIdFileType()));
			deviceRequest.setFileName(groupRequest.getFileName());
			deviceRequest.setProcessId(groupRequest.getProcessId());

			return insertDeviceToGroup(deviceRequest);

		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(groupRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), groupRequest.getUserContext()));

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#fetchGroupsByDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Group> fetchGroupsByDevice(DeviceRequest deviceRequest)
	{
		return getGroupDAC().fetchGroupsByDevice(deviceRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#generateFileCSV(com.sensus.dm.common.group.model.request.InquiryGroupRequest
	 * )
	 */
	@Override
	public InternalResponse generateGroupsCSV(InquiryGroupRequest inquiryGroupRequest)
	{
		// Get groups and generate file csv with this information
		InternalResultsResponse<Group> responseAllGroups = fetchAllGroups(inquiryGroupRequest);

		if (responseAllGroups.isInError())
		{
			return responseAllGroups;
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Get all groups filtered finished");
		}

		if (ServiceTypeEnum.ELECTRIC.equals(inquiryGroupRequest.getServiceTypeEnum()))
		{
			DMUtil.generateCSV(getAllPossibleColumns(), GROUP_ELECTRIC_COLUMNS_TO_EXPORT,
					responseAllGroups.getResultsList(), inquiryGroupRequest, responseAllGroups);
		}
		else
		{
			DMUtil.generateCSV(getAllPossibleColumns(), GROUP_COLUMNS_TO_EXPORT, responseAllGroups.getResultsList(),
					inquiryGroupRequest, responseAllGroups);
		}

		if (responseAllGroups.isInError())
		{
			return responseAllGroups;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryGroupRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED),
						inquiryGroupRequest.getUserContext(), inquiryGroupRequest.getFileName()));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#fetchDevicesByGroup(com.sensus.dm.common.group.model.request.GroupRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Device> fetchDevicesByGroup(GroupRequest groupRequest)
	{
		return getGroupDAC().fetchDevicesByGroup(groupRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#fetchCanGroupBeInserted(com.sensus.dm.common.group.model.request.
	 * GroupRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Boolean> fetchCanGroupBeInserted(GroupRequest groupRequest)
	{
		return getGroupDAC().fetchCanGroupBeInserted(groupRequest);
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Validate update group.
	 * 
	 * @param groupResponse the group response
	 * @param groupRequest the group request
	 * @return true, if successful
	 */
	private boolean validateUpdateGroup(InternalResultsResponse<Group> groupResponse, GroupRequest groupRequest)
	{
		InternalResultsResponse<Boolean> canInsertResponse = getGroupDAC().fetchCanGroupBeInserted(groupRequest);

		if (ValidationUtil.isNull(canInsertResponse) || !canInsertResponse.getFirstResult())
		{
			groupResponse.setStatus(canInsertResponse.getStatus());
			groupResponse.addMessages(canInsertResponse.getMessageInfoList());
			return false;
		}

		// Are there something running for this group ?
		InternalResponse fetchResponse = fetchMessageProcessing(groupRequest.getFirstGroup());
		if (fetchResponse.isInError())
		{
			groupResponse.setStatus(fetchResponse.getStatus());
			groupResponse.addMessages(fetchResponse.getMessageInfoList());
			return false;
		}

		return true;
	}

	/**
	 * Update group replace.
	 * 
	 * @param groupResponse the group response
	 * @param processResponse the process response
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	private InternalResultsResponse<Group> updateGroupReplace(InternalResultsResponse<Group> groupResponse,
			InternalResultsResponse<DMProcess> processResponse, GroupRequest groupRequest)
	{
		InquiryDeviceRequest inquiryDeviceRequest =
				new InquiryDeviceRequest(new ArrayList<BigInteger>(), false, groupRequest.getUserContext(),
						groupRequest.getFirstGroup(),
						new SortExpression(FLEXNET_ID, Direction.Ascending), groupRequest.getServiceTypeEnum(),
						groupRequest.getTenant());

		InternalResponse responseMeter = new InternalResponse();

		List<Device> devices = fetchDevicesByGroup(groupRequest).getResultsList();

		if (!ValidationUtil.isNullOrEmpty(devices))
		{
			inquiryDeviceRequest.setDevices(devices);
			responseMeter = deleteDeviceFromGroup(inquiryDeviceRequest);
			groupResponse.getMessageInfoList().addAll(responseMeter.getMessageInfoList());
			groupResponse.setStatus(responseMeter.getStatus());

		}

		if (!ValidationUtil.isNull(groupRequest.getIdsFile())
				|| !ValidationUtil.isNull(groupRequest.getUploadIds()))
		{
			return updateGroupByFile(groupResponse, inquiryDeviceRequest, responseMeter, processResponse,
					groupRequest);
		}

		return groupResponse;
	}

	/**
	 * Check group devices.
	 * 
	 * @param groupRequest the group request
	 * @param unreachableIds the unreachable ids
	 * @param meterIds the meter ids
	 * @return the group
	 */
	private Group checkGroupDevices(GroupRequest groupRequest, List<String> unreachableIds, String meterIds)
	{
		PropertyEnum idType = groupRequest.getIdFileType();
		Group group = groupRequest.getFirstGroup();

		InternalResultsResponse<Group> response =
				getGroupDAC().fetchAllGroups(
						new InquiryGroupRequest(group, groupRequest.getServiceTypeEnum(),
								groupRequest.getTenant()));

		InternalResultsResponse<Device> deviceResponse = null;

		if (!response.isInError() && !ValidationUtil.isNullOrEmpty(meterIds)
				&& !ValidationUtil.isNull(response.getFirstResult()))
		{
			String[] ids = meterIds.split(STR_COMMA);
			List<Device> devices = new ArrayList<Device>();

			for (String id : ids)
			{
				id = id.trim();

				if (ValidationUtil.isNullOrEmpty(id))
				{
					unreachableIds.add(id);
					continue;
				}

				if (!PropertyEnum.NETWORK_ADDRESS.equals(idType) && !PropertyEnum.DEVICE_ID.equals(idType))
				{
					unreachableIds.add(id);
					continue;
				}

				try
				{
					Device device = new Device();

					if (PropertyEnum.NETWORK_ADDRESS.equals(idType))
					{
						BigInteger flexNetId = DMConvertUtil.convertMacAddress(id);
						Radio radio = new Radio();
						device.setRadio(radio);
						if (flexNetId != null)
						{
							radio.setFlexNetId(flexNetId);
						}
						else
						{
							radio.setFlexNetId(new BigInteger(id));
						}
					}

					if (PropertyEnum.DEVICE_ID.equals(idType))
					{
						device.setDeviceId(id);
					}

					deviceResponse =
							getDeviceBCL().fetchDeviceByIdImport(
									new DeviceRequest(device, groupRequest.getServiceTypeEnum(), groupRequest
											.getTenant(), new DeviceSearch(group.getDeviceType(), new HanDeviceSearch(
											group.getHanDeviceType()))));

					if (!deviceResponse.isInError() && !ValidationUtil.isNullOrEmpty(deviceResponse.getResultsList()))
					{
						if (!DMUtil.containsFlexNetIDOnDeviceList(deviceResponse.getFirstResult().getRadio()
								.getFlexNetId(),
								devices))
						{
							devices.add(deviceResponse.getFirstResult());
						}
						else
						{
							unreachableIds.add(id);
						}
					}
					else
					{
						unreachableIds.add(id);
					}
				}
				catch (NumberFormatException e)
				{
					unreachableIds.add(id);
				}

			}

			response.getFirstResult().setDevices(devices);
		}

		return response.getFirstResult();
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param group the group
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Group group)
	{
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), group.getId().toString()));

		InternalResponse response = getProcessBCL().fetchCheckProcessing(
				new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));

		if (response.isInError())
		{
			response.addMessage(PROCESSING_GROUP,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation);

		}

		return response;

	}

	/**
	 * Adds the devices to group.
	 * 
	 * @param groupRequest the group request
	 * @param groupResponse the group response
	 */
	private void addDevicesToGroup(GroupRequest groupRequest, InternalResultsResponse<Group> groupResponse)
	{
		// insert process for add devices to group
		InternalResultsResponse<DMProcess> processResponse =
				insertGroupProcess(groupRequest.getFirstGroup(), groupRequest.getIsMonitored(),
						AddDeviceToGroupAction.ACTION, ProcessStatusEnum.IN_PROCESS,
						null, groupRequest);

		if (processResponse.isInError())
		{
			groupResponse.setStatus(processResponse.getStatus());
			groupResponse.addMessages(processResponse.getMessageInfoList());
			return;
		}

		// need the Id to do the async request
		groupRequest.setProcessId(processResponse.getFirstResult().getId());

		// Create uploaded name
		String fileName = groupRequest.getIdFileType().getValue() + STR_HIFEN
				+ groupRequest.getFirstGroup().getId()
				+ STR_CSV;

		if (!ValidationUtil.isNull(groupRequest.getIdsFile())
				&& !ValidationUtil.isNull(groupRequest.getIdFileType())
				&& !ValidationUtil.isNull(groupResponse.getFirstResult()))
		{
			File file = new File(getTempUploadFilePath() + fileName);

			DMUtil.copyFile(groupRequest.getIdsFile(), file);

			groupRequest.setFileName(file.getName());
		}
		else if (!ValidationUtil.isNull(groupRequest.getIdFileType())
				&& !ValidationUtil.isNull(groupRequest.getUploadIds()))
		{

			String[] uploadIds = groupRequest.getUploadIds().split(STR_COMMA);

			if (DMUtil.createFile(Arrays.asList(uploadIds), getTempUploadFilePath() + fileName))
			{
				groupRequest.setFileName(fileName);
			}
		}
	}

	/**
	 * Insert group process.
	 * 
	 * @param group the group
	 * @param isMonitored the is monitored
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMProcess> insertGroupProcess(Group group, Boolean isMonitored, String actionType,
			ProcessStatusEnum processStatusEnum,
			List<ProcessItem> processItems, TenantRequest tenantRequest)
	{

		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), group.getId()
				.toString()));
		properties.add(new Property(PropertyEnum.GROUP_NAME.getValue(), group.getName()));

		if (!ValidationUtil.isNull(group.getDeviceType()))
		{
			properties.add(new Property(PropertyEnum.DEVICE_TYPE.getValue(), group.getDeviceType().toString()));
		}

		if (!ValidationUtil.isNull(group.getHanDeviceType()))
		{
			properties.add(new Property(PropertyEnum.HAN_DEVICE_TYPE.getValue(), group.getHanDeviceType().toString()));
		}

		// create a Process for group
		return getProcessBCL().insertProcess(
				new ProcessRequest(DMUtil.generateProcess(isMonitored, true, null,
						new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
						.getUserContext(),
						tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));

	}

	/**
	 * Update group by file.
	 * 
	 * @param groupResponse the group response
	 * @param inquiryDeviceRequest the inquiry device request
	 * @param responseMeter the response meter
	 * @param processResponse the process response
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	private InternalResultsResponse<Group> updateGroupByFile(InternalResultsResponse<Group> groupResponse,
			InquiryDeviceRequest inquiryDeviceRequest, InternalResponse responseMeter,
			InternalResultsResponse<DMProcess> processResponse, GroupRequest groupRequest)
	{
		List<Device> devices =
				fetchDevicesByGroup(new GroupRequest(groupRequest.getFirstGroup())).getResultsList();

		for (Device device : devices)
		{
			inquiryDeviceRequest.getSelectionPaginationIds().add(device.getRadio().getFlexNetId());
		}

		if (!ValidationUtil.isNullOrEmpty(devices))
		{
			responseMeter = deleteDeviceFromGroup(inquiryDeviceRequest);
			groupResponse.getMessageInfoList().addAll(responseMeter.getMessageInfoList());
			groupResponse.setStatus(responseMeter.getStatus());
		}

		// insert process for edit group

		processResponse = insertGroupProcess(groupRequest.getFirstGroup(), groupRequest.getIsMonitored(),
				AddDeviceToGroupAction.ACTION, ProcessStatusEnum.IN_PROCESS, null, groupRequest);

		if (processResponse.isInError())
		{
			groupResponse.setStatus(processResponse.getStatus());
			groupResponse.addMessages(processResponse.getMessageInfoList());
			return groupResponse;
		}

		groupRequest.setProcessId(processResponse.getFirstResult().getId());

		if (!ValidationUtil.isNull(groupRequest.getIdsFile())
				&& !ValidationUtil.isNull(groupRequest.getIdFileType())
				&& !ValidationUtil.isNull(groupResponse.getFirstResult()))
		{

			// Create uploaded name
			String fileName =
					groupRequest.getIdFileType().getValue() + STR_HIFEN
							+ groupRequest.getFirstGroup().getId()
							+ STR_CSV;

			File file = new File(getTempUploadFilePath() + fileName);

			if (!DMUtil.copyFile(groupRequest.getIdsFile(), file))
			{
				groupResponse.setStatus(Status.ExceptionError);
				groupResponse.addMessage(FILE_CREATION_FAILED, MessageSeverity.Error, MessageLevel.Other);
				return groupResponse;
			}

			groupRequest.setFileName(file.getName());

		}
		else if (!ValidationUtil.isNull(groupRequest.getUploadIds())
				&& !ValidationUtil.isNull(groupRequest.getIdFileType()))
		{

			// Create uploaded name
			String fileName =
					groupRequest.getIdFileType().getValue() + STR_HIFEN
							+ groupRequest.getFirstGroup().getId()
							+ STR_CSV;

			String[] uploadIds = groupRequest.getUploadIds().split(STR_COMMA);
			if (DMUtil.createFile(Arrays.asList(uploadIds), getTempUploadFilePath() + fileName))
			{
				groupRequest.setFileName(fileName);
			}
		}

		return groupResponse;
	}

	/**
	 * Apply devices to group.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @param devices the devices
	 * @param process the process
	 * @param properties the properties
	 * @return the internal response
	 */
	private InternalResponse applyDevicesToGroup(InquiryDeviceRequest inquiryDeviceRequest,
			List<Device> devices, DMProcess process, List<Property> properties)
	{
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();

		if (!ValidationUtil.isNullOrEmpty(devices))
		{
			inquiryDeviceRequest.setDevices(new ArrayList<Device>());
			for (Device device : devices)
			{
				if (!ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getDevices()))
				{
					inquiryDeviceRequest.getDevices().clear();
				}

				inquiryDeviceRequest.addDevice(device);
				InternalResponse internalResponse =
						getGroupDAC().insertDeviceToGroup(
								new InquiryDeviceRequest(device, inquiryDeviceRequest.getFirstGroup(),
										inquiryDeviceRequest.getUserContext()));

				if (!ValidationUtil.isNullOrEmpty(internalResponse.getMessageInfoList()))
				{
					processItems.add(new ProcessItem(device, ProcessItemStatusEnum.FAILED, internalResponse
							.getMessageInfoList().get(INT_0).getCode()));
				}
				else
				{
					processItems.add(new ProcessItem(device, ProcessItemStatusEnum.COMPLETED));
				}
			}
		}

		InternalResultsResponse<Group> groupResponse = new InternalResultsResponse<Group>();

		if (ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getFirstGroup().getName()))
		{
			groupResponse = getGroupDAC().fetchAllGroups(
					new InquiryGroupRequest(inquiryDeviceRequest.getFirstGroup(), inquiryDeviceRequest
							.getServiceTypeEnum(), inquiryDeviceRequest.getTenant()));
			if (groupResponse.getStatus().equals(Status.OperationSuccess))
			{
				inquiryDeviceRequest.getFirstGroup().setName(groupResponse.getFirstResult().getName());
			}

		}

		return applyGroupProcess(inquiryDeviceRequest, process, properties, processItems);
	}

	/**
	 * Apply group process.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @param process the process
	 * @param properties the properties
	 * @param processItems the process items
	 * @return the internal response
	 */
	private InternalResponse applyGroupProcess(InquiryDeviceRequest inquiryDeviceRequest, DMProcess process,
			List<Property> properties, List<ProcessItem> processItems)
	{
		if (ValidationUtil.isNull(process))
		{
			InternalResultsResponse<DMProcess> processResponse =
					insertGroupProcess(inquiryDeviceRequest.getFirstGroup(), inquiryDeviceRequest.getIsMonitored(),
							AddDeviceToGroupAction.ACTION, ProcessStatusEnum.COMPLETED, processItems,
							inquiryDeviceRequest);
			if (processResponse.isInError())
			{
				return processResponse;
			}

		}
		else
		{
			process.setProperties(properties);
			process.setProcessItems(processItems);

			InternalResultsResponse<ProcessItem> processItemsResponse =
					getProcessBCL().insertProcessItems(
							new ProcessRequest(process, inquiryDeviceRequest.getUserContext()));

			if (processItemsResponse.isInError())
			{
				return processItemsResponse;
			}

			process.setIsProcessComplete(true);
			process.setEndTime(DMConvertUtil.convertDateToDefaultUTC(Calendar.getInstance().getTime()));
			process.setProcessStatusEnum(ProcessStatusEnum.COMPLETED);

			InternalResponse responseUpdate =
					getProcessBCL().updateProcess(new ProcessRequest(process, inquiryDeviceRequest.getUserContext()));
			if (responseUpdate.isInError())
			{
				return responseUpdate;
			}

		}

		return new InternalResponse();
	}

	// -------------------------------------------------------------------------
}
