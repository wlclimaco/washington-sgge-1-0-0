package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.Banco;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IBancoDAC.
 */
public interface IBancoDAC
{

	/**
	 * Update banco.
	 *
	 * @param banco the banco
	 * @return the internal results response< banco>
	 */
	public InternalResultsResponse<Banco> updateBanco(Banco banco);

	/**
	 * Insert banco.
	 *
	 * @param banco the banco
	 * @return the internal results response< banco>
	 */
	public InternalResultsResponse<Banco> insertBanco(Banco banco);

	/**
	 * Delete banco.
	 *
	 * @param banco the banco
	 * @return the internal response
	 */
	public InternalResponse deleteBanco(Banco banco);

	/**
	 * Fetch banco by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Banco> fetchBancoById(FetchByIdRequest request);

	/**
	 * Fetch all bancos.
	 *
	 * @return the internal results response< banco>
	 */
	public InternalResultsResponse<Banco> fetchAllBancos();

	/**
	 * Fetch banco by request.
	 *
	 * @param request the request
	 * @return the internal results response< banco>
	 */
	public InternalResultsResponse<Banco> fetchBancoByRequest(PagedInquiryRequest request);

}
