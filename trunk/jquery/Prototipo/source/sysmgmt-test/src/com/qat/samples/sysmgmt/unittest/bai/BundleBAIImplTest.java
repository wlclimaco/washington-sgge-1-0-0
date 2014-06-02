package com.qat.samples.sysmgmt.unittest.bai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IBundleBAC;
import com.qat.samples.sysmgmt.bai.IBundleBAI;
import com.qat.samples.sysmgmt.model.Bundle;
import com.qat.samples.sysmgmt.model.request.BundleMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.BundleResponse;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/bundlebaiimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class BundleBAIImplTest extends AbstractJUnit4SpringContextTests
{

	private IBundleBAI bundleBAI;

	private static final String ID_REQUIRED = "sysmgmt.base.bundlevalidator.id.required";
	private static final String BUNDLEDESC_REQUIRED = "sysmgmt.base.bundlevalidator.bundledesc.required";
	private static final String BUNDLECODE_REQUIRED = "sysmgmt.base.bundlevalidator.bundlecode.required";
	private static final String DESCRIPTION_VALUE = "description";
	private static final String CODE_VALUE = "code";
	private static final String FETCH_FAILURE = "fetchFailure";
	private static final String FAILURE = "failure";
	private static final String BAC_KEY = "sysmgmt.base.validator.id.required";
	private static final String BAID_KEY = "sysmgmt.base.bundlebaidimpl.defaultexception";
	private static final String BAI_KEY = "sysmgmt.base.bundlebaiimpl.defaultexception";
	private static final String EXCEPTION = "exception";

	public IBundleBAI getBundleBAI()
	{
		return bundleBAI;
	}

	@Resource
	public void setBundleBAI(IBundleBAI bundleBAI)
	{
		this.bundleBAI = bundleBAI;
	}

	@Test
	public void testInsert()
	{
		Bundle bundle = new Bundle();
		bundle.setCode(CODE_VALUE);
		bundle.setDescription(DESCRIPTION_VALUE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, true, true);
		BundleResponse response = getBundleBAI().insertBundle(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testFetchAll()
	{
		BundleResponse response = getBundleBAI().fetchAllBundles(new FetchAllRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testFetchByRequest()
	{
		BundleResponse response = getBundleBAI().fetchBundlesByRequest(new PagedInquiryRequest());
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testRefresh()
	{
		RefreshRequest request = new RefreshRequest(4, false, false);
		BundleResponse response = getBundleBAI().refreshBundles(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Bundle bundle = new Bundle();
		bundle.setId(1);
		bundle.setCode(CODE_VALUE);
		bundle.setDescription(DESCRIPTION_VALUE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, true, false);
		BundleResponse response = getBundleBAI().updateBundle(request);
		assertTrue(response.getMessageList().isEmpty());
		assertFalse(response.getBundles().isEmpty());
	}

	@Test
	public void testInsertNoBundleCode()
	{
		Bundle bundle = new Bundle();
		bundle.setDescription(DESCRIPTION_VALUE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().insertBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLECODE_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testInsertNoBundleDescription()
	{
		Bundle bundle = new Bundle();
		bundle.setCode(CODE_VALUE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().insertBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLEDESC_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoBundleId()
	{
		Bundle bundle = new Bundle();
		bundle.setCode(CODE_VALUE);
		bundle.setDescription(DESCRIPTION_VALUE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().updateBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(ID_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteNoBundleId()
	{
		Bundle bundle = new Bundle();
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().deleteBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(ID_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdNoBundleId()
	{
		new FetchByIdRequest();
	}

	@Test
	public void testUpdateNoBundleCode()
	{
		Bundle bundle = new Bundle();
		bundle.setId(1);
		bundle.setDescription(DESCRIPTION_VALUE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().updateBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLECODE_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateNoBundleDescription()
	{
		Bundle bundle = new Bundle();
		bundle.setId(1);
		bundle.setCode(CODE_VALUE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().updateBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BUNDLEDESC_REQUIRED, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testInsertFailure()
	{
		Bundle bundle = new Bundle();
		bundle.setCode(CODE_VALUE);
		bundle.setDescription(DESCRIPTION_VALUE);
		MockBundleBAC.setReturnResult(FAILURE);
		MockBundleBAC.setFailureMessageCode(BAC_KEY);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().insertBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteFailure()
	{
		Bundle bundle = new Bundle();
		bundle.setId(1);
		MockBundleBAC.setReturnResult(FAILURE);
		MockBundleBAC.setFailureMessageCode(BAC_KEY);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().deleteBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdFailure()
	{

		FetchByIdRequest request = new FetchByIdRequest(new Integer(1));
		MockBundleBAC.setReturnResult(FETCH_FAILURE);
		MockBundleBAC.setFailureMessageCode(BAC_KEY);
		BundleResponse response = getBundleBAI().fetchBundleById(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchById()
	{
		new FetchByIdRequest(1);
	}

	@Test
	public void testDelete()
	{
		Bundle bundle = new Bundle();
		bundle.setId(1);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().deleteBundle(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertException()
	{
		Bundle bundle = new Bundle();
		bundle.setCode(CODE_VALUE);
		bundle.setDescription(DESCRIPTION_VALUE);
		MockBundleBAC.setReturnResult(EXCEPTION);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().insertBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testDeleteException()
	{
		Bundle bundle = new Bundle();
		bundle.setId(1);
		MockBundleBAC.setReturnResult(EXCEPTION);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().deleteBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(1);
		MockBundleBAC.setReturnResult(EXCEPTION);
		BundleResponse response = getBundleBAI().fetchBundleById(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAllException()
	{
		FetchAllRequest request = new FetchAllRequest();
		MockBundleBAC.setReturnResult(EXCEPTION);
		BundleResponse response = getBundleBAI().fetchAllBundles(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchBundlesByRequestException()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		MockBundleBAC.setReturnResult(EXCEPTION);
		BundleResponse response = getBundleBAI().fetchBundlesByRequest(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testRefreshException()
	{
		RefreshRequest request = new RefreshRequest(4, true, false);
		MockBundleBAC.setReturnResult(EXCEPTION);
		BundleResponse response = getBundleBAI().refreshBundles(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchBundlesByRequestFailure()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		MockBundleBAC.setReturnResult(FETCH_FAILURE);
		BundleResponse response = getBundleBAI().fetchBundlesByRequest(request);
		assertEquals(2, response.getMessageList().size());
		assertEquals(BAID_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testFetchAllFailure()
	{
		FetchAllRequest request = new FetchAllRequest();
		MockBundleBAC.setReturnResult(FETCH_FAILURE);
		BundleResponse response = getBundleBAI().fetchAllBundles(request);
		assertEquals(2, response.getMessageList().size());
		assertEquals(BAID_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateFailure()
	{
		Bundle bundle = new Bundle();
		bundle.setId(1);
		bundle.setCode(CODE_VALUE);
		bundle.setDescription(DESCRIPTION_VALUE);
		MockBundleBAC.setReturnResult(FAILURE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		BundleResponse response = getBundleBAI().updateBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Test
	public void testUpdateException()
	{
		Bundle bundle = new Bundle();
		bundle.setId(1);
		bundle.setCode(CODE_VALUE);
		bundle.setDescription(DESCRIPTION_VALUE);
		BundleMaintenanceRequest request = new BundleMaintenanceRequest(bundle, false, false);
		MockBundleBAC.setReturnResult(EXCEPTION);
		BundleResponse response = getBundleBAI().updateBundle(request);
		assertEquals(1, response.getMessageList().size());
		assertEquals(BAI_KEY, response.getMessageList().get(0).getMessageInfo().getCode());
	}

	@Before
	public void setUp()
	{
		MockBundleBAC.setReturnResult(null);
	}

	public static class MockBundleBAC implements IBundleBAC
	{
		private static String returnResult;
		private static String failureMessageCode;

		public static String getReturnResult()
		{
			return returnResult;
		}

		public static void setReturnResult(String newValue)
		{
			returnResult = newValue;
		}

		public static String getFailureMessageCode()
		{
			return failureMessageCode;
		}

		public static void setFailureMessageCode(String failureMessageCode)
		{
			MockBundleBAC.failureMessageCode = failureMessageCode;
		}

		@Override
		public InternalResponse deleteBundle(Bundle bundle)
		{
			return createInternalResponse(bundle);
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
				response.addMessage(getFailureMessageCode(), MessageSeverity.Error, MessageLevel.Other);
				response.setStatus(Status.UnspecifiedError);
			}
			return response;
		}

		@Override
		public InternalResultsResponse<Bundle> fetchAllBundles()
		{
			return createFetchResponse();
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
				response.addMessage(getFailureMessageCode(), MessageSeverity.Error, MessageLevel.Other);
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new Bundle());
			return response;
		}

		@Override
		public InternalResultsResponse<Bundle> fetchBundlesByRequest(PagedInquiryRequest request)
		{
			return createFetchResponse();
		}

		@Override
		public InternalResponse insertBundle(Bundle bundle)
		{
			return createInternalResponse(bundle);
		}

		@Override
		public void refreshBundles(Integer count)
		{

		}

		@Override
		public InternalResponse updateBundle(Bundle bundle)
		{
			return createInternalResponse(bundle);
		}

		@Override
		public InternalResultsResponse<Bundle> fetchBundleById(FetchByIdRequest request)
		{
			return createFetchResponse();
		}

	}
}
