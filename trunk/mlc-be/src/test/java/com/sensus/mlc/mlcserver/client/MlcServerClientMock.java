package com.sensus.mlc.mlcserver.client;

import com.sensus.mlc.mlcserver.type.AbortTransactionRequest;
import com.sensus.mlc.mlcserver.type.AbortTransactionResponse;
import com.sensus.mlc.mlcserver.type.InitiateApplyScheduleRequest;
import com.sensus.mlc.mlcserver.type.InitiateApplyScheduleResponse;
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
		return new InitiateApplyScheduleResponse();

	}
}
