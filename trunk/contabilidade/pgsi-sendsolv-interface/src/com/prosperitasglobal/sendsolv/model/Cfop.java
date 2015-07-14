package com.prosperitasglobal.sendsolv.model;


/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Cfop extends ModelCosmeDamiao
{
	private Integer id;

	private String cfop;

	private String natureza;

	private String simplificado;

	private CfopTypeEnum cfopTypeEnum;

	private Double icms;

	private Double icmsReduzido;

	private Double margemAgregadaST;

	private Double cstPrincipal;

	private Double classFiscal;

	/** The cep. */
	private String observacao;

	/**
	 * Default constructor.
	 */
	public Cfop()
	{
		super();
	}

	public Cfop(Integer id, PersistanceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.modelAction(modelAction);
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
	 * @return the cfop
	 */
	public String getCfop()
	{
		return cfop;
	}

	/**
	 * @param cfop the cfop to set
	 */
	public void setCfop(String cfop)
	{
		this.cfop = cfop;
	}

	/**
	 * @return the natureza
	 */
	public String getNatureza()
	{
		return natureza;
	}

	/**
	 * @param natureza the natureza to set
	 */
	public void setNatureza(String natureza)
	{
		this.natureza = natureza;
	}

	/**
	 * @return the simplificado
	 */
	public String getSimplificado()
	{
		return simplificado;
	}

	/**
	 * @param simplificado the simplificado to set
	 */
	public void setSimplificado(String simplificado)
	{
		this.simplificado = simplificado;
	}

	/**
	 * @return the cfopTypeEnum
	 */
	public CfopTypeEnum getCfopTypeEnum()
	{
		return cfopTypeEnum;
	}

	/**
	 * @param cfopTypeEnum the cfopTypeEnum to set
	 */
	public void setCfopTypeEnum(CfopTypeEnum cfopTypeEnum)
	{
		this.cfopTypeEnum = cfopTypeEnum;
	}

	/**
	 * @return the icms
	 */
	public Double getIcms()
	{
		return icms;
	}

	/**
	 * @param icms the icms to set
	 */
	public void setIcms(Double icms)
	{
		this.icms = icms;
	}

	/**
	 * @return the icmsReduzido
	 */
	public Double getIcmsReduzido()
	{
		return icmsReduzido;
	}

	/**
	 * @param icmsReduzido the icmsReduzido to set
	 */
	public void setIcmsReduzido(Double icmsReduzido)
	{
		this.icmsReduzido = icmsReduzido;
	}

	/**
	 * @return the margemAgregadaST
	 */
	public Double getMargemAgregadaST()
	{
		return margemAgregadaST;
	}

	/**
	 * @param margemAgregadaST the margemAgregadaST to set
	 */
	public void setMargemAgregadaST(Double margemAgregadaST)
	{
		this.margemAgregadaST = margemAgregadaST;
	}

	/**
	 * @return the cstPrincipal
	 */
	public Double getCstPrincipal()
	{
		return cstPrincipal;
	}

	/**
	 * @param cstPrincipal the cstPrincipal to set
	 */
	public void setCstPrincipal(Double cstPrincipal)
	{
		this.cstPrincipal = cstPrincipal;
	}

	/**
	 * @return the classFiscal
	 */
	public Double getClassFiscal()
	{
		return classFiscal;
	}

	/**
	 * @param classFiscal the classFiscal to set
	 */
	public void setClassFiscal(Double classFiscal)
	{
		this.classFiscal = classFiscal;
	}

	/**
	 * @return the observacao
	 */
	public String getObservacao()
	{
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao)
	{
		this.observacao = observacao;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Cfop [getId()=" + getId() + ", getCfop()=" + getCfop() + ", getNatureza()=" + getNatureza()
				+ ", getSimplificado()=" + getSimplificado() + ", getCfopTypeEnum()=" + getCfopTypeEnum()
				+ ", getIcms()=" + getIcms() + ", getIcmsReduzido()=" + getIcmsReduzido() + ", getMargemAgregadaST()="
				+ getMargemAgregadaST() + ", getCstPrincipal()=" + getCstPrincipal() + ", getClassFiscal()="
				+ getClassFiscal() + ", getObservacao()=" + getObservacao() + ", getTabelaEnumValue()="
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
