package com.qat.samples.sysmgmt.nf.model;

import java.sql.Blob;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class NFStatus extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private Integer idNota;

	private StatusNF status;

	private Long dataMudanca;

	private Blob xmlRetorno;

	/**
	 * The Constructor.
	 */
	public NFStatus()
	{

	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the idNota
	 */
	public Integer getIdNota()
	{
		return idNota;
	}

	/**
	 * @param idNota the idNota to set
	 */
	public void setIdNota(Integer idNota)
	{
		this.idNota = idNota;
	}

	/**
	 * @return the status
	 */
	public StatusNF getStatus()
	{
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusNF status)
	{
		this.status = status;
	}

	/**
	 * @return the dataMudanca
	 */
	public Long getDataMudanca()
	{
		return dataMudanca;
	}

	/**
	 * @param dataMudanca the dataMudanca to set
	 */
	public void setDataMudanca(Long dataMudanca)
	{
		this.dataMudanca = dataMudanca;
	}

	/**
	 * @return the xmlRetorno
	 */
	public Blob getXmlRetorno()
	{
		return xmlRetorno;
	}

	/**
	 * @param xmlRetorno the xmlRetorno to set
	 */
	public void setXmlRetorno(Blob xmlRetorno)
	{
		this.xmlRetorno = xmlRetorno;
	}

	@Override
	public String toString()
	{
		return "NFStatus [getId()=" + getId() + ", getIdNota()=" + getIdNota() + ", getStatus()=" + getStatus()
				+ ", getDataMudanca()=" + getDataMudanca() + ", getXmlRetorno()=" + getXmlRetorno() + ", toString()="
				+ super.toString() + "]";
	}

}