/** create by system gera-java version 1.0.0 13/05/2016 18:8 : 1*/
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
import com.qat.samples.sysmgmt.bar.Produto.IProdutoBAR;
import com.qat.samples.sysmgmt.cfop.model.Cfop;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeItens;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ProdutoBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(ProdutoBARTest.class);
private IProdutoBAR produtoBAR; // injected by Spring through @Resource

@Resource
public void setProdutoBAR(IProdutoBAR produtoBAR)
{
	this.produtoBAR = produtoBAR;
}

public IProdutoBAR getProdutoBAR()
{
	return produtoBAR;
}


//===================================### PRODUTOPARENT ####======================================


@Test
	public void testDeleteProdutoparent()
	{
		Produtoparent produtoparent = insertProdutoparent(4, TabelaEnum.PRODUTOPARENT, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Produtoparent produtoparentResponse = getProdutoBAR().fetchProdutoparentById(request);
		Assert.assertEquals(produtoparentResponse, null);
		getProdutoBAR().insertProdutoparent(produtoparent);
		produtoparentResponse = getProdutoBAR().fetchProdutoparentById(request);
		Assert.assertEquals(produtoparent.getId(), produtoparentResponse.getId());
		getProdutoBAR().deleteProdutoparentById(produtoparent);
		produtoparentResponse = getProdutoBAR().fetchProdutoparentById(request);
		Assert.assertEquals(produtoparentResponse, null);
	}

	@Test
	public void testFetchAllProdutoparents()
	{
	Produtoparent produtoparent = new Produtoparent();
		List<Produtoparent> response = getProdutoBAR().fetchAllProdutoparents(produtoparent).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllProdutoparents()
	{
		getProdutoBAR().deleteAllProdutoparents();
	Produtoparent produtoparent = new Produtoparent();
		List<Produtoparent> response = getProdutoBAR().fetchAllProdutoparents(new Produtoparent()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateProdutoparent()
	{
		Produtoparent produtoparent = insertProdutoparent(1, TabelaEnum.PRODUTOPARENT, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Produtoparent produtoparentResponse = getProdutoBAR().fetchProdutoparentById(request);
		Assert.assertEquals(produtoparentResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateProdutoparent(produtoparent);
		produtoparentResponse = getProdutoBAR().fetchProdutoparentById(request);
		Assert.assertEquals(produtoparentResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchProdutoparentsByRequest() throws Exception
	{
		// check for valid and precount
		ProdutoparentInquiryRequest request = new ProdutoparentInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Produtoparent> response = getProdutoBAR().fetchProdutoparentsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchProdutoparentsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ProdutoparentInquiryRequest request2 = new ProdutoparentInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Produtoparent> response2 = getProdutoBAR().fetchProdutoparentsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllProdutoparents();
		ProdutoparentInquiryRequest request3 = new ProdutoparentInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Produtoparent> response3 = getProdutoBAR().fetchProdutoparentsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### PRODUTO ####======================================


@Test
	public void testDeleteProduto()
	{
		Produto produto = insertProduto(4, TabelaEnum.PRODUTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Produto produtoResponse = getProdutoBAR().fetchProdutoById(request);
		Assert.assertEquals(produtoResponse, null);
		getProdutoBAR().insertProduto(produto);
		produtoResponse = getProdutoBAR().fetchProdutoById(request);
		Assert.assertEquals(produto.getId(), produtoResponse.getId());
		getProdutoBAR().deleteProdutoById(produto);
		produtoResponse = getProdutoBAR().fetchProdutoById(request);
		Assert.assertEquals(produtoResponse, null);
	}

	@Test
	public void testFetchAllProdutos()
	{
	Produto produto = new Produto();
		List<Produto> response = getProdutoBAR().fetchAllProdutos(produto).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllProdutos()
	{
		getProdutoBAR().deleteAllProdutos();
	Produto produto = new Produto();
		List<Produto> response = getProdutoBAR().fetchAllProdutos(new Produto()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateProduto()
	{
		Produto produto = insertProduto(1, TabelaEnum.PRODUTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Produto produtoResponse = getProdutoBAR().fetchProdutoById(request);
		Assert.assertEquals(produtoResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateProduto(produto);
		produtoResponse = getProdutoBAR().fetchProdutoById(request);
		Assert.assertEquals(produtoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchProdutosByRequest() throws Exception
	{
		// check for valid and precount
		ProdutoInquiryRequest request = new ProdutoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Produto> response = getProdutoBAR().fetchProdutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchProdutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ProdutoInquiryRequest request2 = new ProdutoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Produto> response2 = getProdutoBAR().fetchProdutosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllProdutos();
		ProdutoInquiryRequest request3 = new ProdutoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Produto> response3 = getProdutoBAR().fetchProdutosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CFOP ####======================================


@Test
	public void testDeleteCfop()
	{
		Cfop cfop = insertCfop(4, TabelaEnum.CFOP, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Cfop cfopResponse = getProdutoBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse, null);
		getProdutoBAR().insertCfop(cfop);
		cfopResponse = getProdutoBAR().fetchCfopById(request);
		Assert.assertEquals(cfop.getId(), cfopResponse.getId());
		getProdutoBAR().deleteCfopById(cfop);
		cfopResponse = getProdutoBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse, null);
	}

	@Test
	public void testFetchAllCfops()
	{
	Cfop cfop = new Cfop();
		List<Cfop> response = getProdutoBAR().fetchAllCfops(cfop).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCfops()
	{
		getProdutoBAR().deleteAllCfops();
	Cfop cfop = new Cfop();
		List<Cfop> response = getProdutoBAR().fetchAllCfops(new Cfop()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCfop()
	{
		Cfop cfop = insertCfop(1, TabelaEnum.CFOP, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Cfop cfopResponse = getProdutoBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateCfop(cfop);
		cfopResponse = getProdutoBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCfopsByRequest() throws Exception
	{
		// check for valid and precount
		CfopInquiryRequest request = new CfopInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Cfop> response = getProdutoBAR().fetchCfopsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchCfopsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CfopInquiryRequest request2 = new CfopInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Cfop> response2 = getProdutoBAR().fetchCfopsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllCfops();
		CfopInquiryRequest request3 = new CfopInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Cfop> response3 = getProdutoBAR().fetchCfopsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### MARCA ####======================================


@Test
	public void testDeleteMarca()
	{
		Marca marca = insertMarca(4, TabelaEnum.MARCA, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Marca marcaResponse = getProdutoBAR().fetchMarcaById(request);
		Assert.assertEquals(marcaResponse, null);
		getProdutoBAR().insertMarca(marca);
		marcaResponse = getProdutoBAR().fetchMarcaById(request);
		Assert.assertEquals(marca.getId(), marcaResponse.getId());
		getProdutoBAR().deleteMarcaById(marca);
		marcaResponse = getProdutoBAR().fetchMarcaById(request);
		Assert.assertEquals(marcaResponse, null);
	}

	@Test
	public void testFetchAllMarcas()
	{
	Marca marca = new Marca();
		List<Marca> response = getProdutoBAR().fetchAllMarcas(marca).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllMarcas()
	{
		getProdutoBAR().deleteAllMarcas();
	Marca marca = new Marca();
		List<Marca> response = getProdutoBAR().fetchAllMarcas(new Marca()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateMarca()
	{
		Marca marca = insertMarca(1, TabelaEnum.MARCA, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Marca marcaResponse = getProdutoBAR().fetchMarcaById(request);
		Assert.assertEquals(marcaResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateMarca(marca);
		marcaResponse = getProdutoBAR().fetchMarcaById(request);
		Assert.assertEquals(marcaResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchMarcasByRequest() throws Exception
	{
		// check for valid and precount
		MarcaInquiryRequest request = new MarcaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Marca> response = getProdutoBAR().fetchMarcasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchMarcasByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		MarcaInquiryRequest request2 = new MarcaInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Marca> response2 = getProdutoBAR().fetchMarcasByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllMarcas();
		MarcaInquiryRequest request3 = new MarcaInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Marca> response3 = getProdutoBAR().fetchMarcasByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### MARCAPRODUTO ####======================================


@Test
	public void testDeleteMarcaproduto()
	{
		Marcaproduto marcaproduto = insertMarcaproduto(4, TabelaEnum.MARCAPRODUTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Marcaproduto marcaprodutoResponse = getProdutoBAR().fetchMarcaprodutoById(request);
		Assert.assertEquals(marcaprodutoResponse, null);
		getProdutoBAR().insertMarcaproduto(marcaproduto);
		marcaprodutoResponse = getProdutoBAR().fetchMarcaprodutoById(request);
		Assert.assertEquals(marcaproduto.getId(), marcaprodutoResponse.getId());
		getProdutoBAR().deleteMarcaprodutoById(marcaproduto);
		marcaprodutoResponse = getProdutoBAR().fetchMarcaprodutoById(request);
		Assert.assertEquals(marcaprodutoResponse, null);
	}

	@Test
	public void testFetchAllMarcaprodutos()
	{
	Marcaproduto marcaproduto = new Marcaproduto();
		List<Marcaproduto> response = getProdutoBAR().fetchAllMarcaprodutos(marcaproduto).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllMarcaprodutos()
	{
		getProdutoBAR().deleteAllMarcaprodutos();
	Marcaproduto marcaproduto = new Marcaproduto();
		List<Marcaproduto> response = getProdutoBAR().fetchAllMarcaprodutos(new Marcaproduto()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateMarcaproduto()
	{
		Marcaproduto marcaproduto = insertMarcaproduto(1, TabelaEnum.MARCAPRODUTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Marcaproduto marcaprodutoResponse = getProdutoBAR().fetchMarcaprodutoById(request);
		Assert.assertEquals(marcaprodutoResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateMarcaproduto(marcaproduto);
		marcaprodutoResponse = getProdutoBAR().fetchMarcaprodutoById(request);
		Assert.assertEquals(marcaprodutoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchMarcaprodutosByRequest() throws Exception
	{
		// check for valid and precount
		MarcaprodutoInquiryRequest request = new MarcaprodutoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Marcaproduto> response = getProdutoBAR().fetchMarcaprodutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchMarcaprodutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		MarcaprodutoInquiryRequest request2 = new MarcaprodutoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Marcaproduto> response2 = getProdutoBAR().fetchMarcaprodutosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllMarcaprodutos();
		MarcaprodutoInquiryRequest request3 = new MarcaprodutoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Marcaproduto> response3 = getProdutoBAR().fetchMarcaprodutosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### GRUPO ####======================================


@Test
	public void testDeleteGrupo()
	{
		Grupo grupo = insertGrupo(4, TabelaEnum.GRUPO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Grupo grupoResponse = getProdutoBAR().fetchGrupoById(request);
		Assert.assertEquals(grupoResponse, null);
		getProdutoBAR().insertGrupo(grupo);
		grupoResponse = getProdutoBAR().fetchGrupoById(request);
		Assert.assertEquals(grupo.getId(), grupoResponse.getId());
		getProdutoBAR().deleteGrupoById(grupo);
		grupoResponse = getProdutoBAR().fetchGrupoById(request);
		Assert.assertEquals(grupoResponse, null);
	}

	@Test
	public void testFetchAllGrupos()
	{
	Grupo grupo = new Grupo();
		List<Grupo> response = getProdutoBAR().fetchAllGrupos(grupo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllGrupos()
	{
		getProdutoBAR().deleteAllGrupos();
	Grupo grupo = new Grupo();
		List<Grupo> response = getProdutoBAR().fetchAllGrupos(new Grupo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateGrupo()
	{
		Grupo grupo = insertGrupo(1, TabelaEnum.GRUPO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Grupo grupoResponse = getProdutoBAR().fetchGrupoById(request);
		Assert.assertEquals(grupoResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateGrupo(grupo);
		grupoResponse = getProdutoBAR().fetchGrupoById(request);
		Assert.assertEquals(grupoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchGruposByRequest() throws Exception
	{
		// check for valid and precount
		GrupoInquiryRequest request = new GrupoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Grupo> response = getProdutoBAR().fetchGruposByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchGruposByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		GrupoInquiryRequest request2 = new GrupoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Grupo> response2 = getProdutoBAR().fetchGruposByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllGrupos();
		GrupoInquiryRequest request3 = new GrupoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Grupo> response3 = getProdutoBAR().fetchGruposByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### SUBGRUPO ####======================================


@Test
	public void testDeleteSubgrupo()
	{
		Subgrupo subgrupo = insertSubgrupo(4, TabelaEnum.SUBGRUPO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Subgrupo subgrupoResponse = getProdutoBAR().fetchSubgrupoById(request);
		Assert.assertEquals(subgrupoResponse, null);
		getProdutoBAR().insertSubgrupo(subgrupo);
		subgrupoResponse = getProdutoBAR().fetchSubgrupoById(request);
		Assert.assertEquals(subgrupo.getId(), subgrupoResponse.getId());
		getProdutoBAR().deleteSubgrupoById(subgrupo);
		subgrupoResponse = getProdutoBAR().fetchSubgrupoById(request);
		Assert.assertEquals(subgrupoResponse, null);
	}

	@Test
	public void testFetchAllSubgrupos()
	{
	Subgrupo subgrupo = new Subgrupo();
		List<Subgrupo> response = getProdutoBAR().fetchAllSubgrupos(subgrupo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllSubgrupos()
	{
		getProdutoBAR().deleteAllSubgrupos();
	Subgrupo subgrupo = new Subgrupo();
		List<Subgrupo> response = getProdutoBAR().fetchAllSubgrupos(new Subgrupo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateSubgrupo()
	{
		Subgrupo subgrupo = insertSubgrupo(1, TabelaEnum.SUBGRUPO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Subgrupo subgrupoResponse = getProdutoBAR().fetchSubgrupoById(request);
		Assert.assertEquals(subgrupoResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateSubgrupo(subgrupo);
		subgrupoResponse = getProdutoBAR().fetchSubgrupoById(request);
		Assert.assertEquals(subgrupoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchSubgruposByRequest() throws Exception
	{
		// check for valid and precount
		SubgrupoInquiryRequest request = new SubgrupoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Subgrupo> response = getProdutoBAR().fetchSubgruposByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchSubgruposByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		SubgrupoInquiryRequest request2 = new SubgrupoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Subgrupo> response2 = getProdutoBAR().fetchSubgruposByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllSubgrupos();
		SubgrupoInquiryRequest request3 = new SubgrupoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Subgrupo> response3 = getProdutoBAR().fetchSubgruposByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### UNIMED ####======================================


@Test
	public void testDeleteUnimed()
	{
		Unimed unimed = insertUnimed(4, TabelaEnum.UNIMED, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Unimed unimedResponse = getProdutoBAR().fetchUnimedById(request);
		Assert.assertEquals(unimedResponse, null);
		getProdutoBAR().insertUnimed(unimed);
		unimedResponse = getProdutoBAR().fetchUnimedById(request);
		Assert.assertEquals(unimed.getId(), unimedResponse.getId());
		getProdutoBAR().deleteUnimedById(unimed);
		unimedResponse = getProdutoBAR().fetchUnimedById(request);
		Assert.assertEquals(unimedResponse, null);
	}

	@Test
	public void testFetchAllUnimeds()
	{
	Unimed unimed = new Unimed();
		List<Unimed> response = getProdutoBAR().fetchAllUnimeds(unimed).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllUnimeds()
	{
		getProdutoBAR().deleteAllUnimeds();
	Unimed unimed = new Unimed();
		List<Unimed> response = getProdutoBAR().fetchAllUnimeds(new Unimed()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateUnimed()
	{
		Unimed unimed = insertUnimed(1, TabelaEnum.UNIMED, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Unimed unimedResponse = getProdutoBAR().fetchUnimedById(request);
		Assert.assertEquals(unimedResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateUnimed(unimed);
		unimedResponse = getProdutoBAR().fetchUnimedById(request);
		Assert.assertEquals(unimedResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchUnimedsByRequest() throws Exception
	{
		// check for valid and precount
		UnimedInquiryRequest request = new UnimedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Unimed> response = getProdutoBAR().fetchUnimedsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchUnimedsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		UnimedInquiryRequest request2 = new UnimedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Unimed> response2 = getProdutoBAR().fetchUnimedsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllUnimeds();
		UnimedInquiryRequest request3 = new UnimedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Unimed> response3 = getProdutoBAR().fetchUnimedsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### TRIBUTACAO ####======================================


@Test
	public void testDeleteTributacao()
	{
		Tributacao tributacao = insertTributacao(4, TabelaEnum.TRIBUTACAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Tributacao tributacaoResponse = getProdutoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse, null);
		getProdutoBAR().insertTributacao(tributacao);
		tributacaoResponse = getProdutoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacao.getId(), tributacaoResponse.getId());
		getProdutoBAR().deleteTributacaoById(tributacao);
		tributacaoResponse = getProdutoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse, null);
	}

	@Test
	public void testFetchAllTributacaos()
	{
	Tributacao tributacao = new Tributacao();
		List<Tributacao> response = getProdutoBAR().fetchAllTributacaos(tributacao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllTributacaos()
	{
		getProdutoBAR().deleteAllTributacaos();
	Tributacao tributacao = new Tributacao();
		List<Tributacao> response = getProdutoBAR().fetchAllTributacaos(new Tributacao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateTributacao()
	{
		Tributacao tributacao = insertTributacao(1, TabelaEnum.TRIBUTACAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Tributacao tributacaoResponse = getProdutoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateTributacao(tributacao);
		tributacaoResponse = getProdutoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchTributacaosByRequest() throws Exception
	{
		// check for valid and precount
		TributacaoInquiryRequest request = new TributacaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Tributacao> response = getProdutoBAR().fetchTributacaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchTributacaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		TributacaoInquiryRequest request2 = new TributacaoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Tributacao> response2 = getProdutoBAR().fetchTributacaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllTributacaos();
		TributacaoInquiryRequest request3 = new TributacaoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Tributacao> response3 = getProdutoBAR().fetchTributacaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CUSTO ####======================================


@Test
	public void testDeleteCusto()
	{
		Custo custo = insertCusto(4, TabelaEnum.CUSTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Custo custoResponse = getProdutoBAR().fetchCustoById(request);
		Assert.assertEquals(custoResponse, null);
		getProdutoBAR().insertCusto(custo);
		custoResponse = getProdutoBAR().fetchCustoById(request);
		Assert.assertEquals(custo.getId(), custoResponse.getId());
		getProdutoBAR().deleteCustoById(custo);
		custoResponse = getProdutoBAR().fetchCustoById(request);
		Assert.assertEquals(custoResponse, null);
	}

	@Test
	public void testFetchAllCustos()
	{
	Custo custo = new Custo();
		List<Custo> response = getProdutoBAR().fetchAllCustos(custo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCustos()
	{
		getProdutoBAR().deleteAllCustos();
	Custo custo = new Custo();
		List<Custo> response = getProdutoBAR().fetchAllCustos(new Custo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCusto()
	{
		Custo custo = insertCusto(1, TabelaEnum.CUSTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Custo custoResponse = getProdutoBAR().fetchCustoById(request);
		Assert.assertEquals(custoResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateCusto(custo);
		custoResponse = getProdutoBAR().fetchCustoById(request);
		Assert.assertEquals(custoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCustosByRequest() throws Exception
	{
		// check for valid and precount
		CustoInquiryRequest request = new CustoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Custo> response = getProdutoBAR().fetchCustosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchCustosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CustoInquiryRequest request2 = new CustoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Custo> response2 = getProdutoBAR().fetchCustosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllCustos();
		CustoInquiryRequest request3 = new CustoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Custo> response3 = getProdutoBAR().fetchCustosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CUSTOITENS ####======================================


@Test
	public void testDeleteCustoitens()
	{
		Custoitens custoitens = insertCustoitens(4, TabelaEnum.CUSTOITENS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Custoitens custoitensResponse = getProdutoBAR().fetchCustoitensById(request);
		Assert.assertEquals(custoitensResponse, null);
		getProdutoBAR().insertCustoitens(custoitens);
		custoitensResponse = getProdutoBAR().fetchCustoitensById(request);
		Assert.assertEquals(custoitens.getId(), custoitensResponse.getId());
		getProdutoBAR().deleteCustoitensById(custoitens);
		custoitensResponse = getProdutoBAR().fetchCustoitensById(request);
		Assert.assertEquals(custoitensResponse, null);
	}

	@Test
	public void testFetchAllCustoitenss()
	{
	Custoitens custoitens = new Custoitens();
		List<Custoitens> response = getProdutoBAR().fetchAllCustoitenss(custoitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCustoitenss()
	{
		getProdutoBAR().deleteAllCustoitenss();
	Custoitens custoitens = new Custoitens();
		List<Custoitens> response = getProdutoBAR().fetchAllCustoitenss(new Custoitens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCustoitens()
	{
		Custoitens custoitens = insertCustoitens(1, TabelaEnum.CUSTOITENS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Custoitens custoitensResponse = getProdutoBAR().fetchCustoitensById(request);
		Assert.assertEquals(custoitensResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateCustoitens(custoitens);
		custoitensResponse = getProdutoBAR().fetchCustoitensById(request);
		Assert.assertEquals(custoitensResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchCustoitenssByRequest() throws Exception
	{
		// check for valid and precount
		CustoitensInquiryRequest request = new CustoitensInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Custoitens> response = getProdutoBAR().fetchCustoitenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchCustoitenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CustoitensInquiryRequest request2 = new CustoitensInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Custoitens> response2 = getProdutoBAR().fetchCustoitenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllCustoitenss();
		CustoitensInquiryRequest request3 = new CustoitensInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Custoitens> response3 = getProdutoBAR().fetchCustoitenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### ESTOQUE ####======================================


@Test
	public void testDeleteEstoque()
	{
		Estoque estoque = insertEstoque(4, TabelaEnum.ESTOQUE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Estoque estoqueResponse = getProdutoBAR().fetchEstoqueById(request);
		Assert.assertEquals(estoqueResponse, null);
		getProdutoBAR().insertEstoque(estoque);
		estoqueResponse = getProdutoBAR().fetchEstoqueById(request);
		Assert.assertEquals(estoque.getId(), estoqueResponse.getId());
		getProdutoBAR().deleteEstoqueById(estoque);
		estoqueResponse = getProdutoBAR().fetchEstoqueById(request);
		Assert.assertEquals(estoqueResponse, null);
	}

	@Test
	public void testFetchAllEstoques()
	{
	Estoque estoque = new Estoque();
		List<Estoque> response = getProdutoBAR().fetchAllEstoques(estoque).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllEstoques()
	{
		getProdutoBAR().deleteAllEstoques();
	Estoque estoque = new Estoque();
		List<Estoque> response = getProdutoBAR().fetchAllEstoques(new Estoque()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateEstoque()
	{
		Estoque estoque = insertEstoque(1, TabelaEnum.ESTOQUE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Estoque estoqueResponse = getProdutoBAR().fetchEstoqueById(request);
		Assert.assertEquals(estoqueResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateEstoque(estoque);
		estoqueResponse = getProdutoBAR().fetchEstoqueById(request);
		Assert.assertEquals(estoqueResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchEstoquesByRequest() throws Exception
	{
		// check for valid and precount
		EstoqueInquiryRequest request = new EstoqueInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Estoque> response = getProdutoBAR().fetchEstoquesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchEstoquesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		EstoqueInquiryRequest request2 = new EstoqueInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Estoque> response2 = getProdutoBAR().fetchEstoquesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllEstoques();
		EstoqueInquiryRequest request3 = new EstoqueInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Estoque> response3 = getProdutoBAR().fetchEstoquesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### PORCAO ####======================================


@Test
	public void testDeletePorcao()
	{
		Porcao porcao = insertPorcao(4, TabelaEnum.PORCAO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Porcao porcaoResponse = getProdutoBAR().fetchPorcaoById(request);
		Assert.assertEquals(porcaoResponse, null);
		getProdutoBAR().insertPorcao(porcao);
		porcaoResponse = getProdutoBAR().fetchPorcaoById(request);
		Assert.assertEquals(porcao.getId(), porcaoResponse.getId());
		getProdutoBAR().deletePorcaoById(porcao);
		porcaoResponse = getProdutoBAR().fetchPorcaoById(request);
		Assert.assertEquals(porcaoResponse, null);
	}

	@Test
	public void testFetchAllPorcaos()
	{
	Porcao porcao = new Porcao();
		List<Porcao> response = getProdutoBAR().fetchAllPorcaos(porcao).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllPorcaos()
	{
		getProdutoBAR().deleteAllPorcaos();
	Porcao porcao = new Porcao();
		List<Porcao> response = getProdutoBAR().fetchAllPorcaos(new Porcao()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdatePorcao()
	{
		Porcao porcao = insertPorcao(1, TabelaEnum.PORCAO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Porcao porcaoResponse = getProdutoBAR().fetchPorcaoById(request);
		Assert.assertEquals(porcaoResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updatePorcao(porcao);
		porcaoResponse = getProdutoBAR().fetchPorcaoById(request);
		Assert.assertEquals(porcaoResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchPorcaosByRequest() throws Exception
	{
		// check for valid and precount
		PorcaoInquiryRequest request = new PorcaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Porcao> response = getProdutoBAR().fetchPorcaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchPorcaosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PorcaoInquiryRequest request2 = new PorcaoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Porcao> response2 = getProdutoBAR().fetchPorcaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllPorcaos();
		PorcaoInquiryRequest request3 = new PorcaoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Porcao> response3 = getProdutoBAR().fetchPorcaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### PORCAOITENS ####======================================


@Test
	public void testDeletePorcaoitens()
	{
		Porcaoitens porcaoitens = insertPorcaoitens(4, TabelaEnum.PORCAOITENS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Porcaoitens porcaoitensResponse = getProdutoBAR().fetchPorcaoitensById(request);
		Assert.assertEquals(porcaoitensResponse, null);
		getProdutoBAR().insertPorcaoitens(porcaoitens);
		porcaoitensResponse = getProdutoBAR().fetchPorcaoitensById(request);
		Assert.assertEquals(porcaoitens.getId(), porcaoitensResponse.getId());
		getProdutoBAR().deletePorcaoitensById(porcaoitens);
		porcaoitensResponse = getProdutoBAR().fetchPorcaoitensById(request);
		Assert.assertEquals(porcaoitensResponse, null);
	}

	@Test
	public void testFetchAllPorcaoitenss()
	{
	Porcaoitens porcaoitens = new Porcaoitens();
		List<Porcaoitens> response = getProdutoBAR().fetchAllPorcaoitenss(porcaoitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllPorcaoitenss()
	{
		getProdutoBAR().deleteAllPorcaoitenss();
	Porcaoitens porcaoitens = new Porcaoitens();
		List<Porcaoitens> response = getProdutoBAR().fetchAllPorcaoitenss(new Porcaoitens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdatePorcaoitens()
	{
		Porcaoitens porcaoitens = insertPorcaoitens(1, TabelaEnum.PORCAOITENS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Porcaoitens porcaoitensResponse = getProdutoBAR().fetchPorcaoitensById(request);
		Assert.assertEquals(porcaoitensResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updatePorcaoitens(porcaoitens);
		porcaoitensResponse = getProdutoBAR().fetchPorcaoitensById(request);
		Assert.assertEquals(porcaoitensResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchPorcaoitenssByRequest() throws Exception
	{
		// check for valid and precount
		PorcaoitensInquiryRequest request = new PorcaoitensInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Porcaoitens> response = getProdutoBAR().fetchPorcaoitenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchPorcaoitenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PorcaoitensInquiryRequest request2 = new PorcaoitensInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Porcaoitens> response2 = getProdutoBAR().fetchPorcaoitenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllPorcaoitenss();
		PorcaoitensInquiryRequest request3 = new PorcaoitensInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Porcaoitens> response3 = getProdutoBAR().fetchPorcaoitenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### RENTABILIDADE ####======================================


@Test
	public void testDeleteRentabilidade()
	{
		Rentabilidade rentabilidade = insertRentabilidade(4, TabelaEnum.RENTABILIDADE, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Rentabilidade rentabilidadeResponse = getProdutoBAR().fetchRentabilidadeById(request);
		Assert.assertEquals(rentabilidadeResponse, null);
		getProdutoBAR().insertRentabilidade(rentabilidade);
		rentabilidadeResponse = getProdutoBAR().fetchRentabilidadeById(request);
		Assert.assertEquals(rentabilidade.getId(), rentabilidadeResponse.getId());
		getProdutoBAR().deleteRentabilidadeById(rentabilidade);
		rentabilidadeResponse = getProdutoBAR().fetchRentabilidadeById(request);
		Assert.assertEquals(rentabilidadeResponse, null);
	}

	@Test
	public void testFetchAllRentabilidades()
	{
	Rentabilidade rentabilidade = new Rentabilidade();
		List<Rentabilidade> response = getProdutoBAR().fetchAllRentabilidades(rentabilidade).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllRentabilidades()
	{
		getProdutoBAR().deleteAllRentabilidades();
	Rentabilidade rentabilidade = new Rentabilidade();
		List<Rentabilidade> response = getProdutoBAR().fetchAllRentabilidades(new Rentabilidade()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateRentabilidade()
	{
		Rentabilidade rentabilidade = insertRentabilidade(1, TabelaEnum.RENTABILIDADE, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Rentabilidade rentabilidadeResponse = getProdutoBAR().fetchRentabilidadeById(request);
		Assert.assertEquals(rentabilidadeResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateRentabilidade(rentabilidade);
		rentabilidadeResponse = getProdutoBAR().fetchRentabilidadeById(request);
		Assert.assertEquals(rentabilidadeResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchRentabilidadesByRequest() throws Exception
	{
		// check for valid and precount
		RentabilidadeInquiryRequest request = new RentabilidadeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Rentabilidade> response = getProdutoBAR().fetchRentabilidadesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchRentabilidadesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		RentabilidadeInquiryRequest request2 = new RentabilidadeInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Rentabilidade> response2 = getProdutoBAR().fetchRentabilidadesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllRentabilidades();
		RentabilidadeInquiryRequest request3 = new RentabilidadeInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Rentabilidade> response3 = getProdutoBAR().fetchRentabilidadesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### RENTABILIDADEITENS ####======================================


@Test
	public void testDeleteRentabilidadeitens()
	{
		Rentabilidadeitens rentabilidadeitens = insertRentabilidadeitens(4, TabelaEnum.RENTABILIDADEITENS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Rentabilidadeitens rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeitensById(request);
		Assert.assertEquals(rentabilidadeitensResponse, null);
		getProdutoBAR().insertRentabilidadeitens(rentabilidadeitens);
		rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeitensById(request);
		Assert.assertEquals(rentabilidadeitens.getId(), rentabilidadeitensResponse.getId());
		getProdutoBAR().deleteRentabilidadeitensById(rentabilidadeitens);
		rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeitensById(request);
		Assert.assertEquals(rentabilidadeitensResponse, null);
	}

	@Test
	public void testFetchAllRentabilidadeitenss()
	{
	Rentabilidadeitens rentabilidadeitens = new Rentabilidadeitens();
		List<Rentabilidadeitens> response = getProdutoBAR().fetchAllRentabilidadeitenss(rentabilidadeitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllRentabilidadeitenss()
	{
		getProdutoBAR().deleteAllRentabilidadeitenss();
	Rentabilidadeitens rentabilidadeitens = new Rentabilidadeitens();
		List<Rentabilidadeitens> response = getProdutoBAR().fetchAllRentabilidadeitenss(new Rentabilidadeitens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateRentabilidadeitens()
	{
		Rentabilidadeitens rentabilidadeitens = insertRentabilidadeitens(1, TabelaEnum.RENTABILIDADEITENS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Rentabilidadeitens rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeitensById(request);
		Assert.assertEquals(rentabilidadeitensResponse.getDescription(), "NATIVE INSERT");
		getProdutoBAR().updateRentabilidadeitens(rentabilidadeitens);
		rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeitensById(request);
		Assert.assertEquals(rentabilidadeitensResponse.getDescription(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchRentabilidadeitenssByRequest() throws Exception
	{
		// check for valid and precount
		RentabilidadeitensInquiryRequest request = new RentabilidadeitensInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Rentabilidadeitens> response = getProdutoBAR().fetchRentabilidadeitenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchRentabilidadeitenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		RentabilidadeitensInquiryRequest request2 = new RentabilidadeitensInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Rentabilidadeitens> response2 = getProdutoBAR().fetchRentabilidadeitenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllRentabilidadeitenss();
		RentabilidadeitensInquiryRequest request3 = new RentabilidadeitensInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Rentabilidadeitens> response3 = getProdutoBAR().fetchRentabilidadeitenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertProdutoparent.sql", false);
		executeSqlScript("conf/insertProduto.sql", false);
		executeSqlScript("conf/insertCfop.sql", false);
		executeSqlScript("conf/insertMarca.sql", false);
		executeSqlScript("conf/insertMarcaproduto.sql", false);
		executeSqlScript("conf/insertGrupo.sql", false);
		executeSqlScript("conf/insertSubgrupo.sql", false);
		executeSqlScript("conf/insertUnimed.sql", false);
		executeSqlScript("conf/insertTributacao.sql", false);
		executeSqlScript("conf/insertCusto.sql", false);
		executeSqlScript("conf/insertCustoitens.sql", false);
		executeSqlScript("conf/insertEstoque.sql", false);
		executeSqlScript("conf/insertPorcao.sql", false);
		executeSqlScript("conf/insertPorcaoitens.sql", false);
		executeSqlScript("conf/insertRentabilidade.sql", false);
		executeSqlScript("conf/insertRentabilidadeitens.sql", false);
	}


	public ProdutoParent insertProdutoParent(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ProdutoParent produtoparent = new ProdutoParent();
			Date a = new Date();
			produtoparent.setId(100);
			produtoparent.setParentId(100);
			produtoparent.setEmprId(100);
			produtoparent.setTributacao(100);
			produtoparent.setEstoqueList(new ArrayList<undefined>());
			produtoparent.getundefined().add(insertundefined(id,TabelaEnum.PRODUTOPARENT,action));
			produtoparent.setPrecoList(new ArrayList<undefined>());
			produtoparent.getundefined().add(insertundefined(id,TabelaEnum.PRODUTOPARENT,action));
			produtoparent.setCustoList(new ArrayList<undefined>());
			produtoparent.getundefined().add(insertundefined(id,TabelaEnum.PRODUTOPARENT,action));
			produtoparent.setPorcaoList(new ArrayList<undefined>());
			produtoparent.getundefined().add(insertundefined(id,TabelaEnum.PRODUTOPARENT,action));
			produtoparent.setRentabilidadeList(new ArrayList<undefined>());
			produtoparent.getundefined().add(insertundefined(id,TabelaEnum.PRODUTOPARENT,action));
			produtoparent.setCfopList(new ArrayList<undefined>());
			produtoparent.getundefined().add(insertundefined(id,TabelaEnum.PRODUTOPARENT,action));
			produtoparent.setDataValidade(a.getTime());
			produtoparent.setParentId(id);
			produtoparent.setEmprId(1);
			produtoparent.setModifyDateUTC(a.getTime());
			produtoparent.setCreateDateUTC(a.getTime());
			produtoparent.setCreateUser("system");
			produtoparent.setModifyUser("system");
			produtoparent.setProcessId(1);
			produtoparent.setModelAction(action);

			return produtoparent;
		}


	public Produto insertProduto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Produto produto = new Produto();
			Date a = new Date();
			produto.setId(100);
			produto.setCodigo("NATIVE INSERT UPDATE");
			produto.setCdBarras("NATIVE INSERT UPDATE");
			produto.setProduto("NATIVE INSERT UPDATE");
			produto.setDataCreate(a.getTime());
			produto.setAplicacao("NATIVE INSERT UPDATE");
			produto.setLocalizacao("NATIVE INSERT UPDATE");
			produto.setComissao("NATIVE INSERT UPDATE");
			produto.setFracao("NATIVE INSERT UPDATE");
			produto.setClassificacao(100);
			produto.setUniMed(100);
			produto.setGrupo(100);
			produto.setSubGrupo(100);
			produto.setMarca(100);
			produto.setPorcao(new Double(1.99));
			produto.setPesoBruto(new Double(1.99));
			produto.setPesoLiquido(new Double(1.99));
			produto.setModoUso("NATIVE INSERT UPDATE");
			produto.setParentId(id);
			produto.setEmprId(1);
			produto.setModifyDateUTC(a.getTime());
			produto.setCreateDateUTC(a.getTime());
			produto.setCreateUser("system");
			produto.setModifyUser("system");
			produto.setProcessId(1);
			produto.setModelAction(action);

			return produto;
		}


	public Cfop insertCfop(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Cfop cfop = new Cfop();
			Date a = new Date();
			cfop.setId(100);
			cfop.setCfop("NATIVE INSERT UPDATE");
			cfop.setNatureza("NATIVE INSERT UPDATE");
			cfop.setSimplificado("NATIVE INSERT UPDATE");
			cfop.setCfopTypeEnum(100);
			cfop.setIcms(new Double(1.99));
			cfop.setIcmsReduzido(new Double(1.99));
			cfop.setMargemAgregadaST(new Double(1.99));
			cfop.setCstPrincipal(new Double(1.99));
			cfop.setClassFiscal(new Double(1.99));
			cfop.setObservacao("NATIVE INSERT UPDATE");
			cfop.setParentId(id);
			cfop.setEmprId(1);
			cfop.setModifyDateUTC(a.getTime());
			cfop.setCreateDateUTC(a.getTime());
			cfop.setCreateUser("system");
			cfop.setModifyUser("system");
			cfop.setProcessId(1);
			cfop.setModelAction(action);

			return cfop;
		}


	public Marca insertMarca(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Marca marca = new Marca();
			Date a = new Date();
			marca.setId(100);
			marca.setUnimed("NATIVE INSERT UPDATE");
			marca.setSigla("NATIVE INSERT UPDATE");
			marca.setParentId(id);
			marca.setEmprId(1);
			marca.setModifyDateUTC(a.getTime());
			marca.setCreateDateUTC(a.getTime());
			marca.setCreateUser("system");
			marca.setModifyUser("system");
			marca.setProcessId(1);
			marca.setModelAction(action);

			return marca;
		}


	public MarcaProduto insertMarcaProduto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			MarcaProduto marcaproduto = new MarcaProduto();
			Date a = new Date();
			marcaproduto.setId(100);
			marcaproduto.setParentId(100);
			marcaproduto.setMarcaId(100);
			marcaproduto.setParentId(id);
			marcaproduto.setEmprId(1);
			marcaproduto.setModifyDateUTC(a.getTime());
			marcaproduto.setCreateDateUTC(a.getTime());
			marcaproduto.setCreateUser("system");
			marcaproduto.setModifyUser("system");
			marcaproduto.setProcessId(1);
			marcaproduto.setModelAction(action);

			return marcaproduto;
		}


	public Grupo insertGrupo(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Grupo grupo = new Grupo();
			Date a = new Date();
			grupo.setId(100);
			grupo.setGrupo("NATIVE INSERT UPDATE");
			grupo.setDescricao("NATIVE INSERT UPDATE");
			grupo.setSubGrupo(100);
			grupo.setParentId(id);
			grupo.setEmprId(1);
			grupo.setModifyDateUTC(a.getTime());
			grupo.setCreateDateUTC(a.getTime());
			grupo.setCreateUser("system");
			grupo.setModifyUser("system");
			grupo.setProcessId(1);
			grupo.setModelAction(action);

			return grupo;
		}


	public SubGrupo insertSubGrupo(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			SubGrupo subgrupo = new SubGrupo();
			Date a = new Date();
			subgrupo.setId(100);
			subgrupo.setSubGrupo("NATIVE INSERT UPDATE");
			subgrupo.setDescricao("NATIVE INSERT UPDATE");
			subgrupo.setParentId(id);
			subgrupo.setEmprId(1);
			subgrupo.setModifyDateUTC(a.getTime());
			subgrupo.setCreateDateUTC(a.getTime());
			subgrupo.setCreateUser("system");
			subgrupo.setModifyUser("system");
			subgrupo.setProcessId(1);
			subgrupo.setModelAction(action);

			return subgrupo;
		}


	public UniMed insertUniMed(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			UniMed unimed = new UniMed();
			Date a = new Date();
			unimed.setId(100);
			unimed.setUnimed("NATIVE INSERT UPDATE");
			unimed.setSigla("NATIVE INSERT UPDATE");
			unimed.setParentId(id);
			unimed.setEmprId(1);
			unimed.setModifyDateUTC(a.getTime());
			unimed.setCreateDateUTC(a.getTime());
			unimed.setCreateUser("system");
			unimed.setModifyUser("system");
			unimed.setProcessId(1);
			unimed.setModelAction(action);

			return unimed;
		}


	public Tributacao insertTributacao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Tributacao tributacao = new Tributacao();
			Date a = new Date();
			tributacao.setId(100);
			tributacao.setParentId(100);
			tributacao.setCstId(100);
			tributacao.setIcms(new Double(1.99));
			tributacao.setSt(new Double(1.99));
			tributacao.setMva(new Double(1.99));
			tributacao.setCsosnId(100);
			tributacao.setIpi(new Double(1.99));
			tributacao.setIat(new Double(1.99));
			tributacao.setIppt(new Double(1.99));
			tributacao.setIncidencia(100);
			tributacao.setParentId(id);
			tributacao.setEmprId(1);
			tributacao.setModifyDateUTC(a.getTime());
			tributacao.setCreateDateUTC(a.getTime());
			tributacao.setCreateUser("system");
			tributacao.setModifyUser("system");
			tributacao.setProcessId(1);
			tributacao.setModelAction(action);

			return tributacao;
		}


	public Custo insertCusto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Custo custo = new Custo();
			Date a = new Date();
			custo.setId(100);
			custo.setValor(new Double(1.99));
			custo.setCusto(new ArrayList<undefined>());
			custo.getundefined().add(insertundefined(id,TabelaEnum.CUSTO,action));
			custo.setParentId(100);
			custo.setParentId(id);
			custo.setEmprId(1);
			custo.setModifyDateUTC(a.getTime());
			custo.setCreateDateUTC(a.getTime());
			custo.setCreateUser("system");
			custo.setModifyUser("system");
			custo.setProcessId(1);
			custo.setModelAction(action);

			return custo;
		}


	public CustoItens insertCustoItens(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			CustoItens custoitens = new CustoItens();
			Date a = new Date();
			custoitens.setId(100);
			custoitens.setParentId(100);
			custoitens.setCusto(100);
			custoitens.setCustoDesp(100);
			custoitens.setParentId(id);
			custoitens.setEmprId(1);
			custoitens.setModifyDateUTC(a.getTime());
			custoitens.setCreateDateUTC(a.getTime());
			custoitens.setCreateUser("system");
			custoitens.setModifyUser("system");
			custoitens.setProcessId(1);
			custoitens.setModelAction(action);

			return custoitens;
		}


	public Estoque insertEstoque(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Estoque estoque = new Estoque();
			Date a = new Date();
			estoque.setId(100);
			estoque.setParentId(100);
			estoque.setEstoqueTypeEnum(100);
			estoque.setUltimoMov(a.getTime());
			estoque.setQuant(new Double(1.99));
			estoque.setParentId(id);
			estoque.setEmprId(1);
			estoque.setModifyDateUTC(a.getTime());
			estoque.setCreateDateUTC(a.getTime());
			estoque.setCreateUser("system");
			estoque.setModifyUser("system");
			estoque.setProcessId(1);
			estoque.setModelAction(action);

			return estoque;
		}


	public Porcao insertPorcao(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Porcao porcao = new Porcao();
			Date a = new Date();
			porcao.setId(100);
			porcao.setParentId(100);
			porcao.setValor(new Double(1.99));
			porcao.setPorcaoItens(new ArrayList<undefined>());
			porcao.getundefined().add(insertundefined(id,TabelaEnum.PORCAO,action));
			porcao.setParentId(id);
			porcao.setEmprId(1);
			porcao.setModifyDateUTC(a.getTime());
			porcao.setCreateDateUTC(a.getTime());
			porcao.setCreateUser("system");
			porcao.setModifyUser("system");
			porcao.setProcessId(1);
			porcao.setModelAction(action);

			return porcao;
		}


	public PorcaoItens insertPorcaoItens(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			PorcaoItens porcaoitens = new PorcaoItens();
			Date a = new Date();
			porcaoitens.setId(100);
			porcaoitens.setParentId(100);
			porcaoitens.setPorcao(new Double(1.99));
			porcaoitens.setVd(new Double(1.99));
			porcaoitens.setUnimed(100);
			porcaoitens.setNome("NATIVE INSERT UPDATE");
			porcaoitens.setParentId(id);
			porcaoitens.setEmprId(1);
			porcaoitens.setModifyDateUTC(a.getTime());
			porcaoitens.setCreateDateUTC(a.getTime());
			porcaoitens.setCreateUser("system");
			porcaoitens.setModifyUser("system");
			porcaoitens.setProcessId(1);
			porcaoitens.setModelAction(action);

			return porcaoitens;
		}


	public Rentabilidade insertRentabilidade(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Rentabilidade rentabilidade = new Rentabilidade();
			Date a = new Date();
			rentabilidade.setId(100);
			rentabilidade.setParentId(100);
			rentabilidade.setRentabilidadeList(new ArrayList<undefined>());
			rentabilidade.getundefined().add(insertundefined(id,TabelaEnum.RENTABILIDADE,action));
			rentabilidade.setParentId(id);
			rentabilidade.setEmprId(1);
			rentabilidade.setModifyDateUTC(a.getTime());
			rentabilidade.setCreateDateUTC(a.getTime());
			rentabilidade.setCreateUser("system");
			rentabilidade.setModifyUser("system");
			rentabilidade.setProcessId(1);
			rentabilidade.setModelAction(action);

			return rentabilidade;
		}


	public RentabilidadeItens insertRentabilidadeItens(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			RentabilidadeItens rentabilidadeitens = new RentabilidadeItens();
			Date a = new Date();
			rentabilidadeitens.setId(100);
			rentabilidadeitens.setParentId(100);
			rentabilidadeitens.setProduto(100);
			rentabilidadeitens.setValor(new Double(1.99));
			rentabilidadeitens.setRentabilidadeTypeEnum(100);
			rentabilidadeitens.setParentId(id);
			rentabilidadeitens.setEmprId(1);
			rentabilidadeitens.setModifyDateUTC(a.getTime());
			rentabilidadeitens.setCreateDateUTC(a.getTime());
			rentabilidadeitens.setCreateUser("system");
			rentabilidadeitens.setModifyUser("system");
			rentabilidadeitens.setProcessId(1);
			rentabilidadeitens.setModelAction(action);

			return rentabilidadeitens;
		}


}
