package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dac.ICountyDAC;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml",
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CountyDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(CountyDACTest.class);
	private ICountyDAC countyDAC; // injected by Spring through @resource

	@Resource
	public void setCountyDAC(ICountyDAC countyDAC)
	{
		this.countyDAC = countyDAC;
	}

	public ICountyDAC getCountyDAC()
	{
		return countyDAC;
	}

	@Test
	public void testDeleteCounty()
	{
		County county = new County(999, "County999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(999);
		County countyResponse = getCountyDAC().fetchCountyById(request).getFirstResult();
		assertEquals(countyResponse, null);
		getCountyDAC().insertCounty(county);
		countyResponse = getCountyDAC().fetchCountyById(request).getFirstResult();
		assertEquals(county.getId(), countyResponse.getId());
		getCountyDAC().deleteCountyById(county);
		countyResponse = getCountyDAC().fetchCountyById(request).getFirstResult();
		assertEquals(countyResponse, null);
	}

	@Test
	public void testFetchAllCounties()
	{
		List<County> response = getCountyDAC().fetchAllCounties().getResultsList();
		assertNotNull(response);
	}

	@Test
	public void testDeleteAllCounties()
	{
		getCountyDAC().deleteAllCounties();
		List<County> response = getCountyDAC().fetchAllCounties().getResultsList();
		assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCounty()
	{
		County county = new County(1234, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1234);
		County countyResponse = getCountyDAC().fetchCountyById(request).getFirstResult();
		assertEquals(countyResponse.getDescription(), "NATIVE INSERT");
		getCountyDAC().updateCounty(county);
		countyResponse = getCountyDAC().fetchCountyById(request).getFirstResult();
		assertEquals(countyResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCountiesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<County> response = getCountyDAC().fetchCountiesByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getCountyDAC().fetchCountiesByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<County> response2 = getCountyDAC().fetchCountiesByRequest(request2);
		assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getCountyDAC().deleteAllCounties();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<County> response3 = getCountyDAC().fetchCountiesByRequest(request3);
		assertTrue(response3.getStatus() == Status.NoRowsFoundError);

	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertCounty.sql", false);
	}

}
