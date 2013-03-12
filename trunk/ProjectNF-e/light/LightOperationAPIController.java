package com.sensus.mlc.wui.light;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointProcessorBCF;
import com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.wui.BaseController;

/**
 * The Class LightOperationAPIController.
 * 
 * @author Alexandre Tiveron
 */

@Controller
@RequestMapping("api/lighttop")
public class LightOperationAPIController extends BaseController
{

	/** The Constant TABLE. */
	public static final String TABLE = "table";

	/** The Constant ACTION. */
	public static final String ACTION = "action";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightOperationAPIController";

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LightOperationAPIController.class);

	/** The smart point updater bcf. */
	@Autowired
	private ISmartPointUpdaterBCF smartPointUpdaterBCF;

	/** The smart point processor bcf. */
	@Autowired
	private ISmartPointProcessorBCF smartPointProcessorBCF;

	/* Spring Actions URLs */
	/** The Constant CLEAR_STATUS. */
	public static final String CLEAR_STATUS = "/clearstatus";

	/** The Constant UPSERT_PROPERTY. */
	public static final String UPSERT_PROPERTY = "/upsertproperty";

	/** The Constant FETCH_STATUS. */
	public static final String FETCH_STATUS = "/fetchstatus";

	/** The Constant UPDATE_PROTECTED. */
	public static final String UPDATE_PROTECTED = "/updateprotected";

	/** The Constant UPDATE_STATUS. */
	public static final String UPDATE_STATUS = "/updatestatus";

	/** The Constant UPDATE_RESET. */
	public static final String UPDATE_RESET = "/updatereset";

	/**
	 * Clear status.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = CLEAR_STATUS, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse clearStatus(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();
		try
		{

			setUserContext(lightRequest, request);

			processResponse = getSmartPointProcessorBCF().initiateDeleteAlert(lightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

	/**
	 * Update light property.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = UPSERT_PROPERTY, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse updateLightProperty(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{

			setUserContext(lightRequest, request);

			processResponse = getSmartPointProcessorBCF().initiateUpsertLightProperty(lightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

	/**
	 * Fetch light status.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = FETCH_STATUS, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse fetchLightStatus(@RequestBody Map<String, Object> jsonRequest, HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			LightRequest lightRequest = new LightRequest();
			setUserContext(lightRequest, request);

			processResponse = getSmartPointProcessorBCF().initiateGetLightStatus(lightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

	/**
	 * Update list light protected.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the light response
	 */
	@RequestMapping(value = UPDATE_PROTECTED, method = RequestMethod.POST)
	@ResponseBody
	public LightResponse updateLightProtected(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		LightResponse lightResponse = new LightResponse();

		try
		{

			setUserContext(lightRequest, request);

			lightResponse = getSmartPointUpdaterBCF().updateLightProtected(lightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, lightResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return lightResponse;

	}

	/**
	 * Update light status.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = UPDATE_STATUS, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse updateLightStatus(@RequestBody Map<String, Object> jsonRequest, HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			LightRequest lightRequest = new LightRequest();
			setUserContext(lightRequest, request);

			processResponse = getSmartPointProcessorBCF().initiateUpdateLightStatus(lightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;

	}

	/**
	 * Reset values.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the light response
	 */
	@RequestMapping(value = UPDATE_RESET, method = RequestMethod.POST)
	@ResponseBody
	public LightResponse resetValues(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		LightResponse lightResponse = new LightResponse();

		try
		{

			setUserContext(lightRequest, request);

			lightResponse = getSmartPointUpdaterBCF().resetMinMaxValue(lightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, lightResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return lightResponse;

	}

	/**
	 * Gets the smart point updater bcf.
	 * 
	 * @return the smartPointUpdaterBCF
	 */
	public ISmartPointUpdaterBCF getSmartPointUpdaterBCF()
	{
		return smartPointUpdaterBCF;
	}

	/**
	 * Sets the smart point updater bcf.
	 * 
	 * @param smartPointUpdaterBCF the smartPointUpdaterBCF to set
	 */
	public void setSmartPointUpdaterBCF(ISmartPointUpdaterBCF smartPointUpdaterBCF)
	{
		this.smartPointUpdaterBCF = smartPointUpdaterBCF;
	}

	/**
	 * Gets the smart point processor bcf.
	 * 
	 * @return the smartPointProcessorBCF
	 */
	public ISmartPointProcessorBCF getSmartPointProcessorBCF()
	{
		return smartPointProcessorBCF;
	}

	/**
	 * Sets the smart point processor bcf.
	 * 
	 * @param smartPointProcessorBCF the smartPointProcessorBCF to set
	 */
	public void setSmartPointProcessorBCF(ISmartPointProcessorBCF smartPointProcessorBCF)
	{
		this.smartPointProcessorBCF = smartPointProcessorBCF;
	}

}
