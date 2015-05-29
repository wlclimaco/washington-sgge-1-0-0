package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.ITenantDAC;
import com.prosperitasglobal.sendsolv.tenant.model.ApiControl;
import com.prosperitasglobal.sendsolv.tenant.model.Tenant;
import com.prosperitasglobal.sendsolv.tenant.model.request.ApiControlMaintenanceRequest;
import com.prosperitasglobal.sendsolv.tenant.model.request.TenantRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class TenantDACImpl.
 */
public class TenantDACImpl extends SqlSessionDaoSupport implements ITenantDAC
{

	/** The Constant INSERT_API_CONTROL. */
	private static final String INSERT_API_CONTROL = "InsertApiControl";

	/** The Constant UPDATE_API_CONTROL. */
	private static final String UPDATE_API_CONTROL = "UpdateApiControl";

	/** The Constant FETCH_API_CONTROL_BY_TENANT_ID. */
	private static final String FETCH_API_CONTROL_BY_TENANT_ID = "fetchApiControlByTenantId";

	/** The Constant TENANT_NAMESPACE. */
	private static final String TENANT_NAMESPACE = "Tenant.";

	/** The Constant FETCH_ALL_TENANTS. */
	private static final String FETCH_ALL_TENANTS = TENANT_NAMESPACE + "fetchAllTenants";

	/** The Constant FETCH_TENANT_BY_SERVER_NAME. */
	private static final String FETCH_TENANT_BY_SERVER_NAME = TENANT_NAMESPACE + "fetchTenantByServerName";

	/** The Constant FETCH_TENANT_BY_RNI_CODE. */
	private static final String FETCH_TENANT_BY_RNI_CODE = TENANT_NAMESPACE + "fetchTenantByRniCode";

	/** The Constant FETCH_BY_ID. */
	private static final String FETCH_BY_ID = TENANT_NAMESPACE + "fetchById";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#fetchAllTenant()
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchAllTenant()
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		// doQueryForList(getSqlSession(), FETCH_ALL_TENANTS, response);
		return response;
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
		// doQueryForList(getSqlSession(), FETCH_TENANT_BY_SERVER_NAME,
		// tenantRequest.getServerName(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#fetchTenantByRniCode(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByRniCode(TenantRequest tenantRequest)
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		// doQueryForList(getSqlSession(), FETCH_TENANT_BY_RNI_CODE, tenantRequest.getTenant().getRniCode(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IProcessDAO#fetchTenantById(java.lang.Integer)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantById(TenantRequest tenantRequest)
	{
		InternalResultsResponse<Tenant> internalResultsResponse = new InternalResultsResponse<Tenant>();
		// doQueryForList(getSqlSession(), FETCH_BY_ID, tenantRequest.getTenant().getId(), internalResultsResponse);
		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.tenant.dac.ITenantDAC#updateApiControl(com.sensus.lc.tenant.model.request.ApiControlMaintenanceRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ApiControl> updateApiControl(ApiControlMaintenanceRequest request)
	{
		InternalResultsResponse<ApiControl> response = new InternalResultsResponse<ApiControl>();
		// doQueryForList(getSqlSession(), UPDATE_API_CONTROL, request.getApiControl(), response);
		return response;
	}

}
