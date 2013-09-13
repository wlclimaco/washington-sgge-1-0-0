package com.sensus.dm.common.pessoa.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.dm.common.academia.model.Academia;
import com.sensus.dm.common.dieta.model.Dieta;
import com.sensus.dm.common.foto.model.Foto;
import com.sensus.dm.common.medida.model.Medida;
import com.sensus.dm.common.suplemento.model.Suplemento;
import com.sensus.dm.commons.serie.model.Serie;

@SuppressWarnings("serial")
public class Pessoa extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;
	private Integer cdpessoa;
	private List<Serie> series;
	private List<Medida> medidas;
	private List<Dieta> dietas;
	private List<Suplemento> suplementos;
	private List<Pessoa> amigos;
	private List<Academia> academias;
	private String obj;
	private List<Foto> fotos;
	private Date dtinicio;

	/**
	 * Gets the cdpessoa.
	 * 
	 * @return the cdpessoa
	 */
	public Integer getCdpessoa()
	{
		return cdpessoa;
	}

	/**
	 * Sets the cdpessoa.
	 * 
	 * @param cdpessoa the new cdpessoa
	 */
	public void setCdpessoa(Integer cdpessoa)
	{
		this.cdpessoa = cdpessoa;
	}

	public List<Serie> getSeries()
	{
		return series;
	}

	public void setSeries(List<Serie> series)
	{
		this.series = series;
	}

	public List<Medida> getMedidas()
	{
		return medidas;
	}

	public void setMedidas(List<Medida> medidas)
	{
		this.medidas = medidas;
	}

	public List<Dieta> getDietas()
	{
		return dietas;
	}

	public void setDietas(List<Dieta> dietas)
	{
		this.dietas = dietas;
	}

	public List<Suplemento> getSuplementos()
	{
		return suplementos;
	}

	public void setSuplementos(List<Suplemento> suplementos)
	{
		this.suplementos = suplementos;
	}

	public List<Pessoa> getAmigos()
	{
		return amigos;
	}

	public void setAmigos(List<Pessoa> amigos)
	{
		this.amigos = amigos;
	}

	public List<Academia> getAcademias()
	{
		return academias;
	}

	public void setAcademias(List<Academia> academias)
	{
		this.academias = academias;
	}

	public String getObj()
	{
		return obj;
	}

	public void setObj(String obj)
	{
		this.obj = obj;
	}

	public List<Foto> getFotos()
	{
		return fotos;
	}

	public void setFotos(List<Foto> fotos)
	{
		this.fotos = fotos;
	}

	public Date getDtinicio()
	{
		return dtinicio;
	}

	public void setDtinicio(Date dtinicio)
	{
		this.dtinicio = dtinicio;
	}

	/**
	 * Adds the Serie
	 * 
	 * @param serie the Serie
	 */
	public void addSerie(Serie serie)
	{
		if (getSeries() == null)
		{
			setSeries(new ArrayList<Serie>());
		}

		getSeries().add(serie);
	}

	/**
	 * Gets the first serie.
	 * 
	 * @return the first serie
	 */
	public Serie getFirstSerie()
	{
		if ((getSeries() != null) && !getSeries().isEmpty())
		{
			return getSeries().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the Medida
	 * 
	 * @param medida the Medida
	 */
	public void addMedida(Medida medida)
	{
		if (getMedidas() == null)
		{
			setMedidas(new ArrayList<Medida>());
		}

		getMedidas().add(medida);
	}

	/**
	 * Gets the first medida.
	 * 
	 * @return the first medida
	 */
	public Medida getFirstMedida()
	{
		if ((getMedidas() != null) && !getMedidas().isEmpty())
		{
			return getMedidas().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the Dieta
	 * 
	 * @param dieta the Dieta
	 */
	public void addDieta(Dieta dieta)
	{
		if (getDietas() == null)
		{
			setDietas(new ArrayList<Dieta>());
		}

		getDietas().add(dieta);
	}

	/**
	 * Gets the first dieta.
	 * 
	 * @return the first dieta
	 */
	public Dieta getFirstDieta()
	{
		if ((getDietas() != null) && !getDietas().isEmpty())
		{
			return getDietas().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the Suplemento
	 * 
	 * @param suplemento the Suplemento
	 */
	public void addSuplemento(Suplemento suplemento)
	{
		if (getSuplementos() == null)
		{
			setSuplementos(new ArrayList<Suplemento>());
		}

		getSuplementos().add(suplemento);
	}

	/**
	 * Gets the first suplemento.
	 * 
	 * @return the first suplemento
	 */
	public Suplemento getFirstSuplemento()
	{
		if ((getSuplementos() != null) && !getSuplementos().isEmpty())
		{
			return getSuplementos().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the Pessoa
	 * 
	 * @param pessoa the Pessoa
	 */
	public void addPessoa(Pessoa pessoa)
	{
		if (getAmigos() == null)
		{
			setAmigos(new ArrayList<Pessoa>());
		}

		getAmigos().add(pessoa);
	}

	/**
	 * Gets the first pessoa.
	 * 
	 * @return the first pessoa
	 */
	public Pessoa getFirstPessoa()
	{
		if ((getAmigos() != null) && !getAmigos().isEmpty())
		{
			return getAmigos().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the Academia
	 * 
	 * @param academia the Academia
	 */
	public void addAcademia(Academia academia)
	{
		if (getAcademias() == null)
		{
			setAcademias(new ArrayList<Academia>());
		}

		getAcademias().add(academia);
	}

	/**
	 * Gets the first academia.
	 * 
	 * @return the first academia
	 */
	public Academia getFirstAcademia()
	{
		if ((getAcademias() != null) && !getAcademias().isEmpty())
		{
			return getAcademias().get(FIRST);
		}

		return null;
	}

	/**
	 * Adds the Foto
	 * 
	 * @param foto the Foto
	 */
	public void addFoto(Foto foto)
	{
		if (getFotos() == null)
		{
			setFotos(new ArrayList<Foto>());
		}

		getFotos().add(foto);
	}

	/**
	 * Gets the first foto.
	 * 
	 * @return the first foto
	 */
	public Foto getFirstFoto()
	{
		if ((getFotos() != null) && !getFotos().isEmpty())
		{
			return getFotos().get(FIRST);
		}

		return null;
	}

	@Override
	public String toString()
	{
		return "Pessoa [cdpessoa=" + cdpessoa + ", series=" + series + ", medidas=" + medidas + ", dietas=" + dietas
				+ ", suplementos=" + suplementos + ", amigos=" + amigos + ", academias=" + academias + ", obj=" + obj
				+ ", fotos=" + fotos + ", dtinicio=" + dtinicio + ", getCdpessoa()=" + getCdpessoa() + ", getSeries()="
				+ getSeries() + ", getMedidas()=" + getMedidas() + ", getDietas()=" + getDietas()
				+ ", getSuplementos()=" + getSuplementos() + ", getAmigos()=" + getAmigos() + ", getAcademias()="
				+ getAcademias() + ", getObj()=" + getObj() + ", getFotos()=" + getFotos() + ", getDtinicio()="
				+ getDtinicio() + ", getFirstSerie()=" + getFirstSerie() + ", getFirstMedida()=" + getFirstMedida()
				+ ", getFirstDieta()=" + getFirstDieta() + ", getFirstSuplemento()=" + getFirstSuplemento()
				+ ", getFirstPessoa()=" + getFirstPessoa() + ", getFirstAcademia()=" + getFirstAcademia()
				+ ", getFirstFoto()=" + getFirstFoto() + ", getModelAction()=" + getModelAction()
				+ ", getCreateUser()=" + getCreateUser() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyDate()=" + getModifyDate() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
