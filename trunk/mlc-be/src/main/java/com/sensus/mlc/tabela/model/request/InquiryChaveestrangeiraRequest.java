package com.sensus.mlc.chaveestrangeira.model.request;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.chaveestrangeira.model.Chaveestrangeira;


public class InquiryChaveestrangeiraRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Chaveestrangeira> chaveestrangeira;
}
