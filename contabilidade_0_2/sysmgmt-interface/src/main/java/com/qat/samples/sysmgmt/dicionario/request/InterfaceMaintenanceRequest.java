package com.qat.samples.sysmgmt.dicionario.request;

import com.qat.samples.sysmgmt.dicionario.Interface;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class InterfaceMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Interface Interface;

	/**
	 * The Constructor.
	 */
	public InterfaceMaintenanceRequest()
	{

	}

	/**
	 * @return the Interface
	 */
	public Interface getInterface()
	{
		return Interface;
	}

	/**
	 * @param Interface the Interface to set
	 */
	public void setInterface(Interface Interface)
	{
		this.Interface = Interface;
	}

	@Override
	public String toString() {
		return "InterfaceMaintenanceRequest [getInterface()=" + getInterface() + ", toString()=" + super.toString() + "]";
	}




}