package com.sensus.dm.common.group.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.DeviceTypeEnum;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.BaseSearch;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.group.bcf.IGroupBCF;
import com.sensus.dm.common.group.bcl.IGroupBCL;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.GroupRequest;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.group.model.response.GroupResponse;
import com.sensus.dm.common.group.model.response.InquiryGroupResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * Group BCF implementation.
 * 
 * @author QAT Brazil.
 * 
 */
public class GroupBCFImpl implements IGroupBCF
{

	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_GROUP_BCF_EXCEPTION_MSG = "sensus.dm.elec.groupbcfimpl.defaultexception";

	/** The Constant DEFAULT_GROUP_BCL_EXCEPTION_MSG. */
	private static final String DEFAULT_GROUP_BCL_EXCEPTION_MSG = "sensus.dm.elec.groupbclimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GroupBCFImpl.class);

	/** The group bcl. */
	private IGroupBCL groupBCL; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The order by validation controller. */
	private ValidationController orderByValidationController; // injected by Spring through setter

	/** The page size validation controller. */
	private ValidationController pageSizeValidationController; // injected by Spring through setter

	/** The base search validation controller. */
	private ValidationController baseSearchValidationController; // injected by Spring through setter

	/** The group validation controller. */
	private ValidationController groupValidationController; // injected by Spring through setter

	/** The device validation controller. */
	private ValidationController deviceValidationController; // injected by Spring through setter

	/** The radio validation controller. */
	private ValidationController radioValidationController; // injected by Spring through setter

	/** The device search validation controller. */
	private ValidationController deviceSearchValidationController; // injected by Spring through setter

	/** The inquiry device request validation controller. */
	private ValidationController inquiryDeviceRequestValidationController; // injected by Spring through setter

	/**
	 * Gets the device validation controller.
	 * 
	 * @return the device validation controller
	 */
	public ValidationController getDeviceValidationController()
	{
		return deviceValidationController;
	}

	/**
	 * Sets the device validation controller.
	 * 
	 * @param deviceValidationController the new device validation controller
	 */
	public void setDeviceValidationController(ValidationController deviceValidationController)
	{
		this.deviceValidationController = deviceValidationController;
	}

	/**
	 * Gets the group validation controller.
	 * 
	 * @return the group validation controller
	 */
	public ValidationController getGroupValidationController()
	{
		return groupValidationController;
	}

	/**
	 * Sets the group validation controller.
	 * 
	 * @param groupValidationController the new group validation controller
	 */
	public void setGroupValidationController(ValidationController groupValidationController)
	{
		this.groupValidationController = groupValidationController;
	}

	/**
	 * Gets the Tenant Request validation controller.
	 * 
	 * @return the Tenant Request validation controller
	 */
	public ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	/**
	 * Sets the Tenant Request validation controller.
	 * 
	 * @param tenantRequestValidationController the new Tenant Request validation controller
	 */
	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	/**
	 * Gets the order by validation controller.
	 * 
	 * @return the order by validation controller
	 */
	public ValidationController getOrderByValidationController()
	{
		return orderByValidationController;
	}

	/**
	 * Sets the order by validation controller.
	 * 
	 * @param orderByValidationController the new order by validation controller
	 */
	public void setOrderByValidationController(ValidationController orderByValidationController)
	{
		this.orderByValidationController = orderByValidationController;
	}

	/**
	 * Gets the page size validation controller.
	 * 
	 * @return the page size validation controller
	 */
	public ValidationController getPageSizeValidationController()
	{
		return pageSizeValidationController;
	}

	/**
	 * Sets the page size validation controller.
	 * 
	 * @param pageSizeValidationController the new page size validation controller
	 */
	public void setPageSizeValidationController(ValidationController pageSizeValidationController)
	{
		this.pageSizeValidationController = pageSizeValidationController;
	}

	/**
	 * Gets the base search validation controller.
	 * 
	 * @return the base search validation controller
	 */
	public ValidationController getBaseSearchValidationController()
	{
		return baseSearchValidationController;
	}

	/**
	 * Sets the base search validation controller.
	 * 
	 * @param baseSearchValidationController the new base search validation controller
	 */
	public void setBaseSearchValidationController(ValidationController baseSearchValidationController)
	{
		this.baseSearchValidationController = baseSearchValidationController;
	}

	/**
	 * Gets the radio validation controller.
	 * 
	 * @return the radioValidationController
	 */
	public ValidationController getRadioValidationController()
	{
		return radioValidationController;
	}

	/**
	 * Sets the radio validation controller.
	 * 
	 * @param radioValidationController the radioValidationController to set
	 */
	public void setRadioValidationController(ValidationController radioValidationController)
	{
		this.radioValidationController = radioValidationController;
	}

	/**
	 * Gets the device search validation controller.
	 * 
	 * @return the device search validation controller
	 */
	public ValidationController getDeviceSearchValidationController()
	{
		return deviceSearchValidationController;
	}

	/**
	 * Sets the device search validation controller.
	 * 
	 * @param deviceSearchValidationController the new device search validation controller
	 */
	public void setDeviceSearchValidationController(ValidationController deviceSearchValidationController)
	{
		this.deviceSearchValidationController = deviceSearchValidationController;
	}

	/**
	 * Gets the inquiry device request validation controller.
	 * 
	 * @return the inquiry device request validation controller
	 */
	public ValidationController getInquiryDeviceRequestValidationController()
	{
		return inquiryDeviceRequestValidationController;
	}

	/**
	 * Sets the inquiry device request validation controller.
	 * 
	 * @param inquiryDeviceRequestValidationController the new inquiry device request validation controller
	 */
	public void setInquiryDeviceRequestValidationController(
			ValidationController inquiryDeviceRequestValidationController)
	{
		this.inquiryDeviceRequestValidationController = inquiryDeviceRequestValidationController;
	}

	/**
	 * Sets the group bcl.
	 * 
	 * @param paramGroupBCL the new group bcl
	 */
	public void setGroupBCL(IGroupBCL paramGroupBCL)
	{
		groupBCL = paramGroupBCL;
	}

	/**
	 * Gets the group bcl.
	 * 
	 * @return the group bcl
	 */
	public IGroupBCL getGroupBCL()
	{
		return groupBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchAllGroups(com.sensus.dm.common.group.model.request.InquiryGroupRequest
	 * )
	 */
	@Override
	public InquiryGroupResponse fetchAllGroups(InquiryGroupRequest inquiryGroupRequest)
	{
		InquiryGroupResponse response = new InquiryGroupResponse();

		try
		{
			InternalResultsResponse<Group> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_GROUPS);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryGroupRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryGroupRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryGroupRequest);
			context.putObjectToBeValidated(BaseSearch.class.getSimpleName(),
					inquiryGroupRequest.getDeviceSearch());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context)
					&& getBaseSearchValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchAllGroups(inquiryGroupRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchGroupById(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupById(InquiryGroupRequest request)
	{
		GroupResponse response = new GroupResponse();

		try
		{
			InternalResultsResponse<Group> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_ID);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);
			context.putObjectToBeValidated(Group.class.getSimpleName(), request.getFirstGroup());

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchAllGroups(request);
				response.setGroups(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchGroupsByDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public GroupResponse fetchGroupsByDevice(DeviceRequest deviceRequest)
	{
		GroupResponse response = new GroupResponse();
		try
		{
			InternalResultsResponse<Group> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_GROUPS_BY_DEVICE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					deviceRequest);
			Device device = deviceRequest.getFirstDevice();
			context.putObjectToBeValidated(Device.class.getSimpleName(),
					device);

			if (getTenantRequestValidationController().validate(context)
					&& getDeviceValidationController().validate(context))
			{
				Radio radio = device.getRadio();
				context.putObjectToBeValidated(Radio.class.getSimpleName(), radio);

				if (getRadioValidationController().validate(context))
				{
					internalResponse = getGroupBCL().fetchGroupsByDevice(deviceRequest);
					response.setGroups(internalResponse.getResultsList());
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchGroupByName(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupByName(InquiryGroupRequest inquiryGroupRequest)
	{
		GroupResponse response = new GroupResponse();
		try
		{
			InternalResultsResponse<Group> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_NAME);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryGroupRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(), inquiryGroupRequest.getFirstGroup());

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchAllGroups(inquiryGroupRequest);
				response.setGroups(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#insertGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		try
		{
			InternalResultsResponse<Group> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_GROUP);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					groupRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(),
					groupRequest.getFirstGroup());

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().insertGroup(groupRequest);
				response.setGroups(internalResponse.getResultsList());
				response.setProcessId(groupRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#updateGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		try
		{
			InternalResultsResponse<Group> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					groupRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(),
					groupRequest.getFirstGroup());

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().updateGroup(groupRequest);
				response.setGroups(internalResponse.getResultsList());
				response.setProcessId(groupRequest.getProcessId());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.group.bcf.IGroupBCF#deleteGroup(com.sensus.dm.common.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DELETE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					groupRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(),
					groupRequest.getFirstGroup());

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().deleteGroup(groupRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#insertDeviceToGroup(com.sensus.dm.common.group.model.request.GroupRequest
	 * )
	 */
	@Override
	public DeviceResponse insertDeviceToGroup(InquiryDeviceRequest inquiryDeviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.ADD_SMP_TO_GRP);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryDeviceRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(),
					inquiryDeviceRequest.getFirstGroup());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceSearch.class.getSimpleName(),
					inquiryDeviceRequest.getDeviceSearch());
			context.putObjectToBeValidated(InquiryDeviceRequest.class.getSimpleName(), inquiryDeviceRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getInquiryDeviceRequestValidationController().validate(context))
			{
				if (!validateHanDevice(response, internalResponse, inquiryDeviceRequest, context))
				{
					return response;
				}

				internalResponse = getGroupBCL().insertDeviceToGroup(inquiryDeviceRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#insertDeviceFromFileToGroup(com.sensus.dm.common.group.model.request
	 * .GroupRequest)
	 */
	@Override
	public GroupResponse insertDeviceFromFileToGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.ADD_SM_FROM_FILE_TO_GRP);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					groupRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(),
					groupRequest.getFirstGroup());

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().insertDeviceFromFileToGroup(groupRequest);
				SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
						DEFAULT_GROUP_BCL_EXCEPTION_MSG);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#deleteDeviceFromGroup(com.sensus.dm.common.group.model.request.GroupRequest
	 * )
	 */
	@Override
	public DeviceResponse deleteDeviceFromGroup(InquiryDeviceRequest inquiryDeviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.DEL_SMP_FROM_GRP);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryDeviceRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(),
					inquiryDeviceRequest.getFirstGroup());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceSearch.class.getSimpleName(),
					inquiryDeviceRequest.getDeviceSearch());
			context.putObjectToBeValidated(InquiryDeviceRequest.class.getSimpleName(), inquiryDeviceRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getInquiryDeviceRequestValidationController().validate(context))
			{
				if (!validateHanDevice(response, internalResponse, inquiryDeviceRequest, context))
				{
					return response;
				}

				internalResponse = getGroupBCL().deleteDeviceFromGroup(inquiryDeviceRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#generateFileCSV(com.sensus.dm.common.group.model.request.InquiryGroupRequest
	 * )
	 */
	@Override
	public InquiryGroupResponse generateGroupsCSV(InquiryGroupRequest inquiryGroupRequest)
	{
		InquiryGroupResponse response = new InquiryGroupResponse();
		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_GROUP_FILE_CSV);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryGroupRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryGroupRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryGroupRequest);
			context.putObjectToBeValidated(BaseSearch.class.getSimpleName(),
					inquiryGroupRequest.getDeviceSearch());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getPageSizeValidationController().validate(context)
					&& getBaseSearchValidationController().validate(context))
			{
				internalResponse = getGroupBCL().generateGroupsCSV(inquiryGroupRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchDevicesByGroup(com.sensus.dm.common.group.model.request.GroupRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchDevicesByGroup(GroupRequest groupRequest)
	{
		DeviceResponse response = new DeviceResponse();
		try
		{
			InternalResultsResponse<Device> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEVICES);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					groupRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(),
					groupRequest.getFirstGroup());

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchDevicesByGroup(groupRequest);
				response.setDevices(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.group.bcf.IGroupBCF#fetchCanGroupBeInserted(com.sensus.dm.common.group.model.request.
	 * GroupRequest
	 * )
	 */
	@Override
	public GroupResponse fetchCanGroupBeInserted(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		try
		{
			InternalResultsResponse<Boolean> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_BY_NAME);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					groupRequest);
			context.putObjectToBeValidated(Group.class.getSimpleName(),
					groupRequest.getFirstGroup());

			if (getTenantRequestValidationController().validate(context)
					&& getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchCanGroupBeInserted(groupRequest);
				response.setIsGroupNameUnique(internalResponse.getFirstResult());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Validate han device.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @param context the context
	 * @return true, if successful
	 */
	private boolean validateHanDevice(DeviceResponse response, InternalResponse internalResponse,
			InquiryDeviceRequest inquiryDeviceRequest, ValidationContext context)
	{
		if (DeviceTypeEnum.HAN_DEVICE.equals(inquiryDeviceRequest.getDeviceType())
				&& !getDeviceSearchValidationController().validate(context))
		{
			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
					context.getMessages(), false);
			return false;
		}
		return true;
	}
}
