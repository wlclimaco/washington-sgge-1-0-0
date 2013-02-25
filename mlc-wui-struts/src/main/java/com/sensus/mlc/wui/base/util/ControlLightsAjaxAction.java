package com.sensus.mlc.wui.base.util;

import com.sensus.common.model.Response;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * The Class ControlLightsAjaxAction.
 * 
 * @author Alex Tiveron
 */
public class ControlLightsAjaxAction extends ActionBase
{

	/** The smart point bcf. */
	private ISmartPointProcessorBCF smartPointProcessorBCF;

	/** The light request. */
	private LightRequest lightRequest;

	/** The response. */
	private Response response;

	/**
	 * Struts Action to switch lights on or off. Returns immediate result (i.e.
	 * that command was sent correctly).
	 * 
	 * @return always "SUCCESS"
	 */
	public String controlLights()

	{
		LightRequest request = getLightRequest();
		request.setUserContext(getUserContext());
		ProcessResponse processResponse = getSmartPointProcessorBCF().initiateUpdateLightIntensity(request);
		setResponse(processResponse);

		return SUCCESS;
	}

	/**
	 * Gets the smart point processor bcf.
	 * 
	 * @return the smart point processor bcf
	 */
	public ISmartPointProcessorBCF getSmartPointProcessorBCF()
	{
		return smartPointProcessorBCF;
	}

	/**
	 * Sets the smart point processor bcf.
	 * 
	 * @param smartPointProcessorBCF the new smart point processor bcf
	 */
	public void setSmartPointProcessorBCF(ISmartPointProcessorBCF smartPointProcessorBCF)
	{
		this.smartPointProcessorBCF = smartPointProcessorBCF;
	}

	/**
	 * Gets the light request.
	 * 
	 * @return the lightRequest
	 */
	public LightRequest getLightRequest()
	{
		return lightRequest;
	}

	/**
	 * Sets the light request.
	 * 
	 * @param lightRequest the lightRequest to set
	 */
	public void setLightRequest(LightRequest lightRequest)
	{
		this.lightRequest = lightRequest;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

}
