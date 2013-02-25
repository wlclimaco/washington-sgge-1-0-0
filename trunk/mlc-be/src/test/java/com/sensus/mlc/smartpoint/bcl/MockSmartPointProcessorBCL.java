package com.sensus.mlc.smartpoint.bcl;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.smartpoint.model.StatusMessage;
import com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest;
import com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest;

/**
 * The Class MockSmartPointProcessorBCL.
 */
public class MockSmartPointProcessorBCL extends AbstractMockBase implements ISmartPointProcessorBCL
{

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateUpdateLightIntensity(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateUpdateLightIntensity(LightRequest lightRequest)
	{
		return testSituationsProcessResultsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateUpsertLightProperty(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateUpsertLightProperty(LightRequest lightRequest)
	{
		return testSituationsProcessResultsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateSetupDimmingConfiguration(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateSetupDimmingConfiguration(LightRequest request)
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();

		if (request.getFirstLight().getId() == 2)
		{
			response.getMessageInfoList().add(new MessageInfo());
			response.setStatus(Status.ExceptionError);

		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateUpdateLightStatus(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateUpdateLightStatus(LightRequest lightRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();

		if (getAreaEnum() != LCAreaEnum.SMARTPOINT)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);

			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException("JUnit1");
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateDeleteAlert(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateDeleteAlert(LightRequest lightRequest)
	{
		return testSituationsProcessResultsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processApplySmartpointPropertiesNotification(com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest)
	 */
	@Override
	public InternalResponse processApplySmartpointPropertiesNotification(ProcessLightsRequest request)
	{
		return testSituationsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processChannelSetupAuditNotification(com.sensus.mlc.smartpoint.model.request.ChannelSetupAuditRequest)
	 */
	@Override
	public InternalResponse processChannelSetupAuditNotification(ChannelSetupAuditRequest request)
	{
		return testSituationsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processAlarmWarningNotification(com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> processAlarmWarningNotification(AlarmNotificationRequest request)
	{
		return testSituationsStatusMessageResultsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processLightStatusNotification(com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest)
	 */
	@Override
	public InternalResponse processLightStatusNotification(LightStatusNotificationRequest request)
	{
		InternalResponse response = getInternalResponseDefault();
		response = testSituationsResponse();

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processLightBindingNotification(com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest)
	 */
	@Override
	public InternalResponse processLightBindingNotification(LightStatusNotificationRequest request)
	{
		InternalResponse response = getInternalResponseDefault();
		response = testSituationsResponse();

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#initiateGetLightStatus(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Process> initiateGetLightStatus(LightRequest lightRequest)
	{
		InternalResultsResponse<Process> response = getResultResponseProcess();

		if (getAreaEnum() != LCAreaEnum.SMARTPOINT)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response = new InternalResultsResponse<Process>();
			response.setStatus(Status.ExceptionError);
			response.addFieldErrorMessage(ERROR_CODE);

			return response;
		}
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException("JUnit");
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processForcedLightStatusNotification(com.sensus.mlc.smartpoint.model.request.ForcedLightStatusNotificationRequest)
	 */
	@Override
	public InternalResponse processForcedLightStatusNotification(ForcedLightStatusNotificationRequest request)
	{
		InternalResponse response = getInternalResponseDefault();
		response = testSituationsResponse();

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processLightSetupNotification(com.sensus.mlc.smartpoint.model.request.LightSetupNotificationRequest)
	 */
	@Override
	public InternalResponse processLightSetupNotification(LightSetupNotificationRequest request)
	{
		return testSituationsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processSetLightIntensityNotification(com.sensus.mlc.smartpoint.model.request.ProcessLightsRequest)
	 */
	@Override
	public InternalResponse processSetLightIntensityNotification(ProcessLightsRequest request)
	{
		return testSituationsResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.smartpoint.bcl.ISmartPointProcessorBCL#processClearAlarmNotification(com.sensus.mlc.smartpoint.model.request.AlarmNotificationRequest)
	 */
	@Override
	public InternalResultsResponse<StatusMessage> processClearAlarmNotification(AlarmNotificationRequest request)
	{
		return testSituationsStatusMessageResultsResponse();
	}

	/**
	 * Gets the response default.
	 *
	 * @return the response default
	 */
	private InternalResponse getResponseDefault()
	{
		return new InternalResponse();
	}

	/**
	 * Test situations response.
	 *
	 * @return the internal response
	 */
	private InternalResponse testSituationsResponse()
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResponse;
	}

	/**
	 * Gets the process response default.
	 *
	 * @return the process response default
	 */
	private InternalResultsResponse<Process> getProcessResponseDefault()
	{
		InternalResultsResponse<Process> response = new InternalResultsResponse<Process>();
		response.addResult(TestBaseUtil.createProcess(TestBaseUtil.createLight(), null));
		return response;
	}

	/**
	 * Gets the status message response default.
	 *
	 * @return the status message response default
	 */
	private InternalResultsResponse<StatusMessage> getStatusMessageResponseDefault()
	{
		InternalResultsResponse<StatusMessage> response = new InternalResultsResponse<StatusMessage>();
		response.addResult(TestBaseUtil.createStatusMessage());
		return response;
	}

	/**
	 * Test situations process results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Process> testSituationsProcessResultsResponse()
	{
		InternalResultsResponse<Process> internalResultsResponse = new InternalResultsResponse<Process>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getProcessResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	/**
	 * Test situations status message results response.
	 *
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<StatusMessage> testSituationsStatusMessageResultsResponse()
	{
		InternalResultsResponse<StatusMessage> internalResultsResponse = new InternalResultsResponse<StatusMessage>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getStatusMessageResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}
}
