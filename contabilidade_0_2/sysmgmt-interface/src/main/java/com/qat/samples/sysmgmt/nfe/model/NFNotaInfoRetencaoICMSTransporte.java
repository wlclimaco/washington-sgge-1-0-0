/** create by system gera-java version 1.0.0 28/09/2016 14:58 : 22*/
package com.qat.samples.sysmgmt.nfe.model;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoRetencaoICMSTransporte extends NFBase
{

    /** The econtabil valorServico for the NFNotaInfoRetencaoICMSTransporte. */
    private String valorservico;

    /** The econtabil bcRetencaoICMS for the NFNotaInfoRetencaoICMSTransporte. */
    private String bcretencaoicms;

    /** The econtabil aliquotaRetencao for the NFNotaInfoRetencaoICMSTransporte. */
    private String aliquotaretencao;

    /** The econtabil valorICMSRetido for the NFNotaInfoRetencaoICMSTransporte. */
    private String valoricmsretido;

    /** The econtabil cfop for the NFNotaInfoRetencaoICMSTransporte. */
    private String cfop;

    /** The econtabil codigoMunicipioOcorrenciaFatoGeradorICMSTransporte for the NFNotaInfoRetencaoICMSTransporte. */
    private String codigomunicipioocorrenciafatogeradoricmstransporte;



    /**
     * Default constructor.
     */
    public NFNotaInfoRetencaoICMSTransporte()
    {
        super();
    }


    /**
     * Gets the valorservico.
     *
     * @return the valorservico
     */
    /**
     * Gets the valorservico.
     *
     * @return the valorservico
     */
    public String getValorServico()
    {
        return valorservico;
    }

    /**
     * Sets the valorservico.
     *
* @param id the valorservico to set
 */
public void setValorServico(String valorservico)
{
        this.valorservico = valorservico;
    }

    /**
     * Gets the bcretencaoicms.
     *
     * @return the bcretencaoicms
     */
    /**
     * Gets the bcretencaoicms.
     *
     * @return the bcretencaoicms
     */
    public String getBcRetencaoICMS()
    {
        return bcretencaoicms;
    }

    /**
     * Sets the bcretencaoicms.
     *
* @param id the bcretencaoicms to set
 */
public void setBcRetencaoICMS(String bcretencaoicms)
{
        this.bcretencaoicms = bcretencaoicms;
    }

    /**
     * Gets the aliquotaretencao.
     *
     * @return the aliquotaretencao
     */
    /**
     * Gets the aliquotaretencao.
     *
     * @return the aliquotaretencao
     */
    public String getAliquotaRetencao()
    {
        return aliquotaretencao;
    }

    /**
     * Sets the aliquotaretencao.
     *
* @param id the aliquotaretencao to set
 */
public void setAliquotaRetencao(String aliquotaretencao)
{
        this.aliquotaretencao = aliquotaretencao;
    }

    /**
     * Gets the valoricmsretido.
     *
     * @return the valoricmsretido
     */
    /**
     * Gets the valoricmsretido.
     *
     * @return the valoricmsretido
     */
    public String getValorICMSRetido()
    {
        return valoricmsretido;
    }

    /**
     * Sets the valoricmsretido.
     *
* @param id the valoricmsretido to set
 */
public void setValorICMSRetido(String valoricmsretido)
{
        this.valoricmsretido = valoricmsretido;
    }

    /**
     * Gets the cfop.
     *
     * @return the cfop
     */
    /**
     * Gets the cfop.
     *
     * @return the cfop
     */
    public String getCfop()
    {
        return cfop;
    }

    /**
     * Sets the cfop.
     *
* @param id the cfop to set
 */
public void setCfop(String cfop)
{
        this.cfop = cfop;
    }

    /**
     * Gets the codigomunicipioocorrenciafatogeradoricmstransporte.
     *
     * @return the codigomunicipioocorrenciafatogeradoricmstransporte
     */
    /**
     * Gets the codigomunicipioocorrenciafatogeradoricmstransporte.
     *
     * @return the codigomunicipioocorrenciafatogeradoricmstransporte
     */
    public String getCodigoMunicipioOcorrenciaFatoGeradorICMSTransporte()
    {
        return codigomunicipioocorrenciafatogeradoricmstransporte;
    }

    /**
     * Sets the codigomunicipioocorrenciafatogeradoricmstransporte.
     *
* @param id the codigomunicipioocorrenciafatogeradoricmstransporte to set
 */
public void setCodigoMunicipioOcorrenciaFatoGeradorICMSTransporte(String codigomunicipioocorrenciafatogeradoricmstransporte)
{
        this.codigomunicipioocorrenciafatogeradoricmstransporte = codigomunicipioocorrenciafatogeradoricmstransporte;
    }


	public String getValorservico() {
		return valorservico;
	}


	public void setValorservico(String valorservico) {
		this.valorservico = valorservico;
	}


	public String getBcretencaoicms() {
		return bcretencaoicms;
	}


	public void setBcretencaoicms(String bcretencaoicms) {
		this.bcretencaoicms = bcretencaoicms;
	}


	public String getAliquotaretencao() {
		return aliquotaretencao;
	}


	public void setAliquotaretencao(String aliquotaretencao) {
		this.aliquotaretencao = aliquotaretencao;
	}


	public String getValoricmsretido() {
		return valoricmsretido;
	}


	public void setValoricmsretido(String valoricmsretido) {
		this.valoricmsretido = valoricmsretido;
	}


	public String getCodigomunicipioocorrenciafatogeradoricmstransporte() {
		return codigomunicipioocorrenciafatogeradoricmstransporte;
	}


	public void setCodigomunicipioocorrenciafatogeradoricmstransporte(
			String codigomunicipioocorrenciafatogeradoricmstransporte) {
		this.codigomunicipioocorrenciafatogeradoricmstransporte = codigomunicipioocorrenciafatogeradoricmstransporte;
	}


	@Override
	public String toString() {
		return "NFNotaInfoRetencaoICMSTransporte [getValorServico()=" + getValorServico() + ", getBcRetencaoICMS()="
				+ getBcRetencaoICMS() + ", getAliquotaRetencao()=" + getAliquotaRetencao() + ", getValorICMSRetido()="
				+ getValorICMSRetido() + ", getCfop()=" + getCfop()
				+ ", getCodigoMunicipioOcorrenciaFatoGeradorICMSTransporte()="
				+ getCodigoMunicipioOcorrenciaFatoGeradorICMSTransporte() + ", getValorservico()=" + getValorservico()
				+ ", getBcretencaoicms()=" + getBcretencaoicms() + ", getAliquotaretencao()=" + getAliquotaretencao()
				+ ", getValoricmsretido()=" + getValoricmsretido()
				+ ", getCodigomunicipioocorrenciafatogeradoricmstransporte()="
				+ getCodigomunicipioocorrenciafatogeradoricmstransporte() + ", toString()=" + super.toString() + "]";
	}




 }
