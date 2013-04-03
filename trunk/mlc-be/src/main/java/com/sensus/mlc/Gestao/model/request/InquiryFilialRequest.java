Memo1
package com.sensus.mlc.Filial.model.request;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.Filial.model.Filial;


public class InquiryFilialRequest extends InquiryPaginationRequest

    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Filial> filial;
}
