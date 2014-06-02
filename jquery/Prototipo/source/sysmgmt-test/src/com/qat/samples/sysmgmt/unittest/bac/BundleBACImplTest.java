package com.qat.samples.sysmgmt.unittest.bac;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bac.IBundleBAC;
import com.qat.samples.sysmgmt.dac.IBundleDAC;
import com.qat.samples.sysmgmt.model.Bundle;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/bundlebacimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class BundleBACImplTest extends AbstractJUnit4SpringContextTests
{

	private IBundleBAC bundleBAC;

	public IBundleBAC getBundleBAC()
	{
		return bundleBAC;
	}

	@Resource
	public void setBundleBAC(IBundleBAC bundleBAC)
	{
		this.bundleBAC = bundleBAC;
	}

	@Test
	public void testInsertBundle()
	{
		Bundle bundle = new Bundle(1, "pc88", "desc88", null);
		InternalResponse response = getBundleBAC().insertBundle(bundle);
		assertNotNull(response);
		assertNotNull(bundle.getPrice());
		assertNotNull(bundle.getId());
	}

	@Test
	public void testUpdateBundle()
	{
		assertNotNull(getBundleBAC().updateBundle(new Bundle(1, "pc89", "desc89", null)));
	}

	@Test
	public void testUpdateBundleError()
	{
		assertNotNull(getBundleBAC().updateBundle(new Bundle(1, null, "desc89", null)));
	}

	@Test
	public void testDeleteBundle()
	{
		assertNotNull(getBundleBAC().deleteBundle(new Bundle()));
	}

	@Test
	public void testFetchAllBundle()
	{
		InternalResultsResponse<Bundle> results = getBundleBAC().fetchAllBundles();
		assertNotNull(results);
		assertNotNull(results.getResultsList());
	}

	@Test
	public void testFetchBundlesByRequest()
	{
		InternalResultsResponse<Bundle> results = getBundleBAC().fetchBundlesByRequest(new PagedInquiryRequest());
		assertNotNull(results);
		assertNotNull(results.getResultsList());
	}

	@Test
	public void testFetchBundleById()
	{
		assertNotNull(getBundleBAC().fetchBundleById(new FetchByIdRequest()));
	}

	@Test
	public void testRefreshBundles()
	{
		getBundleBAC().refreshBundles(4);

	}

	@Test
	public void testRefreshBundlesLessThanOne()
	{
		getBundleBAC().refreshBundles(0);
	}

	public static class MockBundleDAC implements IBundleDAC
	{
		@Override
		public InternalResponse deleteAllBundles()
		{
			return new InternalResponse();
		}

		@Override
		public InternalResponse deleteBundle(Bundle bundle)
		{
			return new InternalResponse();
		}

		@Override
		public List<Bundle> fetchAllBundles()
		{
			List<Bundle> results = new ArrayList<Bundle>();
			results.add(new Bundle());
			results.add(new Bundle());
			results.add(new Bundle());
			results.add(new Bundle());
			return results;
		}

		@Override
		public InternalResponse insertBundle(Bundle bundle)
		{
			bundle.setId(100);
			return new InternalResponse();
		}

		@Override
		public InternalResponse updateBundle(Bundle bundle)
		{
			if (ValidationUtil.isNullOrEmpty(bundle.getCode()))
			{
				return new InternalResponse(Status.ExternalError);
			}
			else
			{
				return new InternalResponse();
			}
		}

		@Override
		public InternalResultsResponse<Bundle> fetchBundlesByRequest(PagedInquiryRequest request)
		{
			InternalResultsResponse<Bundle> results = new InternalResultsResponse<Bundle>();
			results.addResults(fetchAllBundles());
			return results;
		}

		@Override
		public Bundle fetchBundleById(FetchByIdRequest request)
		{
			Bundle bundle = new Bundle();
			return bundle;
		}

	}
}
