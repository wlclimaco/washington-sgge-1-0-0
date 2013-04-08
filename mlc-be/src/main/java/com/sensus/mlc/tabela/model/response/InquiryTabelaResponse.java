package com.sensus.mlc.tabela.model.response;

import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.tabela.model.Tabela;


// TODO: Auto-generated Javadoc
/**
 * The Class InquiryTabelaResponse.
 */
public class InquiryTabelaResponse extends InquiryResponse
{

    /** The tabela. */
    private List<Tabela> tabela ;


    /**
     * Gets the tabela.
     *
     * @return the tabela
     */
    public List<Tabela> getTabela() {
      return tabela;
    }


    /**
     * Sets the tabela.
     *
     * @param Tabela the new tabela
     */
    public void setTabela(List<Tabela> Tabela) {
        this.tabela = Tabela;
}



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    return "InquiryTabelaResponse [Tabela=" + tabela
      + ", getTabela()=" + getTabela()
    + "]";
    }

}