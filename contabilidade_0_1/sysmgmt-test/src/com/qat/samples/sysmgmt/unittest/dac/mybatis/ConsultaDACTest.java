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
import com.qat.samples.sysmgmt.fiscal.Consulta;
import com.qat.samples.sysmgmt.fiscal.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.ConsultaInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.Configuracao;
import com.qat.samples.sysmgmt.util.Documento;
import com.qat.samples.sysmgmt.util.DocumentoTypeEnum;
import com.qat.samples.sysmgmt.util.Email;
import com.qat.samples.sysmgmt.util.EmailTypeEnum;
import com.qat.samples.sysmgmt.util.Consulta;
import com.qat.samples.sysmgmt.util.ConsultaTypeEnum;
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
public class ConsultaDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(ConsultaDACTest.class);
	private IConsultaDAC consultaDAC; // injected by Spring through setter @resource

	// below

	public IConsultaDAC getConsultaDAC()
	{
		return consultaDAC;
	}

	@Resource
	public void setConsultaDAC(IConsultaDAC consultaDAC)
	{
		this.consultaDAC = consultaDAC;
	}

	@Test
	public void testupdateConsulta() throws Exception
	{

		Consulta funcionario = new Consulta();
		funcionario = insertConsulta(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();
		Integer a = getEntidadeDAC().insertConsulta(funcionario,"", response);
		
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		response = new InternalResultsResponse<Consulta>();
		
		a = getEntidadeDAC().updateConsulta(funcionario, response);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertConsulta() throws Exception
	{

		Consulta funcionario = new Consulta();
		funcionario = insertConsulta(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();

		Integer a = getConsultaDAC().insertConsulta(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		
		
		Consulta funcionario = new Consulta();
		funcionario = insertConsulta(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();

		Integer a = getEntidadeDAC().insertConsulta(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	//	FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Consulta> responseA = getEntidadeDAC().fetchConsultaById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);


	}

	@Test
	public void testdeleteConsulta() throws Exception
	{

		Consulta funcionario = new Consulta();
		funcionario = insertConsulta(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Consulta> response = new InternalResultsResponse<Consulta>();
		Integer a = getEntidadeDAC().insertConsulta(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Consulta>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getEntidadeDAC().deleteConsulta(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		//FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Classicacao> responseA = getEntidadeDAC().fetchConsultaById(funcionarioResponse.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchConsultaById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Consulta> response = getConsultaDAC().fetchConsultaById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchConsultaById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Consulta> response = getConsultaDAC().fetchConsultaById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchConsultaByRequest() throws Exception
	{
		// check for valid and precount
		ConsultaInquiryRequest request = new ConsultaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Consulta> response = getConsultaDAC().fetchConsultaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Consulta insertConsulta(PersistanceActionEnum action)
	{
		Consulta exame = new Consulta();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataConsulta((int)a.getTime());
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
