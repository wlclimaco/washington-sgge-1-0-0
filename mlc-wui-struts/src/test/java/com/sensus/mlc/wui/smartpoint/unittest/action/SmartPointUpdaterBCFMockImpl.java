package com.sensus.mlc.wui.smartpoint.unittest.action;

import java.util.ArrayList;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.bcf.ISmartPointUpdaterBCF;
import com.sensus.mlc.smartpoint.model.CustomSearch;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class SmartPointUpdaterBCFMockImpl extends BaseMockImpl implements ISmartPointUpdaterBCF
{

	@Override
	public LightResponse upsertLightProperty(LightStatusNotificationRequest lightStatusNotificationRequest)
	{
		return null;
	}

	@Override
	public LightResponse updateLightProtected(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();
		if (ValidationUtil.isNullOrEmpty(lightRequest.getSelectionPaginationIds())
				|| ValidationUtil.isNull(lightRequest.getProtect()))
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage("error");
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public CustomSearchResponse insertCustomSearch(CustomSearchRequest request)
	{
		CustomSearchResponse response = new CustomSearchResponse();

		// Invalid inputs cover Failure scenario
		if (ValidationUtil.isNull(request.getCustomSearch())
				|| ValidationUtil.isNull(request.getCustomSearch().getName()))
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setCustomSearches(new ArrayList<CustomSearch>());
			response.getCustomSearches().add(request.getCustomSearch());
			response.getCustomSearches().get(0).setId(1);
			response.getCustomSearches().get(0).setModelAction(PersistanceActionEnum.INSERT);

			return response;
		}

		throw new RuntimeException("Kaboom");
	}

	@Override
	public CustomSearchResponse deleteCustomSearch(CustomSearchRequest request)
	{
		CustomSearchResponse response = new CustomSearchResponse();
		// Invalid inputs cover Failure scenario
		if (request.getCustomSearch().getId() == null)
		{
			response.setOperationSuccess(false);
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.FieldValidation));
			return response;
		}
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightResponse updateLightLatLng(LightRequest lightRequest)
	{
		return null;
	}

	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return null;
	}

	@Override
	public LightResponse resetMinMaxValue(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public ColumnFilterResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse columnFilterResponse = new ColumnFilterResponse();

		// Invalid inputs cover Failure scenario
		if ((ValidationUtil.isNullOrEmpty(columnFilterRequest.getFilters())
				&& ValidationUtil.isNullOrEmpty(columnFilterRequest.getListColumn()))
				|| (columnFilterRequest.getListTypeEnum().getValue() != 1))
		{
			columnFilterResponse.setOperationSuccess(false);
			columnFilterResponse.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error,
					MessageLevel.FieldValidation));

			return columnFilterResponse;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			columnFilterResponse.setOperationSuccess(true);
			columnFilterResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return columnFilterResponse;
		}

		throw new RuntimeException("Kaboom");
	}

	@Override
	public LightResponse updateLightStatus(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

}
