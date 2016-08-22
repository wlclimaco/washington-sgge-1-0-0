/** create by system gera-java version 1.0.0 03/05/2016 12:25 : 26*/
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
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.banco.model.Banco;
import com.qat.samples.sysmgmt.banco.model.BancoPessoa;
import com.qat.samples.sysmgmt.bar.Pessoa.IPessoaBAR;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condpag.model.CondPag;
import com.qat.samples.sysmgmt.condpag.model.CondPagPessoa;
import com.qat.samples.sysmgmt.condpag.model.FormaPg;
import com.qat.samples.sysmgmt.condpag.model.FormaPgPessoa;
import com.qat.samples.sysmgmt.contato.model.Contato;
import com.qat.samples.sysmgmt.contato.model.ContatoItens;
import com.qat.samples.sysmgmt.contato.model.ContatoStatusEnum;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.pessoa.model.PessoaTypeEnum;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.Email;
import com.qat.samples.sysmgmt.util.model.Endereco;
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
import com.qat.samples.sysmgmt.util.model.Telefone;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/bartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class PessoaBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(PessoaBARTest.class);
private IPessoaBAR pessoaBAR; // injected by Spring through @Resource

@Resource
public void setPessoaBAR(IPessoaBAR pessoaBAR)
{
	this.pessoaBAR = pessoaBAR;
}

public IPessoaBAR getPessoaBAR()
{
	return pessoaBAR;
}


//===================================### ADVOGADO ####======================================


@Test
	public void testDeleteAdvogado()
	{
		Advogado advogado = insertAdvogado(1035, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1035);
		Advogado advogadoResponse = getPessoaBAR().fetchAdvogadoById(request);
		Assert.assertEquals(advogadoResponse, null);
		getPessoaBAR().insertAdvogado(advogado);
		advogadoResponse = getPessoaBAR().fetchAdvogadoById(request);
		Assert.assertEquals(advogado.getId(), advogadoResponse.getId());
		getPessoaBAR().deleteAdvogadoById(advogado);
		advogadoResponse = getPessoaBAR().fetchAdvogadoById(request);
		Assert.assertEquals(advogadoResponse, null);
	}

	@Test
	public void testFetchAllAdvogados()
	{
	Advogado advogado = insertAdvogado(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		List<Advogado> response = getPessoaBAR().fetchAllAdvogados(advogado).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllAdvogados()
	{
		getPessoaBAR().deleteAllAdvogados();
		Advogado advogado = insertAdvogado(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		List<Advogado> response = getPessoaBAR().fetchAllAdvogados(new Advogado()).getResultsList();
		Assert.assertEquals(response.size(), 3);
	}

	@Test
	public void testUpdateAdvogado()
	{
		Advogado advogado = insertAdvogado(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Advogado advogadoResponse = getPessoaBAR().fetchAdvogadoById(request);
		Assert.assertEquals(advogadoResponse.getNome(), "nome_1");
		getPessoaBAR().updateAdvogado(advogado);
		advogadoResponse = getPessoaBAR().fetchAdvogadoById(request);
		Assert.assertEquals(advogadoResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchAdvogadosByRequest() throws Exception
	{
		// check for valid and precount
		AdvogadoInquiryRequest request = new AdvogadoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Advogado> response = getPessoaBAR().fetchAdvogadosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchAdvogadosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		AdvogadoInquiryRequest request2 = new AdvogadoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Advogado> response2 = getPessoaBAR().fetchAdvogadosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllAdvogados();
		AdvogadoInquiryRequest request3 = new AdvogadoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Advogado> response3 = getPessoaBAR().fetchAdvogadosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### CLIENTE ####======================================


@Test
	public void testDeleteCliente()
	{
		Cliente cliente = insertCliente(1040, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1040);
		Cliente clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(clienteResponse, null);
		getPessoaBAR().insertCliente(cliente);
		clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(cliente.getNome(), clienteResponse.getNome());
		getPessoaBAR().deleteClienteById(cliente);
		clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(clienteResponse, null);
	}

	@Test
	public void testFetchAllClientes()
	{
	Cliente cliente = insertCliente(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		List<Cliente> response = getPessoaBAR().fetchAllClientes(cliente).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllClientes()
	{
		getPessoaBAR().deleteAllClientes();
	Cliente cliente = insertCliente(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		List<Cliente> response = getPessoaBAR().fetchAllClientes(new Cliente()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCliente()
	{
		Cliente cliente = insertCliente(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Cliente clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(clienteResponse.getNome(), "nome_1");
		getPessoaBAR().updateCliente(cliente);
		clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(clienteResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchClientesByRequest() throws Exception
	{
		// check for valid and precount
		ClienteInquiryRequest request = new ClienteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Cliente> response = getPessoaBAR().fetchClientesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchClientesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		ClienteInquiryRequest request2 = new ClienteInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Cliente> response2 = getPessoaBAR().fetchClientesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllClientes();
		ClienteInquiryRequest request3 = new ClienteInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Cliente> response3 = getPessoaBAR().fetchClientesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### FORNECEDOR ####======================================


@Test
	public void testDeleteFornecedor()
	{
		Fornecedor fornecedor = insertFornecedor(1040, TabelaEnum.FORNECEDOR,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1040);
		Fornecedor fornecedorResponse = getPessoaBAR().fetchFornecedorById(request);
		Assert.assertEquals(fornecedorResponse, null);
		getPessoaBAR().insertFornecedor(fornecedor);
		fornecedorResponse = getPessoaBAR().fetchFornecedorById(request);
		Assert.assertEquals(fornecedor.getId(), fornecedorResponse.getId());
		getPessoaBAR().deleteFornecedorById(fornecedor);
		fornecedorResponse = getPessoaBAR().fetchFornecedorById(request);
		Assert.assertEquals(fornecedorResponse, null);
	}

	@Test
	public void testFetchAllFornecedors()
	{
	Fornecedor fornecedor = insertFornecedor(1001, TabelaEnum.FORNECEDOR,PersistenceActionEnum.INSERT);
		List<Fornecedor> response = getPessoaBAR().fetchAllFornecedors(fornecedor).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllFornecedors()
	{
		getPessoaBAR().deleteAllFornecedors();
	Fornecedor fornecedor = new Fornecedor();
		List<Fornecedor> response = getPessoaBAR().fetchAllFornecedors(new Fornecedor()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateFornecedor()
	{
		Fornecedor fornecedor = insertFornecedor(1001, TabelaEnum.FORNECEDOR,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Fornecedor fornecedorResponse = getPessoaBAR().fetchFornecedorById(request);
		Assert.assertEquals(fornecedorResponse.getNome(), "nome_1");
		getPessoaBAR().updateFornecedor(fornecedor);
		fornecedorResponse = getPessoaBAR().fetchFornecedorById(request);
		Assert.assertEquals(fornecedorResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchFornecedorsByRequest() throws Exception
	{
		// check for valid and precount
		FornecedorInquiryRequest request = new FornecedorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Fornecedor> response = getPessoaBAR().fetchFornecedorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchFornecedorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		FornecedorInquiryRequest request2 = new FornecedorInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Fornecedor> response2 = getPessoaBAR().fetchFornecedorsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllFornecedors();
		FornecedorInquiryRequest request3 = new FornecedorInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Fornecedor> response3 = getPessoaBAR().fetchFornecedorsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### TRANSPORTADOR ####======================================


@Test
	public void testDeleteTransportador()
	{
		Transportador transportador = insertTransportador(1060, TabelaEnum.TRANSPORTADOR,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1060);
		Transportador transportadorResponse = getPessoaBAR().fetchTransportadorById(request);
		Assert.assertEquals(transportadorResponse, null);
		getPessoaBAR().insertTransportador(transportador);
		transportadorResponse = getPessoaBAR().fetchTransportadorById(request);
		Assert.assertEquals(transportador.getId(), transportadorResponse.getId());
		getPessoaBAR().deleteTransportadorById(transportador);
		transportadorResponse = getPessoaBAR().fetchTransportadorById(request);
		Assert.assertEquals(transportadorResponse, null);
	}

	@Test
	public void testFetchAllTransportadors()
	{
	Transportador transportador = insertTransportador(1001, TabelaEnum.TRANSPORTADOR,PersistenceActionEnum.INSERT);
		List<Transportador> response = getPessoaBAR().fetchAllTransportadors(transportador).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllTransportadors()
	{
		getPessoaBAR().deleteAllTransportadors();
	Transportador transportador = insertTransportador(1001, TabelaEnum.TRANSPORTADOR,PersistenceActionEnum.INSERT);
		List<Transportador> response = getPessoaBAR().fetchAllTransportadors(new Transportador()).getResultsList();
		Assert.assertEquals(response.size(), 3);
	}

	@Test
	public void testUpdateTransportador()
	{
		Transportador transportador = insertTransportador(1001, TabelaEnum.TRANSPORTADOR,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Transportador transportadorResponse = getPessoaBAR().fetchTransportadorById(request);
		Assert.assertEquals(transportadorResponse.getNome(), "nome_1");
		getPessoaBAR().updateTransportador(transportador);
		transportadorResponse = getPessoaBAR().fetchTransportadorById(request);
		Assert.assertEquals(transportadorResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchTransportadorsByRequest() throws Exception
	{
		// check for valid and precount
		TransportadorInquiryRequest request = new TransportadorInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Transportador> response = getPessoaBAR().fetchTransportadorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchTransportadorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		TransportadorInquiryRequest request2 = new TransportadorInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Transportador> response2 = getPessoaBAR().fetchTransportadorsByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllTransportadors();
		TransportadorInquiryRequest request3 = new TransportadorInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Transportador> response3 = getPessoaBAR().fetchTransportadorsByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### MEDICO ####======================================


@Test
	public void testDeleteMedico()
	{
		Medico medico = insertMedico(1045, TabelaEnum.MEDICO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1045);
		Medico medicoResponse = getPessoaBAR().fetchMedicoById(request);
		Assert.assertEquals(medicoResponse, null);
		getPessoaBAR().insertMedico(medico);
		medicoResponse = getPessoaBAR().fetchMedicoById(request);
		Assert.assertEquals(medico.getId(), medicoResponse.getId());
		getPessoaBAR().deleteMedicoById(medico);
		medicoResponse = getPessoaBAR().fetchMedicoById(request);
		Assert.assertEquals(medicoResponse, null);
	}

	@Test
	public void testFetchAllMedicos()
	{
	Medico medico = insertMedico(1001, TabelaEnum.MEDICO,PersistenceActionEnum.INSERT);
		List<Medico> response = getPessoaBAR().fetchAllMedicos(medico).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllMedicos()
	{
		getPessoaBAR().deleteAllMedicos();
	Medico medico = insertMedico(1001, TabelaEnum.MEDICO,PersistenceActionEnum.INSERT);
		List<Medico> response = getPessoaBAR().fetchAllMedicos(new Medico()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateMedico()
	{
		Medico medico = insertMedico(1001, TabelaEnum.MEDICO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Medico medicoResponse = getPessoaBAR().fetchMedicoById(request);
		Assert.assertEquals(medicoResponse.getNome(), "nome_1");
		getPessoaBAR().updateMedico(medico);
		medicoResponse = getPessoaBAR().fetchMedicoById(request);
		Assert.assertEquals(medicoResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchMedicosByRequest() throws Exception
	{
		// check for valid and precount
		MedicoInquiryRequest request = new MedicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Medico> response = getPessoaBAR().fetchMedicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchMedicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		MedicoInquiryRequest request2 = new MedicoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Medico> response2 = getPessoaBAR().fetchMedicosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllMedicos();
		MedicoInquiryRequest request3 = new MedicoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Medico> response3 = getPessoaBAR().fetchMedicosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### PACIENTE ####======================================


@Test
	public void testDeletePaciente()
	{
		Paciente paciente = insertPaciente(1060, TabelaEnum.PACIENTE,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1060);
		Paciente pacienteResponse = getPessoaBAR().fetchPacienteById(request);
		Assert.assertEquals(pacienteResponse, null);
		getPessoaBAR().insertPaciente(paciente);
		pacienteResponse = getPessoaBAR().fetchPacienteById(request);
		Assert.assertEquals(paciente.getId(), pacienteResponse.getId());
		getPessoaBAR().deletePacienteById(paciente);
		pacienteResponse = getPessoaBAR().fetchPacienteById(request);
		Assert.assertEquals(pacienteResponse, null);
	}

	@Test
	public void testFetchAllPacientes()
	{
	Paciente paciente = insertPaciente(1001, TabelaEnum.PACIENTE,PersistenceActionEnum.INSERT);
		List<Paciente> response = getPessoaBAR().fetchAllPacientes(paciente).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllPacientes()
	{
		getPessoaBAR().deleteAllPacientes();
	Paciente paciente = insertPaciente(1001, TabelaEnum.PACIENTE,PersistenceActionEnum.INSERT);
		List<Paciente> response = getPessoaBAR().fetchAllPacientes(new Paciente()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdatePaciente()
	{
		Paciente paciente = insertPaciente(1001, TabelaEnum.PACIENTE,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Paciente pacienteResponse = getPessoaBAR().fetchPacienteById(request);
		Assert.assertEquals(pacienteResponse.getNome(), "nome_1");
		getPessoaBAR().updatePaciente(paciente);
		pacienteResponse = getPessoaBAR().fetchPacienteById(request);
		Assert.assertEquals(pacienteResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchPacientesByRequest() throws Exception
	{
		// check for valid and precount
		PacienteInquiryRequest request = new PacienteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Paciente> response = getPessoaBAR().fetchPacientesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchPacientesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PacienteInquiryRequest request2 = new PacienteInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Paciente> response2 = getPessoaBAR().fetchPacientesByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllPacientes();
		PacienteInquiryRequest request3 = new PacienteInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Paciente> response3 = getPessoaBAR().fetchPacientesByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### SINDICO ####======================================


@Test
	public void testDeleteSindico()
	{
		Sindico sindico = insertSindico(1040, TabelaEnum.SINDICO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1040);
		Sindico sindicoResponse = getPessoaBAR().fetchSindicoById(request);
		Assert.assertEquals(sindicoResponse, null);
		getPessoaBAR().insertSindico(sindico);
		sindicoResponse = getPessoaBAR().fetchSindicoById(request);
		Assert.assertEquals(sindico.getId(), sindicoResponse.getId());
		getPessoaBAR().deleteSindicoById(sindico);
		sindicoResponse = getPessoaBAR().fetchSindicoById(request);
		Assert.assertEquals(sindicoResponse, null);
	}

	@Test
	public void testFetchAllSindicos()
	{
	Sindico sindico = insertSindico(1001, TabelaEnum.SINDICO,PersistenceActionEnum.INSERT);
		List<Sindico> response = getPessoaBAR().fetchAllSindicos(sindico).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllSindicos()
	{
		getPessoaBAR().deleteAllSindicos();
	Sindico sindico = insertSindico(1001, TabelaEnum.SINDICO,PersistenceActionEnum.INSERT);
		List<Sindico> response = getPessoaBAR().fetchAllSindicos(new Sindico()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateSindico()
	{
		Sindico sindico = insertSindico(1001, TabelaEnum.SINDICO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Sindico sindicoResponse = getPessoaBAR().fetchSindicoById(request);
		Assert.assertEquals(sindicoResponse.getNome(), "nome_1");
		getPessoaBAR().updateSindico(sindico);
		sindicoResponse = getPessoaBAR().fetchSindicoById(request);
		Assert.assertEquals(sindicoResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchSindicosByRequest() throws Exception
	{
		// check for valid and precount
		SindicoInquiryRequest request = new SindicoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Sindico> response = getPessoaBAR().fetchSindicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchSindicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		SindicoInquiryRequest request2 = new SindicoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Sindico> response2 = getPessoaBAR().fetchSindicosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllSindicos();
		SindicoInquiryRequest request3 = new SindicoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Sindico> response3 = getPessoaBAR().fetchSindicosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### INQUILINO ####======================================


@Test
	public void testDeleteInquilino()
	{
		Inquilino inquilino = insertInquilino(1040, TabelaEnum.INQUILINO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1040);
		Inquilino inquilinoResponse = getPessoaBAR().fetchInquilinoById(request);
		Assert.assertEquals(inquilinoResponse, null);
		getPessoaBAR().insertInquilino(inquilino);
		inquilinoResponse = getPessoaBAR().fetchInquilinoById(request);
		Assert.assertEquals(inquilino.getId(), inquilinoResponse.getId());
		getPessoaBAR().deleteInquilinoById(inquilino);
		inquilinoResponse = getPessoaBAR().fetchInquilinoById(request);
		Assert.assertEquals(inquilinoResponse, null);
	}

	@Test
	public void testFetchAllInquilinos()
	{
	Inquilino inquilino = insertInquilino(1035, TabelaEnum.INQUILINO,PersistenceActionEnum.INSERT);
		List<Inquilino> response = getPessoaBAR().fetchAllInquilinos(inquilino).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllInquilinos()
	{
		getPessoaBAR().deleteAllInquilinos();
	Inquilino inquilino = insertInquilino(1035, TabelaEnum.INQUILINO,PersistenceActionEnum.INSERT);
		List<Inquilino> response = getPessoaBAR().fetchAllInquilinos(new Inquilino()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateInquilino()
	{
		Inquilino inquilino = insertInquilino(1001, TabelaEnum.INQUILINO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Inquilino inquilinoResponse = getPessoaBAR().fetchInquilinoById(request);
		Assert.assertEquals(inquilinoResponse.getNome(), "nome_1");
		getPessoaBAR().updateInquilino(inquilino);
		inquilinoResponse = getPessoaBAR().fetchInquilinoById(request);
		Assert.assertEquals(inquilinoResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchInquilinosByRequest() throws Exception
	{
		// check for valid and precount
		InquilinoInquiryRequest request = new InquilinoInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Inquilino> response = getPessoaBAR().fetchInquilinosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchInquilinosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		InquilinoInquiryRequest request2 = new InquilinoInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Inquilino> response2 = getPessoaBAR().fetchInquilinosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllInquilinos();
		InquilinoInquiryRequest request3 = new InquilinoInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Inquilino> response3 = getPessoaBAR().fetchInquilinosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

//===================================### FUNCIONARIO ####======================================


@Test
	public void testDeleteFuncionario()
	{
		Funcionario funcionario = insertFuncionario(1035, TabelaEnum.FUNCIONARIO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1035);
		Funcionario funcionarioResponse = getPessoaBAR().fetchFuncionarioById(request);
		Assert.assertEquals(funcionarioResponse, null);
		getPessoaBAR().insertFuncionario(funcionario);
		funcionarioResponse = getPessoaBAR().fetchFuncionarioById(request);
		Assert.assertEquals(funcionario.getId(), funcionarioResponse.getId());
		getPessoaBAR().deleteFuncionarioById(funcionario);
		funcionarioResponse = getPessoaBAR().fetchFuncionarioById(request);
		Assert.assertEquals(funcionarioResponse, null);
	}

	@Test
	public void testFetchAllFuncionarios()
	{
	Funcionario funcionario =insertFuncionario(1035, TabelaEnum.FUNCIONARIO,PersistenceActionEnum.INSERT);
		List<Funcionario> response = getPessoaBAR().fetchAllFuncionarios(funcionario).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllFuncionarios()
	{
		getPessoaBAR().deleteAllFuncionarios();
	Funcionario funcionario = insertFuncionario(1035, TabelaEnum.FUNCIONARIO,PersistenceActionEnum.INSERT);
		List<Funcionario> response = getPessoaBAR().fetchAllFuncionarios(new Funcionario()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateFuncionario()
	{
		Funcionario funcionario = insertFuncionario(1001, TabelaEnum.FUNCIONARIO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Funcionario funcionarioResponse = getPessoaBAR().fetchFuncionarioById(request);
		Assert.assertEquals(funcionarioResponse.getNome(), "nome_1");
		getPessoaBAR().updateFuncionario(funcionario);
		funcionarioResponse = getPessoaBAR().fetchFuncionarioById(request);
		Assert.assertEquals(funcionarioResponse.getNome(), "NATIVE INSERT UPDATE");
	}

	@Test
	public void testFetchFuncionariosByRequest() throws Exception
	{
		// check for valid and precount
		FuncionarioInquiryRequest request = new FuncionarioInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Funcionario> response = getPessoaBAR().fetchFuncionariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchFuncionariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		FuncionarioInquiryRequest request2 = new FuncionarioInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Funcionario> response2 = getPessoaBAR().fetchFuncionariosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getPessoaBAR().deleteAllFuncionarios();
		FuncionarioInquiryRequest request3 = new FuncionarioInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Funcionario> response3 = getPessoaBAR().fetchFuncionariosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertAdvogado.sql", false);
		executeSqlScript("conf/insertCliente.sql", false);
		executeSqlScript("conf/insertFornecedor.sql", false);
		executeSqlScript("conf/insertTransportador.sql", false);
		executeSqlScript("conf/insertMedico.sql", false);
		executeSqlScript("conf/insertPaciente.sql", false);
		executeSqlScript("conf/insertSindico.sql", false);
		executeSqlScript("conf/insertInquilino.sql", false);
		executeSqlScript("conf/insertFuncionario.sql", false);
	}


	public Advogado insertAdvogado(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Advogado advogado = new Advogado();
			Date a = new Date();
			advogado.setId(id);
			advogado.setNome("NATIVE INSERT UPDATE");
			advogado.setNomePai("NATIVE INSERT UPDATE");
			advogado.setNomeMae("NATIVE INSERT UPDATE");
			advogado.setNomeConjugue("NATIVE INSERT UPDATE");
			advogado.setEstadoCivil(1);
			advogado.setTipoPessoa(1);
			advogado.setDatanasc(a.getTime());
			advogado.setFoto("NATIVE INSERT UPDATE");
			advogado.setPessoaTypeEnum(PessoaTypeEnum.ADVOGADO);
			advogado.setSexo(1);
			advogado.setEnderecos(new ArrayList<Endereco>());
			advogado.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			advogado.setDocumentos(new ArrayList<Documento>());
			advogado.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			advogado.setEmails(new ArrayList<Email>());
			advogado.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			advogado.setTelefones(new ArrayList<Telefone>());
			advogado.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			advogado.setBancos(new ArrayList<BancoPessoa>());
			advogado.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			advogado.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			advogado.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			advogado.setCondPagList(new ArrayList<CondPagPessoa>());
			advogado.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			advogado.setContatoList(new ArrayList<Contato>());
			advogado.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			advogado.setParentId(id);
			advogado.setEmprId(1);
			advogado.setModifyDateUTC(a.getTime());
			advogado.setCreateDateUTC(a.getTime());
			advogado.setCreateUser("system");
			advogado.setModifyUser("system");
			advogado.setProcessId(1);
			advogado.setModelAction(action);

			return advogado;
		}


	public Cliente insertCliente(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Cliente cliente = new Cliente();
			Date a = new Date();
			cliente.setId(id);
			cliente.setNome("NATIVE INSERT UPDATE");
			cliente.setNomePai("NATIVE INSERT UPDATE");
			cliente.setNomeMae("NATIVE INSERT UPDATE");
			cliente.setNomeConjugue("NATIVE INSERT UPDATE");
			cliente.setEstadoCivil(100);
			cliente.setTipoPessoa(100);
			cliente.setDatanasc(a.getTime());
			cliente.setFoto("NATIVE INSERT UPDATE");
			cliente.setPessoaTypeEnum(PessoaTypeEnum.CLIENTE);
			cliente.setSexo(100);
			cliente.setEnderecos(new ArrayList<Endereco>());
			cliente.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			cliente.setDocumentos(new ArrayList<Documento>());
			cliente.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			cliente.setEmails(new ArrayList<Email>());
			cliente.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			cliente.setTelefones(new ArrayList<Telefone>());
			cliente.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			cliente.setBancos(new ArrayList<BancoPessoa>());
			cliente.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			cliente.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			cliente.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			cliente.setCondPagList(new ArrayList<CondPagPessoa>());
			cliente.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			cliente.setContatoList(new ArrayList<Contato>());
			cliente.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			cliente.setParentId(id);
			cliente.setEmprId(1);
			cliente.setModifyDateUTC(a.getTime());
			cliente.setCreateDateUTC(a.getTime());
			cliente.setCreateUser("system");
			cliente.setModifyUser("system");
			cliente.setProcessId(1);
			cliente.setModelAction(action);

			return cliente;
		}


	public Fornecedor insertFornecedor(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Fornecedor fornecedor = new Fornecedor();
			Date a = new Date();
			fornecedor.setId(id);
			fornecedor.setNome("NATIVE INSERT UPDATE");
			fornecedor.setNomePai("NATIVE INSERT UPDATE");
			fornecedor.setNomeMae("NATIVE INSERT UPDATE");
			fornecedor.setNomeConjugue("NATIVE INSERT UPDATE");
			fornecedor.setEstadoCivil(100);
			fornecedor.setTipoPessoa(100);
			fornecedor.setDatanasc(a.getTime());
			fornecedor.setFoto("NATIVE INSERT UPDATE");
			fornecedor.setPessoaTypeEnum(PessoaTypeEnum.FORNECEDOR);
			fornecedor.setSexo(100);
			fornecedor.setEnderecos(new ArrayList<Endereco>());
			fornecedor.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			fornecedor.setDocumentos(new ArrayList<Documento>());
			fornecedor.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			fornecedor.setEmails(new ArrayList<Email>());
			fornecedor.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			fornecedor.setTelefones(new ArrayList<Telefone>());
			fornecedor.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			fornecedor.setBancos(new ArrayList<BancoPessoa>());
			fornecedor.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			fornecedor.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			fornecedor.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			fornecedor.setCondPagList(new ArrayList<CondPagPessoa>());
			fornecedor.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			fornecedor.setContatoList(new ArrayList<Contato>());
			fornecedor.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			fornecedor.setParentId(id);
			fornecedor.setEmprId(1);
			fornecedor.setModifyDateUTC(a.getTime());
			fornecedor.setCreateDateUTC(a.getTime());
			fornecedor.setCreateUser("system");
			fornecedor.setModifyUser("system");
			fornecedor.setProcessId(1);
			fornecedor.setModelAction(action);

			return fornecedor;
		}


	public Transportador insertTransportador(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Transportador transportador = new Transportador();
			Date a = new Date();
			transportador.setId(id);
			transportador.setNome("NATIVE INSERT UPDATE");
			transportador.setNomePai("NATIVE INSERT UPDATE");
			transportador.setNomeMae("NATIVE INSERT UPDATE");
			transportador.setNomeConjugue("NATIVE INSERT UPDATE");
			transportador.setEstadoCivil(100);
			transportador.setTipoPessoa(100);
			transportador.setDatanasc(a.getTime());
			transportador.setFoto("NATIVE INSERT UPDATE");
			transportador.setPessoaTypeEnum(PessoaTypeEnum.TRANSPORTADOR);
			transportador.setSexo(100);
			transportador.setEnderecos(new ArrayList<Endereco>());
			transportador.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			transportador.setDocumentos(new ArrayList<Documento>());
			transportador.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			transportador.setEmails(new ArrayList<Email>());
			transportador.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			transportador.setTelefones(new ArrayList<Telefone>());
			transportador.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			transportador.setBancos(new ArrayList<BancoPessoa>());
			transportador.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			transportador.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			transportador.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			transportador.setCondPagList(new ArrayList<CondPagPessoa>());
			transportador.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			transportador.setContatoList(new ArrayList<Contato>());
			transportador.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			transportador.setParentId(id);
			transportador.setEmprId(1);
			transportador.setModifyDateUTC(a.getTime());
			transportador.setCreateDateUTC(a.getTime());
			transportador.setCreateUser("system");
			transportador.setModifyUser("system");
			transportador.setProcessId(1);
			transportador.setModelAction(action);

			return transportador;
		}


	public Medico insertMedico(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Medico medico = new Medico();
			Date a = new Date();
			medico.setId(id);
			medico.setNome("NATIVE INSERT UPDATE");
			medico.setNomePai("NATIVE INSERT UPDATE");
			medico.setNomeMae("NATIVE INSERT UPDATE");
			medico.setNomeConjugue("NATIVE INSERT UPDATE");
			medico.setEstadoCivil(100);
			medico.setTipoPessoa(100);
			medico.setDatanasc(a.getTime());
			medico.setFoto("NATIVE INSERT UPDATE");
			medico.setPessoaTypeEnum(PessoaTypeEnum.MEDICO);
			medico.setSexo(100);
			medico.setEnderecos(new ArrayList<Endereco>());
			medico.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			medico.setDocumentos(new ArrayList<Documento>());
			medico.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			medico.setEmails(new ArrayList<Email>());
			medico.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			medico.setTelefones(new ArrayList<Telefone>());
			medico.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			medico.setBancos(new ArrayList<BancoPessoa>());
			medico.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			medico.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			medico.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			medico.setCondPagList(new ArrayList<CondPagPessoa>());
			medico.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			medico.setContatoList(new ArrayList<Contato>());
			medico.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			medico.setParentId(id);
			medico.setEmprId(1);
			medico.setModifyDateUTC(a.getTime());
			medico.setCreateDateUTC(a.getTime());
			medico.setCreateUser("system");
			medico.setModifyUser("system");
			medico.setProcessId(1);
			medico.setModelAction(action);

			return medico;
		}


	public Paciente insertPaciente(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Paciente paciente = new Paciente();
			Date a = new Date();
			paciente.setId(id);
			paciente.setNome("NATIVE INSERT UPDATE");
			paciente.setNomePai("NATIVE INSERT UPDATE");
			paciente.setNomeMae("NATIVE INSERT UPDATE");
			paciente.setNomeConjugue("NATIVE INSERT UPDATE");
			paciente.setEstadoCivil(100);
			paciente.setTipoPessoa(100);
			paciente.setDatanasc(a.getTime());
			paciente.setFoto("NATIVE INSERT UPDATE");
			paciente.setPessoaTypeEnum(PessoaTypeEnum.PACIENTE);
			paciente.setSexo(100);
			paciente.setEnderecos(new ArrayList<Endereco>());
			paciente.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			paciente.setDocumentos(new ArrayList<Documento>());
			paciente.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			paciente.setEmails(new ArrayList<Email>());
			paciente.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			paciente.setTelefones(new ArrayList<Telefone>());
			paciente.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			paciente.setBancos(new ArrayList<BancoPessoa>());
			paciente.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			paciente.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			paciente.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			paciente.setCondPagList(new ArrayList<CondPagPessoa>());
			paciente.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			paciente.setContatoList(new ArrayList<Contato>());
			paciente.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			paciente.setParentId(id);
			paciente.setEmprId(1);
			paciente.setModifyDateUTC(a.getTime());
			paciente.setCreateDateUTC(a.getTime());
			paciente.setCreateUser("system");
			paciente.setModifyUser("system");
			paciente.setProcessId(1);
			paciente.setModelAction(action);

			return paciente;
		}


	public Sindico insertSindico(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Sindico sindico = new Sindico();
			Date a = new Date();
			sindico.setId(id);
			sindico.setNome("NATIVE INSERT UPDATE");
			sindico.setNomePai("NATIVE INSERT UPDATE");
			sindico.setNomeMae("NATIVE INSERT UPDATE");
			sindico.setNomeConjugue("NATIVE INSERT UPDATE");
			sindico.setEstadoCivil(100);
			sindico.setTipoPessoa(100);
			sindico.setDatanasc(a.getTime());
			sindico.setFoto("NATIVE INSERT UPDATE");
			sindico.setPessoaTypeEnum(PessoaTypeEnum.SINDICO);
			sindico.setSexo(100);
			sindico.setEnderecos(new ArrayList<Endereco>());
			sindico.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			sindico.setDocumentos(new ArrayList<Documento>());
			sindico.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			sindico.setEmails(new ArrayList<Email>());
			sindico.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			sindico.setTelefones(new ArrayList<Telefone>());
			sindico.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			sindico.setBancos(new ArrayList<BancoPessoa>());
			sindico.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			sindico.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			sindico.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			sindico.setCondPagList(new ArrayList<CondPagPessoa>());
			sindico.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			sindico.setContatoList(new ArrayList<Contato>());
			sindico.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			sindico.setParentId(id);
			sindico.setEmprId(1);
			sindico.setModifyDateUTC(a.getTime());
			sindico.setCreateDateUTC(a.getTime());
			sindico.setCreateUser("system");
			sindico.setModifyUser("system");
			sindico.setProcessId(1);
			sindico.setModelAction(action);

			return sindico;
		}


	public Inquilino insertInquilino(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Inquilino inquilino = new Inquilino();
			Date a = new Date();
			inquilino.setId(id);
			inquilino.setNome("NATIVE INSERT UPDATE");
			inquilino.setNomePai("NATIVE INSERT UPDATE");
			inquilino.setNomeMae("NATIVE INSERT UPDATE");
			inquilino.setNomeConjugue("NATIVE INSERT UPDATE");
			inquilino.setEstadoCivil(100);
			inquilino.setTipoPessoa(100);
			inquilino.setDatanasc(a.getTime());
			inquilino.setFoto("NATIVE INSERT UPDATE");
			inquilino.setPessoaTypeEnum(PessoaTypeEnum.INQUILINO);
			inquilino.setSexo(100);
			inquilino.setEnderecos(new ArrayList<Endereco>());
			inquilino.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			inquilino.setDocumentos(new ArrayList<Documento>());
			inquilino.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			inquilino.setEmails(new ArrayList<Email>());
			inquilino.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			inquilino.setTelefones(new ArrayList<Telefone>());
			inquilino.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			inquilino.setBancos(new ArrayList<BancoPessoa>());
			inquilino.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			inquilino.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			inquilino.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			inquilino.setCondPagList(new ArrayList<CondPagPessoa>());
			inquilino.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			inquilino.setContatoList(new ArrayList<Contato>());
			inquilino.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			inquilino.setParentId(id);
			inquilino.setEmprId(1);
			inquilino.setModifyDateUTC(a.getTime());
			inquilino.setCreateDateUTC(a.getTime());
			inquilino.setCreateUser("system");
			inquilino.setModifyUser("system");
			inquilino.setProcessId(1);
			inquilino.setModelAction(action);

			return inquilino;
		}


	public Funcionario insertFuncionario(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Funcionario funcionario = new Funcionario();
			Date a = new Date();
			funcionario.setId(id);
			funcionario.setNome("NATIVE INSERT UPDATE");
			funcionario.setNomePai("NATIVE INSERT UPDATE");
			funcionario.setNomeMae("NATIVE INSERT UPDATE");
			funcionario.setNomeConjugue("NATIVE INSERT UPDATE");
			funcionario.setEstadoCivil(100);
			funcionario.setTipoPessoa(100);
			funcionario.setDatanasc(a.getTime());
			funcionario.setFoto("NATIVE INSERT UPDATE");
			funcionario.setPessoaTypeEnum(PessoaTypeEnum.FUNCIONARIO);
			funcionario.setSexo(100);
			funcionario.setEnderecos(new ArrayList<Endereco>());
			funcionario.getEnderecos().add(insertEndereco(id,TabelaEnum.ADVOCACIA,action));
			funcionario.setDocumentos(new ArrayList<Documento>());
			funcionario.getDocumentos().add(insertDocumento(id,TabelaEnum.ADVOCACIA,action));
			funcionario.setEmails(new ArrayList<Email>());
			funcionario.getEmails().add(insertEmail(id,TabelaEnum.ADVOCACIA,action));
			funcionario.setTelefones(new ArrayList<Telefone>());
			funcionario.getTelefones().add(insertTelefone(id,TabelaEnum.ADVOCACIA,action));
			funcionario.setBancos(new ArrayList<BancoPessoa>());
			funcionario.getBancos().add(insertBanco(id,TabelaEnum.ADVOCACIA,action));
			funcionario.setFormaPagamentoList(new ArrayList<FormaPgPessoa>());
			funcionario.getFormaPagamentoList().add(insertFormaPg(id,TabelaEnum.ADVOCACIA,action));
			funcionario.setCondPagList(new ArrayList<CondPagPessoa>());
			funcionario.getCondPagList().add(insertCondPag(id,TabelaEnum.ADVOCACIA,action));
			funcionario.setContatoList(new ArrayList<Contato>());
			funcionario.getContatoList().add(insertContato(id,TabelaEnum.ADVOCACIA,action));
			funcionario.setParentId(id);
			funcionario.setEmprId(1);
			funcionario.setModifyDateUTC(a.getTime());
			funcionario.setCreateDateUTC(a.getTime());
			funcionario.setCreateUser("system");
			funcionario.setModifyUser("system");
			funcionario.setProcessId(1);
			funcionario.setModelAction(action);

			return funcionario;
		}


		public BancoPessoa insertBanco(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			BancoPessoa banco = new BancoPessoa();
			Date a = new Date();
			banco.setId(100);
			banco.setBancoId(new Banco(1));
			banco.setParentId(id);
			banco.setEmprId(1);
			banco.setModifyDateUTC(a.getTime());
			banco.setCreateDateUTC(a.getTime());
			banco.setCreateUser("system");
			banco.setModifyUser("system");
			banco.setProcessId(1);
			banco.setModelAction(action);

			return banco;
		}

		public Endereco insertEndereco(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Endereco endereco = new Endereco();
			Date a = new Date();
			endereco.setId(100);
			endereco.setCodIbge("10");
			endereco.setLogradouro("NATIVE INSERT UPDATE");
			endereco.setBairro("NATIVE INSERT UPDATE");
			endereco.setNumero("NATIVE INSERT UPDATE");
			endereco.setEnderecoTypeValue(100);
			endereco.setCep("NATIVE INSERT UPDATE");
			endereco.setLatitude(new Double(10.99));
			endereco.setLongitude(new Double(10.99));
			endereco.setComplemento("NATIVE INSERT UPDATE");
			endereco.setCidade(new Cidade());
			endereco.setParentId(id);
			endereco.setEmprId(1);
			endereco.setModifyDateUTC(a.getTime());
			endereco.setCreateDateUTC(a.getTime());
			endereco.setCreateUser("system");
			endereco.setModifyUser("system");
			endereco.setProcessId(1);
			endereco.setModelAction(action);

			return endereco;
		}

		public Documento insertDocumento(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Documento documento = new Documento();
			Date a = new Date();
			documento.setId(100);
			documento.setDocumentoTypeEnumValue(100);
			documento.setNumero("NATIVE INSERT UPDATE");
			documento.setData(a.getTime());
			documento.setParentId(id);
			documento.setEmprId(1);
			documento.setModifyDateUTC(a.getTime());
			documento.setCreateDateUTC(a.getTime());
			documento.setCreateUser("system");
			documento.setModifyUser("system");
			documento.setProcessId(1);
			documento.setModelAction(action);

			return documento;
		}
		public Email insertEmail(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Email email = new Email();
			Date a = new Date();
			email.setId(100);
			email.setTypeValue(100);
			email.setEmail("NATIVE INSERT UPDATE");
			email.setEmailTypeEnumValue(100);
			email.setParentId(id);
			email.setEmprId(1);
			email.setModifyDateUTC(a.getTime());
			email.setCreateDateUTC(a.getTime());
			email.setCreateUser("system");
			email.setModifyUser("system");
			email.setProcessId(1);
			email.setModelAction(action);

			return email;
		}

		public Telefone insertTelefone(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Telefone telefone = new Telefone();
			Date a = new Date();
			telefone.setId(100);
			telefone.setTypeValue(100);
			telefone.setDdd("NATIVE INSERT UPDATE");
			telefone.setNumero("NATIVE INSERT UPDATE");
			telefone.setTelefoneTypeEnumValue(100);
			telefone.setParentId(id);
			telefone.setEmprId(1);
			telefone.setModifyDateUTC(a.getTime());
			telefone.setCreateDateUTC(a.getTime());
			telefone.setCreateUser("system");
			telefone.setModifyUser("system");
			telefone.setProcessId(1);
			telefone.setModelAction(action);

			return telefone;
		}

		public CondPagPessoa insertCondPag(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			CondPagPessoa condpag = new CondPagPessoa();
			Date a = new Date();
			condpag.setId(100);
			condpag.setCondPagId(new CondPag());
			condpag.setParentId(id);
			condpag.setEmprId(1);
			condpag.setModifyDateUTC(a.getTime());
			condpag.setCreateDateUTC(a.getTime());
			condpag.setCreateUser("system");
			condpag.setModifyUser("system");
			condpag.setProcessId(1);
			condpag.setModelAction(action);

			return condpag;
		}


		public FormaPgPessoa insertFormaPg(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			FormaPgPessoa formapg = new FormaPgPessoa();
			Date a = new Date();
			formapg.setId(100);
			formapg.setFormaPgId(new FormaPg());
			formapg.setParentId(id);
			formapg.setEmprId(1);
			formapg.setModifyDateUTC(a.getTime());
			formapg.setCreateDateUTC(a.getTime());
			formapg.setCreateUser("system");
			formapg.setModifyUser("system");
			formapg.setProcessId(1);
			formapg.setModelAction(action);

			return formapg;
		}
		public Contato insertContato(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			Contato contato = new Contato();
			Date a = new Date();
			contato.setId(100);
			contato.setDataContato(a.getTime());
			contato.setNome("NATIVE INSERT UPDATE");
			contato.setMotivoValue(100);
			contato.setParentId(id);
			contato.setEmprId(1);
			contato.setModifyDateUTC(a.getTime());
			contato.setCreateDateUTC(a.getTime());
			contato.setCreateUser("system");
			contato.setModifyUser("system");
			contato.setProcessId(1);
			contato.setModelAction(action);

			return contato;
		}


	public ContatoItens insertContatoItens(Integer id,TabelaEnum tabela,PersistenceActionEnum action)
		{
			ContatoItens contatoitens = new ContatoItens();
			Date a = new Date();
			contatoitens.setId(100);
			contatoitens.setDataAlt(a.getTime());
			contatoitens.setTexto("NATIVE INSERT UPDATE");
			contatoitens.setTitulo("NATIVE INSERT UPDATE");
			contatoitens.setContatoStatus(ContatoStatusEnum.ABERTO);
			contatoitens.setVisto(true);
			contatoitens.setParentId(id);
			contatoitens.setEmprId(1);
			contatoitens.setModifyDateUTC(a.getTime());
			contatoitens.setCreateDateUTC(a.getTime());
			contatoitens.setCreateUser("system");
			contatoitens.setModifyUser("system");
			contatoitens.setProcessId(1);
			contatoitens.setModelAction(action);

			return contatoitens;
		}

}
