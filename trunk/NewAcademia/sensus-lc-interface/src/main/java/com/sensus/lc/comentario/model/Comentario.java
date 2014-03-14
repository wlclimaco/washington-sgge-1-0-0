package com.sensus.lc.comentario.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.base.model.TabelaEnum;
import com.sensus.lc.curtir.model.Curtir;
import com.sensus.lc.foto.model.Foto;

/**
 * The Class Comentario.
 */
@SuppressWarnings("serial")
public class Comentario extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The cdcomentario. */
	private Integer cdcomentario;

	/** The dtpost. */
	private Date dtpost;

	/** The coment. */
	private String coment;

	/** The id. */
	private Integer id;

	/** The acao comentario enum. */
	private TabelaEnum acaoComentarioEnum;

	/** The curtis. */
	private List<Curtir> curtis;

	/** The fotos. */
	private List<Foto> fotos;

	public Comentario(Integer cdcomentario, String coment, Integer id, TabelaEnum acaoComentarioEnum)
	{
		super();
		this.cdcomentario = cdcomentario;
		this.coment = coment;
		this.id = id;
		this.acaoComentarioEnum = acaoComentarioEnum;
	}

	public Comentario(Integer cdcomentario, Date dtpost, String coment, Integer id, TabelaEnum acaoComentarioEnum,
			List<Curtir> curtis, List<Foto> fotos)
	{
		super();
		this.cdcomentario = cdcomentario;
		this.dtpost = dtpost;
		this.coment = coment;
		this.id = id;
		this.acaoComentarioEnum = acaoComentarioEnum;
		this.curtis = curtis;
		this.fotos = fotos;
	}

	/**
	 * Gets the cdcomentario.
	 * 
	 * @return the cdcomentario
	 */
	public Integer getCdcomentario()
	{
		return cdcomentario;
	}

	/**
	 * Sets the cdcomentario.
	 * 
	 * @param cdcomentario the new cdcomentario
	 */
	public void setCdcomentario(Integer cdcomentario)
	{
		this.cdcomentario = cdcomentario;
	}

	/**
	 * Gets the acao comentario enum.
	 * 
	 * @return the acao comentario enum
	 */
	public TabelaEnum getAcaoComentarioEnum()
	{
		return acaoComentarioEnum;
	}

	/**
	 * Sets the acao comentario enum.
	 * 
	 * @param acaoComentarioEnum the new acao comentario enum
	 */
	public void setAcaoComentarioEnum(TabelaEnum acaoComentarioEnum)
	{
		this.acaoComentarioEnum = acaoComentarioEnum;
	}

	/**
	 * Gets the curtis.
	 * 
	 * @return the curtis
	 */
	public List<Curtir> getCurtis()
	{
		return curtis;
	}

	/**
	 * Sets the curtis.
	 * 
	 * @param curtis the new curtis
	 */
	public void setCurtis(List<Curtir> curtis)
	{
		this.curtis = curtis;
	}

	/**
	 * Adds the curtir.
	 * 
	 * @param exercicio the exercicio
	 */
	public void addCurtir(Curtir exercicio)
	{
		if (getCurtis() == null)
		{
			setCurtis(new ArrayList<Curtir>());
		}

		getCurtis().add(exercicio);
	}

	/**
	 * Gets the fotos.
	 * 
	 * @return the fotos
	 */
	public List<Foto> getFotos()
	{
		return fotos;
	}

	/**
	 * Sets the fotos.
	 * 
	 * @param fotos the new fotos
	 */
	public void setFotos(List<Foto> fotos)
	{
		this.fotos = fotos;
	}

	/**
	 * Adds the fotos.
	 * 
	 * @param exercicio the exercicio
	 */
	public void addFotos(Foto exercicio)
	{
		if (getFotos() == null)
		{
			setFotos(new ArrayList<Foto>());
		}

		getFotos().add(exercicio);
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
	 * @param id the new id
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "Comentario [getCdcomentario()=" + getCdcomentario() + ", getAcaoComentarioEnum()="
				+ getAcaoComentarioEnum() + ", getCurtis()=" + getCurtis() + ", getFotos()=" + getFotos()
				+ ", getCdfoto()=" + getCdfoto() + ", getDtpost()=" + getDtpost() + ", getComent()=" + getComent()
				+ ", getId()=" + getId() + ", toString()=" + super.toString() + "]";
	}
}
