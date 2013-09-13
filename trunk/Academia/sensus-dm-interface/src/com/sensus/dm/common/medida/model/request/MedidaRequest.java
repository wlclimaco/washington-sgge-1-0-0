package com.sensus.dm.common.medida.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.medida.model.Medida;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class MedidaRequest.
 * 
 * @author Washington
 */
public class MedidaRequest extends TenantRequest
{
	/** The medidas. */
	private List<Medida> medidas;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new medida request.
	 */
	public MedidaRequest()
	{
	}

	/**
	 * Instantiates a new medida request.
	 * 
	 * @param medida the medida
	 */
	public MedidaRequest(Medida medida)
	{
		addMedida(medida);
	}

	/**
	 * Instantiates a new medida request.
	 * 
	 * @param userContext the user context
	 */
	public MedidaRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new medida request.
	 * 
	 * @param medida the medida
	 * @param userContext the user context
	 */
	public MedidaRequest(Medida medida, UserContext userContext)
	{
		addMedida(medida);
		setUserContext(userContext);
	}

	/**
	 * Gets the medidas.
	 * 
	 * @return the medidas
	 */
	public List<Medida> getMedidas()
	{
		return medidas;
	}

	/**
	 * Gets the first medida.
	 * 
	 * @return the first medida
	 */  
	public Medida getFirstMedida()
	{
		if (getMedidas() != null && !getMedidas().isEmpty())
		{
			return getMedidas().get(0);
		}

		return null;
	}

	/**
	 * Sets the medidas.
	 * 
	 * @param medidas the new medidas
	 */
	public void setMedidas(List<Medida> medidas)
	{
		this.medidas = medidas;
	}

	/**
	 * Adds the medida.
	 * 
	 * @param medida the medida
	 */
	public void addMedida(Medida medida)
	{
		if (getMedidas() == null)
		{
			setMedidas(new ArrayList<Medida>());
		}

		getMedidas().add(medida);
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
		return "MedidaRequest [getMedidas()=" + getMedidas() + ", getFirstMedida()=" + getFirstMedida()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}


