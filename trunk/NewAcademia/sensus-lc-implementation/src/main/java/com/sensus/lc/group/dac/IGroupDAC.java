package com.sensus.lc.group.dac;


import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.request.LightRequest;

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
	 * Insert light to group.
	 *
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse insertLightToGroup(GroupRequest groupRequest);

	/**
	 * Update light to group.
	 *
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse updateLightToGroup(GroupRequest groupRequest);

	/**
	 * Delete light from group.
	 *
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse deleteLightFromGroup(GroupRequest groupRequest);

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
	InternalResultsResponse<Group> fetchCountLightsFromGroups(GroupRequest groupRequest);

}