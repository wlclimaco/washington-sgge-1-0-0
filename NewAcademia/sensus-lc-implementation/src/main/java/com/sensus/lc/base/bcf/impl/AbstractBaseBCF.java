package com.sensus.lc.base.bcf.impl;

import com.sensus.common.validation.ValidationController;

/**
 * The Class AbstractBaseBCF.
 */
public abstract class AbstractBaseBCF
{

	/** The lighting control request validation controller. */
	private ValidationController lightingControlRequestValidationController;

	/** The selection pagination validation controller. */
	private ValidationController lightSelectionRequestValidationController;

	/** The range date validation controller. */
	private ValidationController rangeDateValidationController;

	/** The geo code validation controller. */
	private ValidationController geoCodeValidationController;

	/** The light request validation controller. */
	private ValidationController lightRequestValidationController; // injected by Spring through setter

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/**
	 * Gets the light request validation controller.
	 * 
	 * @return the light request validation controller
	 */
	public ValidationController getLightRequestValidationController()
	{
		return lightRequestValidationController;
	}

	/**
	 * Sets the light request validation controller.
	 * 
	 * @param lightRequestValidationController the new light request validation controller
	 */
	public void setLightRequestValidationController(ValidationController lightRequestValidationController)
	{
		this.lightRequestValidationController = lightRequestValidationController;
	}

	/**
	 * Gets the geo code validation controller.
	 * 
	 * @return the geo code validation controller
	 */
	public ValidationController getGeoCodeValidationController()
	{
		return geoCodeValidationController;
	}

	/**
	 * Sets the geo code validation controller.
	 * 
	 * @param geoCodeValidationController the new geo code validation controller
	 */
	public void setGeoCodeValidationController(ValidationController geoCodeValidationController)
	{
		this.geoCodeValidationController = geoCodeValidationController;
	}

	/**
	 * Gets the lighting control request validation controller.
	 * 
	 * @return the lighting control request validation controller
	 */
	public ValidationController getLightingControlRequestValidationController()
	{
		return lightingControlRequestValidationController;
	}

	/**
	 * Sets the lighting control request validation controller.
	 * 
	 * @param lightingControlRequestValidationController the new lighting control request validation controller
	 */
	public void setLightingControlRequestValidationController(
			ValidationController lightingControlRequestValidationController)
	{
		this.lightingControlRequestValidationController = lightingControlRequestValidationController;
	}

	/**
	 * Gets the range date validation controller.
	 * 
	 * @return the range date validation controller
	 */
	public ValidationController getRangeDateValidationController()
	{
		return rangeDateValidationController;
	}

	/**
	 * Sets the range date validation controller.
	 * 
	 * @param rangeDateValidationController the new range date validation controller
	 */
	public void setRangeDateValidationController(ValidationController rangeDateValidationController)
	{
		this.rangeDateValidationController = rangeDateValidationController;
	}

	/**
	 * Gets the light selection request validation controller.
	 * 
	 * @return the light selection request validation controller
	 */
	public ValidationController getLightSelectionRequestValidationController()
	{
		return lightSelectionRequestValidationController;
	}

	/**
	 * Sets the light selection request validator controller.
	 * 
	 * @param lightSelectionRequestValidationController the new light selection request validator controller
	 */
	public void setLightSelectionRequestValidationController(
			ValidationController lightSelectionRequestValidationController)
	{
		this.lightSelectionRequestValidationController = lightSelectionRequestValidationController;
	}

	/**
	 * Gets the inquiry request validation controller.
	 * 
	 * @return the inquiry request validation controller
	 */
	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * Sets the request validation controller.
	 * 
	 * @param requestValidationController the new request validation controller
	 */
	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
	}

	/**
	 * Gets the request validation controller.
	 * 
	 * @return the request validation controller
	 */
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
	}

	/**
	 * Sets the request validation controller.
	 * 
	 * @param requestValidationController the new request validation controller
	 */
	public void setRequestValidationController(ValidationController requestValidationController)
	{
		this.requestValidationController = requestValidationController;
	}

}
