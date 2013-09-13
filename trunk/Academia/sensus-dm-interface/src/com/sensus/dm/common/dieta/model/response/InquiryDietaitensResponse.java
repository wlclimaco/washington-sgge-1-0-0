package com.sensus.dm.common.dieta.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.dieta.model.Dietaitens;

/**
 * The Class InquiryDietaitensResponse.
 * 
 * @author - Washington
 */
public class InquiryDietaitensResponse extends InquiryResponse
{

	/** The dietaitenss. */
	@XmlElement(nillable = true)
	private List<Dietaitens> dietaitenss;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the dietaitenss.
	 * 
	 * @return the dietaitenss
	 */
	public List<Dietaitens> getDietaitenss()
	{
		return dietaitenss;
	}

	/**
	 * Sets the dietaitenss.
	 * 
	 * @param dietaitensList the new dietaitenss
	 */
	public void setDietaitenss(List<Dietaitens> dietaitensList)
	{
		dietaitenss = dietaitensList;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName()
	{
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.response.InquiryResponse#addResults(java.util.Collection)
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public void addResults(Collection coll)
	{
		setDietaitenss(new ArrayList<Dietaitens>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryDietaitensResponse [dietaitenss=" + dietaitenss + ", fileName=" + fileName
				+ ", getDietaitenss()=" + getDietaitenss()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
