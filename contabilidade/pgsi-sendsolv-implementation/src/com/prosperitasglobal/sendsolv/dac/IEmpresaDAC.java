package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Empresa;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IEmpresaDAC.
 */
public interface IEmpresaDAC
{

	/**
	 * Update empresa.
	 *
	 * @param empresa the empresa
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> updateEmpresa(Empresa empresa);

	/**
	 * Insert empresa.
	 *
	 * @param empresa the empresa
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> insertEmpresa(Empresa empresa);

	/**
	 * Delete empresa.
	 *
	 * @param empresa the empresa
	 * @return the internal response
	 */
	public InternalResponse deleteEmpresa(Empresa empresa);

	/**
	 * Fetch empresa by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Empresa> fetchEmpresaById(FetchByIdRequest request);

	/**
	 * Fetch empresa by request.
	 *
	 * @param request the request
	 * @return the internal results response< empresa>
	 */
	public InternalResultsResponse<Empresa> fetchEmpresaByRequest(PagedInquiryRequest request);

}
