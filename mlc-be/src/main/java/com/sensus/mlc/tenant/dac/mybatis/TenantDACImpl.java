package com.sensus.mlc.tenant.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.tenant.dac.ITenantDAC;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

/**
 * The Class TenantDACImpl.
 */
public class TenantDACImpl extends SqlSessionDaoSupport implements ITenantDAC
{
	/** The Constant TENANT_NAMESPACE. */
	private static final String TENANT_NAMESPACE = "Tenant.";

	/** The Constant FETCH_ALL_TENANTS. */
	private static final String FETCH_ALL_TENANTS = TENANT_NAMESPACE + "fetchAllTenants";

	/** The Constant FETCH_TENANT_BY_SERVER_NAME. */
	private static final String FETCH_TENANT_BY_SERVER_NAME = TENANT_NAMESPACE + "fetchTenantByServerName";


	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.tenant.dac.ITenantDAC#fetchAllTenants()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Tenant> fetchAllTenants()
	{
		List<Tenant> tenants = doQueryForList(getSqlSession(), FETCH_ALL_TENANTS);
		return tenants;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.tenant.dac.ITenantDAC#fetchTenantByServerName(com.sensus.mlc.settings.model.request.TenantRequest)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest)
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		doQueryForList(getSqlSession(), FETCH_TENANT_BY_SERVER_NAME,
				tenantRequest.getServerName(), response);

		return response;
	}

}
