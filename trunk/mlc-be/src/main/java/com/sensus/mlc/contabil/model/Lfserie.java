package com.sensus.mlc.contabil.model;
public class Lfserie extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String serie;
    private Integer docserie;
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
    * Gets the serie.
    * 
    * @return the serie 
    */
    	public String getSerie() 
    { 
    	return this.serie; 
    } 

    /** 
     * Sets the serie.
     *
     * @param serie the new serie 
     */
    public void setSerie(String serie)
    {
    	this.serie = serie; 
    }  
    /** 
    * Gets the docserie.
    * 
    * @return the docserie 
    */
    	public Integer getDocserie() 
    { 
    	return this.docserie; 
    } 

    /** 
     * Sets the docserie.
     *
     * @param docserie the new docserie 
     */
    public void setDocserie(Integer docserie)
    {
    	this.docserie = docserie; 
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
return "Lfserie[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getSerie()=" + getSerie()+ ", getDocserie()=" + getDocserie()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
