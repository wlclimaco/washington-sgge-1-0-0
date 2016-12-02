/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;


import java.util.List;

import com.qat.samples.sysmgmt.util.model.DoisValores;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoTransporte extends NFBase
{

    /** The econtabil modalidadeFrete for the NFNotaInfoTransporte. */
    private DoisValores modalidadefrete;

    /** The econtabil transportador for the NFNotaInfoTransporte. */
    private NFNotaInfoTransportador transportador;

    /** The econtabil icmsTransporte for the NFNotaInfoTransporte. */
    private NFNotaInfoRetencaoICMSTransporte icmstransporte;

    /** The econtabil veiculo for the NFNotaInfoTransporte. */
    private NFNotaInfoVeiculo veiculo;

    /** The econtabil reboques for the NFNotaInfoTransporte. */
    private List<NFNotaInfoReboque> reboques;

    /** The econtabil vagao for the NFNotaInfoTransporte. */
    private String vagao;

    /** The econtabil balsa for the NFNotaInfoTransporte. */
    private String balsa;

    private Double quantVol;
    private String especieVol;
    private String marcaVol;
    private String numeroVol;
    private Double pesoLiquido;
    private Double pesoBruto;
    private String numeroLacres;


    /**
     * Default constructor.
     */
    public NFNotaInfoTransporte()
    {
        super();
    }


    /**
     * Gets the modalidadefrete.
     *
     * @return the modalidadefrete
     */
    /**
     * Gets the modalidadefrete.
     *
     * @return the modalidadefrete
     */
    public DoisValores getModalidadeFrete()
    {
        return modalidadefrete;
    }

    /**
     * Sets the modalidadefrete.
     *
* @param id the modalidadefrete to set
 */
public void setModalidadeFrete(DoisValores modalidadefrete)
{
        this.modalidadefrete = modalidadefrete;
    }

    /**
     * Gets the transportador.
     *
     * @return the transportador
     */
    /**
     * Gets the transportador.
     *
     * @return the transportador
     */
    public NFNotaInfoTransportador getTransportador()
    {
        return transportador;
    }

    /**
     * Sets the transportador.
     *
* @param id the transportador to set
 */
public void setTransportador(NFNotaInfoTransportador transportador)
{
        this.transportador = transportador;
    }

    /**
     * Gets the icmstransporte.
     *
     * @return the icmstransporte
     */
    /**
     * Gets the icmstransporte.
     *
     * @return the icmstransporte
     */
    public NFNotaInfoRetencaoICMSTransporte getIcmsTransporte()
    {
        return icmstransporte;
    }

    /**
     * Sets the icmstransporte.
     *
* @param id the icmstransporte to set
 */
public void setIcmsTransporte(NFNotaInfoRetencaoICMSTransporte icmstransporte)
{
        this.icmstransporte = icmstransporte;
    }

    /**
     * Gets the veiculo.
     *
     * @return the veiculo
     */
    /**
     * Gets the veiculo.
     *
     * @return the veiculo
     */
    public NFNotaInfoVeiculo getVeiculo()
    {
        return veiculo;
    }

    /**
     * Sets the veiculo.
     *
* @param id the veiculo to set
 */
public void setVeiculo(NFNotaInfoVeiculo veiculo)
{
        this.veiculo = veiculo;
    }

    /**
     * Gets the reboques.
     *
     * @return the reboques
     */
    /**
     * Gets the reboques.
     *
     * @return the reboques
     */
    public List<NFNotaInfoReboque> getReboques()
    {
        return reboques;
    }

    /**
     * Sets the reboques.
     *
* @param id the reboques to set
 */
public void setReboques(List<NFNotaInfoReboque> reboques)
{
        this.reboques = reboques;
    }

    /**
     * Gets the vagao.
     *
     * @return the vagao
     */
    /**
     * Gets the vagao.
     *
     * @return the vagao
     */
    public String getVagao()
    {
        return vagao;
    }

    /**
     * Sets the vagao.
     *
* @param id the vagao to set
 */
public void setVagao(String vagao)
{
        this.vagao = vagao;
    }

    /**
     * Gets the balsa.
     *
     * @return the balsa
     */
    /**
     * Gets the balsa.
     *
     * @return the balsa
     */
    public String getBalsa()
    {
        return balsa;
    }

    /**
     * Sets the balsa.
     *
* @param id the balsa to set
 */
public void setBalsa(String balsa)
{
        this.balsa = balsa;
    }


	public NFNotaInfoRetencaoICMSTransporte getIcmstransporte() {
		return icmstransporte;
	}


	public void setIcmstransporte(NFNotaInfoRetencaoICMSTransporte icmstransporte) {
		this.icmstransporte = icmstransporte;
	}


	public DoisValores getModalidadefrete() {
		return modalidadefrete;
	}


	public void setModalidadefrete(DoisValores modalidadefrete) {
		this.modalidadefrete = modalidadefrete;
	}


	public Double getQuantVol() {
		return quantVol;
	}


	public void setQuantVol(Double quantVol) {
		this.quantVol = quantVol;
	}


	public String getEspecieVol() {
		return especieVol;
	}


	public void setEspecieVol(String especieVol) {
		this.especieVol = especieVol;
	}


	public String getMarcaVol() {
		return marcaVol;
	}


	public void setMarcaVol(String marcaVol) {
		this.marcaVol = marcaVol;
	}


	public String getNumeroVol() {
		return numeroVol;
	}


	public void setNumeroVol(String numeroVol) {
		this.numeroVol = numeroVol;
	}


	public Double getPesoLiquido() {
		return pesoLiquido;
	}


	public void setPesoLiquido(Double pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}


	public Double getPesoBruto() {
		return pesoBruto;
	}


	public void setPesoBruto(Double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}


	public String getNumeroLacres() {
		return numeroLacres;
	}


	public void setNumeroLacres(String numeroLacres) {
		this.numeroLacres = numeroLacres;
	}


	@Override
	public String toString() {
		return "NFNotaInfoTransporte [getModalidadeFrete()=" + getModalidadeFrete() + ", getTransportador()="
				+ getTransportador() + ", getIcmsTransporte()=" + getIcmsTransporte() + ", getVeiculo()=" + getVeiculo()
				+ ", getReboques()=" + getReboques() + ", getVagao()=" + getVagao() + ", getBalsa()=" + getBalsa()
				+ ", getIcmstransporte()=" + getIcmstransporte() + ", getModalidadefrete()=" + getModalidadefrete()
				+ ", getQuantVol()=" + getQuantVol() + ", getEspecieVol()=" + getEspecieVol() + ", getMarcaVol()="
				+ getMarcaVol() + ", getNumeroVol()=" + getNumeroVol() + ", getPesoLiquido()=" + getPesoLiquido()
				+ ", getPesoBruto()=" + getPesoBruto() + ", getNumeroLacres()=" + getNumeroLacres() + ", toString()="
				+ super.toString() + "]";
	}

 }
