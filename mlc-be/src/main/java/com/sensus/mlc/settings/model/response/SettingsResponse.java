package com.sensus.mlc.settings.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.settings.model.Setting;

/**
 * The Class SettingsResponse.
 */
public class SettingsResponse extends Response
{

	/** The Id. */
	private Integer id;

	/** The setting. */
	private List<Setting> settings;
	//private Setting setting;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId()
	{
		return this.id;
	}






	/**
	 * @return the settings
	 */
	public List<Setting> getSettings()
	{
		return settings;
	}






	/**
	 * @param settings the settings to set
	 */
	public void setSettings(List<Setting> settings)
	{
		this.settings = settings;
	}






	/**
	 * Sets the id.
	 *
	 * @param idValue the new id
	 */
	public void setId(Integer idValue)
	{
		this.id = idValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SettingsResponse [id=" + id + ", setting=" + settings + "]";
	}





}
