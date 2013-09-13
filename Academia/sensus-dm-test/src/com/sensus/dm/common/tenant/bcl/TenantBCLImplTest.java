package com.sensus.dm.common.tenant.bcl;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class TenantBCLImplTest.
 * 
 * @author QAT Global.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/dm/common/tenant/tenantbclimpltest.xml"})
public class TenantBCLImplTest extends AbstractTestBaseBusiness
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
	 * Test fetch tenant description.
	 */
	@Test
	public void testFetchTenantDescription()
	{
		TenantRequest tenantRequest = TestBaseUtil.createTenantRequest();
		InternalResultsResponse<DMTenant> response = getTenantBCL().fetchTenantDescription(tenantRequest);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}
}
