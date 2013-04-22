package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLffretevendaResponse extends InquiryResponse
{
    private List<Lffretevenda> lffretevenda ;


    public List<Lffretevenda> getLffretevenda() {
      return lffretevenda;
    }


    public void setLffretevenda(List<Lffretevenda> lffretevenda) {
        this.lffretevenda = lffretevenda;
}



    public String toString() {
    return "InquiryLffretevendaResponse [lffretevenda=" + lffretevenda
      + ", getLffretevenda()=" + getLffretevenda()
    + "]";
    }

}
