package com.qat.samples.sysmgmt.controle.dac;

import java.util.List;

import com.qat.samples.sysmgmt.controle.model.Controle;
import com.qat.samples.sysmgmt.controle.model.request.AcessosInquiryRequest;
import com.qat.samples.sysmgmt.controle.model.request.ControleInquiryRequest;
import com.qat.samples.sysmgmt.util.ControleAcess;

/**
 * The Interface ICidadeDAC. (Data Access Component - DAC)
 */
public interface IControleDAC
{

	public List<Controle> fetchAllControles(ControleInquiryRequest request);

	/**
	 * Fetch all acessos.
	 * 
	 * @param request the request
	 * @return the controle response
	 */
	public List<ControleAcess> fetchAllAcessos(AcessosInquiryRequest request);

	/**
	 * Fetch cidade by id.
	 * 
	 * @param request the request
	 * @return the cidade response
	 */
	public List<Controle> fetchControleByPage(ControleInquiryRequest request);

	/**
	 * Fetch cidades by request.
	 * 
	 * @param request the request
	 * @return the cidade paged response
	 */
	public List<Controle> fetchControlesByRequest(ControleInquiryRequest request);

	/**
	 * Fetch controle by action.
	 * 
	 * @param request the request
	 * @return the controle response
	 */
	public List<Controle> fetchControleByAction(ControleInquiryRequest request);

}
