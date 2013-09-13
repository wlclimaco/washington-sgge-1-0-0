package com.sensus.dm.controller.savedsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.dm.common.device.bcf.ICustomSearchBCF;
import com.sensus.dm.common.device.model.Column;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceFilterEnum;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.device.model.response.CustomSearchResponse;
import com.sensus.dm.common.device.model.response.InquiryCustomSearchResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.controller.base.BaseController;

/**
 * The Class SavedSearchAPIController.
 */
@Controller
@RequestMapping("/api/search")
public class SavedSearchAPIController extends BaseController
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SavedSearchAPIController.class);

	/** The Constant COLUMNS. */
	private static final String COLUMNS = "columns";

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "SavedSearchAPIController";

	/** The Constant DELETE. */
	private static final String DELETE = "/delete";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant FILTERS. */
	private static final String FILTERS = "filters";

	/** The Constant FETCH_COLUMN_FILTER. */
	private static final String FETCH_COLUMN_FILTER = "/fetchColumnFilter";

	/** The Constant INSERT. */
	private static final String INSERT = "/insert";

	/** The Constant PROPERTY_ENUM. */
	private static final String PROPERTY_ENUM = "propertyEnum";

	/** The Constant TYPE. */
	private static final String TYPE = "type";

	/** The Constant UPDATE_COLUMN_FILTER. */
	private static final String UPDATE_COLUMN_FILTER = "updateColumnFilter";

	/** The custom search BCF. */
	private ICustomSearchBCF customSearchBCF;

	/**
	 * Gets the custom search BCF.
	 * 
	 * @return the customSearchBCF
	 */
	public ICustomSearchBCF getCustomSearchBCF()
	{
		return customSearchBCF;
	}

	/**
	 * Sets the custom search BCF.
	 * 
	 * @param customSearchBCF the customSearchBCF to set
	 */
	@Resource
	public void setCustomSearchBCF(ICustomSearchBCF customSearchBCF)
	{
		this.customSearchBCF = customSearchBCF;
	}

	/**
	 * Fetch All Custom Search.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody InquiryCustomSearchRequest request)
	{

		InquiryCustomSearchResponse response = new InquiryCustomSearchResponse();

		try
		{
			// Add user context to request
			addUserContextToRequest(request);

			response = getCustomSearchBCF().fetchAllCustomSearch(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

	/**
	 * Insert.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	@ResponseBody
	public Response insert(@RequestBody CustomSearchRequest request)
	{
		CustomSearchResponse response = new CustomSearchResponse();

		try
		{
			// Add user context to request
			addUserContextToRequest(request);

			response = getCustomSearchBCF().insertCustomSearch(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Delete.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(@RequestBody CustomSearchRequest request)
	{
		CustomSearchResponse response = new CustomSearchResponse();

		try
		{
			// Add user context to request
			addUserContextToRequest(request);

			response = getCustomSearchBCF().deleteCustomSearch(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Fetch column filter.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_COLUMN_FILTER, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchColumnFilter(@RequestBody Map<String, String> request)
	{

		ColumnFilterResponse response = new ColumnFilterResponse();

		try
		{
			ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();

			List<Property> properties = new ArrayList<Property>();
			properties.add(new Property(request.get(PROPERTY_ENUM), null));
			columnFilterRequest.setProperties(properties);

			// ADD user context to request
			addUserContextToRequest(columnFilterRequest);

			response = getCustomSearchBCF().fetchAllColumnFilters(columnFilterRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

	/*
	 * Update column filter.
	 * @return the response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = UPDATE_COLUMN_FILTER, method = RequestMethod.POST)
	@ResponseBody
	public Response updateColumnFilter(@RequestBody Map<String, Object> request)
	{

		ColumnFilterResponse response = new ColumnFilterResponse();

		try
		{
			ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();

			columnFilterRequest.setPropertyEnum(PropertyEnum.valueOf((String)request.get(PROPERTY_ENUM)));

			// ADD user context to request
			addUserContextToRequest(columnFilterRequest);

			List<String> listColumnFilter = (List<String>)request.get(FILTERS);
			;

			if (FILTERS.equals(request.get(TYPE)))
			{
				List<DeviceFilterEnum> listFilters = new ArrayList<DeviceFilterEnum>();

				for (String filter : listColumnFilter)
				{
					listFilters.add(DeviceFilterEnum.valueOf(filter));
				}

				columnFilterRequest.setFilters(listFilters);
			}
			else if (COLUMNS.equals(request.get(TYPE)))
			{
				List<Column> listColumns = new ArrayList<Column>();

				for (String column : listColumnFilter)
				{
					listColumns.add(new Column(DeviceColumnEnum.valueOf(column)));
				}

				columnFilterRequest.setListColumn(listColumns);
			}

			response = getCustomSearchBCF().updateColumnFilters(columnFilterRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;

	}

}
