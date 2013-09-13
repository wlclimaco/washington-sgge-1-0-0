package com.sensus.dm.common.grupomuscular.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.grupomuscular.model.Grupomuscular;

/**
 * The Class InquiryGrupomuscularResponse.
 * 
 * @author - Washington
 */
public class InquiryGrupomuscularResponse extends InquiryResponse
{

	/** The grupomusculars. */
	@XmlElement(nillable = true)
	private List<Grupomuscular> grupomusculars;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the grupomusculars.
	 * 
	 * @return the grupomusculars
	 */
	public List<Grupomuscular> getGrupomusculars()
	{
		return grupomusculars;
	}

	/**
	 * Sets the grupomusculars.
	 * 
	 * @param grupomuscularList the new grupomusculars
	 */
	public void setGrupomusculars(List<Grupomuscular> grupomuscularList)
	{
		grupomusculars = grupomuscularList;
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
		setGrupomusculars(new ArrayList<Grupomuscular>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryGrupomuscularResponse [grupomusculars=" + grupomusculars + ", fileName=" + fileName
				+ ", getGrupomusculars()=" + getGrupomusculars()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
