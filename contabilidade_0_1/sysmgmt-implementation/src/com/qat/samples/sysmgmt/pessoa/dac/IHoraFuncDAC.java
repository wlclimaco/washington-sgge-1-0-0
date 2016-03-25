package com.qat.samples.sysmgmt.pessoa.dac;

import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.dp.HorarioFunc;
import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISocioDAC.
 */
public interface IHoraFuncDAC
{

	public Integer updateHorarioFunc(HorarioFunc horarioFunc, InternalResultsResponse<?> response);

	/**
	 * Insert horario func.
	 * 
	 * @param horarioFunc the horario func
	 * @param statementName the statement name
	 * @param response the response
	 * @return the integer
	 */
	public Integer insertHorarioFunc(HorarioFunc horarioFunc, String statementName, InternalResultsResponse<?> response);

	/**
	 * Delete horario func.
	 * 
	 * @param horarioFunc the horario func
	 * @param response the response
	 * @return the integer
	 */
	public Integer deleteHorarioFunc(HorarioFunc horarioFunc, InternalResultsResponse<?> response);

	public InternalResultsResponse<HorarioFunc> fetchHorarioFuncById(FetchByIdRequest request);

	public InternalResultsResponse<HorarioFunc> fetchHorarioFuncByRequest(PagedInquiryRequest request);

}
