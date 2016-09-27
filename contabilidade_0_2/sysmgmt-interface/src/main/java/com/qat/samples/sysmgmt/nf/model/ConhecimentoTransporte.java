/** create by system gera-java version 1.0.0 27/09/2016 12:23 : 27*/
package com.qat.samples.sysmgmt.nf.model;


import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.pessoa.model.Transportador;
import com.qat.samples.sysmgmt.produto.model.Marca;
import com.qat.samples.sysmgmt.util.model.ModelCosmeDamiao;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */

@SuppressWarnings("serial")
public class ConhecimentoTransporte extends ModelCosmeDamiao
{

    /** The econtabil id for the ConhecimentoTransporte. */
    private Integer id;

    /** The econtabil IdNota for the ConhecimentoTransporte. */
    private Integer idnota;

    /** The econtabil remetente for the ConhecimentoTransporte. */
    private Integer remetente;

    /** The econtabil vrTotalMercadorias for the ConhecimentoTransporte. */
    private Double vrtotalmercadorias;

    /** The econtabil apCreIcms for the ConhecimentoTransporte. */
    private Integer apcreicms;

    /** The econtabil fretePorConta for the ConhecimentoTransporte. */
    private Integer freteporconta;

    /** The econtabil placa for the ConhecimentoTransporte. */
    private String placa;

    /** The econtabil especie for the ConhecimentoTransporte. */
    private Integer especie;

    /** The econtabil volume for the ConhecimentoTransporte. */
    private Double volume;

    /** The econtabil pesoLiquido for the ConhecimentoTransporte. */
    private Double pesoliquido;

    /** The econtabil pesoBruto for the ConhecimentoTransporte. */
    private Double pesobruto;

    /** The econtabil transportador for the ConhecimentoTransporte. */
    private Transportador transportador;

    /** The econtabil marca for the ConhecimentoTransporte. */
    private Marca marca;

    /** The econtabil estado for the ConhecimentoTransporte. */
    private Estado estado;

    /** The econtabil registroNacionalTransportadorCarga for the ConhecimentoTransporte. */
    private String registronacionaltransportadorcarga;

    /** The econtabil vagao for the ConhecimentoTransporte. */
    private String vagao;

    /** The econtabil balsa for the ConhecimentoTransporte. */
    private String balsa;



    /**
     * Default constructor.
     */
    public ConhecimentoTransporte()
    {
        super();
    }


    /**
     * Gets the id.
     *
     * @return the id
     */
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Sets the id.
     *
* @param id the id to set
 */
public void setId(Integer id)
{
        this.id = id;
    }

    /**
     * Gets the idnota.
     *
     * @return the idnota
     */
    /**
     * Gets the idnota.
     *
     * @return the idnota
     */
    public Integer getIdNota()
    {
        return idnota;
    }

    /**
     * Sets the idnota.
     *
* @param id the idnota to set
 */
public void setIdNota(Integer idnota)
{
        this.idnota = idnota;
    }

    /**
     * Gets the remetente.
     *
     * @return the remetente
     */
    /**
     * Gets the remetente.
     *
     * @return the remetente
     */
    public Integer getRemetente()
    {
        return remetente;
    }

    /**
     * Sets the remetente.
     *
* @param id the remetente to set
 */
public void setRemetente(Integer remetente)
{
        this.remetente = remetente;
    }

    /**
     * Gets the vrtotalmercadorias.
     *
     * @return the vrtotalmercadorias
     */
    /**
     * Gets the vrtotalmercadorias.
     *
     * @return the vrtotalmercadorias
     */
    public Double getVrTotalMercadorias()
    {
        return vrtotalmercadorias;
    }

    /**
     * Sets the vrtotalmercadorias.
     *
* @param id the vrtotalmercadorias to set
 */
public void setVrTotalMercadorias(Double vrtotalmercadorias)
{
        this.vrtotalmercadorias = vrtotalmercadorias;
    }

    /**
     * Gets the apcreicms.
     *
     * @return the apcreicms
     */
    /**
     * Gets the apcreicms.
     *
     * @return the apcreicms
     */
    public Integer getApCreIcms()
    {
        return apcreicms;
    }

    /**
     * Sets the apcreicms.
     *
* @param id the apcreicms to set
 */
public void setApCreIcms(Integer apcreicms)
{
        this.apcreicms = apcreicms;
    }

    /**
     * Gets the freteporconta.
     *
     * @return the freteporconta
     */
    /**
     * Gets the freteporconta.
     *
     * @return the freteporconta
     */
    public Integer getFretePorConta()
    {
        return freteporconta;
    }

    /**
     * Sets the freteporconta.
     *
* @param id the freteporconta to set
 */
public void setFretePorConta(Integer freteporconta)
{
        this.freteporconta = freteporconta;
    }

    /**
     * Gets the placa.
     *
     * @return the placa
     */
    /**
     * Gets the placa.
     *
     * @return the placa
     */
    public String getPlaca()
    {
        return placa;
    }

    /**
     * Sets the placa.
     *
* @param id the placa to set
 */
public void setPlaca(String placa)
{
        this.placa = placa;
    }

    /**
     * Gets the especie.
     *
     * @return the especie
     */
    /**
     * Gets the especie.
     *
     * @return the especie
     */
    public Integer getEspecie()
    {
        return especie;
    }

    /**
     * Sets the especie.
     *
* @param id the especie to set
 */
public void setEspecie(Integer especie)
{
        this.especie = especie;
    }

    /**
     * Gets the volume.
     *
     * @return the volume
     */
    /**
     * Gets the volume.
     *
     * @return the volume
     */
    public Double getVolume()
    {
        return volume;
    }

    /**
     * Sets the volume.
     *
* @param id the volume to set
 */
public void setVolume(Double volume)
{
        this.volume = volume;
    }

    /**
     * Gets the pesoliquido.
     *
     * @return the pesoliquido
     */
    /**
     * Gets the pesoliquido.
     *
     * @return the pesoliquido
     */
    public Double getPesoLiquido()
    {
        return pesoliquido;
    }

    /**
     * Sets the pesoliquido.
     *
* @param id the pesoliquido to set
 */
public void setPesoLiquido(Double pesoliquido)
{
        this.pesoliquido = pesoliquido;
    }

    /**
     * Gets the pesobruto.
     *
     * @return the pesobruto
     */
    /**
     * Gets the pesobruto.
     *
     * @return the pesobruto
     */
    public Double getPesoBruto()
    {
        return pesobruto;
    }

    /**
     * Sets the pesobruto.
     *
* @param id the pesobruto to set
 */
public void setPesoBruto(Double pesobruto)
{
        this.pesobruto = pesobruto;
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
    public Transportador getTransportador()
    {
        return transportador;
    }

    /**
     * Sets the transportador.
     *
* @param id the transportador to set
 */
public void setTransportador(Transportador transportador)
{
        this.transportador = transportador;
    }

    /**
     * Gets the marca.
     *
     * @return the marca
     */
    /**
     * Gets the marca.
     *
     * @return the marca
     */
    public Marca getMarca()
    {
        return marca;
    }

    /**
     * Sets the marca.
     *
* @param id the marca to set
 */
public void setMarca(Marca marca)
{
        this.marca = marca;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public Estado getEstado()
    {
        return estado;
    }

    /**
     * Sets the estado.
     *
* @param id the estado to set
 */
public void setEstado(Estado estado)
{
        this.estado = estado;
    }

    /**
     * Gets the registronacionaltransportadorcarga.
     *
     * @return the registronacionaltransportadorcarga
     */
    /**
     * Gets the registronacionaltransportadorcarga.
     *
     * @return the registronacionaltransportadorcarga
     */
    public String getRegistroNacionalTransportadorCarga()
    {
        return registronacionaltransportadorcarga;
    }

    /**
     * Sets the registronacionaltransportadorcarga.
     *
* @param id the registronacionaltransportadorcarga to set
 */
public void setRegistroNacionalTransportadorCarga(String registronacionaltransportadorcarga)
{
        this.registronacionaltransportadorcarga = registronacionaltransportadorcarga;
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


	@Override
	public String toString() {
		return "ConhecimentoTransporte [getId()=" + getId() + ", getIdNota()=" + getIdNota() + ", getRemetente()="
				+ getRemetente() + ", getVrTotalMercadorias()=" + getVrTotalMercadorias() + ", getApCreIcms()="
				+ getApCreIcms() + ", getFretePorConta()=" + getFretePorConta() + ", getPlaca()=" + getPlaca()
				+ ", getEspecie()=" + getEspecie() + ", getVolume()=" + getVolume() + ", getPesoLiquido()="
				+ getPesoLiquido() + ", getPesoBruto()=" + getPesoBruto() + ", getTransportador()=" + getTransportador()
				+ ", getMarca()=" + getMarca() + ", getEstado()=" + getEstado()
				+ ", getRegistroNacionalTransportadorCarga()=" + getRegistroNacionalTransportadorCarga()
				+ ", getVagao()=" + getVagao() + ", getBalsa()=" + getBalsa() + ", toString()=" + super.toString()
				+ "]";
	}





 }
