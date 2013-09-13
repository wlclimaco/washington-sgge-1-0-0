package com.sensus.dm.elec.action.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Interface IActionBCL.
 * 
 * @author - QAT Global.
 */
public interface IActionBCL
{

	/**
	 * Apply action on demand.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	public InternalResponse applyActionOnDemand(ActionRequest actionRequest);

	/**
	 * Connect disconnect action on demand.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	public InternalResponse connectDisconnectActionOnDemand(ActionRequest actionRequest);

	/**
	 * Insert action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResultsResponse<DMAction> insertAction(ActionRequest actionRequest);

	/**
	 * Update action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse updateAction(ActionRequest actionRequest);

	/**
	 * Insert groups to action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse insertGroupSetToAction(ActionRequest actionRequest);

	/**
	 * Insert device to action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse insertDevicesToAction(ActionRequest actionRequest);

	/**
	 * Fetch action by id.
	 * 
	 * @param actionRequest the action request
	 * @return the internal results response
	 */
	InternalResultsResponse<DMAction> fetchActionById(ActionRequest actionRequest);

	/**
	 * Apply device to action.
	 * 
	 * @param inquiryActionRequest the inquiry action request
	 * @return the internal response
	 */
	InternalResponse applyDeviceToAction(InquiryActionRequest inquiryActionRequest);

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
	 * Insert device from file to action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse insertDeviceFromFileToAction(ActionRequest actionRequest);

	/**
	 * Import han device from action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse importHanDeviceFromAction(ActionRequest actionRequest);

	/**
	 * Abort action.
	 * 
	 * @param actionRequest the action request
	 * @return the internal response
	 */
	InternalResponse abortAction(ActionRequest actionRequest);

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
