package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.HorarioFunc;
import com.qat.framework.model.response.InternalResultsResponse;

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

}
