/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;


import com.qat.samples.sysmgmt.util.model.DoisValores;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoProcessoReferenciado extends NFBase
{

    /** The econtabil identificadorProcessoOuAtoConcessorio for the NFNotaInfoProcessoReferenciado. */
    private String identificadorprocessoouatoconcessorio;

    /** The econtabil indicadorOrigemProcesso for the NFNotaInfoProcessoReferenciado. */
    private DoisValores indicadororigemprocesso;



    /**
     * Default constructor.
     */
    public NFNotaInfoProcessoReferenciado()
    {
        super();
    }


    /**
     * Gets the identificadorprocessoouatoconcessorio.
     *
     * @return the identificadorprocessoouatoconcessorio
     */
    /**
     * Gets the identificadorprocessoouatoconcessorio.
     *
     * @return the identificadorprocessoouatoconcessorio
     */
    public String getIdentificadorProcessoOuAtoConcessorio()
    {
        return identificadorprocessoouatoconcessorio;
    }

    /**
     * Sets the identificadorprocessoouatoconcessorio.
     *
* @param id the identificadorprocessoouatoconcessorio to set
 */
public void setIdentificadorProcessoOuAtoConcessorio(String identificadorprocessoouatoconcessorio)
{
        this.identificadorprocessoouatoconcessorio = identificadorprocessoouatoconcessorio;
    }

    /**
     * Gets the indicadororigemprocesso.
     *
     * @return the indicadororigemprocesso
     */
    /**
     * Gets the indicadororigemprocesso.
     *
     * @return the indicadororigemprocesso
     */
    public DoisValores getIndicadorOrigemProcesso()
    {
        return indicadororigemprocesso;
    }

    /**
     * Sets the indicadororigemprocesso.
     *
* @param id the indicadororigemprocesso to set
 */
public void setIndicadorOrigemProcesso(DoisValores indicadororigemprocesso)
{
        this.indicadororigemprocesso = indicadororigemprocesso;
    }


	public String getIdentificadorprocessoouatoconcessorio() {
		return identificadorprocessoouatoconcessorio;
	}


	public void setIdentificadorprocessoouatoconcessorio(String identificadorprocessoouatoconcessorio) {
		this.identificadorprocessoouatoconcessorio = identificadorprocessoouatoconcessorio;
	}


	public DoisValores getIndicadororigemprocesso() {
		return indicadororigemprocesso;
	}


	public void setIndicadororigemprocesso(DoisValores indicadororigemprocesso) {
		this.indicadororigemprocesso = indicadororigemprocesso;
	}


	@Override
	public String toString() {
		return "NFNotaInfoProcessoReferenciado [getIdentificadorProcessoOuAtoConcessorio()="
				+ getIdentificadorProcessoOuAtoConcessorio() + ", getIndicadorOrigemProcesso()="
				+ getIndicadorOrigemProcesso() + ", getIdentificadorprocessoouatoconcessorio()="
				+ getIdentificadorprocessoouatoconcessorio() + ", getIndicadororigemprocesso()="
				+ getIndicadororigemprocesso() + ", toString()=" + super.toString() + "]";
	}



 }
