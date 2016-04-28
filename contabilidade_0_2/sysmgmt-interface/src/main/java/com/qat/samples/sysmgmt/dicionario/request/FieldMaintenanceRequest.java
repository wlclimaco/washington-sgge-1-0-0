package com.qat.samples.sysmgmt.dicionario.request;

import com.qat.samples.sysmgmt.dicionario.Field;
import com.qat.samples.sysmgmt.util.model.request.UtilMaintenanceRequest;

public class FieldMaintenanceRequest extends UtilMaintenanceRequest
{

	/** Attributes */
	private Field field;

	/**
	 * The Constructor.
	 */
	public FieldMaintenanceRequest()
	{

	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "FieldMaintenanceRequest [getField()=" + getField() + ", toString()=" + super.toString() + "]";
	}





}