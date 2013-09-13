package com.sensus.dm.elec.settings.bcf;

import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.property.model.response.PropertyResponse;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.elec.settings.model.response.InquiryUserResponse;

/**
 * The Class MockSettingsBCF.
 * 
 * @author QAT Global.
 */
public class MockSettingsBCF extends AbstractMockBase implements ISettingsBCF
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

		return new PropertyResponse();
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

		return new PropertyResponse();
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

		return new InquiryUserResponse();
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

		return new PropertyResponse();
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

		return new PropertyResponse();
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

		return new PropertyResponse();
	}

}
