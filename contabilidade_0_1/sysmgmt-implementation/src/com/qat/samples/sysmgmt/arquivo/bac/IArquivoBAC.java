package com.qat.samples.sysmgmt.arquivo.bac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.arquivo.Arquivo;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoInquiryRequest;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IArquivoBAC.
 */
public interface IArquivoBAC
{

	/**
	 * Insert arquivo.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> insertArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Update arquivo.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> updateArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Delete arquivo.
	 * 
	 * @param request the request
	 * @return the internal response
	 */
	public InternalResponse deleteArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Fetch arquivo by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> fetchArquivoById(FetchByIdRequest request);

	/**
	 * Fetch arquivo by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> fetchArquivoByRequest(ArquivoInquiryRequest request);

}
