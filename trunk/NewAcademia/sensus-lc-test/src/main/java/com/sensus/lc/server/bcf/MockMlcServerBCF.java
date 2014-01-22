package com.sensus.lc.server.bcf;

import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.server.model.request.MlcServerRequest;

public class MockMlcServerBCF extends AbstractMockBase implements IMlcServerBCF
{

	/** The Constant BAD. */
	private static final String BAD = "BAD";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processAlarmWarningNotification(com.sensus.lc.server.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processAlarmWarningNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getAlarmWarningNotification())
				&& !ValidationUtil.isNull(request.getAlarmWarningNotification().getTransactionID())
				&& request.getAlarmWarningNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processReadLightStatusNotification(com.sensus.lc.server.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processReadLightStatusNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getReadLightStatusNotification())
				&& !ValidationUtil.isNull(request.getReadLightStatusNotification().getTransactionID())
				&& request.getReadLightStatusNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processLightBindingNotification(com.sensus.lc.server.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processLightBindingNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getLightBindingNotification())
				&& !ValidationUtil.isNull(request.getLightBindingNotification().getTransactionID())
				&& request.getLightBindingNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processLightSetupNotification(com.sensus.lc.server.model.request
	 * .MlcServerRequest)
	 */
	@Override
	public Response processLightSetupNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getLightSetupNotification())
				&& !ValidationUtil.isNull(request.getLightSetupNotification().getTransactionID())
				&& request.getLightSetupNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processLightStatusNotification(com.sensus.lc.server.model.
	 * request.MlcServerRequest)
	 */
	@Override
	public Response processLightStatusNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getLightStatusNotification())
				&& !ValidationUtil.isNull(request.getLightStatusNotification().getTransactionID())
				&& request.getLightStatusNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processApplyLightLevelNotification(com.sensus.lc.server.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processApplyLightLevelNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getApplyLightLevelNotification())
				&& !ValidationUtil.isNull(request.getApplyLightLevelNotification().getTransactionID())
				&& request.getApplyLightLevelNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processClearScheduleNotification(com.sensus.lc.server.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processClearScheduleNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getClearScheduleNotification())
				&& !ValidationUtil.isNull(request.getClearScheduleNotification().getTransactionID())
				&& request.getClearScheduleNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processApplyScheduleNotification(com.sensus.lc.server.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processApplyScheduleNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getApplyScheduleNotification())
				&& !ValidationUtil.isNull(request.getApplyScheduleNotification().getTransactionID())
				&& request.getApplyScheduleNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processClearAlarmsNotification(com.sensus.lc.server.model.
	 * request.MlcServerRequest)
	 */
	@Override
	public Response processClearAlarmsNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getClearAlarmsNotification())
				&& !ValidationUtil.isNull(request.getClearAlarmsNotification().getTransactionID())
				&& request.getClearAlarmsNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processApplySmartpointPropertiesNotification(com.sensus.lc.server
	 * .model.request.MlcServerRequest)
	 */
	@Override
	public Response processApplySmartpointPropertiesNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getApplySmartpointPropertiesNotification())
				&& !ValidationUtil.isNull(request.getApplySmartpointPropertiesNotification().getTransactionID())
				&& request.getApplySmartpointPropertiesNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processChannelSetupAuditNotification(com.sensus.lc.server.
	 * model.request.MlcServerRequest)
	 */
	@Override
	public Response processChannelSetupAuditNotification(MlcServerRequest request)
	{
		Response response = new Response();

		if (!ValidationUtil.isNull(request.getChannelSetupAuditNotification())
				&& !ValidationUtil.isNull(request.getChannelSetupAuditNotification().getTransactionID())
				&& request.getChannelSetupAuditNotification().getTransactionID().equals(BAD))
		{
			response.setOperationSuccess(false);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processApplyDimmingConfiguration(com.sensus.lc.server.model
	 * .request.MlcServerRequest)
	 */
	@Override
	public Response processApplyDimmingConfiguration(MlcServerRequest request)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.server.bcf.IserverBCF#processUpdateLightStatusNotification(com.sensus.lc.server.
	 * model.request.MlcServerRequest)
	 */
	@Override
	public Response processUpdateLightStatusNotification(MlcServerRequest request)
	{
		return null;
	}

}
