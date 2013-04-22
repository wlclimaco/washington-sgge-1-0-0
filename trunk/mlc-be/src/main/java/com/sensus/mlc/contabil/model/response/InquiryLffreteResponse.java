package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLffreteResponse extends InquiryResponse
{
    private List<Lffrete> lffrete ;


    public List<Lffrete> getLffrete() {
      return lffrete;
    }


    public void setLffrete(List<Lffrete> lffrete) {
        this.lffrete = lffrete;
}



    public String toString() {
    return "InquiryLffreteResponse [lffrete=" + lffrete
      + ", getLffrete()=" + getLffrete()
    + "]";
    }

}
