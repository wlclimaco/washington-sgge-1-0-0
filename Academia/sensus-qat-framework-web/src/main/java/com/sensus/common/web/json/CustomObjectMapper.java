package com.sensus.common.web.json;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.stereotype.Component;

/**
 * Used to handle/map date values in the context of JSON.
 */
@Component("jacksonObjectMapper")
public class CustomObjectMapper extends ObjectMapper
{

	/**
	 * Date format string.
	 */
	private String dateFormat; // injected by spring

	/**
	 * @return the dateFormat
	 */
	public String getDateFormat()
	{
		return dateFormat;
	}

	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(String dateFormat)
	{
		this.dateFormat = dateFormat;
	}

	/**
	 * After properties set this method is invoked to set the date format that will
	 * be used for all JSON dates.
	 * 
	 * @throws Exception the exception
	 */
	@PostConstruct
	public void afterPropertiesSet() throws Exception
	{

		SimpleDateFormat sdf = new SimpleDateFormat(getDateFormat());
		SerializationConfig serialConfig = getSerializationConfig().withDateFormat(sdf);
		setSerializationConfig(serialConfig);

		DeserializationConfig deserializationConfig = getDeserializationConfig().withDateFormat(sdf);
		setDeserializationConfig(deserializationConfig);

	}
}
