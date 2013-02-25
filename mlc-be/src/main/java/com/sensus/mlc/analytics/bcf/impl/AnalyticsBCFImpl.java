package com.sensus.mlc.analytics.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;
import static com.sensus.mlc.base.util.LCHelp.setLightProperties;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.analytics.bcl.IAnalyticsBCL;
import com.sensus.mlc.analytics.model.AnalyticsDateTypeEnum;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;

/**
 * The Class AnalyticsBCFImpl.
 */
public class AnalyticsBCFImpl extends AbstractBaseBCF implements IAnalyticsBCF
{

	/** The Constant DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG = "sensus.mlc.analyticsbcfimpl.defaultexception";

	/** The Constant DEFAULT_LIGHT_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_ANALYTICS_BCL_EXCEPTION_MSG = "sensus.mlc.smartpointbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AnalyticsBCFImpl.class);

	/** The analytics bcl. */
	private IAnalyticsBCL analyticsBCL; // injected by Spring through setter

	/** The selection pagination controller. */
	private ValidationController analyticsTypeValidationController;

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
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(), inquiryAnalyticsRequest.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(), inquiryAnalyticsRequest.getEndDate());
			context.putObjectToBeValidated(AnalyticsTypeEnum.class.getSimpleName(), inquiryAnalyticsRequest.getAnalyticsTypeEnum());
			context.putObjectToBeValidated(AnalyticsDateTypeEnum.class.getSimpleName(), inquiryAnalyticsRequest.getAnalyticsDateTypeEnum());

			if (getLightingControlRequestValidationController().validate(context)
					&& getRangeDateValidationController().validate(context)
					&& getAnalyticsTypeValidationController().validate(context))
			{
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
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(), analyticsRequest.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(), analyticsRequest.getEndDate());

			if (getLightingControlRequestValidationController().validate(context) && getRangeDateValidationController().validate(context))
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
			context.putObjectToBeValidated(AnalyticsTypeEnum.class.getSimpleName(), analyticsRequest.getAnalyticsTypeEnum());
			context.putObjectToBeValidated(AnalyticsDateTypeEnum.class.getSimpleName(), analyticsRequest.getAnalyticsDateTypeEnum());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(), analyticsRequest.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(), analyticsRequest.getEndDate());

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
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_ANALYTICS_BY_STATUS_ID);
			context.putObjectToBeValidated(AnalyticsRequest.class.getSimpleName(), analyticsRequest);
			context.putObjectToBeValidated(StatusExceptionTypeEnum.class.getSimpleName(), analyticsRequest.getStatusExceptionTypeEnum());

			if (getLightingControlRequestValidationController().validate(context) && getAnalyticsTypeValidationController().validate(context))
			{
				internalResponse = getAnalyticsBCL().fetchAnalyticsAlarmsByStatusId(analyticsRequest);
				response.setLights(internalResponse.getResultsList());
				setLightProperties(response.getLights());
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
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#generateFileCSV(com.sensus.mlc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InquiryAnalyticsResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InquiryAnalyticsResponse response = new InquiryAnalyticsResponse();

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(InquiryAnalyticsRequest.class.getSimpleName(), inquiryAnalyticsRequest);
			context.putObjectToBeValidated(AnalyticsTypeEnum.class.getSimpleName(), inquiryAnalyticsRequest.getAnalyticsTypeEnum());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(), inquiryAnalyticsRequest.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(), inquiryAnalyticsRequest.getEndDate());

			if (getLightingControlRequestValidationController().validate(context)
					&& getAnalyticsTypeValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context))
			{
				// generate CSV File
				response = getAnalyticsBCL().generateFileCSV(inquiryAnalyticsRequest);
				if (!response.isOperationSuccess() && ValidationUtil.isNullOrEmpty(response.getMessageInfoList()))
				{
					response.addOperationFailedMessage(DEFAULT_ANALYTICS_BCL_EXCEPTION_MSG);
				}
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_ANALYTICS_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#insertCSVProcess(com.sensus.mlc.base.model.request.LightSelectionRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return getProcessBCF().insertCSVProcess(lightSelectionRequest);
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
}
