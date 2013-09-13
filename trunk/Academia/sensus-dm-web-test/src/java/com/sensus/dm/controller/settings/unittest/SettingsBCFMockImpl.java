package com.sensus.dm.controller.settings.unittest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.UserContext;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.property.model.response.PropertyResponse;
import com.sensus.dm.controller.unittest.util.BaseMockImpl;
import com.sensus.dm.controller.unittest.util.ModeEnum;
import com.sensus.dm.elec.settings.bcf.ISettingsBCF;
import com.sensus.dm.elec.settings.model.response.InquiryUserResponse;

/**
 * The Class SettingsBCFMockImpl.
 */
public class SettingsBCFMockImpl extends BaseMockImpl implements ISettingsBCF
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#fetchUserSettings(com.sensus.dm.common.property.model.request.
	 * PropertyRequest)
	 */
	@Override
	public PropertyResponse fetchUserSettings(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<Property> properties = new ArrayList<Property>();
			Property property;

			property = new Property("LANGUAGE", "en_US");
			properties.add(property);

			property = new Property("TIME_ZONE", "US/Eastern");
			properties.add(property);

			property = new Property("DATE_FORMAT", "dd/MM/yyyy");
			properties.add(property);

			property = new Property("PAGE_SIZE", "100");
			properties.add(property);

			property = new Property("MONITOR_REQUEST", "2");
			properties.add(property);

			response.setProperties(properties);

			return response;
		}
		return (PropertyResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#fetchSystemSettings(com.sensus.dm.common.property.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public PropertyResponse fetchSystemSettings(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<Property> properties = new ArrayList<Property>();
			Property property;

			property = new Property("LANGUAGE", "en_US");
			properties.add(property);

			property = new Property("TIME_ZONE", "US/Eastern");
			properties.add(property);

			property = new Property("DATE_FORMAT", "dd/MM/yyyy");
			properties.add(property);

			property = new Property("PAGE_SIZE", "100");
			properties.add(property);

			response.setProperties(properties);

			return response;
		}
		return (PropertyResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#fetchAllUsers(com.sensus.dm.common.property.model.request.
	 * PropertyRequest)
	 */
	@Override
	public InquiryUserResponse fetchAllUsers(PropertyRequest propertyRequest)
	{
		InquiryUserResponse response = new InquiryUserResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			List<UserContext> userList = new ArrayList<UserContext>();
			UserContext userContext = new UserContext();
			// userContext
			userList.add(userContext);

			response.setOperationSuccess(true);
			response.setUsers(userList);
			return response;
		}
		return (InquiryUserResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#upsertSystemSettings(com.sensus.dm.common.property.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public PropertyResponse upsertSystemSettings(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}
		return (PropertyResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#upsertUserSettings(com.sensus.dm.common.property.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public PropertyResponse upsertUserSettings(PropertyRequest propertyRequest)
	{
		PropertyResponse response = new PropertyResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}
		return (PropertyResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcf.ISettingsBCF#insertUser(com.sensus.dm.common.property.model.request.PropertyRequest
	 * )
	 */
	@Override
	public PropertyResponse insertUser(PropertyRequest propertyRequest)
	{

		PropertyResponse response = new PropertyResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}
		return (PropertyResponse)testOtherDefaultModes(response);
	}

}
