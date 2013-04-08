package com.sensus.mlc.gestao.model.response;
import java.util.List;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.unimed.model.Unimed;


public class UnimedRequest extends LightSelectionRequest
{
    
    private Integer parentRetry;
    
    private Unimed  unimed;
}
