package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.clinica.Exame;
import com.qat.samples.sysmgmt.clinica.ExamePessoa;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IExameDAC.
 */
public interface IExameDAC
{

	/**
	 * Update exame.
	 * 
	 * @param exame the exame
	 * @return the internal results response< exame>
	 */
	public Integer updateExame(Exame exame, InternalResultsResponse<?> response);

	/**
	 * Insert exame.
	 * 
	 * @param exame the exame
	 * @return the internal results response< exame>
	 */
	public Integer insertExame(Exame exame, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete exame.
	 * 
	 * @param exame the exame
	 * @return the internal response
	 */
	public Integer deleteExame(Exame exame, InternalResultsResponse<?> response);

	/**
	 * Fetch exame by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Exame> fetchExameById(FetchByIdRequest request);

	/**
	 * Fetch all exames.
	 * 
	 * @return the internal results response< exame>
	 */
	public InternalResultsResponse<Exame> fetchAllExames();

	/**
	 * Fetch exame by request.
	 * 
	 * @param request the request
	 * @return the internal results response< exame>
	 */
	public InternalResultsResponse<Exame> fetchExameByRequest(PagedInquiryRequest request);

	/**
	 * Update exame.
	 * 
	 * @param exame the exame
	 * @return the internal results response< exame>
	 */
	public Integer updateExamePessoa(ExamePessoa exame, InternalResultsResponse<?> response);

	/**
	 * Insert exame.
	 * 
	 * @param exame the exame
	 * @return the internal results response< exame>
	 */
	public Integer insertExamePessoa(ExamePessoa exame, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete exame.
	 * 
	 * @param exame the exame
	 * @return the internal response
	 */
	public Integer deleteExamePessoa(ExamePessoa exame, InternalResultsResponse<?> response);

}
