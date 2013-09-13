package com.sensus.dm.common.serie.model.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.Response;
import com.sensus.dm.common.serie.model.Serie;

/**
 * The Class SerieResponse.
 * 
 * @author - Washington
 */
public class SerieResponse extends Response
{

	/** The series. */
	@XmlElement(nillable = true)
	private List<Serie> series;

	/** The is serie name unique. */
	private Boolean isSerieNameUnique;

	/** The process id. */
	private Integer processId;

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
	 * Sets the series.
	 * 
	 * @param serieObjects the new series
	 */
	public void setSeries(List<Serie> serieObjects)
	{
		series = serieObjects;
	}

	/**
	 * Gets the checks if is serie name unique.
	 * 
	 * @return the checks if is serie name unique
	 */
	public Boolean getIsSerieNameUnique()
	{
		return isSerieNameUnique;
	}

	/**
	 * Sets the checks if is serie name unique.
	 * 
	 * @param isSerieNameUnique the new checks if is serie name unique
	 */
	public void setIsSerieNameUnique(Boolean isSerieNameUnique)
	{
		this.isSerieNameUnique = isSerieNameUnique;
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
		return "SerieResponse [getSeries()=" + getSeries() + ", getMessageIterator()=" + getMessageIterator()
				+ ", getMessageList()=" + getMessageList() + ", getMessageInfoList()=" + getMessageInfoList()
				+ ", getProcessId()=" + getProcessId()
				+ ", isOperationSuccess()=" + isOperationSuccess() + ", getIsSerieNameUnique()="
				+ getIsSerieNameUnique() + "]";
	}

}
