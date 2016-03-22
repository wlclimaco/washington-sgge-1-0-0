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
	public Integer updateConvenio(Convenio convenio, InternalResultsResponse<?> response);

	/**
	 * Insert convenio.
	 * 
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer insertConvenio(Convenio convenio, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete convenio.
	 * 
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer deleteConvenio(Convenio convenio, InternalResultsResponse<?> response);

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
	public Integer insertConvenioPessoa(ConvenioPessoa convenio, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete convenio pessoa.
	 * 
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer deleteConvenioPessoa(ConvenioPessoa convenio, InternalResultsResponse<?> response);

	/**
	 * Update convenio pessoa.
	 * 
	 * @param convenio the convenio
	 * @return the integer
	 */
	public Integer updateConvenioPessoa(ConvenioPessoa convenio, InternalResultsResponse<?> response);
}