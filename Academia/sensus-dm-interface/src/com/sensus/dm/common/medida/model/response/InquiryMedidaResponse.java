package com.sensus.dm.common.medida.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.common.medida.model.Medida;

/**
 * The Class InquiryMedidaResponse.
 * 
 * @author - Washington
 */
public class InquiryMedidaResponse extends InquiryResponse
{

	/** The medidas. */
	@XmlElement(nillable = true)
	private List<Medida> medidas;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the medidas.
	 * 
	 * @return the medidas
	 */
	public List<Medida> getMedidas()
	{
		return medidas;
	}

	/**
	 * Sets the medidas.
	 * 
	 * @param medidaList the new medidas
	 */
	public void setMedidas(List<Medida> medidaList)
	{
		medidas = medidaList;
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
		setMedidas(new ArrayList<Medida>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryMedidaResponse [medidas=" + medidas + ", fileName=" + fileName + ", getMedidas()=" + getMedidas()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
