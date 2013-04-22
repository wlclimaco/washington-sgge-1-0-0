package com.sensus.mlc.tabela.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryTabelaResponse extends InquiryResponse
{
    private List<Tabela> tabela ;


    public List<Tabela> getTabela() {
      return tabela;
    }


    public void setTabela(List<Tabela> tabela) {
        this.tabela = tabela;
}



    public String toString() {
    return "InquiryTabelaResponse [tabela=" + tabela
      + ", getTabela()=" + getTabela()
    + "]";
    }

}
