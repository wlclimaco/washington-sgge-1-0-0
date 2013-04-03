Memo1
import java.util.List;
import com.sensus.common.model.response.InquiryResponse


public class InquiryEdit1Response extends InquiryResponse

    private List<Edit1> edit1 ;


    public List<Edit1> getEdit1() {
      return Edit1;
    }


    public void setEdit1(List<Edit1> Edit1) {
        this.Edit1 = Edit1;
}



    public String toString() {
    return "InquiryEdit1Response [Edit1=" + Edit1
      + ", getEdit1()=" + getEdit1()
    + "]";


}
