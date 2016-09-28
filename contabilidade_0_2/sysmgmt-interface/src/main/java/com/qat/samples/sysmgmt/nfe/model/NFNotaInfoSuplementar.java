/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoSuplementar extends NFBase
{

    /** The econtabil qrCode for the NFNotaInfoSuplementar. */
    private String qrcode;



    /**
     * Default constructor.
     */
    public NFNotaInfoSuplementar()
    {
        super();
    }


    /**
     * Gets the qrcode.
     *
     * @return the qrcode
     */
    /**
     * Gets the qrcode.
     *
     * @return the qrcode
     */
    public String getQrCode()
    {
        return qrcode;
    }

    /**
     * Sets the qrcode.
     *
* @param id the qrcode to set
 */
public void setQrCode(String qrcode)
{
        this.qrcode = qrcode;
    }


	public String getQrcode() {
		return qrcode;
	}


	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}


	@Override
	public String toString() {
		return "NFNotaInfoSuplementar [getQrCode()=" + getQrCode() + ", getQrcode()=" + getQrcode() + ", toString()="
				+ super.toString() + "]";
	}





 }
