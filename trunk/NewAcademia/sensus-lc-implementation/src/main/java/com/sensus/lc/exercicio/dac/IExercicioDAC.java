package com.sensus.lc.exercicio.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.exercicio.model.Exercicio;
import com.sensus.lc.exercicio.model.request.ExercicioRequest;
import com.sensus.lc.exercicio.model.request.InquiryExercicioRequest;

/**
 * The Interface IExercicioDAC.
 * 
 * @author - Washington.
 */
public interface IExercicioDAC
{
	/**
	 * Fetch all exercicios.
	 * 
	 * @param inquiryExercicioRequest the inquiry exercicio request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> fetchAllExercicios(InquiryExercicioRequest inquiryExercicioRequest);

	/**
	 * Fetch all exercicio by user.
	 * 
	 * @param inquiryExercicioRequest the inquiry exercicio request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> fetchAllExercicioByUser(InquiryExercicioRequest inquiryExercicioRequest);

	/**
	 * Fetch devices by exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> fetchExercicioById(InquiryExercicioRequest inquiryExercicioRequest);

	/**
	 * Fetch devices by exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> fetchExerciciosByName(ExercicioRequest exercicioRequest);

	/**
	 * Insert exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> insertExercicio(ExercicioRequest exercicioRequest);

	/**
	 * Delete exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the internal response
	 */
	InternalResponse deleteExercicio(ExercicioRequest exercicioRequest);

	/**
	 * Update exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> updateExercicio(ExercicioRequest exercicioRequest);

}
