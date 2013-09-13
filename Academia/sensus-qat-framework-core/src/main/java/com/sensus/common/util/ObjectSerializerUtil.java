package com.sensus.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * Utility class for serializing objects.
 * With this class you can serialize an object graph, including complex object graphs, into other types of objects.
 * See individual method documentation for details.
 */
public final class ObjectSerializerUtil
{

	/**
	 * Required in order for serialization to work.
	 */
	@JsonFilter("filter properties by name")
	class PropertyFilterMixIn
	{
	}

	/**
	 * Required since this is a final class.
	 */
	private ObjectSerializerUtil()
	{
	}

	/**
	 * Serialize an object graph to a map, including all properties and associations.
	 *
	 * @param payload The object graph to serialize.
	 * @return The map of property names and values. Complex types are represented using another map instance. You can
	 *         use the flattenMap method to flatten the hierarchy as you need.
	 * @throws Exception the exception
	 */
	public static Map<String, Object> toMap(Object payload) throws Exception
	{
		return toMap(payload, null);
	}

	/**
	 * Serialize an object graph to a map, including all properties and associations but excluding those properties
	 * based on String array parameter.
	 *
	 * @param payload The object graph to serialize.
	 * @param ignoreProperties String array of property-names to be excluded. Note regardless of where the property-name
	 *            is found within the object graph the property will be excluded.
	 * @return The map of property names and values. Complex types are represented using another map instance. You can
	 *         use the flattenMap method to flatten the hierarchy as you need.
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(Object payload, String[] ignoreProperties) throws Exception
	{
		ObjectWriter writer = null;
		ObjectMapper mapper = new ObjectMapper();

		if (ignoreProperties != null)
		{
			mapper.getSerializationConfig().addMixInAnnotations(
					Object.class, PropertyFilterMixIn.class);

			FilterProvider filters = new SimpleFilterProvider()
					.addFilter("filter properties by name",
							SimpleBeanPropertyFilter.serializeAllExcept(
									ignoreProperties));
			writer = mapper.writer(filters);
		}
		else
		{
			writer = mapper.writer();
		}

		return new ObjectMapper().readValue(writer.writeValueAsString(payload), Map.class);
	}

	/**
	 * Used to flatten the map so that complex types are included at the root map level instead of embedding another map
	 * instance. In this case the property-names indicate the property-path to the object value.
	 *
	 * @param map the map
	 * @return the map
	 */
	public static Map<String, Object> flattenMap(Map<String, Object> map)
	{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		doFlattenMap("", map, resultMap);
		return resultMap;
	}

	/**
	 * Do flatten map.
	 *
	 * @param propertyPrefix the property prefix
	 * @param inputMap the input map
	 * @param resultMap the result map
	 */
	private static void doFlattenMap(String propertyPrefix, Map<String, Object> inputMap, Map<String, Object> resultMap)
	{
		if (StringUtils.hasText(propertyPrefix))
		{
			propertyPrefix = propertyPrefix + ".";
		}
		for (String key : inputMap.keySet())
		{
			Object value = inputMap.get(key);
			doFlattenMapProcessElement(propertyPrefix + key, value, resultMap);
		}
	}

	/**
	 * Do flatten map process collection.
	 *
	 * @param propertyPrefix the property prefix
	 * @param list the list
	 * @param resultMap the result map
	 */
	private static void doFlattenMapProcessCollection(String propertyPrefix, Collection<?> list,
			Map<String, Object> resultMap)
	{
		int counter = 0;
		for (Object element : list)
		{
			doFlattenMapProcessElement(propertyPrefix + "[" + counter + "]", element, resultMap);
			counter++;
		}
	}

	/**
	 * Do flatten map process element.
	 *
	 * @param propertyPrefix the property prefix
	 * @param element the element
	 * @param resultMap the result map
	 */
	@SuppressWarnings("unchecked")
	private static void doFlattenMapProcessElement(String propertyPrefix, Object element, Map<String, Object> resultMap)
	{
		if (element instanceof Map)
		{
			doFlattenMap(propertyPrefix, (Map<String, Object>)element, resultMap);
		}
		else if (element instanceof Collection)
		{
			doFlattenMapProcessCollection(propertyPrefix, (Collection<?>)element, resultMap);
		}
		else if (element != null && element.getClass().isArray())
		{
			Collection<?> collection = CollectionUtils.arrayToList(element);
			doFlattenMapProcessCollection(propertyPrefix, collection, resultMap);
		}
		else
		{
			resultMap.put(propertyPrefix, element);
		}
	}

}
