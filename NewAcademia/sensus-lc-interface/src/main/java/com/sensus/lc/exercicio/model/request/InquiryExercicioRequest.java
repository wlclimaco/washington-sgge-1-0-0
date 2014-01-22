package com.sensus.lc.exercicio.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.exercicio.model.Exercicio;

/**
 * The Class InquiryExercicioRequest.
 * 
 * @author Washington
 */
public class InquiryExercicioRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The exercicios. */
	private List<Exercicio> exercicios;

	/**
	 * Instantiates a new inquiry exercicio request.
	 */
	public InquiryExercicioRequest()
	{

	}

	/**
	 * Instantiates a new inquiry exercicio request.
	 * 
	 * @param exercicio the exercicio
	 */
	public InquiryExercicioRequest(Exercicio exercicio)
	{
		addExercicio(exercicio);
	}

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
	 * @param exercicios the new exercicios
	 */
	public void setExercicios(List<Exercicio> exercicios)
	{
		this.exercicios = exercicios;
	}

	/**
	 * Gets the first exercicio.
	 * 
	 * @return the first exercicio
	 */
	public Exercicio getFirstExercicio()
	{
		if ((getExercicios() != null) && !getExercicios().isEmpty())
		{
			return getExercicios().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the exercicio.
	 * 
	 * @param exercicio the exercicio
	 */
	public void addExercicio(Exercicio exercicio)
	{
		if (getExercicios() == null)
		{
			setExercicios(new ArrayList<Exercicio>());
		}

		getExercicios().add(exercicio);
	}

	@Override
	public String toString()
	{
		return "InquiryExercicioRequest [getExercicios()=" + getExercicios() + ", getFirstExercicio()="
				+ getFirstExercicio()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
