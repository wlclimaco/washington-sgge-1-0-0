package com.sensus.lc.light.model.request;

import com.sensus.lc.light.model.DeleteLightReferenceEnum;
import com.sensus.lc.light.model.Light;

/**
 * The Class LightDeleteRequest.
 */
public class LightDeleteRequest extends LightMaintenanceRequest
{

	/** The delete light reference. */
	private DeleteLightReferenceEnum deleteLightReference;

	/**
	 * Instantiates a new light delete request.
	 *
	 * @param newLight the new light
	 */
	public LightDeleteRequest(Light newLight)
	{
		super(newLight);
	}

	/**
	 * Gets the delete light reference.
	 *
	 * @return the deleteLightReference
	 */
	public DeleteLightReferenceEnum getDeleteLightReference()
	{
		return deleteLightReference;
	}

	/**
	 * Sets the delete light reference.
	 *
	 * @param deleteLightReference the deleteLightReference to set
	 */
	public void setDeleteLightReference(DeleteLightReferenceEnum deleteLightReference)
	{
		this.deleteLightReference = deleteLightReference;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "LightDeleteRequest [getDeleteLightReference()=" + getDeleteLightReference() + ", toString()="
				+ super.toString() + "]";
	}

}
