package com.sensus.dm.elec.action.listener;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.sensus.api.ontheglassreadping.messages.OnTheGlassReadPingResponse;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.messagetypes.measurements.DataQualifier;
import com.sensus.common.messagetypes.measurements.NetFlowType;
import com.sensus.common.messagetypes.measurements.QuantityType;
import com.sensus.common.messagetypes.meter.MeterError;
import com.sensus.common.messagetypes.meterread.Channel;
import com.sensus.common.messagetypes.meterread.MeterRead;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.action.model.ActionType;
import com.sensus.dm.common.action.model.ActionTypeEnum;
import com.sensus.dm.common.action.model.DMAction;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessItemStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.tenant.model.DMTenant;

/**
 * Used on Water and Gas.
 * 
 * @author QAT Brazil
 */
@ManagedResource(objectName = "spring:name=DemandReponseListener", description = "DemandReponseListener JMX Bean")
public class DemandReadPingListener extends AbstractListener<OnTheGlassReadPingResponse>
{

	/** The Constant TEN. */
	private static final int TEN = 10;

	/** The Constant DECIMAL_FORMAT. */
	private static final String DECIMAL_FORMAT = "######0.000";

	/**
	 * Instantiates a new demand read ping listener.
	 */
	public DemandReadPingListener()
	{
		setJmsContainer("demandReadPing.notif.jmsContainer");
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.action.listener.AbstractListener#onResponse(java.lang.Object)
	 */
	@Override
	public void onResponse(OnTheGlassReadPingResponse response)
	{

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_BEGINNING).append(this.getClass().getName()));
		}

		if (!validateDemandReadPing(response))
		{
			return;
		}

		DMProcess process = new DMProcess(new DMAction(new ActionType(ActionTypeEnum.DEMAND_READ)));

		ProcessRequest processRequest =
				new ProcessRequest(process, new DMTenant(response.getMeterIdentity().getCustomerId()));

		if (!convertProcessId(response, processRequest))
		{
			return;
		}

		verifyMeterReadChannel(response.getMeterRead(), process);

		createUserContext(processRequest);

		verifyMeterError(response.getErrors(), process);

		if (!checkProcessItem(processRequest, this.getClass().getName()))
		{
			return;
		}

		summarizeResponse(processRequest);

		if (LOG.isInfoEnabled())
		{
			LOG.info(new StringBuilder(STR_FINISHED).append(this.getClass().getName()));
		}
	}

	/**
	 * Validate demand read ping.
	 * 
	 * @param response the response
	 * @return true, if successful
	 */
	private boolean validateDemandReadPing(OnTheGlassReadPingResponse response)
	{

		if (ValidationUtil.isNull(response.getMeterRead()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response without meter read. "));
			}

			return false;
		}

		if (ValidationUtil.isNull(response.getClientContext()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response with no clienteContext. "));
			}

			return false;
		}

		if (ValidationUtil.isNullOrEmpty(response.getClientContext().getTransactionId()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response did not receive required request ID for : ").append(response));
			}
			return false;
		}

		if (ValidationUtil.isNull(response.getMeterIdentity()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response with no meterIdentity. "));
			}

			return false;
		}

		if (ValidationUtil.isNullOrEmpty(response.getMeterIdentity().getMeterNo()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response with no meterNo. "));
			}

			return false;
		}

		if (ValidationUtil.isNull(response.getFlexnetId()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response with no FlexNetId. "));
			}

			return false;
		}

		if (ValidationUtil.isNullOrEmpty(response.getMeterRead().getChannels()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response with no channels. "));
			}

			return false;
		}

		if (ValidationUtil.isNull(response.getMeterRead().getSamplePoint()))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response with no sample point. "));
			}

			return false;
		}

		return true;
	}

	/**
	 * Convert process id.
	 * 
	 * @param response the response
	 * @param processRequest the process request
	 * @return true, if successful
	 */
	private boolean convertProcessId(OnTheGlassReadPingResponse response, ProcessRequest processRequest)
	{
		try
		{
			processRequest.getFirstProcess().addProcessItem(
					new ProcessItem(Integer.parseInt(response.getClientContext().getTransactionId())
							, ProcessItemStatusEnum.COMPLETED));

			return verifyProcessItems(processRequest, new Device(response.getMeterIdentity().getMeterNo(), new Radio(
					new BigInteger(String.valueOf(response.getFlexnetId()))), null), this
					.getClass().getName());

		}
		catch (NumberFormatException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.info(new StringBuilder(STR_LOG).append(this.getClass().getName()).append(
						"Demand Read Ping response - Error converting id: "
								+ response.getClientContext().getTransactionId()));
			}
			return false;
		}
	}

	/**
	 * Verify meter read.
	 * 
	 * @param meterRead the meter read
	 * @param process the process
	 */
	private void verifyMeterReadChannel(MeterRead meterRead, DMProcess process)
	{
		if (ValidationUtil.isNull(meterRead))
		{
			return;
		}

		String strValue = null;

		for (Channel channel : meterRead.getChannels())
		{
			if (DataQualifier.NORMAL.equals(channel.getQualifier())
					&& QuantityType.SUMMATION.equals(channel.getQuantityType())
					&& NetFlowType.FORWARD_ONLY.equals(channel.getNetFlowType()))
			{
				strValue = DMConvertUtil.convertFormattedValue(
						DMConvertUtil.convertMultiplier(channel.getValue(),
								channel.getMultiplier(), channel.getUnit(), true)
						, channel.getUnit(), DECIMAL_FORMAT, null);

			}
		}

		// Creating a list with base properties
		List<Property> properties = new ArrayList<Property>();
		properties.add(new Property(PropertyEnum.LAST_READ_VALUE.getValue(), strValue));
		String timeStamp = String.valueOf(meterRead.getSamplePoint().toGregorianCalendar().getTime().getTime());
		properties.add(new Property(PropertyEnum.LAST_READ_TIME.getValue(), StringUtils.mid(timeStamp, 0, TEN)));
		process.getFirstProcessItem().setProperties(properties);

	}

	/**
	 * Verify meter error.
	 * 
	 * @param error the error
	 * @param process the process
	 */
	private void verifyMeterError(MeterError error, DMProcess process)
	{
		if (ValidationUtil.isNull(error))
		{
			return;
		}

		process.getFirstProcessItem().setProcessItemStatusEnum(ProcessItemStatusEnum.FAILED);
		process.getFirstProcessItem().setMessage(DMUtil.generateMessageRni(error.getErrorDetail().getDescription()));
	}

}
