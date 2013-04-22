package com.sensus.mlc.contabil.model;
public class Lfsittrib extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String codsittrib;
    private String impsittrib;
    private String descsittrib;
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
    * Gets the codsittrib.
    * 
    * @return the codsittrib 
    */
    	public String getCodsittrib() 
    { 
    	return this.codsittrib; 
    } 

    /** 
     * Sets the codsittrib.
     *
     * @param codsittrib the new codsittrib 
     */
    public void setCodsittrib(String codsittrib)
    {
    	this.codsittrib = codsittrib; 
    }  
    /** 
    * Gets the impsittrib.
    * 
    * @return the impsittrib 
    */
    	public String getImpsittrib() 
    { 
    	return this.impsittrib; 
    } 

    /** 
     * Sets the impsittrib.
     *
     * @param impsittrib the new impsittrib 
     */
    public void setImpsittrib(String impsittrib)
    {
    	this.impsittrib = impsittrib; 
    }  
    /** 
    * Gets the descsittrib.
    * 
    * @return the descsittrib 
    */
    	public String getDescsittrib() 
    { 
    	return this.descsittrib; 
    } 

    /** 
     * Sets the descsittrib.
     *
     * @param descsittrib the new descsittrib 
     */
    public void setDescsittrib(String descsittrib)
    {
    	this.descsittrib = descsittrib; 
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
return "Lfsittrib[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getCodsittrib()=" + getCodsittrib()+ ", getImpsittrib()=" + getImpsittrib()+ ", getDescsittrib()=" + 
getDescsittrib()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
