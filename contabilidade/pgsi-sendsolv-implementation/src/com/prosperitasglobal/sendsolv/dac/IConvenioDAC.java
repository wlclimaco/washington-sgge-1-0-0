package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.Convenio;
import com.prosperitasglobal.sendsolv.model.ConvenioPessoa;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IConvenioDAC.
 */
public interface IConvenioDAC
{

	/**
	 * Update convenio.
	 *
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer updateConvenio(Convenio convenio);

	/**
	 * Insert convenio.
	 *
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer insertConvenio(Convenio convenio);

	/**
	 * Delete convenio.
	 *
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer deleteConvenio(Convenio convenio);

	/**
	 * Fetch empresa by request.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Convenio> fetchConvenioByRequest(PagedInquiryRequest request);

	/**
	 * Fetch convenio by id.
	 *
	 * @param id the id
	 * @return the internal results response
	 */
	public InternalResultsResponse<Convenio> fetchConvenioById(Integer id);

	/**
	 * Insert convenio pessoa.
	 *
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer insertConvenioPessoa(ConvenioPessoa convenio);

	/**
	 * Delete convenio pessoa.
	 *
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer deleteConvenioPessoa(ConvenioPessoa convenio);

	/**
	 * Update convenio pessoa.
	 *
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer updateConvenioPessoa(ConvenioPessoa convenio);
}