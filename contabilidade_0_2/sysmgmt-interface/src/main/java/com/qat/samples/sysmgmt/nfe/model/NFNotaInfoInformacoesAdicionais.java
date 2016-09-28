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
    private List<List<NFNotaInfoObservacao>> observacoescontribuinte;

    /** The econtabil observacoesFisco for the NFNotaInfoInformacoesAdicionais. */
    private List<List<NFNotaInfoObservacao>> observacoesfisco;

    /** The econtabil processosRefenciado for the NFNotaInfoInformacoesAdicionais. */
    private List<List<NFNotaInfoProcessoReferenciado>> processosrefenciado;



    /**
     * Default constructor.
     */
    public NFNotaInfoInformacoesAdicionais()
    {
        super();
    }


    /**
     * Gets the informacoesadicionaisinteressefisco.
     *
     * @return the informacoesadicionaisinteressefisco
     */
    /**
     * Gets the informacoesadicionaisinteressefisco.
     *
     * @return the informacoesadicionaisinteressefisco
     */
    public String getInformacoesAdicionaisInteresseFisco()
    {
        return informacoesadicionaisinteressefisco;
    }

    /**
     * Sets the informacoesadicionaisinteressefisco.
     *
* @param id the informacoesadicionaisinteressefisco to set
 */
public void setInformacoesAdicionaisInteresseFisco(String informacoesadicionaisinteressefisco)
{
        this.informacoesadicionaisinteressefisco = informacoesadicionaisinteressefisco;
    }

    /**
     * Gets the informacoescomplementaresinteressecontribuinte.
     *
     * @return the informacoescomplementaresinteressecontribuinte
     */
    /**
     * Gets the informacoescomplementaresinteressecontribuinte.
     *
     * @return the informacoescomplementaresinteressecontribuinte
     */
    public String getInformacoesComplementaresInteresseContribuinte()
    {
        return informacoescomplementaresinteressecontribuinte;
    }

    /**
     * Sets the informacoescomplementaresinteressecontribuinte.
     *
* @param id the informacoescomplementaresinteressecontribuinte to set
 */
public void setInformacoesComplementaresInteresseContribuinte(String informacoescomplementaresinteressecontribuinte)
{
        this.informacoescomplementaresinteressecontribuinte = informacoescomplementaresinteressecontribuinte;
    }

    /**
     * Gets the observacoescontribuinte.
     *
     * @return the observacoescontribuinte
     */
    /**
     * Gets the observacoescontribuinte.
     *
     * @return the observacoescontribuinte
     */
    public List<List<NFNotaInfoObservacao>> getObservacoesContribuinte()
    {
        return observacoescontribuinte;
    }

    /**
     * Sets the observacoescontribuinte.
     *
* @param id the observacoescontribuinte to set
 */
public void setObservacoesContribuinte(List<List<NFNotaInfoObservacao>> observacoescontribuinte)
{
        this.observacoescontribuinte = observacoescontribuinte;
    }

    /**
     * Gets the observacoesfisco.
     *
     * @return the observacoesfisco
     */
    /**
     * Gets the observacoesfisco.
     *
     * @return the observacoesfisco
     */
    public List<List<NFNotaInfoObservacao>> getObservacoesFisco()
    {
        return observacoesfisco;
    }

    /**
     * Sets the observacoesfisco.
     *
* @param id the observacoesfisco to set
 */
public void setObservacoesFisco(List<List<NFNotaInfoObservacao>> observacoesfisco)
{
        this.observacoesfisco = observacoesfisco;
    }

    /**
     * Gets the processosrefenciado.
     *
     * @return the processosrefenciado
     */
    /**
     * Gets the processosrefenciado.
     *
     * @return the processosrefenciado
     */
    public List<List<NFNotaInfoProcessoReferenciado>> getProcessosRefenciado()
    {
        return processosrefenciado;
    }

    /**
     * Sets the processosrefenciado.
     *
* @param id the processosrefenciado to set
 */
public void setProcessosRefenciado(List<List<NFNotaInfoProcessoReferenciado>> processosrefenciado)
{
        this.processosrefenciado = processosrefenciado;
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


	public List<List<NFNotaInfoObservacao>> getObservacoescontribuinte() {
		return observacoescontribuinte;
	}


	public void setObservacoescontribuinte(List<List<NFNotaInfoObservacao>> observacoescontribuinte) {
		this.observacoescontribuinte = observacoescontribuinte;
	}


	public List<List<NFNotaInfoObservacao>> getObservacoesfisco() {
		return observacoesfisco;
	}


	public void setObservacoesfisco(List<List<NFNotaInfoObservacao>> observacoesfisco) {
		this.observacoesfisco = observacoesfisco;
	}


	public List<List<NFNotaInfoProcessoReferenciado>> getProcessosrefenciado() {
		return processosrefenciado;
	}


	public void setProcessosrefenciado(List<List<NFNotaInfoProcessoReferenciado>> processosrefenciado) {
		this.processosrefenciado = processosrefenciado;
	}


	@Override
	public String toString() {
		return "NFNotaInfoInformacoesAdicionais [getInformacoesAdicionaisInteresseFisco()="
				+ getInformacoesAdicionaisInteresseFisco() + ", getInformacoesComplementaresInteresseContribuinte()="
				+ getInformacoesComplementaresInteresseContribuinte() + ", getObservacoesContribuinte()="
				+ getObservacoesContribuinte() + ", getObservacoesFisco()=" + getObservacoesFisco()
				+ ", getProcessosRefenciado()=" + getProcessosRefenciado()
				+ ", getInformacoesadicionaisinteressefisco()=" + getInformacoesadicionaisinteressefisco()
				+ ", getInformacoescomplementaresinteressecontribuinte()="
				+ getInformacoescomplementaresinteressecontribuinte() + ", getObservacoescontribuinte()="
				+ getObservacoescontribuinte() + ", getObservacoesfisco()=" + getObservacoesfisco()
				+ ", getProcessosrefenciado()=" + getProcessosrefenciado() + ", toString()=" + super.toString() + "]";
	}




 }
