package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfitcompraResponse extends InquiryResponse
{
    private List<Lfitcompra> lfitcompra ;


    public List<Lfitcompra> getLfitcompra() {
      return lfitcompra;
    }


    public void setLfitcompra(List<Lfitcompra> lfitcompra) {
        this.lfitcompra = lfitcompra;
}



    public String toString() {
    return "InquiryLfitcompraResponse [lfitcompra=" + lfitcompra
      + ", getLfitcompra()=" + getLfitcompra()
    + "]";
    }

}
