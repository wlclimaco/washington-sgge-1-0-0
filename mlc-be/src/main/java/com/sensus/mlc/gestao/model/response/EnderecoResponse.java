package com.sensus.mlc.endereco.model.response;
import java.util.List;
import com.sensus.common.model.response.Response;
import com.sensus.mlc.endereco.model.Endereco;


public class EnderecoResponse extends Response
{
    
    private Integer parentRetry;
    
    private List<Endereco> endereco ;
}
