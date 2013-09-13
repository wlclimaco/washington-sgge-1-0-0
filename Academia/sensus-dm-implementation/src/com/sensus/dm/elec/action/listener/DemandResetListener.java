package com.sensus.dm.elec.action.listener;

import java.util.ArrayList;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.applicationeventnotification.messages.ApplicationEventNotification;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;

/**
 * The listener interface for receiving demandReset events.
 * The class that is interested in processing a demandReset
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's addDemandResetListener method. When
 * the demandReset event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see DemandResetEvent
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=ReadGapReceiver", description = "ReadGapReceiver JMX Bean")
public class DemandResetListener extends AbstractListener<ApplicationEventNotification>
{

	/**
	 * Instantiates a new connect han device listener.
	 */
	public DemandResetListener()
	{
		setJmsContainer("demandReset.notif.jmsContainer");
	}

	/**
	 * Gets the max concurrent consumers.
	 * 
	 * @return the max concurrent consumers
	 */
	@Override
	@ManagedAttribute(description = "Gets the maximum number of concurrent consumers on this listener.")
	public int getMaxConcurrentConsumers()
	{
		return getJmsListenerContainerManager().getMaxConcurrentConsumers();
	}

	/**
	 * Start listener.
	 */
	@Override
	@ManagedOperation(description = "Starts this listener.")
	public void startListener()
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
					.append("- Starting listener with destination: ")
					.append(getDestinationName()));
		}

		// Starting...
		getJmsListenerContainerManager().startListeners(new ArrayList<String>());

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
					.append(" - Listener with destination: ")
					.append(getDestinationName())
					.append(" successfully started."));
		}
	}

	/**
	 * Stop listener.
	 */
	@Override
	@ManagedOperation(description = "Stops this listener.")
	public void stopListener()
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
					.append("- Stopping listener with destination: ")
					.append(getDestinationName()));
		}

		// Stopping...
		getJmsListenerContainerManager().stopListeners();

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
					.append("- Listener with destination: ")
					.append(getDestinationName())
					.append("\" successfully stopped."));
		}
	}

	/**
	 * Gets the destination name.
	 * 
	 * @return the destination name
	 */
	@Override
	public String getDestinationName()
	{
		return getJmsListenerContainerManager().getDestinationName();
	}

	/**
	 * Update max concurrent consumers.
	 * 
	 * @param maxConcurrentConsumers the max concurrent consumers
	 */
	@Override
	@ManagedOperation(description = "Gets the status of this listener.")
	@ManagedOperationParameters({
			@ManagedOperationParameter(name = "maxConcurrentConsumers", description = "The number of maximum concurrent consumers.")})
	public void updateMaxConcurrentConsumers(int maxConcurrentConsumers)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
					.append("- Setting the number of maximum concurrent consumers to \"")
					.append(maxConcurrentConsumers)
					.append("\" for queue \"")
					.append(getDestinationName()));
		}

		getJmsListenerContainerManager().setMaxConcurrentConsumers(maxConcurrentConsumers);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#onResponse(java.lang.Object)
	 */
	@Override
	public void onResponse(ApplicationEventNotification response)
	{

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		// we only update this if the status is COMPLETE, otherwise quit since there is no Failed status
		if (!com.sensus.api.applicationeventnotification.messages.Status.COMPLETED.equals(response.getStatus()))
		{
			return;
		}

		Integer correlationId;

		try
		{
			correlationId = Integer.parseInt(response.getCorrelationId());
		}
		catch (NumberFormatException ex)
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName())
						.append(" Correlation ID: ")
						.append(response.getCorrelationId())
						.append(" incorrect for Demand Reset, should be a number."));
			}
			return;
		}

		ProcessRequest processRequest = new ProcessRequest(new DMProcess());
		processRequest.getFirstProcess().addProcessItem(
				new ProcessItem(correlationId,
						ProcessItemStatusEnum.COMPLETED));
		createUserContext(processRequest);
		summarizeResponse(processRequest);
		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}
}