package com.prosperitasglobal.sendsolv.model;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Documento extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The description. */
	private DocumentoTypeEnum documentoType;

	/** The numero. */
	private String numero;

	/** The data. */
	private Long data;

	/** The estado. */
	private Estado estado;

	public Integer getDocumentoTypeEnumValue()
	{
		if (documentoType != null)
		{
			return documentoType.getValue();
		}
		return null;
	}

	public void setDocumentoTypeEnumValue(Integer acaoTypeValue)
	{
		documentoType = DocumentoTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Default constructor.
	 */
	public Documento()
	{
		super();
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
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the documentoType
	 */
	public DocumentoTypeEnum getDocumentoType()
	{
		return documentoType;
	}

	/**
	 * @param documentoType the documentoType to set
	 */
	public void setDocumentoType(DocumentoTypeEnum documentoType)
	{
		this.documentoType = documentoType;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the numero to set
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Long getData()
	{
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the data to set
	 */
	public void setData(Long data)
	{
		this.data = data;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public Estado getEstado()
	{
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Documento [getDocumentoTypeEnumValue()=" + getDocumentoTypeEnumValue() + ", getId()=" + getId()
				+ ", getDocumentoType()=" + getDocumentoType() + ", getNumero()=" + getNumero() + ", getData()="
				+ getData() + ", getEstado()=" + getEstado() + ", getTabelaEnumValue()=" + getTabelaEnumValue()
				+ ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()=" + getAcaoEnumValue()
				+ ", getParentId()=" + getParentId() + ", getType()=" + getType() + ", getAcaoType()=" + getAcaoType()
				+ ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()=" + getStatusList() + ", getEmprId()="
				+ getEmprId() + ", getSite()=" + getSite() + ", getProcessId()=" + getProcessId() + ", getUserId()="
				+ getUserId() + ", getNotes()=" + getNotes() + ", toString()=" + super.toString()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
