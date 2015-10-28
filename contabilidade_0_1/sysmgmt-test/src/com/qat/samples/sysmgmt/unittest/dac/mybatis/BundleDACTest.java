package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dac.IBundleDAC;
import com.qat.samples.sysmgmt.model.Bundle;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class BundleDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(BundleDACTest.class);
	private IBundleDAC bundleDAC; // injected by Spring through setter below

	public IBundleDAC getBundleDAC()
	{
		return bundleDAC;
	}

	@Resource
	public void setBundleDAC(IBundleDAC newValue)
	{
		bundleDAC = newValue;
	}

	@Test
	public void testInsertBundle() throws Exception
	{
		getBundleDAC().deleteAllBundles();
		Bundle bundle = createBundle();
		getBundleDAC().insertBundle(bundle);
		FetchByIdRequest request = createFetchByIdRequest(bundle.getId());
		Bundle response = getBundleDAC().fetchBundleById(request);
		assertEquals(bundle.getId(), response.getId());
		assertEquals(bundle.getDescription(), response.getDescription());
		assertEquals(bundle.getCode(), response.getCode());
	}

	@Test
	public void testUpdateBundle() throws Exception
	{
		getBundleDAC().deleteAllBundles();
		Bundle bundle = createBundle();
		getBundleDAC().insertBundle(bundle);
		FetchByIdRequest request = createFetchByIdRequest(bundle.getId());
		bundle.setDescription("NewDescription");
		getBundleDAC().updateBundle(bundle);
		Bundle response = getBundleDAC().fetchBundleById(request);
		assertEquals(bundle.getId(), response.getId());
		assertEquals(bundle.getDescription(), response.getDescription());
	}

	@Test
	public void testDeleteAll() throws Exception
	{
		getBundleDAC().deleteAllBundles();
		assertTrue(getBundleDAC().fetchAllBundles().isEmpty());
	}

	@Test
	public void testFetchBundlesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Bundle> response = getBundleDAC().fetchBundlesByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check next page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(4);
		response = getBundleDAC().fetchBundlesByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Bundle> response2 = getBundleDAC().fetchBundlesByRequest(request2);
		assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getBundleDAC().deleteAllBundles();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Bundle> response3 = getBundleDAC().fetchBundlesByRequest(request3);
		assertTrue(response3.getStatus() == Status.NoRowsFoundError);

	}

	@Test
	public void testDeleteBundle() throws Exception
	{
		Bundle bundle = createBundle();
		getBundleDAC().insertBundle(bundle);
		FetchByIdRequest request = createFetchByIdRequest(bundle.getId());
		assertNotNull(getBundleDAC().fetchBundleById(request));
		getBundleDAC().deleteBundle(bundle);
		assertNull(getBundleDAC().fetchBundleById(request));
	}

	private Bundle createBundle()
	{
		Bundle bundle = new Bundle();
		bundle.setCode("Code");
		bundle.setDescription("Description");
		bundle.setPrice(new BigDecimal(12));
		return bundle;
	}

	private FetchByIdRequest createFetchByIdRequest(Integer value)
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(value);
		return request;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBundle.sql", false);
	}

}
