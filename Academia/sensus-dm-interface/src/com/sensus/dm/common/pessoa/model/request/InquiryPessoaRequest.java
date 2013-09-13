package com.sensus.dm.common.pessoa.model.request;

import java.util.ArrayList;
import java.util.List;

import com.sensus.dm.common.base.model.request.InquiryPaginationRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.pessoa.model.Pessoa;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * The Class InquiryPessoaRequest.
 * 
 * @author Washington
 */
public class InquiryPessoaRequest extends InquiryPaginationRequest
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The device search. */
	private DeviceSearch deviceSearch;

	/** The pessoas. */
	private List<Pessoa> pessoas;

	/**
	 * Instantiates a new inquiry pessoa request.
	 */
	public InquiryPessoaRequest()
	{

	}

	/**
	 * Instantiates a new inquiry pessoa request.
	 * 
	 * @param pessoa the pessoa
	 */
	public InquiryPessoaRequest(Pessoa pessoa)
	{
		addPessoa(pessoa);
	}

	/**
	 * Instantiates a new inquiry pessoa request.
	 * 
	 * @param pessoa the pessoa
	 * @param tenant the tenant
	 */
	public InquiryPessoaRequest(Pessoa pessoa, DMTenant tenant)
	{
		addPessoa(pessoa);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry pessoa request.
	 * 
	 * @param pessoa the pessoa
	 * @param serviceTypeEnum the service type enum
	 * @param tenant the tenant
	 */
	public InquiryPessoaRequest(Pessoa pessoa, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		addPessoa(pessoa);
		setServiceTypeEnum(serviceTypeEnum);
		setTenant(tenant);
	}

	/**
	 * Instantiates a new inquiry pessoa request.
	 * 
	 * @param serviceTypeEnum the service type enum
	 */
	public InquiryPessoaRequest(ServiceTypeEnum serviceTypeEnum)
	{
		setServiceTypeEnum(serviceTypeEnum);
	}

	/**
	 * Instantiates a new inquiry pessoa request.
	 * 
	 * @param deviceSearchParam the device search param
	 */
	public InquiryPessoaRequest(DeviceSearch deviceSearchParam)
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
	 * Gets the pessoas.
	 * 
	 * @return the pessoas
	 */
	public List<Pessoa> getPessoas()
	{
		return pessoas;
	}

	/**
	 * Sets the pessoas.
	 * 
	 * @param pessoas the new pessoas
	 */
	public void setPessoas(List<Pessoa> pessoas)
	{
		this.pessoas = pessoas;
	}

	/**
	 * Gets the first pessoa.
	 * 
	 * @return the first pessoa
	 */
	public Pessoa getFirstPessoa()
	{
		if ((getPessoas() != null) && !getPessoas().isEmpty())
		{
			return getPessoas().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the pessoa.
	 * 
	 * @param pessoa the pessoa
	 */
	public void addPessoa(Pessoa pessoa)
	{
		if (getPessoas() == null)
		{
			setPessoas(new ArrayList<Pessoa>());
		}

		getPessoas().add(pessoa);
	}

	@Override
	public String toString()
	{
		return "InquiryPessoaRequest [getDeviceSearch()="
				+ getDeviceSearch()
				+ ", getPessoas()=" + getPessoas() + ", getFirstPessoa()=" + getFirstPessoa() + ", toString()="
				+ super.toString() + "]";
	}

}
