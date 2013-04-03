Memo1
package com.sensus.mlc.Edit1.model.request;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.Edit1.model.Edit1;


public class InquiryEdit1Request extends InquiryPaginationRequest

    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Edit1> edit1;
}
