package com.sensus.mlc.group.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleException;
import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.ADD_SMP_TO_GRP;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DELETE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.DEL_SMP_FROM_GRP;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_ID;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_BY_NAME;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_COUNT_LIGHTS_FROM_GROUPS;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_GROUPS_BY_ID_LIST;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_GROUPS_BY_LIGHT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.FETCH_GROUPS_BY_SMARTPOINT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.INSERT;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_INTENSITY;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.UPDATE_LIGHT_PROTECTED;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.group.bcf.IGroupBCF;
import com.sensus.mlc.group.bcl.IGroupBCL;
import com.sensus.mlc.group.model.Group;
import com.sensus.mlc.group.model.GroupOrderByEnum;
import com.sensus.mlc.group.model.request.GroupRequest;
import com.sensus.mlc.group.model.response.GroupResponse;
import com.sensus.mlc.group.model.response.InquiryGroupResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;

/**
 * The Class GroupBCFImpl.
 * 
 * @author Gustavo Aragao - QAT.
 */
public class GroupBCFImpl extends AbstractBaseBCF implements IGroupBCF
{
	/** The Constant DEFAULT_GROUP_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_GROUP_BCF_EXCEPTION_MSG = "sensus.mlc.groupbcfimpl.defaultexception";

	/** The Constant GROUP_NAME. */
	private static final String GROUP_NAME = Group.class.getSimpleName();

	/** The Constant GROUP_REQUEST_NAME. */
	private static final String GROUP_REQUEST_NAME = GroupRequest.class.getSimpleName();

	/** The Constant INQUIRY_PAGINATION_REQUEST_NAME. */
	private static final String INQUIRY_PAGINATION_REQUEST_NAME = InquiryPaginationRequest.class.getSimpleName();

	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant LIGHT_NAME. */
	private static final String LIGHT_NAME = Light.class.getSimpleName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GroupBCFImpl.class);

	/** The group bcl. */
	private IGroupBCL groupBCL; // injected by Spring through setter

	/** The group validator controller. */
	private ValidationController groupValidationController;

	/** The group request validator controller. */
	private ValidationController groupRequestValidationController;

	/** The group list validator controller. */
	private ValidationController groupListValidationController;

	/** The light validation controller. */
	private ValidationController lightValidationController;

	/** The map data validation controller. */
	private ValidationController mapDataValidationController;

	/**
	 * Sets the group bcl.
	 * 
	 * @param paramGroupBCL the new group bcl
	 */
	public void setGroupBCL(IGroupBCL paramGroupBCL)
	{
		this.groupBCL = paramGroupBCL;
	}

	/**
	 * Gets the group bcl.
	 * 
	 * @return the group bcl
	 */
	public IGroupBCL getGroupBCL()
	{
		return this.groupBCL;
	}

	/**
	 * Gets the group validation controller.
	 * 
	 * @return the group validation controller
	 */
	public ValidationController getGroupValidationController()
	{
		return this.groupValidationController;
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
	 * Gets the group request validation controller.
	 * 
	 * @return the group request validation controller
	 */
	public ValidationController getGroupRequestValidationController()
	{
		return this.groupRequestValidationController;
	}

	/**
	 * Sets the group request validation controller.
	 * 
	 * @param groupRequestValidationController the new group request validation controller
	 */
	public void setGroupRequestValidationController(ValidationController groupRequestValidationController)
	{
		this.groupRequestValidationController = groupRequestValidationController;
	}

	/**
	 * Gets the group list validation controller.
	 * 
	 * @return the group list validation controller
	 */
	public ValidationController getGroupListValidationController()
	{
		return this.groupListValidationController;
	}

	/**
	 * Sets the group list validation controller.
	 * 
	 * @param groupListValidationController the new group list validation controller
	 */
	public void setGroupListValidationController(ValidationController groupListValidationController)
	{
		this.groupListValidationController = groupListValidationController;
	}

	/**
	 * Gets the light validation controller.
	 * 
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return this.lightValidationController;
	}

	/**
	 * Sets the light validation controller.
	 * 
	 * @param lightValidationController the new light validation controller
	 */
	public void setLightValidationController(ValidationController lightValidationController)
	{
		this.lightValidationController = lightValidationController;
	}

	/**
	 * Gets the map data validation controller.
	 * 
	 * @return the map data validation controller
	 */
	public ValidationController getMapDataValidationController()
	{
		return this.mapDataValidationController;
	}

	/**
	 * Sets the map data validation controller.
	 * 
	 * @param mapDataValidationController the new map data validation controller
	 */
	public void setMapDataValidationController(ValidationController mapDataValidationController)
	{
		this.mapDataValidationController = mapDataValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#insertSmartpointToGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertSmartpointToGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), ADD_SMP_TO_GRP);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(GROUP_NAME, groupRequest.getGroup());

			if ((getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context)) &&
					getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().insertSmartPointToGroup(groupRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#insertGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse insertGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResultsResponse<Group> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), INSERT);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(GROUP_NAME, groupRequest.getGroup());

			if (getLightingControlRequestValidationController().validate(context) &&
					getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().insertGroup(groupRequest);
				response.setGroups(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#updateGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(GROUP_NAME, groupRequest.getGroup());

			if (getLightingControlRequestValidationController().validate(context) &&
					getGroupRequestValidationController().validate(context) &&
					getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().updateGroup(groupRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#deleteGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), DELETE);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context))
			{
				internalResponse = getGroupBCL().deleteGroup(groupRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#deleteSmartPointFromGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse deleteSmartPointFromGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), DEL_SMP_FROM_GRP);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(GROUP_NAME, groupRequest.getGroup());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context) &&
					getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().deleteSmartPointFromGroup(groupRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#fetchAllGroups(com.sensus.mlc.base.model.request.InquiryPaginationRequest)
	 */
	@Override
	public InquiryGroupResponse fetchAllGroups(InquiryPaginationRequest inquiryPaginationRequest)
	{
		InquiryGroupResponse response = new InquiryGroupResponse();
		InternalResultsResponse<Group> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_PAGINATION_REQUEST_NAME, inquiryPaginationRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				checkDefaultsFetchAll(inquiryPaginationRequest);
				internalResponse = getGroupBCL().fetchAllGroups(inquiryPaginationRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupById(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupById(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResultsResponse<Group> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_BY_ID);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(GROUP_NAME, groupRequest.getGroup());

			if (getLightingControlRequestValidationController().validate(context) &&
					getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchGroupById(groupRequest);
				response.setGroups(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupsByIdList(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupsByIdList(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResultsResponse<Group> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_GROUPS_BY_ID_LIST);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.GROUP_LIST.getValue(),
					groupRequest.getGroupList());

			if (getLightingControlRequestValidationController().validate(context) &&
					getGroupRequestValidationController().validate(context) &&
					getGroupListValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchGroupsByIdList(groupRequest);
				response.setGroups(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#updateLightIntensityForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateLightIntensityForGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_INTENSITY);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(GROUP_NAME, groupRequest.getGroup());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context) &&
					getGroupRequestValidationController().validate(context) &&
					getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().updateLightIntensityForGroup(groupRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupsBySmartPoint(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public GroupResponse fetchGroupsBySmartPoint(LightRequest lightRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResultsResponse<Group> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_GROUPS_BY_SMARTPOINT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchGroupsBySmartPoint(lightRequest);
				response.setGroups(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupsByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public GroupResponse fetchGroupsByLight(LightRequest lightRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_GROUPS_BY_LIGHT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(LIGHT_NAME, lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchGroupsByLight(lightRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.group.bcf.IGroupBCF#fetchGroupByName(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse fetchGroupByName(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResultsResponse<Group> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_BY_NAME);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(GROUP_NAME, groupRequest.getGroup());

			if (getLightingControlRequestValidationController().validate(context) &&
					getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchGroupByName(groupRequest);
				response.setGroups(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#updateLightProtectedForGroup(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public GroupResponse updateLightProtectedForGroup(GroupRequest groupRequest)
	{
		GroupResponse response = new GroupResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), UPDATE_LIGHT_PROTECTED);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);
			context.putObjectToBeValidated(GROUP_NAME, groupRequest.getGroup());

			if (getLightingControlRequestValidationController().validate(context) &&
					getLightSelectionRequestValidationController().validate(context) &&
					getGroupRequestValidationController().validate(context) &&
					getGroupValidationController().validate(context))
			{
				internalResponse = getGroupBCL().updateLightProtectedForGroup(groupRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.group.bcf.IGroupBCF#fetchCountLightsFromGroups(com.sensus.mlc.group.model.request.GroupRequest)
	 */
	@Override
	public InquiryLightResponse fetchCountLightsFromGroups(GroupRequest groupRequest)
	{
		InquiryLightResponse response = new InquiryLightResponse();
		InternalResultsResponse<Integer> internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), FETCH_COUNT_LIGHTS_FROM_GROUPS);
			context.putObjectToBeValidated(GROUP_REQUEST_NAME, groupRequest);

			if (getLightingControlRequestValidationController().validate(context) &&
					getGroupRequestValidationController().validate(context))
			{
				internalResponse = getGroupBCL().fetchCountLightsFromGroups(groupRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			handleException(LOG, response, ex, DEFAULT_GROUP_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Check defaults fetch all.
	 * 
	 * @param inquiryPaginationRequest the inquiry pagination request
	 */
	private void checkDefaultsFetchAll(InquiryPaginationRequest inquiryPaginationRequest)
	{
		if (!ValidationUtil.isNullOrEmpty(inquiryPaginationRequest.getSortExpressions()))
		{
			return;
		}

		SortExpression sortExpression = new SortExpression();
		sortExpression.setField(GroupOrderByEnum.NAME_COLUMN.getValue());
		sortExpression.setDirection(Direction.Ascending);
		inquiryPaginationRequest.setSortExpressions(new ArrayList<SortExpression>());
		inquiryPaginationRequest.addSortExpressions(sortExpression);
	}
}
