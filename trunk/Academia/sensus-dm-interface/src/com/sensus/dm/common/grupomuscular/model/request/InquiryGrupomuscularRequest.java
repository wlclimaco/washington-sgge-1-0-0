package com.sensus.dm.commons.grupomuscular.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.commons.grupomuscular.model.Grupomuscular;

/**
 * The Class InquiryGrupomuscularRequest.
 * 
 * @author Washington
 */
public class InquiryGrupomuscularRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The grupomusculars. */
	private List<Grupomuscular> grupomusculars;

	/**
	 * Instantiates a new inquiry grupomuscular request.
	 */
	public InquiryGrupomuscularRequest()
	{

	}

	/**
	 * Instantiates a new inquiry grupomuscular request.
	 * 
	 * @param grupomuscular the grupomuscular
	 */
	public InquiryGrupomuscularRequest(Grupomuscular grupomuscular)
	{
		addGrupomuscular(grupomuscular);
	}

	/**
	 * Instantiates a new inquiry grupomuscular request.
	 * 
	 * @param grupomuscular the grupomuscular
	 * @param tenant the tenant
	 */
	public InquiryGrupomuscularRequest(Grupomuscular grupomuscular, DMTenant tenant)
	{
		addGrupomuscular(grupomuscular);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry grupomuscular request.
	 * 
	 * @param grupomuscular the grupomuscular
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryGrupomuscularRequest(Grupomuscular grupomuscular, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addGrupomuscular(grupomuscular);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry grupomuscular request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryGrupomuscularRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry grupomuscular request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryGrupomuscularRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the grupomusculars.
	 * 
	 * @return the grupomusculars
	 */
	public List<Grupomuscular> getGrupomusculars()
	{
		return grupomusculars;
	}

	/**
	 * Sets the grupomusculars.
	 * 
	 * @param grupomusculars the new grupomusculars
	 */
	public void setGrupomusculars(List<Grupomuscular> grupomusculars)
	{
		this.grupomusculars = grupomusculars;
	}

	/**
	 * Gets the first grupomuscular.
	 * 
	 * @return the first grupomuscular
	 */
	public Grupomuscular getFirstGrupomuscular()
	{
		if ((getGrupomusculars() != null) && !getGrupomusculars().isEmpty())
		{
			return getGrupomusculars().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the grupomuscular.
	 * 
	 * @param grupomuscular the grupomuscular
	 */
	public void addGrupomuscular(Grupomuscular grupomuscular)
	{
		if (getGrupomusculars() == null)
		{
			setGrupomusculars(new ArrayList<Grupomuscular>());
		}

		getGrupomusculars().add(grupomuscular);
	}

	@Override
	public String toString()
	{
		return "InquiryGrupomuscularRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getGrupomusculars()=" + getGrupomusculars() + ", getFirstGrupomuscular()="
				+ getFirstGrupomuscular() + ", toString()="
				+ super.toString() + "]";
	}

}
