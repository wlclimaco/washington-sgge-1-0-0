	/**
	 * Update cnae.
	 *
	 * @param cnae the cnae
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateCnae(Cnae cnae, InternalResultsResponse<?> response);

	/**
	 * Insert cnae.
	 *
	 * @param cnae the cnae
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertCnae(Cnae cnae, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete business cnae.
	 *
	 * @param cnae the cnae
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteBusinessCnae(Cnae cnae, InternalResultsResponse<?> response);

	/**
	 * Delete person cnae.
	 *
	 * @param cnae the cnae
	 * @param response the response
	 * @return the integer
	 */
	public Integer deletePersonCnae(Cnae cnae, InternalResultsResponse<?> response);

	/**
	 * Fetch cnae by parent.
	 *
	 * @param parentId the parent id
	 * @param parentType the parent type
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> fetchCnaeByParent(Integer parentId, BusinessTypeEnum parentType);

	/**
	 * Fetch cnae by id.
	 *
	 * @param id the id
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> fetchCnaeById(Integer id);

	/**
	 * Maintain cnae associations.
	 *
	 * @param cnaeList the cnae list
	 * @param parentId the parent id
	 * @param associateStatement the associate statement
	 * @param response the response
	 * @return the integer
	 */
	public Integer maintainCnaeAssociations(List<Cnae> cnaeList, Integer parentId, String associateStatement,
			InternalResultsResponse<?> response);package com.prosperitasglobal.sendsolv.dac;

import java.util.List;

import com.prosperitasglobal.cbof.dac.BusinessTypeEnum;
import com.prosperitasglobal.cbof.dac.Cnae;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Cnae;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ICnaeDAC.
 */
public interface ICnaeDAC
{

	/**
	 * Update cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> updateCnae(Cnae cnae);

	/**
	 * Insert cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> insertCnae(Cnae cnae);

	/**
	 * Delete cnae.
	 *
	 * @param cnae the cnae
	 * @return the internal response
	 */
	public InternalResponse deleteCnae(Cnae cnae);

	/**
	 * Fetch cnae by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cnae> fetchCnaeById(FetchByIdRequest request);

	/**
	 * Fetch all cnaes.
	 *
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> fetchAllCnaes();

	/**
	 * Fetch cnae by request.
	 *
	 * @param request the request
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(PagedInquiryRequest request);

}
