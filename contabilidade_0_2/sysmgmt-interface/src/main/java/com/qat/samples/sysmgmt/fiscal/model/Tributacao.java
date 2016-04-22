package com.qat.samples.sysmgmt.fiscal.model;

import com.qat.samples.sysmgmt.produto.model.Incidencia;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Tributacao extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private Cst cst;

	/** The description. */
	private Double icms;

	/** The estado. */
	private Double st;

	private Double mva;

	private Csosn csosn;

	private Double ipi;

	private Double iat;

	private Double ippt;

	private Double pisconfins;

	private Incidencia incidencia;

	/**
	 * Default constructor.
	 */
	public Tributacao()
	{
		super();
	}

	public Tributacao(int i, String string) {
		// TODO Auto-generated constructor stub
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the cst
	 */
	public Cst getCst()
	{
		return cst;
	}

	/**
	 * @param cst the cst to set
	 */
	public void setCst(Cst cst)
	{
		this.cst = cst;
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
	 * @return the st
	 */
	public Double getSt()
	{
		return st;
	}

	/**
	 * @param st the st to set
	 */
	public void setSt(Double st)
	{
		this.st = st;
	}

	/**
	 * @return the mva
	 */
	public Double getMva()
	{
		return mva;
	}

	/**
	 * @param mva the mva to set
	 */
	public void setMva(Double mva)
	{
		this.mva = mva;
	}

	/**
	 * @return the csosn
	 */
	public Csosn getCsosn()
	{
		return csosn;
	}

	/**
	 * @param csosn the csosn to set
	 */
	public void setCsosn(Csosn csosn)
	{
		this.csosn = csosn;
	}

	/**
	 * @return the ipi
	 */
	public Double getIpi()
	{
		return ipi;
	}

	/**
	 * @param ipi the ipi to set
	 */
	public void setIpi(Double ipi)
	{
		this.ipi = ipi;
	}

	/**
	 * @return the iat
	 */
	public Double getIat()
	{
		return iat;
	}

	/**
	 * @param iat the iat to set
	 */
	public void setIat(Double iat)
	{
		this.iat = iat;
	}

	/**
	 * @return the ippt
	 */
	public Double getIppt()
	{
		return ippt;
	}

	/**
	 * @param ippt the ippt to set
	 */
	public void setIppt(Double ippt)
	{
		this.ippt = ippt;
	}

	/**
	 * @return the pisconfins
	 */
	public Double getPisconfins()
	{
		return pisconfins;
	}

	/**
	 * @param pisconfins the pisconfins to set
	 */
	public void setPisconfins(Double pisconfins)
	{
		this.pisconfins = pisconfins;
	}

	/**
	 * @return the incidencia
	 */
	public Incidencia getIncidencia()
	{
		return incidencia;
	}

	/**
	 * @param incidencia the incidencia to set
	 */
	public void setIncidencia(Incidencia incidencia)
	{
		this.incidencia = incidencia;
	}

	@Override
	public String toString()
	{
		return "Tributacao [getId()=" + getId() + ", getCst()=" + getCst() + ", getIcms()=" + getIcms() + ", getSt()="
				+ getSt() + ", getMva()=" + getMva() + ", getCsosn()=" + getCsosn() + ", getIpi()=" + getIpi()
				+ ", getIat()=" + getIat() + ", getIppt()=" + getIppt() + ", getPisconfins()=" + getPisconfins()
				+ ", getIncidencia()=" + getIncidencia() + ", toString()=" + super.toString() + "]";
	}

}
