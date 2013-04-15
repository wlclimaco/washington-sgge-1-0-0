package com.sensus.mlc.gestao.model.response;
import java.util.List;

import com.sensus.common.model.response.InquiryResponse;
import com.sensus.mlc.gestao.model.Unimed;


public class InquiryUnimedResponse extends InquiryResponse
{
    private List<Unimed> unimed ;


    public List<Unimed> getUnimed() {
      return unimed;
    }


    public void setUnimed(List<Unimed> unimed) {
        this.unimed = unimed;
}



    public String toString() {
    return "InquiryUnimedResponse [unimed=" + unimed
      + ", getUnimed()=" + getUnimed()
    + "]";
    }

}
