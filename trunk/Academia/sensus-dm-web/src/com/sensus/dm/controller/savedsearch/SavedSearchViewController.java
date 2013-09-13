package com.sensus.dm.controller.savedsearch;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.bcf.ICustomSearchBCF;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.InquiryCustomSearchResponse;
import com.sensus.dm.controller.base.BaseViewController;

/**
 * The Class SavedSearchViewController.
 */
@Controller
@RequestMapping("/savedSearch")
public class SavedSearchViewController extends BaseViewController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SavedSearchViewController.class);

	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant VIEW_SEARCH_MAIN. */
	private static final String VIEW_SEARCH_MAIN = "/saved_search/saved_search_main";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_NAME = "NAME";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SavedSearchViewController";

	/** The custom search bcf. */
	private ICustomSearchBCF customSearchBCF;

	/**
	 * Gets the custom search bcf.
	 * 
	 * @return the custom search bcf
	 */
	public ICustomSearchBCF getCustomSearchBCF()
	{
		return customSearchBCF;
	}

	/**
	 * Sets the custom search bcf.
	 * 
	 * @param customSearchBCF the new custom search bcf
	 */
	@Resource
	public void setCustomSearchBCF(ICustomSearchBCF customSearchBCF)
	{
		this.customSearchBCF = customSearchBCF;
	}

	/**
	 * Load list.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{

		ModelAndView modelAndView = new ModelAndView(VIEW_SEARCH_MAIN);

		InquiryCustomSearchRequest inquiryCustomSearchRequest = new InquiryCustomSearchRequest();
		inquiryCustomSearchRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_NAME, Direction.Ascending));

		// ADD user context to request
		addUserContextToRequest(inquiryCustomSearchRequest);

		String pageSize = getUserSettings().getPageSize();

		if (!ValidationUtil.isNullOrEmpty(pageSize))
		{
			inquiryCustomSearchRequest.setPageSize(Integer.valueOf(pageSize));
		}

		// Fetch Table
		InquiryCustomSearchResponse inquiryCustomSearchResponse =
				getCustomSearchBCF().fetchAllCustomSearch(inquiryCustomSearchRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryCustomSearchResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}

		return modelAndView;
	}

}
