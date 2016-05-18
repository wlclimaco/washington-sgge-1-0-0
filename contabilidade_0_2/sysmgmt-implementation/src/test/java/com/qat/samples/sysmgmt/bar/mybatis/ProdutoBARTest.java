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
import com.qat.samples.sysmgmt.cfop.model.CfopParentId;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.Tributacao;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.CustoItens;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.MarcaProduto;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.PorcaoItens;
import com.qat.samples.sysmgmt.produto.model.Preco;
import com.qat.samples.sysmgmt.produto.model.PrecoTypeEnum;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.ProdutoParent;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeItens;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoParentInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
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


//===================================### ProdutoParent ####======================================


@Test
	public void testDeleteProdutoParent()
	{
		ProdutoParent ProdutoParent = insertProdutoParent(4, TabelaEnum.PRODUTOPARENT, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		ProdutoParent ProdutoParentResponse = getProdutoBAR().fetchProdutoParentById(request);
		Assert.assertEquals(ProdutoParentResponse, null);
		getProdutoBAR().insertProdutoParent(ProdutoParent);
		ProdutoParentResponse = getProdutoBAR().fetchProdutoParentById(request);
		Assert.assertEquals(ProdutoParent.getId(), ProdutoParentResponse.getId());
		getProdutoBAR().deleteProdutoParentById(ProdutoParent);
		ProdutoParentResponse = getProdutoBAR().fetchProdutoParentById(request);
		Assert.assertEquals(ProdutoParentResponse, null);
	}

	@Test
	public void testFetchAllProdutoParents()
	{
	ProdutoParent ProdutoParent = new ProdutoParent();
		List<ProdutoParent> response = getProdutoBAR().fetchAllProdutoParents(ProdutoParent).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllProdutoParents()
	{
		getProdutoBAR().deleteAllProdutoParents();
	ProdutoParent ProdutoParent = new ProdutoParent();
		List<ProdutoParent> response = getProdutoBAR().fetchAllProdutoParents(new ProdutoParent()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateProdutoParent()
	{
		ProdutoParent ProdutoParent = insertProdutoParent(1, TabelaEnum.PRODUTOPARENT, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		ProdutoParent ProdutoParentResponse = getProdutoBAR().fetchProdutoParentById(request);
		Assert.assertEquals(ProdutoParentResponse.getLocalizacao(), "localizacao_11");
		getProdutoBAR().updateProdutoParent(ProdutoParent);
		ProdutoParentResponse = getProdutoBAR().fetchProdutoParentById(request);
		Assert.assertEquals(ProdutoParentResponse.getLocalizacao(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchProdutoParentsByRequest() throws Exception
	{
		// check for valid and precount
		ProdutoParentInquiryRequest request = new ProdutoParentInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<ProdutoParent> response = getProdutoBAR().fetchProdutoParentsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchProdutoParentsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ProdutoParentInquiryRequest request2 = new ProdutoParentInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<ProdutoParent> response2 = getProdutoBAR().fetchProdutoParentsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllProdutoParents();
		ProdutoParentInquiryRequest request3 = new ProdutoParentInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<ProdutoParent> response3 = getProdutoBAR().fetchProdutoParentsByRequest(request3);
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
		Assert.assertEquals(produtoResponse.getProduto(), "produto_3");
		getProdutoBAR().updateProduto(produto);
		produtoResponse = getProdutoBAR().fetchProdutoById(request);
		Assert.assertEquals(produtoResponse.getProduto(), "NATIVE INSERT UPDATE");
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
		Assert.assertEquals(cfopResponse.getCfop(), "cfop_1");
		getProdutoBAR().updateCfop(cfop);
		cfopResponse = getProdutoBAR().fetchCfopById(request);
		Assert.assertEquals(cfopResponse.getCfop(), "NATIVE INSERT UPDATE");
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
		Assert.assertEquals(marcaResponse.getMarca(), "marca_1");
		getProdutoBAR().updateMarca(marca);
		marcaResponse = getProdutoBAR().fetchMarcaById(request);
		Assert.assertEquals(marcaResponse.getMarca(), "NATIVE INSERT UPDATE");
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
	public void testDeleteMarcaProd()
	{
		MarcaProduto marcaproduto = insertMarcaProd(4, TabelaEnum.MARCAPRODUTO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		MarcaProduto marcaprodutoResponse = getProdutoBAR().fetchMarcaProdutoById(request);
		Assert.assertEquals(marcaprodutoResponse, null);
		getProdutoBAR().insertMarcaProduto(marcaproduto);
		marcaprodutoResponse = getProdutoBAR().fetchMarcaProdutoById(request);
		Assert.assertEquals(marcaproduto.getId(), marcaprodutoResponse.getId());
		getProdutoBAR().deleteMarcaProdutoById(marcaproduto);
		marcaprodutoResponse = getProdutoBAR().fetchMarcaProdutoById(request);
		Assert.assertEquals(marcaprodutoResponse, null);
	}

	@Test
	public void testFetchAllMarcaProds()
	{
	MarcaProduto marcaproduto = new MarcaProduto();
		List<MarcaProduto> response = getProdutoBAR().fetchAllMarcaProdutos(marcaproduto).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllMarcaProds()
	{
		getProdutoBAR().deleteAllMarcaProdutos();
	MarcaProduto marcaproduto = new MarcaProduto();
		List<MarcaProduto> response = getProdutoBAR().fetchAllMarcaProdutos(new MarcaProduto()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateMarcaProd()
	{
		MarcaProduto marcaproduto = insertMarcaProd(1, TabelaEnum.MARCAPRODUTO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		MarcaProduto marcaprodutoResponse = getProdutoBAR().fetchMarcaProdutoById(request);
		Assert.assertEquals(marcaprodutoResponse.getModifyUser(),"rod");
		getProdutoBAR().updateMarcaProduto(marcaproduto);
		marcaprodutoResponse = getProdutoBAR().fetchMarcaProdutoById(request);
		Assert.assertEquals(marcaprodutoResponse.getModifyUser(), "system");
	}

	@Test
	public void testFetchMarcaProdsByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<MarcaProduto> response = getProdutoBAR().fetchMarcaProdutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchMarcaProdutosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<MarcaProduto> response2 = getProdutoBAR().fetchMarcaProdutosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllMarcaProdutos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<MarcaProduto> response3 = getProdutoBAR().fetchMarcaProdutosByRequest(request3);
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
		Assert.assertEquals(grupoResponse.getGrupo(), "grupo_1");
		getProdutoBAR().updateGrupo(grupo);
		grupoResponse = getProdutoBAR().fetchGrupoById(request);
		Assert.assertEquals(grupoResponse.getGrupo(), "NATIVE INSERT UPDATE");
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
	public void testDeleteSubGrupo()
	{
		SubGrupo subgrupo = insertSubGrupo(4, TabelaEnum.SUBGRUPO, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		SubGrupo subgrupoResponse = getProdutoBAR().fetchSubGrupoById(request);
		Assert.assertEquals(subgrupoResponse, null);
		getProdutoBAR().insertSubGrupo(subgrupo);
		subgrupoResponse = getProdutoBAR().fetchSubGrupoById(request);
		Assert.assertEquals(subgrupo.getId(), subgrupoResponse.getId());
		getProdutoBAR().deleteSubGrupoById(subgrupo);
		subgrupoResponse = getProdutoBAR().fetchSubGrupoById(request);
		Assert.assertEquals(subgrupoResponse, null);
	}

	@Test
	public void testFetchAllSubGrupos()
	{
	SubGrupo subgrupo = new SubGrupo();
		List<SubGrupo> response = getProdutoBAR().fetchAllSubGrupos(subgrupo).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllSubGrupos()
	{
		getProdutoBAR().deleteAllSubGrupos();
	SubGrupo subgrupo = new SubGrupo();
		List<SubGrupo> response = getProdutoBAR().fetchAllSubGrupos(new SubGrupo()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateSubGrupo()
	{
		SubGrupo subgrupo = insertSubGrupo(1, TabelaEnum.SUBGRUPO, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		SubGrupo subgrupoResponse = getProdutoBAR().fetchSubGrupoById(request);
		Assert.assertEquals(subgrupoResponse.getSubGrupo(), "subGrupo_1");
		getProdutoBAR().updateSubGrupo(subgrupo);
		subgrupoResponse = getProdutoBAR().fetchSubGrupoById(request);
		Assert.assertEquals(subgrupoResponse.getSubGrupo(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchSubGruposByRequest() throws Exception
	{
		// check for valid and precount
		SubGrupoInquiryRequest request = new SubGrupoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<SubGrupo> response = getProdutoBAR().fetchSubGruposByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchSubGruposByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		SubGrupoInquiryRequest request2 = new SubGrupoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<SubGrupo> response2 = getProdutoBAR().fetchSubGruposByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllSubGrupos();
		SubGrupoInquiryRequest request3 = new SubGrupoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<SubGrupo> response3 = getProdutoBAR().fetchSubGruposByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### UNIMED ####======================================


@Test
	public void testDeleteUniMed()
	{
		UniMed unimed = insertUniMed(4, TabelaEnum.UNIMED, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		UniMed unimedResponse = getProdutoBAR().fetchUniMedById(request);
		Assert.assertEquals(unimedResponse, null);
		getProdutoBAR().insertUniMed(unimed);
		unimedResponse = getProdutoBAR().fetchUniMedById(request);
		Assert.assertEquals(unimed.getId(), unimedResponse.getId());
		getProdutoBAR().deleteUniMedById(unimed);
		unimedResponse = getProdutoBAR().fetchUniMedById(request);
		Assert.assertEquals(unimedResponse, null);
	}

	@Test
	public void testFetchAllUniMeds()
	{
	UniMed unimed = new UniMed();
		List<UniMed> response = getProdutoBAR().fetchAllUniMeds(unimed).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllUniMeds()
	{
		getProdutoBAR().deleteAllUniMeds();
	UniMed unimed = new UniMed();
		List<UniMed> response = getProdutoBAR().fetchAllUniMeds(new UniMed()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateUniMed()
	{
		UniMed unimed = insertUniMed(1, TabelaEnum.UNIMED, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		UniMed unimedResponse = getProdutoBAR().fetchUniMedById(request);
		Assert.assertEquals(unimedResponse.getUnimed(), "unimed_1");
		getProdutoBAR().updateUniMed(unimed);
		unimedResponse = getProdutoBAR().fetchUniMedById(request);
		Assert.assertEquals(unimedResponse.getUnimed(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchUniMedsByRequest() throws Exception
	{
		// check for valid and precount
		UniMedInquiryRequest request = new UniMedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<UniMed> response = getProdutoBAR().fetchUniMedsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchUniMedsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		UniMedInquiryRequest request2 = new UniMedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<UniMed> response2 = getProdutoBAR().fetchUniMedsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllUniMeds();
		UniMedInquiryRequest request3 = new UniMedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<UniMed> response3 = getProdutoBAR().fetchUniMedsByRequest(request3);
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
		Assert.assertEquals(tributacaoResponse.getCreateUser(), "system");
		getProdutoBAR().updateTributacao(tributacao);
		tributacaoResponse = getProdutoBAR().fetchTributacaoById(request);
		Assert.assertEquals(tributacaoResponse.getCreateUser(), "system");
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
		Assert.assertEquals(custoResponse.getCreateUser(), "system");
		getProdutoBAR().updateCusto(custo);
		custoResponse = getProdutoBAR().fetchCustoById(request);
		Assert.assertEquals(custoResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchCustosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
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
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Custo> response2 = getProdutoBAR().fetchCustosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllCustos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Custo> response3 = getProdutoBAR().fetchCustosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CUSTOITENS ####======================================


@Test
	public void testDeleteCustoItens()
	{
		CustoItens custoitens = insertCustoItens(4, TabelaEnum.CUSTOITENS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		CustoItens custoitensResponse = getProdutoBAR().fetchCustoItensById(request);
		Assert.assertEquals(custoitensResponse, null);
		getProdutoBAR().insertCustoItens(custoitens);
		custoitensResponse = getProdutoBAR().fetchCustoItensById(request);
		Assert.assertEquals(custoitens.getId(), custoitensResponse.getId());
		getProdutoBAR().deleteCustoItensById(custoitens);
		custoitensResponse = getProdutoBAR().fetchCustoItensById(request);
		Assert.assertEquals(custoitensResponse, null);
	}

	@Test
	public void testFetchAllCustoItenss()
	{
	CustoItens custoitens = new CustoItens();
		List<CustoItens> response = getProdutoBAR().fetchAllCustoItenss(custoitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllCustoItenss()
	{
		getProdutoBAR().deleteAllCustoItenss();
	CustoItens custoitens = new CustoItens();
		List<CustoItens> response = getProdutoBAR().fetchAllCustoItenss(new CustoItens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCustoItens()
	{
		CustoItens custoitens = insertCustoItens(1, TabelaEnum.CUSTOITENS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		CustoItens custoitensResponse = getProdutoBAR().fetchCustoItensById(request);
		Assert.assertEquals(custoitensResponse.getCusto(), "1");
		getProdutoBAR().updateCustoItens(custoitens);
		custoitensResponse = getProdutoBAR().fetchCustoItensById(request);
		Assert.assertEquals(custoitensResponse.getCusto(), "Custo");
	}

	@Test
	public void testFetchCustoItenssByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<CustoItens> response = getProdutoBAR().fetchCustoItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchCustoItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<CustoItens> response2 = getProdutoBAR().fetchCustoItenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllCustoItenss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<CustoItens> response3 = getProdutoBAR().fetchCustoItenssByRequest(request3);
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
		Assert.assertEquals(estoqueResponse.getModifyUser(), "rod");
		getProdutoBAR().updateEstoque(estoque);
		estoqueResponse = getProdutoBAR().fetchEstoqueById(request);
		Assert.assertEquals(estoqueResponse.getModifyUser(), "system");
	}

	@Test
	public void testFetchEstoquesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
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
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Estoque> response2 = getProdutoBAR().fetchEstoquesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllEstoques();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
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
		Assert.assertEquals(porcaoResponse.getCreateUser(), "system");
		getProdutoBAR().updatePorcao(porcao);
		porcaoResponse = getProdutoBAR().fetchPorcaoById(request);
		Assert.assertEquals(porcaoResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchPorcaosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
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
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Porcao> response2 = getProdutoBAR().fetchPorcaosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllPorcaos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Porcao> response3 = getProdutoBAR().fetchPorcaosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### PORCAOITENS ####======================================


@Test
	public void testDeletePorcaoItens()
	{
		PorcaoItens porcaoitens = insertPorcaoItens(4, TabelaEnum.PORCAOITENS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		PorcaoItens porcaoitensResponse = getProdutoBAR().fetchPorcaoItensById(request);
		Assert.assertEquals(porcaoitensResponse, null);
		getProdutoBAR().insertPorcaoItens(porcaoitens);
		porcaoitensResponse = getProdutoBAR().fetchPorcaoItensById(request);
		Assert.assertEquals(porcaoitens.getId(), porcaoitensResponse.getId());
		getProdutoBAR().deletePorcaoItensById(porcaoitens);
		porcaoitensResponse = getProdutoBAR().fetchPorcaoItensById(request);
		Assert.assertEquals(porcaoitensResponse, null);
	}

	@Test
	public void testFetchAllPorcaoItenss()
	{
	PorcaoItens porcaoitens = new PorcaoItens();
		List<PorcaoItens> response = getProdutoBAR().fetchAllPorcaoItenss(porcaoitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllPorcaoItenss()
	{
		getProdutoBAR().deleteAllPorcaoItenss();
	PorcaoItens porcaoitens = new PorcaoItens();
		List<PorcaoItens> response = getProdutoBAR().fetchAllPorcaoItenss(new PorcaoItens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdatePorcaoItens()
	{
		PorcaoItens porcaoitens = insertPorcaoItens(1, TabelaEnum.PORCAOITENS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		PorcaoItens porcaoitensResponse = getProdutoBAR().fetchPorcaoItensById(request);
		Assert.assertEquals(porcaoitensResponse.getNome(), "nome_5");
		getProdutoBAR().updatePorcaoItens(porcaoitens);
		porcaoitensResponse = getProdutoBAR().fetchPorcaoItensById(request);
		Assert.assertEquals(porcaoitensResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchPorcaoItenssByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<PorcaoItens> response = getProdutoBAR().fetchPorcaoItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchPorcaoItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<PorcaoItens> response2 = getProdutoBAR().fetchPorcaoItenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllPorcaoItenss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<PorcaoItens> response3 = getProdutoBAR().fetchPorcaoItenssByRequest(request3);
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
		Assert.assertEquals(rentabilidadeResponse.getCreateUser(), "system");
		getProdutoBAR().updateRentabilidade(rentabilidade);
		rentabilidadeResponse = getProdutoBAR().fetchRentabilidadeById(request);
		Assert.assertEquals(rentabilidadeResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchRentabilidadesByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
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
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Rentabilidade> response2 = getProdutoBAR().fetchRentabilidadesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllRentabilidades();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Rentabilidade> response3 = getProdutoBAR().fetchRentabilidadesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### RENTABILIDADEITENS ####======================================


@Test
	public void testDeleteRentabilidadeItens()
	{
		RentabilidadeItens rentabilidadeitens = insertRentabilidadeItens(4, TabelaEnum.RENTABILIDADEITENS, PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		RentabilidadeItens rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeItensById(request);
		Assert.assertEquals(rentabilidadeitensResponse, null);
		getProdutoBAR().insertRentabilidadeItens(rentabilidadeitens);
		rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeItensById(request);
		Assert.assertEquals(rentabilidadeitens.getId(), rentabilidadeitensResponse.getId());
		getProdutoBAR().deleteRentabilidadeItensById(rentabilidadeitens);
		rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeItensById(request);
		Assert.assertEquals(rentabilidadeitensResponse, null);
	}

	@Test
	public void testFetchAllRentabilidadeItenss()
	{
	RentabilidadeItens rentabilidadeitens = new RentabilidadeItens();
		List<RentabilidadeItens> response = getProdutoBAR().fetchAllRentabilidadeItenss(rentabilidadeitens).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllRentabilidadeItenss()
	{
		getProdutoBAR().deleteAllRentabilidadeItenss();
	RentabilidadeItens rentabilidadeitens = new RentabilidadeItens();
		List<RentabilidadeItens> response = getProdutoBAR().fetchAllRentabilidadeItenss(new RentabilidadeItens()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateRentabilidadeItens()
	{
		RentabilidadeItens rentabilidadeitens = insertRentabilidadeItens(1, TabelaEnum.RENTABILIDADEITENS, PersistenceActionEnum.UPDATE);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		RentabilidadeItens rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeItensById(request);
		Assert.assertEquals(rentabilidadeitensResponse.getCreateUser(), "system");
		getProdutoBAR().updateRentabilidadeItens(rentabilidadeitens);
		rentabilidadeitensResponse = getProdutoBAR().fetchRentabilidadeItensById(request);
		Assert.assertEquals(rentabilidadeitensResponse.getCreateUser(), "system");
	}

	@Test
	public void testFetchRentabilidadeItenssByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<RentabilidadeItens> response = getProdutoBAR().fetchRentabilidadeItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getProdutoBAR().fetchRentabilidadeItenssByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<RentabilidadeItens> response2 = getProdutoBAR().fetchRentabilidadeItenssByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoBAR().deleteAllRentabilidadeItenss();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<RentabilidadeItens> response3 = getProdutoBAR().fetchRentabilidadeItenssByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertProdutoParent.sql", false);
		executeSqlScript("conf/insertProduto.sql", false);
		executeSqlScript("conf/insertCfop.sql", false);
		executeSqlScript("conf/insertMarca.sql", false);
		executeSqlScript("conf/insertMarcaProduto.sql", false);
		executeSqlScript("conf/insertGrupo.sql", false);
		executeSqlScript("conf/insertSubGrupo.sql", false);
		executeSqlScript("conf/insertUniMed.sql", false);
		executeSqlScript("conf/insertTributacao.sql", false);
		executeSqlScript("conf/insertCusto.sql", false);
		executeSqlScript("conf/insertCustoItens.sql", false);
		executeSqlScript("conf/insertEstoque.sql", false);
		executeSqlScript("conf/insertPorcao.sql", false);
		executeSqlScript("conf/insertPorcaoItens.sql", false);
		executeSqlScript("conf/insertRentabilidade.sql", false);
		executeSqlScript("conf/insertRentabilidadeItens.sql", false);
	}


	public ProdutoParent insertProdutoParent(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ProdutoParent ProdutoParent = new ProdutoParent();
			Date a = new Date();
			ProdutoParent.setId(id);
			ProdutoParent.setParentId(id);
			ProdutoParent.setEmprId(100);
			ProdutoParent.setLocalizacao("NATIVE INSERT UPDATE");
			ProdutoParent.setTributacao(insertTributacao(id, tabela, action));
			ProdutoParent.setEstoqueList(new ArrayList<Estoque>());
			ProdutoParent.getEstoqueList().add(insertEstoque(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoParent.setPrecoList(new ArrayList<Preco>());
			ProdutoParent.getPrecoList().add(insertPreco(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoParent.setCustoList(new ArrayList<Custo>());
			ProdutoParent.getCustoList().add(insertCusto(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoParent.setPorcaoList(new ArrayList<Porcao>());
			ProdutoParent.getPorcaoList().add(insertPorcao(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoParent.setRentabilidadeList(new ArrayList<Rentabilidade>());
			ProdutoParent.getRentabilidadeList().add(insertRentabilidade(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoParent.setCfopList(new ArrayList<CfopParentId>());
			ProdutoParent.getCfopList().add(insertCfopParentId(id,TabelaEnum.PRODUTOPARENT,action));
			ProdutoParent.setDataValidade(a.getTime());
			ProdutoParent.setParentId(id);
			ProdutoParent.setEmprId(1);
			ProdutoParent.setModifyDateUTC(a.getTime());
			ProdutoParent.setCreateDateUTC(a.getTime());
			ProdutoParent.setCreateUser("system");
			ProdutoParent.setModifyUser("system");
			ProdutoParent.setProcessId(1);
			ProdutoParent.setModelAction(action);

			return ProdutoParent;
		}


	private CfopParentId insertCfopParentId(Integer id, TabelaEnum produtoparent, PersistenceActionEnum action) {

		CfopParentId produto = new CfopParentId();
		Date a = new Date();
		produto.setId(100);
		produto.setTabelaEnum(produtoparent);
		produto.setCfop(insertCfop(id, produtoparent, action));
		produto.setEmprId(1);
		produto.setModifyDateUTC(a.getTime());
		produto.setCreateDateUTC(a.getTime());
		produto.setCreateUser("system");
		produto.setModifyUser("system");
		produto.setProcessId(1);
		produto.setModelAction(action);

		return produto;
	}

	public Produto insertProduto(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Produto produto = new Produto();
			Date a = new Date();
			produto.setId(id);
			produto.setCodigo("NATIVE INSERT UPDATE");
			produto.setCdBarras("NATIVE INSERT UPDATE");
			produto.setProduto("NATIVE INSERT UPDATE");
			produto.setDataCreate(a.getTime());
			produto.setAplicacao("NATIVE INSERT UPDATE");
			produto.setFracao("NATIVE INSERT UPDATE");
//			produto.setPorcao(new Double(1.99));
//			produto.setPesoBruto(new Double(1.99));
//			produto.setPesoLiquido(new Double(1.99));
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
			cfop.setId(id);
			cfop.setCfop("NATIVE INSERT UPDATE");
			cfop.setNatureza("NATIVE INSERT UPDATE");
			cfop.setSimplificado("NATIVE INSERT UPDATE");
			cfop.setCfopTypeEnumValue(1);
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
			marca.setId(id);
			marca.setFabricante("NATIVE INSERT UPDATE");
			marca.setMarca("NATIVE INSERT UPDATE");
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


	public MarcaProduto insertMarcaProd(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			MarcaProduto marcaproduto = new MarcaProduto();
			Date a = new Date();
			marcaproduto.setId(id);
			marcaproduto.setParentId(id);
			marcaproduto.setMarcaId(insertMarca(id, tabela, action));
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
			grupo.setId(id);
			grupo.setGrupo("NATIVE INSERT UPDATE");
			grupo.setDescricao("NATIVE INSERT UPDATE");
			grupo.setSubGrupo(insertSubGrupo(id, tabela, action));
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
			subgrupo.setId(id);
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
			unimed.setId(id);
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
			tributacao.setId(id);
		//	tributacao.setCstId(100);
			tributacao.setIcms(new Double(1.99));
			tributacao.setSt(new Double(1.99));
			tributacao.setMva(new Double(1.99));
		//	tributacao.setCsosnId(100);
			tributacao.setIpi(new Double(1.99));
			tributacao.setIat(new Double(1.99));
			tributacao.setIppt(new Double(1.99));
		//	tributacao.setIncidencia(100);
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
			custo.setId(id);
			custo.setValor(new Double(1.99));
			custo.setCustoItens(new ArrayList<CustoItens>());
			custo.getCustoItens().add(insertCustoItens(id,TabelaEnum.CUSTO,action));
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
			custoitens.setId(id);
			custoitens.setParentId(100);
			custoitens.setCusto("Custo");
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
			estoque.setId(id);
			estoque.setParentId(id);
			estoque.setEstoqueTypeEnumValue(1);
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
			porcao.setId(id);
			porcao.setParentId(100);
			porcao.setValor(new Double(1.99));
			porcao.setPorcaoItens(new ArrayList<PorcaoItens>());
			porcao.getPorcaoItens().add(insertPorcaoItens(id,TabelaEnum.PORCAO,action));
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
			porcaoitens.setId(id);
			porcaoitens.setParentId(id);
			porcaoitens.setPorcao(new Double(1.99));
			porcaoitens.setVd(new Double(1.99));
			porcaoitens.setUnimed(insertUniMed(id, tabela, action));
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
			rentabilidade.setId(id);
			rentabilidade.setRentabilidadeItensList(new ArrayList<RentabilidadeItens>());
			rentabilidade.getRentabilidadeItensList().add(insertRentabilidadeItens(id,TabelaEnum.RENTABILIDADE,action));
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
			rentabilidadeitens.setId(id);
			rentabilidadeitens.setParentId(100);
			rentabilidadeitens.setProduto(100);
			rentabilidadeitens.setValor(new Double(1.99));
			rentabilidadeitens.setRentabilidadeTypeEnumValue(1);
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

	public Preco insertPreco(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
	{
		Preco servico = new Preco();
		Date a = new Date();

		servico.setDataMarcacao(a.getTime());
		servico.setParentId(id);
		servico.setTabelaEnum(tabela);
		servico.setPrecoTypeEnum(PrecoTypeEnum.COMPRA);
		servico.setValor(new Double(1.99));
		servico.setDataProInicial(a.getTime());
		servico.setDataProFinal(a.getTime());
		servico.setMaxVendProd(10);
		servico.setEmprId(1);
		servico.setModifyDateUTC(a.getTime());
		servico.setCreateDateUTC(a.getTime());
		servico.setCreateUser("system");
		servico.setModifyUser("system");
		servico.setProcessId(1);
		servico.setModelAction(action);

		return servico;
	}
}
