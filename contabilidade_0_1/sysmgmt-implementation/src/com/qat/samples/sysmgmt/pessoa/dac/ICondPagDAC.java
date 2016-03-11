package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.condpag.CondPag;
import com.qat.samples.sysmgmt.condpag.CondPagPessoa;
import com.qat.samples.sysmgmt.condpag.TipoPag;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface ICondPagDAC.
 */
public interface ICondPagDAC
{

	/**
	 * Update forma pg.
	 * 
	 * @param condPag the forma pg
	 * @return the integer
	 */
	public Integer updateCondPag(CondPag condPag);

	/**
	 * Insert forma pg.
	 * 
	 * @param condPag the forma pg
	 * @return the integer
	 */
	public Integer insertCondPag(CondPag condPag);

	/**
	 * Delete forma pg.
	 * 
	 * @param condPag the forma pg
	 * @return the integer
	 */
	public Integer deleteCondPag(CondPag condPag);

	/**
	 * Fetch forma pg by id.
	 * 
	 * @param id the id
	 * @return the internal results response
	 */
	public InternalResultsResponse<CondPag> fetchCondPagById(Integer id);

	/**
	 * Fetch forma pg by request.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<CondPag> fetchCondPagByRequest(PagedInquiryRequest request);

	/**
	 * Update forma pg pessoa.
	 * 
	 * @param condPag the forma pg
	 * @return the integer
	 */
	public Integer updateCondPagPessoa(CondPagPessoa condPag);

	/**
	 * Insert forma pg pessoa.
	 * 
	 * @param condPag the forma pg
	 * @return the integer
	 */
	public Integer insertCondPagPessoa(CondPagPessoa condPag);

	/**
	 * Delete forma pg pessoa.
	 * 
	 * @param condPag the forma pg
	 * @return the integer
	 */
	public Integer deleteCondPagPessoa(CondPagPessoa condPag);

	/**
	 * Update tipo pag.
	 * 
	 * @param tipoPag the tipo pag
	 * @return the integer
	 */
	public Integer updateTipoPag(TipoPag tipoPag);

	/**
	 * Insert tipo pag.
	 * 
	 * @param tipoPag the tipo pag
	 * @return the integer
	 */
	public Integer insertTipoPag(TipoPag tipoPag);

	/**
	 * Delete tipo pag.
	 * 
	 * @param tipoPag the tipo pag
	 * @return the integer
	 */
	public Integer deleteTipoPag(TipoPag tipoPag);

}
