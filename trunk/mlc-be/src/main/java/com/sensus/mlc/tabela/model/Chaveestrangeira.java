package com.sensus.mlc.tabela.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.gestao.model.Auditoria;

public class Chaveestrangeira extends SensusModel
{
    private String nmchaest;
    private String nmtabela;
    private String nmtabest;
    private String tpchaest;
    private List<Auditoria> auditorias;
    /**
    * Gets the nmchaest.
    *
    * @return the nmchaest
    */
    	public String getNmchaest()
    {
    	return this.nmchaest;
    }

    /**
     * Sets the nmchaest.
     *
     * @param nmchaest the new nmchaest
     */
    public void setNmchaest(String nmchaest)
    {
    	this.nmchaest = nmchaest;
    }
    /**
    * Gets the nmtabela.
    *
    * @return the nmtabela
    */
    	public String getNmtabela()
    {
    	return this.nmtabela;
    }

    /**
     * Sets the nmtabela.
     *
     * @param nmtabela the new nmtabela
     */
    public void setNmtabela(String nmtabela)
    {
    	this.nmtabela = nmtabela;
    }
    /**
    * Gets the nmtabest.
    *
    * @return the nmtabest
    */
    	public String getNmtabest()
    {
    	return this.nmtabest;
    }

    /**
     * Sets the nmtabest.
     *
     * @param nmtabest the new nmtabest
     */
    public void setNmtabest(String nmtabest)
    {
    	this.nmtabest = nmtabest;
    }
    /**
    * Gets the tpchaest.
    *
    * @return the tpchaest
    */
    	public String getTpchaest()
    {
    	return this.tpchaest;
    }

    /**
     * Sets the tpchaest.
     *
     * @param tpchaest the new tpchaest
     */
    public void setTpchaest(String tpchaest)
    {
    	this.tpchaest = tpchaest;
    }
    /**
    * Gets the auditorias.
    *
    * @return the auditorias
    */
    	public List<Auditoria> getAuditorias()
    {
    	return this.auditorias;
    }

    /**
     * Sets the auditorias.
     *
     * @param auditorias the new auditorias
     */
    public void setAuditorias(List<Auditoria> auditorias)
    {
    	this.auditorias = auditorias;
    }
/*
* (non-Javadoc)
* @see com.sensus.common.model.SensusModel#toString()
*/
@Override
public String toString()
{
return "Chaveestrangeira[ getNmchaest()=" + getNmchaest()+ ", getNmtabela()=" + getNmtabela()+ ", getNmtabest()=" + getNmtabest()+ ", getTpchaest()=" + getTpchaest()+ ", getAuditorias()=" + getAuditorias()
+"]";
}
}
