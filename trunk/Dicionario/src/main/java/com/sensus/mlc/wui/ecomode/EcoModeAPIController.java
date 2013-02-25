package com.sensus.mlc.wui.ecomode;

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
import com.sensus.mlc.ecomode.bcf.IEcoModeBCF;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.wui.BaseController;

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
	private static final String MAP_FETCH = "/fetch";

	private static final String TABLE = "table";

	private static final String MAP = "map";

	private IEcoModeBCF ecoModeBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EcoModeAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "EcoModeAPIController";

	public IEcoModeBCF getEcoModeBCF()
	{
		return ecoModeBCF;
	}

	@Resource
	public void setEcoModeBCF(IEcoModeBCF ecoModeBCF)
	{
		this.ecoModeBCF = ecoModeBCF;
	}

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

			String key = inquiryEcoModeRequest.getAction();
			switch (key)
			{
				case TABLE:
					inquiryEcoModeResponse =
							this.getEcoModeBCF().fetchLightConsumptionsByLightId(inquiryEcoModeRequest);
					break;
				case MAP:
					inquiryEcoModeResponse = this.getEcoModeBCF().fetchLightConsumptionsToChart(inquiryEcoModeRequest);
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
}
