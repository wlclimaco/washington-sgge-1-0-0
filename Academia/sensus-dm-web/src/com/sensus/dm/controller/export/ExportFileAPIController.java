package com.sensus.dm.controller.export;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;
import com.sensus.dm.common.process.bcf.IProcessCSVBCF;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.common.schedule.model.request.InquiryScheduleRequest;
import com.sensus.dm.common.schedule.model.request.ScheduleRequest;
import com.sensus.dm.common.schedule.model.response.InquiryScheduleResponse;
import com.sensus.dm.common.schedule.model.response.ScheduleResponse;
import com.sensus.dm.controller.base.BaseController;
import com.sensus.dm.elec.device.bcf.IDeviceReadingBCF;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryIntervalReadResponse;
import com.sensus.dm.elec.device.model.response.InquiryPeakDemandResponse;
import com.sensus.dm.elec.device.model.response.TOUReadResponse;
import com.sensus.dm.gas.device.bcf.IGasMeterBCF;
import com.sensus.dm.water.device.bcf.IWaterMeterBCF;
import com.sensus.dm.water.device.model.response.WaterMeterResponse;

/**
 * The Class ExportFileAPIController.
 */
@Controller
@RequestMapping("/api/export")
public class ExportFileAPIController extends BaseController
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ExportFileAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ExportFileAPIController";

	/** The Constant INSERT_CSV_PROCESS. */
	private static final String INSERT_CSV_PROCESS = "/insertCsvProcess";

	/** The Constant GENERATE_SUMMARY_CSV. */
	private static final String GENERATE_SUMMARY_CSV = "/generateSummaryCSV";

	/** The Constant GENERATE_COMMUNICATION_SUMMARY_CSV. */
	private static final String GENERATE_COMMUNICATION_SUMMARY_CSV = "/generateComunicationSummaryCSV";

	/** The Constant GENERATE_DEMAND_RESPONSE_SUMMARY_CSV. */
	private static final String GENERATE_DEMAND_RESPONSE_SUMMARY_CSV = "/generateDemandResponseCSV";

	/** The Constant GENERATE_DEMAND_RESPONSE_SETUP_SUMMARY_CSV. */
	private static final String GENERATE_DEMAND_RESPONSE_SETUP_SUMMARY_CSV = "/generateDemandResponseSetupCSV";

	/** The Constant GENERATE_IMPORT_HAN_SUMMARY_CSV. */
	private static final String GENERATE_IMPORT_HAN_SUMMARY_CSV = "/generateImportHanSummaryCSV";

	/** The Constant GENERATE_TAMPER_DETECT_SUMMARY_CSV. */
	private static final String GENERATE_TAMPER_DETECT_SUMMARY_CSV = "/generateTamperDetectSummaryCSV";

	/** The Constant GENERATE_TODAY_CSV. */
	private static final String GENERATE_TODAY_CSV = "/generateTodayCSV";

	/** The Constant GENERATE_HISTORY_CSV. */
	private static final String GENERATE_HISTORY_CSV = "/generateHistoryCSV";

	/** The Constant GENERATE_DEVICE_HISTORY_CSV. */
	private static final String GENERATE_DEVICE_HISTORY_CSV = "/generateDeviceHistoryCSV";

	/** The Constant GENERATE_GROUP_CSV. */
	private static final String GENERATE_GROUP_CSV = "/generateGroupCSV";

	/** The Constant GENERATE_DEVICES_CSV. */
	private static final String GENERATE_DEVICES_CSV = "/generateDevicesCSV";

	/** The Constant GENERATE_SCHEDULE_CSV. */
	private static final String GENERATE_SCHEDULE_CSV = "/generateScheduleCSV";

	/** The Constant GENERATE_SCHEDULE_DEVICE_CSV. */
	private static final String GENERATE_SCHEDULE_DEVICE_CSV = "/generateScheduleDeviceCSV";

	/** The Constant GENERATE_FILE_CSV_INTERVAL_READ. */
	private static final String GENERATE_FILE_CSV_INTERVAL_READ = "/generateFileCsvIntervalRead";

	/** The Constant GENERATE_FILE_CSV_LOAD_PROFILE. */
	private static final String GENERATE_FILE_CSV_LOAD_PROFILE = "/generateFileCsvLoadProfile";

	/** The Constant GENERATE_FILE_CSV_LEAK_REPORT. */
	private static final String GENERATE_FILE_CSV_LEAK_REPORT = "/generateFileCsvLeakReport";

	/** The Constant GENERATE_FILE_CSV_READ_DATA. */
	private static final String GENERATE_FILE_CSV_READ_DATA = "/generateFileCsvReadData";

	/** The Constant GENERATE_FILE_CSV_SNAPSHOT. */
	private static final String GENERATE_FILE_CSV_SNAPSHOT = "/generateFileCsvSnapshot";

	/** The Constant GENERATE_FILE_CSV_TOU_READ. */
	private static final String GENERATE_FILE_CSV_TOU_READ = "/generateFileCsvTouRead";

	/** The Constant GENERATE_WATER_DEVICES_CSV. */
	private static final String GENERATE_WATER_DEVICES_CSV = "/generateWaterDevicesCSV";

	/** The Constant GENERATE_GAS_DEVICES_CSV. */
	private static final String GENERATE_GAS_DEVICES_CSV = "/generateGasDevicesCSV";

	/** The Constant GENERATE_DEMAND_READ_CSV. */
	private static final String GENERATE_DEMAND_READ_DETAIL_CSV = "/generateDemandReadDetailCSV";

	/** The Constant GENERATE_DEMAND_READ_CSV. */
	private static final String GENERATE_DEMAND_READ_CSV = "/generateDemandReadCSV";

	/** The process CSV BCF. */
	private IProcessCSVBCF processCsvBCF;

	/** The group BCF. */
	private IGroupBCF groupBCF;

	/** The electric meter bcf. */
	private IElectricMeterBCF electricMeterBCF;

	/** The schedule BCF. */
	private IScheduleBCF scheduleBCF;

	/** The device reading bcf. */
	private IDeviceReadingBCF deviceReadingBCF;

	/** The water meter bcf. */
	private IWaterMeterBCF waterMeterBCF;

	/** The gas meter bcf. */
	private IGasMeterBCF gasMeterBCF;

	/**
	 * Gets the gas meter bcf.
	 * 
	 * @return the gasMeterBCF
	 */
	public IGasMeterBCF getGasMeterBCF()
	{
		return gasMeterBCF;
	}

	/**
	 * Sets the gas meter bcf.
	 * 
	 * @param gasMeterBCF the gasMeterBCF to set
	 */
	@Resource
	public void setGasMeterBCF(IGasMeterBCF gasMeterBCF)
	{
		this.gasMeterBCF = gasMeterBCF;
	}

	/**
	 * Gets the water meter bcf.
	 * 
	 * @return the water meter bcf
	 */
	public IWaterMeterBCF getWaterMeterBCF()
	{
		return waterMeterBCF;
	}

	/**
	 * Sets the water meter BCF.
	 * 
	 * @param waterMeterBCF the new water meter BCF
	 */
	@Resource
	public void setWaterMeterBCF(IWaterMeterBCF waterMeterBCF)
	{
		this.waterMeterBCF = waterMeterBCF;
	}

	/**
	 * Gets the process CSV BCF.
	 * 
	 * @return the process CSV BCF
	 */
	public IProcessCSVBCF getProcessCsvBCF()
	{
		return processCsvBCF;
	}

	/**
	 * Sets the process CSV BCF.
	 * 
	 * @param processCsvBCF the new process CSV BCF
	 */
	@Resource
	public void setProcessCsvBCF(IProcessCSVBCF processCsvBCF)
	{
		this.processCsvBCF = processCsvBCF;
	}

	/**
	 * Gets the group BCF.
	 * 
	 * @return the group BCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group BCF.
	 * 
	 * @param groupBCF the new group BCF
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Gets the electric meter bcf.
	 * 
	 * @return the electric meter bcf
	 */
	public IElectricMeterBCF getElectricMeterBCF()
	{
		return electricMeterBCF;
	}

	/**
	 * Sets the electric meter bcf.
	 * 
	 * @param electricMeterBCF the new electric meter bcf
	 */
	@Resource
	public void setElectricMeterBCF(IElectricMeterBCF electricMeterBCF)
	{
		this.electricMeterBCF = electricMeterBCF;
	}

	/**
	 * Gets the schedule BCF.
	 * 
	 * @return the scheduleBCF
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule BCF.
	 * 
	 * @param scheduleBCF the scheduleBCF to set
	 */
	@Resource
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the device reading BCF.
	 * 
	 * @return the device reading BCF
	 */
	public IDeviceReadingBCF getDeviceReadingBCF()
	{
		return deviceReadingBCF;
	}

	/**
	 * Sets the device reading BCF.
	 * 
	 * @param deviceReadingBCF the new device reading BCF
	 */
	@Resource
	public void setDeviceReadingBCF(IDeviceReadingBCF deviceReadingBCF)
	{
		this.deviceReadingBCF = deviceReadingBCF;
	}

	/**
	 * Generate groups csv.
	 * 
	 * @param groupRequest the group request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_GROUP_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateGroupsCSV(@RequestBody InquiryGroupRequest groupRequest)
	{

		InquiryGroupResponse response = new InquiryGroupResponse();
		try
		{
			addUserContextToRequest(groupRequest);

			response = getGroupBCF().generateGroupsCSV(groupRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

	/**
	 * Generate devices csv.
	 * 
	 * @param deviceRequest the device request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_DEVICES_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateDevicesCSV(@RequestBody InquiryDeviceRequest deviceRequest)
	{

		InquiryDeviceResponse response = new InquiryDeviceResponse();
		try
		{
			addUserContextToRequest(deviceRequest);

			response = getElectricMeterBCF().generateDevicesCSV(deviceRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate water devices csv.
	 * 
	 * @param deviceRequest the device request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_WATER_DEVICES_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateWaterDevicesCSV(@RequestBody InquiryDeviceRequest deviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();
		try
		{
			addUserContextToRequest(deviceRequest);

			response = getWaterMeterBCF().generateDevicesCSV(deviceRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate gas devices csv.
	 * 
	 * @param deviceRequest the device request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_GAS_DEVICES_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateGasDevicesCSV(@RequestBody InquiryDeviceRequest deviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();
		try
		{
			addUserContextToRequest(deviceRequest);

			response = getGasMeterBCF().generateDevicesCSV(deviceRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate demand read csv.
	 * 
	 * @param deviceRequest the device request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_DEMAND_READ_DETAIL_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateDemandReadDetailCSV(@RequestBody DeviceReadingRequest request)
	{
		InquiryPeakDemandResponse response = new InquiryPeakDemandResponse();
		try
		{
			addUserContextToRequest(request);

			response = getElectricMeterBCF().generatePeakDemandCSV(request);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate schedule csv.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_SCHEDULE_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateScheduleCSV(@RequestBody InquiryScheduleRequest scheduleRequest)
	{

		InquiryScheduleResponse response = new InquiryScheduleResponse();
		try
		{
			addUserContextToRequest(scheduleRequest);

			response = getScheduleBCF().generateFileCSV(scheduleRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate schedule device csv.
	 * 
	 * @param scheduleRequest the schedule request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_SCHEDULE_DEVICE_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateScheduleDeviceCSV(@RequestBody ScheduleRequest scheduleRequest)
	{

		ScheduleResponse response = new ScheduleResponse();
		try
		{
			addUserContextToRequest(scheduleRequest);

			response = getScheduleBCF().generateFileCSVScheduleDevice(scheduleRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate summary file csv.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_SUMMARY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateSummaryFileCSV(@RequestBody InquiryProcessRequest processRequest)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVSummary(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv communication summary.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_COMMUNICATION_SUMMARY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVCommunicationSummary(@RequestBody InquiryProcessRequest processRequest)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVCommunicationSummary(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv demand response summary.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_DEMAND_RESPONSE_SUMMARY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVDemandResponseSummary(@RequestBody InquiryProcessRequest processRequest)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVDemandResponseSummary(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv import han summary.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_IMPORT_HAN_SUMMARY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVImportHanSummary(@RequestBody InquiryProcessRequest processRequest)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVImportHanSummary(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv tamper detect summary.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_TAMPER_DETECT_SUMMARY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVTamperDetectSummary(@RequestBody InquiryProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVTamperDetectSummary(processRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv demand response setup summary.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_DEMAND_RESPONSE_SETUP_SUMMARY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVDemandResponseSetupSummary(@RequestBody InquiryProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVDemandResponseSetupSummary(processRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate today csv.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_TODAY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateTodayCSV(@RequestBody InquiryProcessRequest processRequest)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVToday(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate event history csv.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_HISTORY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateEventHistoryCSV(@RequestBody InquiryProcessRequest processRequest)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVEventHistory(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate device history csv.
	 * 
	 * @param processRequest the process request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_DEVICE_HISTORY_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateDeviceHistoryCSV(@RequestBody InquiryProcessRequest processRequest)
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			addUserContextToRequest(processRequest);

			response = getProcessCsvBCF().generateFileCSVDeviceHistory(processRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv interval read.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_FILE_CSV_INTERVAL_READ, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVIntervalRead(@RequestBody DeviceReadingRequest request)
	{

		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		try
		{
			addUserContextToRequest(request);

			response = getDeviceReadingBCF().generateFileCSVIntervalRead(request);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv load profile.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_FILE_CSV_LOAD_PROFILE, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVLoadProfile(@RequestBody DeviceReadingRequest request)
	{

		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		try
		{
			addUserContextToRequest(request);

			response = getDeviceReadingBCF().generateFileCSVLoadProfileRead(request);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv tou read.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_FILE_CSV_TOU_READ, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVTOURead(@RequestBody DeviceReadingRequest request)
	{

		TOUReadResponse response = new TOUReadResponse();
		try
		{
			addUserContextToRequest(request);

			response = getDeviceReadingBCF().generateFileCSVTOURead(request);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file csv snapshot.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_FILE_CSV_SNAPSHOT, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVSnapshot(@RequestBody DeviceReadingRequest request)
	{

		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		try
		{
			addUserContextToRequest(request);

			response = getDeviceReadingBCF().generateFileCSVSnapshot(request);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;
	}

	/**
	 * Generate file CSV read data.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_FILE_CSV_READ_DATA, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVReadData(@RequestBody DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();

		try
		{
			addUserContextToRequest(request);

			response = getDeviceReadingBCF().generateFileCSVWaterGasDataRead(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Generate file csv demand read.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_DEMAND_READ_CSV, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVDemandRead(@RequestBody InquiryProcessRequest request)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			addUserContextToRequest(request);
			response = getProcessCsvBCF().generateFileCSVDemandReadSummary(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Generate file csv leak report.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = GENERATE_FILE_CSV_LEAK_REPORT, method = RequestMethod.POST)
	@ResponseBody
	public Response generateFileCSVLeakReport(@RequestBody DeviceRequest request)
	{
		WaterMeterResponse response = new WaterMeterResponse();

		try
		{
			addUserContextToRequest(request);

			response = getWaterMeterBCF().generateFileCSVLeakReport(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Insert CSV process.
	 * 
	 * @return the response
	 */
	@RequestMapping(value = INSERT_CSV_PROCESS, method = RequestMethod.POST)
	@ResponseBody
	public Response insertCsvProcess()
	{

		ProcessResponse response = new ProcessResponse();
		try
		{
			InquiryProcessRequest inquiryProcessRequest = new InquiryProcessRequest();

			addUserContextToRequest(inquiryProcessRequest);

			response = getProcessCsvBCF().insertCSVProcess(inquiryProcessRequest);

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}
		return response;

	}

}
