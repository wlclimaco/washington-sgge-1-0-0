package com.sensus.lc.api.service.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.UserContext;
import com.sensus.common.util.SensusMessageUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.api.service.model.APIException;
import com.sensus.lc.api.service.model.LCLight;
import com.sensus.lc.api.service.util.APIUtils;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.SearchTerm;
import com.sensus.lc.light.model.criteria.SearchTerm.TermSearchType;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.FetchAllResponse;

/**
 * The Class LightSearchAPIController.
 */
@Controller
@RequestMapping("/lights")
public class LightSearchAPIController extends BaseAPIController
{

	/** The Constant PARAMETER_FLEXNETID. */
	private static final String PARAMETER_FLEXNETID = "flexnetid";

	/** The Constant PARAMETER_POLEID. */
	private static final String PARAMETER_POLEID = "poleid";

	/** The Constant FETCH. */
	private static final String FETCH = "";

	/** The Constant SENSUS_LC_APIVALIDATOR_PARAMETER_REQUIRED. */
	private static final String SENSUS_LC_APIVALIDATOR_PARAMETER_REQUIRED = "sensus.lc.apivalidator.parameter.required";

	/** The Constant SENSUS_LC_APIVALIDATOR_PARAMETER_NOTFOUND. */
	private static final String SENSUS_LC_APIVALIDATOR_PARAMETER_NOTFOUND = "sensus.lc.apivalidator.parameter.notfound";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightSearchAPIController";

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(LightSearchAPIController.class);

	/**
	 * Fetch search.
	 * 
	 * @param poleid the poleid
	 * @param flexnetid the flexnetid
	 * @param request the request
	 * @return the light response
	 * @throws APIException
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.GET)
	@ResponseBody
	public List<LCLight> fetchSearch(@RequestParam(value = PARAMETER_POLEID, required = false) String poleid,
			@RequestParam(value = PARAMETER_FLEXNETID, required = false) BigInteger flexnetid,
			HttpServletRequest request) throws APIException
	{

		// At least one parameter is mandatory
		if (ValidationUtil.isNullOrEmpty(poleid) && ValidationUtil.isNull(flexnetid))
		{
			throw new APIException(SensusMessageUtil.getMessage(SENSUS_LC_APIVALIDATOR_PARAMETER_REQUIRED));
		}

		// Verify access
		UserContext userContext = verifyAccessControl(request);

		LightRequest lightRequest = new LightRequest(userContext);
		LightCriteria lightCriteria = new LightCriteria();

		TermSearchType searchType = SearchTerm.TermSearchType.EQUAL;
		if (!ValidationUtil.isNull(flexnetid))
		{
			lightCriteria.setFlexnetId(new SearchTerm(flexnetid, searchType));
		}
		if (!ValidationUtil.isNullOrEmpty(poleid))
		{
			lightCriteria.setPoleId(new SearchTerm(poleid, searchType));
		}

		lightRequest.setLightCriteria(lightCriteria);
		List<LCLight> lightResponse = new ArrayList<LCLight>();

		try
		{
			FetchAllResponse fetchAllResponse = getLightBCF().fetchAllByRequest(lightRequest);

			lightResponse = APIUtils.handleLightList(fetchAllResponse);
			if (ValidationUtil.isNull(lightResponse))
			{
				throw new Exception();
			}
		}
		catch (Exception e)
		{
			handleDefaultException(LOG, e, CONTROLLER_EXCEPTION_MSG);
		}

		if (ValidationUtil.isNullOrEmpty(lightResponse))
		{
			throw new APIException(SensusMessageUtil.getMessage(SENSUS_LC_APIVALIDATOR_PARAMETER_NOTFOUND),
					HttpStatus.BAD_REQUEST);
		}

		return lightResponse;
	}
}