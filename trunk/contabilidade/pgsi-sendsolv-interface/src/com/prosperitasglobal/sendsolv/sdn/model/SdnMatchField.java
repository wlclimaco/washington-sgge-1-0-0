package com.prosperitasglobal.sendsolv.sdn.model;

import com.qat.framework.model.QATModel;

@SuppressWarnings("serial")
public class SdnMatchField extends QATModel
{
	private Integer id;
	private Integer parentKey;
	private SdnFieldEnum sdnField;
	private Integer sdnUid;
	private String sdnValue;
	private String systemValue;
	private Double matchProximity;

	public SdnMatchField()
	{

	}

	public SdnMatchField(SdnFieldEnum theField, Double matchProximity, String sdnValue, String systemValue, Integer uid)
	{
		setSdnField(theField);
		setMatchProximity(matchProximity);
		setSdnValue(sdnValue);
		setSystemValue(systemValue);
		setSdnUid(uid);
	}

	public SdnMatchField(SdnFieldEnum theField, Double matchProximity, String sdnValue, String systemValue)
	{
		setSdnField(theField);
		setMatchProximity(matchProximity);
		setSdnValue(sdnValue);
		setSystemValue(systemValue);
	}

	public SdnMatchField(SdnMatchField sdnMatchField)
	{
		setSdnField(sdnMatchField.getSdnField());
		setMatchProximity(sdnMatchField.getMatchProximity());
		setSdnValue(sdnMatchField.getSdnValue());
		setSystemValue(sdnMatchField.getSystemValue());
	}

	public Integer getParentKey()
	{
		return parentKey;
	}

	public void setParentKey(Integer parentKey)
	{
		this.parentKey = parentKey;
	}

	public SdnFieldEnum getSdnField()
	{
		return sdnField;
	}

	public void setSdnField(SdnFieldEnum sdnField)
	{
		this.sdnField = sdnField;
	}

	/**
	 * Methods that follow the naming pattern get.....Value() provide convenience for returning the primitive value of
	 * an enum. For example, database mapping of an enum to a database column could make use of this method.
	 *
	 * @return the contact type value
	 */
	public String getSdnFieldValue()
	{
		if (sdnField != null)
		{
			return sdnField.getValue();
		}
		return null;
	}

	/**
	 * Methods that follow the naming pattern set.....Value(argValue) provide convenience for assigning the primitive
	 * value of an enum. For example, database mapping of an database column to an enum could make use of this method.
	 *
	 * @param contactTypeValue the contact type value
	 */
	public void setSdnFieldValue(String sdnFieldValue)
	{
		sdnField = SdnFieldEnum.enumForValue(sdnFieldValue);
	}

	public Double getMatchProximity()
	{
		return matchProximity;
	}

	public void setMatchProximity(Double matchProximity)
	{
		this.matchProximity = matchProximity;
	}

	public String getSdnValue()
	{
		return sdnValue;
	}

	public void setSdnValue(String sdnValue)
	{
		this.sdnValue = sdnValue;
	}

	public String getSystemValue()
	{
		return systemValue;
	}

	public void setSystemValue(String systemValue)
	{
		this.systemValue = systemValue;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getSdnUid()
	{
		return sdnUid;
	}

	public void setSdnUid(Integer sdnUid)
	{
		this.sdnUid = sdnUid;
	}

	@Override
	public String toString()
	{
		return "SdnMatchField [getParentKey()=" + getParentKey() + ", getSdnField()=" + getSdnField()
				+ ", getSdnFieldValue()=" + getSdnFieldValue() + ", getMatchProximity()=" + getMatchProximity()
				+ ", getSdnValue()=" + getSdnValue() + ", getSystemValue()=" + getSystemValue() + ", getId()="
				+ getId() + ", getSdnUid()=" + getSdnUid() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()=" + getCreateDateUTC()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()=" + getModifyDateUTC() + "]";
	}
}
