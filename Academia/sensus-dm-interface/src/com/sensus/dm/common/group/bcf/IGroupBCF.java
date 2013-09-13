package com.sensus.dm.common.group.bcf;

import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.GroupResponse;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;

/**
 * The Interface IGroupBCF.
 * 
 * @author QAT Brazil.
 * 
 */
public interface IGroupBCF
{

	/**
	 * Generate file csv.
	 * 
	 * @param inquiryGroupRequest the inquiry group request
	 * @return the inquiry group response
	 */
	InquiryGroupResponse generateGroupsCSV(InquiryGroupRequest inquiryGroupRequest);

	/**
	 * Fetch all groups.
	 * 
	 * @param inquiryGroupRequest the inquiry group request
	 * @return the inquiry group response
	 */
	InquiryGroupResponse fetchAllGroups(InquiryGroupRequest inquiryGroupRequest);

	/**
	 * Fetch group by id.
	 * 
	 * @param inquiryGroupRequest the inquiry group request
	 * @return the group response
	 */
	GroupResponse fetchGroupById(InquiryGroupRequest inquiryGroupRequest);

	/**
	 * Fetch groups by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the group response
	 */
	GroupResponse fetchGroupsByDevice(DeviceRequest deviceRequest);

	/**
	 * Fetch group by name.
	 * 
	 * @param inquiryGroupRequest the inquiry group request
	 * @return the group response
	 */
	GroupResponse fetchGroupByName(InquiryGroupRequest inquiryGroupRequest);

	/**
	 * Fetch can group be inserted.
	 * 
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse fetchCanGroupBeInserted(GroupRequest groupRequest);

	/**
	 * Insert group.
	 * 
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse insertGroup(GroupRequest groupRequest);

	/**
	 * Insert device to group.
	 * 
	 * @param inquiryDeviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse insertDeviceToGroup(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Insert device from file to group.
	 * 
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse insertDeviceFromFileToGroup(GroupRequest groupRequest);

	/**
	 * Delete device from group.
	 * 
	 * @param inquiryDeviceRequest the device request
	 * @return the device response
	 */
	DeviceResponse deleteDeviceFromGroup(InquiryDeviceRequest inquiryDeviceRequest);

	/**
	 * Update group.
	 * 
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse updateGroup(GroupRequest groupRequest);

	/**
	 * Delete group.
	 * 
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse deleteGroup(GroupRequest groupRequest);

	/**
	 * Fetch devices by group.
	 * 
	 * @param groupRequest the group request
	 * @return the device response
	 */
	DeviceResponse fetchDevicesByGroup(GroupRequest groupRequest);

}
