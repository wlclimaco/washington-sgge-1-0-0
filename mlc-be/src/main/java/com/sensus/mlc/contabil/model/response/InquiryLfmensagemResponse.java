package com.sensus.mlc.contabil.model.response;
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryLfmensagemResponse extends InquiryResponse
{
    private List<Lfmensagem> lfmensagem ;


    public List<Lfmensagem> getLfmensagem() {
      return lfmensagem;
    }


    public void setLfmensagem(List<Lfmensagem> lfmensagem) {
        this.lfmensagem = lfmensagem;
}



    public String toString() {
    return "InquiryLfmensagemResponse [lfmensagem=" + lfmensagem
      + ", getLfmensagem()=" + getLfmensagem()
    + "]";
    }

}
