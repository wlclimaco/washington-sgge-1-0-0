package com.sensus.mlc.tabela.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.gestao.model.Auditoria;

public class Chaveprimaria extends SensusModel
{
    private String nmtabela;
    private String nmatribu;
    private Integer nrseqkey;
    private List<Auditoria> auditorias;
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
    * Gets the nrseqkey.
    *
    * @return the nrseqkey
    */
    	public Integer getNrseqkey()
    {
    	return this.nrseqkey;
    }

    /**
     * Sets the nrseqkey.
     *
     * @param nrseqkey the new nrseqkey
     */
    public void setNrseqkey(Integer nrseqkey)
    {
    	this.nrseqkey = nrseqkey;
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
return "Chaveprimaria[ getNmtabela()=" + getNmtabela()+ ", getNmatribu()=" + getNmatribu()+ ", getNrseqkey()=" + getNrseqkey()+ ", getAuditorias()=" + getAuditorias()
+"]";
}
}
