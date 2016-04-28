package com.qat.samples.sysmgmt.dicionario.request;

import com.qat.samples.sysmgmt.dicionario.Classes;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class ClassesMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Classes classes;

	/**
	 * The Constructor.
	 */
	public ClassesMaintenanceRequest()
	{

	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "ClassesMaintenanceRequest [getClasses()=" + getClasses() + ", toString()=" + super.toString() + "]";
	}





}