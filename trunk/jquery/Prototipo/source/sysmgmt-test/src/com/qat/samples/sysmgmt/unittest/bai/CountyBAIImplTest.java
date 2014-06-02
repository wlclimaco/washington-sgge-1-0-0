package com.qat.samples.sysmgmt.unittest.bai;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.ICountyBAC;
import com.qat.samples.sysmgmt.bai.ICountyBAI;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.response.CountyResponse;
import com.qat.samples.sysmgmt.unittest.dac.mybatis.CountyDACTest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/countybaiimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class CountyBAIImplTest extends AbstractJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(CountyDACTest.class);

	private static final String EXCEPTION = "exception";
	private static final String FAILURE = "failure";
	private static final String FETCH_FAILURE = "fetchFailure";

	private ICountyBAI countyBAI;

	public ICountyBAI getCountyBAI()
	{
		return countyBAI;
	}

	@Resource
	public void setCountyBAI(ICountyBAI countyBAI)
	{
		this.countyBAI = countyBAI;
	}

	@Test
	public void testInsert()
	{
		CountyMaintenanceRequest request = new CountyMaintenanceRequest();
		County county = new County();
		county.setId(123);
		county.setDescription("Description of county " + county.getId());
		request.setCounty(county);
		CountyResponse response = getCountyBAI().insertCounty(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		CountyMaintenanceRequest request = new CountyMaintenanceRequest();
		County county = new County();
		county.setId(123);
		county.setDescription("Description of county updated:" + county.getId());
		request.setCounty(county);
		request.setReturnList(false);
		CountyResponse response = getCountyBAI().updateCounty(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertWithValidationError()
	{
		CountyMaintenanceRequest request = new CountyMaintenanceRequest();
		County county = new County();
		county.setDescription("Description of county " + county.getId());
		request.setCounty(county);
		CountyResponse response = getCountyBAI().insertCounty(request);
		assertTrue("Should have 1 validation message", response.getMessageList().size() == 1);
		LOG.info("Validation messages:" + response.getMessageList());
	}

	public static class MockCountyBAC implements ICountyBAC
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

		private InternalResponse createInternalResponse(County county)
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

		private InternalResultsResponse<County> createInternalResultsResponse(County county)
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<County> response = new InternalResultsResponse<County>();
			if (county != null)
			{
				response.addResult(county);
			}

			if (FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
			}

			return response;
		}

		@Override
		public InternalResponse insertCounty(County county)
		{
			return createInternalResponse(county);
		}

		@Override
		public InternalResponse updateCounty(County county)
		{
			return createInternalResponse(county);
		}

		@Override
		public InternalResponse deleteCounty(County county)
		{
			return createInternalResponse(county);
		}

		@Override
		public void refreshCounties(Integer refreshNumber)
		{

		}

		@Override
		public InternalResultsResponse<County> fetchCountyById(FetchByIdRequest request)
		{
			County county = new County();
			county.setId(request.getFetchId());
			return createInternalResultsResponse(county);
		}

		@Override
		public InternalResultsResponse<County> fetchAllCounties()
		{
			return createFetchResponse();
		}

		@Override
		public InternalResultsResponse<County> fetchCountiesByRequest(PagedInquiryRequest request)
		{
			County county = new County();
			return createInternalResultsResponse(county);
		}

		private InternalResultsResponse<County> createFetchResponse()
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<County> response = new InternalResultsResponse<County>();
			if (FETCH_FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new County());
			return response;
		}

	}
}