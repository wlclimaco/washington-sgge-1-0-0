package com.sensus.lc.controller.search;

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
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.light.bcf.ILightCustomSearchBCF;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.CustomSearchResponse;

/**
 * The Class SavedSearchViewController.
 */
/**
 * @author Rodolfo Alves - QAT
 * 
 * 
 */
@Controller
@RequestMapping("/search")
public class SavedSearchViewController extends BaseViewController
{

	/** The Constant MAP_FETCH. */
	private static final String MAP_FETCH = "";

	/** The Constant VIEW_SAVEDSEARCH_MAIN. */
	private static final String VIEW_SAVEDSEARCH_MAIN = "/search/search_main";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_NAME = "CS.CUSTOM_SEARCH_NAME";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SavedSearchViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "SavedSearchViewController";

	/** The light custom search bcf. */
	private ILightCustomSearchBCF lightCustomSearchBCF;

	/**
	 * Gets the light custom search bcf.
	 * 
	 * @return the light custom search bcf
	 */
	public ILightCustomSearchBCF getLightCustomSearchBCF()
	{
		return lightCustomSearchBCF;
	}

	/**
	 * Sets the light custom search bcf.
	 * 
	 * @param lightCustomSearchBCF the new light custom search bcf
	 */
	@Resource
	public void setLightCustomSearchBCF(ILightCustomSearchBCF lightCustomSearchBCF)
	{
		this.lightCustomSearchBCF = lightCustomSearchBCF;
	}

	/**
	 * Fetch list.
	 * 
	 * @param request the request
	 * @return the model (inquiry SavedSearch response) and view
	 */
	@RequestMapping(value = MAP_FETCH, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		CustomSearchResponse customSearchResponse = new CustomSearchResponse();
		ModelAndView modelAndView = new ModelAndView(VIEW_SAVEDSEARCH_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		LightRequest lightRequest = new LightRequest(getUserContext(request));

		// Check for page size
		Integer pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			lightRequest.setPageSize(pageSize);
		}

		lightRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_NAME, Direction.Ascending));

		customSearchResponse = getLightCustomSearchBCF().fetchAllCustomSearch(lightRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(customSearchResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;
	}
}
