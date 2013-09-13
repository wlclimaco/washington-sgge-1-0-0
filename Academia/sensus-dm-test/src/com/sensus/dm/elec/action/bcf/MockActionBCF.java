package com.sensus.dm.elec.action.bcf;

import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.response.ActionResponse;

/**
 * The Class MockActionBCF.
 */
public class MockActionBCF extends AbstractMockBase implements IActionBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcf.IActionBCF#insertDeviceFromFileToAction(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public ActionResponse insertDeviceFromFileToAction(ActionRequest actionRequest)
	{
		return new ActionResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcf.IActionBCF#abortAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public ActionResponse abortAction(ActionRequest actionRequest)
	{
		return new ActionResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcf.IActionBCF#insertDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public ActionResponse insertDevicesOptOutList(ActionRequest actionRequest)
	{
		return new ActionResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcf.IActionBCF#deleteDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public ActionResponse deleteDevicesOptOutList(ActionRequest actionRequest)
	{
		return new ActionResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcf.IActionBCF#applyActionOnDemand(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public ActionResponse applyActionOnDemand(ActionRequest actionRequest)
	{
		return new ActionResponse();
	}

}
