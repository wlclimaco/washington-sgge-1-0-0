package com.sensus.dm.elec.action.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.elec.action.model.request.ActionRequest;

/**
 * The Class MockActionDAC.
 * 
 * @author QAT Global
 */
public class MockActionDAC extends AbstractMockBase implements IActionDAC
{

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.dac.IActionDAC#insertAction(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResultsResponse<DMAction> insertAction(ActionRequest actionRequest)
	{
		InternalResultsResponse<DMAction> response = new InternalResultsResponse<DMAction>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			actionRequest.getAction().setId(1);
			response.addResult(actionRequest.getAction());
		}
		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#insertGroupsToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertGroupsToAction(ActionRequest actionRequest)
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
	 * com.sensus.dm.elec.action.dac.IActionDAC#insertDevicesToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertDevicesToAction(ActionRequest actionRequest)
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
	 * com.sensus.dm.elec.action.dac.IActionDAC#deleteGroupsFromAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse deleteGroupsFromAction(ActionRequest actionRequest)
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
	 * @see com.sensus.dm.elec.action.dac.IActionDAC#deleteDevicesFromAction(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public InternalResponse deleteDevicesFromAction(ActionRequest actionRequest)
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
	 * com.sensus.dm.elec.action.dac.IActionDAC#fetchActionById(com.sensus.dm.elec.action.model.request.ActionRequest)
	 */
	@Override
	public InternalResultsResponse<DMAction> fetchActionById(ActionRequest actionRequest)
	{
		InternalResultsResponse<DMAction> response = new InternalResultsResponse<DMAction>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			actionRequest.getAction().setId(1);
			response.addResult(actionRequest.getAction());
		}
		else if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.action.dac.IActionDAC#insertTagsToAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse insertTagsToAction(ActionRequest actionRequest)
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
	 * com.sensus.dm.elec.action.dac.IActionDAC#deleteTagsFromAction(com.sensus.dm.elec.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public InternalResponse deleteTagsFromAction(ActionRequest actionRequest)
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
	 * com.sensus.dm.elec.action.dac.IActionDAC#insertDevicesOptOutList(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public InternalResponse insertDevicesOptOutList(ActionRequest actionRequest)
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
	 * com.sensus.dm.elec.action.dac.IActionDAC#deleteDevicesOptOutList(com.sensus.dm.elec.action.model.request
	 * .ActionRequest)
	 */
	@Override
	public InternalResponse deleteDevicesOptOutList(ActionRequest actionRequest)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
		}
		return response;
	}
}
