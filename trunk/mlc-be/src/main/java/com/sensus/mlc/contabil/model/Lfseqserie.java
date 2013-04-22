package com.sensus.mlc.contabil.model;
public class Lfseqserie extends SensusModel
{
    private Integer codemp;
    private Integer codfilial;
    private String serie;
    private Integer codempss;
    private Integer codfilialss;
    private Integer seqserie;
    private Integer docserie;
    private String ativserie;
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
    * Gets the codempss.
    * 
    * @return the codempss 
    */
    	public Integer getCodempss() 
    { 
    	return this.codempss; 
    } 

    /** 
     * Sets the codempss.
     *
     * @param codempss the new codempss 
     */
    public void setCodempss(Integer codempss)
    {
    	this.codempss = codempss; 
    }  
    /** 
    * Gets the codfilialss.
    * 
    * @return the codfilialss 
    */
    	public Integer getCodfilialss() 
    { 
    	return this.codfilialss; 
    } 

    /** 
     * Sets the codfilialss.
     *
     * @param codfilialss the new codfilialss 
     */
    public void setCodfilialss(Integer codfilialss)
    {
    	this.codfilialss = codfilialss; 
    }  
    /** 
    * Gets the seqserie.
    * 
    * @return the seqserie 
    */
    	public Integer getSeqserie() 
    { 
    	return this.seqserie; 
    } 

    /** 
     * Sets the seqserie.
     *
     * @param seqserie the new seqserie 
     */
    public void setSeqserie(Integer seqserie)
    {
    	this.seqserie = seqserie; 
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
    * Gets the ativserie.
    * 
    * @return the ativserie 
    */
    	public String getAtivserie() 
    { 
    	return this.ativserie; 
    } 

    /** 
     * Sets the ativserie.
     *
     * @param ativserie the new ativserie 
     */
    public void setAtivserie(String ativserie)
    {
    	this.ativserie = ativserie; 
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
return "Lfseqserie[ getCodemp()=" + getCodemp()+ ", getCodfilial()=" + getCodfilial()+ ", getSerie()=" + getSerie()+ ", getCodempss()=" + getCodempss()+ ", getCodfilialss()=" + 
getCodfilialss()+ ", getSeqserie()=" + getSeqserie()+ ", getDocserie()=" + getDocserie()+ ", getAtivserie()=" + getAtivserie()+ ", getListinsalt()=" + getListinsalt()
+"]";
}
}
