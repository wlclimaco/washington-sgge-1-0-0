package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Arquivo;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

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
