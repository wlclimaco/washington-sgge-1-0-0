package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.clinica.Especialidade;
import com.qat.samples.sysmgmt.clinica.EspecialidadePessoa;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IEspecialidadeDAC.
 */
public interface IEspecialidadeDAC
{

	/**
	 * Update especialidade.
	 * 
	 * @param especialidade the especialidade
	 * @return the internal results response< especialidade>
	 */
	public Integer updateEspecialidade(Especialidade especialidade);

	/**
	 * Insert especialidade.
	 * 
	 * @param especialidade the especialidade
	 * @return the internal results response< especialidade>
	 */
	public Integer insertEspecialidade(Especialidade especialidade);

	/**
	 * Delete especialidade.
	 * 
	 * @param especialidade the especialidade
	 * @return the internal response
	 */
	public Integer deleteEspecialidade(Especialidade especialidade);

	/**
	 * Fetch especialidade by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Especialidade> fetchEspecialidadeById(FetchByIdRequest request);

	/**
	 * Fetch all especialidades.
	 * 
	 * @return the internal results response< especialidade>
	 */
	public InternalResultsResponse<Especialidade> fetchAllEspecialidades();

	/**
	 * Fetch especialidade by request.
	 * 
	 * @param request the request
	 * @return the internal results response< especialidade>
	 */
	public InternalResultsResponse<Especialidade> fetchEspecialidadeByRequest(PagedInquiryRequest request);

	/**
	 * Update especialidade.
	 * 
	 * @param especialidade the especialidade
	 * @return the internal results response< especialidade>
	 */
	public Integer updateEspecialidadePessoa(EspecialidadePessoa especialidade);

	/**
	 * Insert especialidade.
	 * 
	 * @param especialidade the especialidade
	 * @return the internal results response< especialidade>
	 */
	public Integer insertEspecialidadePessoa(EspecialidadePessoa especialidade);

	/**
	 * Delete especialidade.
	 * 
	 * @param especialidade the especialidade
	 * @return the internal response
	 */
	public Integer deleteEspecialidadePessoa(EspecialidadePessoa especialidade);

}
