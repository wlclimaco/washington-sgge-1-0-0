package com.sensus.lc.server.client;

import org.apache.commons.lang3.math.NumberUtils;

import com.sensus.mlc.mlcserver.type.AbortTransactionRequest;
import com.sensus.mlc.mlcserver.type.AbortTransactionResponse;
import com.sensus.mlc.mlcserver.type.InitiateApplyDimmingConfigurationRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplyDimmingConfigurationResponse;
import com.sensus.mlc.mlcserver.type.InitiateApplyLightLevelRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplyLightLevelResponse;
import com.sensus.mlc.mlcserver.type.InitiateApplyScheduleRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplyScheduleResponse;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleRequest;
import com.sensus.mlc.mlcserver.type.InitiateClearScheduleResponse;
import com.sensus.mlc.mlcserver.type.MessageInfo;
import com.sensus.mlc.mlcserver.type.MessageType;
import com.sensus.mlc.mlcserver.type.PingURLRequest;
import com.sensus.mlc.mlcserver.type.PingURLResponse;
import com.sensus.mlc.mlcserver.type.Status;

public class MlcServerClientMock extends MlcServerClient
{

	public MlcServerClientMock()
	{
		super();
	}

	@Override
	public AbortTransactionResponse setAborted(AbortTransactionRequest abortRequest)
	{
		AbortTransactionResponse response = new AbortTransactionResponse();

		if (abortRequest.getTransactionID().equals("2"))
		{
			MessageInfo message = new MessageInfo();
			message.setMessageType(MessageType.OTHER);
			response.getMessage().add(message);
			message = new MessageInfo();
			message.setMessageType(MessageType.TRANSACTION_NOT_FOUND);
			response.getMessage().add(message);
			response.setStatus(Status.FAIL);
		}

		return response;
	}

	@Override
	public PingURLResponse pingURL(PingURLRequest pingRequest)
	{
		return new PingURLResponse();
	}

	@Override
	public InitiateApplyScheduleResponse setApplySchedule(InitiateApplyScheduleRequest applyScheduleRequest)
	{
		// To Tenant "ACME" we will simulate a RNI OFF with the STATUS FAIL
		if (applyScheduleRequest.getCustomerID().equals("PECO"))
		{
			MessageInfo message = new MessageInfo();
			message.setMessageType(MessageType.DEVICE_DOES_NOT_EXIST);
			message.setObjectId(String.valueOf(NumberUtils.INTEGER_ZERO));

			InitiateApplyScheduleResponse response = new InitiateApplyScheduleResponse();
			response.setStatus(Status.FAIL);
			response.getMessage().add(message);
			return response;
		}

		// With the tenant "TEST" we are simulating a return response NULL
		if (applyScheduleRequest.getCustomerID().equals("TEST"))
		{
			return null;
		}

		return new InitiateApplyScheduleResponse();
	}

	@Override
	public InitiateApplyDimmingConfigurationResponse applyDimmingConfiguration(
			InitiateApplyDimmingConfigurationRequest request)
	{
		return new InitiateApplyDimmingConfigurationResponse();
	}

	@Override
	public InitiateClearScheduleResponse clearSchedule(InitiateClearScheduleRequest request)
	{
		return new InitiateClearScheduleResponse();
	}

	@Override
	public InitiateApplyLightLevelResponse setLightIntensity(InitiateApplyLightLevelRequest lightIntensity)
	{
		return new InitiateApplyLightLevelResponse();
	}

}
