package com.qat.samples.sysmgmt.controle.bac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.controle.model.Controle;
import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * The Interface ICidadeBAC. (Business Area Component - BAC)
 */
public interface IControleBAC
{

	public InternalResultsResponse<Controle> fetchAllControles(ControleInquiryRequest request);

	/**
	 * Fetch all acessos.
	 * 
	 * @param request the request
	 * @return the controle response
	 */
	public InternalResultsResponse<ControleAcess> fetchAllAcessos(AcessosInquiryRequest request);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	public InternalResultsResponse<Controle> fetchControleByPage(ControleInquiryRequest request);

	/**
	 * Fetch cidades by request.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	public InternalResultsResponse<Controle> fetchControlesByRequest(ControleInquiryRequest request);

	/**
	 * Fetch controle by action.
	 * 
	 * @param request the request
	 * @return the controle response
	 */
	public InternalResultsResponse<Controle> fetchControleByAction(ControleInquiryRequest request);
}
