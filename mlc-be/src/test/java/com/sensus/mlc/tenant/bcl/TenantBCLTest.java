package com.sensus.mlc.tenant.bcl;

import static com.sensus.mlc.base.TestBaseUtil.assertResultResponse;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractTestBaseBusiness;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.tenant.model.Tenant;

@ContextConfiguration(locations = {"classpath:com/sensus/mlc/tenant/tenantbclimpltest.xml"})
public class TenantBCLTest extends AbstractTestBaseBusiness
{

	/** The tenant bcl. */
	private ITenantBCL tenantBCL;

	/**
	 * Gets the tenant bcl.
	 *
	 * @return the tenant bcl
	 */
	public ITenantBCL getTenantBCL()
	{
		return tenantBCL;
	}

	/**
	 * Sets the tenant bcl.
	 *
	 * @param tenantBCL the new tenant bcl
	 */
	@Resource(name = "tenantBCLTarget")
	public void setTenantBCL(ITenantBCL tenantBCL)
	{
		this.tenantBCL = tenantBCL;
	}

	/**
	 * Sets the tenant area.
	 */
	public void setTenantArea()
	{
		setArea(getTenantBCL(), LCAreaEnum.TENANT);
	}

	/**
	 * Removes the tenant area.
	 */
	@After
	public void removeTenantArea()
	{
		setArea(getTenantBCL(), LCAreaEnum.DEFAULT);
	}

	/**
	 * Reset mocks to tenant area.
	 */
	@After
	public void resetMocksToTenantArea()
	{
		resetMocksData(getTenantBCL());
		setTenantArea();
	}

	/**
	 * Test fetch all tenants.
	 */
	@Test
	public void testFetchAllTenants()
	{
		// Success
		InternalResultsResponse<Tenant> response = getTenantBCL().fetchAllTenants();
		assertResultResponse(response);
	}
}
