/** create by system gera-java version 1.0.0 01/05/2016 18:42 : 57*/
package com.qat.samples.sysmgmt.bar.Empresa;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.advocacia.Advocacia;
import com.qat.samples.sysmgmt.advocacia.request.AdvocaciaInquiryRequest;
import com.qat.samples.sysmgmt.clinica.model.Clinica;
import com.qat.samples.sysmgmt.clinica.model.request.ClinicaInquiryRequest;
import com.qat.samples.sysmgmt.condominio.model.Condominio;
import com.qat.samples.sysmgmt.condominio.model.request.CondominioInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.Deposito;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.entidade.model.Filial;
import com.qat.samples.sysmgmt.entidade.model.Usuario;
import com.qat.samples.sysmgmt.entidade.model.request.DepositoInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.EmpresaInquiryRequest;
import com.qat.samples.sysmgmt.entidade.model.request.FilialInquiryRequest;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.UsuarioInquiryRequest;

/**
 * The Interface EmpresaBAR.. (Data Access Component - DAC)
 */
public interface IEmpresaBAR
{

	/**
	 * Fetch empresa by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Empresa fetchEmpresaById(FetchByIdRequest request);

	/**
* Insert empresa.
*
* @param empresa the empresa
*
* @return the internal response
*/
	public InternalResponse insertEmpresa(Empresa empresa);

	/**
* Update empresa.
*
* @param empresa the empresa
*
* @return the internal response
*/
	public InternalResponse updateEmpresa(Empresa empresa);

	/**
* Delete empresa.
*
* @param empresa the empresa
*
* @return the internal response
*/
	public InternalResponse deleteEmpresaById(Empresa empresa);

	/**
* Delete all empresas.
*
* @return the internal response
*/
	public InternalResponse deleteAllEmpresas();

	/**
* Fetch all empresas.
*
* @return the list< empresa>
*/
	public InternalResultsResponse<Empresa> fetchAllEmpresas(Empresa  empresa);

	/**
* Fetch empresas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Empresa> fetchEmpresasByRequest(EmpresaInquiryRequest request);

	/**
	 * Fetch filial by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Filial fetchFilialById(FetchByIdRequest request);

	/**
* Insert filial.
*
* @param filial the filial
*
* @return the internal response
*/
	public InternalResponse insertFilial(Filial filial);

	/**
* Update filial.
*
* @param filial the filial
*
* @return the internal response
*/
	public InternalResponse updateFilial(Filial filial);

	/**
* Delete filial.
*
* @param filial the filial
*
* @return the internal response
*/
	public InternalResponse deleteFilialById(Filial filial);

	/**
* Delete all filials.
*
* @return the internal response
*/
	public InternalResponse deleteAllFilials();

	/**
* Fetch all filials.
*
* @return the list< filial>
*/
	public InternalResultsResponse<Filial> fetchAllFilials(Filial  filial);

	/**
* Fetch filials by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Filial> fetchFilialsByRequest(FilialInquiryRequest request);

	/**
	 * Fetch deposito by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Deposito fetchDepositoById(FetchByIdRequest request);

	/**
* Insert deposito.
*
* @param deposito the deposito
*
* @return the internal response
*/
	public InternalResponse insertDeposito(Deposito deposito);

	/**
* Update deposito.
*
* @param deposito the deposito
*
* @return the internal response
*/
	public InternalResponse updateDeposito(Deposito deposito);

	/**
* Delete deposito.
*
* @param deposito the deposito
*
* @return the internal response
*/
	public InternalResponse deleteDepositoById(Deposito deposito);

	/**
* Delete all depositos.
*
* @return the internal response
*/
	public InternalResponse deleteAllDepositos();

	/**
* Fetch all depositos.
*
* @return the list< deposito>
*/
	public InternalResultsResponse<Deposito> fetchAllDepositos(Deposito  deposito);

	/**
* Fetch depositos by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Deposito> fetchDepositosByRequest(DepositoInquiryRequest request);

	/**
	 * Fetch usuario by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Usuario fetchUsuarioById(FetchByIdRequest request);

	/**
* Insert usuario.
*
* @param usuario the usuario
*
* @return the internal response
*/
	public InternalResponse insertUsuario(Usuario usuario);

	/**
* Update usuario.
*
* @param usuario the usuario
*
* @return the internal response
*/
	public InternalResponse updateUsuario(Usuario usuario);

	/**
* Delete usuario.
*
* @param usuario the usuario
*
* @return the internal response
*/
	public InternalResponse deleteUsuarioById(Usuario usuario);

	/**
* Delete all usuarios.
*
* @return the internal response
*/
	public InternalResponse deleteAllUsuarios();

	/**
* Fetch all usuarios.
*
* @return the list< usuario>
*/
	public InternalResultsResponse<Usuario> fetchAllUsuarios(Usuario  usuario);

	/**
* Fetch usuarios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Usuario> fetchUsuariosByRequest(UsuarioInquiryRequest request);

	/**
	 * Fetch condominio by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Condominio fetchCondominioById(FetchByIdRequest request);

	/**
* Insert condominio.
*
* @param condominio the condominio
*
* @return the internal response
*/
	public InternalResponse insertCondominio(Condominio condominio);

	/**
* Update condominio.
*
* @param condominio the condominio
*
* @return the internal response
*/
	public InternalResponse updateCondominio(Condominio condominio);

	/**
* Delete condominio.
*
* @param condominio the condominio
*
* @return the internal response
*/
	public InternalResponse deleteCondominioById(Condominio condominio);

	/**
* Delete all condominios.
*
* @return the internal response
*/
	public InternalResponse deleteAllCondominios();

	/**
* Fetch all condominios.
*
* @return the list< condominio>
*/
	public InternalResultsResponse<Condominio> fetchAllCondominios(Condominio  condominio);

	/**
* Fetch condominios by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Condominio> fetchCondominiosByRequest(CondominioInquiryRequest request);

	/**
	 * Fetch clinica by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Clinica fetchClinicaById(FetchByIdRequest request);

	/**
* Insert clinica.
*
* @param clinica the clinica
*
* @return the internal response
*/
	public InternalResponse insertClinica(Clinica clinica);

	/**
* Update clinica.
*
* @param clinica the clinica
*
* @return the internal response
*/
	public InternalResponse updateClinica(Clinica clinica);

	/**
* Delete clinica.
*
* @param clinica the clinica
*
* @return the internal response
*/
	public InternalResponse deleteClinicaById(Clinica clinica);

	/**
* Delete all clinicas.
*
* @return the internal response
*/
	public InternalResponse deleteAllClinicas();

	/**
* Fetch all clinicas.
*
* @return the list< clinica>
*/
	public InternalResultsResponse<Clinica> fetchAllClinicas(Clinica  clinica);

	/**
* Fetch clinicas by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Clinica> fetchClinicasByRequest(ClinicaInquiryRequest request);

	/**
	 * Fetch advocacia by id.
	 *
	 * @param request the request
* @return the internal results response
*/
	public Advocacia fetchAdvocaciaById(FetchByIdRequest request);

	/**
* Insert advocacia.
*
* @param advocacia the advocacia
*
* @return the internal response
*/
	public InternalResponse insertAdvocacia(Advocacia advocacia);

	/**
* Update advocacia.
*
* @param advocacia the advocacia
*
* @return the internal response
*/
	public InternalResponse updateAdvocacia(Advocacia advocacia);

	/**
* Delete advocacia.
*
* @param advocacia the advocacia
*
* @return the internal response
*/
	public InternalResponse deleteAdvocaciaById(Advocacia advocacia);

	/**
* Delete all advocacias.
*
* @return the internal response
*/
	public InternalResponse deleteAllAdvocacias();

	/**
* Fetch all advocacias.
*
* @return the list< advocacia>
*/
	public InternalResultsResponse<Advocacia> fetchAllAdvocacias(Advocacia  advocacia);

	/**
* Fetch advocacias by request.
*
* @param request the request
* @return the internal results response
*/
	public InternalResultsResponse<Advocacia> fetchAdvocaciasByRequest(AdvocaciaInquiryRequest request);

}
