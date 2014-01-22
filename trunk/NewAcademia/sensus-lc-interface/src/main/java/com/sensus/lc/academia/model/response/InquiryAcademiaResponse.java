package com.sensus.lc.academia.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.academia.model.Academia;

/**
 * The Class InquiryAcademiaResponse.
 * 
 * @author - Washington
 */
public class InquiryAcademiaResponse extends InquiryResponse
{

	/** The academias. */
	@XmlElement(nillable = true)
	private List<Academia> academias;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the academias.
	 * 
	 * @return the academias
	 */
	public List<Academia> getAcademias()
	{
		return academias;
	}

	/**
	 * Sets the academias.
	 * 
	 * @param academiaList the new academias
	 */
	public void setAcademias(List<Academia> academiaList)
	{
		academias = academiaList;
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
		setAcademias(new ArrayList<Academia>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryAcademiaResponse [academias=" + academias + ", fileName=" + fileName + ", getAcademias()="
				+ getAcademias()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
