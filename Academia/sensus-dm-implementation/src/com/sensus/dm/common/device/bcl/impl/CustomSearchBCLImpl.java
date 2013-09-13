package com.sensus.dm.common.device.bcl.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.sensus.cbof.model.Radio;
import com.sensus.common.model.MessageInfo;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.SortExpression.Direction;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.device.bcl.ICustomSearchBCL;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.device.dac.ICustomSearchDAC;
import com.sensus.dm.common.device.model.AlarmEnum;
import com.sensus.dm.common.device.model.Column;
import com.sensus.dm.common.device.model.CustomSearch;
import com.sensus.dm.common.device.model.DeviceColumnEnum;
import com.sensus.dm.common.device.model.DeviceFilterEnum;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.SearchParameter;
import com.sensus.dm.common.device.model.ServiceTypeEnum;
import com.sensus.dm.common.device.model.request.ColumnFilterRequest;
import com.sensus.dm.common.device.model.request.CustomSearchRequest;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryCustomSearchRequest;
import com.sensus.dm.common.device.model.response.ColumnFilterResponse;
import com.sensus.dm.common.group.bcl.IGroupBCL;
import com.sensus.dm.common.group.model.Group;
import com.sensus.dm.common.group.model.request.InquiryGroupRequest;
import com.sensus.dm.common.property.bcl.IPropertyBCL;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.tag.bcl.ITagBCL;
import com.sensus.dm.common.tag.model.Tag;
import com.sensus.dm.common.tag.model.request.InquiryTagRequest;
import com.sensus.dm.common.tenant.model.DMTenant;
import com.sensus.dm.elec.device.model.ElectricMeterLifecycleStateEnum;
import com.sensus.dm.elec.device.model.HanDeviceTypeEnum;
import com.sensus.dm.elec.device.model.HanLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMLifecycleStateEnum;
import com.sensus.dm.elec.device.model.LCMTypeEnum;
import com.sensus.dm.elec.device.model.RemoteConnectStatusEnum;
import com.sensus.dm.water.device.model.WaterGasMeterStatusEnum;

/**
 * The Class CustomSearchBCLImpl.
 * 
 * @author QAT Brazil
 */
public class CustomSearchBCLImpl implements ICustomSearchBCL
{
	/** The Constant PROPERTY_NAME_FILTER_TYPE. */
	private static final String PROPERTY_NAME_FILTER_TYPE = "FILTER";

	/** The Constant PROPERTY_NAME_COLUMN_TYPE. */
	private static final String PROPERTY_NAME_COLUMN_TYPE = "COLUMN";

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant CUSTOM_SEARCH_PROVIDER. */
	private static final String CUSTOM_SEARCH_PROVIDER = "EPM.Group";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = "ORDER_BY";

	/** The Constant NAME. */
	private static final String NAME = "name";

	/** The Constant APPLICATION_USER_COLUMN. */
	private static final String APPLICATION_USER_COLUMN = "EPM.User.Column";

	/** The Constant DEL_PROPERTY_TO_CUSTOM_SEARCH_FAILED. */
	private static final String DEL_PROPERTY_TO_CUSTOM_SEARCH_FAILED =
			"sensus.epm.devicebclimpl.del.property.to.customsearch.failed";

	/** The Constant ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED. */
	private static final String ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED =
			"sensus.epm.devicebclimpl.add.property.to.customsearch.failed";

	/** The Constant FETCH_PROPERTY_TO_COLUMN_FILTER_FAILED. */
	private static final String FETCH_PROPERTY_TO_COLUMN_FILTER_FAILED =
			"sensus.epm.devicebclimpl.fetch.property.to.columnfilter.failed";

	/** The group bcl. */
	private IGroupBCL groupBCL;

	/** The tag bcl. */
	private ITagBCL tagBCL;

	/** The custom search dac. */
	private ICustomSearchDAC customSearchDAC;

	/** The property bcl. */
	private IPropertyBCL propertyBCL;

	/** The device bcl. */
	private IDeviceBCL deviceBCL;

	/**
	 * Gets the group bcl.
	 * 
	 * @return the group bcl
	 */
	public IGroupBCL getGroupBCL()
	{
		return groupBCL;
	}

	/**
	 * Sets the group bcl.
	 * 
	 * @param groupBCL the new group bcl
	 */
	public void setGroupBCL(IGroupBCL groupBCL)
	{
		this.groupBCL = groupBCL;
	}

	/**
	 * Gets the tag bcl.
	 * 
	 * @return the tag bcl
	 */
	public ITagBCL getTagBCL()
	{
		return tagBCL;
	}

	/**
	 * Sets the tag bcl.
	 * 
	 * @param tagBCL the new tag bcl
	 */
	public void setTagBCL(ITagBCL tagBCL)
	{
		this.tagBCL = tagBCL;
	}

	/**
	 * Gets the property bcl.
	 * 
	 * @return the property bcl
	 */
	public IPropertyBCL getPropertyBCL()
	{
		return propertyBCL;
	}

	/**
	 * Sets the property bcl.
	 * 
	 * @param propertyBCL the new property bcl
	 */
	public void setPropertyBCL(IPropertyBCL propertyBCL)
	{
		this.propertyBCL = propertyBCL;
	}

	/**
	 * Gets the custom search dac.
	 * 
	 * @return the custom search dac
	 */
	public ICustomSearchDAC getCustomSearchDAC()
	{
		return customSearchDAC;
	}

	/**
	 * Sets the custom search dac.
	 * 
	 * @param customSearchDAC the new custom search dac
	 */
	public void setCustomSearchDAC(ICustomSearchDAC customSearchDAC)
	{
		this.customSearchDAC = customSearchDAC;
	}

	/**
	 * Gets the device bcl.
	 * 
	 * @return the device bcl
	 */
	public IDeviceBCL getDeviceBCL()
	{
		return deviceBCL;
	}

	/**
	 * Sets the device bcl.
	 * 
	 * @param deviceBCL the new device bcl
	 */
	public void setDeviceBCL(IDeviceBCL deviceBCL)
	{
		this.deviceBCL = deviceBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcl.IDeviceBCL#fetchAllCustomSearch(com.sensus.dm.common.device.model.request.
	 * InquiryCustomSearchRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> fetchAllCustomSearch(InquiryCustomSearchRequest request)
	{
		InternalResultsResponse<CustomSearch> response = getCustomSearchDAC().fetchAllCustomSearch(request);
		if (response.isInError() || !response.hasResults() || request.getIsDashboard())
		{
			return response;
		}

		for (CustomSearch customSearch : response.getResultsList())
		{
			ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
			columnFilterRequest.setProperties(new ArrayList<Property>());
			columnFilterRequest.setPropertyProviderType(CUSTOM_SEARCH_PROVIDER + SPACE
					+ customSearch.getId());

			InternalResultsResponse<Property> propertyResponse = fetchAllColumnFilter(columnFilterRequest);
			if (propertyResponse.isInError() || !propertyResponse.hasResults())
			{
				continue;
			}

			customSearch.setListColumn(new ArrayList<Column>());
			customSearch.setListFilters(new ArrayList<DeviceFilterEnum>());
			customSearch.setSearchParameters(new ArrayList<SearchParameter>());

			for (Property property : propertyResponse.getResultsList())
			{
				if (property.getDisplayOrder() == 0)
				{
					if (PropertyEnum.SMARTPOINT_ORDER.getValue().equals(property.getPropertyName()))
					{
						continue;
					}

					if (property.getPropertyName().equals(ORDER_BY))
					{
						String[] order = property.getPropertyValue().split(SPACE);

						if (order[1].equals("ASC"))
						{
							customSearch.setSortExpression(new SortExpression(order[0], Direction.Ascending));
						}
						else
						{
							customSearch.setSortExpression(new SortExpression(order[0], Direction.Descending));
						}
					}
					else
					{
						if (ValidationUtil.isNull(customSearch.getDeviceType()))
						{
							continue;
						}
						customSearch.getSearchParameters().add(
								new SearchParameter(DeviceFilterEnum.enumForValue(property
										.getPropertyName()), property.getPropertyValue()));

					}
				}
				else
				{
					if (PropertyEnum.SMARTPOINT_COLUMN.getValue().equals(property.getPropertyName()))
					{
						generationColumn(propertyResponse.getResultsList(), customSearch.getListColumn(),
								property.getPropertyValue());
					}
					else if (PropertyEnum.SMARTPOINT_FILTER.getValue().equals(property.getPropertyName()))
					{
						customSearch.getListFilters().add(
								DeviceFilterEnum.enumForValue(property.getPropertyValue()));
					}
				}
			}

			if (!ValidationUtil.isNullOrEmpty(customSearch.getSearchParameters()) && !request.getIsDashboard())
			{
				verifyCustomSearch(customSearch, request.getServiceTypeEnum(), request.getTenant());
			}

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#insertCustomSearch(com.sensus.dm.common.device.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public InternalResultsResponse<CustomSearch> insertCustomSearch(CustomSearchRequest request)
	{
		InternalResultsResponse<Boolean> canInsertResponse = getCustomSearchDAC().canCustomSearchBeInserted(request);

		InternalResultsResponse<CustomSearch> response;

		if (!ValidationUtil.isNull(canInsertResponse.getFirstResult()) && canInsertResponse.getFirstResult())
		{
			response = getCustomSearchDAC().insertCustomSearch(request);

			if (response.getStatus().equals(Status.OperationSuccess))
			{
				PropertyRequest propertyRequest = new PropertyRequest();
				List<Property> properties = new ArrayList<Property>();

				// Add Properties
				fillPropertyList(request, response, properties);

				switch (request.getCustomSearch().getDeviceType())
				{
					case ELECTRIC_METER:
						// Add Electric Meter Properties
						fillPropertyElectricMeterList(request, response, properties);
						break;
					case HAN_DEVICE:
						// Add HanDevice Properties
						fillPropertyHanDeviceList(request, response, properties);
						break;
					case LCM:
						// Add LCM Properties
						fillPropertyLcmList(request, response, properties);
						break;
					// Add Water Properties
					case WATER_METER:
						fillPropertyWaterMeterList(request, response, properties);
						break;
					// Add Gas Properties
					case GAS_METER:
						fillPropertyGasMeterList(request, response, properties);
						break;
					default:
						break;
				}

				// Add Dates Properties
				fillPropertyDatesList(request, response, properties);

				propertyRequest.setProperties(properties);
				propertyRequest.setAlwaysInsert(true);
				propertyRequest.setUserContext(request.getUserContext());
				InternalResponse responseProperty = getPropertyBCL().upsertProperty(propertyRequest);
				response = verifyMessage(response, responseProperty);

				// save column and filter
				if (response.getStatus().equals(Status.OperationSuccess) && response.hasResults())
				{
					if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getListColumn())
							|| !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getListFilters()))
					{
						ColumnFilterRequest columnFilterRequest = new ColumnFilterRequest();
						columnFilterRequest.setProperties(new ArrayList<Property>());
						columnFilterRequest.setPropertyEnum(PropertyEnum.SMARTPOINT_COLUMN);
						columnFilterRequest.setPropertyProviderType(CUSTOM_SEARCH_PROVIDER);

						Property property = new Property();
						property.setProviderId(String.valueOf(response.getFirstResult().getId()));
						columnFilterRequest.getProperties().add(property);

						columnFilterRequest.setListColumn(response.getFirstResult().getListColumn());
						columnFilterRequest.setFilters(response.getFirstResult().getListFilters());
						columnFilterRequest.setUserContext(request.getUserContext());

						responseProperty = updateColumnFilters(columnFilterRequest);
						response = verifyMessage(response, responseProperty);
					}
				}
			}
		}
		else
		{
			response = new InternalResultsResponse<CustomSearch>();
			response.setStatus(Status.PersistenceError);
			response.addMessages(canInsertResponse.getMessageInfoList());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcl.IDeviceBCL#deleteCustomSearch(com.sensus.dm.common.device.model.request.
	 * CustomSearchRequest)
	 */
	@Override
	public InternalResponse deleteCustomSearch(CustomSearchRequest request)
	{
		InternalResponse response = getCustomSearchDAC().deleteCustomSearch(request);

		if (response.getStatus().equals(Status.OperationSuccess))
		{
			PropertyRequest propertyRequest = new PropertyRequest();
			propertyRequest.setProperties(new ArrayList<Property>());

			Property property = new Property();
			property.setProviderType(CUSTOM_SEARCH_PROVIDER);
			property.setProviderId(request.getCustomSearch().getId().toString());
			propertyRequest.getProperties().add(property);
			propertyRequest.setUserContext(request.getUserContext());

			response = getPropertyBCL().deletePropertyProvider(propertyRequest);

			if (ValidationUtil.isNull(response)
					|| !response.getStatus().equals(Status.OperationSuccess))
			{
				response.getMessageInfoList().add(
						MessageInfo.createFieldValidationError(DEL_PROPERTY_TO_CUSTOM_SEARCH_FAILED));
				response.setStatus(Status.ExceptionError);
			}

		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#updateColumnFilters(com.sensus.dm.common.device.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse updateColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		PropertyRequest propertyRequest = new PropertyRequest();
		propertyRequest.setProperties(new ArrayList<Property>());
		propertyRequest.setUserContext(columnFilterRequest.getUserContext());
		propertyRequest.setPropertyProviderType(columnFilterRequest.getPropertyProviderType());
		propertyRequest.setAlwaysInsert(true);

		InternalResponse response = deleteColumnFilter(columnFilterRequest, propertyRequest);
		if (!response.getStatus().equals(Status.OperationSuccess))
		{
			return response;
		}

		return insertColumnFilter(columnFilterRequest, propertyRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcl.IElectricMeterBCL#fetchAllColumnFilters(com.sensus.dm.common.device.model.request.
	 * ColumnFilterRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<ColumnFilterResponse> fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResultsResponse<ColumnFilterResponse> columnFilterResponse =
				new InternalResultsResponse<ColumnFilterResponse>();
		InternalResultsResponse<Property> responseProperty = null;
		ColumnFilterResponse responseColumn = null;

		ColumnFilterRequest request = new ColumnFilterRequest();
		request.setProperties(columnFilterRequest.getProperties());

		if (ValidationUtil.isNullOrEmpty(columnFilterRequest.getPropertyProviderType()))
		{
			request.setPropertyProviderType(APPLICATION_USER_COLUMN + SPACE
					+ columnFilterRequest.getUserContext().getUserId());
		}

		responseProperty = getCustomSearchDAC().fetchAllColumnFilter(request);

		if (!ValidationUtil.isNullOrEmpty(responseProperty.getResultsList()))
		{
			responseColumn = new ColumnFilterResponse();

			Column column = null;
			for (Property property : responseProperty.getResultsList())
			{
				if (property.getPropertyName().contains(PROPERTY_NAME_COLUMN_TYPE))
				{

					if (ValidationUtil.isNullOrEmpty(responseColumn.getListColumn()))
					{
						responseColumn.setListColumn(new ArrayList<Column>());
					}

					column = new Column();
					column.setColumnEnum(DeviceColumnEnum.enumForValue(property.getPropertyValue()));
					responseColumn.getListColumn().add(column);
				}
				else if (property.getPropertyName().contains(PROPERTY_NAME_FILTER_TYPE))
				{
					if (ValidationUtil.isNullOrEmpty(responseColumn.getFilters()))
					{
						responseColumn.setFilters(new ArrayList<DeviceFilterEnum>());
					}

					responseColumn.getFilters().add(
							DeviceFilterEnum.enumForValue(property.getPropertyValue()));
				}
			}
		}

		if (ValidationUtil.isNull(responseProperty)
				|| !responseProperty.getStatus().equals(Status.OperationSuccess))
		{
			columnFilterResponse.getMessageInfoList().add(
					MessageInfo.createFieldValidationError(FETCH_PROPERTY_TO_COLUMN_FILTER_FAILED));
			columnFilterResponse.setStatus(Status.ExceptionError);

			return columnFilterResponse;
		}

		if (!ValidationUtil.isNull(responseColumn))
		{
			columnFilterResponse.addResult(responseColumn);
		}

		columnFilterResponse.setStatus(Status.OperationSuccess);

		return columnFilterResponse;
	}

	/**
	 * Fetch all column filter.
	 * 
	 * @param request the request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Property> fetchAllColumnFilter(ColumnFilterRequest request)
	{
		return getCustomSearchDAC().fetchAllColumnFilter(request);
	}

	/**
	 * Generation column.
	 * 
	 * @param properties the properties
	 * @param columnList the column list
	 * @param columnName the column name
	 */
	private void generationColumn(List<Property> properties, List<Column> columnList, String columnName)
	{
		Column column = new Column(DeviceColumnEnum.enumForValue(columnName));

		for (Property property : properties)
		{
			if (PropertyEnum.SMARTPOINT_ORDER.getValue().equals(property.getPropertyName()))
			{
				column.setOrdered(Boolean.TRUE);
				columnList.add(column);
				return;
			}
		}

		columnList.add(column);
	}

	/**
	 * Verify parameters.
	 * 
	 * @param groupIds the group ids
	 * @param tagIds the tag ids
	 * @param descriptionIds the description ids
	 * @param searchParameters the search parameters
	 * @param search the search
	 */
	private void verifyParameters(List<BigInteger> groupIds, List<BigInteger> tagIds,
			List<DeviceModel> deviceModelList, List<SearchParameter> searchParameters, CustomSearch search)
	{
		for (SearchParameter param : search.getSearchParameters())
		{
			if (!ValidationUtil.isNull(param.getFilterEnum())
					&& !ValidationUtil.isNull(param.getValue()))
			{
				if (param.getFilterEnum().equals(DeviceFilterEnum.GROUP))
				{
					groupIds.add(new BigInteger(param.getValue()));
				}
				else if (param.getFilterEnum().equals(DeviceFilterEnum.TAG))
				{
					tagIds.add(new BigInteger(param.getValue()));
				}
				else if (param.getFilterEnum().equals(DeviceFilterEnum.DESCRIPTION))
				{
					deviceModelList.add(new DeviceModel(new Integer(param.getValue()), null, null));
				}
				else
				{
					searchParameters.add(param);
				}
			}
		}
	}

	/**
	 * Verify groups.
	 * 
	 * @param groupIds the group ids
	 * @param searchParameters the search parameters
	 * @param serviceTypeEnum the service type enum
	 */
	private void verifyGroups(List<BigInteger> groupIds, List<SearchParameter> searchParameters,
			ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		InquiryGroupRequest request = new InquiryGroupRequest();
		request.setSelectionPaginationIds(groupIds);
		request.setPageSize(0);
		request.setPaginationAllSelected(Boolean.FALSE);
		request.addSortExpressions(new SortExpression(NAME, Direction.Ascending));
		request.setServiceTypeEnum(serviceTypeEnum);
		request.setTenant(tenant);
		InternalResultsResponse<Group> response = getGroupBCL().fetchAllGroups(request);

		if (!response.isInError())
		{
			for (Group group : response.getResultsList())
			{
				searchParameters.add(
						new SearchParameter(group.getName(), DeviceFilterEnum.GROUP, group.getId()
								.toString()));
			}
		}
	}

	/**
	 * Verify tags.
	 * 
	 * @param tagIds the tag ids
	 * @param searchParameters the search parameters
	 * @param serviceTypeEnum the service type enum
	 */
	private void verifyTags(List<BigInteger> tagIds, List<SearchParameter> searchParameters,
			ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{
		InquiryTagRequest request = new InquiryTagRequest();
		request.setSelectionPaginationIds(tagIds);
		request.setPageSize(0);
		request.setPaginationAllSelected(Boolean.FALSE);
		request.addSortExpressions(new SortExpression(NAME, Direction.Ascending));
		request.setServiceTypeEnum(serviceTypeEnum);
		request.setTenant(tenant);

		InternalResultsResponse<Tag> response = getTagBCL().fetchAllTags(request);

		if (!response.isInError())
		{
			for (Tag tag : response.getResultsList())
			{
				searchParameters.add(
						new SearchParameter(tag.getName(), DeviceFilterEnum.TAG, tag.getId().toString()));
			}
		}
	}

	/**
	 * Verify device models.
	 * 
	 * @param deviceModelList the device model list
	 * @param searchParameters the search parameters
	 * @param search the search
	 * @param tenant the tenant
	 */
	private void verifyDeviceModels(List<DeviceModel> deviceModelList, List<SearchParameter> searchParameters,
			CustomSearch search, DMTenant tenant)
	{
		DeviceRequest request = new DeviceRequest(new DeviceSearch(search.getDeviceType(), deviceModelList), tenant);

		InternalResultsResponse<DeviceModel> response = getDeviceBCL().fetchAllDeviceTypeDescriptions(request);

		if (!response.isInError())
		{
			for (DeviceModel deviceModel : response.getResultsList())
			{
				searchParameters.add(
						new SearchParameter(deviceModel.getDescription(), DeviceFilterEnum.DESCRIPTION, deviceModel
								.getId().toString()));
			}
		}
	}

	/**
	 * Verify custom search.
	 * 
	 * @param search the search
	 * @param serviceTypeEnum the service type enum
	 */
	private void verifyCustomSearch(CustomSearch search, ServiceTypeEnum serviceTypeEnum, DMTenant tenant)
	{

		List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
		List<BigInteger> groupIds = new ArrayList<BigInteger>();
		List<BigInteger> tagIds = new ArrayList<BigInteger>();
		List<DeviceModel> deviceModelList = new ArrayList<DeviceModel>();

		verifyParameters(groupIds, tagIds, deviceModelList, searchParameters, search);

		if (!ValidationUtil.isNullOrEmpty(groupIds))
		{
			verifyGroups(groupIds, searchParameters, serviceTypeEnum, tenant);
		}

		if (!ValidationUtil.isNullOrEmpty(tagIds))
		{
			verifyTags(tagIds, searchParameters, serviceTypeEnum, tenant);
		}

		if (!ValidationUtil.isNullOrEmpty(deviceModelList))
		{
			verifyDeviceModels(deviceModelList, searchParameters, search, tenant);
		}

		search.setSearchParameters(searchParameters);

	}

	/**
	 * Verify message.
	 * 
	 * @param response the response
	 * @param responseProperty the response property
	 * @return the internal results response
	 */
	private InternalResultsResponse<CustomSearch> verifyMessage(InternalResultsResponse<CustomSearch> response,
			InternalResponse responseProperty)
	{
		if (ValidationUtil.isNull(responseProperty) || !responseProperty.getStatus().equals(Status.OperationSuccess))
		{
			response.getMessageInfoList().add(
					MessageInfo.createFieldValidationError(ADD_PROPERTY_TO_CUSTOM_SEARCH_FAILED));
			response.setStatus(Status.ExceptionError);
		}

		return response;
	}

	/**
	 * Creates the search property.
	 * 
	 * @param id the id
	 * @param filterEnumValue the filter enum value
	 * @param value the value
	 * @return the property
	 */
	private Property createSearchProperty(Integer id, String filterEnumValue, String value)
	{
		return new Property(CUSTOM_SEARCH_PROVIDER, "" + id,
				filterEnumValue, value, "", 0);
	}

	/**
	 * Fill property dates list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyDatesList(CustomSearchRequest request, InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{
		// Add Install Date Start
		if (!ValidationUtil.isNull(request.getCustomSearch().getStartDate())
				&& !ValidationUtil.isNull(request.getCustomSearch().getEndDate()))
		{

			String dateStart = String.valueOf(request.getCustomSearch().getStartDate().getTime());
			String dateEnd = String.valueOf(request.getCustomSearch().getEndDate().getTime());

			properties.add(createSearchProperty(response.getFirstResult().getId(),
					DeviceFilterEnum.INSTALL_DATE_START.getValue(), dateStart));

			properties.add(createSearchProperty(response.getFirstResult().getId(),
					DeviceFilterEnum.INSTALL_DATE_END.getValue(), dateEnd));

		}

	}

	/**
	 * Fill property meter list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyElectricMeterList(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{

		if (ValidationUtil.isNull(request.getCustomSearch().getElectricMeterSearch())
				&& ValidationUtil.isNull(request.getCustomSearch().getElectricMeterSearch().getElectricMeter()))
		{
			return;
		}

		fillPropertyRadioLocationList(request.getCustomSearch().getElectricMeterSearch().getElectricMeter().getRadio(),
				response, properties);

		// Device Id
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getElectricMeterSearch().getElectricMeter()
				.getDeviceId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.DEVICE_ID.getValue(), request.getCustomSearch().getElectricMeterSearch()
							.getElectricMeter().getDeviceId(), "", 0));

		}

		// Add Remote Disconnect
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getElectricMeterSearch()
				.getRemoteConnectStatusEnumList()))
		{
			for (RemoteConnectStatusEnum remoteDisconnectStatus : request.getCustomSearch()
					.getElectricMeterSearch().getRemoteConnectStatusEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.REMOTE_DISCONNECT.getValue(),
						String.valueOf(remoteDisconnectStatus)));
			}

		}

		// Premise ID
		if (!ValidationUtil.isNull(request.getCustomSearch().getElectricMeterSearch().getElectricMeter()
				.getConfiguration())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getElectricMeterSearch().getElectricMeter()
						.getConfiguration().getPremiseId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.PREMISE_ID.getValue(), request.getCustomSearch().getElectricMeterSearch()
							.getElectricMeter().getConfiguration().getPremiseId(), "", 0));

		}

		// Add LifeCycleState Properties
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getElectricMeterSearch()
				.getElectricMeterLifecycleStateEnumList()))
		{
			for (ElectricMeterLifecycleStateEnum electricLifecycleState : request.getCustomSearch()
					.getElectricMeterSearch().getElectricMeterLifecycleStateEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.LIFECYCLE_STATE.getValue(),
						String.valueOf(electricLifecycleState)));
			}
		}

		// Add Meter Firmware
		if (!ValidationUtil.isNull(request.getCustomSearch().getElectricMeterSearch().getElectricMeter()
				.getConfiguration())
				&& !ValidationUtil.isNull(request.getCustomSearch().getElectricMeterSearch().getElectricMeter()
						.getConfiguration().getFirmwareMeter()))
		{
			properties.add(createSearchProperty(response.getFirstResult().getId(),
					DeviceFilterEnum.METER_FIRMWARE.getValue(),
					request.getCustomSearch().getElectricMeterSearch().getElectricMeter().getConfiguration()
							.getFirmwareMeter()));
		}

		// Quarantine
		if (!ValidationUtil.isNull(request.getCustomSearch().getElectricMeterSearch().getElectricMeter()
				.getQuarantine()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.QUARANTINE.getValue(), request.getCustomSearch().getElectricMeterSearch()
							.getElectricMeter().getQuarantine().toString(), "", 0));

		}

	}

	/**
	 * Fill property location han device list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyLocationHanDeviceList(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{
		// Address
		if (!ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
				.getRadio())
				&& !ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
						.getRadio().getLocation())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
						.getRadio().getLocation().getAddress()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.ADDRESS.getValue(), request.getCustomSearch().getHanDeviceSearch()
							.getHanDevice().getRadio().getLocation().getAddress(), "", 0));
		}

		// City
		if (!ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
				.getRadio())
				&& !ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
						.getRadio().getLocation())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
						.getRadio().getLocation().getCity()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.CITY_NAME.getValue(), request.getCustomSearch().getHanDeviceSearch()
							.getHanDevice().getRadio()
							.getLocation()
							.getCity(), "", 0));
		}

		// Zip
		if (!ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
				.getRadio())
				&& !ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
						.getRadio().getLocation())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
						.getRadio().getLocation().getZip()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.ZIP_CODE.getValue(), request.getCustomSearch().getHanDeviceSearch()
							.getHanDevice().getRadio()
							.getLocation()
							.getZip(), "", 0));
		}
	}

	/**
	 * Fill property han device list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyHanDeviceList(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{
		if (ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch())
				&& ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice()))
		{
			return;
		}

		fillPropertyLocationHanDeviceList(request, response, properties);

		// Device Id
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getHanDeviceSearch().getHanDevice()
				.getDeviceId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.DEVICE_ID.getValue(), request.getCustomSearch().getHanDeviceSearch()
							.getHanDevice().getDeviceId(), "", 0));

		}

		// Flexnet Id
		if (!ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice().getRadio())
				&& !ValidationUtil.isNull(request.getCustomSearch().getHanDeviceSearch().getHanDevice().getRadio()
						.getFlexNetId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.NETWORK_ADDRESS.getValue(), String.valueOf(request.getCustomSearch()
							.getHanDeviceSearch().getHanDevice().getRadio().getFlexNetId()), "", 0));

		}

		// Add LifeCycleState Properties
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getHanDeviceSearch()
				.getHanLifecycleStateEnumList()))
		{
			for (HanLifecycleStateEnum hanLifecycleState : request.getCustomSearch().getHanDeviceSearch()
					.getHanLifecycleStateEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.LIFECYCLE_STATE.getValue(), String.valueOf(hanLifecycleState)));
			}
		}

		// Add Devices SubtType
		if (!ValidationUtil
				.isNullOrEmpty(request.getCustomSearch().getHanDeviceSearch().getHanDeviceTypeEnumList()))
		{
			for (HanDeviceTypeEnum deviceType : request.getCustomSearch().getHanDeviceSearch()
					.getHanDeviceTypeEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.DEVICE_SUBTYPE.getValue(), String.valueOf(deviceType)));
			}
		}
	}

	/**
	 * Fill property location lcm list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyLocationLcmList(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{
		// Address
		if (!ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getRadio())
				&& !ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getRadio()
						.getLocation())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getLcmSearch().getLcm().getRadio()
						.getLocation().getAddress()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.ADDRESS.getValue(), request.getCustomSearch().getLcmSearch().getLcm()
							.getRadio().getLocation().getAddress(), "", 0));
		}

		// City
		if (!ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getRadio())
				&& !ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getRadio()
						.getLocation())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getLcmSearch().getLcm().getRadio()
						.getLocation().getCity()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.CITY_NAME.getValue(), request.getCustomSearch().getLcmSearch().getLcm()
							.getRadio().getLocation().getCity(), "", 0));
		}

		// Zip
		if (!ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getRadio())
				&& !ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getRadio()
						.getLocation())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getLcmSearch().getLcm().getRadio()
						.getLocation().getZip()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.ZIP_CODE.getValue(), request.getCustomSearch().getLcmSearch().getLcm()
							.getRadio().getLocation().getZip(), "", 0));
		}
	}

	/**
	 * Fill property lcm list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyLcmList(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{
		if (ValidationUtil.isNull(request.getCustomSearch().getLcmSearch()))
		{
			return;
		}

		// Add Alarm Properties
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getLcmSearch()
				.getAlarmEnumList()))

		{
			for (AlarmEnum alarmEnum : request.getCustomSearch().getLcmSearch().getAlarmEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.ALARM.toString(), alarmEnum.toString()));
			}
		}

		// Add LifeCycleState Properties
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getLcmSearch().getLcmLifecycleStateEnumList()))
		{
			for (LCMLifecycleStateEnum lcmLifecycleState : request.getCustomSearch().getLcmSearch()
					.getLcmLifecycleStateEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.LIFECYCLE_STATE.getValue(), String.valueOf(lcmLifecycleState)));
			}
		}

		// Add Devices SubType
		if (!ValidationUtil
				.isNullOrEmpty(request.getCustomSearch().getLcmSearch().getLcmTypeEnumList()))
		{

			for (LCMTypeEnum deviceType : request.getCustomSearch().getLcmSearch().getLcmTypeEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.DEVICE_SUBTYPE.getValue(), String.valueOf(deviceType)));
			}
		}

		if (ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm()))
		{
			return;
		}

		fillPropertyLocationLcmList(request, response, properties);

		// Device Id
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getLcmSearch().getLcm().getDeviceId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.DEVICE_ID.getValue(), request.getCustomSearch().getLcmSearch().getLcm()
							.getDeviceId(), "", 0));

		}

		// Flexnet Id
		if (!ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getRadio())
				&& !ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getRadio()
						.getFlexNetId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.NETWORK_ADDRESS.getValue(), String.valueOf(request
							.getCustomSearch().getLcmSearch().getLcm().getRadio().getFlexNetId()), "", 0));

		}

		// Add Meter Firmware
		if (!ValidationUtil.isNull(request.getCustomSearch().getLcmSearch().getLcm().getConfiguration())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getLcmSearch().getLcm().getConfiguration()
						.getFirmwareMeter()))
		{
			properties.add(createSearchProperty(response.getFirstResult().getId(),
					DeviceFilterEnum.METER_FIRMWARE.getValue(),
					request.getCustomSearch().getLcmSearch().getLcm().getConfiguration().getFirmwareMeter()));
		}

	}

	/**
	 * Fill property list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyList(CustomSearchRequest request, InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{

		if (!ValidationUtil.isNull(request.getCustomSearch().getSortExpression())
				&& !ValidationUtil.isNull(request.getCustomSearch().getSortExpression().getField()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					ORDER_BY, request.getCustomSearch().getSortExpression().getField() + SPACE
							+ request.getCustomSearch().getSortExpression().getDirectionValue(), "", 0));
		}

		// Add Groups
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getGroups()))
		{
			for (Group group : request.getCustomSearch().getGroups())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.GROUP.getValue(), group.getId().toString()));
			}
		}

		// Add Tags
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getTags()))
		{
			for (Tag tag : request.getCustomSearch().getTags())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.TAG.getValue(), tag.getId().toString()));
			}
		}

		// Add Processes
		if (!ValidationUtil.isNull(request.getCustomSearch().getProcessId()))
		{
			properties.add(createSearchProperty(response.getFirstResult().getId(),
					DeviceFilterEnum.PROCESSES.getValue(), request.getCustomSearch().getProcessId().toString()));
		}

		// Add Device type Description
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getDeviceModels()))
		{
			for (DeviceModel deviceModel : request.getCustomSearch().getDeviceModels())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.DESCRIPTION.getValue(), deviceModel.getId().toString()));
			}
		}

		// Add Device type
		if (!ValidationUtil.isNull(request.getCustomSearch().getDeviceType()))
		{

			properties.add(createSearchProperty(response.getFirstResult().getId(),
					DeviceFilterEnum.DEVICE_TYPE_ENUM.getValue(),
					String.valueOf(request.getCustomSearch().getDeviceType())));

		}

	}

	/**
	 * Delete column filter.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @param propertRequest the propert request
	 * @return the internal response
	 */
	private InternalResponse deleteColumnFilter(ColumnFilterRequest columnFilterRequest, PropertyRequest propertRequest)
	{

		propertRequest.getProperties().add(new Property(columnFilterRequest.getPropertyEnum().getValue(), null));

		fillPropertyProvider(columnFilterRequest, propertRequest.getProperties().get(0));

		InternalResponse response = null;

		// delete properties for column / filter
		if (!ValidationUtil.isNullOrEmpty(columnFilterRequest.getListColumn()))
		{

			response = getPropertyBCL().deleteProperty(propertRequest);
			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				return response;
			}

			propertRequest.getProperties().get(0).setPropertyName(PropertyEnum.SMARTPOINT_ORDER.getValue());
			response = getPropertyBCL().deleteProperty(propertRequest);
			if (!response.getStatus().equals(Status.OperationSuccess))
			{
				return response;
			}
			propertRequest.getProperties().clear();
			return response;

		}
		else if (!ValidationUtil.isNullOrEmpty(columnFilterRequest.getFilters()))
		{
			response = getPropertyBCL().deleteProperty(propertRequest);
			propertRequest.getProperties().clear();
			return response;
		}
		return null;
	}

	/**
	 * Fill property provider.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @param property the property
	 */
	private void fillPropertyProvider(ColumnFilterRequest columnFilterRequest, Property property)
	{
		if (ValidationUtil.isNullOrEmpty(columnFilterRequest.getPropertyProviderType()))
		{
			property.setProviderType(APPLICATION_USER_COLUMN);
			property.setProviderId("" + columnFilterRequest.getUserContext().getUserId());
		}
		else
		{
			property.setProviderId(columnFilterRequest.getProperties().get(0).getProviderId());
		}
	}

	/**
	 * Insert column filter.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @param propertyRequest the property request
	 * @return the internal response
	 */
	private InternalResponse insertColumnFilter(ColumnFilterRequest columnFilterRequest, PropertyRequest propertyRequest)
	{

		// inserting columns
		if (!ValidationUtil.isNullOrEmpty(columnFilterRequest.getListColumn()))
		{

			propertyRequest.getProperties().clear();

			for (Column column : columnFilterRequest.getListColumn())
			{
				propertyRequest.getProperties().add(
						new Property(columnFilterRequest.getPropertyEnum().getValue(), column.getColumnEnum()
								.getValue(), columnFilterRequest.getListColumn().indexOf(column) + 1));
				if (column.isOrdered())
				{
					propertyRequest.getProperties()
							.add(new Property(PropertyEnum.SMARTPOINT_ORDER.getValue(), column.getColumnEnum()
									.getValue(), 0));
				}
			}
		}

		// inserting filter
		if (!ValidationUtil.isNullOrEmpty(columnFilterRequest.getFilters()))
		{
			for (DeviceFilterEnum filter : columnFilterRequest.getFilters())
			{
				propertyRequest.getProperties()
						.add(new Property(columnFilterRequest.getPropertyEnum().getValue(), filter.getValue(),
								columnFilterRequest.getFilters().indexOf(filter) + 1));
			}
		}

		// filling property provider Id
		for (Property property : propertyRequest.getProperties())
		{
			fillPropertyProvider(columnFilterRequest, property);
		}

		return getPropertyBCL().upsertProperty(propertyRequest);

	}

	/**
	 * Fill property water meter list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyWaterMeterList(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{

		if (ValidationUtil.isNull(request.getCustomSearch().getWaterMeterSearch()))
		{
			return;
		}

		// Add Status
		if (!ValidationUtil.isNull(request.getCustomSearch().getWaterMeterSearch().getWaterGasMeterStatusEnumList()))
		{
			for (WaterGasMeterStatusEnum waterGasMeterStatusEnum : request.getCustomSearch()
					.getWaterMeterSearch().getWaterGasMeterStatusEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.STATUS_METER.getValue(),
						String.valueOf(waterGasMeterStatusEnum)));
			}
		}

		// Add Alarm Properties
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getWaterMeterSearch()
				.getAlarmEnumList()))

		{
			for (AlarmEnum alarmEnum : request.getCustomSearch().getWaterMeterSearch().getAlarmEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.ALARM.toString(), alarmEnum.toString()));
			}
		}

		if (ValidationUtil.isNull(request.getCustomSearch().getWaterMeterSearch().getWaterMeter()))
		{
			return;
		}

		fillPropertyRadioLocationList(request.getCustomSearch().getWaterMeterSearch().getWaterMeter().getRadio(),
				response,
				properties);

		// Device Id
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getWaterMeterSearch().getWaterMeter()
				.getDeviceId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.DEVICE_ID.getValue(), request.getCustomSearch().getWaterMeterSearch()
							.getWaterMeter().getDeviceId(), "", 0));
		}

		// Premise ID
		if (!ValidationUtil.isNull(request.getCustomSearch().getWaterMeterSearch().getWaterMeter()
				.getConfiguration())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getWaterMeterSearch().getWaterMeter()
						.getConfiguration().getPremiseId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.PREMISE_ID.getValue(), request.getCustomSearch().getWaterMeterSearch()
							.getWaterMeter().getConfiguration().getPremiseId(), "", 0));

		}

		// Add Meter Firmware
		if (!ValidationUtil.isNull(request.getCustomSearch().getWaterMeterSearch().getWaterMeter()
				.getConfiguration())
				&& !ValidationUtil.isNull(request.getCustomSearch().getWaterMeterSearch().getWaterMeter()
						.getConfiguration().getFirmwareMeter()))
		{
			properties.add(createSearchProperty(response.getFirstResult().getId(),
					DeviceFilterEnum.METER_FIRMWARE.getValue(),
					request.getCustomSearch().getWaterMeterSearch().getWaterMeter().getConfiguration()
							.getFirmwareMeter()));
		}

		// Quarantine
		if (!ValidationUtil.isNull(request.getCustomSearch().getWaterMeterSearch().getWaterMeter()
				.getQuarantine()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.QUARANTINE.getValue(), request.getCustomSearch().getWaterMeterSearch()
							.getWaterMeter().getQuarantine().toString(), "", 0));

		}
	}

	/**
	 * Fill property gas meter list.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyGasMeterList(CustomSearchRequest request,
			InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{

		if (ValidationUtil.isNull(request.getCustomSearch().getGasMeterSearch()))
		{
			return;
		}

		// Add Status
		if (!ValidationUtil.isNull(request.getCustomSearch().getGasMeterSearch().getWaterGasMeterStatusEnumList()))
		{
			for (WaterGasMeterStatusEnum waterGasMeterStatusEnum : request.getCustomSearch()
					.getGasMeterSearch().getWaterGasMeterStatusEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.STATUS_METER.getValue(),
						String.valueOf(waterGasMeterStatusEnum)));
			}
		}

		// Add Alarm Properties
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getGasMeterSearch()
				.getAlarmEnumList()))

		{
			for (AlarmEnum alarmEnum : request.getCustomSearch().getGasMeterSearch().getAlarmEnumList())
			{
				properties.add(createSearchProperty(response.getFirstResult().getId(),
						DeviceFilterEnum.ALARM.toString(), alarmEnum.toString()));
			}
		}

		if (ValidationUtil.isNull(request.getCustomSearch().getGasMeterSearch().getGasMeter()))
		{
			return;
		}

		fillPropertyRadioLocationList(request.getCustomSearch().getGasMeterSearch().getGasMeter().getRadio(), response,
				properties);

		// Device Id
		if (!ValidationUtil.isNullOrEmpty(request.getCustomSearch().getGasMeterSearch().getGasMeter()
				.getDeviceId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.DEVICE_ID.getValue(), request.getCustomSearch().getGasMeterSearch()
							.getGasMeter().getDeviceId(), "", 0));
		}

		// Premise ID
		if (!ValidationUtil.isNull(request.getCustomSearch().getGasMeterSearch().getGasMeter()
				.getConfiguration())
				&& !ValidationUtil.isNullOrEmpty(request.getCustomSearch().getGasMeterSearch().getGasMeter()
						.getConfiguration().getPremiseId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.PREMISE_ID.getValue(), request.getCustomSearch().getGasMeterSearch()
							.getGasMeter().getConfiguration().getPremiseId(), "", 0));

		}

		// Add Meter Firmware
		if (!ValidationUtil.isNull(request.getCustomSearch().getGasMeterSearch().getGasMeter()
				.getConfiguration())
				&& !ValidationUtil.isNull(request.getCustomSearch().getGasMeterSearch().getGasMeter()
						.getConfiguration().getFirmwareMeter()))
		{
			properties.add(createSearchProperty(response.getFirstResult().getId(),
					DeviceFilterEnum.METER_FIRMWARE.getValue(),
					request.getCustomSearch().getGasMeterSearch().getGasMeter().getConfiguration()
							.getFirmwareMeter()));
		}

		// Quarantine
		if (!ValidationUtil.isNull(request.getCustomSearch().getGasMeterSearch().getGasMeter()
				.getQuarantine()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.QUARANTINE.getValue(), request.getCustomSearch().getGasMeterSearch()
							.getGasMeter().getQuarantine().toString(), "", 0));

		}

	}

	/**
	 * Fill property location list.
	 * 
	 * @param radio the radio
	 * @param response the response
	 * @param properties the properties
	 */
	private void fillPropertyRadioLocationList(Radio radio, InternalResultsResponse<CustomSearch> response,
			List<Property> properties)
	{

		if (ValidationUtil.isNull(radio))
		{
			return;
		}

		// Flexnet Id
		if (!ValidationUtil.isNull(radio.getFlexNetId()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.NETWORK_ADDRESS.getValue(), String.valueOf(radio.getFlexNetId()), "", 0));

		}

		if (ValidationUtil.isNull(radio.getLocation()))
		{
			return;
		}

		// Address
		if (!ValidationUtil.isNullOrEmpty(radio.getLocation().getAddress()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.ADDRESS.getValue(), radio.getLocation()
							.getAddress(), "", 0));
		}

		// City
		if (!ValidationUtil.isNullOrEmpty(radio.getLocation().getCity()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.CITY_NAME.getValue(), radio.getLocation()
							.getCity(), "", 0));
		}

		// Zip
		if (!ValidationUtil.isNullOrEmpty(radio.getLocation().getZip()))
		{
			properties.add(new Property(CUSTOM_SEARCH_PROVIDER, "" + response.getFirstResult().getId(),
					DeviceFilterEnum.ZIP_CODE.getValue(), radio.getLocation()
							.getZip(), "", 0));
		}

	}
}
