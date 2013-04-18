package com.sensus.mlc.tabela.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.gestao.model.Auditoria;

public class Atributos extends SensusModel
{
    private Integer cdtabela;
    private String nmtabela;
    private String nmatribu;
    private String dsatribu;
    private String tpatribu;
    private String dsmascar;
    private String dshelp;
    private Integer tmatribu;
    private Integer nrseqatr;
    private List<Auditoria> auditorias;
    /**
    * Gets the cdtabela.
    *
    * @return the cdtabela
    */
    	public Integer getCdtabela()
    {
    	return this.cdtabela;
    }

    /**
     * Sets the cdtabela.
     *
     * @param cdtabela the new cdtabela
     */
    public void setCdtabela(Integer cdtabela)
    {
    	this.cdtabela = cdtabela;
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
    * Gets the nmatribu.
    *
    * @return the nmatribu
    */
    	public String getNmatribu()
    {
    	return this.nmatribu;
    }

    /**
     * Sets the nmatribu.
     *
     * @param nmatribu the new nmatribu
     */
    public void setNmatribu(String nmatribu)
    {
    	this.nmatribu = nmatribu;
    }
    /**
    * Gets the dsatribu.
    *
    * @return the dsatribu
    */
    	public String getDsatribu()
    {
    	return this.dsatribu;
    }

    /**
     * Sets the dsatribu.
     *
     * @param dsatribu the new dsatribu
     */
    public void setDsatribu(String dsatribu)
    {
    	this.dsatribu = dsatribu;
    }
    /**
    * Gets the tpatribu.
    *
    * @return the tpatribu
    */
    	public String getTpatribu()
    {
    	return this.tpatribu;
    }

    /**
     * Sets the tpatribu.
     *
     * @param tpatribu the new tpatribu
     */
    public void setTpatribu(String tpatribu)
    {
    	this.tpatribu = tpatribu;
    }
    /**
    * Gets the dsmascar.
    *
    * @return the dsmascar
    */
    	public String getDsmascar()
    {
    	return this.dsmascar;
    }

    /**
     * Sets the dsmascar.
     *
     * @param dsmascar the new dsmascar
     */
    public void setDsmascar(String dsmascar)
    {
    	this.dsmascar = dsmascar;
    }
    /**
    * Gets the dshelp.
    *
    * @return the dshelp
    */
    	public String getDshelp()
    {
    	return this.dshelp;
    }

    /**
     * Sets the dshelp.
     *
     * @param dshelp the new dshelp
     */
    public void setDshelp(String dshelp)
    {
    	this.dshelp = dshelp;
    }
    /**
    * Gets the tmatribu.
    *
    * @return the tmatribu
    */
    	public Integer getTmatribu()
    {
    	return this.tmatribu;
    }

    /**
     * Sets the tmatribu.
     *
     * @param tmatribu the new tmatribu
     */
    public void setTmatribu(Integer tmatribu)
    {
    	this.tmatribu = tmatribu;
    }
    /**
    * Gets the nrseqatr.
    *
    * @return the nrseqatr
    */
    	public Integer getNrseqatr()
    {
    	return this.nrseqatr;
    }

    /**
     * Sets the nrseqatr.
     *
     * @param nrseqatr the new nrseqatr
     */
    public void setNrseqatr(Integer nrseqatr)
    {
    	this.nrseqatr = nrseqatr;
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
return "Atributos[ getCdtabela()=" + getCdtabela()+ ", getNmtabela()=" + getNmtabela()+ ", getNmatribu()=" + getNmatribu()+ ", getDsatribu()=" + getDsatribu()+ ", getTpatribu()=" + getTpatribu()+ ", getDsmascar()=" + getDsmascar()+ ", getDshelp()=" + getDshelp()+ ", getTmatribu()=" + getTmatribu()+ ", getNrseqatr()=" + getNrseqatr()+ ", getAuditorias()=" + getAuditorias()
+"]";
}
}
