package com.sensus.lc.pessoa.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.lc.pessoa.model.Pessoa;

/**
 * The Class InquiryPessoaResponse.
 * 
 * @author - Washington
 */
public class InquiryPessoaResponse extends InquiryResponse
{

	/** The pessoas. */
	@XmlElement(nillable = true)
	private List<Pessoa> pessoas;

	/** The file name. */
	private String fileName;

	/**
	 * Gets the pessoas.
	 * 
	 * @return the pessoas
	 */
	public List<Pessoa> getPessoas()
	{
		return pessoas;
	}

	/**
	 * Sets the pessoas.
	 * 
	 * @param pessoaList the new pessoas
	 */
	public void setPessoas(List<Pessoa> pessoaList)
	{
		pessoas = pessoaList;
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
		setPessoas(new ArrayList<Pessoa>(coll));
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "InquiryPessoaResponse [pessoas=" + pessoas + ", fileName=" + fileName + ", getPessoas()="
				+ getPessoas()
				+ ", getFileName()=" + getFileName() + "]";
	}
}
