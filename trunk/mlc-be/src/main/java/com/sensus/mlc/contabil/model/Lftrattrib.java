package com.sensus.mlc.contabil.model;
public class Lftrattrib extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String codtrattrib;
    private String desctrattrib;
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
    * Gets the codtrattrib.
    * 
    * @return the codtrattrib 
    */
    	public String getCodtrattrib() 
    { 
    	return this.codtrattrib; 
    } 

    /** 
     * Sets the codtrattrib.
     *
     * @param codtrattrib the new codtrattrib 
     */
    public void setCodtrattrib(String codtrattrib)
    {
    	this.codtrattrib = codtrattrib; 
    }  
    /** 
    * Gets the desctrattrib.
    * 
    * @return the desctrattrib 
    */
    	public String getDesctrattrib() 
    { 
    	return this.desctrattrib; 
    } 

    /** 
     * Sets the desctrattrib.
     *
     * @param desctrattrib the new desctrattrib 
     */
    public void setDesctrattrib(String desctrattrib)
    {
    	this.desctrattrib = desctrattrib; 
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
return "Lftrattrib[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodtrattrib()=" + getCodtrattrib()+ ", getDesctrattrib()=" + getDesctrattrib()+ ", 
getListinsalt()=" + getListinsalt()
+"]";
}
}
