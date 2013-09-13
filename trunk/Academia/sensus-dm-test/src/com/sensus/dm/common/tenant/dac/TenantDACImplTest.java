package com.sensus.dm.common.tenant.dac;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.util.AbstractTestBaseDAC;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class TenantDACImplTest.
 * 
 * @author QAT Global.
 */
public class TenantDACImplTest extends AbstractTestBaseDAC
{

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

	@Resource
	public void setTenantDAC(ITenantDAC tenantDAC)
	{
		this.tenantDAC = tenantDAC;
	}

	/**
	 * Test fetch tenant description.
	 */
	@Test
	public void testFetchTenantDescription()
	{
		TenantRequest tenantRequest = TestBaseUtil.createTenantRequest();
		InternalResultsResponse<DMTenant> response = getTenantDAC().fetchTenantDescription(tenantRequest);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}
}
