package com.sensus.dm.elec.device.bcl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.sensus.api.clearerrorandwarningalarms.messages.ClearAlarms;
import com.sensus.api.clearerrorandwarningalarms.messages.ClearAlarmsResponse;
import com.sensus.api.getlatestreading.messages.GetLatestReadingResponse;
import com.sensus.api.getmeterreadings.messages.GetMeterReadingsRequestContext;
import com.sensus.api.getmeterreadings.messages.GetMeterReadingsResponse;
import com.sensus.api.getmeterreadings.messages.ReadingTypeSelector;
import com.sensus.api.ontheglassreadping.messages.OnTheGlassReadPingResponse;
import com.sensus.bcf.meterreading.service.MeterReadingService;
import com.sensus.bcf.meterreading.service.camel.PostRefreshOptions;
import com.sensus.common.messagetypes.measurements.DataQualifier;
import com.sensus.common.messagetypes.measurements.Multiplier;
import com.sensus.common.messagetypes.measurements.NetFlowType;
import com.sensus.common.messagetypes.measurements.QuantityType;
import com.sensus.common.messagetypes.measurements.Segmentation;
import com.sensus.common.messagetypes.meter.MeterError;
import com.sensus.common.messagetypes.meter.MeterIdentity;
import com.sensus.common.messagetypes.meterread.Channel;
import com.sensus.common.messagetypes.meterread.ChannelType;
import com.sensus.common.messagetypes.meterread.IntervalRead;
import com.sensus.common.messagetypes.meterread.MeterRead;
import com.sensus.common.messagetypes.meterread.TierMeasurementValue;
import com.sensus.common.messagetypes.meterread.TierRead;
import com.sensus.common.messagetypes.meterread.TierValue;
import com.sensus.common.messagetypes.meterread.UnitOfMeasure;
import com.sensus.common.messagetypes.read.missing.MissingReadStatus;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;

/**
 * The Class MockMeterReadingService.
 * 
 * @author QAT Global.
 */
public class MockMeterReadingService extends AbstractMockBase implements MeterReadingService
{

	/** The Constant ELEVEN. */
	private static final String ELEVEN = "11";

	/** The Constant TEN. */
	private static final String TEN = "10";

	/** The Constant TWELVE. */
	private static final String TWELVE = "12";

	/** The Constant THIRTEEN. */
	private static final String THIRTEEN = "13";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.bcf.meterreading.service.MeterReadingService#sendMeterEventsReset(java.util.List,
	 * java.lang.String)
	 */
	@Override
	public ClearAlarmsResponse sendMeterEventsReset(List<ClearAlarms> paramList, String paramString)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.bcf.meterreading.service.MeterReadingService#getLatestMeterReading(boolean, boolean,
	 * com.sensus.common.messagetypes.meter.MeterIdentity)
	 */
	@Override
	public GetLatestReadingResponse getLatestMeterReading(boolean arg0, boolean arg1, MeterIdentity arg2)
	{
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			GetLatestReadingResponse response = new GetLatestReadingResponse();

			XMLGregorianCalendar xgc =
					DMConvertUtil.convertCalendarToXMLGregorianCalendar(new GregorianCalendar());

			MeterRead mr = new MeterRead();
			mr.setSampleTime(xgc);
			mr.setIsEndReading(true);

			Channel c = new Channel();
			c.setType(ChannelType.DEMAND);
			c.setValue(TEN);
			c.setQuantityType(QuantityType.SUMMATION);
			c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
			c.setSegmentation(Segmentation.PHASE_A);
			c.setUnit(UnitOfMeasure.WH);
			c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
			c.setQualifier(DataQualifier.NORMAL);

			mr.getChannels().add(c);

			c = new Channel();
			c.setType(ChannelType.ENERGY);
			c.setValue(ELEVEN);
			c.setQuantityType(QuantityType.SUMMATION);
			c.setNetFlowType(NetFlowType.FORWARD_ONLY);
			c.setSegmentation(Segmentation.PHASE_A);
			c.setUnit(UnitOfMeasure.KW);
			c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
			c.setQualifier(DataQualifier.NORMAL);
			mr.getChannels().add(c);

			c = new Channel();
			c.setType(ChannelType.ENERGY);
			c.setValue(THIRTEEN);
			c.setQuantityType(QuantityType.SUMMATION);
			c.setNetFlowType(NetFlowType.FORWARD_ONLY);
			c.setSegmentation(Segmentation.PHASE_A);
			c.setUnit(UnitOfMeasure.WH);
			c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
			c.setQualifier(DataQualifier.NORMAL);
			mr.getChannels().add(c);

			List<com.sensus.common.messagetypes.meterread.MeterRead> nrList =
					new ArrayList<com.sensus.common.messagetypes.meterread.MeterRead>();

			nrList.add(mr);

			response.getNormalMeterRead().addAll(nrList);

			return response;
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getLatestMeterReadingErrorResponse();
		}

		GetLatestReadingResponse response = new GetLatestReadingResponse();

		XMLGregorianCalendar xgc =
				DMConvertUtil.convertCalendarToXMLGregorianCalendar(new GregorianCalendar());

		MeterRead mr = new MeterRead();
		mr.setIsEndReading(true);
		mr.setSampleTime(xgc);

		Channel c = new Channel();
		c.setType(ChannelType.DEMAND);
		c.setValue(TEN);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.KVAR);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(ELEVEN);
		c.setQuantityType(QuantityType.SUMMATION);
		c.setNetFlowType(NetFlowType.FORWARD_ONLY);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.KVAR);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		List<com.sensus.common.messagetypes.meterread.MeterRead> nrList =
				new ArrayList<com.sensus.common.messagetypes.meterread.MeterRead>();

		nrList.add(mr);

		response.getNormalMeterRead().addAll(nrList);

		return response;
	}

	/**
	 * Gets the latest meter reading error response.
	 * 
	 * @return the latest meter reading error response
	 */
	private GetLatestReadingResponse getLatestMeterReadingErrorResponse()
	{
		GetLatestReadingResponse response = new GetLatestReadingResponse();

		XMLGregorianCalendar xgc =
				DMConvertUtil.convertCalendarToXMLGregorianCalendar(new GregorianCalendar());

		MeterRead mr = new MeterRead();
		mr.setSampleTime(xgc);
		mr.setIsEndReading(true);
		mr.setMissingReadStatus(MissingReadStatus.READ_AVAILABLE);

		Channel c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(TEN);
		c.setQuantityType(QuantityType.SUMMATION);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.DEMAND);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_NEG_3);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.DEMAND);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_NEG_2);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.DEMAND);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_9);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.DEMAND);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_6);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.DEMAND);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_3);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.DEMAND);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_2);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(ELEVEN);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);
		List<com.sensus.common.messagetypes.meterread.MeterRead> nrList =
				new ArrayList<com.sensus.common.messagetypes.meterread.MeterRead>();

		nrList.add(mr);

		response.getNormalMeterRead().addAll(nrList);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.bcf.meterreading.service.MeterReadingService#getMeterReadings(java.util.List,
	 * com.sensus.common.messagetypes.meter.MeterIdentity, javax.xml.datatype.XMLGregorianCalendar,
	 * javax.xml.datatype.XMLGregorianCalendar, java.lang.Integer, java.lang.String,
	 * com.sensus.api.getmeterreadings.messages.GetMeterReadingsRequestContext, boolean, boolean)
	 */
	@Override
	public GetMeterReadingsResponse getMeterReadings(List<ReadingTypeSelector> arg0, MeterIdentity arg1,
			XMLGregorianCalendar arg2, XMLGregorianCalendar arg3, Integer arg4, String arg5,
			GetMeterReadingsRequestContext arg6, boolean arg7, boolean arg8)
	{
		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			return verifyMeterReadingSituations();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getMeterReadingErrorResponse();
		}

		// Situation Success
		GetMeterReadingsResponse response = new GetMeterReadingsResponse();

		response.setMeterIdentity(new MeterIdentity());
		response.getMeterIdentity().setMeterNo(ELEVEN);
		response.getMeterIdentity().setCustomerId(TEN);

		// Interval Read and Water/Gas Data Read
		if (ValidationUtil.isNullOrEmpty(arg0))
		{
			List<IntervalRead> irList = new ArrayList<IntervalRead>();

			irList.add(getIntervalRead());

			response.getIntervalRead().addAll(irList);

			return response;
		}

		// TOU Data
		if (arg0.get(0).isTou())
		{
			List<TierValue> tiers = new ArrayList<TierValue>();

			// tier 0 - total
			TierValue tv = new TierValue();
			tv.setTierId("0");

			TierMeasurementValue tmv = new TierMeasurementValue();
			tmv.setValue(TEN);
			tmv.setUnit(UnitOfMeasure.KVAR);
			tmv.setQuantityType(QuantityType.PEAK_DEMAND);
			tmv.setType(ChannelType.CONSUMED_F);

			tv.getTierMeasuredQuantities().add(tmv);

			tmv = new TierMeasurementValue();
			tmv.setValue(ELEVEN);
			tmv.setUnit(UnitOfMeasure.KW);
			tmv.setQuantityType(QuantityType.INTERVAL);
			tmv.setType(ChannelType.CONSUMED_F);

			tv.getTierMeasuredQuantities().add(tmv);

			tiers.add(tv);

			// tier 1 - normal

			tv = new TierValue();
			tv.setTierId("1");

			tmv = new TierMeasurementValue();
			tmv.setValue(TWELVE);
			tmv.setUnit(UnitOfMeasure.KW);
			tmv.setQuantityType(QuantityType.INTERVAL);
			tmv.setType(ChannelType.DELIVERED_R);

			tv.getTierMeasuredQuantities().add(tmv);

			tiers.add(tv);

			TierRead tr = new TierRead();

			tr.getTiers().addAll(tiers);

			List<TierRead> tList = new ArrayList<TierRead>();

			tList.add(tr);
			response.getTouMeterRead().addAll(tList);
			response.setMeterIdentity(new MeterIdentity());

			return response;
		}

		// Snapshot
		if (arg0.get(0).getQuantityType().equals(QuantityType.SNAPSHOT))
		{
			XMLGregorianCalendar xgc =
					DMConvertUtil.convertCalendarToXMLGregorianCalendar(new GregorianCalendar());

			MeterRead mr = new MeterRead();
			mr.setSampleTime(xgc);
			mr.setSamplePoint(xgc);
			mr.setIsLPRead(false);
			mr.setMissingReadStatus(MissingReadStatus.READ_AVAILABLE);

			Channel c = new Channel();
			c.setValue(TEN);
			c.setQuantityType(QuantityType.SNAPSHOT);
			c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
			c.setSegmentation(Segmentation.PHASE_A);
			c.setUnit(UnitOfMeasure.KVAR);
			c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
			c.setQualifier(DataQualifier.MAXIMUM);
			c.setQualifier(DataQualifier.NORMAL);

			mr.getChannels().add(c);

			List<MeterRead> nrList = new ArrayList<MeterRead>();

			nrList.add(mr);

			response.getNormalMeterRead().addAll(nrList);
		}

		XMLGregorianCalendar xgc2 =
				DMConvertUtil.convertCalendarToXMLGregorianCalendar(new GregorianCalendar());

		MeterRead mr2 = new MeterRead();
		mr2.setSampleTime(xgc2);
		mr2.setSamplePoint(xgc2);
		mr2.setIsLPRead(false);
		mr2.setMissingReadStatus(MissingReadStatus.READ_AVAILABLE);

		mr2.getChannels().add(getChannelQuantityType(QuantityType.INTERVAL));
		mr2.getChannels().add(getChannelQuantityType(QuantityType.SUMMATION));

		response.getNormalMeterRead().add(mr2);
		return response;
	}

	/**
	 * Gets the meter reading error response.
	 * 
	 * @return the meter reading error response
	 */
	private GetMeterReadingsResponse getMeterReadingErrorResponse()
	{
		GetMeterReadingsResponse response = new GetMeterReadingsResponse();

		response.setMeterIdentity(new MeterIdentity());
		response.getMeterIdentity().setMeterNo(ELEVEN);
		response.getMeterIdentity().setCustomerId(TEN);

		XMLGregorianCalendar xgc =
				DMConvertUtil.convertCalendarToXMLGregorianCalendar(new GregorianCalendar());

		MeterRead mr = new MeterRead();
		mr.setIsEndReading(true);
		mr.setSampleTime(xgc);
		mr.setSamplePoint(xgc);
		mr.setIsLPRead(false);
		mr.setMissingReadStatus(MissingReadStatus.READ_AVAILABLE);

		Channel c = new Channel();
		c.setValue(TEN);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.KVAR);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setValue(TEN);
		c.setQuantityType(QuantityType.SUMMATION);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.KVAR);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_NEG_3);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_NEG_2);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_9);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_6);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_3);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(TWELVE);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_2);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setType(ChannelType.ENERGY);
		c.setValue(ELEVEN);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.WH);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		com.sensus.common.messagetypes.meterread.IntervalRead ir =
				new com.sensus.common.messagetypes.meterread.IntervalRead();

		List<com.sensus.common.messagetypes.meterread.IntervalRead> irList =
				new ArrayList<com.sensus.common.messagetypes.meterread.IntervalRead>();

		ir.getMeterRead().add(mr);

		irList.add(ir);
		// request.getIntervalReads().get(0).getChannelSize();
		// add second meter read...
		mr = new MeterRead();
		mr.setIsEndReading(false);
		mr.setSampleTime(xgc);
		mr.setSamplePoint(xgc);
		mr.setIsLPRead(false);
		mr.setMissingReadStatus(MissingReadStatus.READ_AVAILABLE);

		c = new Channel();
		c.setValue(TEN);
		c.setQuantityType(QuantityType.INTERVAL);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.KVAR);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		c = new Channel();
		c.setValue(TEN);
		c.setQuantityType(QuantityType.SUMMATION);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.KVAR);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		mr.getChannels().add(c);

		ir.getMeterRead().add(mr);

		irList.add(ir);

		response.getIntervalRead().addAll(irList);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.bcf.meterreading.service.MeterReadingService#getOnTheGlassRead(java.util.List,
	 * com.sensus.common.messagetypes.meter.MeterIdentity, java.lang.String, java.lang.String)
	 */
	@Override
	public OnTheGlassReadPingResponse getOnTheGlassRead(
			List<com.sensus.bcf.meterreading.cim.Version_1.ReadingTypeSelector> arg0, MeterIdentity arg1,
			String arg2, String arg3)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.bcf.meterreading.service.MeterReadingService#sendDemandReset(java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<MeterError> sendDemandReset(List<MeterIdentity> arg0, String arg, Long arg2, Long arg3, String arg4)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.bcf.meterreading.service.MeterReadingService#onDemandReadPing(java.util.List, java.util.List,
	 * java.lang.String, java.lang.String, com.sensus.bcf.meterreading.service.camel.PostRefreshOptions,
	 * java.lang.String)
	 */
	@Override
	public List<MeterError> onDemandReadPing(List<MeterIdentity> arg0,
			List<com.sensus.bcf.meterreading.cim.Version_1.ReadingTypeSelector> arg1, String arg2, String arg3,
			PostRefreshOptions arg4, String arg5)
	{
		return null;
	}

	/**
	 * Gets the interval read.
	 * 
	 * @return the interval read
	 */
	private IntervalRead getIntervalRead()
	{
		IntervalRead intervalRead = new IntervalRead();
		intervalRead.getMeterRead().add(getMeterReadIsEndReading(false));
		intervalRead.getMeterRead().add(getMeterReadIsEndReading(true));
		return intervalRead;
	}

	/**
	 * Gets the meter read is end reading.
	 * 
	 * @param isEndReading the is end reading
	 * @return the meter read is end reading
	 */
	private MeterRead getMeterReadIsEndReading(Boolean isEndReading)
	{
		XMLGregorianCalendar xgc = DMConvertUtil.convertCalendarToXMLGregorianCalendar(new GregorianCalendar());

		MeterRead mr = new MeterRead();
		mr.setIsEndReading(isEndReading);
		mr.setSampleTime(xgc);
		mr.setSamplePoint(xgc);
		mr.setIsLPRead(false);
		mr.setMissingReadStatus(MissingReadStatus.READ_AVAILABLE);

		mr.getChannels().add(getChannelQuantityType(QuantityType.INTERVAL));
		mr.getChannels().add(getChannelQuantityType(QuantityType.SUMMATION));

		return mr;
	}

	/**
	 * Gets the channel quantity type.
	 * 
	 * @param quantityType the quantity type
	 * @return the channel quantity type
	 */
	private Channel getChannelQuantityType(QuantityType quantityType)
	{
		Channel c = new Channel();
		c.setValue(TEN);
		c.setQuantityType(quantityType);
		c.setNetFlowType(NetFlowType.REVERSE_ENERGY_DETECTION);
		c.setSegmentation(Segmentation.PHASE_A);
		c.setUnit(UnitOfMeasure.KVAR);
		c.setMultiplier(Multiplier.MULTIPLIER_10_POWER_1);
		c.setQualifier(DataQualifier.NORMAL);

		return c;
	}
}
