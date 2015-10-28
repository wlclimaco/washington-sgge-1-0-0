package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
import com.qat.samples.sysmgmt.produto.model.Cadastro;
import com.qat.samples.sysmgmt.produto.model.CadastroTypeEnum;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Tabelapreco;
import com.qat.samples.sysmgmt.produto.model.request.CadastroInquiryRequest;
import com.qat.samples.sysmgmt.supermercado.model.Supermercado;
import com.qat.samples.sysmgmt.util.ControleAcess;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class ProdutoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoDACTest.class);
	private IProdutoDAC produtoDAC; // injected by Spring through setter below

	public IProdutoDAC getProdutoDAC()
	{
		return produtoDAC;
	}

	@Resource
	public void setProdutoDAC(IProdutoDAC newValue)
	{
		produtoDAC = newValue;
	}

	@Test
	public void testInsertProduto() throws Exception
	{
		// getProdutoDAC().deleteAllProdutos();
		Produto produto = createProduto();
		getProdutoDAC().insertProduto(produto);
		FetchByIdRequest request = createFetchByIdRequest(produto.getId());
		Produto response = getProdutoDAC().fetchProdutoById(request);
		assertEquals(produto.getId(), response.getId());
		// assertEquals(produto.getEstado(), response.getEstado());
		assertEquals(produto.getId(), response.getId());
	}

	@Test
	public void testCadastroProduto() throws Exception
	{
		getProdutoDAC().deleteAllCadastros(createCadastro());
		Cadastro produto = createCadastro();
		getProdutoDAC().insertCadastro(produto);
		Cadastro response = getProdutoDAC().fetchCadastroById(createCadastro());
		assertEquals(produto.getId(), response.getId());
		// assertEquals(produto.getEstado(), response.getEstado());
		assertEquals(produto.getId(), response.getId());
	}

	@Test
	public void testUpdateProduto() throws Exception
	{
		getProdutoDAC().deleteAllProdutos();
		Produto produto = createProduto();
		getProdutoDAC().insertProduto(produto);
		FetchByIdRequest request = createFetchByIdRequest(produto.getId());
		// produto.setProduto("NewDescription");
		getProdutoDAC().updateProduto(produto);
		Produto response = getProdutoDAC().fetchProdutoById(request);
		assertEquals(produto.getId(), response.getId());
		// assertEquals(produto.getEstado(), response.getEstado());
	}

	@Test
	public void testUpdateCadastro() throws Exception
	{
		getProdutoDAC().deleteAllCadastros(createCadastro());
		Cadastro produto = createCadastro();
		getProdutoDAC().insertCadastro(produto);
		FetchByIdRequest request = createFetchByIdRequest(produto.getId());
		// produto.setCadastro("NewDescription");
		getProdutoDAC().updateCadastro(produto);
		Cadastro response = getProdutoDAC().fetchCadastroById(createCadastro());
		assertEquals(produto.getId(), response.getId());
		// assertEquals(produto.getEstado(), response.getEstado());
	}

	@Test
	public void testDeleteAll() throws Exception
	{
		getProdutoDAC().deleteAllProdutos();
		assertTrue(getProdutoDAC().fetchAllProdutos().isEmpty());
	}

	@Test
	public void testFetchProdutosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Produto> response = getProdutoDAC().fetchProdutosByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check next page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(4);
		response = getProdutoDAC().fetchProdutosByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Produto> response2 = getProdutoDAC().fetchProdutosByRequest(request2);
		assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoDAC().deleteAllProdutos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Produto> response3 = getProdutoDAC().fetchProdutosByRequest(request3);
		assertTrue(response3.getStatus() == Status.NoRowsFoundError);

	}

	@Test
	public void testDeleteProduto() throws Exception
	{
		Produto produto = createProduto();
		getProdutoDAC().insertProduto(produto);
		FetchByIdRequest request = createFetchByIdRequest(produto.getId());
		assertNotNull(getProdutoDAC().fetchProdutoById(request));
		getProdutoDAC().deleteProduto(produto);
		assertNull(getProdutoDAC().fetchProdutoById(request));
	}

	private Produto createProduto()
	{
		List<Tabelapreco> list = new ArrayList<Tabelapreco>();
		Tabelapreco tabela = new Tabelapreco();
		tabela.setIdProduto(1);
		tabela.setPreco(new Double(1.5));
		list.add(tabela);
		Produto produto = new Produto(1, "0000001", new Cadastro(1),
				new Cadastro(2),
				new Cadastro(3),
				new Cadastro(4),
				new Cadastro(5),
				"nome",
				"descricao",
				"foto",
				list
				);
		Supermercado supermercado = new Supermercado(1);
		produto.setSupermercadoid(supermercado);
		List<ControleAcess> lista = new ArrayList<ControleAcess>();
		ControleAcess controle = new ControleAcess();
		controle.setUserId(1);
		controle.setTenantId(1);
		lista.add(controle);
		produto.setAcessos(lista);
		return produto;
	}

	private Cadastro createCadastro()
	{
		Cadastro cadastro = new Cadastro(1, CadastroTypeEnum.MENU, "COCA COLA");
		ControleAcess controle = new ControleAcess();
		controle.setUserId(1);
		controle.setTenantId(1);
		List<ControleAcess> list = new ArrayList<ControleAcess>();
		list.add(controle);
		cadastro.setAcessos(list);
		return cadastro;
	}

	private FetchByIdRequest createFetchByIdRequest(Integer value)
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(value);
		return request;
	}

	private FetchByIdRequest createFetchByIdCadastroRequest(Integer value)
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(value);
		return request;
	}

	//

	@Test
	public void testFetchCadastrosByRequest() throws Exception
	{
		// check for valid and precount
		CadastroInquiryRequest request = new CadastroInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setCadastro(createCadastro());
		request.setPageSize(4);
		InternalResultsResponse<Cadastro> response = getProdutoDAC().fetchCadastrosByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		Cadastro responses = getProdutoDAC().fetchCadastroById(createCadastro());
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check next page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(4);
		request.setCadastro(createCadastro());
		response = getProdutoDAC().fetchCadastrosByRequest(request);
		assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		CadastroInquiryRequest request2 = new CadastroInquiryRequest();
		request2.setPreQueryCount(false);
		request.setCadastro(createCadastro());
		InternalResultsResponse<Cadastro> response2 = getProdutoDAC().fetchCadastrosByRequest(request2);
		assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getProdutoDAC().deleteAllCadastros(createCadastro());
		CadastroInquiryRequest request3 = new CadastroInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Cadastro> response3 = getProdutoDAC().fetchCadastrosByRequest(request3);
		assertTrue(response3.getStatus() == Status.NoRowsFoundError);

	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertCadastro.sql", false);
	}

}
