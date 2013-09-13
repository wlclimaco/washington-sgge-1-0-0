package com.sensus.dm.common.dieta.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.dieta.model.Dietaitens;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryDietaitensRequest.
 * 
 * @author Washington
 */
public class InquiryDietaitensRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The dietaitenss. */
	private List<Dietaitens> dietaitenss;

	/**
	 * Instantiates a new inquiry dietaitens request.
	 */
	public InquiryDietaitensRequest()
	{

	}

	/**
	 * Instantiates a new inquiry dietaitens request.
	 * 
	 * @param dietaitens the dietaitens
	 */
	public InquiryDietaitensRequest(Dietaitens dietaitens)
	{
		addDietaitens(dietaitens);
	}

	/**
	 * Instantiates a new inquiry dietaitens request.
	 * 
	 * @param dietaitens the dietaitens
	 * @param tenant the tenant
	 */
	public InquiryDietaitensRequest(Dietaitens dietaitens, DMTenant tenant)
	{
		addDietaitens(dietaitens);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry dietaitens request.
	 * 
	 * @param dietaitens the dietaitens
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryDietaitensRequest(Dietaitens dietaitens, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addDietaitens(dietaitens);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry dietaitens request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryDietaitensRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry dietaitens request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryDietaitensRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the dietaitenss.
	 * 
	 * @return the dietaitenss
	 */
	public List<Dietaitens> getDietaitenss()
	{
		return dietaitenss;
	}

	/**
	 * Sets the dietaitenss.
	 * 
	 * @param dietaitenss the new dietaitenss
	 */
	public void setDietaitenss(List<Dietaitens> dietaitenss)
	{
		this.dietaitenss = dietaitenss;
	}

	/**
	 * Gets the first dietaitens.
	 * 
	 * @return the first dietaitens
	 */
	public Dietaitens getFirstDietaitens()
	{
		if ((getDietaitenss() != null) && !getDietaitenss().isEmpty())
		{
			return getDietaitenss().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the dietaitens.
	 * 
	 * @param dietaitens the dietaitens
	 */
	public void addDietaitens(Dietaitens dietaitens)
	{
		if (getDietaitenss() == null)
		{
			setDietaitenss(new ArrayList<Dietaitens>());
		}

		getDietaitenss().add(dietaitens);
	}

	@Override
	public String toString()
	{
		return "InquiryDietaitensRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getDietaitenss()=" + getDietaitenss() + ", getFirstDietaitens()=" + getFirstDietaitens()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
