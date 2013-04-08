package com.sensus.mlc.gestao.model;

import java.util.Date;

import com.sensus.common.model.SensusModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Auditoria.
 */
public class Auditoria extends SensusModel
{

    /** The codaltins. */
    private Integer codaltins;

    /** The tipo. */
    private String tipo;

    /** The dtins. */
    private Date dtins;

    /** The hins. */
    private Date hins;

    /** The idusuins. */
    private String idusuins;

    /** The dtalt. */
    private Date dtalt;

    /** The halt. */
    private Date halt;

    /** The idusualt. */
    private String idusualt;

	/**
	 * Gets the codaltins.
	 *
	 * @return the codaltins
	 */
	public Integer getCodaltins() {
		return codaltins;
	}

	/**
	 * Sets the codaltins.
	 *
	 * @param codaltins the new codaltins
	 */
	public void setCodaltins(Integer codaltins) {
		this.codaltins = codaltins;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the dtins.
	 *
	 * @return the dtins
	 */
	public Date getDtins() {
		return dtins;
	}

	/**
	 * Sets the dtins.
	 *
	 * @param dtins the new dtins
	 */
	public void setDtins(Date dtins) {
		this.dtins = dtins;
	}

	/**
	 * Gets the hins.
	 *
	 * @return the hins
	 */
	public Date getHins() {
		return hins;
	}

	/**
	 * Sets the hins.
	 *
	 * @param hins the new hins
	 */
	public void setHins(Date hins) {
		this.hins = hins;
	}

	/**
	 * Gets the idusuins.
	 *
	 * @return the idusuins
	 */
	public String getIdusuins() {
		return idusuins;
	}

	/**
	 * Sets the idusuins.
	 *
	 * @param idusuins the new idusuins
	 */
	public void setIdusuins(String idusuins) {
		this.idusuins = idusuins;
	}

	/**
	 * Gets the dtalt.
	 *
	 * @return the dtalt
	 */
	public Date getDtalt() {
		return dtalt;
	}

	/**
	 * Sets the dtalt.
	 *
	 * @param dtalt the new dtalt
	 */
	public void setDtalt(Date dtalt) {
		this.dtalt = dtalt;
	}

	/**
	 * Gets the halt.
	 *
	 * @return the halt
	 */
	public Date getHalt() {
		return halt;
	}

	/**
	 * Sets the halt.
	 *
	 * @param halt the new halt
	 */
	public void setHalt(Date halt) {
		this.halt = halt;
	}

	/**
	 * Gets the idusualt.
	 *
	 * @return the idusualt
	 */
	public String getIdusualt() {
		return idusualt;
	}

	/**
	 * Sets the idusualt.
	 *
	 * @param idusualt the new idusualt
	 */
	public void setIdusualt(String idusualt) {
		this.idusualt = idusualt;
	}

	/* (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Auditoria [codaltins=" + codaltins + ", tipo=" + tipo
				+ ", dtins=" + dtins + ", hins=" + hins + ", idusuins="
				+ idusuins + ", dtalt=" + dtalt + ", halt=" + halt
				+ ", idusualt=" + idusualt + ", getCodaltins()="
				+ getCodaltins() + ", getTipo()=" + getTipo() + ", getDtins()="
				+ getDtins() + ", getHins()=" + getHins() + ", getIdusuins()="
				+ getIdusuins() + ", getDtalt()=" + getDtalt() + ", getHalt()="
				+ getHalt() + ", getIdusualt()=" + getIdusualt() + "]";
	}

}
