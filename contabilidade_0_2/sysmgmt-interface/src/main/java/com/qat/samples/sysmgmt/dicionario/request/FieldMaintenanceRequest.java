package com.qat.samples.sysmgmt.advocacia.request;

import com.qat.samples.sysmgmt.advocacia.Field;
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

	/**
	 * @return the Field
	 */
	public Field getField()
	{
		return field;
	}

	/**
	 * @param Field the Field to set
	 */
	public void setField(Field field)
	{
		this.Field = field;
	}

	@Override
	public String toString() {
		return "FieldMaintenanceRequest [getField()=" + getField() + ", toString()=" + super.toString() + "]";
	}




}