package com.sensus.mlc.gestao.model;
import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Bairro.
 */
public class Bairro extends SensusModel
{

    /** The codbairro. */
    private Bairro codbairro;

    /** The codmunic. */
    private Municipio codmunic;

    /** The siglauf. */
    private String siglauf;

    /** The codpais. */
    private Pais codpais;

    /** The nomebairro. */
    private String nomebairro;

    /** The vlrfrete. */
    private Float vlrfrete;

    /** The qtdfrete. */
    private Float qtdfrete;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the codbairro.
	 *
	 * @return the codbairro
	 */
	public Bairro getCodbairro() {
		return codbairro;
	}

	/**
	 * Sets the codbairro.
	 *
	 * @param codbairro the new codbairro
	 */
	public void setCodbairro(Bairro codbairro) {
		this.codbairro = codbairro;
	}

	/**
	 * Gets the codmunic.
	 *
	 * @return the codmunic
	 */
	public Municipio getCodmunic() {
		return codmunic;
	}

	/**
	 * Sets the codmunic.
	 *
	 * @param codmunic the new codmunic
	 */
	public void setCodmunic(Municipio codmunic) {
		this.codmunic = codmunic;
	}

	/**
	 * Gets the siglauf.
	 *
	 * @return the siglauf
	 */
	public String getSiglauf() {
		return siglauf;
	}

	/**
	 * Sets the siglauf.
	 *
	 * @param siglauf the new siglauf
	 */
	public void setSiglauf(String siglauf) {
		this.siglauf = siglauf;
	}

	/**
	 * Gets the codpais.
	 *
	 * @return the codpais
	 */
	public Pais getCodpais() {
		return codpais;
	}

	/**
	 * Sets the codpais.
	 *
	 * @param codpais the new codpais
	 */
	public void setCodpais(Pais codpais) {
		this.codpais = codpais;
	}

	/**
	 * Gets the nomebairro.
	 *
	 * @return the nomebairro
	 */
	public String getNomebairro() {
		return nomebairro;
	}

	/**
	 * Sets the nomebairro.
	 *
	 * @param nomebairro the new nomebairro
	 */
	public void setNomebairro(String nomebairro) {
		this.nomebairro = nomebairro;
	}

	/**
	 * Gets the vlrfrete.
	 *
	 * @return the vlrfrete
	 */
	public Float getVlrfrete() {
		return vlrfrete;
	}

	/**
	 * Sets the vlrfrete.
	 *
	 * @param vlrfrete the new vlrfrete
	 */
	public void setVlrfrete(Float vlrfrete) {
		this.vlrfrete = vlrfrete;
	}

	/**
	 * Gets the qtdfrete.
	 *
	 * @return the qtdfrete
	 */
	public Float getQtdfrete() {
		return qtdfrete;
	}

	/**
	 * Sets the qtdfrete.
	 *
	 * @param qtdfrete the new qtdfrete
	 */
	public void setQtdfrete(Float qtdfrete) {
		this.qtdfrete = qtdfrete;
	}

	/**
	 * Gets the listinsalt.
	 *
	 * @return the listinsalt
	 */
	public List<Auditoria> getListinsalt() {
		return listinsalt;
	}

	/**
	 * Sets the listinsalt.
	 *
	 * @param listinsalt the new listinsalt
	 */
	public void setListinsalt(List<Auditoria> listinsalt) {
		this.listinsalt = listinsalt;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Bairro [codbairro=" + codbairro + ", codmunic=" + codmunic
				+ ", siglauf=" + siglauf + ", codpais=" + codpais
				+ ", nomebairro=" + nomebairro + ", vlrfrete=" + vlrfrete
				+ ", qtdfrete=" + qtdfrete + ", listinsalt=" + listinsalt + "]";
	}


}
