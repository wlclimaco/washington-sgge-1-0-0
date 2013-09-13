package com.sensus.dm.controller.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sensus.dm.common.device.bcf.ICustomSearchBCF;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.property.model.Property;

/**
 * The Class DeviceBaseController.
 */
public class DeviceBaseController extends BaseViewController
{
	/** The Constant COLUMNS. */
	private static final String COLUMNS = "Columns";

	/** The column filter. */
	public static String COLUMN_FILTER = "ColumnFilter";

	/** The filters. */
	private static final String FILTERS = "Filters";

	/** The Constant SERVICE_TYPE. */
	public static final String SERVICE_TYPE = "serviceType";

	/** The Constant SESSION. */
	public static final String SESSION = "session";

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
	 * Load column filters.
	 * 
	 * @param deviceType the device type
	 * @return the column filter response
	 */
	public ColumnFilterResponse loadColumnFilters(String deviceType)
	{
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(deviceType + "_FILTER", null));
		properties.add(new Property(deviceType + "_COLUMN", null));

		ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
		columnFilterRequest.setProperties(properties);

		// ADD user context to request
		addUserContextToRequest(columnFilterRequest);

		// FetchColumn Filter
		return getCustomSearchBCF().fetchAllColumnFilters(columnFilterRequest);
	}

	/**
	 * Load session column filter.
	 * 
	 * @param request the request
	 * @return the hash map
	 */
	public HashMap<String, Object> loadSessionColumnFilter(HttpServletRequest request)
	{
		HashMap<String, Object> response = new HashMap<String, Object>();
		HttpSession session = request.getSession(true);

		response.put(FILTERS, session.getAttribute(FILTERS));
		response.put(COLUMNS, session.getAttribute(COLUMNS));

		return response;
	}
}