package com.sensus.dm.common.property.dac.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.property.dac.IPropertyDAC;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;

/**
 * The Class PropertyDACImpl.
 * 
 * @author QAT Global.
 */
public class PropertyDACImpl extends SqlSessionDaoSupport implements IPropertyDAC
{

	/** The Constant INT_0. */
	private static final Integer INT_0 = 0;

	/** The Constant PARAMSIZE1. */
	private static final Integer PARAMSIZE1 = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE3. */
	private static final Integer PARAMSIZE3 = 3;

	/** The Constant SPACER. */
	private static final String SPACER = " ";

	/** The Constant DISPLAY_ORDER. */
	private static final String DISPLAY_ORDER = "order";

	/** The Constant PROPERTY_NAME. */
	private static final String PROPERTY_NAME = "property_name";

	/** The Constant PROPERTY_VALUE. */
	private static final String PROPERTY_VALUE = "property_value";

	/** The Constant PROVIDER. */
	private static final String PROVIDER = "provider";

	/** The Constant USER. */
	private static final String USER = "user";

	/** The Constant VALID_VALUE. */
	private static final String VALID_VALUE = "valid_value";

	/** The Constant SENSUS_EPM_PROPERTY_ERROR_DELETING_PROPERTY. */
	private static final String SENSUS_EPM_PROPERTY_ERROR_DELETING_PROPERTY =
			"sensus.epm.propertyvalidator.property.error.deleting.property";

	/** The Constant SENSUS_EPM_PROPERTY_ERROR_DELETING_PROPERTY_PROVIDER. */
	private static final String SENSUS_EPM_PROPERTY_ERROR_DELETING_PROPERTY_PROVIDER =
			"sensus.epm.propertyvalidator.property.error.deleting.property.provider";

	/** The Constant SENSUS_EPM_PROPERTY_PROPERTY_VALUE_NOT_VALID. */
	private static final String SENSUS_EPM_PROPERTY_PROPERTY_VALUE_NOT_VALID =
			"sensus.epm.propertyvalidator.property.not.valid";

	/** The Constant PROPERTY_MAP. */
	private static final String PROPERTY_MAP = "PropertyMap.";

	/** The Constant DELETE_PROPERTY. */
	private static final String DELETE_PROPERTY = PROPERTY_MAP + "deleteProperty";

	/** The Constant DELETE_PROPERTY_PROVIDER. */
	private static final String DELETE_PROPERTY_PROVIDER = PROPERTY_MAP + "deletePropertyProvider";

	/** The Constant FETCH_PROPERTY. */
	private static final String FETCH_PROPERTY = PROPERTY_MAP + "fetchProperty";

	/** The Constant FETCH_PROPERTY_COUNT. */
	private static final String FETCH_PROPERTY_COUNT = PROPERTY_MAP + "fetchPropertyCount";

	/** The Constant FETCH_PROPERTY_PROVIDER_BY_OWNER_INSTANCE. */
	private static final String FETCH_PROPERTY_PROVIDER_BY_OWNER_INSTANCE = PROPERTY_MAP
			+ "fetchPropertyProviderByOwnerInstance";

	/** The Constant FETCH_PROPERTY_VALID_VALUE. */
	private static final String FETCH_PROPERTY_VALID_VALUE = PROPERTY_MAP + "fetchPropertyValidValue";

	/** The Constant FETCH_PROPERTY_VALID_VALUES_COUNT. */
	private static final String FETCH_PROPERTY_VALID_VALUES_COUNT = PROPERTY_MAP
			+ "fetchPropertyValidValueCount";

	/** The Constant INSERT_PROPERTY_INSTANCE. */
	private static final String INSERT_PROPERTY_INSTANCE = PROPERTY_MAP + "insertPropertyInstance";

	/** The Constant INSERT_PROPERTY_PROVIDER. */
	private static final String INSERT_PROPERTY_PROVIDER = PROPERTY_MAP + "insertPropertyProvider";

	/** The Constant UPDATE_PROPERTY_INSTANCE. */
	private static final String UPDATE_PROPERTY_INSTANCE = PROPERTY_MAP + "updatePropertyInstance";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.dac.IPropertyDAC#fetchProperty(com.sensus.dm.common.property.model.request.PropertyRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Property> fetchProperty(PropertyRequest propertyRequest)
	{
		Property property = null;
		if (!ValidationUtil.isNullOrEmpty(propertyRequest.getProperties()))
		{
			property = propertyRequest.getProperties().get(INT_0);
		}

		String providerType = null;
		if (!ValidationUtil.isNullOrEmpty(propertyRequest.getPropertyProviderType()))
		{
			providerType = propertyRequest.getPropertyProviderType();
		}
		else
		{
			providerType = property.getProviderType();
		}

		Map<String, Object> params = new HashMap<String, Object>(PARAMSIZE2);

		if (!ValidationUtil.isNull(property))
		{
			params.put(PROVIDER, new StringBuilder(providerType).append(SPACER).append(property.getProviderId())
					.toString());

			params.put(PROPERTY_NAME, property.getPropertyName());
		}

		return new InternalResultsResponse<Property>(
				SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_PROPERTY, params));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.dac.IPropertyDAC#upsertProperty(com.sensus.dm.common.property.model.request.PropertyRequest)
	 */
	@Override
	public InternalResponse upsertProperty(PropertyRequest propertyRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Property property : propertyRequest.getProperties())
		{

			Integer validValuesCount = (Integer)SensusMyBatisDacHelper.doQueryForObject(
					getSqlSession(), FETCH_PROPERTY_VALID_VALUES_COUNT, property);

			Integer validValue = null;

			if (validValuesCount > 0)
			{
				validValue = (Integer)SensusMyBatisDacHelper.doQueryForObject(
						getSqlSession(), FETCH_PROPERTY_VALID_VALUE, property);

				if (ValidationUtil.isNullOrZero(validValue))
				{
					response.addMessage(SENSUS_EPM_PROPERTY_PROPERTY_VALUE_NOT_VALID, MessageSeverity.Error,
							MessageLevel.Field);
					response.setStatus(Status.ExceptionError);
					return response;
				}
			}

			String providerType = null;
			if (!ValidationUtil.isNullOrEmpty(propertyRequest.getPropertyProviderType()))
			{
				providerType = propertyRequest.getPropertyProviderType();
			}
			else
			{
				providerType = property.getProviderType();
			}

			HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);
			paramMap.put(PROVIDER, new StringBuilder(providerType).append(SPACER).append(property.getProviderId())
					.toString());

			Integer propertyProviderId = (Integer)SensusMyBatisDacHelper.doQueryForObject(
					getSqlSession(), FETCH_PROPERTY_PROVIDER_BY_OWNER_INSTANCE, paramMap);

			if (ValidationUtil.isNullOrZero(propertyProviderId))
			{
				paramMap = new HashMap<String, Object>(PARAMSIZE2);
				paramMap.put(USER, propertyRequest.getUserContext().getUserId());
				paramMap.put(PROVIDER, new StringBuilder(providerType).append(SPACER).append(property.getProviderId())
						.toString());

				propertyProviderId = (Integer)SensusMyBatisDacHelper.doQueryForObject(
						getSqlSession(), INSERT_PROPERTY_PROVIDER, paramMap);
			}

			paramMap = new HashMap<String, Object>(PARAMSIZE2);
			paramMap.put(PROPERTY_NAME, property.getPropertyName());
			paramMap.put(PROVIDER, new StringBuilder(providerType).append(SPACER).append(property.getProviderId())
					.toString());

			Integer propertyCount = (Integer)SensusMyBatisDacHelper.doQueryForObject(
					getSqlSession(), FETCH_PROPERTY_COUNT, paramMap);

			Boolean alwaysInsert =
					!ValidationUtil.isNull(propertyRequest.getAlwaysInsert()) && propertyRequest.getAlwaysInsert();

			if (!alwaysInsert && propertyCount > 0)
			{
				paramMap.put(PROVIDER, propertyProviderId);
				paramMap.put(USER, propertyRequest.getUserContext().getUserId());
				paramMap.put(PROPERTY_NAME, property.getPropertyName());
				paramMap.put(PROPERTY_VALUE, property.getPropertyValue());
				paramMap.put(VALID_VALUE, validValue);

				if (!ValidationUtil.isNull(property.getDisplayOrder()))
				{
					paramMap.put(DISPLAY_ORDER, property.getDisplayOrder());
				}

				SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), UPDATE_PROPERTY_INSTANCE, paramMap);
			}
			else
			{
				paramMap.put(PROVIDER, propertyProviderId);
				paramMap.put(USER, propertyRequest.getUserContext().getUserId());
				paramMap.put(PROPERTY_NAME, property.getPropertyName());
				paramMap.put(PROPERTY_VALUE, property.getPropertyValue());

				if (!ValidationUtil.isNull(property.getDisplayOrder()))
				{
					paramMap.put(DISPLAY_ORDER, property.getDisplayOrder());
				}

				paramMap.put(VALID_VALUE, validValue);

				SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), INSERT_PROPERTY_INSTANCE, paramMap);
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.dac.IPropertyDAC#deleteProperty(com.sensus.dm.common.property.model.request.PropertyRequest)
	 */
	@Override
	public InternalResponse deleteProperty(PropertyRequest propertyRequest)
	{
		InternalResponse response = new InternalResponse();

		Property property = null;
		if (!ValidationUtil.isNullOrEmpty(propertyRequest.getProperties()))
		{
			property = propertyRequest.getProperties().get(INT_0);
		}

		String providerType;
		if (!ValidationUtil.isNullOrEmpty(propertyRequest.getPropertyProviderType()))
		{
			providerType = propertyRequest.getPropertyProviderType();
		}
		else
		{
			providerType = property.getProviderType();
		}

		if (ValidationUtil.isNull(property) || ValidationUtil.isNull(property.getProviderId()))
		{
			response.addMessage(SENSUS_EPM_PROPERTY_ERROR_DELETING_PROPERTY, MessageSeverity.Error, MessageLevel.Field);
			response.setStatus(Status.ExceptionError);
			return response;
		}
		else
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE3);
			paramMap.put(
					PROVIDER,
					new StringBuilder(providerType).append(SPACER).append(property.getProviderId()).toString());

			if (!ValidationUtil.isNullOrEmpty(property.getPropertyName()))
			{
				paramMap.put(PROPERTY_NAME, property.getPropertyName());
			}

			if (!ValidationUtil.isNullOrEmpty(property.getPropertyValue()))
			{
				paramMap.put(PROPERTY_VALUE, property.getPropertyValue());
			}

			SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_PROPERTY, paramMap);

			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.property.dac.IPropertyDAC#deletePropertyProvider(com.sensus.dm.common.property.model.request.PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse deletePropertyProvider(PropertyRequest propertyRequest)
	{
		InternalResponse response = new InternalResponse();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);

		Property property = null;
		if (!ValidationUtil.isNullOrEmpty(propertyRequest.getProperties()))
		{
			property = propertyRequest.getProperties().get(INT_0);
		}

		String providerType;
		if (!ValidationUtil.isNullOrEmpty(propertyRequest.getPropertyProviderType()))
		{
			providerType = propertyRequest.getPropertyProviderType();
		}
		else
		{
			providerType = property.getProviderType();
		}

		if (ValidationUtil.isNull(property) || ValidationUtil.isNull(property.getProviderId()))
		{
			response.addMessage(SENSUS_EPM_PROPERTY_ERROR_DELETING_PROPERTY_PROVIDER, MessageSeverity.Error,
					MessageLevel.Field);
			response.setStatus(Status.ExceptionError);
			return response;
		}
		else
		{
			paramMap.put(
					PROVIDER,
					new StringBuilder(providerType).append(SPACER).append(property.getProviderId()).toString());
		}

		SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), DELETE_PROPERTY_PROVIDER, paramMap);

		return response;
	}
}
