package com.qat.samples.sysmgmt.arquivo.bai;

import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoInquiryRequest;
import com.qat.samples.sysmgmt.arquivo.model.request.ArquivoMaintenanceRequest;
import com.qat.samples.sysmgmt.arquivo.model.response.ArquivoResponse;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface IArquivoBAI.
 * 
 * @author abarros
 * @version 1.0
 * @created 22-Jul-2014 10:24:55 AM
 */
public interface IArquivoBAI
{

	/**
	 * Insert location.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ArquivoResponse insertArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Update location.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ArquivoResponse updateArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Delete location.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ArquivoResponse deleteArquivo(ArquivoMaintenanceRequest request);

	/**
	 * Fetch location by id.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ArquivoResponse fetchArquivoById(FetchByIdRequest request);

	/**
	 * Fetch location by request.
	 * 
	 * @param request the request
	 * @return the location response
	 */
	public ArquivoResponse fetchArquivoByRequest(ArquivoInquiryRequest request);

}