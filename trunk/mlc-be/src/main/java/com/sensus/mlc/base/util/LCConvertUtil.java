package com.sensus.mlc.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.xwork.StringUtils;

import com.sensus.common.validation.ValidationUtil;

/**
 * The Class LCConvertUtil.
 *
 * @author - Cristiane Cobo - QAT Brazil
 *
 */
public final class LCConvertUtil
{

	/** The Constant SCALE. */
	public static final int SCALE = 3;

	/** The Constant ROUNDING_MODE. */
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

	/** The Constant THOUSAND. */
	private static final Integer THOUSAND = 1000;

	/**
	 * Instantiates a new lC convert util.
	 */
	private LCConvertUtil()
	{
	}

	/**
	 * Gets the ids to string.
	 *
	 * @param idsList the ids list
	 * @param delimiter the delimiter
	 * @return the ids to string
	 */
	public static String getIdsToString(Collection<?> idsList, String delimiter)
	{
		return getIdsToString(idsList, delimiter, "", "");
	}

	/**
	 * Gets the ids to string.
	 *
	 * @param idsList the ids list
	 * @param delimiter the delimiter
	 * @param delimiterOpen the delimiter open
	 * @param delimiterClose the delimiter close
	 * @return the ids to string
	 */
	public static String getIdsToString(Collection<?> idsList, String delimiter, String delimiterOpen,
			String delimiterClose)
	{
		if (CollectionUtils.isEmpty(idsList))
		{
			return null;
		}

		return delimiterOpen + StringUtils.join(idsList, delimiter) + delimiterClose;
	}

	/**
	 * Convert allowed group ids to string list.
	 *
	 * @param allowedGroupIds the allowed group ids
	 * @return the list
	 */
	public static List<String> convertAllowedGroupIdsToStringList(List<Integer> allowedGroupIds)
	{
		if (ValidationUtil.isNullOrEmpty(allowedGroupIds))
		{
			return null;
		}

		List<String> groups = new ArrayList<String>();
		for (Integer id : allowedGroupIds)
		{
			groups.add(String.valueOf(id));
		}

		return groups;
	}

	/**
	 * Convert string ids to string.
	 *
	 * @param ids the ids
	 * @return the string
	 */
	public static String convertStringIdsToString(List<String> ids)
	{
		if (ValidationUtil.isNullOrEmpty(ids))
		{
			return null;
		}

		String quote = "'";
		return quote + StringUtils.join(ids, "','") + quote;
	}

	/**
	 * Convert wattage to k wattage.
	 *
	 * @param wattage the wattage
	 * @return the double
	 */
	public static Double convertWattageToKWattage(Double wattage)
	{
		if (ValidationUtil.isNull(wattage))
		{
			return null;
		}

		return new BigDecimal(wattage / THOUSAND).setScale(SCALE, ROUNDING_MODE).doubleValue();
	}
}
