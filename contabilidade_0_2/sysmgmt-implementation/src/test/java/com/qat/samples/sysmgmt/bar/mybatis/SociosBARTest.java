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
import com.qat.samples.sysmgmt.bar.ISociosBAR;
import com.qat.samples.sysmgmt.model.Socios;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/sociosbartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class SociosBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(SociosBARTest.class);
private ISociosBAR sociosBAR; // injected by Spring through @Resource

@Resource
public void setSociosBAR(ISociosBAR sociosBAR)
{
	this.sociosBAR = sociosBAR;
}

public ISociosBAR getSociosBAR()
{
	return sociosBAR;
}


//===================================### SOCIO ####======================================


@Test
	public void testDeleteSocio()
	{
		Socio socio = new Socio(999, "Socio_999");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(999);
		Socio socioResponse = getSociosBAR().fetchSocioById(request);
		Assert.assertEquals(socioResponse, null);
		getSociosBAR().insertSocio(socio);
		socioResponse = getSociosBAR().fetchSocioById(request);
		Assert.assertEquals(socio.getId(), socioResponse.getId());
		getSociosBAR().deleteSocioById(socio);
		socioResponse = getSociosBAR().fetchSocioById(request);
		Assert.assertEquals(socioResponse, null);
	}

	@Test
	public void testFetchAllSocios()
	{
	Socio socio = new Socio();
		List<Socio> response = getSociosBAR().fetchAllSocios(socio).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllSocios()
	{
		getSociosBAR().deleteAllSocios();
	Socio socio = new Socio();
		List<Socio> response = getSociosBAR().fetchAllSocios().getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateSocio()
	{
		Socio socio = new Socio(1234, "NATIVE INSERT UPDATE");
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1234);
		Socio socioResponse = getSociosBAR().fetchSocioById(request);
		Assert.assertEquals(socioResponse.getDescription(), "NATIVE INSERT");
		getSociosBAR().updateSocio(socio);
		socioResponse = getSociosBAR().fetchSocioById(request);
		Assert.assertEquals(socioResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchSociosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Socio> response = getSociosBAR().fetchSociosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getSociosBAR().fetchSociosByRequest(request);
		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Socio> response2 = getSociosBAR().fetchSociosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getSociosBAR().deleteAllSocios();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Socio> response3 = getSociosBAR().fetchSociosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertSocio.sql", false);
	}

}
