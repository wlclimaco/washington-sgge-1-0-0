package com.qat.samples.sysmgmt.unittest.dac.mybatis;

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

import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.dac.ICidadeDAC;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CidadeDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(CidadeDACTest.class);
	private ICidadeDAC cidadeDAC; // injected by Spring through setter below

	public ICidadeDAC getCidadeDAC()
	{
		return cidadeDAC;
	}

	@Resource
	public void setCidadeDAC(ICidadeDAC newValue)
	{
		cidadeDAC = newValue;
	}

	@Test
	public void testInsertCidade() throws Exception
	{
		// getCidadeDAC().deleteAllCidades();
		// Cidade cidade = createCidade();
		// getCidadeDAC().insertCidade(cidade);
		// FetchByIdRequest request = createFetchByIdRequest(cidade.getId());
		// Cidade response = getCidadeDAC().fetchCidadeById(request);
		// assertEquals(cidade.getId(), response.getId());
		// assertEquals(cidade.getEstado(), response.getEstado());
		// assertEquals(cidade.getId(), response.getId());
	}

	@Test
	public void testUpdateCidade() throws Exception
	{
		// getCidadeDAC().deleteAllCidades();
		// Cidade cidade = createCidade();
		// getCidadeDAC().insertCidade(cidade);
		// FetchByIdRequest request = createFetchByIdRequest(cidade.getId());
		// cidade.setCidade("NewDescription");
		// getCidadeDAC().updateCidade(cidade);
		// Cidade response = getCidadeDAC().fetchCidadeById(request);
		// assertEquals(cidade.getId(), response.getId());
		// assertEquals(cidade.getEstado(), response.getEstado());
	}

	@Test
	public void testDeleteAll() throws Exception
	{
		// getCidadeDAC().deleteAllCidades();
		// assertTrue(getCidadeDAC().fetchAllCidades().isEmpty());
	}

	@Test
	public void testFetchCidadesByRequest() throws Exception
	{
		// check for valid and precount
		// PagedInquiryRequest request = new PagedInquiryRequest();
		// request.setPreQueryCount(true);
		// request.setStartPage(0);
		// request.setPageSize(4);
		// InternalResultsResponse<Cidade> response = getCidadeDAC().fetchCidadesByRequest(request);
		// assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// // check next page
		// request.setPreQueryCount(true);
		// request.setStartPage(1);
		// request.setPageSize(4);
		// response = getCidadeDAC().fetchCidadesByRequest(request);
		// assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		//
		// // check for valid and no precount
		// PagedInquiryRequest request2 = new PagedInquiryRequest();
		// request2.setPreQueryCount(false);
		// InternalResultsResponse<Cidade> response2 = getCidadeDAC().fetchCidadesByRequest(request2);
		// assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		// assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
		//
		// // check for zero rows
		// getCidadeDAC().deleteAllCidades();
		// PagedInquiryRequest request3 = new PagedInquiryRequest();
		// request3.setPreQueryCount(true);
		// InternalResultsResponse<Cidade> response3 = getCidadeDAC().fetchCidadesByRequest(request3);
		// assertTrue(response3.getStatus() == Status.NoRowsFoundError);

	}

	@Test
	public void testDeleteCidade() throws Exception
	{
		// Cidade cidade = createCidade();
		// getCidadeDAC().insertCidade(cidade);
		// FetchByIdRequest request = createFetchByIdRequest(cidade.getId());
		// assertNotNull(getCidadeDAC().fetchCidadeById(request));
		// getCidadeDAC().deleteCidade(cidade);
		// assertNull(getCidadeDAC().fetchCidadeById(request));
	}

	private Cidade createCidade()
	{
		Cidade cidade = new Cidade();

		return cidade;
	}

	private FetchByIdRequest createFetchByIdRequest(Integer value)
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(value);
		return request;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertCidade.sql", false);
	}

}
