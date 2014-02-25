package com.sensus.lc.comentario.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.base.model.AcaoTabelaEnum;
import com.sensus.lc.curtir.model.Curtir;

@SuppressWarnings("serial")
public class Comentario extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdcomentario;
	private Date dtpost;
	private String coment;
	private AcaoTabelaEnum acaoComentarioEnum;
	private List<Curtir> curtis;

	public Integer getCdcomentario()
	{
		return cdcomentario;
	}

	public void setCdcomentario(Integer cdcomentario)
	{
		this.cdcomentario = cdcomentario;
	}

	public AcaoTabelaEnum getAcaoComentarioEnum()
	{
		return acaoComentarioEnum;
	}

	public void setAcaoComentarioEnum(AcaoTabelaEnum acaoComentarioEnum)
	{
		this.acaoComentarioEnum = acaoComentarioEnum;
	}

	public List<Curtir> getCurtis()
	{
		return curtis;
	}

	public void setCurtis(List<Curtir> curtis)
	{
		this.curtis = curtis;
	}

	public void addCurtir(Curtir exercicio)
	{
		if (getCurtis() == null)
		{
			setCurtis(new ArrayList<Curtir>());
		}

		getCurtis().add(exercicio);
	}

	/**
	 * Gets the cdfoto.
	 * 
	 * @return the cdfoto
	 */
	public Integer getCdfoto()
	{
		return cdcomentario;
	}

	/**
	 * Sets the cdfoto.
	 * 
	 * @param cdfoto the new cdfoto
	 */
	public void setCdfoto(Integer cdfoto)
	{
		cdcomentario = cdfoto;
	}

	/**
	 * Gets the dtpost.
	 * 
	 * @return the dtpost
	 */
	public Date getDtpost()
	{
		return dtpost;
	}

	/**
	 * Sets the dtpost.
	 * 
	 * @param dtpost the new dtpost
	 */
	public void setDtpost(Date dtpost)
	{
		this.dtpost = dtpost;
	}

	/**
	 * Gets the coment.
	 * 
	 * @return the coment
	 */
	public String getComent()
	{
		return coment;
	}

	/**
	 * Sets the coment.
	 * 
	 * @param coment the new coment
	 */
	public void setComent(String coment)
	{
		this.coment = coment;
	}

	/**
	 * Gets the foto.
	 * 
	 * @return the foto
	 */
	public AcaoTabelaEnum getFoto()
	{
		return acaoComentarioEnum;
	}

	/**
	 * Sets the foto.
	 * 
	 * @param foto the new foto
	 */
	public void setFoto(AcaoTabelaEnum foto)
	{
		acaoComentarioEnum = foto;
	}

	@Override
	public String toString()
	{
		return "Comentario [getCdcomentario()=" + getCdcomentario() + ", getAcaoComentarioEnum()="
				+ getAcaoComentarioEnum() + ", getCurtis()=" + getCurtis() + ", getCdfoto()=" + getCdfoto()
				+ ", getDtpost()=" + getDtpost() + ", getComent()=" + getComent() + ", getFoto()=" + getFoto()
				+ ", toString()=" + super.toString() + "]";
	}
}
