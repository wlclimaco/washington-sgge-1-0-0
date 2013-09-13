package com.sensus.dm.controller.unittest.util;

import com.sensus.dm.common.tenant.bcf.ITenantBCF;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.tenant.model.response.TenantResponse;

/**
 * Base class for mock BCF implementation. Provides the mode field and common constants.
 */
public class TenantBCFMockImpl extends BaseMockImpl implements ITenantBCF
{

	@Override
	public TenantResponse fetchTenantDescription(TenantRequest tenantRequest)
	{
		TenantResponse response = new TenantResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			// TODO
			return response;

		}
		return (TenantResponse)testOtherDefaultModes(response);
	}
}