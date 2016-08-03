/** create by system gera-java version 1.0.0 27/07/2016 15:55 : 11*/
package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.ArrayList;
import java.util.Date;
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
import com.qat.samples.sysmgmt.bar.Configuracao.IConfiguracaoBAR;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.entidade.model.Boleto;
import com.qat.samples.sysmgmt.entidade.model.ConfigAlertas;
import com.qat.samples.sysmgmt.entidade.model.ConfigCarne;
import com.qat.samples.sysmgmt.entidade.model.ConfigEntrada;
import com.qat.samples.sysmgmt.entidade.model.ConfigFiscal;
import com.qat.samples.sysmgmt.entidade.model.ConfigGeral;
import com.qat.samples.sysmgmt.entidade.model.ConfigProduto;
import com.qat.samples.sysmgmt.entidade.model.ConfigSMTP;
import com.qat.samples.sysmgmt.entidade.model.ConfigVendas;
import com.qat.samples.sysmgmt.entidade.model.Configuracao;
import com.qat.samples.sysmgmt.entidade.model.ConfiguracaoNFe;
import com.qat.samples.sysmgmt.fiscal.model.Regime;
import com.qat.samples.sysmgmt.util.model.DoisValores;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CondominioBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(CondominioBARTest.class);
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
		Configuracao configuracao = insertConfiguracao(1000, TabelaEnum.CONFIGURACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		Configuracao configuracaoResponse = getConfiguracaoBAR().fetchConfiguracaoById(request);
		Assert.assertEquals(configuracaoResponse.getModifyUser(), "rod");
		getConfiguracaoBAR().updateConfiguracao(configuracao);
		configuracaoResponse = getConfiguracaoBAR().fetchConfiguracaoById(request);
		Assert.assertEquals(configuracaoResponse.getModifyUser(), "rod");
	}

	@Test
	public void testFetchConfiguracaosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
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
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Configuracao> response2 = getConfiguracaoBAR().fetchConfiguracaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfiguracaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
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
		Boleto boleto = insertBoleto(1010, TabelaEnum.BOLETO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		Boleto boletoResponse = getConfiguracaoBAR().fetchBoletoById(request);
		Assert.assertEquals(boletoResponse.getInstrucoes(), "instrucoes_0");
		getConfiguracaoBAR().updateBoleto(boleto);
		boletoResponse = getConfiguracaoBAR().fetchBoletoById(request);
		Assert.assertEquals(boletoResponse.getInstrucoes(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchBoletosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
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
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Boleto> response2 = getConfiguracaoBAR().fetchBoletosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllBoletos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Boleto> response3 = getConfiguracaoBAR().fetchBoletosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfigCarne ####======================================


@Test
	public void testDeleteConfigCarne()
	{
		ConfigCarne ConfigCarne = insertConfigCarne(4, TabelaEnum.CONFIGCARNE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfigCarne ConfigCarneResponse = getConfiguracaoBAR().fetchConfigCarneById(request);
		Assert.assertEquals(ConfigCarneResponse, null);
		getConfiguracaoBAR().insertConfigCarne(ConfigCarne);
		ConfigCarneResponse = getConfiguracaoBAR().fetchConfigCarneById(request);
		Assert.assertEquals(ConfigCarne.getId(), ConfigCarneResponse.getId());
		getConfiguracaoBAR().deleteConfigCarneById(ConfigCarne);
		ConfigCarneResponse = getConfiguracaoBAR().fetchConfigCarneById(request);
		Assert.assertEquals(ConfigCarneResponse, null);
	}

	@Test
	public void testFetchAllConfigCarnes()
	{
	ConfigCarne ConfigCarne = new ConfigCarne();
		List<ConfigCarne> response = getConfiguracaoBAR().fetchAllConfigCarnes(ConfigCarne).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigCarnes()
	{
		getConfiguracaoBAR().deleteAllConfigCarnes();
	ConfigCarne ConfigCarne = new ConfigCarne();
		List<ConfigCarne> response = getConfiguracaoBAR().fetchAllConfigCarnes(new ConfigCarne()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigCarne()
	{
		ConfigCarne ConfigCarne = insertConfigCarne(1010, TabelaEnum.CONFIGCARNE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfigCarne ConfigCarneResponse = getConfiguracaoBAR().fetchConfigCarneById(request);
		Assert.assertEquals(ConfigCarneResponse.getCarneBotelo().toString(), "0");
		getConfiguracaoBAR().updateConfigCarne(ConfigCarne);
		ConfigCarneResponse = getConfiguracaoBAR().fetchConfigCarneById(request);
		Assert.assertEquals(ConfigCarneResponse.getCarneBotelo().toString(), "1");
	}

	@Test
	public void testFetchConfigCarnesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfigCarne> response = getConfiguracaoBAR().fetchConfigCarnesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigCarnesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfigCarne> response2 = getConfiguracaoBAR().fetchConfigCarnesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigCarnes();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfigCarne> response3 = getConfiguracaoBAR().fetchConfigCarnesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfigEntrada ####======================================


@Test
	public void testDeleteConfigEntrada()
	{
		ConfigEntrada ConfigEntrada = insertConfigEntrada(4, TabelaEnum.CONFIGENTRADA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfigEntrada ConfigEntradaResponse = getConfiguracaoBAR().fetchConfigEntradaById(request);
		Assert.assertEquals(ConfigEntradaResponse, null);
		getConfiguracaoBAR().insertConfigEntrada(ConfigEntrada);
		ConfigEntradaResponse = getConfiguracaoBAR().fetchConfigEntradaById(request);
		Assert.assertEquals(ConfigEntrada.getId(), ConfigEntradaResponse.getId());
		getConfiguracaoBAR().deleteConfigEntradaById(ConfigEntrada);
		ConfigEntradaResponse = getConfiguracaoBAR().fetchConfigEntradaById(request);
		Assert.assertEquals(ConfigEntradaResponse, null);
	}

	@Test
	public void testFetchAllConfigEntradas()
	{
	ConfigEntrada ConfigEntrada = new ConfigEntrada();
		List<ConfigEntrada> response = getConfiguracaoBAR().fetchAllConfigEntradas(ConfigEntrada).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigEntradas()
	{
		getConfiguracaoBAR().deleteAllConfigEntradas();
		ConfigEntrada ConfigEntrada = new ConfigEntrada();
		List<ConfigEntrada> response = getConfiguracaoBAR().fetchAllConfigEntradas(new ConfigEntrada()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigEntrada()
	{
		ConfigEntrada ConfigEntrada = insertConfigEntrada(1010, TabelaEnum.CONFIGENTRADA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfigEntrada ConfigEntradaResponse = getConfiguracaoBAR().fetchConfigEntradaById(request);
		Assert.assertEquals(ConfigEntradaResponse.getValorTotalFixo().toString(), "0");
		getConfiguracaoBAR().updateConfigEntrada(ConfigEntrada);
		ConfigEntradaResponse = getConfiguracaoBAR().fetchConfigEntradaById(request);
		Assert.assertEquals(ConfigEntradaResponse.getValorTotalFixo().toString(), "1");
	}

	@Test
	public void testFetchConfigEntradasByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfigEntrada> response = getConfiguracaoBAR().fetchConfigEntradasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigEntradasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfigEntrada> response2 = getConfiguracaoBAR().fetchConfigEntradasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigEntradas();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfigEntrada> response3 = getConfiguracaoBAR().fetchConfigEntradasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfigFiscal ####======================================


@Test
	public void testDeleteConfigFiscal()
	{
		ConfigFiscal ConfigFiscal = insertConfigFiscal(4, TabelaEnum.CONFIGFISCAL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfigFiscal ConfigFiscalResponse = getConfiguracaoBAR().fetchConfigFiscalById(request);
		Assert.assertEquals(ConfigFiscalResponse, null);
		getConfiguracaoBAR().insertConfigFiscal(ConfigFiscal);
		ConfigFiscalResponse = getConfiguracaoBAR().fetchConfigFiscalById(request);
		Assert.assertEquals(ConfigFiscal.getId(), ConfigFiscalResponse.getId());
		getConfiguracaoBAR().deleteConfigFiscalById(ConfigFiscal);
		ConfigFiscalResponse = getConfiguracaoBAR().fetchConfigFiscalById(request);
		Assert.assertEquals(ConfigFiscalResponse, null);
	}

	@Test
	public void testFetchAllConfigFiscals()
	{
	ConfigFiscal ConfigFiscal = new ConfigFiscal();
		List<ConfigFiscal> response = getConfiguracaoBAR().fetchAllConfigFiscals(ConfigFiscal).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigFiscals()
	{
		getConfiguracaoBAR().deleteAllConfigFiscals();
	ConfigFiscal ConfigFiscal = new ConfigFiscal();
		List<ConfigFiscal> response = getConfiguracaoBAR().fetchAllConfigFiscals(new ConfigFiscal()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigFiscal()
	{
		ConfigFiscal ConfigFiscal = insertConfigFiscal(1010, TabelaEnum.CONFIGFISCAL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfigFiscal ConfigFiscalResponse = getConfiguracaoBAR().fetchConfigFiscalById(request);
		Assert.assertEquals(ConfigFiscalResponse.getAliqSimples().toString(), "10.0");
		getConfiguracaoBAR().updateConfigFiscal(ConfigFiscal);
		ConfigFiscalResponse = getConfiguracaoBAR().fetchConfigFiscalById(request);
		Assert.assertEquals(ConfigFiscalResponse.getAliqSimples().toString(), "1.99");
	}

	@Test
	public void testFetchConfigFiscalsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfigFiscal> response = getConfiguracaoBAR().fetchConfigFiscalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigFiscalsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfigFiscal> response2 = getConfiguracaoBAR().fetchConfigFiscalsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigFiscals();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfigFiscal> response3 = getConfiguracaoBAR().fetchConfigFiscalsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfigAlertas ####======================================


@Test
	public void testDeleteConfigAlertas()
	{
		ConfigAlertas ConfigAlertas = insertConfigAlertas(4, TabelaEnum.CONFIGALERTAS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfigAlertas ConfigAlertasResponse = getConfiguracaoBAR().fetchConfigAlertasById(request);
		Assert.assertEquals(ConfigAlertasResponse, null);
		getConfiguracaoBAR().insertConfigAlertas(ConfigAlertas);
		ConfigAlertasResponse = getConfiguracaoBAR().fetchConfigAlertasById(request);
		Assert.assertEquals(ConfigAlertas.getId(), ConfigAlertasResponse.getId());
		getConfiguracaoBAR().deleteConfigAlertasById(ConfigAlertas);
		ConfigAlertasResponse = getConfiguracaoBAR().fetchConfigAlertasById(request);
		Assert.assertEquals(ConfigAlertasResponse, null);
	}

	@Test
	public void testFetchAllConfigAlertass()
	{
	ConfigAlertas ConfigAlertas = new ConfigAlertas();
		List<ConfigAlertas> response = getConfiguracaoBAR().fetchAllConfigAlertass(ConfigAlertas).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigAlertass()
	{
		getConfiguracaoBAR().deleteAllConfigAlertass();
	ConfigAlertas ConfigAlertas = new ConfigAlertas();
		List<ConfigAlertas> response = getConfiguracaoBAR().fetchAllConfigAlertass(new ConfigAlertas()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigAlertas()
	{
		ConfigAlertas ConfigAlertas = insertConfigAlertas(1010, TabelaEnum.CONFIGALERTAS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfigAlertas ConfigAlertasResponse = getConfiguracaoBAR().fetchConfigAlertasById(request);
		Assert.assertEquals(ConfigAlertasResponse.getEstoqMax().toString(), "0");
		getConfiguracaoBAR().updateConfigAlertas(ConfigAlertas);
		ConfigAlertasResponse = getConfiguracaoBAR().fetchConfigAlertasById(request);
		Assert.assertEquals(ConfigAlertasResponse.getEstoqMax().toString(), "1");
	}

	@Test
	public void testFetchConfigAlertassByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfigAlertas> response = getConfiguracaoBAR().fetchConfigAlertassByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigAlertassByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfigAlertas> response2 = getConfiguracaoBAR().fetchConfigAlertassByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigAlertass();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfigAlertas> response3 = getConfiguracaoBAR().fetchConfigAlertassByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfigGeral ####======================================


@Test
	public void testDeleteConfigGeral()
	{
		ConfigGeral ConfigGeral = insertConfigGeral(4, TabelaEnum.CONFIGGERAL, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfigGeral ConfigGeralResponse = getConfiguracaoBAR().fetchConfigGeralById(request);
		Assert.assertEquals(ConfigGeralResponse, null);
		getConfiguracaoBAR().insertConfigGeral(ConfigGeral);
		ConfigGeralResponse = getConfiguracaoBAR().fetchConfigGeralById(request);
		Assert.assertEquals(ConfigGeral.getId(), ConfigGeralResponse.getId());
		getConfiguracaoBAR().deleteConfigGeralById(ConfigGeral);
		ConfigGeralResponse = getConfiguracaoBAR().fetchConfigGeralById(request);
		Assert.assertEquals(ConfigGeralResponse, null);
	}

	@Test
	public void testFetchAllConfigGerals()
	{
	ConfigGeral ConfigGeral = new ConfigGeral();
		List<ConfigGeral> response = getConfiguracaoBAR().fetchAllConfigGerals(ConfigGeral).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigGerals()
	{
		getConfiguracaoBAR().deleteAllConfigGerals();
	ConfigGeral ConfigGeral = new ConfigGeral();
		List<ConfigGeral> response = getConfiguracaoBAR().fetchAllConfigGerals(new ConfigGeral()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigGeral()
	{
		ConfigGeral ConfigGeral = insertConfigGeral(1010, TabelaEnum.CONFIGGERAL, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfigGeral ConfigGeralResponse = getConfiguracaoBAR().fetchConfigGeralById(request);
		Assert.assertEquals(ConfigGeralResponse.getCnpjCPFUnico().toString(), "0");
		getConfiguracaoBAR().updateConfigGeral(ConfigGeral);
		ConfigGeralResponse = getConfiguracaoBAR().fetchConfigGeralById(request);
		Assert.assertEquals(ConfigGeralResponse.getCnpjCPFUnico().toString(), "1");
	}

	@Test
	public void testFetchConfigGeralsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfigGeral> response = getConfiguracaoBAR().fetchConfigGeralsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigGeralsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfigGeral> response2 = getConfiguracaoBAR().fetchConfigGeralsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigGerals();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfigGeral> response3 = getConfiguracaoBAR().fetchConfigGeralsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfigProduto ####======================================


@Test
	public void testDeleteConfigProduto()
	{
		ConfigProduto ConfigProduto = insertConfigProduto(4, TabelaEnum.CONFIGPRODUTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfigProduto ConfigProdutoResponse = getConfiguracaoBAR().fetchConfigProdutoById(request);
		Assert.assertEquals(ConfigProdutoResponse, null);
		getConfiguracaoBAR().insertConfigProduto(ConfigProduto);
		ConfigProdutoResponse = getConfiguracaoBAR().fetchConfigProdutoById(request);
		Assert.assertEquals(ConfigProduto.getId(), ConfigProdutoResponse.getId());
		getConfiguracaoBAR().deleteConfigProdutoById(ConfigProduto);
		ConfigProdutoResponse = getConfiguracaoBAR().fetchConfigProdutoById(request);
		Assert.assertEquals(ConfigProdutoResponse, null);
	}

	@Test
	public void testFetchAllConfigProdutos()
	{
	ConfigProduto ConfigProduto = new ConfigProduto();
		List<ConfigProduto> response = getConfiguracaoBAR().fetchAllConfigProdutos(ConfigProduto).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigProdutos()
	{
		getConfiguracaoBAR().deleteAllConfigProdutos();
	ConfigProduto ConfigProduto = new ConfigProduto();
		List<ConfigProduto> response = getConfiguracaoBAR().fetchAllConfigProdutos(new ConfigProduto()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigProduto()
	{
		ConfigProduto ConfigProduto = insertConfigProduto(1010, TabelaEnum.CONFIGPRODUTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfigProduto ConfigProdutoResponse = getConfiguracaoBAR().fetchConfigProdutoById(request);
		Assert.assertEquals(ConfigProdutoResponse.getIpiCNPJProd(), "ipiCNPJProd_0");
		getConfiguracaoBAR().updateConfigProduto(ConfigProduto);
		ConfigProdutoResponse = getConfiguracaoBAR().fetchConfigProdutoById(request);
		Assert.assertEquals(ConfigProdutoResponse.getIpiCNPJProd(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigProdutosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfigProduto> response = getConfiguracaoBAR().fetchConfigProdutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigProdutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfigProduto> response2 = getConfiguracaoBAR().fetchConfigProdutosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigProdutos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfigProduto> response3 = getConfiguracaoBAR().fetchConfigProdutosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfigSMTP ####======================================


@Test
	public void testDeleteConfigSMTP()
	{
		ConfigSMTP ConfigSMTP = insertConfigSMTP(4, TabelaEnum.CONFIGSMTP, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfigSMTP ConfigSMTPResponse = getConfiguracaoBAR().fetchConfigSMTPById(request);
		Assert.assertEquals(ConfigSMTPResponse, null);
		getConfiguracaoBAR().insertConfigSMTP(ConfigSMTP);
		ConfigSMTPResponse = getConfiguracaoBAR().fetchConfigSMTPById(request);
		Assert.assertEquals(ConfigSMTP.getId(), ConfigSMTPResponse.getId());
		getConfiguracaoBAR().deleteConfigSMTPById(ConfigSMTP);
		ConfigSMTPResponse = getConfiguracaoBAR().fetchConfigSMTPById(request);
		Assert.assertEquals(ConfigSMTPResponse, null);
	}

	@Test
	public void testFetchAllConfigSMTPs()
	{
	ConfigSMTP ConfigSMTP = new ConfigSMTP();
		List<ConfigSMTP> response = getConfiguracaoBAR().fetchAllConfigSMTPs(ConfigSMTP).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigSMTPs()
	{
		getConfiguracaoBAR().deleteAllConfigSMTPs();
	ConfigSMTP ConfigSMTP = new ConfigSMTP();
		List<ConfigSMTP> response = getConfiguracaoBAR().fetchAllConfigSMTPs(new ConfigSMTP()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigSMTP()
	{
		ConfigSMTP ConfigSMTP = insertConfigSMTP(1010, TabelaEnum.CONFIGSMTP, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfigSMTP ConfigSMTPResponse = getConfiguracaoBAR().fetchConfigSMTPById(request);
		Assert.assertEquals(ConfigSMTPResponse.getEndEmail(), "endEmail_0");
		getConfiguracaoBAR().updateConfigSMTP(ConfigSMTP);
		ConfigSMTPResponse = getConfiguracaoBAR().fetchConfigSMTPById(request);
		Assert.assertEquals(ConfigSMTPResponse.getEndEmail(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfigSMTPsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfigSMTP> response = getConfiguracaoBAR().fetchConfigSMTPsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigSMTPsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfigSMTP> response2 = getConfiguracaoBAR().fetchConfigSMTPsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigSMTPs();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfigSMTP> response3 = getConfiguracaoBAR().fetchConfigSMTPsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfiguracaoNFe ####======================================


@Test
	public void testDeleteConfiguracaoNFe()
	{
		ConfiguracaoNFe ConfiguracaoNFe = insertConfiguracaoNFe(4, TabelaEnum.CONFIGURACAONFE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfiguracaoNFe ConfiguracaoNFeResponse = getConfiguracaoBAR().fetchConfiguracaoNFeById(request);
		Assert.assertEquals(ConfiguracaoNFeResponse, null);
		getConfiguracaoBAR().insertConfiguracaoNFe(ConfiguracaoNFe);
		ConfiguracaoNFeResponse = getConfiguracaoBAR().fetchConfiguracaoNFeById(request);
		Assert.assertEquals(ConfiguracaoNFe.getId(), ConfiguracaoNFeResponse.getId());
		getConfiguracaoBAR().deleteConfiguracaoNFeById(ConfiguracaoNFe);
		ConfiguracaoNFeResponse = getConfiguracaoBAR().fetchConfiguracaoNFeById(request);
		Assert.assertEquals(ConfiguracaoNFeResponse, null);
	}

	@Test
	public void testFetchAllConfiguracaoNFes()
	{
	ConfiguracaoNFe ConfiguracaoNFe = new ConfiguracaoNFe();
		List<ConfiguracaoNFe> response = getConfiguracaoBAR().fetchAllConfiguracaoNFes(ConfiguracaoNFe).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfiguracaoNFes()
	{
		getConfiguracaoBAR().deleteAllConfiguracaoNFes();
	ConfiguracaoNFe ConfiguracaoNFe = new ConfiguracaoNFe();
		List<ConfiguracaoNFe> response = getConfiguracaoBAR().fetchAllConfiguracaoNFes(new ConfiguracaoNFe()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfiguracaoNFe()
	{
		ConfiguracaoNFe ConfiguracaoNFe = insertConfiguracaoNFe(1010, TabelaEnum.CONFIGURACAONFE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfiguracaoNFe ConfiguracaoNFeResponse = getConfiguracaoBAR().fetchConfiguracaoNFeById(request);
		Assert.assertEquals(ConfiguracaoNFeResponse.getcSC(), "cSC_0");
		getConfiguracaoBAR().updateConfiguracaoNFe(ConfiguracaoNFe);
		ConfiguracaoNFeResponse = getConfiguracaoBAR().fetchConfiguracaoNFeById(request);
		Assert.assertEquals(ConfiguracaoNFeResponse.getcSC(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchConfiguracaoNFesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfiguracaoNFe> response = getConfiguracaoBAR().fetchConfiguracaoNFesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfiguracaoNFesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfiguracaoNFe> response2 = getConfiguracaoBAR().fetchConfiguracaoNFesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfiguracaoNFes();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfiguracaoNFe> response3 = getConfiguracaoBAR().fetchConfiguracaoNFesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ConfigVendas ####======================================


@Test
	public void testDeleteConfigVendas()
	{
		ConfigVendas ConfigVendas = insertConfigVendas(4, TabelaEnum.CONFIGVENDAS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ConfigVendas ConfigVendasResponse = getConfiguracaoBAR().fetchConfigVendasById(request);
		Assert.assertEquals(ConfigVendasResponse, null);
		getConfiguracaoBAR().insertConfigVendas(ConfigVendas);
		ConfigVendasResponse = getConfiguracaoBAR().fetchConfigVendasById(request);
		Assert.assertEquals(ConfigVendas.getId(), ConfigVendasResponse.getId());
		getConfiguracaoBAR().deleteConfigVendasById(ConfigVendas);
		ConfigVendasResponse = getConfiguracaoBAR().fetchConfigVendasById(request);
		Assert.assertEquals(ConfigVendasResponse, null);
	}

	@Test
	public void testFetchAllConfigVendass()
	{
	ConfigVendas ConfigVendas = new ConfigVendas();
		List<ConfigVendas> response = getConfiguracaoBAR().fetchAllConfigVendass(ConfigVendas).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllConfigVendass()
	{
		getConfiguracaoBAR().deleteAllConfigVendass();
	ConfigVendas ConfigVendas = new ConfigVendas();
		List<ConfigVendas> response = getConfiguracaoBAR().fetchAllConfigVendass(new ConfigVendas()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateConfigVendas()
	{
		ConfigVendas ConfigVendas = insertConfigVendas(1010, TabelaEnum.CONFIGVENDAS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1010);
		ConfigVendas ConfigVendasResponse = getConfiguracaoBAR().fetchConfigVendasById(request);
		Assert.assertEquals(ConfigVendasResponse.getBloquearvendProdSemEstoq().toString(), "0");
		getConfiguracaoBAR().updateConfigVendas(ConfigVendas);
		ConfigVendasResponse = getConfiguracaoBAR().fetchConfigVendasById(request);
		Assert.assertEquals(ConfigVendasResponse.getBloquearvendProdSemEstoq().toString(), "1");
	}

	@Test
	public void testFetchConfigVendassByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ConfigVendas> response = getConfiguracaoBAR().fetchConfigVendassByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getConfiguracaoBAR().fetchConfigVendassByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ConfigVendas> response2 = getConfiguracaoBAR().fetchConfigVendassByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getConfiguracaoBAR().deleteAllConfigVendass();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ConfigVendas> response3 = getConfiguracaoBAR().fetchConfigVendassByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertConfiguracao.sql", false);
		executeSqlScript("conf/insertBoleto.sql", false);
		executeSqlScript("conf/insertConfigCarne.sql", false);
		executeSqlScript("conf/insertConfigEntrada.sql", false);
		executeSqlScript("conf/insertConfigFiscal.sql", false);
		executeSqlScript("conf/insertConfigAlertas.sql", false);
		executeSqlScript("conf/insertConfigGeral.sql", false);
		executeSqlScript("conf/insertConfigProduto.sql", false);
		executeSqlScript("conf/insertConfigSMTP.sql", false);
		executeSqlScript("conf/insertConfiguracaoNFe.sql", false);
		executeSqlScript("conf/insertConfigVendas.sql", false);
	}


	public Configuracao insertConfiguracao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Configuracao configuracao = new Configuracao();
			Date a = new Date();
			configuracao.setId(id);
			configuracao.setConfGeral(insertConfigGeral(id,TabelaEnum.BOLETO,action));
			configuracao.setConfNFe(insertConfiguracaoNFe(id,TabelaEnum.BOLETO,action));
			configuracao.setConfFiscal(insertConfigFiscal(id,TabelaEnum.BOLETO,action));
			configuracao.setConfProd(insertConfigProduto(id,TabelaEnum.BOLETO,action));
			configuracao.setConfVendas(insertConfigVendas(id,TabelaEnum.BOLETO,action));
			configuracao.setConfCMTP(insertConfigSMTP(id,TabelaEnum.BOLETO,action));
			configuracao.setConfEntrada(insertConfigEntrada(id,TabelaEnum.BOLETO,action));
			configuracao.setConfCarne(insertConfigCarne(id,TabelaEnum.BOLETO,action));
			configuracao.setBoletoList(new ArrayList<Boleto>());
			configuracao.getBoletoList().add(insertBoleto(id,TabelaEnum.BOLETO,action));
			configuracao.setParentId(id);
			configuracao.setEmprId(1);
			configuracao.setModifyDateUTC(a.getTime());
			configuracao.setCreateDateUTC(a.getTime());
			configuracao.setCreateUser("system");
			configuracao.setModifyUser("rod");
			configuracao.setProcessId(1);
			configuracao.setModelAction(action);

			return configuracao;
		}


	public Boleto insertBoleto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Boleto boleto = new Boleto();
			Date a = new Date();
			boleto.setId(id);
			boleto.setAtivarBolOnLine(1);
			boleto.setTipoBoleto(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			boleto.setAgencia(100);
			boleto.setCedente(100);
			boleto.setJuros(new Double(1.99));
			boleto.setTipoCalcMora(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			boleto.setMora(new Double(1.99));
			boleto.setInstrucoes("NATIVE INSERT UPDATE");
			boleto.setDemonstrativo("NATIVE INSERT UPDATE");
			boleto.setImpJuros(1);
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
			ConfigCarne ConfigCarne = new ConfigCarne();
			Date a = new Date();
			ConfigCarne.setId(id);
			ConfigCarne.setCarneBotelo(1);
			ConfigCarne.setCarneNormal(1);
			ConfigCarne.setParentId(id);
			ConfigCarne.setEmprId(1);
			ConfigCarne.setModifyDateUTC(a.getTime());
			ConfigCarne.setCreateDateUTC(a.getTime());
			ConfigCarne.setCreateUser("system");
			ConfigCarne.setModifyUser("system");
			ConfigCarne.setProcessId(1);
			ConfigCarne.setModelAction(action);

			return ConfigCarne;
		}


	public ConfigEntrada insertConfigEntrada(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigEntrada ConfigEntrada = new ConfigEntrada();
			Date a = new Date();
			ConfigEntrada.setId(id);
			ConfigEntrada.setValorTotalFixo(1);
			ConfigEntrada.setManterPrecoVendaProd(1);
			ConfigEntrada.setParentId(id);
			ConfigEntrada.setEmprId(1);
			ConfigEntrada.setModifyDateUTC(a.getTime());
			ConfigEntrada.setCreateDateUTC(a.getTime());
			ConfigEntrada.setCreateUser("system");
			ConfigEntrada.setModifyUser("system");
			ConfigEntrada.setProcessId(1);
			ConfigEntrada.setModelAction(action);

			return ConfigEntrada;
		}


	public ConfigFiscal insertConfigFiscal(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigFiscal ConfigFiscal = new ConfigFiscal();
			Date a = new Date();
			ConfigFiscal.setId(id);
			ConfigFiscal.setPrincAtividade(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigFiscal.setRegime(new Regime());
			ConfigFiscal.setAliqSimples(new Double(1.99));
			ConfigFiscal.setParentId(id);
			ConfigFiscal.setEmprId(1);
			ConfigFiscal.setModifyDateUTC(a.getTime());
			ConfigFiscal.setCreateDateUTC(a.getTime());
			ConfigFiscal.setCreateUser("system");
			ConfigFiscal.setModifyUser("system");
			ConfigFiscal.setProcessId(1);
			ConfigFiscal.setModelAction(action);

			return ConfigFiscal;
		}


	public ConfigAlertas insertConfigAlertas(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigAlertas ConfigAlertas = new ConfigAlertas();
			Date a = new Date();
			ConfigAlertas.setId(id);
			ConfigAlertas.setEstoqMin(1);
			ConfigAlertas.setEstoqMax(1);
			ConfigAlertas.setErroNFe(1);
			ConfigAlertas.setPdCompra(1);
			ConfigAlertas.setParentId(id);
			ConfigAlertas.setEmprId(1);
			ConfigAlertas.setModifyDateUTC(a.getTime());
			ConfigAlertas.setCreateDateUTC(a.getTime());
			ConfigAlertas.setCreateUser("system");
			ConfigAlertas.setModifyUser("system");
			ConfigAlertas.setProcessId(1);
			ConfigAlertas.setModelAction(action);

			return ConfigAlertas;
		}


	public ConfigGeral insertConfigGeral(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigGeral ConfigGeral = new ConfigGeral();
			Date a = new Date();
			ConfigGeral.setId(id);
			ConfigGeral.setFusoHorario(100);
			ConfigGeral.setCasasDecimais(100);
			ConfigGeral.setDiasCartaCobr(100);
			ConfigGeral.setInfPosicionarMouse(1);
			ConfigGeral.setCnpjCPFUnico(1);
			ConfigGeral.setImpCodPersonalizado(1);
			ConfigGeral.setLogListRelImp(1);
			ConfigGeral.setObsProdFinProd(1);
			ConfigGeral.setParentId(id);
			ConfigGeral.setEmprId(1);
			ConfigGeral.setModifyDateUTC(a.getTime());
			ConfigGeral.setCreateDateUTC(a.getTime());
			ConfigGeral.setCreateUser("system");
			ConfigGeral.setModifyUser("system");
			ConfigGeral.setProcessId(1);
			ConfigGeral.setModelAction(action);

			return ConfigGeral;
		}


	public ConfigProduto insertConfigProduto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigProduto ConfigProduto = new ConfigProduto();
			Date a = new Date();
			ConfigProduto.setId(id);
			ConfigProduto.setCfop(new Cfop());
			ConfigProduto.setIcmsSitTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsOrigem(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsModalidadeBC(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsRedBaseCalc(new Double(1.99));
			ConfigProduto.setIcmsAliq(new Double(1.99));
			ConfigProduto.setIcmsMotDesoneracao(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsModBCST(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIcmsMargValAdic(new Double(1.99));
			ConfigProduto.setIcmsRedBaseCalcST(new Double(1.99));
			ConfigProduto.setIcmsPrecoUnitPautaST(new Double(1.99));
			ConfigProduto.setIcmsAliqST(new Double(1.99));
			ConfigProduto.setIpiSitTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIpiClasCigarroBebida(new Double(1.99));
			ConfigProduto.setIpiCNPJProd("NATIVE INSERT UPDATE");
			ConfigProduto.setIpiCodSeloCont("NATIVE INSERT UPDATE");
			ConfigProduto.setIpiQtdSelo(new Double(1.99));
			ConfigProduto.setIpiCodEnquad(100);
			ConfigProduto.setIpiTipCalc(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setIpiAliq(new Double(1.99));
			ConfigProduto.setPisSitTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setPisAliq(new Double(1.99));
			ConfigProduto.setPisValUnidtrib(new Double(1.99));
			ConfigProduto.setPistipoCalcSubstTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setPisAliqST(new Double(1.99));
			ConfigProduto.setPisValorAliqST(new Double(1.99));
			ConfigProduto.setCofinsSubstTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setCofinsAliq(new Double(1.99));
			ConfigProduto.setCofinsValorAliq(new Double(1.99));
			ConfigProduto.setCofinsTipoCalcSubstTrib(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigProduto.setCofinsAliqST(new Double(1.99));
			ConfigProduto.setCofinsValorAliqST(new Double(1.99));
			ConfigProduto.setParentId(id);
			ConfigProduto.setEmprId(1);
			ConfigProduto.setModifyDateUTC(a.getTime());
			ConfigProduto.setCreateDateUTC(a.getTime());
			ConfigProduto.setCreateUser("system");
			ConfigProduto.setModifyUser("system");
			ConfigProduto.setProcessId(1);
			ConfigProduto.setModelAction(action);

			return ConfigProduto;
		}


	public ConfigSMTP insertConfigSMTP(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigSMTP ConfigSMTP = new ConfigSMTP();
			Date a = new Date();
			ConfigSMTP.setId(id);
			ConfigSMTP.setServSMTP("NATIVE INSERT UPDATE");
			ConfigSMTP.setPorta("NATIVE INSERT UPDATE");
			ConfigSMTP.setEndEmail("NATIVE INSERT UPDATE");
			ConfigSMTP.setUsuario("NATIVE INSERT UPDATE");
			ConfigSMTP.setSenha("NATIVE INSERT UPDATE");
			ConfigSMTP.setSeguranca(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfigSMTP.setParentId(id);
			ConfigSMTP.setEmprId(1);
			ConfigSMTP.setModifyDateUTC(a.getTime());
			ConfigSMTP.setCreateDateUTC(a.getTime());
			ConfigSMTP.setCreateUser("system");
			ConfigSMTP.setModifyUser("system");
			ConfigSMTP.setProcessId(1);
			ConfigSMTP.setModelAction(action);

			return ConfigSMTP;
		}


	public ConfiguracaoNFe insertConfiguracaoNFe(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfiguracaoNFe ConfiguracaoNFe = new ConfiguracaoNFe();
			Date a = new Date();
			ConfiguracaoNFe.setId(id);
			ConfiguracaoNFe.setPresCompr(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfiguracaoNFe.setDestConsFinal(1);
			ConfiguracaoNFe.setPreencherDataHora(1);
			ConfiguracaoNFe.setIcmsPadrao(new Double(1.99));
			ConfiguracaoNFe.setIpiPadrao(new Double(1.99));
			ConfiguracaoNFe.setPisPadrao(new Double(1.99));
			ConfiguracaoNFe.setCofinsPadrao(new Double(1.99));
			ConfiguracaoNFe.setAmbienteEnvio(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfiguracaoNFe.setServMsmNota(insertDoisValor(id,TabelaEnum.CONFIGVENDAS, action));
			ConfiguracaoNFe.setSerieEnvio("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setAnexarXmlEmail(1);
			ConfiguracaoNFe.setIdCSC("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setcSC("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setInformacaoAdd("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setCertificado("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setSenha("NATIVE INSERT UPDATE");
			ConfiguracaoNFe.setSalvarSenha(1);
			ConfiguracaoNFe.setCfopPadrao(new Cfop());
			ConfiguracaoNFe.setConfSMTP(new ConfigSMTP());
			ConfiguracaoNFe.setParentId(id);
			ConfiguracaoNFe.setEmprId(1);
			ConfiguracaoNFe.setModifyDateUTC(a.getTime());
			ConfiguracaoNFe.setCreateDateUTC(a.getTime());
			ConfiguracaoNFe.setCreateUser("system");
			ConfiguracaoNFe.setModifyUser("system");
			ConfiguracaoNFe.setProcessId(1);
			ConfiguracaoNFe.setModelAction(action);

			return ConfiguracaoNFe;
		}


	public ConfigVendas insertConfigVendas(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ConfigVendas ConfigVendas = new ConfigVendas();
			Date a = new Date();
			ConfigVendas.setId(id);
			ConfigVendas.setDescontoMaxVenda(new Double(1.99));
			ConfigVendas.setObservacao("observação");
			ConfigVendas.setImprSegVia(1);
			ConfigVendas.setImprAssinatura(1);
			ConfigVendas.setImprResumoFinanc(1);
			ConfigVendas.setAtuaPrecoClonar(1);
			ConfigVendas.setImprColUnidade(1);
			ConfigVendas.setBloquearvendProdSemEstoq(1);
			ConfigVendas.setAddDespCalcImposto(1);
			ConfigVendas.setRetSubstTribICMS(1);
			ConfigVendas.setParentId(id);
			ConfigVendas.setEmprId(1);
			ConfigVendas.setModifyDateUTC(a.getTime());
			ConfigVendas.setCreateDateUTC(a.getTime());
			ConfigVendas.setCreateUser("system");
			ConfigVendas.setModifyUser("system");
			ConfigVendas.setProcessId(1);
			ConfigVendas.setModelAction(action);

			return ConfigVendas;
		}

	public DoisValores insertDoisValor(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		DoisValores ConfigVendas = new DoisValores();
		Date a = new Date();
		ConfigVendas.setId(id);
		ConfigVendas.setNome("teste");
		ConfigVendas.setDescricao("Description");
		ConfigVendas.setParentId(id);
		ConfigVendas.setEmprId(1);
		ConfigVendas.setModifyDateUTC(a.getTime());
		ConfigVendas.setCreateDateUTC(a.getTime());
		ConfigVendas.setCreateUser("system");
		ConfigVendas.setModifyUser("system");
		ConfigVendas.setProcessId(1);
		ConfigVendas.setModelAction(action);

		return ConfigVendas;
	}


}
