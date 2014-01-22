package com.sensus.lc.tenant.bcf;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.lc.base.AbstractTestBaseBusiness;
import com.sensus.lc.base.LCAreaEnum;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.tenant.bcl.ITenantBCL;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.tenant.model.response.ApiControlResponse;
import com.sensus.lc.tenant.model.response.TenantResponse;

/**
 * The Class TenantBCFTest.
 */
@ContextConfiguration(locations = {"classpath:com/sensus/mlc/tenant/tenantbcfimpltest.xml"})
public class TenantBCFTest extends AbstractTestBaseBusiness
{

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.tenantbcfimpl.defaultexception";

	/** The tenant bcf. */
	private ITenantBCF tenantBCF;

	/**
	 * Gets the tenant bcf.
	 * 
	 * @return the tenant bcf
	 */
	public ITenantBCF getTenantBCF()
	{
		return tenantBCF;
	}

	/**
	 * Sets the tenant bcf.
	 * 
	 * @param tenantBCF the new tenant bcf
	 */
	@Resource(name = "tenantBCFTarget")
	public void setTenantBCF(ITenantBCF tenantBCF)
	{
		this.tenantBCF = tenantBCF;
	}

	/**
	 * Sets the tenant area.
	 */
	@Before
	public void setTenantArea()
	{
		setArea(getTenantBCF(), LCAreaEnum.TENANT);
	}

	/**
	 * Reset mocks to tenant area.
	 */
	@After
	public void resetMocksToTenantArea()
	{
		resetMocksData(getTenantBCF());
		setTenantArea();
	}

	/**
	 * Test fetch all tenants.
	 */
	@Test
	public void testFetchAllTenants()
	{
		// Success validation
		TenantResponse response = getTenantBCF().fetchAllTenants();
		assertTrue(response.isOperationSuccess());
		assertNotNull(response);
		assertEquals("Fetch All Tenants OK", 0, response.getMessageList().size());

		// Exception validation
		resetMocksToTenantArea();
		setSituation(getTenantBCF(), SituationsEnum.EXCEPTION, ITenantBCL.class, "fetchAllTenant");
		response = getTenantBCF().fetchAllTenants();
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);
	}

	@Test
	public void testVerifyAPIAccess()
	{
		// Success situation
		TenantRequest request = TestBaseUtil.createTenantRequest();
		ApiControlResponse response = getTenantBCF().verifyAPIAccess(request);
		assertTrue(response.isOperationSuccess());
		assertNotNull(response);
		assertTrue(response.getAllowAPIAccess());

		// Validation situation - tenant id is null
		request = TestBaseUtil.createTenantRequest();
		request.getTenant().setId(null);
		response = getTenantBCF().verifyAPIAccess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, SENSUS_MLC_VALIDATOR_ID_REQUIRED);

		// Exception validation
		resetMocksToTenantArea();
		setSituation(getTenantBCF(), SituationsEnum.EXCEPTION, ITenantBCL.class, "verifyAPIAccess");
		request = TestBaseUtil.createTenantRequest();
		response = getTenantBCF().verifyAPIAccess(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_EXCEPTION_MSG);

	}
}
