package com.sensus.lc.exercicio.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.lc.exercicio.model.Exercicio;

/**
 * The Class ExercicioResponse.
 * 
 * @author - Washington
 */
public class ExercicioResponse extends Response
{

	/** The exercicios. */
	@XmlElement(nillable = true)
	private List<Exercicio> exercicios;

	/** The is exercicio name unique. */
	private Boolean isExercicioNameUnique;

	/** The process id. */
	private Integer processId;

	/**
	 * Gets the exercicios.
	 * 
	 * @return the exercicios
	 */
	public List<Exercicio> getExercicios()
	{
		return exercicios;
	}

	/**
	 * Sets the exercicios.
	 * 
	 * @param exercicioObjects the new exercicios
	 */
	public void setExercicios(List<Exercicio> exercicioObjects)
	{
		exercicios = exercicioObjects;
	}

	/**
	 * Gets the checks if is exercicio name unique.
	 * 
	 * @return the checks if is exercicio name unique
	 */
	public Boolean getIsExercicioNameUnique()
	{
		return isExercicioNameUnique;
	}

	/**
	 * Sets the checks if is exercicio name unique.
	 * 
	 * @param isExercicioNameUnique the new checks if is exercicio name unique
	 */
	public void setIsExercicioNameUnique(Boolean isExercicioNameUnique)
	{
		this.isExercicioNameUnique = isExercicioNameUnique;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	@Override
	public String toString()
	{
		return "ExercicioResponse [getExercicios()=" + getExercicios() + ", getMessageIterator()="
				+ getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsExercicioNameUnique()="
				+ getIsExercicioNameUnique() + "]";
	}

}
