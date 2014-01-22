package com.qat.webdaptive.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.util.QATAppContext;
import com.qat.framework.util.QATInterfaceUtil;
import com.qat.samples.sysmgmt.bas.IProcedureBAS;
import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;
import com.qat.samples.sysmgmt.model.request.ProcedureMaintenanceRequest;
import com.qat.samples.sysmgmt.model.request.RefreshRequest;
import com.qat.samples.sysmgmt.model.response.ProcedureResponse;

/**
 * The Class CountyBaseController.
 */
public class ProcedureBaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcedureBaseController.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "webdaptive.controller.procedure.defaultexception";

	/** The Constant PROCEDURE_RESPONSE. */
	private static final String PROCEDURE_RESPONSE = "procedureResponse";

	/**
	 * Procedure mav.
	 * 
	 * @param request the request
	 * @param returnViewName the return view name
	 * @return the model and view
	 */
	protected ModelAndView procedureMAV(PagedInquiryRequest request, String returnViewName)
	{
		ModelAndView modelAndView = new ModelAndView(returnViewName);
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			modelAndView.addObject(PROCEDURE_RESPONSE, mapper.writeValueAsString(procedureFetchByRequest(request)));
		}
		catch (Exception ex)
		{
			LOG.error(DEFAULT_EXCEPTION_MSG + ":" + ex);
			modelAndView.addObject(PROCEDURE_RESPONSE, null);
		}
		return modelAndView;
	}

	/**
	 * Refresh procedures.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	protected ProcedureResponse refreshProcedures(RefreshRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			IProcedureBAS client = (IProcedureBAS)QATAppContext.getBean("procedureBASClientTarget");
			response = client.refreshProcedures(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Maintain procedures.
	 * 
	 * @param request the request
	 * @param persistType the persist type
	 * @return the procedure response
	 */
	protected ProcedureResponse maintainProcedures(ProcedureMaintenanceRequest request, PersistanceActionEnum persistType)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			IProcedureBAS client = (IProcedureBAS)QATAppContext.getBean("procedureBASClientTarget");
			switch (persistType)
			{
				case INSERT:
					response = client.insertProcedure(request);
					break;
				case UPDATE:
					response = client.updateProcedure(request);
					break;
				case DELETE:
					response = client.deleteProcedure(request);
					break;
				default:
					if (LOG.isDebugEnabled())
					{
						LOG.debug("persistType missing! Setting Unspecified Error status.");
					}
					response.addOperationFailedMessage(DEFAULT_EXCEPTION_MSG);
					break;
			}

		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Procedure fetch by request.
	 * 
	 * @param request the request
	 * @return the procedure response
	 */
	protected ProcedureResponse procedureFetchByRequest(PagedInquiryRequest request)
	{
		ProcedureResponse response = new ProcedureResponse();
		try
		{
			IProcedureBAS client = (IProcedureBAS)QATAppContext.getBean("procedureBASClientTarget");
			response = client.fetchProceduresByRequest(request);
		}
		catch (Exception ex)
		{
			QATInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

}
