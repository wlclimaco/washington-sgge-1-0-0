package com.sensus.mlc.base.bcf.impl;

import com.sensus.common.validation.ValidationController;

/**
 * The Class AbstractBaseBCF.
 */
public abstract class AbstractBaseBCF
{

	/** The lighting control request validation controller. */
	private ValidationController lightingControlRequestValidationController;

	/** The lighting control inquiry request validation controller. */
	private ValidationController lightingControlInquiryRequestValidationController;

	/** The selection pagination validation controller. */
	private ValidationController lightSelectionRequestValidationController;

	/** The range date validation controller. */
	private ValidationController rangeDateValidationController;

	/** The geo code validation controller. */
	private ValidationController geoCodeValidationController;

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
	 * Gets the lighting control inquiry request validation controller.
	 * 
	 * @return the lighting control inquiry request validation controller
	 */
	public ValidationController getLightingControlInquiryRequestValidationController()
	{
		return lightingControlInquiryRequestValidationController;
	}

	/**
	 * Sets the lighting control inquiry request validation controller.
	 * 
	 * @param lightingControlInquiryRequestValidationController the new lighting control inquiry request validation
	 *            controller
	 */
	public void setLightingControlInquiryRequestValidationController(
			ValidationController lightingControlInquiryRequestValidationController)
	{
		this.lightingControlInquiryRequestValidationController = lightingControlInquiryRequestValidationController;
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

}
