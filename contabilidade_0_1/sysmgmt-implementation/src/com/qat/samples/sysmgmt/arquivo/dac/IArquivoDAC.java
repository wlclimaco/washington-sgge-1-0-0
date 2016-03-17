package com.qat.samples.sysmgmt.arquivo.dac;

import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.arquivo.Arquivo;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IArquivoDAC.
 */
public interface IArquivoDAC
{

	/**
	 * Update arquivo.
	 * 
	 * @param arquivo the arquivo
	 * @return the internal results response< arquivo>
	 */
	public InternalResultsResponse<Arquivo> updateArquivo(Arquivo arquivo);

	/**
	 * Insert arquivo.
	 * 
	 * @param arquivo the arquivo
	 * @return the internal results response< arquivo>
	 */
	public InternalResultsResponse<Arquivo> insertArquivo(Arquivo arquivo);

	/**
	 * Delete arquivo.
	 * 
	 * @param arquivo the arquivo
	 * @return the internal response
	 */
	public InternalResponse deleteArquivo(Arquivo arquivo);

	/**
	 * Fetch arquivo by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Arquivo> fetchArquivoById(FetchByIdRequest request);

	/**
	 * Fetch all arquivos.
	 * 
	 * @return the internal results response< arquivo>
	 */
	public InternalResultsResponse<Arquivo> fetchAllArquivos();

	/**
	 * Fetch arquivo by request.
	 * 
	 * @param request the request
	 * @return the internal results response< arquivo>
	 */
	public InternalResultsResponse<Arquivo> fetchArquivoByRequest(PagedInquiryRequest request);

}
