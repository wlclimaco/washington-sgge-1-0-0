package com.sensus.mlc.tabela.model;

import java.util.List;

import com.sensus.common.model.SensusModel;
import com.sensus.mlc.gestao.model.Auditoria;

public class Tabela extends SensusModel
{
    private String nmtabela;
    private String dstabela;
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
    * Gets the dstabela.
    *
    * @return the dstabela
    */
    	public String getDstabela()
    {
    	return this.dstabela;
    }

    /**
     * Sets the dstabela.
     *
     * @param dstabela the new dstabela
     */
    public void setDstabela(String dstabela)
    {
    	this.dstabela = dstabela;
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
return "Tabela[ getNmtabela()=" + getNmtabela()+ ", getDstabela()=" + getDstabela()+ ", getAuditorias()=" + getAuditorias()
+"]";
}
}
