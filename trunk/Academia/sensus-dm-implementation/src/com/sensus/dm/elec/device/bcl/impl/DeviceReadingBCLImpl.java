package com.sensus.dm.elec.device.bcl.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TimeZone;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.cellprocessor.Optional;

import com.sensus.api.getlatestreading.messages.GetLatestReadingResponse;
import com.sensus.api.getmeterreadings.messages.GetMeterReadingsResponse;
import com.sensus.api.getmeterreadings.messages.ObjectFactory;
import com.sensus.api.getmeterreadings.messages.ReadingTypeSelector;
import com.sensus.bcf.meterreading.service.MeterReadingService;
import com.sensus.bcf.meterreading.service.camel.MeterReadingServiceSOAImpl;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.csv.CSVColumn;
import com.sensus.common.messagetypes.events.InitiatorId;
import com.sensus.common.messagetypes.measurements.QuantityType;
import com.sensus.common.messagetypes.meter.MeterIdentity;
import com.sensus.common.messagetypes.meterread.Channel;
import com.sensus.common.messagetypes.meterread.ChannelType;
import com.sensus.common.messagetypes.meterread.MeterRead;
import com.sensus.common.messagetypes.meterread.TierMeasurementValue;
import com.sensus.common.messagetypes.meterread.TierRead;
import com.sensus.common.messagetypes.meterread.TierValue;
import com.sensus.common.messagetypes.meterread.UnitOfMeasure;
import com.sensus.common.messagetypes.service.ServiceType;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.util.DMConvertUtil;
import com.sensus.dm.common.base.util.DMUtil;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertChannel;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertTimeZoneDateTime;
import com.sensus.dm.common.base.util.csv.cellprocessor.ConvertTouRead;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.ProcessStatusEnum;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.elec.device.bcl.IDeviceReadingBCL;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.IntervalRead;
import com.sensus.dm.elec.device.model.IntervalReadSort;
import com.sensus.dm.elec.device.model.LoadProfile;
import com.sensus.dm.elec.device.model.TOURead;
import com.sensus.dm.elec.device.model.TierData;
import com.sensus.dm.elec.device.model.TierDataSort;
import com.sensus.dm.elec.device.model.UnitOfMeasureTotal;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;

/**
 * The Class DeviceReadingBCLImpl.
 * 
 * @author QAT Global
 * 
 */
public class DeviceReadingBCLImpl implements IDeviceReadingBCL
{

	// -------------------------------------------------------------------------
	// Logs.

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DeviceReadingBCLImpl.class);

	// -------------------------------------------------------------------------
	// Symbols, characters and etc (not i18n).

	/** The Constant ERROR_GETTING_DATA_FROM_API_FOR_FLEXNET_ID. */
	private static final String ERROR_GETTING_DATA_FROM_API_FOR_FLEXNET_ID =
			"Error getting data from API for flexnetId  ";

	/** The Constant API_CALLED. */
	private static final String API_CALLED = " Sensus BCF Called";

	/** The Constant STR_ENERGY. */
	private static final String STR_ENERGY = "energy";

	/** The Constant STR_PIPE. */
	private static final String STR_PIPE = "|";

	/** The Constant STR_UNDERLINE. */
	private static final String STR_UNDERLINE = "_";

	/** The Constant STR_SEG. */
	private static final String STR_SEG = "seg: ";

	/** The Constant STR_COMMA. */
	private static final String STR_COMMA = ",";

	/** The Constant STR_DASH. */
	private static final String STR_DASH = "-";

	/** The Constant STR_SUMMATION. */
	private static final String STR_SUMMATION = "summation";

	/** The Constant STR_INTERVAL. */
	private static final String STR_INTERVAL = "interval";

	/** The Constant STR_METER_ID. */
	private static final String STR_METER_ID = " and meter ID ";

	/** The Constant INT_0. */
	private static final int INT_0 = 0;

	/** The Constant INT_1. */
	private static final int INT_1 = 1;

	/** The Constant STR_FORMAT_3D. */
	private static final String STR_FORMAT_3D = "######0.000";

	/** The Constant STR_FORMAT_1D. */
	private static final String STR_FORMAT_1D = "######.0";

	/** The Constant KILO. */
	private static final String KILO = "k";

	/** The Constant NOT_AVAILABLE. */
	private static final String NOT_AVAILABLE = "N/A";

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant STR_NUM_ZERO. */
	private static final String STR_NUM_ZERO = "0";

	/** The Constant LOG_DATA_READ. */
	private static final String LOG_DATA_READ = " Data Reading: ";

	/** The Constant LOG_TOU_READING. */
	private static final String LOG_ELEC_TOU_READING = "### ELECTRIC TOU Reading: ";

	/** The Constant LOG_INTERVAL_READ. */
	private static final String LOG_ELEC_INTERVAL_READ = "### ELECTRIC Interval Reading: ";

	/** The Constant LOG_ELEC_LOAD_PROFILE_READ. */
	private static final String LOG_ELEC_LOAD_PROFILE_READ = "### ELECTRIC Load Profile Reading: ";

	/** The Constant LOG_METER_READING_RESPONSE. */
	private static final String LOG_METER_READ_RESPONSE = "### Meter Reading Response: ";

	/** The Constant LOG_SNAPSHOT_READ. */
	private static final String LOG_ELEC_SNAPSHOT_READ = "### ELECTRIC Snapshot Reading: ";

	/** The Constant LOG_LOAD_PROFILE_READ. */
	private static final String LOG_LOAD_PROFILE_READ = "### Load Profile Reading: ";

	/** The Constant METER_READING_NOT_AVAILABLE. */
	private static final String METER_READING_NOT_AVAILABLE =
			" Meter reading NOT available. Returning without readings. ";

	/** The Constant HASH_TAG. */
	private static final String HASH_TAG = "### ";

	/** The Constant BEGIN. */
	private static final String BEGIN = "Begin " + HASH_TAG;

	/** The Constant END. */
	private static final String END = "End " + HASH_TAG;

	/** The Constant PROPERTY_PATH_CHANNELS. */
	private static final String PROPERTY_PATH_CHANNELS = "channels";

	/** The Constant SIXTY_MINUTES. */
	private static final int SIXTY_MINUTES = 60;

	// -------------------------------------------------------------------------
	// Not i18n messages/words.

	/** The Constant STR_TIER_ID. */
	private static final String STR_TIER_ID = "Tier Id";

	/** The Constant STR_TIER. */
	private static final String STR_TIER = "Tier ";

	/** The Constant STR_TOTAL. */
	private static final String STR_TOTAL = "Total";

	/** The Constant INTERVAL_READ. */
	private static final String INTERVAL_READ = "IntervalRead";

	/** The Constant TOU_READ. */
	private static final String TOU_READ = "TOURead";

	/** The Constant SNAPSHOT_READ. */
	private static final String SNAPSHOT_READ = "SnapshotRead";

	/** The Constant HOUR_OFFSET. */
	private static final Integer HOUR_OFFSET = 23;

	/** The Constant MINUTE_OFFSET. */
	private static final Integer MINUTE_OFFSET = 59;

	/** The Constant SECOND_OFFSET. */
	private static final Integer SECOND_OFFSET = 59;

	/** The Constant CALLING_API. */
	private static final String CALLING_API = "Calling Sensus BCF";

	/** The Constant SLASH_CONSTANT. */
	private static final String SLASH_CONSTANT = "/";

	/** The Constant TIME_ZONE_GMT. */
	private static final String TIME_ZONE_GMT = "GMT";

	/** The Constant LATEST_READING_AVAIABLE_CONVERTING_TO_LOAD_PROFILE. */
	private static final String LATEST_READING_AVAIABLE_CONVERTING_TO_LOAD_PROFILE =
			"Latest reading avaiable. Converting to Load Profile...";

	/** The Constant RETURNING_LOAD_PROFILE. */
	private static final String RETURNING_LOAD_PROFILE = "Returning Load Profile...";

	/** The Constant GET_ALL_INTERVAL_READS_FILTERED_FINISHED_AT_GENERATE_CSV_FILE. */
	private static final String GET_ALL_INTERVAL_READS_FILTERED_FINISHED_AT_GENERATE_CSV_FILE =
			"Get all interval reads filtered finished at generate CSV File";

	/** The Constant GET_ALL_SNAPSHOT_READS_FILTERED_FINISHED_AT_GENERATE_CSV_FILE. */
	private static final String GET_ALL_SNAPSHOT_READS_FILTERED_FINISHED_AT_GENERATE_CSV_FILE =
			"Get all snapshot reads filtered finished at generate CSV File";

	/** The Constant SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION. */
	private static final String SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION =
			"sensus.dm.common.process.nc.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The meter reading service. */
	private MeterReadingService meterReadingService;

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL;

	/** The water meter bcl. */
	private IWaterMeterBCL waterMeterBCL;

	/** The gas meter bcl. */
	private IGasMeterBCL gasMeterBCL;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The csv interval read list all columns. */
	private List<CSVColumn> csvIntervalReadListAllColumns;

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
	 * Gets the water meter bcl.
	 * 
	 * @return the water meter bcl
	 */
	public IWaterMeterBCL getWaterMeterBCL()
	{
		return waterMeterBCL;
	}

	/**
	 * Sets the water meter bcl.
	 * 
	 * @param waterMeterBCL the new water meter bcl
	 */
	public void setWaterMeterBCL(IWaterMeterBCL waterMeterBCL)
	{
		this.waterMeterBCL = waterMeterBCL;
	}

	/**
	 * Gets the gas meter bcl.
	 * 
	 * @return the gas meter bcl
	 */
	public IGasMeterBCL getGasMeterBCL()
	{
		return gasMeterBCL;
	}

	/**
	 * Sets the gas meter bcl.
	 * 
	 * @param gasMeterBCL the new gas meter bcl
	 */
	public void setGasMeterBCL(IGasMeterBCL gasMeterBCL)
	{
		this.gasMeterBCL = gasMeterBCL;
	}

	/**
	 * Gets the meter reading service.
	 * 
	 * @return the meter reading service
	 */
	public MeterReadingService getMeterReadingService()
	{
		return meterReadingService;
	}

	/**
	 * Sets the meter reading service.
	 * 
	 * @param meterReadingService the new meter reading service
	 */
	public void setMeterReadingService(MeterReadingService meterReadingService)
	{
		this.meterReadingService = meterReadingService;
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
	 * Gets the csv interval read list all columns.
	 * 
	 * @return the csv interval read list all columns
	 */
	public List<CSVColumn> getCsvIntervalReadListAllColumns()
	{
		return csvIntervalReadListAllColumns;
	}

	/**
	 * Sets the csv interval read list all columns.
	 * 
	 * @param csvIntervalReadListAllColumns the new csv interval read list all columns
	 */
	public void setCsvIntervalReadListAllColumns(List<CSVColumn> csvIntervalReadListAllColumns)
	{
		this.csvIntervalReadListAllColumns = csvIntervalReadListAllColumns;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Water Data Reading interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchAllWaterGasDataRead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<IntervalRead> fetchAllWaterGasDataRead(DeviceReadingRequest request)
	{
		ServiceType serviceType = DMConvertUtil.convertToSensusServiceType(request.getServiceTypeEnum());

		printLogInfoEnable(HASH_TAG + serviceType.toString() + LOG_DATA_READ + BEGIN);
		printLogInfoEnable(HASH_TAG + serviceType.toString() + LOG_DATA_READ + CALLING_API);

		try
		{
			// Send meter request (goes to BCF/API Sensus project)
			GetMeterReadingsResponse meterReadResponse =
					getMeterReadingService().getMeterReadings(
							createSelectors(INTERVAL_READ, serviceType),
							DMConvertUtil.convertToMeterIdentity(request.getDevice()),
							DMUtil.createXMLGregorianCalendar(request.getInitialDate(), 0, 0, 0,
									TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
											.getDisplayName())),
							DMUtil.createXMLGregorianCalendar(request.getEndDate(), HOUR_OFFSET,
									MINUTE_OFFSET, SECOND_OFFSET,
									TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
											.getDisplayName())),
							null, null, new ObjectFactory().createGetMeterReadingsRequestContext()
							, false, false);

			printLogInfoEnable(HASH_TAG + serviceType.toString() + API_CALLED);

			return createIntervalReadResponse(request, meterReadResponse, serviceType, QuantityType.SUMMATION,
					STR_FORMAT_1D, false);

		}
		catch (Exception ex)
		{
			LOG.error(HASH_TAG + serviceType.toString() + ERROR_GETTING_DATA_FROM_API_FOR_FLEXNET_ID
					+ request.getDevice().getDeviceId(), ex);
			InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();
			response.setStatus(Status.ExternalError);
			response.addMessage(SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION, MessageSeverity.Error,
					MessageLevel.None);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#generateFileCSVWaterGasDataRead(com.sensus.dm.elec.device.model
	 * .request.DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVWaterGasDataRead(DeviceReadingRequest request)
	{
		printLogInfoEnable(GET_ALL_INTERVAL_READS_FILTERED_FINISHED_AT_GENERATE_CSV_FILE);

		// Get interval reads and generate file csv with this time of use reads information
		InternalResultsResponse<IntervalRead> responseAllIntervalReads =
				fetchAllWaterGasDataRead(request);

		if (responseAllIntervalReads.isInError() || !responseAllIntervalReads.hasResults())
		{
			return responseAllIntervalReads;
		}

		InternalResponse response = new InternalResponse();

		DMUtil.generateCSV(getIntervalReadListColumns(request, responseAllIntervalReads),
				responseAllIntervalReads.getResultsList(), request, response);

		// If file csv was successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(request.getProcessId(),
						ProcessStatusEnum.COMPLETED), request.getUserContext()));
	}

	/**
	 * Capitalize first letter.
	 * 
	 * @param key the key
	 * @param nextPos the next pos
	 * @return the string
	 */
	private String capitalizeFirstLetter(String key, int nextPos)
	{
		// get the letter starting at the given position...that`s the one we need to capitalize
		String letterToCapitalize = key.substring(nextPos, nextPos + 1);

		// create a buffer with the string before that letter, with the new letter capitalized, and string after that
		// letter
		StringBuffer sb =
				new StringBuffer(key.substring(0, nextPos) + letterToCapitalize.toUpperCase()
						+ key.substring(++nextPos));

		// search for an empty space
		if (key.indexOf(SPACE, nextPos) > 0)
		{
			// if we find an empty space, recurse with the position of the letter after that space
			return capitalizeFirstLetter(sb.toString(), key.indexOf(SPACE, nextPos) + 1);
		}

		// no empty spaces found...we`re done
		return sb.toString();

	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// TOU (Time Of Use) interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.IDeviceReadingBCLImpl#fetchAllTOURead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<String[][]> fetchAllTOURead(DeviceReadingRequest request)
	{
		printLogInfoEnable(LOG_ELEC_TOU_READING + BEGIN);
		printLogInfoEnable(LOG_ELEC_TOU_READING
				+ new StringBuilder(CALLING_API)
						.append(request.getDevice().getRadio().getFlexNetId()).append(STR_METER_ID)
						.append(request.getDevice().getDeviceId()));
		try
		{
			GetMeterReadingsResponse mrResponse = getMeterReadingService().getMeterReadings(
					createSelectors(TOU_READ, ServiceType.ELECTRIC),
					DMConvertUtil.convertToMeterIdentity(request.getDevice()),
					DMUtil.createXMLGregorianCalendar(request.getInitialDate(), 0, 0, 0,
							TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
									.getDisplayName())),
					DMUtil.createXMLGregorianCalendar(request.getEndDate(), HOUR_OFFSET, MINUTE_OFFSET,
							MINUTE_OFFSET,
							TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
									.getDisplayName())), null, null,
					new ObjectFactory().createGetMeterReadingsRequestContext(), false, false);

			printLogInfoEnable(LOG_ELEC_TOU_READING
					+ new StringBuilder(API_CALLED)
							.append(request.getDevice().getRadio().getFlexNetId()).append(STR_METER_ID)
							.append(request.getDevice().getDeviceId()));

			InternalResultsResponse<String[][]> response = new InternalResultsResponse<String[][]>();

			response.addResult(transformTOUReadInto2DArray(createTOURead(mrResponse)));

			printLogInfoEnable(LOG_ELEC_TOU_READING + END);

			return response;
		}
		catch (Exception e)
		{
			LOG.error(
					new StringBuilder(LOG_ELEC_TOU_READING)
							.append("An exception occurred while executing a TOU reading for FlexNet ID \"")
							.append(request.getDevice().getRadio().getFlexNetId()).append(STR_METER_ID)
							.append(request.getDevice().getDeviceId()).toString(), e);
			InternalResultsResponse<String[][]> response = new InternalResultsResponse<String[][]>();
			response.setStatus(Status.ExternalError);
			response.addMessage(SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION, MessageSeverity.Error,
					MessageLevel.None);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCLImpl#generateFileCSVTOURead(com.sensus.dm.elec.device.model.request
	 * .
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVTOURead(DeviceReadingRequest request)
	{

		InternalResponse response = new InternalResponse();

		InternalResultsResponse<String[][]> trResponse = fetchAllTOURead(request);
		if (trResponse.isInError() || !trResponse.hasResults()
				|| ValidationUtil.isNull(trResponse.getFirstResult().length <= 1))
		{
			return trResponse;
		}

		List<CSVColumn> csvColumnList = new ArrayList<CSVColumn>();

		String[][] touRead = trResponse.getFirstResult();
		for (int i = 0; i < touRead[0].length; i++)
		{
			CSVColumn column = new CSVColumn();
			column.setHeader(touRead[0][i]);
			column.setPropertyPath(".");
			column.setWriteCellProcessor(new Optional(new ConvertTouRead(i)));
			csvColumnList.add(column);
		}

		List<String[]> touReadList = new ArrayList<String[]>();

		for (int i = 1; i < trResponse.getResultsList().get(0).length; i++)
		{
			touReadList.add(trResponse.getResultsList().get(0)[i]);
		}

		DMUtil.generateCSV(csvColumnList, touReadList, request, response);

		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(request.getProcessId(),
						ProcessStatusEnum.COMPLETED), request.getUserContext(), request.getFileName()));

	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// load profile interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchAllLoadProfileRead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<IntervalRead> fetchAllLoadProfileRead(
			DeviceReadingRequest request)
	{
		printLogInfoEnable(LOG_ELEC_LOAD_PROFILE_READ + CALLING_API);

		try
		{
			MeterReadingServiceSOAImpl mrs = (MeterReadingServiceSOAImpl)getMeterReadingService();
			mrs.setInitiatorId(InitiatorId.DEVICEMANAGER);

			// Send meter request (goes to BCF/API Sensus project)
			GetMeterReadingsResponse meterReadResponse =
					mrs.getMeterReadings(
							createSelectors(INTERVAL_READ, ServiceType.ELECTRIC),
							DMConvertUtil.convertToMeterIdentity(request.getDevice()),
							DMUtil.createXMLGregorianCalendar(request.getInitialDate(), 0, 0, 0,
									TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
											.getDisplayName())),
							DMUtil.createXMLGregorianCalendar(request.getEndDate(), HOUR_OFFSET, MINUTE_OFFSET,
									SECOND_OFFSET,
									TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
											.getDisplayName())), null, null,
							new ObjectFactory().createGetMeterReadingsRequestContext()
							, false, false);

			return createIntervalReadResponse(request, meterReadResponse, ServiceType.ELECTRIC, QuantityType.INTERVAL,
					STR_FORMAT_3D, true);
		}
		catch (Exception ex)
		{
			LOG.error(LOG_ELEC_LOAD_PROFILE_READ + ERROR_GETTING_DATA_FROM_API_FOR_FLEXNET_ID
					+ request.getDevice().getDeviceId(), ex);
			InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();
			response.setStatus(Status.ExternalError);
			response.addMessage(SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION, MessageSeverity.Error,
					MessageLevel.None);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#generateFileCSVLoadProfileRead(com.sensus.dm.elec.device.model
	 * .request.DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVLoadProfileRead(DeviceReadingRequest inquiryIntervalReadRequest)
	{
		printLogInfoEnable(GET_ALL_INTERVAL_READS_FILTERED_FINISHED_AT_GENERATE_CSV_FILE);

		// Get interval reads and generate file csv with this time of use reads information
		InternalResultsResponse<IntervalRead> loadProfileResponse =
				fetchAllLoadProfileRead(inquiryIntervalReadRequest);

		if (loadProfileResponse.isInError() || !loadProfileResponse.hasResults())
		{
			return loadProfileResponse;
		}

		InternalResponse response = new InternalResponse();

		DMUtil.generateCSV(getIntervalReadListColumns(inquiryIntervalReadRequest, loadProfileResponse),
				loadProfileResponse.getResultsList(), inquiryIntervalReadRequest, response);

		// If file csv was successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryIntervalReadRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryIntervalReadRequest.getUserContext()));
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Interval reads interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchAllIntervalRead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<IntervalRead> fetchAllIntervalRead(
			DeviceReadingRequest request)
	{
		printLogInfoEnable(LOG_ELEC_INTERVAL_READ + CALLING_API);

		try
		{
			// Send meter request (goes to BCF/API Sensus project)
			GetMeterReadingsResponse meterReadResponse =
					getMeterReadingService().getMeterReadings(
							createSelectors(INTERVAL_READ, ServiceType.ELECTRIC),
							DMConvertUtil.convertToMeterIdentity(request.getDevice()),
							DMUtil.createXMLGregorianCalendar(request.getInitialDate(), 0, 0, 0,
									TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
											.getDisplayName())),
							DMUtil.createXMLGregorianCalendar(request.getEndDate(), HOUR_OFFSET, MINUTE_OFFSET,
									SECOND_OFFSET,
									TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
											.getDisplayName())), null, null,
							new ObjectFactory().createGetMeterReadingsRequestContext()
							, false, false);

			return createIntervalReadResponse(request, meterReadResponse, ServiceType.ELECTRIC, QuantityType.INTERVAL,
					STR_FORMAT_3D, false);
		}
		catch (Exception ex)
		{
			LOG.error(LOG_ELEC_INTERVAL_READ + ERROR_GETTING_DATA_FROM_API_FOR_FLEXNET_ID
					+ request.getDevice().getDeviceId(), ex);
			InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();
			response.setStatus(Status.ExternalError);
			response.addMessage(SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION, MessageSeverity.Error,
					MessageLevel.None);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#generateFileCSVIntervalRead(com.sensus.dm.elec.device.model.request
	 * .
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVIntervalRead(DeviceReadingRequest inquiryIntervalReadRequest)
	{
		printLogInfoEnable(GET_ALL_INTERVAL_READS_FILTERED_FINISHED_AT_GENERATE_CSV_FILE);

		// Get interval reads and generate file csv with this time of use reads information
		InternalResultsResponse<IntervalRead> responseAllIntervalReads =
				fetchAllIntervalRead(inquiryIntervalReadRequest);

		if (responseAllIntervalReads.isInError() || !responseAllIntervalReads.hasResults())
		{
			return responseAllIntervalReads;
		}

		InternalResponse response = new InternalResponse();

		DMUtil.generateCSV(getIntervalReadListColumns(inquiryIntervalReadRequest, responseAllIntervalReads),
				responseAllIntervalReads.getResultsList(), inquiryIntervalReadRequest, response);

		// If file csv was successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(inquiryIntervalReadRequest.getProcessId(),
						ProcessStatusEnum.COMPLETED), inquiryIntervalReadRequest.getUserContext()));
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Snapshot interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchAllSnapshots(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResultsResponse<IntervalRead> fetchAllSnapshots(DeviceReadingRequest request)
	{
		printLogInfoEnable(LOG_ELEC_SNAPSHOT_READ + BEGIN);
		printLogInfoEnable(LOG_ELEC_SNAPSHOT_READ + CALLING_API);

		try
		{
			// Send meter request.
			GetMeterReadingsResponse meterReadResponse =
					getMeterReadingService().getMeterReadings(
							createSelectors(SNAPSHOT_READ, ServiceType.ELECTRIC),
							DMConvertUtil.convertToMeterIdentity(request.getDevice()),
							DMUtil.createXMLGregorianCalendar(request.getInitialDate(), 0, 0, 0,
									TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
											.getDisplayName())),
							DMUtil.createXMLGregorianCalendar(request.getEndDate(), HOUR_OFFSET, MINUTE_OFFSET,
									SECOND_OFFSET,
									TimeZone.getTimeZone(request.getDevice().getRadio().getLocation().getTimeZoneInfo()
											.getDisplayName())), null,
							null, new ObjectFactory().createGetMeterReadingsRequestContext(), false, false);

			printLogInfoEnable(LOG_ELEC_SNAPSHOT_READ + API_CALLED);

			return createMeterReadResponse(request, meterReadResponse, QuantityType.SNAPSHOT, ServiceType.ELECTRIC,
					true);
		}
		catch (Exception ex)
		{
			LOG.error(LOG_ELEC_SNAPSHOT_READ + "Error getting data from API for flexnetId "
					+ request.getDevice().getDeviceId(), ex);
			InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();
			response.setStatus(Status.ExternalError);
			response.addMessage(SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION, MessageSeverity.Error,
					MessageLevel.None);
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#generateFileCSVSnapshot(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InternalResponse generateFileCSVSnapshot(DeviceReadingRequest request)
	{
		printLogInfoEnable(GET_ALL_SNAPSHOT_READS_FILTERED_FINISHED_AT_GENERATE_CSV_FILE);

		// Get interval reads and generate file csv with this time of use reads information
		InternalResultsResponse<IntervalRead> responseAllIntervalReads =
				fetchAllSnapshots(request);

		if (responseAllIntervalReads.isInError() || !responseAllIntervalReads.hasResults())
		{
			return responseAllIntervalReads;
		}

		InternalResponse response = new InternalResponse();

		DMUtil.generateCSV(getIntervalReadListColumns(request, responseAllIntervalReads),
				responseAllIntervalReads.getResultsList(), request, response);

		// If file csv was not successfully generated update process to complete
		if (response.isInError())
		{
			return response;
		}

		// update process as complete
		return getProcessBCL().updateProcess(
				new ProcessRequest(DMUtil.generateProcess(request.getProcessId(),
						ProcessStatusEnum.COMPLETED),
						request.getUserContext(), request.getFileName()));

	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Load profile interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IDeviceReadingBCL#fetchUpdatedMeterLoadProfile(com.sensus.dm.common.device.model
	 * .request.DeviceRequest)
	 */
	@Override
	public InternalResultsResponse<LoadProfile> fetchUpdatedMeterLoadProfile(DeviceRequest request)
	{
		printLogInfoEnable(LOG_LOAD_PROFILE_READ + BEGIN);

		InternalResultsResponse<LoadProfile> response = new InternalResultsResponse<LoadProfile>();
		response.addResult(new LoadProfile(NOT_AVAILABLE, NOT_AVAILABLE, NOT_AVAILABLE, NOT_AVAILABLE, NOT_AVAILABLE));

		// return if it is electric
		if (request.getFirstDevice().getDeviceType().equals(DeviceTypeEnum.ELECTRIC_METER))
		{
			printLogInfoEnable(LOG_LOAD_PROFILE_READ + "Call not available for service electric.");
			return response;

		}

		printLogInfoEnable(LOG_LOAD_PROFILE_READ + "Calling Latest reading API.");

		try
		{
			MeterReadingServiceSOAImpl mrs = (MeterReadingServiceSOAImpl)getMeterReadingService();
			mrs.setInitiatorId(InitiatorId.DEVICEMANAGER);

			MeterIdentity meterIdentity = DMConvertUtil.convertToMeterIdentity(request.getFirstDevice());

			// latest reading response returns data for both, interval data and snapshot data
			GetLatestReadingResponse latestResponse =
					mrs.getLatestMeterReading(false, false, meterIdentity);

			if (ValidationUtil.isNull(latestResponse)
					|| ValidationUtil.isNullOrEmpty(latestResponse.getNormalMeterRead())
					|| ValidationUtil.isNull(latestResponse.getNormalMeterRead().get(INT_0)))
			{
				printLogInfoEnable(LOG_LOAD_PROFILE_READ + METER_READING_NOT_AVAILABLE);
				return response;
			}

			// setting profile create date
			XMLGregorianCalendar lastDate = latestResponse.getNormalMeterRead().get(INT_0).getSampleTime();

			if (ValidationUtil.isNull(lastDate))
			{
				printLogInfoEnable(LOG_LOAD_PROFILE_READ + METER_READING_NOT_AVAILABLE);
				return response;
			}

			// setting last read time
			response.getFirstResult().setLastReadTime(DMConvertUtil.convertCalendarToDateUTC(TIME_ZONE_GMT,
					lastDate.toGregorianCalendar()));

			// filling load profile object according its service type
			switch (meterIdentity.getServiceType())
			{
				case WATER:
				case GAS:
					fillWaterGasMeterLoadProfile(meterIdentity, latestResponse, response);
					break;
				default:
					break;
			}

		}
		catch (Exception ex)
		{
			response.setStatus(Status.ExternalError);
			response.addMessage(SENSUS_DM_COMMON_PROCESS_NC_DEFAULTEXCEPTION, MessageSeverity.Error,
					MessageLevel.None);
		}

		return response;
	}

	/**
	 * Fill water gas meter load profile.
	 * 
	 * @param meterIdentity the meter identity
	 * @param latestReadingResponse the latest reading response
	 * @param response the response
	 */
	private void fillWaterGasMeterLoadProfile(MeterIdentity meterIdentity,
			GetLatestReadingResponse latestReadingResponse, InternalResultsResponse<LoadProfile> response)
	{
		printLogInfoEnable(meterIdentity.getServiceType().toString() + LOG_LOAD_PROFILE_READ
				+ LATEST_READING_AVAIABLE_CONVERTING_TO_LOAD_PROFILE);

		// gets the current channel
		Channel channel = fetchCurrentChannel(latestReadingResponse.getNormalMeterRead().get(INT_0).getChannels());

		if (!ValidationUtil.isNull(channel))
		{
			response.getFirstResult().setLastReadValue(DMConvertUtil.convertFormattedValue(
					DMConvertUtil.convertMultiplier(channel.getValue(), channel.getMultiplier(),
							channel.getUnit(), true), channel.getUnit(), STR_FORMAT_1D, null));
		}

		printLogInfoEnable(meterIdentity.getServiceType().toString() + LOG_LOAD_PROFILE_READ + RETURNING_LOAD_PROFILE);
		printLogInfoEnable(meterIdentity.getServiceType().toString() + LOG_LOAD_PROFILE_READ
				+ response.getFirstResult());

	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Private interface:

	/**
	 * Creates the selectors.
	 * 
	 * @param fldName the fld name
	 * @param serviceType the service type
	 * @return the list
	 */
	private List<ReadingTypeSelector> createSelectors(String fldName, ServiceType serviceType)
	{

		// at this point, no need to select anything from Water, lets get the hole list of readings
		List<ReadingTypeSelector> rtSelectors = new ArrayList<ReadingTypeSelector>();

		switch (serviceType)
		{
			case ELECTRIC:

				if (fldName.equals(SNAPSHOT_READ))
				{
					rtSelectors.add(new ReadingTypeSelector());
					rtSelectors.get(INT_0).setQuantityType(QuantityType.SNAPSHOT);
				}
				else if (fldName.equals(TOU_READ))
				{
					rtSelectors.add(new ReadingTypeSelector());
					rtSelectors.get(INT_0).setTou(true);

				}

				break;

			default:
				break;
		}

		return rtSelectors;

	}

	/**
	 * Fetch current channel.
	 * 
	 * @param demandChannels the demand channels
	 * @return the channel
	 */
	private Channel fetchCurrentChannel(List<Channel> demandChannels)
	{

		if (demandChannels.size() >= 1)
		{
			return demandChannels.get(INT_0);
		}

		return null;

	}

	/**
	 * Transforms a <code>com.sensus.api.getmeterreadings.messages.GetMeterReadingsResponse</code> from
	 * Sensus's specific APIs into a DM's TOU read object (<code>com.sensus.dm.elec.device.model.TOURead</code>).
	 * 
	 * @param response the response
	 * @return a <code>com.sensus.dm.elec.device.model.TOURead</code> object
	 */
	private TOURead createTOURead(GetMeterReadingsResponse response)
	{
		printLogInfoEnable(LOG_ELEC_TOU_READING + "Creating TOURead objects.");

		// The final TOU data will be stored here.

		TOURead touRead = new TOURead(new LinkedHashSet<String>(), new ArrayList<TierData>());

		TOURead touReadTotal = new TOURead(new LinkedHashSet<String>(), new ArrayList<TierData>());

		if (!ValidationUtil.isNull(response)
				&& ValidationUtil.isNullOrEmpty(response.getTouMeterRead()))
		{
			String meterNo = null;
			MeterIdentity meterIdentity = response.getMeterIdentity();
			if (!ValidationUtil.isNull(meterIdentity))
			{
				meterNo = meterIdentity.getMeterNo();
			}

			printLogInfoEnable(new StringBuilder(LOG_ELEC_TOU_READING)
					.append(METER_READING_NOT_AVAILABLE)
					.append(meterNo)
					.append(SLASH_CONSTANT).append(meterIdentity).toString());

			return touRead;
		}

		// Iterating over the result. Processing/Creating the final response.

		if (!ValidationUtil.isNull(response) && !ValidationUtil.isNull(response.getTouMeterRead()))
		{
			for (TierRead tierRead : response.getTouMeterRead())
			{
				if (ValidationUtil.isNullOrEmpty(tierRead.getTiers()))
				{
					String meterNo = null;
					MeterIdentity meterIdentity = response.getMeterIdentity();
					if (!ValidationUtil.isNull(meterIdentity))
					{
						meterNo = meterIdentity.getMeterNo();
					}
					printLogInfoEnable(new StringBuilder(LOG_ELEC_TOU_READING)
							.append("No tiers avaiable for TierRead for the meter number ")
							.append(meterNo)
							.append(SLASH_CONSTANT).append(meterIdentity.getCustomerId()).toString());
					return touRead;
				}

				// We reach the tier values. Below, we reach the data we need to process.
				for (TierValue tierValue : tierRead.getTiers())
				{
					// tier id zero is the total
					if (tierValue.getTierId().equals(STR_NUM_ZERO))
					{
						fillTOUData(tierValue, STR_TOTAL, touReadTotal);
					}
					else
					{
						fillTOUData(tierValue, STR_TIER + tierValue.getTierId(), touRead);
					}

				}

			}
		}

		if (!ValidationUtil.isNull(touRead.getTierDatas()))
		{
			Collections.sort(touRead.getTierDatas(), new TierDataSort());

			// adding the total at returning tou
			touRead.getTierDatas().addAll(touReadTotal.getTierDatas());
		}

		return touRead;
	}

	/**
	 * Fill tou data.
	 * 
	 * @param tierValue the tier value
	 * @param tierId the tier id
	 * @param touRead the tou read
	 */
	private void fillTOUData(TierValue tierValue, String tierId, TOURead touRead)
	{

		// We try to find the tier data for the respective tier ID.
		// If not found, we create a new TierData with the tier ID.

		TierData tierData = touRead.getTierData(tierId);

		if (ValidationUtil.isNull(tierData))
		{
			tierData = new TierData(tierId);
			touRead.addTierData(tierData);
		}

		// Iterating over the tier measured quantities (here lies the needed values).

		if (!ValidationUtil.isNullOrEmpty(tierValue.getTierMeasuredQuantities()))
		{
			for (TierMeasurementValue tmv : tierValue.getTierMeasuredQuantities())
			{

				StringBuilder channelName = new StringBuilder("");

				// peak demand need to be in a separated channel
				// so first deal with quantity type, then deal with channel type
				if (tmv.getQuantityType().equals(QuantityType.PEAK_DEMAND))
				{
					channelName.append(ChannelType.PEAK_DEMAND.value());
				}
				// ignore demand time column
				else if (tmv.getType().equals(ChannelType.DEMAND_TIME))
				{
					continue;
				}
				else
				{
					channelName.append(tmv.getType().value());
				}

				channelName.append(SPACE).append(fetchFormattedUnit(tmv.getUnit()));

				// UnitOfMeasureTotal umTotal = tierData.getUnitOfMeasureTotal(um);
				UnitOfMeasureTotal umTotal = tierData.getUnitOfMeasureTotal(channelName.toString());

				if (ValidationUtil.isNull(umTotal))
				{
					tierData.addUnitOfMeasureTotal(new UnitOfMeasureTotal(channelName.toString(),
							new BigDecimal(tmv.getValue())));
				}
				else
				{
					umTotal.setTotal(umTotal.getTotal().add(new BigDecimal(tmv.getValue())));
				}

				// All distinct units of measure should go to "distinctUnitsOfMeasure" property
				// of "com.sensus.dm.elec.device.model.TOURead".
				if (!touRead.getDistinctChannelName().contains(channelName.toString()))
				{
					touRead.addDistinctChannelName(channelName.toString());
				}
			}
		}
	}

	/**
	 * Apply to u2 d array header.
	 * 
	 * @param rows the rows
	 * @param distinctChannelName the distinct channel name
	 */
	private void applyTOU2DArrayHeader(List<String[]> rows, List<String> distinctChannelName)
	{

		// Creating the headers row.

		String[] headers = new String[distinctChannelName.size() + INT_1];
		headers[INT_0] = STR_TIER_ID;

		for (int i = INT_0; i < distinctChannelName.size(); i++)
		{
			// Using "headers[i + INT_1]" because position INT_0 is for STR_TIER_ID header.
			headers[i + INT_1] = distinctChannelName.get(i);
		}

		rows.add(headers);
	}

	/**
	 * Apply to u2 d array body.
	 * 
	 * @param rows the rows
	 * @param distinctChannelName the distinct channel name
	 * @param tierDatas the tier datas
	 */
	private void applyTOU2DArrayBody(List<String[]> rows, List<String> distinctChannelName, List<TierData> tierDatas)
	{
		for (TierData tierData : tierDatas)
		{
			String[] row = new String[distinctChannelName.size() + INT_1];

			initializeArrayWithZeroStringValue(row);

			row[INT_0] = tierData.getTierId().toString();

			for (UnitOfMeasureTotal umt : tierData.getUnitOfMeasureTotals())
			{
				int umtIndex = distinctChannelName.indexOf(umt.getChannelName()) + INT_1;

				BigDecimal total = umt.getTotal();

				row[umtIndex] = total.toString();

			}

			rows.add(row);
		}
	}

	/**
	 * Transform a <code>com.sensus.dm.elec.device.model.TOURead</code> object into a 2D array
	 * containing the tier IDs and its related totals by unit type. A summary is also
	 * contained in the 2D array in last row of the 1st dimension. The 1st row of
	 * the 1st dimension (position or index 0) contains the values's unit types.
	 * Below, an example of the returned array with sample data:<br>
	 * <br>
	 * 
	 * String [[0] = String [ Tier ID, KW, Hz, KHz ] [1] = String [ 0, 223.03, 454.3, 34543.4 ]
	 * [2] = String [ 1, 323, 33, 455.6 ][3] = String [ 2, 3, 2344.56, 33.09 ] [4] = String [ Total,
	 * 549.03, 2831.86, 35032.09 ]<br>
	 * ]<br>
	 * <br>
	 * 
	 * As you can see, the position 0 contains the the values's unit types (like column
	 * names). The last position contains the totals of the rows between position 1 to
	 * position N-2.
	 * 
	 * @param touRead the <code>com.sensus.dm.elec.device.model.TOURead</code> object
	 * @return the 2D array with the final data
	 */
	private String[][] transformTOUReadInto2DArray(TOURead touRead)
	{
		List<TierData> tierDatas = touRead.getTierDatas();

		if (ValidationUtil.isNullOrEmpty(tierDatas))
		{
			return new String[][] {{STR_TIER_ID}};
		}

		List<String> distinctChannelName = touRead.getDistinctChannelNameAsList();

		if (ValidationUtil.isNullOrEmpty(distinctChannelName))
		{
			printLogInfoEnable(LOG_ELEC_TOU_READING
					+ " distinctUnitsOfMeasureSize is 0 (zero) or less but tierDatas.size() is bigger than 0 (zero).");
			return new String[][] {{STR_TIER_ID}};
		}

		List<String[]> rows = new ArrayList<String[]>();

		applyTOU2DArrayHeader(rows, distinctChannelName);

		applyTOU2DArrayBody(rows, distinctChannelName, tierDatas);

		return rows.toArray(new String[INT_0][]);
	}

	/**
	 * Initialize all positions of the given array with the string 0 ("0").
	 * 
	 * @param strArray an array of strings
	 */
	private void initializeArrayWithZeroStringValue(String[] strArray)
	{
		int strArrayLength = strArray.length;

		for (int i = 0; i < strArrayLength; i++)
		{
			strArray[i] = STR_NUM_ZERO;
		}
	}

	/**
	 * Builds the label.
	 * 
	 * @param channel the channel
	 * @param qt the qt
	 * @param serviceType the service type
	 * @return the string
	 */
	private String buildLabel(Channel channel, QuantityType qt, ServiceType serviceType)
	{

		StringBuilder result = new StringBuilder("");

		// if there is a channel id, add it on the description, because it will be used to sort the channels according
		// its ids
		if (!ValidationUtil.isNull(channel.getId()))
		{
			result.append(channel.getId()).append(STR_DASH);
		}

		switch (serviceType)
		{
			case ELECTRIC:

				// the channel unit is used only to define unique labels
				// when the label is added to the response it is removed (text after the "|" is removed).
				result.append(STR_ENERGY).append(STR_COMMA).append(SPACE)
						.append(channel.getQuantityType().toString()).append(STR_COMMA).append(SPACE)
						.append(channel.getNetFlowType().toString()).append(STR_COMMA).append(SPACE);

				// if it is a snapshot channel, data qualifier has to be used on channel name creation
				if (!ValidationUtil.isNull(qt) && QuantityType.SNAPSHOT.equals(qt))
				{
					result.append(channel.getQualifier().toString()).append(STR_COMMA).append(SPACE);
				}
				break;

			case WATER:
			case GAS:

				result.append(channel.getQuantityType().toString()).append(STR_COMMA).append(SPACE)
						.append(channel.getNetFlowType().toString()).append(STR_COMMA).append(SPACE)
						.append(channel.getQualifier().toString()).append(STR_COMMA).append(SPACE);
				break;

			default:
				break;
		}

		result.append(STR_SEG).append(channel.getSegmentation().toString()).append(STR_PIPE)
				.append(channel.getUnit().toString());

		return result.toString().replaceAll(STR_UNDERLINE, SPACE).toLowerCase();

	}

	/**
	 * Creates the interval read.
	 * 
	 * @param device the device
	 * @param uniqueChannels the unique channels
	 * @param meterRead the meter read
	 * @return the interval read
	 */
	private IntervalRead createIntervalRead(Device device, List<String> uniqueChannels, MeterRead meterRead)
	{
		IntervalRead intervalRead = new IntervalRead();
		intervalRead.setChannels(new ArrayList<HashMap<String, String>>());

		for (String channelName : uniqueChannels)
		{
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(channelName, "");
			intervalRead.getChannels().add(hm);
		}

		if (!ValidationUtil.isNull(meterRead.getSamplePoint()))
		{
			intervalRead.setReadingDate(DMConvertUtil.convertDateToDefaultUTC(meterRead.getSamplePoint()
					.toGregorianCalendar().getTime()));
		}

		return intervalRead;
	}

	/**
	 * Creates the unique channels interval read.
	 * 
	 * @param electricMeter the electric meter
	 * @param meterReadResponse the meter read response
	 * @param intervalReadList the interval read list
	 * @param endReadList the end read list
	 * @param serviceType the service type
	 * @param intervalQuantityType the interval quantity type
	 * @param isLPRead the is lp read
	 * @return the list
	 */
	private List<String> createUniqueChannelsIntervalRead(Device electricMeter,
			GetMeterReadingsResponse meterReadResponse, List<MeterRead> intervalReadList, List<MeterRead> endReadList,
			ServiceType serviceType, QuantityType intervalQuantityType, boolean isLPRead)
	{
		// rolls the interval reads list creating 2 lists: interval and end read
		// also creates the unique channels name list (according interval read)
		List<String> uniqueChannels = new ArrayList<String>();

		for (com.sensus.common.messagetypes.meterread.IntervalRead read : meterReadResponse
				.getIntervalRead())
		{
			if (!ValidationUtil.isNullOrEmpty(read.getMeterRead()))
			{
				for (MeterRead meterRead : read.getMeterRead())
				{
					// ignore the reading whether it is a quarantine read / there is no channel / LPRead does not match
					// parameter
					if (isValidRead(meterRead, isLPRead))
					{
						// split the read in 2 lists: end reading and interval reading
						splitMeterRead(meterRead, intervalReadList, endReadList, intervalQuantityType);

						// create unique channels name according channels list of meter reading
						createUniqueChannelsName(meterRead, uniqueChannels, serviceType, intervalQuantityType);

					}
				}
			}
		}

		Collections.sort(uniqueChannels);

		return uniqueChannels;
	}

	/**
	 * Split meter read.
	 * 
	 * @param meterRead the meter read
	 * @param intervalReadList the interval read list
	 * @param endReadList the end read list
	 * @param intervalQuantityType the interval quantity type
	 */
	private void splitMeterRead(MeterRead meterRead, List<MeterRead> intervalReadList, List<MeterRead> endReadList,
			QuantityType intervalQuantityType)
	{

		// splits the meter reading list into 2 lists: interval and end reading
		if (meterRead.isIsEndReading())
		{
			// and there is at least one summation channel
			for (Channel channel : meterRead.getChannels())
			{
				if (QuantityType.SUMMATION.equals(channel.getQuantityType()))
				{
					endReadList.add(meterRead);
					break;
				}
			}
		}

		// does not matter if it is a end read or not, because the end read and the interval read came inside the same
		// meter read
		// if there is at least one interval channel
		for (Channel channel : meterRead.getChannels())
		{
			if (intervalQuantityType.equals(channel.getQuantityType()))
			{
				intervalReadList.add(meterRead);
				break;
			}
		}

	}

	/**
	 * Checks if is valid read.
	 * 
	 * @param meterRead the meter read
	 * @param isLPRead the is lp read
	 * @return true, if is valid read
	 */
	private boolean isValidRead(MeterRead meterRead, boolean isLPRead)
	{
		return !ValidationUtil.isNullOrEmpty(meterRead.getChannels())
				&& meterRead.isIsLPRead().equals(isLPRead)
				&& meterRead.getMissingReadStatus().value() >= 0;
	}

	/**
	 * Creates the unique channels name.
	 * 
	 * @param meterRead the meter read
	 * @param uniqueChannels the unique channels
	 * @param serviceType the service type
	 * @param intervalQuantityType the interval quantity type
	 */
	private void createUniqueChannelsName(MeterRead meterRead, List<String> uniqueChannels, ServiceType serviceType,
			QuantityType intervalQuantityType)
	{

		// to meter reading, only quantity type Interval matters to create the channels
		for (Channel channel : meterRead.getChannels())
		{
			// defines the list of all unique channel labels
			// process only the quantity types according caller method
			if (intervalQuantityType.equals(channel.getQuantityType()))
			{
				String label = buildLabel(channel, intervalQuantityType, serviceType);
				if (!uniqueChannels.contains(label))
				{
					uniqueChannels.add(label);
				}
			}
		}
	}

	/**
	 * Creates the interval read list.
	 * 
	 * @param device the device
	 * @param intervalEndReadList the interval end read list
	 * @param uniqueChannels the unique channels
	 * @param total the total
	 * @param serviceType the service type
	 * @param intervalQuantityType the interval quantity type
	 * @param valueFormat the value format
	 * @return the list
	 */
	private List<IntervalRead> createIntervalReadList(Device device, List<MeterRead> intervalEndReadList,
			List<String> uniqueChannels, String total, ServiceType serviceType, QuantityType intervalQuantityType,
			String valueFormat)
	{

		List<IntervalRead> responseList = new ArrayList<IntervalRead>();

		for (MeterRead meterRead : intervalEndReadList)
		{
			// creates an intervalRead for each meter read
			IntervalRead intervalRead = createIntervalRead(device, uniqueChannels, meterRead);

			for (Channel channel : meterRead.getChannels())
			{
				// rolls the unique channels name list
				for (int i = 0; i < uniqueChannels.size(); i++)
				{
					String channelName = uniqueChannels.get(i);

					// we changing channel name for summation to fill the value in the correct column
					if (!ValidationUtil.isNullOrEmpty(total))
					{
						channelName = channelName.replace(STR_INTERVAL, STR_SUMMATION);
					}

					// if the current label matches the unique channels list name, then add it.
					if (channelName.equals(buildLabel(channel, intervalQuantityType, serviceType)))
					{
						HashMap<String, String> channelMap = new HashMap<String, String>();

						// add the channel and its value, removing the unit (used only to define the
						// channels name)
						channelMap.put(channelName, total +
								DMConvertUtil.convertFormattedValue(
										DMConvertUtil.convertMultiplier(channel.getValue(),
												channel.getMultiplier(), channel.getUnit(), true)
										, channel.getUnit(), valueFormat, null));

						intervalRead.getChannels().set(i, channelMap);

					}
				}
			}

			intervalRead.setChannelSize(uniqueChannels.size());
			responseList.add(intervalRead);

		}

		return responseList;

	}

	/**
	 * Merge interval reads.
	 * 
	 * @param intervalReadList the interval read list
	 * @param endReadList the end read list
	 * @return the list
	 */
	private List<IntervalRead> mergeIntervalReads(List<IntervalRead> intervalReadList, List<IntervalRead> endReadList)
	{

		List<IntervalRead> responseList = new ArrayList<IntervalRead>();

		for (IntervalRead ir : intervalReadList)
		{
			// if there is an end reading on date X, then deal with it
			if (!ValidationUtil.isNullOrEmpty(endReadList) && endReadList.contains(ir))
			{

				IntervalRead er = endReadList.get(endReadList.indexOf(ir));

				IntervalRead intervalRead = new IntervalRead();
				intervalRead.setChannels(ir.getChannels());
				intervalRead.setReadingDate(ir.getReadingDate());
				intervalRead.setChannelSize(ir.getChannelSize());

				for (HashMap<String, String> irMap : intervalRead.getChannels())
				{
					Iterator<String> it = irMap.keySet().iterator();
					while (it.hasNext())
					{
						// it is necessary to have 2 keys once the channel name for end reads has "summation" in its
						// name
						String irKey = it.next();
						String erKey = irKey.replace(STR_INTERVAL, STR_SUMMATION);

						for (HashMap<String, String> erMap : er.getChannels())
						{
							if (erMap.containsKey(erKey))
							{
								irMap.put(irKey, erMap.get(erKey) + STR_PIPE + irMap.get(irKey));
							}
						}
					}
				}
				responseList.add(intervalRead);
			}
			// otherwise just add on the main return list
			else
			{
				responseList.add(ir);
			}

		}

		return responseList;

	}

	/**
	 * Creates the interval read response.
	 * 
	 * @param meterRequest the meter request
	 * @param meterReadResponse the meter read response
	 * @param service the service
	 * @param intervalQuantityType the interval quantity type
	 * @param valueFormat the value format
	 * @param isLPRead the is lp read
	 * @return the internal results response
	 */
	private InternalResultsResponse<IntervalRead> createIntervalReadResponse(
			DeviceReadingRequest meterRequest, GetMeterReadingsResponse meterReadResponse,
			ServiceType service, QuantityType intervalQuantityType, String valueFormat, boolean isLPRead)
	{
		// this method rolls the response completely defining the unique channels name
		// then it splits interval reads into 2 lists (interval and end read)
		// then deal with this 2 lists, adding to the response object
		// finally it sort the responses list in the descending order

		printLogInfoEnable(LOG_METER_READ_RESPONSE + "Initializing create interval/end read response.");

		InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();

		if (ValidationUtil.isNull(meterReadResponse)
				|| ValidationUtil.isNullOrEmpty(meterReadResponse.getIntervalRead()))
		{
			printLogInfoEnable(LOG_METER_READ_RESPONSE + " Meter readings NOT avaiable. Returning without readings.");
			return response;
		}

		// Two lists to deal with interval and end reads
		List<MeterRead> intervalReadList = new ArrayList<MeterRead>();
		List<MeterRead> endReadList = new ArrayList<MeterRead>();

		// List containing only uniqueChannelNames
		List<String> uniqueChannels =
				createUniqueChannelsIntervalRead(meterRequest.getDevice(), meterReadResponse, intervalReadList,
						endReadList, service, intervalQuantityType, isLPRead);

		if (ValidationUtil.isNullOrEmpty(intervalReadList))
		{
			printLogInfoEnable(LOG_METER_READ_RESPONSE
					+ "Interval/end reads dont match isValidRead() criteria. Returning without readings.");
			return response;
		}

		// interval read list to add into the response
		List<IntervalRead> irList =
				createIntervalReadList(meterRequest.getDevice(), intervalReadList, uniqueChannels, "", service,
						intervalQuantityType, valueFormat);

		switch (service)
		{
			case ELECTRIC:

				List<IntervalRead> erList =
						createIntervalReadList(meterRequest.getDevice(), endReadList, uniqueChannels, "TOTAL  ",
								service, intervalQuantityType, valueFormat);
				response.addResults(mergeIntervalReads(irList, erList));
				break;

			case WATER:
			case GAS:
				response.addResults(irList);
				break;

			default:
				break;

		}

		// sorts the list to put interval and end reads at correct order
		Collections.sort(response.getResultsList(), new IntervalReadSort());

		// reverts the read meter list in order to show new values first
		// once the meter list comes at ascending order (from 12am to 12pm)
		Collections.reverse(response.getResultsList());

		printLogInfoEnable(LOG_METER_READ_RESPONSE + "Finished create interval/end read response.");
		printLogInfoEnable(LOG_METER_READ_RESPONSE + END);

		return response;
	}

	/**
	 * Creates the unique channels meter read.
	 * 
	 * @param meterReadResponse the meter read response
	 * @param meterReadList the meter read list
	 * @param quantityType the quantity type
	 * @param serviceType the service type
	 * @param isLPRead the is lp read
	 * @return the list
	 */
	private List<String> createUniqueChannelsMeterRead(GetMeterReadingsResponse meterReadResponse,
			List<MeterRead> meterReadList,
			QuantityType quantityType, ServiceType serviceType, boolean isLPRead)
	{

		List<String> uniqueChannels = new ArrayList<String>();

		for (MeterRead meterRead : meterReadResponse.getNormalMeterRead())
		{
			// ignore the reading whether it is a quarantine read / there is no channel / LPRead does not match
			// parameter
			if (isValidRead(meterRead, isLPRead))
			{
				for (Channel channel : meterRead.getChannels())
				{
					// defines the list of all unique channel labels
					// process only the quantity types according caller method
					String label = buildLabel(channel, quantityType, serviceType);
					if (!uniqueChannels.contains(label))
					{
						uniqueChannels.add(label);
					}
				}

				// add in the list only good reads
				meterReadList.add(meterRead);
			}
		}

		Collections.sort(uniqueChannels);

		return uniqueChannels;
	}

	/**
	 * Creates the meter read response.
	 * 
	 * @param meterRequest the meter request
	 * @param meterReadResponse the meter read response
	 * @param quantityType the quantity type
	 * @param serviceType the service type
	 * @param isLPRead the is lp read
	 * @return the internal results response
	 */
	private InternalResultsResponse<IntervalRead> createMeterReadResponse(DeviceReadingRequest meterRequest,
			GetMeterReadingsResponse meterReadResponse, QuantityType quantityType, ServiceType serviceType,
			boolean isLPRead)
	{
		printLogInfoEnable(LOG_ELEC_SNAPSHOT_READ + "Creating snapshot response...");

		InternalResultsResponse<IntervalRead> response = new InternalResultsResponse<IntervalRead>();

		if (ValidationUtil.isNull(meterReadResponse)
				|| ValidationUtil.isNullOrEmpty(meterReadResponse.getNormalMeterRead()))
		{
			printLogInfoEnable(LOG_ELEC_SNAPSHOT_READ + "Meter readings NOT avaiable.");
			return response;
		}

		List<MeterRead> meterReadList = new ArrayList<MeterRead>();

		// Overview:
		// 1: rolls the XML completely defining the unique channels name
		// 2: creates an aux list containing only the MeterRead (do not need to look to IntervalRead again, it was
		// messing up the order once channel order - at XML file - can change from one interval read to another)
		// 3: rolls the aux meter read list creating the necessary data
		// List containing only uniqueChannelNames
		List<String> uniqueChannels =
				createUniqueChannelsMeterRead(meterReadResponse, meterReadList, quantityType, serviceType, isLPRead);

		if (ValidationUtil.isNullOrEmpty(meterReadList))
		{
			printLogInfoEnable(LOG_ELEC_SNAPSHOT_READ
					+ "Meter reads dont match isValidRead() criteria. Returning without readings.");
			return response;
		}

		// reverts the read meter list in order to show new values first
		// one the meter list comes at ascending order (from 12am to 12pm)
		Collections.reverse(meterReadList);

		// rolls the meterRead list
		for (MeterRead meterRead : meterReadList)
		{
			// creates an intervalRead for each meter read
			IntervalRead intervalRead = createIntervalRead(meterRequest.getDevice(), uniqueChannels, meterRead);

			for (Channel channel : meterRead.getChannels())
			{

				// rolls the unique channels name list
				for (int i = 0; i < uniqueChannels.size(); i++)
				{
					String channelName = uniqueChannels.get(i);

					// if the current label matches the unique channels list name, then add it.
					if (channelName.equals(buildLabel(channel, quantityType, serviceType)))
					{
						HashMap<String, String> channelMap = new HashMap<String, String>();

						// add the channel and its value, removing the unit (used only to define the
						// channels name)
						channelMap.put(channelName,
								DMConvertUtil.convertFormattedValue(
										DMConvertUtil.convertMultiplier(channel.getValue(),
												channel.getMultiplier(), channel.getUnit(), true)
										, channel.getUnit(), STR_FORMAT_3D, null));

						intervalRead.getChannels().set(i, channelMap);
					}
				}

			}

			intervalRead.setChannelSize(uniqueChannels.size());
			response.getResultsList().add(intervalRead);
		}

		printLogInfoEnable(LOG_ELEC_SNAPSHOT_READ + "Creating snapshot response finished.");
		printLogInfoEnable(LOG_ELEC_SNAPSHOT_READ + END);

		return response;
	}

	/**
	 * Gets the interval read list columns.
	 * 
	 * @param request the request
	 * @param responseAllIntervalReads the response all interval reads
	 * @return the interval read list columns
	 */
	private List<CSVColumn> getIntervalReadListColumns(DeviceReadingRequest request,
			InternalResultsResponse<IntervalRead> responseAllIntervalReads)
	{
		List<CSVColumn> listColumn = new ArrayList<CSVColumn>(getCsvIntervalReadListAllColumns());

		listColumn.get(0).setWriteCellProcessor(
				new Optional(new ConvertTimeZoneDateTime(request.getDateFormat(),
						Integer.toString(Integer.parseInt(request.getDevice().getRadio()
								.getLocation().getTimeZoneInfo().getDisplayMinutes()) / SIXTY_MINUTES))));

		List<HashMap<String, String>> channels = responseAllIntervalReads.getFirstResult().getChannels();
		for (int i = 0; i < channels.size(); i++)
		{
			CSVColumn column = new CSVColumn();
			String channelHeader = channels.get(i).keySet().iterator().next();

			// Input: 2-energy, interval, reverse only, seg: na|wh
			// Output: energy, interval, reverse only, seg: na|wh
			channelHeader = channelHeader.substring(channelHeader.indexOf(STR_DASH) + 1, channelHeader.length());

			// Input: energy, interval, reverse only, seg: na|wh
			// Output: energy, interval, reverse only, seg: na
			channelHeader = channelHeader.substring(0, channelHeader.indexOf(STR_PIPE));

			column.setHeader(capitalizeFirstLetter(channelHeader, 0));
			column.setPropertyPath(PROPERTY_PATH_CHANNELS);
			column.setWriteCellProcessor(new Optional(new ConvertChannel(i)));
			listColumn.add(column);
		}
		return listColumn;
	}

	/**
	 * Fetch formatted unit.
	 * 
	 * @param unit the unit
	 * @return the string
	 */
	private String fetchFormattedUnit(UnitOfMeasure unit)
	{

		if (unit.equals(UnitOfMeasure.WH) || unit.equals(UnitOfMeasure.V_AH) || unit.equals(UnitOfMeasure.VA_RH)
				|| unit.equals(UnitOfMeasure.W) || unit.equals(UnitOfMeasure.VA) || unit.equals(UnitOfMeasure.VAR))
		{
			return KILO + unit.value();
		}

		return unit.value();
	}

	/**
	 * Prints the log info.
	 * 
	 * @param logInfo the log info
	 */
	private void printLogInfoEnable(String logInfo)
	{
		if (LOG.isInfoEnabled())
		{
			LOG.info(logInfo);
		}
	}

}
