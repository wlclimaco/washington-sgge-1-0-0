package com.sensus.dm.controller.importfile;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.common.model.Message;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.GroupTypeEnum;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.response.GroupResponse;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.controller.importfile.model.GroupModel;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;

/**
 * The Class ImportGroupAPIControler.
 */
@Controller
@RequestMapping("/group")
public class ImportGroupAPIController extends ImportFileAPIController
{

	/**
	 * The logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ImportGroupAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "ImportGroupAPIController";

	/** The Constant .METER. */
	private static final String DEVICEID = "commons.pages.device_id";

	/** The Constant FLEXNET. */
	private static final String NETWORKADDRESS = "commons.pages.network_address";

	/** The Constant UPLOAD. */
	private static final String UPLOAD = "/upload";

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/**
	 * Gets the group bcf.
	 * 
	 * @return the group bcf
	 */
	@Override
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the new group bcf
	 */
	@Override
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Upload.
	 * 
	 * @param groupModel the group model
	 * @param servletRequest the servlet request
	 * @return the group model
	 */
	@RequestMapping(value = UPLOAD, method = RequestMethod.POST)
	public GroupModel upload(GroupModel groupModel, HttpServletRequest servletRequest)
	{
		GroupResponse groupResponse = new GroupResponse();

		try
		{
			GroupRequest groupRequest = new GroupRequest();

			addUserContextToRequest(groupRequest);

			GroupTypeEnum groupTypeEnum = GroupTypeEnum.enumForValue(groupModel.getType());

			Group group = new Group(groupModel.getName(), groupModel.getDescription(), groupTypeEnum);

			// Device Type
			if (!ValidationUtil.isNullOrEmpty(groupModel.getDeviceType()))
			{
				if ((HanDeviceTypeEnum.THERMOSTAT.toString().equals(groupModel.getDeviceType()))
						|| (HanDeviceTypeEnum.IHD.toString().equals(groupModel.getDeviceType())))
				{
					group.setDeviceType(DeviceTypeEnum.HAN_DEVICE);
					group.setHanDeviceType(HanDeviceTypeEnum.valueOf(groupModel.getDeviceType()));
				}
				else
				{
					group.setDeviceType(DeviceTypeEnum.valueOf(groupModel.getDeviceType()));
				}
			}

			groupRequest.addGroup(group);

			// Type of the Import
			if (groupModel.getUploadType().equals(getText(DEVICEID, servletRequest.getLocale())))
			{
				groupRequest.setIdFileType(PropertyEnum.DEVICE_ID);
			}
			else if (groupModel.getUploadType().equals(getText(NETWORKADDRESS, servletRequest.getLocale())))
			{
				groupRequest.setIdFileType(PropertyEnum.NETWORK_ADDRESS);
			}

			// Upload File
			if (ValidationUtil.isNullOrEmpty(groupModel.getDeviceList())
					&& !ValidationUtil.isNull(groupModel.getUpload())
					&& !ValidationUtil.isNullOrEmpty(groupModel.getUpload().getOriginalFilename()))
			{
				File f = new File(FILE_NAME);
				groupModel.getUpload().transferTo(f);
				groupRequest.setIdsFile(f);

			}
			else if (!ValidationUtil.isNullOrEmpty(groupModel.getDeviceList()))
			{
				groupRequest.setUploadIds(groupModel.getDeviceList());
			}

			// Monitored
			if (!ValidationUtil.isNull(groupModel.getMonitored()))
			{
				groupRequest.setIsMonitored(groupModel.getMonitored());
			}

			// Edit Group - Group Id
			if (!ValidationUtil.isNull(groupModel.getId()))
			{

				// Old Name to Edit Group
				if (!ValidationUtil.isNullOrEmpty(groupModel.getGroupOldName()))
				{
					groupRequest.getFirstGroup().setOldName(groupModel.getGroupOldName());
				}

				groupRequest.getFirstGroup().setId(groupModel.getId());

				// Edit Group - Replace or Add devices
				if (TWO.equals(groupModel.getGroupAction()))
				{
					groupRequest.setGroupReplace(true);
				}

				groupResponse = getGroupBCF().updateGroup(groupRequest);

				if (groupResponse.isOperationSuccess())
				{
					groupModel.setMessageSuccess("UPDATE");
				}

			}
			else
			{
				groupResponse = getGroupBCF().insertGroup(groupRequest);

				if (groupResponse.isOperationSuccess())
				{
					groupModel.setMessageSuccess("INSERT");
				}
			}

			groupModel.setId(groupResponse.getGroups().get(0).getId());
			groupModel.setFileName(groupRequest.getFileName());
			groupModel.setName(groupRequest.getFirstGroup().getName());
			groupModel.setProcessId(groupRequest.getProcessId());

			if (!groupResponse.isOperationSuccess())
			{
				for (Message msg : groupResponse.getMessageList())
				{
					groupModel.setMessageError(msg.getText());
				}
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, groupResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return groupModel;
	}
}
