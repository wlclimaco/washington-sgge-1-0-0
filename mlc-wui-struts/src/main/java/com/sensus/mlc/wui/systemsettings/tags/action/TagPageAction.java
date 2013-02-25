package com.sensus.mlc.wui.systemsettings.tags.action;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.tag.model.request.InquiryTagRequest;
import com.sensus.mlc.tag.model.response.InquiryTagResponse;
import com.sensus.mlc.wui.base.action.LayoutBase;
import com.sensus.mlc.wui.base.model.IdValuePair;

/**
 * Action rendering the main tag-related pages. At this point, this page has no extra logic over what LayoutBase
 * already
 * provides.
 * 
 * @author Raphael Constantino
 * 
 */
@SuppressWarnings("serial")
public class TagPageAction extends LayoutBase
{

	/** CONSTANTS **/

	/**
	 * The default "empty" value for droplists.
	 */
	private static final String DEFAULT_VALUE = "0";

	/**
	 * The key for the default "loading error" droplist prompt.
	 */
	public static final String DEFAULT_ERROR_KEY = "widgets.combobox.errorprompt2";

	/** The Constant GROUP_LIST_ERROR. */
	private static final String TAG_LIST_ERROR = "Error loading Tag List";

	/**
	 * The key for the default "empty" droplist prompt.
	 */
	public static final String DEFAULT_DESCRIPTION_KEY = "ALL";

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/**
	 * Gets the tag list.
	 * 
	 * @return the tag list
	 */
	public List<IdValuePair> getTagList()
	{
		List<IdValuePair> list = new ArrayList<IdValuePair>();
		try
		{
			InquiryTagRequest request = new InquiryTagRequest(getUserContext());

			request.setPageSize(0);

			InquiryTagResponse response = getTagBCF().fetchAllTags(request);
			if (!response.isOperationSuccess())
			{
				list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));

				if (LOG.isErrorEnabled())
				{
					LOG.error(TAG_LIST_ERROR);
				}

			}
			else
			{
				if (!ValidationUtil.isNull(response.getTags()))
				{
					for (Tag tag : response.getTags())
					{
						list.add(new IdValuePair(tag.getId(), tag.getName()));
					}
				}
			}
		}
		catch (Exception e)
		{
			list.add(new IdValuePair(DEFAULT_VALUE, getText(DEFAULT_ERROR_KEY)));
			if (LOG.isErrorEnabled())
			{
				LOG.error(TAG_LIST_ERROR);
			}
		}
		return list;
	}

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
	public void setTagBCF(ITagBCF tagBCF)
	{
		this.tagBCF = tagBCF;
	}

}
