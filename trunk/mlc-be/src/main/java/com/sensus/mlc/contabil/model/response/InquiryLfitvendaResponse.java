package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfitvendaResponse extends InquiryResponse
{
    private List<Lfitvenda> lfitvenda ;


    public List<Lfitvenda> getLfitvenda() {
      return lfitvenda;
    }


    public void setLfitvenda(List<Lfitvenda> lfitvenda) {
        this.lfitvenda = lfitvenda;
}



    public String toString() {
    return "InquiryLfitvendaResponse [lfitvenda=" + lfitvenda
      + ", getLfitvenda()=" + getLfitvenda()
    + "]";
    }

}
