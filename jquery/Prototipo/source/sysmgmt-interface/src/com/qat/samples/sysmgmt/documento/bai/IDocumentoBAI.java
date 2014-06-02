package com.qat.samples.sysmgmt.documento.bai;

import com.qat.samples.sysmgmt.documento.model.request.DocumentoMaintenanceRequest;
import com.qat.samples.sysmgmt.documento.model.response.DocumentoResponse;
import com.qat.samples.sysmgmt.model.request.FetchAllRequest;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;

/**
 * The Interface IDocumentoBAI. (Business Area Interface - BAI)
 */
public interface IDocumentoBAI
{

	/**
	 * Insert documento.
	 * 
	 * @param request the request
	 * @return the documento paged response
	 */
	public DocumentoResponse insertDocumento(DocumentoMaintenanceRequest request);

	/**
	 * Update documento.
	 * 
	 * @param request the request
	 * @return the documento paged response
	 */
	public DocumentoResponse updateDocumento(DocumentoMaintenanceRequest request);

	/**
	 * Delete documento.
	 * 
	 * @param request the request
	 * @return the documento paged response
	 */
	public DocumentoResponse deleteDocumento(DocumentoMaintenanceRequest request);

	/**
	 * Fetch all documentos.
	 * 
	 * @param request the request
	 * @return the documento response
	 */
	public DocumentoResponse fetchAllDocumentos(FetchAllRequest request);

	/**
	 * Refresh documentos.
	 * 
	 * @param request the request
	 * @return the documento paged response
	 */
	public DocumentoResponse refreshDocumentos(RefreshRequest request);

	/**
	 * Fetch documento by id.
	 * 
	 * @param request the request
	 * @return the documento response
	 */
	public DocumentoResponse fetchDocumentoById(FetchByIdRequest request);

	/**
	 * Fetch documentos by request.
	 * 
	 * @param request the request
	 * @return the documento paged response
	 */
	public DocumentoResponse fetchDocumentosByRequest(PagedInquiryRequest request);

}
