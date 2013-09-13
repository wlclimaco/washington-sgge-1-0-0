package com.sensus.dm.elec.action.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;

/**
 * The Interface IActionDAC.
 * 
 * @author - QAT Brazil.
 */
public interface IActionDAC
{
	/**
	 * Insert action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResultsResponse<DMAction> insertAction(ActionRequest actionRequest);

	/**
	 * Insert groups to action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse insertGroupsToAction(ActionRequest actionRequest);

	/**
	 * Insert device to action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse insertDevicesToAction(ActionRequest actionRequest);

	/**
	 * Delete groups from action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse deleteGroupsFromAction(ActionRequest actionRequest);

	/**
	 * Delete device from action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse deleteDevicesFromAction(ActionRequest actionRequest);

	/**
	 * Fetch action by id.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResultsResponse<DMAction> fetchActionById(ActionRequest actionRequest);

	/**
	 * Insert tags to action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse insertTagsToAction(ActionRequest actionRequest);

	/**
	 * Delete tags from action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse deleteTagsFromAction(ActionRequest actionRequest);

	/**
	 * Insert devices opt out list.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse insertDevicesOptOutList(ActionRequest actionRequest);

	/**
	 * Delete devices opt out list.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse deleteDevicesOptOutList(ActionRequest actionRequest);
}
