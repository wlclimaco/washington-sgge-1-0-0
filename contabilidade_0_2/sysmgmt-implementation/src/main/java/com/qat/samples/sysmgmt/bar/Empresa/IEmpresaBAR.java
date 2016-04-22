package com.qat.samples.sysmgmt.bar.Empresa;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
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
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request);

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
	public InternalResultsResponse<Filial> fetchFilialById(FetchByIdRequest request);

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
	public InternalResultsResponse<Deposito> fetchDepositoById(FetchByIdRequest request);

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
	public InternalResultsResponse<Usuario> fetchUsuarioById(FetchByIdRequest request);

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

}
