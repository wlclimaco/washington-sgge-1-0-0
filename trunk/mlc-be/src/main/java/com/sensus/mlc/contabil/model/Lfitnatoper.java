package com.sensus.mlc.contabil.model;
public class Lfitnatoper extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String codnat;
    private Integer coditnatoper;
    private Integer codempti;
    private Integer codfilialti;
    private String ufti;
    private List<Alterinse> 	listinsalt;
    /** 
    * Gets the codemp.
    * 
    * @return the codemp 
    */
    	public Integer getCodemp() 
    { 
    	return this.codemp; 
    } 

    /** 
     * Sets the codemp.
     *
     * @param codemp the new codemp 
     */
    public void setCodemp(Integer codemp)
    {
    	this.codemp = codemp; 
    }  
    /** 
    * Gets the codfilial.
    * 
    * @return the codfilial 
    */
    	public Integer getCodfilial() 
    { 
    	return this.codfilial; 
    } 

    /** 
     * Sets the codfilial.
     *
     * @param codfilial the new codfilial 
     */
    public void setCodfilial(Integer codfilial)
    {
    	this.codfilial = codfilial; 
    }  
    /** 
    * Gets the codnat.
    * 
    * @return the codnat 
    */
    	public String getCodnat() 
    { 
    	return this.codnat; 
    } 

    /** 
     * Sets the codnat.
     *
     * @param codnat the new codnat 
     */
    public void setCodnat(String codnat)
    {
    	this.codnat = codnat; 
    }  
    /** 
    * Gets the coditnatoper.
    * 
    * @return the coditnatoper 
    */
    	public Integer getCoditnatoper() 
    { 
    	return this.coditnatoper; 
    } 

    /** 
     * Sets the coditnatoper.
     *
     * @param coditnatoper the new coditnatoper 
     */
    public void setCoditnatoper(Integer coditnatoper)
    {
    	this.coditnatoper = coditnatoper; 
    }  
    /** 
    * Gets the codempti.
    * 
    * @return the codempti 
    */
    	public Integer getCodempti() 
    { 
    	return this.codempti; 
    } 

    /** 
     * Sets the codempti.
     *
     * @param codempti the new codempti 
     */
    public void setCodempti(Integer codempti)
    {
    	this.codempti = codempti; 
    }  
    /** 
    * Gets the codfilialti.
    * 
    * @return the codfilialti 
    */
    	public Integer getCodfilialti() 
    { 
    	return this.codfilialti; 
    } 

    /** 
     * Sets the codfilialti.
     *
     * @param codfilialti the new codfilialti 
     */
    public void setCodfilialti(Integer codfilialti)
    {
    	this.codfilialti = codfilialti; 
    }  
    /** 
    * Gets the ufti.
    * 
    * @return the ufti 
    */
    	public String getUfti() 
    { 
    	return this.ufti; 
    } 

    /** 
     * Sets the ufti.
     *
     * @param ufti the new ufti 
     */
    public void setUfti(String ufti)
    {
    	this.ufti = ufti; 
    }  
    /** 
    * Gets the 	listinsalt.
    * 
    * @return the 	listinsalt 
    */
    	public List<Alterinse> get	listinsalt() 
    { 
    	return this.	listinsalt; 
    } 

    /** 
     * Sets the 	listinsalt.
     *
     * @param 	listinsalt the new 	listinsalt 
     */
    public void set	listinsalt(List<Alterinse> 	listinsalt)
    {
    	this.	listinsalt = 	listinsalt; 
    }  
/* 
* (non-Javadoc) 
* @see com.sensus.common.model.SensusModel#toString() 
*/ 
@Override 
public String toString()  
{
return "Lfitnatoper[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodnat()=" + getCodnat()+ ", getCoditnatoper()=" + getCoditnatoper()+ ", getCodempti()=" 
+ getCodempti()+ ", getCodfilialti()=" + getCodfilialti()+ ", getUfti()=" + getUfti()+ ", get	listinsalt()=" + get	listinsalt()
+"]";
}
}
