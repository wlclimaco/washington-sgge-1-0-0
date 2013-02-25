package com.sensus.mlc.wui.base.util;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.sensus.common.model.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.smartpoint.bcf.ISmartPointAccessorBCF;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.wui.base.action.ActionBase;

/**
 * The Class CustomizeAjaxAction.
 * 
 * Used for customize filter/column component.
 * 
 * Action for searching saved.
 * 
 * @author QAT - BRAZIL
 */
public class CustomizeAjaxAction extends ActionBase implements SessionAware
{
	/** The Constant CUSTOMIZE. */
	private static final String CUSTOMIZE = "customize";

	/** The Constant FILTERS. */
	private static final String FILTERS = "filters";

	/** The Constant COLUMNS. */
	private static final String COLUMNS = "columns";

	/**
	 * The SmartPoint BCF object.
	 */
	private transient ISmartPointAccessorBCF smartPointAccessorBCF;

	/** The session customize type. */
	private String elementType;

	/** The page. */
	private String page;

	/** The response. */
	private Response response;

	/** The column filter request. */
	private ColumnFilterRequest columnFilterRequest;

	/**
	 * Creates the session customize.
	 * 
	 * @return the string
	 */
	public String createSessionCustomize()
	{
		try
		{
			Map<String, Object> session = ActionContext.getContext().getSession();

			Response frontEndResponse = new Response();

			if (ValidationUtil.isNull(getColumnFilterRequest()) && ValidationUtil.isNullOrEmpty(getElementType()))
			{

				frontEndResponse.setOperationSuccess(false);

				setResponse(frontEndResponse);

				return SUCCESS;
			}

			ColumnFilterRequest columnFilterReq = getColumnFilterRequest();

			session.put(getElementType().toLowerCase(), columnFilterReq);

			frontEndResponse.setOperationSuccess(true);

			setResponse(frontEndResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error create session customize", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Recover session customize.
	 * 
	 * @return the string
	 */
	public String recoverSessionCustomize()
	{
		try
		{
			setColumnFilterRequest(CustomizeAjaxAction.checkSession(getElementType().toLowerCase()));
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error recover session customize", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Check session.
	 * 
	 * @param type the type
	 * @return the string[]
	 */
	public static ColumnFilterRequest checkSession(String type)
	{
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (ValidationUtil.isNullOrEmpty(type) && ValidationUtil.isNull(session.get(type)))
		{
			return null;
		}

		return (ColumnFilterRequest)session.get(type);
	}

	/**
	 * Removes the session customize.
	 * 
	 * @return the string
	 */
	public String removeSessionCustomize()
	{
		try
		{
			Map<String, Object> session = ActionContext.getContext().getSession();

			Response frontEndResponse = new Response();

			if (ValidationUtil.isNull(getColumnFilterRequest()) && ValidationUtil.isNullOrEmpty(getElementType()))
			{

				frontEndResponse.setOperationSuccess(false);

				setResponse(frontEndResponse);

				return SUCCESS;
			}

			session.remove(getElementType().toLowerCase());

			frontEndResponse.setOperationSuccess(true);

			setResponse(frontEndResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error remove session customize", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Fetch customize.
	 * 
	 * @return the string
	 */
	public String fetchCustomize()
	{

		try
		{
			ColumnFilterRequest columnFilterReq = getColumnFilterRequest();

			ColumnFilterResponse columnFilterResponse =
					getSmartPointAccessorBCF().fetchAllColumnFilters(columnFilterReq);

			setResponse(columnFilterResponse);

		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Error fetch filters and columns", e);
			}
		}

		return SUCCESS;
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page
	 */
	public String getPage()
	{
		return page;
	}

	/**
	 * Sets the page.
	 * 
	 * @param page the page to set
	 */
	public void setPage(String page)
	{
		this.page = page;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response the response to set
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * Gets the smart point accessor bcf.
	 * 
	 * @return the smart point accessor bcf
	 */
	public ISmartPointAccessorBCF getSmartPointAccessorBCF()
	{
		return smartPointAccessorBCF;
	}

	/**
	 * Sets the smart point accessor bcf.
	 * 
	 * @param smartPointAccessorBCF the new smart point accessor bcf
	 */
	public void setSmartPointAccessorBCF(ISmartPointAccessorBCF smartPointAccessorBCF)
	{
		this.smartPointAccessorBCF = smartPointAccessorBCF;
	}

	/**
	 * @return the columnFilterRequest
	 */
	public ColumnFilterRequest getColumnFilterRequest()
	{
		return columnFilterRequest;
	}

	/**
	 * @param columnFilterRequest the columnFilterRequest to set
	 */
	public void setColumnFilterRequest(ColumnFilterRequest columnFilterRequest)
	{
		this.columnFilterRequest = columnFilterRequest;
	}

	/**
	 * @return the elementType
	 */
	public String getElementType()
	{
		return elementType;
	}

	/**
	 * @param elementType the elementType to set
	 */
	public void setElementType(String elementType)
	{
		this.elementType = elementType;
	}

}
