package com.sensus.lc.controller.light;

import java.util.Arrays;

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

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.lc.controller.model.LightDetailResponse;
import com.sensus.lc.light.bcf.ILightNotificationHistoryBCF;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.light.model.response.FetchAllResponse;
import com.sensus.lc.light.model.response.LightHistoryResponse;
import com.sensus.lc.light.model.response.NotificationHistoryResponse;

/**
 * The Class LightAPIController is called when table is rendering.
 * 
 */

@Controller
@RequestMapping("api/light")
public class LightAPIController extends BaseLightController
{

	/** The Constant TABLE. */
	public static final String TABLE = "table";

	/** The Constant ACTION. */
	public static final String ACTION = "action";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant STATUS_MESSAGE_ID. */
	public static final String STATUS_MESSAGE_ID = "statusMessageId";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightAPIController";

	/* Spring Actions URLs */
	/** The Constant FETCH. */
	public static final String FETCH_LIGHTS_BY_PROCESS = "/fetchByProcess";

	/** The Constant FETCH_LIGHT_BY_ID. */
	public static final String FETCH_LIGHT_BY_ID = "/fetchById/{id}";

	/** The Constant FETCH_ALL. */
	public static final String FETCH_ALL = "/fetchall";

	/** The Constant FETCH_MESSAGES. */
	public static final String FETCH_MESSAGES = "/fetchmessages/{id}";

	/** The Constant FETCH_ALARMS. */
	public static final String FETCH_ALARMS = "/fetchalarms";

	/** The Constant FETCH_HISTORY. */
	public static final String FETCH_HISTORY = "/fetchhistory";

	/** The Constant FETCH_HISTORY. */
	public static final String FETCH_HISTORY_BY_ID = "/fetchhistorybyid";

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The light notification history bcf. */
	private ILightNotificationHistoryBCF lightNotificationHistoryBCF;

	/**
	 * Gets the light notification history bcf.
	 * 
	 * @return the light notification history bcf
	 */
	public ILightNotificationHistoryBCF getLightNotificationHistoryBCF()
	{
		return lightNotificationHistoryBCF;
	}

	/**
	 * Sets the light notification history bcf.
	 * 
	 * @param lightNotificationHistoryBCF the new light notification history bcf
	 */
	@Resource
	public void setLightNotificationHistoryBCF(ILightNotificationHistoryBCF lightNotificationHistoryBCF)
	{
		this.lightNotificationHistoryBCF = lightNotificationHistoryBCF;
	}

	/**
	 * Fetch.
	 * 
	 * @param lightRequest the light request
	 * @param request the request
	 * @return the inquiry light response
	 */
	@RequestMapping(value = FETCH_LIGHTS_BY_PROCESS, method = RequestMethod.POST)
	@ResponseBody
	public FetchAllResponse fetchByProcess(@RequestBody LightRequest lightRequest, HttpServletRequest request)
	{

		FetchAllResponse fetchAllResponse = new FetchAllResponse();

		try
		{

			fetchAllResponse = getLightBCF().fetchAllByRequest(lightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, fetchAllResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return fetchAllResponse;
	}

	/**
	 * Fetch the light and it's associated groups and tags based on the light-id.
	 * As long as the light exists try to populate the group and tag collections.
	 * 
	 * @param lightId the light id
	 * @param request the request
	 * @return the light detail response
	 */
	@RequestMapping(value = FETCH_LIGHT_BY_ID, method = RequestMethod.POST)
	@ResponseBody
	public LightDetailResponse fetchLightById(@PathVariable(ID) int lightId, HttpServletRequest request)
	{
		LightRequest lightRequest = new LightRequest(getUserContext(request));
		LightCriteria lightCriteria = new LightCriteria();
		lightCriteria.setLightIdList(Arrays.asList(lightId));
		lightRequest.setLightCriteria(lightCriteria);
		LightDetailResponse lightDetailResponse = new LightDetailResponse();

		try
		{
			FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(lightId);
			setUserContext(fetchByIdRequest, request);

			// If the light is not found then do not try to get the groups and tags.
			if (fetchLight(lightDetailResponse, fetchByIdRequest))
			{
				fetchGroups(lightDetailResponse, lightRequest);
				fetchTags(lightDetailResponse, lightRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, lightDetailResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return lightDetailResponse;
	}

	/**
	 * Fetch all.
	 * 
	 * @param lightRequest the light request
	 * @param request the request
	 * @return the inquiry light response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public FetchAllResponse fetchAll(@RequestBody LightRequest lightRequest,
			HttpServletRequest request)
	{

		FetchAllResponse fetchAllResponse = new FetchAllResponse();

		try
		{
			setUserContext(lightRequest, request);

			fetchAllResponse = getLightBCF().fetchAllByRequest(lightRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, fetchAllResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return fetchAllResponse;
	}

	/**
	 * Fetch light history.
	 * 
	 * @param notificationHistoryRequest the notification history request
	 * @param request the request
	 * @return the notification history response
	 */
	@RequestMapping(value = FETCH_HISTORY, method = RequestMethod.POST)
	@ResponseBody
	public LightHistoryResponse fetchLightHistory(
			@RequestBody NotificationHistoryRequest notificationHistoryRequest, HttpServletRequest request)
	{
		LightHistoryResponse lightHistoryResponse = new LightHistoryResponse();

		try
		{

			setUserContext(notificationHistoryRequest, request);
			lightHistoryResponse =
					getLightNotificationHistoryBCF().fetchLightNotificationHistory(notificationHistoryRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, lightHistoryResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return lightHistoryResponse;

	}

	/**
	 * Fetch light history.
	 * 
	 * @param notificationHistoryRequest the notification history request
	 * @param request the request
	 * @return the notification history response
	 */
	@RequestMapping(value = FETCH_HISTORY_BY_ID, method = RequestMethod.POST)
	@ResponseBody
	public NotificationHistoryResponse fetchLightHistoryById(
			@RequestBody NotificationHistoryRequest notificationHistoryRequest, HttpServletRequest request)
	{
		NotificationHistoryResponse notificationHistoryResponse = new NotificationHistoryResponse();
		
		try
		{
			setUserContext(notificationHistoryRequest, request);
			notificationHistoryResponse =
					getLightNotificationHistoryBCF().fetchNotificationHistoryById(notificationHistoryRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, notificationHistoryResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return notificationHistoryResponse;
	}
}
