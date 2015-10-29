package com.qat.samples.sysmgmt.util.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.cnae.Cnae;
import com.qat.samples.sysmgmt.cnae.CnaeEmpresa;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICnaeDAC.
 */
public interface ICnaeDAC
{
	/**
	 * Update cnae.
	 * 
	 * @param cnae the cnae
	 * @param response the response
	 * @return the integer
	 */
	public Integer updateCnae(Cnae cnae);

	/**
	 * Insert cnae.
	 * 
	 * @param cnae the cnae
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertCnae(Cnae cnae);

	/**
	 * Delete business cnae.
	 * 
	 * @param cnae the cnae
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteCnae(Cnae cnae);

	/**
	 * Fetch cnae by id.
	 * 
	 * @param id the id
	 * @return the internal results response< cnae>
	 */
	public InternalResultsResponse<Cnae> fetchCnaeById(Integer id);

	/**
	 * Fetch cnae by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Cnae> fetchCnaeByRequest(PagedInquiryRequest request);

	/**
	 * Update cnae pessoa.
	 * 
	 * @param cnae the cnae
	 * @return the integer
	 */
	public Integer updateCnaeEmpresa(CnaeEmpresa cnae);

	/**
	 * Insert cnae.
	 * 
	 * @param cnae the cnae
	 * @return the integer
	 */
	public Integer insertCnaeEmpresa(CnaeEmpresa cnae);

	/**
	 * Delete business cnae.
	 * 
	 * @param cnae the cnae
	 * @return the integer
	 */
	public Integer deleteCnaeEmpresa(CnaeEmpresa cnae);

}