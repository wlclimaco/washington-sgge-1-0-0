package com.sensus.dm.common.group.dac;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;

/**
 * The Interface IGroupDAC.
 * 
 * @author - QAT Brazil.
 */
public interface IGroupDAC
{
	/**
	 * Fetch all groups.
	 * 
	 * @param inquiryGroupRequest the inquiry group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchAllGroups(InquiryGroupRequest inquiryGroupRequest);

	/**
	 * Insert group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest);

	/**
	 * Delete group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse deleteGroup(GroupRequest groupRequest);

	/**
	 * Update group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> updateGroup(GroupRequest groupRequest);

	/**
	 * Insert device to group.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse insertDeviceToGroup(InquiryDeviceRequest deviceRequest);

	/**
	 * Delete device from group.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse deleteDeviceFromGroup(InquiryDeviceRequest deviceRequest);

	/**
	 * Fetch groups by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchGroupsByDevice(DeviceRequest deviceRequest);

	/**
	 * Fetch can group be inserted.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Boolean> fetchCanGroupBeInserted(GroupRequest groupRequest);

	/**
	 * Fetch devices by group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchDevicesByGroup(GroupRequest groupRequest);
}
