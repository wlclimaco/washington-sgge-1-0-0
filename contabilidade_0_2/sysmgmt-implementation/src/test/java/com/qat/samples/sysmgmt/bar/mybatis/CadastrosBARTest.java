/** create by system gera-java version 1.0.0 30/04/2016 19:8 : 52*/
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

import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Cadastros.ICadastrosBAR;
import com.qat.samples.sysmgmt.convenio.model.Convenio;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Tarefa;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/cadastrosbartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class CadastrosBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(CadastrosBARTest.class);
private ICadastrosBAR cadastrosBAR; // injected by Spring through @Resource

@Resource
public void setCadastrosBAR(ICadastrosBAR cadastrosBAR)
{
	this.cadastrosBAR = cadastrosBAR;
}

public ICadastrosBAR getCadastrosBAR()
{
	return cadastrosBAR;
}


//===================================### CLIENTE ####======================================


//@Test
//	public void testDeleteCliente()
//	{
//		Cliente cliente = new Cliente(999, "Cliente_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(999);
//		Cliente clienteResponse = getCadastrosBAR().fetchClienteById(request);
//		Assert.assertEquals(clienteResponse, null);
//		getCadastrosBAR().insertCliente(cliente);
//		clienteResponse = getCadastrosBAR().fetchClienteById(request);
//		Assert.assertEquals(cliente.getId(), clienteResponse.getId());
//		getCadastrosBAR().deleteClienteById(cliente);
//		clienteResponse = getCadastrosBAR().fetchClienteById(request);
//		Assert.assertEquals(clienteResponse, null);
//	}
//
//	@Test
//	public void testFetchAllClientes()
//	{
//	Cliente cliente = new Cliente();
//		List<Cliente> response = getCadastrosBAR().fetchAllClientes(cliente).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllClientes()
//	{
//		getCadastrosBAR().deleteAllClientes();
//	Cliente cliente = new Cliente();
//		List<Cliente> response = getCadastrosBAR().fetchAllClientes(new Cliente()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateCliente()
//	{
//		Cliente cliente = new Cliente(1234, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1234);
//		Cliente clienteResponse = getCadastrosBAR().fetchClienteById(request);
//		Assert.assertEquals(clienteResponse.getNome(), "NATIVE INSERT");
//		getCadastrosBAR().updateCliente(cliente);
//		clienteResponse = getCadastrosBAR().fetchClienteById(request);
//		Assert.assertEquals(clienteResponse.getNome(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchClientesByRequest() throws Exception
//	{
//		// check for valid and precount
//		ClienteInquiryRequest request = new ClienteInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Cliente> response = getCadastrosBAR().fetchClientesByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getCadastrosBAR().fetchClientesByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		ClienteInquiryRequest request2 = new ClienteInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Cliente> response2 = getCadastrosBAR().fetchClientesByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getCadastrosBAR().deleteAllClientes();
//		ClienteInquiryRequest request3 = new ClienteInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Cliente> response3 = getCadastrosBAR().fetchClientesByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
//
////===================================### FORNECEDOR ####======================================
//
//
//@Test
//	public void testDeleteFornecedor()
//	{
//		Fornecedor fornecedor = new Fornecedor(999, "Fornecedor_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(999);
//		Fornecedor fornecedorResponse = getCadastrosBAR().fetchFornecedorById(request);
//		Assert.assertEquals(fornecedorResponse, null);
//		getCadastrosBAR().insertFornecedor(fornecedor);
//		fornecedorResponse = getCadastrosBAR().fetchFornecedorById(request);
//		Assert.assertEquals(fornecedor.getId(), fornecedorResponse.getId());
//		getCadastrosBAR().deleteFornecedorById(fornecedor);
//		fornecedorResponse = getCadastrosBAR().fetchFornecedorById(request);
//		Assert.assertEquals(fornecedorResponse, null);
//	}
//
//	@Test
//	public void testFetchAllFornecedors()
//	{
//	Fornecedor fornecedor = new Fornecedor();
//		List<Fornecedor> response = getCadastrosBAR().fetchAllFornecedors(fornecedor).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllFornecedors()
//	{
//		getCadastrosBAR().deleteAllFornecedors();
//	Fornecedor fornecedor = new Fornecedor();
//		List<Fornecedor> response = getCadastrosBAR().fetchAllFornecedors(new Fornecedor()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateFornecedor()
//	{
//		Fornecedor fornecedor = new Fornecedor(1, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1);
//		Fornecedor fornecedorResponse = getCadastrosBAR().fetchFornecedorById(request);
//		Assert.assertEquals(fornecedorResponse.getNome(), "NATIVE INSERT");
//		getCadastrosBAR().updateFornecedor(fornecedor);
//		fornecedorResponse = getCadastrosBAR().fetchFornecedorById(request);
//		Assert.assertEquals(fornecedorResponse.getNome(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchFornecedorsByRequest() throws Exception
//	{
//		// check for valid and precount
//		FornecedorInquiryRequest request = new FornecedorInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Fornecedor> response = getCadastrosBAR().fetchFornecedorsByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getCadastrosBAR().fetchFornecedorsByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		FornecedorInquiryRequest request2 = new FornecedorInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Fornecedor> response2 = getCadastrosBAR().fetchFornecedorsByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getCadastrosBAR().deleteAllFornecedors();
//		FornecedorInquiryRequest request3 = new FornecedorInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Fornecedor> response3 = getCadastrosBAR().fetchFornecedorsByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
//
////===================================### TRANSPORTADOR ####======================================
//
//
//@Test
//	public void testDeleteTransportador()
//	{
//		Transportador transportador = new Transportador(999, "Transportador_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(999);
//		Transportador transportadorResponse = getCadastrosBAR().fetchTransportadorById(request);
//		Assert.assertEquals(transportadorResponse, null);
//		getCadastrosBAR().insertTransportador(transportador);
//		transportadorResponse = getCadastrosBAR().fetchTransportadorById(request);
//		Assert.assertEquals(transportador.getId(), transportadorResponse.getId());
//		getCadastrosBAR().deleteTransportadorById(transportador);
//		transportadorResponse = getCadastrosBAR().fetchTransportadorById(request);
//		Assert.assertEquals(transportadorResponse, null);
//	}
//
//	@Test
//	public void testFetchAllTransportadors()
//	{
//	Transportador transportador = new Transportador();
//		List<Transportador> response = getCadastrosBAR().fetchAllTransportadors(transportador).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllTransportadors()
//	{
//		getCadastrosBAR().deleteAllTransportadors();
//	Transportador transportador = new Transportador();
//		List<Transportador> response = getCadastrosBAR().fetchAllTransportadors(new Transportador()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateTransportador()
//	{
//		Transportador transportador = new Transportador(1234, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1234);
//		Transportador transportadorResponse = getCadastrosBAR().fetchTransportadorById(request);
//		Assert.assertEquals(transportadorResponse.getNome(), "NATIVE INSERT");
//		getCadastrosBAR().updateTransportador(transportador);
//		transportadorResponse = getCadastrosBAR().fetchTransportadorById(request);
//		Assert.assertEquals(transportadorResponse.getNome(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchTransportadorsByRequest() throws Exception
//	{
//		// check for valid and precount
//		TransportadorInquiryRequest request = new TransportadorInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Transportador> response = getCadastrosBAR().fetchTransportadorsByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getCadastrosBAR().fetchTransportadorsByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		TransportadorInquiryRequest request2 = new TransportadorInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Transportador> response2 = getCadastrosBAR().fetchTransportadorsByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getCadastrosBAR().deleteAllTransportadors();
//		TransportadorInquiryRequest request3 = new TransportadorInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Transportador> response3 = getCadastrosBAR().fetchTransportadorsByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
//
////===================================### CONVENIO ####======================================
//
//
//@Test
//	public void testDeleteConvenio()
//	{
//		Convenio convenio = new Convenio(999, "Convenio_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(999);
//		Convenio convenioResponse = getCadastrosBAR().fetchConvenioById(request);
//		Assert.assertEquals(convenioResponse, null);
//		getCadastrosBAR().insertConvenio(convenio);
//		convenioResponse = getCadastrosBAR().fetchConvenioById(request);
//		Assert.assertEquals(convenio.getId(), convenioResponse.getId());
//		getCadastrosBAR().deleteConvenioById(convenio);
//		convenioResponse = getCadastrosBAR().fetchConvenioById(request);
//		Assert.assertEquals(convenioResponse, null);
//	}
//
//	@Test
//	public void testFetchAllConvenios()
//	{
//	Convenio convenio = new Convenio();
//		List<Convenio> response = getCadastrosBAR().fetchAllConvenios(convenio).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllConvenios()
//	{
//		getCadastrosBAR().deleteAllConvenios();
//	Convenio convenio = new Convenio();
//		List<Convenio> response = getCadastrosBAR().fetchAllConvenios(new Convenio()).getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateConvenio()
//	{
//		Convenio convenio = new Convenio(1234, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1234);
//		Convenio convenioResponse = getCadastrosBAR().fetchConvenioById(request);
//		Assert.assertEquals(convenioResponse.getNome(), "NATIVE INSERT");
//		getCadastrosBAR().updateConvenio(convenio);
//		convenioResponse = getCadastrosBAR().fetchConvenioById(request);
//		Assert.assertEquals(convenioResponse.getNome(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchConveniosByRequest() throws Exception
//	{
//		// check for valid and precount
//		ConvenioInquiryRequest request = new ConvenioInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Convenio> response = getCadastrosBAR().fetchConveniosByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getCadastrosBAR().fetchConveniosByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		ConvenioInquiryRequest request2 = new ConvenioInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Convenio> response2 = getCadastrosBAR().fetchConveniosByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getCadastrosBAR().deleteAllConvenios();
//		ConvenioInquiryRequest request3 = new ConvenioInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Convenio> response3 = getCadastrosBAR().fetchConveniosByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
//	@Before
//	public void setup()
//	{
//
//	}
//
//
////===================================### CIDADE ####======================================
//
//
//@Test
//	public void testDeleteCidade()
//	{
//		Cidade cidade = new Cidade(999, "Cidade_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(999);
//		Cidade cidadeResponse = getCadastrosBAR().fetchCidadeById(request);
//		Assert.assertEquals(cidadeResponse, null);
//		getCadastrosBAR().insertCidade(cidade);
//		cidadeResponse = getCadastrosBAR().fetchCidadeById(request);
//		Assert.assertEquals(cidade.getId(), cidadeResponse.getId());
//		getCadastrosBAR().deleteCidadeById(cidade);
//		cidadeResponse = getCadastrosBAR().fetchCidadeById(request);
//		Assert.assertEquals(cidadeResponse, null);
//	}
//
//	@Test
//	public void testFetchAllCidades()
//	{
//	Cidade cidade = new Cidade();
//		List<Cidade> response = getCadastrosBAR().fetchAllCidades(cidade).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllCidades()
//	{
//		getCadastrosBAR().deleteAllCidades();
//	Cidade cidade = new Cidade();
//		List<Cidade> response = getCadastrosBAR().fetchAllCidades().getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateCidade()
//	{
//		Cidade cidade = new Cidade(1234, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1234);
//		Cidade cidadeResponse = getCadastrosBAR().fetchCidadeById(request);
//		Assert.assertEquals(cidadeResponse.getDescription(), "NATIVE INSERT");
//		getCadastrosBAR().updateCidade(cidade);
//		cidadeResponse = getCadastrosBAR().fetchCidadeById(request);
//		Assert.assertEquals(cidadeResponse.getDescription(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchCidadesByRequest() throws Exception
//	{
//		// check for valid and precount
//		CidadeInquiryRequest request = new CidadeInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Cidade> response = getCadastrosBAR().fetchCidadesByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getCadastrosBAR().fetchCidadesByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		CidadeInquiryRequest request2 = new CidadeInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Cidade> response2 = getCadastrosBAR().fetchCidadesByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getCadastrosBAR().deleteAllCidades();
//		CidadeInquiryRequest request3 = new CidadeInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Cidade> response3 = getCadastrosBAR().fetchCidadesByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
//	@Before
//	public void setup()
//	{
//		executeSqlScript("conf/insertCidade.sql", false);
//	}
//
//
////===================================### ESTADO ####======================================
//
//
//@Test
//	public void testDeleteEstado()
//	{
//		Estado estado = new Estado(999, "Estado_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(999);
//		Estado estadoResponse = getCadastrosBAR().fetchEstadoById(request);
//		Assert.assertEquals(estadoResponse, null);
//		getCadastrosBAR().insertEstado(estado);
//		estadoResponse = getCadastrosBAR().fetchEstadoById(request);
//		Assert.assertEquals(estado.getId(), estadoResponse.getId());
//		getCadastrosBAR().deleteEstadoById(estado);
//		estadoResponse = getCadastrosBAR().fetchEstadoById(request);
//		Assert.assertEquals(estadoResponse, null);
//	}
//
//	@Test
//	public void testFetchAllEstados()
//	{
//	Estado estado = new Estado();
//		List<Estado> response = getCadastrosBAR().fetchAllEstados(estado).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllEstados()
//	{
//		getCadastrosBAR().deleteAllEstados();
//	Estado estado = new Estado();
//		List<Estado> response = getCadastrosBAR().fetchAllEstados().getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateEstado()
//	{
//		Estado estado = new Estado(1234, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1234);
//		Estado estadoResponse = getCadastrosBAR().fetchEstadoById(request);
//		Assert.assertEquals(estadoResponse.getDescription(), "NATIVE INSERT");
//		getCadastrosBAR().updateEstado(estado);
//		estadoResponse = getCadastrosBAR().fetchEstadoById(request);
//		Assert.assertEquals(estadoResponse.getDescription(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchEstadosByRequest() throws Exception
//	{
//		// check for valid and precount
//		EstadoInquiryRequest request = new EstadoInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Estado> response = getCadastrosBAR().fetchEstadosByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getCadastrosBAR().fetchEstadosByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		EstadoInquiryRequest request2 = new EstadoInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Estado> response2 = getCadastrosBAR().fetchEstadosByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getCadastrosBAR().deleteAllEstados();
//		EstadoInquiryRequest request3 = new EstadoInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Estado> response3 = getCadastrosBAR().fetchEstadosByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
//	@Before
//	public void setup()
//	{
//		executeSqlScript("conf/insertEstado.sql", false);
//	}
//
//
////===================================### TAREFA ####======================================
//
//
//@Test
//	public void testDeleteTarefa()
//	{
//		Tarefa tarefa = new Tarefa(999, "Tarefa_999");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(999);
//		Tarefa tarefaResponse = getCadastrosBAR().fetchTarefaById(request);
//		Assert.assertEquals(tarefaResponse, null);
//		getCadastrosBAR().insertTarefa(tarefa);
//		tarefaResponse = getCadastrosBAR().fetchTarefaById(request);
//		Assert.assertEquals(tarefa.getId(), tarefaResponse.getId());
//		getCadastrosBAR().deleteTarefaById(tarefa);
//		tarefaResponse = getCadastrosBAR().fetchTarefaById(request);
//		Assert.assertEquals(tarefaResponse, null);
//	}
//
//	@Test
//	public void testFetchAllTarefas()
//	{
//	Tarefa tarefa = new Tarefa();
//		List<Tarefa> response = getCadastrosBAR().fetchAllTarefas(tarefa).getResultsList();
//		Assert.assertNotNull(response);
//	}
//
//	@Test
//	public void testDeleteAllTarefas()
//	{
//		getCadastrosBAR().deleteAllTarefas();
//	Tarefa tarefa = new Tarefa();
//		List<Tarefa> response = getCadastrosBAR().fetchAllTarefas().getResultsList();
//		Assert.assertEquals(response.size(), 0);
//	}
//
//	@Test
//	public void testUpdateTarefa()
//	{
//		Tarefa tarefa = new Tarefa(1234, "NATIVE INSERT UPDATE");
//		FetchByIdRequest request = new FetchByIdRequest();
//		request.setFetchId(1234);
//		Tarefa tarefaResponse = getCadastrosBAR().fetchTarefaById(request);
//		Assert.assertEquals(tarefaResponse.getDescription(), "NATIVE INSERT");
//		getCadastrosBAR().updateTarefa(tarefa);
//		tarefaResponse = getCadastrosBAR().fetchTarefaById(request);
//		Assert.assertEquals(tarefaResponse.getDescription(), "NATIVE INSERT UPDATE");
//	}
//
//	@Test
//	public void testFetchTarefasByRequest() throws Exception
//	{
//		// check for valid and precount
//		TarefaInquiryRequest request = new TarefaInquiryRequest();
//		request.setPreQueryCount(true);
//		request.setStartPage(0);
//		request.setPageSize(3);
//		InternalResultsResponse<Tarefa> response = getCadastrosBAR().fetchTarefasByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//		// check for valid and precount and start 2nd page
//		request.setPreQueryCount(true);
//		request.setStartPage(1);
//		request.setPageSize(3);
//		response = getCadastrosBAR().fetchTarefasByRequest(request);
//		Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
//		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
//
//		// check for valid and no precount
//		TarefaInquiryRequest request2 = new TarefaInquiryRequest();
//		request2.setPreQueryCount(false);
//		InternalResultsResponse<Tarefa> response2 = getCadastrosBAR().fetchTarefasByRequest(request2);
//		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
//		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
//		// this is because we did not choose to precount
//		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);
//
//		// check for zero rows
//		getCadastrosBAR().deleteAllTarefas();
//		TarefaInquiryRequest request3 = new TarefaInquiryRequest();
//		request3.setPreQueryCount(true);
//		InternalResultsResponse<Tarefa> response3 = getCadastrosBAR().fetchTarefasByRequest(request3);
//		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);
//
//	}
//
//	@Before
//	public void setup()
//	{
//		executeSqlScript("conf/insertConvenio.sql", false);
//		executeSqlScript("conf/insertTransportador.sql", false);
//		executeSqlScript("conf/insertFornecedor.sql", false);
//		executeSqlScript("conf/insertCliente.sql", false);
//		executeSqlScript("conf/insertTarefa.sql", false);
//	}

}
