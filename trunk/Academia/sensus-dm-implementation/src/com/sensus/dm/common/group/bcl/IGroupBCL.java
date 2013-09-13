package com.sensus.dm.common.group.bcl;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;

/**
 * The Interface IGroupBCL.
 * 
 * @author QAT Brazil.
 * 
 */
public interface IGroupBCL
{

	/**
	 * Fetch all groups.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchAllGroups(InquiryGroupRequest inquiryPaginationRequest);

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
	 * @return the internal response
	 */
	InternalResultsResponse<Group> updateGroup(GroupRequest groupRequest);

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
	 * Insert group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> insertGroup(GroupRequest groupRequest);

	/**
	 * Insert device to group.
	 * 
	 * @param inquiryDeviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse insertDeviceToGroup(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Insert device from file to group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse insertDeviceFromFileToGroup(GroupRequest groupRequest);

	/**
	 * Delete device from group.
	 * 
	 * @param inquiryDeviceRequest the device request
	 * @return the internal response
	 */
	InternalResponse deleteDeviceFromGroup(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryGroupRequest the inquiry group request
	 * @return the internal response
	 */
	InternalResponse generateGroupsCSV(InquiryGroupRequest inquiryGroupRequest);

	/**
	 * Fetch devices by group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Device> fetchDevicesByGroup(GroupRequest groupRequest);

}
