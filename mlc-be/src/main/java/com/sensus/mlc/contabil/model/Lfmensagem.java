package com.sensus.mlc.contabil.model;
public class Lfmensagem extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private Integer codmens;
    private String mens;
    private List<Alterinse> listinsalt;
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
    * Gets the codmens.
    * 
    * @return the codmens 
    */
    	public Integer getCodmens() 
    { 
    	return this.codmens; 
    } 

    /** 
     * Sets the codmens.
     *
     * @param codmens the new codmens 
     */
    public void setCodmens(Integer codmens)
    {
    	this.codmens = codmens; 
    }  
    /** 
    * Gets the mens.
    * 
    * @return the mens 
    */
    	public String getMens() 
    { 
    	return this.mens; 
    } 

    /** 
     * Sets the mens.
     *
     * @param mens the new mens 
     */
    public void setMens(String mens)
    {
    	this.mens = mens; 
    }  
    /** 
    * Gets the listinsalt.
    * 
    * @return the listinsalt 
    */
    	public List<Alterinse> getListinsalt() 
    { 
    	return this.listinsalt; 
    } 

    /** 
     * Sets the listinsalt.
     *
     * @param listinsalt the new listinsalt 
     */
    public void setListinsalt(List<Alterinse> listinsalt)
    {
    	this.listinsalt = listinsalt; 
    }  
/* 
* (non-Javadoc) 
* @see com.sensus.common.model.SensusModel#toString() 
*/ 
@Override 
public String toString()  
{
return "Lfmensagem[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodmens()=" + getCodmens()+ ", getMens()=" + getMens()+ ", getListinsalt()=" + 
getListinsalt()
+"]";
}
}
