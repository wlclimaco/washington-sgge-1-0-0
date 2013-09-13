package com.sensus.dm.commons.serie.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.commons.serie.model.Serieitens;

/**
 * The Class InquirySerieitensRequest.
 * 
 * @author Washington
 */
public class InquirySerieitensRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The serieitenss. */
	private List<Serieitens> serieitenss;

	/**
	 * Instantiates a new inquiry serieitens request.
	 */
	public InquirySerieitensRequest()
	{

	}

	/**
	 * Instantiates a new inquiry serieitens request.
	 * 
	 * @param serieitens the serieitens
	 */
	public InquirySerieitensRequest(Serieitens serieitens)
	{
		addSerieitens(serieitens);
	}

	/**
	 * Instantiates a new inquiry serieitens request.
	 * 
	 * @param serieitens the serieitens
	 * @param tenant the tenant
	 */
	public InquirySerieitensRequest(Serieitens serieitens, DMTenant tenant)
	{
		addSerieitens(serieitens);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry serieitens request.
	 * 
	 * @param serieitens the serieitens
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquirySerieitensRequest(Serieitens serieitens, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addSerieitens(serieitens);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry serieitens request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquirySerieitensRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry serieitens request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquirySerieitensRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the serieitenss.
	 * 
	 * @return the serieitenss
	 */
	public List<Serieitens> getSerieitenss()
	{
		return serieitenss;
	}

	/**
	 * Sets the serieitenss.
	 * 
	 * @param serieitenss the new serieitenss
	 */
	public void setSerieitenss(List<Serieitens> serieitenss)
	{
		this.serieitenss = serieitenss;
	}

	/**
	 * Gets the first serieitens.
	 * 
	 * @return the first serieitens
	 */
	public Serieitens getFirstSerieitens()
	{
		if ((getSerieitenss() != null) && !getSerieitenss().isEmpty())
		{
			return getSerieitenss().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the serieitens.
	 * 
	 * @param serieitens the serieitens
	 */
	public void addSerieitens(Serieitens serieitens)
	{
		if (getSerieitenss() == null)
		{
			setSerieitenss(new ArrayList<Serieitens>());
		}

		getSerieitenss().add(serieitens);
	}

	@Override
	public String toString()
	{
		return "InquirySerieitensRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getSerieitenss()=" + getSerieitenss() + ", getFirstSerieitens()=" + getFirstSerieitens()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
