package com.sensus.lc.group.bcf;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.group.model.response.GroupResponse;
import com.sensus.lc.group.model.response.InquiryGroupResponse;
import com.sensus.lc.light.model.request.LightRequest;

/**
 * The Interface IGroupBCF.
 */
public interface IGroupBCF
{

	/**
	 * Insert light to group.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse insertLightToGroup(GroupRequest groupRequest);

	/**
	 * Update light protected for group.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse updateLightProtectedForGroup(GroupRequest groupRequest);

	/**
	 * Insert group.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse insertGroup(GroupRequest groupRequest);

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
	 * Fetch all groups.
	 *
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the inquiry group response
	 */
	InquiryGroupResponse fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest);

	/**
	 * Fetch group by id.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse fetchGroupById(GroupRequest groupRequest);

	/**
	 * Fetch group by id list.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse fetchGroupsByIdList(GroupRequest groupRequest);

	/**
	 * Delete light from group.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse deleteLightFromGroup(GroupRequest groupRequest);

	/**
	 * Update light intensity for group.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse updateLightIntensityForGroup(GroupRequest groupRequest);

	/**
	 * Fetch groups by light.
	 *
	 * @param lightRequest the light request
	 * @return the group response
	 */
	GroupResponse fetchGroupsByLight(LightRequest lightRequest);

	/**
	 * Fetch group by name.
	 *
	 * @param groupRequest the group request
	 * @return the group response
	 */
	GroupResponse fetchGroupByName(GroupRequest groupRequest);

	/**
	 * Fetch count lights from groups.
	 *
	 * @param groupRequest the group request
	 * @return the inquiry light response
	 */
	GroupResponse fetchCountLightsFromGroups(GroupRequest groupRequest);
}
