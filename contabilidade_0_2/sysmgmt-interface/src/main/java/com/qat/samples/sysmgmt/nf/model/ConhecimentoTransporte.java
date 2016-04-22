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
	/** The SendSolv id for the account. */
	private Integer id;

	private Integer IdNota;

	private Transportador transportador;

	/** The tipo endereco. */
	private String remetente;

	private Double vrTotalMercadorias;

	private Integer apCreIcms;

	private Integer fretePorConta;

	private String placa;

	private Estado estado;

	private Marca marca;

	private Double especie;

	private Double volume;

	private Double pesoLiquido;

	private Double pesoBruto;

	/**
	 * Default constructor.
	 */
	public ConhecimentoTransporte()
	{
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the idNota
	 */
	public Integer getIdNota()
	{
		return IdNota;
	}

	/**
	 * @param idNota the idNota to set
	 */
	public void setIdNota(Integer idNota)
	{
		IdNota = idNota;
	}

	/**
	 * @return the transportador
	 */
	public Transportador getTransportador()
	{
		return transportador;
	}

	/**
	 * @param transportador the transportador to set
	 */
	public void setTransportador(Transportador transportador)
	{
		this.transportador = transportador;
	}

	/**
	 * @return the remetente
	 */
	public String getRemetente()
	{
		return remetente;
	}

	/**
	 * @param remetente the remetente to set
	 */
	public void setRemetente(String remetente)
	{
		this.remetente = remetente;
	}

	/**
	 * @return the vrTotalMercadorias
	 */
	public Double getVrTotalMercadorias()
	{
		return vrTotalMercadorias;
	}

	/**
	 * @param vrTotalMercadorias the vrTotalMercadorias to set
	 */
	public void setVrTotalMercadorias(Double vrTotalMercadorias)
	{
		this.vrTotalMercadorias = vrTotalMercadorias;
	}

	/**
	 * @return the apCreIcms
	 */
	public Integer getApCreIcms()
	{
		return apCreIcms;
	}

	/**
	 * @param apCreIcms the apCreIcms to set
	 */
	public void setApCreIcms(Integer apCreIcms)
	{
		this.apCreIcms = apCreIcms;
	}

	/**
	 * @return the fretePorConta
	 */
	public Integer getFretePorConta()
	{
		return fretePorConta;
	}

	/**
	 * @param fretePorConta the fretePorConta to set
	 */
	public void setFretePorConta(Integer fretePorConta)
	{
		this.fretePorConta = fretePorConta;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca()
	{
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa)
	{
		this.placa = placa;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado()
	{
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	/**
	 * @return the marca
	 */
	public Marca getMarca()
	{
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(Marca marca)
	{
		this.marca = marca;
	}

	/**
	 * @return the especie
	 */
	public Double getEspecie()
	{
		return especie;
	}

	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(Double especie)
	{
		this.especie = especie;
	}

	/**
	 * @return the volume
	 */
	public Double getVolume()
	{
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Double volume)
	{
		this.volume = volume;
	}

	/**
	 * @return the pesoLiquido
	 */
	public Double getPesoLiquido()
	{
		return pesoLiquido;
	}

	/**
	 * @param pesoLiquido the pesoLiquido to set
	 */
	public void setPesoLiquido(Double pesoLiquido)
	{
		this.pesoLiquido = pesoLiquido;
	}

	/**
	 * @return the pesoBruto
	 */
	public Double getPesoBruto()
	{
		return pesoBruto;
	}

	/**
	 * @param pesoBruto the pesoBruto to set
	 */
	public void setPesoBruto(Double pesoBruto)
	{
		this.pesoBruto = pesoBruto;
	}

	@Override
	public String toString()
	{
		return "ConhecimentoTransporte [getId()=" + getId() + ", getIdNota()=" + getIdNota() + ", getTransportador()="
				+ getTransportador() + ", getRemetente()=" + getRemetente() + ", getVrTotalMercadorias()="
				+ getVrTotalMercadorias() + ", getApCreIcms()=" + getApCreIcms() + ", getFretePorConta()="
				+ getFretePorConta() + ", getPlaca()=" + getPlaca() + ", getEstado()=" + getEstado() + ", getMarca()="
				+ getMarca() + ", getEspecie()=" + getEspecie() + ", getVolume()=" + getVolume()
				+ ", getPesoLiquido()=" + getPesoLiquido() + ", getPesoBruto()=" + getPesoBruto() + ", toString()="
				+ super.toString() + "]";
	}

}
