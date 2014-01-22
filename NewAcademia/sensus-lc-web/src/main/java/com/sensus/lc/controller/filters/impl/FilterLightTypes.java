package com.sensus.lc.controller.filters.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sensus.common.model.UserContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.controller.model.FiltersResponse;
import com.sensus.lc.controller.model.ViewFilterLightTypes;
import com.sensus.lc.light.model.LightFilterTypeEnum;
import com.sensus.lc.light.model.LightFilterValue;
import com.sensus.lc.light.model.request.LightFilterRequest;
import com.sensus.lc.light.model.response.LightFilterResponse;

/**
 * The Class FilterLightTypes.
 */
@Component
public class FilterLightTypes extends AbstractFilterBase
{
	/** The Constant ZERO. */
	private static final int ZERO = 0;

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant TWO. */
	private static final int TWO = 2;

	/** The Constant LIGHT_TYPES. */
	private static final String LIGHT_TYPES = "LIGHT_TYPES";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#isAssignableFrom(java.lang.String)
	 */
	@Override
	public boolean isAssignableFrom(String filter)
	{
		return LIGHT_TYPES.equals(filter);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.controller.filters.IFilter#createFilter(com.sensus.common.model.UserContext,
	 * com.sensus.mlc.controller.model.FiltersResponse, java.lang.Object[])
	 */
	@Override
	public void createFilter(UserContext userContext, FiltersResponse filtersResponse, Object... addInformations)
	{
		ViewFilterLightTypes viewFilterLightTypes = new ViewFilterLightTypes();
		List<String[]> mapsLampTypes = new ArrayList<String[]>();
		List<String[]> mapsWattages = new ArrayList<String[]>();
		List<String[]> mapsHousing = new ArrayList<String[]>();
		List<String[]> mapsDimmable = new ArrayList<String[]>();
		List<Integer> wattageSort = new ArrayList<Integer>();

		// Request
		LightFilterRequest lightFilterRequest = new LightFilterRequest(userContext);
		lightFilterRequest.setFilterList(new ArrayList<LightFilterTypeEnum>());
		lightFilterRequest.getFilterList().add(LightFilterTypeEnum.LAMP_TYPE);
		lightFilterRequest.getFilterList().add(LightFilterTypeEnum.WATTAGE_RATING);
		lightFilterRequest.getFilterList().add(LightFilterTypeEnum.HOUSING);
		lightFilterRequest.getFilterList().add(LightFilterTypeEnum.DIMMABLE);

		// Response
		LightFilterResponse lightFilterResponse =
				getLightCustomSearchBCF().fetchLightFilters(lightFilterRequest);

		// Create the options for filter
		if (!ValidationUtil.isNullOrEmpty(lightFilterResponse.getLightFilterValueList()))
		{
			for (LightFilterValue lightFilterValue : lightFilterResponse.getLightFilterValueList())
			{

				if (LightFilterTypeEnum.LAMP_TYPE.equals(lightFilterValue
						.getLightFilterType()))
				{
					mapsLampTypes.add(new String[] {lightFilterValue.getFilterValue(),
							lightFilterValue.getFilterValue()});
					continue;
				}

				if (LightFilterTypeEnum.WATTAGE_RATING.equals(lightFilterValue
						.getLightFilterType()))
				{
					wattageSort.add(Integer.parseInt(lightFilterValue.getFilterValue()
							.replace('W', ' ').trim()));
					continue;
				}

				if (LightFilterTypeEnum.HOUSING.equals(lightFilterValue
						.getLightFilterType()))
				{
					mapsHousing.add(new String[] {lightFilterValue.getFilterValue(),
							lightFilterValue.getFilterValue()});
					continue;
				}

				if (LightFilterTypeEnum.DIMMABLE.equals(lightFilterValue
						.getLightFilterType()))
				{
					mapsDimmable.add(new String[] {lightFilterValue.getFilterValue().toLowerCase(),
							lightFilterValue.getFilterValue()});
				}
			}

			Collections.sort(wattageSort);
			for (Integer wattage : wattageSort)
			{
				String val = wattage.toString() + "W";
				mapsWattages.add(getKeyValue(val, val));

			}

			viewFilterLightTypes.setDimmable(mapsDimmable);
			viewFilterLightTypes.setHousing(mapsHousing);
			viewFilterLightTypes.setLamptype(mapsLampTypes);
			viewFilterLightTypes.setWattage(mapsWattages);

			filtersResponse.setLight_type(viewFilterLightTypes);
		}
	}

	/**
	 * Gets the key value.
	 * 
	 * @param id the id
	 * @param name the name
	 * @return the key value
	 */
	private String[] getKeyValue(String id, String name)
	{

		String[] record = new String[TWO];

		record[ZERO] = id;

		record[ONE] = name;

		return record;

	}

}
