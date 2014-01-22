package com.sensus.lc.serie.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.serie.model.Serie;

/**
 * The Class InquirySerieRequest.
 * 
 * @author Washington
 */
public class InquirySerieRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The series. */
	private List<Serie> series;

	/**
	 * Instantiates a new inquiry serie request.
	 */
	public InquirySerieRequest()
	{

	}

	/**
	 * Instantiates a new inquiry serie request.
	 * 
	 * @param serie the serie
	 */
	public InquirySerieRequest(Serie serie)
	{
		addSerie(serie);
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
	 * Sets the series.
	 * 
	 * @param series the new series
	 */
	public void setSeries(List<Serie> series)
	{
		this.series = series;
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
			return getSeries().get(FIRST);
		}

		return null;
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

	@Override
	public String toString()
	{
		return "InquirySerieRequest [getSeries()=" + getSeries() + ", getFirstSerie()=" + getFirstSerie()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
