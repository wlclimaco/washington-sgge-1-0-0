package com.sensus.dm.commons.serie.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.commons.serie.model.Serieitens;

/**
 * The Class InquirySerieitensResponse.
 * 
 * @author - Washington
 */
public class InquirySerieitensResponse extends InquiryResponse
{

	/** The serieitenss. */
	@XmlElement(nillable = true)
	private List<Serieitens> serieitenss;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the serieitenss.
	 * 
	 * @return the serieitenss
	 */
	public List<Serieitens> getSerieitenss()
	{
		return serieitenss;
	}

	/**
	 * Sets the serieitenss.
	 * 
	 * @param serieitensList the new serieitenss
	 */
	public void setSerieitenss(List<Serieitens> serieitensList)
	{
		serieitenss = serieitensList;
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
		setSerieitenss(new ArrayList<Serieitens>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquirySerieitensResponse [serieitenss=" + serieitenss + ", fileName=" + fileName
				+ ", getSerieitenss()=" + getSerieitenss()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
