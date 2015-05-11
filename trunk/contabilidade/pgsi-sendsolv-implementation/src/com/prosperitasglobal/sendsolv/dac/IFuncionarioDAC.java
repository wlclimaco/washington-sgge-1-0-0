package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.Funcionario;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface IFuncionarioDAC.
 */
public interface IFuncionarioDAC
{

	/**
	 * Update funcionario.
	 *
	 * @param funcionario the funcionario
	 * @return the internal results response< funcionario>
	 */
	public InternalResultsResponse<Funcionario> updateFuncionario(Funcionario funcionario);

	/**
	 * Insert funcionario.
	 *
	 * @param funcionario the funcionario
	 * @return the internal results response< funcionario>
	 */
	public InternalResultsResponse<Funcionario> insertFuncionario(Funcionario funcionario);

	/**
	 * Delete funcionario.
	 *
	 * @param funcionario the funcionario
	 * @return the internal response
	 */
	public InternalResponse deleteFuncionario(Funcionario funcionario);

	/**
	 * Fetch frequency based event by id.
	 *
	 * @param id the id
	 * @return The {@link InternalResultsResponse} containing the {@link java.util.List} of {@link FrequencyBasedEvent}
	 *         collection.
	 */
	public InternalResultsResponse<FrequencyBasedEvent> fetchFrequencyBasedEventById(Integer id);

	/**
	 * Fetch funcionario by id.
	 *
	 * @param request the request
	 * @return the internal results response
	 */
	public InternalResultsResponse<Funcionario> fetchFuncionarioById(FetchByIdRequest request);

	/**
	 * Fetch all funcionarios.
	 *
	 * @return the internal results response< funcionario>
	 */
	public InternalResultsResponse<Funcionario> fetchAllFuncionarios();

	/**
	 * Fetch funcionario by request.
	 *
	 * @param request the request
	 * @return the internal results response< funcionario>
	 */
	public InternalResultsResponse<Funcionario> fetchFuncionarioByRequest(PagedInquiryRequest request);

}
