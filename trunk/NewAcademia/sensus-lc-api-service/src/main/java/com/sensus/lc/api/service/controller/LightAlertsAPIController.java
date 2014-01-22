package com.sensus.lc.api.service.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.api.service.model.APIException;
import com.sensus.lc.api.service.util.APIUtils;
import com.sensus.lc.light.model.AlertTypeEnum;
import com.sensus.lc.light.model.LightOrderByEnum;
import com.sensus.lc.light.model.criteria.AlertCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.FetchAllResponse;

/**
 * The Class LightAlertsAPIController.
 */
@Controller
@RequestMapping("/alertlist")
public class LightAlertsAPIController extends BaseAPIController
{

	/** The Constant FETCH. */
	private static final String FETCH = "";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightAlertsAPIController";

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LightAlertsAPIController.class);

	/**
	 * Fetch alert list.
	 * 
	 * @param request the request
	 * @return the alert list response
	 * @throws APIException
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<Long[]>> fetchAlertList(HttpServletRequest request) throws APIException
	{

		Map<String, List<Long[]>> alertListResponse = new HashMap<String, List<Long[]>>();

		// Verify access
		UserContext userContext = verifyAccessControl(request);

		LightRequest lightRequest = new LightRequest(userContext);

		AlertCriteria alertCriteria = new AlertCriteria();
		alertCriteria.setAlertTypeList(Arrays.asList(AlertTypeEnum.ALARM, AlertTypeEnum.WARNING));

		lightRequest.setAlertCriteria(alertCriteria);
		lightRequest.addSortExpressions(new SortExpression(LightOrderByEnum.ALERTS.getValue(), Direction.Ascending));

		try
		{
			FetchAllResponse fetchAllResponse = getLightBCF().fetchAllByRequest(lightRequest);

			alertListResponse = APIUtils.handleAlertList(fetchAllResponse);
			if (ValidationUtil.isNull(alertListResponse))
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			handleDefaultException(LOG, e, CONTROLLER_EXCEPTION_MSG);
		}

		return alertListResponse;
	}
}