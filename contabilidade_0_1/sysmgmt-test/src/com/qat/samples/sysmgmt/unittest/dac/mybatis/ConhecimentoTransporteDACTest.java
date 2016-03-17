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
public class ConhecimetoTransporteDACTest extends AbstractTransactionalJUnit4SpringContextTests
{
	private static final Logger LOG = LoggerFactory.getLogger(ConhecimetoTransporteDACTest.class);
	private IConhecimetoTransporteDAC produtoDAC; // injected by Spring through setter below

	public IConhecimetoTransporteDAC getConhecimetoTransporteDAC()
	{
		return produtoDAC;
	}

	@Resource
	public void setConhecimetoTransporteDAC(IConhecimetoTransporteDAC newValue)
	{
		produtoDAC = newValue;
	}
	
	public void testupdateConhecimentoTransporte() throws Exception
	{
		ConhecimetoTransporte conhecimetoTransporte = new ConhecimetoTransporte();
		conhecimetoTransporte = mockConhecimetoTransporte(PersistanceActionEnum.UPDATE);
		InternalResultsResponse<ConhecimetoTransporte> response = getConhecimetoTransporteDAC().updateConhecimetoTransporte(conhecimetoTransporte);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	public void testinsertConhecimentoTransporte() throws Exception
	{
		ConhecimetoTransporte conhecimetoTransporte = new ConhecimetoTransporte();
		conhecimetoTransporte = mockConhecimetoTransporte(PersistanceActionEnum.INSERT);
		InternalResultsResponse<ConhecimetoTransporte> response = getConhecimetoTransporteDAC().insertConhecimetoTransporte(conhecimetoTransporte);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(22);
		InternalResultsResponse<ConhecimetoTransporte> responseA = getConhecimetoTransporteDAC().fetchConhecimetoTransporteById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertTrue(responseA.getResultsList().get(0).getCfopList().size() == conhecimetoTransporte.getCfopList().size());
		assertTrue(responseA.getResultsList().get(0).getClassificacao().getDescricao() == conhecimetoTransporte.getClassificacao()
				.getDescricao());
		assertTrue(responseA.getResultsList().get(0).getCustoList().size() == conhecimetoTransporte.getCustoList().size());

	}

	public void testdeleteConhecimentoTransporte() throws Exception
	{
		ConhecimetoTransporte conhecimetoTransporte = new ConhecimetoTransporte();
		conhecimetoTransporte.setId(1);
		conhecimetoTransporte = mockConhecimetoTransporte(PersistanceActionEnum.DELETE);
		InternalResponse response = getConhecimetoTransporteDAC().deleteConhecimetoTransporte(conhecimetoTransporte);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}
	public void testfetchConhecimentoTransporteByRequest() throws Exception
	{
		// check for valid and precount
		ConhecimetoTransporteInquiryRequest request = new ConhecimetoTransporteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<ConhecimetoTransporte> response = getConhecimetoTransporteDAC().fetchConhecimetoTransporteByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

	}

	public void testfetchConhecimentoTransporteById() throws Exception
	{
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1000);
		InternalResultsResponse<ConhecimetoTransporte> response = getConhecimetoTransporteDAC().fetchConhecimetoTransporteById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertConhecimentoTransporte.sql", false);
	}

}
