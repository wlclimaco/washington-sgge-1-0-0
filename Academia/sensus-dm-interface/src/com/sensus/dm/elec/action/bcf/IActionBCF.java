package com.sensus.dm.elec.action.bcf;

import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.response.ActionResponse;

/**
 * The Interface IActionBCF.
 * 
 * @author QAT Global.
 */
public interface IActionBCF
{

	/**
	 * Apply action on demand.
	 * 
	 * @param actionRequest the action request
	 * @return the action response
	 */
	ActionResponse applyActionOnDemand(ActionRequest actionRequest);

	/**
	 * Insert device from file to event.
	 * 
	 * @param actionRequest the action request
	 * @return the schedule response
	 */
	ActionResponse insertDeviceFromFileToAction(ActionRequest actionRequest);

	/**
	 * Abort action.
	 * 
	 * @param actionRequest the action request
	 * @return the action response
	 */
	ActionResponse abortAction(ActionRequest actionRequest);

	/**
	 * Insert devices opt out list.
	 * 
	 * @param actionRequest the action request
	 * @return the action response
	 */
	ActionResponse insertDevicesOptOutList(ActionRequest actionRequest);

	/**
	 * Delete devices opt out list.
	 * 
	 * @param actionRequest the action request
	 * @return the action response
	 */
	ActionResponse deleteDevicesOptOutList(ActionRequest actionRequest);
}