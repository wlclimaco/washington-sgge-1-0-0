package com.qat.samples.sysmgmt.controle.bai;

import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.response.AcessosResponse;
import com.qat.samples.sysmgmt.controle.model.response.ControleResponse;

/**
 * The Interface IControleBAI. (Business Area Interface - BAI)
 */
public interface IControleBAI
{

	/**
	 * Fetch all cidades.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	public ControleResponse fetchAllControles(ControleInquiryRequest request);

	/**
	 * Fetch all acessos.
	 * 
	 * @param request the request
	 * @return the controle response
	 */
	public AcessosResponse fetchAllAcessos(AcessosInquiryRequest request);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	public ControleResponse fetchControleByPage(ControleInquiryRequest request);

	/**
	 * Fetch cidades by request.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	public ControleResponse fetchControlesByRequest(ControleInquiryRequest request);

	/**
	 * Fetch controle by action.
	 * 
	 * @param request the request
	 * @return the controle response
	 */
	public ControleResponse fetchControleByAction(ControleInquiryRequest request);

}
