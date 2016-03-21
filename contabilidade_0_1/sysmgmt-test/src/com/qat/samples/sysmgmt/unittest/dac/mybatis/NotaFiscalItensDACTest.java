package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
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
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.banco.BancoPessoa;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.CfopPessoa;
import com.qat.samples.sysmgmt.cfop.CfopTypeEnum;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.CnaeEmpresa;
import com.qat.samples.sysmgmt.cnae.model.request.CnaeInquiryRequest;
import com.qat.samples.sysmgmt.contabilidade.Plano;
import com.qat.samples.sysmgmt.entidade.Deposito;
import com.qat.samples.sysmgmt.entidade.Empresa;
import com.qat.samples.sysmgmt.entidade.EntidadeTypeEnum;
import com.qat.samples.sysmgmt.entidade.Filial;
import com.qat.samples.sysmgmt.entidade.Usuario;
import com.qat.samples.sysmgmt.entidade.dac.IEmpresaDAC;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.estado.Estado;
import com.qat.samples.sysmgmt.fiscal.Classificacao;
import com.qat.samples.sysmgmt.fiscal.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.ClassificacaoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.Configuracao;
import com.qat.samples.sysmgmt.util.Documento;
import com.qat.samples.sysmgmt.util.DocumentoTypeEnum;
import com.qat.samples.sysmgmt.util.Email;
import com.qat.samples.sysmgmt.util.EmailTypeEnum;
import com.qat.samples.sysmgmt.util.NotaFiscalItens;
import com.qat.samples.sysmgmt.util.NotaFiscalItensTypeEnum;
import com.qat.samples.sysmgmt.util.Note;
import com.qat.samples.sysmgmt.util.Telefone;
import com.qat.samples.sysmgmt.util.TelefoneTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class NotaFiscalItensDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItensDACTest.class);
	private IEmpresaDAC enderecoDAC; // injected by Spring through setter @resource

	// below

	public INotaFiscalItensDAC getNotaFiscalItensDAC()
	{
		return enderecoDAC;
	}

	@Resource
	public void setNotaFiscalItensDAC(INotaFiscalItensDAC enderecoDAC)
	{
		this.enderecoDAC = enderecoDAC;
	}

	@Test
	public void testupdateNotaFiscalItens() throws Exception
	{

		NotaFiscalItens funcionario = new NotaFiscalItens();
		funcionario = insertNotaFiscalItens(PersistanceActionEnum.UPDATE);

		InternalResultsResponse<NotaFiscalItens> funcionarioResponse = getNotaFiscalItensDAC().updateNotaFiscalItens(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertNotaFiscalItens() throws Exception
	{

		NotaFiscalItens funcionario = new NotaFiscalItens();
		funcionario = insertNotaFiscalItens(PersistanceActionEnum.INSERT);

		InternalResultsResponse<NotaFiscalItens> funcionarioResponse = getNotaFiscalItensDAC().insertNotaFiscalItens(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(22);
		InternalResultsResponse<NotaFiscalItens> responseA = getNotaFiscalItensDAC().fetchNotaFiscalItensById(request);
		assertTrue(responseA.getResultsList().size() == 1);
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == StatusEnum.ANALIZANDO);

	}

	@Test
	public void testdeleteNotaFiscalItens() throws Exception
	{

		NotaFiscalItens funcionario = new NotaFiscalItens();
		funcionario.setId(1);
		funcionario = insertNotaFiscalItens(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getNotaFiscalItensDAC().deleteNotaFiscalItens(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchNotaFiscalItensById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<NotaFiscalItens> response = getNotaFiscalItensDAC().fetchNotaFiscalItensById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchNotaFiscalItensByRequest() throws Exception
	{
		// check for valid and precount
		NotaFiscalItensInquiryRequest request = new NotaFiscalItensInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<NotaFiscalItens> response = getNotaFiscalItensDAC().fetchNotaFiscalItensByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertNotaFiscalItens.sql", false);
	}
}
