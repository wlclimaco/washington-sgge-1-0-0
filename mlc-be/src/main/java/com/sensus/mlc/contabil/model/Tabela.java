package com.sensus.mlc.tabela.model
public class Tabela extends SensusModel
{
    private String nmtabela;
    private String dstabela;
    private List<Auditoria> auditorias;
    private String nmtabela;
    /** 
    * Gets the nmtabela.
    * 
    * @return the nmtabela 
    */
    	public String getTabela() 
    { 
    	return this.nmtabela; 
    } 

    /** 
     * Sets the nmtabela.
     *
     * @param nmtabela the new nmtabela 
     */
    public void setTabela(String nmtabela)
    {
    	this.nmtabela = nmtabela; 
    }  
    private String dstabela;
    /** 
    * Gets the dstabela.
    * 
    * @return the dstabela 
    */
    	public String getTabela() 
    { 
    	return this.dstabela; 
    } 

    /** 
     * Sets the dstabela.
     *
     * @param dstabela the new dstabela 
     */
    public void setTabela(String dstabela)
    {
    	this.dstabela = dstabela; 
    }  
    private List<Auditoria> auditorias;
    /** 
    * Gets the auditorias.
    * 
    * @return the auditorias 
    */
    	public List<Auditoria> getTabela() 
    { 
    	return this.auditorias; 
    } 

    /** 
     * Sets the auditorias.
     *
     * @param auditorias the new auditorias 
     */
    public void setTabela(List<Auditoria> auditorias)
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
return "Tabela[
" getNmtabela()=" + getNmtabela()+ ", getDstabela()=" + getDstabela()+ ", getAuditorias()=" + getAuditorias()
+"]";
}
}
