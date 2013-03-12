package com.sensus.mlc.wui.light;

import java.util.ArrayList;
import java.util.List;
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
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.smartpoint.model.response.StatusMessageResponse;
import com.sensus.mlc.wui.BaseController;

/**
 * The Class LightAPIController is called when table is rendering.
 * 
 * @author Alexandre Tiveron
 */

@Controller
@RequestMapping("api/light")
public class LightAPIController extends BaseController
{

	/** The Constant TABLE. */
	public static final String TABLE = "table";

	/** The Constant ACTION. */
	public static final String ACTION = "action";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightAPIController";

	/* Spring Actions URLs */
	/** The Constant FETCH. */
	public static final String FETCH = "/fetch";

	/** The Constant FETCH_ALL. */
	public static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH_MESSAGES. */
	public static final String FETCH_MESSAGES = "/fetchmessages";

	/** The Constant FETCH_ALARMS. */
	public static final String FETCH_ALARMS = "/fetchalarms";

	public static final String FETCH_HISTORY = "/fetchhistory";

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{

		return smartPointAccessorBCF;

	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{

		this.smartPointAccessorBCF = smartPointAccessorBCF;

	}

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The smart point accessor bcf. */
	@Autowired
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/**
	 * Fetch.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the inquiry light response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public LightResponse fetch(@RequestBody Map<String, Object> jsonRequest, HttpServletRequest request)
	{

		LightResponse lightResponse = new LightResponse();

		try
		{
			String key = jsonRequest.get(ACTION).toString();
			switch (key)
			{
				case ID:
					LightRequest lightRequest = new LightRequest();
					setUserContext(lightRequest, request);

					List<Light> lightList = new ArrayList<Light>();

					Light light = new Light();
					light.setId(Integer.parseInt(jsonRequest.get(ID).toString()));

					lightList.add(light);
					lightRequest.setLights(lightList);

					lightResponse = getSmartPointAccessorBCF().fetchLightById(lightRequest);
					break;

				case TABLE:

					ProcessRequest processRequest = new ProcessRequest();
					setUserContext(processRequest, request);

					Process process = new Process();
					process.setId(Integer.parseInt(jsonRequest.get(ID).toString()));
					processRequest.setProcess(process);

					lightResponse = getSmartPointAccessorBCF().fetchUpdateLightStatus(processRequest);
					break;
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, lightResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return lightResponse;
	}

	/**
	 * Fetch all.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the inquiry light response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public InquiryLightResponse fetchAll(@RequestBody InquiryLightRequest inquiryLightRequest,
			HttpServletRequest request)
	{

		InquiryLightResponse inquiryLightResponse = new InquiryLightResponse();

		try
		{
			setUserContext(inquiryLightRequest, request);

			inquiryLightResponse = getSmartPointAccessorBCF().fetchAllLights(inquiryLightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryLightResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryLightResponse;
	}

	/**
	 * Fetch status message.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the status message response
	 */
	@RequestMapping(value = FETCH_MESSAGES, method = RequestMethod.POST)
	@ResponseBody
	public StatusMessageResponse fetchStatusMessage(@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		StatusMessageResponse response = new StatusMessageResponse();

		try
		{
			LightRequest lightRequest = new LightRequest();
			setUserContext(lightRequest, request);
			response = getSmartPointAccessorBCF().fetchStatusMessageById(lightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

	/**
	 * Current alarm status.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = FETCH_ALARMS, method = RequestMethod.POST)
	@ResponseBody
	public CurrentAlarmWarningMessageResponse currentAlarmStatus(@RequestBody Map<String, Object> jsonRequest,
			HttpServletRequest request)
	{
		CurrentAlarmWarningMessageResponse currentMsgResponse = new CurrentAlarmWarningMessageResponse();

		try
		{
			LightRequest lightRequest = new LightRequest();
			setUserContext(lightRequest, request);
			currentMsgResponse = getSmartPointAccessorBCF().fetchCurrentAlarmStatusMessagesByLight(lightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, currentMsgResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return currentMsgResponse;

	}

	@RequestMapping(value = FETCH_HISTORY, method = RequestMethod.POST)
	@ResponseBody
	public InquiryLightResponse fetchLightHistory(@RequestBody InquiryLightRequest inquiryLightRequest,
			HttpServletRequest request)
	{
		InquiryLightResponse inquiryLightResponse = new InquiryLightResponse();

		try
		{

			setUserContext(inquiryLightRequest, request);
			inquiryLightResponse = getSmartPointAccessorBCF().fetchLightHistory(inquiryLightRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryLightResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryLightResponse;

	}

}
