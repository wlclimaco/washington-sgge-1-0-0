package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.gestao.model.Uf;


public class InquiryUfResponse extends InquiryResponse
{
    private List<Uf> uf ;


    public List<Uf> getUf() {
      return uf;
    }


    public void setUf(List<Uf> uf) {
        this.uf = uf;
}



    public String toString() {
    return "InquiryUfResponse [uf=" + uf
      + ", getUf()=" + getUf()
    + "]";
    }

}
