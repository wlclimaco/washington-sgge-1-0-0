package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.convenio.Convenio;
import com.qat.samples.sysmgmt.convenio.ConvenioPessoa;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

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