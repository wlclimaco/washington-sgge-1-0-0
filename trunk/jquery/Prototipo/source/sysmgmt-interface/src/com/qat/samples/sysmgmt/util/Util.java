package com.qat.samples.sysmgmt.util;

import java.sql.Date;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

// TODO: Auto-generated Javadoc
/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Foto", propOrder = {"id", "user", "local", "data", "tenantId", "acao"})
public class Util extends QATModelOL
{

	/** The id. */
	private Integer id;

	/** The code. */
	private String user;

	/** The description. */
	private String local;

	/** The data. */
	private Date data;

	/** The tenant id. */
	private Integer tenantId;

	private AcaoTypeEnum acao;

	/**
	 * Instantiates a new bundle.
	 */
	public Util()
	{

	}

	/**
	 * Instantiates a new bundle.
	 * 
	 * @param id the id
	 */
	public Util(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * Sets the code.
	 * 
	 * @param user the new code
	 */
	public void setUser(String user)
	{
		this.user = user;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getLocal()
	{
		return local;
	}

	/**
	 * Sets the description.
	 * 
	 * @param local the new description
	 */
	public void setLocal(String local)
	{
		this.local = local;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public Date getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the new data
	 */
	public void setData(Date data)
	{
		this.data = data;
	}

	/**
	 * Gets the tenant id.
	 * 
	 * @return the tenant id
	 */
	public Integer getTenantId()
	{
		return tenantId;
	}

	/**
	 * Sets the tenant id.
	 * 
	 * @param tenantId the new tenant id
	 */
	public void setTenantId(Integer tenantId)
	{
		this.tenantId = tenantId;
	}

	public AcaoTypeEnum getAcao()
	{
		return acao;
	}

	public void setAcao(AcaoTypeEnum acao)
	{
		this.acao = acao;
	}

	@Override
	public String toString()
	{
		return "Util [getId()=" + getId() + ", getUser()=" + getUser() + ", getLocal()=" + getLocal() + ", getData()="
				+ getData() + ", getTenantId()=" + getTenantId() + ", getAcao()=" + getAcao() + ", toString()="
				+ super.toString() + "]";
	}

}
