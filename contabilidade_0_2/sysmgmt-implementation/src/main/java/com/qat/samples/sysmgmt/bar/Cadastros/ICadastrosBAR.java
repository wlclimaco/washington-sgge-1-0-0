/** create by system gera-java version 1.0.0 30/04/2016 19:13 : 13*/
package com.qat.samples.sysmgmt.bar.Cadastros;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
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

/**
 * The Interface CadastrosBAR.. (Data Access Component - DAC)
 */
public interface ICadastrosBAR
{

	/**
	 * Fetch cliente by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Cliente fetchClienteById(FetchByIdRequest request);

	/**
* Insert cliente.
*
* @param cliente the cliente
*
* @return the internal response
*/
	public InternalResponse insertCliente(Cliente cliente);

	/**
* Update cliente.
*
* @param cliente the cliente
*
* @return the internal response
*/
	public InternalResponse updateCliente(Cliente cliente);

	/**
* Delete cliente.
*
* @param cliente the cliente
*
* @return the internal response
*/
	public InternalResponse deleteClienteById(Cliente cliente);

	/**
* Delete all clientes.
*
* @return the internal response
*/
	public InternalResponse deleteAllClientes();

	/**
* Fetch all clientes.
*
* @return the list< cliente>
*/
	public InternalResultsResponse<Cliente> fetchAllClientes(Cliente  cliente);

	/**
* Fetch clientes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cliente> fetchClientesByRequest(ClienteInquiryRequest request);

	/**
	 * Fetch fornecedor by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Fornecedor fetchFornecedorById(FetchByIdRequest request);

	/**
* Insert fornecedor.
*
* @param fornecedor the fornecedor
*
* @return the internal response
*/
	public InternalResponse insertFornecedor(Fornecedor fornecedor);

	/**
* Update fornecedor.
*
* @param fornecedor the fornecedor
*
* @return the internal response
*/
	public InternalResponse updateFornecedor(Fornecedor fornecedor);

	/**
* Delete fornecedor.
*
* @param fornecedor the fornecedor
*
* @return the internal response
*/
	public InternalResponse deleteFornecedorById(Fornecedor fornecedor);

	/**
* Delete all fornecedors.
*
* @return the internal response
*/
	public InternalResponse deleteAllFornecedors();

	/**
* Fetch all fornecedors.
*
* @return the list< fornecedor>
*/
	public InternalResultsResponse<Fornecedor> fetchAllFornecedors(Fornecedor  fornecedor);

	/**
* Fetch fornecedors by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Fornecedor> fetchFornecedorsByRequest(FornecedorInquiryRequest request);

	/**
	 * Fetch transportador by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Transportador fetchTransportadorById(FetchByIdRequest request);

	/**
* Insert transportador.
*
* @param transportador the transportador
*
* @return the internal response
*/
	public InternalResponse insertTransportador(Transportador transportador);

	/**
* Update transportador.
*
* @param transportador the transportador
*
* @return the internal response
*/
	public InternalResponse updateTransportador(Transportador transportador);

	/**
* Delete transportador.
*
* @param transportador the transportador
*
* @return the internal response
*/
	public InternalResponse deleteTransportadorById(Transportador transportador);

	/**
* Delete all transportadors.
*
* @return the internal response
*/
	public InternalResponse deleteAllTransportadors();

	/**
* Fetch all transportadors.
*
* @return the list< transportador>
*/
	public InternalResultsResponse<Transportador> fetchAllTransportadors(Transportador  transportador);

	/**
* Fetch transportadors by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Transportador> fetchTransportadorsByRequest(TransportadorInquiryRequest request);

	/**
	 * Fetch convenio by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Convenio fetchConvenioById(FetchByIdRequest request);

	/**
* Insert convenio.
*
* @param convenio the convenio
*
* @return the internal response
*/
	public InternalResponse insertConvenio(Convenio convenio);

	/**
* Update convenio.
*
* @param convenio the convenio
*
* @return the internal response
*/
	public InternalResponse updateConvenio(Convenio convenio);

	/**
* Delete convenio.
*
* @param convenio the convenio
*
* @return the internal response
*/
	public InternalResponse deleteConvenioById(Convenio convenio);

	/**
* Delete all convenios.
*
* @return the internal response
*/
	public InternalResponse deleteAllConvenios();

	/**
* Fetch all convenios.
*
* @return the list< convenio>
*/
	public InternalResultsResponse<Convenio> fetchAllConvenios(Convenio  convenio);

	/**
* Fetch convenios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Convenio> fetchConveniosByRequest(ConvenioInquiryRequest request);

	/**
	 * Fetch cidade by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Cidade fetchCidadeById(FetchByIdRequest request);

	/**
* Insert cidade.
*
* @param cidade the cidade
*
* @return the internal response
*/
	public InternalResponse insertCidade(Cidade cidade);

	/**
* Update cidade.
*
* @param cidade the cidade
*
* @return the internal response
*/
	public InternalResponse updateCidade(Cidade cidade);

	/**
* Delete cidade.
*
* @param cidade the cidade
*
* @return the internal response
*/
	public InternalResponse deleteCidadeById(Cidade cidade);

	/**
* Delete all cidades.
*
* @return the internal response
*/
	public InternalResponse deleteAllCidades();

	/**
* Fetch all cidades.
*
* @return the list< cidade>
*/
	public InternalResultsResponse<Cidade> fetchAllCidades(Cidade  cidade);

	/**
* Fetch cidades by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Cidade> fetchCidadesByRequest(CidadeInquiryRequest request);

	/**
	 * Fetch estado by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Estado fetchEstadoById(FetchByIdRequest request);

	/**
* Insert estado.
*
* @param estado the estado
*
* @return the internal response
*/
	public InternalResponse insertEstado(Estado estado);

	/**
* Update estado.
*
* @param estado the estado
*
* @return the internal response
*/
	public InternalResponse updateEstado(Estado estado);

	/**
* Delete estado.
*
* @param estado the estado
*
* @return the internal response
*/
	public InternalResponse deleteEstadoById(Estado estado);

	/**
* Delete all estados.
*
* @return the internal response
*/
	public InternalResponse deleteAllEstados();

	/**
* Fetch all estados.
*
* @return the list< estado>
*/
	public InternalResultsResponse<Estado> fetchAllEstados(Estado  estado);

	/**
* Fetch estados by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Estado> fetchEstadosByRequest(EstadoInquiryRequest request);

	/**
	 * Fetch tarefa by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Tarefa fetchTarefaById(FetchByIdRequest request);

	/**
* Insert tarefa.
*
* @param tarefa the tarefa
*
* @return the internal response
*/
	public InternalResponse insertTarefa(Tarefa tarefa);

	/**
* Update tarefa.
*
* @param tarefa the tarefa
*
* @return the internal response
*/
	public InternalResponse updateTarefa(Tarefa tarefa);

	/**
* Delete tarefa.
*
* @param tarefa the tarefa
*
* @return the internal response
*/
	public InternalResponse deleteTarefaById(Tarefa tarefa);

	/**
* Delete all tarefas.
*
* @return the internal response
*/
	public InternalResponse deleteAllTarefas();

	/**
* Fetch all tarefas.
*
* @return the list< tarefa>
*/
	public InternalResultsResponse<Tarefa> fetchAllTarefas(Tarefa  tarefa);

	/**
* Fetch tarefas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Tarefa> fetchTarefasByRequest(TarefaInquiryRequest request);

}
