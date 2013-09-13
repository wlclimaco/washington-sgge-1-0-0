package com.sensus.dm.common.group.dac;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.elec.device.model.ElectricMeter;

/**
 * The Class MockGroupDAC.
 */
public class MockGroupDAC extends AbstractMockBase implements IGroupDAC
{

	/** The Constant GROUP. */
	private static final String GROUP = "group";

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = "downtown";

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "1";

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant DEVICE_NOT_IN_THE_GROUP. */
	private static final String DEVICE_NOT_IN_THE_GROUP = "sensus.epm.epm_action.add_smp_to_grp.not_in_group";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#fetchAllGroups(com.sensus.dm.common.group.model.request.InquiryGroupRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryGroupRequest request)
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			// response.addResult(TestBaseUtil.createCustomSearch());
			if (!ValidationUtil.isNullOrEmpty(request.getSelectionPaginationIds()))
			{
				for (BigInteger id : request.getSelectionPaginationIds())
				{
					Group gp = new Group(id.intValue(), GROUP_NAME, "", 2, GroupTypeEnum.BILLING, null, null);
					gp.setDeviceType(DeviceTypeEnum.ELECTRIC_METER);
					gp.setCreateDate(new Date());
					response.getResultsList().add(gp);
				}
			}
			else if (!ValidationUtil.isNullOrEmpty(request.getGroups()))
			{
				request.getFirstGroup().setId(request.getFirstGroup().getId());
				request.getFirstGroup().setName(GROUP_NAME);
				response.getResultsList().add(request.getFirstGroup());
				return response;
			}

			return response;
		}

		return (InternalResultsResponse<Group>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#fetchGroupsByDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Group> fetchGroupsByDevice(DeviceRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return new InternalResultsResponse<Group>(new Group(GROUP_NAME));
		}

		return (InternalResultsResponse<Group>)verifyOtherSituations();

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.dac.IGroupDAC#insertGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			InternalResultsResponse<Group> internalResponse = new InternalResultsResponse<Group>();
			internalResponse.addResult(new Group(1));
			return internalResponse;
		}

		return (InternalResultsResponse<Group>)verifyOtherSituations();

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#insertDeviceToGroup(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InternalResponse insertDeviceToGroup(InquiryDeviceRequest deviceRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			InternalResultsResponse<Group> internalResponse = new InternalResultsResponse<Group>();
			ArrayList<MessageInfo> messages = new ArrayList<MessageInfo>();
			messages.add(new MessageInfo("code ten", MessageSeverity.Warning, MessageLevel.FieldValidation));
			internalResponse.addMessages(messages);
			internalResponse.addResult(new Group(1));
			return internalResponse;
		}

		return verifyOtherSituations();

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.dac.IGroupDAC#updateGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Group> updateGroup(GroupRequest groupRequest)
	{
		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return (InternalResultsResponse<Group>)verifyOtherSituations();
		}

		InternalResultsResponse<Group> internalResultsResponse = new InternalResultsResponse<Group>();

		if (!ValidationUtil.isNull(groupRequest.getFirstGroup()))
		{
			groupRequest.getFirstGroup().setId(1);
			internalResultsResponse.getResultsList().add(groupRequest.getFirstGroup());
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#deleteDeviceFromGroup(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InternalResponse deleteDeviceFromGroup(InquiryDeviceRequest deviceRequest)
	{
		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return verifyOtherSituations();
		}

		InternalResponse internalResponse = new InternalResponse();

		if (!ValidationUtil.isNullOrEmpty(deviceRequest.getDevices())
				&& deviceRequest.getFirstDevice().getRadio().getFlexNetId().compareTo(new BigInteger(FLEXNET_ID)) == 0)
		{
			internalResponse.addMessage(DEVICE_NOT_IN_THE_GROUP, MessageSeverity.Error, MessageLevel.None);
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.dac.IGroupDAC#deleteGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() != SituationsEnum.SUCCESS)
		{
			return verifyOtherSituations();
		}

		if (groupRequest.getFirstGroup().getId().equals(THREE))
		{
			internalResponse.setStatus(Status.ExceptionError);
			internalResponse.addMessage(ERROR_CODE, MessageSeverity.Error, MessageLevel.Other);
			return internalResponse;
		}

		if (groupRequest.getFirstGroup().getId().equals(THREE))
		{
			internalResponse.setStatus(Status.OperationSuccess);
			return internalResponse;
		}

		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.dac.IGroupDAC#fetchCanGroupBeInserted(com.sensus.dm.common.group.model.request.
	 * GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Boolean> fetchCanGroupBeInserted(GroupRequest groupRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (!ValidationUtil.isNull(groupRequest.getFirstGroup())
				&& !groupRequest.getFirstGroup().getName().equals(GROUP))
		{
			response.getResultsList().add(Boolean.TRUE);
		}
		else
		{
			response.getResultsList().add(Boolean.FALSE);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.dac.IGroupDAC#fetchDevicesByGroup(com.sensus.dm.common.group.model.request.GroupRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Device> fetchDevicesByGroup(GroupRequest groupRequest)
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();
		Device meter = new ElectricMeter();
		meter.setRadio(new Radio());
		meter.getRadio().setFlexNetId(new BigInteger(FLEXNET_ID));
		response.addResult(meter);
		return response;
	}

}
