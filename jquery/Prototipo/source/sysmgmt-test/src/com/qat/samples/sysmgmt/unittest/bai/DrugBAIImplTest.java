package com.qat.samples.sysmgmt.unittest.bai;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bac.IDrugBAC;
import com.qat.samples.sysmgmt.bai.IDrugBAI;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.DrugPrice;
import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.response.DrugResponse;
import com.qat.samples.sysmgmt.unittest.dac.mybatis.CountyDACTest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/drugbaiimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class DrugBAIImplTest extends AbstractJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(CountyDACTest.class);

	private static final String EXCEPTION = "exception";
	private static final String FAILURE = "failure";
	private static final String FETCH_FAILURE = "fetchFailure";

	private IDrugBAI drugBAI;

	public IDrugBAI getDrugBAI()
	{
		return drugBAI;
	}

	@Resource
	public void setDrugBAI(IDrugBAI drugBAI)
	{
		this.drugBAI = drugBAI;
	}

	@Test
	public void testInsert()
	{
		Drug drug = new Drug("T123456", "1");
		DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), new Date());
		drug.setDrugPrices(Arrays.asList(drugPrice));
		DrugMaintenanceRequest request = new DrugMaintenanceRequest();
		request.setDrug(drug);

		DrugResponse response = getDrugBAI().insertDrug(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Drug drug = new Drug("T123456", "1");
		DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), new Date());
		drug.setDrugPrices(Arrays.asList(drugPrice));
		DrugMaintenanceRequest request = new DrugMaintenanceRequest();
		request.setDrug(drug);

		DrugResponse response = getDrugBAI().updateDrug(request);
		request.setDrug(drug);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertWithValidationError()
	{
		Drug drug = new Drug("T123456", "1");
		// DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), new Date());
		// drug.setDrugPrices(Arrays.asList(drugPrice));
		DrugMaintenanceRequest request = new DrugMaintenanceRequest();
		request.setDrug(drug);

		DrugResponse response = getDrugBAI().insertDrug(request);
		assertTrue(response.getMessageList().size() == 1);
	}

	// @Test
	// public void testInsertWithValidationError()
	// {
	// CountyRequest request = new CountyRequest();
	// County county = new County();
	// // county.setId(123);
	// county.setDescription("Description of county " + county.getId());
	// request.setCounty(county);
	// CountyResponse response = getCountyBAI().insertCounty(request);
	// assertTrue("Should have 1 validation message", response.getMessageList().size() == 1);
	// LOG.info("Validation messages:" + response.getMessageList());
	// }

	public static class MockDrugBAC implements IDrugBAC
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

		private InternalResponse createInternalResponse(Drug drug)
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

		private InternalResultsResponse<Drug> createInternalResultsResponse(Drug drug)
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Drug> response = new InternalResultsResponse<Drug>();
			if (drug != null)
			{
				response.addResult(drug);
			}

			if (FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
			}

			return response;
		}

		@Override
		public InternalResponse insertDrug(Drug drug)
		{
			return createInternalResponse(drug);
		}

		@Override
		public InternalResponse updateDrug(Drug drug)
		{
			return createInternalResponse(drug);
		}

		@Override
		public InternalResponse deleteDrug(Drug drug)
		{
			return createInternalResponse(drug);
		}

		@Override
		public void refreshDrugs(Integer refreshNumber)
		{
		}

		@Override
		public InternalResultsResponse<Drug> fetchDrugByCode(String code)
		{
			Drug drug = new Drug();
			drug.setCode(code);
			return createInternalResultsResponse(drug);
		}

		@Override
		public InternalResultsResponse<Drug> fetchAllDrugs()
		{
			return createFetchResponse();
		}

		@Override
		public InternalResultsResponse<Drug> fetchDrugsByRequest(PagedInquiryRequest request)
		{
			Drug drug = new Drug();
			return createInternalResultsResponse(drug);
		}

		private InternalResultsResponse<Drug> createFetchResponse()
		{
			if (EXCEPTION.equals(getReturnResult()))
			{
				throw new RuntimeException("Boom");
			}

			InternalResultsResponse<Drug> response = new InternalResultsResponse<Drug>();
			if (FETCH_FAILURE.equals(getReturnResult()))
			{
				response.setStatus(Status.UnspecifiedError);
				return response;
			}

			response.addResult(new Drug());
			return response;
		}

	}
}