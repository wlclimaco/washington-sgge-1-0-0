package com.sensus.mlc.wui.systemsettings.ecomode.action;

import java.io.File;
import java.util.ArrayList;

import com.sensus.common.model.Message;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.ecomode.bcf.IEcoModeBCF;
import com.sensus.mlc.ecomode.model.request.InquiryEcoModeRequest;
import com.sensus.mlc.ecomode.model.response.InquiryEcoModeResponse;
import com.sensus.mlc.tag.model.Tag;
import com.sensus.mlc.wui.base.action.ActionBase;

@SuppressWarnings("serial")
public class EcoModeAjaxAction extends ActionBase
{
	/** The upload file. */
	private File uploadEcoModeFile;

	/** The upload tag. */
	private String uploadTag;

	/** The message success. */
	private String messageSuccess;

	/** The message error. */
	private String messageError;

	/** The eco mode bcf. */
	private IEcoModeBCF ecoModeBCF;

	/**
	 * Import eco mode.
	 * 
	 * @return the string
	 */
	public String importEcoMode()
	{

		/** The eco mode request. */
		InquiryEcoModeRequest ecoModeRequest = new InquiryEcoModeRequest();
		ecoModeRequest.setUserContext(getUserContext());

		if (!ValidationUtil.isNull(getUploadTag()))
		{
			String[] tagIds = getUploadTag().split(",");
			ecoModeRequest.setTags(new ArrayList<Tag>());
			Tag tag;

			for (String id : tagIds)
			{
				tag = new Tag();
				tag.setId(Integer.parseInt(id.trim()));
				ecoModeRequest.getTags().add(tag);
			}

		}

		if (!ValidationUtil.isNull(getUploadEcoModeFile()))
		{
			ecoModeRequest.setEcoModeCSVImport(getUploadEcoModeFile());
		}

		InquiryEcoModeResponse response = getEcoModeBCF().importEcoModeBaselineFromFileCSV(ecoModeRequest);

		if (!ValidationUtil.isNullOrEmpty(response.getMessageList()))
		{

			if (response.isOperationSuccess())
			{
				String success = "";
				for (Message message : response.getMessageList())
				{
					success += message.getText() + ". ";

					setMessageSuccess(success);
				}
			}
			else
			{
				String error = "";
				for (Message message : response.getMessageList())
				{
					error += message.getText();
					setMessageError(error);
				}
			}

		}
		return SUCCESS;
	}

	/**
	 * @return the uploadEcoModeFile
	 */
	public File getUploadEcoModeFile()
	{
		return uploadEcoModeFile;
	}

	/**
	 * @param uploadEcoModeFile the uploadEcoModeFile to set
	 */
	public void setUploadEcoModeFile(File uploadEcoModeFile)
	{
		this.uploadEcoModeFile = uploadEcoModeFile;
	}

	/**
	 * @return the ecoModeBCF
	 */
	public IEcoModeBCF getEcoModeBCF()
	{
		return ecoModeBCF;
	}

	/**
	 * @param ecoModeBCF the ecoModeBCF to set
	 */
	public void setEcoModeBCF(IEcoModeBCF ecoModeBCF)
	{
		this.ecoModeBCF = ecoModeBCF;
	}

	/**
	 * @return the messageSuccess
	 */
	public String getMessageSuccess()
	{
		return messageSuccess;
	}

	/**
	 * @param messageSuccess the messageSuccess to set
	 */
	public void setMessageSuccess(String messageSuccess)
	{
		this.messageSuccess = messageSuccess;
	}

	/**
	 * @return the messageError
	 */
	public String getMessageError()
	{
		return messageError;
	}

	/**
	 * @param messageError the messageError to set
	 */
	public void setMessageError(String messageError)
	{
		this.messageError = messageError;
	}

	/**
	 * @return the uploadTag
	 */
	public String getUploadTag()
	{
		return uploadTag;
	}

	/**
	 * @param uploadTag the uploadTag to set
	 */
	public void setUploadTag(String uploadTag)
	{
		this.uploadTag = uploadTag;
	}

}
