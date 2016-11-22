/** create by system gera-java version 1.0.0 28/09/2016 12:13 : 30*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.util.model.DoisValores;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNota extends NFBase
{

    /** The econtabil identificadorLocal for the NFNota. */
    private long identificadorlocal;

    /** The econtabil info for the NFNota. */
    private NFNotaInfo info;

    /** The econtabil infoSuplementar for the NFNota. */
    private NFNotaInfoSuplementar infosuplementar;

    /** The econtabil assinatura for the NFNota. */
    private DoisValores assinatura;

    private DoisValores tipo;



    /**
     * Default constructor.
     */
    public NFNota()
    {
        super();
    }


    /**
     * Gets the identificadorlocal.
     *
     * @return the identificadorlocal
     */
    /**
     * Gets the identificadorlocal.
     *
     * @return the identificadorlocal
     */
    public Long getIdentificadorLocal()
    {
        return identificadorlocal;
    }

    /**
     * Sets the identificadorlocal.
     *
* @param id the identificadorlocal to set
 */
public void setIdentificadorLocal(Long identificadorlocal)
{
        this.identificadorlocal = identificadorlocal;
    }

    /**
     * Gets the info.
     *
     * @return the info
     */
    /**
     * Gets the info.
     *
     * @return the info
     */
    public NFNotaInfo getInfo()
    {
        return info;
    }

    /**
     * Sets the info.
     *
* @param id the info to set
 */
public void setInfo(NFNotaInfo info)
{
        this.info = info;
    }

    /**
     * Gets the infosuplementar.
     *
     * @return the infosuplementar
     */
    /**
     * Gets the infosuplementar.
     *
     * @return the infosuplementar
     */
    public NFNotaInfoSuplementar getInfoSuplementar()
    {
        return infosuplementar;
    }

    /**
     * Sets the infosuplementar.
     *
* @param id the infosuplementar to set
 */
public void setInfoSuplementar(NFNotaInfoSuplementar infosuplementar)
{
        this.infosuplementar = infosuplementar;
    }

    /**
     * Gets the assinatura.
     *
     * @return the assinatura
     */
    /**
     * Gets the assinatura.
     *
     * @return the assinatura
     */
    public DoisValores getAssinatura()
    {
        return assinatura;
    }

    /**
     * Sets the assinatura.
     *
* @param id the assinatura to set
 */
public void setAssinatura(DoisValores assinatura)
{
        this.assinatura = assinatura;
    }


	public long getIdentificadorlocal() {
		return identificadorlocal;
	}


	public void setIdentificadorlocal(long identificadorlocal) {
		this.identificadorlocal = identificadorlocal;
	}


	public NFNotaInfoSuplementar getInfosuplementar() {
		return infosuplementar;
	}


	public void setInfosuplementar(NFNotaInfoSuplementar infosuplementar) {
		this.infosuplementar = infosuplementar;
	}


	public DoisValores getTipo() {
		return tipo;
	}


	public void setTipo(DoisValores tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "NFNota [getIdentificadorLocal()=" + getIdentificadorLocal() + ", getInfo()=" + getInfo()
				+ ", getInfoSuplementar()=" + getInfoSuplementar() + ", getAssinatura()=" + getAssinatura()
				+ ", getIdentificadorlocal()=" + getIdentificadorlocal() + ", getInfosuplementar()="
				+ getInfosuplementar() + ", getTipo()=" + getTipo() + ", toString()=" + super.toString() + "]";
	}

 }
