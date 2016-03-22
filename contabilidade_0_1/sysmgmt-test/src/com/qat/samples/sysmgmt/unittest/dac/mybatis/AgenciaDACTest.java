package com.qat.samples.sysmgmt.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
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

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.estado.Estado;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.dac.IAgenciaDAC;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.Email;
import com.qat.samples.sysmgmt.util.EmailTypeEnum;
import com.qat.samples.sysmgmt.util.Endereco;
import com.qat.samples.sysmgmt.util.EnderecoTypeEnum;
import com.qat.samples.sysmgmt.util.Telefone;
import com.qat.samples.sysmgmt.util.TelefoneTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class AgenciaDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(AgenciaDACTest.class);
	private IAgenciaDAC agenciaDAC; // injected by Spring through setter @resource

	// below

	public IAgenciaDAC getAgenciaDAC()
	{
		return agenciaDAC;
	}

	@Resource
	public void setAgenciaDAC(IAgenciaDAC agenciaDAC)
	{
		this.agenciaDAC = agenciaDAC;
	}

	@Test
	public void testupdateAgencia() throws Exception
	{

		Agencia funcionario = new Agencia();
		Integer a = 0;
		funcionario = insertAgencia(PersistanceActionEnum.UPDATE);

		a = getAgenciaDAC().updateAgencia(funcionario);

	}

	@Test
	public void testinsertAgencia() throws Exception
	{

		Agencia funcionario = new Agencia();
		Integer a = 0;
		funcionario = insertAgencia(PersistanceActionEnum.INSERT);

		a = getAgenciaDAC().insertAgencia(funcionario);

	}

	@Test
	public void testdeleteAgencia() throws Exception
	{

		Agencia funcionario = new Agencia();
		funcionario.setId(1);
		Integer a = 0;
		funcionario = insertAgencia(PersistanceActionEnum.DELETE);
		a = getAgenciaDAC().deleteAgencia(funcionario);

	}

	@Test
	public void testfetchAgenciaById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Agencia> response = getAgenciaDAC().fetchAgenciaById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchAgenciaByRequest() throws Exception
	{
		// check for valid and precount
		AgenciaInquiryRequest request = new AgenciaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Agencia> response = getAgenciaDAC().fetchAgenciaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertAgencia.sql", false);
	}

	public Agencia insertAgencia(PersistanceActionEnum action)
	{
		Agencia agencia = new Agencia();
		agencia.setId(1);
		agencia.setEmails(emailList(action));
		agencia.setNome("NOME");
		agencia.setEnderecos(enderecoList(action));
		agencia.setTelefones(telefoneList(action));
		agencia.setGerente("GERENTE");
		agencia.setResponsavelConta("RESPONSAVEL");
		agencia.setNumeroConta("123456789");

		return agencia;
	}

	public List<Email> emailList(PersistanceActionEnum action)
	{
		List<Email> documentoList = new ArrayList<Email>();
		Email documento = new Email(0, "email", EmailTypeEnum.COMPRAS, action);
		documento.setProcessId(1);
		documentoList.add(documento);
		documento = new Email(0, "email", EmailTypeEnum.NFE, action);
		documento.setProcessId(1);
		documentoList.add(documento);
		documento = new Email(0, "email", EmailTypeEnum.VENDAS, action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<Telefone> telefoneList(PersistanceActionEnum action)
	{
		List<Telefone> documentoList = new ArrayList<Telefone>();
		Telefone documento = new Telefone(0, "ddd", "numero", TelefoneTypeEnum.DIRETOR, action);
		documento.setProcessId(1);
		documentoList.add(documento);

		documento = new Telefone(0, "ddd", "numero", TelefoneTypeEnum.COMPRAS, action);
		documento.setProcessId(1);
		documentoList.add(documento);

		documento = new Telefone(0, "ddd", "numero", TelefoneTypeEnum.REPRESENTANTE, action);
		documento.setProcessId(1);
		documentoList.add(documento);

		return documentoList;

	}

	public List<Endereco> enderecoList(PersistanceActionEnum action)
	{
		List<Endereco> enderecoList = new ArrayList<Endereco>();
		Endereco endereco =
				new Endereco(0, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
						EnderecoTypeEnum.ENTREGA, action);
		endereco.setProcessId(1);
		enderecoList.add(endereco);

		endereco =
				new Endereco(0, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
						EnderecoTypeEnum.COBRANCA, action);
		endereco.setProcessId(1);
		enderecoList.add(endereco);

		endereco =
				new Endereco(0, "logradouro", new Cidade(), new Estado(), "bairro", "numero", "cep", "complemento",
						EnderecoTypeEnum.PRINCIPAL, action);
		endereco.setProcessId(1);
		enderecoList.add(endereco);
		return enderecoList;

	}
}
