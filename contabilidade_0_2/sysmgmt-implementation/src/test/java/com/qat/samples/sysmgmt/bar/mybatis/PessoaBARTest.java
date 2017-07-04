/** create by system gera-java version 1.0.0 03/05/2016 12:25 : 26*/
package com.qat.samples.sysmgmt.bar.mybatis;


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
import com.qat.samples.sysmgmt.bar.Pessoa.IPessoaBAR;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
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
import com.qat.samples.sysmgmt.util.model.TabelaEnum;
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
		Advogado advogado = Objects.insertAdvogado(1035, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
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
	Advogado advogado = Objects.insertAdvogado(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		List<Advogado> response = getPessoaBAR().fetchAllAdvogados(advogado).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllAdvogados()
	{
		getPessoaBAR().deleteAllAdvogados();
		Advogado advogado = Objects.insertAdvogado(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		List<Advogado> response = getPessoaBAR().fetchAllAdvogados(new Advogado()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateAdvogado()
	{
		Advogado advogado = Objects.insertAdvogado(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Advogado advogadoResponse = getPessoaBAR().fetchAdvogadoById(request);
		Assert.assertEquals(advogadoResponse.getNome(), "nome_1");
		getPessoaBAR().updateAdvogado(advogado);
		advogadoResponse = getPessoaBAR().fetchAdvogadoById(request);
		Assert.assertEquals(advogadoResponse.getNome(), "nome_1 - INSERT");
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
		//Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchAdvogadosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		//Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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
		Cliente cliente = Objects.insertCliente(1090, TabelaEnum.CLIENTE,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1090);
		Cliente clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(clienteResponse, null);
		getPessoaBAR().insertCliente(cliente);
		clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(PessoaTypeEnum.CLIENTE.getLabelKey().toLowerCase(), clienteResponse.getPessoaTipo().get(0).getPessoaTypeEnum().name().toLowerCase());
		Assert.assertEquals(cliente.getNome(), clienteResponse.getNome());
		cliente = Objects.insertCliente(1090, TabelaEnum.CLIENTE,PersistenceActionEnum.DELETE);
		getPessoaBAR().deleteClienteById(cliente);
		clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(clienteResponse, null);
	}

	@Test
	public void testFetchAllClientes()
	{
	Cliente cliente = Objects.insertCliente(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		List<Cliente> response = getPessoaBAR().fetchAllClientes(cliente).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllClientes()
	{
		getPessoaBAR().deleteAllClientes();
	Cliente cliente = Objects.insertCliente(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		List<Cliente> response = getPessoaBAR().fetchAllClientes(new Cliente()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateCliente()
	{
		Cliente cliente = Objects.insertCliente(1001, TabelaEnum.ADVOCACIA,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Cliente clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(clienteResponse.getNome(), "nome_1");
		getPessoaBAR().updateCliente(cliente);
		clienteResponse = getPessoaBAR().fetchClienteById(request);
		Assert.assertEquals(clienteResponse.getNome(), "nome_1 - INSERT");
	}

	@Test
	public void testFetchClientesByRequest() throws Exception
	{
		// check for valid and precount
		ClienteInquiryRequest request = new ClienteInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		request.setSelect(false);
		InternalResultsResponse<Cliente> response = getPessoaBAR().fetchClientesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		request.setSelect(true);
		response = getPessoaBAR().fetchClientesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 20);

		// check for valid and no precount
		ClienteInquiryRequest request2 = new ClienteInquiryRequest();
		request2.setPreQueryCount(false);
		request2.setSelect(false);
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
		Fornecedor fornecedor = Objects.insertFornecedor(1040, TabelaEnum.FORNECEDOR,PersistenceActionEnum.INSERT);
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
	Fornecedor fornecedor = Objects.insertFornecedor(1001, TabelaEnum.FORNECEDOR,PersistenceActionEnum.INSERT);
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
		Fornecedor fornecedor = Objects.insertFornecedor(1001, TabelaEnum.FORNECEDOR,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Fornecedor fornecedorResponse = getPessoaBAR().fetchFornecedorById(request);
		Assert.assertEquals(fornecedorResponse.getNome(), "nome_1");
		getPessoaBAR().updateFornecedor(fornecedor);
		fornecedorResponse = getPessoaBAR().fetchFornecedorById(request);
		Assert.assertEquals(fornecedorResponse.getNome(), "nome_1 - INSERT");
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
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchFornecedorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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
		Transportador transportador = Objects.insertTransportador(1060, TabelaEnum.TRANSPORTADOR,PersistenceActionEnum.INSERT);
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
	Transportador transportador = Objects.insertTransportador(1001, TabelaEnum.TRANSPORTADOR,PersistenceActionEnum.INSERT);
		List<Transportador> response = getPessoaBAR().fetchAllTransportadors(transportador).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllTransportadors()
	{
		getPessoaBAR().deleteAllTransportadors();
	Transportador transportador = Objects.insertTransportador(1001, TabelaEnum.TRANSPORTADOR,PersistenceActionEnum.INSERT);
		List<Transportador> response = getPessoaBAR().fetchAllTransportadors(new Transportador()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateTransportador()
	{
		Transportador transportador = Objects.insertTransportador(11010, TabelaEnum.TRANSPORTADOR,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(11010);
		Transportador transportadorResponse = getPessoaBAR().fetchTransportadorById(request);
		Assert.assertEquals(transportadorResponse.getNome(), "nome_1");
		getPessoaBAR().updateTransportador(transportador);
		transportadorResponse = getPessoaBAR().fetchTransportadorById(request);
		Assert.assertEquals(transportadorResponse.getNome(), "nome_1 - INSERT");
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
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchTransportadorsByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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
		Medico medico = Objects.insertMedico(1045, TabelaEnum.MEDICO,PersistenceActionEnum.INSERT);
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
	Medico medico = Objects.insertMedico(1001, TabelaEnum.MEDICO,PersistenceActionEnum.INSERT);
		List<Medico> response = getPessoaBAR().fetchAllMedicos(medico).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllMedicos()
	{
		getPessoaBAR().deleteAllMedicos();
	Medico medico = Objects.insertMedico(1001, TabelaEnum.MEDICO,PersistenceActionEnum.INSERT);
		List<Medico> response = getPessoaBAR().fetchAllMedicos(new Medico()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateMedico()
	{
		Medico medico = Objects.insertMedico(1001, TabelaEnum.MEDICO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Medico medicoResponse = getPessoaBAR().fetchMedicoById(request);
		Assert.assertEquals(medicoResponse.getNome(), "nome_1");
		getPessoaBAR().updateMedico(medico);
		medicoResponse = getPessoaBAR().fetchMedicoById(request);
		Assert.assertEquals(medicoResponse.getNome(), "nome_1 - INSERT");
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
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchMedicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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
		Paciente paciente = Objects.insertPaciente(1060, TabelaEnum.PACIENTE,PersistenceActionEnum.INSERT);
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
	Paciente paciente = Objects.insertPaciente(1001, TabelaEnum.PACIENTE,PersistenceActionEnum.INSERT);
		List<Paciente> response = getPessoaBAR().fetchAllPacientes(paciente).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllPacientes()
	{
		getPessoaBAR().deleteAllPacientes();
	Paciente paciente = Objects.insertPaciente(1001, TabelaEnum.PACIENTE,PersistenceActionEnum.INSERT);
		List<Paciente> response = getPessoaBAR().fetchAllPacientes(new Paciente()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdatePaciente()
	{
		Paciente paciente = Objects.insertPaciente(1001, TabelaEnum.PACIENTE,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Paciente pacienteResponse = getPessoaBAR().fetchPacienteById(request);
		Assert.assertEquals(pacienteResponse.getNome(), "nome_1");
		getPessoaBAR().updatePaciente(paciente);
		pacienteResponse = getPessoaBAR().fetchPacienteById(request);
		Assert.assertEquals(pacienteResponse.getNome(), "nome_1 - INSERT");
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
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchPacientesByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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
		Sindico sindico = Objects.insertSindico(1040, TabelaEnum.SINDICO,PersistenceActionEnum.INSERT);
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
	Sindico sindico = Objects.insertSindico(1001, TabelaEnum.SINDICO,PersistenceActionEnum.INSERT);
		List<Sindico> response = getPessoaBAR().fetchAllSindicos(sindico).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllSindicos()
	{
		getPessoaBAR().deleteAllSindicos();
	Sindico sindico = Objects.insertSindico(1001, TabelaEnum.SINDICO,PersistenceActionEnum.INSERT);
		List<Sindico> response = getPessoaBAR().fetchAllSindicos(new Sindico()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateSindico()
	{
		Sindico sindico = Objects.insertSindico(1001, TabelaEnum.SINDICO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Sindico sindicoResponse = getPessoaBAR().fetchSindicoById(request);
		Assert.assertEquals(sindicoResponse.getNome(), "nome_1");
		getPessoaBAR().updateSindico(sindico);
		sindicoResponse = getPessoaBAR().fetchSindicoById(request);
		Assert.assertEquals(sindicoResponse.getNome(), "nome_1 - INSERT");
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
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchSindicosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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
		Inquilino inquilino = Objects.insertInquilino(1040, TabelaEnum.INQUILINO,PersistenceActionEnum.INSERT);
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
	Inquilino inquilino = Objects.insertInquilino(1035, TabelaEnum.INQUILINO,PersistenceActionEnum.INSERT);
		List<Inquilino> response = getPessoaBAR().fetchAllInquilinos(inquilino).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllInquilinos()
	{
		getPessoaBAR().deleteAllInquilinos();
	Inquilino inquilino = Objects.insertInquilino(1035, TabelaEnum.INQUILINO,PersistenceActionEnum.INSERT);
		List<Inquilino> response = getPessoaBAR().fetchAllInquilinos(new Inquilino()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateInquilino()
	{
		Inquilino inquilino = Objects.insertInquilino(1001, TabelaEnum.INQUILINO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Inquilino inquilinoResponse = getPessoaBAR().fetchInquilinoById(request);
		Assert.assertEquals(inquilinoResponse.getNome(), "nome_1");
		getPessoaBAR().updateInquilino(inquilino);
		inquilinoResponse = getPessoaBAR().fetchInquilinoById(request);
		Assert.assertEquals(inquilinoResponse.getNome(), "nome_1 - INSERT");
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
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchInquilinosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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
		Funcionario funcionario = Objects.insertFuncionario(1035, TabelaEnum.FUNCIONARIO,PersistenceActionEnum.INSERT);
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
	Funcionario funcionario = Objects.insertFuncionario(1035, TabelaEnum.FUNCIONARIO,PersistenceActionEnum.INSERT);
		List<Funcionario> response = getPessoaBAR().fetchAllFuncionarios(funcionario).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllFuncionarios()
	{
		getPessoaBAR().deleteAllFuncionarios();
	Funcionario funcionario = Objects.insertFuncionario(1035, TabelaEnum.FUNCIONARIO,PersistenceActionEnum.INSERT);
		List<Funcionario> response = getPessoaBAR().fetchAllFuncionarios(new Funcionario()).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateFuncionario()
	{
		Funcionario funcionario = Objects.insertFuncionario(1001, TabelaEnum.FUNCIONARIO,PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1001);
		Funcionario funcionarioResponse = getPessoaBAR().fetchFuncionarioById(request);
		Assert.assertEquals(funcionarioResponse.getNome(), "nome_1");
		getPessoaBAR().updateFuncionario(funcionario);
		funcionarioResponse = getPessoaBAR().fetchFuncionarioById(request);
		Assert.assertEquals(funcionarioResponse.getNome(), "nome_1 - INSERT");
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
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getPessoaBAR().fetchFuncionariosByRequest(request);
		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
	//	Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
	//	Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

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

}
