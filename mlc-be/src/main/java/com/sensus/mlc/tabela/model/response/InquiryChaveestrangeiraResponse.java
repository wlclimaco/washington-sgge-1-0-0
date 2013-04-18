package com.sensus.mlc.tabela.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.tabela.model.Chaveestrangeira;


public class InquiryChaveestrangeiraResponse extends InquiryResponse
{
    private List<Chaveestrangeira> chaveestrangeira ;


    public List<Chaveestrangeira> getChaveestrangeira() {
      return chaveestrangeira;
    }


    public void setChaveestrangeira(List<Chaveestrangeira> chaveestrangeira) {
        this.chaveestrangeira = chaveestrangeira;
}



    public String toString() {
    return "InquiryChaveestrangeiraResponse [chaveestrangeira=" + chaveestrangeira
      + ", getChaveestrangeira()=" + getChaveestrangeira()
    + "]";
    }

}
