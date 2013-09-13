package com.sensus.dm.controller.tag;

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
import com.sensus.dm.common.tag.bcf.ITagBCF;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tag.model.response.InquiryTagResponse;
import com.sensus.dm.controller.base.BaseViewController;

/**
 * The Class TagViewController.
 */
@Controller
@RequestMapping("/tag")
public class TagViewController extends BaseViewController
{

	/** The Constant VIEW_GROUP_MAIN. */
	private static final String VIEW_GROUP = "/tag/tag_main";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "TagViewController";

	/** The Constant FETCH_LIST. */
	private static final String FETCH_LIST = "";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TagViewController.class);

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_NAME = "name";

	/** The Constant TAGS. */
	private static final String TAGS = "tags";

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/**
	 * Gets the tag bcf.
	 * 
	 * @return the tag bcf
	 */
	public ITagBCF getTagBCF()
	{
		return tagBCF;
	}

	/**
	 * Sets the tag bcf.
	 * 
	 * @param tagBCF the new tag bcf
	 */
	@Resource
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
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

		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest();
		inquiryTagRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_NAME, Direction.Ascending));

		// ADD user context to request
		addUserContextToRequest(inquiryTagRequest);

		inquiryTagRequest.setPreQueryCount(true);

		InquiryTagResponse inquiryTagResponse = getTagBCF().fetchAllTags(inquiryTagRequest);
		ModelAndView modelAndView = new ModelAndView(VIEW_GROUP);
		try
		{
			modelAndView.addObject(TAGS, getMapper().writeValueAsString(inquiryTagResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(TAGS, null);
		}

		return modelAndView;
	}

}
