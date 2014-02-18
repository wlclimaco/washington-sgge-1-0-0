package com.sensus.lc.exercicio.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.grupomuscular.model.Grupomuscular;

/**
 * The Class Exercicio.
 */
@SuppressWarnings("serial")
public class Exercicio extends SensusModel
{
	/** The Constant FIRST. */
	private static final Integer FIRST = 0;

	/** The cdexerc. */
	private Integer cdexerc;

	/** The nmexerc. */
	private String nmexerc;

	/** The dsexerc. */
	private String dsexerc;

	/** The createdate. */
	private Date createdate;

	/** The createuser. */
	private String createuser;

	/** The tenantid. */
	private Integer tenantid;

	/** The tenantid. */
	private Integer userid;

	/** The ftexerc. */
	private List<Foto> ftexerc;

	/** The grupomuscular. */
	private List<Grupomuscular> grupomuscular;

	/**
	 * Instantiates a new exercicio.
	 * 
	 * @param cdexerc the cdexerc
	 * @param ftexerc the ftexerc
	 * @param grupomuscular the grupomuscular
	 */
	public Exercicio(Integer cdexerc, List<Foto> ftexerc, List<Grupomuscular> grupomuscular)
	{
		super();
		this.cdexerc = cdexerc;
		this.ftexerc = ftexerc;
		this.grupomuscular = grupomuscular;
	}

	/**
	 * Instantiates a new exercicio.
	 * 
	 * @param cdexerc the cdexerc
	 * @param ftexerc the ftexerc
	 */
	public Exercicio(Integer cdexerc, List<Foto> ftexerc)
	{
		super();
		this.cdexerc = cdexerc;
		this.ftexerc = ftexerc;
	}

	/**
	 * Instantiates a new exercicio.
	 * 
	 * @param cdexerc the cdexerc
	 */
	public Exercicio(Integer cdexerc)
	{
		super();
		this.cdexerc = cdexerc;
	}

	/**
	 * Instantiates a new exercicio.
	 * 
	 * @param cdexerc the cdexerc
	 * @param nmexerc the nmexerc
	 * @param dsexerc the dsexerc
	 * @param ftexerc the ftexerc
	 * @param grupomuscular the grupomuscular
	 */
	public Exercicio(Integer cdexerc, String nmexerc, String dsexerc, List<Foto> ftexerc,
			List<Grupomuscular> grupomuscular)
	{
		super();
		this.cdexerc = cdexerc;
		this.nmexerc = nmexerc;
		this.dsexerc = dsexerc;
		this.ftexerc = ftexerc;
		this.grupomuscular = grupomuscular;
	}

	public Date getCreatedate()
	{
		return createdate;
	}

	public void setCreatedate(Date createdate)
	{
		this.createdate = createdate;
	}

	public String getCreateuser()
	{
		return createuser;
	}

	public void setCreateuser(String createuser)
	{
		this.createuser = createuser;
	}

	public Integer getTenantid()
	{
		return tenantid;
	}

	public void setTenantid(Integer tenantid)
	{
		this.tenantid = tenantid;
	}

	public Integer getUserid()
	{
		return userid;
	}

	public void setUserid(Integer userid)
	{
		this.userid = userid;
	}

	/**
	 * Gets the grupomuscular.
	 * 
	 * @return the grupomuscular
	 */
	public List<Grupomuscular> getGrupomuscular()
	{
		return grupomuscular;
	}

	/**
	 * Sets the grupomuscular.
	 * 
	 * @param grupomuscular the new grupomuscular
	 */
	public void setGrupomuscular(List<Grupomuscular> grupomuscular)
	{
		this.grupomuscular = grupomuscular;
	}

	/**
	 * Gets the cdexerc.
	 * 
	 * @return the cdexerc
	 */
	public Integer getCdexerc()
	{
		return cdexerc;
	}

	/**
	 * Sets the cdexerc.
	 * 
	 * @param cdexerc the new cdexerc
	 */
	public void setCdexerc(Integer cdexerc)
	{
		this.cdexerc = cdexerc;
	}

	/**
	 * Gets the nmexerc.
	 * 
	 * @return the nmexerc
	 */
	public String getNmexerc()
	{
		return nmexerc;
	}

	/**
	 * Sets the nmexerc.
	 * 
	 * @param nmexerc the new nmexerc
	 */
	public void setNmexerc(String nmexerc)
	{
		this.nmexerc = nmexerc;
	}

	/**
	 * Gets the dsexerc.
	 * 
	 * @return the dsexerc
	 */
	public String getDsexerc()
	{
		return dsexerc;
	}

	/**
	 * Sets the dsexerc.
	 * 
	 * @param dsexerc the new dsexerc
	 */
	public void setDsexerc(String dsexerc)
	{
		this.dsexerc = dsexerc;
	}

	/**
	 * Gets the ftexerc.
	 * 
	 * @return the ftexerc
	 */
	public List<Foto> getFtexerc()
	{
		return ftexerc;
	}

	/**
	 * Sets the ftexerc.
	 * 
	 * @param ftexerc the new ftexerc
	 */
	public void setFtexerc(List<Foto> ftexerc)
	{
		this.ftexerc = ftexerc;
	}

	/**
	 * Adds the Grupomuscular.
	 * 
	 * @param grupomuscular the Grupomuscular
	 */
	public void addGrupomuscular(Grupomuscular grupomuscular)
	{
		if (getGrupomuscular() == null)
		{
			setGrupomuscular(new ArrayList<Grupomuscular>());
		}

		getGrupomuscular().add(grupomuscular);
	}

	/**
	 * Gets the first grupomuscular.
	 * 
	 * @return the first grupomuscular
	 */
	public Grupomuscular getFirstGrupomuscular()
	{
		if ((getGrupomuscular() != null) && !getGrupomuscular().isEmpty())
		{
			return getGrupomuscular().get(FIRST);
		}

		return null;
	}

	@Override
	public String toString()
	{
		return "Exercicio [cdexerc=" + cdexerc + ", nmexerc=" + nmexerc + ", dsexerc=" + dsexerc + ", createdate="
				+ createdate + ", createuser=" + createuser + ", tenantid=" + tenantid + ", userid=" + userid
				+ ", ftexerc=" + ftexerc + ", grupomuscular=" + grupomuscular + ", getCreatedate()=" + getCreatedate()
				+ ", getCreateuser()=" + getCreateuser() + ", getTenantid()=" + getTenantid() + ", getUserid()="
				+ getUserid() + ", getGrupomuscular()=" + getGrupomuscular() + ", getCdexerc()=" + getCdexerc()
				+ ", getNmexerc()=" + getNmexerc() + ", getDsexerc()=" + getDsexerc() + ", getFtexerc()="
				+ getFtexerc() + ", getFirstGrupomuscular()=" + getFirstGrupomuscular() + "]";
	}
}
