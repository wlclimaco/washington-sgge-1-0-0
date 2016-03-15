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
import com.qat.samples.sysmgmt.agencia.Agencia;
import com.qat.samples.sysmgmt.agencia.model.request.AgenciaInquiryRequest;
import com.qat.samples.sysmgmt.banco.Banco;
import com.qat.samples.sysmgmt.banco.BancoPessoa;
import com.qat.samples.sysmgmt.banco.model.request.BancoInquiryRequest;
import com.qat.samples.sysmgmt.beneficios.BeneficioPessoa;
import com.qat.samples.sysmgmt.beneficios.Beneficios;
import com.qat.samples.sysmgmt.beneficios.model.request.BeneficiosInquiryRequest;
import com.qat.samples.sysmgmt.cfop.Cfop;
import com.qat.samples.sysmgmt.cfop.CfopPessoa;
import com.qat.samples.sysmgmt.cfop.CfopTypeEnum;
import com.qat.samples.sysmgmt.clinica.Consulta;
import com.qat.samples.sysmgmt.clinica.Especialidade;
import com.qat.samples.sysmgmt.clinica.Exame;
import com.qat.samples.sysmgmt.clinica.PlanoSaude;
import com.qat.samples.sysmgmt.clinica.model.request.ConsultaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.EspecializacaoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ExameInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PlanoSaudeInquiryRequest;
import com.qat.samples.sysmgmt.condpag.CondPag;
import com.qat.samples.sysmgmt.condpag.CondPagPessoa;
import com.qat.samples.sysmgmt.condpag.FormaPg;
import com.qat.samples.sysmgmt.condpag.FormaPgPessoa;
import com.qat.samples.sysmgmt.condpag.TipoPag;
import com.qat.samples.sysmgmt.condpag.model.request.FormaPgInquiryRequest;
import com.qat.samples.sysmgmt.conta.Conta;
import com.qat.samples.sysmgmt.contato.Contato;
import com.qat.samples.sysmgmt.contato.ContatoItens;
import com.qat.samples.sysmgmt.contato.ContatoTypeEnum;
import com.qat.samples.sysmgmt.convenio.Convenio;
import com.qat.samples.sysmgmt.convenio.ConvenioPessoa;
import com.qat.samples.sysmgmt.dp.EventoMesApp;
import com.qat.samples.sysmgmt.dp.EventoPessoa;
import com.qat.samples.sysmgmt.dp.Eventos;
import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.dp.Profissao;
import com.qat.samples.sysmgmt.dp.Salario;
import com.qat.samples.sysmgmt.dp.model.request.EventoInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.HoraFuncInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.ProfissaoInquiryRequest;
import com.qat.samples.sysmgmt.estado.Estado;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.pessoa.Cliente;
import com.qat.samples.sysmgmt.pessoa.Contador;
import com.qat.samples.sysmgmt.pessoa.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.Funcionario;
import com.qat.samples.sysmgmt.pessoa.Medico;
import com.qat.samples.sysmgmt.pessoa.Paciente;
import com.qat.samples.sysmgmt.pessoa.PessoaTypeEnum;
import com.qat.samples.sysmgmt.pessoa.Transportador;
import com.qat.samples.sysmgmt.pessoa.dac.IPessoaDAC;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContaInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ContadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.produto.model.ProdutoPessoa;
import com.qat.samples.sysmgmt.util.Cidade;
import com.qat.samples.sysmgmt.util.Documento;
import com.qat.samples.sysmgmt.util.DocumentoTypeEnum;
import com.qat.samples.sysmgmt.util.Email;
import com.qat.samples.sysmgmt.util.EmailTypeEnum;
import com.qat.samples.sysmgmt.util.Endereco;
import com.qat.samples.sysmgmt.util.EnderecoTypeEnum;
import com.qat.samples.sysmgmt.util.Note;
import com.qat.samples.sysmgmt.util.Telefone;
import com.qat.samples.sysmgmt.util.TelefoneTypeEnum;

@ContextConfiguration(locations = {
		"classpath:com/qat/samples/sysmgmt/unittest/conf/unittest-datasource-txn-context.xml",
		"classpath:com/qat/samples/sysmgmt/unittest/conf/aoplogger-context.xml",
		"classpath:conf/web/conf/qat-sysmgmt-base-dac-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class EntidadeDACTest extends AbstractTransactionalJUnit4SpringContextTests
{

	private static final Logger LOG = LoggerFactory.getLogger(EntidadeDACTest.class);
	private IPessoaDAC pessoaDAC; // injected by Spring through setter @resource

	// below

	public IPessoaDAC getPessoaDAC()
	{
		return pessoaDAC;
	}

	@Resource
	public void setPessoaDAC(IPessoaDAC pessoaDAC)
	{
		this.pessoaDAC = pessoaDAC;
	}

	@Test
	public void testInsertFunction() throws Exception
	{

		Funcionario funcionario = new Funcionario();
		funcionario = insertFuncionario(PersistanceActionEnum.INSERT);

		InternalResultsResponse<Funcionario> funcionarioResponse = getPessoaDAC().insertFuncionario(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testInsertClientes() throws Exception
	{

		Cliente funcionario = new Cliente();
		funcionario = insertCliente(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Cliente> funcionarioResponse = getPessoaDAC().insertCliente(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testInsertFornecedor() throws Exception
	{

		Fornecedor funcionario = new Fornecedor();
		funcionario = insertFornecedor(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Fornecedor> funcionarioResponse = getPessoaDAC().insertFornecedor(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testInsertTransportador() throws Exception
	{

		Transportador funcionario = new Transportador();
		funcionario = insertTransportador(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Transportador> funcionarioResponse = getPessoaDAC().insertTransportador(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testInsertContador() throws Exception
	{

		Contador funcionario = new Contador();
		funcionario = insertContador(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Contador> funcionarioResponse = getPessoaDAC().insertContador(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testUpdateFunction() throws Exception
	{

		Funcionario funcionario = new Funcionario();
		funcionario = insertFuncionario(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Funcionario> funcionarioResponse = getPessoaDAC().updateFuncionario(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().updateFuncionario(funcionario);

	}

	@Test
	public void testUpdateClientes() throws Exception
	{

		Cliente funcionario = new Cliente();
		funcionario = insertCliente(PersistanceActionEnum.UPDATE);
		Date a = new Date();

		InternalResultsResponse<Cliente> funcionarioResponse = getPessoaDAC().updateCliente(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().updateCliente(funcionario);

	}

	@Test
	public void testUpdateFornecedor() throws Exception
	{

		Fornecedor funcionario = new Fornecedor();
		funcionario = insertFornecedor(PersistanceActionEnum.UPDATE);
		Date a = new Date();

		InternalResultsResponse<Fornecedor> funcionarioResponse = getPessoaDAC().updateFornecedor(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().updateFornecedor(funcionario);

	}

	@Test
	public void testUpdateTransportador() throws Exception
	{

		Transportador funcionario = new Transportador();
		funcionario = insertTransportador(PersistanceActionEnum.UPDATE);
		Date a = new Date();

		InternalResultsResponse<Transportador> funcionarioResponse = getPessoaDAC().updateTransportador(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().updateTransportador(funcionario);

	}

	@Test
	public void testUpdateContador() throws Exception
	{

		Contador funcionario = new Contador();
		funcionario = insertContador(PersistanceActionEnum.UPDATE);
		Date a = new Date();

		InternalResultsResponse<Contador> funcionarioResponse = getPessoaDAC().updateContador(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().updateContador(funcionario);

	}

	@Test
	public void testDeleteFunction() throws Exception
	{

		Funcionario funcionario = new Funcionario();
		funcionario.setId(1);
		funcionario = insertFuncionario(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getPessoaDAC().deleteFuncionario(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testDeleteClientes() throws Exception
	{

		Cliente funcionario = new Cliente();
		funcionario = insertCliente(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getPessoaDAC().deleteCliente(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testDeleteFornecedor() throws Exception
	{

		Fornecedor funcionario = new Fornecedor();
		funcionario = insertFornecedor(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getPessoaDAC().deleteFornecedor(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testDeleteTransportador() throws Exception
	{

		Transportador funcionario = new Transportador();
		funcionario = insertTransportador(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getPessoaDAC().deleteTransportador(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testDeleteContador() throws Exception
	{

		Contador funcionario = new Contador();
		funcionario = insertContador(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getPessoaDAC().deleteContador(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testFetchFuncionariosByRequest() throws Exception
	{
		// check for valid and precount
		FuncionarioInquiryRequest request = new FuncionarioInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Funcionario> response = getPessoaDAC().fetchFuncionarioByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchClientesByRequest() throws Exception
	{
		// check for valid and precount
		ClienteInquiryRequest request = new ClienteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Cliente> response = getPessoaDAC().fetchClienteByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchFornecedorsByRequest() throws Exception
	{
		// check for valid and precount
		FornecedorInquiryRequest request = new FornecedorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Fornecedor> response = getPessoaDAC().fetchFornecedorByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchTransportadorsByRequest() throws Exception
	{
		// check for valid and precount
		TransportadorInquiryRequest request = new TransportadorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Transportador> response = getPessoaDAC().fetchTransportadorByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchContadorsByRequest() throws Exception
	{
		// check for valid and precount
		ContadorInquiryRequest request = new ContadorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Contador> response = getPessoaDAC().fetchContadorByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchFuncionariosById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(2);
		InternalResultsResponse<Funcionario> response = getPessoaDAC().fetchFuncionarioById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testFetchClientesById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(3);
		InternalResultsResponse<Cliente> response = getPessoaDAC().fetchClienteById(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testFetchFornecedorsById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		InternalResultsResponse<Fornecedor> response = getPessoaDAC().fetchFornecedorById(request);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testFetchTransportadorsById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		InternalResultsResponse<Transportador> response = getPessoaDAC().fetchTransportadorById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testFetchContadorsById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		InternalResultsResponse<Contador> response = getPessoaDAC().fetchContadorById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testFetchAgenciaByRequest() throws Exception
	{
		// check for valid and precount
		AgenciaInquiryRequest request = new AgenciaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Agencia> response = getPessoaDAC().fetchAgenciaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchBancoByRequest() throws Exception
	{
		// check for valid and precount
		BancoInquiryRequest request = new BancoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Banco> response = getPessoaDAC().fetchBancoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchBeneficiosRequest() throws Exception
	{
		// check for valid and precount
		BeneficiosInquiryRequest request = new BeneficiosInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Beneficios> response = getPessoaDAC().fetchBeneficiosRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchContaByRequest() throws Exception
	{
		// check for valid and precount
		ContaInquiryRequest request = new ContaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Conta> response = getPessoaDAC().fetchContaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchConveniosByRequest() throws Exception
	{
		// check for valid and precount
		ConvenioInquiryRequest request = new ConvenioInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Convenio> response = getPessoaDAC().fetchConvenioByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchEventosByRequest() throws Exception
	{
		// check for valid and precount
		EventoInquiryRequest request = new EventoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Eventos> response = getPessoaDAC().fetchEventosRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchFormaPgByRequest() throws Exception
	{
		// check for valid and precount
		FormaPgInquiryRequest request = new FormaPgInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<FormaPg> response = getPessoaDAC().fetchFormaPgByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchHorarioFuncsByRequest() throws Exception
	{
		// check for valid and precount
		HoraFuncInquiryRequest request = new HoraFuncInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<HorarioFunc> response = getPessoaDAC().fetchHorarioFuncsRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testFetchProfissaoByRequest() throws Exception
	{
		// check for valid and precount
		ProfissaoInquiryRequest request = new ProfissaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Profissao> response = getPessoaDAC().fetchProfissaoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	// ================================
	@Test
	public void testupdateMedico() throws Exception
	{

		Medico funcionario = new Medico();
		funcionario = insertMedico(PersistanceActionEnum.UPDATE);

		InternalResultsResponse<Medico> funcionarioResponse = getPessoaDAC().updateMedico(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testinsertMedico() throws Exception
	{

		Medico funcionario = new Medico();
		funcionario = insertMedico(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Medico> funcionarioResponse = getPessoaDAC().insertMedico(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeleteMedico() throws Exception
	{

		Medico funcionario = new Medico();
		funcionario = insertMedico(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getPessoaDAC().deleteMedico(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testfetchMedicoById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		InternalResultsResponse<Medico> response = getPessoaDAC().fetchMedicoById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchMedicoByRequest() throws Exception
	{
		// check for valid and precount
		MedicoInquiryRequest request = new MedicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Medico> response = getPessoaDAC().fetchMedicoByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	// ================================
	@Test
	public void testupdatePaciente() throws Exception
	{

		Transportador funcionario = new Transportador();
		funcionario = insertTransportador(PersistanceActionEnum.UPDATE);

		InternalResultsResponse<Transportador> funcionarioResponse = getPessoaDAC().updateTransportador(funcionario);
		assertEquals(funcionarioResponse, null);
		getPessoaDAC().updateTransportador(funcionario);

	}

	@Test
	public void testinsertPaciente() throws Exception
	{

		Paciente funcionario = new Paciente();
		funcionario = insertPaciente(PersistanceActionEnum.INSERT);
		InternalResultsResponse<Paciente> funcionarioResponse = getPessoaDAC().insertPaciente(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testdeletePaciente() throws Exception
	{

		Paciente funcionario = new Paciente();
		funcionario = insertPaciente(PersistanceActionEnum.DELETE);
		InternalResponse funcionarioResponse = getPessoaDAC().deletePaciente(funcionario);
		assertEquals(funcionarioResponse.getStatus(), Status.OperationSuccess);

	}

	@Test
	public void testfetchPacienteById() throws Exception
	{
		// check for valid and precount
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		InternalResultsResponse<Paciente> response = getPessoaDAC().fetchPacienteById(request);
		assertTrue(response.getResultsList().size() == 1);
		assertEquals(response.getStatus(), Status.OperationSuccess);
	}

	@Test
	public void testfetchPacienteByRequest() throws Exception
	{
		// check for valid and precount
		PacienteInquiryRequest request = new PacienteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Paciente> response = getPessoaDAC().fetchPacienteByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchConsultaByRequest() throws Exception
	{
		// check for valid and precount
		ConsultaInquiryRequest request = new ConsultaInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Consulta> response = getPessoaDAC().fetchConsultaByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchEspecialidadeRequest() throws Exception
	{
		// check for valid and precount
		EspecializacaoInquiryRequest request = new EspecializacaoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Especialidade> response = getPessoaDAC().fetchEspecialidadeByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchExameRequest() throws Exception
	{
		// check for valid and precount
		ExameInquiryRequest request = new ExameInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<Exame> response = getPessoaDAC().fetchExameByRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Test
	public void testfetchPlanoSaudeRequest() throws Exception
	{
		// check for valid and precount
		PlanoSaudeInquiryRequest request = new PlanoSaudeInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(4);
		InternalResultsResponse<PlanoSaude> response = getPessoaDAC().fetchPlanoSaudeRequest(request);
		assertTrue(response.getResultsSetInfo().getPageSize() == 4);
		assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
	}

	@Before
	public void setup()
	{
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertPessoa.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBanco.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertBeneficios.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertCondPag.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertHorario.sql", false);
		executeSqlScript("com/qat/samples/sysmgmt/unittest/conf/insertEspecialidade.sql", false);
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

	public List<Documento> documentoList(PersistanceActionEnum action)
	{
		List<Documento> documentoList = new ArrayList<Documento>();
		Documento documento = new Documento(0, DocumentoTypeEnum.CNPJ, "numero", (long)123456789, new Estado(), action);
		documento.setProcessId(1);
		documentoList.add(documento);

		documento = new Documento(0, DocumentoTypeEnum.IE, "numero", (long)123456789, new Estado(), action);
		documento.setProcessId(1);
		documentoList.add(documento);

		documento = new Documento(0, DocumentoTypeEnum.IM, "numero", (long)123456789, new Estado(), action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

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

	public List<BancoPessoa> bancoList(PersistanceActionEnum action)
	{
		List<BancoPessoa> documentoList = new ArrayList<BancoPessoa>();
		BancoPessoa documento = new BancoPessoa();
		documento.setModelAction(action);
		documento.setBancoId(new Banco(0, "ITAU", action));
		documento.setProcessId(1);
		documento.setSaldo(new Double(1.99));
		documentoList.add(documento);
		return documentoList;

	}

	public List<FormaPgPessoa> formaPagamentoList(PersistanceActionEnum action)
	{
		List<FormaPgPessoa> documentoList = new ArrayList<FormaPgPessoa>();
		FormaPgPessoa documento = new FormaPgPessoa();
		documento.setModelAction(action);
		documento.setFormaPgId(new FormaPg(0, "descricao", 12345678, 123456789, action));
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<CondPagPessoa> condPagList(PersistanceActionEnum action)
	{
		List<CondPagPessoa> documentoList = new ArrayList<CondPagPessoa>();
		CondPagPessoa documento = new CondPagPessoa();
		documento.setModelAction(action);
		documento.setCondPagId(condPagsList(action).get(0));
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<CondPag> condPagsList(PersistanceActionEnum action)
	{
		List<CondPag> documentoList = new ArrayList<CondPag>();
		CondPag documento = new CondPag(0, "nome", 11111111, 2222222, 2, tipoPagList(action), action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<TipoPag> tipoPagList(PersistanceActionEnum action)
	{
		List<TipoPag> documentoList = new ArrayList<TipoPag>();
		TipoPag documento = new TipoPag(0, "descricao", action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<Contato> contatoList(PersistanceActionEnum action)
	{
		List<Contato> documentoList = new ArrayList<Contato>();
		List<ContatoItens> contatoItensList = new ArrayList<ContatoItens>();
		contatoItensList.add(new ContatoItens(0, (long)123456789, "texto", "titulo", action));
		contatoItensList.add(new ContatoItens(0, (long)123456789, "texto", "titulo", action));
		Contato documento =
				new Contato(0, 123456789, "nome", "email", "telefone", ContatoTypeEnum.COBRANCA, contatoItensList,
						action);

		documentoList.add(documento);
		return documentoList;

	}

	public List<Salario> salarioList(PersistanceActionEnum action)
	{
		List<Salario> salarioList = new ArrayList<Salario>();
		Salario salario = new Salario();

		salario.setId(0);
		salario.setProfissao(profissaoList(action).get(0));
		salario.setData((long)123456789);
		salario.setValor(1.99);
		salario.setProcessId(1);
		salario.setModelAction(action);

		salarioList.add(salario);
		return salarioList;

	}

	public List<HorarioFunc> horarioList(PersistanceActionEnum action)
	{
		List<HorarioFunc> documentoList = new ArrayList<HorarioFunc>();
		HorarioFunc documento = new HorarioFunc();
		documento.setHorarioEntr((long)123456789);
		documento.setModelAction(action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<BeneficioPessoa> beneficioList(PersistanceActionEnum action)
	{
		List<BeneficioPessoa> documentoList = new ArrayList<BeneficioPessoa>();
		BeneficioPessoa documento = new BeneficioPessoa();
		documento.setModelAction(action);
		documento.setBenefId(new Beneficios(0, "nome", "codigo", "descricao", 1.98, 10, "tipo", action));
		documento.setProcessId(1);
		documento.setIdFunc(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<EventoPessoa> eventosList(PersistanceActionEnum action)
	{
		List<EventoPessoa> documentoList = new ArrayList<EventoPessoa>();
		List<EventoMesApp> eventoMesAppList = new ArrayList<EventoMesApp>();
		EventoMesApp eventoMesApp = new EventoMesApp();
		for (Integer i = 0; i < 5; i++)
		{
			eventoMesApp = new EventoMesApp();
			eventoMesApp.setId(i);
			eventoMesApp.setData((long)1457547231);
			eventoMesAppList.add(eventoMesApp);

		}
		EventoPessoa documento = new EventoPessoa();
		documento.setModelAction(action);
		documento.setProcessId(1);
		documento.setIdEvent(new Eventos(0, "nome", eventoMesAppList, "descricao", "codigo", "tipo",
				0.99, (double)10, true, true, "noteText", action));

		documentoList.add(documento);
		return documentoList;

	}

	public List<Profissao> profissaoList(PersistanceActionEnum action)
	{
		List<Profissao> documentoList = new ArrayList<Profissao>();
		Profissao documento = new Profissao();
		documento.setModelAction(action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<ConvenioPessoa> convenioList(PersistanceActionEnum action)
	{
		List<ConvenioPessoa> documentoList = new ArrayList<ConvenioPessoa>();
		ConvenioPessoa documento = new ConvenioPessoa();
		documento.setModelAction(action);
		documento.setProcessId(1);
		documento.setConvId(new Convenio(0, "nome", 12334567, 1234567, 0.55, 1.99,
				condPagsList(action), tipoPagList(action), action));

		documentoList.add(documento);
		return documentoList;

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

	public List<ProdutoPessoa> produtoList(PersistanceActionEnum action)
	{
		List<ProdutoPessoa> documentoList = new ArrayList<ProdutoPessoa>();
		ProdutoPessoa documento = new ProdutoPessoa();
		documento.setModelAction(action);
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public List<Note> noteList(PersistanceActionEnum action)
	{
		List<Note> documentoList = new ArrayList<Note>();
		Note documento = new Note();
		documento.setModelAction(action);
		documento.setEmprId(1);
		documento.setNoteText("Test NOte");
		documento.setProcessId(1);
		documentoList.add(documento);
		return documentoList;

	}

	public Funcionario insertFuncionario(PersistanceActionEnum action)
	{
		Funcionario pessoa = new Funcionario();
		Date a = new Date();
		pessoa.setId(1);
		pessoa.setMatricula("matricula");
		pessoa.setDataAdm(a.getTime());
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));
		pessoa.setBeneficios(beneficioList(action));
		pessoa.setProcessId(1);

		return pessoa;
	}

	public Cliente insertCliente(PersistanceActionEnum action)
	{
		Cliente pessoa = new Cliente();
		Date a = new Date();
		pessoa.setId(1);
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));
		pessoa.setProcessId(1);

		return pessoa;
	}

	public Fornecedor insertFornecedor(PersistanceActionEnum action)
	{
		Fornecedor pessoa = new Fornecedor();
		pessoa.setId(1);
		Date a = new Date();
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));
		pessoa.setProcessId(1);

		return pessoa;
	}

	public Transportador insertTransportador(PersistanceActionEnum action)
	{
		Transportador pessoa = new Transportador();
		Date a = new Date();
		pessoa.setId(1);
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));
		pessoa.setProcessId(1);

		return pessoa;
	}

	public Contador insertContador(PersistanceActionEnum action)
	{
		Contador pessoa = new Contador();
		Date a = new Date();
		pessoa.setId(1);
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));
		pessoa.setProcessId(1);

		return pessoa;
	}

	public Medico insertMedico(PersistanceActionEnum action)
	{
		Medico pessoa = new Medico();
		Date a = new Date();
		pessoa.setId(1);
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));
		pessoa.setProcessId(1);

		return pessoa;
	}

	public Paciente insertPaciente(PersistanceActionEnum action)
	{
		Paciente pessoa = new Paciente();
		Date a = new Date();
		pessoa.setId(1);
		pessoa.setNome("TESTE0001");
		pessoa.setNomePai("nomePai");
		pessoa.setEmprId(9);
		pessoa.setNomeMae("nomeMae");
		pessoa.setNomeConjugue("nomeConjugue");
		pessoa.setEstadoCivil(0);
		pessoa.setTipoPessoa(1);
		pessoa.setDatanasc(a.getTime());
		pessoa.setFoto("foto");
		pessoa.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
		pessoa.setSexo(1);
		pessoa.setModelAction(action);
		pessoa.setDocumentos(documentoList(action));
		pessoa.setTelefones(telefoneList(action));
		pessoa.setEmails(emailList(action));
		pessoa.setEnderecos(enderecoList(action));
		pessoa.setBancos(bancoList(action));
		pessoa.setNotes(noteList(action));
		pessoa.setContatoList(contatoList(action));
		pessoa.setFormaPagamentoList(formaPagamentoList(action));
		pessoa.setCondPagList(condPagList(action));
		pessoa.setProcessId(1);

		return pessoa;
	}
}
