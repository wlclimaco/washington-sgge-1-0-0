package com.sensus.lc.tenant.bcl;

import static com.sensus.lc.base.TestBaseUtil.assertResponse;
import static com.sensus.lc.base.TestBaseUtil.assertResultResponse;
import static com.sensus.lc.base.TestBaseUtil.createTenant;
import static com.sensus.lc.base.TestBaseUtil.createTenantRequest;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.tenant.dac.ITenantDAC;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class TenantBCLTest.
 */
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
		InternalResultsResponse<Tenant> response = getTenantBCL().fetchAllTenant();
		assertResultResponse(response);
	}

	/**
	 * Test fetch all tenant.
	 */
	@Test
	public void testFetchAllTenant()
	{
		// Success situation
		InternalResultsResponse<Tenant> response = getTenantBCL().fetchAllTenant();
		assertResponse(response);

		// Error situation
		resetMocksToTenantArea();
		this.setSituation(getTenantBCL(), SituationsEnum.ERROR, ITenantDAC.class, "fetchAllTenant");
		response = getTenantBCL().fetchAllTenant();
		this.assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test fetch tenant by rni code.
	 */
	@Test
	public void testFetchTenantByRniCode()
	{
		// Success situation
		TenantRequest request = createTenantRequest();
		request.setTenant(createTenant());
		InternalResultsResponse<Tenant> response = getTenantBCL().fetchTenantByRniCode(request);
		assertResultResponse(response);

		// Error situation
		resetMocksToTenantArea();
		setSituation(getTenantBCL(), SituationsEnum.ERROR, ITenantDAC.class, "fetchTenantByRniCode");
		request = createTenantRequest();
		response = getTenantBCL().fetchTenantByRniCode(request);
		assertMessages(response, ERROR_CODE);
	}

	/**
	 * Test verify api access.
	 */
	@Test
	public void testVerifyAPIAccess()
	{
		// Success situation
		TenantRequest request = createTenantRequest();
		request.setTenant(createTenant());
		InternalResultsResponse<Boolean> response = getTenantBCL().verifyAPIAccess(request);
		assertResponse(response);
	}

	/**
	 * Test fetch tenant by id.
	 */
	@Test
	public void testFetchTenantById()
	{
		TenantRequest request = new TenantRequest();
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResultsResponse<Tenant> response = getTenantBCL().fetchTenantById(request);
		TestBaseUtil.assertResponse(response);
	}

	/**
	 * Test fetch tenant by server name.
	 */
	@Test
	public void testFetchTenantByServerName()
	{
		TenantRequest request = new TenantRequest();
		request.setUserContext(TestBaseUtil.createUserContext());

		InternalResultsResponse<Tenant> response = getTenantBCL().fetchTenantByServerName(request);
		TestBaseUtil.assertResponse(response);
	}
}
