package com.sensus.lc.controller.light.unittest;

import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.bcf.ILightNotificationHistoryBCF;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.response.LightHistoryResponse;
import com.sensus.lc.light.model.response.NotificationHistoryResponse;

public class LightNotificationHistoryBCFMockImpl extends BaseMockImpl implements ILightNotificationHistoryBCF
{

	@Override
	public NotificationHistoryResponse fetchLightNotificationHistoryByRequest(NotificationHistoryRequest request)
	{
		NotificationHistoryResponse response = new NotificationHistoryResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (NotificationHistoryResponse)testOtherDefaultModes(response);
	}

	@Override
	public NotificationHistoryResponse fetchNotificationHistoryById(NotificationHistoryRequest request)
	{
		NotificationHistoryResponse response = new NotificationHistoryResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (NotificationHistoryResponse)testOtherDefaultModes(response);
	}

	@Override
	public LightHistoryResponse fetchLightNotificationHistory(NotificationHistoryRequest request)
	{
		LightHistoryResponse response = new LightHistoryResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (LightHistoryResponse)testOtherDefaultModes(response);
	}

}
