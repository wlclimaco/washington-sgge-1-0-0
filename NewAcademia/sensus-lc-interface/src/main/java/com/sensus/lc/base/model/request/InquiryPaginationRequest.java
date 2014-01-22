package com.sensus.lc.base.model.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.request.InquiryRequest;
import com.sensus.lc.base.util.LCPropertiesExtractorUtil;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.AddressCriteria;
import com.sensus.lc.light.model.criteria.AlertCriteria;
import com.sensus.lc.light.model.criteria.ConfigurationCriteria;
import com.sensus.lc.light.model.criteria.GroupCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.criteria.LightCustomSearchCriteria;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.criteria.OperationalDataCriteria;
import com.sensus.lc.light.model.criteria.ProcessCriteria;
import com.sensus.lc.light.model.criteria.ScheduleCriteria;
import com.sensus.lc.light.model.criteria.TagCriteria;

/**
 * The Class InquiryPaginationRequest.
 */
/**
 * @author QATEmployee
 * 
 */
public class InquiryPaginationRequest extends InquiryRequest
{
	/**
	 * Actions and other attributes.
	 */
	private String fileName;
	private Integer processId;
	private Date initialDate;
	private Date endDate;
	private Boolean paginationAllSelected = false;
	private String datePattern;
	private String timezone;
	private String action;
	private List<Integer> selectionPaginationIds = new ArrayList<Integer>();
	private List<Integer> unselectionPaginationIds = new ArrayList<Integer>();
	private List<Column> listColumn;
	private Boolean monitored;
	private SearchLight searchLight;

	/**
	 * Criteria attributes.
	 */
	private LightCriteria lightCriteria;
	private ActionCriteria actionCriteria;
	private GroupCriteria groupCriteria;
	private AlertCriteria alertCriteria;
	private AddressCriteria addressCriteria;
	private ProcessCriteria processCriteria;
	private ScheduleCriteria scheduleCriteria;
	private ConfigurationCriteria configurationCriteria;
	private OperationalDataCriteria operationalDataCriteria;
	private TagCriteria tagCriteria;
	private NotificationHistoryCriteria notificationHistoryCriteria;
	private LightCustomSearchCriteria lightCustomSearchCriteria;

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * Sets the action.
	 * 
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * Gets the timezone.
	 * 
	 * @return the timezone
	 */
	public String getTimezone()
	{
		return timezone;
	}

	/**
	 * Sets the timezone.
	 * 
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone)
	{
		this.timezone = timezone;
	}

	/**
	 * Gets the date pattern.
	 * 
	 * @return the datePattern
	 */
	public String getDatePattern()
	{
		return datePattern;
	}

	/**
	 * Sets the date pattern.
	 * 
	 * @param datePattern the datePattern to set
	 */
	public void setDatePattern(String datePattern)
	{
		this.datePattern = datePattern;
	}

	/**
	 * Gets the pagination all selected.
	 * 
	 * @return the paginationAllSelected
	 */
	public Boolean getPaginationAllSelected()
	{
		return paginationAllSelected;
	}

	/**
	 * Sets the pagination all selected.
	 * 
	 * @param paginationAllSelected the paginationAllSelected to set
	 */
	public void setPaginationAllSelected(Boolean paginationAllSelected)
	{
		this.paginationAllSelected = paginationAllSelected;
	}

	/**
	 * Gets the selection pagination ids.
	 * 
	 * @return the selectionPaginationIds
	 */
	public List<Integer> getSelectionPaginationIds()
	{
		return selectionPaginationIds;
	}

	/**
	 * Sets the selection pagination ids.
	 * 
	 * @param selectionPaginationIds the selectionPaginationIds to set
	 */
	public void setSelectionPaginationIds(List<Integer> selectionPaginationIds)
	{
		this.selectionPaginationIds = selectionPaginationIds;
	}

	/**
	 * Gets the unselection pagination ids.
	 * 
	 * @return the unselection pagination ids
	 */
	public List<Integer> getUnselectionPaginationIds()
	{
		return unselectionPaginationIds;
	}

	/**
	 * Sets the unselection pagination ids.
	 * 
	 * @param unselectionPaginationIds the new unselection pagination ids
	 */
	public void setUnselectionPaginationIds(List<Integer> unselectionPaginationIds)
	{
		this.unselectionPaginationIds = unselectionPaginationIds;
	}

	/**
	 * Gets the allowed group id list.
	 * 
	 * @return the allowed group id list
	 */
	public List<Integer> getAllowedGroupIdList()
	{
		if (getUserContext() != null)
		{
			return LCPropertiesExtractorUtil.extractAuthorityId(getUserContext().getAuthorities());
		}

		return new ArrayList<Integer>();
	}

	/**
	 * Instantiates a new inquiry pagination request.
	 */
	public InquiryPaginationRequest()
	{
	}

	/**
	 * Instantiates a new inquiry pagination request.
	 * 
	 * @param userContext the user context
	 */
	public InquiryPaginationRequest(UserContext userContext)
	{
		setUserContext(userContext);
	}

	/**
	 * Gets the fileName.
	 * 
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the fileName.
	 * 
	 * @param fileName the new fileName
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the initial date.
	 * 
	 * @return the initial date
	 */
	public Date getInitialDate()
	{
		return initialDate;
	}

	/**
	 * Sets the initial date.
	 * 
	 * @param initialDate the new initial date
	 */
	public void setInitialDate(Date initialDate)
	{
		this.initialDate = initialDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Gets the list column.
	 * 
	 * @return the list column
	 */
	public List<Column> getListColumn()
	{
		return listColumn;
	}

	/**
	 * Sets the list column.
	 * 
	 * @param listColumn the new list column
	 */
	public void setListColumn(List<Column> listColumn)
	{
		this.listColumn = listColumn;
	}

	/**
	 * Gets the monitored.
	 * 
	 * @return the monitored
	 */
	public Boolean isMonitored()
	{
		return monitored;
	}

	/**
	 * Sets the monitored.
	 * 
	 * @param monitored the new monitored
	 */
	public void setMonitored(Boolean monitored)
	{
		this.monitored = monitored;
	}

	/**
	 * Gets the search light.
	 * 
	 * @return the search light
	 */
	public SearchLight getSearchLight()
	{
		return searchLight;
	}

	/**
	 * Sets the search light.
	 * 
	 * @param searchLight the new search light
	 */
	public void setSearchLight(SearchLight searchLight)
	{
		this.searchLight = searchLight;
	}

	/**
	 * Gets the light criteria.
	 * 
	 * @return the light criteria
	 */
	public LightCriteria getLightCriteria()
	{
		return lightCriteria;
	}

	/**
	 * Sets the light criteria.
	 * 
	 * @param lightCriteria the new light criteria
	 */
	public void setLightCriteria(LightCriteria lightCriteria)
	{
		this.lightCriteria = lightCriteria;
	}

	/**
	 * Gets the action criteria.
	 * 
	 * @return the action criteria
	 */
	public ActionCriteria getActionCriteria()
	{
		return actionCriteria;
	}

	/**
	 * Sets the action criteria.
	 * 
	 * @param actionCriteria the new action criteria
	 */
	public void setActionCriteria(ActionCriteria actionCriteria)
	{
		this.actionCriteria = actionCriteria;
	}

	/**
	 * Gets the group criteria.
	 * 
	 * @return the group criteria
	 */
	public GroupCriteria getGroupCriteria()
	{
		return groupCriteria;
	}

	/**
	 * Sets the group criteria.
	 * 
	 * @param groupCriteria the new group criteria
	 */
	public void setGroupCriteria(GroupCriteria groupCriteria)
	{
		this.groupCriteria = groupCriteria;
	}

	/**
	 * Gets the alert criteria.
	 * 
	 * @return the alert criteria
	 */
	public AlertCriteria getAlertCriteria()
	{
		return alertCriteria;
	}

	/**
	 * Sets the alert criteria.
	 * 
	 * @param alertCriteria the new alert criteria
	 */
	public void setAlertCriteria(AlertCriteria alertCriteria)
	{
		this.alertCriteria = alertCriteria;
	}

	/**
	 * Gets the address criteria.
	 * 
	 * @return the address criteria
	 */
	public AddressCriteria getAddressCriteria()
	{
		return addressCriteria;
	}

	/**
	 * Sets the address criteria.
	 * 
	 * @param addressCriteria the new address criteria
	 */
	public void setAddressCriteria(AddressCriteria addressCriteria)
	{
		this.addressCriteria = addressCriteria;
	}

	/**
	 * Gets the process criteria.
	 * 
	 * @return the process criteria
	 */
	public ProcessCriteria getProcessCriteria()
	{
		return processCriteria;
	}

	/**
	 * Sets the process criteria.
	 * 
	 * @param processCriteria the new process criteria
	 */
	public void setProcessCriteria(ProcessCriteria processCriteria)
	{
		this.processCriteria = processCriteria;
	}

	/**
	 * Gets the schedule criteria.
	 * 
	 * @return the schedule criteria
	 */
	public ScheduleCriteria getScheduleCriteria()
	{
		return scheduleCriteria;
	}

	/**
	 * Sets the schedule criteria.
	 * 
	 * @param scheduleCriteria the new schedule criteria
	 */
	public void setScheduleCriteria(ScheduleCriteria scheduleCriteria)
	{
		this.scheduleCriteria = scheduleCriteria;
	}

	/**
	 * Gets the configuration criteria.
	 * 
	 * @return the configuration criteria
	 */
	public ConfigurationCriteria getConfigurationCriteria()
	{
		return configurationCriteria;
	}

	/**
	 * Sets the configuration criteria.
	 * 
	 * @param configurationCriteria the new configuration criteria
	 */
	public void setConfigurationCriteria(ConfigurationCriteria configurationCriteria)
	{
		this.configurationCriteria = configurationCriteria;
	}

	/**
	 * Gets the operational data criteria.
	 * 
	 * @return the operational data criteria
	 */
	public OperationalDataCriteria getOperationalDataCriteria()
	{
		return operationalDataCriteria;
	}

	/**
	 * Sets the operational data criteria.
	 * 
	 * @param operationalDataCriteria the new operational data criteria
	 */
	public void setOperationalDataCriteria(OperationalDataCriteria operationalDataCriteria)
	{
		this.operationalDataCriteria = operationalDataCriteria;
	}

	/**
	 * Gets the tag criteria.
	 * 
	 * @return the tag criteria
	 */
	public TagCriteria getTagCriteria()
	{
		return tagCriteria;
	}

	/**
	 * Sets the tag criteria.
	 * 
	 * @param tagCriteria the new tag criteria
	 */
	public void setTagCriteria(TagCriteria tagCriteria)
	{
		this.tagCriteria = tagCriteria;
	}

	/**
	 * Gets the notification history criteria.
	 * 
	 * @return the notification history criteria
	 */
	public NotificationHistoryCriteria getNotificationHistoryCriteria()
	{
		return notificationHistoryCriteria;
	}

	/**
	 * Sets the notification history criteria.
	 * 
	 * @param notificationHistoryCriteria the new notification history criteria
	 */
	public void setNotificationHistoryCriteria(NotificationHistoryCriteria notificationHistoryCriteria)
	{
		this.notificationHistoryCriteria = notificationHistoryCriteria;
	}

	/**
	 * Gets the light custom search criteria.
	 * 
	 * @return the light custom search criteria
	 */
	public LightCustomSearchCriteria getLightCustomSearchCriteria()
	{
		return lightCustomSearchCriteria;
	}

	/**
	 * Sets the light custom search criteria.
	 * 
	 * @param lightCustomSearchCriteria the new light custom search criteria
	 */
	public void setLightCustomSearchCriteria(LightCustomSearchCriteria lightCustomSearchCriteria)
	{
		this.lightCustomSearchCriteria = lightCustomSearchCriteria;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryPaginationRequest [getAction()=" + getAction() + ", getTimezone()=" + getTimezone()
				+ ", getDatePattern()=" + getDatePattern() + ", getPaginationAllSelected()="
				+ getPaginationAllSelected() + ", getSelectionPaginationIds()=" + getSelectionPaginationIds()
				+ ", getUnselectionPaginationIds()=" + getUnselectionPaginationIds() + ", getAllowedGroupIdList()="
				+ getAllowedGroupIdList() + ", getFileName()=" + getFileName() + ", getProcessId()=" + getProcessId()
				+ ", getInitialDate()=" + getInitialDate() + ", getEndDate()=" + getEndDate() + ", getListColumn()="
				+ getListColumn() + ", isMonitored()=" + isMonitored() + ", getSearchLight()=" + getSearchLight()
				+ ", getLightCriteria()=" + getLightCriteria() + ", getActionCriteria()=" + getActionCriteria()
				+ ", getGroupCriteria()=" + getGroupCriteria() + ", getAlertCriteria()=" + getAlertCriteria()
				+ ", getAddressCriteria()=" + getAddressCriteria() + ", getProcessCriteria()=" + getProcessCriteria()
				+ ", getScheduleCriteria()=" + getScheduleCriteria() + ", getConfigurationCriteria()="
				+ getConfigurationCriteria() + ", getOperationalDataCriteria()=" + getOperationalDataCriteria()
				+ ", getTagCriteria()=" + getTagCriteria() + ", getNotificationHistoryCriteria()="
				+ getNotificationHistoryCriteria() + ", getLightCustomSearchCriteria()="
				+ getLightCustomSearchCriteria() + ", toString()=" + super.toString() + "]";
	}

}
