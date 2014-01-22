package com.sensus.lc.controller.tags;

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
import com.sensus.lc.controller.BaseViewController;
import com.sensus.lc.tag.bcf.ITagBCF;
import com.sensus.lc.tag.model.request.InquiryTagRequest;
import com.sensus.lc.tag.model.response.InquiryTagResponse;

/**
 * The Class TagViewController.
 */
/**
 * @author Alexandre Tiveron
 * 
 */
@Controller
@RequestMapping("/tag")
public class TagViewController extends BaseViewController
{

	/** The Constant FETCH_LIST. */
	public static final String FETCH_LIST = "";

	/** The Constant INQUIRY_ACTION_TABLE. */
	private static final String INQUIRY_ACTION_TABLE = "table";

	/** The Constant SORT_EXPRESSION_NAME. */
	private static final String SORT_EXPRESSION_NAME = "NAME";

	/** The Constant VIEW_TAG_MAIN. */
	private static final String VIEW_TAG_MAIN = "/tag/tag_main";

	/** The Constant TAG. */
	private static final String TAG = "tags";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TagViewController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	public static final String CONTROLLER_EXCEPTION_MSG = "TagViewController";

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
	 * Load list.
	 * 
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping(value = FETCH_LIST, method = RequestMethod.GET)
	public ModelAndView loadList(HttpServletRequest request)
	{
		InquiryTagResponse inquiryTagResponse = new InquiryTagResponse();
		InquiryTagRequest inquiryTagRequest = new InquiryTagRequest();
		setUserContext(inquiryTagRequest, request);

		inquiryTagRequest.addSortExpressions(new SortExpression(SORT_EXPRESSION_NAME, Direction.Ascending));
		inquiryTagRequest.setAction(INQUIRY_ACTION_TABLE);

		inquiryTagRequest.setPageSize(getUserSettings().getPageSize());

		inquiryTagResponse = getTagBCF().fetchAllTags(inquiryTagRequest);
		ModelAndView modelAndView = new ModelAndView(VIEW_TAG_MAIN);
		try
		{
			modelAndView.addObject(TAG, getMapper().writeValueAsString(inquiryTagResponse));
		}
		catch (IOException e)
		{
			LOG.info(new StringBuilder(CONTROLLER_EXCEPTION_MSG).append(e).toString());
			modelAndView.addObject(TAG, null);
		}
		return modelAndView;
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

}
