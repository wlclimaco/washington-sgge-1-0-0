package com.sensus.dm.controller.importfile.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * The Class ActionModel.
 */
public class ActionModel
{

	/** The upload. */
	private MultipartFile upload;

	/** The tag ID's. */
	private String tagIds;

	/** The message error. */
	private String messageError;

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
	 * Gets the tag ID's.
	 * 
	 * @return the tagIds
	 */
	public String getTagIds()
	{
		return tagIds;
	}

	/**
	 * Sets the tag ID's.
	 * 
	 * @param tagIds the tagIds to set
	 */
	public void setTagIds(String tagIds)
	{
		this.tagIds = tagIds;
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

}
