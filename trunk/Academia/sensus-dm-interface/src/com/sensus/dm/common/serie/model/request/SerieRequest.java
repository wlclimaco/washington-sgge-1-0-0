package com.sensus.dm.common.serie.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.serie.model.Serie;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class SerieRequest.
 * 
 * @author Washington
 */
public class SerieRequest extends TenantRequest
{
	/** The series. */
	private List<Serie> series;

	/** The retrieve history. */
	private Boolean retrieveHistory = false;

	/**
	 * Instantiates a new serie request.
	 */
	public SerieRequest()
	{
	}

	/**
	 * Instantiates a new serie request.
	 * 
	 * @param serie the serie
	 */
	public SerieRequest(Serie serie)
	{
		addSerie(serie);
	}

	/**
	 * Instantiates a new serie request.
	 * 
	 * @param userContext the user context
	 */
	public SerieRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Instantiates a new serie request.
	 * 
	 * @param serie the serie
	 * @param userContext the user context
	 */
	public SerieRequest(Serie serie, UserContext userContext)
	{
		addSerie(serie);
		setUserContext(userContext);
	}

	/**
	 * Gets the series.
	 * 
	 * @return the series
	 */
	public List<Serie> getSeries()
	{
		return series;
	}

	/**
	 * Gets the first serie.
	 * 
	 * @return the first serie
	 */
	public Serie getFirstSerie()
	{
		if ((getSeries() != null) && !getSeries().isEmpty())
		{
			return getSeries().get(0);
		}

		return null;
	}

	/**
	 * Sets the series.
	 * 
	 * @param series the new series
	 */
	public void setSeries(List<Serie> series)
	{
		this.series = series;
	}

	/**
	 * Adds the serie.
	 * 
	 * @param serie the serie
	 */
	public void addSerie(Serie serie)
	{
		if (getSeries() == null)
		{
			setSeries(new ArrayList<Serie>());
		}

		getSeries().add(serie);
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
		return "SerieRequest [getSeries()=" + getSeries() + ", getFirstSerie()=" + getFirstSerie()
				+ ", getRetrieveHistory()=" + getRetrieveHistory() + ", toString()=" + super.toString() + "]";
	}

}
