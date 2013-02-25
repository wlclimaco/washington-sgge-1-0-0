package com.sensus.mlc.tenant.dac;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.mlc.base.AbstractTestBaseDAC;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class TenantDACImplTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/tenant/tenantdacimpltest.xml"})
public class TenantDACTest extends AbstractTestBaseDAC
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
	 * Test fetch all tenants.
	 */
	@Test
	public void testFetchAllTenants()
	{
		final List<Tenant> tenants = getTenantDAC().fetchAllTenants();
		Assert.assertNotNull(tenants);
	}
}