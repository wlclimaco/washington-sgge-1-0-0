package com.sensus.mlc.wui.systemsettings.tags.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Response;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.tag.bcf.ITagBCF;
import com.sensus.mlc.tag.model.request.TagRequest;
import com.sensus.mlc.tag.model.response.TagResponse;
import com.sensus.mlc.wui.base.action.ActionBase;
import com.sensus.mlc.wui.base.model.JsonResult;
import com.sensus.mlc.wui.base.model.SearchJsonResult;
import com.sensus.mlc.wui.base.util.Constants;

/**
 * Action supporting Ajax Callbacks from the tag page.
 * 
 * @author Raphael Constantino
 * 
 */
@SuppressWarnings("serial")
public class TagAjaxAction extends ActionBase
{

	/**
	 * The logger for this class.
	 */
	private static final Log LOG = LogFactory.getLog(TagAjaxAction.class);

	/** The system settings result. */
	private JsonResult tagResult;

	/** The search tag result. */
	private SearchJsonResult searchTagResult;

	/** The tag bcf. */
	private ITagBCF tagBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The tag id. */
	private Integer id;

	/** The tag name. */
	private String tagName;

	/** The auto group. */
	private Boolean autoGroup;

	/** The include smart points to group. */
	private Boolean includeSmartPointsToGroup;

	/** The group description. */
	private String groupDescription;

	/** The tag request. */
	private TagRequest tagRequest;

	/** The group request. */
	private GroupRequest groupRequest;

	/** The tag response. */
	private Response response;

	/**
	 * Update auto group.
	 * 
	 * @return the string
	 */
	public String updateAutoGroup()
	{
		try
		{
			TagResponse tagResponse = getTagBCF().updateAutoGroupTag(getTagRequest());
			setResponse(tagResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error suspend Auto Group", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Exist group with tag name.
	 * 
	 * @return the string
	 */
	public String existGroupWithTagName()
	{
		try
		{
			GroupResponse groupResponse = getGroupBCF().fetchGroupByName(getGroupRequest());
			setResponse(groupResponse);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error checking Auto Group", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Creates the group.
	 * 
	 * @return the string
	 */
	public String createGroup()
	{
		try
		{
			GroupResponse groupResponse = getGroupBCF().insertGroup(getGroupRequest());
			setResponse(groupResponse);
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error creating groups", e);
			}
		}
		return SUCCESS;
	}

	/**
	 * Delete tag.
	 * 
	 * @return the string
	 */
	public String deleteTag()
	{
		try
		{

			TagResponse tagResponse = getTagBCF().deleteTag(getTagRequest());

			setResponse(tagResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error deleting tag", e);
			}
			getTagResult().setResult(Constants.JSON_FAIL);
		}

		return SUCCESS;
	}

	/**
	 * Adds the tag.
	 * 
	 * @return the string
	 */
	public String addTag()
	{
		try
		{
			TagRequest tagReq = getTagRequest();
			TagResponse tagResponse = getTagBCF().insertTag(tagReq);

			setResponse(tagResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error inserting tag", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Gets the tag result.
	 * 
	 * @return the tag result
	 */
	public JsonResult getTagResult()
	{
		return tagResult;
	}

	/**
	 * Sets the tag result.
	 * 
	 * @param tagResult the new tag result
	 */
	public void setTagResult(JsonResult tagResult)
	{
		this.tagResult = tagResult;
	}

	/**
	 * Gets the search tag result.
	 * 
	 * @return the search tag result
	 */
	public SearchJsonResult getSearchTagResult()
	{
		return searchTagResult;
	}

	/**
	 * Sets the search tag result.
	 * 
	 * @param searchTagResult the new search tag result
	 */
	public void setSearchTagResult(SearchJsonResult searchTagResult)
	{
		this.searchTagResult = searchTagResult;
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

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the tag name.
	 * 
	 * @return the tag name
	 */
	public String getTagName()
	{
		return tagName;
	}

	/**
	 * Sets the tag name.
	 * 
	 * @param tagName the new tag name
	 */
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Gets the auto group.
	 * 
	 * @return the auto group
	 */
	public Boolean getAutoGroup()
	{
		return autoGroup;
	}

	/**
	 * Sets the auto group.
	 * 
	 * @param autoGroup the new auto group
	 */
	public void setAutoGroup(Boolean autoGroup)
	{
		this.autoGroup = autoGroup;
	}

	/**
	 * Gets the include smart points to group.
	 * 
	 * @return the include smart points to group
	 */
	public Boolean getIncludeSmartPointsToGroup()
	{
		return includeSmartPointsToGroup;
	}

	/**
	 * Sets the include smart points to group.
	 * 
	 * @param includeSmartPointsToGroup the new include smart points to group
	 */
	public void setIncludeSmartPointsToGroup(Boolean includeSmartPointsToGroup)
	{
		this.includeSmartPointsToGroup = includeSmartPointsToGroup;
	}

	/**
	 * Gets the group description.
	 * 
	 * @return the group description
	 */
	public String getGroupDescription()
	{
		return groupDescription;
	}

	/**
	 * Sets the group description.
	 * 
	 * @param groupDescription the new group description
	 */
	public void setGroupDescription(String groupDescription)
	{
		this.groupDescription = groupDescription;
	}

	/**
	 * @return the groupRequest
	 */
	public GroupRequest getGroupRequest()
	{
		return groupRequest;
	}

	/**
	 * @param groupRequest the groupRequest to set
	 */
	public void setGroupRequest(GroupRequest groupRequest)
	{
		this.groupRequest = groupRequest;
	}

	/**
	 * @return the tagRequest
	 */
	public TagRequest getTagRequest()
	{
		return tagRequest;
	}

	/**
	 * @param tagRequest the tagRequest to set
	 */
	public void setTagRequest(TagRequest tagRequest)
	{
		this.tagRequest = tagRequest;
	}

	/**
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

}
