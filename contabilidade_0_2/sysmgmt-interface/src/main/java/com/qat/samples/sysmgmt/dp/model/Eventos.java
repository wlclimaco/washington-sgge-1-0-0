package com.qat.samples.sysmgmt.dp.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class Eventos extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private String nome;

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

	public Eventos(Integer id, String nome, List<EventoMesApp> dataList, String descricao, String codigo, String tipo,
			Double valor, Double porcentagem, Boolean isMensal, Boolean isSistema, String noteText,
			PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.dataList = dataList;
		this.descricao = descricao;
		this.codigo = codigo;
		Tipo = tipo;
		this.valor = valor;
		this.porcentagem = porcentagem;
		this.isMensal = isMensal;
		this.isSistema = isSistema;
		this.noteText = noteText;
		setModelAction(modelAction);
	}

	public Eventos(Integer id)
	{
		super();
		this.id = id;
	}

	public Eventos(int i, String string) {
		// TODO Auto-generated constructor stub
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
	 * @return the nome
	 */
	public String getNome()
	{
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
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

	@Override
	public String toString()
	{
		return "Eventos [getId()=" + getId() + ", getNome()=" + getNome() + ", getDataList()=" + getDataList()
				+ ", getCodigo()=" + getCodigo() + ", getDescricao()=" + getDescricao() + ", getTipo()=" + getTipo()
				+ ", getValor()=" + getValor() + ", getPorcentagem()=" + getPorcentagem() + ", getIsMensal()="
				+ getIsMensal() + ", getIsSistema()=" + getIsSistema() + ", getNoteText()=" + getNoteText()
				+ ", toString()=" + super.toString() + "]";
	}

}