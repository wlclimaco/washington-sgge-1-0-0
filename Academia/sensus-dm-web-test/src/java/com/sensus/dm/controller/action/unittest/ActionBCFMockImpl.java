package com.sensus.dm.controller.action.unittest;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.elec.action.bcf.IActionBCF;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.response.ActionResponse;

/**
 * The Class ActionBCFMockImpl.
 */
public class ActionBCFMockImpl extends BaseMockImpl implements IActionBCF
{
	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.epm.action.bcf.IActionBCF#insertDeviceFromFileToAction(com.sensus.epm.action.model.request.ActionRequest
	 * )
	 */
	@Override
	public ActionResponse insertDeviceFromFileToAction(ActionRequest actionRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionResponse applyActionOnDemand(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();
		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{

			if (!ValidationUtil.isNull(actionRequest.getAction()))
			{
				if (!ValidationUtil.isNull(actionRequest.getAction().getFirstDevice()))
				{
					response.setOperationSuccess(Boolean.TRUE);
				}
				else
				{
					response.setOperationSuccess(Boolean.FALSE);
				}
			}
			else
			{
				response.setOperationSuccess(Boolean.FALSE);
			}
			return response;
		}

		return (ActionResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.epm.action.bcf.IActionBCF#abortAction(com.sensus.epm.action.model.request.ActionRequest)
	 */
	@Override
	public ActionResponse abortAction(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();
		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}
		return (ActionResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcf.IActionBCF#insertDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public ActionResponse insertDevicesOptOutList(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			if (!ValidationUtil.isNull(actionRequest) && !ValidationUtil.isNull(actionRequest.getAction())
					&& !ValidationUtil.isNull(actionRequest.getAction().getId())
					&& !ValidationUtil.isNullOrEmpty(actionRequest.getAction().getDevices())
					&& !ValidationUtil.isNull(actionRequest.getAction().getFirstDevice().getRadio())
					&& !ValidationUtil.isNull(actionRequest.getAction().getFirstDevice().getRadio().getFlexNetId()))
			{
				response.setOperationSuccess(Boolean.TRUE);
				response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
				return response;
			}

			response.setOperationSuccess(Boolean.FALSE);
			return response;
		}

		return (ActionResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.bcf.IActionBCF#deleteDevicesOptOutList(com.sensus.dm.elec.action.model.request.
	 * ActionRequest)
	 */
	@Override
	public ActionResponse deleteDevicesOptOutList(ActionRequest actionRequest)
	{
		ActionResponse response = new ActionResponse();

		if (ModeEnum.MODE_SUCCESS.equals(getMode()))
		{
			if (!ValidationUtil.isNull(actionRequest) && !ValidationUtil.isNull(actionRequest.getAction())
					&& !ValidationUtil.isNull(actionRequest.getAction().getId())
					&& !ValidationUtil.isNullOrEmpty(actionRequest.getAction().getDevices())
					&& !ValidationUtil.isNull(actionRequest.getAction().getFirstDevice().getRadio())
					&& !ValidationUtil.isNull(actionRequest.getAction().getFirstDevice().getRadio().getFlexNetId()))
			{
				response.setOperationSuccess(Boolean.TRUE);
				response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
				return response;
			}

			response.setOperationSuccess(Boolean.FALSE);
			return response;
		}

		return (ActionResponse)testOtherDefaultModes(response);
	}
}