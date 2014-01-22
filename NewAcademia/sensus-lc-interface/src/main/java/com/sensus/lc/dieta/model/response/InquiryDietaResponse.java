package com.sensus.lc.dieta.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.dieta.model.Dieta;

/**
 * The Class InquiryDietaResponse.
 * 
 * @author - Washington
 */
public class InquiryDietaResponse extends InquiryResponse
{

	/** The dietas. */
	@XmlElement(nillable = true)
	private List<Dieta> dietas;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the dietas.
	 * 
	 * @return the dietas
	 */
	public List<Dieta> getDietas()
	{
		return dietas;
	}

	/**
	 * Sets the dietas.
	 * 
	 * @param dietaList the new dietas
	 */
	public void setDietas(List<Dieta> dietaList)
	{
		dietas = dietaList;
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
		setDietas(new ArrayList<Dieta>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryDietaResponse [dietas=" + dietas + ", fileName=" + fileName + ", getDietas()=" + getDietas()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
