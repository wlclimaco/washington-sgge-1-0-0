package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.gestao.model.Filial;


public class InquiryFilialResponse extends InquiryResponse
{
    private List<Filial> filial ;


    public List<Filial> getFilial() {
      return filial;
    }


    public void setFilial(List<Filial> filial) {
        this.filial = filial;
}



    public String toString() {
    return "InquiryFilialResponse [filial=" + filial
      + ", getFilial()=" + getFilial()
    + "]";
    }

}
