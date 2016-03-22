package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.entidade.Usuario;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IUsuarioDAC.
 */
public interface IUsuarioDAC
{

	/**
	 * Update usuario.
	 * 
	 * @param usuario the usuario
	 * @return the internal results response< usuario>
	 */
	public Integer updateUsuario(Usuario usuario, InternalResultsResponse<?> response);

	/**
	 * Insert usuario.
	 * 
	 * @param usuario the usuario
	 * @return the internal results response< usuario>
	 */
	public Integer insertUsuario(Usuario usuario, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete usuario.
	 * 
	 * @param usuario the usuario
	 * @return the internal response
	 */
	public Integer deleteUsuario(Usuario usuario, InternalResultsResponse<?> response);

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
	public InternalResultsResponse<Usuario> fetchAllUsuarios();

	/**
	 * Fetch usuario by request.
	 * 
	 * @param request the request
	 * @return the internal results response< usuario>
	 */
	public InternalResultsResponse<Usuario> fetchUsuarioByRequest(PagedInquiryRequest request);

}
