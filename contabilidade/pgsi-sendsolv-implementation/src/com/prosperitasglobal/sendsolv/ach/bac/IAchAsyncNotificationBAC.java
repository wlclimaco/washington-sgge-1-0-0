package com.prosperitasglobal.sendsolv.ach.bac;

import com.prosperitasglobal.sendsolv.ach.model.AchStatus;
import com.prosperitasglobal.sendsolv.ach.model.request.AchNotificationRequest;
import com.qat.framework.model.response.InternalResultsResponse;

public interface IAchAsyncNotificationBAC extends IAchCommonBAC
{
	public InternalResultsResponse<AchStatus> obtainNotifications(AchNotificationRequest request);

	public InternalResultsResponse<AchStatus> confirmNotifications(AchNotificationRequest request);

}
