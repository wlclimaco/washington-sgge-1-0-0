package com.sensus.dm.elec.action.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.request.InquiryActionRequest;

/**
 * The Class MockActionBCL.
 * 
 * @author QAT Global.
 */
public class MockActionBCL extends AbstractMockBase implements IActionBCL
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#insertAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResultsResponse<DMAction> insertAction(ActionRequest actionRequest)
	{
		InternalResultsResponse<DMAction> response = new InternalResultsResponse<DMAction>();
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			return response;
		}

		actionRequest.getAction().setId(1);
		response.addResult(actionRequest.getAction());
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#updateAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResponse updateAction(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#insertGroupsToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertGroupSetToAction(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#insertDevicesToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertDevicesToAction(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#fetchActionById(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResultsResponse<DMAction> fetchActionById(ActionRequest actionRequest)
	{
		return new InternalResultsResponse<DMAction>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#applyDeviceToAction(com.sensus.dm.elec.action.model.request.
	 * InquiryActionRequest)
	 */
	@Override
	public InternalResponse applyDeviceToAction(InquiryActionRequest inquiryActionRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#deleteGroupsFromAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse deleteGroupsFromAction(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#deleteDevicesFromAction(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse deleteDevicesFromAction(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.bcl.IActionBCL#insertDeviceFromFileToAction(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public InternalResponse insertDeviceFromFileToAction(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#importHanDeviceFromAction(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse importHanDeviceFromAction(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#abortAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResponse abortAction(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#insertDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse insertDevicesOptOutList(ActionRequest actionRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcl.IActionBCL#deleteDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse deleteDevicesOptOutList(ActionRequest actionRequest)
	{
		return verifyOtherSituations();
	}

	@Override
	public InternalResponse applyActionOnDemand(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}

	@Override
	public InternalResponse connectDisconnectActionOnDemand(ActionRequest actionRequest)
	{
		return new InternalResponse();
	}
}
