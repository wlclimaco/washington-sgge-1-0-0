package com.sensus.mlc.tabela.model
public class Chaveestrangeira extends SensusModel
{
    private String nmchaest;
    private String nmtabela;
    private String nmtabest;
    private String tpchaest;
    private List<Auditoria> auditorias;
    private String nmchaest;
    /** 
    * Gets the nmchaest.
    * 
    * @return the nmchaest 
    */
    	public String getChaveestrangeira() 
    { 
    	return this.nmchaest; 
    } 

    /** 
     * Sets the nmchaest.
     *
     * @param nmchaest the new nmchaest 
     */
    public void setChaveestrangeira(String nmchaest)
    {
    	this.nmchaest = nmchaest; 
    }  
    private String nmtabela;
    /** 
    * Gets the nmtabela.
    * 
    * @return the nmtabela 
    */
    	public String getChaveestrangeira() 
    { 
    	return this.nmtabela; 
    } 

    /** 
     * Sets the nmtabela.
     *
     * @param nmtabela the new nmtabela 
     */
    public void setChaveestrangeira(String nmtabela)
    {
    	this.nmtabela = nmtabela; 
    }  
    private String nmtabest;
    /** 
    * Gets the nmtabest.
    * 
    * @return the nmtabest 
    */
    	public String getChaveestrangeira() 
    { 
    	return this.nmtabest; 
    } 

    /** 
     * Sets the nmtabest.
     *
     * @param nmtabest the new nmtabest 
     */
    public void setChaveestrangeira(String nmtabest)
    {
    	this.nmtabest = nmtabest; 
    }  
    private String tpchaest;
    /** 
    * Gets the tpchaest.
    * 
    * @return the tpchaest 
    */
    	public String getChaveestrangeira() 
    { 
    	return this.tpchaest; 
    } 

    /** 
     * Sets the tpchaest.
     *
     * @param tpchaest the new tpchaest 
     */
    public void setChaveestrangeira(String tpchaest)
    {
    	this.tpchaest = tpchaest; 
    }  
    private List<Auditoria> auditorias;
    /** 
    * Gets the auditorias.
    * 
    * @return the auditorias 
    */
    	public List<Auditoria> getChaveestrangeira() 
    { 
    	return this.auditorias; 
    } 

    /** 
     * Sets the auditorias.
     *
     * @param auditorias the new auditorias 
     */
    public void setChaveestrangeira(List<Auditoria> auditorias)
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
return "Chaveestrangeira[
" getNmchaest()=" + getNmchaest()+ ", getNmtabela()=" + getNmtabela()+ ", getNmtabest()=" + getNmtabest()+ ", getTpchaest()=" + getTpchaest()+ ", getAuditorias()=" + 
getAuditorias()
+"]";
}
}
