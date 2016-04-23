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
import com.qat.samples.sysmgmt.bar.ICountyBAR;
import com.qat.samples.sysmgmt.model.County;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/countybartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CountyBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(CountyBARTest.class);
	private ICountyBAR countyBAR; // injected by Spring through @Resource

	@Resource
	public void setCountyBAR(ICountyBAR countyBAR)
	{
		this.countyBAR = countyBAR;
	}

	public ICountyBAR getCountyBAR()
	{
		return countyBAR;
	}

	@Test
	public void testDeleteCounty()
	{
		County county = new County(999, "County999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(999);
		County countyResponse = getCountyBAR().fetchCountyById(request).getFirstResult();
		Assert.assertEquals(countyResponse, null);
		getCountyBAR().insertCounty(county);
		countyResponse = getCountyBAR().fetchCountyById(request).getFirstResult();
		Assert.assertEquals(county.getId(), countyResponse.getId());
		getCountyBAR().deleteCountyById(county);
		countyResponse = getCountyBAR().fetchCountyById(request).getFirstResult();
		Assert.assertEquals(countyResponse, null);
	}

	@Test
	public void testFetchAllCounties()
	{
		List<County> response = getCountyBAR().fetchAllCounties().getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCounties()
	{
		getCountyBAR().deleteAllCounties();
		List<County> response = getCountyBAR().fetchAllCounties().getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCounty()
	{
		County county = new County(1234, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1234);
		County countyResponse = getCountyBAR().fetchCountyById(request).getFirstResult();
		Assert.assertEquals(countyResponse.getDescription(), "NATIVE INSERT");
		getCountyBAR().updateCounty(county);
		countyResponse = getCountyBAR().fetchCountyById(request).getFirstResult();
		Assert.assertEquals(countyResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCountiesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<County> response = getCountyBAR().fetchCountiesByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getCountyBAR().fetchCountiesByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<County> response2 = getCountyBAR().fetchCountiesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getCountyBAR().deleteAllCounties();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<County> response3 = getCountyBAR().fetchCountiesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertCounty.sql", false);
	}

}
