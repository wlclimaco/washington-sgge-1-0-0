package com.prosperitasglobal.sendsolv.model;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class BeneficioMesApp extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	/** The parent key type. */
	private Long data;

	private Integer idFuncBenef;

	/**
	 * The Constructor.
	 */
	public BeneficioMesApp()
	{

	}

	public BeneficioMesApp(Integer id, Long data, Integer idFuncBenef)
	{
		super();
		this.id = id;
		this.data = data;
		this.idFuncBenef = idFuncBenef;
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * @return the idFuncBenef
	 */
	public Integer getIdFuncBenef()
	{
		return idFuncBenef;
	}

	/**
	 * @param idFuncBenef the idFuncBenef to set
	 */
	public void setIdFuncBenef(Integer idFuncBenef)
	{
		this.idFuncBenef = idFuncBenef;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BeneficioMesApp [getId()=" + getId() + ", getData()=" + getData() + ", getIdFuncBenef()="
				+ getIdFuncBenef() + ", getTabelaEnumValue()=" + getTabelaEnumValue() + ", getTypeValue()="
				+ getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue() + ", getParentId()=" + getParentId()
				+ ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()="
				+ getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()=" + getEmprId()
				+ ", getSite()=" + getSite() + ", toString()=" + super.toString() + ", getModelAction()="
				+ getModelAction() + ", getCreateUser()=" + getCreateUser() + ", getCreateDateUTC()="
				+ getCreateDateUTC() + ", getModifyUser()=" + getModifyUser() + ", getModifyDateUTC()="
				+ getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}