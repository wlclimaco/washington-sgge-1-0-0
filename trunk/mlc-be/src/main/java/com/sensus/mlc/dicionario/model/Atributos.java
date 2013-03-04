package com.sensus.mlc.dicionario.model;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Atributos.
 */
public class Atributos {

	  /** The codatrib. */
  	public Integer codatrib;

	  /** The nmatrib. */
  	public String nmatrib;

	  /** The cdvalidacao. */
  	public List<Validacoes> cdvalidacao;

	  /** The codtipo. */
  	public Tipos codtipo;

	  /** The posicaox. */
  	public Double posicaox;

	  /** The posicaoy. */
  	public Double posicaoy;

	  /** The tamanho. */
  	public Integer tamanho;

	  /** The label. */
  	public String label;

  	/** The cod tela busca. */
	  public Integer codTelaBusca;

	  /** The requerido. */
  	public Boolean requerido;

	  /** The busca. */
  	public Boolean busca;

	  /** The help. */
  	public String help;

	  /** The mascara. */
  	public String mascara;

  	/** The dominios. */
	  public List<Dominios> dominios;

	/**
	 * Gets the cod tela busca.
	 *
	 * @return the cod tela busca
	 */
	public Integer getCodTelaBusca() {
		return codTelaBusca;
	}

	/**
	 * Sets the cod tela busca.
	 *
	 * @param codTelaBusca the new cod tela busca
	 */
	public void setCodTelaBusca(Integer codTelaBusca) {
		this.codTelaBusca = codTelaBusca;
	}

	/**
	 * Gets the codatrib.
	 *
	 * @return the codatrib
	 */
	public Integer getCodatrib() {
		return codatrib;
	}

	/**
	 * Sets the codatrib.
	 *
	 * @param codatrib the new codatrib
	 */
	public void setCodatrib(Integer codatrib) {
		this.codatrib = codatrib;
	}

	/**
	 * Gets the nmatrib.
	 *
	 * @return the nmatrib
	 */
	public String getNmatrib() {
		return nmatrib;
	}

	/**
	 * Sets the nmatrib.
	 *
	 * @param nmatrib the new nmatrib
	 */
	public void setNmatrib(String nmatrib) {
		this.nmatrib = nmatrib;
	}

	/**
	 * Gets the cdvalidacao.
	 *
	 * @return the cdvalidacao
	 */
	public List<Validacoes> getCdvalidacao() {
		return cdvalidacao;
	}

	/**
	 * Sets the cdvalidacao.
	 *
	 * @param cdvalidacao the new cdvalidacao
	 */
	public void setCdvalidacao(List<Validacoes> cdvalidacao) {
		this.cdvalidacao = cdvalidacao;
	}

	/**
	 * Gets the codtipo.
	 *
	 * @return the codtipo
	 */
	public Tipos getCodtipo() {
		return codtipo;
	}

	/**
	 * Sets the codtipo.
	 *
	 * @param codtipo the new codtipo
	 */
	public void setCodtipo(Tipos codtipo) {
		this.codtipo = codtipo;
	}

	/**
	 * Gets the posicaox.
	 *
	 * @return the posicaox
	 */
	public Double getPosicaox() {
		return posicaox;
	}

	/**
	 * Sets the posicaox.
	 *
	 * @param posicaox the new posicaox
	 */
	public void setPosicaox(Double posicaox) {
		this.posicaox = posicaox;
	}

	/**
	 * Gets the posicaoy.
	 *
	 * @return the posicaoy
	 */
	public Double getPosicaoy() {
		return posicaoy;
	}

	/**
	 * Sets the posicaoy.
	 *
	 * @param posicaoy the new posicaoy
	 */
	public void setPosicaoy(Double posicaoy) {
		this.posicaoy = posicaoy;
	}

	/**
	 * Gets the tamanho.
	 *
	 * @return the tamanho
	 */
	public Integer getTamanho() {
		return tamanho;
	}

	/**
	 * Sets the tamanho.
	 *
	 * @param tamanho the new tamanho
	 */
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the requerido.
	 *
	 * @return the requerido
	 */
	public Boolean getRequerido() {
		return requerido;
	}

	/**
	 * Sets the requerido.
	 *
	 * @param requerido the new requerido
	 */
	public void setRequerido(Boolean requerido) {
		this.requerido = requerido;
	}

	/**
	 * Gets the busca.
	 *
	 * @return the busca
	 */
	public Boolean getBusca() {
		return busca;
	}

	/**
	 * Sets the busca.
	 *
	 * @param busca the new busca
	 */
	public void setBusca(Boolean busca) {
		this.busca = busca;
	}

	/**
	 * Gets the help.
	 *
	 * @return the help
	 */
	public String getHelp() {
		return help;
	}

	/**
	 * Sets the help.
	 *
	 * @param help the new help
	 */
	public void setHelp(String help) {
		this.help = help;
	}

	/**
	 * Gets the mascara.
	 *
	 * @return the mascara
	 */
	public String getMascara() {
		return mascara;
	}

	/**
	 * Sets the mascara.
	 *
	 * @param mascara the new mascara
	 */
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	/**
	 * Gets the dominios.
	 *
	 * @return the dominios
	 */
	public List<Dominios> getDominios() {
		return dominios;
	}

	/**
	 * Sets the dominios.
	 *
	 * @param dominios the new dominios
	 */
	public void setDominios(List<Dominios> dominios) {
		this.dominios = dominios;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Atributos [codatrib=" + codatrib + ", nmatrib=" + nmatrib
				+ ", cdvalidacao=" + cdvalidacao + ", codtipo=" + codtipo
				+ ", posicaox=" + posicaox + ", posicaoy=" + posicaoy
				+ ", tamanho=" + tamanho + ", label=" + label
				+ ", codTelaBusca=" + codTelaBusca + ", requerido=" + requerido
				+ ", busca=" + busca + ", help=" + help + ", mascara="
				+ mascara + ", dominios=" + dominios + ", getCodTelaBusca()="
				+ getCodTelaBusca() + ", getCodatrib()=" + getCodatrib()
				+ ", getNmatrib()=" + getNmatrib() + ", getCdvalidacao()="
				+ getCdvalidacao() + ", getCodtipo()=" + getCodtipo()
				+ ", getPosicaox()=" + getPosicaox() + ", getPosicaoy()="
				+ getPosicaoy() + ", getTamanho()=" + getTamanho()
				+ ", getLabel()=" + getLabel() + ", getRequerido()="
				+ getRequerido() + ", getBusca()=" + getBusca()
				+ ", getHelp()=" + getHelp() + ", getMascara()=" + getMascara()
				+ ", getDominios()=" + getDominios() + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */


}
