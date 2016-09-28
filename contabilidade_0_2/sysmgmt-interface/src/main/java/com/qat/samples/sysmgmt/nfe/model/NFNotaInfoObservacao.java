/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoObservacao extends NFBase
{

    /** The econtabil identificacaoCampo for the NFNotaInfoObservacao. */
    private String identificacaocampo;

    /** The econtabil conteudoCampo for the NFNotaInfoObservacao. */
    private String conteudocampo;



    /**
     * Default constructor.
     */
    public NFNotaInfoObservacao()
    {
        super();
    }


    /**
     * Gets the identificacaocampo.
     *
     * @return the identificacaocampo
     */
    /**
     * Gets the identificacaocampo.
     *
     * @return the identificacaocampo
     */
    public String getIdentificacaoCampo()
    {
        return identificacaocampo;
    }

    /**
     * Sets the identificacaocampo.
     *
* @param id the identificacaocampo to set
 */
public void setIdentificacaoCampo(String identificacaocampo)
{
        this.identificacaocampo = identificacaocampo;
    }

    /**
     * Gets the conteudocampo.
     *
     * @return the conteudocampo
     */
    /**
     * Gets the conteudocampo.
     *
     * @return the conteudocampo
     */
    public String getConteudoCampo()
    {
        return conteudocampo;
    }

    /**
     * Sets the conteudocampo.
     *
* @param id the conteudocampo to set
 */
public void setConteudoCampo(String conteudocampo)
{
        this.conteudocampo = conteudocampo;
    }


	public String getIdentificacaocampo() {
		return identificacaocampo;
	}


	public void setIdentificacaocampo(String identificacaocampo) {
		this.identificacaocampo = identificacaocampo;
	}


	public String getConteudocampo() {
		return conteudocampo;
	}


	public void setConteudocampo(String conteudocampo) {
		this.conteudocampo = conteudocampo;
	}


	@Override
	public String toString() {
		return "NFNotaInfoObservacao [getIdentificacaoCampo()=" + getIdentificacaoCampo() + ", getConteudoCampo()="
				+ getConteudoCampo() + ", getIdentificacaocampo()=" + getIdentificacaocampo() + ", getConteudocampo()="
				+ getConteudocampo() + ", toString()=" + super.toString() + "]";
	}


 }
