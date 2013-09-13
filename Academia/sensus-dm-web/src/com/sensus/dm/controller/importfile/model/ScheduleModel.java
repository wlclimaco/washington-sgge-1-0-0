package com.sensus.dm.controller.importfile.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sensus.common.model.response.Response;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.schedule.bcf.IScheduleBCF;
import com.sensus.dm.elec.action.bcf.IActionBCF;

/**
 * The Class ScheduleModel.
 */
public class ScheduleModel
{

	/** The action id. */
	private String actionId;

	/** The action id. */
	private Integer actionViewId;

	/** The action bcf. */
	private IActionBCF actionBCF;

	/** The response. */
	private Response response;

	/** The dash board monitored. */
	private Boolean dashBoardMonitored;

	/** The ends after occurences. */
	private Integer endsAfterOccurences;

	/** The end date. */
	private Date endDate;

	/** The file name. */
	private String fileName;

	/** The frequency enum. */
	private String frequencyEnum;

	/** The group list. */
	private String groupList;

	/** The han start. */
	private String hanStart;

	/** The han end. */
	private String hanEnd;

	/** The tag list. */
	private String tagList;

	/** The old name. */
	private String oldName;

	/** The id. */
	private Integer id;

	/** The pct heating. */
	private Integer pctHeating;

	/** The pct cooling. */
	private Integer pctCooling;

	/** The id groups. */
	private String idGroups;

	/** The send_now. */
	private String send_now;

	/** The action test. */
	private String actionTest;

	/** The send_later. */
	private String send_later;

	/** The import by. */
	private String importBy;

	/** The meter list. */
	private String meterList;

	/** The never ends. */
	private String checkEnd;

	/** The recent request monitor. */
	private Boolean recentRequestMonitor;

	/** The repeats every value. */
	private Integer repeatsEveryValue;

	/** The schedule bcf. */
	private IScheduleBCF scheduleBCF;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/** The scheduled date time. */
	private String scheduledEventWhen;

	/** The schedule event time. */
	private String scheduleEventTime;

	/** The scheduled event id. */
	private Integer scheduledEventId;

	/** The scheduled event description. */
	private String scheduledEventDescription;

	/** The scheduled event name. */
	private String scheduledEventName;

	/** The process id. */
	private Integer processId;

	/** The repeat. */
	private Boolean repeatCheckBox;

	/** The upload file process. */
	private String uploadFileProcess;

	/** The upload action id. */
	private Integer uploadActionId;

	/** The upload file. */
	private String uploadFile;

	/** The b monitored. */
	private Boolean bMonitored;

	/** The schedule id. */
	private Integer scheduleId;

	/** The date han. */
	private String dateHan;

	/** The time han. */
	private String timeHan;

	/** The pre_program. */
	private String pre_program;

	/** The hancheck. */
	private List<String> hancheck;

	/** The hancheck. */
	private List<String> preProgram;

	/** The send messages date. */
	private String sendMessagesDate;

	/** The send messages time. */
	private String sendMessagesTime;

	/** The action name. */
	private String actionName;

	/** The han criticality. */
	private Integer hanCriticality;

	/** The load adjustment. */
	private Integer loadAdjustment;

	/** The duty cycle. */
	private Integer dutyCycle;

	/** The offset select. */
	private String offsetSelect;

	/** The offset value. */
	private Integer offsetValue;

	/** The pct setback. */
	private Integer pctSetback;

	/** The enrollment code. */
	private Integer enrollmentCode;

	/** The duration. */
	private Integer duration;

	/** The text message han. */
	private String textMessageHan;

	/** The text message duration. */
	private Integer textMessageDuration;

	/** The demand response pre program. */
	private List<String> demandResponsePreProgram;

	/** The demand response date. */
	private String demandResponseDate;

	/** The demand response time. */
	private String demandResponseTime;

	/**
	 * DEMAND RESPONSE FIELDS.
	 */

	/** The demand response duration. */
	private Integer demandResponseDuration;

	/** The demand response when. */
	private String demandResponseWhen;

	/** The demand_response_time. */
	private String demand_response_time;

	/**
	 * 
	 * Form.
	 * 
	 * */

	private String repeatType;

	/** The repeats every value daily. */
	private String repeatsEveryValueDaily;

	/** The ends daily. */
	private String endsDaily;

	/** The ends weekday. */
	private String endsWeekday;

	/** The end every other. */
	private String endEveryOther;

	/** The ends every t. */
	private String endsEveryT;

	/** The repeat every value weekly. */
	private String repeatEveryValueWeekly;

	/** The day weekly. */
	private String dayWeekly;

	/** The ends weekly. */
	private String endsWeekly;

	/** The repeat every value monthly. */
	private String repeatEveryValueMonthly;

	/** The repeat by monthly. */
	private String repeatByMonthly;

	/** The ends monthly. */
	private String endsMonthly;

	/** The repeat every value yearly. */
	private String repeatEveryValueYearly;

	/** The ends yearly. */
	private String endsYearly;

	/** The custom text. */
	private String customText;

	/** The message error. */
	private String messageError;

	/** The message success. */
	private String messageSuccess;

	/** The occurrences daily. */
	private Integer occurrencesDaily;

	/** The date after daily. */
	private String dateAfterDaily;

	/** The occurrences weekday. */
	private Integer occurrencesWeekday;

	/** The date after weekday. */
	private String dateAfterWeekday;

	/** The occurrences every other. */
	private Integer occurrencesEveryOther;

	/** The date after every other. */
	private String dateAfterEveryOther;

	/** The occurrences every t. */
	private Integer occurrencesEveryT;

	/** The date after every t. */
	private String dateAfterEveryT;

	/** The occurrences weekly. */
	private Integer occurrencesWeekly;

	/** The date after weekly. */
	private String dateAfterWeekly;

	/** The occurrences monthly. */
	private Integer occurrencesMonthly;

	/** The date after monthly. */
	private String dateAfterMonthly;

	/** The occurrences yearly. */
	private Integer occurrencesYearly;

	/** The date after early. */
	private String dateAfterYearly;

	/** The ends every other. */
	private String endsEveryOther;

	/** The repeats every value weekly. */
	private String repeatsEveryValueWeekly;

	/** The repeats every value monthly. */
	private String repeatsEveryValueMonthly;

	/** The repeats every value yearly. */
	private String repeatsEveryValueYearly;

	/** The upload. */
	private MultipartFile upload;

	/**
	 * Gets the upload.
	 * 
	 * @return the upload
	 */
	public MultipartFile getUpload()
	{
		return upload;
	}

	/**
	 * Sets the upload.
	 * 
	 * @param upload the new upload
	 */
	public void setUpload(MultipartFile upload)
	{
		this.upload = upload;
	}

	/**
	 * Gets the action id.
	 * 
	 * @return the action id
	 */
	public String getActionId()
	{
		return actionId;
	}

	/**
	 * Sets the action id.
	 * 
	 * @param actionId the new action id
	 */
	public void setActionId(String actionId)
	{
		this.actionId = actionId;
	}

	/**
	 * Gets the action view id.
	 * 
	 * @return the action view id
	 */
	public Integer getActionViewId()
	{
		return actionViewId;
	}

	/**
	 * Sets the action view id.
	 * 
	 * @param actionViewId the new action view id
	 */
	public void setActionViewId(Integer actionViewId)
	{
		this.actionViewId = actionViewId;
	}

	/**
	 * Gets the action bcf.
	 * 
	 * @return the action bcf
	 */
	public IActionBCF getActionBCF()
	{
		return actionBCF;
	}

	/**
	 * Sets the action bcf.
	 * 
	 * @param actionBCF the new action bcf
	 */
	public void setActionBCF(IActionBCF actionBCF)
	{
		this.actionBCF = actionBCF;
	}

	/**
	 * Gets the response.
	 * 
	 * @return the response
	 */
	public Response getResponse()
	{
		return response;
	}

	/**
	 * Sets the response.
	 * 
	 * @param response the new response
	 */
	public void setResponse(Response response)
	{
		this.response = response;
	}

	/**
	 * Gets the dash board monitored.
	 * 
	 * @return the dash board monitored
	 */
	public Boolean getDashBoardMonitored()
	{
		return dashBoardMonitored;
	}

	/**
	 * Sets the dash board monitored.
	 * 
	 * @param dashBoardMonitored the new dash board monitored
	 */
	public void setDashBoardMonitored(Boolean dashBoardMonitored)
	{
		this.dashBoardMonitored = dashBoardMonitored;
	}

	/**
	 * Gets the ends after occurences.
	 * 
	 * @return the ends after occurences
	 */
	public Integer getEndsAfterOccurences()
	{
		return endsAfterOccurences;
	}

	/**
	 * Sets the ends after occurences.
	 * 
	 * @param endsAfterOccurences the new ends after occurences
	 */
	public void setEndsAfterOccurences(Integer endsAfterOccurences)
	{
		this.endsAfterOccurences = endsAfterOccurences;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the frequency enum.
	 * 
	 * @return the frequency enum
	 */
	public String getFrequencyEnum()
	{
		return frequencyEnum;
	}

	/**
	 * Sets the frequency enum.
	 * 
	 * @param frequencyEnum the new frequency enum
	 */
	public void setFrequencyEnum(String frequencyEnum)
	{
		this.frequencyEnum = frequencyEnum;
	}

	/**
	 * Gets the group list.
	 * 
	 * @return the group list
	 */
	public String getGroupList()
	{
		return groupList;
	}

	/**
	 * Sets the group list.
	 * 
	 * @param groupList the new group list
	 */
	public void setGroupList(String groupList)
	{
		this.groupList = groupList;
	}

	/**
	 * Gets the han start.
	 * 
	 * @return the han start
	 */
	public String getHanStart()
	{
		return hanStart;
	}

	/**
	 * Sets the han start.
	 * 
	 * @param hanStart the new han start
	 */
	public void setHanStart(String hanStart)
	{
		this.hanStart = hanStart;
	}

	/**
	 * Gets the han end.
	 * 
	 * @return the han end
	 */
	public String getHanEnd()
	{
		return hanEnd;
	}

	/**
	 * Sets the han end.
	 * 
	 * @param hanEnd the new han end
	 */
	public void setHanEnd(String hanEnd)
	{
		this.hanEnd = hanEnd;
	}

	/**
	 * Gets the tag list.
	 * 
	 * @return the tag list
	 */
	public String getTagList()
	{
		return tagList;
	}

	/**
	 * Sets the tag list.
	 * 
	 * @param tagList the new tag list
	 */
	public void setTagList(String tagList)
	{
		this.tagList = tagList;
	}

	/**
	 * Gets the old name.
	 * 
	 * @return the old name
	 */
	public String getOldName()
	{
		return oldName;
	}

	/**
	 * Sets the old name.
	 * 
	 * @param oldName the new old name
	 */
	public void setOldName(String oldName)
	{
		this.oldName = oldName;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the pct heating.
	 * 
	 * @return the pct heating
	 */
	public Integer getPctHeating()
	{
		return pctHeating;
	}

	/**
	 * Sets the pct heating.
	 * 
	 * @param pctHeating the new pct heating
	 */
	public void setPctHeating(Integer pctHeating)
	{
		this.pctHeating = pctHeating;
	}

	/**
	 * Gets the pct cooling.
	 * 
	 * @return the pct cooling
	 */
	public Integer getPctCooling()
	{
		return pctCooling;
	}

	/**
	 * Sets the pct cooling.
	 * 
	 * @param pctCooling the new pct cooling
	 */
	public void setPctCooling(Integer pctCooling)
	{
		this.pctCooling = pctCooling;
	}

	/**
	 * Gets the id groups.
	 * 
	 * @return the id groups
	 */
	public String getIdGroups()
	{
		return idGroups;
	}

	/**
	 * Sets the id groups.
	 * 
	 * @param idGroups the new id groups
	 */
	public void setIdGroups(String idGroups)
	{
		this.idGroups = idGroups;
	}

	/**
	 * Gets the send_now.
	 * 
	 * @return the send_now
	 */
	public String getSend_now()
	{
		return send_now;
	}

	/**
	 * Sets the send_now.
	 * 
	 * @param send_now the new send_now
	 */
	public void setSend_now(String send_now)
	{
		this.send_now = send_now;
	}

	/**
	 * Gets the action test.
	 * 
	 * @return the action test
	 */
	public String getActionTest()
	{
		return actionTest;
	}

	/**
	 * Sets the action test.
	 * 
	 * @param actionTest the new action test
	 */
	public void setActionTest(String actionTest)
	{
		this.actionTest = actionTest;
	}

	/**
	 * Gets the send_later.
	 * 
	 * @return the send_later
	 */
	public String getSend_later()
	{
		return send_later;
	}

	/**
	 * Sets the send_later.
	 * 
	 * @param send_later the new send_later
	 */
	public void setSend_later(String send_later)
	{
		this.send_later = send_later;
	}

	/**
	 * Gets the import by.
	 * 
	 * @return the import by
	 */
	public String getImportBy()
	{
		return importBy;
	}

	/**
	 * Sets the import by.
	 * 
	 * @param importBy the new import by
	 */
	public void setImportBy(String importBy)
	{
		this.importBy = importBy;
	}

	/**
	 * Gets the meter list.
	 * 
	 * @return the meter list
	 */
	public String getMeterList()
	{
		return meterList;
	}

	/**
	 * Sets the meter list.
	 * 
	 * @param meterList the new meter list
	 */
	public void setMeterList(String meterList)
	{
		this.meterList = meterList;
	}

	/**
	 * Gets the check end.
	 * 
	 * @return the check end
	 */
	public String getCheckEnd()
	{
		return checkEnd;
	}

	/**
	 * Sets the check end.
	 * 
	 * @param checkEnd the new check end
	 */
	public void setCheckEnd(String checkEnd)
	{
		this.checkEnd = checkEnd;
	}

	/**
	 * Gets the recent request monitor.
	 * 
	 * @return the recent request monitor
	 */
	public Boolean getRecentRequestMonitor()
	{
		return recentRequestMonitor;
	}

	/**
	 * Sets the recent request monitor.
	 * 
	 * @param recentRequestMonitor the new recent request monitor
	 */
	public void setRecentRequestMonitor(Boolean recentRequestMonitor)
	{
		this.recentRequestMonitor = recentRequestMonitor;
	}

	/**
	 * Gets the repeats every value.
	 * 
	 * @return the repeats every value
	 */
	public Integer getRepeatsEveryValue()
	{
		return repeatsEveryValue;
	}

	/**
	 * Sets the repeats every value.
	 * 
	 * @param repeatsEveryValue the new repeats every value
	 */
	public void setRepeatsEveryValue(Integer repeatsEveryValue)
	{
		this.repeatsEveryValue = repeatsEveryValue;
	}

	/**
	 * Gets the schedule bcf.
	 * 
	 * @return the schedule bcf
	 */
	public IScheduleBCF getScheduleBCF()
	{
		return scheduleBCF;
	}

	/**
	 * Sets the schedule bcf.
	 * 
	 * @param scheduleBCF the new schedule bcf
	 */
	public void setScheduleBCF(IScheduleBCF scheduleBCF)
	{
		this.scheduleBCF = scheduleBCF;
	}

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Gets the scheduled event when.
	 * 
	 * @return the scheduled event when
	 */
	public String getScheduledEventWhen()
	{
		return scheduledEventWhen;
	}

	/**
	 * Sets the scheduled event when.
	 * 
	 * @param scheduledEventWhen the new scheduled event when
	 */
	public void setScheduledEventWhen(String scheduledEventWhen)
	{
		this.scheduledEventWhen = scheduledEventWhen;
	}

	/**
	 * Gets the schedule event time.
	 * 
	 * @return the schedule event time
	 */
	public String getScheduleEventTime()
	{
		return scheduleEventTime;
	}

	/**
	 * Sets the schedule event time.
	 * 
	 * @param scheduleEventTime the new schedule event time
	 */
	public void setScheduleEventTime(String scheduleEventTime)
	{
		this.scheduleEventTime = scheduleEventTime;
	}

	/**
	 * Gets the scheduled event id.
	 * 
	 * @return the scheduled event id
	 */
	public Integer getScheduledEventId()
	{
		return scheduledEventId;
	}

	/**
	 * Sets the scheduled event id.
	 * 
	 * @param scheduledEventId the new scheduled event id
	 */
	public void setScheduledEventId(Integer scheduledEventId)
	{
		this.scheduledEventId = scheduledEventId;
	}

	/**
	 * Gets the scheduled event description.
	 * 
	 * @return the scheduled event description
	 */
	public String getScheduledEventDescription()
	{
		return scheduledEventDescription;
	}

	/**
	 * Sets the scheduled event description.
	 * 
	 * @param scheduledEventDescription the new scheduled event description
	 */
	public void setScheduledEventDescription(String scheduledEventDescription)
	{
		this.scheduledEventDescription = scheduledEventDescription;
	}

	/**
	 * Gets the scheduled event name.
	 * 
	 * @return the scheduled event name
	 */
	public String getScheduledEventName()
	{
		return scheduledEventName;
	}

	/**
	 * Sets the scheduled event name.
	 * 
	 * @param scheduledEventName the new scheduled event name
	 */
	public void setScheduledEventName(String scheduledEventName)
	{
		this.scheduledEventName = scheduledEventName;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the process id
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the new process id
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the repeat check box.
	 * 
	 * @return the repeat check box
	 */
	public Boolean getRepeatCheckBox()
	{
		return repeatCheckBox;
	}

	/**
	 * Sets the repeat check box.
	 * 
	 * @param repeatCheckBox the new repeat check box
	 */
	public void setRepeatCheckBox(Boolean repeatCheckBox)
	{
		if (ValidationUtil.isNull(repeatCheckBox))
		{

			this.repeatCheckBox = false;
		}
		else
		{

			this.repeatCheckBox = repeatCheckBox;
		}
	}

	/**
	 * Gets the upload file process.
	 * 
	 * @return the upload file process
	 */
	public String getUploadFileProcess()
	{
		return uploadFileProcess;
	}

	/**
	 * Sets the upload file process.
	 * 
	 * @param uploadFileProcess the new upload file process
	 */
	public void setUploadFileProcess(String uploadFileProcess)
	{
		this.uploadFileProcess = uploadFileProcess;
	}

	/**
	 * Gets the upload action id.
	 * 
	 * @return the upload action id
	 */
	public Integer getUploadActionId()
	{
		return uploadActionId;
	}

	/**
	 * Sets the upload action id.
	 * 
	 * @param uploadActionId the new upload action id
	 */
	public void setUploadActionId(Integer uploadActionId)
	{
		this.uploadActionId = uploadActionId;
	}

	/**
	 * Gets the upload file.
	 * 
	 * @return the upload file
	 */
	public String getUploadFile()
	{
		return uploadFile;
	}

	/**
	 * Sets the upload file.
	 * 
	 * @param uploadFile the new upload file
	 */
	public void setUploadFile(String uploadFile)
	{
		this.uploadFile = uploadFile;
	}

	/**
	 * Gets the b monitored.
	 * 
	 * @return the b monitored
	 */
	public Boolean getbMonitored()
	{
		return bMonitored;
	}

	/**
	 * Sets the b monitored.
	 * 
	 * @param bMonitored the new b monitored
	 */
	public void setbMonitored(Boolean bMonitored)
	{
		this.bMonitored = bMonitored;
	}

	/**
	 * Gets the schedule id.
	 * 
	 * @return the schedule id
	 */
	public Integer getScheduleId()
	{
		return scheduleId;
	}

	/**
	 * Sets the schedule id.
	 * 
	 * @param scheduleId the new schedule id
	 */
	public void setScheduleId(Integer scheduleId)
	{
		this.scheduleId = scheduleId;
	}

	/**
	 * Gets the date han.
	 * 
	 * @return the date han
	 */
	public String getDateHan()
	{
		return dateHan;
	}

	/**
	 * Sets the date han.
	 * 
	 * @param dateHan the new date han
	 */
	public void setDateHan(String dateHan)
	{
		this.dateHan = dateHan;
	}

	/**
	 * Gets the time han.
	 * 
	 * @return the time han
	 */
	public String getTimeHan()
	{
		return timeHan;
	}

	/**
	 * Sets the time han.
	 * 
	 * @param timeHan the new time han
	 */
	public void setTimeHan(String timeHan)
	{
		this.timeHan = timeHan;
	}

	/**
	 * Gets the pre_program.
	 * 
	 * @return the pre_program
	 */
	public String getPre_program()
	{
		return pre_program;
	}

	/**
	 * Sets the pre_program.
	 * 
	 * @param pre_program the new pre_program
	 */
	public void setPre_program(String pre_program)
	{
		this.pre_program = pre_program;
	}

	/**
	 * Gets the hancheck.
	 * 
	 * @return the hancheck
	 */
	public List<String> getHancheck()
	{
		return hancheck;
	}

	/**
	 * Sets the hancheck.
	 * 
	 * @param hancheck the new hancheck
	 */
	public void setHancheck(List<String> hancheck)
	{
		this.hancheck = hancheck;
	}

	/**
	 * Gets the pre program.
	 * 
	 * @return the pre program
	 */
	public List<String> getPreProgram()
	{
		return preProgram;
	}

	/**
	 * Sets the pre program.
	 * 
	 * @param preProgram the new pre program
	 */
	public void setPreProgram(List<String> preProgram)
	{
		this.preProgram = preProgram;
	}

	/**
	 * Gets the send messages date.
	 * 
	 * @return the send messages date
	 */
	public String getSendMessagesDate()
	{
		return sendMessagesDate;
	}

	/**
	 * Sets the send messages date.
	 * 
	 * @param sendMessagesDate the new send messages date
	 */
	public void setSendMessagesDate(String sendMessagesDate)
	{
		this.sendMessagesDate = sendMessagesDate;
	}

	/**
	 * Gets the send messages time.
	 * 
	 * @return the send messages time
	 */
	public String getSendMessagesTime()
	{
		return sendMessagesTime;
	}

	/**
	 * Sets the send messages time.
	 * 
	 * @param sendMessagesTime the new send messages time
	 */
	public void setSendMessagesTime(String sendMessagesTime)
	{
		this.sendMessagesTime = sendMessagesTime;
	}

	/**
	 * Gets the action name.
	 * 
	 * @return the action name
	 */
	public String getActionName()
	{
		return actionName;
	}

	/**
	 * Sets the action name.
	 * 
	 * @param actionName the new action name
	 */
	public void setActionName(String actionName)
	{
		this.actionName = actionName;
	}

	/**
	 * Gets the han criticality.
	 * 
	 * @return the han criticality
	 */
	public Integer getHanCriticality()
	{
		return hanCriticality;
	}

	/**
	 * Sets the han criticality.
	 * 
	 * @param hanCriticality the new han criticality
	 */
	public void setHanCriticality(Integer hanCriticality)
	{
		this.hanCriticality = hanCriticality;
	}

	/**
	 * Gets the load adjustment.
	 * 
	 * @return the load adjustment
	 */
	public Integer getLoadAdjustment()
	{
		return loadAdjustment;
	}

	/**
	 * Sets the load adjustment.
	 * 
	 * @param loadAdjustment the new load adjustment
	 */
	public void setLoadAdjustment(Integer loadAdjustment)
	{
		this.loadAdjustment = loadAdjustment;
	}

	/**
	 * Gets the duty cycle.
	 * 
	 * @return the duty cycle
	 */
	public Integer getDutyCycle()
	{
		return dutyCycle;
	}

	/**
	 * Sets the duty cycle.
	 * 
	 * @param dutyCycle the new duty cycle
	 */
	public void setDutyCycle(Integer dutyCycle)
	{
		this.dutyCycle = dutyCycle;
	}

	/**
	 * Gets the offset select.
	 * 
	 * @return the offset select
	 */
	public String getOffsetSelect()
	{
		return offsetSelect;
	}

	/**
	 * Sets the offset select.
	 * 
	 * @param offsetSelect the new offset select
	 */
	public void setOffsetSelect(String offsetSelect)
	{
		this.offsetSelect = offsetSelect;
	}

	/**
	 * Gets the offset value.
	 * 
	 * @return the offset value
	 */
	public Integer getOffsetValue()
	{
		return offsetValue;
	}

	/**
	 * Sets the offset value.
	 * 
	 * @param offsetValue the new offset value
	 */
	public void setOffsetValue(Integer offsetValue)
	{
		this.offsetValue = offsetValue;
	}

	/**
	 * Gets the pct setback.
	 * 
	 * @return the pct setback
	 */
	public Integer getPctSetback()
	{
		return pctSetback;
	}

	/**
	 * Sets the pct setback.
	 * 
	 * @param pctSetback the new pct setback
	 */
	public void setPctSetback(Integer pctSetback)
	{
		this.pctSetback = pctSetback;
	}

	/**
	 * Gets the enrollment code.
	 * 
	 * @return the enrollment code
	 */
	public Integer getEnrollmentCode()
	{
		return enrollmentCode;
	}

	/**
	 * Sets the enrollment code.
	 * 
	 * @param enrollmentCode the new enrollment code
	 */
	public void setEnrollmentCode(Integer enrollmentCode)
	{
		this.enrollmentCode = enrollmentCode;
	}

	/**
	 * Gets the duration.
	 * 
	 * @return the duration
	 */
	public Integer getDuration()
	{
		return duration;
	}

	/**
	 * Sets the duration.
	 * 
	 * @param duration the new duration
	 */
	public void setDuration(Integer duration)
	{
		this.duration = duration;
	}

	/**
	 * Gets the text message han.
	 * 
	 * @return the text message han
	 */
	public String getTextMessageHan()
	{
		return textMessageHan;
	}

	/**
	 * Sets the text message han.
	 * 
	 * @param textMessageHan the new text message han
	 */
	public void setTextMessageHan(String textMessageHan)
	{
		this.textMessageHan = textMessageHan;
	}

	/**
	 * Gets the text message duration.
	 * 
	 * @return the text message duration
	 */
	public Integer getTextMessageDuration()
	{
		return textMessageDuration;
	}

	/**
	 * Sets the text message duration.
	 * 
	 * @param textMessageDuration the new text message duration
	 */
	public void setTextMessageDuration(Integer textMessageDuration)
	{
		this.textMessageDuration = textMessageDuration;
	}

	/**
	 * Gets the demand response pre program.
	 * 
	 * @return the demand response pre program
	 */
	public List<String> getDemandResponsePreProgram()
	{
		return demandResponsePreProgram;
	}

	/**
	 * Sets the demand response pre program.
	 * 
	 * @param demandResponsePreProgram the new demand response pre program
	 */
	public void setDemandResponsePreProgram(List<String> demandResponsePreProgram)
	{
		this.demandResponsePreProgram = demandResponsePreProgram;
	}

	/**
	 * Gets the demand response date.
	 * 
	 * @return the demand response date
	 */
	public String getDemandResponseDate()
	{
		return demandResponseDate;
	}

	/**
	 * Sets the demand response date.
	 * 
	 * @param demandResponseDate the new demand response date
	 */
	public void setDemandResponseDate(String demandResponseDate)
	{
		this.demandResponseDate = demandResponseDate;
	}

	/**
	 * Gets the demand response time.
	 * 
	 * @return the demand response time
	 */
	public String getDemandResponseTime()
	{
		return demandResponseTime;
	}

	/**
	 * Sets the demand response time.
	 * 
	 * @param demandResponseTime the new demand response time
	 */
	public void setDemandResponseTime(String demandResponseTime)
	{
		this.demandResponseTime = demandResponseTime;
	}

	/**
	 * Gets the repeat type.
	 * 
	 * @return the repeat type
	 */
	public String getRepeatType()
	{
		return repeatType;
	}

	/**
	 * Sets the repeat type.
	 * 
	 * @param repeatType the new repeat type
	 */
	public void setRepeatType(String repeatType)
	{
		this.repeatType = repeatType;
	}

	/**
	 * Gets the repeats every value daily.
	 * 
	 * @return the repeats every value daily
	 */
	public String getRepeatsEveryValueDaily()
	{
		return repeatsEveryValueDaily;
	}

	/**
	 * Sets the repeats every value daily.
	 * 
	 * @param repeatsEveryValueDaily the new repeats every value daily
	 */
	public void setRepeatsEveryValueDaily(String repeatsEveryValueDaily)
	{
		this.repeatsEveryValueDaily = repeatsEveryValueDaily;
	}

	/**
	 * Gets the ends daily.
	 * 
	 * @return the ends daily
	 */
	public String getEndsDaily()
	{
		return endsDaily;
	}

	/**
	 * Sets the ends daily.
	 * 
	 * @param endsDaily the new ends daily
	 */
	public void setEndsDaily(String endsDaily)
	{
		this.endsDaily = endsDaily;
	}

	/**
	 * Gets the ends weekday.
	 * 
	 * @return the ends weekday
	 */
	public String getEndsWeekday()
	{
		return endsWeekday;
	}

	/**
	 * Sets the ends weekday.
	 * 
	 * @param endsWeekday the new ends weekday
	 */
	public void setEndsWeekday(String endsWeekday)
	{
		this.endsWeekday = endsWeekday;
	}

	/**
	 * Gets the end every other.
	 * 
	 * @return the end every other
	 */
	public String getEndEveryOther()
	{
		return endEveryOther;
	}

	/**
	 * Sets the end every other.
	 * 
	 * @param endEveryOther the new end every other
	 */
	public void setEndEveryOther(String endEveryOther)
	{
		this.endEveryOther = endEveryOther;
	}

	/**
	 * Gets the ends every t.
	 * 
	 * @return the ends every t
	 */
	public String getEndsEveryT()
	{
		return endsEveryT;
	}

	/**
	 * Sets the ends every t.
	 * 
	 * @param endsEveryT the new ends every t
	 */
	public void setEndsEveryT(String endsEveryT)
	{
		this.endsEveryT = endsEveryT;
	}

	/**
	 * Gets the repeat every value weekly.
	 * 
	 * @return the repeat every value weekly
	 */
	public String getRepeatEveryValueWeekly()
	{
		return repeatEveryValueWeekly;
	}

	/**
	 * Sets the repeat every value weekly.
	 * 
	 * @param repeatEveryValueWeekly the new repeat every value weekly
	 */
	public void setRepeatEveryValueWeekly(String repeatEveryValueWeekly)
	{
		this.repeatEveryValueWeekly = repeatEveryValueWeekly;
	}

	/**
	 * Gets the day weekly.
	 * 
	 * @return the day weekly
	 */
	public String getDayWeekly()
	{
		return dayWeekly;
	}

	/**
	 * Sets the day weekly.
	 * 
	 * @param dayWeekly the new day weekly
	 */
	public void setDayWeekly(String dayWeekly)
	{
		this.dayWeekly = dayWeekly;
	}

	/**
	 * Gets the ends weekly.
	 * 
	 * @return the ends weekly
	 */
	public String getEndsWeekly()
	{
		return endsWeekly;
	}

	/**
	 * Sets the ends weekly.
	 * 
	 * @param endsWeekly the new ends weekly
	 */
	public void setEndsWeekly(String endsWeekly)
	{
		this.endsWeekly = endsWeekly;
	}

	/**
	 * Gets the repeat every value monthly.
	 * 
	 * @return the repeat every value monthly
	 */
	public String getRepeatEveryValueMonthly()
	{
		return repeatEveryValueMonthly;
	}

	/**
	 * Sets the repeat every value monthly.
	 * 
	 * @param repeatEveryValueMonthly the new repeat every value monthly
	 */
	public void setRepeatEveryValueMonthly(String repeatEveryValueMonthly)
	{
		this.repeatEveryValueMonthly = repeatEveryValueMonthly;
	}

	/**
	 * Gets the repeat by monthly.
	 * 
	 * @return the repeat by monthly
	 */
	public String getRepeatByMonthly()
	{
		return repeatByMonthly;
	}

	/**
	 * Sets the repeat by monthly.
	 * 
	 * @param repeatByMonthly the new repeat by monthly
	 */
	public void setRepeatByMonthly(String repeatByMonthly)
	{
		this.repeatByMonthly = repeatByMonthly;
	}

	/**
	 * Gets the ends monthly.
	 * 
	 * @return the ends monthly
	 */
	public String getEndsMonthly()
	{
		return endsMonthly;
	}

	/**
	 * Sets the ends monthly.
	 * 
	 * @param endsMonthly the new ends monthly
	 */
	public void setEndsMonthly(String endsMonthly)
	{
		this.endsMonthly = endsMonthly;
	}

	/**
	 * Gets the repeat every value yearly.
	 * 
	 * @return the repeat every value yearly
	 */
	public String getRepeatEveryValueYearly()
	{
		return repeatEveryValueYearly;
	}

	/**
	 * Sets the repeat every value yearly.
	 * 
	 * @param repeatEveryValueYearly the new repeat every value yearly
	 */
	public void setRepeatEveryValueYearly(String repeatEveryValueYearly)
	{
		this.repeatEveryValueYearly = repeatEveryValueYearly;
	}

	/**
	 * Gets the ends yearly.
	 * 
	 * @return the ends yearly
	 */
	public String getEndsYearly()
	{
		return endsYearly;
	}

	/**
	 * Sets the ends yearly.
	 * 
	 * @param endsYearly the new ends yearly
	 */
	public void setEndsYearly(String endsYearly)
	{
		this.endsYearly = endsYearly;
	}

	/**
	 * Gets the custom text.
	 * 
	 * @return the custom text
	 */
	public String getCustomText()
	{
		return customText;
	}

	/**
	 * Sets the custom text.
	 * 
	 * @param customText the new custom text
	 */
	public void setCustomText(String customText)
	{
		this.customText = customText;
	}

	/**
	 * Gets the message error.
	 * 
	 * @return the message error
	 */
	public String getMessageError()
	{
		return messageError;
	}

	/**
	 * Sets the message error.
	 * 
	 * @param messageError the new message error
	 */
	public void setMessageError(String messageError)
	{
		this.messageError = messageError;
	}

	/**
	 * Gets the message success.
	 * 
	 * @return the message success
	 */
	public String getMessageSuccess()
	{
		return messageSuccess;
	}

	/**
	 * Sets the message success.
	 * 
	 * @param messageSuccess the new message success
	 */
	public void setMessageSuccess(String messageSuccess)
	{
		this.messageSuccess = messageSuccess;
	}

	/**
	 * Gets the occurrences daily.
	 * 
	 * @return the occurrences daily
	 */
	public Integer getOccurrencesDaily()
	{
		return occurrencesDaily;
	}

	/**
	 * Sets the occurrences daily.
	 * 
	 * @param occurrencesDaily the new occurrences daily
	 */
	public void setOccurrencesDaily(Integer occurrencesDaily)
	{
		this.occurrencesDaily = occurrencesDaily;
	}

	/**
	 * Gets the date after daily.
	 * 
	 * @return the date after daily
	 */
	public String getDateAfterDaily()
	{
		return dateAfterDaily;
	}

	/**
	 * Sets the date after daily.
	 * 
	 * @param dateAfterDaily the new date after daily
	 */
	public void setDateAfterDaily(String dateAfterDaily)
	{
		this.dateAfterDaily = dateAfterDaily;
	}

	/**
	 * Gets the occurrences weekday.
	 * 
	 * @return the occurrences weekday
	 */
	public Integer getOccurrencesWeekday()
	{
		return occurrencesWeekday;
	}

	/**
	 * Sets the occurrences weekday.
	 * 
	 * @param occurrencesWeekday the new occurrences weekday
	 */
	public void setOccurrencesWeekday(Integer occurrencesWeekday)
	{
		this.occurrencesWeekday = occurrencesWeekday;
	}

	/**
	 * Gets the date after weekday.
	 * 
	 * @return the date after weekday
	 */
	public String getDateAfterWeekday()
	{
		return dateAfterWeekday;
	}

	/**
	 * Sets the date after weekday.
	 * 
	 * @param dateAfterWeekday the new date after weekday
	 */
	public void setDateAfterWeekday(String dateAfterWeekday)
	{
		this.dateAfterWeekday = dateAfterWeekday;
	}

	/**
	 * Gets the occurrences every other.
	 * 
	 * @return the occurrences every other
	 */
	public Integer getOccurrencesEveryOther()
	{
		return occurrencesEveryOther;
	}

	/**
	 * Sets the occurrences every other.
	 * 
	 * @param occurrencesEveryOther the new occurrences every other
	 */
	public void setOccurrencesEveryOther(Integer occurrencesEveryOther)
	{
		this.occurrencesEveryOther = occurrencesEveryOther;
	}

	/**
	 * Gets the date after every other.
	 * 
	 * @return the date after every other
	 */
	public String getDateAfterEveryOther()
	{
		return dateAfterEveryOther;
	}

	/**
	 * Sets the date after every other.
	 * 
	 * @param dateAfterEveryOther the new date after every other
	 */
	public void setDateAfterEveryOther(String dateAfterEveryOther)
	{
		this.dateAfterEveryOther = dateAfterEveryOther;
	}

	/**
	 * Gets the occurrences every t.
	 * 
	 * @return the occurrences every t
	 */
	public Integer getOccurrencesEveryT()
	{
		return occurrencesEveryT;
	}

	/**
	 * Sets the occurrences every t.
	 * 
	 * @param occurrencesEveryT the new occurrences every t
	 */
	public void setOccurrencesEveryT(Integer occurrencesEveryT)
	{
		this.occurrencesEveryT = occurrencesEveryT;
	}

	/**
	 * Gets the date after every t.
	 * 
	 * @return the date after every t
	 */
	public String getDateAfterEveryT()
	{
		return dateAfterEveryT;
	}

	/**
	 * Sets the date after every t.
	 * 
	 * @param dateAfterEveryT the new date after every t
	 */
	public void setDateAfterEveryT(String dateAfterEveryT)
	{
		this.dateAfterEveryT = dateAfterEveryT;
	}

	/**
	 * Gets the occurrences weekly.
	 * 
	 * @return the occurrences weekly
	 */
	public Integer getOccurrencesWeekly()
	{
		return occurrencesWeekly;
	}

	/**
	 * Sets the occurrences weekly.
	 * 
	 * @param occurrencesWeekly the new occurrences weekly
	 */
	public void setOccurrencesWeekly(Integer occurrencesWeekly)
	{
		this.occurrencesWeekly = occurrencesWeekly;
	}

	/**
	 * Gets the date after weekly.
	 * 
	 * @return the date after weekly
	 */
	public String getDateAfterWeekly()
	{
		return dateAfterWeekly;
	}

	/**
	 * Sets the date after weekly.
	 * 
	 * @param dateAfterWeekly the new date after weekly
	 */
	public void setDateAfterWeekly(String dateAfterWeekly)
	{
		this.dateAfterWeekly = dateAfterWeekly;
	}

	/**
	 * Gets the occurrences monthly.
	 * 
	 * @return the occurrences monthly
	 */
	public Integer getOccurrencesMonthly()
	{
		return occurrencesMonthly;
	}

	/**
	 * Sets the occurrences monthly.
	 * 
	 * @param occurrencesMonthly the new occurrences monthly
	 */
	public void setOccurrencesMonthly(Integer occurrencesMonthly)
	{
		this.occurrencesMonthly = occurrencesMonthly;
	}

	/**
	 * Gets the date after monthly.
	 * 
	 * @return the date after monthly
	 */
	public String getDateAfterMonthly()
	{
		return dateAfterMonthly;
	}

	/**
	 * Sets the date after monthly.
	 * 
	 * @param dateAfterMonthly the new date after monthly
	 */
	public void setDateAfterMonthly(String dateAfterMonthly)
	{
		this.dateAfterMonthly = dateAfterMonthly;
	}

	/**
	 * Gets the occurrences yearly.
	 * 
	 * @return the occurrences yearly
	 */
	public Integer getOccurrencesYearly()
	{
		return occurrencesYearly;
	}

	/**
	 * Sets the occurrences yearly.
	 * 
	 * @param occurrencesYearly the new occurrences yearly
	 */
	public void setOccurrencesYearly(Integer occurrencesYearly)
	{
		this.occurrencesYearly = occurrencesYearly;
	}

	/**
	 * Gets the date after yearly.
	 * 
	 * @return the date after yearly
	 */
	public String getDateAfterYearly()
	{
		return dateAfterYearly;
	}

	/**
	 * Sets the date after yearly.
	 * 
	 * @param dateAfterYearly the new date after yearly
	 */
	public void setDateAfterYearly(String dateAfterYearly)
	{
		this.dateAfterYearly = dateAfterYearly;
	}

	/**
	 * Gets the ends every other.
	 * 
	 * @return the ends every other
	 */
	public String getEndsEveryOther()
	{
		return endsEveryOther;
	}

	/**
	 * Sets the ends every other.
	 * 
	 * @param endsEveryOther the new ends every other
	 */
	public void setEndsEveryOther(String endsEveryOther)
	{
		this.endsEveryOther = endsEveryOther;
	}

	/**
	 * Gets the repeats every value weekly.
	 * 
	 * @return the repeats every value weekly
	 */
	public String getRepeatsEveryValueWeekly()
	{
		return repeatsEveryValueWeekly;
	}

	/**
	 * Sets the repeats every value weekly.
	 * 
	 * @param repeatsEveryValueWeekly the new repeats every value weekly
	 */
	public void setRepeatsEveryValueWeekly(String repeatsEveryValueWeekly)
	{
		this.repeatsEveryValueWeekly = repeatsEveryValueWeekly;
	}

	/**
	 * Gets the repeats every value monthly.
	 * 
	 * @return the repeats every value monthly
	 */
	public String getRepeatsEveryValueMonthly()
	{
		return repeatsEveryValueMonthly;
	}

	/**
	 * Sets the repeats every value monthly.
	 * 
	 * @param repeatsEveryValueMonthly the new repeats every value monthly
	 */
	public void setRepeatsEveryValueMonthly(String repeatsEveryValueMonthly)
	{
		this.repeatsEveryValueMonthly = repeatsEveryValueMonthly;
	}

	/**
	 * Gets the repeats every value yearly.
	 * 
	 * @return the repeats every value yearly
	 */
	public String getRepeatsEveryValueYearly()
	{
		return repeatsEveryValueYearly;
	}

	/**
	 * Sets the repeats every value yearly.
	 * 
	 * @param repeatsEveryValueYearly the new repeats every value yearly
	 */
	public void setRepeatsEveryValueYearly(String repeatsEveryValueYearly)
	{
		this.repeatsEveryValueYearly = repeatsEveryValueYearly;
	}

	/**
	 * @return the demandResponseDuration
	 */
	public Integer getDemandResponseDuration()
	{
		return demandResponseDuration;
	}

	/**
	 * @param demandResponseDuration the demandResponseDuration to set
	 */
	public void setDemandResponseDuration(Integer demandResponseDuration)
	{
		this.demandResponseDuration = demandResponseDuration;
	}

	/**
	 * @return the demandResponseWhen
	 */
	public String getDemandResponseWhen()
	{
		return demandResponseWhen;
	}

	/**
	 * @param demandResponseWhen the demandResponseWhen to set
	 */
	public void setDemandResponseWhen(String demandResponseWhen)
	{
		this.demandResponseWhen = demandResponseWhen;
	}

	/**
	 * @return the demand_response_time
	 */
	public String getDemand_response_time()
	{
		return demand_response_time;
	}

	/**
	 * @param demand_response_time the demand_response_time to set
	 */
	public void setDemand_response_time(String demand_response_time)
	{
		this.demand_response_time = demand_response_time;
	}

}
