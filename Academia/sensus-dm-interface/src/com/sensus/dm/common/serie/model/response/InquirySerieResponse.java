package com.sensus.dm.commons.serie.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.commons.serie.model.Serie;

/**
 * The Class InquirySerieResponse.
 * 
 * @author - Washington
 */
public class InquirySerieResponse extends InquiryResponse
{

	/** The series. */
	@XmlElement(nillable = true)
	private List<Serie> series;

	/** The file name. */
	private String fileName;

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
	 * @param serieList the new series
	 */
	public void setSeries(List<Serie> serieList)
	{
		series = serieList;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setSeries(new ArrayList<Serie>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquirySerieResponse [series=" + series + ", fileName=" + fileName + ", getSeries()=" + getSeries()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
