package com.sensus.dm.controller.group.unittest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.response.ResultsSetInfo;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.GroupResponse;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;

/**
 * The Class GroupBCFMockImpl.
 */
public class GroupBCFMockImpl extends BaseMockImpl implements IGroupBCF
{

	/** The Constant ALL_DEVICES. */
	public static final String ALL_DEVICES = "All Devices Burnside from city center to 205";

	/** The Constant GROUP_NAME. */
	public static final String GROUP_NAME = "BillGroup %d";

	/** The page size. */
	private Integer pageSize = 100;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#generateGroupsCSV(com.sensus.dm.common.group.model.request.
	 * InquiryGroupRequest)
	 */
	@Override
	public InquiryGroupResponse generateGroupsCSV(InquiryGroupRequest inquiryGroupRequest)
	{
		InquiryGroupResponse inquiryGroupResponse = new InquiryGroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return inquiryGroupResponse;
		}

		return (InquiryGroupResponse)testOtherDefaultModes(inquiryGroupResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchAllGroups(com.sensus.dm.common.group.model.request.InquiryGroupRequest
	 * )
	 */
	@Override
	public InquiryGroupResponse fetchAllGroups(InquiryGroupRequest inquiryGroupRequest)
	{
		InquiryGroupResponse inquiryGroupResponse = new InquiryGroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			inquiryGroupResponse.setGroups(new ArrayList<Group>());
			pageSize = inquiryGroupRequest.getPageSize();

			if (!ValidationUtil.isNull(inquiryGroupRequest.getDeviceSearch())
					&& !ValidationUtil.isNull(inquiryGroupRequest.getDeviceSearch().getSearchText()))
			{
				pageSize -= 5;
			}

			if (!ValidationUtil.isNull(inquiryGroupRequest.getDeviceSearch())
					&& !ValidationUtil.isNull((inquiryGroupRequest.getDeviceSearch().getGroupTypes()))
					&& (inquiryGroupRequest.getDeviceSearch().getGroupTypes().size() > 0))
			{
				pageSize -= 10;
			}

			if (inquiryGroupRequest.getPageSize() == null)
			{
				pageSize = 20;
			}

			for (int i = inquiryGroupRequest.getStartRow(); i < (inquiryGroupRequest.getStartRow() + pageSize); i++)
			{
				Integer id = i + 1;
				Group group = new Group(id);
				group.setName(String.format(GROUP_NAME, id));
				group.setDescription(String.format(ALL_DEVICES));
				group.setCreateDate(new Date());
				group.setGroupTypeEnum(GroupTypeEnum.OPERATIONS);
				group.setDevices(new ArrayList<Device>());

				List<Device> devices = new ArrayList<Device>();

				for (int j = 1; j <= 2; j++)
				{
					ElectricMeterConfiguration electricMeterConfiguration = new ElectricMeterConfiguration();
					electricMeterConfiguration.setCreateDate(new Date());

					Radio radio = new Radio();
					Location location = new Location();

					location.setLatitude(43.7717 + (j / 100));
					location.setLongitude(11.2536 + (j / 100));

					radio.setLocation(location);
					radio.setFlexNetId(BigInteger.valueOf(j));

					ElectricMeter eletricMeter = new ElectricMeter();
					eletricMeter.setConfiguration(electricMeterConfiguration);
					eletricMeter.setRadio(radio);

					devices.add(eletricMeter);
				}
				group.setDevicesCount(1024 + id);
				inquiryGroupResponse.getGroups().add(group);
				ResultsSetInfo resultsSetInfo = new ResultsSetInfo();
				resultsSetInfo.setTotalRowsAvailable(pageSize);
				inquiryGroupResponse.setResultsSetInfo(resultsSetInfo);
			}

			return inquiryGroupResponse;
		}

		return (InquiryGroupResponse)testOtherDefaultModes(inquiryGroupResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchGroupById(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupById(InquiryGroupRequest inquiryGroupRequest)
	{
		GroupResponse groupResponse = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<Group> groups = new ArrayList<Group>();

			if (!ValidationUtil.isNullOrEmpty(inquiryGroupRequest.getGroups())
					&& !ValidationUtil.isNull(inquiryGroupRequest.getGroups().get(0)))
			{
				Group group = new Group();
				group.setId(inquiryGroupRequest.getGroups().get(0).getId());
				group.setGroupTypeEnum(GroupTypeEnum.OPERATIONS);
				group.setName(String.format(GROUP_NAME, inquiryGroupRequest.getGroups().get(0).getId()));
				group.setDescription(String.format(ALL_DEVICES));
				group.setCreateDate(new Date());
				group.setDevices(new ArrayList<Device>());
				group.setDevicesCount(1024 + inquiryGroupRequest.getGroups().get(0).getId());
				groups.add(group);
				groupResponse.setGroups(groups);
				groupResponse.setOperationSuccess(true);
			}
			return groupResponse;
		}

		return (GroupResponse)testOtherDefaultModes(groupResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchGroupsByDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public GroupResponse fetchGroupsByDevice(DeviceRequest deviceRequest)
	{
		GroupResponse groupResponse = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			groupResponse.setGroups(new ArrayList<Group>());

			for (int count = 1; count <= 10; count++)
			{
				Group group = new Group();
				group.setId(count);
				group.setName(String.format(GROUP_NAME, count));
				group.setDevices(new ArrayList<Device>());
				for (int i = 1; i <= 3; i++)
				{
					Radio radio = new Radio();
					radio.setFlexNetId(BigInteger.valueOf(i));
					ElectricMeter eletricMeter = new ElectricMeter();
					eletricMeter.setRadio(radio);
					group.getDevices().add(eletricMeter);
				}
				group.setDevicesCount(count * 50);
				groupResponse.getGroups().add(group);
			}
			return groupResponse;
		}

		return (GroupResponse)testOtherDefaultModes(groupResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchGroupByName(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupByName(InquiryGroupRequest inquiryGroupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#fetchCanGroupBeInserted(com.sensus.dm.common.group.model.request.
	 * GroupRequest)
	 */
	@Override
	public GroupResponse fetchCanGroupBeInserted(GroupRequest groupRequest)
	{
		GroupResponse groupResponse = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNull(groupRequest.getFirstGroup().getName()))
			{
				groupResponse.setOperationSuccess(true);
			}

			return groupResponse;
		}

		return (GroupResponse)testOtherDefaultModes(groupResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#insertGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertGroup(GroupRequest groupRequest)
	{
		GroupResponse groupResponse = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			groupResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			groupResponse.setGroups(new ArrayList<Group>());
			groupResponse.getGroups().add(groupRequest.getGroups().get(0));
			groupResponse.getGroups().get(0).setId(1);
			groupResponse.getGroups().get(0).setGroupTypeEnum(groupRequest.getGroups().get(0).getGroupTypeEnum());
			groupResponse.getGroups().get(0).setDescription(groupRequest.getGroups().get(0).getDescription());

			if (!ValidationUtil.isNull(groupRequest.getGroups().get(0).getDevices()))
			{
				groupResponse.getGroups().get(0).setDevices(groupRequest.getGroups().get(0).getDevices());
			}

			return groupResponse;
		}

		return (GroupResponse)testOtherDefaultModes(groupResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#insertDeviceToGroup(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public DeviceResponse insertDeviceToGroup(InquiryDeviceRequest inquiryDeviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			deviceResponse.setOperationSuccess(true);
			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#insertDeviceFromFileToGroup(com.sensus.dm.common.group.model.request.
	 * GroupRequest)
	 */
	@Override
	public GroupResponse insertDeviceFromFileToGroup(GroupRequest groupRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#deleteDeviceFromGroup(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse deleteDeviceFromGroup(InquiryDeviceRequest inquiryDeviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			deviceResponse.setOperationSuccess(true);
			return deviceResponse;
		}

		return (DeviceResponse)testOtherDefaultModes(deviceResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#updateGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateGroup(GroupRequest groupRequest)
	{
		GroupResponse groupResponse = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			groupResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			groupResponse.setGroups(new ArrayList<Group>());
			groupResponse.getGroups().add(groupRequest.getGroups().get(0));
			groupResponse.getGroups().get(0).setGroupTypeEnum(groupRequest.getGroups().get(0).getGroupTypeEnum());
			groupResponse.getGroups().get(0).setDescription(groupRequest.getGroups().get(0).getDescription());

			if (!ValidationUtil.isNull(groupRequest.getGroups().get(0).getDevices()))
			{
				groupResponse.getGroups().get(0).setDevices(groupRequest.getGroups().get(0).getDevices());
			}
			// else if (!ValidationUtil.isNullOrEmpty(groupRequest.getSelectionPaginationIds()))
			// {
			// // TODO
			// }
			groupResponse.getGroups().get(0).setModelAction(PersistanceActionEnum.UPDATE);
			return groupResponse;
		}

		return (GroupResponse)testOtherDefaultModes(groupResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#deleteGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteGroup(GroupRequest groupRequest)
	{
		GroupResponse groupResponse = new GroupResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			groupResponse.setOperationSuccess(true);
			return groupResponse;
		}

		return (GroupResponse)testOtherDefaultModes(groupResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#fetchDevicesByGroupToMap(com.sensus.dm.common.group.model.request.
	 * GroupRequest)
	 */
	// @Override
	// public DeviceResponse fetchDevicesByGroupToMap(GroupRequest groupRequest)
	// {
	// DeviceResponse response = new DeviceResponse();
	//
	// if (getMode().equals(ModeEnum.MODE_SUCCESS))
	// {
	// Integer maxDeviceCount = null;
	// Double topRightLon = null;
	// Double topRightLat = null;
	// Double bottomLeftLon = null;
	// Double bottomLeftLat = null;
	//
	// Group group = new Group();
	//
	// if (!ValidationUtil.isNull(groupRequest.getGroups().get(0).getId()))
	// {
	// group.setId(groupRequest.getGroups().get(0).getId());
	// }
	// if (!ValidationUtil.isNull(groupRequest.getBottomLeftLat()))
	// {
	// bottomLeftLat = groupRequest.getBottomLeftLat();
	// }
	// if (!ValidationUtil.isNull(groupRequest.getBottomLeftLon()))
	// {
	// bottomLeftLon = groupRequest.getBottomLeftLon();
	// }
	// if (!ValidationUtil.isNull(groupRequest.getTopRightLat()))
	// {
	// topRightLat = groupRequest.getTopRightLat();
	// }
	// if (!ValidationUtil.isNull(groupRequest.getTopRightLon()))
	// {
	// topRightLon = groupRequest.getTopRightLon();
	// }
	// if (!ValidationUtil.isNull(groupRequest.getMaxDeviceCount()))
	// {
	// maxDeviceCount = groupRequest.getMaxDeviceCount();
	// }
	//
	// if (!ValidationUtil.isNullOrZero(maxDeviceCount) && !ValidationUtil.isNullOrZero(topRightLon)
	// && !ValidationUtil.isNullOrZero(topRightLat) && !ValidationUtil.isNullOrZero(bottomLeftLon)
	// && !ValidationUtil.isNullOrZero(bottomLeftLat) && !ValidationUtil.isNullOrZero(group.getId()))
	// {
	//
	// List<Device> deviceList = new ArrayList<Device>();
	//
	// for (int i = 0; i < 25; i++)
	// {
	// Radio radio = new Radio();
	// radio.setFlexNetId(BigInteger.valueOf(i));
	// ElectricMeter eletricMeter = new ElectricMeter();
	// eletricMeter.setRadio(radio);
	//
	// deviceList.add(eletricMeter);
	// }
	//
	// response.setDevices(deviceList);
	// /**
	// * SUCCESS CASE
	// */
	// response.setOperationSuccess(true);
	// return response;
	// }
	// else
	// {
	// /**
	// * FAILURE CASE
	// */
	// response.setOperationSuccess(false);
	// return response;
	// }
	// }
	//
	// return (DeviceResponse)testOtherDefaultModes(response);
	// }

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchDevicesByGroup(com.sensus.dm.common.group.model.request.GroupRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchDevicesByGroup(GroupRequest groupRequest)
	{
		DeviceResponse response = new DeviceResponse();
		// Invalid inputs cover Failure scenario
		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<Device> devices = new ArrayList<Device>();
			for (int x = 0; x < 9; x++)
			{
				Radio radio = new Radio();
				radio.setFlexNetId(BigInteger.valueOf(123 * x));
				ElectricMeter eletricMeter = new ElectricMeter();
				eletricMeter.setRadio(radio);
				devices.add(eletricMeter);
			}

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setDevices(devices);
			return response;
		}
		return (DeviceResponse)testOtherDefaultModes(response);
	}

}