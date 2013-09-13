package com.sensus.dm.common.tenant.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.dm.common.schedule.model.DMSchedule;
import com.sensus.dm.common.tenant.dac.ITenantDAC;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class TenantDACImpl.
 * 
 * @author QAT Global.
 */
public class TenantDACImpl extends SqlSessionDaoSupport implements ITenantDAC
{

	/** The Constant TENANT_MAP. */
	private static final String TENANT_MAP = "TenantMap.";

	/** The Constant FETCH_TENANT_DESCRIPTION. */
	private static final String FETCH_TENANT_DESCRIPTION = TENANT_MAP + "fetchTenantDescription";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.tenant.dac.ITenantDAC#fetchTenantDescription(com.sensus.dm.common.tenant.model.request.
	 * TenantRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<DMTenant> fetchTenantDescription(TenantRequest tenantRequest)
	{
		return SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_TENANT_DESCRIPTION,
				tenantRequest, new InternalResultsResponse<DMSchedule>());
	}

}
