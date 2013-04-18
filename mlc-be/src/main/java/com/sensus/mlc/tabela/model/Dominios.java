package com.sensus.mlc.tabela.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.gestao.model.Auditoria;

public class Dominios extends SensusModel {
	private String nmtabela;
	private String nmatribu;
	private String dsdomini;
	private String vrdomini;
	private List<Auditoria> auditorias;

	/**
	 * Gets the nmtabela.
	 *
	 * @return the nmtabela
	 */
	public String getNmtabela() {
		return this.nmtabela;
	}

	/**
	 * Sets the nmtabela.
	 *
	 * @param nmtabela
	 *            the new nmtabela
	 */
	public void setNmtabela(String nmtabela) {
		this.nmtabela = nmtabela;
	}

	/**
	 * Gets the nmatribu.
	 *
	 * @return the nmatribu
	 */
	public String getNmatribu() {
		return this.nmatribu;
	}

	/**
	 * Sets the nmatribu.
	 *
	 * @param nmatribu
	 *            the new nmatribu
	 */
	public void setNmatribu(String nmatribu) {
		this.nmatribu = nmatribu;
	}

	/**
	 * Gets the dsdomini.
	 *
	 * @return the dsdomini
	 */
	public String getDsdomini() {
		return this.dsdomini;
	}

	/**
	 * Sets the dsdomini.
	 *
	 * @param dsdomini
	 *            the new dsdomini
	 */
	public void setDsdomini(String dsdomini) {
		this.dsdomini = dsdomini;
	}

	/**
	 * Gets the vrdomini.
	 *
	 * @return the vrdomini
	 */
	public String getVrdomini() {
		return this.vrdomini;
	}

	/**
	 * Sets the vrdomini.
	 *
	 * @param vrdomini
	 *            the new vrdomini
	 */
	public void setVrdomini(String vrdomini) {
		this.vrdomini = vrdomini;
	}

	/**
	 * Gets the auditorias.
	 *
	 * @return the auditorias
	 */
	public List<Auditoria> getAuditorias() {
		return this.auditorias;
	}

	/**
	 * Sets the auditorias.
	 *
	 * @param auditorias
	 *            the new auditorias
	 */
	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString() {
		return "Dominios[ getNmtabela()=" + getNmtabela() + ", getNmatribu()="
				+ getNmatribu() + ", getDsdomini()=" + getDsdomini()
				+ ", getVrdomini()=" + getVrdomini() + ", getAuditorias()="
				+ getAuditorias() + "]";
	}
}
