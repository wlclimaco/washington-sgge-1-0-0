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
import com.qat.samples.sysmgmt.fiscal.Plano;
import com.qat.samples.sysmgmt.fiscal.Regime;
import com.qat.samples.sysmgmt.fiscal.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.fiscal.model.request.RegimeInquiryRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.produto.model.request.PlanoInquiryRequest;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.Configuracao;
import com.qat.samples.sysmgmt.util.Documento;
import com.qat.samples.sysmgmt.util.DocumentoTypeEnum;
import com.qat.samples.sysmgmt.util.Email;
import com.qat.samples.sysmgmt.util.EmailTypeEnum;
import com.qat.samples.sysmgmt.util.Plano;
import com.qat.samples.sysmgmt.util.PlanoTypeEnum;
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
public class PlanoDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(PlanoDACTest.class);
	private IPlanoDAC planoDAC; // injected by Spring through setter @resource

	// below

	public IPlanoDAC getPlanoDAC()
	{
		return planoDAC;
	}

	@Resource
	public void setPlanoDAC(IPlanoDAC planoDAC)
	{
		this.planoDAC = planoDAC;
	}

	@Test
	public void testupdatePlano() throws Exception
	{

		Plano funcionario = new Plano();
		funcionario = insertPlano(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();
		Integer a = getEntidadeDAC().insertPlano(funcionario,"", response);
		
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = funcionarioResponse.getFirstResult();
		funcionario.setModelAction(PersistanceActionEnum.UPDATE);
		funcionario.setId(funcionarioResponse.getFirstResult().getId());
		response = new InternalResultsResponse<Plano>();
		
		a = getEntidadeDAC().updatePlano(funcionario, response);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertPlano() throws Exception
	{

		Plano funcionario = new Plano();
		funcionario = insertPlano(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();

		Integer a = getPlanoDAC().insertPlano(funcionario, "INSERT", response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		
		
		Plano funcionario = new Plano();
		funcionario = insertPlano(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();

		Integer a = getEntidadeDAC().insertPlano(funcionario, response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	//	FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(response.getFirstResult().getId());
		InternalResultsResponse<Plano> responseA = getEntidadeDAC().fetchPlanoById(response.getFirstResult().getId());
		assertTrue(responseA.getResultsList().size() == 1);
		assertEquals(responseA.getStatus(), Status.OperationSuccess);


	}

	@Test
	public void testdeletePlano() throws Exception
	{

		Plano funcionario = new Plano();
		funcionario = insertPlano(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Plano> response = new InternalResultsResponse<Plano>();
		Integer a = getEntidadeDAC().insertPlano(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		funcionario = response.getFirstResult();
		response = new InternalResultsResponse<Plano>();
		funcionario.setModelAction(PersistanceActionEnum.DELETE);
		Integer b = getEntidadeDAC().deletePlano(funcionario,response);
		assertEquals(response.getStatus(), Status.OperationSuccess);
		//FetchByIdRequest request = new FetchByIdRequest();
	//	request.setFetchId(funcionarioResponse.getFirstResult().getId());
		InternalResultsResponse<Classicacao> responseA = getEntidadeDAC().fetchPlanoById(funcionarioResponse.getFirstResult().getId());
		assertTrue(responseA.getResultsList().get(0).getStatusList().get(0).getStatus() == CdStatusTypeEnum.DELETADO);

	}

	@Test
	public void testfetchPlanoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Plano> response = getPlanoDAC().fetchPlanoById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchPlanoById2() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Plano> response = getPlanoDAC().fetchPlanoById(1);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
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
		InternalResultsResponse<Plano> response = getPlanoDAC().fetchPlanoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	public Plano insertPlano(PersistanceActionEnum action)
	{
		Plano exame = new Plano();
		Date a = new Date();
		exame.setId(1);
		exame.setModelAction(action);
		// exame.setNome("Nome");
		// exame.setDataPlano((int)a.getTime());
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
