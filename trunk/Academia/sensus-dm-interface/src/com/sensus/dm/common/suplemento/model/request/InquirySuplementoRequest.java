package com.sensus.dm.common.suplemento.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.suplemento.model.Suplemento;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquirySuplementoRequest.
 * 
 * @author Washington
 */
public class InquirySuplementoRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The suplementos. */
	private List<Suplemento> suplementos;

	/**
	 * Instantiates a new inquiry suplemento request.
	 */
	public InquirySuplementoRequest()
	{

	}

	/**
	 * Instantiates a new inquiry suplemento request.
	 * 
	 * @param suplemento the suplemento
	 */
	public InquirySuplementoRequest(Suplemento suplemento)
	{
		addSuplemento(suplemento);
	}

	/**
	 * Instantiates a new inquiry suplemento request.
	 * 
	 * @param suplemento the suplemento
	 * @param tenant the tenant
	 */
	public InquirySuplementoRequest(Suplemento suplemento, DMTenant tenant)
	{
		addSuplemento(suplemento);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry suplemento request.
	 * 
	 * @param suplemento the suplemento
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquirySuplementoRequest(Suplemento suplemento, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addSuplemento(suplemento);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry suplemento request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquirySuplementoRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry suplemento request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquirySuplementoRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the suplementos.
	 * 
	 * @return the suplementos
	 */
	public List<Suplemento> getSuplementos()
	{
		return suplementos;
	}

	/**
	 * Sets the suplementos.
	 * 
	 * @param suplementos the new suplementos
	 */
	public void setSuplementos(List<Suplemento> suplementos)
	{
		this.suplementos = suplementos;
	}

	/**
	 * Gets the first suplemento.
	 * 
	 * @return the first suplemento
	 */
	public Suplemento getFirstSuplemento()
	{
		if ((getSuplementos() != null) && !getSuplementos().isEmpty())
		{
			return getSuplementos().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the suplemento.
	 * 
	 * @param suplemento the suplemento
	 */
	public void addSuplemento(Suplemento suplemento)
	{
		if (getSuplementos() == null)
		{
			setSuplementos(new ArrayList<Suplemento>());
		}

		getSuplementos().add(suplemento);
	}

	@Override
	public String toString()
	{
		return "InquirySuplementoRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getSuplementos()=" + getSuplementos() + ", getFirstSuplemento()=" + getFirstSuplemento()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
