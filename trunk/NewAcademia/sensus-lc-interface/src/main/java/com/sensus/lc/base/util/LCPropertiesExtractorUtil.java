package com.sensus.lc.base.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensus.common.model.Authority;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.group.model.Group;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.tag.model.Tag;
import com.sensus.lc.user.model.User;

/**
 * The Class LCExtractorUtil.
 */
public final class LCPropertiesExtractorUtil
{

	/** The Constant GET_ID_METHOD. */
	private static final String GET_ID_METHOD = "getId";

	/**
	 * Logger.
	 */
	private static final Log LOG = LogFactory.getLog(LCPropertiesExtractorUtil.class);

	/**
	 * Instantiates a new lC extractor util.
	 */
	private LCPropertiesExtractorUtil()
	{

	}

	/**
	 * Extract authority id.
	 * 
	 * @param authorities the authorities
	 * @return the list
	 */
	public static List<Integer> extractAuthorityId(List<Authority> authorities)
	{
		return extractIdFromObject(authorities, Authority.class);
	}

	/**
	 * Extract light id.
	 * 
	 * @param authorities the authorities
	 * @return the list
	 */
	public static List<Integer> extractLightId(List<Light> lights)
	{
		return extractIdFromObject(lights, Light.class);
	}

	/**
	 * Extract tag id.
	 * 
	 * @param tags the tags
	 * @return the list
	 */
	public static List<Integer> extractTagId(List<Tag> tags)
	{
		return extractIdFromObject(tags, Tag.class);
	}

	/**
	 * Extract user id.
	 * 
	 * @param users the users
	 * @return the list
	 */
	public static List<Integer> extractUserId(List<User> users)
	{
		return extractIdFromObject(users, User.class);
	}

	/**
	 * Extract group id.
	 * 
	 * @param groups the groups
	 * @return the list
	 */
	public static List<Integer> extractGroupId(List<Group> groups)
	{
		return extractIdFromObject(groups, Group.class);
	}

	/**
	 * Extract smartpoint id.
	 * 
	 * @param smartpoints the smartpoints
	 * @return the list
	 */
	public static List<Integer> extractSmartpointId(List<Light> smartpoints)
	{
		if (ValidationUtil.isNullOrEmpty(smartpoints))
		{
			return null;
		}

		List<Integer> smartpointIds = new ArrayList<Integer>();
		for (Light light : smartpoints)
		{
			smartpointIds.add(light.getRadio().getFlexNetId().intValue());
		}

		return smartpointIds;
	}

	/**
	 * Extract id from object.
	 * 
	 * @param models the models
	 * @param clazz the clazz
	 * @return the list
	 */
	private static List<Integer> extractIdFromObject(List<?> models, Class<?> clazz)
	{
		try
		{
			if (ValidationUtil.isNullOrEmpty(models) || (clazz == null))
			{
				return null;
			}

			List<Integer> ids = new ArrayList<Integer>();

			Method method = clazz.getDeclaredMethod(GET_ID_METHOD);
			for (Object model : models)
			{
				ids.add((Integer)method.invoke(model));
			}

			return ids;
		}
		catch (Exception e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error(e.getMessage());
			}

			return null;
		}
	}
}
