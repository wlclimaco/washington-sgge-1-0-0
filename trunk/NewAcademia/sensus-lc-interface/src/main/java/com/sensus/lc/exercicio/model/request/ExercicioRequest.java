package com.sensus.lc.exercicio.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.lc.exercicio.model.Exercicio;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Class ExercicioRequest.
 * 
 * @author Washington
 */
public class ExercicioRequest extends TenantRequest
{
	/** The exercicios. */
	private List<Exercicio> exercicios;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new exercicio request.
	 */
	public ExercicioRequest()
	{
	}

	/**
	 * Instantiates a new exercicio request.
	 * 
	 * @param exercicio the exercicio
	 */
	public ExercicioRequest(Exercicio exercicio)
	{
		addExercicio(exercicio);
	}

	/**
	 * Instantiates a new exercicio request.
	 * 
	 * @param userContext the user context
	 */
	public ExercicioRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new exercicio request.
	 * 
	 * @param exercicio the exercicio
	 * @param userContext the user context
	 */
	public ExercicioRequest(Exercicio exercicio, UserContext userContext)
	{
		addExercicio(exercicio);
		setUserContext(userContext);
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
	 * Gets the first exercicio.
	 * 
	 * @return the first exercicio
	 */
	public Exercicio getFirstExercicio()
	{
		if ((getExercicios() != null) && !getExercicios().isEmpty())
		{
			return getExercicios().get(0);
		}

		return null;
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

	/**
	 * Gets the retrieve history.
	 * 
	 * @return the retrieve history
	 */
	public Boolean getRetrieveHistory()
	{
		return retrieveHistory;
	}

	/**
	 * Sets the retrieve history.
	 * 
	 * @param retrieveHistory the new retrieve history
	 */
	public void setRetrieveHistory(Boolean retrieveHistory)
	{
		this.retrieveHistory = retrieveHistory;
	}

	@Override
	public String toString()
	{
		return "ExercicioRequest [getExercicios()=" + getExercicios() + ", getFirstExercicio()=" + getFirstExercicio()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}
