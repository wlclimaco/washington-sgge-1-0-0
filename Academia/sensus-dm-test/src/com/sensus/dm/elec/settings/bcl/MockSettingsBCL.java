package com.sensus.dm.elec.settings.bcl;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.PropertyEnum;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.util.AbstractMockBase;
import com.sensus.dm.common.util.SituationsEnum;

/**
 * The Class MockSettingsBCL.
 * 
 * @author QAT Global.
 */
public class MockSettingsBCL extends AbstractMockBase implements ISettingsBCL
{

	/** The Constant ONE. */
	private static final String ONE = "1";

	/** The Constant ONE_INT. */
	private static final int ONE_INT = 1;

	/** The Constant TWO. */
	private static final int TWO = 2;

	/** The Constant USE_ID. */
	private static final String USE_ID = "Use ID";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#fetchAllUsers(com.sensus.dm.elec.settings.model.request.PropertyRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<UserContext> fetchAllUsers(PropertyRequest propertyRequest)
	{

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			InternalResultsResponse<UserContext> internalResultsResponse = new InternalResultsResponse<UserContext>();
			internalResultsResponse.getResultsList().add(new UserContext(USE_ID));
			return internalResultsResponse;
		}

		return (InternalResultsResponse<UserContext>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#fetchUserSettings(com.sensus.dm.elec.settings.model.request.
	 * PropertyRequest
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Property> fetchUserSettings(PropertyRequest propertyRequest)
	{
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			InternalResultsResponse<Property> internalResultsResponse = new InternalResultsResponse<Property>();
			internalResultsResponse.getResultsList().add(
					new Property(ONE, ONE, PropertyEnum.LANGUAGE.getValue(), ONE, ONE, ONE_INT));
			internalResultsResponse.getResultsList().add(
					new Property(ONE, ONE, PropertyEnum.LANGUAGE.getValue(), ONE, ONE, TWO));
			return internalResultsResponse;
		}

		return (InternalResultsResponse<Property>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.settings.bcl.ISettingsBCL#fetchSystemSettings(com.sensus.dm.elec.settings.model.request.
	 * PropertyRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Property> fetchSystemSettings(PropertyRequest propertyRequest)
	{

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			InternalResultsResponse<Property> internalResultsResponse = new InternalResultsResponse<Property>();
			internalResultsResponse.getResultsList().add(
					new Property(ONE, ONE, PropertyEnum.LANGUAGE.getValue(), ONE, ONE, ONE_INT));
			internalResultsResponse.getResultsList().add(
					new Property(ONE, ONE, PropertyEnum.LANGUAGE.getValue(), ONE, ONE, TWO));
			return internalResultsResponse;
		}

		return (InternalResultsResponse<Property>)verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#upsertSystemSettings(com.sensus.dm.elec.settings.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse upsertSystemSettings(PropertyRequest propertyRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#upsertUserSettings(com.sensus.dm.elec.settings.model.request.
	 * PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse upsertUserSettings(PropertyRequest propertyRequest)
	{
		return verifyOtherSituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.bcl.ISettingsBCL#insertUser(com.sensus.dm.common.property.model.request.PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse insertUser(PropertyRequest propertyRequest)
	{
		return verifyOtherSituations();
	}

}
