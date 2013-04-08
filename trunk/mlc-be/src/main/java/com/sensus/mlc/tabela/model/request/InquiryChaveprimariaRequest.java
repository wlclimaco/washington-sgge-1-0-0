package com.sensus.mlc.chaveprimaria.model.request;
import java.util.List;
import com.sensus.mlc.base.model.BaseSearch;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.chaveprimaria.model.Chaveprimaria;


public class InquiryChaveprimariaRequest extends InquiryPaginationRequest
{
    private BaseSearch baseSearch;
    
    private String fileName;
    
    Integer processId;
    
    private List<Chaveprimaria> chaveprimaria;
}
