package com.qat.samples.sysmgmt.bar.mybatis;


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

import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Status.IStatusBAR;
import com.qat.samples.sysmgmt.util.model.CdStatusTypeEnum;
import com.qat.samples.sysmgmt.util.model.Status;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class StatusBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(StatusBARTest.class);
private IStatusBAR statusBAR; // injected by Spring through @Resource

@Resource
public void setStatusBAR(IStatusBAR statusBAR)
{
	this.statusBAR = statusBAR;
}

public IStatusBAR getStatusBAR()
{
	return statusBAR;
}


//===================================### STATUS ####======================================


@Test
	public void testDeleteStatus()
	{
		Status status = new Status(4,(long) 9999999,CdStatusTypeEnum.ATIVO, "Note");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Status statusResponse = getStatusBAR().fetchStatusById(request);
		Assert.assertEquals(statusResponse, null);
		getStatusBAR().insertStatus(status);
//		request.setFetchId(1);
//		statusResponse = getStatusBAR().fetchStatusById(request);
//		Assert.assertEquals(status.getId(), statusResponse.getId());
//		getStatusBAR().deleteStatusById(status);
//		statusResponse = getStatusBAR().fetchStatusById(request);
//		Assert.assertEquals(statusResponse, null);
	}

	@Test
	public void testFetchAllStatuss()
	{
	Status status = new Status();
		List<Status> response = getStatusBAR().fetchAllStatus(status).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllStatuss()
	{
		getStatusBAR().deleteAllStatus();
	Status status = new Status();
		List<Status> response = getStatusBAR().fetchAllStatus( new Status(999,(long) 9999999,CdStatusTypeEnum.ATIVO, "Note")).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateStatus()
	{
		Status status = new Status(999,(long) 9999999,CdStatusTypeEnum.ATIVO, "Note");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Status statusResponse = getStatusBAR().fetchStatusById(request);
		Assert.assertEquals(statusResponse.getStatus(), CdStatusTypeEnum.ATIVO);
		getStatusBAR().updateStatus(new Status(1,(long) 9999999,CdStatusTypeEnum.ANALISANDO, "Note"));
		statusResponse = getStatusBAR().fetchStatusById(request);
		Assert.assertEquals(statusResponse.getStatus(), CdStatusTypeEnum.ANALISANDO);
	}

	@Test
	public void testFetchStatussByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Status> response = getStatusBAR().fetchStatusByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getStatusBAR().fetchStatusByRequest(request);
	//	Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Status> response2 = getStatusBAR().fetchStatusByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getStatusBAR().deleteAllStatus();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Status> response3 = getStatusBAR().fetchStatusByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertStatus.sql", false);
	}

}
