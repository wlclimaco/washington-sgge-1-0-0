package com.sensus.dm.common.tag.bcl.impl;

import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.Message;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.AddDeviceToTagAction;
import com.sensus.dm.common.action.model.CreateTagAction;
import com.sensus.dm.common.action.model.DeleteDeviceFromTagAction;
import com.sensus.dm.common.action.model.RemoveTagAction;
import com.sensus.dm.common.base.util.DMUtil;
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
import com.sensus.dm.common.tag.dac.ITagDAC;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.request.TagRequest;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;

/**
 * The Class TagBCLImpl.
 * 
 * @author QAT Brazil
 */
public class TagBCLImpl implements ITagBCL
{
	/** The Constant TAG_NAME_FIELD. */
	private static final String TAG_NAME = "name";

	/** The Constant INT_0. */
	private static final Integer INT_0 = 0;

	/** The Constant PROCESSING_TAG. */
	private static final String PROCESSING_TAG =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingTag";

	/** The tag dac. */
	private ITagDAC tagDAC;

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL;

	/** The water meter bcl. */
	private IWaterMeterBCL waterMeterBCL;

	/** The gas meter bcl. */
	private IGasMeterBCL gasMeterBCL;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/**
	 * Gets the tag dac.
	 * 
	 * @return the tag dac
	 */
	public ITagDAC getTagDAC()
	{
		return tagDAC;
	}

	/**
	 * Sets the tag dac.
	 * 
	 * @param tagDAC the new tag dac
	 */
	public void setTagDAC(ITagDAC tagDAC)
	{
		this.tagDAC = tagDAC;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#fetchAllTags(InquiryTagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> fetchAllTags(InquiryTagRequest inquiryTagRequest)
	{
		return getTagDAC().fetchAllTags(inquiryTagRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.tag.bcl.ITagBCL#insertDeviceToTag(com.sensus.dm.common.device.model.request.DeviceRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResponse insertDeviceToTag(InquiryDeviceRequest inquiryDeviceRequest)
	{

		// insert or fetch tag according its id
		InternalResultsResponse<Tag> response = applyDeviceToTag(inquiryDeviceRequest);
		if (response.isInError())
		{
			return response;
		}

		// the devices list may or may not have values
		List<Device> devices = inquiryDeviceRequest.getDevices();

		// if there is no devices list, fetch according ids
		if (ValidationUtil.isNullOrEmpty(devices))
		{
			// fetch the list of devices to add to tag (according its paginationIds)
			switch (inquiryDeviceRequest.getServiceTypeEnum())
			{
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

		List<ProcessItem> processItems = new ArrayList<ProcessItem>();

		// rolls device list inserting to tag
		for (Device device : devices)
		{
			// Insert one by one.
			InternalResponse insertResponse =
					getTagDAC().insertDeviceToTag(
							new InquiryDeviceRequest(device, response.getFirstResult(), inquiryDeviceRequest
									.getUserContext()));

			if (!ValidationUtil.isNullOrEmpty(insertResponse.getMessageInfoList()))
			{
				processItems.add(new ProcessItem(device, ProcessItemStatusEnum.FAILED, insertResponse
						.getMessageInfoList().get(INT_0).getCode()));
			}
			else
			{
				processItems.add(new ProcessItem(device, ProcessItemStatusEnum.COMPLETED));
			}

		}

		// insert process for add devices to tag
		InternalResultsResponse<DMProcess> processResponse =
				insertTagProcess(response.getFirstResult(), AddDeviceToTagAction.ACTION,
						ProcessStatusEnum.COMPLETED, processItems, inquiryDeviceRequest);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#insertTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResultsResponse<Tag> insertTag(TagRequest tagRequest)
	{
		InternalResultsResponse<Tag> response = new InternalResultsResponse<Tag>();

		// verify whether the tag can be inserted
		InternalResultsResponse<Boolean> canInsertResponse = getTagDAC().canTagBeInserted(tagRequest);

		if (ValidationUtil.isNull(canInsertResponse) || !canInsertResponse.getFirstResult())
		{
			response.setStatus(canInsertResponse.getStatus());
			response.addMessages(canInsertResponse.getMessageInfoList());
			return response;
		}

		// insert tag
		response = getTagDAC().insertTag(tagRequest);
		if (response.isInError())
		{
			return response;
		}

		// insert process for add devices to tag
		InternalResultsResponse<DMProcess> processResponse =
				insertTagProcess(tagRequest.getFirstTag(), CreateTagAction.ACTION,
						ProcessStatusEnum.COMPLETED, null, tagRequest);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
			return response;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#deleteTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@Override
	public InternalResponse deleteTag(TagRequest tagRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Tag tag : tagRequest.getTags())
		{

			// Are there something running for this tag ?
			InternalResponse fetchResponse = fetchMessageProcessing(tag);
			if (fetchResponse.isInError())
			{
				return fetchResponse;
			}

			// delete the tag
			response = getTagDAC().deleteTag(new TagRequest(tag));
			if (response.isInError())
			{
				return response;
			}

			// insert process for delete tag
			InternalResultsResponse<DMProcess> processResponse =
					insertTagProcess(tagRequest.getFirstTag(), RemoveTagAction.ACTION,
							ProcessStatusEnum.COMPLETED, null, tagRequest);

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
	 * @see com.sensus.dm.common.tag.bcl.ITagBCL#deleteDeviceFromTag(com.sensus.dm.common.tag.model.request.TagRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResponse deleteDeviceFromTag(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InternalResponse response = new InternalResponse();

		List<Device> devices = inquiryDeviceRequest.getDevices();

		if (ValidationUtil.isNullOrEmpty(devices))
		{
			// fetch the list of devices to add to tag (according its paginationIds)
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
					devices =
							(List<Device>)(List<?>)
							getGasMeterBCL().fetchAllGasMeters(inquiryDeviceRequest).getResultsList();
					break;
				default:
					break;
			}

		}

		// list of process items with its results
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();

		// delete devices
		for (Device device : devices)
		{
			// uses the same request to delete
			response = getTagDAC().deleteDeviceFromTag(
					new InquiryDeviceRequest(device, inquiryDeviceRequest.getFirstTag(), inquiryDeviceRequest
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

		// fetch the tag name if it is not available
		if (ValidationUtil.isNullOrEmpty(inquiryDeviceRequest.getFirstTag().getName()))
		{
			InternalResultsResponse<Tag> tagResultsResponse =
					getTagDAC().fetchAllTags(
							new InquiryTagRequest(inquiryDeviceRequest.getFirstTag(),
									inquiryDeviceRequest.getUserContext(),
									new SortExpression(TAG_NAME, Direction.Ascending), inquiryDeviceRequest
											.getServiceTypeEnum(), inquiryDeviceRequest.getTenant()));

			if (!tagResultsResponse.isInError() && tagResultsResponse.hasResults())
			{
				inquiryDeviceRequest.getFirstTag().setName(tagResultsResponse.getFirstResult().getName());
			}
		}

		// insert process for del tag
		InternalResultsResponse<DMProcess> processResponse =
				insertTagProcess(inquiryDeviceRequest.getFirstTag(), DeleteDeviceFromTagAction.ACTION,
						ProcessStatusEnum.COMPLETED, processItems, inquiryDeviceRequest);

		if (processResponse.isInError())
		{
			response.setStatus(processResponse.getStatus());
			response.addMessages(processResponse.getMessageInfoList());
			return response;
		}

		return response;
	}

	/**
	 * Insert tag process.
	 * 
	 * @param tag the tag
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	private InternalResultsResponse<DMProcess> insertTagProcess(Tag tag, String actionType,
			ProcessStatusEnum processStatusEnum,
			List<ProcessItem> processItems, TenantRequest tenantRequest)
	{

		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.TAG_ID.getValue(), tag.getId()
				.toString()));
		properties.add(new Property(PropertyEnum.TAG_NAME.getValue(), tag.getName()));

		// create a Process for group
		return getProcessBCL().insertProcess(
				new ProcessRequest(DMUtil.generateProcess(tenantRequest.getIsMonitored(), true, null,
						new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
						.getUserContext(),
						tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param tag the tag
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Tag tag)
	{
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.TAG_ID.getValue(), tag.getId().toString()));

		InternalResponse response = getProcessBCL().fetchCheckProcessing(
				new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));

		if (response.isInError())
		{
			response.addMessage(PROCESSING_TAG,
					Message.MessageSeverity.Error,
					Message.MessageLevel.FieldValidation);

		}

		return response;

	}

	/**
	 * Apply device to tag.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the internal response
	 */
	private InternalResultsResponse<Tag> applyDeviceToTag(InquiryDeviceRequest inquiryDeviceRequest)
	{

		// fetch tag if there is tag Id
		// otherwise (there is just tag name) insert the tag
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getFirstTag().getId()))
		{
			return getTagDAC().fetchAllTags(new InquiryTagRequest(
					inquiryDeviceRequest.getFirstTag(), inquiryDeviceRequest.getUserContext(),
					new SortExpression(TAG_NAME, Direction.Ascending), inquiryDeviceRequest
							.getServiceTypeEnum(), inquiryDeviceRequest.getTenant()));
		}

		return insertTag(new TagRequest(inquiryDeviceRequest.getFirstTag(), inquiryDeviceRequest.getUserContext(),
				inquiryDeviceRequest.getServiceTypeEnum(), inquiryDeviceRequest.getTenant()));

	}

}
