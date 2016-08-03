package com.qat.samples.sysmgmt.dp.model;

import java.util.List;

import com.qat.samples.sysmgmt.util.model.DoisValores;
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
	private DoisValores Tipo;

	/** The keyword text. */
	private Double valor;

	/** The filing status. */
	private Double porcentagem;

	/** The is action required. */
	private Integer isMensal;

	private Integer isSistema;


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
		this.valor = valor;
		this.porcentagem = porcentagem;
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

	public DoisValores getTipo() {
		return Tipo;
	}

	public void setTipo(DoisValores tipo) {
		Tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public Integer getIsMensal() {
		return isMensal;
	}

	public void setIsMensal(Integer isMensal) {
		this.isMensal = isMensal;
	}

	public Integer getIsSistema() {
		return isSistema;
	}

	public void setIsSistema(Integer isSistema) {
		this.isSistema = isSistema;
	}

	@Override
	public String toString() {
		return "Eventos [getId()=" + getId() + ", getNome()=" + getNome() + ", getDataList()=" + getDataList()
				+ ", getCodigo()=" + getCodigo() + ", getDescricao()=" + getDescricao() + ", getTipo()=" + getTipo()
				+ ", getValor()=" + getValor() + ", getPorcentagem()=" + getPorcentagem() + ", getIsMensal()="
				+ getIsMensal() + ", getIsSistema()=" + getIsSistema() + ", toString()=" + super.toString() + "]";
	}



}