package com.qat.samples.sysmgmt.util;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.qat.framework.model.QATModelOL;

/**
 * The Model Object Procedure.
 */
@SuppressWarnings("serial")
@XmlType(name = "Util", propOrder = {"acessos"})
public class Util extends QATModelOL
{

	/** The acessos. */
	List<ControleAcess> acessos;

	/**
	 * Instantiates a new bundle.
	 */
	public Util()
	{

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
	 * Instantiates a new util.
	 * 
	 * @param acessos the acessos
	 */
	public Util(List<ControleAcess> acessos)
	{
		super();
		this.acessos = acessos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.qat.framework.model.QATModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Util [getAcessos()=" + getAcessos() + ", toString()=" + super.toString() + "]";
	}

}
