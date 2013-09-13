package com.sensus.dm.common.exercicio.bcf;

import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.exercicio.model.request.ExercicioRequest;
import com.sensus.dm.common.exercicio.model.request.InquiryExercicioRequest;
import com.sensus.dm.common.exercicio.model.response.ExercicioResponse;
import com.sensus.dm.common.exercicio.model.response.InquiryExercicioResponse;

/**
 * The Interface IExercicioBCF.
 * 
 * @author Washington.
 * 
 */
public interface IExercicioBCF
{

	/**
	 * Fetch all exercicios.
	 * 
	 * @param inquiryExercicioRequest the inquiry exercicio request
	 * @return the inquiry exercicio response
	 */
	InquiryExercicioResponse fetchAllExercicios(InquiryExercicioRequest inquiryExercicioRequest);

	/**
	 * Fetch exercicio by id.
	 * 
	 * @param inquiryExercicioRequest the inquiry exercicio request
	 * @return the exercicio response
	 */
	ExercicioResponse fetchExercicioById(InquiryExercicioRequest inquiryExercicioRequest);

	/**
	 * Fetch exercicio by name.
	 * 
	 * @param inquiryExercicioRequest the inquiry exercicio request
	 * @return the exercicio response
	 */
	ExercicioResponse fetchExercicioByName(InquiryExercicioRequest inquiryExercicioRequest);

	/**
	 * Insert exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the exercicio response
	 */
	ExercicioResponse insertExercicio(ExercicioRequest exercicioRequest);

	/**
	 * Update exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the exercicio response
	 */
	ExercicioResponse updateExercicio(ExercicioRequest exercicioRequest);

	/**
	 * Delete exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the exercicio response
	 */
	ExercicioResponse deleteExercicio(ExercicioRequest exercicioRequest);

	/**
	 * Fetch devices by exercicio.
	 * 
	 * @param exercicioRequest the exercicio request
	 * @return the device response
	 */
	DeviceResponse fetchDevicesByExercicio(ExercicioRequest exercicioRequest);

}
