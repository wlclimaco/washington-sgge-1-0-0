package com.sensus.lc.controller.academia;

import java.io.IOException;

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
import com.sensus.lc.academia.bcf.IAcademiaBCF;
import com.sensus.lc.academia.model.request.InquiryAcademiaRequest;
import com.sensus.lc.academia.model.response.InquiryAcademiaResponse;
import com.sensus.lc.controller.BaseViewController;

/**
 * The Class GroupViewController.
 */
/**
 * @author Delcides Junior
 * 
 */
@Controller
@RequestMapping("/academia")
public class AcademiaViewController extends BaseViewController
{

	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant FETCH_UPDATE. */
	public static final String FETCH_UPDATE = "/update";

	/** The Constant VIEW_GROUP_CREATE. */
	private static final String VIEW_GROUP_CREATE = "/group/group_create";

	/** The Constant VIEW_GROUP_MAIN. */
	private static final String VIEW_GROUP_MAIN = "/group/group_main";

	/** The Constant GROUP_ID. */
	private static final String GROUP_ID = "groupId";

	/** The Constant INQUIRY_ACTION_TABLE. */
	private static final String INQUIRY_ACTION_TABLE = "table";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_NAME = "NAME";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AcademiaViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "GroupViewController";

	/** The group bcf. */
	private IAcademiaBCF academiaBCF;

	public IAcademiaBCF getAcademiaBCF()
	{
		return academiaBCF;
	}

	public void setAcademiaBCF(IAcademiaBCF academiaBCF)
	{
		this.academiaBCF = academiaBCF;
	}

	/**
	 * Fetch list.
	 * 
	 * @param request the request
	 * @return the model (inquiry group response) and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView(VIEW_GROUP_MAIN);

		// Check whether has initial load or not
		if (!isInitialLoad(request, modelAndView))
		{
			return modelAndView;
		}

		InquiryAcademiaRequest inquiryPaginationRequest = new InquiryAcademiaRequest();
		setUserContext(inquiryPaginationRequest, request);

		// Check for page size
		Integer pageSize = getUserSettings().getPageSize();
		if (!ValidationUtil.isNullOrZero(pageSize))
		{
			inquiryPaginationRequest.setPageSize(pageSize);
		}

		inquiryPaginationRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_NAME, Direction.Ascending));
		inquiryPaginationRequest.setAction(INQUIRY_ACTION_TABLE);

		InquiryAcademiaResponse inquiryAcademiaResponse = getAcademiaBCF().fetchAllAcademias(inquiryPaginationRequest);

		try
		{
			modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(inquiryAcademiaResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(RESPONSE, null);
		}
		return modelAndView;
	}

	// /**
	// * Fetch update.
	// *
	// * @param groupId the group id
	// * @param request the request
	// * @return the model (inquiry group response) and view
	// */
	// @RequestMapping(value = FETCH_UPDATE, method = RequestMethod.GET)
	// public ModelAndView loadUpdate(@RequestParam(value = GROUP_ID, required = true) Integer groupId,
	// HttpServletRequest request)
	// {
	// GroupResponse groupResponse = new GroupResponse();
	// GroupRequest groupRequest = new GroupRequest();
	// setUserContext(groupRequest, request);
	//
	// groupRequest.setGroup(new Group(groupId));
	//
	// groupResponse = getGroupBCF().fetchGroupById(groupRequest);
	// ModelAndView modelAndView = new ModelAndView(VIEW_GROUP_CREATE);
	// try
	// {
	// modelAndView.addObject(RESPONSE, getMapper().writeValueAsString(groupResponse));
	// }
	// catch (IOException e)
	// {
	// LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
	// modelAndView.addObject(RESPONSE, null);
	// }
	//
	// return modelAndView;
	//
	// }

}
