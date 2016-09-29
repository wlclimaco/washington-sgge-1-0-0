/** create by system gera-java version 1.0.0 28/09/2016 12:13 : 30*/
package com.qat.samples.sysmgmt.nfe.model;


import java.util.List;

import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.util.model.DoisValores;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class NFNotaInfoIdentificacao extends NFBase
{

    /** The econtabil uf for the NFNotaInfoIdentificacao. */
    private Estado uf;

    /** The econtabil codigoRandomico for the NFNotaInfoIdentificacao. */
    private String codigorandomico;

    /** The econtabil naturezaOperacao for the NFNotaInfoIdentificacao. */
    private String naturezaoperacao;

    /** The econtabil formaPagamento for the NFNotaInfoIdentificacao. */
    private DoisValores formapagamento;

    /** The econtabil modelo for the NFNotaInfoIdentificacao. */
    private DoisValores modelo;

    /** The econtabil serie for the NFNotaInfoIdentificacao. */
    private String serie;

    /** The econtabil numeroNota for the NFNotaInfoIdentificacao. */
    private String numeronota;

    /** The econtabil dataHoraEmissao for the NFNotaInfoIdentificacao. */
    private Long datahoraemissao;

    /** The econtabil dataHoraSaidaOuEntrada for the NFNotaInfoIdentificacao. */
    private Long datahorasaidaouentrada;

    /** The econtabil tipo for the NFNotaInfoIdentificacao. */
    private DoisValores tipo;

    /** The econtabil identificadorLocalDestinoOperacao for the NFNotaInfoIdentificacao. */
    private DoisValores identificadorlocaldestinooperacao;

    /** The econtabil codigoMunicipio for the NFNotaInfoIdentificacao. */
    private String codigomunicipio;

    /** The econtabil tipoImpressao for the NFNotaInfoIdentificacao. */
    private DoisValores tipoimpressao;

    /** The econtabil tipoEmissao for the NFNotaInfoIdentificacao. */
    private DoisValores tipoemissao;

    /** The econtabil digitoVerificador for the NFNotaInfoIdentificacao. */
    private Integer digitoverificador;

    /** The econtabil ambiente for the NFNotaInfoIdentificacao. */
    private DoisValores ambiente;

    /** The econtabil finalidade for the NFNotaInfoIdentificacao. */
    private DoisValores finalidade;

    /** The econtabil operacaoConsumidorFinal for the NFNotaInfoIdentificacao. */
    private DoisValores operacaoconsumidorfinal;

    /** The econtabil indicadorPresencaComprador for the NFNotaInfoIdentificacao. */
    private DoisValores indicadorpresencacomprador;

    /** The econtabil programaEmissor for the NFNotaInfoIdentificacao. */
    private DoisValores programaemissor;

    /** The econtabil versaoEmissor for the NFNotaInfoIdentificacao. */
    private String versaoemissor;

    /** The econtabil dataHoraContigencia for the NFNotaInfoIdentificacao. */
    private Long datahoracontigencia;

    /** The econtabil justificativaEntradaContingencia for the NFNotaInfoIdentificacao. */
    private String justificativaentradacontingencia;

    /** The econtabil referenciadas for the NFNotaInfoIdentificacao. */
    private List<NFInfoReferenciada> referenciadas;



    /**
     * Default constructor.
     */
    public NFNotaInfoIdentificacao()
    {
        super();
    }


    /**
     * Gets the uf.
     *
     * @return the uf
     */
    /**
     * Gets the uf.
     *
     * @return the uf
     */
    public Estado getUf()
    {
        return uf;
    }

    /**
     * Sets the uf.
     *
* @param id the uf to set
 */
public void setUf(Estado uf)
{
        this.uf = uf;
    }

    /**
     * Gets the codigorandomico.
     *
     * @return the codigorandomico
     */
    /**
     * Gets the codigorandomico.
     *
     * @return the codigorandomico
     */
    public String getCodigoRandomico()
    {
        return codigorandomico;
    }

    /**
     * Sets the codigorandomico.
     *
* @param id the codigorandomico to set
 */
public void setCodigoRandomico(String codigorandomico)
{
        this.codigorandomico = codigorandomico;
    }

    /**
     * Gets the naturezaoperacao.
     *
     * @return the naturezaoperacao
     */
    /**
     * Gets the naturezaoperacao.
     *
     * @return the naturezaoperacao
     */
    public String getNaturezaOperacao()
    {
        return naturezaoperacao;
    }

    /**
     * Sets the naturezaoperacao.
     *
* @param id the naturezaoperacao to set
 */
public void setNaturezaOperacao(String naturezaoperacao)
{
        this.naturezaoperacao = naturezaoperacao;
    }

    /**
     * Gets the formapagamento.
     *
     * @return the formapagamento
     */
    /**
     * Gets the formapagamento.
     *
     * @return the formapagamento
     */
    public DoisValores getFormaPagamento()
    {
        return formapagamento;
    }

    /**
     * Sets the formapagamento.
     *
* @param id the formapagamento to set
 */
public void setFormaPagamento(DoisValores formapagamento)
{
        this.formapagamento = formapagamento;
    }

    /**
     * Gets the modelo.
     *
     * @return the modelo
     */
    /**
     * Gets the modelo.
     *
     * @return the modelo
     */
    public DoisValores getModelo()
    {
        return modelo;
    }

    /**
     * Sets the modelo.
     *
* @param id the modelo to set
 */
public void setModelo(DoisValores modelo)
{
        this.modelo = modelo;
    }

    /**
     * Gets the serie.
     *
     * @return the serie
     */
    /**
     * Gets the serie.
     *
     * @return the serie
     */
    public String getSerie()
    {
        return serie;
    }

    /**
     * Sets the serie.
     *
* @param id the serie to set
 */
public void setSerie(String serie)
{
        this.serie = serie;
    }

    /**
     * Gets the numeronota.
     *
     * @return the numeronota
     */
    /**
     * Gets the numeronota.
     *
     * @return the numeronota
     */
    public String getNumeroNota()
    {
        return numeronota;
    }

    /**
     * Sets the numeronota.
     *
* @param id the numeronota to set
 */
public void setNumeroNota(String numeronota)
{
        this.numeronota = numeronota;
    }

    /**
     * Gets the datahoraemissao.
     *
     * @return the datahoraemissao
     */
    /**
     * Gets the datahoraemissao.
     *
     * @return the datahoraemissao
     */
    public Long getDataHoraEmissao()
    {
        return datahoraemissao;
    }

    /**
     * Sets the datahoraemissao.
     *
* @param id the datahoraemissao to set
 */
public void setDataHoraEmissao(Long datahoraemissao)
{
        this.datahoraemissao = datahoraemissao;
    }

    /**
     * Gets the datahorasaidaouentrada.
     *
     * @return the datahorasaidaouentrada
     */
    /**
     * Gets the datahorasaidaouentrada.
     *
     * @return the datahorasaidaouentrada
     */
    public Long getDataHoraSaidaOuEntrada()
    {
        return datahorasaidaouentrada;
    }

    /**
     * Sets the datahorasaidaouentrada.
     *
* @param id the datahorasaidaouentrada to set
 */
public void setDataHoraSaidaOuEntrada(Long datahorasaidaouentrada)
{
        this.datahorasaidaouentrada = datahorasaidaouentrada;
    }

    /**
     * Gets the tipo.
     *
     * @return the tipo
     */
    /**
     * Gets the tipo.
     *
     * @return the tipo
     */
    public DoisValores getTipo()
    {
        return tipo;
    }

    /**
     * Sets the tipo.
     *
* @param id the tipo to set
 */
public void setTipo(DoisValores tipo)
{
        this.tipo = tipo;
    }

    /**
     * Gets the identificadorlocaldestinooperacao.
     *
     * @return the identificadorlocaldestinooperacao
     */
    /**
     * Gets the identificadorlocaldestinooperacao.
     *
     * @return the identificadorlocaldestinooperacao
     */
    public DoisValores getIdentificadorLocalDestinoOperacao()
    {
        return identificadorlocaldestinooperacao;
    }

    /**
     * Sets the identificadorlocaldestinooperacao.
     *
* @param id the identificadorlocaldestinooperacao to set
 */
public void setIdentificadorLocalDestinoOperacao(DoisValores identificadorlocaldestinooperacao)
{
        this.identificadorlocaldestinooperacao = identificadorlocaldestinooperacao;
    }

    /**
     * Gets the codigomunicipio.
     *
     * @return the codigomunicipio
     */
    /**
     * Gets the codigomunicipio.
     *
     * @return the codigomunicipio
     */
    public String getCodigoMunicipio()
    {
        return codigomunicipio;
    }

    /**
     * Sets the codigomunicipio.
     *
* @param id the codigomunicipio to set
 */
public void setCodigoMunicipio(String codigomunicipio)
{
        this.codigomunicipio = codigomunicipio;
    }

    /**
     * Gets the tipoimpressao.
     *
     * @return the tipoimpressao
     */
    /**
     * Gets the tipoimpressao.
     *
     * @return the tipoimpressao
     */
    public DoisValores getTipoImpressao()
    {
        return tipoimpressao;
    }

    /**
     * Sets the tipoimpressao.
     *
* @param id the tipoimpressao to set
 */
public void setTipoImpressao(DoisValores tipoimpressao)
{
        this.tipoimpressao = tipoimpressao;
    }

    /**
     * Gets the tipoemissao.
     *
     * @return the tipoemissao
     */
    /**
     * Gets the tipoemissao.
     *
     * @return the tipoemissao
     */
    public DoisValores getTipoEmissao()
    {
        return tipoemissao;
    }

    /**
     * Sets the tipoemissao.
     *
* @param id the tipoemissao to set
 */
public void setTipoEmissao(DoisValores tipoemissao)
{
        this.tipoemissao = tipoemissao;
    }

    /**
     * Gets the digitoverificador.
     *
     * @return the digitoverificador
     */
    /**
     * Gets the digitoverificador.
     *
     * @return the digitoverificador
     */
    public Integer getDigitoVerificador()
    {
        return digitoverificador;
    }

    /**
     * Sets the digitoverificador.
     *
* @param id the digitoverificador to set
 */
public void setDigitoVerificador(Integer digitoverificador)
{
        this.digitoverificador = digitoverificador;
    }

    /**
     * Gets the ambiente.
     *
     * @return the ambiente
     */
    /**
     * Gets the ambiente.
     *
     * @return the ambiente
     */
    public DoisValores getAmbiente()
    {
        return ambiente;
    }

    /**
     * Sets the ambiente.
     *
* @param id the ambiente to set
 */
public void setAmbiente(DoisValores ambiente)
{
        this.ambiente = ambiente;
    }

    /**
     * Gets the finalidade.
     *
     * @return the finalidade
     */
    /**
     * Gets the finalidade.
     *
     * @return the finalidade
     */
    public DoisValores getFinalidade()
    {
        return finalidade;
    }

    /**
     * Sets the finalidade.
     *
* @param id the finalidade to set
 */
public void setFinalidade(DoisValores finalidade)
{
        this.finalidade = finalidade;
    }

    /**
     * Gets the operacaoconsumidorfinal.
     *
     * @return the operacaoconsumidorfinal
     */
    /**
     * Gets the operacaoconsumidorfinal.
     *
     * @return the operacaoconsumidorfinal
     */
    public DoisValores getOperacaoConsumidorFinal()
    {
        return operacaoconsumidorfinal;
    }

    /**
     * Sets the operacaoconsumidorfinal.
     *
* @param id the operacaoconsumidorfinal to set
 */
public void setOperacaoConsumidorFinal(DoisValores operacaoconsumidorfinal)
{
        this.operacaoconsumidorfinal = operacaoconsumidorfinal;
    }

    /**
     * Gets the indicadorpresencacomprador.
     *
     * @return the indicadorpresencacomprador
     */
    /**
     * Gets the indicadorpresencacomprador.
     *
     * @return the indicadorpresencacomprador
     */
    public DoisValores getIndicadorPresencaComprador()
    {
        return indicadorpresencacomprador;
    }

    /**
     * Sets the indicadorpresencacomprador.
     *
* @param id the indicadorpresencacomprador to set
 */
public void setIndicadorPresencaComprador(DoisValores indicadorpresencacomprador)
{
        this.indicadorpresencacomprador = indicadorpresencacomprador;
    }

    /**
     * Gets the programaemissor.
     *
     * @return the programaemissor
     */
    /**
     * Gets the programaemissor.
     *
     * @return the programaemissor
     */
    public DoisValores getProgramaEmissor()
    {
        return programaemissor;
    }

    /**
     * Sets the programaemissor.
     *
* @param id the programaemissor to set
 */
public void setProgramaEmissor(DoisValores programaemissor)
{
        this.programaemissor = programaemissor;
    }

    /**
     * Gets the versaoemissor.
     *
     * @return the versaoemissor
     */
    /**
     * Gets the versaoemissor.
     *
     * @return the versaoemissor
     */
    public String getVersaoEmissor()
    {
        return versaoemissor;
    }

    /**
     * Sets the versaoemissor.
     *
* @param id the versaoemissor to set
 */
public void setVersaoEmissor(String versaoemissor)
{
        this.versaoemissor = versaoemissor;
    }

    /**
     * Gets the datahoracontigencia.
     *
     * @return the datahoracontigencia
     */
    /**
     * Gets the datahoracontigencia.
     *
     * @return the datahoracontigencia
     */
    public Long getDataHoraContigencia()
    {
        return datahoracontigencia;
    }

    /**
     * Sets the datahoracontigencia.
     *
* @param id the datahoracontigencia to set
 */
public void setDataHoraContigencia(Long datahoracontigencia)
{
        this.datahoracontigencia = datahoracontigencia;
    }

    /**
     * Gets the justificativaentradacontingencia.
     *
     * @return the justificativaentradacontingencia
     */
    /**
     * Gets the justificativaentradacontingencia.
     *
     * @return the justificativaentradacontingencia
     */
    public String getJustificativaEntradaContingencia()
    {
        return justificativaentradacontingencia;
    }

    /**
     * Sets the justificativaentradacontingencia.
     *
* @param id the justificativaentradacontingencia to set
 */
public void setJustificativaEntradaContingencia(String justificativaentradacontingencia)
{
        this.justificativaentradacontingencia = justificativaentradacontingencia;
    }

    /**
     * Gets the referenciadas.
     *
     * @return the referenciadas
     */
    /**
     * Gets the referenciadas.
     *
     * @return the referenciadas
     */
    public List<NFInfoReferenciada> getReferenciadas()
    {
        return referenciadas;
    }

    /**
     * Sets the referenciadas.
     *
* @param id the referenciadas to set
 */
public void setReferenciadas(List<NFInfoReferenciada> referenciadas)
{
        this.referenciadas = referenciadas;
    }


	public String getCodigorandomico() {
		return codigorandomico;
	}


	public void setCodigorandomico(String codigorandomico) {
		this.codigorandomico = codigorandomico;
	}


	public String getNaturezaoperacao() {
		return naturezaoperacao;
	}


	public void setNaturezaoperacao(String naturezaoperacao) {
		this.naturezaoperacao = naturezaoperacao;
	}


	public DoisValores getFormapagamento() {
		return formapagamento;
	}


	public void setFormapagamento(DoisValores formapagamento) {
		this.formapagamento = formapagamento;
	}


	public String getNumeronota() {
		return numeronota;
	}


	public void setNumeronota(String numeronota) {
		this.numeronota = numeronota;
	}


	public Long getDatahoraemissao() {
		return datahoraemissao;
	}


	public void setDatahoraemissao(Long datahoraemissao) {
		this.datahoraemissao = datahoraemissao;
	}


	public Long getDatahorasaidaouentrada() {
		return datahorasaidaouentrada;
	}


	public void setDatahorasaidaouentrada(Long datahorasaidaouentrada) {
		this.datahorasaidaouentrada = datahorasaidaouentrada;
	}


	public DoisValores getIdentificadorlocaldestinooperacao() {
		return identificadorlocaldestinooperacao;
	}


	public void setIdentificadorlocaldestinooperacao(DoisValores identificadorlocaldestinooperacao) {
		this.identificadorlocaldestinooperacao = identificadorlocaldestinooperacao;
	}


	public String getCodigomunicipio() {
		return codigomunicipio;
	}


	public void setCodigomunicipio(String codigomunicipio) {
		this.codigomunicipio = codigomunicipio;
	}


	public DoisValores getTipoimpressao() {
		return tipoimpressao;
	}


	public void setTipoimpressao(DoisValores tipoimpressao) {
		this.tipoimpressao = tipoimpressao;
	}


	public DoisValores getTipoemissao() {
		return tipoemissao;
	}


	public void setTipoemissao(DoisValores tipoemissao) {
		this.tipoemissao = tipoemissao;
	}


	public Integer getDigitoverificador() {
		return digitoverificador;
	}


	public void setDigitoverificador(Integer digitoverificador) {
		this.digitoverificador = digitoverificador;
	}


	public DoisValores getOperacaoconsumidorfinal() {
		return operacaoconsumidorfinal;
	}


	public void setOperacaoconsumidorfinal(DoisValores operacaoconsumidorfinal) {
		this.operacaoconsumidorfinal = operacaoconsumidorfinal;
	}


	public DoisValores getIndicadorpresencacomprador() {
		return indicadorpresencacomprador;
	}


	public void setIndicadorpresencacomprador(DoisValores indicadorpresencacomprador) {
		this.indicadorpresencacomprador = indicadorpresencacomprador;
	}


	public DoisValores getProgramaemissor() {
		return programaemissor;
	}


	public void setProgramaemissor(DoisValores programaemissor) {
		this.programaemissor = programaemissor;
	}


	public String getVersaoemissor() {
		return versaoemissor;
	}


	public void setVersaoemissor(String versaoemissor) {
		this.versaoemissor = versaoemissor;
	}


	public Long getDatahoracontigencia() {
		return datahoracontigencia;
	}


	public void setDatahoracontigencia(Long datahoracontigencia) {
		this.datahoracontigencia = datahoracontigencia;
	}


	public String getJustificativaentradacontingencia() {
		return justificativaentradacontingencia;
	}


	public void setJustificativaentradacontingencia(String justificativaentradacontingencia) {
		this.justificativaentradacontingencia = justificativaentradacontingencia;
	}


	@Override
	public String toString() {
		return "NFNotaInfoIdentificacao [getUf()=" + getUf() + ", getCodigoRandomico()=" + getCodigoRandomico()
				+ ", getNaturezaOperacao()=" + getNaturezaOperacao() + ", getFormaPagamento()=" + getFormaPagamento()
				+ ", getModelo()=" + getModelo() + ", getSerie()=" + getSerie() + ", getNumeroNota()=" + getNumeroNota()
				+ ", getDataHoraEmissao()=" + getDataHoraEmissao() + ", getDataHoraSaidaOuEntrada()="
				+ getDataHoraSaidaOuEntrada() + ", getTipo()=" + getTipo() + ", getIdentificadorLocalDestinoOperacao()="
				+ getIdentificadorLocalDestinoOperacao() + ", getCodigoMunicipio()=" + getCodigoMunicipio()
				+ ", getTipoImpressao()=" + getTipoImpressao() + ", getTipoEmissao()=" + getTipoEmissao()
				+ ", getDigitoVerificador()=" + getDigitoVerificador() + ", getAmbiente()=" + getAmbiente()
				+ ", getFinalidade()=" + getFinalidade() + ", getOperacaoConsumidorFinal()="
				+ getOperacaoConsumidorFinal() + ", getIndicadorPresencaComprador()=" + getIndicadorPresencaComprador()
				+ ", getProgramaEmissor()=" + getProgramaEmissor() + ", getVersaoEmissor()=" + getVersaoEmissor()
				+ ", getDataHoraContigencia()=" + getDataHoraContigencia() + ", getJustificativaEntradaContingencia()="
				+ getJustificativaEntradaContingencia() + ", getReferenciadas()=" + getReferenciadas()
				+ ", getCodigorandomico()=" + getCodigorandomico() + ", getNaturezaoperacao()=" + getNaturezaoperacao()
				+ ", getFormapagamento()=" + getFormapagamento() + ", getNumeronota()=" + getNumeronota()
				+ ", getDatahoraemissao()=" + getDatahoraemissao() + ", getDatahorasaidaouentrada()="
				+ getDatahorasaidaouentrada() + ", getIdentificadorlocaldestinooperacao()="
				+ getIdentificadorlocaldestinooperacao() + ", getCodigomunicipio()=" + getCodigomunicipio()
				+ ", getTipoimpressao()=" + getTipoimpressao() + ", getTipoemissao()=" + getTipoemissao()
				+ ", getDigitoverificador()=" + getDigitoverificador() + ", getOperacaoconsumidorfinal()="
				+ getOperacaoconsumidorfinal() + ", getIndicadorpresencacomprador()=" + getIndicadorpresencacomprador()
				+ ", getProgramaemissor()=" + getProgramaemissor() + ", getVersaoemissor()=" + getVersaoemissor()
				+ ", getDatahoracontigencia()=" + getDatahoracontigencia() + ", getJustificativaentradacontingencia()="
				+ getJustificativaentradacontingencia() + ", toString()=" + super.toString() + "]";
	}



 }
