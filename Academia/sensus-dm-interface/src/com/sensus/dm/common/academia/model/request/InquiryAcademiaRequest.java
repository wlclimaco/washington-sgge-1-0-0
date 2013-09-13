package com.sensus.dm.common.academia.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.academia.model.Academia;
import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryAcademiaRequest.
 * 
 * @author Washington
 */
public class InquiryAcademiaRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The academias. */
	private List<Academia> academias;

	/**
	 * Instantiates a new inquiry academia request.
	 */
	public InquiryAcademiaRequest()
	{

	}

	/**
	 * Instantiates a new inquiry academia request.
	 * 
	 * @param academia the academia
	 */
	public InquiryAcademiaRequest(Academia academia)
	{
		addAcademia(academia);
	}

	/**
	 * Instantiates a new inquiry academia request.
	 * 
	 * @param academia the academia
	 * @param tenant the tenant
	 */
	public InquiryAcademiaRequest(Academia academia, DMTenant tenant)
	{
		addAcademia(academia);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry academia request.
	 * 
	 * @param academia the academia
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryAcademiaRequest(Academia academia, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addAcademia(academia);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry academia request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryAcademiaRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry academia request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryAcademiaRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the academias.
	 * 
	 * @return the academias
	 */
	public List<Academia> getAcademias()
	{
		return academias;
	}

	/**
	 * Sets the academias.
	 * 
	 * @param academias the new academias
	 */
	public void setAcademias(List<Academia> academias)
	{
		this.academias = academias;
	}

	/**
	 * Gets the first academia.
	 * 
	 * @return the first academia
	 */
	public Academia getFirstAcademia()
	{
		if ((getAcademias() != null) && !getAcademias().isEmpty())
		{
			return getAcademias().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the academia.
	 * 
	 * @param academia the academia
	 */
	public void addAcademia(Academia academia)
	{
		if (getAcademias() == null)
		{
			setAcademias(new ArrayList<Academia>());
		}

		getAcademias().add(academia);
	}

	@Override
	public String toString()
	{
		return "InquiryAcademiaRequest [ getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getAcademias()=" + getAcademias() + ", getFirstAcademia()=" + getFirstAcademia() + ", toString()="
				+ super.toString() + "]";
	}

}
