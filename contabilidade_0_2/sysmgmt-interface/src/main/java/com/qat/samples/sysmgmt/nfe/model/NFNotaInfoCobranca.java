/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoCobranca extends NFBase
{

    /** The econtabil fatura for the NFNotaInfoCobranca. */
    private NFNotaInfoFatura fatura;

    /** The econtabil duplicatas for the NFNotaInfoCobranca. */
    private NFNotaInfoDuplicata duplicatas;



    /**
     * Default constructor.
     */
    public NFNotaInfoCobranca()
    {
        super();
    }


    /**
     * Gets the fatura.
     *
     * @return the fatura
     */
    /**
     * Gets the fatura.
     *
     * @return the fatura
     */
    public NFNotaInfoFatura getFatura()
    {
        return fatura;
    }

    /**
     * Sets the fatura.
     *
* @param id the fatura to set
 */
public void setFatura(NFNotaInfoFatura fatura)
{
        this.fatura = fatura;
    }

    /**
     * Gets the duplicatas.
     *
     * @return the duplicatas
     */
    /**
     * Gets the duplicatas.
     *
     * @return the duplicatas
     */
    public NFNotaInfoDuplicata getDuplicatas()
    {
        return duplicatas;
    }

    /**
     * Sets the duplicatas.
     *
* @param id the duplicatas to set
 */
public void setDuplicatas(NFNotaInfoDuplicata duplicatas)
{
        this.duplicatas = duplicatas;
    }


	@Override
	public String toString() {
		return "NFNotaInfoCobranca [getFatura()=" + getFatura() + ", getDuplicatas()=" + getDuplicatas()
				+ ", toString()=" + super.toString() + "]";
	}





 }
