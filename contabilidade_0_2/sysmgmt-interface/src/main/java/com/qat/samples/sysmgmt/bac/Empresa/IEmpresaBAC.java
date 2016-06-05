/** create by system gera-java version 1.0.0 04/06/2016 19:30 : 57*/
package com.qat.samples.sysmgmt.bac.Empresa;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaMaintenanceRequest;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaMaintenanceRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaMaintenanceRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialMaintenanceRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioMaintenanceRequest;

/**
 * The Interface IEmpresaBAC. (Business Area Component - BAC)
 */
public interface IEmpresaBAC
{



//===================================### EMPRESA ####======================================
	/**

	/**
	 * Insert empresa.
	 *
* @param request the empresa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> insertEmpresa(EmpresaMaintenanceRequest request);

	/**
* Update empresa.
*
* @param request the empresa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> updateEmpresa(EmpresaMaintenanceRequest request);

	/**
* Delete empresa.
*
* @param request the empresa maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> deleteEmpresa(EmpresaMaintenanceRequest request);

	/**
* Refresh empresas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Empresa> refreshEmpresas(RefreshRequest request);

	/**
* Fetch empresa by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request);

	/**
* Fetch all empresas.
*
* @return the internal results response< empresa>
*/
	public InternalResultsResponse<Empresa> fetchAllEmpresas(Empresa  empresa);

	/**
* Fetch empresas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> fetchEmpresasByRequest(EmpresaInquiryRequest request);


//===================================### FILIAL ####======================================
	/**

	/**
	 * Insert filial.
	 *
* @param request the filial maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Filial> insertFilial(FilialMaintenanceRequest request);

	/**
* Update filial.
*
* @param request the filial maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Filial> updateFilial(FilialMaintenanceRequest request);

	/**
* Delete filial.
*
* @param request the filial maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Filial> deleteFilial(FilialMaintenanceRequest request);

	/**
* Refresh filials.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Filial> refreshFilials(RefreshRequest request);

	/**
* Fetch filial by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request);

	/**
* Fetch all filials.
*
* @return the internal results response< filial>
*/
	public InternalResultsResponse<Filial> fetchAllFilials(Filial  filial);

	/**
* Fetch filials by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Filial> fetchFilialsByRequest(FilialInquiryRequest request);


//===================================### DEPOSITO ####======================================
	/**

	/**
	 * Insert deposito.
	 *
* @param request the deposito maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> insertDeposito(DepositoMaintenanceRequest request);

	/**
* Update deposito.
*
* @param request the deposito maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> updateDeposito(DepositoMaintenanceRequest request);

	/**
* Delete deposito.
*
* @param request the deposito maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> deleteDeposito(DepositoMaintenanceRequest request);

	/**
* Refresh depositos.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Deposito> refreshDepositos(RefreshRequest request);

	/**
* Fetch deposito by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request);

	/**
* Fetch all depositos.
*
* @return the internal results response< deposito>
*/
	public InternalResultsResponse<Deposito> fetchAllDepositos(Deposito  deposito);

	/**
* Fetch depositos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> fetchDepositosByRequest(DepositoInquiryRequest request);


//===================================### USUARIO ####======================================
	/**

	/**
	 * Insert usuario.
	 *
* @param request the usuario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> insertUsuario(UsuarioMaintenanceRequest request);

	/**
* Update usuario.
*
* @param request the usuario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> updateUsuario(UsuarioMaintenanceRequest request);

	/**
* Delete usuario.
*
* @param request the usuario maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> deleteUsuario(UsuarioMaintenanceRequest request);

	/**
* Refresh usuarios.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Usuario> refreshUsuarios(RefreshRequest request);

	/**
* Fetch usuario by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> fetchUsuarioById(FetchByIdRequest request);

	/**
* Fetch all usuarios.
*
* @return the internal results response< usuario>
*/
	public InternalResultsResponse<Usuario> fetchAllUsuarios(Usuario  usuario);

	/**
* Fetch usuarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> fetchUsuariosByRequest(UsuarioInquiryRequest request);


//===================================### ADVOCACIA ####======================================
	/**

	/**
	 * Insert advocacia.
	 *
* @param request the advocacia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> insertAdvocacia(AdvocaciaMaintenanceRequest request);

	/**
* Update advocacia.
*
* @param request the advocacia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> updateAdvocacia(AdvocaciaMaintenanceRequest request);

	/**
* Delete advocacia.
*
* @param request the advocacia maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> deleteAdvocacia(AdvocaciaMaintenanceRequest request);

	/**
* Refresh advocacias.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Advocacia> refreshAdvocacias(RefreshRequest request);

	/**
* Fetch advocacia by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> fetchAdvocaciaById(FetchByIdRequest request);

	/**
* Fetch all advocacias.
*
* @return the internal results response< advocacia>
*/
	public InternalResultsResponse<Advocacia> fetchAllAdvocacias(Advocacia  advocacia);

	/**
* Fetch advocacias by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> fetchAdvocaciasByRequest(AdvocaciaInquiryRequest request);


//===================================### CLINICA ####======================================
	/**

	/**
	 * Insert clinica.
	 *
* @param request the clinica maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> insertClinica(ClinicaMaintenanceRequest request);

	/**
* Update clinica.
*
* @param request the clinica maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> updateClinica(ClinicaMaintenanceRequest request);

	/**
* Delete clinica.
*
* @param request the clinica maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> deleteClinica(ClinicaMaintenanceRequest request);

	/**
* Refresh clinicas.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Clinica> refreshClinicas(RefreshRequest request);

	/**
* Fetch clinica by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> fetchClinicaById(FetchByIdRequest request);

	/**
* Fetch all clinicas.
*
* @return the internal results response< clinica>
*/
	public InternalResultsResponse<Clinica> fetchAllClinicas(Clinica  clinica);

	/**
* Fetch clinicas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> fetchClinicasByRequest(ClinicaInquiryRequest request);


//===================================### CONDOMINIO ####======================================
	/**

	/**
	 * Insert condominio.
	 *
* @param request the condominio maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> insertCondominio(CondominioMaintenanceRequest request);

	/**
* Update condominio.
*
* @param request the condominio maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> updateCondominio(CondominioMaintenanceRequest request);

	/**
* Delete condominio.
*
* @param request the condominio maintenance request
*
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> deleteCondominio(CondominioMaintenanceRequest request);

	/**
* Refresh condominios.
*
* @param request containing the number to refresh with and whether to return the result
*/
	public InternalResultsResponse<Condominio> refreshCondominios(RefreshRequest request);

	/**
* Fetch condominio by id.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> fetchCondominioById(FetchByIdRequest request);

	/**
* Fetch all condominios.
*
* @return the internal results response< condominio>
*/
	public InternalResultsResponse<Condominio> fetchAllCondominios(Condominio  condominio);

	/**
* Fetch condominios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> fetchCondominiosByRequest(CondominioInquiryRequest request);

}
