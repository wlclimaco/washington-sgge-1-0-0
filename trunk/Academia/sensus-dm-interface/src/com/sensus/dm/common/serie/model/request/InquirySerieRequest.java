package com.sensus.dm.common.serie.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.serie.model.Serie;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquirySerieRequest.
 * 
 * @author Washington
 */
public class InquirySerieRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

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
	 * Instantiates a new inquiry serie request.
	 * 
	 * @param serie the serie
	 * @param tenant the tenant
	 */
	public InquirySerieRequest(Serie serie, DMTenant tenant)
	{
		addSerie(serie);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry serie request.
	 * 
	 * @param serie the serie
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquirySerieRequest(Serie serie, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addSerie(serie);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry serie request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquirySerieRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry serie request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquirySerieRequest(DeviceSearch deviceSearchParam)
	{
		setDeviceSearch(deviceSearchParam);
	}

	/**
	 * Gets the device search.
	 * 
	 * @return the device search
	 */
	public DeviceSearch getDeviceSearch()
	{
		return deviceSearch;
	}

	/**
	 * Sets the device search.
	 * 
	 * @param deviceSearch the new device search
	 */
	public void setDeviceSearch(DeviceSearch deviceSearch)
	{
		this.deviceSearch = deviceSearch;
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
		return "InquirySerieRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getSeries()=" + getSeries() + ", getFirstSerie()=" + getFirstSerie() + ", toString()="
				+ super.toString() + "]";
	}

}
