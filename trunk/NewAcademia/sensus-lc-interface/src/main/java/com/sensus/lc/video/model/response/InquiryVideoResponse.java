package com.sensus.lc.video.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.foto.model.Foto;

/**
 * The Class InquiryFotoResponse.
 * 
 * @author - Washington
 */
public class InquiryVideoResponse extends InquiryResponse
{

	/** The fotos. */
	@XmlElement(nillable = true)
	private List<Foto> fotos;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the fotos.
	 * 
	 * @return the fotos
	 */
	public List<Foto> getFotos()
	{
		return fotos;
	}

	/**
	 * Sets the fotos.
	 * 
	 * @param fotoList the new fotos
	 */
	public void setFotos(List<Foto> fotoList)
	{
		fotos = fotoList;
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
		setFotos(new ArrayList<Foto>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryFotoResponse [fotos=" + fotos + ", fileName=" + fileName + ", getFotos()=" + getFotos()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
