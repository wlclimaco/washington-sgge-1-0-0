package com.sensus.dm.commons.exercicio.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.dm.commons.exercicio.model.Exercicio;

/**
 * The Class InquiryExercicioResponse.
 * 
 * @author - Washington
 */
public class InquiryExercicioResponse extends InquiryResponse
{

	/** The exercicios. */
	@XmlElement(nillable = true)
	private List<Exercicio> exercicios;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the exercicios.
	 * 
	 * @return the exercicios
	 */
	public List<Exercicio> getExercicios()
	{
		return exercicios;
	}

	/**
	 * Sets the exercicios.
	 * 
	 * @param exercicioList the new exercicios
	 */
	public void setExercicios(List<Exercicio> exercicioList)
	{
		exercicios = exercicioList;
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
		setExercicios(new ArrayList<Exercicio>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryExercicioResponse [exercicios=" + exercicios + ", fileName=" + fileName + ", getExercicios()="
				+ getExercicios()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
