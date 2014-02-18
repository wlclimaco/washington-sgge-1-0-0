package com.sensus.lc.exercicio.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.exercicio.model.Exercicio;
import com.sensus.lc.exercicio.model.request.ExercicioRequest;
import com.sensus.lc.exercicio.model.request.InquiryExercicioRequest;

/**
 * The Interface IExercicioBCL.
 * 
 * @author Washington.
 * 
 */
public interface IExercicioBCL
{

	/**
	 * Fetch all exercicios.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> fetchAllExercicios(InquiryExercicioRequest inquiryPaginationRequest);

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
	 * @return the internal response
	 */
	InternalResultsResponse<Exercicio> updateExercicio(ExercicioRequest exercicioRequest);

	/**
	 * Fetch exercicios by device.
	 * 
	 * @param deviceRequest the device request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> fetchExerciciosById(InquiryExercicioRequest InquiryExercicioRequest);

	/**
	 * Fetch all exercicio by user.
	 * 
	 * @param InquiryExercicioRequest the inquiry exercicio request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> fetchAllExercicioByUser(InquiryExercicioRequest InquiryExercicioRequest);

	/**
	 * Insert exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the internal results response
	 */
	InternalResultsResponse<Exercicio> insertExercicio(ExercicioRequest exercicioRequest);

}
