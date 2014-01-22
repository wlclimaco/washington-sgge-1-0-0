package com.sensus.lc.controller.ecomode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.lc.controller.BaseController;
import com.sensus.lc.controller.light.LightAPIController;
import com.sensus.lc.ecomode.bcf.IEcoModeBCF;
import com.sensus.lc.ecomode.model.request.EcoModeRequest;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.ecomode.model.response.EcoModeResponse;
import com.sensus.lc.ecomode.model.response.InquiryEcoModeResponse;

/**
 * The Class GroupAPIController.
 */
/**
 * @author wsilva
 * 
 */
@Controller
@RequestMapping("/api/ecomode")
public class EcoModeAPIController extends BaseController
{

	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	/** The Constant MAP_UPSERT. */
	private static final String MAP_UPSERT = "/upsert";

	/** The Constant MAP. */
	private static final String MAP = "map";

	/** The eco mode bcf. */
	private IEcoModeBCF ecoModeBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "EcoModeAPIController";

	/**
	 * Gets the eco mode bcf.
	 * 
	 * @return the eco mode bcf
	 */
	public IEcoModeBCF getEcoModeBCF()
	{
		return ecoModeBCF;
	}

	/**
	 * Sets the eco mode bcf.
	 * 
	 * @param ecoModeBCF the new eco mode bcf
	 */
	@Resource
	public void setEcoModeBCF(IEcoModeBCF ecoModeBCF)
	{
		this.ecoModeBCF = ecoModeBCF;
	}

	/**
	 * Fetch all.
	 * 
	 * @param inquiryEcoModeRequest the inquiry eco mode request
	 * @param request the request
	 * @return the inquiry eco mode response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public InquiryEcoModeResponse fetchAll(@RequestBody InquiryEcoModeRequest inquiryEcoModeRequest,
			HttpServletRequest request)
	{

		InquiryEcoModeResponse inquiryEcoModeResponse = new InquiryEcoModeResponse();

		try
		{
			// UserContext object from session
			setUserContext(inquiryEcoModeRequest, request);

			String key = String.valueOf(inquiryEcoModeRequest.getAction());
			switch (key)
			{

				case MAP:
					inquiryEcoModeResponse = getEcoModeBCF().fetchLightConsumptionsToChart(inquiryEcoModeRequest);
					break;
				default:
					inquiryEcoModeResponse = getEcoModeBCF().fetchLightConsumptionsByLightId(inquiryEcoModeRequest);
					break;

			}
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, inquiryEcoModeResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return inquiryEcoModeResponse;

	}

	/**
	 * Update base line.
	 * 
	 * @param inquiryEcoModeRequest the inquiry eco mode request
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = MAP_UPSERT, method = RequestMethod.POST)
	@ResponseBody
	public EcoModeResponse updateBaseLine(@RequestBody EcoModeRequest inquiryEcoModeRequest,
			HttpServletRequest request)
	{

		EcoModeResponse ecoModeResponse = new EcoModeResponse();

		try
		{
			// UserContext object from session
			setUserContext(inquiryEcoModeRequest, request);

			ecoModeResponse =
					getEcoModeBCF().upsertEcoMode(inquiryEcoModeRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, ecoModeResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return ecoModeResponse;
	}

}
