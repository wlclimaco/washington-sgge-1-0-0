/** create by system gera-java version 1.0.0 02/05/2016 21:44 : 15*/
package com.qat.samples.sysmgmt.bac.Pessoa;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.MedicoMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.PacienteMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Inquilino;
import com.qat.samples.sysmgmt.condominio.model.Sindico;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.InquilinoMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.SindicoMaintenanceRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioInquiryRequest;
import com.qat.samples.sysmgmt.dp.model.request.FuncionarioMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.Cliente;
import com.qat.samples.sysmgmt.pessoa.model.Fornecedor;
import com.qat.samples.sysmgmt.pessoa.model.Funcionario;
import com.qat.samples.sysmgmt.pessoa.model.Medico;
import com.qat.samples.sysmgmt.pessoa.model.Paciente;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorMaintenanceRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;

/**
 * The Interface IPessoaBAC. (Business Area Component - BAC)
 */
public interface IPessoaBAC
{



//===================================### ADVOGADO ####======================================
	/**

	/**
	 * Insert advogado.
	 *
* @param request the advogado maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> insertAdvogado(AdvogadoMaintenanceRequest request);

	/**
* Update advogado.
*
* @param request the advogado maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> updateAdvogado(AdvogadoMaintenanceRequest request);

	/**
* Delete advogado.
*
* @param request the advogado maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> deleteAdvogado(AdvogadoMaintenanceRequest request);

	/**
* Refresh advogados.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Advogado> refreshAdvogados(RefreshRequest request);

	/**
* Fetch advogado by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> fetchAdvogadoById(FetchByIdRequest request);

	/**
* Fetch all advogados.
*
* @return the internal results response< advogado>
*/
	public InternalResultsResponse<Advogado> fetchAllAdvogados(Advogado  advogado);

	/**
* Fetch advogados by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> fetchAdvogadosByRequest(AdvogadoInquiryRequest request);


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


//===================================### MEDICO ####======================================
	/**

	/**
	 * Insert medico.
	 *
* @param request the medico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Medico> insertMedico(MedicoMaintenanceRequest request);

	/**
* Update medico.
*
* @param request the medico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Medico> updateMedico(MedicoMaintenanceRequest request);

	/**
* Delete medico.
*
* @param request the medico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Medico> deleteMedico(MedicoMaintenanceRequest request);

	/**
* Refresh medicos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Medico> refreshMedicos(RefreshRequest request);

	/**
* Fetch medico by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Medico> fetchMedicoById(FetchByIdRequest request);

	/**
* Fetch all medicos.
*
* @return the internal results response< medico>
*/
	public InternalResultsResponse<Medico> fetchAllMedicos(Medico  medico);

	/**
* Fetch medicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Medico> fetchMedicosByRequest(MedicoInquiryRequest request);


//===================================### PACIENTE ####======================================
	/**

	/**
	 * Insert paciente.
	 *
* @param request the paciente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> insertPaciente(PacienteMaintenanceRequest request);

	/**
* Update paciente.
*
* @param request the paciente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> updatePaciente(PacienteMaintenanceRequest request);

	/**
* Delete paciente.
*
* @param request the paciente maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> deletePaciente(PacienteMaintenanceRequest request);

	/**
* Refresh pacientes.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Paciente> refreshPacientes(RefreshRequest request);

	/**
* Fetch paciente by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> fetchPacienteById(FetchByIdRequest request);

	/**
* Fetch all pacientes.
*
* @return the internal results response< paciente>
*/
	public InternalResultsResponse<Paciente> fetchAllPacientes(Paciente  paciente);

	/**
* Fetch pacientes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> fetchPacientesByRequest(PacienteInquiryRequest request);


//===================================### SINDICO ####======================================
	/**

	/**
	 * Insert sindico.
	 *
* @param request the sindico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> insertSindico(SindicoMaintenanceRequest request);

	/**
* Update sindico.
*
* @param request the sindico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> updateSindico(SindicoMaintenanceRequest request);

	/**
* Delete sindico.
*
* @param request the sindico maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> deleteSindico(SindicoMaintenanceRequest request);

	/**
* Refresh sindicos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Sindico> refreshSindicos(RefreshRequest request);

	/**
* Fetch sindico by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> fetchSindicoById(FetchByIdRequest request);

	/**
* Fetch all sindicos.
*
* @return the internal results response< sindico>
*/
	public InternalResultsResponse<Sindico> fetchAllSindicos(Sindico  sindico);

	/**
* Fetch sindicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> fetchSindicosByRequest(SindicoInquiryRequest request);


//===================================### INQUILINO ####======================================
	/**

	/**
	 * Insert inquilino.
	 *
* @param request the inquilino maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> insertInquilino(InquilinoMaintenanceRequest request);

	/**
* Update inquilino.
*
* @param request the inquilino maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> updateInquilino(InquilinoMaintenanceRequest request);

	/**
* Delete inquilino.
*
* @param request the inquilino maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> deleteInquilino(InquilinoMaintenanceRequest request);

	/**
* Refresh inquilinos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Inquilino> refreshInquilinos(RefreshRequest request);

	/**
* Fetch inquilino by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> fetchInquilinoById(FetchByIdRequest request);

	/**
* Fetch all inquilinos.
*
* @return the internal results response< inquilino>
*/
	public InternalResultsResponse<Inquilino> fetchAllInquilinos(Inquilino  inquilino);

	/**
* Fetch inquilinos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> fetchInquilinosByRequest(InquilinoInquiryRequest request);


//===================================### FUNCIONARIO ####======================================
	/**

	/**
	 * Insert funcionario.
	 *
* @param request the funcionario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> insertFuncionario(FuncionarioMaintenanceRequest request);

	/**
* Update funcionario.
*
* @param request the funcionario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> updateFuncionario(FuncionarioMaintenanceRequest request);

	/**
* Delete funcionario.
*
* @param request the funcionario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> deleteFuncionario(FuncionarioMaintenanceRequest request);

	/**
* Refresh funcionarios.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Funcionario> refreshFuncionarios(RefreshRequest request);

	/**
* Fetch funcionario by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request);

	/**
* Fetch all funcionarios.
*
* @return the internal results response< funcionario>
*/
	public InternalResultsResponse<Funcionario> fetchAllFuncionarios(Funcionario  funcionario);

	/**
* Fetch funcionarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Funcionario> fetchFuncionariosByRequest(FuncionarioInquiryRequest request);

}
