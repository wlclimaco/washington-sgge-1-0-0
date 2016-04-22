package com.qat.samples.sysmgmt.bac.Cadastros;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.convenio.model.Convenio;
import com.qat.samples.sysmgmt.entidade.model.request.CidadeMaintenanceRequest;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.estado.model.request.EstadoInquiryRequest;
import com.qat.samples.sysmgmt.estado.model.request.EstadoMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ConvenioInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.Cidade;
import com.qat.samples.sysmgmt.util.model.Tarefa;
import com.qat.samples.sysmgmt.util.model.request.CidadeInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.ConvenioMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.TarefaMaintenanceRequest;

	/**
	 * The Interface ICadastrosBAC. (Business Area Component - BAC)
	 */
	public interface ICadastrosBAC
	{



	//===================================### CLIENTE ####======================================
		/**

		/**
		 * Insert cliente.
		 *
	* @param request the cliente maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Cliente> insertCliente(ClienteMaintenanceRequest request);

		/**
	* Update cliente.
	*
	* @param request the cliente maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Cliente> updateCliente(ClienteMaintenanceRequest request);

		/**
	* Delete cliente.
	*
	* @param request the cliente maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Cliente> deleteCliente(ClienteMaintenanceRequest request);

		/**
	* Refresh clientes.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Cliente> refreshClientes(RefreshRequest request);

		/**
	* Fetch cliente by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Cliente> fetchClienteById(FetchByIdRequest request);

		/**
	* Fetch all clientes.
	*
	* @return the internal results response< cliente>
	*/
		public InternalResultsResponse<Cliente> fetchAllClientes(Cliente  cliente);

		/**
	* Fetch clientes by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Cliente> fetchClientesByRequest(ClienteInquiryRequest request);


	//===================================### FORNECEDOR ####======================================
		/**

		/**
		 * Insert fornecedor.
		 *
	* @param request the fornecedor maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Fornecedor> insertFornecedor(FornecedorMaintenanceRequest request);

		/**
	* Update fornecedor.
	*
	* @param request the fornecedor maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Fornecedor> updateFornecedor(FornecedorMaintenanceRequest request);

		/**
	* Delete fornecedor.
	*
	* @param request the fornecedor maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Fornecedor> deleteFornecedor(FornecedorMaintenanceRequest request);

		/**
	* Refresh fornecedors.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Fornecedor> refreshFornecedors(RefreshRequest request);

		/**
	* Fetch fornecedor by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Fornecedor> fetchFornecedorById(FetchByIdRequest request);

		/**
	* Fetch all fornecedors.
	*
	* @return the internal results response< fornecedor>
	*/
		public InternalResultsResponse<Fornecedor> fetchAllFornecedors(Fornecedor  fornecedor);

		/**
	* Fetch fornecedors by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Fornecedor> fetchFornecedorsByRequest(FornecedorInquiryRequest request);


	//===================================### TRANSPORTADOR ####======================================
		/**

		/**
		 * Insert transportador.
		 *
	* @param request the transportador maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Transportador> insertTransportador(TransportadorMaintenanceRequest request);

		/**
	* Update transportador.
	*
	* @param request the transportador maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Transportador> updateTransportador(TransportadorMaintenanceRequest request);

		/**
	* Delete transportador.
	*
	* @param request the transportador maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Transportador> deleteTransportador(TransportadorMaintenanceRequest request);

		/**
	* Refresh transportadors.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Transportador> refreshTransportadors(RefreshRequest request);

		/**
	* Fetch transportador by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Transportador> fetchTransportadorById(FetchByIdRequest request);

		/**
	* Fetch all transportadors.
	*
	* @return the internal results response< transportador>
	*/
		public InternalResultsResponse<Transportador> fetchAllTransportadors(Transportador  transportador);

		/**
	* Fetch transportadors by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Transportador> fetchTransportadorsByRequest(TransportadorInquiryRequest request);


	//===================================### CONVENIO ####======================================
		/**

		/**
		 * Insert convenio.
		 *
	* @param request the convenio maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Convenio> insertConvenio(ConvenioMaintenanceRequest request);

		/**
	* Update convenio.
	*
	* @param request the convenio maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Convenio> updateConvenio(ConvenioMaintenanceRequest request);

		/**
	* Delete convenio.
	*
	* @param request the convenio maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Convenio> deleteConvenio(ConvenioMaintenanceRequest request);

		/**
	* Refresh convenios.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Convenio> refreshConvenios(RefreshRequest request);

		/**
	* Fetch convenio by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Convenio> fetchConvenioById(FetchByIdRequest request);

		/**
	* Fetch all convenios.
	*
	* @return the internal results response< convenio>
	*/
		public InternalResultsResponse<Convenio> fetchAllConvenios(Convenio  convenio);

		/**
	* Fetch convenios by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Convenio> fetchConveniosByRequest(ConvenioInquiryRequest request);


	//===================================### CIDADE ####======================================
		/**

		/**
		 * Insert cidade.
		 *
	* @param request the cidade maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Cidade> insertCidade(CidadeMaintenanceRequest request);

		/**
	* Update cidade.
	*
	* @param request the cidade maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Cidade> updateCidade(CidadeMaintenanceRequest request);

		/**
	* Delete cidade.
	*
	* @param request the cidade maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Cidade> deleteCidade(CidadeMaintenanceRequest request);

		/**
	* Refresh cidades.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Cidade> refreshCidades(RefreshRequest request);

		/**
	* Fetch cidade by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Cidade> fetchCidadeById(FetchByIdRequest request);

		/**
	* Fetch all cidades.
	*
	* @return the internal results response< cidade>
	*/
		public InternalResultsResponse<Cidade> fetchAllCidades(Cidade  cidade);

		/**
	* Fetch cidades by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Cidade> fetchCidadesByRequest(CidadeInquiryRequest request);


	//===================================### ESTADO ####======================================
		/**

		/**
		 * Insert estado.
		 *
	* @param request the estado maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Estado> insertEstado(EstadoMaintenanceRequest request);

		/**
	* Update estado.
	*
	* @param request the estado maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Estado> updateEstado(EstadoMaintenanceRequest request);

		/**
	* Delete estado.
	*
	* @param request the estado maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Estado> deleteEstado(EstadoMaintenanceRequest request);

		/**
	* Refresh estados.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Estado> refreshEstados(RefreshRequest request);

		/**
	* Fetch estado by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Estado> fetchEstadoById(FetchByIdRequest request);

		/**
	* Fetch all estados.
	*
	* @return the internal results response< estado>
	*/
		public InternalResultsResponse<Estado> fetchAllEstados(Estado  estado);

		/**
	* Fetch estados by request.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Estado> fetchEstadosByRequest(EstadoInquiryRequest request);


	//===================================### TAREFA ####======================================
		/**

		/**
		 * Insert tarefa.
		 *
	* @param request the tarefa maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Tarefa> insertTarefa(TarefaMaintenanceRequest request);

		/**
	* Update tarefa.
	*
	* @param request the tarefa maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Tarefa> updateTarefa(TarefaMaintenanceRequest request);

		/**
	* Delete tarefa.
	*
	* @param request the tarefa maintenance request
	*
	* @return the internal results response
	*/
		public InternalResultsResponse<Tarefa> deleteTarefa(TarefaMaintenanceRequest request);

		/**
	* Refresh tarefas.
	*
	* @param request containing the number to refresh with and whether to return the result
	*/
		public InternalResultsResponse<Tarefa> refreshTarefas(RefreshRequest request);

		/**
	* Fetch tarefa by id.
	*
	* @param request the request
	* @return the internal results response
	*/
		public InternalResultsResponse<Tarefa> fetchTarefaById(FetchByIdRequest request);

		/**
	* Fetch all tarefas.
	*
	* @return the internal results response< tarefa>
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
