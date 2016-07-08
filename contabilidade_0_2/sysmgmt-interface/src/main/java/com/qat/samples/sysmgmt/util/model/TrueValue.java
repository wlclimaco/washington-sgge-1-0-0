package com.qat.samples.sysmgmt.util.model;

import java.util.Date;

import com.qat.samples.sysmgmt.estado.model.Estado;

// TODO: Auto-generated Javadoc
/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class TrueValue extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The description. */
	private TrueValueTypeEnum valueType;

	/** The numero. */
	private String valor;

	/** The data. */
	private Long label;


	public Integer getTrueValueTypeEnumValue()
	{
		if (documentoType != null)
		{
			return TrueValueTypeEnum.getValue();
		}
		return null;
	}

	public void setTrueValueEnumValue(Integer acaoTypeValue)
	{
		valueType = TrueValueTypeEnum.enumForValue(acaoTypeValue);
	}

	/**
	 * Default constructor.
	 */
	public Documento()
	{
		super();
	}

	public Documento(Integer id, DocumentoTypeEnum documentoType, String numero, Long data, Estado estado,
			PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.documentoType = documentoType;
		this.numero = numero;
		this.data = data;
		this.estado = estado;
		setModelAction(modelAction);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
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

	@Override
	public String toString()
	{
		return "Documento [getDocumentoTypeEnumValue()=" + getDocumentoTypeEnumValue() + ", getId()=" + getId()
				+ ", getDocumentoType()=" + getDocumentoType() + ", getNumero()=" + getNumero() + ", getData()="
				+ getData() + ", getEstado()=" + getEstado() + ", toString()=" + super.toString() + "]";
	}

}
