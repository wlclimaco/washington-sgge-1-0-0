package com.sensus.mlc.dicionario.model.response;

import java.util.List;

import com.sensus.common.model.response.Response;
import com.sensus.mlc.dicionario.model.Tela;


// TODO: Auto-generated Javadoc
/**
 * The Class DicionarioResponse.
 */
public class TelaResponse extends Response
{
	/** The tela. */
	private List<Tela> tela ;



	/**
	 * Gets the tela.
	 *
	 * @return the tela
	 */
	public List<Tela> getTela() {
		return tela;
	}



	/**
	 * Sets the tela.
	 *
	 * @param tela the new tela
	 */
	public void setTela(List<Tela> tela) {
		this.tela = tela;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DicionarioResponse [tela=" + tela + ", getTela()=" + getTela()
				+ "]";
	}


}
