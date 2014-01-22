package com.sensus.lc.tenant.dac;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createTenantRequest;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.AbstractTestBaseDAC;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.tenant.model.ApiControl;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.ApiControlMaintenanceRequest;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class TenantDACImplTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/tenant/tenantdacimpltest.xml"})
public class TenantDACTest extends AbstractTestBaseDAC
{

	/** The Constant NUMBER_1. */
	private static final int NUMBER_1 = 1;

	/** The Constant NUMBER_5. */
	private static final int NUMBER_5 = 5;

	/** The tenant dac. */
	private ITenantDAC tenantDAC;

	/**
	 * Gets the tenant dac.
	 * 
	 * @return the tenant dac
	 */
	public ITenantDAC getTenantDAC()
	{
		return tenantDAC;
	}

	/**
	 * Sets the tenant dac.
	 * 
	 * @param tenantDAC the new tenant dac
	 */
	@Resource
	public void setTenantDAC(final ITenantDAC tenantDAC)
	{
		this.tenantDAC = tenantDAC;
	}

	/**
	 * Test fetch all tenant.
	 */
	@Test
	public void testFetchAllTenant()
	{
		InternalResultsResponse<Tenant> response = getTenantDAC().fetchAllTenant();
		assertResponse(response);

		for (Tenant t : response.getResultsList())
		{
			assertNotNull(t.getId());
			assertNotNull(t.getName());
			assertNotNull(t.getGatewayURL());
		}
	}

	/**
	 * Test fetch tenant by id.
	 */
	@Test
	public void testFetchTenantById()
	{
		TenantRequest request = createTenantRequest();
		InternalResultsResponse<Tenant> response = getTenantDAC().fetchTenantById(request);
		Tenant tenant = response.getFirstResult();
		assertResultResponse(response);
		assertNotNull(tenant.getId());
		assertNotNull(tenant.getName());
		assertNotNull(tenant.getDescription());
		assertNotNull(tenant.getRniCode());
	}

	/**
	 * Test fetch tenant by rni code.
	 */
	@Test
	public void testFetchTenantByRniCode()
	{
		TenantRequest request = createTenantRequest();
		InternalResultsResponse<Tenant> response = getTenantDAC().fetchTenantByRniCode(request);
		Tenant tenant = response.getFirstResult();
		assertResultResponse(response);
		assertNotNull(tenant.getId());
		assertNotNull(tenant.getName());
		assertNotNull(tenant.getDescription());
		assertNotNull(tenant.getRniCode());
	}

	/**
	 * Test update control.
	 */
	@Test
	public void testUpdateControl()
	{
		// Success situation
		ApiControlMaintenanceRequest request = new ApiControlMaintenanceRequest();
		request.setApiControl(new ApiControl());
		request.getApiControl().setTenantId(NUMBER_1);
		InternalResultsResponse<ApiControl> updateResponse = getTenantDAC().updateApiControl(request);
		assertResultResponse(updateResponse);
		assertNotNull(updateResponse.getFirstResult().getCount());
	}

	/**
	 * Test fetch tenant by server name.
	 */
	@Test
	public void testFetchTenantByServerName()
	{
		TenantRequest tenantRequest = TestBaseUtil.createTenantRequest();

		// Get the tenant by ID first
		InternalResultsResponse<Tenant> response = getTenantDAC().fetchTenantById(tenantRequest);
		TestBaseUtil.assertResponse(response);

		// Now try to do a fetch by Server Name (using the server name from with the last response)
		if (!ValidationUtil.isNull(response.getFirstResult())
				&& !ValidationUtil.isNull(response.getFirstResult().getServerName()))
		{
			tenantRequest = TestBaseUtil.createTenantRequest();
			tenantRequest.setServerName(response.getFirstResult().getServerName());
			response = getTenantDAC().fetchTenantByServerName(tenantRequest);
			TestBaseUtil.assertResponse(response);

		}

	}
}