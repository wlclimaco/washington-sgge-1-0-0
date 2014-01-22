package com.sensus.lc.suplemento.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.suplemento.model.Suplemento;

/**
 * The Class InquirySuplementoResponse.
 * 
 * @author - Washington
 */
public class InquirySuplementoResponse extends InquiryResponse
{

	/** The suplementos. */
	@XmlElement(nillable = true)
	private List<Suplemento> suplementos;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the suplementos.
	 * 
	 * @return the suplementos
	 */
	public List<Suplemento> getSuplementos()
	{
		return suplementos;
	}

	/**
	 * Sets the suplementos.
	 * 
	 * @param suplementoList the new suplementos
	 */
	public void setSuplementos(List<Suplemento> suplementoList)
	{
		suplementos = suplementoList;
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
		setSuplementos(new ArrayList<Suplemento>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquirySuplementoResponse [suplementos=" + suplementos + ", fileName=" + fileName
				+ ", getSuplementos()=" + getSuplementos()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
