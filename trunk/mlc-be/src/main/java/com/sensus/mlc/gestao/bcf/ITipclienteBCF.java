package com.sensus.mlc.gestao.bcf;

import com.sensus.mlc.gestao.model.request.InquiryTipclienteRequest;
import com.sensus.mlc.gestao.model.request.TipclienteRequest;
import com.sensus.mlc.gestao.model.response.InquiryTipclienteResponse;
import com.sensus.mlc.gestao.model.response.TipclienteResponse;


/**
 * The Interface ITipclienteBCF.
 *
 * @author Washington.Costa
 */
public interface ITipclienteBCF
{

	/**
	 * Insert tipcliente.
	 *
	 * @param tipclienteRequest the tipcliente request
	 * @return the tipcliente response
	 */
	public TipclienteResponse insertTipcliente(TipclienteRequest tipclienteRequest);

	/**
	 * Update tipcliente.
	 *
	 * @param tipclienteRequest the tipcliente request
	 * @return the tipcliente response
	 */
	public TipclienteResponse updateTipcliente(TipclienteRequest tipclienteRequest);

	/**
	 * Delete tipcliente.
	 *
	 * @param tipclienteRequest the tipcliente request
	 * @return the tipcliente response
	 */
	public TipclienteResponse deleteTipcliente(TipclienteRequest tipclienteRequest);

	/**
	 * Fetch all tipcliente.
	 *
	 * @param inquiryTipclienteRequest the inquiryTipcliente request
	 * @return the inquiry tipcliente response
	 */
	public InquiryTipclienteResponse fetchAllTipcliente(InquiryTipclienteRequest inquiryTipclienteRequest);

	/**
	 * Fetch tipcliente by id.
	 *
	 * @param TipclienteRequest the tipcliente request
	 * @return the tipcliente response
	 */
	public TipclienteResponse fetchTipclienteById(TipclienteRequest tipclienteRequest);

}
