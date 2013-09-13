package com.sensus.dm.controller.importfile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sensus.common.model.Message;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.controller.importfile.model.ActionModel;
import com.sensus.dm.controller.util.DMConvertUtil;
import com.sensus.dm.elec.action.model.ImportHanDeviceAction;
import com.sensus.dm.elec.action.model.request.ActionRequest;
import com.sensus.dm.elec.action.model.response.ActionResponse;

@Controller
@RequestMapping("/action")
public class ImportActionController extends ImportFileAPIController
{
	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ImportActionController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ImportActionAPIController";

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ActionModel insertDevice(ActionModel actionModel, HttpServletRequest servletRequest)
	{

		ActionResponse actionResponse = new ActionResponse();

		try
		{

			ActionRequest actionRequest = new ActionRequest();

			// ADD user context to request
			addUserContextToRequest(actionRequest);

			if (!ValidationUtil.isNull(actionModel.getUpload()))
			{
				File f = new File(FILE_NAME);
				actionModel.getUpload().transferTo(f);
				actionRequest.setHanDevicesFile(f);
			}

			if (!ValidationUtil.isNullOrEmpty(actionModel.getTagIds()))
			{

				String[] values = actionModel.getTagIds().split(",");

				actionRequest.setTags(new ArrayList<Tag>());

				for (String value : values)
				{
					String[] valueTags = value.split("[|]");

					Tag tag = new Tag();

					if (!ValidationUtil.isNull(valueTags))
					{
						if (valueTags.length > 1)
						{
							if (!ValidationUtil.isNullOrEmpty(valueTags[1]))
							{
								tag.setId(Integer.valueOf(valueTags[1]));
							}

						}
						else
						{
							tag.setName(valueTags[0].trim());
						}
						actionRequest.getTags().add(tag);
					}

				}

			}

			actionRequest.setAction(new ImportHanDeviceAction(Boolean.TRUE, Boolean.TRUE, DMConvertUtil
					.convertDateToDefaultUTC(new Date())));

			actionResponse = getActionBCF().applyActionOnDemand(actionRequest);

			if (!actionResponse.isOperationSuccess())
			{
				for (Message msg : actionResponse.getMessageList())
				{
					actionModel.setMessageError(msg.getText());
				}
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, actionResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return actionModel;

	}
}
