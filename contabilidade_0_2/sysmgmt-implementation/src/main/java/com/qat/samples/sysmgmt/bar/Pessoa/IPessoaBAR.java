/** create by system gera-java version 1.0.0 02/05/2016 21:44 : 15*/
package com.qat.samples.sysmgmt.bar.Pessoa;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advogado;
import com.qat.samples.sysmgmt.advocacia.request.AdvogadoInquiryRequest;
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
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.pessoa.model.request.ClienteInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.FornecedorInquiryRequest;
import com.qat.samples.sysmgmt.pessoa.model.request.TransportadorInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * The Interface PessoaBAR.. (Data Access Component - DAC)
 */
public interface IPessoaBAR
{

	/**
	 * Fetch advogado by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Advogado fetchAdvogadoById(FetchByIdRequest request);

	/**
* Insert advogado.
*
* @param advogado the advogado
*
* @return the internal response
*/
	public InternalResponse insertAdvogado(Advogado advogado);

	/**
* Update advogado.
*
* @param advogado the advogado
*
* @return the internal response
*/
	public InternalResponse updateAdvogado(Advogado advogado);

	/**
* Delete advogado.
*
* @param advogado the advogado
*
* @return the internal response
*/
	public InternalResponse deleteAdvogadoById(Advogado advogado);

	/**
* Delete all advogados.
*
* @return the internal response
*/
	public InternalResponse deleteAllAdvogados();

	/**
* Fetch all advogados.
*
* @return the list< advogado>
*/
	public InternalResultsResponse<Advogado> fetchAllAdvogados(Advogado  advogado);

	/**
* Fetch advogados by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advogado> fetchAdvogadosByRequest(AdvogadoInquiryRequest request);

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
	 * Fetch medico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Medico fetchMedicoById(FetchByIdRequest request);

	/**
* Insert medico.
*
* @param medico the medico
*
* @return the internal response
*/
	public InternalResponse insertMedico(Medico medico);

	/**
* Update medico.
*
* @param medico the medico
*
* @return the internal response
*/
	public InternalResponse updateMedico(Medico medico);

	/**
* Delete medico.
*
* @param medico the medico
*
* @return the internal response
*/
	public InternalResponse deleteMedicoById(Medico medico);

	/**
* Delete all medicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllMedicos();

	/**
* Fetch all medicos.
*
* @return the list< medico>
*/
	public InternalResultsResponse<Medico> fetchAllMedicos(Medico  medico);

	/**
* Fetch medicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Medico> fetchMedicosByRequest(MedicoInquiryRequest request);

	/**
	 * Fetch paciente by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Paciente fetchPacienteById(FetchByIdRequest request);

	/**
* Insert paciente.
*
* @param paciente the paciente
*
* @return the internal response
*/
	public InternalResponse insertPaciente(Paciente paciente);

	/**
* Update paciente.
*
* @param paciente the paciente
*
* @return the internal response
*/
	public InternalResponse updatePaciente(Paciente paciente);

	/**
* Delete paciente.
*
* @param paciente the paciente
*
* @return the internal response
*/
	public InternalResponse deletePacienteById(Paciente paciente);

	/**
* Delete all pacientes.
*
* @return the internal response
*/
	public InternalResponse deleteAllPacientes();

	/**
* Fetch all pacientes.
*
* @return the list< paciente>
*/
	public InternalResultsResponse<Paciente> fetchAllPacientes(Paciente  paciente);

	/**
* Fetch pacientes by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Paciente> fetchPacientesByRequest(PacienteInquiryRequest request);

	/**
	 * Fetch sindico by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Sindico fetchSindicoById(FetchByIdRequest request);

	/**
* Insert sindico.
*
* @param sindico the sindico
*
* @return the internal response
*/
	public InternalResponse insertSindico(Sindico sindico);

	/**
* Update sindico.
*
* @param sindico the sindico
*
* @return the internal response
*/
	public InternalResponse updateSindico(Sindico sindico);

	/**
* Delete sindico.
*
* @param sindico the sindico
*
* @return the internal response
*/
	public InternalResponse deleteSindicoById(Sindico sindico);

	/**
* Delete all sindicos.
*
* @return the internal response
*/
	public InternalResponse deleteAllSindicos();

	/**
* Fetch all sindicos.
*
* @return the list< sindico>
*/
	public InternalResultsResponse<Sindico> fetchAllSindicos(Sindico  sindico);

	/**
* Fetch sindicos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Sindico> fetchSindicosByRequest(SindicoInquiryRequest request);

	/**
	 * Fetch inquilino by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Inquilino fetchInquilinoById(FetchByIdRequest request);

	/**
* Insert inquilino.
*
* @param inquilino the inquilino
*
* @return the internal response
*/
	public InternalResponse insertInquilino(Inquilino inquilino);

	/**
* Update inquilino.
*
* @param inquilino the inquilino
*
* @return the internal response
*/
	public InternalResponse updateInquilino(Inquilino inquilino);

	/**
* Delete inquilino.
*
* @param inquilino the inquilino
*
* @return the internal response
*/
	public InternalResponse deleteInquilinoById(Inquilino inquilino);

	/**
* Delete all inquilinos.
*
* @return the internal response
*/
	public InternalResponse deleteAllInquilinos();

	/**
* Fetch all inquilinos.
*
* @return the list< inquilino>
*/
	public InternalResultsResponse<Inquilino> fetchAllInquilinos(Inquilino  inquilino);

	/**
* Fetch inquilinos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Inquilino> fetchInquilinosByRequest(InquilinoInquiryRequest request);

	/**
	 * Fetch funcionario by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Funcionario fetchFuncionarioById(FetchByIdRequest request);

	/**
* Insert funcionario.
*
* @param funcionario the funcionario
*
* @return the internal response
*/
	public InternalResponse insertFuncionario(Funcionario funcionario);

	/**
* Update funcionario.
*
* @param funcionario the funcionario
*
* @return the internal response
*/
	public InternalResponse updateFuncionario(Funcionario funcionario);

	/**
* Delete funcionario.
*
* @param funcionario the funcionario
*
* @return the internal response
*/
	public InternalResponse deleteFuncionarioById(Funcionario funcionario);

	/**
* Delete all funcionarios.
*
* @return the internal response
*/
	public InternalResponse deleteAllFuncionarios();

	/**
* Fetch all funcionarios.
*
* @return the list< funcionario>
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
