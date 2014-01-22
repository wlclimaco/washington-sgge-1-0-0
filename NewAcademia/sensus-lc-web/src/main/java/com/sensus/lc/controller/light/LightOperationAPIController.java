package com.sensus.lc.controller.light;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.MaintenanceResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.light.bcf.ILightBCF;
import com.sensus.lc.light.bcf.ILightProcessorBCF;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.LightResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Class LightOperationAPIController.
 * 
 */

@Controller
@RequestMapping("api/lighttop")
public class LightOperationAPIController extends BaseController
{

	/** The Constant TYPE. */
	private static final String TYPE = "type";

	/** The Constant TABLE. */
	public static final String TABLE = "table";

	/** The Constant ACTION. */
	public static final String ACTION = "action";

	/** The Constant LIGHT_ID. */
	public static final String LIGHT_ID = "light_id";

	/** The Constant LOCATION. */
	public static final String LOCATION = "location";

	/** The Constant POLE_ID. */
	public static final String POLE_ID = "poleid";

	/** The Constant IS_MONITORED. */
	public static final String IS_MONITORED = "isMonitored";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightOperationAPIController";

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/* Spring Actions URLs */
	/** The Constant CLEAR_STATUS. */
	public static final String CLEAR_STATUS = "/clearstatus";

	/** The Constant UPSERT_PROPERTY. */
	public static final String UPSERT_PROPERTY = "/upsertproperty/{type}";

	/** The Constant FETCH_STATUS. */
	public static final String FETCH_STATUS = "/fetchstatus";

	/** The Constant UPDATE_PROTECTED. */
	public static final String UPDATE_PROTECTED = "/updateprotected";

	/** The Constant UPDATE_STATUS. */
	public static final String UPDATE_STATUS = "/updatestatus";

	/** The Constant UPDATE_RESET. */
	public static final String UPDATE_RESET = "/updatereset";

	/** The Constant CONTROL_LIGHTS. */
	public static final String CONTROL_LIGHTS = "/controlLights";

	/** The smart point accessor bcf. */
	private ILightBCF lightBCF;

	/** The light processor bcf. */
	private ILightProcessorBCF lightProcessorBCF;

	/**
	 * Gets the light bcf.
	 * 
	 * @return the light bcf
	 */
	public ILightBCF getLightBCF()
	{
		return lightBCF;
	}

	/**
	 * Sets the light bcf.
	 * 
	 * @param lightBCF the new light bcf
	 */
	@Resource
	public void setLightBCF(ILightBCF lightBCF)
	{
		this.lightBCF = lightBCF;
	}

	/**
	 * Gets the light processor bcf.
	 * 
	 * @return the light processor bcf
	 */
	public ILightProcessorBCF getLightProcessorBCF()
	{
		return lightProcessorBCF;
	}

	/**
	 * Sets the light processor bcf.
	 * 
	 * @param lightProcessorBCF the new light processor bcf
	 */
	@Resource
	public void setLightProcessorBCF(ILightProcessorBCF lightProcessorBCF)
	{
		this.lightProcessorBCF = lightProcessorBCF;
	}

	/**
	 * Clear status.
	 * 
	 * @param lightRequest the light request
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

			processResponse = getLightProcessorBCF().initiateDeleteAlert(lightRequest);

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
	 * @param type the type
	 * @param lightMaintenanceRequest the light maintenance request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = UPSERT_PROPERTY, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse updateLightProperty(@PathVariable(TYPE) String type,
			@RequestBody LightMaintenanceRequest lightMaintenanceRequest,
			HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{

			setUserContext(lightMaintenanceRequest, request);

			switch (type)
			{
				case LOCATION:
					processResponse = getLightProcessorBCF().initiateUpsertLightLatLng(lightMaintenanceRequest);
					break;
				case POLE_ID:
					processResponse = getLightProcessorBCF().initiateUpsertLightPoleId(lightMaintenanceRequest);
					break;

				default:
					break;
			}

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
	 * @param lightRequest the light request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = FETCH_STATUS, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse fetchLightStatus(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			setUserContext(lightRequest, request);

			processResponse = getLightProcessorBCF().initiateGetLightStatus(lightRequest);
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
	 * @param lightRequest the light request
	 * @param request the request
	 * @return the light response
	 */
	@RequestMapping(value = UPDATE_PROTECTED, method = RequestMethod.POST)
	@ResponseBody
	public MaintenanceResponse updateLightProtected(@RequestBody LightMassUpdateRequest lightMassUpdateRequest,
			HttpServletRequest request)
	{

		MaintenanceResponse maintenanceResponse = new MaintenanceResponse();

		try
		{

			setUserContext(lightMassUpdateRequest, request);
			maintenanceResponse = getLightBCF().updateLightMass(lightMassUpdateRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, maintenanceResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return maintenanceResponse;

	}

	/**
	 * Update light status.
	 * 
	 * @param lightRequest the light request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = UPDATE_STATUS, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse updateLightStatus(@RequestBody LightMaintenanceRequest lightMaintenanceRequest,
			HttpServletRequest request)
	{

		ProcessResponse processResponse = new ProcessResponse();

		try
		{
			setUserContext(lightMaintenanceRequest, request);

			processResponse = getLightProcessorBCF().initiateUpdateLightStatus(lightMaintenanceRequest);
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
	 * @param lightRequest the light request
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

			lightResponse = getLightBCF().resetMinMaxValue(lightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, lightResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return lightResponse;

	}

	/**
	 * Initiate update light intensity.
	 * 
	 * @param lightRequest the light request
	 * @param request the request
	 * @return the process response
	 */
	@RequestMapping(value = CONTROL_LIGHTS, method = RequestMethod.POST)
	@ResponseBody
	public ProcessResponse initiateUpdateLightIntensity(@RequestBody LightRequest lightRequest,
			HttpServletRequest request)
	{
		ProcessResponse processResponse = new ProcessResponse();
		try
		{
			setUserContext(lightRequest, request);
			processResponse = getLightProcessorBCF().initiateUpdateLightIntensity(lightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, processResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return processResponse;
	}

}
