package com.sensus.lc.group.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Interface IGroupBCL.
 */
public interface IGroupBCL
{

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
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchGroupById(GroupRequest groupRequest);

	/**
	 * Fetch group by id list.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	InternalResultsResponse<Group> fetchGroupsByIdList(GroupRequest groupRequest);

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
	 * Delete light from group.
	 *
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse deleteLightFromGroup(GroupRequest groupRequest);

	/**
	 * Update light intensity for group.
	 *
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse updateLightIntensityForGroup(GroupRequest groupRequest);

	/**
	 * Fetch groups by light.
	 *
	 * @param lightRequest the light request
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
	 * Update light protected for group.
	 *
	 * @param groupRequest the group request
	 * @return the internal response
	 */
	InternalResponse updateLightProtectedForGroup(GroupRequest groupRequest);

	/**
	 * Fetch count lights from groups.
	 *
	 * @param groupRequest the group request
	 * @return the internal results response
	 */
	InternalResultsResponse<Group> fetchCountLightsFromGroups(GroupRequest groupRequest);

}
