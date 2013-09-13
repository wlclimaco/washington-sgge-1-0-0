package com.sensus.dm.common.dieta.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.dieta.model.Dieta;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryDietaRequest.
 * 
 * @author Washington
 */
public class InquiryDietaRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The dietas. */
	private List<Dieta> dietas;

	/**
	 * Instantiates a new inquiry dieta request.
	 */
	public InquiryDietaRequest()
	{

	}

	/**
	 * Instantiates a new inquiry dieta request.
	 * 
	 * @param dieta the dieta
	 */
	public InquiryDietaRequest(Dieta dieta)
	{
		addDieta(dieta);
	}

	/**
	 * Instantiates a new inquiry dieta request.
	 * 
	 * @param dieta the dieta
	 * @param tenant the tenant
	 */
	public InquiryDietaRequest(Dieta dieta, DMTenant tenant)
	{
		addDieta(dieta);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry dieta request.
	 * 
	 * @param dieta the dieta
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryDietaRequest(Dieta dieta, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addDieta(dieta);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry dieta request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryDietaRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry dieta request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryDietaRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the dietas.
	 * 
	 * @return the dietas
	 */
	public List<Dieta> getDietas()
	{
		return dietas;
	}

	/**
	 * Sets the dietas.
	 * 
	 * @param dietas the new dietas
	 */
	public void setDietas(List<Dieta> dietas)
	{
		this.dietas = dietas;
	}

	/**
	 * Gets the first dieta.
	 * 
	 * @return the first dieta
	 */
	public Dieta getFirstDieta()
	{
		if ((getDietas() != null) && !getDietas().isEmpty())
		{
			return getDietas().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the dieta.
	 * 
	 * @param dieta the dieta
	 */
	public void addDieta(Dieta dieta)
	{
		if (getDietas() == null)
		{
			setDietas(new ArrayList<Dieta>());
		}

		getDietas().add(dieta);
	}

	@Override
	public String toString()
	{
		return "InquiryDietaRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getDietas()=" + getDietas() + ", getFirstDieta()=" + getFirstDieta() + ", toString()="
				+ super.toString() + "]";
	}

}
