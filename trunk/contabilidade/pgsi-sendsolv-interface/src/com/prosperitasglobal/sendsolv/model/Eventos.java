package com.prosperitasglobal.sendsolv.model;

import java.util.List;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Eventos extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	/** The parent key type. */
	private List<EventoMesApp> dataList;

	private String descricao;

	private String codigo;

	/** The document type. */
	private String Tipo;

	/** The keyword text. */
	private Double valor;

	/** The filing status. */
	private Double porcentagem;

	/** The is action required. */
	private Boolean isMensal;

	private Boolean isSistema;

	/** The note text. */
	private String noteText;

	/**
	 * The Constructor.
	 */
	public Eventos()
	{

	}

	public Eventos(Integer id)
	{
		super();
		this.id = id;
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
	 * @return the dataList
	 */
	public List<EventoMesApp> getDataList()
	{
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<EventoMesApp> dataList)
	{
		this.dataList = dataList;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao()
	{
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo()
	{
		return Tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo)
	{
		Tipo = tipo;
	}

	/**
	 * @return the valor
	 */
	public Double getValor()
	{
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor)
	{
		this.valor = valor;
	}

	/**
	 * @return the porcentagem
	 */
	public Double getPorcentagem()
	{
		return porcentagem;
	}

	/**
	 * @param porcentagem the porcentagem to set
	 */
	public void setPorcentagem(Double porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	/**
	 * @return the isMensal
	 */
	public Boolean getIsMensal()
	{
		return isMensal;
	}

	/**
	 * @param isMensal the isMensal to set
	 */
	public void setIsMensal(Boolean isMensal)
	{
		this.isMensal = isMensal;
	}

	/**
	 * @return the isSistema
	 */
	public Boolean getIsSistema()
	{
		return isSistema;
	}

	/**
	 * @param isSistema the isSistema to set
	 */
	public void setIsSistema(Boolean isSistema)
	{
		this.isSistema = isSistema;
	}

	/**
	 * @return the noteText
	 */
	public String getNoteText()
	{
		return noteText;
	}

	/**
	 * @param noteText the noteText to set
	 */
	public void setNoteText(String noteText)
	{
		this.noteText = noteText;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Eventos [getId()=" + getId() + ", getDataList()=" + getDataList() + ", getCodigo()=" + getCodigo()
				+ ", getDescricao()=" + getDescricao() + ", getTipo()=" + getTipo() + ", getValor()=" + getValor()
				+ ", getPorcentagem()=" + getPorcentagem() + ", getIsMensal()=" + getIsMensal() + ", getIsSistema()="
				+ getIsSistema() + ", getNoteText()=" + getNoteText() + ", getTabelaEnumValue()="
				+ getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()="
				+ getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()=" + getType()
				+ ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()="
				+ getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite() + ", toString()="
				+ super.toString() + ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}