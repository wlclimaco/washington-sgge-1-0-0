package com.prosperitasglobal.sendsolv.model.response;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.qat.framework.model.Message;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.Response;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MoneyTransferProcessingResponse.
 */
public class MoneyTransferProcessingResponse extends Response
{
	/**
	 * Default Constructor.
	 */
	public MoneyTransferProcessingResponse()
	{
		super();
	}

	/**
	 * Multiple parameter constructor.
	 *
	 * @param response The response.
	 */
	public MoneyTransferProcessingResponse(Response response)
	{
		setOperationSuccess(response.isOperationSuccess());
		setMessageList(response.getMessageList());
	}

	/**
	 * Multiple parameter constructor.
	 *
	 * @param response The response.
	 */
	public MoneyTransferProcessingResponse(InternalResponse response)
	{
		setOperationSuccess(response.getStatus());
		addMessages(response.getMessageInfoList());
	}

	/**
	 * Gets the message iterator.
	 *
	 * @return the messages
	 */
	@Override
	@JsonIgnore
	public Iterator<Message> getMessageIterator()
	{
		return super.getMessageIterator();
	}

	/**
	 * Returns a collection of the internal MessageInfo instance related to each Message object within this
	 * PgsiResponse.
	 *
	 * @return A collection of MessageInfo instances.
	 */
	@Override
	@JsonIgnore
	public List<MessageInfo> getMessageInfoList()
	{
		return super.getMessageInfoList();
	}

	/**
	 * Sets the operation success.
	 *
	 * @param operationSuccess the new operation success
	 */
	@Override
	@JsonProperty("operationSuccess")
	public void setOperationSuccess(boolean operationSuccess)
	{
		super.setOperationSuccess(operationSuccess);
	}

	/**
	 * Convenience method for setting the operation success property.<br/>
	 *
	 * @param iStatus The status from an {@link InternalResponse}
	 * @return The boolean value for the operation success property.
	 */
	@Override
	@JsonIgnore
	public boolean setOperationSuccess(InternalResponse.Status iStatus)
	{
		return super.setOperationSuccess(iStatus);
	}

	/**
	 * Convert a String representation of a {@link MoneyTransferProcessingResponse} to its object.
	 * <p>
	 * Currently, the String representation will be in a JSON format.
	 *
	 * @param response The String representation of the {@link MoneyTransferProcessingResponse} object.
	 * @return The object representation.
	 */
	public static MoneyTransferProcessingResponse fromJsonString(String response)
	{
		if (ValidationUtil.isNull(response))
		{
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		MoneyTransferProcessingResponse objectResponse = null;

		try
		{
			objectResponse = mapper.readValue(response, MoneyTransferProcessingResponse.class);
		}

		catch (JsonGenerationException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return objectResponse;
	}

	/**
	 * Convert a {@link MoneyTransferProcessingResponse} to its String representation.
	 * <p>
	 * Currently, the String representation will be in a JSON format.
	 *
	 * @return The String representation.
	 */
	public String toJsonString()
	{
		ObjectMapper mapper = new ObjectMapper();
		String objectAsString = null;

		try
		{
			objectAsString = mapper.writeValueAsString(this);
		}

		catch (JsonGenerationException e)
		{
			e.printStackTrace();
		}

		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}

		catch (IOException e)
		{
			e.printStackTrace();
		}

		return objectAsString;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MoneyTransferProcessingResponse [getMessageIterator()=" + getMessageIterator()
				+ ", getMessageInfoList()=" + getMessageInfoList() + ", toJsonString()=" + toJsonString()
				+ ", getMessageList()=" + getMessageList() + ", isOperationSuccess()=" + isOperationSuccess() + "]";
	}
}
