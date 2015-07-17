package com.prosperitasglobal.sendsolv.model;

/**
 * The Class Document represents a generic formal business or personal document, such as driver's license or bylaws.
 */
@SuppressWarnings("serial")
public class BancoPessoa extends ModelCosmeDamiao
{

	/** Attributes. */
	private Integer id;

	private String numCont;

	private Double saldo;

	private Banco bancoId;

	private Agencia agenciaId;

	public BancoPessoa(Integer id, PersistanceActionEnum mode)
	{
		super();
		this.id = id;
		setModelAction(mode);
	}

	/**
	 * The Constructor.
	 */
	public BancoPessoa()
	{

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
	 * @return the numCont
	 */
	public String getNumCont()
	{
		return numCont;
	}

	/**
	 * @param numCont the numCont to set
	 */
	public void setNumCont(String numCont)
	{
		this.numCont = numCont;
	}

	/**
	 * @return the saldo
	 */
	public Double getSaldo()
	{
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Double saldo)
	{
		this.saldo = saldo;
	}

	/**
	 * @return the bancoId
	 */
	public Banco getBancoId()
	{
		return bancoId;
	}

	/**
	 * @param bancoId the bancoId to set
	 */
	public void setBancoId(Banco bancoId)
	{
		this.bancoId = bancoId;
	}

	/**
	 * @return the agenciaId
	 */
	public Agencia getAgenciaId()
	{
		return agenciaId;
	}

	/**
	 * @param agenciaId the agenciaId to set
	 */
	public void setAgenciaId(Agencia agenciaId)
	{
		this.agenciaId = agenciaId;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BancoPessoa [getId()=" + getId() + ", getNumCont()=" + getNumCont() + ", getSaldo()=" + getSaldo()
				+ ", getBancoId()=" + getBancoId() + ", getAgenciaId()=" + getAgenciaId() + ", getTabelaEnumValue()="
				+ getTabelaEnumValue() + ", getTypeValue()=" + getTypeValue() + ", getAcaoEnumValue()="
				+ getAcaoEnumValue() + ", getParentId()=" + getParentId() + ", getType()=" + getType()
				+ ", getAcaoType()=" + getAcaoType() + ", getTabelaEnum()=" + getTabelaEnum() + ", getStatusList()="
				+ getStatusList() + ", getEmprId()=" + getEmprId() + ", getSite()=" + getSite() + ", getProcessId()="
				+ getProcessId() + ", getUserId()=" + getUserId() + ", toString()=" + super.toString()
				+ ", getModelAction()=" + getModelAction() + ", getCreateUser()=" + getCreateUser()
				+ ", getCreateDateUTC()=" + getCreateDateUTC() + ", getModifyUser()=" + getModifyUser()
				+ ", getModifyDateUTC()=" + getModifyDateUTC() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
}