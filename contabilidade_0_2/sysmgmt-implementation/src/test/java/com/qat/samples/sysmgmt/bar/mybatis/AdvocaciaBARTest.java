/** create by system gera-java version 1.0.0 03/08/2016 11:30 : 58*/
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

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Audiencia;
import com.qat.samples.sysmgmt.advocacia.Processo;
import com.qat.samples.sysmgmt.advocacia.request.AudienciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.ProcessoInquiryRequest;
import com.qat.samples.sysmgmt.bar.Advogado.IAdvocaciaBAR;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class AdvocaciaBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(AdvocaciaBARTest.class);
private IAdvocaciaBAR advocaciaBAR; // injected by Spring through @Resource




//===================================### AUDIENCIA ####======================================


public IAdvocaciaBAR getAdvocaciaBAR() {
	return advocaciaBAR;
}
@Resource
public void setAdvocaciaBAR(IAdvocaciaBAR advocaciaBAR) {
	this.advocaciaBAR = advocaciaBAR;
}

@Test
	public void testDeleteAudiencia()
	{
		Audiencia audiencia = Objects.insertAudiencia(1004, TabelaEnum.AUDIENCIA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		Audiencia audienciaResponse = getAdvocaciaBAR().fetchAudienciaById(request);
		Assert.assertEquals(audienciaResponse, null);
		getAdvocaciaBAR().insertAudiencia(audiencia);
		audienciaResponse = getAdvocaciaBAR().fetchAudienciaById(request);
		Assert.assertEquals(audiencia.getId().toString(), audienciaResponse.getId().toString());
		getAdvocaciaBAR().deleteAudienciaById(audiencia);
		audienciaResponse = getAdvocaciaBAR().fetchAudienciaById(request);
		Assert.assertEquals(audienciaResponse, null);
	}

	@Test
	public void testFetchAllAudiencias()
	{
	Audiencia audiencia = new Audiencia();
		List<Audiencia> response = getAdvocaciaBAR().fetchAllAudiencias(audiencia).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllAudiencias()
	{
		getAdvocaciaBAR().deleteAllAudiencias();
	Audiencia audiencia = new Audiencia();
		List<Audiencia> response = getAdvocaciaBAR().fetchAllAudiencias(new Audiencia()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateAudiencia()
	{
		Audiencia audiencia = Objects.insertAudiencia(1000, TabelaEnum.AUDIENCIA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		Audiencia audienciaResponse = getAdvocaciaBAR().fetchAudienciaById(request);
		Assert.assertEquals(audienciaResponse.getDescricao(), "descricao_0");
		getAdvocaciaBAR().updateAudiencia(audiencia);
		audienciaResponse = getAdvocaciaBAR().fetchAudienciaById(request);
		Assert.assertEquals(audienciaResponse.getDescricao(), "descricao_3 - UPDATE");
	}

	@Test
	public void testFetchAudienciasByRequest() throws Exception
	{
		// check for valid and precount
		AudienciaInquiryRequest request = new AudienciaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Audiencia> response = getAdvocaciaBAR().fetchAudienciasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getAdvocaciaBAR().fetchAudienciasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		AudienciaInquiryRequest request2 = new AudienciaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Audiencia> response2 = getAdvocaciaBAR().fetchAudienciasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getAdvocaciaBAR().deleteAllAudiencias();
		AudienciaInquiryRequest request3 = new AudienciaInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Audiencia> response3 = getAdvocaciaBAR().fetchAudienciasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### PROCESSO ####======================================


@Test
	public void testDeleteProcesso()
	{
		Processo processo = Objects.insertProcesso(1004, TabelaEnum.PROCESSO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1004);
		Processo processoResponse = getAdvocaciaBAR().fetchProcessoById(request);
		Assert.assertEquals(processoResponse, null);
		getAdvocaciaBAR().insertProcesso(processo);
		processoResponse = getAdvocaciaBAR().fetchProcessoById(request);
		Assert.assertEquals(processo.getId(), processoResponse.getId());
		 processo = Objects.insertProcesso(1004, TabelaEnum.PROCESSO, PersistenceActionEnum.DELETE);
		getAdvocaciaBAR().deleteProcessoById(processo);
		processoResponse = getAdvocaciaBAR().fetchProcessoById(request);
		Assert.assertEquals(processoResponse, null);
	}

	@Test
	public void testFetchAllProcessos()
	{
	Processo processo = new Processo();
		List<Processo> response = getAdvocaciaBAR().fetchAllProcessos(processo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllProcessos()
	{
		getAdvocaciaBAR().deleteAllProcessos();
	Processo processo = new Processo();
		List<Processo> response = getAdvocaciaBAR().fetchAllProcessos(new Processo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateProcesso()
	{
		Processo processo = Objects.insertProcesso(1000, TabelaEnum.PROCESSO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		Processo processoResponse = getAdvocaciaBAR().fetchProcessoById(request);
		Assert.assertEquals(processoResponse.getValor().toString(), "10.0");
		getAdvocaciaBAR().updateProcesso(processo);
		processoResponse = getAdvocaciaBAR().fetchProcessoById(request);
		Assert.assertEquals(processoResponse.getValor().toString(), "11.0");
	}

	@Test
	public void testFetchProcessosByRequest() throws Exception
	{
		// check for valid and precount
		ProcessoInquiryRequest request = new ProcessoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Processo> response = getAdvocaciaBAR().fetchProcessosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getAdvocaciaBAR().fetchProcessosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ProcessoInquiryRequest request2 = new ProcessoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Processo> response2 = getAdvocaciaBAR().fetchProcessosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getAdvocaciaBAR().deleteAllProcessos();
		ProcessoInquiryRequest request3 = new ProcessoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Processo> response3 = getAdvocaciaBAR().fetchProcessosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertAudiencia.sql", false);
		executeSqlScript("conf/insertProcesso.sql", false);
	}



}
