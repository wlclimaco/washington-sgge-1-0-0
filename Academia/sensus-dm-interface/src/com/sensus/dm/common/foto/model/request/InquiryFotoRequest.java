package com.sensus.dm.common.foto.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.foto.model.Foto;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryFotoRequest.
 * 
 * @author Washington
 */
public class InquiryFotoRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The fotos. */
	private List<Foto> fotos;

	/**
	 * Instantiates a new inquiry foto request.
	 */
	public InquiryFotoRequest()
	{

	}

	/**
	 * Instantiates a new inquiry foto request.
	 * 
	 * @param foto the foto
	 */
	public InquiryFotoRequest(Foto foto)
	{
		addFoto(foto);
	}

	/**
	 * Instantiates a new inquiry foto request.
	 * 
	 * @param foto the foto
	 * @param tenant the tenant
	 */
	public InquiryFotoRequest(Foto foto, DMTenant tenant)
	{
		addFoto(foto);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry foto request.
	 * 
	 * @param foto the foto
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryFotoRequest(Foto foto, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addFoto(foto);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry foto request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryFotoRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry foto request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryFotoRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the fotos.
	 * 
	 * @return the fotos
	 */
	public List<Foto> getFotos()
	{
		return fotos;
	}

	/**
	 * Sets the fotos.
	 * 
	 * @param fotos the new fotos
	 */
	public void setFotos(List<Foto> fotos)
	{
		this.fotos = fotos;
	}

	/**
	 * Gets the first foto.
	 * 
	 * @return the first foto
	 */
	public Foto getFirstFoto()
	{
		if ((getFotos() != null) && !getFotos().isEmpty())
		{
			return getFotos().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the foto.
	 * 
	 * @param foto the foto
	 */
	public void addFoto(Foto foto)
	{
		if (getFotos() == null)
		{
			setFotos(new ArrayList<Foto>());
		}

		getFotos().add(foto);
	}

	@Override
	public String toString()
	{
		return "InquiryFotoRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getFotos()=" + getFotos() + ", getFirstFoto()=" + getFirstFoto() + ", toString()="
				+ super.toString() + "]";
	}

}
