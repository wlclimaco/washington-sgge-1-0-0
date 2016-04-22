package com.qat.samples.sysmgmt.bar.mybatis;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.BaseModel;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.IDrugBAR;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.DrugPrice;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/drugbartest-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class DrugBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(DrugBARTest.class);
	private IDrugBAR drugBAR; // injected by Spring through setter @Resource

	// below

	public IDrugBAR getDrugBAR()
	{
		return drugBAR;
	}

	@Resource
	public void setDrugBAR(IDrugBAR drugBAR)
	{
		this.drugBAR = drugBAR;
	}

	@Test
	public void testDeleteDrug() throws Exception
	{
		Drug drug = new Drug("D1234567y", "1");
		DrugPrice drugPrice = new DrugPrice("D1234567y", "B", new BigDecimal(10.00), System.currentTimeMillis());
		drug.setDrugPrices(Arrays.asList(drugPrice));
		getDrugBAR().deleteDrug(drug);
		Drug drugResponse = getDrugBAR().fetchDrugByCode(drug.getCode());
		Assert.assertEquals(drugResponse, null);
		drug = new Drug("D1234567z", "1");
		getDrugBAR().deleteDrug(drug);
		drugResponse = getDrugBAR().fetchDrugByCode(drug.getCode());
		Assert.assertEquals(drugResponse, null);
	}

	@Test
	public void testInsertDrug() throws Exception
	{
		Drug drug = new Drug("T123456", "1");
		DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), System.currentTimeMillis());
		drug.setDrugPrices(Arrays.asList(drugPrice));
		Drug drugResponse = getDrugBAR().fetchDrugByCode(drug.getCode());
		Assert.assertEquals(drugResponse, null);
		getDrugBAR().insertDrug(drug);
		drugResponse = getDrugBAR().fetchDrugByCode(drug.getCode());
		Assert.assertEquals(drug.getCode(), drugResponse.getCode());
	}

	@Test
	public void testUpdatetDrugDeleteDrugPrice() throws Exception
	{
		Drug drug = new Drug("P123456", "Neosporin Ointment");
		drug.setModelAction(BaseModel.PersistenceActionEnum.UPDATE);
		DrugPrice drugPrice = new DrugPrice("P123456", "B", new BigDecimal(10.00), System.currentTimeMillis());
		drugPrice.setModelAction(BaseModel.PersistenceActionEnum.DELETE);
		drug.setDrugPrices(Arrays.asList(drugPrice));
		getDrugBAR().updateDrug(drug);
		Drug drugResponse = getDrugBAR().fetchDrugByCode(drug.getCode());
		Assert.assertEquals(drugResponse.getDescription(), drug.getDescription());
		Assert.assertEquals(drugResponse.getDrugPrices().size(), 0);
	}

	@Test
	public void testUpdatetDrugUpdateDrugPrice() throws Exception
	{
		Drug drug = new Drug("P123456", "Neosporin Ointment");
		drug.setModelAction(BaseModel.PersistenceActionEnum.UPDATE);
		DrugPrice drugPrice = new DrugPrice("P123456", "B", new BigDecimal(10.00), System.currentTimeMillis());
		drugPrice.setModelAction(BaseModel.PersistenceActionEnum.UPDATE);
		drug.setDrugPrices(Arrays.asList(drugPrice));
		getDrugBAR().updateDrug(drug);
		Drug drugResponse = getDrugBAR().fetchDrugByCode(drug.getCode());
		Assert.assertEquals(drugResponse.getDescription(), drug.getDescription());
		Assert.assertEquals(drugResponse.getDrugPrices().get(0).getEffectiveDateUTC(), drugPrice.getEffectiveDateUTC());
	}

	@Test
	public void testUpdatetDrugInsertDrugPrice() throws Exception
	{
		Drug drug = new Drug("P123457", "Equate Ointment");
		drug.setModelAction(BaseModel.PersistenceActionEnum.UPDATE);
		DrugPrice drugPrice = new DrugPrice("P123457", "F", new BigDecimal(10.00), System.currentTimeMillis());
		drugPrice.setModelAction(BaseModel.PersistenceActionEnum.INSERT);
		drug.setDrugPrices(Arrays.asList(drugPrice));
		getDrugBAR().updateDrug(drug);
		Drug drugResponse = getDrugBAR().fetchDrugByCode(drug.getCode());
		Assert.assertEquals(drugResponse.getDescription(), drug.getDescription());
		Assert.assertEquals(drugResponse.getDrugPrices().size(), 2);
	}

	@Test
	public void testFetchAllNDCs()
	{
		List<Drug> response = getDrugBAR().fetchAllDrugs();
		equals(response.size() > 1);
	}

	@Test
	public void testFetchDrugsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Drug> response = getDrugBAR().fetchDrugsByRequest(request);
		Assert.assertFalse(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check next page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(4);
		response = getDrugBAR().fetchDrugsByRequest(request);
		Assert.assertFalse(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Drug> response2 = getDrugBAR().fetchDrugsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDrugBAR().deleteAllDrugs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Drug> response3 = getDrugBAR().fetchDrugsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Test
	public void testDeleteAll()
	{
		getDrugBAR().deleteAllDrugs();
		List<Drug> response = getDrugBAR().fetchAllDrugs();
		equals(response.isEmpty());
	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertDrug.sql", false);
	}
}
