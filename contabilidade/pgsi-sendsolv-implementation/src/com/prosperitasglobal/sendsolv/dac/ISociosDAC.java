package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.sendsolv.model.Socio;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ISocioDAC.
 */
public interface ISociosDAC
{

	public Integer updateSocio(Socio Socio, InternalResultsResponse<?> response);

	/**
	 * Insert Socio.
	 *
	 * @param Socio the Socio
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertSocio(Socio Socio, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business Socio.
	 *
	 * @param Socio the Socio
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessSocio(Socio Socio, InternalResultsResponse<?> response);

	/**
	 * Delete person Socio.
	 *
	 * @param Socio the Socio
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonSocio(Socio Socio, InternalResultsResponse<?> response);

	/**
	 * Fetch Socio by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< Socio>
	 */
	public InternalResultsResponse<Socio> fetchSocioByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch Socio by id.
	 *
	 * @param id the id
	 * @return the internal results response< Socio>
	 */
	public InternalResultsResponse<Socio> fetchSocioById(Integer id);

	public InternalResultsResponse<Socio> fetchSocioByRequest(PagedInquiryRequest request);

}
