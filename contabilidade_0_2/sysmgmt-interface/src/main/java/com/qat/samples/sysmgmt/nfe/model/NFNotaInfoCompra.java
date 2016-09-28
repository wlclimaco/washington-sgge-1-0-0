/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoCompra extends NFBase
{

    /** The econtabil notaDeEmpenho for the NFNotaInfoCompra. */
    private String notadeempenho;

    /** The econtabil notaDeEmpenho for the NFNotaInfoCompra. */
    private String pedido;

    /** The econtabil contrato for the NFNotaInfoCompra. */
    private String contrato;



    /**
     * Default constructor.
     */
    public NFNotaInfoCompra()
    {
        super();
    }




    public String getNotadeempenho() {
		return notadeempenho;
	}




	public void setNotadeempenho(String notadeempenho) {
		this.notadeempenho = notadeempenho;
	}




	public String getPedido() {
		return pedido;
	}




	public void setPedido(String pedido) {
		this.pedido = pedido;
	}




	/**
     * Gets the notadeempenho.
     *
     * @return the notadeempenho
     */
    /**
     * Gets the notadeempenho.
     *
     * @return the notadeempenho
     */
    public String getNotaDeEmpenho()
    {
        return notadeempenho;
    }

    /**
     * Sets the notadeempenho.
     *
* @param id the notadeempenho to set
 */
public void setNotaDeEmpenho(String notadeempenho)
{
        this.notadeempenho = notadeempenho;
    }

    /**
     * Gets the contrato.
     *
     * @return the contrato
     */
    /**
     * Gets the contrato.
     *
     * @return the contrato
     */
    public String getContrato()
    {
        return contrato;
    }

    /**
     * Sets the contrato.
     *
* @param id the contrato to set
 */
public void setContrato(String contrato)
{
        this.contrato = contrato;
    }




	@Override
	public String toString() {
		return "NFNotaInfoCompra [getNotadeempenho()=" + getNotadeempenho() + ", getPedido()=" + getPedido()
				+ ", getNotaDeEmpenho()=" + getNotaDeEmpenho() + ", getContrato()=" + getContrato() + ", toString()="
				+ super.toString() + "]";
	}




 }
