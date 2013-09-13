package com.sensus.dm.controller.group;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.response.Response;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.GroupResponse;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;
import com.sensus.dm.controller.base.BaseController;

/**
 * The Class GroupAPIController.
 */
@Controller
@RequestMapping("/api/group")
public class GroupAPIController extends BaseController
{
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GroupAPIController.class);

	/** The Constant CONTROLLER_EXCEPTION_MSG. */
	private static final String CONTROLLER_EXCEPTION_MSG = "GroupAPIController";

	/** The Constant CHECK_GROUP_NAME. */
	private static final String CHECK_GROUP_NAME = "checkGroupName";

	/** The Constant DELETE. */
	private static final String DELETE = "/delete";

	/** The Constant DELETE_DEVICE. */
	private static final String DELETE_DEVICE = "/deleteDevice";

	/** The Constant EDIT_GROUP. */
	private static final String EDIT_GROUP = "editGroup";

	/** The Constant FETCH. */
	private static final String FETCH = "/fetch";

	/** The Constant FETCH_ALL. */
	private static final String FETCH_ALL = "/fetchAll";

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = "groupName";

	/** The Constant GROUPS. */
	private static final String GROUPS = "groups";

	/** The Constant GROUPS_BY_DEVICE. */
	private static final String GROUPS_BY_DEVICE = "fetchGroupsByDevice";

	/** The Constant ID. */
	private static final String ID = "id";

	/** The Constant INSERT_DEVICE. */
	private static final String INSERT_DEVICE = "/insertDevice";

	/** The Constant TYPE. */
	private static final String TYPE = "type";

	/** The Constant DEVICE_TYPE. */
	private static final String DEVICE_TYPE = "deviceType";

	/** The group bcf. */
	private IGroupBCF groupBCF;

	/**
	 * Gets the group bcf.
	 * 
	 * @return the groupBCF
	 */
	public IGroupBCF getGroupBCF()
	{
		return groupBCF;
	}

	/**
	 * Sets the group bcf.
	 * 
	 * @param groupBCF the groupBCF to set
	 */
	@Resource
	public void setGroupBCF(IGroupBCF groupBCF)
	{
		this.groupBCF = groupBCF;
	}

	/**
	 * Fetch.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH_ALL, method = RequestMethod.POST)
	@ResponseBody
	public Response fetchAll(@RequestBody InquiryGroupRequest request)
	{
		InquiryGroupResponse response = new InquiryGroupResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);

			response = getGroupBCF().fetchAllGroups(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Fetch.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = FETCH, method = RequestMethod.POST)
	@ResponseBody
	public Response fetch(@RequestBody Map<String, String> request)
	{
		GroupResponse response = new GroupResponse();

		try
		{
			if (EDIT_GROUP.equals(request.get(TYPE)) || CHECK_GROUP_NAME.equals(request.get(TYPE)))
			{

				Group group = new Group();

				// Call the fetchGroupById method from BE for to load the group edit page.
				if (EDIT_GROUP.equals(request.get(TYPE)))
				{
					InquiryGroupRequest inquiryGroupRequest = new InquiryGroupRequest();

					// ADD user context to request
					addUserContextToRequest(inquiryGroupRequest);

					group.setId(Integer.parseInt(request.get(ID)));
					group.setModelAction(PersistanceActionEnum.FETCHBYID);

					if (!ValidationUtil.isNullOrEmpty(request.get(DEVICE_TYPE)))
					{
						group.setDeviceType(DeviceTypeEnum.valueOf(request.get(DEVICE_TYPE)));
					}
					inquiryGroupRequest.addGroup(group);

					return getGroupBCF().fetchGroupById(inquiryGroupRequest);
				}
				// Call the fetchCanGroupBeInserted method from BE for to check if the is unique group name.
				if (CHECK_GROUP_NAME.equals(request.get(TYPE)))
				{
					GroupRequest groupRequest = new GroupRequest();

					// ADD user context to request
					addUserContextToRequest(groupRequest);

					group.setName(request.get(GROUP_NAME));

					groupRequest.addGroup(group);

					return getGroupBCF().fetchCanGroupBeInserted(groupRequest);
				}
			}

			// Call the fetchGroupsByDevice method from BE for to load the groups list through of the Device ID.
			else if (GROUPS_BY_DEVICE.equals(request.get(TYPE)))
			{
				DeviceRequest deviceRequest = new DeviceRequest();

				// ADD user context to request
				addUserContextToRequest(deviceRequest);

				Device device = new Device(new Radio(new BigInteger(request.get(ID))));

				deviceRequest.addDevice(device);

				return getGroupBCF().fetchGroupsByDevice(deviceRequest);
			}

		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Delete groups.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	@ResponseBody
	public Response delete(@RequestBody Map<String, List<String>> request)
	{
		GroupResponse response = new GroupResponse();

		try
		{
			GroupRequest groupRequest = new GroupRequest();

			// ADD user context to request
			addUserContextToRequest(groupRequest);

			if (!ValidationUtil.isNull(request) || !ValidationUtil.isNullOrEmpty(request.get(GROUPS)))
			{
				groupRequest.setGroups(new ArrayList<Group>());

				for (String idGroup : request.get(GROUPS))
				{
					groupRequest.getGroups().add(new Group(Integer.parseInt(idGroup)));
				}
			}

			response = getGroupBCF().deleteGroup(groupRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}

	/**
	 * Insert list device to group.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = INSERT_DEVICE, method = RequestMethod.POST)
	@ResponseBody
	public Response insertDevice(@RequestBody InquiryDeviceRequest inquiryDeviceRequest)
	{
		DeviceResponse deviceResponse = new DeviceResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(inquiryDeviceRequest);

			deviceResponse = getGroupBCF().insertDeviceToGroup(inquiryDeviceRequest);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, deviceResponse, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return deviceResponse;
	}

	/**
	 * Delete device.
	 * 
	 * @param request the request
	 * @return the response
	 */
	@RequestMapping(value = DELETE_DEVICE, method = RequestMethod.POST)
	@ResponseBody
	public Response deleteDevice(@RequestBody InquiryDeviceRequest request)
	{
		DeviceResponse response = new DeviceResponse();

		try
		{
			// ADD user context to request
			addUserContextToRequest(request);

			response = getGroupBCF().deleteDeviceFromGroup(request);
		}
		catch (Exception e)
		{
			SensusInterfaceUtil.handleException(LOG, response, e, DEFAULT_EXCEPTION_MSG,
					new String[] {CONTROLLER_EXCEPTION_MSG});
		}

		return response;
	}
}