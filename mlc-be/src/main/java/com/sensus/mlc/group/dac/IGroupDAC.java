package com.sensus.mlc.group.dac;

import java.util.HashMap;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;

/**
 * The Interface IGroupDAC.
 */
public interface IGroupDAC
{
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
	 * @return the internal response
	 */
	InternalResponse updateGroup(GroupRequest groupRequest);

	/**
	 * Insert smart point to group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse insertSmartPointToGroup(GroupRequest groupRequest);

	/**
	 * Insert smart point to auto group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResultsResponse<HashMap<String, Integer>> insertSmartPointToAutoGroup(GroupRequest groupRequest);

	/**
	 * Update smart point to group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse updateSmartPointToGroup(GroupRequest groupRequest);

	/**
	 * Delete smart point from group.
	 * 
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse deleteSmartPointFromGroup(GroupRequest groupRequest);

	/**
	 * Fetch all groups.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest);

	/**
	 * Fetch group by id.
	 * 
	 * @param group request the group
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchGroupById(GroupRequest groupRequest);

	/**
	 * Fetch group by id list.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchGroupsByIdList(GroupRequest groupRequest);

	/**
	 * Can delete.
	 * 
	 * @param group request the group
	 * @return the boolean
	 */
	Boolean fetchCanDelete(GroupRequest groupRequest);

	/**
	 * Count light groups.
	 * 
	 * @param light request the light
	 * @return the integer
	 */
	Integer countLightGroups(LightRequest lightRequest);

	/**
	 * Fetch groups by smartpoint.
	 * 
	 * @param lightRequest the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchGroupsBySmartPoint(LightRequest lightRequest);

	/**
	 * Fetch groups by light.
	 * 
	 * @param lightRequest the request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchGroupsByLight(LightRequest lightRequest);

	/**
	 * Fetch group by name.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchGroupByName(GroupRequest groupRequest);

	/**
	 * Fetch lights belong groups max allowed.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightsWithGroupsMaxAllowed(GroupRequest groupRequest);

	/**
	 * Fetch lights belong.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Light> fetchLightsBelongGroup(GroupRequest groupRequest);

	/**
	 * Fetch count lights from groups.
	 * 
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Integer> fetchCountLightsFromGroups(GroupRequest groupRequest);

}