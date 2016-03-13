package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.clinica.Consulta;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

/**
 * The Interface IConsultaDAC.
 */
public interface IConsultaDAC
{

	/**
	 * Update consulta.
	 * 
	 * @param consulta the consulta
	 * @return the internal results response< consulta>
	 */
	public Integer updateConsulta(Consulta consulta);

	/**
	 * Insert consulta.
	 * 
	 * @param consulta the consulta
	 * @return the internal results response< consulta>
	 */
	public Integer insertConsulta(Consulta consulta);

	/**
	 * Delete consulta.
	 * 
	 * @param consulta the consulta
	 * @return the internal response
	 */
	public Integer deleteConsulta(Consulta consulta);

	/**
	 * Fetch consulta by id.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Consulta> fetchConsultaById(FetchByIdRequest request);

	/**
	 * Fetch all consultas.
	 * 
	 * @return the internal results response< consulta>
	 */
	public InternalResultsResponse<Consulta> fetchAllConsultas();

	/**
	 * Fetch consulta by request.
	 * 
	 * @param request the request
	 * @return the internal results response< consulta>
	 */
	public InternalResultsResponse<Consulta> fetchConsultaByRequest(PagedInquiryRequest request);

	/**
	 * Update consulta.
	 * 
	 * @param consulta the consulta
	 * @return the internal results response< consulta>
	 */
	public Integer updateConsultaPessoa(ConsultaPessoa consulta);

	/**
	 * Insert consulta.
	 * 
	 * @param consulta the consulta
	 * @return the internal results response< consulta>
	 */
	public Integer insertConsultaPessoa(ConsultaPessoa consulta);

	/**
	 * Delete consulta.
	 * 
	 * @param consulta the consulta
	 * @return the internal response
	 */
	public Integer deleteConsultaPessoa(ConsultaPessoa consulta);

}
