package com.sensus.dm.common.exercicio.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.exercicio.model.Exercicio;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryExercicioRequest.
 * 
 * @author Washington
 */
public class InquiryExercicioRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The exercicios. */
	private List<Exercicio> exercicios;

	/**
	 * Instantiates a new inquiry exercicio request.
	 */
	public InquiryExercicioRequest()
	{

	}

	/**
	 * Instantiates a new inquiry exercicio request.
	 * 
	 * @param exercicio the exercicio
	 */
	public InquiryExercicioRequest(Exercicio exercicio)
	{
		addExercicio(exercicio);
	}

	/**
	 * Instantiates a new inquiry exercicio request.
	 * 
	 * @param exercicio the exercicio
	 * @param tenant the tenant
	 */
	public InquiryExercicioRequest(Exercicio exercicio, DMTenant tenant)
	{
		addExercicio(exercicio);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry exercicio request.
	 * 
	 * @param exercicio the exercicio
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryExercicioRequest(Exercicio exercicio, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addExercicio(exercicio);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry exercicio request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryExercicioRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry exercicio request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryExercicioRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the exercicios.
	 * 
	 * @return the exercicios
	 */
	public List<Exercicio> getExercicios()
	{
		return exercicios;
	}

	/**
	 * Sets the exercicios.
	 * 
	 * @param exercicios the new exercicios
	 */
	public void setExercicios(List<Exercicio> exercicios)
	{
		this.exercicios = exercicios;
	}

	/**
	 * Gets the first exercicio.
	 * 
	 * @return the first exercicio
	 */
	public Exercicio getFirstExercicio()
	{
		if ((getExercicios() != null) && !getExercicios().isEmpty())
		{
			return getExercicios().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the exercicio.
	 * 
	 * @param exercicio the exercicio
	 */
	public void addExercicio(Exercicio exercicio)
	{
		if (getExercicios() == null)
		{
			setExercicios(new ArrayList<Exercicio>());
		}

		getExercicios().add(exercicio);
	}

	@Override
	public String toString()
	{
		return "InquiryExercicioRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getExercicios()=" + getExercicios() + ", getFirstExercicio()=" + getFirstExercicio()
				+ ", toString()="
				+ super.toString() + "]";
	}

}
