package com.sensus.dm.common.group.bcl;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class MockGroupBCL.
 */
public class MockGroupBCL extends AbstractMockBase implements IGroupBCL
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#fetchAllGroups(com.sensus.dm.common.group.model.request.InquiryGroupRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Group> fetchAllGroups(InquiryGroupRequest inquiryPaginationRequest)
	{
		return groupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcl.IGroupBCL#deleteGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResponse deleteGroup(GroupRequest groupRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcl.IGroupBCL#updateGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> updateGroup(GroupRequest groupRequest)
	{
		return groupResultsResponse();
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
		return groupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcl.IGroupBCL#fetchCanGroupBeInserted(com.sensus.dm.common.group.model.request.
	 * GroupRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Boolean> fetchCanGroupBeInserted(GroupRequest groupRequest)
	{
		InternalResultsResponse<Boolean> response = new InternalResultsResponse<Boolean>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(true);
			return response;
		}

		return (InternalResultsResponse<Boolean>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcl.IGroupBCL#insertGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest)
	{
		return groupResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#insertDeviceToGroup(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InternalResponse insertDeviceToGroup(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return verifyOtherSituations();
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
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcl.IGroupBCL#deleteDeviceFromGroup(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InternalResponse deleteDeviceFromGroup(InquiryDeviceRequest inquiryDeviceRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcl.IGroupBCL#generateGroupsCSV(com.sensus.dm.common.group.model.request.
	 * InquiryGroupRequest)
	 */
	@Override
	public InternalResponse generateGroupsCSV(InquiryGroupRequest inquiryGroupRequest)
	{
		return verifyOtherSituations();
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
		return deviceResultsResponse();
	}

	/**
	 * Group results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Group> groupResultsResponse()
	{
		InternalResultsResponse<Group> response = new InternalResultsResponse<Group>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Group group = new Group();
			group.setId(1);
			group.setName("Group");
			group.setDescription("Group for tests");
			response.addResult(group);

			return response;
		}

		return (InternalResultsResponse<Group>)verifyOtherSituations();
	}

	/**
	 * Device results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Device> deviceResultsResponse()
	{
		InternalResultsResponse<Device> response = new InternalResultsResponse<Device>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Device device = TestBaseUtil.createDevice();
			response.addResult(device);

			return response;
		}

		return (InternalResultsResponse<Device>)verifyOtherSituations();
	}

}
