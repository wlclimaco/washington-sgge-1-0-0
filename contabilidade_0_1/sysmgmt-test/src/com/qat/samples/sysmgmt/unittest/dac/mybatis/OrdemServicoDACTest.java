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
import com.qat.samples.sysmgmt.fiscal.OrdemServico;
import com.qat.samples.sysmgmt.fiscal.Csosn;
import com.qat.samples.sysmgmt.fiscal.Cst;
import com.qat.samples.sysmgmt.fiscal.Tributacao;
import com.qat.samples.sysmgmt.fiscal.model.request.OrdemServicoInquiryRequest;
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

	private static final Logger LOG = LoggerFactory.getLogger(OrdemServicoDACTest.class);
	private IOrdemServicoDAC ordemServicoDAC; // injected by Spring through setter @resource

	// below

	public IOrdemServicoDAC getOrdemServicoDAC()
	{
		return ordemServicoDAC;
	}

	@Resource
	public void setOrdemServicoDAC(IOrdemServicoDAC ordemServicoDAC)
	{
		this.ordemServicoDAC = ordemServicoDAC;
	}

	@Test
	public void testupdateOrdemServico() throws Exception
	{

		OrdemServico funcionario = new OrdemServico();
		funcionario = insertOrdemServico(PersistanceActionEnum.INSERT);
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
		Integer a = getEntidadeDAC().insertOrdemServico(funcionario,"", response);
		
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		response = new InternalResultsResponse<OrdemServico>();
		
		a = getEntidadeDAC().updateOrdemServico(funcionario, response);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertOrdemServico() throws Exception
	{

		OrdemServico funcionario = new OrdemServico();
		funcionario = insertOrdemServico(PersistanceActionEnum.INSERT);

		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		Integer a = getOrdemServicoDAC().insertOrdemServico(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		
		
		OrdemServico funcionario = new OrdemServico();
		funcionario = insertOrdemServico(PersistanceActionEnum.INSERT);
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();

		Integer a = getEntidadeDAC().insertOrdemServico(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	//	FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<OrdemServico> responseA = getEntidadeDAC().fetchOrdemServicoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);


	}

	@Test
	public void testdeleteOrdemServico() throws Exception
	{

		OrdemServico funcionario = new OrdemServico();
		funcionario = insertOrdemServico(PersistanceActionEnum.INSERT);
		InternalResultsResponse<OrdemServico> response = new InternalResultsResponse<OrdemServico>();
		Integer a = getEntidadeDAC().insertOrdemServico(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<OrdemServico>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getEntidadeDAC().deleteOrdemServico(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		//FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Classicacao> responseA = getEntidadeDAC().fetchOrdemServicoById(funcionarioResponse.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchOrdemServicoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<OrdemServico> response = getOrdemServicoDAC().fetchOrdemServicoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchOrdemServicoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<OrdemServico> response = getOrdemServicoDAC().fetchOrdemServicoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchOrdemServicoByRequest() throws Exception
	{
		// check for valid and precount
		OrdemServicoInquiryRequest request = new OrdemServicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<OrdemServico> response = getOrdemServicoDAC().fetchOrdemServicoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public OrdemServico insertOrdemServico(PersistanceActionEnum action)
	{
		OrdemServico exame = new OrdemServico();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataOrdemServico((int)a.getTime());
		// exame.setMedicoResponsavel("Resposnsavel");
		// exame.setLaboratorio("Laboratorio");

		return exame;
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBanco.sql", false);
	}
}
