package com.sensus.dm.elec.action.listener;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.event.AbstractEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;

import com.sensus.api.JmsListenerContainerManager;
import com.sensus.api.MessageReceiver;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * The listener interface for receiving abstract events.
 * The class that is interested in processing a abstract
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addAbstractListenermethod. When
 * the abstract event occurs, that object's appropriate
 * method is invoked.
 * 
 * @param <T> the response type
 * @see AbstractEvent
 * @author QAT Brazil
 */
abstract class AbstractListener<T> implements ApplicationContextAware, MessageReceiver<T>
{
	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant STR_LISTENER_WITH_DESTINATION. */
	private static final String STR_LISTENER_WITH_DESTINATION = "Listener with destination: ";

	/** The Constant STR_CONNECT. */
	protected static final String STR_CONNECT = "CONNECT";

	/** The Constant STR_DISCONNECT. */
	protected static final String STR_DISCONNECT = "DISCONNECT";

	/** The Constant STR_REJECTED. */
	protected static final String STR_REJECTED = "rejected";

	/** The Constant STR_SUCCESS. */
	protected static final String STR_SUCCESS = "SUCCESS";

	/** The Constant STR_BEGINNING. */
	protected static final String STR_BEGINNING = "### BEGINNING: ";

	/** The Constant STR_FINISHED. */
	protected static final String STR_FINISHED = "### FINISHED: ";

	/** The default user context Id. */
	protected static final String USER_CONTEXT_ID = "DM";

	/** The Constant STR_LOG. */
	protected static final String STR_LOG = "### ";

	/** The Constant ERROR_EXECUTING. */
	protected static final String ERROR_EXECUTING = "Error executing ";

	/** The Constant ERROR. */
	protected static final String ERROR = "Error ";

	/** The Constant MESSAGE. */
	protected static final String MESSAGE = "Message";

	// -------------------------------------------------------------------------
	// Fields.

	/** The Constant LOG. */
	public static final Log LOG = LogFactory.getLog(AbstractListener.class);

	/** The jms container. */
	private String jmsContainer;

	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The application context. */
	private ApplicationContext applicationContext;

	/** The listener container. */
	private DefaultMessageListenerContainer defaultMessageListenerContainer;

	/** The jms listener container manager. */
	private JmsListenerContainerManager jmsListenerContainerManager;

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/**
	 * Gets the application context.
	 * 
	 * @return the application context
	 */
	public ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	/**
	 * Gets the default message listener container.
	 * 
	 * @return the default message listener container
	 */
	public DefaultMessageListenerContainer getDefaultMessageListenerContainer()
	{

		if (defaultMessageListenerContainer == null)
		{
			defaultMessageListenerContainer =
					(DefaultMessageListenerContainer)getApplicationContext().getBean(getJmsContainer());
		}

		return defaultMessageListenerContainer;
	}

	/**
	 * Gets the jms listener container manager.
	 * 
	 * @return the jms listener container manager
	 */
	public JmsListenerContainerManager getJmsListenerContainerManager()
	{
		if (jmsListenerContainerManager == null)
		{
			jmsListenerContainerManager =
					(JmsListenerContainerManager)getApplicationContext().getBean(getJmsContainer());
		}
		return jmsListenerContainerManager;
	}

	/**
	 * Gets the electric meter bcl.
	 * 
	 * @return the electric meter bcl
	 */
	public IElectricMeterBCL getElectricMeterBCL()
	{
		return electricMeterBCL;
	}

	/**
	 * Sets the electric meter bcl.
	 * 
	 * @param electricMeterBCL the new electric meter bcl
	 */
	public void setElectricMeterBCL(IElectricMeterBCL electricMeterBCL)
	{
		this.electricMeterBCL = electricMeterBCL;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Gets the jms container.
	 * 
	 * @return the jms container
	 */
	public String getJmsContainer()
	{
		return jmsContainer;
	}

	/**
	 * Sets the jms container.
	 * 
	 * @param jmsContainer the new jms container
	 */
	public void setJmsContainer(String jmsContainer)
	{
		this.jmsContainer = jmsContainer;
	}

	// -------------------------------------------------------------------------
	// Abstract methods:

	/**
	 * On response.
	 * 
	 * @param response the response
	 */
	public abstract void onResponse(T response);

	// -------------------------------------------------------------------------
	// ApplicationContextAware overrides:

	/*
	 * (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.
	 * ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	// -------------------------------------------------------------------------
	// MessageReceiver overrides:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.api.MessageReceiver#onMessage(java.lang.Object)
	 */
	@Override
	public void onMessage(T response)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(response));
		}

		// execute the response (each child listener)
		onResponse(response);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(response));
		}
	}

	// -------------------------------------------------------------------------
	// JMX interface:

	/**
	 * Checks if is running.
	 * 
	 * @return true, if is running
	 */
	@ManagedAttribute(description = "Gets whether this listener is running of not.")
	public boolean isRunning()
	{
		return getDefaultMessageListenerContainer().isRunning();
	}

	/**
	 * Gets the max concurrent consumers.
	 * 
	 * @return the max concurrent consumers
	 */
	@ManagedAttribute(description = "Gets the maximum number of concurrent consumers on this listener.")
	public int getMaxConcurrentConsumers()
	{
		return getDefaultMessageListenerContainer().getMaxConcurrentConsumers();
	}

	/**
	 * Start listener.
	 */
	@ManagedOperation(description = "Starts this listener.")
	public void startListener()
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(
					new StringBuilder(STR_LOG).append("Starting listener with destination: ")
							.append(getDestinationName()));
		}

		// Starting...
		getDefaultMessageListenerContainer().start();

		if (LOG.isInfoEnabled())
		{
			LOG.info(
					new StringBuilder(STR_LOG).append(STR_LISTENER_WITH_DESTINATION)
							.append(getDestinationName())
							.append(" successfully started."));
		}
	}

	/**
	 * Stop listener.
	 */
	@ManagedOperation(description = "Stops this listener.")
	public void stopListener()
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(
					new StringBuilder(STR_LOG).append("Stopping listener with destination: ")
							.append(getDestinationName()));
		}

		getDefaultMessageListenerContainer().stop();

		if (LOG.isInfoEnabled())
		{
			LOG.info(
					new StringBuilder(STR_LOG).append(STR_LISTENER_WITH_DESTINATION)
							.append(getDestinationName())
							.append(" successfully stopped."));
		}
	}

	/**
	 * Gets the destination name.
	 * 
	 * @return the destination name
	 */
	public String getDestinationName()
	{
		return getDefaultMessageListenerContainer().getDestinationName();
	}

	/**
	 * Update max concurrent consumers.
	 * 
	 * @param maxConcurrentConsumers the max concurrent consumers
	 */
	@ManagedOperation(description = "Gets the status of this listener.")
	@ManagedOperationParameters({
			@ManagedOperationParameter(name = "maxConcurrentConsumers", description = "The number of maximum concurrent consumers.")})
	public void updateMaxConcurrentConsumers(int maxConcurrentConsumers)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(
					new StringBuilder(STR_LOG).append("Setting the number of maximum concurrent consumers to \"")
							.append(maxConcurrentConsumers)
							.append("\" for queue \"")
							.append(getDestinationName()).append("\"")
							.toString());
		}

		getDefaultMessageListenerContainer().setMaxConcurrentConsumers(maxConcurrentConsumers);
	}

	// -------------------------------------------------------------------------
	// Protected interface:

	/**
	 * Verify process status.
	 * 
	 * @param status the status
	 * @param messages the messages
	 * @param processRequest the process request
	 * @param response the response
	 * @param subStatus the sub status
	 */
	protected void verifyProcessStatus(
			String status, List<String> messages, ProcessRequest processRequest, T response, String subStatus)
	{
		if (status.equals(STR_SUCCESS))
		{
			setProcessStatusSuccess(status, messages, processRequest, response);
		}
		else
		{
			setProcessStatusError(status, messages, processRequest, response, subStatus);
		}
	}

	/**
	 * Sets the process status success.
	 * 
	 * @param status the status
	 * @param messages the messages
	 * @param processRequest the process request
	 * @param response the response
	 */
	protected void setProcessStatusSuccess(
			String status, List<String> messages, ProcessRequest processRequest, T response)
	{
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.COMPLETED);
	}

	/**
	 * Sets the process status error.
	 * 
	 * @param status the status
	 * @param messages the messages
	 * @param processRequest the process request
	 * @param response the response
	 * @param subStatus the sub status
	 */
	protected void setProcessStatusError(
			String status, List<String> messages, ProcessRequest processRequest, T response, String subStatus)
	{
		processRequest.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);

		// Setting "ProcessItemStatusEnum.FAILED" on children.

		DMProcess process = processRequest.getFirstProcess();

		if (!ValidationUtil.isNull(process))
		{
			List<ProcessItem> processItems = process.getProcessItems();

			if (!ValidationUtil.isNullOrEmpty(processItems))
			{
				for (ProcessItem item : processItems)
				{
					item.setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
					if (!ValidationUtil.isNullOrEmpty(messages))
					{
						item.setMessage(DMUtil.generateMessageRni(messages.toString()));
					}
					else
					{
						item.setMessage(DMUtil.generateMessageRni(subStatus));
					}
				}
			}
		}
		// Logging.

		if (!ValidationUtil.isNullOrEmpty(messages) && LOG.isInfoEnabled())
		{
			for (String message : messages)
			{
				LOG.info(new StringBuilder(STR_LOG).append("Response Error: ").append(message));
			}
		}
	}

	/**
	 * Summarize response.
	 * 
	 * @param processRequest the process request
	 */
	protected void summarizeResponse(ProcessRequest processRequest)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append("Summarizing Response..."));
		}

		InternalResponse summarizeResponse = getProcessBCL().summarizeProcess(processRequest);

		if (summarizeResponse.isInError())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(
						new StringBuilder(STR_LOG).append("Error summarizing response: ")
								.append(summarizeResponse.getMessageInfoList()));
			}
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append("Response Summarized."));
		}
	}

	// -------------------------------------------------------------------------
	// Protected static interface:

	/**
	 * Creates the han device.
	 * 
	 * @param deviceId the device id
	 * @param customerId the customer id
	 * @return the han device
	 */
	protected static HanDevice createHanDevice(String deviceId, String customerId)
	{
		HanDevice hanDevice = new HanDevice();
		hanDevice.setDeviceId(deviceId);
		hanDevice.setRadio(new Radio(customerId));
		return hanDevice;
	}

	/**
	 * Creates the process request.
	 * 
	 * @param processRequest the process request
	 * @return true, if successful
	 */
	protected void createUserContext(ProcessRequest processRequest)
	{
		// consider the user that initiated the action if there is no userContext when summarizing
		// Fetch process item by process endpoint id
		ProcessRequest processUserRequest =
				new ProcessRequest(new DMProcess(new ProcessItem(processRequest.getFirstProcess()
						.getFirstProcessItem().getId())));

		InternalResultsResponse<ProcessItem> processItemResponse =
				getProcessBCL().fetchAllProcessItems(processUserRequest);

		if (!processItemResponse.isInError() && !ValidationUtil.isNull(processItemResponse.getFirstResult())
				&& !ValidationUtil.isNullOrEmpty(processItemResponse.getFirstResult().getModifyUser()))
		{
			processRequest.setUserContext(new UserContext(processItemResponse.getFirstResult().getModifyUser()));
		}
		else
		{
			processRequest.setUserContext(new UserContext(USER_CONTEXT_ID));
		}
	}

	/**
	 * Verify process items.
	 * 
	 * @param processRequest the process request
	 * @param device the Device
	 * @param listener the listener
	 * @return true, if successful
	 */
	protected boolean verifyProcessItems(ProcessRequest processRequest, Device device, String listener)
	{

		if (ValidationUtil.isNullOrEmpty(processRequest.getProcessList()))
		{
			return false;
		}

		DMProcess process = new DMProcess(processRequest.getFirstProcess().getAction(),
				new ProcessItem(device));

		if (!ValidationUtil.isNullOrEmpty(processRequest.getFirstProcess().getProcessItems()))
		{
			process.getFirstProcessItem().setId(processRequest.getFirstProcess().getFirstProcessItem().getId());
		}

		InternalResultsResponse<ProcessItem> getProcessItemsResp =
				getProcessBCL().fetchProcessItemsByDevice(
						new ProcessRequest(process));

		if (getProcessItemsResp.isInError() || !getProcessItemsResp.hasResults())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(
						new StringBuilder(STR_LOG).append(listener)
								.append(" Not Found for Response Device with Device ID: ")
								.append(device.getDeviceId()).append(" and Customer ID: ")
								.append(device.getRadio().getCustomerId()));
			}

			return false;
		}

		for (ProcessItem processItem : getProcessItemsResp.getResultsList())
		{
			processItem.setDevice(device);
			processRequest.getFirstProcess().addProcessItem(processItem);
		}

		return true;
	}

	/**
	 * Check device status.
	 * 
	 * @param deviceRequest the device request
	 * @param strListener the str listener
	 */
	protected void checkDeviceStatus(DeviceRequest deviceRequest, String strListener)
	{
		InternalResponse deviceResponse = getElectricMeterBCL().updateDeviceStatus(deviceRequest);

		if (deviceResponse.isInError())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(ERROR + "updateDeviceStatus " + MESSAGE)
						.append(deviceResponse.getMessageInfoList()));
			}
		}
	}

	/**
	 * Check process item.
	 * 
	 * @param processRequest the process request
	 * @param strListener the str listener
	 * @return true, if successful
	 */
	protected boolean checkProcessItem(ProcessRequest processRequest, String strListener)
	{
		InternalResponse updateResponse = getProcessBCL().updateProcessItems(processRequest);

		if (updateResponse.isInError())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(strListener).append(ERROR + "updateItem  " + MESSAGE + ":")
						.append(
								updateResponse.getMessageInfoList()).toString());
			}
			return false;
		}
		return true;
	}

	/**
	 * Check process by Rni event id.
	 * 
	 * @param processRequest the process request
	 * @param strListener the str listener
	 * @return true, if successful
	 */
	protected boolean checkProcessByRniEventId(ProcessRequest processRequest, String strListener)
	{
		InternalResultsResponse<DMProcess> internalResponse =
				getProcessBCL().fetchProcessByRniEventId(processRequest);

		if (internalResponse.isInError())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(strListener)
						.append(ERROR_EXECUTING + "checkProcessByRniEventId - ")
						.append(internalResponse.getMessageInfoList().toString()));
			}

			return false;
		}

		if (!internalResponse.hasResults())
		{
			return false;
		}

		processRequest.getFirstProcess().setId(internalResponse.getFirstResult().getId());
		processRequest.getFirstProcess().setProcessItems(internalResponse.getFirstResult().getProcessItems());
		processRequest.getFirstProcess().setProperties(internalResponse.getFirstResult().getProperties());
		processRequest.getFirstProcess().setAction(internalResponse.getFirstResult().getAction());
		processRequest.setUserContext(new UserContext(internalResponse.getFirstResult().getCreateUser()));

		return true;
	}

	/**
	 * Check process item by rni event id.
	 * 
	 * @param processRequest the process request
	 * @param strListener the str listener
	 * @return true, if successful
	 */
	protected boolean checkProcessItemByRniEventId(ProcessRequest processRequest, String strListener)
	{
		InternalResultsResponse<DMProcess> internalResponse =
				getProcessBCL().fetchProcessItemsByProcessId(processRequest);

		if (internalResponse.isInError())
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(strListener)
						.append(ERROR_EXECUTING + "checkProcessItemByRniEventId - ")
						.append(internalResponse.getMessageInfoList().toString()));
			}

			return false;
		}

		if (!internalResponse.hasResults()
				|| ValidationUtil.isNull(internalResponse.getFirstResult().getFirstProcessItem()))
		{
			return false;
		}

		processRequest.getFirstProcess().setId(internalResponse.getFirstResult().getId());
		processRequest.getFirstProcess().setProcessItems(internalResponse.getFirstResult().getProcessItems());

		return true;
	}

	/**
	 * Check rni event id.
	 * 
	 * @param process the process
	 * @param requestId the request id
	 * @return true, if successful
	 */
	protected boolean checkRniEventId(DMProcess process, String requestId)
	{
		if (!ValidationUtil.isNullOrEmpty(requestId))
		{
			try
			{
				process.setRniEventId(Integer.parseInt(requestId));
				return true;
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return false;
	}

	/**
	 * Creates the process item.
	 * 
	 * @param process the process
	 * @param devices the devices
	 */
	protected void createProcessItem(DMProcess process, List<Device> devices)
	{
		process.setProcessItems(new ArrayList<ProcessItem>());
		for (Device device : devices)
		{
			process.addProcessItem(new ProcessItem(device, ProcessItemStatusEnum.RUNNING));
		}
	}

	// -------------------------------------------------------------------------
}
