package com.sensus.lc.api.service.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.UserContext;
import com.sensus.common.query.SearchDate;
import com.sensus.common.query.SearchDate.DateSearchType;
import com.sensus.common.util.SensusConvertUtil;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.api.service.model.APIException;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ChangesResponse;

/**
 * The Class AttributeChangesAPIController.
 */
@Controller
@RequestMapping("/changed")
public class AttributeChangesAPIController extends BaseAPIController
{

	/** The Constant BASE_DATE. */
	private static final String BASE_DATE = "19700101";

	/** The Constant DATE_INPUT_FORMAT. */
	private static final String DATE_INPUT_FORMAT = "yyyyMMdd";

	/** The Constant PARAMETER_SINCE. */
	private static final String PARAMETER_SINCE = "since";

	/** The Constant FETCH. */
	private static final String FETCH = "";

	/** The Constant SENSUS_LC_APIVALIDATOR_DATE_INVALID. */
	private static final String SENSUS_LC_APIVALIDATOR_DATE_INVALID = "sensus.lc.apivalidator.date.invalid";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "AttributeChangesAPIController";

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AttributeChangesAPIController.class);

	/**
	 * Fetch changes.
	 * 
	 * @param date the date
	 * @param request the request
	 * @return the light response
	 * @throws APIException
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.GET)
	@ResponseBody
	public List<BigInteger> fetchChangedLights(
			@RequestParam(value = PARAMETER_SINCE, required = false) String date,
			HttpServletRequest request) throws APIException
	{

		date = ValidationUtil.isNullOrEmpty(date, BASE_DATE);
		Date searchdate = SensusConvertUtil.toDate(date, DATE_INPUT_FORMAT);

		if (ValidationUtil.isNull(searchdate))
		{
			throw new APIException(SensusMessageUtil.getMessage(SENSUS_LC_APIVALIDATOR_DATE_INVALID));
		}

		// Verify access
		UserContext userContext = verifyAccessControl(request);

		LightRequest lightRequest = new LightRequest(userContext);
		LightCriteria lightCriteria = new LightCriteria();

		List<BigInteger> response = new ArrayList<BigInteger>();

		try
		{
			lightCriteria.setModifyDate(new SearchDate(searchdate, DateSearchType.GREATER_THAN));
			lightRequest.setLightCriteria(lightCriteria);
			ChangesResponse changesResponse = getLightBCF().fetchAttributeChanges(lightRequest);
			response = changesResponse.getFlexnetList();
		}
		catch (Exception e)
		{
			handleDefaultException(LOG, e, CONTROLLER_EXCEPTION_MSG);
		}

		return response;
	}
}