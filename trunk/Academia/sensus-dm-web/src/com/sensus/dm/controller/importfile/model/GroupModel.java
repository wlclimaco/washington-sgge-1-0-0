package com.sensus.dm.controller.importfile.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * The Class GroupModel.
 */
public class GroupModel
{

	/** The id. */
	private Integer id;

	/** The group type. */
	private Integer type;

	/** The group name. */
	private String name;

	/** The group description. */
	private String description;

	/** The group action. */
	private Integer groupAction;

	/** The upload. */
	private MultipartFile upload;

	/** The upload type. */
	private String uploadType;

	/** The device type. */
	private String deviceType;

	/** The device list. */
	private String deviceList;

	/** The process id. */
	private Integer processId;

	/** The file name. */
	private String fileName;

	/** The group old name. */
	private String groupOldName;

	/** The message error. */
	private String messageError;

	/** The message success. */
	private String messageSuccess;

	/** The monitored. */
	private Boolean monitored;

	/**
	 * Gets the device type.
	 * 
	 * @return the device type
	 */
	public String getDeviceType()
	{
		return deviceType;
	}

	/**
	 * Sets the device type.
	 * 
	 * @param deviceType the new device type
	 */
	public void setDeviceType(String deviceType)
	{
		this.deviceType = deviceType;
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public Integer getType()
	{
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type the type to set
	 */
	public void setType(Integer type)
	{
		this.type = type;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

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
	 * @param upload the upload to set
	 */
	public void setUpload(MultipartFile upload)
	{
		this.upload = upload;
	}

	/**
	 * Gets the upload type.
	 * 
	 * @return the uploadType
	 */
	public String getUploadType()
	{
		return uploadType;
	}

	/**
	 * Sets the upload type.
	 * 
	 * @param uploadType the uploadType to set
	 */
	public void setUploadType(String uploadType)
	{
		this.uploadType = uploadType;
	}

	/**
	 * Gets the device list.
	 * 
	 * @return the deviceList
	 */
	public String getDeviceList()
	{
		return deviceList;
	}

	/**
	 * Sets the device list.
	 * 
	 * @param deviceList the deviceList to set
	 */
	public void setDeviceList(String deviceList)
	{
		this.deviceList = deviceList;
	}

	/**
	 * Gets the process id.
	 * 
	 * @return the processId
	 */
	public Integer getProcessId()
	{
		return processId;
	}

	/**
	 * Sets the process id.
	 * 
	 * @param processId the processId to set
	 */
	public void setProcessId(Integer processId)
	{
		this.processId = processId;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the fileName
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * Gets the group action.
	 * 
	 * @return the groupAction
	 */
	public Integer getGroupAction()
	{
		return groupAction;
	}

	/**
	 * Sets the group action.
	 * 
	 * @param groupAction the groupAction to set
	 */
	public void setGroupAction(Integer groupAction)
	{
		this.groupAction = groupAction;
	}

	/**
	 * Gets the group old name.
	 * 
	 * @return the groupOldName
	 */
	public String getGroupOldName()
	{
		return groupOldName;
	}

	/**
	 * Sets the group old name.
	 * 
	 * @param groupOldName the groupOldName to set
	 */
	public void setGroupOldName(String groupOldName)
	{
		this.groupOldName = groupOldName;
	}

	/**
	 * Gets the message error.
	 * 
	 * @return the messageError
	 */
	public String getMessageError()
	{
		return messageError;
	}

	/**
	 * Sets the message error.
	 * 
	 * @param messageError the messageError to set
	 */
	public void setMessageError(String messageError)
	{
		this.messageError = messageError;
	}

	/**
	 * Gets the message success.
	 * 
	 * @return the messageSuccess
	 */
	public String getMessageSuccess()
	{
		return messageSuccess;
	}

	/**
	 * Sets the message success.
	 * 
	 * @param messageSuccess the messageSuccess to set
	 */
	public void setMessageSuccess(String messageSuccess)
	{
		this.messageSuccess = messageSuccess;
	}

	/**
	 * Gets the monitored.
	 * 
	 * @return the monitored
	 */
	public Boolean getMonitored()
	{
		return monitored;
	}

	/**
	 * Sets the monitored.
	 * 
	 * @param monitored the new monitored
	 */
	public void setMonitored(Boolean monitored)
	{
		this.monitored = monitored;
	}
}
