package com.sensus.lc.analytics.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.lc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.bcf.IAnalyticsBCF;
import com.sensus.lc.analytics.bcl.IAnalyticsBCL;
import com.sensus.lc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsGroupColumns;
import com.sensus.lc.analytics.model.AnalyticsTypeEnum;
import com.sensus.lc.analytics.model.request.AnalyticsCSVRequest;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.analytics.model.response.AnalyticsResponse;
import com.sensus.lc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.lc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVInternalResponse;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.bcf.IProcessBCF;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Class AnalyticsBCFImpl.
 */
public class AnalyticsBCFImpl extends AbstractBaseBCF implements IAnalyticsBCF
{

	/** The Constant DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG = "sensus.mlc.analyticsbcfimpl.defaultexception";

	/** The Constant ANALYTICS_CSV_REQUEST_NAME. */
	private static final String ANALYTICS_CSV_REQUEST_NAME = AnalyticsCSVRequest.class.getSimpleName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AnalyticsBCFImpl.class);

	/** The analytics bcl. */
	private IAnalyticsBCL analyticsBCL; // injected by Spring through setter

	/** The selection pagination controller. */
	private ValidationController analyticsTypeValidationController;

	/** The request validation controller. */
	private ValidationController requestValidationController;

	/** The process bcf. */
	private IProcessBCF processBCF;

	/**
	 * Gets the analytics type validation controller.
	 * 
	 * @return the analytics type validation controller
	 */
	public ValidationController getAnalyticsTypeValidationController()
	{
		return analyticsTypeValidationController;
	}

	/**
	 * Sets the analytics type validation controller.
	 * 
	 * @param analyticsTypeValidationController the new analytics type validation controller
	 */
	public void setAnalyticsTypeValidationController(ValidationController analyticsTypeValidationController)
	{
		this.analyticsTypeValidationController = analyticsTypeValidationController;
	}

	/**
	 * Gets the process bcf.
	 * 
	 * @return the process bcf
	 */
	public IProcessBCF getProcessBCF()
	{
		return processBCF;
	}

	/**
	 * Sets the process bcf.
	 * 
	 * @param processBCF the new process bcf
	 */
	public void setProcessBCF(IProcessBCF processBCF)
	{
		this.processBCF = processBCF;
	}

	/**
	 * Gets the analytics bcl.
	 * 
	 * @return the analytics bcl
	 */
	public IAnalyticsBCL getAnalyticsBCL()
	{
		return analyticsBCL;
	}

	/**
	 * Sets the analytics bcl.
	 * 
	 * @param analyticsBCL the new analytics bcl
	 */
	public void setAnalyticsBCL(IAnalyticsBCL analyticsBCL)
	{
		this.analyticsBCL = analyticsBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.bcf.impl.AbstractBaseBCF#getRequestValidationController()
	 */
	@Override
	public ValidationController getRequestValidationController()
	{
		return requestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.base.bcf.impl.AbstractBaseBCF#setRequestValidationController(com.sensus.common.validation.
	 * ValidationController)
	 */
	@Override
	public void setRequestValidationController(ValidationController requestValidationController)
	{
		this.requestValidationController = requestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsByGroup(com.sensus.mlc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InquiryAnalyticsResponse fetchAllAnalyticsByGroup(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InquiryAnalyticsResponse response = new InquiryAnalyticsResponse();
		InternalResultsResponse<AnalyticsGroup> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_ANALYTICS_BY_GROUP);
			context.putObjectToBeValidated(InquiryAnalyticsRequest.class.getSimpleName(), inquiryAnalyticsRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
					inquiryAnalyticsRequest.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
					inquiryAnalyticsRequest.getEndDate());
			context.putObjectToBeValidated(AnalyticsTypeEnum.class.getSimpleName(),
					inquiryAnalyticsRequest.getAnalyticsTypeEnum());
			context.putObjectToBeValidated(AnalyticsDateTypeEnum.class.getSimpleName(),
					inquiryAnalyticsRequest.getAnalyticsDateTypeEnum());

			if (getLightingControlRequestValidationController().validate(context)
					&& getRangeDateValidationController().validate(context)
					&& getAnalyticsTypeValidationController().validate(context))
			{
				setOrderByDefault(inquiryAnalyticsRequest);
				internalResponse = getAnalyticsBCL().fetchAllAnalyticsByGroup(inquiryAnalyticsRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchDashboardResume(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchDashboardResume(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		InternalResultsResponse<AnalyticsGroupColumns> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_DASHBOARD_RESUME);
			context.putObjectToBeValidated(AnalyticsRequest.class.getSimpleName(), analyticsRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
					analyticsRequest.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
					analyticsRequest.getEndDate());

			if (getLightingControlRequestValidationController().validate(context)
					&& getRangeDateValidationController().validate(context))
			{
				internalResponse = getAnalyticsBCL().fetchDashboardResume(analyticsRequest);
				response.setColumns(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsByDate(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAllAnalyticsByDate(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		InternalResultsResponse<AnalyticsGroupColumns> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_ANALYTICS_BY_DATE);
			context.putObjectToBeValidated(AnalyticsRequest.class.getSimpleName(), analyticsRequest);
			context.putObjectToBeValidated(AnalyticsTypeEnum.class.getSimpleName(),
					analyticsRequest.getAnalyticsTypeEnum());
			context.putObjectToBeValidated(AnalyticsDateTypeEnum.class.getSimpleName(),
					analyticsRequest.getAnalyticsDateTypeEnum());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
					analyticsRequest.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
					analyticsRequest.getEndDate());

			if (getLightingControlRequestValidationController().validate(context)
					&& getRangeDateValidationController().validate(context)
					&& getAnalyticsTypeValidationController().validate(context))
			{
				internalResponse = getAnalyticsBCL().fetchAllAnalyticsByDate(analyticsRequest);
				response.setColumns(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAnalyticsAlarmsByStatusId(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		InternalResultsResponse<Light> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(),
					MLCPersistanceActionEnum.FETCH_ANALYTICS_BY_STATUS_ID);
			context.putObjectToBeValidated(AnalyticsRequest.class.getSimpleName(), analyticsRequest);
			context.putObjectToBeValidated(AlertSubTypeEnum.class.getSimpleName(), analyticsRequest.getAlertSubtype());

			if (getLightingControlRequestValidationController().validate(context)
					&& getAnalyticsTypeValidationController().validate(context))
			{
				internalResponse = getAnalyticsBCL().fetchAnalyticsAlarmsByStatusId(analyticsRequest);
				response.setLights(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAnalyticsAlertsByType(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAnalyticsAlertsByType(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		InternalResultsResponse<Map<String, Integer>> internalResultsResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(AnalyticsRequest.class.getSimpleName(), analyticsRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResultsResponse = getAnalyticsBCL().fetchAnalyticsAlertsByType(analyticsRequest);
				response.setAlertsByType(internalResultsResponse.getFirstResult());
			}

			handleOperationStatusAndMessages(response, internalResultsResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchDashboardHeader(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchDashboardHeader(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		InternalResultsResponse<AnalyticsGroupColumns> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(AnalyticsRequest.class.getSimpleName(), analyticsRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getAnalyticsBCL().fetchDashboardHeader(analyticsRequest);
				response.setColumns(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.analytics.bcf.IAnalyticsBCF#generateFileCSV(com.sensus.lc.analytics.model.request.AnalyticsCSVRequest
	 * )
	 */
	@Override
	public CSVResponse generateFileCSV(AnalyticsCSVRequest analyticsCSVRequest)
	{
		CSVResponse response = new CSVResponse();
		CSVInternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			ValidationContext requestContext = new ValidationContext();
			requestContext.putObjectToBeValidated(ANALYTICS_CSV_REQUEST_NAME, analyticsCSVRequest);
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(InquiryAnalyticsRequest.class.getSimpleName(),
					analyticsCSVRequest.getInquiryAnalyticsRequest());
			context.putObjectToBeValidated(AnalyticsTypeEnum.class.getSimpleName(),
					analyticsCSVRequest.getInquiryAnalyticsRequest().getAnalyticsTypeEnum());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
					analyticsCSVRequest.getInquiryAnalyticsRequest().getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
					analyticsCSVRequest.getInquiryAnalyticsRequest().getEndDate());

			if (!getRequestValidationController().validate(requestContext)) // Validate Tenant and UserContext
			{
				handleOperationStatusAndMessages(response, internalResponse, requestContext.getMessages(), false);
				return response;
			}

			if (getAnalyticsTypeValidationController().validate(context)
					|| getInquiryRequestValidationController().validate(context))
			{
				// generate CSV File
				setOrderByDefault(analyticsCSVRequest.getInquiryAnalyticsRequest());
				internalResponse = getAnalyticsBCL().generateFileCSV(analyticsCSVRequest);
				response.setFileName(internalResponse.getFileName());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcf.IAnalyticsBCF#insertCSVProcess(com.sensus.mlc.base.model.request.LightSelectionRequest
	 * )
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest request)
	{
		return getProcessBCF().insertCSVProcess(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsGroup(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();
		InternalResultsResponse<AnalyticsGroup> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(AnalyticsRequest.class.getSimpleName(), analyticsRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getAnalyticsBCL().fetchAllAnalyticsGroup(analyticsRequest);
				response.setAnalyticsGroups(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Sets the order by default.
	 * 
	 * @param inquiryAnalyticsRequest the new order by default
	 */
	private void setOrderByDefault(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		if (ValidationUtil.isNullOrEmpty(inquiryAnalyticsRequest.getSortExpressions()))
		{
			inquiryAnalyticsRequest.addSortExpressions(new SortExpression("total", Direction.Descending));
		}
	}
}
