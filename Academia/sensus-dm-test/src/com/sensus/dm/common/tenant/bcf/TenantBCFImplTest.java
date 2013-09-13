package com.sensus.dm.common.tenant.bcf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.sensus.dm.common.tenant.bcl.ITenantBCL;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.common.tenant.model.response.TenantResponse;
import com.sensus.dm.common.util.AbstractTestBaseBusiness;
import com.sensus.dm.common.util.EPMAreaEnum;
import com.sensus.dm.common.util.SituationsEnum;
import com.sensus.dm.common.util.TestBaseUtil;

/**
 * The Class TenantBCFImplTest.
 * 
 * @author QAT Brazil.
 */
@ContextConfiguration(locations = {
		"classpath:com/sensus/dm/common/tenant/tenantbcfimpltest.xml"})
public class TenantBCFImplTest extends AbstractTestBaseBusiness
{

	/** The Constant FETCH_TENANT_DESCRIPTION. */
	private static final String FETCH_TENANT_DESCRIPTION = "fetchTenantDescription";

	/** The Constant DEFAULT_TENANT_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_TENANT_BCF_EXCEPTION_MSG = "sensus.dm.common.tenantbcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The tenant bcf. */
	private ITenantBCF tenantBCF; // injected by Spring through setter

	/**
	 * Sets the enant bcf.
	 * 
	 * @param iTenantBCFParam the new enant bcf
	 */
	@Resource
	public void setenantBCF(ITenantBCF iTenantBCFParam)
	{
		tenantBCF = iTenantBCFParam;
	}

	/**
	 * Gets the tenant bcf.
	 * 
	 * @return the tenant bcf
	 */
	public ITenantBCF getTenantBCF()
	{
		return tenantBCF;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Test Settings:

	/**
	 * Sets the tenant area.
	 */
	public void setTenantArea()
	{
		setArea(getTenantBCF(), EPMAreaEnum.TENANT);
	}

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		setTenantArea();
	}

	/**
	 * Removes the tenant area.
	 */
	@After
	public void resetMocksToTenantArea()
	{
		resetMocksData(getTenantBCF());
		setTenantArea();
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Tests:

	/**
	 * Test fetch tenant description.
	 */
	@Test
	public void testFetchTenantDescription()
	{
		// Validation with fail situation - tenant required
		TenantResponse response =
				getTenantBCF().fetchTenantDescription(new TenantRequest());
		assertFalse(response.isOperationSuccess());
		assertMessages(response, TENANT_REQUIRED);

		// Validation with fail situation - customer id required
		TenantRequest request = new TenantRequest();
		request.setTenant(TestBaseUtil.createEmptyTenant());

		response = getTenantBCF().fetchTenantDescription(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, CUSTOMER_ID_REQUIRED);

		// Success Situation
		request = TestBaseUtil.createTenantRequest();
		response = getTenantBCF().fetchTenantDescription(request);

		assertTrue(response.isOperationSuccess());
		assertEquals("should bring one tenant ", 1, response.getTenant().size());
		assertNotNull("must be the KEY", response.getTenant().get(0).getKey());

		// Error situation
		setSituation(getTenantBCF(), SituationsEnum.ERROR, ITenantBCL.class,
				FETCH_TENANT_DESCRIPTION);
		response = getTenantBCF().fetchTenantDescription(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, ERROR_CODE);

		resetMocksToTenantArea();

		// Exception situation
		setSituation(getTenantBCF(), SituationsEnum.EXCEPTION, ITenantBCL.class,
				FETCH_TENANT_DESCRIPTION);
		response = getTenantBCF().fetchTenantDescription(request);
		assertFalse(response.isOperationSuccess());
		assertMessages(response, DEFAULT_TENANT_BCF_EXCEPTION_MSG);
	}

}
