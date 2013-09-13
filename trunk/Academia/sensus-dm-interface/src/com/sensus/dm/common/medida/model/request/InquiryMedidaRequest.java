package com.sensus.dm.common.medida.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.medida.model.Medida;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryMedidaRequest.
 * 
 * @author Washington
 */
public class InquiryMedidaRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The medidas. */
	private List<Medida> medidas;

	/**
	 * Instantiates a new inquiry medida request.
	 */
	public InquiryMedidaRequest()
	{

	}

	/**
	 * Instantiates a new inquiry medida request.
	 * 
	 * @param medida the medida
	 */
	public InquiryMedidaRequest(Medida medida)
	{
		addMedida(medida);
	}

	/**
	 * Instantiates a new inquiry medida request.
	 * 
	 * @param medida the medida
	 * @param tenant the tenant
	 */
	public InquiryMedidaRequest(Medida medida, DMTenant tenant)
	{
		addMedida(medida);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry medida request.
	 * 
	 * @param medida the medida
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryMedidaRequest(Medida medida, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addMedida(medida);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry medida request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryMedidaRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry medida request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryMedidaRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the medidas.
	 * 
	 * @return the medidas
	 */
	public List<Medida> getMedidas()
	{
		return medidas;
	}

	/**
	 * Sets the medidas.
	 * 
	 * @param medidas the new medidas
	 */
	public void setMedidas(List<Medida> medidas)
	{
		this.medidas = medidas;
	}

	/**
	 * Gets the first medida.
	 * 
	 * @return the first medida
	 */
	public Medida getFirstMedida()
	{
		if ((getMedidas() != null) && !getMedidas().isEmpty())
		{
			return getMedidas().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the medida.
	 * 
	 * @param medida the medida
	 */
	public void addMedida(Medida medida)
	{
		if (getMedidas() == null)
		{
			setMedidas(new ArrayList<Medida>());
		}

		getMedidas().add(medida);
	}

	@Override
	public String toString()
	{
		return "InquiryMedidaRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getMedidas()=" + getMedidas() + ", getFirstMedida()=" + getFirstMedida() + ", toString()="
				+ super.toString() + "]";
	}

}
