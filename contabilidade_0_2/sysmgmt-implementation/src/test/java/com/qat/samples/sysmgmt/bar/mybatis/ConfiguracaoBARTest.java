/** create by system gera-java version 1.0.0 27/07/2016 15:55 : 11*/
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
import com.qat.samples.sysmgmt.bar.IConfiguracaoBAR;
import com.qat.samples.sysmgmt.model.Configuracao;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.ConfiguracaoInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ConfiguracaoBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(ConfiguracaoBARTest.class);
private IConfiguracaoBAR configuracaoBAR; // injected by Spring through @Resource

@Resource
public void setConfiguracaoBAR(IConfiguracaoBAR configuracaoBAR)
{
	this.configuracaoBAR = configuracaoBAR;
}

public IConfiguracaoBAR getConfiguracaoBAR()
{
	return configuracaoBAR;
}


//===================================### CONFIGURACAO ####======================================


@Test
	public void testDeleteConfiguracao()
	{
		Configuracao configuracao = insertConfiguracao(4, TabelaEnum.CONFIGURACAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configuracao configuracaoResponse = getConfiguracaoBAR().fetchConfiguracaoById(request);
		Assert.assertEquals(configuracaoResponse, null);
		getConfiguracaoBAR().insertConfiguracao(configuracao);
		configuracaoResponse = getConfiguracaoBAR().fetchConfiguracaoById(request);
		Assert.assertEquals(configuracao.getId(), configuracaoResponse.getId());
		getConfiguracaoBAR().deleteConfiguracaoById(configuracao);
		configuracaoResponse = getConfiguracaoBAR().fetchConfiguracaoById(request);
		Assert.assertEquals(configuracaoResponse, null);
	}

	@Test
	public void testFetchAllConfiguracaos()
	{
	Configuracao configuracao = new Configuracao();
		List<Configuracao> response = getConfiguracaoBAR().fetchAllConfiguracaos(configuracao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfiguracaos()
	{
		getConfiguracaoBAR().deleteAllConfiguracaos();
	Configuracao configuracao = new Configuracao();
		List<Configuracao> response = getConfiguracaoBAR().fetchAllConfiguracaos(new Configuracao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfiguracao()
	{
		Configuracao configuracao = insertConfiguracao(1, TabelaEnum.CONFIGURACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configuracao configuracaoResponse = getConfiguracaoBAR().fetchConfiguracaoById(request);
		Assert.assertEquals(configuracaoResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfiguracao(configuracao);
		configuracaoResponse = getConfiguracaoBAR().fetchConfiguracaoById(request);
		Assert.assertEquals(configuracaoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfiguracaosByRequest() throws Exception
	{
		// check for valid and precount
		ConfiguracaoInquiryRequest request = new ConfiguracaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configuracao> response = getConfiguracaoBAR().fetchConfiguracaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfiguracaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfiguracaoInquiryRequest request2 = new ConfiguracaoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configuracao> response2 = getConfiguracaoBAR().fetchConfiguracaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfiguracaos();
		ConfiguracaoInquiryRequest request3 = new ConfiguracaoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configuracao> response3 = getConfiguracaoBAR().fetchConfiguracaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### BOLETO ####======================================


@Test
	public void testDeleteBoleto()
	{
		Boleto boleto = insertBoleto(4, TabelaEnum.BOLETO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Boleto boletoResponse = getConfiguracaoBAR().fetchBoletoById(request);
		Assert.assertEquals(boletoResponse, null);
		getConfiguracaoBAR().insertBoleto(boleto);
		boletoResponse = getConfiguracaoBAR().fetchBoletoById(request);
		Assert.assertEquals(boleto.getId(), boletoResponse.getId());
		getConfiguracaoBAR().deleteBoletoById(boleto);
		boletoResponse = getConfiguracaoBAR().fetchBoletoById(request);
		Assert.assertEquals(boletoResponse, null);
	}

	@Test
	public void testFetchAllBoletos()
	{
	Boleto boleto = new Boleto();
		List<Boleto> response = getConfiguracaoBAR().fetchAllBoletos(boleto).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllBoletos()
	{
		getConfiguracaoBAR().deleteAllBoletos();
	Boleto boleto = new Boleto();
		List<Boleto> response = getConfiguracaoBAR().fetchAllBoletos(new Boleto()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateBoleto()
	{
		Boleto boleto = insertBoleto(1, TabelaEnum.BOLETO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Boleto boletoResponse = getConfiguracaoBAR().fetchBoletoById(request);
		Assert.assertEquals(boletoResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateBoleto(boleto);
		boletoResponse = getConfiguracaoBAR().fetchBoletoById(request);
		Assert.assertEquals(boletoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchBoletosByRequest() throws Exception
	{
		// check for valid and precount
		BoletoInquiryRequest request = new BoletoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Boleto> response = getConfiguracaoBAR().fetchBoletosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchBoletosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		BoletoInquiryRequest request2 = new BoletoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Boleto> response2 = getConfiguracaoBAR().fetchBoletosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllBoletos();
		BoletoInquiryRequest request3 = new BoletoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Boleto> response3 = getConfiguracaoBAR().fetchBoletosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGCARNE ####======================================


@Test
	public void testDeleteConfigcarne()
	{
		Configcarne configcarne = insertConfigcarne(4, TabelaEnum.CONFIGCARNE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configcarne configcarneResponse = getConfiguracaoBAR().fetchConfigcarneById(request);
		Assert.assertEquals(configcarneResponse, null);
		getConfiguracaoBAR().insertConfigcarne(configcarne);
		configcarneResponse = getConfiguracaoBAR().fetchConfigcarneById(request);
		Assert.assertEquals(configcarne.getId(), configcarneResponse.getId());
		getConfiguracaoBAR().deleteConfigcarneById(configcarne);
		configcarneResponse = getConfiguracaoBAR().fetchConfigcarneById(request);
		Assert.assertEquals(configcarneResponse, null);
	}

	@Test
	public void testFetchAllConfigcarnes()
	{
	Configcarne configcarne = new Configcarne();
		List<Configcarne> response = getConfiguracaoBAR().fetchAllConfigcarnes(configcarne).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigcarnes()
	{
		getConfiguracaoBAR().deleteAllConfigcarnes();
	Configcarne configcarne = new Configcarne();
		List<Configcarne> response = getConfiguracaoBAR().fetchAllConfigcarnes(new Configcarne()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigcarne()
	{
		Configcarne configcarne = insertConfigcarne(1, TabelaEnum.CONFIGCARNE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configcarne configcarneResponse = getConfiguracaoBAR().fetchConfigcarneById(request);
		Assert.assertEquals(configcarneResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfigcarne(configcarne);
		configcarneResponse = getConfiguracaoBAR().fetchConfigcarneById(request);
		Assert.assertEquals(configcarneResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigcarnesByRequest() throws Exception
	{
		// check for valid and precount
		ConfigcarneInquiryRequest request = new ConfigcarneInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configcarne> response = getConfiguracaoBAR().fetchConfigcarnesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigcarnesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfigcarneInquiryRequest request2 = new ConfigcarneInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configcarne> response2 = getConfiguracaoBAR().fetchConfigcarnesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigcarnes();
		ConfigcarneInquiryRequest request3 = new ConfigcarneInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configcarne> response3 = getConfiguracaoBAR().fetchConfigcarnesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGENTRADA ####======================================


@Test
	public void testDeleteConfigentrada()
	{
		Configentrada configentrada = insertConfigentrada(4, TabelaEnum.CONFIGENTRADA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configentrada configentradaResponse = getConfiguracaoBAR().fetchConfigentradaById(request);
		Assert.assertEquals(configentradaResponse, null);
		getConfiguracaoBAR().insertConfigentrada(configentrada);
		configentradaResponse = getConfiguracaoBAR().fetchConfigentradaById(request);
		Assert.assertEquals(configentrada.getId(), configentradaResponse.getId());
		getConfiguracaoBAR().deleteConfigentradaById(configentrada);
		configentradaResponse = getConfiguracaoBAR().fetchConfigentradaById(request);
		Assert.assertEquals(configentradaResponse, null);
	}

	@Test
	public void testFetchAllConfigentradas()
	{
	Configentrada configentrada = new Configentrada();
		List<Configentrada> response = getConfiguracaoBAR().fetchAllConfigentradas(configentrada).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigentradas()
	{
		getConfiguracaoBAR().deleteAllConfigentradas();
	Configentrada configentrada = new Configentrada();
		List<Configentrada> response = getConfiguracaoBAR().fetchAllConfigentradas(new Configentrada()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigentrada()
	{
		Configentrada configentrada = insertConfigentrada(1, TabelaEnum.CONFIGENTRADA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configentrada configentradaResponse = getConfiguracaoBAR().fetchConfigentradaById(request);
		Assert.assertEquals(configentradaResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfigentrada(configentrada);
		configentradaResponse = getConfiguracaoBAR().fetchConfigentradaById(request);
		Assert.assertEquals(configentradaResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigentradasByRequest() throws Exception
	{
		// check for valid and precount
		ConfigentradaInquiryRequest request = new ConfigentradaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configentrada> response = getConfiguracaoBAR().fetchConfigentradasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigentradasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfigentradaInquiryRequest request2 = new ConfigentradaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configentrada> response2 = getConfiguracaoBAR().fetchConfigentradasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigentradas();
		ConfigentradaInquiryRequest request3 = new ConfigentradaInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configentrada> response3 = getConfiguracaoBAR().fetchConfigentradasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGFISCAL ####======================================


@Test
	public void testDeleteConfigfiscal()
	{
		Configfiscal configfiscal = insertConfigfiscal(4, TabelaEnum.CONFIGFISCAL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configfiscal configfiscalResponse = getConfiguracaoBAR().fetchConfigfiscalById(request);
		Assert.assertEquals(configfiscalResponse, null);
		getConfiguracaoBAR().insertConfigfiscal(configfiscal);
		configfiscalResponse = getConfiguracaoBAR().fetchConfigfiscalById(request);
		Assert.assertEquals(configfiscal.getId(), configfiscalResponse.getId());
		getConfiguracaoBAR().deleteConfigfiscalById(configfiscal);
		configfiscalResponse = getConfiguracaoBAR().fetchConfigfiscalById(request);
		Assert.assertEquals(configfiscalResponse, null);
	}

	@Test
	public void testFetchAllConfigfiscals()
	{
	Configfiscal configfiscal = new Configfiscal();
		List<Configfiscal> response = getConfiguracaoBAR().fetchAllConfigfiscals(configfiscal).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigfiscals()
	{
		getConfiguracaoBAR().deleteAllConfigfiscals();
	Configfiscal configfiscal = new Configfiscal();
		List<Configfiscal> response = getConfiguracaoBAR().fetchAllConfigfiscals(new Configfiscal()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigfiscal()
	{
		Configfiscal configfiscal = insertConfigfiscal(1, TabelaEnum.CONFIGFISCAL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configfiscal configfiscalResponse = getConfiguracaoBAR().fetchConfigfiscalById(request);
		Assert.assertEquals(configfiscalResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfigfiscal(configfiscal);
		configfiscalResponse = getConfiguracaoBAR().fetchConfigfiscalById(request);
		Assert.assertEquals(configfiscalResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigfiscalsByRequest() throws Exception
	{
		// check for valid and precount
		ConfigfiscalInquiryRequest request = new ConfigfiscalInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configfiscal> response = getConfiguracaoBAR().fetchConfigfiscalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigfiscalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfigfiscalInquiryRequest request2 = new ConfigfiscalInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configfiscal> response2 = getConfiguracaoBAR().fetchConfigfiscalsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigfiscals();
		ConfigfiscalInquiryRequest request3 = new ConfigfiscalInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configfiscal> response3 = getConfiguracaoBAR().fetchConfigfiscalsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGALERTAS ####======================================


@Test
	public void testDeleteConfigalertas()
	{
		Configalertas configalertas = insertConfigalertas(4, TabelaEnum.CONFIGALERTAS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configalertas configalertasResponse = getConfiguracaoBAR().fetchConfigalertasById(request);
		Assert.assertEquals(configalertasResponse, null);
		getConfiguracaoBAR().insertConfigalertas(configalertas);
		configalertasResponse = getConfiguracaoBAR().fetchConfigalertasById(request);
		Assert.assertEquals(configalertas.getId(), configalertasResponse.getId());
		getConfiguracaoBAR().deleteConfigalertasById(configalertas);
		configalertasResponse = getConfiguracaoBAR().fetchConfigalertasById(request);
		Assert.assertEquals(configalertasResponse, null);
	}

	@Test
	public void testFetchAllConfigalertass()
	{
	Configalertas configalertas = new Configalertas();
		List<Configalertas> response = getConfiguracaoBAR().fetchAllConfigalertass(configalertas).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigalertass()
	{
		getConfiguracaoBAR().deleteAllConfigalertass();
	Configalertas configalertas = new Configalertas();
		List<Configalertas> response = getConfiguracaoBAR().fetchAllConfigalertass(new Configalertas()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigalertas()
	{
		Configalertas configalertas = insertConfigalertas(1, TabelaEnum.CONFIGALERTAS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configalertas configalertasResponse = getConfiguracaoBAR().fetchConfigalertasById(request);
		Assert.assertEquals(configalertasResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfigalertas(configalertas);
		configalertasResponse = getConfiguracaoBAR().fetchConfigalertasById(request);
		Assert.assertEquals(configalertasResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigalertassByRequest() throws Exception
	{
		// check for valid and precount
		ConfigalertasInquiryRequest request = new ConfigalertasInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configalertas> response = getConfiguracaoBAR().fetchConfigalertassByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigalertassByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfigalertasInquiryRequest request2 = new ConfigalertasInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configalertas> response2 = getConfiguracaoBAR().fetchConfigalertassByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigalertass();
		ConfigalertasInquiryRequest request3 = new ConfigalertasInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configalertas> response3 = getConfiguracaoBAR().fetchConfigalertassByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGGERAL ####======================================


@Test
	public void testDeleteConfiggeral()
	{
		Configgeral configgeral = insertConfiggeral(4, TabelaEnum.CONFIGGERAL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configgeral configgeralResponse = getConfiguracaoBAR().fetchConfiggeralById(request);
		Assert.assertEquals(configgeralResponse, null);
		getConfiguracaoBAR().insertConfiggeral(configgeral);
		configgeralResponse = getConfiguracaoBAR().fetchConfiggeralById(request);
		Assert.assertEquals(configgeral.getId(), configgeralResponse.getId());
		getConfiguracaoBAR().deleteConfiggeralById(configgeral);
		configgeralResponse = getConfiguracaoBAR().fetchConfiggeralById(request);
		Assert.assertEquals(configgeralResponse, null);
	}

	@Test
	public void testFetchAllConfiggerals()
	{
	Configgeral configgeral = new Configgeral();
		List<Configgeral> response = getConfiguracaoBAR().fetchAllConfiggerals(configgeral).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfiggerals()
	{
		getConfiguracaoBAR().deleteAllConfiggerals();
	Configgeral configgeral = new Configgeral();
		List<Configgeral> response = getConfiguracaoBAR().fetchAllConfiggerals(new Configgeral()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfiggeral()
	{
		Configgeral configgeral = insertConfiggeral(1, TabelaEnum.CONFIGGERAL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configgeral configgeralResponse = getConfiguracaoBAR().fetchConfiggeralById(request);
		Assert.assertEquals(configgeralResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfiggeral(configgeral);
		configgeralResponse = getConfiguracaoBAR().fetchConfiggeralById(request);
		Assert.assertEquals(configgeralResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfiggeralsByRequest() throws Exception
	{
		// check for valid and precount
		ConfiggeralInquiryRequest request = new ConfiggeralInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configgeral> response = getConfiguracaoBAR().fetchConfiggeralsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfiggeralsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfiggeralInquiryRequest request2 = new ConfiggeralInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configgeral> response2 = getConfiguracaoBAR().fetchConfiggeralsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfiggerals();
		ConfiggeralInquiryRequest request3 = new ConfiggeralInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configgeral> response3 = getConfiguracaoBAR().fetchConfiggeralsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGPRODUTO ####======================================


@Test
	public void testDeleteConfigproduto()
	{
		Configproduto configproduto = insertConfigproduto(4, TabelaEnum.CONFIGPRODUTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configproduto configprodutoResponse = getConfiguracaoBAR().fetchConfigprodutoById(request);
		Assert.assertEquals(configprodutoResponse, null);
		getConfiguracaoBAR().insertConfigproduto(configproduto);
		configprodutoResponse = getConfiguracaoBAR().fetchConfigprodutoById(request);
		Assert.assertEquals(configproduto.getId(), configprodutoResponse.getId());
		getConfiguracaoBAR().deleteConfigprodutoById(configproduto);
		configprodutoResponse = getConfiguracaoBAR().fetchConfigprodutoById(request);
		Assert.assertEquals(configprodutoResponse, null);
	}

	@Test
	public void testFetchAllConfigprodutos()
	{
	Configproduto configproduto = new Configproduto();
		List<Configproduto> response = getConfiguracaoBAR().fetchAllConfigprodutos(configproduto).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigprodutos()
	{
		getConfiguracaoBAR().deleteAllConfigprodutos();
	Configproduto configproduto = new Configproduto();
		List<Configproduto> response = getConfiguracaoBAR().fetchAllConfigprodutos(new Configproduto()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigproduto()
	{
		Configproduto configproduto = insertConfigproduto(1, TabelaEnum.CONFIGPRODUTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configproduto configprodutoResponse = getConfiguracaoBAR().fetchConfigprodutoById(request);
		Assert.assertEquals(configprodutoResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfigproduto(configproduto);
		configprodutoResponse = getConfiguracaoBAR().fetchConfigprodutoById(request);
		Assert.assertEquals(configprodutoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigprodutosByRequest() throws Exception
	{
		// check for valid and precount
		ConfigprodutoInquiryRequest request = new ConfigprodutoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configproduto> response = getConfiguracaoBAR().fetchConfigprodutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigprodutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfigprodutoInquiryRequest request2 = new ConfigprodutoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configproduto> response2 = getConfiguracaoBAR().fetchConfigprodutosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigprodutos();
		ConfigprodutoInquiryRequest request3 = new ConfigprodutoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configproduto> response3 = getConfiguracaoBAR().fetchConfigprodutosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGSMTP ####======================================


@Test
	public void testDeleteConfigsmtp()
	{
		Configsmtp configsmtp = insertConfigsmtp(4, TabelaEnum.CONFIGSMTP, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configsmtp configsmtpResponse = getConfiguracaoBAR().fetchConfigsmtpById(request);
		Assert.assertEquals(configsmtpResponse, null);
		getConfiguracaoBAR().insertConfigsmtp(configsmtp);
		configsmtpResponse = getConfiguracaoBAR().fetchConfigsmtpById(request);
		Assert.assertEquals(configsmtp.getId(), configsmtpResponse.getId());
		getConfiguracaoBAR().deleteConfigsmtpById(configsmtp);
		configsmtpResponse = getConfiguracaoBAR().fetchConfigsmtpById(request);
		Assert.assertEquals(configsmtpResponse, null);
	}

	@Test
	public void testFetchAllConfigsmtps()
	{
	Configsmtp configsmtp = new Configsmtp();
		List<Configsmtp> response = getConfiguracaoBAR().fetchAllConfigsmtps(configsmtp).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigsmtps()
	{
		getConfiguracaoBAR().deleteAllConfigsmtps();
	Configsmtp configsmtp = new Configsmtp();
		List<Configsmtp> response = getConfiguracaoBAR().fetchAllConfigsmtps(new Configsmtp()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigsmtp()
	{
		Configsmtp configsmtp = insertConfigsmtp(1, TabelaEnum.CONFIGSMTP, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configsmtp configsmtpResponse = getConfiguracaoBAR().fetchConfigsmtpById(request);
		Assert.assertEquals(configsmtpResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfigsmtp(configsmtp);
		configsmtpResponse = getConfiguracaoBAR().fetchConfigsmtpById(request);
		Assert.assertEquals(configsmtpResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigsmtpsByRequest() throws Exception
	{
		// check for valid and precount
		ConfigsmtpInquiryRequest request = new ConfigsmtpInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configsmtp> response = getConfiguracaoBAR().fetchConfigsmtpsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigsmtpsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfigsmtpInquiryRequest request2 = new ConfigsmtpInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configsmtp> response2 = getConfiguracaoBAR().fetchConfigsmtpsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigsmtps();
		ConfigsmtpInquiryRequest request3 = new ConfigsmtpInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configsmtp> response3 = getConfiguracaoBAR().fetchConfigsmtpsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGURACAONFE ####======================================


@Test
	public void testDeleteConfiguracaonfe()
	{
		Configuracaonfe configuracaonfe = insertConfiguracaonfe(4, TabelaEnum.CONFIGURACAONFE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configuracaonfe configuracaonfeResponse = getConfiguracaoBAR().fetchConfiguracaonfeById(request);
		Assert.assertEquals(configuracaonfeResponse, null);
		getConfiguracaoBAR().insertConfiguracaonfe(configuracaonfe);
		configuracaonfeResponse = getConfiguracaoBAR().fetchConfiguracaonfeById(request);
		Assert.assertEquals(configuracaonfe.getId(), configuracaonfeResponse.getId());
		getConfiguracaoBAR().deleteConfiguracaonfeById(configuracaonfe);
		configuracaonfeResponse = getConfiguracaoBAR().fetchConfiguracaonfeById(request);
		Assert.assertEquals(configuracaonfeResponse, null);
	}

	@Test
	public void testFetchAllConfiguracaonfes()
	{
	Configuracaonfe configuracaonfe = new Configuracaonfe();
		List<Configuracaonfe> response = getConfiguracaoBAR().fetchAllConfiguracaonfes(configuracaonfe).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfiguracaonfes()
	{
		getConfiguracaoBAR().deleteAllConfiguracaonfes();
	Configuracaonfe configuracaonfe = new Configuracaonfe();
		List<Configuracaonfe> response = getConfiguracaoBAR().fetchAllConfiguracaonfes(new Configuracaonfe()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfiguracaonfe()
	{
		Configuracaonfe configuracaonfe = insertConfiguracaonfe(1, TabelaEnum.CONFIGURACAONFE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configuracaonfe configuracaonfeResponse = getConfiguracaoBAR().fetchConfiguracaonfeById(request);
		Assert.assertEquals(configuracaonfeResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfiguracaonfe(configuracaonfe);
		configuracaonfeResponse = getConfiguracaoBAR().fetchConfiguracaonfeById(request);
		Assert.assertEquals(configuracaonfeResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfiguracaonfesByRequest() throws Exception
	{
		// check for valid and precount
		ConfiguracaonfeInquiryRequest request = new ConfiguracaonfeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configuracaonfe> response = getConfiguracaoBAR().fetchConfiguracaonfesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfiguracaonfesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfiguracaonfeInquiryRequest request2 = new ConfiguracaonfeInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configuracaonfe> response2 = getConfiguracaoBAR().fetchConfiguracaonfesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfiguracaonfes();
		ConfiguracaonfeInquiryRequest request3 = new ConfiguracaonfeInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configuracaonfe> response3 = getConfiguracaoBAR().fetchConfiguracaonfesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CONFIGVENDAS ####======================================


@Test
	public void testDeleteConfigvendas()
	{
		Configvendas configvendas = insertConfigvendas(4, TabelaEnum.CONFIGVENDAS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Configvendas configvendasResponse = getConfiguracaoBAR().fetchConfigvendasById(request);
		Assert.assertEquals(configvendasResponse, null);
		getConfiguracaoBAR().insertConfigvendas(configvendas);
		configvendasResponse = getConfiguracaoBAR().fetchConfigvendasById(request);
		Assert.assertEquals(configvendas.getId(), configvendasResponse.getId());
		getConfiguracaoBAR().deleteConfigvendasById(configvendas);
		configvendasResponse = getConfiguracaoBAR().fetchConfigvendasById(request);
		Assert.assertEquals(configvendasResponse, null);
	}

	@Test
	public void testFetchAllConfigvendass()
	{
	Configvendas configvendas = new Configvendas();
		List<Configvendas> response = getConfiguracaoBAR().fetchAllConfigvendass(configvendas).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigvendass()
	{
		getConfiguracaoBAR().deleteAllConfigvendass();
	Configvendas configvendas = new Configvendas();
		List<Configvendas> response = getConfiguracaoBAR().fetchAllConfigvendass(new Configvendas()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigvendas()
	{
		Configvendas configvendas = insertConfigvendas(1, TabelaEnum.CONFIGVENDAS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Configvendas configvendasResponse = getConfiguracaoBAR().fetchConfigvendasById(request);
		Assert.assertEquals(configvendasResponse.getDescription(), "NATIVE INSERT");
		getConfiguracaoBAR().updateConfigvendas(configvendas);
		configvendasResponse = getConfiguracaoBAR().fetchConfigvendasById(request);
		Assert.assertEquals(configvendasResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigvendassByRequest() throws Exception
	{
		// check for valid and precount
		ConfigvendasInquiryRequest request = new ConfigvendasInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Configvendas> response = getConfiguracaoBAR().fetchConfigvendassByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigvendassByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ConfigvendasInquiryRequest request2 = new ConfigvendasInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configvendas> response2 = getConfiguracaoBAR().fetchConfigvendassByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigvendass();
		ConfigvendasInquiryRequest request3 = new ConfigvendasInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Configvendas> response3 = getConfiguracaoBAR().fetchConfigvendassByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertConfiguracao.sql", false);
		executeSqlScript("conf/insertBoleto.sql", false);
		executeSqlScript("conf/insertConfigcarne.sql", false);
		executeSqlScript("conf/insertConfigentrada.sql", false);
		executeSqlScript("conf/insertConfigfiscal.sql", false);
		executeSqlScript("conf/insertConfigalertas.sql", false);
		executeSqlScript("conf/insertConfiggeral.sql", false);
		executeSqlScript("conf/insertConfigproduto.sql", false);
		executeSqlScript("conf/insertConfigsmtp.sql", false);
		executeSqlScript("conf/insertConfiguracaonfe.sql", false);
		executeSqlScript("conf/insertConfigvendas.sql", false);
	}

	
	public Configuracao insertConfiguracao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Configuracao configuracao = new Configuracao();
			Date a = new Date();
			configuracao.setId(100);
			configuracao.setConfGeral(new ArrayList<undefined>());
			configuracao.setConfNFe(new ArrayList<undefined>());
			configuracao.setConfFiscal(new ArrayList<undefined>());
			configuracao.setConfProd(new ArrayList<undefined>());
			configuracao.setConfVendas(new ArrayList<undefined>());
			configuracao.setConfCMTP(new ArrayList<undefined>());
			configuracao.setConfEntrada(new ArrayList<undefined>());
			configuracao.setConfCarne(new ArrayList<undefined>());
			configuracao.setBoletoList(new ArrayList<undefined>());
			configuracao.getundefined().add(insertundefined(id,TabelaEnum.CONFIGURACAO,action));
			configuracao.setParentId(id);
			configuracao.setEmprId(1);
			configuracao.setModifyDateUTC(a.getTime());
			configuracao.setCreateDateUTC(a.getTime());
			configuracao.setCreateUser("system");
			configuracao.setModifyUser("system");
			configuracao.setProcessId(1);
			configuracao.setModelAction(action);
	
			return configuracao;
		}

	
	public Boleto insertBoleto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Boleto boleto = new Boleto();
			Date a = new Date();
			boleto.setId(100);
			boleto.setAtivarBolOnLine(new ArrayList<undefined>());
			boleto.setTipoBoleto(new ArrayList<undefined>());
			boleto.setAgencia(100);
			boleto.setCedente(100);
			boleto.setJuros(new Double(1.99));
			boleto.setTipoCalcMora(new ArrayList<undefined>());
			boleto.setMora(new Double(1.99));
			boleto.setInstrucoes("NATIVE INSERT UPDATE");
			boleto.setDemonstrativo("NATIVE INSERT UPDATE");
			boleto.setImpJuros(new ArrayList<undefined>());
			boleto.setParentId(id);
			boleto.setEmprId(1);
			boleto.setModifyDateUTC(a.getTime());
			boleto.setCreateDateUTC(a.getTime());
			boleto.setCreateUser("system");
			boleto.setModifyUser("system");
			boleto.setProcessId(1);
			boleto.setModelAction(action);
	
			return boleto;
		}

	
	public ConfigCarne insertConfigCarne(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigCarne configcarne = new ConfigCarne();
			Date a = new Date();
			configcarne.setId(100);
			configcarne.setCarneBotelo(new ArrayList<undefined>());
			configcarne.setCarneNormal(new ArrayList<undefined>());
			configcarne.setParentId(id);
			configcarne.setEmprId(1);
			configcarne.setModifyDateUTC(a.getTime());
			configcarne.setCreateDateUTC(a.getTime());
			configcarne.setCreateUser("system");
			configcarne.setModifyUser("system");
			configcarne.setProcessId(1);
			configcarne.setModelAction(action);
	
			return configcarne;
		}

	
	public ConfigEntrada insertConfigEntrada(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigEntrada configentrada = new ConfigEntrada();
			Date a = new Date();
			configentrada.setId(100);
			configentrada.setValorTotalFixo(new ArrayList<undefined>());
			configentrada.setManterPrecoVendaProd(new ArrayList<undefined>());
			configentrada.setParentId(id);
			configentrada.setEmprId(1);
			configentrada.setModifyDateUTC(a.getTime());
			configentrada.setCreateDateUTC(a.getTime());
			configentrada.setCreateUser("system");
			configentrada.setModifyUser("system");
			configentrada.setProcessId(1);
			configentrada.setModelAction(action);
	
			return configentrada;
		}

	
	public ConfigFiscal insertConfigFiscal(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigFiscal configfiscal = new ConfigFiscal();
			Date a = new Date();
			configfiscal.setId(100);
			configfiscal.setPrincAtividade(new ArrayList<undefined>());
			configfiscal.setRegime(new ArrayList<undefined>());
			configfiscal.setAliqSimples(new Double(1.99));
			configfiscal.setParentId(id);
			configfiscal.setEmprId(1);
			configfiscal.setModifyDateUTC(a.getTime());
			configfiscal.setCreateDateUTC(a.getTime());
			configfiscal.setCreateUser("system");
			configfiscal.setModifyUser("system");
			configfiscal.setProcessId(1);
			configfiscal.setModelAction(action);
	
			return configfiscal;
		}

	
	public ConfigAlertas insertConfigAlertas(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigAlertas configalertas = new ConfigAlertas();
			Date a = new Date();
			configalertas.setId(100);
			configalertas.setEstoqMin(new ArrayList<undefined>());
			configalertas.setEstoqMax(new ArrayList<undefined>());
			configalertas.setErroNFe(new ArrayList<undefined>());
			configalertas.setPdCompra(new ArrayList<undefined>());
			configalertas.setParentId(id);
			configalertas.setEmprId(1);
			configalertas.setModifyDateUTC(a.getTime());
			configalertas.setCreateDateUTC(a.getTime());
			configalertas.setCreateUser("system");
			configalertas.setModifyUser("system");
			configalertas.setProcessId(1);
			configalertas.setModelAction(action);
	
			return configalertas;
		}

	
	public ConfigGeral insertConfigGeral(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigGeral configgeral = new ConfigGeral();
			Date a = new Date();
			configgeral.setId(100);
			configgeral.setFusoHorario(100);
			configgeral.setCasasDecimais(100);
			configgeral.setDiasCartaCobr(100);
			configgeral.setInfPosicionarMouse(new ArrayList<undefined>());
			configgeral.setCnpjCPFUnico(new ArrayList<undefined>());
			configgeral.setImpCodPersonalizado(new ArrayList<undefined>());
			configgeral.setLogListRelImp(new ArrayList<undefined>());
			configgeral.setObsProdFinProd(new ArrayList<undefined>());
			configgeral.setParentId(id);
			configgeral.setEmprId(1);
			configgeral.setModifyDateUTC(a.getTime());
			configgeral.setCreateDateUTC(a.getTime());
			configgeral.setCreateUser("system");
			configgeral.setModifyUser("system");
			configgeral.setProcessId(1);
			configgeral.setModelAction(action);
	
			return configgeral;
		}

	
	public ConfigProduto insertConfigProduto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigProduto configproduto = new ConfigProduto();
			Date a = new Date();
			configproduto.setId(100);
			configproduto.setCfop(new ArrayList<undefined>());
			configproduto.setIcmsSitTrib(new ArrayList<undefined>());
			configproduto.setIcmsOrigem(new ArrayList<undefined>());
			configproduto.setIcmsModalidadeBC(new ArrayList<undefined>());
			configproduto.setIcmsRedBaseCalc(new Double(1.99));
			configproduto.setIcmsAliq(new Double(1.99));
			configproduto.setIcmsMotDesoneracao(new ArrayList<undefined>());
			configproduto.setIcmsModBCST(new ArrayList<undefined>());
			configproduto.setIcmsMargValAdic(new Double(1.99));
			configproduto.setIcmsRedBaseCalcST(new Double(1.99));
			configproduto.setIcmsPrecoUnitPautaST(new Double(1.99));
			configproduto.setIcmsAliqST(new Double(1.99));
			configproduto.setIpiSitTrib(new ArrayList<undefined>());
			configproduto.setIpiClasCigarroBebida(new Double(1.99));
			configproduto.setIpiCNPJProd("NATIVE INSERT UPDATE");
			configproduto.setIpiCodSeloCont("NATIVE INSERT UPDATE");
			configproduto.setIpiQtdSelo(new Double(1.99));
			configproduto.setIpiCodEnquad(100);
			configproduto.setIpiTipCalc(new ArrayList<undefined>());
			configproduto.setIpiAliq(new Double(1.99));
			configproduto.setPisSitTrib(new ArrayList<undefined>());
			configproduto.setPisAliq(new Double(1.99));
			configproduto.setPisValUnidtrib(new Double(1.99));
			configproduto.setPistipoCalcSubstTrib(new ArrayList<undefined>());
			configproduto.setPisAliqST(new Double(1.99));
			configproduto.setPisValorAliqST(new Double(1.99));
			configproduto.setCofinsSubstTrib(new ArrayList<undefined>());
			configproduto.setCofinsAliq(new Double(1.99));
			configproduto.setCofinsValorAliq(new Double(1.99));
			configproduto.setCofinsTipoCalcSubstTrib(new ArrayList<undefined>());
			configproduto.setCofinsAliqST(new Double(1.99));
			configproduto.setCofinsValorAliqST(new Double(1.99));
			configproduto.setParentId(id);
			configproduto.setEmprId(1);
			configproduto.setModifyDateUTC(a.getTime());
			configproduto.setCreateDateUTC(a.getTime());
			configproduto.setCreateUser("system");
			configproduto.setModifyUser("system");
			configproduto.setProcessId(1);
			configproduto.setModelAction(action);
	
			return configproduto;
		}

	
	public ConfigSMTP insertConfigSMTP(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigSMTP configsmtp = new ConfigSMTP();
			Date a = new Date();
			configsmtp.setId(100);
			configsmtp.setServSMTP("NATIVE INSERT UPDATE");
			configsmtp.setPorta("NATIVE INSERT UPDATE");
			configsmtp.setEndEmail("NATIVE INSERT UPDATE");
			configsmtp.setUsuario("NATIVE INSERT UPDATE");
			configsmtp.setSenha("NATIVE INSERT UPDATE");
			configsmtp.setSeguranca(new ArrayList<undefined>());
			configsmtp.setParentId(id);
			configsmtp.setEmprId(1);
			configsmtp.setModifyDateUTC(a.getTime());
			configsmtp.setCreateDateUTC(a.getTime());
			configsmtp.setCreateUser("system");
			configsmtp.setModifyUser("system");
			configsmtp.setProcessId(1);
			configsmtp.setModelAction(action);
	
			return configsmtp;
		}

	
	public ConfiguracaoNFe insertConfiguracaoNFe(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfiguracaoNFe configuracaonfe = new ConfiguracaoNFe();
			Date a = new Date();
			configuracaonfe.setId(100);
			configuracaonfe.setPresCompr(new ArrayList<undefined>());
			configuracaonfe.setDestConsFinal(new ArrayList<undefined>());
			configuracaonfe.setPreencherDataHora(new ArrayList<undefined>());
			configuracaonfe.setIcmsPadrao(new Double(1.99));
			configuracaonfe.setIpiPadrao(new Double(1.99));
			configuracaonfe.setPisPadrao(new Double(1.99));
			configuracaonfe.setCofinsPadrao(new Double(1.99));
			configuracaonfe.setAmbienteEnvio(new ArrayList<undefined>());
			configuracaonfe.setServMsmNota(new Double(1.99));
			configuracaonfe.setSerieEnvio("NATIVE INSERT UPDATE");
			configuracaonfe.setAnexarXmlEmail(new ArrayList<undefined>());
			configuracaonfe.setIdCSC("NATIVE INSERT UPDATE");
			configuracaonfe.setCSC("NATIVE INSERT UPDATE");
			configuracaonfe.setInformacaoAdd("NATIVE INSERT UPDATE");
			configuracaonfe.setCertificado("NATIVE INSERT UPDATE");
			configuracaonfe.setSenha("NATIVE INSERT UPDATE");
			configuracaonfe.setSalvarSenha(new ArrayList<undefined>());
			configuracaonfe.setCfopPadrao(new ArrayList<undefined>());
			configuracaonfe.setConfSMTP(new ArrayList<undefined>());
			configuracaonfe.setParentId(id);
			configuracaonfe.setEmprId(1);
			configuracaonfe.setModifyDateUTC(a.getTime());
			configuracaonfe.setCreateDateUTC(a.getTime());
			configuracaonfe.setCreateUser("system");
			configuracaonfe.setModifyUser("system");
			configuracaonfe.setProcessId(1);
			configuracaonfe.setModelAction(action);
	
			return configuracaonfe;
		}

	
	public ConfigVendas insertConfigVendas(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigVendas configvendas = new ConfigVendas();
			Date a = new Date();
			configvendas.setId(100);
			configvendas.setDescontoMaxVenda(new Double(1.99));
			configvendas.setObservacao(new ArrayList<undefined>());
			configvendas.setImprSegVia(new ArrayList<undefined>());
			configvendas.setImprAssinatura(new ArrayList<undefined>());
			configvendas.setImprResumoFinanc(new ArrayList<undefined>());
			configvendas.setAtuaPrecoClonar(new ArrayList<undefined>());
			configvendas.setImprColUnidade(new ArrayList<undefined>());
			configvendas.setBloquearvendProdSemEstoq(new ArrayList<undefined>());
			configvendas.setAddDespCalcImposto(new ArrayList<undefined>());
			configvendas.setRetSubstTribICMS(new ArrayList<undefined>());
			configvendas.setParentId(id);
			configvendas.setEmprId(1);
			configvendas.setModifyDateUTC(a.getTime());
			configvendas.setCreateDateUTC(a.getTime());
			configvendas.setCreateUser("system");
			configvendas.setModifyUser("system");
			configvendas.setProcessId(1);
			configvendas.setModelAction(action);
	
			return configvendas;
		}


}
