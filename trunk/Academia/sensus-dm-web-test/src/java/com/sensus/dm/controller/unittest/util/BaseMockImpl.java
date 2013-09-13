package com.sensus.dm.controller.unittest.util;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.Response;

/**
 * Base class for mock BCF implementation. Provides the mode field and common constants.
 */
public abstract class BaseMockImpl
{
	/**
	 * The mode field. Defaults to success mode.
	 */
	private ModeEnum mode;

	/**
	 * Default message code for info message.
	 */
	public static final String MESSAGE_INFO = "info";

	/**
	 * Set the response mode.
	 * 
	 * @param modeIn the new mode
	 */
	public void setMode(ModeEnum modeIn)
	{
		mode = modeIn;
	}

	/**
	 * Get the response mode.
	 * 
	 * @return the response mode
	 */
	public ModeEnum getMode()
	{
		return mode;
	}

	/**
	 * Test other default modes.
	 * 
	 * @param response the response
	 * @return the response
	 */
	protected Response testOtherDefaultModes(Response response)
	{
		switch (getMode())
		{
			case MODE_EMPTY:

				response.getMessageInfoList().add(
						new MessageInfo(TestMessageEnum.MESSAGE_INFO.getValue(), MessageSeverity.Info,
								MessageLevel.Object));
				response.setOperationSuccess(Status.NoRowsFoundError);

				break;
			case MODE_FAILURE:

				response.addMessage(TestMessageEnum.MESSAGE_ERROR.getValue(), MessageSeverity.Error, MessageLevel.Field);
				response.setOperationSuccess(Status.ExceptionError);

				break;
			case MODE_EXCEPTION:

				throw new RuntimeException();

			default:
				break;
		}

		return response;
	}

}