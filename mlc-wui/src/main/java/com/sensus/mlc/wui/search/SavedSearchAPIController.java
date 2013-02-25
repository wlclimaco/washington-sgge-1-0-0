package com.sensus.mlc.wui.search;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.response.InquiryCustomSearchResponse;
import com.sensus.mlc.wui.BaseController;
import com.sensus.mlc.wui.light.LightAPIController;

/**
 * The Class SavedSearchAPIController.
 */
/**
 * @author QATEmployee
 * 
 */
@Controller
@RequestMapping("api/search")
public class SavedSearchAPIController extends BaseController
{

	/*
	 * URLs Mapping
	 */
	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "/fetch";

	/** The Constant ACTION. */
	private static final String ACTION = "action";

	/** The Constant TABLE. */
	private static final String TABLE = "table";

	/** The smart point accessor bcf. */
	private ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LightAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SavedSearchAPIController";

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return this.smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	@Resource
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
	}

	/**
	 * Fetch.
	 * 
	 * @param jsonRequest the json request
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody Map<String, Object> jsonRequest, HttpServletRequest request)
	{

		InquiryCustomSearchResponse customSearchResponse = new InquiryCustomSearchResponse();

		try
		{
			InquiryPaginationRequest inquiryPaginationRequest = new InquiryPaginationRequest();
			setUserContext(inquiryPaginationRequest, request);

			switch (jsonRequest.get(ACTION).toString())
			{
				case TABLE:

					customSearchResponse = getSmartPointAccessorBCF().fetchAllCustomSearch(inquiryPaginationRequest);
					break;

			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, customSearchResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return customSearchResponse;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.wui.BaseController#setMessageSource(org.springframework.context.MessageSource)
	 */
	@Override
	public void setMessageSource(MessageSource arg0)
	{
		// TODO Auto-generated method stub

	}

}