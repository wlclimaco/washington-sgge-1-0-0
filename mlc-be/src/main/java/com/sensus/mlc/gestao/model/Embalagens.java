package com.sensus.mlc.gestao.model;

import java.util.List;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Embalagens.
 */
public class Embalagens extends SensusModel
{

    /** The cdembala. */
    private Integer cdembala;

    /** The cdprodut. */
    private Integer cdprodut;

    /** The cdunidad. */
    private Unimed cdunidad;

    /** The dsembala. */
    private String dsembala;

    /** The qtembala. */
    private Integer qtembala;

    /** The tpembala. */
    private String tpembala;

    /** The listinsalt. */
    private List<Auditoria> listinsalt;

	/**
	 * Gets the cdembala.
	 *
	 * @return the cdembala
	 */
	public Integer getCdembala() {
		return cdembala;
	}

	/**
	 * Sets the cdembala.
	 *
	 * @param cdembala the new cdembala
	 */
	public void setCdembala(Integer cdembala) {
		this.cdembala = cdembala;
	}

	/**
	 * Gets the cdprodut.
	 *
	 * @return the cdprodut
	 */
	public Integer getCdprodut() {
		return cdprodut;
	}

	/**
	 * Sets the cdprodut.
	 *
	 * @param cdprodut the new cdprodut
	 */
	public void setCdprodut(Integer cdprodut) {
		this.cdprodut = cdprodut;
	}

	/**
	 * Gets the cdunidad.
	 *
	 * @return the cdunidad
	 */
	public Unimed getCdunidad() {
		return cdunidad;
	}

	/**
	 * Sets the cdunidad.
	 *
	 * @param cdunidad the new cdunidad
	 */
	public void setCdunidad(Unimed cdunidad) {
		this.cdunidad = cdunidad;
	}

	/**
	 * Gets the dsembala.
	 *
	 * @return the dsembala
	 */
	public String getDsembala() {
		return dsembala;
	}

	/**
	 * Sets the dsembala.
	 *
	 * @param dsembala the new dsembala
	 */
	public void setDsembala(String dsembala) {
		this.dsembala = dsembala;
	}

	/**
	 * Gets the qtembala.
	 *
	 * @return the qtembala
	 */
	public Integer getQtembala() {
		return qtembala;
	}

	/**
	 * Sets the qtembala.
	 *
	 * @param qtembala the new qtembala
	 */
	public void setQtembala(Integer qtembala) {
		this.qtembala = qtembala;
	}

	/**
	 * Gets the tpembala.
	 *
	 * @return the tpembala
	 */
	public String getTpembala() {
		return tpembala;
	}

	/**
	 * Sets the tpembala.
	 *
	 * @param tpembala the new tpembala
	 */
	public void setTpembala(String tpembala) {
		this.tpembala = tpembala;
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
		return "Embalagens [cdembala=" + cdembala + ", cdprodut=" + cdprodut
				+ ", cdunidad=" + cdunidad + ", dsembala=" + dsembala
				+ ", qtembala=" + qtembala + ", tpembala=" + tpembala
				+ ", listinsalt=" + listinsalt + ", getCdembala()="
				+ getCdembala() + ", getCdprodut()=" + getCdprodut()
				+ ", getCdunidad()=" + getCdunidad() + ", getDsembala()="
				+ getDsembala() + ", getQtembala()=" + getQtembala()
				+ ", getTpembala()=" + getTpembala() + ", getListinsalt()="
				+ getListinsalt() + "]";
	}
}
