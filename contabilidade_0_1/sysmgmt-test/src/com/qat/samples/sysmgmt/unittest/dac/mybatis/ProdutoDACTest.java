package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
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

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.CfopPessoa;
import com.qat.samples.sysmgmt.cfop.CfopTypeEnum;
import com.qat.samples.sysmgmt.cfop.model.request.CfopInquiryRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Csosn;
import com.qat.samples.sysmgmt.fiscal.Cst;
import com.qat.samples.sysmgmt.fiscal.Tributacao;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.Fornecedor;
import com.qat.samples.sysmgmt.produto.dac.IProdutoDAC;
import com.qat.samples.sysmgmt.produto.model.Custo;
import com.qat.samples.sysmgmt.produto.model.CustoItem;
import com.qat.samples.sysmgmt.produto.model.Estoque;
import com.qat.samples.sysmgmt.produto.model.EstoqueTypeEnum;
import com.qat.samples.sysmgmt.produto.model.Grupo;
import com.qat.samples.sysmgmt.produto.model.GrupoProd;
import com.qat.samples.sysmgmt.produto.model.Incidencia;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.produto.model.MarcaProd;
import com.qat.samples.sysmgmt.produto.model.PlanoByServico;
import com.qat.samples.sysmgmt.produto.model.Porcao;
import com.qat.samples.sysmgmt.produto.model.PorcaoItem;
import com.qat.samples.sysmgmt.produto.model.PrecoTypeEnum;
import com.qat.samples.sysmgmt.produto.model.Produto;
import com.qat.samples.sysmgmt.produto.model.Rentabilidade;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeItens;
import com.qat.samples.sysmgmt.produto.model.RentabilidadeTypeEnum;
import com.qat.samples.sysmgmt.produto.model.Servico;
import com.qat.samples.sysmgmt.produto.model.SubGrupo;
import com.qat.samples.sysmgmt.produto.model.SubGrupoProd;
import com.qat.samples.sysmgmt.produto.model.TabPreco;
import com.qat.samples.sysmgmt.produto.model.UniMed;
import com.qat.samples.sysmgmt.produto.model.UniMedProd;
import com.qat.samples.sysmgmt.produto.model.request.GrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.MarcaInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ProdutoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.ServicoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.SubGrupoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.TributacaoInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.request.UniMedInquiryRequest;
import com.qat.samples.sysmgmt.util.Imagem;

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
	public void testupdateProduto() throws Exception
	{
		Produto produto = new Produto();
		produto = mockProduto(PersistanceActionEnum.UPDATE);
		InternalResultsResponse<Produto> response = getProdutoDAC().updateProduto(produto);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testinsertProduto() throws Exception
	{
		Produto produto = new Produto();
		produto = mockProduto(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Produto> response = getProdutoDAC().insertProduto(produto);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(22);
		InternalResultsResponse<Produto> responseA = getProdutoDAC().fetchProdutoById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertTrue(responseA.getResultsList().get(0).getCfopList().size() == produto.getCfopList().size());
		assertTrue(responseA.getResultsList().get(0).getClassificacao().getDescricao() == produto.getClassificacao()
				.getDescricao());
		assertTrue(responseA.getResultsList().get(0).getCustoList().size() == produto.getCustoList().size());

	}

	@Test
	public void testdeleteProduto() throws Exception
	{
		Produto produto = new Produto();
		produto.setId(1);
		produto = mockProduto(PersistanceActionEnum.DELETE);
		InternalResponse response = getProdutoDAC().deleteProduto(produto);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchProdutoById() throws Exception
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		InternalResultsResponse<Produto> response = getProdutoDAC().fetchProdutoById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchAllProdutos() throws Exception
	{
		InternalResultsResponse<Produto> response = getProdutoDAC().fetchAllProdutos();
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchProdutoByRequest() throws Exception
	{
		// check for valid and precount
		ProdutoInquiryRequest request = new ProdutoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Produto> response = getProdutoDAC().fetchProdutoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

	}

	@Test
	public void testfetchUniMedByRequest() throws Exception
	{
		// check for valid and precount
		UniMedInquiryRequest request = new UniMedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<UniMed> response = getProdutoDAC().fetchUniMedByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

	}

	@Test
	public void testfetchGrupoByRequest() throws Exception
	{
		// check for valid and precount
		GrupoInquiryRequest request = new GrupoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Grupo> response = getProdutoDAC().fetchGrupoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchSubGrupoByRequest() throws Exception
	{// check for valid and precount
		SubGrupoInquiryRequest request = new SubGrupoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<SubGrupo> response = getProdutoDAC().fetchSubGrupoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

	}

	@Test
	public void testfetchMarcaByRequest() throws Exception
	{// check for valid and precount
		MarcaInquiryRequest request = new MarcaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Marca> response = getProdutoDAC().fetchMarcaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

	}

	@Test
	public void testfetchTributacaoByRequest() throws Exception
	{
		// check for valid and precount
		TributacaoInquiryRequest request = new TributacaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Tributacao> response = getProdutoDAC().fetchTributacaoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchCfopByRequest() throws Exception
	{
		// check for valid and precount
		CfopInquiryRequest request = new CfopInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Cfop> response = getProdutoDAC().fetchCfopByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchClassificacaoByRequest() throws Exception
	{
		// check for valid and precount
		ClassificacaoInquiryRequest request = new ClassificacaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Classificacao> response = getProdutoDAC().fetchClassificacaoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testinsertServico() throws Exception
	{
		Servico produto = new Servico();
		produto = servicoMock(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Servico> response = getProdutoDAC().insertServico(produto);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testupdateServico() throws Exception
	{
		Servico produto = new Servico();
		produto = servicoMock(PersistanceActionEnum.UPDATE);
		InternalResultsResponse<Servico> response = getProdutoDAC().updateServico(produto);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testdeleteServico() throws Exception
	{
		Servico servico = new Servico();
		servico.setId(1);
		servico = servicoMock(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getProdutoDAC().deleteServico(servico);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchServicoById() throws Exception
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(2);
		InternalResultsResponse<Servico> response = getProdutoDAC().fetchServicoById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchServicoByRequest() throws Exception
	{// check for valid and precount
		ServicoInquiryRequest request = new ServicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Servico> response = getProdutoDAC().fetchServicoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

	}

	@Test
	public void testinsertPlano() throws Exception
	{
		Plano produto = new Plano();
		produto = planoMock(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Plano> response = getProdutoDAC().insertPlano(produto);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testupdatePlano() throws Exception
	{
		Plano produto = new Plano();
		produto = planoMock(PersistanceActionEnum.UPDATE);
		InternalResultsResponse<Plano> response = getProdutoDAC().updatePlano(produto);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testdeletePlano() throws Exception
	{
		Plano plano = new Plano();
		plano.setId(1);
		plano = planoMock(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getProdutoDAC().deletePlano(plano);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchPlanoById() throws Exception
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(2);
		InternalResultsResponse<Plano> response = getProdutoDAC().fetchPlanoById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchPlanoByRequest() throws Exception
	{
		// check for valid and precount
		PlanoInquiryRequest request = new PlanoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Plano> response = getProdutoDAC().fetchPlanoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	//
	// @Test
	// public void testInsertProduto() throws Exception
	// {
	// // getProdutoDAC().deleteAllProdutos();
	// Produto produto = createProduto();
	// getProdutoDAC().insertProduto(produto);
	// FetchByIdRequest request = createFetchByIdRequest(produto.getId());
	// Produto response = getProdutoDAC().fetchProdutoById(request);
	// assertEquals(produto.getId(), response.getId());
	// // assertEquals(produto.getEstado(), response.getEstado());
	// assertEquals(produto.getId(), response.getId());
	// }
	//
	// @Test
	// public void testCadastroProduto() throws Exception
	// {
	//
	// }
	//
	// @Test
	// public void testUpdateProduto() throws Exception
	// {
	// getProdutoDAC().deleteAllProdutos();
	// Produto produto = createProduto();
	// getProdutoDAC().insertProduto(produto);
	// FetchByIdRequest request = createFetchByIdRequest(produto.getId());
	// // produto.setProduto("NewDescription");
	// getProdutoDAC().updateProduto(produto);
	// Produto response = getProdutoDAC().fetchProdutoById(request);
	// assertEquals(produto.getId(), response.getId());
	// // assertEquals(produto.getEstado(), response.getEstado());
	// }
	//
	// @Test
	// public void testUpdateCadastro() throws Exception
	// {
	// getProdutoDAC().deleteAllCadastros(createCadastro());
	// Cadastro produto = createCadastro();
	// getProdutoDAC().insertCadastro(produto);
	// FetchByIdRequest request = createFetchByIdRequest(produto.getId());
	// // produto.setCadastro("NewDescription");
	// getProdutoDAC().updateCadastro(produto);
	// Cadastro response = getProdutoDAC().fetchCadastroById(createCadastro());
	// assertEquals(produto.getId(), response.getId());
	// // assertEquals(produto.getEstado(), response.getEstado());
	// }
	//
	// @Test
	// public void testDeleteAll() throws Exception
	// {
	// getProdutoDAC().deleteAllProdutos();
	// assertTrue(getProdutoDAC().fetchAllProdutos().isEmpty());
	// }
	//
	// @Test
	// public void testFetchProdutosByRequest() throws Exception
	// {
	// // check for valid and precount
	// PagedInquiryRequest request = new PagedInquiryRequest();
	// request.setPreQueryCount(true);
	// request.setStartPage(0);
	// request.setPageSize(4);
	// InternalResultsResponse<Produto> response = getProdutoDAC().fetchProdutosByRequest(request);
	// assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	// // check next page
	// request.setPreQueryCount(true);
	// request.setStartPage(1);
	// request.setPageSize(4);
	// response = getProdutoDAC().fetchProdutosByRequest(request);
	// assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	// assertTrue(response.getResultsSetInfo().getPageSize() == 4);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	//
	// // check for valid and no precount
	// PagedInquiryRequest request2 = new PagedInquiryRequest();
	// request2.setPreQueryCount(false);
	// InternalResultsResponse<Produto> response2 = getProdutoDAC().fetchProdutosByRequest(request2);
	// assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
	// assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
	// assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
	//
	// // check for zero rows
	// getProdutoDAC().deleteAllProdutos();
	// PagedInquiryRequest request3 = new PagedInquiryRequest();
	// request3.setPreQueryCount(true);
	// InternalResultsResponse<Produto> response3 = getProdutoDAC().fetchProdutosByRequest(request3);
	// assertTrue(response3.getStatus() == Status.NoRowsFoundError);
	//
	// }
	//
	// @Test
	// public void testDeleteProduto() throws Exception
	// {
	// Produto produto = createProduto();
	// getProdutoDAC().insertProduto(produto);
	// FetchByIdRequest request = createFetchByIdRequest(produto.getId());
	// assertNotNull(getProdutoDAC().fetchProdutoById(request));
	// getProdutoDAC().deleteProduto(produto);
	// assertNull(getProdutoDAC().fetchProdutoById(request));
	// }
	//
	// private Produto createProduto()
	// {
	// List<Tabelapreco> list = new ArrayList<Tabelapreco>();
	// Tabelapreco tabela = new Tabelapreco();
	// tabela.setIdProduto(1);
	// tabela.setPreco(new Double(1.5));
	// list.add(tabela);
	// Produto produto = new Produto(1, "0000001", new Cadastro(1),
	// new Cadastro(2),
	// new Cadastro(3),
	// new Cadastro(4),
	// new Cadastro(5),
	// "nome",
	// "descricao",
	// "foto",
	// list
	// );
	// Supermercado supermercado = new Supermercado(1);
	// produto.setSupermercadoid(supermercado);
	// List<ControleAcess> lista = new ArrayList<ControleAcess>();
	// ControleAcess controle = new ControleAcess();
	// controle.setUserId(1);
	// controle.setTenantId(1);
	// lista.add(controle);
	// produto.setAcessos(lista);
	// return produto;
	// }
	//
	// private Cadastro createCadastro()
	// {
	// Cadastro cadastro = new Cadastro(1, CadastroTypeEnum.MENU, "COCA COLA");
	// ControleAcess controle = new ControleAcess();
	// controle.setUserId(1);
	// controle.setTenantId(1);
	// List<ControleAcess> list = new ArrayList<ControleAcess>();
	// list.add(controle);
	// cadastro.setAcessos(list);
	// return cadastro;
	// }
	//
	// private FetchByIdRequest createFetchByIdRequest(Integer value)
	// {
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(value);
	// return request;
	// }
	//
	// private FetchByIdRequest createFetchByIdCadastroRequest(Integer value)
	// {
	// FetchByIdRequest request = new FetchByIdRequest();
	// request.setFetchId(value);
	// return request;
	// }
	//
	// //
	//
	// @Test
	// public void testFetchCadastrosByRequest() throws Exception
	// {
	// // check for valid and precount
	// CadastroInquiryRequest request = new CadastroInquiryRequest();
	// request.setPreQueryCount(true);
	// request.setStartPage(0);
	// request.setCadastro(createCadastro());
	// request.setPageSize(4);
	// InternalResultsResponse<Cadastro> response = getProdutoDAC().fetchCadastrosByRequest(request);
	// assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	// assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	//
	// Cadastro responses = getProdutoDAC().fetchCadastroById(createCadastro());
	// assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	// assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	//
	// // check next page
	// request.setPreQueryCount(true);
	// request.setStartPage(1);
	// request.setPageSize(4);
	// request.setCadastro(createCadastro());
	// response = getProdutoDAC().fetchCadastrosByRequest(request);
	// assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	// assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	// assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	//
	// // check for valid and no precount
	// CadastroInquiryRequest request2 = new CadastroInquiryRequest();
	// request2.setPreQueryCount(false);
	// request.setCadastro(createCadastro());
	// InternalResultsResponse<Cadastro> response2 = getProdutoDAC().fetchCadastrosByRequest(request2);
	// assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
	// assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
	// assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
	//
	// // check for zero rows
	// getProdutoDAC().deleteAllCadastros(createCadastro());
	// CadastroInquiryRequest request3 = new CadastroInquiryRequest();
	// request3.setPreQueryCount(true);
	// InternalResultsResponse<Cadastro> response3 = getProdutoDAC().fetchCadastrosByRequest(request3);
	// assertTrue(response3.getStatus() == Status.NoRowsFoundError);
	//
	// }

	public Produto mockProduto(PersistanceActionEnum model)
	{
		Produto produto = new Produto();

		produto.setId(1);
		produto.setCodigo("0001");
		produto.setEmprId(1);
		produto.setCdBarras("0001010101110101");
		produto.setDataCreate(new Long("14327833577780"));
		produto.setProduto("Produto 0001");
		produto.setAplicacao("Descricao");
		produto.setLocalizacao("Z001,E005");
		produto.setDataValidade(new Long("14327833577780"));
		produto.setComissao("1.5");
		produto.setFracao("10");
		produto.setPesoBruto(new Double(1.5));
		produto.setPesoLiquido(new Double(1.2));
		produto.setClassificacao(new Classificacao(1));
		produto.setUniMed(new UniMedProd(1));
		produto.setGrupo(new GrupoProd(1));
		produto.setSubGrupo(new SubGrupoProd(1));
		produto.setMarca(new MarcaProd(1));
		produto.setTributacao(mockTributacao(model));
		produto.setEstoqueList(mockEstoque(model));
		produto.setPrecoList(mockPreco(model));
		produto.setCustoList(mockCusto(model));
		produto.setPorcaoList(mockPorcao(model));
		produto.setRentabilidadeList(mockRentabilidade(model));
		produto.setCfopList(cfopList(model));
		produto.setFornecedorList(new ArrayList<Fornecedor>());
		produto.getFornecedorList().add(new Fornecedor());
		produto.setModelAction(model);

		return produto;

	}

	public Tributacao mockTributacao(PersistanceActionEnum model)
	{
		Tributacao tributacao = new Tributacao();

		tributacao.setId(1);
		tributacao.setCst(new Cst(1));
		tributacao.setIcms(new Double(10.60));
		tributacao.setSt((double)10);
		tributacao.setMva(new Double(10.60));
		tributacao.setCsosn(new Csosn(1));
		tributacao.setIpi(50.99);
		tributacao.setIat((double)1);
		tributacao.setModelAction(model);
		// tributacao.setTributacao.setpisconfins(1);
		tributacao.setIncidencia(new Incidencia());
		return tributacao;
	}

	public List<Estoque> mockEstoque(PersistanceActionEnum model)
	{
		List<Estoque> estoqueList = new ArrayList<Estoque>();

		Estoque estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.MINIMO);
		estoque.setUltimoMov(new Long("14327833577780"));
		estoque.setQuant(new Double(10));
		estoque.setModelAction(model);
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.MAXIMO);
		estoque.setUltimoMov(new Long("14327833577780"));
		estoque.setQuant(new Double(100));
		estoque.setModelAction(model);
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.INICIAL);
		estoque.setUltimoMov(new Long("14327833577780"));
		estoque.setQuant(new Double(1));
		estoque.setModelAction(model);
		estoqueList.add(estoque);

		estoque = new Estoque();
		estoque.setId(1);
		estoque.setEstoqueTypeEnum(EstoqueTypeEnum.ATUAL);
		estoque.setUltimoMov(new Long("14327833577780"));
		estoque.setQuant(new Double(16));
		estoque.setModelAction(model);
		estoqueList.add(estoque);

		return estoqueList;
	}

	public List<CfopPessoa> cfopList(PersistanceActionEnum action)
	{
		List<CfopPessoa> documentoList = new ArrayList<CfopPessoa>();
		CfopPessoa documento = new CfopPessoa();
		documento.setModelAction(action);
		documento.setProcessId(1);
		documento.setIdCfop(new Cfop(0, "cfop", "natureza", "simplificado", CfopTypeEnum.ENTRADA, 0.39, 0.15, 0.54,
				0.9, 0.1, "observacao", action));
		documento.setParentId(1);

		documentoList.add(documento);
		return documentoList;

	}

	public List<TabPreco> mockPreco(PersistanceActionEnum model)
	{
		List<TabPreco> precoList = new ArrayList<TabPreco>();

		TabPreco preco = new TabPreco();
		preco.setId(1);
		preco.setDataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.CUSTO);
		preco.setValor(new Double(10.90));
		preco.setDataProInicial(new Long("14327833577780"));
		preco.setDataProFinal(new Long("14327833577780"));
		preco.setModelAction(model);
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setDataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.VENDA);
		preco.setValor(new Double(10.90));
		preco.setDataProInicial(new Long("14327833577780"));
		preco.setDataProFinal(new Long("14327833577780"));
		preco.setModelAction(model);
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setDataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.COMPRA);
		preco.setValor(new Double(10.90));
		preco.setDataProInicial(new Long("14327833577780"));
		preco.setDataProFinal(new Long("14327833577780"));
		preco.setModelAction(model);
		precoList.add(preco);

		preco = new TabPreco();
		preco.setId(1);
		preco.setDataMarcacao(new Long("14327833577780"));
		preco.setPrecoTypeEnum(PrecoTypeEnum.PROMOCAO);
		preco.setValor(new Double(10.90));
		preco.setDataProInicial(new Long("14327833577780"));
		preco.setDataProFinal(new Long("14327833577780"));
		preco.setModelAction(model);
		precoList.add(preco);

		return precoList;
	}

	public List<Custo> mockCusto(PersistanceActionEnum model)
	{
		List<Custo> custoList = new ArrayList<Custo>();

		Custo custo = new Custo();
		custo.setId(1);
		custo.setCusto(new CustoItem(1));
		custo.setValor((double)10);
		custo.setModelAction(model);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(2);
		custo.setCusto(new CustoItem(2));
		custo.setValor((double)20);
		custo.setModelAction(model);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(3);
		custo.setCusto(new CustoItem(3));
		custo.setValor((double)30);
		custo.setModelAction(model);
		custoList.add(custo);

		custo = new Custo();
		custo.setId(4);
		custo.setCusto(new CustoItem(4));
		custo.setValor((double)40);
		custo.setModelAction(model);
		custoList.add(custo);

		return custoList;
	}

	public List<Porcao> mockPorcao(PersistanceActionEnum model)
	{
		List<Porcao> porcaoList = new ArrayList<Porcao>();
		List<PorcaoItem> porcaoItemList = new ArrayList<PorcaoItem>();

		PorcaoItem item = new PorcaoItem();
		item.setId(1);
		item.setNome("AMINIACIDOS");
		item.setPorcao(new Double(20));
		item.setVd(new Double(20));
		item.setUnimed(new UniMed(2));
		porcaoItemList.add(item);

		item = new PorcaoItem();
		item.setId(2);
		item.setNome("PROTEINA");
		item.setPorcao(new Double(20));
		item.setVd(new Double(20));
		item.setUnimed(new UniMed(2));
		porcaoItemList.add(item);

		item = new PorcaoItem();
		item.setId(3);
		item.setNome("GORDURA");
		item.setPorcao(new Double(20));
		item.setVd(new Double(20));
		item.setUnimed(new UniMed(2));
		porcaoItemList.add(item);

		Porcao porcao = new Porcao();
		porcao.setId(1);
		porcao.setPorcaoItens(porcaoItemList);
		porcao.setValor(new Double(100));
		porcaoList.add(porcao);

		return porcaoList;
	}

	public List<Rentabilidade> mockRentabilidade(PersistanceActionEnum model)
	{
		List<Rentabilidade> rentabilidadeList = new ArrayList<Rentabilidade>();
		List<RentabilidadeItens> rentabilidadeItensList = new ArrayList<RentabilidadeItens>();

		RentabilidadeItens item = new RentabilidadeItens();
		item.setId(1);
		item.setProduto(new Produto(1));
		item.setValor(new Double(10));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PRODUTO);
		rentabilidadeItensList.add(item);

		item = new RentabilidadeItens();
		item.setId(2);
		item.setProduto(new Produto(2));
		item.setValor(new Double(5));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PRODUTO);
		rentabilidadeItensList.add(item);

		item = new RentabilidadeItens();
		item.setId(3);
		item.setProduto(new Produto(3));
		item.setValor(new Double(1));
		item.setRentabilidadeTypeEnum(RentabilidadeTypeEnum.PERDA);
		rentabilidadeItensList.add(item);

		Rentabilidade rentabilidade = new Rentabilidade();
		rentabilidade.setId(1);
		rentabilidade.setRentabilidadeList(rentabilidadeItensList);
		rentabilidadeList.add(rentabilidade);

		return rentabilidadeList;
	}

	public Servico servicoMock(PersistanceActionEnum model)
	{
		Servico servico = new Servico();
		servico.setId(1);
		servico.setNome("NOME");
		servico.setDescricao("DESCRICAO");
		servico.setPreco(mockPreco(model));
		servico.setModelAction(model);

		return servico;
	}

	public Plano planoMock(PersistanceActionEnum model)
	{
		Plano plano = new Plano();
		Date a = new Date(0);
		plano.setId(1);
		plano.setDataInicio(a.getTime());
		plano.setDataFinal(a.getTime());
		plano.setPreco(mockPreco(model));
		plano.setNumeroContrato(1051);
		plano.setServicos(new ArrayList<PlanoByServico>());
		plano.setImagens(new ArrayList<Imagem>());
		plano.setDescricao("Descricao");
		plano.setTitulo("Titulo");

		plano.setPreco(mockPreco(model));

		return plano;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertProduto.sql", false);
	}

}
