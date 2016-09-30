/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;


import java.util.List;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoInformacoesAdicionais extends NFBase
{

    /** The econtabil informacoesAdicionaisInteresseFisco for the NFNotaInfoInformacoesAdicionais. */
    private String informacoesadicionaisinteressefisco;

    /** The econtabil informacoesComplementaresInteresseContribuinte for the NFNotaInfoInformacoesAdicionais. */
    private String informacoescomplementaresinteressecontribuinte;

    /** The econtabil observacoesContribuinte for the NFNotaInfoInformacoesAdicionais. */
    private List<NFNotaInfoObservacao> observacoescontribuinte;

    /** The econtabil observacoesFisco for the NFNotaInfoInformacoesAdicionais. */
    private List<NFNotaInfoObservacao> observacoesfisco;

    /** The econtabil processosRefenciado for the NFNotaInfoInformacoesAdicionais. */
    private List<NFNotaInfoProcessoReferenciado> processosrefenciado;



    /**
     * Default constructor.
     */
    public NFNotaInfoInformacoesAdicionais()
    {
        super();
    }



	public String getInformacoesadicionaisinteressefisco() {
		return informacoesadicionaisinteressefisco;
	}



	public void setInformacoesadicionaisinteressefisco(String informacoesadicionaisinteressefisco) {
		this.informacoesadicionaisinteressefisco = informacoesadicionaisinteressefisco;
	}



	public String getInformacoescomplementaresinteressecontribuinte() {
		return informacoescomplementaresinteressecontribuinte;
	}



	public void setInformacoescomplementaresinteressecontribuinte(String informacoescomplementaresinteressecontribuinte) {
		this.informacoescomplementaresinteressecontribuinte = informacoescomplementaresinteressecontribuinte;
	}



	public List<NFNotaInfoObservacao> getObservacoescontribuinte() {
		return observacoescontribuinte;
	}



	public void setObservacoescontribuinte(List<NFNotaInfoObservacao> observacoescontribuinte) {
		this.observacoescontribuinte = observacoescontribuinte;
	}



	public List<NFNotaInfoObservacao> getObservacoesfisco() {
		return observacoesfisco;
	}



	public void setObservacoesfisco(List<NFNotaInfoObservacao> observacoesfisco) {
		this.observacoesfisco = observacoesfisco;
	}



	public List<NFNotaInfoProcessoReferenciado> getProcessosrefenciado() {
		return processosrefenciado;
	}



	public void setProcessosrefenciado(List<NFNotaInfoProcessoReferenciado> processosrefenciado) {
		this.processosrefenciado = processosrefenciado;
	}



	@Override
	public String toString() {
		return "NFNotaInfoInformacoesAdicionais [getInformacoesadicionaisinteressefisco()="
				+ getInformacoesadicionaisinteressefisco() + ", getInformacoescomplementaresinteressecontribuinte()="
				+ getInformacoescomplementaresinteressecontribuinte() + ", getObservacoescontribuinte()="
				+ getObservacoescontribuinte() + ", getObservacoesfisco()=" + getObservacoesfisco()
				+ ", getProcessosrefenciado()=" + getProcessosrefenciado() + ", toString()=" + super.toString() + "]";
	}


 }
