package com.qat.samples.sysmgmt.util.model;

import java.util.Date;

import com.qat.samples.sysmgmt.estado.model.Estado;

/**
 * This class is a representation of an Account (i.e Checking, Savings, etc.). This represents an account for a transfer
 * setting.
 */
@SuppressWarnings("serial")
public class Endereco extends ModelCosmeDamiao
{
	/** The SendSolv id for the account. */
	private Integer id;

	/** The type of an account. */
	private String logradouro;

	/** The description. */
	private Cidade cidade;

	/** The estado. */
	private Estado estado;

	/** The bairro. */
	private String bairro;

	/** The numero. */
	private String numero;

	/** The cep. */
	private String cep;

	private Double latitude;

	private Double longitude;

	private String codIbge;

	private String complemento;

	private EnderecoTypeEnum enderecoType;

	/**
	 * Default constructor.
	 * @param string
	 * @param i
	 */


	public Endereco(Integer id, String logradouro, Cidade cidade, Estado estado, String bairro, String numero,
			String cep, String complemento, EnderecoTypeEnum enderecoType, PersistenceActionEnum modelAction)
	{
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.estado = estado;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.enderecoType = enderecoType;
		setModelAction(modelAction);
		setModifyDateUTC((new Date()).getTime());
		setModifyUser("system");
	}

	public Endereco() {
		super();
	}

	public Integer getEnderecoTypeValue()
	{
		if (enderecoType != null)
		{
			return enderecoType.getValue();
		}
		return null;
	}

	public void setEnderecoTypeValue(Integer acaoTypeValue)
	{
		enderecoType = EnderecoTypeEnum.enumForValue(acaoTypeValue);
	}

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
	 * Gets the logradouro.
	 *
	 * @return the logradouro
	 */
	public String getLogradouro()
	{
		return logradouro;
	}

	/**
	 * Sets the logradouro.
	 *
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
	}

	/**
	 * Gets the cidade.
	 *
	 * @return the cidade
	 */
	public Cidade getCidade()
	{
		return cidade;
	}

	/**
	 * Sets the cidade.
	 *
	 * @param cidade the cidade to set
	 */
	public void setCidade(Cidade cidade)
	{
		this.cidade = cidade;
	}

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
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	/**
	 * Gets the bairro.
	 *
	 * @return the bairro
	 */
	public String getBairro()
	{
		return bairro;
	}

	/**
	 * Sets the bairro.
	 *
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero()
	{
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the numero to set
	 */
	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	/**
	 * Gets the cep.
	 *
	 * @return the cep
	 */
	public String getCep()
	{
		return cep;
	}

	/**
	 * Sets the cep.
	 *
	 * @param cep the cep to set
	 */
	public void setCep(String cep)
	{
		this.cep = cep;
	}

	public String getComplemento()
	{
		return complemento;
	}

	public void setComplemento(String complemento)
	{
		this.complemento = complemento;
	}

	public EnderecoTypeEnum getEnderecoType()
	{
		return enderecoType;
	}

	public void setEnderecoType(EnderecoTypeEnum enderecoType)
	{
		this.enderecoType = enderecoType;
	}





	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getCodIbge() {
		return codIbge;
	}

	public void setCodIbge(String codIbge) {
		this.codIbge = codIbge;
	}

	@Override
	public String toString() {
		return "Endereco [getEnderecoTypeValue()=" + getEnderecoTypeValue() + ", getId()=" + getId()
				+ ", getLogradouro()=" + getLogradouro() + ", getCidade()=" + getCidade() + ", getEstado()="
				+ getEstado() + ", getBairro()=" + getBairro() + ", getNumero()=" + getNumero() + ", getCep()="
				+ getCep() + ", getComplemento()=" + getComplemento() + ", getEnderecoType()=" + getEnderecoType()
				+ ", getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude() + ", getCodIbge()="
				+ getCodIbge() + ", toString()=" + super.toString() + "]";
	}

}
