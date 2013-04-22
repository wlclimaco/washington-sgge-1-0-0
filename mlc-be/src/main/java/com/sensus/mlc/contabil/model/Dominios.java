package com.sensus.mlc.tabela.model
public class Dominios extends SensusModel
{
    private String nmtabela;
    private String nmatribu;
    private String dsdomini;
    private String vrdomini;
    private List<Auditoria> auditorias;
    private String nmtabela;
    /** 
    * Gets the nmtabela.
    * 
    * @return the nmtabela 
    */
    	public String getDominios() 
    { 
    	return this.nmtabela; 
    } 

    /** 
     * Sets the nmtabela.
     *
     * @param nmtabela the new nmtabela 
     */
    public void setDominios(String nmtabela)
    {
    	this.nmtabela = nmtabela; 
    }  
    private String nmatribu;
    /** 
    * Gets the nmatribu.
    * 
    * @return the nmatribu 
    */
    	public String getDominios() 
    { 
    	return this.nmatribu; 
    } 

    /** 
     * Sets the nmatribu.
     *
     * @param nmatribu the new nmatribu 
     */
    public void setDominios(String nmatribu)
    {
    	this.nmatribu = nmatribu; 
    }  
    private String dsdomini;
    /** 
    * Gets the dsdomini.
    * 
    * @return the dsdomini 
    */
    	public String getDominios() 
    { 
    	return this.dsdomini; 
    } 

    /** 
     * Sets the dsdomini.
     *
     * @param dsdomini the new dsdomini 
     */
    public void setDominios(String dsdomini)
    {
    	this.dsdomini = dsdomini; 
    }  
    private String vrdomini;
    /** 
    * Gets the vrdomini.
    * 
    * @return the vrdomini 
    */
    	public String getDominios() 
    { 
    	return this.vrdomini; 
    } 

    /** 
     * Sets the vrdomini.
     *
     * @param vrdomini the new vrdomini 
     */
    public void setDominios(String vrdomini)
    {
    	this.vrdomini = vrdomini; 
    }  
    private List<Auditoria> auditorias;
    /** 
    * Gets the auditorias.
    * 
    * @return the auditorias 
    */
    	public List<Auditoria> getDominios() 
    { 
    	return this.auditorias; 
    } 

    /** 
     * Sets the auditorias.
     *
     * @param auditorias the new auditorias 
     */
    public void setDominios(List<Auditoria> auditorias)
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
return "Dominios[
" getNmtabela()=" + getNmtabela()+ ", getNmatribu()=" + getNmatribu()+ ", getDsdomini()=" + getDsdomini()+ ", getVrdomini()=" + getVrdomini()+ ", getAuditorias()=" + getAuditorias()
+"]";
}
}
