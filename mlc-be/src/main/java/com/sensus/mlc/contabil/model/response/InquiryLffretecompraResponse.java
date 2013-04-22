package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLffretecompraResponse extends InquiryResponse
{
    private List<Lffretecompra> lffretecompra ;


    public List<Lffretecompra> getLffretecompra() {
      return lffretecompra;
    }


    public void setLffretecompra(List<Lffretecompra> lffretecompra) {
        this.lffretecompra = lffretecompra;
}



    public String toString() {
    return "InquiryLffretecompraResponse [lffretecompra=" + lffretecompra
      + ", getLffretecompra()=" + getLffretecompra()
    + "]";
    }

}
