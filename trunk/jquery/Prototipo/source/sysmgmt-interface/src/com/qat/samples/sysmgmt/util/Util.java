package com.qat.samples.sysmgmt.util;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;
import com.qat.framework.model.Tenant;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Util", propOrder = {"acessos", "userId", "tenant", "userRole", "tabela"})
public class Util extends QATModelOL
{

	/** The acessos. */
	List<ControleAcess> acessos;

	/** The user id. */
	String userId;

	/** The tenant. */
	Tenant tenant;

	/** The user role. */
	String userRole;

	/** The tabela. */
	TableTypeEnum tabela;

	/**
	 * Instantiates a new bundle.
	 */
	public Util()
	{

	}

	/**
	 * Instantiates a new util.
	 * 
	 * @param userId the user id
	 */
	public Util(String userId)
	{
		super();
		this.userId = userId;
	}

	/**
	 * Instantiates a new util.
	 * 
	 * @param acessos the acessos
	 * @param userId the user id
	 * @param id the id
	 * @param tenant the tenant
	 * @param userRole the user role
	 */
	public Util(List<ControleAcess> acessos, String userId, Integer id, Tenant tenant, String userRole)
	{
		super();
		this.acessos = acessos;
		this.userId = userId;
		this.tenant = tenant;
		this.userRole = userRole;
	}

	/**
	 * Instantiates a new util.
	 * 
	 * @param acessos the acessos
	 */
	public Util(List<ControleAcess> acessos)
	{
		super();
		this.acessos = acessos;
	}

	/**
	 * Instantiates a new util.
	 * 
	 * @param userId the user id
	 * @param id the id
	 * @param tenant the tenant
	 * @param userRole the user role
	 */
	public Util(String userId, Integer id, Tenant tenant, String userRole)
	{
		super();
		this.userId = userId;
		this.tenant = tenant;
		this.userRole = userRole;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId the new user id
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * Gets the tenant.
	 * 
	 * @return the tenant
	 */
	public Tenant getTenant()
	{
		return tenant;
	}

	/**
	 * Sets the tenant.
	 * 
	 * @param tenant the new tenant
	 */
	public void setTenant(Tenant tenant)
	{
		this.tenant = tenant;
	}

	/**
	 * Gets the user role.
	 * 
	 * @return the user role
	 */
	public String getUserRole()
	{
		return userRole;
	}

	/**
	 * Sets the user role.
	 * 
	 * @param userRole the new user role
	 */
	public void setUserRole(String userRole)
	{
		this.userRole = userRole;
	}

	/**
	 * Gets the acessos.
	 * 
	 * @return the acessos
	 */
	public List<ControleAcess> getAcessos()
	{
		return acessos;
	}

	/**
	 * Sets the acessos.
	 * 
	 * @param acessos the new acessos
	 */
	public void setAcessos(List<ControleAcess> acessos)
	{
		this.acessos = acessos;
	}

	/**
	 * Gets the tabela.
	 * 
	 * @return the tabela
	 */
	public TableTypeEnum getTabela()
	{
		return tabela;
	}

	/**
	 * Sets the tabela.
	 * 
	 * @param tabela the new tabela
	 */
	public void setTabela(TableTypeEnum tabela)
	{
		this.tabela = tabela;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.QATModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Util [getUserId()=" + getUserId() + ", getTenant()=" + getTenant() + ", getUserRole()=" + getUserRole()
				+ ", getAcessos()=" + getAcessos() + ", getTabela()=" + getTabela() + ", toString()="
				+ super.toString() + "]";
	}

}
