package com.sensus.lc.light.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.model.ListTypeEnum;
import com.sensus.lc.group.bcl.IGroupBCL;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.group.model.request.GroupRequest;
import com.sensus.lc.light.bcl.ILightCustomSearchBCL;
import com.sensus.lc.light.dac.ILightColumnFilterDAC;
import com.sensus.lc.light.dac.ILightCustomSearchDAC;
import com.sensus.lc.light.model.Column;
import com.sensus.lc.light.model.CustomSearch;
import com.sensus.lc.light.model.Filter;
import com.sensus.lc.light.model.FilterEnum;
import com.sensus.lc.light.model.LightColumnEnum;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.SearchLight;
import com.sensus.lc.light.model.SearchParameter;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.request.CustomSearchRequest;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.schedule.bcl.IScheduleBCL;
import com.sensus.lc.schedule.model.EventSchedule;
import com.sensus.lc.schedule.model.Schedule;
import com.sensus.lc.schedule.model.request.ScheduleRequest;
import com.sensus.lc.tag.bcl.ITagBCL;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.tag.model.request.TagRequest;

/**
 * Implementation of LightCustomSearchBCL.
 * 
 * @author Thiago
 */
public class LightCustomSearchBCLImpl implements ILightCustomSearchBCL
{
	/** Attributes. */
	private static final Integer ZERO = 0;

	/** The Constant DISPLAY_ORDER_11. */
	private static final Integer DISPLAY_ORDER_11 = 11;

	/** The Constant DISPLAY_ORDER_10. */
	private static final Integer DISPLAY_ORDER_10 = 10;

	/** The Constant DISPLAY_ORDER_9. */
	private static final Integer DISPLAY_ORDER_9 = 9;

	/** The Constant DISPLAY_ORDER_8. */
	private static final Integer DISPLAY_ORDER_8 = 8;

	/** The Constant DISPLAY_ORDER_7. */
	private static final Integer DISPLAY_ORDER_7 = 7;

	/** The Constant DISPLAY_ORDER_6. */
	private static final Integer DISPLAY_ORDER_6 = 6;

	/** The Constant DISPLAY_ORDER_5. */
	private static final Integer DISPLAY_ORDER_5 = 5;

	/** The Constant DISPLAY_ORDER_4. */
	private static final Integer DISPLAY_ORDER_4 = 4;

	/** The Constant DISPLAY_ORDER_3. */
	private static final Integer DISPLAY_ORDER_3 = 3;

	/** The Constant DISPLAY_ORDER_2. */
	private static final Integer DISPLAY_ORDER_2 = 2;

	/** The Constant DISPLAY_ORDER_1. */
	private static final Integer DISPLAY_ORDER_1 = 1;

	/** Messages attributes. */
	private static final String FETCH_COLUMN_FILTER_FAILED = "sensus.mlc.lightbclimpl.fetch.column.filter.failed";

	/** The Constant ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED. */
	private static final String ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED =
			"sensus.mlc.lightbclimpl.add.property.to.customsearch.failed";

	/**
	 * DAC attributes.
	 */
	private ILightColumnFilterDAC lightColumnFilterDAC;

	/** The light custom search dac. */
	private ILightCustomSearchDAC lightCustomSearchDAC;

	/** BCL attributes. */
	private IGroupBCL groupBCL; // injected by Spring through setter

	/** The tag bcl. */
	private ITagBCL tagBCL; // injected by Spring through setter

	/** The schedule bcl. */
	private IScheduleBCL scheduleBCL; // injected by Spring through setter

	/**
	 * Gets the group bcl.
	 * 
	 * @return the groupBCL
	 */
	public IGroupBCL getGroupBCL()
	{
		return groupBCL;
	}

	/**
	 * Sets the group bcl.
	 * 
	 * @param groupBCL the groupBCL to set
	 */
	public void setGroupBCL(IGroupBCL groupBCL)
	{
		this.groupBCL = groupBCL;
	}

	/**
	 * Gets the tag bcl.
	 * 
	 * @return the tagBCL
	 */
	public ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 * 
	 * @param tagBCL the tagBCL to set
	 */
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
	}

	/**
	 * Gets the schedule bcl.
	 * 
	 * @return the scheduleBCL
	 */
	public IScheduleBCL getScheduleBCL()
	{
		return scheduleBCL;
	}

	/**
	 * Sets the schedule bcl.
	 * 
	 * @param scheduleBCL the scheduleBCL to set
	 */
	public void setScheduleBCL(IScheduleBCL scheduleBCL)
	{
		this.scheduleBCL = scheduleBCL;
	}

	/**
	 * Gets the light custom search dac.
	 * 
	 * @return the light custom search dac
	 */
	public ILightCustomSearchDAC getLightCustomSearchDAC()
	{
		return lightCustomSearchDAC;
	}

	/**
	 * Sets the light custom search dac.
	 * 
	 * @param lightCustomSearchDAC the new light custom search dac
	 */
	public void setLightCustomSearchDAC(ILightCustomSearchDAC lightCustomSearchDAC)
	{
		this.lightCustomSearchDAC = lightCustomSearchDAC;
	}

	/**
	 * Gets the light column filter dac.
	 * 
	 * @return the lightColumnFilterDAC
	 */
	public ILightColumnFilterDAC getLightColumnFilterDAC()
	{
		return lightColumnFilterDAC;
	}

	/**
	 * Sets the light column filter dac.
	 * 
	 * @param lightColumnFilterDAC the lightColumnFilterDAC to set
	 */
	public void setLightColumnFilterDAC(ILightColumnFilterDAC lightColumnFilterDAC)
	{
		this.lightColumnFilterDAC = lightColumnFilterDAC;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchAllCustomSearch(com.sensus.mlc.light.model.InquiryPaginationRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(LightRequest lightRequest)
	{
		return getLightCustomSearchDAC().fetchAllCustomSearch(lightRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchLightFilters(com.sensus.mlc.light.model.request.LightFilterRequest)
	 */
	@Override
	public InternalResultsResponse<LightFilterValue> fetchLightFilters(LightFilterRequest lightFilterRequest)
	{
		return getLightColumnFilterDAC().fetchLightFilters(lightFilterRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightCustomSearchBCL#fetchAllColumnFilters(com.sensus.mlc.light.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResultsResponse<ColumnFilterResponse> response = null;
		ColumnFilterResponse responseColumn = null;
		switch (columnFilterRequest.getListTypeEnum())
		{
			case SMARTPOINTLIST:

				response = getLightColumnFilterDAC().fetchAllColumnFilters(columnFilterRequest);
				break;
			default:
				List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();

				messageInfoList.add(new MessageInfo(FETCH_COLUMN_FILTER_FAILED,
						Message.MessageSeverity.Error,
						Message.MessageLevel.FieldValidation));
				response = new InternalResultsResponse<ColumnFilterResponse>();
				response.addMessages(messageInfoList);
				response.setStatus(Status.ExceptionError);

				return response;
		}

		responseColumn = new ColumnFilterResponse();
		ColumnFilterResponse columnFilterResponse = response.getFirstResult();
		if (response.hasResults()
				&& !isNullOrEmpty(columnFilterResponse.getListColumn())
				&& !isNullOrEmpty(columnFilterResponse.getFilters()))
		{

			// Set Columns
			responseColumn.setAdditionalColumns(getAdditionalColumns(columnFilterResponse.getListColumn()));
			responseColumn.setListColumn(columnFilterResponse.getListColumn());

			// Set Filters
			responseColumn.setAdditionalFilters(getAdditionalFilters(columnFilterResponse.getFilters()));
			responseColumn.setFilters(columnFilterResponse.getFilters());

		}
		else
		{
			// Add default values
			addDefaultColumns(response, responseColumn, columnFilterResponse);
			addDefaultFilters(response, responseColumn, columnFilterResponse);
		}

		// Clear and set response
		response.getResultsList().clear();
		response.getResultsList().add(responseColumn);
		response.setStatus(Status.OperationSuccess);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#insertCustomSearch(com.sensus.mlc.light.model.CustomSearch)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		InternalResultsResponse<CustomSearch> response = getLightCustomSearchDAC().insertCustomSearch(request);

		if (!Status.OperationSuccess.equals(response.getStatus()) && !response.hasResults())
		{
			return response;
		}
		// save column and filter
		if (!isNull(request.getCustomSearch())
				&& !isNullOrEmpty(request.getCustomSearch().getListColumn())
				&& !isNullOrEmpty(request.getCustomSearch().getListFilters()))
		{
			ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
			columnFilterRequest.setListTypeEnum(ListTypeEnum.SMARTPOINTLIST);

			columnFilterRequest.setCustomSearchId(response.getFirstResult().getId());

			columnFilterRequest.setListColumn(response.getFirstResult().getListColumn());
			columnFilterRequest.setFilters(response.getFirstResult().getListFilters());
			columnFilterRequest.setUserContext(request.getUserContext());

			InternalResponse responseColumnFilter = insertColumnFilters(columnFilterRequest);
			response = verifyMessage(response, responseColumnFilter);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#insertColumnsFiltersToCustomSearch(com.sensus.mlc.light.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnsFiltersToCustomSearch(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		if (!isNullOrEmpty(columnFilterRequest.getListColumn()))
		{
			response = getLightCustomSearchDAC().insertColumnsToCustomSearch(columnFilterRequest);
		}

		if (!isNullOrEmpty(columnFilterRequest.getFilters()))
		{
			response = getLightCustomSearchDAC().insertFiltersToCustomSearch(columnFilterRequest);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightBCL#insertColumnFilters(com.sensus.mlc.light.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();

		switch (columnFilterRequest.getListTypeEnum())
		{
			case SMARTPOINTLIST:

				response = insertColumnsFiltersToCustomSearch(columnFilterRequest);
				break;
			default:
				response = new InternalResponse();
				List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();

				messageInfoList.add(new MessageInfo(ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED,
						Message.MessageSeverity.Error,
						Message.MessageLevel.FieldValidation));

				response.addMessages(messageInfoList);
				response.setStatus(Status.ExceptionError);
				return response;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.smartpoint.bcl.ISmartPointAccessorBCL#verifyCustomSearch(com.sensus.mlc.smartpoint.model.SearchLight
	 * )
	 */
	@Override
	public SearchLight verifyCustomSearch(SearchLight search)
	{
		SearchLight searchLight = new SearchLight();
		searchLight.setStatusList(search.getStatusList());
		searchLight.setPropertyValues(search.getPropertyValues());
		if (isNullOrEmpty(search.getSearchParameters()))
		{
			return searchLight;
		}

		for (SearchParameter param : search.getSearchParameters())
		{
			if (isNull(param.getValue()))
			{
				continue; // Param is null continue to next.
			}

			param.setValidParameter(true);
			// is the param Group Id?
			if (PropertyEnum.GROUP_ID.equals(param.getPropertyEnum()))
			{
				GroupRequest request = new GroupRequest(new UserContext());
				request.setGroup(new Group(new Integer(param.getValue())));
				InternalResultsResponse<Group> response = getGroupBCL().fetchGroupById(request);
				if (isNull(response.getFirstResult()))
				{
					param.setValidParameter(false);
				}
			}
			else
			// is the param Offset Schedule or Event?
			if (PropertyEnum.OFFSET_SCHEDULE.equals(param.getPropertyEnum())
					|| PropertyEnum.EVENT_SCHEDULE.equals(param.getPropertyEnum()))
			{
				ScheduleRequest request = new ScheduleRequest(new UserContext());
				Schedule schedule = new EventSchedule();
				schedule.setId(new Integer(param.getValue()));
				request.setSchedule(schedule);
				InternalResultsResponse<Schedule> response = getScheduleBCL().fetchScheduleById(request);
				if (isNull(response.getFirstResult()))
				{
					param.setValidParameter(false);
				}
			}
			// is the param Tag Id?
			else if (PropertyEnum.TAG_ID.equals(param.getPropertyEnum()))
			{
				TagRequest request = new TagRequest(new UserContext());
				request.setTag(new Tag(new Integer(param.getValue())));
				InternalResultsResponse<Tag> response = getTagBCL().fetchTagById(request);
				if (isNull(response.getFirstResult()))
				{
					param.setValidParameter(false);
				}
			}
			if (param.isValidParameter())
			{
				searchLight.getSearchParameters().add(param);
			}
		}
		return searchLight;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightBCL#deleteCustomSearch(com.sensus.mlc.light.model.request.CustomSearchRequest)
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		return getLightCustomSearchDAC().deleteCustomSearch(request);
	}

	/**
	 * Verify message.
	 * 
	 * @param response the response
	 * @param responseProperty the response property
	 * @return the internal results response
	 */
	private InternalResultsResponse<CustomSearch> verifyMessage(InternalResultsResponse<CustomSearch> response,
			InternalResponse responseProperty)
	{
		if (!isNull(responseProperty) &&
				Status.OperationSuccess.equals(responseProperty.getStatus()))
		{
			return response;
		}

		List<MessageInfo> messageInfoList = new ArrayList<MessageInfo>();

		messageInfoList.add(new MessageInfo(ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED,
				Message.MessageSeverity.Error,
				Message.MessageLevel.FieldValidation));

		response.addMessages(messageInfoList);
		response.setStatus(Status.ValidationError);
		return response;
	}

	/**
	 * Adds the default filters.
	 * 
	 * @param response the response
	 * @param responseColumn the response column
	 * @param columnFilterResponse the column filter response
	 */
	private void addDefaultFilters(
			InternalResultsResponse<ColumnFilterResponse> response,
			ColumnFilterResponse responseColumn,
			ColumnFilterResponse columnFilterResponse)
	{
		if (!response.hasResults()
				|| isNullOrEmpty(columnFilterResponse.getFilters()))
		{
			responseColumn.setFilters(getDefaultFilters());
			responseColumn.setAdditionalFilters(getDefaultAdditionalFilters());
			return;
		}
		responseColumn.setAdditionalFilters(getAdditionalFilters(columnFilterResponse.getFilters()));
		responseColumn.setFilters(verifyMandatoryFilters(columnFilterResponse.getFilters()));
	}

	/**
	 * Adds the default columns.
	 * 
	 * @param response the response
	 * @param responseColumn the response column
	 * @param columnFilterResponse the column filter response
	 */
	private void addDefaultColumns(
			InternalResultsResponse<ColumnFilterResponse> response,
			ColumnFilterResponse responseColumn,
			ColumnFilterResponse columnFilterResponse)
	{
		if (!response.hasResults()
				|| isNullOrEmpty(columnFilterResponse.getListColumn()))
		{
			responseColumn.setListColumn(getDefaultColumns());
			responseColumn.setAdditionalColumns(getDefaultAdditionalColumns());
			return;
		}
		responseColumn.setAdditionalColumns(getAdditionalColumns(columnFilterResponse.getListColumn()));
		responseColumn.setListColumn(columnFilterResponse.getListColumn());
	}

	/**
	 * Gets the all columns.
	 * 
	 * @return the all columns
	 */
	private List<Column> getAllColumns()
	{
		List<Column> columns = new ArrayList<Column>();
		columns.add(setColumn(LightColumnEnum.BULB_SERIAL_NUMBER, "", ZERO));
		columns.add(setColumn(LightColumnEnum.CITY, "", ZERO));
		columns.add(setColumn(LightColumnEnum.COLOR_TEMPERATURE, "", ZERO));
		columns.add(setColumn(LightColumnEnum.DATE_ADDED, "", ZERO));
		columns.add(setColumn(LightColumnEnum.FIRMWARE_VERSION, "", ZERO));
		columns.add(setColumn(LightColumnEnum.FLEXNET_ID, "", ZERO));
		columns.add(setColumn(LightColumnEnum.HOUSING_COLOR, "", ZERO));
		columns.add(setColumn(LightColumnEnum.SERIAL_NUMBER, "", ZERO));
		columns.add(setColumn(LightColumnEnum.LAMP_TYPE_WATTAGE_DIMMABLE, "", ZERO));
		columns.add(setColumn(LightColumnEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, "", ZERO));
		columns.add(setColumn(LightColumnEnum.MAP_IT, "", ZERO));
		columns.add(setColumn(LightColumnEnum.MODEL_NUMBER, "", ZERO));
		columns.add(setColumn(LightColumnEnum.POLE_ID, "", ZERO));
		columns.add(setColumn(LightColumnEnum.PROTECTED, "", ZERO));
		columns.add(setColumn(LightColumnEnum.LIFECYCLE_STATE, "", ZERO));
		columns.add(setColumn(LightColumnEnum.ALERTS, "", ZERO));
		columns.add(setColumn(LightColumnEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, "", ZERO));
		columns.add(setColumn(LightColumnEnum.INPUT_VOLTAGE_RANGE, "", ZERO));
		columns.add(setColumn(LightColumnEnum.CONSUMPTION, "", ZERO));
		columns.add(setColumn(LightColumnEnum.ECOMODE_REPLACED_TYPE, "", ZERO));
		columns.add(setColumn(LightColumnEnum.ECOMODE_REPLACED_WATTAGE, "", ZERO));
		columns.add(setColumn(LightColumnEnum.ECOMODE, "", ZERO));
		return columns;
	}

	/**
	 * Gets the all filters.
	 * 
	 * @return the all filters
	 */
	private List<Filter> getAllFilters()
	{
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(setFilter(FilterEnum.GROUPS, ZERO));
		filters.add(setFilter(FilterEnum.LIFECYCLE_STATE, ZERO));
		filters.add(setFilter(FilterEnum.ALERTS, ZERO));
		filters.add(setFilter(FilterEnum.ALARM_TYPE, ZERO));
		filters.add(setFilter(FilterEnum.WARNING_TYPE, ZERO));
		filters.add(setFilter(FilterEnum.LIGHT_TYPES, ZERO));
		filters.add(setFilter(FilterEnum.EVENT_SCHEDULE, ZERO));
		filters.add(setFilter(FilterEnum.OFFSET_SCHEDULE, ZERO));
		filters.add(setFilter(FilterEnum.TAGS, ZERO));
		filters.add(setFilter(FilterEnum.ADDRESS, ZERO));
		filters.add(setFilter(FilterEnum.CONFIGURATION, ZERO));
		filters.add(setFilter(FilterEnum.ECOMODE, ZERO));
		filters.add(setFilter(FilterEnum.FIRMWARE_VERSION, ZERO));
		filters.add(setFilter(FilterEnum.DATE_ADDED, ZERO));
		filters.add(setFilter(FilterEnum.MODEL_NUMBER, ZERO));
		filters.add(setFilter(FilterEnum.COLOR_TEMPERATURE, ZERO));
		filters.add(setFilter(FilterEnum.HOUSING_COLOR, ZERO));
		filters.add(setFilter(FilterEnum.VOLTAGE_RANGE, ZERO));
		filters.add(setFilter(FilterEnum.BULB_SERIAL_NUMBER, ZERO));
		filters.add(setFilter(FilterEnum.LIGHT_DRIVER_SERIAL_NUMBER, ZERO));
		filters.add(setFilter(FilterEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, ZERO));
		filters.add(setFilter(FilterEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, ZERO));
		return filters;
	}

	/**
	 * Gets the default additional filters.
	 * 
	 * @return the default additional filters
	 */
	private List<Filter> getDefaultAdditionalFilters()
	{
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(setFilter(FilterEnum.FIRMWARE_VERSION, 0));
		filters.add(setFilter(FilterEnum.DATE_ADDED, 1));
		filters.add(setFilter(FilterEnum.MODEL_NUMBER, 2));
		filters.add(setFilter(FilterEnum.COLOR_TEMPERATURE, DISPLAY_ORDER_3));
		filters.add(setFilter(FilterEnum.HOUSING_COLOR, DISPLAY_ORDER_4));
		filters.add(setFilter(FilterEnum.VOLTAGE_RANGE, DISPLAY_ORDER_5));
		filters.add(setFilter(FilterEnum.BULB_SERIAL_NUMBER, DISPLAY_ORDER_6));
		filters.add(setFilter(FilterEnum.LIGHT_DRIVER_SERIAL_NUMBER, DISPLAY_ORDER_7));
		filters.add(setFilter(FilterEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, DISPLAY_ORDER_8));
		filters.add(setFilter(FilterEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, DISPLAY_ORDER_9));
		return filters;
	}

	/**
	 * Gets the default filters.
	 * 
	 * @return the default filters
	 */
	private List<Filter> getDefaultFilters()
	{
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(setFilter(FilterEnum.GROUPS, 0));
		filters.add(setFilter(FilterEnum.LIFECYCLE_STATE, 1));
		filters.add(setFilter(FilterEnum.ALERTS, 2));
		filters.add(setFilter(FilterEnum.ALARM_TYPE, DISPLAY_ORDER_3));
		filters.add(setFilter(FilterEnum.WARNING_TYPE, DISPLAY_ORDER_4));
		filters.add(setFilter(FilterEnum.LIGHT_TYPES, DISPLAY_ORDER_5));
		filters.add(setFilter(FilterEnum.EVENT_SCHEDULE, DISPLAY_ORDER_6));
		filters.add(setFilter(FilterEnum.OFFSET_SCHEDULE, DISPLAY_ORDER_7));
		filters.add(setFilter(FilterEnum.TAGS, DISPLAY_ORDER_8));
		filters.add(setFilter(FilterEnum.ADDRESS, DISPLAY_ORDER_9));
		filters.add(setFilter(FilterEnum.CONFIGURATION, DISPLAY_ORDER_10));
		filters.add(setFilter(FilterEnum.ECOMODE, DISPLAY_ORDER_11));
		return filters;
	}

	/**
	 * Gets the mandatory filters.
	 * 
	 * @return the mandatory filters
	 */
	private List<Filter> getMandatoryFilters()
	{
		List<Filter> filters = new ArrayList<Filter>();

		filters.add(setFilter(FilterEnum.GROUPS, ZERO));
		filters.add(setFilter(FilterEnum.TAGS, DISPLAY_ORDER_1));
		filters.add(setFilter(FilterEnum.LIFECYCLE_STATE, DISPLAY_ORDER_2));
		filters.add(setFilter(FilterEnum.ALERTS, DISPLAY_ORDER_3));
		filters.add(setFilter(FilterEnum.EVENT_SCHEDULE, DISPLAY_ORDER_4));
		filters.add(setFilter(FilterEnum.OFFSET_SCHEDULE, DISPLAY_ORDER_5));

		return filters;
	}

	/**
	 * Sets the column.
	 * 
	 * @param columnEnum the column enum
	 * @param text the text
	 * @param displayOrder the display order
	 * @return the column
	 */
	private Column setColumn(LightColumnEnum columnEnum, String text, Integer displayOrder)
	{
		Column column = new Column();
		column.setColumnEnum(columnEnum);
		column.setFieldName(text);
		column.setOrdered(true);
		column.setDisplayOrder(displayOrder);
		return column;
	}

	/**
	 * Sets the filter.
	 * 
	 * @param filterEnum the filter enum
	 * @param displayOrder the display order
	 * @return the filter
	 */
	private Filter setFilter(FilterEnum filterEnum, Integer displayOrder)
	{
		Filter filter = new Filter();
		filter.setFilterEnum(filterEnum);
		filter.setDisplayOrder(displayOrder);
		return filter;
	}

	/**
	 * Gets the default columns.
	 * 
	 * @return the default columns
	 */
	private List<Column> getDefaultColumns()
	{
		List<Column> columns = new ArrayList<Column>();
		columns.add(setColumn(LightColumnEnum.POLE_ID, "Pole ID", 0));
		columns.add(setColumn(LightColumnEnum.FLEXNET_ID, "FlexNet ID", 1));
		columns.add(setColumn(LightColumnEnum.LAMP_TYPE_WATTAGE_DIMMABLE, "Light Type", 2));
		columns.add(setColumn(LightColumnEnum.DATE_ADDED, "Date Added", DISPLAY_ORDER_3));
		columns.add(setColumn(LightColumnEnum.CITY, "City", DISPLAY_ORDER_4));
		columns.add(setColumn(LightColumnEnum.MAP_IT, "Map It", DISPLAY_ORDER_5));
		columns.add(setColumn(LightColumnEnum.PROTECTED, "Protected", DISPLAY_ORDER_6));
		columns.add(setColumn(LightColumnEnum.ECOMODE, "Eco-Mode", DISPLAY_ORDER_7));
		columns.add(setColumn(LightColumnEnum.LIFECYCLE_STATE, "State", DISPLAY_ORDER_8));
		columns.add(setColumn(LightColumnEnum.ALERTS, "Alerts", DISPLAY_ORDER_9));
		return columns;
	}

	/**
	 * Gets the default additional columns.
	 * 
	 * @return the default additional columns
	 */
	private List<Column> getDefaultAdditionalColumns()
	{
		List<Column> columns = new ArrayList<Column>();
		columns.add(setColumn(LightColumnEnum.FIRMWARE_VERSION, "Firmware Version", null));
		columns.add(setColumn(LightColumnEnum.MODEL_NUMBER, "Model Number", null));
		columns.add(setColumn(LightColumnEnum.COLOR_TEMPERATURE, "Color Temperature", null));
		columns.add(setColumn(LightColumnEnum.HOUSING_COLOR, "Housing Color", null));
		columns.add(setColumn(LightColumnEnum.INPUT_VOLTAGE_RANGE, "Voltage Range", null));
		columns.add(setColumn(LightColumnEnum.BULB_SERIAL_NUMBER, "Bulb Serial Number", null));
		columns.add(setColumn(LightColumnEnum.SERIAL_NUMBER, "Light Driver Serial Number", null));
		columns.add(setColumn(LightColumnEnum.LOWER_ASSEMBLY_SERIAL_NUMBER, "Lower Assembly Serial Number", null));
		columns.add(setColumn(LightColumnEnum.UPPER_ASSEMBLY_SERIAL_NUMBER, "Upper Assembly Serial Number", null));
		columns.add(setColumn(LightColumnEnum.CONSUMPTION, "Total Consumption", null));
		columns.add(setColumn(LightColumnEnum.ECOMODE_REPLACED_TYPE, "Ecomode_replaced_type", null));
		columns.add(setColumn(LightColumnEnum.ECOMODE_REPLACED_WATTAGE, "Ecomode_relaced_wattage", null));
		return columns;
	}

	/**
	 * Gets the additional columns.
	 * 
	 * @param listColumn the list column
	 * @return the additional columns
	 */
	private List<Column> getAdditionalColumns(List<Column> listColumn)
	{
		List<Column> additionalColumns = getAllColumns();

		Column column = null;
		for (int i = 0; i < additionalColumns.size(); i++)
		{
			column = additionalColumns.get(i);
			for (Column column2 : listColumn)
			{
				if (column2.getColumnEnum().equals(column.getColumnEnum()))
				{
					additionalColumns.remove(column);
					i--;
				}
			}
		}

		return additionalColumns;
	}

	/**
	 * Gets the additional filters.
	 * 
	 * @param listFilter the list filter
	 * @return the additional filters
	 */
	private List<Filter> getAdditionalFilters(List<Filter> listFilter)
	{
		List<Filter> additionalFilters = getAllFilters();

		Filter filter = null;
		for (int i = 0; i < additionalFilters.size(); i++)
		{
			filter = additionalFilters.get(i);
			for (Filter filter2 : listFilter)
			{
				if (filter2.getFilterEnum().equals(filter.getFilterEnum()))
				{
					additionalFilters.remove(filter);
					i--;
				}

			}
		}
		return additionalFilters;
	}

	/**
	 * Verify mandatory filters.
	 * 
	 * @param listFilter the list filter
	 * @return the list
	 */
	private List<Filter> verifyMandatoryFilters(List<Filter> listFilter)
	{
		List<Filter> mandatoryFilters = getMandatoryFilters();
		List<Filter> userFilters = listFilter;
		Boolean isFilterConfigurated;

		Filter filter = null;
		for (int i = 0; i < mandatoryFilters.size(); i++)
		{
			isFilterConfigurated = Boolean.FALSE;

			filter = mandatoryFilters.get(i);

			for (Filter filter2 : userFilters)
			{
				if (filter2.getFilterEnum().equals(filter.getFilterEnum()))
				{
					isFilterConfigurated = Boolean.TRUE;
				}
			}

			if (!isFilterConfigurated)
			{
				userFilters.add(filter);
			}
		}
		return userFilters;
	}

}
