/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

import com.qat.samples.sysmgmt.util.model.DoisValores;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoDuplicata extends NFBase
{

    /** The econtabil numeroDuplicata for the NFNotaInfoDuplicata. */
    private String numeroDuplicata;

    /** The econtabil dataVencimento for the NFNotaInfoDuplicata. */
    private Long dataVencimento;

    /** The econtabil valorDuplicata for the NFNotaInfoDuplicata. */
    private String valorDuplicata;

    private String receberAgora;

    private DoisValores tipoDoc;



    /**
     * Default constructor.
     */
    public NFNotaInfoDuplicata()
    {
        super();
    }



	public String getNumeroDuplicata() {
		return numeroDuplicata;
	}



	public void setNumeroDuplicata(String numeroDuplicata) {
		this.numeroDuplicata = numeroDuplicata;
	}



	public Long getDataVencimento() {
		return dataVencimento;
	}



	public void setDataVencimento(Long dataVencimento) {
		this.dataVencimento = dataVencimento;
	}



	public String getValorDuplicata() {
		return valorDuplicata;
	}



	public void setValorDuplicata(String valorDuplicata) {
		this.valorDuplicata = valorDuplicata;
	}



	public String getReceberAgora() {
		return receberAgora;
	}



	public void setReceberAgora(String receberAgora) {
		this.receberAgora = receberAgora;
	}



	public DoisValores getTipoDoc() {
		return tipoDoc;
	}



	public void setTipoDoc(DoisValores tipoDoc) {
		this.tipoDoc = tipoDoc;
	}



	@Override
	public String toString() {
		return "NFNotaInfoDuplicata [getNumeroDuplicata()=" + getNumeroDuplicata() + ", getDataVencimento()="
				+ getDataVencimento() + ", getValorDuplicata()=" + getValorDuplicata() + ", getReceberAgora()="
				+ getReceberAgora() + ", getTipoDoc()=" + getTipoDoc() + ", toString()=" + super.toString() + "]";
	}






 }
