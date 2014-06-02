package com.qat.samples.sysmgmt.unittest.bas.ws;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IBundleBAC;
import com.qat.samples.sysmgmt.bas.IBundleBAS;
import com.qat.samples.sysmgmt.model.Bundle;
import com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.BundleResponse;
import com.qat.samples.sysmgmt.unittest.dac.mybatis.BundleDACTest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/bundlebasimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class BundleBASImplTest extends AbstractJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(BundleDACTest.class);

	private static final String EXCEPTION = "exception";
	private static final String FAILURE = "failure";
	private static final String FETCH_FAILURE = "fetchFailure";

	private IBundleBAS bundleBAS;

	public IBundleBAS getBundleBAS()
	{
		return bundleBAS;
	}

	@Resource
	public void setBundleBAS(IBundleBAS bundleBAS)
	{
		this.bundleBAS = bundleBAS;
	}

	@Test
	public void testInsert()
	{
		Bundle bundle = new Bundle(1, "T123456", "1", new BigDecimal(10.00));
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		request.setBundle(bundle);

		BundleResponse response = getBundleBAS().insertBundle(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Bundle bundle = new Bundle(1, "T123456", "1", new BigDecimal(10.00));
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAS().updateBundle(request);
		request.setBundle(bundle);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertWithValidationError()
	{
		Bundle bundle = new Bundle(1, null, "1", new BigDecimal(10.00));
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);

		BundleResponse response = getBundleBAS().insertBundle(request);
		assertTrue(response.getMessageList().size() == 1);
	}

	@Test
	public void deleteBundle()
	{
		Bundle bundle = new Bundle(1);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);

		BundleResponse response = getBundleBAS().deleteBundle(request);
		assertTrue(response.isOperationSuccess());
	}

	@Test
	public void refreshBundles()
	{
		RefreshRequest request = new RefreshRequest(4, false, false);
		BundleResponse response = getBundleBAS().refreshBundles(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void fetchAllBundles()
	{
		BundleResponse response = getBundleBAS().fetchAllBundles(new FetchAllRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void fetchBundleById()
	{
		BundleResponse response = getBundleBAS().fetchBundleById(new FetchByIdRequest(1));
		assertTrue(response.getMessageList().isEmpty());
	}

	public static class MockBundleBAC implements IBundleBAC
	{
		private static String returnResult;

		public static String getReturnResult()
		{
			return returnResult;
		}

		public static void setReturnResult(String newValue)
		{
			returnResult = newValue;
		}

		private InternalResponse createInternalResponse(Bundle bundle)
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResponse response = new InternalResponse();
			if (FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
			}

			return response;
		}

		private InternalResultsResponse<Bundle> createInternalResultsResponse(Bundle bundle)
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Bundle> response = new InternalResultsResponse<Bundle>();
			if (bundle != null)
			{
				response.addResult(bundle);
			}

			if (FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
			}

			return response;
		}

		@Override
		public InternalResponse insertBundle(Bundle bundle)
		{
			return createInternalResponse(bundle);
		}

		@Override
		public InternalResponse updateBundle(Bundle bundle)
		{
			return createInternalResponse(bundle);
		}

		@Override
		public InternalResponse deleteBundle(Bundle bundle)
		{
			return createInternalResponse(bundle);
		}

		@Override
		public void refreshBundles(Integer refreshNumber)
		{

		}

		@Override
		public InternalResultsResponse<Bundle> fetchAllBundles()
		{
			return createFetchResponse();
		}

		@Override
		public InternalResultsResponse<Bundle> fetchBundlesByRequest(PagedInquiryRequest request)
		{
			// TODO Auto-generated method stub
			return null;
		}

		private InternalResultsResponse<Bundle> createFetchResponse()
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Bundle> response = new InternalResultsResponse<Bundle>();
			if (FETCH_FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new Bundle());
			return response;
		}

		@Override
		public InternalResultsResponse<Bundle> fetchBundleById(FetchByIdRequest request)
		{
			// TODO Auto-generated method stub
			return null;
		}

	}
}