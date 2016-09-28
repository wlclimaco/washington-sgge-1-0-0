/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoTotal extends NFBase
{

    /** The econtabil icmsTotal for the NFNotaInfoTotal. */
    private NFNotaInfoICMSTotal icmstotal;

    /** The econtabil issqnTotal for the NFNotaInfoTotal. */
    private NFNotaInfoISSQNTotal issqntotal;

    /** The econtabil retencoesTributos for the NFNotaInfoTotal. */
    private NFNotaInfoRetencoesTributos retencoestributos;



    /**
     * Default constructor.
     */
    public NFNotaInfoTotal()
    {
        super();
    }


    /**
     * Gets the icmstotal.
     *
     * @return the icmstotal
     */
    /**
     * Gets the icmstotal.
     *
     * @return the icmstotal
     */
    public NFNotaInfoICMSTotal getIcmsTotal()
    {
        return icmstotal;
    }

    /**
     * Sets the icmstotal.
     *
* @param id the icmstotal to set
 */
public void setIcmsTotal(NFNotaInfoICMSTotal icmstotal)
{
        this.icmstotal = icmstotal;
    }

    /**
     * Gets the issqntotal.
     *
     * @return the issqntotal
     */
    /**
     * Gets the issqntotal.
     *
     * @return the issqntotal
     */
    public NFNotaInfoISSQNTotal getIssqnTotal()
    {
        return issqntotal;
    }

    /**
     * Sets the issqntotal.
     *
* @param id the issqntotal to set
 */
public void setIssqnTotal(NFNotaInfoISSQNTotal issqntotal)
{
        this.issqntotal = issqntotal;
    }

    /**
     * Gets the retencoestributos.
     *
     * @return the retencoestributos
     */
    /**
     * Gets the retencoestributos.
     *
     * @return the retencoestributos
     */
    public NFNotaInfoRetencoesTributos getRetencoesTributos()
    {
        return retencoestributos;
    }

    /**
     * Sets the retencoestributos.
     *
* @param id the retencoestributos to set
 */
public void setRetencoesTributos(NFNotaInfoRetencoesTributos retencoestributos)
{
        this.retencoestributos = retencoestributos;
    }


	public NFNotaInfoICMSTotal getIcmstotal() {
		return icmstotal;
	}


	public void setIcmstotal(NFNotaInfoICMSTotal icmstotal) {
		this.icmstotal = icmstotal;
	}


	public NFNotaInfoISSQNTotal getIssqntotal() {
		return issqntotal;
	}


	public void setIssqntotal(NFNotaInfoISSQNTotal issqntotal) {
		this.issqntotal = issqntotal;
	}


	public NFNotaInfoRetencoesTributos getRetencoestributos() {
		return retencoestributos;
	}


	public void setRetencoestributos(NFNotaInfoRetencoesTributos retencoestributos) {
		this.retencoestributos = retencoestributos;
	}


	@Override
	public String toString() {
		return "NFNotaInfoTotal [getIcmsTotal()=" + getIcmsTotal() + ", getIssqnTotal()=" + getIssqnTotal()
				+ ", getRetencoesTributos()=" + getRetencoesTributos() + ", getIcmstotal()=" + getIcmstotal()
				+ ", getIssqntotal()=" + getIssqntotal() + ", getRetencoestributos()=" + getRetencoestributos()
				+ ", toString()=" + super.toString() + "]";
	}





 }
