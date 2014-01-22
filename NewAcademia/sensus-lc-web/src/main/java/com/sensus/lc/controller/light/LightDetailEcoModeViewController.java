package com.sensus.lc.controller.light;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.util.SensusDateUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.ecomode.bcf.IEcoModeBCF;
import com.sensus.lc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.lc.ecomode.model.response.InquiryEcoModeResponse;

/**
 * The Class LightDetailEcoModeViewController.
 */
@Controller
@RequestMapping("/ecoMode")
public class LightDetailEcoModeViewController extends BaseViewController
{

	/** The Constant FILL_DETAIL. */
	public static final String FILL_ECOMODE = "";

	/** The Constant INQUIRY_ACTION_TABLE. */
	private static final String INQUIRY_ACTION_TABLE = "table";

	/** The Constant CHART_RESPONSE. */
	private static final String CHART_RESPONSE = "chart_response";

	/** The Constant VIEW_DETAIL_ABOUT_MAIN. */
	private static final String VIEW_LIGHT_DETAIL_MAIN = "/light/light_detail_eco_mode";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightDetailEcoModeViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "LightDetailEcoModeViewController";

	/** The Constant LIGHT_ID. */
	public static final String LIGHT_ID = "id";

	/** The ecomode bcf. */
	private IEcoModeBCF ecomodeBCF;

	/**
	 * Fill detail ecomode.
	 *
	 * @param lightId the light id
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FILL_ECOMODE, method = RequestMethod.GET)
	public ModelAndView fillDetailEcomode(@RequestParam(value = LIGHT_ID, required = true) Integer lightId,
			HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_LIGHT_DETAIL_MAIN);
		InquiryEcoModeRequest inquiryEcomodeRequest = new InquiryEcoModeRequest(getUserContext(request));

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}



		Calendar calendar = Calendar.getInstance();
		Date endDate = calendar.getTime();
		calendar.add(Calendar.MONTH, -1);

		Date initialDate = SensusDateUtil.truncateTime(calendar).getTime();


		inquiryEcomodeRequest.setSelectionPaginationIds(new ArrayList<Integer>());
		inquiryEcomodeRequest.getSelectionPaginationIds().add(lightId);
		inquiryEcomodeRequest.setInitialDate(initialDate);
		inquiryEcomodeRequest.setEndDate(endDate);
		Integer pageSize = getUserSettings().getPageSize();

		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			inquiryEcomodeRequest.setPageSize(pageSize);
		}

		InquiryEcoModeResponse inquiryEcomodeResponse =
				getEcomodeBCF().fetchLightConsumptionsByLightId(inquiryEcomodeRequest);

		inquiryEcomodeRequest.setAction(INQUIRY_ACTION_TABLE);
		InquiryEcoModeResponse inquiryEcomodeChartResponse =
				getEcomodeBCF().fetchLightConsumptionsToChart(inquiryEcomodeRequest);

		try
		{
			modelAndView.addObject(CHART_RESPONSE, getMapper().writeValueAsString(inquiryEcomodeChartResponse));
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryEcomodeResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

	/**
	 * Gets the ecomode bcf.
	 *
	 * @return the ecomodeBCF
	 */
	public IEcoModeBCF getEcomodeBCF()
	{
		return ecomodeBCF;
	}

	/**
	 * Sets the ecomode bcf.
	 *
	 * @param ecomodeBCF the ecomodeBCF to set
	 */
	@Resource
	public void setEcomodeBCF(IEcoModeBCF ecomodeBCF)
	{
		this.ecomodeBCF = ecomodeBCF;
	}

}
