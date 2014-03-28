package com.sensus.lc.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.comentario.model.Comentario;
import com.sensus.lc.curtir.model.Curtir;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.video.model.Video;

// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalClass.
 */
@SuppressWarnings("serial")
public class PrincipalClass extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The createdate. */
	private Date createdate;

	/** The createuser. */
	private String createuser;

	/** The tenantid. */
	private Integer tenantid;

	/** The tenantid. */
	private Integer userid;

	/** The list curtir. */
	private List<Curtir> listCurtir;

	/** The list fotos. */
	private List<Foto> listFotos;

	/** The list comentarios. */
	private List<Comentario> listComentarios;

	/** The list videos. */
	private List<Video> listVideos;

	/**
	 * Gets the createdate.
	 * 
	 * @return the createdate
	 */
	public Date getCreatedate()
	{
		return createdate;
	}

	/**
	 * Sets the createdate.
	 * 
	 * @param createdate the new createdate
	 */
	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}

	/**
	 * Gets the createuser.
	 * 
	 * @return the createuser
	 */
	public String getCreateuser()
	{
		return createuser;
	}

	/**
	 * Sets the createuser.
	 * 
	 * @param createuser the new createuser
	 */
	public void setCreateuser(String createuser)
	{
		this.createuser = createuser;
	}

	/**
	 * Gets the tenantid.
	 * 
	 * @return the tenantid
	 */
	public Integer getTenantid()
	{
		return tenantid;
	}

	/**
	 * Sets the tenantid.
	 * 
	 * @param tenantid the new tenantid
	 */
	public void setTenantid(Integer tenantid)
	{
		this.tenantid = tenantid;
	}

	/**
	 * Gets the userid.
	 * 
	 * @return the userid
	 */
	public Integer getUserid()
	{
		return userid;
	}

	/**
	 * Sets the userid.
	 * 
	 * @param userid the new userid
	 */
	public void setUserid(Integer userid)
	{
		this.userid = userid;
	}

	/**
	 * Gets the list curtir.
	 * 
	 * @return the list curtir
	 */
	public List<Curtir> getListCurtir()
	{
		return listCurtir;
	}

	/**
	 * Sets the list curtir.
	 * 
	 * @param listCurtir the new list curtir
	 */
	public void setListCurtir(List<Curtir> listCurtir)
	{
		this.listCurtir = listCurtir;
	}

	/**
	 * Gets the list fotos.
	 * 
	 * @return the list fotos
	 */
	public List<Foto> getListFotos()
	{
		return listFotos;
	}

	/**
	 * Sets the list fotos.
	 * 
	 * @param listFotos the new list fotos
	 */
	public void setListFotos(List<Foto> listFotos)
	{
		this.listFotos = listFotos;
	}

	/**
	 * Gets the list comentarios.
	 * 
	 * @return the list comentarios
	 */
	public List<Comentario> getListComentarios()
	{
		return listComentarios;
	}

	/**
	 * Sets the list comentarios.
	 * 
	 * @param listComentarios the new list comentarios
	 */
	public void setListComentarios(List<Comentario> listComentarios)
	{
		this.listComentarios = listComentarios;
	}

	/**
	 * Adds the curtir.
	 * 
	 * @param exercicio the exercicio
	 */
	public void addCurtir(Curtir exercicio)
	{
		if (getListCurtir() == null)
		{
			setListCurtir(new ArrayList<Curtir>());
		}

		getListCurtir().add(exercicio);
	}

	/**
	 * Adds the foto.
	 * 
	 * @param exercicio the exercicio
	 */
	public void addFoto(Foto exercicio)
	{
		if (getListFotos() == null)
		{
			setListFotos(new ArrayList<Foto>());
		}

		getListFotos().add(exercicio);
	}

	/**
	 * Adds the comentario.
	 * 
	 * @param exercicio the exercicio
	 */
	public void addComentario(Comentario exercicio)
	{
		if (getListComentarios() == null)
		{
			setListComentarios(new ArrayList<Comentario>());
		}

		getListComentarios().add(exercicio);
	}

	/**
	 * Gets the list videos.
	 * 
	 * @return the list videos
	 */
	public List<Video> getListVideos()
	{
		return listVideos;
	}

	/**
	 * Sets the list videos.
	 * 
	 * @param listVideos the new list videos
	 */
	public void setListVideos(List<Video> listVideos)
	{
		this.listVideos = listVideos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.model.SensusModel#toString()
	 */
	@Override
	public String toString()
	{
		return "PrincipalClass [getCreatedate()=" + getCreatedate() + ", getCreateuser()=" + getCreateuser()
				+ ", getTenantid()=" + getTenantid() + ", getUserid()=" + getUserid() + ", getListCurtir()="
				+ getListCurtir() + ", getListFotos()=" + getListFotos() + ", getListComentarios()="
				+ getListComentarios() + ", getListVideos()=" + getListVideos() + ", toString()=" + super.toString()
				+ "]";
	}

}
