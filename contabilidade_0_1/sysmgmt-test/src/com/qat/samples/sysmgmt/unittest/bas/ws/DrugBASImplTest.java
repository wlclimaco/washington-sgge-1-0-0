package com.qat.samples.sysmgmt.unittest.bas.ws;

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
import com.qat.samples.sysmgmt.bas.IDrugBAS;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.DrugPrice;
import com.qat.samples.sysmgmt.model.request.DrugMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.response.DrugResponse;
import com.qat.samples.sysmgmt.unittest.dac.mybatis.CountyDACTest;

@ContextConfiguration(locations = {"classpath:conf/web/conf/qat-sysmgmt-base-validators-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/drugbasimpltest.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-resourcebundles-context.xml"})
public class DrugBASImplTest extends AbstractJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(CountyDACTest.class);

	private static final String EXCEPTION = "exception";
	private static final String FAILURE = "failure";
	private static final String FETCH_FAILURE = "fetchFailure";

	private IDrugBAS drugBAS;

	public IDrugBAS getDrugBAS()
	{
		return drugBAS;
	}

	@Resource
	public void setDrugBAS(IDrugBAS drugBAS)
	{
		this.drugBAS = drugBAS;
	}

	@Test
	public void testInsert()
	{
		Drug drug = new Drug("T123456", "1");
		DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), new Date());
		drug.setDrugPrices(Arrays.asList(drugPrice));
		DrugMaintenanceRequest request = new DrugMaintenanceRequest(drug, false, false);
		request.setDrug(drug);

		DrugResponse response = getDrugBAS().insertDrug(request);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testUpdate()
	{
		Drug drug = new Drug("T123456", "1");
		DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), new Date());
		drug.setDrugPrices(Arrays.asList(drugPrice));
		DrugMaintenanceRequest request = new DrugMaintenanceRequest(drug, false, false);
		DrugResponse response = getDrugBAS().updateDrug(request);
		request.setDrug(drug);
		assertTrue(response.getMessageList().isEmpty());
	}

	@Test
	public void testInsertWithValidationError()
	{
		Drug drug = new Drug("T123456", "1");
		// DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), new Date());
		// drug.setDrugPrices(Arrays.asList(drugPrice));
		DrugMaintenanceRequest request = new DrugMaintenanceRequest(drug, false, false);

		DrugResponse response = getDrugBAS().insertDrug(request);
		assertTrue(response.getMessageList().size() == 1);
	}

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
			// TODO Auto-generated method stub
			return null;
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