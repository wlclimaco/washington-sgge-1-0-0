package com.sensus.lc.curtir.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.curtir.model.Curtir;

/**
 * The Class InquiryFotoResponse.
 * 
 * @author - Washington
 */
public class InquiryCurtirResponse extends InquiryResponse
{

	/** The curtir. */
	@XmlElement(nillable = true)
	private List<Curtir> curtir;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the curtir.
	 * 
	 * @return the curtir
	 */
	public List<Curtir> getCurtir()
	{
		return curtir;
	}

	/**
	 * Sets the curtir.
	 * 
	 * @param fotoList the new curtir
	 */
	public void setCurtir(List<Curtir> fotoList)
	{
		curtir = fotoList;
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
		setCurtir(new ArrayList<Curtir>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryFotoResponse [curtir=" + curtir + ", fileName=" + fileName + ", getCurtir()=" + getCurtir()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
