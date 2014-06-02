package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

import com.qat.framework.model.QATModel;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dac.IDrugDAC;
import com.qat.samples.sysmgmt.model.Drug;
import com.qat.samples.sysmgmt.model.DrugPrice;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class DrugDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(DrugDACTest.class);
	private IDrugDAC drugDAC; // injected by Spring through setter @resource

	// below

	public IDrugDAC getDrugDAC()
	{
		return drugDAC;
	}

	@Resource
	public void setDrugDAC(IDrugDAC drugDAC)
	{
		this.drugDAC = drugDAC;
	}

	@Test
	public void testDeleteDrug() throws Exception
	{
		Drug drug = new Drug("D1234567y", "1");
		DrugPrice drugPrice = new DrugPrice("D1234567y", "B", new BigDecimal(10.00), new Date());
		drug.setDrugPrices(Arrays.asList(drugPrice));
		getDrugDAC().deleteDrug(drug);
		Drug drugResponse = getDrugDAC().fetchDrugByCode(drug.getCode());
		assertEquals(drugResponse, null);
		drug = new Drug("D1234567z", "1");
		getDrugDAC().deleteDrug(drug);
		drugResponse = getDrugDAC().fetchDrugByCode(drug.getCode());
		assertEquals(drugResponse, null);
	}

	@Test
	public void testInsertDrug() throws Exception
	{
		Drug drug = new Drug("T123456", "1");
		DrugPrice drugPrice = new DrugPrice("T123456", "F", new BigDecimal(10.00), new Date());
		drug.setDrugPrices(Arrays.asList(drugPrice));
		Drug drugResponse = getDrugDAC().fetchDrugByCode(drug.getCode());
		assertEquals(drugResponse, null);
		getDrugDAC().insertDrug(drug);
		drugResponse = getDrugDAC().fetchDrugByCode(drug.getCode());
		assertEquals(drug.getCode(), drugResponse.getCode());
	}

	@Test
	public void testUpdatetDrugDeleteDrugPrice() throws Exception
	{
		Drug drug = new Drug("P123456", "Neosporin Ointment");
		drug.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		DrugPrice drugPrice = new DrugPrice("P123456", "B", new BigDecimal(10.00), new Date());
		drugPrice.setModelAction(QATModel.PersistanceActionEnum.DELETE);
		drug.setDrugPrices(Arrays.asList(drugPrice));
		getDrugDAC().updateDrug(drug);
		Drug drugResponse = getDrugDAC().fetchDrugByCode(drug.getCode());
		assertEquals(drugResponse.getDescription(), drug.getDescription());
		assertEquals(drugResponse.getDrugPrices().size(), 0);
	}

	@Test
	public void testUpdatetDrugUpdateDrugPrice() throws Exception
	{
		Drug drug = new Drug("P123456", "Neosporin Ointment");
		drug.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		DrugPrice drugPrice = new DrugPrice("P123456", "B", new BigDecimal(10.00), new Date());
		drugPrice.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		drug.setDrugPrices(Arrays.asList(drugPrice));
		getDrugDAC().updateDrug(drug);
		Drug drugResponse = getDrugDAC().fetchDrugByCode(drug.getCode());
		assertEquals(drugResponse.getDescription(), drug.getDescription());
		assertEquals(drugResponse.getDrugPrices().get(0).getEffectiveDate(), drugPrice.getEffectiveDate());
	}

	@Test
	public void testUpdatetDrugInsertDrugPrice() throws Exception
	{
		Drug drug = new Drug("P123457", "Equate Ointment");
		drug.setModelAction(QATModel.PersistanceActionEnum.UPDATE);
		DrugPrice drugPrice = new DrugPrice("P123457", "F", new BigDecimal(10.00), new Date());
		drugPrice.setModelAction(QATModel.PersistanceActionEnum.INSERT);
		drug.setDrugPrices(Arrays.asList(drugPrice));
		getDrugDAC().updateDrug(drug);
		Drug drugResponse = getDrugDAC().fetchDrugByCode(drug.getCode());
		assertEquals(drugResponse.getDescription(), drug.getDescription());
		assertEquals(drugResponse.getDrugPrices().size(), 2);
	}

	@Test
	public void testFetchAllNDCs()
	{
		List<Drug> response = getDrugDAC().fetchAllDrugs();
		equals(response.size() > 1);
	}

	@Test
	public void testFetchProceduresByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Drug> response = getDrugDAC().fetchDrugsByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check next page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(4);
		response = getDrugDAC().fetchDrugsByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Drug> response2 = getDrugDAC().fetchDrugsByRequest(request2);
		assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDrugDAC().deleteAllDrugs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Drug> response3 = getDrugDAC().fetchDrugsByRequest(request3);
		assertTrue(response3.getStatus() == Status.NoRowsFoundError);

	}

	@Test
	public void testDeleteAll()
	{
		getDrugDAC().deleteAllDrugs();
		List<Drug> response = getDrugDAC().fetchAllDrugs();
		equals(response.isEmpty());
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertDrug.sql", false);
	}
}
